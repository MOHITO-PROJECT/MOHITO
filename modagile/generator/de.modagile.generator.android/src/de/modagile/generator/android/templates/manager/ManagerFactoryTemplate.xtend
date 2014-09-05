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

import java.util.ArrayList
import java.util.List

import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.Set
import java.util.HashSet

class ManagerFactoryTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	/**
	 * Creates the Interface ManagerFactory 
	 */
	def generateManagerFactory(String packagePrefix,  MobileApp m, IFileSystemAccess fsa, String outputConfiguration) {
		fsa.generateFile(packagePrefix+"manager/ManagerFactory.java", outputConfiguration, generateManagerFactoryCode(packagePrefix.replaceAll("/",".")+"manager", m))
	}
	def generateManagerFactoryImpl(String packagePrefix, MobileApp m, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"manager/impl/ManagerFactoryImpl.java", outputConfiguration, generateManagerFactoryImplCode(packagePrefix.replaceAll("/",".")+"manager.impl", m))
	}
	
	/*
	 * ManagerFactory Methods
	 * */
	def generateManagerFactoryCode(String packageName, MobileApp m){
		var Set<String> imports = new HashSet<String>();
		getImports(imports);
		'''
		«generateManagerFactoryClass("ManagerFactoryTemplate", packageName, imports, null, m)»
		'''
	}
	
	def getImports(Set<String> imports){
		imports.add("java.io.Serializable");
		imports.add("java.util.Collection");
		imports.add("android.net.Uri");
		imports.add("android.content.Context");
	}
	
	def generateManagerFactoryClass(String templateName, String packageName, Set<String> imports,
													 							    String inheritance, MobileApp m){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.interfaceDecl("ManagerFactory", inheritance)»
		«generateInterfaceMethods(m)»
		}
		'''
	}
	
	def generateInterfaceMethods(MobileApp m){
		'''
		«FOR EClass domainEntity: javaUtilities.getAllDomainEntities(m)»
			public «domainEntity.name»Manager create«domainEntity.name»Manager(Context ctx);
		«ENDFOR»
		'''
	}
	
	/*
	 * ManagerFactoryImpl Methods
	 * */
	def generateManagerFactoryImplCode(String packageName, MobileApp m){
		var Set<String> imports = new HashSet<String>();
		var List<String> interfaces = new ArrayList<String>();
		getManagerFactoryImplImports(imports, packageName, m);
		interfaces.add("ManagerFactory");
		'''
		«generateManagerFactoryImplClass("ManagerFactoryTemplate", packageName, imports, null , interfaces , m)»
		'''
	}
	
	def getManagerFactoryImplImports(Set<String> imports, String packageName, MobileApp m){
		imports.add("android.content.Context");
		for(EClass domainEntity: javaUtilities.getAllDomainEntities(m)){
			imports.add(packageName.replace("impl","")+domainEntity.name+"Manager");
		}
		
	}
	
	def generateManagerFactoryImplClass(String templateName, String packageName, Set<String> imports, String inheritance,
																			List<String> interfaces, MobileApp m){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.interfaceDecl("ManagerFactoryImpl", inheritance)»
		«generateInterfaceMethods(m)»
		}
		'''
	}
	
	def generateInterfaceImplementationMethods(MobileApp m){
		'''
		«FOR EClass domainEntity: javaUtilities.getAllDomainEntities(m)»
			@Override
			public «domainEntity.name»Manager create«domainEntity.name»Manager(Context ctx){
				return new «domainEntity.name»ManagerImpl(ctx);
			}
		«ENDFOR»
		'''
	}

	
}