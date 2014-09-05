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
import de.modagile.generator.android.templates.java.JavaUtils
import info.multiplatform.generator.java.helper.Pair
import info.multiplatform.generator.java.helper.Triple
import java.util.ArrayList
import java.util.List
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.Set
import java.util.HashSet

class SyncAdapterServiceTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateSyncAdapterService (String packagePrefix, String appName , IFileSystemAccess fileSystemAccess, String outputConfiguration){
		fileSystemAccess.generateFile(packagePrefix+"service/SyncAdapterService.java", outputConfiguration, generateSyncAdapterCode(packagePrefix.replaceAll("/", "."), appName, "SyncAdapterService"))
	}
	
	def generateSyncAdapterCode(String packagePrefix, String appName, String domainEntityName){
		var Set<String> imports = new HashSet<String>();
		var String inheritance = "Service";
		
		imports.add("java.text.Format");
		imports.add("java.text.SimpleDateFormat");
		imports.add("java.util.Date");
		imports.add("android.accounts.Account");
		imports.add("android.accounts.NetworkErrorException");
		imports.add("android.accounts.OperationCanceledException");
		imports.add("android.app.Service");
		imports.add("android.content.AbstractThreadedSyncAdapter");
		imports.add("android.content.ContentProviderClient");
		imports.add("android.content.Context");
		imports.add("android.content.Intent");
		imports.add("android.content.SyncResult");
		imports.add("android.os.Bundle");
		imports.add("android.os.IBinder");
		imports.add("android.util.Log");
										
		imports.add(packagePrefix+"constants."+appName+"Constants");
		imports.add(packagePrefix+"manager.SyncManager");				

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
			var List<Triple<String, String, String>> attributes = new ArrayList<Triple<String, String, String>>();
			var String innerClass = "SyncAdapterImpl";
			var List<Pair<String, String>> innterClassConstrParameters = new ArrayList<Pair<String, String>>();
			var List<Pair<String, String>> innterClassAttributes = new ArrayList<Pair<String, String>>();
			innterClassConstrParameters.add(new Pair("Context","context"));
			innterClassAttributes.add(new Pair("Context","mContext"));
			attributes.add(new Triple("String","TAG",domainEntityName+".class.getSimpleName()"));
			attributes.add(new Triple(innerClass,"sSyncAdapter","null"));			
			'''
			«javaUtilities.statementGenerated(templateName)»
			«javaUtilities.packageStatement(packageName)»
			«javaUtilities.importStatements(imports)»
			«javaUtilities.classDecl(domainEntityName, inheritance, interfaces, false)»
			«javaUtilities.generateConstructorDecl(domainEntityName, null, "super();")»
			«javaUtilities.generateAdditionalAttributesWithExpression(attributes.get(0), true, true)»
			«javaUtilities.generateAdditionalAttributesWithExpression(attributes.get(1), true, false)»
			«javaUtilities.innerClassDecl("", innerClass, "AbstractThreadedSyncAdapter", new ArrayList(), true, false)»
			«javaUtilities.generateAdditionalAttributes(innterClassAttributes, true, false)»
			«javaUtilities.generateConstructorDecl(innerClass, innterClassConstrParameters, "super(context, true); mContext = context;" )»
			«generateInnerClassOnPerformSyncMethod()»
			}
			«gernerateMethodsToBeImplemented(appName)»
			}
			'''
	}
	
	def generateInnerClassOnPerformSyncMethod(){
		'''
		@Override
		public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider,
		      SyncResult syncResult) {
			try {
				SyncAdapterService.performSync(mContext, account, extras, authority, provider, syncResult);
			} catch (OperationCanceledException e) {
			}
		}
		'''
	}
	
	def gernerateMethodsToBeImplemented(String appName){
		'''
		«generateOnBind()»
		«generateGetSyncAdapter()»
		«generatePerformSync(appName)»
		'''
	}

	def generateOnBind(){
		'''
		@Override
		public IBinder onBind(Intent intent) {
			IBinder ret = null;
			ret = getSyncAdapter().getSyncAdapterBinder();
			return ret;
		}
		'''
	}

	def generateGetSyncAdapter(){
		'''
		private SyncAdapterImpl getSyncAdapter() {
			if (sSyncAdapter == null) {
				sSyncAdapter = new SyncAdapterImpl(this);
			}
			return sSyncAdapter;
		}
		'''
	}

	def generatePerformSync(String appName){
		'''
		private static void performSync(Context context, Account account, Bundle extras, String authority,
			ContentProviderClient provider, SyncResult syncResult) throws OperationCanceledException {

			// create an intent with action for starting the sync
			Intent intent = new Intent(«appName»Constants.SYNC_START);
			// send broadcast
			context.sendBroadcast(intent);
			Log.i(TAG, "Performing sync: " + account.toString());

			// prepare synchronization
			Format f = new SimpleDateFormat(«appName»Constants.LOG_TIME_FORMAT);
			Date date = new Date();
			SyncManager sm = new SyncManager(context);
		
			try {
				Log.d(TAG, "Background sync started at " + f.format(date));
		
				// execute synchronization
				sm.executeAllSync();

				date = new Date();
				Log.d(TAG, "Background sync stopped at " + f.format(date));
			} catch (NetworkErrorException e) {
				Log.e(TAG, "Network error! Reason: " + e.getMessage());
			}

			// create an intent with action for stopping the sync
			intent = new Intent(«appName»Constants.SYNC_STOP);
			// send broadcast
			context.sendBroadcast(intent);
		}
		
		'''
	}
}