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
package info.multiplatform.mohito.modeling.annotation.generator;
/**
 * 
 */


import info.multiplatform.mohito.modeling.annotation.AbstractMohitoAnnotationCategoryDefinition;
import info.multiplatform.mohito.modeling.annotation.AbstractMohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationsDefinitionsConstants;

/**Definitions for the MOHITO-Annotation category 'Generator'.
 * 
 * @author hgroenda
 *
 */
public class GeneratorMohitoAnnotationCategory extends
		AbstractMohitoAnnotationCategoryDefinition {
	/** Namespace of this category. */
	public static final String NAMESPACE = "http://www.multiplatform.info/mohito/annotations/generator";
	
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition TARGET_PROJECT_NAME = new AbstractMohitoAnnotationDefinition(
			"Target project name",
			"The name of the project, which is created and receives the generated implementation.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EPACKAGE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_STRING,
			"targetProjectName",
			"");
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition PACKAGE_NAME = new AbstractMohitoAnnotationDefinition(
			"Target package name",
			"The name of the packge, which is created and receives the generated implementation for the MOHITO-Entities.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EPACKAGE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_STRING,
			"packageName",
			"");

	/**Creates the definition(s) of the category 'database'.
	 */
	public GeneratorMohitoAnnotationCategory() {
		super(NAMESPACE, TARGET_PROJECT_NAME, PACKAGE_NAME);
	}

}
