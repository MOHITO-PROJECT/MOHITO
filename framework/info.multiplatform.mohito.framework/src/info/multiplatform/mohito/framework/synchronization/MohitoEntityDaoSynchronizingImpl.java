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

import info.multiplatform.mohito.framework.DaoManager;
import info.multiplatform.mohito.framework.MohitoEntity;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import info.multiplatform.mohito.framework.exceptions.DataLayerException;
import info.multiplatform.mohito.framework.exceptions.NoInformationSourceAvailableException;
import info.multiplatform.mohito.framework.mql.Criteria;

import java.util.ArrayList;
import java.util.List;

/**MOHITO-Entity DAO responsible for synchronization-aware access to local and remote storage.
 * Automatically selects the available storage and throws {@link SynchronizationRequiredException} 
 * if the access would lead to inconsistent data and a prior synchronization is needed. The operations
 * throw {@link NoInformationSourceAvailableException} if operations require access to data and no
 * storage is available. 
 * 
 * @author hgroenda
 *
 * @param <T> MOHITO-Entity type.
 * @param <PK> Type of identifier for the entity.
 */
public class MohitoEntityDaoSynchronizingImpl <T extends MohitoEntity<PK>, PK> extends MohitoEntityDao<T, PK> {

	/** Local storage access entity DAO. */
	private MohitoEntityDao<T, PK> localEntityDao;
	/** Remote storage access entity DAO. */
	private MohitoEntityDao<T, PK> remoteEntityDao;

	/** Flag determining if local access is preferred to remote access. Allows working locally and only request remote data if required. */
	private boolean preferLocal;
	/** Flag determining if the local storage is available. */
	private boolean localAvailable;
	/** Flag determining if the remote storage is available. */
	private boolean remoteAvailable; 
	
	/**Creates a new instance.
	 * @param type The type managed by this DAO.
	 * @param daoManager The manager of this DAO.
	 */
	public MohitoEntityDaoSynchronizingImpl(Class<T> type, 
			DaoManager daoManager) {
		super(daoManager);
		this.localEntityDao = daoManager.getStorageManager().getLocalStorageManager() == null ? null
				: daoManager.getStorageManager().getLocalStorageManager().getEntityDao(
						type);
		this.remoteEntityDao = daoManager.getStorageManager().getRemoteStorageManager() == null ? null
				: daoManager.getStorageManager().getRemoteStorageManager().getEntityDao(
						type);
		ensurePreconditionsAndSetAvailability();
	}
	
	/**Ensures that at least one information source is available and set the information on the availability and if local processing is preferred.
	 */
	private void ensurePreconditionsAndSetAvailability() {
		preferLocal = getManager().getStorageManager().isPreferWorkingLocal();
		localAvailable = getManager().getStorageManager().isLocalAvailable();
		remoteAvailable = getManager().getStorageManager().isRemoteAvailable(); 
		if (!localAvailable && !remoteAvailable) {
			throw new NoInformationSourceAvailableException("Could not create instance. Neither remote nor local storage are available.");
		}
	}

	@Override
	public PK create(T newInstance) throws DataLayerException {
		ensurePreconditionsAndSetAvailability();
		if (preferLocal) {
			return localEntityDao.create(newInstance);
		} else { // preferRemote
			if (remoteAvailable) {
				if (localAvailable) {
					PK result = remoteEntityDao.create(newInstance);
					return localEntityDao.create( remoteEntityDao.getById(result) );
				} else { // ! localAvailable
					return remoteEntityDao.create(newInstance);
				}
			} else { // ! remoteAvailable
				return localEntityDao.create(newInstance);
			}
		}
	}

	@Override
	public T getById(PK id) throws DataLayerException {
		ensurePreconditionsAndSetAvailability();
		if (preferLocal) {
			T result = null;
			if (localAvailable) {
				result = localEntityDao.getById(id);
			}
			if (result == null && remoteAvailable) {
				result = remoteEntityDao.getById(id);
				if (localAvailable && result != null) {
					localEntityDao.create(result);
				}
			}
			return result;
		} else { // ! preferLocal
			if (remoteAvailable) {
				if (localAvailable) {
					T remoteResult = remoteEntityDao.getById(id);
					if (remoteResult == null) {
						return localEntityDao.getById(id);
					} else { // remoteResult != null
						T result = localEntityDao.getById(id);
						if (result == null) {
							localEntityDao.create(remoteResult);
							return remoteResult;
						} else { // result != null
							if (result.getLastUpdateTime() < remoteResult.getLastModificationTime()
									&& result.mIsDirty()) { // modification conflict
								return handleSynchronizationConflict(
										remoteResult, result);
							} else { // no modification conflict
								localEntityDao.update(remoteResult);
								return remoteResult;
							}
						}
					}
				} else { // ! localAvailable
					return remoteEntityDao.getById(id);
				}
			} else { // ! remoteAvailable
				return localEntityDao.getById(id);
			}
		}
	}

	/**Handles a synchronization conflict, if possible.
	 * @param remoteResult the remote result.
	 * @param result The local result.
	 * @return the local (cached) version
	 * @throws SynchronizationRequiredException If the conflict could not be resolved automatically.
	 */
	private T handleSynchronizationConflict(T remoteResult, T result) throws DataLayerException{
		if (getManager().getStorageManager().isHandleConflictsAutomatically()) {
			if (getManager().getStorageManager().isServerWinsInAutomatedConflictResolution()) {
				// Server wins
				localEntityDao.update(remoteResult);
				return remoteResult;
			} else {
				// Client wins
				remoteEntityDao.update(result);
				return result;
			}
		} else {
			throw new SynchronizationRequiredException();
		}
	}

