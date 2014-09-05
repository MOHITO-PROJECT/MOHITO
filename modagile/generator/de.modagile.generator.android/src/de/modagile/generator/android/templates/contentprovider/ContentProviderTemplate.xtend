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

import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.metamodel.app.MobileApp
import de.modagile.generator.android.templates.java.JavaUtils
import javax.inject.Inject
import info.multiplatform.generator.java.templates.PackageInfo
import de.modagile.generator.android.templates.ModagileFolderConstants

class ContentProviderTemplate {
   
    @Inject extension JavaUtils javaUtils
   
    def generateContentProvider(IFileSystemAccess fsa, List<EClass> entities, MobileApp m, PackageInfo packageInfo){
        fsa.generateFile(packageInfo.packageDir + "contentprovider/" + m.name.toFirstUpper + "ContentProvider.java", ModagileFolderConstants::SRC_GEN, generateSource(packageInfo.packageName, packageInfo.packageName + ".contentprovider", m.name.toFirstUpper, entities))
    }
   
    def private generateSource(String basePackgeName, String contentPackageName, String appName, List<EClass> entities) {
        '''
        «javaUtils.statementGenerated("ContentProviderTemplate")»
        «javaUtils.packageStatement(contentPackageName)»
      
        import java.util.HashMap;
        import java.util.UUID;
        import android.database.Cursor;
        import android.content.ContentProvider;
        import android.content.ContentValues;
        import android.content.UriMatcher;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteQueryBuilder;
        import android.content.ContentUris;
        import android.database.SQLException;
        import android.net.Uri;
        import android.text.TextUtils;
        import «basePackgeName».constants.DBConstants;
        import static «contentPackageName».«appName»Indicator.*;
      
        public class «appName.toFirstUpper»ContentProvider extends ContentProvider{
         
            private SQLiteDatabase db;
            private «appName»DatabaseHelper dbHelper;
            private static final UriMatcher uriMatcher = buildUriMatcher();

            «generateMethodBuildUriMatcher(appName, entities)»
         
            «generateMethodOnCreate(appName)»
         
            «generateMethodDelete(entities)»
         
            «generateMethodDeleteSingle()»
         
            «generateMethodDeleteCollection()»
         
            «generateMethodGetType(appName, entities)»
         
            «generateMethodInsert(entities)»
         
            «generateMethodExecuteInsert()»
         
            «generateMethodCheckInsert()»
            
            «generateMethodCreateUUID()»
         
            «generateMethodQuery(entities)»
         
            «generateMethodUpdate(entities)»
         
            «generateMethodUpdateSingle()»
         
            «generateMethodUpdateCollection()»
         
            «generateMethodNotifyChange()»
        }  
        '''
    }
    
   
    def private generateMethodBuildUriMatcher(String appName, List<EClass> entities) {
      '''
      private static UriMatcher buildUriMatcher(){
         UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
         «FOR entity:entities»
            uriMatcher.addURI(DBConstants.AUTHORITY, "«entity.name.toLowerCase»", «appName»Indicator.COLLECTION_«entity.name.toUpperCase»_URI_INDICATOR);
            uriMatcher.addURI(DBConstants.AUTHORITY, "«entity.name.toLowerCase»/#", «appName»Indicator.SINGLE_«entity.name.toUpperCase»_URI_INDICATOR);
         «ENDFOR»
         
         return uriMatcher;
      }
      '''
    }

    def private generateMethodOnCreate(String appName) {
        '''
        @Override
        public boolean onCreate(){
            dbHelper = new «appName»DatabaseHelper(getContext());
            db = dbHelper.getWritableDatabase();
            return true;
        }
        '''
    }
   
    def private generateMethodGetType(String appName, List<EClass> entities) {
        '''
        @Override
        public String getType(Uri uri) {
            switch (uriMatcher.match(uri)) {
            «FOR entity: entities»         
                case «appName»Indicator.COLLECTION_«entity.name.toUpperCase»_URI_INDICATOR:
                return DBConstants.CONTENT_TYPE_«entity.name.toUpperCase»;
                case «appName»Indicator.SINGLE_«entity.name.toUpperCase»_URI_INDICATOR:
                return DBConstants.CONTENT_ITEM_TYPE_«entity.name.toUpperCase»;
            «ENDFOR»
            default: throw new IllegalArgumentException("Unknown URI " + uri);
            }
        }
        '''
    }

    def private generateMethodInsert(List<EClass> entities) {
        '''
        @Override
        public Uri insert(Uri uri, ContentValues values) {
            switch(uriMatcher.match(uri)) {
            «FOR entity: entities»
                case COLLECTION_«entity.name.toUpperCase»_URI_INDICATOR: return executeInsert(DBConstants.TABLE_NAME_«entity.name.toUpperCase», values, DBConstants.CONTENT_URI_«entity.name.toUpperCase»);
            «ENDFOR»
            default:
            throw new IllegalArgumentException("Unsupported indicator " + uri);
            }
        }
        '''
    }
   
    def private generateMethodExecuteInsert() {
        '''
        private Uri executeInsert(String tableName, ContentValues values, Uri uri){
        	createUUID(values);
            long rowId = db.insert(tableName, null, values);
            return checkInsert(rowId, uri);
        }
        '''
    }
   
