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
package info.multiplatform.mohito.modeling.annotation.synchronization;
/**
 * 
 */


import info.multiplatform.mohito.modeling.annotation.AbstractMohitoAnnotationCategoryDefinition;
import info.multiplatform.mohito.modeling.annotation.AbstractMohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationsDefinitionsConstants;

/**Definitions for the MOHITO-Annotation category 'Synchronization'.
 * 
 * @author hgroenda
 *
 */
public class SynchronizationMohitoAnnotationCategory extends
		AbstractMohitoAnnotationCategoryDefinition {
	/** Namespace of this category. */
	public static final String NAMESPACE = "http://www.multiplatform.info/mohito/annotations/synchronization";
	
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition PREFER_WORKING_LOCAL = new AbstractMohitoAnnotationDefinition(
			"Prefer working local",
			"States if modifications of the model are preferred on local storage. Explicit synchronization is required to store modified data on the server.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EPACKAGE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"preferWorkingLocal",
			"true");
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition AUTO_SYNC_ON_REMOTE_AVAILABLE = new AbstractMohitoAnnotationDefinition(
			"Synchronize automatically if the remote storage becomes available",
			"States if it should be tried to synchronize automatically if the remote storage becomes available after it was not available.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EPACKAGE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"autoSyncOnRemoteAvailable",
			"false");
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition RESOLVE_CONFLICTS_AUTOMATICALLY = new AbstractMohitoAnnotationDefinition(
			"Resolve conflicts automatically",
			"States if conflicting changes should be handled and resolved automatically without any possibility for manual intervention.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EPACKAGE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"resolveConflictsAutomatically",
			"false");
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition SERVER_WINS_AUTOMATIC_CONFLICT_RESOLUTION = new AbstractMohitoAnnotationDefinition(
			"Server wins automatic conflict resolution",
			"Determines if the server or the client wins automatic conflict resolution.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EPACKAGE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"serverWinsAutomaticConflictResolution",
			"true");

	/**Creates the definition(s) of the category 'database'.
	 */
	public SynchronizationMohitoAnnotationCategory() {
		super(NAMESPACE, PREFER_WORKING_LOCAL, AUTO_SYNC_ON_REMOTE_AVAILABLE,
				RESOLVE_CONFLICTS_AUTOMATICALLY,
				SERVER_WINS_AUTOMATIC_CONFLICT_RESOLUTION);
	}

}
