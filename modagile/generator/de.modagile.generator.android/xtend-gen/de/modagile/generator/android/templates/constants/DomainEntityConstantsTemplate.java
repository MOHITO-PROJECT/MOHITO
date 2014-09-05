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
package de.modagile.generator.android.templates.constants;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.EcoreModelUtils;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class DomainEntityConstantsTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  @Inject
  @Extension
  private EcoreModelUtils ecoreModelUtilities;
  
  public void generateDBConstans(final IFileSystemAccess fsa, final List<EClass> entities, final String appName, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "constants/DBConstants.java");
    String _packageName = packageInfo.getPackageName();
    String _packageName_1 = packageInfo.getPackageName();
    String _plus_1 = (_packageName_1 + ".constants");
    CharSequence _generateDBConstantsJava = this.generateDBConstantsJava(_packageName, _plus_1, appName, entities);
    fsa.generateFile(_plus, ModagileFolderConstants.SRC_GEN, _generateDBConstantsJava);
  }
  
  private CharSequence generateDBConstantsJava(final String basePackage, final String constantsPackageName, final String appName, final List<EClass> entities) {
    CharSequence _xblockexpression = null;
    {
      final String name = appName.toLowerCase();
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _statementGenerated = this.javaUtilities.statementGenerated("DBConstants");
      _builder.append(_statementGenerated, "");
      _builder.newLineIfNotEmpty();
      CharSequence _packageStatement = this.javaUtilities.packageStatement(constantsPackageName);
      _builder.append(_packageStatement, "");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("import android.net.Uri;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public final class DBConstants{");
      _builder.newLine();
      _builder.append("   ");
      _builder.append("public static final String DBNAME = \"");
      _builder.append(name, "   ");
      _builder.append(".db\";");
      _builder.newLineIfNotEmpty();
      _builder.append("   ");
      _builder.append("public static final int DBVERSION = 1;");
      _builder.newLine();
      _builder.append("   ");
      _builder.append("public static final String AUTHORITY = \"");
      _builder.append(basePackage, "   ");
      _builder.append(".");
      _builder.append(appName, "   ");
      _builder.append("Provider\";");
      _builder.newLineIfNotEmpty();
      _builder.append("   ");
      _builder.append("public static final Uri PROVIDER_URI = Uri.parse(\"content://\" + AUTHORITY);");
      _builder.newLine();
      _builder.append("   ");
      _builder.append("public static final String ID = \"_id\";");
      _builder.newLine();
      _builder.append("   ");
      _builder.append("public static final String UUID = \"uuid\";");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      {
        boolean _hasElements = false;
        for(final EClass entity : entities) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate("\n", "   ");
          }
          _builder.append("   ");
          _builder.append("public static final String TABLE_NAME_");
          String _name = entity.getName();
          String _upperCase = _name.toUpperCase();
          _builder.append(_upperCase, "   ");
          _builder.append(" = \"");
          String _name_1 = entity.getName();
          String _lowerCase = _name_1.toLowerCase();
          _builder.append(_lowerCase, "   ");
          _builder.append("\";");
          _builder.newLineIfNotEmpty();
          _builder.append("   ");
          _builder.append("public static final String CONTENT_TYPE_");
          String _name_2 = entity.getName();
          String _upperCase_1 = _name_2.toUpperCase();
          _builder.append(_upperCase_1, "   ");
          _builder.append(" = \"vnd.android.cursor.dir/vnd.");
          _builder.append(name, "   ");
          _builder.append(".");
          String _name_3 = entity.getName();
          String _lowerCase_1 = _name_3.toLowerCase();
          _builder.append(_lowerCase_1, "   ");
          _builder.append("\";");
          _builder.newLineIfNotEmpty();
          _builder.append("   ");
          _builder.append("public static final String CONTENT_ITEM_TYPE_");
          String _name_4 = entity.getName();
          String _upperCase_2 = _name_4.toUpperCase();
          _builder.append(_upperCase_2, "   ");
          _builder.append(" = \"vnd.android.cursor.item/vnd.");
          _builder.append(name, "   ");
          _builder.append(".");
          String _name_5 = entity.getName();
          String _lowerCase_2 = _name_5.toLowerCase();
          _builder.append(_lowerCase_2, "   ");
          _builder.append("\";");
          _builder.newLineIfNotEmpty();
          _builder.append("   ");
          _builder.append("public static final Uri CONTENT_URI_");
          String _name_6 = entity.getName();
          String _upperCase_3 = _name_6.toUpperCase();
          _builder.append(_upperCase_3, "   ");
          _builder.append(" = Uri.withAppendedPath(PROVIDER_URI, \"");
          String _name_7 = entity.getName();
          String _lowerCase_3 = _name_7.toLowerCase();
          _builder.append(_lowerCase_3, "   ");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public void generateDomainEntityConstants(final IFileSystemAccess fsa, final EClass domainEntity, final List<EClass> allDomainEntities, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "constants/");
    String _name = domainEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + "DBConstants.java");
    String _packageName = packageInfo.getPackageName();
    String _plus_3 = (_packageName + ".constants");
    CharSequence _generateDomainEntityConstantsJava = this.generateDomainEntityConstantsJava(_plus_3, domainEntity, allDomainEntities);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateDomainEntityConstantsJava);
  }
  
  private CharSequence generateDomainEntityConstantsJava(final String packageName, final EClass entity, final List<EClass> allDomainEntities) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated("EntityDBConstants");
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public final class ");
    String _name = entity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("DBConstants{");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("public static final String LASTUPDATE = \"lastupdate\";");
    _builder.newLine();
    {
      EList<EAttribute> _eAttributes = entity.getEAttributes();
      for(final EAttribute attribute : _eAttributes) {
        _builder.append("   ");
        _builder.append("public static final String ");
        String _name_1 = attribute.getName();
        String _upperCase = _name_1.toUpperCase();
        _builder.append(_upperCase, "   ");
        _builder.append(" = \"");
        String _name_2 = attribute.getName();
        String _lowerCase = _name_2.toLowerCase();
        _builder.append(_lowerCase, "   ");
        _builder.append("\";");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      Iterable<EReference> _outgoingToOneEReferences = this.ecoreModelUtilities.outgoingToOneEReferences(entity);
      for(final EReference reference : _outgoingToOneEReferences) {
        _builder.append("   ");
        _builder.append("public static final String ");
        String _name_3 = reference.getName();
        String _upperCase_1 = _name_3.toUpperCase();
        _builder.append(_upperCase_1, "   ");
        _builder.append("_UUID = \"");
        String _name_4 = reference.getName();
        String _lowerCase_1 = _name_4.toLowerCase();
        _builder.append(_lowerCase_1, "   ");
        _builder.append("_id\";");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      List<EReference> _incomingOneToManyEReferences = this.ecoreModelUtilities.incomingOneToManyEReferences(entity, allDomainEntities);
      for(final EReference incomingReference : _incomingOneToManyEReferences) {
        _builder.append("public static final String ");
        String _incomingReferenceName = this.ecoreModelUtilities.incomingReferenceName(incomingReference);
        String _upperCase_2 = _incomingReferenceName.toUpperCase();
        _builder.append(_upperCase_2, "");
        _builder.append("_UUID = \"");
        String _incomingReferenceName_1 = this.ecoreModelUtilities.incomingReferenceName(incomingReference);
        String _lowerCase_2 = _incomingReferenceName_1.toLowerCase();
        _builder.append(_lowerCase_2, "");
        _builder.append("_id\";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
