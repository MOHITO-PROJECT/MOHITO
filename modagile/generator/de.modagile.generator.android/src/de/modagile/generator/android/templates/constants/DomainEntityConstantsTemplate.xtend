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
import java.util.List
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EClass
import de.modagile.generator.android.templates.java.JavaUtils
import info.multiplatform.generator.java.templates.PackageInfo
import de.modagile.generator.android.templates.ModagileFolderConstants
import de.modagile.generator.android.templates.EcoreModelUtils

class DomainEntityConstantsTemplate {
	
	@Inject extension JavaUtils javaUtilities
	@Inject extension EcoreModelUtils ecoreModelUtilities
	
	def generateDBConstans(IFileSystemAccess fsa, List<EClass> entities, String appName, PackageInfo packageInfo){
	   fsa.generateFile(packageInfo.getPackageDir + "constants/DBConstants.java", ModagileFolderConstants::SRC_GEN, generateDBConstantsJava(packageInfo.getPackageName, packageInfo.getPackageName + ".constants", appName, entities))
	}
	
   def private generateDBConstantsJava(String basePackage, String constantsPackageName, String appName, List<EClass> entities) {
      val name = appName.toLowerCase
      '''
      «javaUtilities.statementGenerated("DBConstants")»
      «javaUtilities.packageStatement(constantsPackageName)»
      
      import android.net.Uri;
      
      public final class DBConstants{
         public static final String DBNAME = "«name».db";
         public static final int DBVERSION = 1;
         public static final String AUTHORITY = "«basePackage».«appName»Provider";
         public static final Uri PROVIDER_URI = Uri.parse("content://" + AUTHORITY);
         public static final String ID = "_id";
         public static final String UUID = "uuid";
    
         «FOR entity:entities SEPARATOR "\n"»
            public static final String TABLE_NAME_«entity.name.toUpperCase» = "«entity.name.toLowerCase»";
            public static final String CONTENT_TYPE_«entity.name.toUpperCase» = "vnd.android.cursor.dir/vnd.«name».«entity.name.toLowerCase»";
            public static final String CONTENT_ITEM_TYPE_«entity.name.toUpperCase» = "vnd.android.cursor.item/vnd.«name».«entity.name.toLowerCase»";
            public static final Uri CONTENT_URI_«entity.name.toUpperCase» = Uri.withAppendedPath(PROVIDER_URI, "«entity.name.toLowerCase»");
         «ENDFOR»
      }
      '''
   }
		
	def generateDomainEntityConstants(IFileSystemAccess fsa, EClass domainEntity, List<EClass> allDomainEntities, PackageInfo packageInfo) {
		fsa.generateFile(packageInfo.getPackageDir+"constants/"+domainEntity.name.toFirstUpper+"DBConstants.java", ModagileFolderConstants::SRC_GEN, generateDomainEntityConstantsJava(packageInfo.getPackageName + ".constants", domainEntity, allDomainEntities))
	}
	
   def private generateDomainEntityConstantsJava(String packageName, EClass entity, List<EClass> allDomainEntities) { 
      '''
      «javaUtilities.statementGenerated("EntityDBConstants")»
      «javaUtilities.packageStatement(packageName)»
      
      public final class «entity.name.toFirstUpper»DBConstants{
         public static final String LASTUPDATE = "lastupdate";
         «FOR attribute:entity.EAttributes»
            public static final String «attribute.name.toUpperCase» = "«attribute.name.toLowerCase»";
         «ENDFOR»
         «FOR reference:outgoingToOneEReferences(entity)»
         	public static final String «reference.name.toUpperCase»_UUID = "«reference.name.toLowerCase»_id";
      	 «ENDFOR»
      	 «FOR incomingReference:incomingOneToManyEReferences(entity, allDomainEntities)»
         	public static final String «incomingReferenceName(incomingReference).toUpperCase»_UUID = "«incomingReferenceName(incomingReference).toLowerCase»_id";
	     «ENDFOR»
      }
      '''
   }

}