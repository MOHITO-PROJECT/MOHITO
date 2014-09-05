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

import com.google.inject.Inject
import de.modagile.generator.common.JavaIoFileSystemAccess2
import info.multiplatform.generator.java.helper.JavaFormatter
import info.multiplatform.mohito.generator.AbstractProjectInitializer
import info.multiplatform.mohito.modeling.annotation.generator.GeneratorMohitoAnnotationCategory
import java.util.ArrayList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator

/**
 * Generates the domain model-specific code for the Java platform.
 */
class MohitoModelGenerator implements IGenerator {
	
	/** Utilities for generating Java code. */
	@Inject extension JavaUtils javaUtils
	/** Utilities for MOHITO-Models and annotations. */
	@Inject extension AnnotationUtils annotationUtilities
	/** The implementation for initializing an eclipse project and starting the generation of code for MOHITO-Entities. */
	@Inject extension AbstractProjectInitializer projectInitializer
	/** Generator for the domain-specific model. */
	@Inject extension DomainModelTemplate modelTemplate
	/** Generator for the utilities-package of MOHITO-Models. */ 
	@Inject extension UtilitiesTemplate utilitiesTemplate
	
	override void doGenerate(Resource input, IFileSystemAccess fsa) {
		if (input.contents.size != 1 || input.contents.findFirst[it instanceof EPackage] == null) {
			throw new IllegalArgumentException("The model file with the URI " + input.URI +" must contain exactly one element of type EPackage as root.");
		}
		for (EObject o : input.contents) {
			if (o instanceof EPackage) {
				generateArtifactsForModel(o as EPackage, fsa);
			}
		}
	}

	/**Generates artifacts for the given model.
	 * 
	 * @param domainModel The MOHITO-Model.
	 * @param fsa Access to the file system used as output for generated files.
	 * 
	 */
	def generateArtifactsForModel(EPackage domainModel, IFileSystemAccess fsa) {
		if (domainModel.name.contains(".")) {
			throw new IllegalArgumentException("The domain model name must not contain a '.'. The offending name is " + domainModel.name + ".");
		}
		// determine generation settings from package
		var String packagePrefix = getValueForAnnotationAsString(domainModel, GeneratorMohitoAnnotationCategory::PACKAGE_NAME);
		var String projectName = getValueForAnnotationAsString(domainModel, GeneratorMohitoAnnotationCategory::TARGET_PROJECT_NAME);
		if (projectName.equals("")) {
			projectName = "MohitoProject"; 
		} 
		// initialize project
		val JavaIoFileSystemAccess2 jfsa = fsa as JavaIoFileSystemAccess2;
		projectInitializer.registerOutputConfigurations(jfsa);
		val project = projectInitializer.openProject(projectName);
		jfsa.setProject(project.project);
		val sourceFolders = new ArrayList<String>();
		sourceFolders.add(MohitoJavaFolderConstants::SRC.toLowerCase);
		sourceFolders.add(MohitoJavaFolderConstants::SRC_GEN.toLowerCase);
		val javaProject = projectInitializer.initializeProject(project, fsa, sourceFolders); 
		jfsa.setProject(javaProject.project);
		val packageInfo = new PackageInfo(packagePrefix, packagePrefix.packageNameToFolder).append(MohitoJavaFolderConstants::MODEL);
		// generate content
		generateDomainEntityArtifacts(fsa, domainModel, packageInfo);
		generateUtilityArtifacts(fsa, packageInfo.append(MohitoJavaFolderConstants::MODEL_UTIL), domainModel);
		// format content
		new JavaFormatter().formatAll(javaProject)
	}

}