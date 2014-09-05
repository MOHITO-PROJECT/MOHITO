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

/**
 * Generates URI indicator for very entity
 */
@SuppressWarnings("all")
public class IndicatorConstantsTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtils;
  
  public void generateIndicatorConstants(final IFileSystemAccess fsa, final List<EClass> entities, final MobileApp m, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "contentprovider/");
    String _name = m.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + "Indicator.java");
    String _packageName = packageInfo.getPackageName();
    String _packageName_1 = packageInfo.getPackageName();
    String _plus_3 = (_packageName_1 + ".contentprovider");
    String _name_1 = m.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    CharSequence _generateSource = this.generateSource(_packageName, _plus_3, _firstUpper_1, entities);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateSource);
  }
  
  private CharSequence generateSource(final String basePackageName, final String contentPackageName, final String appName, final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtils.statementGenerated("ContentProviderConstantsTempalte");
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtils.packageStatement(contentPackageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import java.util.Map;");
    _builder.newLine();
    {
      for(final EClass entity : entities) {
        _builder.append("import ");
        _builder.append(basePackageName, "");
        _builder.append(".model.");
        String _name = entity.getName();
        _builder.append(_name, "");
        _builder.append("Converter;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("public class ");
    _builder.append(appName, "");
    _builder.append("Indicator{");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    CharSequence _entityFields = this.entityFields(entities);
    _builder.append(_entityFields, "   ");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence entityFields(final List<EClass> entities) {
    StringConcatenation _builder = new StringConcatenation();
    int counter = 0;
    _builder.newLineIfNotEmpty();
    {
      for(final EClass entity : entities) {
        _builder.append("//");
        String _name = entity.getName();
        _builder.append(_name, "");
        _builder.newLineIfNotEmpty();
        _builder.append("public static Map<String, String> ");
        String _name_1 = entity.getName();
        String _lowerCase = _name_1.toLowerCase();
        _builder.append(_lowerCase, "");
        _builder.append("ProjectionMap = ");
        String _name_2 = entity.getName();
        _builder.append(_name_2, "");
        _builder.append("Converter.createProjectionHashmap();;");
        _builder.newLineIfNotEmpty();
        _builder.append("public static final int COLLECTION_");
        String _name_3 = entity.getName();
        String _upperCase = _name_3.toUpperCase();
        _builder.append(_upperCase, "");
        _builder.append("_URI_INDICATOR = ");
        int _plus = (counter + 1);
        int _counter = counter = _plus;
        _builder.append(_counter, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("public static final int SINGLE_");
        String _name_4 = entity.getName();
        String _upperCase_1 = _name_4.toUpperCase();
        _builder.append(_upperCase_1, "");
        _builder.append("_URI_INDICATOR = ");
        int _plus_1 = (counter + 1);
        int _counter_1 = counter = _plus_1;
        _builder.append(_counter_1, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
}
