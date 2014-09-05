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

/**Stores information about a synchronization conflict between a local and remote MOHITO-Entity.
 * @author hgroenda
 *
 */
public class SynchronizationConflict {
	/** The local entity. */
	protected final MohitoEntity<?> localEntity;
	/** The remote entity. */
	protected final MohitoEntity<?> remoteEntity;
	/** The (local or remote) entity with resolved conflicts. <code>null</code> if the conflict is not resolved (yet). */
	protected MohitoEntity<?> resolvedEntity;

	/**Creates a new synchronization conflict and points to the conflicting entities.
	 * @param localEntity The local entity.
	 * @param remoteEntity The remote entity.
	 */
	public SynchronizationConflict(MohitoEntity<?> localEntity, MohitoEntity<?> remoteEntity) {
		this.localEntity = localEntity;
		this.remoteEntity = remoteEntity;
		resolvedEntity = null;
	}
	
	/**Checks if the conflict is resolved.
	 * Use {@link #setResolvedEntity(MohitoEntity)} in order to set a conflict resolution.
	 * @return <code>true</code> if, and only if, a conflict resolution exists.
	 */
	public boolean isResolved() {
		return resolvedEntity == null;
	}
	
	/**
	 * @return the resolvedEntity
	 */
	public MohitoEntity<?> getResolvedEntity() {
		return resolvedEntity;
	}

	/**Sets a conflict resolution.
	 * This must be either the local or remote entity in the conflict. That entity can but doesn't have to be
	 * modified before setting the resolution.
	 * @param resolvedEntity the resolvedEntity to set
	 */
	public void setResolvedEntity(MohitoEntity<?> resolvedEntity) {
		if (resolvedEntity != localEntity && resolvedEntity != remoteEntity) {
			throw new IllegalArgumentException("The resolved entity must point either to the local or the remote entity in the conflict.");
		}
		this.resolvedEntity = resolvedEntity;
	}

	/**
	 * @return the localEntity
	 */
	public MohitoEntity<?> getLocalEntity() {
		return localEntity;
	}

	/**
	 * @return the remoteEntity
	 */
	public MohitoEntity<?> getRemoteEntity() {
		return remoteEntity;
	}
	
}
