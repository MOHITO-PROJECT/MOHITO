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

import de.modagile.metamodel.app.MobileApp

import info.multiplatform.generator.java.helper.Triple
import info.multiplatform.generator.java.helper.Pair

import java.util.ArrayList
import java.util.List

import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.HashSet
import java.util.Set

class SyncManagerTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateSyncManager(String packagePrefix, MobileApp m, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"manager/SyncManager.java", outputConfiguration, generateSyncManagercode(packagePrefix.replaceAll("/", ".")+"manager", m));
	}
	
	def generateSyncManagerFactory(String packagePrefix, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"manager/SyncManagerFactory.java", outputConfiguration, generateSyncManagerFactoryCode(packagePrefix.replaceAll("/", ".")+"manager"));
	}
	
	def generateSyncManagerFactoryImpl(String packagePrefix, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"manager/impl/SyncManagerFactoryImpl.java", outputConfiguration, generateSyncManagerFactoryImplCode(packagePrefix.replaceAll("/", ".")+"manager.impl"));
	}
	
	/**
	 * SyncMangaer methods
	 */
	def generateSyncManagercode(String packageName, MobileApp m){
		var Set<String> imports = new HashSet<String>();
		getSyncManagerImports(imports, packageName, m);
		'''
		«generateSyncManagerClass("SyncManagerTemplate", packageName, imports, null, new ArrayList(), m )»
		'''
	}
		
	def getSyncManagerImports(Set<String> imports, String packageName, MobileApp m){
		imports.add("android.accounts.NetworkErrorException");
		imports.add("android.content.Context");
		imports.add(packageName.replace("manager", "rest.")+"RestClient");
		for(EClass domainEntity: javaUtilities.getAllDomainEntities(m)){
				imports.add(packageName.replace("manager", "sync.")+"Sync"+domainEntity.name);
		}
	}
	
	def generateSyncManagerClass(String templateName, String packageName, Set<String> imports, String inheritance, List<String> interfaces, MobileApp m){
		var List<Pair<String, String>> attributes = new ArrayList<Pair<String, String>>();
		attributes.add(new Pair<String, String>("Context", "mCtx"));
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("SyncManager", inheritance, interfaces, false)»
		«javaUtilities.generateAdditionalAttributes(attributes)»
		«syncManagerConstructor»
		«executeAllSync(m)»
		}
		'''
	}
	
	def syncManagerConstructor(){
		'''
		public SyncManager(final Context ctx) {
			if (ctx == null) {
				throw new IllegalArgumentException("Context ctx shouldnt be null!");
			}
			mCtx = ctx;
		}
		'''
	}
	
	def executeAllSync(MobileApp m){
		'''
		/**
		 * Handles all sync'ing tasks from one place
		 * 
		 * @throws NetworkErrorException
		 *            If a problem happens
		 */
		public void executeAllSync() throws NetworkErrorException {
		RestClient rc = new RestClient(mCtx);
		«FOR EClass domainEntity: javaUtilities.getAllDomainEntities(m)»
			Sync«domainEntity.name» sync«domainEntity.name» = new Sync«domainEntity.name»(mCtx, rc.get«domainEntity.name»RestClient());
			sync«domainEntity.name».sync();
		«ENDFOR»
		}
		'''
	}

	/*
	 * SyncManagerFactory methods
	 */
	def generateSyncManagerFactoryCode(String packageName){
		var Set<String> imports = new HashSet<String>();
		imports.add("android.content.Context");
		'''
		«generateSyncManagerFactoryClass("SyncManagerTemplate", packageName, imports, null, new ArrayList())»
		'''
	}
	
	def generateSyncManagerFactoryClass(String templateName, String packageName, Set<String> imports, String inheritance, List<String> interfaces){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.interfaceDecl("SyncManagerFactory", inheritance)»
		public SyncManager createSyncManager(Context context);
		}
		'''
	}
	
	/*
	 * SyncManagerFactoryImpl methods 
	 */
	def generateSyncManagerFactoryImplCode(String packageName){
		var Set<String> imports = new HashSet<String>();
		var List<String> interfaces = new ArrayList<String>();
		getSyncMangerFactoryImplImports(imports, packageName);
		interfaces.add("SyncManagerFactory");
		'''
		«generateSyncManagerFactoryImplClass("SyncManagerTemplate", packageName, imports, null, interfaces)»
		'''
	}
	
	def generateSyncManagerFactoryImplClass(String templateName, String packageName, Set<String> imports, String inheritance, List<String> interfaces){
		var Triple<String, String, String> attribute = new Triple<String, String, String>("SyncManagerFactory", "instance", "new SyncManagerFactoryImpl()");
		
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("SyncManagerFactoryImpl", inheritance, interfaces, false)»
		«javaUtilities.generateAdditionalAttributesWithExpression(attribute, true, true)»
		«generateSynManagerFactoryImplMethods()»
		}
		'''
	}

	def getSyncMangerFactoryImplImports(Set<String> imports, String packageName){
		imports.add("android.content.Context");
		imports.add(packageName.replace("impl","")+"SyncManager");
		imports.add(packageName.replace("impl","")+"SyncManagerFactory");
	}
	
	def generateSynManagerFactoryImplMethods (){
		'''
		«getInstanceInSyncManagerFactoryImpl»
		«createSyncManagerInSyncManagerFactoryImpl»
		'''
	}
	
	def getInstanceInSyncManagerFactoryImpl(){
		'''
		public static SyncManagerFactory getInstance(){
			return instance;
		}
		'''
	}
	
	def createSyncManagerInSyncManagerFactoryImpl() {
		'''
		@Override
		public SyncManager createSyncManager(Context context) {
			return new SyncManager(context);
		}
		'''
	}
	
}