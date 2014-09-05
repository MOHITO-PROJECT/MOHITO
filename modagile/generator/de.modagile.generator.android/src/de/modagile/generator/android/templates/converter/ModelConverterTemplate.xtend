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
package de.modagile.generator.android.templates.converter

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EClass
import de.modagile.generator.android.templates.java.JavaUtils
import javax.inject.Inject
import org.eclipse.emf.ecore.EAttribute
import info.multiplatform.generator.java.templates.PackageInfo
import de.modagile.generator.android.templates.ModagileFolderConstants
import java.util.Set
import java.util.HashSet

class ModelConverterTemplate {
   
   @Inject extension JavaUtils javaUtils
   
   def generateModelConverter(IFileSystemAccess fsa, EClass entity, PackageInfo packageInfo){
      fsa.generateFile(packageInfo.packageDir + "model/" + entity.name + "Converter.java", ModagileFolderConstants::SRC_GEN, generateSource(packageInfo.packageName, packageInfo.packageName + ".model", entity))
   }
   
   def private generateSource(String basePackageName, String modelPackageName, EClass entity) {
      var Set<String> imports = new HashSet<String>();
      getModelConverterImports(imports, basePackageName, entity);
      '''
         «javaUtils.statementGenerated("ModelFactoryTemplate")»
         «javaUtils.packageStatement(modelPackageName)»
        «javaUtils.importStatements(imports)»
        «javaUtils.classDecl(entity.name+"Converter", null, null, false)»
         
             «generateParseEntityList(entity)»     
             
             «generateParseEntity(entity)»    
             
             «generateGetJSONEntity(entity)»  
             
             «generateCreateProjectionMap(entity)»
             
             «generateCursorToEntity(entity)»
             
             «generateEntityToValues(entity)»
         }   
      '''
   }

    def getModelConverterImports(Set<String> imports, String basePackageName, EClass entity){
        imports.add("java.util.ArrayList");
        imports.add("java.util.HashMap");
        imports.add("java.util.List");
        imports.add("java.util.Date");
        imports.add("org.json.JSONArray");
        imports.add("org.json.JSONException");
        imports.add("org.json.JSONObject");
        imports.add("android.util.Base64");
        imports.add("android.content.ContentValues");
        imports.add("android.database.Cursor");
        imports.add("android.util.Log");
        imports.add(basePackageName+".constants.DBConstants");
        imports.add(basePackageName+".constants."+entity.name+"DBConstants");
    }

   def private generateParseEntityList(EClass entity) {
   '''
      public static List<«entity.name»> parse«entity.name»List(JSONArray jsonArray) throws JSONException {
         List<«entity.name»> list = new ArrayList<«entity.name»>();
         for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Log.i(«entity.name».TAG, "«entity.name.toLowerCase»" + i + ": " + json.toString());
            «entity.name» entity = parse«entity.name»(json);
            list.add(entity);
         }
         return list;
      }
   '''
   }
    
   def private generateParseEntity(EClass entity) {
   '''
      public static «entity.name» parse«entity.name»(JSONObject json) throws JSONException {
         «entity.name» entity = new «entity.name»();
         entity.setId(null);
         entity.setLastUpdate(json.getLong(«entity.name»DBConstants.LASTUPDATE));
         «FOR attribute: entity.EAttributes»
            «val String type = attribute.typeName»
            «IF type.equals("Date")»
               entity.set«attribute.name.toFirstUpper»(new Date(json.getLong(«entity.name»DBConstants.«attribute.name.toUpperCase»)));
            «ELSEIF type.equals("byte[]")»
               entity.set«attribute.name.toFirstUpper»(Base64.decode(json.getString(«entity.name»DBConstants.«attribute.name.toUpperCase»), Base64.DEFAULT));
            «ELSE»
               entity.set«attribute.name.toFirstUpper»(json.get«type.toFirstUpper»(«entity.name»DBConstants.«attribute.name.toUpperCase»));
            «ENDIF»
         «ENDFOR»
         return entity;
      }
   '''
   }
   
