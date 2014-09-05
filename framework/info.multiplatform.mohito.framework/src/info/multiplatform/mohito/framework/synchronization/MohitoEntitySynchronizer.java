/** 
 * Copyright (c) 2012-2014 MOHITO Project
 *
 * Licensed under the EUPL V.1.1
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package info.multiplatform.mohito.framework.synchronization;

import info.multiplatform.mohito.framework.MohitoEntity;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import info.multiplatform.mohito.framework.StorageManager;
import info.multiplatform.mohito.framework.exceptions.DataLayerException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**Synchronization mechanism for the MOHITO-Entities managed by a storage manager.
 * @author hgroenda
 *
 */
public class MohitoEntitySynchronizer {
	/** The storage manager used to access the data stores. */
	protected final StorageManager<?> storageManager;
	/** Synchronized entity types. */
	protected final Class<? extends MohitoEntity<?>>[] synchronizedEntityTypes;

	/**Creates a new instance.
	 * @param storageManager Manager used to access local and remote data stores.
	 * @param managedClasses The entity types to synchronize.
	 */
	public MohitoEntitySynchronizer(StorageManager<?> storageManager,
			Class<? extends MohitoEntity<?>>[] managedClasses) {
		assert(storageManager != null);
		this.storageManager = storageManager;
		this.synchronizedEntityTypes = managedClasses;
	}

	/**Starts the synchronization for all entities.
	 * If pre-existing conflicts are provided, their resolution as well as the 
	 * existence of unresolved conflicts is checked again. All non-conflicting
	 * entities are synchronized after the successful completion of this method.
	 * @param previousConflicts A collection of pre-existing conflicts. Those can be resolved or unresolved.
	 * @return List of synchronization conflicts. Does not contain automatically resolved conflicts.
	 * @throws DataLayerException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<SynchronizationConflict> synchronize(final Collection<SynchronizationConflict> previousConflicts) throws DataLayerException {
		try {
			if (! (storageManager.isLocalAvailable() & storageManager.isRemoteAvailable() ) ) {
				throw new IllegalStateException("Synchronization requires two available data stores. Either the local or remote data store was not available. Call again if both are available.");
			}
			Collection<SynchronizationConflict> result = new ArrayList<SynchronizationConflict>();
			if (previousConflicts != null) {
				result.addAll(previousConflicts);
			}
			if (storageManager.isSynchronizationRunning()){
				throw new IllegalStateException("Synchronisation is still runnning! Wait until the synchronisation process terminated!");
			} else {
				storageManager.setSynchronizationRunning(true);
			}
			MohitoEntityDao localDao, remoteDao;
			for (Class<? extends MohitoEntity<?>> entityType : synchronizedEntityTypes) {
				localDao = (MohitoEntityDao) storageManager.getLocalStorageManager().getEntityDao(entityType);
				remoteDao = (MohitoEntityDao) storageManager.getRemoteStorageManager().getEntityDao(entityType);
				
				List<MohitoEntity<?>> localEntities = new ArrayList();
				localEntities.addAll(localDao.getByCriteria(null));
				MohitoEntity remoteEntity;
				
				for (MohitoEntity entity : localEntities) {
					remoteEntity = remoteDao.getById(entity.getId());
					if (isSynchronizationRequired(entity, remoteEntity)) {
						if (isConflicting(entity, remoteEntity)) {
							if (canResolveAutomatically(entity, remoteEntity)) {
								doResolveAutomatically(entity, remoteEntity, localDao, remoteDao);
							} else {
								handlePreviousConflicts(previousConflicts, new SynchronizationConflict(entity, remoteEntity));
								result.add(new SynchronizationConflict(entity, remoteEntity));
							}
						} else { // ! isConflicting
							synchronize(entity, remoteEntity, localDao, remoteDao);
						}
					}
				}
				
				List<MohitoEntity<?>> remoteEntities = new ArrayList();
				remoteEntities.addAll(remoteDao.getByCriteria(null));
				MohitoEntity localEntity;
				
				for (MohitoEntity entity : remoteEntities) {
					localEntity = localDao.getById(entity.getId());
					if (isSynchronizationRequired(localEntity, entity)) {
						if (isConflicting(localEntity, entity)) {
							if (canResolveAutomatically(localEntity, entity)) {
								doResolveAutomatically(localEntity, entity, localDao, remoteDao);
							} else {
								handlePreviousConflicts(previousConflicts, new SynchronizationConflict(localEntity, entity));
								result.add(new SynchronizationConflict(localEntity, entity));
							}
						} else { // ! isConflicting
							synchronize(localEntity, entity, localDao, remoteDao);
						}
					}
				}
				
			}
			return result;
		} finally {
			storageManager.setSynchronizationRunning(false);
		}
	}
	
	/**Checks if a synchronization is required for the provided entities.
	 * @param localEntity The local entity.
	 * @param remoteEntity The remote entity.
	 * @return <code>true</code> if a synchronization is required, <code>false</code> otherwise.
	 */
	protected boolean isSynchronizationRequired(final MohitoEntity<?> localEntity, final MohitoEntity<?> remoteEntity) {
		if (localEntity == null || remoteEntity == null) {
			return true;
		}
		if (localEntity.mIsDirty()) {
			return true;
		}
		if(localEntity.getLastModificationTime() == null || localEntity.getLastUpdateTime() == null) {
			return false;
		}
		if (remoteEntity.getLastModificationTime() != null && 
			localEntity.getLastUpdateTime() < remoteEntity.getLastModificationTime()) {
			return true;
		}
		if (remoteEntity.getLastModificationTime() != null &&
			localEntity.getLastModificationTime() != remoteEntity.getLastModificationTime()) {
			return true;
		}
		return false;
	}
	
