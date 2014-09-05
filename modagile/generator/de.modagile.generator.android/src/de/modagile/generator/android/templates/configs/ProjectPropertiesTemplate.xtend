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
package de.modagile.generator.android.templates.configs

import org.eclipse.xtext.generator.IFileSystemAccess

/** 
 * Produces the necessary classpath file for Java.
 * */
class ProjectPropertiesTemplate {


	def public generateProjectProperties(IFileSystemAccess fsa ) { 
 		fsa.generateFile("project.properties", IFileSystemAccess::DEFAULT_OUTPUT, projectProjectPropertiesCode()); 	
 	} 

	def public generateProjectPropertiesWithSherlock(IFileSystemAccess fsa ) { 
 		fsa.generateFile("project.properties", IFileSystemAccess::DEFAULT_OUTPUT, projectProjectPropertiesCodeWithSherlock()); 	
 	} 
 	
 	def private projectProjectPropertiesCode() {
	'''
	target=Google Inc.:Google APIs:14
	android.library=false
	'''
 	}
 	
 	def private projectProjectPropertiesCodeWithSherlock() {
	'''
	target=Google Inc.:Google APIs:14
	android.library=false
	android.library.reference.1=../com.actionbarsherlock
	'''
 	}
	
	
}