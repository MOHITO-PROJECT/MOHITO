/** 
 * Copyright (c) 2012-2014 ModAgile Mobile (http://www.modagile-mobile.de/) and others.
 *
 * Licensed under Eclipse Public License - v 1.0
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE 
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package info.multiplatform.generator.java.templates.model

import com.google.inject.Inject
import info.multiplatform.generator.java.templates.JavaFolderConstants
import info.multiplatform.generator.java.templates.JavaUtils
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.Set
import java.util.HashSet
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

/**
 * Generator template for common java domain model classes.
 * 
 * The template is self contained and can be executed as it is.
 * In addition, it provides extension points by overloading the template
 * methods explicitly declared with protected access modifiers.
 * 
 * */
class DomainModelTemplate {

	@Inject extension JavaUtils javaUtilities
	
	/**
	 * Generates an entity class for the given domainEntity
	 * @param fsa The file system access instance two generate the code output.
	 * @param packageInfo The infos for the base package and code directory to work with
	 * @param domainModelSubPackage The sub package place the domain model code in (should start with '.').
	 * @param domainModelSubDirectory The sub directory of the source gen folder two write the code files to (should end with /).
	 */
	def generateDomainEntity(IFileSystemAccess fsa, EClass domainEntity, PackageInfo packageInfo, String domainModelSubPackage, String domainModelSubDirectory){
		fsa.generateFile(packageInfo.getPackageDir+domainModelSubDirectory+domainEntity.name+".java", JavaFolderConstants::SRC_GEN, generateDomainEntityModelCode(packageInfo, domainEntity, domainModelSubPackage));
	}
	
	/*
	 * DomainEntity methods
	 * */
	def private generateDomainEntityModelCode(PackageInfo packageInfo, EClass domainEntity, String domainModelSubPackage){
		var String inheritance = getSuperClass(packageInfo,domainEntity);
		var List<String> interfaces = getInterfaces(packageInfo,domainEntity);
		var Set<String> imports = getImports(packageInfo,domainEntity);
		'''
		«generateDomainEntityModelClass("DomainModelTemplate", packageInfo, imports, inheritance, interfaces, domainEntity, domainModelSubPackage,domainEntity.ESuperTypes.get(0).name != "")»
		'''
	}

	
	def private generateDomainEntityModelClass(String templateName, 
													PackageInfo packageInfo, 
													Set<String> imports, 
													String inheritance, 
													List<String> interfaces,
													EClass domainEntity,
													String domainModelSubPackage,
													Boolean inherited){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageInfo.packageName + domainModelSubPackage)»
		«javaUtilities.importStatements(imports)»
		«generateClassAnnotations(packageInfo, domainEntity)»
		«javaUtilities.classDecl(domainEntity.name, inheritance, interfaces, inherited)»
		«javaUtilities.generateConstructorDecl(templateName,domainEntity.EAttributes,inherited)»
		«generateAdditionalFields(packageInfo, domainEntity)»
		«generateCodeForClassifier(domainEntity)»
		}
		'''
	}
   
   /**
    * Get the imports for the domain model.
    */
	def private Set<String> getImports(PackageInfo packageInfo, EClass domainEntity) {
		var Set<String> imports = new HashSet<String>();
		if(domainEntity.EAttributes.hasDate){
			imports.add("java.util.Date");
		}
		imports.add("java.util.List");
		imports.addAll(getAdditionalImports(packageInfo,domainEntity));
		return imports;
	}
	
	
	/**
	 * Generate the body of a class with field declarations
	 * and getters and setters.
	 */
	def generateCodeForClassifier(EClass clazz) {
    '''
    «IF clazz != null»
		«var List<EAttribute> attsToGenerateGettersFor = new ArrayList<EAttribute>()»
		«FOR EAttribute att : clazz.EAttributes»
			«generateAttributeAnnotations(clazz, att)»
			«generateCodeForAttributeOrReference(att)»
			«var boolean bugHelperVariable = attsToGenerateGettersFor.add(att)»
		«ENDFOR»
		«FOR EAttribute attr: attsToGenerateGettersFor»
			«generateGetterSetter(attr)»
		«ENDFOR»
		«FOR EReference ref : clazz.EReferences»
			«generateCodeForAttributeOrReference(ref)»
		«ENDFOR»
		«FOR EReference ref : clazz.EReferences»
			«generateGetterSetter(ref)»
		«ENDFOR»
	«ENDIF»
		'''
	}
	
   def boolean hasDate(EList<EAttribute> attributes) {
      !attributes.forall[ attribute |
         !attribute.EType.instanceClassName.equals("java.util.Date")
      ]
   }
      
   /* ************ Template extension methods ********************* */
      
   /**
    * Get the imports for the domain model.
    */
	def protected List<String> getAdditionalImports(PackageInfo packageInfo, EClass domainEntity) {
		return new ArrayList<String>();
	}
   
   /**
    * Get the interfaces the domain model class should implement.
    * @return The names of the interfaces only without any control characters.
    */
	def protected List<String> getInterfaces(PackageInfo packageInfo, EClass domainEntity) {
		return new ArrayList<String>();
	}
   
   /**
    * Get the super class the domain model class should extend.
    * @return The name of the class to be extended. Without any code characters such as ; or "extends".
    */
	def protected String getSuperClass(PackageInfo packageInfo, EClass domainEntity) {
		return domainEntity.ESuperTypes.get(0).name;
	}
   
   /**
    * Generate any annotations to be attached to the class.
    * The method should directly generate the code.
    */
	def protected generateClassAnnotations(PackageInfo packageInfo, EClass domainEntity) {
	}
   
   /**
    * Generate the annotations to be attached to an attribute.
    * The method should directly generate the code.
    */
	def protected generateAttributeAnnotations(EClass domainEntity, EAttribute att) {
	}
   
   /**
    * Generate additional fields to the domain model class which are not related
    * to the classes attributes or references.
    * The method should directly generate the code.
    */
	def protected generateAdditionalFields(PackageInfo packageInfo, EClass domainEntity) {
	}
}