	/**Checks if the provided entities have conflicting changes.
	 * @param localEntity The local entity.
	 * @param remoteEntity The remote entity.
	 * @return <code>true</code> if there are conflicting changes , <code>false</code> otherwise.
	 */
	protected boolean isConflicting(final MohitoEntity<?> localEntity, final MohitoEntity<?> remoteEntity) {
		if (localEntity == null || remoteEntity == null) {
			return false;
		}
		
		if(remoteEntity.getLastModificationTime() == null) {
			return false;
		}
			
		if (localEntity.mIsDirty() 
				&& localEntity.getLastUpdateTime() < remoteEntity.getLastModificationTime()) {
			return true;
		}
		return false;
	}
	
	/**Checks if a conflict between the entities can be automatically resolved.
	 * @param localEntity The local entity.
	 * @param remoteEntity The remote entity.
	 * @return <code>true</code> if it can be automatically resolved, <code>false</code> otherwise.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected boolean canResolveAutomatically(final MohitoEntity localEntity, final MohitoEntity remoteEntity) throws DataLayerException {
		if (localEntity == null || remoteEntity == null) {
			return true;
		}
		if (localEntity.domainContentEquals(remoteEntity)) {
			return true;
		}
		if (storageManager.isHandleConflictsAutomatically()) {
			return true;
		} else {
			return false;
		}
	}	
	
	/**Resolves a conflict automatically, depending on the strategy set in the storage manager.
	 * @param localEntity The local entity.
	 * @param remoteEntity The remote entity.
	 * @param localDao DAO for local entities.
	 * @param remoteDao DAO for remote entities.
	 * @throws DataLayerException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <PK> void doResolveAutomatically( final MohitoEntity localEntity, final MohitoEntity remoteEntity,
			MohitoEntityDao<MohitoEntity<PK>, PK> localDao, MohitoEntityDao<MohitoEntity<PK>, PK> remoteDao) throws DataLayerException {
		
		if (localEntity.getLastUpdateTime() < remoteEntity.getLastModificationTime()
				&& localEntity.mIsDirty()) { // modification conflict
			if (storageManager.isHandleConflictsAutomatically()) {
				if (storageManager.isServerWinsInAutomatedConflictResolution()) {
					// Server wins
					// case overwrite locally
					localDao.update(remoteEntity);
					
				} else {
					// Client wins
					// case overwrite remotely
					remoteDao.update(localEntity);
				}
			} else {
				throw new SynchronizationRequiredException();
			}
		}
	}

	/**Synchronizes a local entity with a remote store. Data is only modified if <code>true</code> is returned.
	 * @param localEntity The local entity. 
	 * @param remoteDao The remote store. 
	 * @return <code>true</code> if the synchronization was successful, <code>false</code> if there are conflicts.
	 * @throws DataLayerException 
	 */
	private <PK> boolean synchronize(MohitoEntity<PK> localEntity, MohitoEntity<PK> remoteEntity, 
			MohitoEntityDao<MohitoEntity<PK>, PK> localDao, MohitoEntityDao<MohitoEntity<PK>, PK> remoteDao) throws DataLayerException {
		
		Date date= new Date();
		
		if (localEntity == null) {	// case download from server
			remoteEntity.setLastUpdateTime(date.getTime());
			localDao.create(remoteEntity);
			localEntity = localDao.getById(remoteEntity.getId());
			return true;
		}
		if (remoteEntity == null) {	// case upload to server
			localEntity.setLastUpdateTime(date.getTime());
			remoteDao.create(localEntity);
			remoteEntity = remoteDao.getById(localEntity.getId());
			return true;
		}
		// case remoteDao deletionPending is set
		// delete remote and local
		if (remoteEntity.mIsDeletionPending()) {
			remoteDao.delete(remoteEntity);
			
			if(localDao != null && localEntity != null) {
				localDao.delete(localEntity);
			}
			return true;
		}
		// case localDao deletionPending is set
		// delete local and remote
		if (localEntity.mIsDeletionPending()) {
			localDao.delete(localEntity);
			
			if(remoteDao != null && remoteEntity != null) {
				remoteDao.delete(remoteEntity);
			}
			return true;
		}
		
		if(localEntity.mIsDirty()) {
			remoteDao.update(localEntity);
		}
		
		if(remoteEntity.mIsDirty()) {
			localDao.update(remoteEntity);
		}
		
		return true;
	}

	/**
	 * The current conflict is added to the previous conflict list. It's checked if the same conflict exists. The old 
	 * conflict will be deleted and the current conflict is added. 
	 * @param previousConflicts A collection of pre-existing conflicts. Those can be resolved or unresolved.
	 * @param currentConflict The current conflict.
	 */
	private void handlePreviousConflicts(final Collection<SynchronizationConflict> previousConflicts, SynchronizationConflict currentConflict){
		ArrayList<SynchronizationConflict> toRemove = new ArrayList<SynchronizationConflict>();
		for (SynchronizationConflict conflict : previousConflicts){
			if (conflict.getLocalEntity().getId() == currentConflict.getLocalEntity().getId()){
				toRemove.add(conflict);
			}
		}
		previousConflicts.removeAll(toRemove);
	}

}
