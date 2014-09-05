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
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.ui.Flow;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class AppConstantsTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateAppConstants(final IFileSystemAccess fsa, final List<Flow> flows, final MobileApp m, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "constants/");
    String _name = m.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + "Constants.java");
    String _name_1 = m.getName();
    CharSequence _generateAppConstantsCode = this.generateAppConstantsCode(packageInfo, flows, _name_1);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateAppConstantsCode);
  }
  
  private CharSequence generateAppConstantsCode(final PackageInfo packageInfo, final List<Flow> flows, final String appName) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated("AppConstantsTemplate");
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    String _packageName = packageInfo.getPackageName();
    String _plus = (_packageName + ".constants");
    CharSequence _packageStatement = this.javaUtilities.packageStatement(_plus);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("      ");
    _builder.append("public class ");
    String _firstUpper = StringExtensions.toFirstUpper(appName);
    _builder.append(_firstUpper, "      ");
    _builder.append("Constants{");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    int counter = 0;
    _builder.newLineIfNotEmpty();
    {
      for(final Flow flow : flows) {
        _builder.append("            ");
        _builder.append("public static final int RESULT_CODE_");
        String _name = flow.getName();
        String _upperCase = _name.toUpperCase();
        String _replace = _upperCase.replace("->", "_TO_");
        String _replace_1 = _replace.replace(" ", "");
        _builder.append(_replace_1, "            ");
        _builder.append(" = ");
        int _plus_1 = (counter + 1);
        int _counter = counter = _plus_1;
        _builder.append(_counter, "            ");
        _builder.append("; ");
        _builder.newLineIfNotEmpty();
        _builder.append("            ");
        _builder.append("public static final int REQUEST_CODE_");
        String _name_1 = flow.getName();
        String _upperCase_1 = _name_1.toUpperCase();
        String _replace_2 = _upperCase_1.replace("->", "_TO_");
        String _replace_3 = _replace_2.replace(" ", "");
        _builder.append(_replace_3, "            ");
        _builder.append(" = ");
        _builder.append(counter, "            ");
        _builder.append("; ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("            ");
    _builder.append("public static final String ");
    String _upperCase_2 = appName.toUpperCase();
    _builder.append(_upperCase_2, "            ");
    _builder.append("_AUTHORITY = \"");
    String _packageName_1 = packageInfo.getPackageName();
    _builder.append(_packageName_1, "            ");
    _builder.append(".");
    _builder.append(appName, "            ");
    _builder.append("Provider\";");
    _builder.newLineIfNotEmpty();
    _builder.append("      ");
    _builder.append("}\t\t");
    _builder.newLine();
    return _builder;
  }
}
