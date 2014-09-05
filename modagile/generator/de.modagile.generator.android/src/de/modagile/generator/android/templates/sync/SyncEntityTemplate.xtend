/** 
 * Copyright (c) 2012-2014 ModAgile Mobile (http://www.modagile-mobile.de/) and others.
 *
 * Licensed under Eclipse Public License - v 1.0
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.modagile.generator.android.templates.sync

import com.google.inject.Inject

import de.modagile.metamodel.app.MobileApp

import java.util.ArrayList
import java.util.List

import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.Set
import java.util.HashSet

class SyncEntityTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
		
	/**
	 * Creates the SuperClass SyncEntity 
	 */
	def generateSuperClassSyncEntitiy(String packagePrefix, MobileApp m, IFileSystemAccess fsa, String outputConfiguration) {
		fsa.generateFile(packagePrefix+"sync/SyncEntity.java", outputConfiguration, generateSuperClassSyncEntityCode(packagePrefix.replaceAll("/",".")+"sync", m))
	}
	
	/*
	 * Creates a SyncSubclass for a specific entity
	 * For SyncDomainEntities methods see section two in this file
	 * */
	def generateSyncDomainEntitiy(String packagePrefix, EClass domainEntity, IFileSystemAccess fsa, String outputConfiguration) {
		fsa.generateFile(packagePrefix+"sync/Sync"+domainEntity.name + ".java", outputConfiguration, generateSyncDomainEntityCode(packagePrefix.replaceAll("/",".")+"sync", domainEntity))
	}
	
	def generateSuperClassSyncEntityCode(String packageName, MobileApp m){
		var Set<String> imports  = new HashSet<String>();
		getImports(imports, packageName);
		'''
		«generateSuperClassSyncEntityClass("SyncEntityTemplate", packageName, imports, m, null, new ArrayList())»
		'''
	}
	
	def getImports(Set<String> imports, String packageName){
		imports.add("java.util.ArrayList");
		imports.add("java.util.Collection");
		imports.add("java.util.List");
		
		imports.add("android.accounts.NetworkErrorException");
		imports.add("android.content.Context");
		imports.add("android.util.Log");
		
		imports.add(packageName.replace("sync","manager")+".EntityManager");
		imports.add(packageName.replace("sync","model")+".Entity");
		imports.add(packageName.replace("sync","rest")+".BaseRestClient");
	}
	
	
	def generateSuperClassSyncEntityClass(String templateName, String packageName, Set<String> imports, MobileApp m,
													 							    String inheritance,
													 								List<String> interfaces){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("SyncEntity<ENTITY extends Entity>", inheritance , interfaces, true)»
		«generateSyncEntityMethods(m)»
		}
		'''
	}
	
	def generateSyncEntityMethods(MobileApp m){
		'''
		«generateSyncEntityAttributes()»
		«generateSyncEntityConstructor()»
		«generateAbstractMethods»
		«sync»
		«removeDeletedEntriesFromServer»
		«createOrUpdateEntries»
		«deleteOutdatesLocalData»
		«createLocally»
		«updateLocalToRemote»
		«updateRemoteToLocal»
		 «pushNewLocalDataToServer»
		'''
	}
	
	def generateSyncEntityAttributes(){
		'''
		protected final Context mCtx;
		private final String TAG;
		protected final EntityManager<ENTITY> entityManager;
		protected final BaseRestClient<ENTITY> restClient;
		'''
	}
	
	def generateSyncEntityConstructor(){
		'''
		/**
		 * Constructor to set up the object instance
		 * 
		 * @param ctx
		 *           The Android Context
		 */
		public SyncEntity(Context ctx, BaseRestClient<ENTITY> restClient, EntityManager<ENTITY> entityManager) {
			this.TAG = getClass().getSimpleName();
			this.mCtx = ctx;
			this.entityManager = entityManager;

			if (restClient == null) {
				this.restClient = createRestClient(mCtx);
			} else {
				this.restClient = restClient;
			}
		}
		'''
	}
	
	def sync(){
		'''
		/**
		 * Synchronizes local and remote data
		 * 
		 * @throws NetworkErrorException
		 *            If there is a communication problem with the server
		 */
		public void sync() throws NetworkErrorException {
			Log.i(TAG, "Starting syncing "+ getClass());
			removeDeletedEntriesFromServer();
			createOrUpdateEntries();
			pushNewLocalDataToServer();
			deleteOutdatedLocalData();
		}
		'''
	}
	
	def removeDeletedEntriesFromServer(){
		'''
		protected void removeDeletedEntriesFromServer() {
			Collection<ENTITY> l = entityManager.getDeleted();
			List<Long> idsToRemove = new ArrayList<Long>();
		
			// collect IDs of entries that should be deleted
			for (ENTITY entity : l) {
				if (entity.getDeleted() == 1) {
					idsToRemove.add(entity.getId());
				}
			}
		
			// remove the entries 1 by 1
			for (Long id : idsToRemove) {
				// get a clean instance
				ENTITY entity = entityManager.getById(id);
				// remove from server
				boolean successful = restClient.delete(entity.getUuid(), entity.getRevision());
		
				if (successful) {
					// remove locally for good if server side deletion was successful
					entityManager.delete(entity);
				}
			}
		
		}
		'''
	}
	
	def generateAbstractMethods(){
		'''
		protected abstract BaseRestClient<ENTITY> createRestClient(Context ctx);
		protected abstract String getShortDescription(ENTITY entity);
		'''
	}
	
	def createOrUpdateEntries(){
		'''
		protected void createOrUpdateEntries() throws NetworkErrorException {
			List<ENTITY> list = null;
			try {
				// get all entries from the remote server
				list = restClient.getAll();
			} catch (Exception e) {
				throw new NetworkErrorException("Network error" + e.getMessage());
			}
		
			if (list != null) {
				for (ENTITY aRemote : list) {
					ENTITY aLocal = entityManager.getByUuid(aRemote.getUuid());
		
					if (aLocal == null) {
						// there is no such entry in the local DB, create it
						createLocally(aRemote);
					} else {
		
						Log.d(TAG, "Considering an update for: " + getShortDescription(aLocal));
		
						long localLastUpdate = aLocal.getLastUpdate();
						long remoteLastUpdate = aRemote.getLastUpdate();
		
						// there is an entry in the local DB
						if (remoteLastUpdate > localLastUpdate) {
							updateRemoteToLocal(aRemote, aLocal);
						} else if (remoteLastUpdate < localLastUpdate) {
							updateLocalToRemote(aLocal);
						} else if (remoteLastUpdate == localLastUpdate) {
							Log.d(TAG, " Already in sync, no update needed");
						}
						Log.d(TAG, "Update finished for this address");
					}
				}
			}
		}
		'''
	}
	
	def deleteOutdatesLocalData(){
		'''
		/**
		 * See if we have entries in the local DB that have a revision (meaning is known to the server), but are not on the
		 * server any more (meaning it has been deleted by an other client)
		 */
		protected void deleteOutdatedLocalData() {
		
			Collection<ENTITY> entityListLocal = entityManager.getAll();
			List<ENTITY> entityListRemote = restClient.getAll();
		
			if (entityListRemote != null) {
		
				ArrayList<ENTITY> listToDelete = new ArrayList<ENTITY>();
		
				for (ENTITY eLocal : entityListLocal) {
					if (eLocal.getRevision() != null) {
						String uuid = eLocal.getUuid();
						boolean found = false;
						for (ENTITY eRemote : entityListRemote) {
							if (eRemote.getUuid().equals(uuid)) {
								found = true;
							}
						}
						if (!found) {
							// this is a local address with revision that is not on the server -> delete locally
							listToDelete.add(eLocal);
						}
					}
				}
		
				for (ENTITY entity : listToDelete) {
					entityManager.delete(entity);
				}
			}
		}
		'''
	}
	
	def createLocally(){
		'''
		/**
		 * Create a local address with remote data
		 * 
		 * @param eRemote
		 *           The remote data that will be written into the local DB
		 */
		 private void createLocally(ENTITY eRemote) {
			Log.d(TAG, "Creating new entry in local db: " + eRemote.getUuid());
		
			// reset some data for creation
			eRemote.setId(null);
		
			// execute creating the address
			entityManager.create(eRemote);
		}'''
	}
	
	def updateLocalToRemote(){
		'''
		/**
		 * Update remote address with local data
		 * 
		 * @param eLocal
		 *           The local address on the client side that will be send to the server
		 */
		private void updateLocalToRemote(ENTITY eLocal) {
			Log.d(TAG, " Updating local -> server");
			// local data is the newer than the remote data -> push data to server
		
			int count = 0;
			int maxCount = 2;
			while (count < maxCount) {
				// execute the update and hold on to the new revision we retrieve from the server
				String rev = restClient.update(eLocal);
		
				// handle error when the update goes wrong. It might have happened because the remote data has been updated.
				if (rev == null) {
					count++;
					//
					ENTITY eRemote = restClient.getByUuid(eLocal.getUuid());
					if (eRemote != null) {
						eLocal.setRevision(eRemote.getRevision());
					}
					continue;
				}
		
				// save the new revision in the local DB
				eLocal.setRevision(rev);
				entityManager.update(eLocal, true);
				break;
			}
		}
		'''
	}
	
	def updateRemoteToLocal(){
		'''
		/**
		 * Update a local address entry with remote data
		 * 
		 * @param eRemote
		 *           Remote data that will be written to the local DB
		 * @param eLocal
		 *           Local address that will be overwritten by the remote data
		 */
		private void updateRemoteToLocal(ENTITY eRemote, ENTITY eLocal) {
			Log.d(TAG, " Updating remote -> local");
			// server's data is newer than ours, write new data to the local DB
		
			// the id is needed for saving an object, set the ID from local, take the rest from remote
			eRemote.setId(eLocal.getId());
		
			// execute the update
			entityManager.update(eRemote);
		}
		'''
	}
	
	def pushNewLocalDataToServer(){
		'''
		/**
		 * inspect all local entries and push new entries to the server
		 */
		protected void pushNewLocalDataToServer() {
		
			// get all the local entries
			Collection<ENTITY> addressListLocal = entityManager.getAll();
		
			for (ENTITY eLocal : addressListLocal) {
				Log.d(TAG, "Considering for server side creation: " + getShortDescription(eLocal));
				if (eLocal.getRevision() == null) {
					// revision is null (meaning they have not been pushed to the server so far)
					Log.d(TAG, "Creating address on server. UUID: " + eLocal.getUuid());
					String revCreatedByServer = restClient.create(eLocal);
					if (revCreatedByServer != null) {
						eLocal.setRevision(revCreatedByServer);
						entityManager.update(eLocal, true);
					}
				}
			}
		}
		'''
	}
	
	/*
	 * SyncSubClass methods
	 * */
	 
	
	def generateSyncDomainEntityCode(String packageName, EClass domainEntity){
		var Set<String> imports = new HashSet<String>();
		var String inheritance = "SyncEntity<"+domainEntity.name+">";
		getSyncDomainEntityImports(imports, packageName, domainEntity.name);
		'''
		«generateSyncDomainEntityClass("SyncEntityTemplate", packageName, imports, domainEntity, inheritance, new ArrayList())»
		'''
	}
	
	def getSyncDomainEntityImports(Set<String> imports, String packageName, String domainEntityName){
		imports.add("android.content.Context");
		imports.add(packageName.replace("sync", "manager.")+domainEntityName+"Manager");
		imports.add(packageName.replace("sync", "manager.impl.")+domainEntityName+"ManagerImpl");
		imports.add(packageName.replace("sync", "model.")+domainEntityName);
		imports.add(packageName.replace("sync", "rest.")+domainEntityName+"RestClient");
		imports.add(packageName.replace("sync", "rest.")+"BaseRestClient");
		imports.add(packageName.replace("sync", "rest.")+"HttpClientFactoryImpl");
	}
	
		
	def generateSyncDomainEntityClass(String templateName, String packageName, Set<String> imports, EClass domainEntity,
													 							    String inheritance,
													 								List<String> interfaces){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("Sync"+domainEntity.name, inheritance , interfaces, false)»
		«generateSyncDomainEntityMethods(domainEntity.name)»
		}
		'''
	}
	
	def generateSyncDomainEntityMethods(String domainEntityName){
		'''
		«generateSyncDomainEntityDefaultConstructor(domainEntityName)»
		«generateSyncDomainEntityConstructor(domainEntityName)»
		«generateCreateRestClient(domainEntityName)»
		«generateGetShortsDescription(domainEntityName)»
		'''
	}
	
	def generateSyncDomainEntityDefaultConstructor(String domainEntityName){
		'''
		/**
		 * Constructor to set up the object instance
		 * 
		 * @param ctx
		 *           The Android Context
		 */
		public Sync«domainEntityName»(Context ctx, BaseRestClient<«domainEntityName»> restClient, «domainEntityName»Manager «domainEntityName.toLowerCase»Manager) {
			super(ctx, restClient, «domainEntityName.toLowerCase»Manager);
		}
		'''
	}
	
	def generateSyncDomainEntityConstructor(String domainEntityName){
		'''
		/**
		 * Constructor to set up the object instance
		 * 
		 * @param ctx
		 *           The Android Context
		 */
		public Sync«domainEntityName»(Context ctx, BaseRestClient<«domainEntityName»> restClient) {
			this(ctx, restClient, new «domainEntityName»ManagerImpl(ctx));
		}
		'''
	}
	
	def generateCreateRestClient(String domainEntityName){
		'''
		@Override
		protected BaseRestClient<«domainEntityName»> createRestClient(Context ctx) {
			return new «domainEntityName»RestClient(ctx, new HttpClientFactoryImpl());
		}
		'''
	}
	
	def generateGetShortsDescription(String domainEntityName){
		'''
		@Override
		protected String getShortDescription(«domainEntityName» entity) {
			return entity.getUuid();
		}
		'''
	}
	
}