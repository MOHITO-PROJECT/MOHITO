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
package de.modagile.generator.android.templates.constants

import com.google.inject.Inject
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.ui.Flow
import java.util.List
import org.eclipse.xtext.generator.IFileSystemAccess
import info.multiplatform.generator.java.templates.PackageInfo
import de.modagile.generator.android.templates.ModagileFolderConstants

class AppConstantsTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
		
	def generateAppConstants(IFileSystemAccess fsa, List<Flow> flows, MobileApp m, PackageInfo packageInfo) {
		fsa.generateFile(packageInfo.packageDir + "constants/"+m.name.toFirstUpper+"Constants.java", ModagileFolderConstants::SRC_GEN, generateAppConstantsCode(packageInfo, flows, m.name))
	}
	
	def private generateAppConstantsCode(PackageInfo packageInfo, List<Flow> flows, String appName){
		'''
		«javaUtilities.statementGenerated("AppConstantsTemplate")»
		«javaUtilities.packageStatement(packageInfo.packageName+".constants")»
		
      public class «appName.toFirstUpper»Constants{
            «var counter = 0»
            «FOR flow: flows»
               public static final int RESULT_CODE_«flow.name.toUpperCase.replace("->", "_TO_").replace(" ", "")» = «counter = counter + 1»; 
               public static final int REQUEST_CODE_«flow.name.toUpperCase.replace("->", "_TO_").replace(" ", "")» = «counter»; 
            «ENDFOR»
            public static final String «appName.toUpperCase»_AUTHORITY = "«packageInfo.packageName».«appName»Provider";
      }		
		'''
	}
}