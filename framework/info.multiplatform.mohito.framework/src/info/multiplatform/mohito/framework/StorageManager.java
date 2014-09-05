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
package info.multiplatform.mohito.framework;

import info.multiplatform.mohito.framework.exceptions.DataLayerException;
import info.multiplatform.mohito.framework.exceptions.NoInformationSourceAvailableException;
import info.multiplatform.mohito.framework.synchronization.MohitoEntitySynchronizer;

/**Manages direct access to the available storages.
 * @author hgroenda
 *
 */
public abstract class StorageManager<DM extends DaoManager> {
	
	/** Manager for access to local storage. */
	protected final DM localInstance;
	/** Manager for access to remote storage. */
	protected final DM remoteInstance;
	/** Manage for synchronized storage access. */
	protected final DM synchronizedInstance;
	/** Flag if the local instance is available and should be used for lookups. */
	protected boolean localAvailable = true;
	/** Flag if the remote instance is available and should be used for lookups. */
	protected boolean remoteAvailable = true;
	/** Flag if working on the local instance is preferred and synchronization is invoked explicitly. */
	protected final boolean preferWorkingLocal;
	/** Flag if synchronization conflicts should be handled automatically or manually. */
	protected final boolean handleConflictsAutomatically;
	/** Flag if the server-side always wins in automated synchronization conflict resolution. */
	protected final boolean serverWinsInAutomatedConflictResolution;
	/** Flag if synchronization should be started automatically if the remote storage becomes available. */
	protected final boolean autoSyncOnRemoteAvailable;
	/** Synchronization mechanism for the managed classes. */
	protected final MohitoEntitySynchronizer synchronizer;
	/** Flag if a synchronization is currently running for the storage. */
	protected boolean synchronizationRunning;
	
	/**Create a new instance.
	 * @param localInstance DAO manager for local access.
	 * @param remoteInstance DAO manager for remote access.
	 */
	@SuppressWarnings("unchecked")
	public StorageManager(DM localInstance, DM remoteInstance,
			DM synchronizedInstance, boolean preferWorkingLocal,
			boolean handleConflictsAutomatically,
			boolean serverWinsInAutomatedConflictResolution,
			boolean autoSyncOnRemoteAvailable,
			Class<? extends MohitoEntity<?>>... managedClasses) {
		this.localInstance = localInstance;
		this.remoteInstance = remoteInstance;
		if (managedClasses != null) {
			this.synchronizer = new MohitoEntitySynchronizer(this, managedClasses);
			for (Class<? extends MohitoEntity<?>> managedClass: managedClasses) {
				if (localInstance.getEntityInstancesCache() != null) {
					localInstance.getEntityInstancesCache().registerClass((Class<? extends MohitoEntity<?>>) managedClass);
				}
				if (remoteInstance.getEntityInstancesCache() != null) {
					remoteInstance.getEntityInstancesCache().registerClass((Class<? extends MohitoEntity<?>>) managedClass);
				}
			}
		} else {
			this.synchronizer = null;
		}
		if (localInstance == null) {
			localAvailable = false;
		} else {
			localInstance.setStorageManager((StorageManager<DaoManager>) this);
		}
		if (remoteInstance == null) {
			remoteAvailable = false;
		} else {
			remoteInstance.setStorageManager((StorageManager<DaoManager>) this);
		}
		this.synchronizedInstance = synchronizedInstance;
		if (synchronizedInstance != null) {
			synchronizedInstance.setStorageManager((StorageManager<DaoManager>) this);
		}
		this.handleConflictsAutomatically = handleConflictsAutomatically;
		this.autoSyncOnRemoteAvailable = autoSyncOnRemoteAvailable;
		this.preferWorkingLocal = preferWorkingLocal;
		this.serverWinsInAutomatedConflictResolution = serverWinsInAutomatedConflictResolution;
		this.synchronizationRunning = false;
	}
	
	/**
	 * @return An available dao manager (either local or remote).
	 */
	public DM getAvailableStorageManager() {
		if (!remoteAvailable && !localAvailable) {
			throw new NoInformationSourceAvailableException("Neither local nor remote storage are available.");
		} else {
			return synchronizedInstance;
		}
	}

	public boolean isRemoteAvailable() {
		return remoteAvailable;
	}

	public void setRemoteAvailable(boolean isRemoteAvailable) throws DataLayerException {
		this.remoteAvailable = isRemoteAvailable;
		if (synchronizer != null && autoSyncOnRemoteAvailable && isLocalAvailable() == true) {
			synchronizer.synchronize(null);
		}
	}

	public boolean isLocalAvailable() {
		return localAvailable;
	}

	public void setLocalAvailable(boolean isLocalAvailable) {
		this.localAvailable = isLocalAvailable;
	}
	
	public DM getLocalStorageManager() {
		return localInstance;
	}

	public DM getRemoteStorageManager() {
		return remoteInstance;
	}
	
	public DM getSynchronizedStorageManager() {
		return synchronizedInstance;
	}

	/**
	 * @return the preferWorkingLocal
	 */
	public boolean isPreferWorkingLocal() {
		return preferWorkingLocal;
	}
	
	/**
	 * @return the handleConflictsAutomatically
	 */
	public boolean isHandleConflictsAutomatically() {
		return handleConflictsAutomatically;
	}

	/**
	 * @return the serverWinsInAutomatedConflictResolution
	 */
	public boolean isServerWinsInAutomatedConflictResolution() {
		return serverWinsInAutomatedConflictResolution;
	}

	/**
	 * @return the synchronizationRunning
	 */
	public boolean isSynchronizationRunning() {
		return synchronizationRunning;
	}

	/**
	 * @param synchronizationRunning the synchronizationRunning to set
	 */
	public void setSynchronizationRunning(boolean synchronizationRunning) {
		this.synchronizationRunning = synchronizationRunning;
	}

}
