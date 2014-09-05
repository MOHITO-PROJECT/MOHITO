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
import java.util.ArrayList
import java.util.List

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EClass
import java.util.Set
import java.util.HashSet

class EntityRestClientTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateEntityRestClient(String packagePrefix, EClass domainEntity, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"rest/"+domainEntity.name +"RestClient.java", outputConfiguration, generateEntityRestClientCode(packagePrefix.replaceAll("/",".")+"rest", domainEntity))
	}
	
	def generateEntityRestClientCode(String packageName, EClass domainEntity) {
		var Set<String> imports = new HashSet<String>();
		var String inheritance ="BaseRestClientImpl<"+domainEntity.name.toFirstUpper+">";
		getImports(imports, domainEntity.name.toFirstUpper(), packageName)
		'''
		«generateEntityRestClientClass("EntityRestClientTemplate", packageName, imports, domainEntity.name, inheritance, new ArrayList())»
		'''
	}
	
	def getImports(Set<String> imports, String domainEntityName, String packageName){
		imports.add("android.content.Context");
		imports.add(packageName.replace("rest", "convert.")+domainEntityName+"Converter");
		imports.add(packageName.replace("rest", "convert.")+"JsonConverter");
		imports.add(packageName.replace("rest", "model.")+domainEntityName);
	}
	
	def generateEntityRestClientClass(String templateName, String packageName, Set<String> imports, String domainEntityName,
													 							   String inheritance,
													 						List<String> interfaces) {
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl(domainEntityName.toFirstUpper+"RestClient", inheritance , interfaces, false)»
		«generateConstructors(domainEntityName)»
		«generateMethodsToBeImplemented(domainEntityName)»
		}
		'''
	}
	
	def generateConstructors(String domainEntityName){
		'''
		public «domainEntityName.toFirstUpper»RestClient(Context ctx, HttpClientFactory httpClientFactory) {
			super(ctx, httpClientFactory);
		}
		
		public «domainEntityName.toFirstUpper»RestClient(Context ctx) {
			this(ctx, new HttpClientFactoryImpl());
		}
		'''
	}
	
	def generateMethodsToBeImplemented(String domainEntityName){
		'''
		«getEntitiyListSuffix(domainEntityName)»
		«createJSonConverter(domainEntityName)»
		'''
	}
	
	def getEntitiyListSuffix(String domainEntityName){
		'''
		@Override
		protected String getEntityListSuffix(){
			return "/«domainEntityName.toLowerCase»s";
		}
		'''
	}
	
	def createJSonConverter(String domainEntityName){
		'''
		@Override
		protected JsonConverter<«domainEntityName.toFirstUpper»> createJsonConverter() {
			return new «domainEntityName.toFirstUpper»Converter();
		}
		'''
	}
}