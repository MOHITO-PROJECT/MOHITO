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
package de.modagile.generator.android.templates.rest

import com.google.inject.Inject

import de.modagile.metamodel.app.MobileApp

import info.multiplatform.generator.java.helper.Pair

import java.util.ArrayList
import java.util.List

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EClass
import java.util.HashSet
import java.util.Set

class RestClientTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
		
	def generateRestClient(String packagePrefix, MobileApp m, IFileSystemAccess fsa, String outputConfiguration) {
		fsa.generateFile(packagePrefix+"rest/RestClient.java", outputConfiguration, m.generateRestClientCode(packagePrefix.replaceAll("/",".")+"rest"))
	}
	
	
	def generateRestClientCode(MobileApp m, String packageName) {
		var Set<String> imports = new HashSet<String>();
		imports.add("android.content.Context");
		'''
		«generateClass("RestClientTemplate", packageName, m, imports, null, new ArrayList())»
		'''
	}
	
	
	def generateClass(String templateName, String packageName, MobileApp m, 
													 Set<String> imports,
													 String inheritance,
													 List<String> interfaces) {
		var List<String> usedEntities = new ArrayList<String>();		
		var List<Pair<String, String>> restAttributes = new ArrayList<Pair<String, String>>(); 									 	
		'''
		«getUsedEntitiesInApp(m, usedEntities)»
		«getAdditionalAttributes(usedEntities, restAttributes)»
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("RestClient", inheritance, interfaces, false)»
		«javaUtilities.generateAdditionalAttributes(restAttributes)»
		«generateRestConstructors(usedEntities)»
		}
		'''
	}
	
	def getUsedEntitiesInApp(MobileApp m, List<String> usedEntites){
		for(EClass domainEntity: javaUtilities.getAllDomainEntities(m)){
			usedEntites.add(domainEntity.name+"RestClient");
		}
	}
	
	def getAdditionalAttributes(List<String> usedEntities, List<Pair<String, String>> restAttributes){
		for(String entity: usedEntities){
			restAttributes.add(new Pair<String, String>(entity.toFirstUpper, entity));
		}
	}
	
	def generateRestConstructors(List<String> entityRestClients){
		var List<Pair<String,String>> defconstructorParameters = new ArrayList<Pair<String,String>>();
		var String defContstructorBody = "";
		var List<Pair<String,String>> constructorParameters = new ArrayList<Pair<String,String>>();
		var String contructorBody = "";
		defconstructorParameters.add(new Pair<String, String>("Context","ctx"));
		constructorParameters.add(defconstructorParameters.get(0));
		constructorParameters.add(new Pair<String, String>("HttpClientFactory","httpClientFactory"));
		defContstructorBody = "this("+defconstructorParameters.get(0).secondElement+", HttpClientFactoryImpl.getInstance());"
		
		for(String entity : entityRestClients){
			contructorBody = contructorBody + entity + " = new "+entity.toFirstUpper +"("+constructorParameters.get(0).secondElement+","+constructorParameters.get(1).secondElement+");";
		}
		'''
		«javaUtilities.generateConstructorDecl("RestClient", defconstructorParameters, defContstructorBody)»
		«javaUtilities.generateConstructorDecl("RestClient", constructorParameters, contructorBody)»
		«generateMethodsForAttributes(entityRestClients)»
		'''
	}
	
	def generateMethodsForAttributes(List<String> entityRestClients){
		'''
		«FOR String entityRestClient: entityRestClients»
		public «entityRestClient» get«entityRestClient»() {
			return «entityRestClient»;
		}
		«ENDFOR»
		'''
	}
	
}