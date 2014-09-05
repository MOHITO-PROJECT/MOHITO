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
package de.modagile.generator.android.templates.service;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.java.JavaUtils;
import info.multiplatform.generator.java.helper.Pair;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class AccountAuthenticatorServiceTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateAccountAuthenticatorService(final String packagePrefix, final String appName, final IFileSystemAccess fileSystemAccess, final String outputConfiguration) {
    String _plus = (packagePrefix + "service/AccountAuthenticatorService.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "service");
    CharSequence _generateAccountAuthenticatorCode = this.generateAccountAuthenticatorCode(_plus_1, "AccountAuthenticatorService");
    fileSystemAccess.generateFile(_plus, outputConfiguration, _generateAccountAuthenticatorCode);
  }
  
  public CharSequence generateAccountAuthenticatorCode(final String packageName, final String domainEntityName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      imports.add("android.app.Service");
      imports.add("android.content.Intent");
      imports.add("android.os.IBinder");
      String inheritance = "Service";
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateClass = this.generateClass("Account", packageName, domainEntityName, imports, inheritance, _arrayList);
      _xblockexpression = (_generateClass);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateClass(final String templateName, final String packageName, final String domainEntityName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Pair<String,String>> _arrayList = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> attr = _arrayList;
      Pair<String,String> _pair = new Pair<String,String>("AccountAuthenticator", "sAccountAuthenticator");
      attr.add(_pair);
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
      _builder.append(_statementGenerated, "");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
      _builder.append(_packageStatement, "			");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      CharSequence _importStatements = this.javaUtilities.importStatements(imports);
      _builder.append(_importStatements, "			");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      CharSequence _classDecl = this.javaUtilities.classDecl(domainEntityName, inheritance, interfaces, false);
      _builder.append(_classDecl, "			");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      CharSequence _generateAdditionalAttributes = this.javaUtilities.generateAdditionalAttributes(attr, true, false);
      _builder.append(_generateAdditionalAttributes, "			");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("\t\t\t ");
      _builder.append("* Constructor for this service.");
      _builder.newLine();
      _builder.append("\t\t\t ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t\t\t");
      CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl(domainEntityName, null, "super();");
      _builder.append(_generateConstructorDecl, "			");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t");
      CharSequence _generateMethodsToBeImplemented = this.generateMethodsToBeImplemented();
      _builder.append(_generateMethodsToBeImplemented, "			");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("}");
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateMethodsToBeImplemented() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _gernateOnBind = this.gernateOnBind();
    _builder.append(_gernateOnBind, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateGetAuthenticator = this.generateGetAuthenticator();
    _builder.append(_generateGetAuthenticator, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence gernateOnBind() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t \t");
    _builder.append("* This method must return a subclass of AbstractAccountAuthenticator.");
    _builder.newLine();
    _builder.append("\t \t");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public IBinder onBind(Intent intent) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("IBinder binder = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT)) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("binder = getAuthenticator().getIBinder();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return binder;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateGetAuthenticator() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private AccountAuthenticator getAuthenticator() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (sAccountAuthenticator == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sAccountAuthenticator = new AccountAuthenticator(this);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return sAccountAuthenticator;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
