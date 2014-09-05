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
import info.multiplatform.mohito.framework.mql.Criteria;

import java.util.List;



/**Data access object for MOHITO entities.
 * @author hgroenda
 *
 * @param <T> Type of MOHITO business entity accessed by this DAO.
 * @param <PK> Type of primary key. 
 */
public abstract class MohitoEntityDao<T extends MohitoEntity<PK>, PK> {
	/** DAO manager taking care of caching of instances of MOHITO-Entities. */
	private final DaoManager manager;
	
	/**Default constructor setting the MOHITO-storage manager.
	 * @param storageManager The storage manager taking care of caching of instances of MOHITO-Entities regardless of local or remote storage.
	 */
	public MohitoEntityDao(DaoManager manager) {
		this.manager = manager;
	}
	
	/**
	 * Stores the provided entity.
	 * @return The automatically generated identifier.
	 */
	public abstract PK create(T newInstance) throws DataLayerException;
	
	/**
	 * Retrieve an entity by its identifier.
	 * If the same entity is requested multiple times, the same object is returned. If an entity is modified between
	 * different calls to this method then the most recent version, which may include modifications, is returned. 
	 * Use {@link #revert(MohitoEntity)} on an existing entity to restore the contained information according to
	 * the available storage(s). 
	 * @param id The internal identifier.
	 * @return The entity or <code>null</code>.
	 */
	public abstract T getById(PK id) throws DataLayerException;
	
	/**
	 * Retrieves all entities, which match given criteria.
	 * @param criteria The criterion for filtering the wanted entities. Provide <code>null</code> in order to
	 * retrieve all entities.
	 * @return The list of entities, which can be empty in case no entities where found. If entities are modified 
	 * between different calls to this method then the most recent versions, which may include modifications, are 
	 * returned.
	 */
	public abstract List<T> getByCriteria(Criteria criteria) throws DataLayerException;
	
	/**
	 * Persists the entity. The meta-data of the entity is updated accordingly.
	 * @param entity The entity to update.
	 * @return <code>true</code> if the update succeeded, <code>false</code> otherwise.
	 */
	public abstract boolean update(T entity) throws DataLayerException;
	
	/**
	 * Delete an entity specified by the supplied entity.
	 * @param entity The entity to delete.
	 * @return <code>true</code> if the deletion was successful, <code>false</code> otherwise.
	 */
	public abstract boolean delete(T entity) throws DataLayerException;

	/**Reverts the values of the provided entity to the available values in the local or remote storage.
	 * @param entity The entity to revert.
	 * @return <code>true</code> if the reversion was successful, <code>false</code> otherwise.
	 */
	public abstract boolean revert(T entity) throws DataLayerException;

	/**
	 * @return the manager for this DAO.
	 */
	public DaoManager getManager() {
		return manager;
	}
	
}
