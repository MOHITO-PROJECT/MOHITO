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
package de.modagile.generator.android.templates.manager

import com.google.inject.Inject
import de.modagile.generator.android.templates.ModagileFolderConstants
import info.multiplatform.generator.java.templates.PackageInfo
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.HashSet
import java.util.Set

class DomainEntityManagerTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
		
	def generateDomainEntityManager(IFileSystemAccess fsa, EClass domainEntity, PackageInfo packageInfo) {
		fsa.generateFile(packageInfo.getPackageDir+"manager/"+domainEntity.name+"Manager.java", ModagileFolderConstants::SRC_GEN, generateDomainEntityManagerCode(packageInfo, domainEntity))
	}
	
	/*  DomainEntityManger Interfaces  */
	def private generateDomainEntityManagerCode(PackageInfo packageInfo, EClass domainEntity){
		var Set<String> imports = new HashSet<String>();
		var String inheritence = "EntityManager<"+domainEntity.name+">";
		getImports(imports, packageInfo.getPackageName, domainEntity);
	 	'''
	 	«generateDomainEntityManagerClass(packageInfo.getPackageName+".manager", imports, inheritence, domainEntity)»
	 	'''
	} 
	 
	def private getImports(Set<String> imports, String packageName, EClass domainEntity){
		imports.add("java.util.Collection");
		imports.add("java.util.List");
		imports.add("java.util.List");
		imports.add(packageName + ".model."+domainEntity.name);
	}
	 
	 def private generateDomainEntityManagerClass(String packageName, Set<String> imports,
													 							    String inheritance, EClass domainEntity){
	 	'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.interfaceDecl(domainEntity.name+"Manager", inheritance)»
		«generateInterfaceMethods(domainEntity)»
		}
	 	'''
	 }
	 
	 def private generateInterfaceMethods(EClass domainEntity){
		// TODO
		// check references, add methods to access referenced objects (synchronous and asynchronous)
	 }
	 

}