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
package de.modagile.generator.android.templates.converter;

import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ModelConverterTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtils;
  
  public void generateModelConverter(final IFileSystemAccess fsa, final EClass entity, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "model/");
    String _name = entity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "Converter.java");
    String _packageName = packageInfo.getPackageName();
    String _packageName_1 = packageInfo.getPackageName();
    String _plus_3 = (_packageName_1 + ".model");
    CharSequence _generateSource = this.generateSource(_packageName, _plus_3, entity);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateSource);
  }
  
  private CharSequence generateSource(final String basePackageName, final String modelPackageName, final EClass entity) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      this.getModelConverterImports(imports, basePackageName, entity);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(" ");
      CharSequence _statementGenerated = this.javaUtils.statementGenerated("ModelFactoryTemplate");
      _builder.append(_statementGenerated, " ");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      CharSequence _packageStatement = this.javaUtils.packageStatement(modelPackageName);
      _builder.append(_packageStatement, " ");
      _builder.newLineIfNotEmpty();
      CharSequence _importStatements = this.javaUtils.importStatements(imports);
      _builder.append(_importStatements, "");
      _builder.newLineIfNotEmpty();
      String _name = entity.getName();
      String _plus = (_name + "Converter");
      CharSequence _classDecl = this.javaUtils.classDecl(_plus, null, null, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.newLine();
      _builder.append("     ");
      CharSequence _generateParseEntityList = this.generateParseEntityList(entity);
      _builder.append(_generateParseEntityList, "     ");
      _builder.append("     ");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.newLine();
      _builder.append("     ");
      CharSequence _generateParseEntity = this.generateParseEntity(entity);
      _builder.append(_generateParseEntity, "     ");
      _builder.append("    ");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.newLine();
      _builder.append("     ");
      CharSequence _generateGetJSONEntity = this.generateGetJSONEntity(entity);
      _builder.append(_generateGetJSONEntity, "     ");
      _builder.append("  ");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.newLine();
      _builder.append("     ");
      CharSequence _generateCreateProjectionMap = this.generateCreateProjectionMap(entity);
      _builder.append(_generateCreateProjectionMap, "     ");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.newLine();
      _builder.append("     ");
      CharSequence _generateCursorToEntity = this.generateCursorToEntity(entity);
      _builder.append(_generateCursorToEntity, "     ");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.newLine();
      _builder.append("     ");
      CharSequence _generateEntityToValues = this.generateEntityToValues(entity);
      _builder.append(_generateEntityToValues, "     ");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("}   ");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public boolean getModelConverterImports(final Set<String> imports, final String basePackageName, final EClass entity) {
    boolean _xblockexpression = false;
    {
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
      String _plus = (basePackageName + ".constants.DBConstants");
      imports.add(_plus);
      String _plus_1 = (basePackageName + ".constants.");
      String _name = entity.getName();
      String _plus_2 = (_plus_1 + _name);
      String _plus_3 = (_plus_2 + "DBConstants");
      boolean _add = imports.add(_plus_3);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateParseEntityList(final EClass entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static List<");
    String _name = entity.getName();
    _builder.append(_name, "");
    _builder.append("> parse");
    String _name_1 = entity.getName();
    _builder.append(_name_1, "");
    _builder.append("List(JSONArray jsonArray) throws JSONException {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("List<");
    String _name_2 = entity.getName();
    _builder.append(_name_2, "   ");
    _builder.append("> list = new ArrayList<");
    String _name_3 = entity.getName();
    _builder.append(_name_3, "   ");
    _builder.append(">();");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("for (int i = 0; i < jsonArray.length(); i++) {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("JSONObject json = jsonArray.getJSONObject(i);");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("Log.i(");
    String _name_4 = entity.getName();
    _builder.append(_name_4, "      ");
    _builder.append(".TAG, \"");
    String _name_5 = entity.getName();
    String _lowerCase = _name_5.toLowerCase();
    _builder.append(_lowerCase, "      ");
    _builder.append("\" + i + \": \" + json.toString());");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    String _name_6 = entity.getName();
    _builder.append(_name_6, "      ");
    _builder.append(" entity = parse");
    String _name_7 = entity.getName();
    _builder.append(_name_7, "      ");
    _builder.append("(json);");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("list.add(entity);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("return list;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateParseEntity(final EClass entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static ");
    String _name = entity.getName();
    _builder.append(_name, "");
    _builder.append(" parse");
    String _name_1 = entity.getName();
    _builder.append(_name_1, "");
    _builder.append("(JSONObject json) throws JSONException {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    String _name_2 = entity.getName();
    _builder.append(_name_2, "   ");
    _builder.append(" entity = new ");
    String _name_3 = entity.getName();
    _builder.append(_name_3, "   ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("entity.setId(null);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("entity.setLastUpdate(json.getLong(");
    String _name_4 = entity.getName();
    _builder.append(_name_4, "   ");
    _builder.append("DBConstants.LASTUPDATE));");
    _builder.newLineIfNotEmpty();
    {
      EList<EAttribute> _eAttributes = entity.getEAttributes();
      for(final EAttribute attribute : _eAttributes) {
        _builder.append("   ");
        final String type = this.getTypeName(attribute);
        _builder.newLineIfNotEmpty();
        {
          boolean _equals = type.equals("Date");
          if (_equals) {
            _builder.append("   ");
            _builder.append("entity.set");
            String _name_5 = attribute.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_5);
            _builder.append(_firstUpper, "   ");
            _builder.append("(new Date(json.getLong(");
            String _name_6 = entity.getName();
            _builder.append(_name_6, "   ");
            _builder.append("DBConstants.");
            String _name_7 = attribute.getName();
            String _upperCase = _name_7.toUpperCase();
            _builder.append(_upperCase, "   ");
            _builder.append(")));");
            _builder.newLineIfNotEmpty();
          } else {
            boolean _equals_1 = type.equals("byte[]");
            if (_equals_1) {
              _builder.append("   ");
              _builder.append("entity.set");
              String _name_8 = attribute.getName();
              String _firstUpper_1 = StringExtensions.toFirstUpper(_name_8);
              _builder.append(_firstUpper_1, "   ");
              _builder.append("(Base64.decode(json.getString(");
              String _name_9 = entity.getName();
              _builder.append(_name_9, "   ");
              _builder.append("DBConstants.");
              String _name_10 = attribute.getName();
              String _upperCase_1 = _name_10.toUpperCase();
              _builder.append(_upperCase_1, "   ");
              _builder.append("), Base64.DEFAULT));");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("   ");
              _builder.append("entity.set");
              String _name_11 = attribute.getName();
              String _firstUpper_2 = StringExtensions.toFirstUpper(_name_11);
              _builder.append(_firstUpper_2, "   ");
              _builder.append("(json.get");
              String _firstUpper_3 = StringExtensions.toFirstUpper(type);
              _builder.append(_firstUpper_3, "   ");
              _builder.append("(");
              String _name_12 = entity.getName();
              _builder.append(_name_12, "   ");
              _builder.append("DBConstants.");
              String _name_13 = attribute.getName();
              String _upperCase_2 = _name_13.toUpperCase();
              _builder.append(_upperCase_2, "   ");
              _builder.append("));");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
    }
    _builder.append("   ");
    _builder.append("return entity;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Returns the name of the java class for the attribute type (String, Int, etc.)
   * Equal to the simplename
   */
  private String getTypeName(final EAttribute attribute) {
    EClassifier _eType = attribute.getEType();
    Class<? extends Object> _instanceClass = _eType.getInstanceClass();
    return _instanceClass.getSimpleName();
  }
  
  private CharSequence generateGetJSONEntity(final EClass entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static String getJson(");
    String _name = entity.getName();
    _builder.append(_name, "");
    _builder.append(" entity) {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("JSONObject jsonObject = new JSONObject();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("jsonObject.put(DBConstants.ID, entity.getId());");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("jsonObject.put(");
    String _name_1 = entity.getName();
    _builder.append(_name_1, "      ");
    _builder.append("DBConstants.LASTUPDATE, entity.getLastUpdate());");
    _builder.newLineIfNotEmpty();
    {
      EList<EAttribute> _eAttributes = entity.getEAttributes();
      for(final EAttribute attribute : _eAttributes) {
        _builder.append("      ");
        _builder.append("jsonObject.put(");
        String _name_2 = entity.getName();
        _builder.append(_name_2, "      ");
        _builder.append("DBConstants.");
        String _name_3 = attribute.getName();
        String _upperCase = _name_3.toUpperCase();
        _builder.append(_upperCase, "      ");
        _builder.append(", entity.get");
        String _name_4 = attribute.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_4);
        _builder.append(_firstUpper, "      ");
        _builder.append("());");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("      ");
    _builder.append("Log.d(");
    String _name_5 = entity.getName();
    _builder.append(_name_5, "      ");
    _builder.append(".TAG, \"jsonified ");
    String _name_6 = entity.getName();
    _builder.append(_name_6, "      ");
    _builder.append("\" + jsonObject.toString());");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("return jsonObject.toString();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("} catch (JSONException e) {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateCreateProjectionMap(final EClass entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static HashMap<String, String> createProjectionHashmap() {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("HashMap<String, String> projectionMap = new HashMap<String, String>();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("projectionMap.put(DBConstants.ID, DBConstants.ID);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("projectionMap.put(");
    String _name = entity.getName();
    _builder.append(_name, "   ");
    _builder.append("DBConstants.LASTUPDATE, ");
    String _name_1 = entity.getName();
    _builder.append(_name_1, "   ");
    _builder.append("DBConstants.LASTUPDATE);");
    _builder.newLineIfNotEmpty();
    {
      EList<EAttribute> _eAttributes = entity.getEAttributes();
      for(final EAttribute attribute : _eAttributes) {
        _builder.append("   ");
        _builder.append("projectionMap.put(");
        String _name_2 = entity.getName();
        _builder.append(_name_2, "   ");
        _builder.append("DBConstants.");
        String _name_3 = attribute.getName();
        String _upperCase = _name_3.toUpperCase();
        _builder.append(_upperCase, "   ");
        _builder.append(", ");
        String _name_4 = entity.getName();
        _builder.append(_name_4, "   ");
        _builder.append("DBConstants.");
        String _name_5 = attribute.getName();
        String _upperCase_1 = _name_5.toUpperCase();
        _builder.append(_upperCase_1, "   ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("  ");
    _builder.append("return projectionMap;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateCursorToEntity(final EClass entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static ");
    String _name = entity.getName();
    _builder.append(_name, "");
    _builder.append(" cursorTo");
    String _name_1 = entity.getName();
    _builder.append(_name_1, "");
    _builder.append("(Cursor cursor) {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    String _name_2 = entity.getName();
    _builder.append(_name_2, "   ");
    _builder.append(" entity = new ");
    String _name_3 = entity.getName();
    _builder.append(_name_3, "   ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("entity.setId(cursor.getLong(cursor.getColumnIndex(DBConstants.ID)));");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("entity.setLastUpdate(cursor.getLong(cursor.getColumnIndex(");
    String _name_4 = entity.getName();
    _builder.append(_name_4, "   ");
    _builder.append("DBConstants.LASTUPDATE)));");
    _builder.newLineIfNotEmpty();
    {
      EList<EAttribute> _eAttributes = entity.getEAttributes();
      for(final EAttribute attribute : _eAttributes) {
        _builder.append("   ");
        final String type = this.getTypeName(attribute);
        _builder.newLineIfNotEmpty();
        {
          boolean _equals = type.equals("Date");
          if (_equals) {
            _builder.append("   ");
            _builder.append("entity.set");
            String _name_5 = attribute.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_5);
            _builder.append(_firstUpper, "   ");
            _builder.append("(new Date(cursor.getLong(cursor.getColumnIndex(");
            String _name_6 = entity.getName();
            _builder.append(_name_6, "   ");
            _builder.append("DBConstants.");
            String _name_7 = attribute.getName();
            String _upperCase = _name_7.toUpperCase();
            _builder.append(_upperCase, "   ");
            _builder.append("))));");
            _builder.newLineIfNotEmpty();
          } else {
            boolean _equals_1 = type.equals("byte[]");
            if (_equals_1) {
              _builder.append("   ");
              _builder.append("entity.set");
              String _name_8 = attribute.getName();
              String _firstUpper_1 = StringExtensions.toFirstUpper(_name_8);
              _builder.append(_firstUpper_1, "   ");
              _builder.append("(cursor.getBlob(cursor.getColumnIndex(");
              String _name_9 = entity.getName();
              _builder.append(_name_9, "   ");
              _builder.append("DBConstants.");
              String _name_10 = attribute.getName();
              String _upperCase_1 = _name_10.toUpperCase();
              _builder.append(_upperCase_1, "   ");
              _builder.append(")));");
              _builder.newLineIfNotEmpty();
            } else {
              boolean _equals_2 = type.equals("boolean");
              if (_equals_2) {
                _builder.append("   ");
                _builder.append("entity.set");
                String _name_11 = attribute.getName();
                String _firstUpper_2 = StringExtensions.toFirstUpper(_name_11);
                _builder.append(_firstUpper_2, "   ");
                _builder.append("(Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(");
                String _name_12 = entity.getName();
                _builder.append(_name_12, "   ");
                _builder.append("DBConstants.");
                String _name_13 = attribute.getName();
                String _upperCase_2 = _name_13.toUpperCase();
                _builder.append(_upperCase_2, "   ");
                _builder.append("))));");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("   ");
                _builder.append("entity.set");
                String _name_14 = attribute.getName();
                String _firstUpper_3 = StringExtensions.toFirstUpper(_name_14);
                _builder.append(_firstUpper_3, "   ");
                _builder.append("(cursor.get");
                String _firstUpper_4 = StringExtensions.toFirstUpper(type);
                _builder.append(_firstUpper_4, "   ");
                _builder.append("(cursor.getColumnIndex(");
                String _name_15 = entity.getName();
                _builder.append(_name_15, "   ");
                _builder.append("DBConstants.");
                String _name_16 = attribute.getName();
                String _upperCase_3 = _name_16.toUpperCase();
                _builder.append(_upperCase_3, "   ");
                _builder.append(")));");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("   ");
    _builder.append("return entity;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateEntityToValues(final EClass entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static ContentValues ");
    String _name = entity.getName();
    String _firstLower = StringExtensions.toFirstLower(_name);
    _builder.append(_firstLower, "");
    _builder.append("ToValues(");
    String _name_1 = entity.getName();
    _builder.append(_name_1, "");
    _builder.append(" entity) {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("ContentValues values = new ContentValues();");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("values.put(DBConstants.ID, entity.getId());");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("values.put(");
    String _name_2 = entity.getName();
    _builder.append(_name_2, "   ");
    _builder.append("DBConstants.LASTUPDATE, entity.getLastUpdate());");
    _builder.newLineIfNotEmpty();
    {
      EList<EAttribute> _eAttributes = entity.getEAttributes();
      for(final EAttribute attribute : _eAttributes) {
        {
          String _typeName = this.getTypeName(attribute);
          boolean _equals = _typeName.equals("Date");
          if (_equals) {
            _builder.append("   ");
            _builder.append("values.put(");
            String _name_3 = entity.getName();
            _builder.append(_name_3, "   ");
            _builder.append("DBConstants.");
            String _name_4 = attribute.getName();
            String _upperCase = _name_4.toUpperCase();
            _builder.append(_upperCase, "   ");
            _builder.append(", entity.get");
            String _name_5 = attribute.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_5);
            _builder.append(_firstUpper, "   ");
            _builder.append("().getTime());");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("   ");
            _builder.append("values.put(");
            String _name_6 = entity.getName();
            _builder.append(_name_6, "   ");
            _builder.append("DBConstants.");
            String _name_7 = attribute.getName();
            String _upperCase_1 = _name_7.toUpperCase();
            _builder.append(_upperCase_1, "   ");
            _builder.append(", entity.get");
            String _name_8 = attribute.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_8);
            _builder.append(_firstUpper_1, "   ");
            _builder.append("());");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("   ");
    _builder.append("return values;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
