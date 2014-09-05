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

import com.google.common.base.Objects;
import com.google.inject.Inject;
import de.modagile.generator.android.templates.EcoreModelUtils;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Contains helper specific generator methods and calls all helper sub de.modagile.generator.android.templates
 */
@SuppressWarnings("all")
public class DatabaseHelperTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  @Inject
  @Extension
  private EcoreModelUtils ecoreModelUtilities;
  
  public void generateDatabaseHelper(final IFileSystemAccess fsa, final List<EClass> entities, final MobileApp m, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "contentprovider/");
    String _name = m.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + "DatabaseHelper.java");
    String _packageName = packageInfo.getPackageName();
    String _packageName_1 = packageInfo.getPackageName();
    String _plus_3 = (_packageName_1 + ".contentprovider");
    String _name_1 = m.getName();
    CharSequence _generateHelperCode = this.generateHelperCode(_packageName, _plus_3, _name_1, entities);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateHelperCode);
  }
  
  private CharSequence generateHelperCode(final String basePackage, final String contentProviderPackageName, final String appName, final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated("DatabaseHelperTemplate");
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(contentProviderPackageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import android.content.Context;");
    _builder.newLine();
    _builder.append("import android.database.sqlite.SQLiteDatabase;");
    _builder.newLine();
    _builder.append("import android.database.sqlite.SQLiteOpenHelper;");
    _builder.newLine();
    _builder.append("import ");
    _builder.append(basePackage, "");
    _builder.append(".constants.DBConstants;");
    _builder.newLineIfNotEmpty();
    {
      for(final EClass entity : entities) {
        _builder.append("import ");
        _builder.append(basePackage, "");
        _builder.append(".constants.");
        String _name = entity.getName();
        _builder.append(_name, "");
        _builder.append("DBConstants;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("public class ");
    String _firstUpper = StringExtensions.toFirstUpper(appName);
    _builder.append(_firstUpper, "");
    _builder.append("DatabaseHelper extends SQLiteOpenHelper{");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("public ");
    String _firstUpper_1 = StringExtensions.toFirstUpper(appName);
    _builder.append(_firstUpper_1, "   ");
    _builder.append("DatabaseHelper(Context context){");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("super(context, DBConstants.DBNAME, null, DBConstants.DBVERSION);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("public void onCreate(SQLiteDatabase db) {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("createTables(db);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("private void createTables(SQLiteDatabase db){");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("StringBuilder builder;");
    _builder.newLine();
    {
      boolean _hasElements = false;
      for(final EClass entity_1 : entities) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("\n", "      ");
        }
        _builder.append("      ");
        _builder.append("builder = new StringBuilder();");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("builder.append(\"CREATE TABLE \" + DBConstants.TABLE_NAME_");
        String _name_1 = entity_1.getName();
        String _upperCase = _name_1.toUpperCase();
        _builder.append(_upperCase, "      ");
        _builder.append(" + \"(\");");
        _builder.newLineIfNotEmpty();
        _builder.append("      ");
        _builder.append("builder.append(DBConstants.ID + \" INTEGER PRIMARY KEY AUTOINCREMENT, \");");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("builder.append(DBConstants.UUID + \" TEXT, \");");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("builder.append(");
        String _name_2 = entity_1.getName();
        _builder.append(_name_2, "      ");
        _builder.append("DBConstants.LASTUPDATE + \" REAL\");");
        _builder.newLineIfNotEmpty();
        {
          EList<EAttribute> _eAttributes = entity_1.getEAttributes();
          int _size = _eAttributes.size();
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            _builder.append("      ");
            _builder.append("builder.append(\", \");");
            _builder.newLine();
          }
        }
        {
          EList<EAttribute> _eAttributes_1 = entity_1.getEAttributes();
          boolean _hasElements_1 = false;
          for(final EAttribute attribute : _eAttributes_1) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate("\nbuilder.append(\", \");", "      ");
            }
            _builder.append("      ");
            _builder.append("builder.append(");
            String _name_3 = entity_1.getName();
            _builder.append(_name_3, "      ");
            _builder.append("DBConstants.");
            String _name_4 = attribute.getName();
            String _upperCase_1 = _name_4.toUpperCase();
            _builder.append(_upperCase_1, "      ");
            _builder.append(" + \" ");
            String _sQLType = this.getSQLType(attribute);
            _builder.append(_sQLType, "      ");
            _builder.append("\");");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          Iterable<EReference> _outgoingToOneEReferences = this.ecoreModelUtilities.outgoingToOneEReferences(entity_1);
          int _size_1 = IterableExtensions.size(_outgoingToOneEReferences);
          boolean _greaterThan_1 = (_size_1 > 0);
          if (_greaterThan_1) {
            _builder.append("      ");
            _builder.append("builder.append(\", \");");
            _builder.newLine();
          }
        }
        {
          Iterable<EReference> _outgoingToOneEReferences_1 = this.ecoreModelUtilities.outgoingToOneEReferences(entity_1);
          boolean _hasElements_2 = false;
          for(final EReference reference : _outgoingToOneEReferences_1) {
            if (!_hasElements_2) {
              _hasElements_2 = true;
            } else {
              _builder.appendImmediate("\nbuilder.append(\", \");", "");
            }
            _builder.append("builder.append(");
            String _name_5 = entity_1.getName();
            _builder.append(_name_5, "");
            _builder.append("DBConstants.");
            String _name_6 = reference.getName();
            String _upperCase_2 = _name_6.toUpperCase();
            _builder.append(_upperCase_2, "");
            _builder.append("_UUID + \" INTEGER\");");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          List<EReference> _incomingOneToManyEReferences = this.ecoreModelUtilities.incomingOneToManyEReferences(entity_1, entities);
          int _size_2 = _incomingOneToManyEReferences.size();
          boolean _greaterThan_2 = (_size_2 > 0);
          if (_greaterThan_2) {
            _builder.append("      ");
            _builder.append("builder.append(\", \");");
            _builder.newLine();
          }
        }
        {
          List<EReference> _incomingOneToManyEReferences_1 = this.ecoreModelUtilities.incomingOneToManyEReferences(entity_1, entities);
          boolean _hasElements_3 = false;
          for(final EReference incomingReference : _incomingOneToManyEReferences_1) {
            if (!_hasElements_3) {
              _hasElements_3 = true;
            } else {
              _builder.appendImmediate("\nbuilder.append(\", \");", "      ");
            }
            _builder.append("      ");
            _builder.append("builder.append(");
            String _name_7 = entity_1.getName();
            _builder.append(_name_7, "      ");
            _builder.append("DBConstants.");
            String _incomingReferenceName = this.ecoreModelUtilities.incomingReferenceName(incomingReference);
            _builder.append(_incomingReferenceName, "      ");
            _builder.append("_UUID + \" INTEGER\");");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("      ");
        _builder.append("builder.append(\")\");");
        _builder.newLine();
        _builder.append("      ");
        _builder.append("db.execSQL(builder.toString());");
        _builder.newLine();
      }
    }
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {");
    _builder.newLine();
    {
      for(final EClass entity_2 : entities) {
        _builder.append("      ");
        _builder.append("db.execSQL(\"DROP TABLE IF EXISTS \" + DBConstants.TABLE_NAME_");
        String _name_8 = entity_2.getName();
        String _upperCase_3 = _name_8.toUpperCase();
        _builder.append(_upperCase_3, "      ");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("      ");
    _builder.append("createTables(db);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private String getSQLType(final EAttribute attribute) {
    String _switchResult = null;
    EClassifier _eType = attribute.getEType();
    String _instanceClassName = _eType.getInstanceClassName();
    final String _switchValue = _instanceClassName;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(_switchValue,"java.lang.String")) {
        _matched=true;
        _switchResult = "TEXT";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"java.util.Date")) {
        _matched=true;
        _switchResult = "REAL";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"java.lang.Integer")) {
        _matched=true;
        _switchResult = "INTEGER";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"java.lang.Double")) {
        _matched=true;
        _switchResult = "REAL";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"java.lang.Long")) {
        _matched=true;
        _switchResult = "INTEGER";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"java.lang.Boolean")) {
        _matched=true;
        _switchResult = "INTEGER";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"boolean")) {
        _matched=true;
        _switchResult = "INTEGER";
      }
    }
    return _switchResult;
  }
}