   /**
    * Returns the name of the java class for the attribute type (String, Int, etc.)
    * Equal to the simplename
    * 
    */
   def private String getTypeName(EAttribute attribute) { 
      return attribute.EType.instanceClass.simpleName;
   }

   def private generateGetJSONEntity(EClass entity) {
   '''
      public static String getJson(«entity.name» entity) {
         JSONObject jsonObject = new JSONObject();
         try {
            jsonObject.put(DBConstants.ID, entity.getId());
            jsonObject.put(«entity.name»DBConstants.LASTUPDATE, entity.getLastUpdate());
            «FOR attribute: entity.EAttributes»
               jsonObject.put(«entity.name»DBConstants.«attribute.name.toUpperCase», entity.get«attribute.name.toFirstUpper»());
            «ENDFOR»
            Log.d(«entity.name».TAG, "jsonified «entity.name»" + jsonObject.toString());
            return jsonObject.toString();
         } catch (JSONException e) {
            e.printStackTrace();
         }
         return null;
      }
   '''
   }

   def private generateCreateProjectionMap(EClass entity) {
   '''
      public static HashMap<String, String> createProjectionHashmap() {
         HashMap<String, String> projectionMap = new HashMap<String, String>();
         projectionMap.put(DBConstants.ID, DBConstants.ID);
         projectionMap.put(«entity.name»DBConstants.LASTUPDATE, «entity.name»DBConstants.LASTUPDATE);
         «FOR attribute: entity.EAttributes»
            projectionMap.put(«entity.name»DBConstants.«attribute.name.toUpperCase», «entity.name»DBConstants.«attribute.name.toUpperCase»);
         «ENDFOR»
        return projectionMap;
      }
   '''
   }
   
   def private generateCursorToEntity(EClass entity) {
   '''
      public static «entity.name» cursorTo«entity.name»(Cursor cursor) {
         «entity.name» entity = new «entity.name»();
         entity.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));
         entity.setLastUpdate(cursor.getLong(cursor.getColumnIndex(«entity.name»DBConstants.LASTUPDATE)));
         «FOR attribute: entity.EAttributes»
            «val type = attribute.typeName»
            «IF type.equals("Date")»
               entity.set«attribute.name.toFirstUpper»(new Date(cursor.getLong(cursor.getColumnIndex(«entity.name»DBConstants.«attribute.name.toUpperCase»))));
            «ELSEIF type.equals("byte[]")»
               entity.set«attribute.name.toFirstUpper»(cursor.getBlob(cursor.getColumnIndex(«entity.name»DBConstants.«attribute.name.toUpperCase»)));
            «ELSEIF type.equals("boolean")»
               entity.set«attribute.name.toFirstUpper»(Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(«entity.name»DBConstants.«attribute.name.toUpperCase»))));
            «ELSE»
               entity.set«attribute.name.toFirstUpper»(cursor.get«type.toFirstUpper»(cursor.getColumnIndex(«entity.name»DBConstants.«attribute.name.toUpperCase»)));
            «ENDIF»
         «ENDFOR»
         return entity;
      }
   '''
   }
   
   def private generateEntityToValues(EClass entity) {
   '''
      public static ContentValues «entity.name.toFirstLower»ToValues(«entity.name» entity) {
         ContentValues values = new ContentValues();
         values.put(DBConstants.ID, entity.getId());
         values.put(«entity.name»DBConstants.LASTUPDATE, entity.getLastUpdate());
         «FOR attribute : entity.EAttributes»
            «IF attribute.typeName.equals("Date")»
               values.put(«entity.name»DBConstants.«attribute.name.toUpperCase», entity.get«attribute.name.toFirstUpper»().getTime());
            «ELSE»
               values.put(«entity.name»DBConstants.«attribute.name.toUpperCase», entity.get«attribute.name.toFirstUpper»());
            «ENDIF»
         «ENDFOR»
         return values;
      }
   '''
   }
}