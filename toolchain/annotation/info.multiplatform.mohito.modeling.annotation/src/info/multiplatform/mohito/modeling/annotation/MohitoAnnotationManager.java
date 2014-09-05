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
package info.multiplatform.mohito.modeling.annotation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EcoreFactory;

/**Manages access to MOHITO-Annotations.
 * Provides convenience functions for accessing annotations of elements.
 * @author hgroenda
 *
 */
public class MohitoAnnotationManager {
	
	/**Returns if the element has an annotation for the provided definition.
	 * @param element The element.
	 * @param annotationDefinition The definition of the annotation entry.
	 * @return <code>true</code> if the element has an annotation, false otherwise.
	 */
	public static boolean hasAnnotation(EModelElement element,
			MohitoAnnotationDefinition annotationDefinition) {
		EAnnotation eAnnotation = element.getEAnnotation(annotationDefinition.getNamespace());
		if (eAnnotation == null) {
			return false;
		} else {
			if (eAnnotation.getDetails().get(annotationDefinition.getProperty()) == null) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**Removes the annotation.
	 * @param element The element.
	 * @param annotationDefinition The definition of the annotation entry.
	 */
	public static void removeAnnotation(EModelElement element, MohitoAnnotationDefinition annotationDefinition) {
		EAnnotation eAnnotation = element.getEAnnotation(annotationDefinition.getNamespace());
		if (eAnnotation == null) {
			return;
		}
		eAnnotation.getDetails().removeKey(annotationDefinition.getProperty());
		if (eAnnotation.getDetails().size() == 0) {
			element.getEAnnotations().remove(eAnnotation);
		}
	}
	
	/**Sets the annotation to a given value.
	 * @param element The element.
	 * @param annotationDefinition The definition of the annotation entry.
	 * @param value The object used to set the value via {@link #toString()}.
	 */
	public static void setAnnotation(EModelElement element, MohitoAnnotationDefinition annotationDefinition, Object value) {
		EAnnotation eAnnotation = element.getEAnnotation(annotationDefinition.getNamespace());
		if (eAnnotation == null) {
			eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(annotationDefinition.getNamespace());
			element.getEAnnotations().add(eAnnotation);
		}
		eAnnotation.getDetails().put(annotationDefinition.getProperty(), value.toString());
	}

	/**Returns the value of the annotation for the element. Returns the default value if the element has no annotation.
	 * @param element The element.
	 * @param annotationDefinition The definition of the annotation entry.
	 * @return Object of according type or value.
	 */
	public static Object getAnnotation(EModelElement element,
			MohitoAnnotationDefinition annotationDefinition) {
		EList<EAnnotation> list = element.getEAnnotations();
		if (list != null) {
			for (EAnnotation eAnnotation: list) {
				if (eAnnotation.getSource().equals(annotationDefinition.getNamespace())) {
					String annotation = eAnnotation.getDetails().get(annotationDefinition.getProperty());
					if (annotation == null) {
						return annotationDefinition.getDefaultValue();
					} else {
						if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN)) {
							return Boolean.parseBoolean(annotation);
						} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_STRING)) {
							return annotation;
						} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_INTEGER)) {
							return Integer.parseInt(annotation);
						} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_LONG)) {
							return Long.parseLong(annotation);
						} else {
							throw new IllegalArgumentException("Target type not supported. Target type is: " + annotationDefinition.getDataType());
						}
					}
				}
			}
		}
		return annotationDefinition.getDefaultValue();
	}

}
