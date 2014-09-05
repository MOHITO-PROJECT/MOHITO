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

import com.google.inject.Inject
import de.modagile.generator.android.templates.ModagileFolderConstants
import de.modagile.generator.android.templates.java.JavaUtils
import de.modagile.metamodel.app.MobileApp
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.List
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.generator.android.templates.EcoreModelUtils


/**
 * Contains helper specific generator methods and calls all helper sub de.modagile.generator.android.templates
 * */
class DatabaseHelperTemplate {
	
	@Inject extension JavaUtils javaUtilities
	@Inject extension EcoreModelUtils ecoreModelUtilities
	
	def generateDatabaseHelper(IFileSystemAccess fsa, List<EClass> entities, MobileApp m, PackageInfo packageInfo) {
		fsa.generateFile(packageInfo.getPackageDir+"contentprovider/"+m.name.toFirstUpper+ "DatabaseHelper.java", ModagileFolderConstants::SRC_GEN, generateHelperCode(packageInfo.packageName, packageInfo.packageName+".contentprovider", m.name, entities))
	}
	
   def private generateHelperCode(String basePackage, String contentProviderPackageName, String appName, List<EClass> entities) { 
      '''
         «javaUtilities.statementGenerated("DatabaseHelperTemplate")»
         «javaUtilities.packageStatement(contentProviderPackageName)»
         
         import android.content.Context;
         import android.database.sqlite.SQLiteDatabase;
         import android.database.sqlite.SQLiteOpenHelper;
         import «basePackage».constants.DBConstants;
         «FOR entity: entities»
         import «basePackage».constants.«entity.name»DBConstants;
         «ENDFOR»
         
         public class «appName.toFirstUpper»DatabaseHelper extends SQLiteOpenHelper{
            public «appName.toFirstUpper»DatabaseHelper(Context context){
               super(context, DBConstants.DBNAME, null, DBConstants.DBVERSION);
            }
            
            @Override
            public void onCreate(SQLiteDatabase db) {
               createTables(db);
            }
            
            private void createTables(SQLiteDatabase db){
                StringBuilder builder;
               «FOR entity:entities SEPARATOR "\n"»
                  builder = new StringBuilder();
                  builder.append("CREATE TABLE " + DBConstants.TABLE_NAME_«entity.name.toUpperCase» + "(");
                  builder.append(DBConstants.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
                  builder.append(DBConstants.UUID + " TEXT, ");
                  builder.append(«entity.name»DBConstants.LASTUPDATE + " REAL");
                  «IF entity.EAttributes.size >0 »
                    builder.append(", ");
                  «ENDIF»
                  «FOR attribute:entity.EAttributes SEPARATOR "\nbuilder.append(\", \");"»
                     builder.append(«entity.name»DBConstants.«attribute.name.toUpperCase» + " «getSQLType(attribute)»");
                  «ENDFOR»
                  «IF outgoingToOneEReferences(entity).size >0 »
                    builder.append(", ");
                  «ENDIF»
                  «FOR reference:outgoingToOneEReferences(entity) SEPARATOR "\nbuilder.append(\", \");"»
	              	 builder.append(«entity.name»DBConstants.«reference.name.toUpperCase»_UUID + " INTEGER");
                  «ENDFOR»
                  «IF incomingOneToManyEReferences(entity, entities).size >0 »
                    builder.append(", ");
                  «ENDIF»
                  «FOR incomingReference:incomingOneToManyEReferences(entity, entities) SEPARATOR "\nbuilder.append(\", \");"»
                  	builder.append(«entity.name»DBConstants.«incomingReferenceName(incomingReference)»_UUID + " INTEGER");
                  «ENDFOR»
                  builder.append(")");
                  db.execSQL(builder.toString());
               «ENDFOR»
            }
            
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
               «FOR entity:entities»
                  db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME_«entity.name.toUpperCase»);
               «ENDFOR»
               createTables(db);
            }
         }
      '''
   }
   
   def private getSQLType(EAttribute attribute) { 
      switch(attribute.EType.instanceClassName){
        case "java.lang.String": "TEXT"
        case "java.util.Date": "REAL"
        case "java.lang.Integer": "INTEGER"
        case "java.lang.Double": "REAL"
        case "java.lang.Long": "INTEGER"
        case "java.lang.Boolean": "INTEGER"
        case "boolean" : "INTEGER"
      }
   }
   
}