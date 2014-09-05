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
package de.modagile.generator.android.templates.helper;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.java.JavaUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class TimeProviderTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateTimeProvider(final String packagePrefix, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "helper/TimeProvider.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "helper");
    CharSequence _generateTimeProviderCode = this.generateTimeProviderCode(_plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateTimeProviderCode);
  }
  
  public void generateTimeProviderImpl(final String packagePrefix, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "helper/TimeProviderImpl.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "helper");
    CharSequence _generateTimeProviderImplCode = this.generateTimeProviderImplCode(_plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateTimeProviderImplCode);
  }
  
  public CharSequence generateTimeProviderCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateTimeProviderClass = this.generateTimeProviderClass("TimeproviderTemplate", packageName, imports, null);
      _builder.append(_generateTimeProviderClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateTimeProviderClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl("TimeProvider", inheritance);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    _builder.append("long currentTimeMillis();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * TimeProviderImpl Methods
   */
  public CharSequence generateTimeProviderImplCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> interfaces = _arrayList;
      interfaces.add("TimeProvider");
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateTimeProviderImplClass = this.generateTimeProviderImplClass("TimeproviderTemplate", packageName, imports, interfaces, null);
      _builder.append(_generateTimeProviderImplClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateTimeProviderImplClass(final String templateName, final String packageName, final Set<String> imports, final List<String> interfaces, final String inheritance) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    CharSequence _classDecl = this.javaUtilities.classDecl("TimeProviderImpl", inheritance, interfaces, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    _builder.append("private static TimeProviderImpl instance = new TimeProviderImpl();");
    _builder.newLine();
    _builder.append("private TimeProviderImpl() {}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public static TimeProvider getInstance() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(instance == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("instance = new TimeProviderImpl();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return instance;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public long currentTimeMillis() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return System.currentTimeMillis();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
