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
package info.multiplatform.mohito.framework.android;

import info.multiplatform.mohito.framework.MohitoEntity;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import info.multiplatform.mohito.framework.mql.Criteria;
import info.multiplatform.mohito.framework.mql.MohitoQueryLanguage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

/**Implementation for accessing MOHITO-Entities using ORMLite as OR-Mapper and H2 as Database.
 * It is an adapter between MOHITO-DAOs and the ORMLite-DAOs.
 * @author egailus
 *
 */
public class MohitoEntityDaoOrmLiteImpl <T extends MohitoEntity<PK>, PK> extends MohitoEntityDao<T, PK> {
	/** Logger for this class. */
	private static final Logger logger = Logger.getLogger(MohitoEntityDaoOrmLiteImpl.class.getCanonicalName());
	
	/** ORMLite-internal DAO. */
	private Dao<T, PK> dao;
	/** Type of class managed by this DAO. */
	private Class<T> type;
	
	/**Prepares data access.
	 * @param type Type managed by this DAO.
	 */
	public MohitoEntityDaoOrmLiteImpl(Class<T> type, info.multiplatform.mohito.framework.DaoManager dm) {
		super(dm);
		this.type = type;
		try {
			IAndroidDatabaseHelper dbHelper = DatabaseHelperManager.getInstance().getDbHelper();
			if(dbHelper == null) {
				logger.log(Level.SEVERE, "Could not provide a ORMLite-Dao for accessing stored data.");
				dao = null;
			}
			else {
				this.dao = DaoManager.createDao(dbHelper.getConnectionSource(), type);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Could not create table in database and provide a ORMLite-Dao for accessing stored data.", e);
		}
	}
	
	@Override
	public PK create(T newInstance) {
		newInstance.setCreationTime(System.currentTimeMillis());
		
		try {
			PK id = dao.createIfNotExists(newInstance).getId();
			if (getManager().getEntityInstancesCache() != null) {
				getManager().getEntityInstancesCache().put(type, id, newInstance);
			}
			return id;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Could not store object in database.", e);
			return null;
		}
	}

	@Override
	public T getById(PK id) {
		try {
			if (getManager().getEntityInstancesCache() != null) {
				if (getManager().getEntityInstancesCache().get(type, id) != null) {
					return getManager().getEntityInstancesCache().get(type, id);
				}
			}
			return dao.queryForId(id);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Could not retrieve object from database.", e);
			return null;
		}
	}
	
	@Override
	public List<T> getByCriteria(Criteria criteria) {
		try {
			/* Load all entries and resolve them. 
			 * Optimized version only loading the non-cached ones.
			 * Further optimization is possible by mapping the criteria to the query. However, this is
			 * complicated if additional mapping tables are used and requires strict ID handling.
			 */
			List<T> values = new ArrayList<T>();
			for (T databaseEntry : dao.queryForAll()) {
				if (getManager().getEntityInstancesCache() != null) {
					if (getManager().getEntityInstancesCache().get(type, databaseEntry.getId()) != null) {
						values.add(getManager().getEntityInstancesCache().get(type, databaseEntry.getId()));
					} else {
						values.add(databaseEntry);
					}
				} else {
					values.add(databaseEntry);
				}
			}
			return MohitoQueryLanguage.filterByCriteria(values, criteria);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Could not retrieve object from database.", e);
			return null;
		}
	}

	@Override
	public boolean update(T entity) {
		entity.setLastModificationTime(System.currentTimeMillis());
		entity.mSetDirty(true);
		
		try {
			dao.update(entity);
			if (getManager().getEntityInstancesCache() != null) {
				getManager().getEntityInstancesCache().put(type, entity.getId(), entity);
			}
			return true;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Could not update object in database.", e);
			return false;
		}
	}

	@Override
	public boolean delete(T entity) {
		try {
			dao.delete(entity);
			if (getManager().getEntityInstancesCache() != null) {
				getManager().getEntityInstancesCache().remove(type, entity.getId());
			}
			return true;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Could not update object in database.", e);
			return false;
		}
	}

	@Override
	public boolean revert(T entity) {
		try {
			dao.refresh(entity);
			if (getManager().getEntityInstancesCache() != null) {
				getManager().getEntityInstancesCache().put(type, entity.getId(), entity);
			}
			return true;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Could not revert object to data from database.", e);
			return false;
		}
	}

}
