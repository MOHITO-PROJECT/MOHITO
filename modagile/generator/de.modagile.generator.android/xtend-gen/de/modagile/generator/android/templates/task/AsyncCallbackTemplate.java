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
package de.modagile.generator.android.templates.task;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import info.multiplatform.generator.java.templates.PackageInfo;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class AsyncCallbackTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateAsyncCallback(final IFileSystemAccess fsa, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "task/AsyncCallback.java");
    CharSequence _generateInterfaceCode = this.generateInterfaceCode(packageInfo);
    fsa.generateFile(_plus, ModagileFolderConstants.SRC_GEN, _generateInterfaceCode);
  }
  
  private CharSequence generateInterfaceCode(final PackageInfo packageInfo) {
    StringConcatenation _builder = new StringConcatenation();
    Class<? extends AsyncCallbackTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    String _packageName = packageInfo.getPackageName();
    String _plus = (_packageName + ".task");
    CharSequence _packageStatement = this.javaUtilities.packageStatement(_plus);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl("AsyncCallback<T>", null);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateCallBackMethod = this.generateCallBackMethod();
    _builder.append(_generateCallBackMethod, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateCallBackMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void onResult(T result);");
    _builder.newLine();
    return _builder;
  }
}
