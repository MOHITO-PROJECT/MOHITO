/** 
 * Copyright (c) 2012-2014 B2M Software AG
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
package de.b2m.software.model;

import info.multiplatform.mohito.framework.MohitoEntity;
import com.j256.ormlite.field.DatabaseField;

/**Marker for model entities of the Library.
 * {@link #generateUUID()} can be overrridden to provide model-specific generator implementations for generation UUIDs.
 *
 * @generated info.multiplatform.mohito.generator.environment.java.DomainModelTemplate (Xtend) 
 */
public abstract class LibraryMohitoEntity<PK> extends MohitoEntity<PK> {

	/**Default constructor.
	 */
	public LibraryMohitoEntity() {
		super();
	}

	/** Required helper to allow annotations. See #creationTime */
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	protected Long presistedCreationTime;

	/** Required helper to allow annotations. See #lastUpdateTime */
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	protected Long presistedLastUpdateTime;

	/** Required helper to allow annotations. See #lastModificationTime */
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	protected Long presistedLastModificationTime;

	/** Required helper to allow annotations. See #mIsProxy */
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	protected boolean presistedIsProxy;

	/** Required helper to allow annotations. See #mIsDirty */
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	protected boolean presistedIsDirty;

	/** Required helper to allow annotations. See #mDeletionPending */
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	protected boolean presistedDeletionPending;

	@Override
	public Long getCreationTime() {
		return presistedCreationTime;
	}

	@Override
	public void setCreationTime(Long creationTime) {
		presistedCreationTime = creationTime;
		this.creationTime = creationTime;
	}

	@Override
	public Long getLastUpdateTime() {
		return presistedLastUpdateTime;
	}

	@Override
	public void setLastUpdateTime(Long lastUpdateTime) {
		this.presistedLastUpdateTime = lastUpdateTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public Long getLastModificationTime() {
		return presistedLastModificationTime;
	}

	@Override
	public void setLastModificationTime(Long lastModificationTime) {
		this.presistedLastModificationTime = lastModificationTime;
		this.lastModificationTime = lastModificationTime;
	}

	@Override
	public boolean mIsProxy() {
		return presistedIsProxy;
	}

	@Override
	public void mSetProxy(boolean isProxy) {
		presistedIsProxy = isProxy;
		mIsProxy = isProxy;
	}

	@Override
	public boolean mIsDirty() {
		return presistedIsDirty;
	}

	@Override
	public void mSetDirty(boolean isDirty) {
		presistedIsDirty = isDirty;
		mIsDirty = isDirty;
	}

	@Override
	public boolean mIsDeletionPending() {
		return presistedDeletionPending;
	}

	@Override
	public void mSetDeletionPending(boolean deletionPending) {
		presistedIsDirty = true;
		mIsDirty = true;

		presistedDeletionPending = deletionPending;
		mDeletionPending = deletionPending;
	}
}
