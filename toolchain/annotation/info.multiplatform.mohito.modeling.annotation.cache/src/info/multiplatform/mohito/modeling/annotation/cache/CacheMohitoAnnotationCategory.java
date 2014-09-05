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
package info.multiplatform.mohito.modeling.annotation.cache;

import info.multiplatform.mohito.modeling.annotation.AbstractMohitoAnnotationCategoryDefinition;
import info.multiplatform.mohito.modeling.annotation.AbstractMohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationsDefinitionsConstants;

/**Definitions for the MOHITO-Annotation category 'cache'.
 * @author hgroenda
 *
 */
public class CacheMohitoAnnotationCategory extends
		AbstractMohitoAnnotationCategoryDefinition {
	/** Namespace of this category. */
	public static final String NAMESPACE = "http://www.multiplatform.info/mohito/annotations/cache";
	
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition CLASS_IS_CACHEABLE = new AbstractMohitoAnnotationDefinition(
			"Cacheable class",
			"Flag determining if this class can be cached or caching is forbidden.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_ECLASS,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"classCacheable", Boolean.TRUE.toString());
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition ATTRIBUTE_IS_CACHEABLE = new AbstractMohitoAnnotationDefinition(
			"Cacheable attribute",
			"Flag determining if this attribute can be cached or caching is forbidden.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"attributeCacheable", Boolean.TRUE.toString());

	
	/**Default constructor using the parent helper class for the definition(s) of the category 'cache'.
	 */
	public CacheMohitoAnnotationCategory() {
		super(NAMESPACE, CLASS_IS_CACHEABLE, ATTRIBUTE_IS_CACHEABLE);
	}
}
