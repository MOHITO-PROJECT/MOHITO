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
package de.modagile.generator.android.templates.model

import com.google.inject.Inject
import info.multiplatform.generator.java.helper.Pair
import info.multiplatform.generator.java.templates.JavaFolderConstants
import info.multiplatform.generator.java.templates.JavaUtils
import info.multiplatform.generator.java.templates.PackageInfo
import info.multiplatform.generator.java.templates.UtilityFunctions
import info.multiplatform.generator.java.templates.model.DomainModelTemplate
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.HashSet
import java.util.Set

/**
 * Contains modagile mobile specific specific model generator methods.
 * 
 * It provides template methods to generate the Entity super class and extends
 * the generic model template to customize the generated data model in terms
 * of extending the super entity class etc.
 * */
class ModelTemplate extends DomainModelTemplate {

	@Inject extension JavaUtils javaUtilities
	@Inject extension UtilityFunctions utilities
	
	
	/**
	 * Generates an abstract Entity as a super class for all domain entities
	 */
	def generateAbstractEntity(IFileSystemAccess fsa, PackageInfo packageInfo){
		fsa.generateFile(packageInfo.getPackageDir+"model/Entity.java", JavaFolderConstants::SRC_GEN, generateEntityModelCode(packageInfo.getPackageName+".model"));
	}
	
	/**
	 * Generate the code for the Entity super class.
	 */
	def private generateEntityModelCode(String packageName){
		var Set<String> imports = new HashSet<String>();
		var List<String> interfaces = new ArrayList<String>();
		var List<Pair<String, String>> entityAttributes = new ArrayList<Pair<String, String>>();
		imports.add("java.io.Serializable");
		interfaces.add("Serializable");
		getEntityAttributes(entityAttributes);  // FIXME this doesnt cause anything, right?
		'''
		«generateEntityAttributesClass("ModelTemplate", packageName, imports, null, interfaces, entityAttributes)»
		'''
	}
	
	/**
	 * Get the attributes the Entity super class should have.
	 */
	def private getEntityAttributes(List<Pair<String, String>> entityAttributes){
		entityAttributes.add(new Pair("Long","id"));
		entityAttributes.add(new Pair("Long","lastUpdate"));
	}
	
	
	/**
	 * Generate the code of the abstract Entity super class.
	 */
	def private generateEntityAttributesClass(String templateName, String packageName, Set<String> imports, 
																				String inheritance, 
																				List<String> interfaces,
																				List<Pair<String, String>> entityAttributes){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("Entity", inheritance, interfaces, true)»
		«javaUtilities.gernerateSerializeableID(packageName, packageName, "Entity")»
		«javaUtilities.generateAdditionalAttributes(entityAttributes)»
		«utilities.generateSetterGetterForAdditionalAttributes(entityAttributes)»
		}
		'''
	}
   
   
	/**
	 * Customize the generic template method to let the domain classes extend the entity class. 
	 */
	override String getSuperClass(PackageInfo packageInfo, EClass domainEntity) {
		return "Entity";
	}
   
	override protected generateAdditionalFields(PackageInfo packageInfo, EClass domainEntity) {
		'''
		«javaUtilities.gernerateSerializeableID(packageInfo.packageName, packageInfo.packageName, domainEntity.name)»
		public static final String TAG = «domainEntity.name».class.getSimpleName();
		'''	
	}
	
	override protected getAdditionalImports(PackageInfo packageInfo, EClass domainEntity) {
		var List<String> imports = new ArrayList<String>();
		imports.add("android.content.ContentValues");
		imports.add(packageInfo.packageName + ".constants." + domainEntity.name + "DBConstants");
		return imports;
	}
}