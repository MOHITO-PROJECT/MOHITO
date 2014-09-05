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
package de.modagile.generator.android.templates.contentprovider;

import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ContentProviderTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtils;
  
  public void generateContentProvider(final IFileSystemAccess fsa, final List<EClass> entities, final MobileApp m, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "contentprovider/");
    String _name = m.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + "ContentProvider.java");
    String _packageName = packageInfo.getPackageName();
    String _packageName_1 = packageInfo.getPackageName();
    String _plus_3 = (_packageName_1 + ".contentprovider");
    String _name_1 = m.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    CharSequence _generateSource = this.generateSource(_packageName, _plus_3, _firstUpper_1, entities);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateSource);
  }
  
  private CharSequence generateSource(final String basePackgeName, final String contentPackageName, final String appName, final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("  ");
    CharSequence _statementGenerated = this.javaUtils.statementGenerated("ContentProviderTemplate");
    _builder.append(_statementGenerated, "  ");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    CharSequence _packageStatement = this.javaUtils.packageStatement(contentPackageName);
    _builder.append(_packageStatement, "  ");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import java.util.HashMap;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import java.util.UUID;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.database.Cursor;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.content.ContentProvider;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.content.ContentValues;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.content.UriMatcher;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.database.sqlite.SQLiteDatabase;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.database.sqlite.SQLiteQueryBuilder;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.content.ContentUris;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.database.SQLException;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.net.Uri;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import android.text.TextUtils;");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("import ");
    _builder.append(basePackgeName, "  ");
    _builder.append(".constants.DBConstants;");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("import static ");
    _builder.append(contentPackageName, "  ");
    _builder.append(".");
    _builder.append(appName, "  ");
    _builder.append("Indicator.*;");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("public class ");
    String _firstUpper = StringExtensions.toFirstUpper(appName);
    _builder.append(_firstUpper, "  ");
    _builder.append("ContentProvider extends ContentProvider{");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("private SQLiteDatabase db;");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("private ");
    _builder.append(appName, "      ");
    _builder.append("DatabaseHelper dbHelper;");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("private static final UriMatcher uriMatcher = buildUriMatcher();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodBuildUriMatcher = this.generateMethodBuildUriMatcher(appName, entities);
    _builder.append(_generateMethodBuildUriMatcher, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodOnCreate = this.generateMethodOnCreate(appName);
    _builder.append(_generateMethodOnCreate, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodDelete = this.generateMethodDelete(entities);
    _builder.append(_generateMethodDelete, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodDeleteSingle = this.generateMethodDeleteSingle();
    _builder.append(_generateMethodDeleteSingle, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodDeleteCollection = this.generateMethodDeleteCollection();
    _builder.append(_generateMethodDeleteCollection, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodGetType = this.generateMethodGetType(appName, entities);
    _builder.append(_generateMethodGetType, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodInsert = this.generateMethodInsert(entities);
    _builder.append(_generateMethodInsert, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodExecuteInsert = this.generateMethodExecuteInsert();
    _builder.append(_generateMethodExecuteInsert, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodCheckInsert = this.generateMethodCheckInsert();
    _builder.append(_generateMethodCheckInsert, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodCreateUUID = this.generateMethodCreateUUID();
    _builder.append(_generateMethodCreateUUID, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodQuery = this.generateMethodQuery(entities);
    _builder.append(_generateMethodQuery, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodUpdate = this.generateMethodUpdate(entities);
    _builder.append(_generateMethodUpdate, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodUpdateSingle = this.generateMethodUpdateSingle();
    _builder.append(_generateMethodUpdateSingle, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodUpdateCollection = this.generateMethodUpdateCollection();
    _builder.append(_generateMethodUpdateCollection, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("      ");
    CharSequence _generateMethodNotifyChange = this.generateMethodNotifyChange();
    _builder.append(_generateMethodNotifyChange, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("}  ");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodBuildUriMatcher(final String appName, final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private static UriMatcher buildUriMatcher(){");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);");
    _builder.newLine();
    {
      for(final EClass entity : entities) {
        _builder.append("   ");
        _builder.append("uriMatcher.addURI(DBConstants.AUTHORITY, \"");
        String _name = entity.getName();
        String _lowerCase = _name.toLowerCase();
        _builder.append(_lowerCase, "   ");
        _builder.append("\", ");
        _builder.append(appName, "   ");
        _builder.append("Indicator.COLLECTION_");
        String _name_1 = entity.getName();
        String _upperCase = _name_1.toUpperCase();
        _builder.append(_upperCase, "   ");
        _builder.append("_URI_INDICATOR);");
        _builder.newLineIfNotEmpty();
        _builder.append("   ");
        _builder.append("uriMatcher.addURI(DBConstants.AUTHORITY, \"");
        String _name_2 = entity.getName();
        String _lowerCase_1 = _name_2.toLowerCase();
        _builder.append(_lowerCase_1, "   ");
        _builder.append("/#\", ");
        _builder.append(appName, "   ");
        _builder.append("Indicator.SINGLE_");
        String _name_3 = entity.getName();
        String _upperCase_1 = _name_3.toUpperCase();
        _builder.append(_upperCase_1, "   ");
        _builder.append("_URI_INDICATOR);");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("   ");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("return uriMatcher;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodOnCreate(final String appName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public boolean onCreate(){");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("dbHelper = new ");
    _builder.append(appName, "    ");
    _builder.append("DatabaseHelper(getContext());");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("db = dbHelper.getWritableDatabase();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodGetType(final String appName, final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public String getType(Uri uri) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("switch (uriMatcher.match(uri)) {");
    _builder.newLine();
    {
      for(final EClass entity : entities) {
        _builder.append("    ");
        _builder.append("case ");
        _builder.append(appName, "    ");
        _builder.append("Indicator.COLLECTION_");
        String _name = entity.getName();
        String _upperCase = _name.toUpperCase();
        _builder.append(_upperCase, "    ");
        _builder.append("_URI_INDICATOR:");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return DBConstants.CONTENT_TYPE_");
        String _name_1 = entity.getName();
        String _upperCase_1 = _name_1.toUpperCase();
        _builder.append(_upperCase_1, "    ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("case ");
        _builder.append(appName, "    ");
        _builder.append("Indicator.SINGLE_");
        String _name_2 = entity.getName();
        String _upperCase_2 = _name_2.toUpperCase();
        _builder.append(_upperCase_2, "    ");
        _builder.append("_URI_INDICATOR:");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return DBConstants.CONTENT_ITEM_TYPE_");
        String _name_3 = entity.getName();
        String _upperCase_3 = _name_3.toUpperCase();
        _builder.append(_upperCase_3, "    ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.append("default: throw new IllegalArgumentException(\"Unknown URI \" + uri);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodInsert(final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Uri insert(Uri uri, ContentValues values) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("switch(uriMatcher.match(uri)) {");
    _builder.newLine();
    {
      for(final EClass entity : entities) {
        _builder.append("    ");
        _builder.append("case COLLECTION_");
        String _name = entity.getName();
        String _upperCase = _name.toUpperCase();
        _builder.append(_upperCase, "    ");
        _builder.append("_URI_INDICATOR: return executeInsert(DBConstants.TABLE_NAME_");
        String _name_1 = entity.getName();
        String _upperCase_1 = _name_1.toUpperCase();
        _builder.append(_upperCase_1, "    ");
        _builder.append(", values, DBConstants.CONTENT_URI_");
        String _name_2 = entity.getName();
        String _upperCase_2 = _name_2.toUpperCase();
        _builder.append(_upperCase_2, "    ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.append("default:");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("throw new IllegalArgumentException(\"Unsupported indicator \" + uri);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodExecuteInsert() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private Uri executeInsert(String tableName, ContentValues values, Uri uri){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("createUUID(values);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("long rowId = db.insert(tableName, null, values);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return checkInsert(rowId, uri);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateMethodCheckInsert() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" ");
    _builder.append("private Uri checkInsert(long rowId, Uri contentUri) {");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("Uri insertedUri = ContentUris.withAppendedId(contentUri, rowId);");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("if (rowId > 0) {");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("getContext().getContentResolver().notifyChange(insertedUri, null);");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("getContext().getContentResolver().notifyChange(contentUri, null);");
    _builder.newLine();
    _builder.append("         ");
    _builder.append("return insertedUri;");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("throw new SQLException(\"Failed to insert row into: \" + insertedUri);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodQuery(final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sort) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("SQLiteQueryBuilder qb = new SQLiteQueryBuilder();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Cursor cursor = null;");
    _builder.newLine();
    _builder.append(" ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("switch(uriMatcher.match(uri)) {");
    _builder.newLine();
    {
      for(final EClass entity : entities) {
        _builder.append("    ");
        _builder.append("case COLLECTION_");
        String _name = entity.getName();
        String _upperCase = _name.toUpperCase();
        _builder.append(_upperCase, "    ");
        _builder.append("_URI_INDICATOR: ");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("   ");
        CharSequence _queryCase = this.queryCase(entity, false);
        _builder.append(_queryCase, "       ");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("case SINGLE_");
        String _name_1 = entity.getName();
        String _upperCase_1 = _name_1.toUpperCase();
        _builder.append(_upperCase_1, "    ");
        _builder.append("_URI_INDICATOR: ");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("   ");
        CharSequence _queryCase_1 = this.queryCase(entity, true);
        _builder.append(_queryCase_1, "       ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.append("default:");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("throw new IllegalArgumentException(\"Unsupported indicator \" + uri);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    {
      int _size = entities.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("    ");
        _builder.append("cursor.setNotificationUri(getContext().getContentResolver(), uri);");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("return cursor;");
        _builder.newLine();
      }
    }
    _builder.append("    ");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence queryCase(final EClass entity, final boolean single) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("qb.setTables(DBConstants.TABLE_NAME_");
    String _name = entity.getName();
    String _upperCase = _name.toUpperCase();
    _builder.append(_upperCase, "");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("qb.setProjectionMap(");
    String _name_1 = entity.getName();
    String _lowerCase = _name_1.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("ProjectionMap);");
    _builder.newLineIfNotEmpty();
    {
      if (single) {
        _builder.append("qb.appendWhere(DBConstants.ID + \"=\" + uri.getPathSegments().get(1));");
        _builder.newLine();
      }
    }
    _builder.append("cursor = qb.query(db, projection, selection, selectionArgs, null, null, sort);");
    _builder.newLine();
    _builder.append("break;");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodUpdate(final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("switch(uriMatcher.match(uri)) {");
    _builder.newLine();
    {
      for(final EClass entity : entities) {
        _builder.append("    ");
        _builder.append("case COLLECTION_");
        String _name = entity.getName();
        String _upperCase = _name.toUpperCase();
        _builder.append(_upperCase, "    ");
        _builder.append("_URI_INDICATOR: return updateCollection(DBConstants.TABLE_NAME_");
        String _name_1 = entity.getName();
        String _upperCase_1 = _name_1.toUpperCase();
        _builder.append(_upperCase_1, "    ");
        _builder.append(", DBConstants.CONTENT_URI_");
        String _name_2 = entity.getName();
        String _upperCase_2 = _name_2.toUpperCase();
        _builder.append(_upperCase_2, "    ");
        _builder.append(", uri, values, where, whereArgs);");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("case SINGLE_");
        String _name_3 = entity.getName();
        String _upperCase_3 = _name_3.toUpperCase();
        _builder.append(_upperCase_3, "    ");
        _builder.append("_URI_INDICATOR: return updateSingle(DBConstants.TABLE_NAME_");
        String _name_4 = entity.getName();
        String _upperCase_4 = _name_4.toUpperCase();
        _builder.append(_upperCase_4, "    ");
        _builder.append(", DBConstants.CONTENT_URI_");
        String _name_5 = entity.getName();
        String _upperCase_5 = _name_5.toUpperCase();
        _builder.append(_upperCase_5, "    ");
        _builder.append(", uri, values, where, whereArgs);");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("        ");
    _builder.append("default:");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("throw new IllegalArgumentException(\"Unsupported indicator \" + uri);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodUpdateSingle() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private int updateSingle(String tableName, Uri contentUri, Uri uri, ContentValues values, String where, String[] whereArgs) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("int count;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("String rowId;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("String sqlUpdate;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("rowId = uri.getPathSegments().get(1);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("sqlUpdate = DBConstants.ID + \"=\" + rowId + (!TextUtils.isEmpty(where) ? \" AND (\" + where + \")\" : \"\");");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("count = db.update(tableName, values, sqlUpdate, whereArgs);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("notifyChange(contentUri, uri);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return count;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodUpdateCollection() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private int updateCollection(String tableName, Uri contentUri, Uri uri, ContentValues values, String where, String[] whereArgs) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("int count;");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("count = db.update(tableName, values, where, whereArgs);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("notifyChange(contentUri, uri);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return count;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodNotifyChange() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private void notifyChange(Uri contentUri, Uri uri) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("getContext().getContentResolver().notifyChange(uri, null);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("getContext().getContentResolver().notifyChange(contentUri, null);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodDelete(final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("  ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("public int delete(Uri uri, String where, String[] whereArgs) {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("String segment = uri.getPathSegments().get(1);");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("long rowId = Long.parseLong(segment);");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("switch(uriMatcher.match(uri)) {");
    _builder.newLine();
    {
      for(final EClass entity : entities) {
        _builder.append("      ");
        _builder.append("case COLLECTION_");
        String _name = entity.getName();
        String _upperCase = _name.toUpperCase();
        _builder.append(_upperCase, "      ");
        _builder.append("_URI_INDICATOR: return deleteCollection(where, whereArgs, DBConstants.TABLE_NAME_");
        String _name_1 = entity.getName();
        String _upperCase_1 = _name_1.toUpperCase();
        _builder.append(_upperCase_1, "      ");
        _builder.append(", uri);");
        _builder.newLineIfNotEmpty();
        _builder.append("      ");
        _builder.append("case SINGLE_");
        String _name_2 = entity.getName();
        String _upperCase_2 = _name_2.toUpperCase();
        _builder.append(_upperCase_2, "      ");
        _builder.append("_URI_INDICATOR: return deleteSingle(rowId, where, whereArgs, DBConstants.TABLE_NAME_");
        String _name_3 = entity.getName();
        String _upperCase_3 = _name_3.toUpperCase();
        _builder.append(_upperCase_3, "      ");
        _builder.append(", uri);");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("          ");
    _builder.append("default:");
    _builder.newLine();
    _builder.append("              ");
    _builder.append("throw new IllegalArgumentException(\"Unsupported indicator \" + uri);");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("}  ");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodDeleteSingle() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private int deleteSingle(long rowId, String where, String[] whereArgs, String tableName, Uri uri) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("String deleteSql = DBConstants.ID + \"=\" + rowId + (!TextUtils.isEmpty(where) ? \" AND (\" + where + \")\" : \"\");");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("int count = db.delete(tableName, deleteSql, whereArgs);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("getContext().getContentResolver().notifyChange(uri, null);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return count;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodDeleteCollection() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private int deleteCollection(String where, String[] whereArgs, String tableName, Uri uri){");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("int count = db.delete(tableName, where, whereArgs);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("getContext().getContentResolver().notifyChange(uri, null);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return count;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodCreateUUID() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private void createUUID(ContentValues values) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (values.get(DBConstants.UUID) == null) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// no UUID has been set -> this entry is being created on this device, not coming from the server");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("values.put(DBConstants.UUID, UUID.randomUUID().toString());");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
