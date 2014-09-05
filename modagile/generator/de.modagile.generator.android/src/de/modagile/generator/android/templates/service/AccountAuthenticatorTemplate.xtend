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

class AccountAuthenticatorTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateAccountAuthenticator(String packagePrefix, String appName , IFileSystemAccess fileSystemAccess, String outputConfiguration){
		fileSystemAccess.generateFile(packagePrefix+"service/AccountAuthenticator.java", outputConfiguration, generateAccountAuthenticatorCode(packagePrefix.replaceAll("/", "."), appName, "AccountAuthenticator"))
	}
	
	def generateAccountAuthenticatorCode(String packagePrefix, String appName, String domainEntityName){
		var Set<String> imports = new HashSet<String>();
		imports.add("android.accounts.AbstractAccountAuthenticator");
		imports.add("android.accounts.Account");
		imports.add("android.accounts.AccountAuthenticatorResponse");
		imports.add("android.accounts.AccountManager");
		
		imports.add("android.accounts.NetworkErrorException");
		imports.add("android.content.Context");
		imports.add("android.content.Intent");
		imports.add("android.os.Bundle");
		imports.add("android.app.Activity");
		
		imports.add(packagePrefix+"R");
		imports.add(packagePrefix+"constants."+appName+"Constants")
		//
		//imports.add(packagePrefix+".activity."+???+"ListActivity")
		
		var String inheritance  = "AbstractAccountAuthenticator";
		
		generateClass("Account", packagePrefix+"service", domainEntityName,
						imports, 
						inheritance,
						new ArrayList(),
						appName)
	}
						
	def  generateClass(String templateName, String packageName, String domainEntityName,
							Set<String> imports,
							String inheritance,
							List<String> interfaces, 
							String appName) {
		var List<Pair<String, String>> attr = new ArrayList<Pair<String, String>>();
		var List<Pair<String, String>> contructorParameters = new ArrayList<Pair<String, String>>();
		attr.add(new Pair("Context","mContext"));
		contructorParameters.add(new Pair("Context","context"));	
									
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl(domainEntityName, inheritance, interfaces, false)»
		«javaUtilities.generateConstructorDecl(domainEntityName, contructorParameters, "super(context); mContext = context;")»
		«javaUtilities.generateAdditionalAttributes(attr, false, true)»
		«generateMethodsToBeImplemented(appName)»
		}
		'''
	}
	
	
	def generateMethodsToBeImplemented (String appName) {
		'''
		«generateAddAccountMethod(appName)»
		«generateConfirmCredentials»
		«generateEditProperties»
		«generateGetAuthToken»
		«generateGetAuthTokenLabel»
		«generateHasFeatures»
		«generateUpdateCredentials»
		'''
	}
	
	def generateAddAccountMethod(String appName){
		'''
		/*
		 * The user has requested to add a new account to the system. We return an intent that will launch our login screen
		 * if the user has not logged in yet, otherwise our activity will just pass the user's credentials on to the account
		 * manager.
		 */
		@Override
		public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType,
		      String[] requiredFeatures, Bundle options) throws NetworkErrorException {
	
			Bundle reply = new Bundle();
	
			// create an intent
			//the Activity.class has to be replaced with a concrete Activity
			Intent i = new Intent(mContext, Activity.class);
			i.setAction(«appName»Constants.loginActivityAction);
			i.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
			reply.putParcelable(AccountManager.KEY_INTENT, i);
	
			return reply;
		}
		'''
	}
	
	def generateConfirmCredentials (){
		'''
		@Override
		public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) {
			return null;
		}
		'''
	}
	
	def generateEditProperties(){
		'''
		@Override
		public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
			return null;
		}
		'''
	}
	
	def generateGetAuthToken(){
		'''
		@Override
		public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType,
	      Bundle options) throws NetworkErrorException {
			return null;
		}
		'''
	}
	
	def generateGetAuthTokenLabel(){
		'''
		@Override
		public String getAuthTokenLabel(String authTokenType) {
	
			if (authTokenType != null) {
				return mContext.getString(R.string.app_name);
			}
	
			return null;
		}
		'''
	}
	
	def generateHasFeatures(){
		'''
		@Override
		public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features)
	    	throws NetworkErrorException {
			return null;
		}
		'''
	}
	
	def generateUpdateCredentials(){
		'''
		@Override
		public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType,
			Bundle options) {
			return null;
		}
		'''
	}
	
}