    def generateMethodCheckInsert() {
        '''
        private Uri checkInsert(long rowId, Uri contentUri) {
            Uri insertedUri = ContentUris.withAppendedId(contentUri, rowId);
            if (rowId > 0) {
                getContext().getContentResolver().notifyChange(insertedUri, null);
                getContext().getContentResolver().notifyChange(contentUri, null);
                return insertedUri;
            }
            throw new SQLException("Failed to insert row into: " + insertedUri);
       }
       '''
    }

    def private generateMethodQuery(List<EClass> entities) {
        '''
        @Override
        public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {
            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
            Cursor cursor = null;
         
            switch(uriMatcher.match(uri)) {
            «FOR entity: entities»
               case COLLECTION_«entity.name.toUpperCase»_URI_INDICATOR: 
                  «queryCase(entity, false)»
               case SINGLE_«entity.name.toUpperCase»_URI_INDICATOR: 
                  «queryCase(entity, true)»
            «ENDFOR»
            default:
               throw new IllegalArgumentException("Unsupported indicator " + uri);
            }
            «IF entities.size > 0»
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
            «ENDIF»
            
        }
        '''
    }
   
    def private queryCase(EClass entity, boolean single) {
        '''
        qb.setTables(DBConstants.TABLE_NAME_«entity.name.toUpperCase»);
        qb.setProjectionMap(«entity.name.toLowerCase»ProjectionMap);
        «IF single»
            qb.appendWhere(DBConstants.ID + "=" + uri.getPathSegments().get(1));
        «ENDIF»
        cursor = qb.query(db, projection, selection, selectionArgs, null, null, sort);
        break;
        '''
    }
   
    def private generateMethodUpdate(List<EClass> entities) {
        '''
        @Override
        public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
            switch(uriMatcher.match(uri)) {
            «FOR entity: entities»
                case COLLECTION_«entity.name.toUpperCase»_URI_INDICATOR: return updateCollection(DBConstants.TABLE_NAME_«entity.name.toUpperCase», DBConstants.CONTENT_URI_«entity.name.toUpperCase», uri, values, where, whereArgs);
                case SINGLE_«entity.name.toUpperCase»_URI_INDICATOR: return updateSingle(DBConstants.TABLE_NAME_«entity.name.toUpperCase», DBConstants.CONTENT_URI_«entity.name.toUpperCase», uri, values, where, whereArgs);
            «ENDFOR»
                default:
                    throw new IllegalArgumentException("Unsupported indicator " + uri);
            }
        }
        '''
    }
   
    def private generateMethodUpdateSingle() {
        '''
        private int updateSingle(String tableName, Uri contentUri, Uri uri, ContentValues values, String where, String[] whereArgs) {
            int count;
            String rowId;
            String sqlUpdate;
            rowId = uri.getPathSegments().get(1);
            sqlUpdate = DBConstants.ID + "=" + rowId + (!TextUtils.isEmpty(where) ? " AND (" + where + ")" : "");
            count = db.update(tableName, values, sqlUpdate, whereArgs);

            notifyChange(contentUri, uri);
            return count;
        }
        '''
    }

    def private generateMethodUpdateCollection() {
        '''
        private int updateCollection(String tableName, Uri contentUri, Uri uri, ContentValues values, String where, String[] whereArgs) {
            int count;
            count = db.update(tableName, values, where, whereArgs);

            notifyChange(contentUri, uri);
            return count;
        }
        '''
        }
   
    def private generateMethodNotifyChange() {
        '''
        private void notifyChange(Uri contentUri, Uri uri) {
            getContext().getContentResolver().notifyChange(uri, null);
            getContext().getContentResolver().notifyChange(contentUri, null);
        }
        '''
    }
   
    def private generateMethodDelete(List<EClass> entities) {'''
        @Override
        public int delete(Uri uri, String where, String[] whereArgs) {
            String segment = uri.getPathSegments().get(1);
            long rowId = Long.parseLong(segment);
            switch(uriMatcher.match(uri)) {
            «FOR entity: entities»
                case COLLECTION_«entity.name.toUpperCase»_URI_INDICATOR: return deleteCollection(where, whereArgs, DBConstants.TABLE_NAME_«entity.name.toUpperCase», uri);
                case SINGLE_«entity.name.toUpperCase»_URI_INDICATOR: return deleteSingle(rowId, where, whereArgs, DBConstants.TABLE_NAME_«entity.name.toUpperCase», uri);
            «ENDFOR»
                default:
                    throw new IllegalArgumentException("Unsupported indicator " + uri);
            }  
      }
        '''
    }
   
    def private generateMethodDeleteSingle() {
        '''
        private int deleteSingle(long rowId, String where, String[] whereArgs, String tableName, Uri uri) {
            String deleteSql = DBConstants.ID + "=" + rowId + (!TextUtils.isEmpty(where) ? " AND (" + where + ")" : "");
            int count = db.delete(tableName, deleteSql, whereArgs);
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        '''
    }
   
    def private generateMethodDeleteCollection() {
        '''
        private int deleteCollection(String where, String[] whereArgs, String tableName, Uri uri){
            int count = db.delete(tableName, where, whereArgs);
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        '''
    }
    
    def private generateMethodCreateUUID(){
    	'''
		private void createUUID(ContentValues values) {
		    if (values.get(DBConstants.UUID) == null) {
		        // no UUID has been set -> this entry is being created on this device, not coming from the server
		        values.put(DBConstants.UUID, UUID.randomUUID().toString());
		    }
		}
    	'''
    }
        
}