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
import info.multiplatform.mohito.framework.synchronization.MohitoEntityDaoSynchronizingImpl;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Interface and convenience functionality for handling MOHITO model entities.
 * @author hgroenda
 *
 */
public abstract class MohitoEntity <PK> {
	/** Logger for this class. */
	public static final Logger logger = Logger.getLogger(MohitoEntity.class.getCanonicalName());
	
	// MOHITO-specific internal administration flags (Runtime)
	/** Flag if this entity is a proxy and does not contain the real values. Transient value, which does not need to be persisted. */
	protected boolean mIsProxy;
	// MOHITO-specific internal administration flags (Client)
	/** Flag if this entity has been modified since the last update. */
	protected boolean mIsDirty;
	// MOHITO-specific internal administration flags (Client & Server)
	/** Flag if this entity is pending deletion upon the next update. */
	protected boolean mDeletionPending;
	
	// general information for all MOHITO entities (Client only)
	/**Entity was last updated or synchronized on this date. Time zone is UTC, reference point in time January 1, 1970, resolution milliseconds. 
	 * The time must be set by the server before starting the data transmission.
	 * This values is only used on a Client and updated during synchronization. It is set in {@link MohitoEntityDaoSynchronizingImpl}. */
	protected Long lastUpdateTime;
	// general information for all MOHITO entities (Client & Server)
	/** Entity creation date. Time zone is UTC, reference point in time January 1, 1970. */
	protected Long creationTime;
	/** Entity was last modified on this date. Time zone is UTC, reference point in time January 1, 1970, resolution milliseconds. */
	protected Long lastModificationTime;
	
	/**Creates a new instance and assigns the administrative flags and general information.
	 */
	public MohitoEntity() {
		this.mIsProxy = false;
		this.mIsDirty = false;
		this.mDeletionPending = false;
		this.lastUpdateTime = null;
		this.creationTime = System.currentTimeMillis();
		this.lastModificationTime = null;
	}

	/**Generator for UUIDs. Must be overwritten if customized generation strategies are used for MOHITO-Entities.
	 * @return UUID.
	 */
	@SuppressWarnings("unchecked")
	public PK generateUUID() {
		String classname = "";
		try {
			@SuppressWarnings("rawtypes")
			Class clazz = this.getClass();
			while (clazz != Object.class && clazz != null 
					&& ! (clazz.getGenericSuperclass() instanceof ParameterizedType  
						&& ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments().length == 1)) {
				clazz = clazz.getSuperclass();
			}
			logger.finest("current class=" + clazz.getCanonicalName());
			if (clazz.getGenericSuperclass() instanceof ParameterizedType 
					&& ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments().length == 1) {
				@SuppressWarnings("rawtypes")
				Class pkType = (Class) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
				classname = pkType.getCanonicalName();
				if (pkType == String.class) {
					return (PK) UUID.randomUUID().toString();
				} else if (pkType == Integer.class) {
					return  (PK) (Integer) ((Long) UUID.randomUUID().getLeastSignificantBits()).intValue();
				} else if (pkType == Long.class) {
					return (PK) (Long) UUID.randomUUID().getLeastSignificantBits();
				}
			}
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, "Could not identify dynamic type of id.", e);
		}
		throw new IllegalArgumentException(
				"Automatic generation of new UUIDs is not supported for the provided type. Override the method generateUUID() in your generated MOHITO-Entity type and provide a generator for the type "
						+ classname + ".");
	}

	/**Checks if this MOHITO-Entity is a proxy and loads the data if necessary.
	 */
	protected void checkProxyAndResolve() {
		if (this.mIsProxy()) {
			MohitoEntity<PK> ref = null;
			try {
				ref = doCheckProxyAndResolveGetReferenceEntity();
			} catch (DataLayerException e) {
				logger.log(Level.SEVERE, "Failed to resolve proxy.", e);
				ref = null;
			}
			if (ref == null) throw new IllegalStateException("Unable to resolve Proxy.");
			this.mDeletionPending = ref.mDeletionPending;
			this.mIsDirty = ref.mIsDirty;
			this.mIsProxy = ref.mIsProxy;
			this.lastUpdateTime = ref.lastUpdateTime;
			this.creationTime = ref.creationTime;
			this.lastModificationTime = ref.lastModificationTime;
			assert(this.mIsProxy == false);
			doCheckProxyAndResolveAssignment(ref);
		}
	}

	/**Retrieves the DAO for the actual handled entity for {@link #checkProxyAndResolve()}.
	 */
	protected abstract MohitoEntity<PK> doCheckProxyAndResolveGetReferenceEntity() throws DataLayerException;
	
	/**Actual assignment of data for each subclass for {@link #checkProxyAndResolve()}.
	 */
	protected abstract void doCheckProxyAndResolveAssignment(MohitoEntity<PK> reference);
	
	/**Compares this entity with a given reference for equality.
	 * The ids of referenced MOHITO-Entites are used for the comparison and the comparison is not nested.
	 * @param reference Reference entity.
	 * @return <code>true</code> if the provided entity is assignment compatible and the domain content is equal.
	 */
	public abstract boolean domainContentEquals(MohitoEntity<PK> reference);
	
	// Getter / Setter
	/**
	 * @return the identifier of this entity.
	 */
	public abstract PK getId();
	
	//REMARK Setting the id is not allowed.

	/**
	 * @return If this entity is a proxy.
	 */
	public boolean mIsProxy() {
		return mIsProxy;
	}
	
	/**Marks if this entity is only a proxy for the object and does not contain the correct values (yet).
	 * @param mIsProxy <code>true</code> if it was modified, <code>false</code> otherwise.
	 */
	public void mSetProxy(boolean mIsProxy) {
		this.mIsProxy = mIsProxy;
	}

	/**
	 * @return If this entity is a proxy.
	 */
	public boolean mIsDirty() {
		return mIsDirty;
	}
	
	/**Marks if this entity has been modified by a user since it was create or loaded.
	 * @param mIsDirty <code>true</code> if it was modified, <code>false</code> otherwise.
	 */
	public void mSetDirty(boolean mIsDirty) {
		this.mIsDirty = mIsDirty;
		//Updates the modification time of the entity and set the current time.
		this.lastModificationTime = System.currentTimeMillis();
}

	/**
	 * @return the creationTime
	 */
	public Long getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the lastModificationTime
	 */
	public Long getLastModificationTime() {
		return lastModificationTime;
	}

	/**
	 * @param lastModificationTime the lastModificationTime to set
	 */
	public void setLastModificationTime(Long lastModificationTime) {
		this.lastModificationTime = lastModificationTime;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the mDeletionPending
	 */
	public boolean mIsDeletionPending() {
		return mDeletionPending;
	}

	/**
	 * @param mDeletionPending the mDeletionPending to set
	 */
	public void mSetDeletionPending(boolean deletionPending) {
		this.mIsDirty = true;
		this.mDeletionPending = deletionPending;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MohitoEntity<?> other = (MohitoEntity<?>) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
}
