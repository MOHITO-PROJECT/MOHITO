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
package de.modagile.generator.android.templates.helper

import com.google.inject.Inject

import java.util.ArrayList
import java.util.List
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.HashSet
import java.util.Set

class TimeProviderTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateTimeProvider(String packagePrefix,IFileSystemAccess fsa, String outputConfiguration) {
		fsa.generateFile(packagePrefix+"helper/TimeProvider.java", outputConfiguration, generateTimeProviderCode(packagePrefix.replaceAll("/",".")+"helper"))
	}
	
	def generateTimeProviderImpl(String packagePrefix,IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"helper/TimeProviderImpl.java", outputConfiguration, generateTimeProviderImplCode(packagePrefix.replaceAll("/",".")+"helper"))
	}
	
	def generateTimeProviderCode(String packageName){
		var Set<String> imports = new HashSet<String>();
		'''
		«generateTimeProviderClass("TimeproviderTemplate", packageName, imports, null)»
		'''
	}
	
	def generateTimeProviderClass(String templateName, String packageName, Set<String> imports, 
													 							    String inheritance){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.interfaceDecl("TimeProvider", inheritance)»
		long currentTimeMillis();
		}
		'''
	}
	/*
	 * TimeProviderImpl Methods
	 * */
	def generateTimeProviderImplCode(String packageName){
		var Set<String> imports = new HashSet<String>();
		var List<String> interfaces = new ArrayList<String>();
		interfaces.add("TimeProvider");
		'''
		«generateTimeProviderImplClass("TimeproviderTemplate", packageName, imports, interfaces, null)»
		'''
	}
	
	def generateTimeProviderImplClass(String templateName, String packageName, Set<String> imports, List<String> interfaces,
													 							    String inheritance){
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("TimeProviderImpl", inheritance, interfaces, false)»
		private static TimeProviderImpl instance = new TimeProviderImpl();
		private TimeProviderImpl() {}
		
		public static TimeProvider getInstance() {
			if(instance == null) {
				instance = new TimeProviderImpl();
			}
			return instance;
		}
		@Override
		public long currentTimeMillis() {
			return System.currentTimeMillis();
		}
		}
		'''
	}
	
}