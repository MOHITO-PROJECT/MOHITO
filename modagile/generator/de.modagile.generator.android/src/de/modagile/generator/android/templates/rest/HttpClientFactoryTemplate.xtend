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
import info.multiplatform.generator.java.helper.Triple
import java.util.HashSet
import java.util.Set

class HttpClientFactoryTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateHttpClientFactory(String packagePrefix, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"rest/HttpClientFactory.java", outputConfiguration, generateHttpClientFactoryCode(packagePrefix.replaceAll("/",".")+"rest"))
	}
	
	def generateHttpClientFactoryImpl(String packagePrefix, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"rest/HttpClientFactoryImpl.java", outputConfiguration, generateHttpClientFactoryImplCode(packagePrefix.replaceAll("/",".")+"rest"))
	}
	
	/** HttpClientFactory Methods*/
	def generateHttpClientFactoryCode(String packageName) {
		var Set<String> imports = new HashSet<String>();
		imports.add("org.apache.http.client.HttpClient");
		'''
		«generateHttpClientFactoryCodeInterface("HttpClientFactoryTemplate", packageName, imports, null, new ArrayList())»
		'''
	}
	
	def generateHttpClientFactoryCodeInterface(String templateName, String packageName, 
													 Set<String> imports,
													 String inheritance,
													 List<String> interfaces) {
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.interfaceDecl("HttpClientFactory", null)»
		«generateHttpClientFactoryMethod()»
		}
		'''
	}
	
	def generateHttpClientFactoryMethod(){
		'''
		HttpClient createHttpClientWithParams();
		'''
	}
	
	/**HttpClientFactoryImpl Methods*/
	def generateHttpClientFactoryImplCode(String packageName) {
		var Set<String> imports = new HashSet<String>();
		var List<String> interfaces = new ArrayList<String>();
		interfaces.add("HttpClientFactory");
		imports.add("org.apache.http.client.HttpClient");
		imports.add(packageName.replace("rest","helper")+".RestHelper");
		'''
		«generateHttpClientFactoryImplClass("HttpClientFactoryTemplate", packageName, imports, null,interfaces)»
		}
		'''
	}
	
		def generateHttpClientFactoryImplClass(String templateName, String packageName, 
													 Set<String> imports,
													 String inheritance,
													 List<String> interfaces) {
		var List<Triple<String, String, String>> attributes = new ArrayList<Triple<String, String, String>>();
		attributes.add(new Triple<String, String, String>("HttpClientFactory","instance", "new HttpClientFactoryImpl()"))
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("HttpClientFactoryImpl", inheritance, interfaces, false)»
		«javaUtilities.generateAdditionalAttributesWithExpression(attributes.get(0), true, true)»
		«generateHttpClientFactoryImplMethods»
		'''
	}
	
	def generateHttpClientFactoryImplMethods(){
		'''
		«getInstance()»
		«createHttpClientWithParams()»
		'''
	}
	
	def getInstance(){
		'''
		public static HttpClientFactory getInstance(){
			return instance;
		}
		'''
	}
	
	def createHttpClientWithParams(){
		'''
		@Override
		public HttpClient createHttpClientWithParams() {
			return RestHelper.createHttpClientWithParams();
		}
		'''
	}
}