	@Override
	public List<T> getByCriteria(Criteria criteria) throws DataLayerException {
		ensurePreconditionsAndSetAvailability();
		if (preferLocal) {
			List<T> results = null;
			if (localAvailable) {
				results = localEntityDao.getByCriteria(criteria);
			}
			if (results == null && remoteAvailable) {
				results = remoteEntityDao.getByCriteria(criteria);
				if (localAvailable && results != null) {
					for(T result : results) {
						localEntityDao.create(result);
					}
				}
			}
			return results;
		} else { // ! preferLocal
			if (remoteAvailable) {
				if (localAvailable) {
					List<T> remoteResults = remoteEntityDao.getByCriteria(criteria);
					if (remoteResults == null) {
						return localEntityDao.getByCriteria(criteria);
					} else { // remoteResult != null
						List<T> results = localEntityDao.getByCriteria(criteria);
						if (results == null) {
							for(T remoteResult : remoteResults) {
								localEntityDao.create(remoteResult);
							}
							return remoteResults;
						} else { // result != null
							List<T> returnedList = new ArrayList<T>();
							T remoteResult;
							for (T result : results) {
								if (remoteResults.contains(result)) {
									remoteResult = remoteResults.get(remoteResults.indexOf(result));
									if (result.getLastUpdateTime() < remoteResult.getLastModificationTime()
											&& result.mIsDirty()) { // modification conflict
										handleSynchronizationConflict(remoteResult, result);
										returnedList.add(result);
									} else { // no modification conflict
										localEntityDao.update(remoteResult);
										returnedList.add(remoteResult);
									}
								} else {
									returnedList.add(result);
								}
							}
							remoteResults.removeAll(results);
							for (T onlyRemote : remoteResults) {
								localEntityDao.create(onlyRemote);
								returnedList.add(onlyRemote);
							}
							return returnedList;
						}
					}
				} else { // ! localAvailable
					return remoteEntityDao.getByCriteria(criteria);
				}
			} else { // ! remoteAvailable
				return localEntityDao.getByCriteria(criteria);
			}
		}
	}
	
	@Override
	public boolean update(T entity) throws DataLayerException {
		ensurePreconditionsAndSetAvailability();
		if (preferLocal) {
			if (localAvailable) {
				return localEntityDao.update(entity);
			} else { // ! localAvailable
				return remoteEntityDao.update(entity);
			}
		} else { // ! preferLocal
			if (remoteAvailable) {
				boolean result = true;
				T remoteResult = remoteEntityDao.getById(entity.getId());
				if (remoteResult == null) {
					remoteEntityDao.create(entity);
					remoteResult = remoteEntityDao.getById(entity.getId());
				}
				if (remoteResult.getLastModificationTime() > entity.getLastUpdateTime()) { // modification conflict
					handleSynchronizationConflict(remoteResult, entity);
					result &= true;
				} else { // no modification conflict
					result &= remoteEntityDao.update(entity);
				}
				if (localAvailable & result) {
					result &= localEntityDao.update(entity);
				}
				return result;
			} else { // ! remoteAvailable
				return localEntityDao.update(entity);
			}
		}
	}

	@Override
	public boolean delete(T entity) throws DataLayerException {
		ensurePreconditionsAndSetAvailability();
		if (preferLocal) {
			if (localAvailable) {
				// mark for deletion on next synchronization
				entity.mSetDeletionPending(true);
				return localEntityDao.update(entity);
			} else {
				return remoteEntityDao.delete(entity);
			}
		} else { // ! preferLocal
			if (remoteAvailable) {
				boolean result = true;
				T remoteResult = remoteEntityDao.getById(entity.getId());
				if (remoteResult.getLastModificationTime() > entity.getLastUpdateTime()) { // modification conflict
					if (handleSynchronizationConflict(remoteResult, entity).mIsDeletionPending()) {
						result &= remoteEntityDao.delete(entity);
						if (localAvailable & result) {
							result &= localEntityDao.delete(entity);
						}
						return result;
					}
					return true;
				} else { // no modification conflict
					result &= remoteEntityDao.delete(entity);
					if (localAvailable & result) {
						result &= localEntityDao.delete(entity);
					}
					return result;
				}
			} else { // ! remoteAvailable
				// mark for deletion on next synchronization
				entity.mSetDeletionPending(true);
				return localEntityDao.update(entity);
			}
		}
	}

	@Override
	public boolean revert(T entity) throws DataLayerException {
		ensurePreconditionsAndSetAvailability();
		if (preferLocal) {
			if (localAvailable) {
				return localEntityDao.revert(entity);
			} else { // ! localAvailable
				return remoteEntityDao.revert(entity);
			}
		} else { // ! preferLocal
			if (remoteAvailable) {
				boolean result = true;
				result &= remoteEntityDao.revert(entity);
				if (localAvailable & result) {
					result &= localEntityDao.update(entity);
				}
				return result;
			} else { 
				return localEntityDao.revert(entity);
			}
		}
	}

}
