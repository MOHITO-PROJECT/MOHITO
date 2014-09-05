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

import java.lang.reflect.Modifier;


/**Management interface for all types of entities of a model. 
 * @author hgroenda
 *
 */
public abstract class DaoManager {
	
	/** Cache managing access to instance of all elements connected to this storage. */
	protected final WeakReferenceCache mohitoEntityInstancesCache;
	/** The storage manager using this dao manager. */
	protected StorageManager<DaoManager> storageManager;

	/**Creates a new instance.
	 * @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.
	 */
	public DaoManager(boolean useCache) {
		if (useCache) {
			this.mohitoEntityInstancesCache = new WeakReferenceCache();
		} else {
			this.mohitoEntityInstancesCache = null;
		}
		this.storageManager = null;
	}
	
	
	/**Request the entity manager responsible for a an entity.
	 * @param mohitoEntityClass The entity class.
	 * @return The entity manager responsible for the class.
	 */
	public <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> getEntityDao(Class<T> mohitoEntityClass) {
		if (Modifier.isAbstract(mohitoEntityClass.getModifiers())) {
			throw new IllegalArgumentException("Cannot create a DAO for an abstract class.");
		} else {
			return doGetEntityDao(mohitoEntityClass);
		}
	}

	/**Actually returns the DAO for the given class.
	 * @param mohitoEntityClass The class.
	 * @return The DAO responsible for the class.
	 */
	abstract protected <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> doGetEntityDao(Class<T> mohitoEntityClass);
	
	/**
	 * @return The cache for instances created by any DAO managed by this manager. Returns <code>null</code> if no cache should be used.
	 */
	public WeakReferenceCache getEntityInstancesCache() {
		return this.mohitoEntityInstancesCache;
	}


	/**
	 * @return the storageManager
	 */
	public StorageManager<DaoManager> getStorageManager() {
		return storageManager;
	}


	/**
	 * @param storageManager the storageManager to set
	 */
	public void setStorageManager(StorageManager<DaoManager> storageManager) {
		this.storageManager = storageManager;
	}

	
}
