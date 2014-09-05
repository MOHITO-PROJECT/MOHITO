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
package de.modagile.generator.android.templates.contentprovider

import de.modagile.generator.android.templates.ModagileFolderConstants
import de.modagile.generator.android.templates.java.JavaUtils
import de.modagile.metamodel.app.MobileApp
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.List
import javax.inject.Inject
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess

/**
 * Generates URI indicator for very entity
 */
class IndicatorConstantsTemplate {
   
   @Inject extension JavaUtils javaUtils
   
   def generateIndicatorConstants(IFileSystemAccess fsa, List<EClass> entities, MobileApp m, PackageInfo packageInfo){
      fsa.generateFile(packageInfo.packageDir + "contentprovider/" + m.name.toFirstUpper + "Indicator.java", ModagileFolderConstants::SRC_GEN, generateSource(packageInfo.packageName, packageInfo.packageName + ".contentprovider", m.name.toFirstUpper, entities))
   }
   
   def private generateSource(String basePackageName, String contentPackageName, String appName, List<EClass> entities) { 
      
      '''
         «javaUtils.statementGenerated("ContentProviderConstantsTempalte")»
         «javaUtils.packageStatement(contentPackageName)»
         
         import java.util.Map;
         «FOR entity:entities»
            import «basePackageName».model.«entity.name»Converter;
         «ENDFOR»
         
         public class «appName»Indicator{
            «entityFields(entities)»
         }
      '''
   }
   
   def private entityFields(List<EClass> entities) '''
      «var counter = 0»
      «FOR entity: entities»
         //«entity.name»
         public static Map<String, String> «entity.name.toLowerCase»ProjectionMap = «entity.name»Converter.createProjectionHashmap();;
         public static final int COLLECTION_«entity.name.toUpperCase»_URI_INDICATOR = «counter = counter + 1»;
         public static final int SINGLE_«entity.name.toUpperCase»_URI_INDICATOR = «counter = counter + 1»;
      «ENDFOR»
   '''
}