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
package de.modagile.generator.android.templates.service

import com.google.inject.Inject
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.ArrayList
import java.util.List
import info.multiplatform.generator.java.helper.Pair
import java.util.Set
import java.util.HashSet

class AccountAuthenticatorServiceTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateAccountAuthenticatorService(String packagePrefix, String appName , IFileSystemAccess fileSystemAccess, String outputConfiguration){
		fileSystemAccess.generateFile(packagePrefix+"service/AccountAuthenticatorService.java", outputConfiguration, generateAccountAuthenticatorCode(packagePrefix.replaceAll("/",".")+"service", "AccountAuthenticatorService"))
	}
	
	def generateAccountAuthenticatorCode(String packageName, String domainEntityName){
		var Set<String> imports = new HashSet<String>();
		imports.add("android.app.Service");
		imports.add("android.content.Intent");
		imports.add("android.os.IBinder");
		
		var String inheritance  = "Service";
		
		generateClass("Account", packageName, domainEntityName,
						imports, 
						inheritance,
						new ArrayList())
	}
	
	def  generateClass(String templateName, String packageName, String domainEntityName,
							Set<String> imports,
							String inheritance,
							List<String> interfaces) {
		var List<Pair<String, String>> attr = new ArrayList<Pair<String, String>>();
		attr.add(new Pair("AccountAuthenticator","sAccountAuthenticator"));		
						
		'''«javaUtilities.statementGenerated(templateName)»
			«javaUtilities.packageStatement(packageName)»
			«javaUtilities.importStatements(imports)»
			«javaUtilities.classDecl(domainEntityName, inheritance, interfaces, false)»
			«javaUtilities.generateAdditionalAttributes(attr, true, false)»
			/**
			 * Constructor for this service.
			 */
			«javaUtilities.generateConstructorDecl(domainEntityName, null, "super();")»
			«generateMethodsToBeImplemented()»
		}'''
	}
	
	def generateMethodsToBeImplemented(){
		'''
		«gernateOnBind»
		«generateGetAuthenticator»
		'''
	}
	
	def gernateOnBind(){
		'''
		/**
	 	* This method must return a subclass of AbstractAccountAuthenticator.
	 	*/
		@Override
		public IBinder onBind(Intent intent) {
			IBinder binder = null;
			if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT)) {
				binder = getAuthenticator().getIBinder();
			}
			return binder;
		}
		'''
	}
	
	def generateGetAuthenticator() {
		'''
		private AccountAuthenticator getAuthenticator() {
			if (sAccountAuthenticator == null) {
				sAccountAuthenticator = new AccountAuthenticator(this);
			}
			return sAccountAuthenticator;
		}
		'''
	}
}