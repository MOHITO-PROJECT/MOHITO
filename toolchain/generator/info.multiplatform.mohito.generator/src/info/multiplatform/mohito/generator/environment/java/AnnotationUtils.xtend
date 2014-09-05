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
package info.multiplatform.mohito.generator.environment.java

import org.eclipse.emf.ecore.EModelElement
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationsDefinitionsConstants
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EAnnotation
import org.eclipse.emf.ecore.EReference

/**Utility class for handling annotations of domain elements.
 * 
 * @author groenda
 */
class AnnotationUtils {
	
	/**@param element The element, which may contain annotations.
	 * @param annotationDefinition The definition of the annotation.
	 * @return The value for the annotation. The default is returned if the annotation is not explicitly set.
	 */
	def String getValueForAnnotation(EModelElement element, MohitoAnnotationDefinition annotationDefinition) {
		if (annotationDefinition.scope == MohitoAnnotationsDefinitionsConstants::TARGET_TYPE_ECLASS && 
				! ( element instanceof EClass)) {
			throw new IllegalArgumentException("Requested an annotation for EClasses on another type of object.");
		}
		if (annotationDefinition.scope == MohitoAnnotationsDefinitionsConstants::TARGET_TYPE_EATTRIBUTE && 
				! ( element instanceof EAttribute || element instanceof EReference)) {
			throw new IllegalArgumentException("Requested an annotation for EAttributes on another type of object.");
		}
		val EAnnotation categoryAnnotation = element.getEAnnotations.findFirst[it.source == annotationDefinition.namespace]
		if (categoryAnnotation == null) {
			return annotationDefinition.defaultValue
		} else {
			val String value = categoryAnnotation.details.get(annotationDefinition.property)
			if (value == null) {
				return annotationDefinition.defaultValue
			} else {
				return value;
			}
		}
	}
	
	/**@param element The element, which may contain annotations.
	 * @param annotationDefinition The definition of the annotation.
	 * @return The value for the annotation. The default is returned if the annotation is not explicitly set.
	 */
	def Boolean getValueForAnnotationAsBoolean(EModelElement element, MohitoAnnotationDefinition annotationDefinition) {
		if (annotationDefinition.dataType != MohitoAnnotationsDefinitionsConstants::DATA_TYPE_BOOLEAN) {
			throw new IllegalArgumentException("Boolean values can only be requested for definitions with data type boolean.");
		}
		"true".equals(getValueForAnnotation(element, annotationDefinition));
	}
	
	/**@param element The element, which may contain annotations.
	 * @param annotationDefinition The definition of the annotation.
	 * @return The value for the annotation. The default is returned if the annotation is not explicitly set.
	 */
	def String getValueForAnnotationAsString(EModelElement element, MohitoAnnotationDefinition annotationDefinition) {
		if (annotationDefinition.dataType != MohitoAnnotationsDefinitionsConstants::DATA_TYPE_STRING) {
			throw new IllegalArgumentException("Boolean values can only be requested for definitions with data type boolean.");
		}
		getValueForAnnotation(element, annotationDefinition);
	}
	
}