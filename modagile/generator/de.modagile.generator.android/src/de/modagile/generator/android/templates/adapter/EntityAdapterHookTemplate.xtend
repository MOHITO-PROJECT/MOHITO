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
package de.modagile.generator.android.templates.adapter

import com.google.inject.Inject

import java.util.ArrayList
import java.util.List

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference

import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.HashSet
import java.util.Set

class EntityAdapterHookTemplate {


	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities

 
 	def generateDomainAdapters (String packagePrefix, EClass domainEntity , IFileSystemAccess fsa, String outputConfiguration, String appName, String appVersion ) { 
 		fsa.generateFile(packagePrefix+"adapter/"+domainEntity.name + "AdapterHook.java",  outputConfiguration,  domainEntity.generateDomainCode(packagePrefix.replaceAll("/",".")+"adapter", appName, appVersion))
 	} 
 
	def generateDomainCode(EClass domainEntity, String packageName, String appName, String appVersion) {
		var Set<String> imports  = new HashSet<String>();
		var String inheritance = domainEntity.name + "Adapter";
		
		imports.add("android.accounts.Account");
		imports.add("android.accounts.AccountManager");
		imports.add("android.app.Activity");
		imports.add("android.content.ContentResolver");
		imports.add("android.content.Context");
		imports.add("android.database.Cursor");
		imports.add("android.graphics.Color");
		imports.add("android.os.Bundle");
		imports.add("android.util.Log");
		imports.add("android.view.LayoutInflater");
		imports.add("android.view.View");
		imports.add("android.view.ViewGroup");
		imports.add("android.widget.CursorAdapter");
		imports.add("android.widget.TextView");
		
		imports.add(packageName.replaceAll(".adapter", "")+".R");
		imports.add(packageName.replaceAll(".adapter", "")+".constants."+appName+"Constants");
		imports.add(packageName.replaceAll(".adapter", "")+".constants."+domainEntity.name.toFirstUpper+"Constants");
		imports.add(packageName.replaceAll(".adapter", "")+".manager.impl."+domainEntity.name.toFirstUpper+"ManagerImpl");
		imports.add(packageName.replaceAll(".adapter", "")+".model."+domainEntity.name.toFirstUpper);
		
		
		generateClass("EntityAdapterTemplate", packageName, domainEntity,
							imports, 
							inheritance, 
							new ArrayList(),
							null,
							appName,
							appVersion);
	}
	
	def generateClass(String templateName, String packageName, EClass domainEntity, 
							Set<String> imports, 
							String inheritance, 
							List<String> interfaces, 
							EReference obj,
							String appName,
							String appVersion ) {
		'''«javaUtilities.statementGenerated(templateName)»
			«javaUtilities.packageStatement(packageName)»
			«javaUtilities.importStatements(imports)»
			«javaUtilities.classDecl(domainEntity.name+"AdapterHook", inheritance, interfaces, false)»
			«generateAttributes(domainEntity.name, appName)»
			«generateConstructor(domainEntity.name, appName, appVersion)»
			«generateMethodsToBeImplemented(domainEntity, appName, appVersion)»
		}''' 				
	}
	
	def generateAttributes(String domainEntityName,  String appName ){
		'''«javaUtilities.generateCodeForAttribute("String", "TAG", true, domainEntityName + ".class.getName()")»
			«javaUtilities.generateCodeForAttribute("LayoutInflater", "mInflater", true, null)»
			«javaUtilities.generateCodeForAttribute(domainEntityName+"ManagerImpl", "m"+domainEntityName+"Manager", true, null)»
			«javaUtilities.generateCodeForAttribute("Context", "mCtx", true, null)»
			«javaUtilities.generateCodeForAttribute("Account", "mAccount", false , null)»
		'''
	}
 
	 def generateConstructor(String domainEntityName,  String appName , String appVersion){
	 	'''	
	 	public «domainEntityName.toFirstUpper»AdapterHook(Context context, boolean autoRequery) {
	 		super(context, autoRequery);
	 	
	 		mCtx = context;
	 		mAccount = null;
	 		
	 		Account[] accounts = AccountManager.get(mCtx).getAccountsByType(«appName»Constants.«appName.toUpperCase+appVersion»AccountString);
	 		for (Account account : accounts) {
	 		mAccount = account;
	 	 		break;
	 	 		}
	 	 	
	 	 	// create a cursor that the Adapter should use
	 	 	String selection = «domainEntityName.toFirstUpper»Constants.DELETED + "=0";
	 	 	Cursor c = context.getContentResolver().query(«domainEntityName.toFirstUpper»Constants.CONTENT_URI, null, selection, null, null);
	 	 	
	 	 	if (context instanceof Activity) {
	 	 		((Activity) context).startManagingCursor(c);
	 	 		changeCursor(c);
	 	 	} else {
	 	 		throw new IllegalArgumentException("Context cannot be cast to Activity");
	 	 	}
	 	 	mInflater = LayoutInflater.from(context);
	 	 	m«domainEntityName.toFirstUpper»Manager = new «domainEntityName.toFirstUpper»ManagerImpl(context);
	 	 }
	 	'''
	}
 
 	def generateMethodsToBeImplemented(EClass domainEntity, String appName, String appVersion){
 		''' 
		«generateBindViewMethod(domainEntity)»
		«generateNewViewMethod(domainEntity)»
		«generateViewHolderClass(domainEntity)»
		«generateSyncDateBase(appName, appVersion)»
  		'''
 	}
 	
 	def generateBindViewMethod(EClass domainEntity){
 		'''
		/**
		 * Reuse our existing View objects for Views (rows) if they already have been created
		 */
		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			super.bindView(view, context, cursor);
		}
 		'''
 	}
 	
 	def generateNewViewMethod(EClass domainEntity){
 		'''
		/**
		 * 
		 */
		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return super.newView(context, cursor, parent);
		}
 		'''
 	}
 	
 	def generateViewHolderClass(EClass classifier){
 		'''
		/**
		 * 
		 */
		private static class ViewHolder {
		}
 		'''
 	}
 	
 	def generateSyncDateBase(String appName, String appVersion){
 		'''
		/**
		 * 
		 */
		@Override
		public void syncDatabase() {
		super.syncDatabase();
		}
 		'''
 	}
}