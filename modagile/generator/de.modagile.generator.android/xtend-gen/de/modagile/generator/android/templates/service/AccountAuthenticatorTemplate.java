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
public class AccountAuthenticatorTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateAccountAuthenticator(final String packagePrefix, final String appName, final IFileSystemAccess fileSystemAccess, final String outputConfiguration) {
    String _plus = (packagePrefix + "service/AccountAuthenticator.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    CharSequence _generateAccountAuthenticatorCode = this.generateAccountAuthenticatorCode(_replaceAll, appName, "AccountAuthenticator");
    fileSystemAccess.generateFile(_plus, outputConfiguration, _generateAccountAuthenticatorCode);
  }
  
  public CharSequence generateAccountAuthenticatorCode(final String packagePrefix, final String appName, final String domainEntityName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      imports.add("android.accounts.AbstractAccountAuthenticator");
      imports.add("android.accounts.Account");
      imports.add("android.accounts.AccountAuthenticatorResponse");
      imports.add("android.accounts.AccountManager");
      imports.add("android.accounts.NetworkErrorException");
      imports.add("android.content.Context");
      imports.add("android.content.Intent");
      imports.add("android.os.Bundle");
      imports.add("android.app.Activity");
      String _plus = (packagePrefix + "R");
      imports.add(_plus);
      String _plus_1 = (packagePrefix + "constants.");
      String _plus_2 = (_plus_1 + appName);
      String _plus_3 = (_plus_2 + "Constants");
      imports.add(_plus_3);
      String inheritance = "AbstractAccountAuthenticator";
      String _plus_4 = (packagePrefix + "service");
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateClass = this.generateClass("Account", _plus_4, domainEntityName, imports, inheritance, _arrayList, appName);
      _xblockexpression = (_generateClass);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateClass(final String templateName, final String packageName, final String domainEntityName, final Set<String> imports, final String inheritance, final List<String> interfaces, final String appName) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Pair<String,String>> _arrayList = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> attr = _arrayList;
      ArrayList<Pair<String,String>> _arrayList_1 = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> contructorParameters = _arrayList_1;
      Pair<String,String> _pair = new Pair<String,String>("Context", "mContext");
      attr.add(_pair);
      Pair<String,String> _pair_1 = new Pair<String,String>("Context", "context");
      contructorParameters.add(_pair_1);
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
      CharSequence _classDecl = this.javaUtilities.classDecl(domainEntityName, inheritance, interfaces, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl(domainEntityName, contructorParameters, "super(context); mContext = context;");
      _builder.append(_generateConstructorDecl, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateAdditionalAttributes = this.javaUtilities.generateAdditionalAttributes(attr, false, true);
      _builder.append(_generateAdditionalAttributes, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateMethodsToBeImplemented = this.generateMethodsToBeImplemented(appName);
      _builder.append(_generateMethodsToBeImplemented, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateMethodsToBeImplemented(final String appName) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateAddAccountMethod = this.generateAddAccountMethod(appName);
    _builder.append(_generateAddAccountMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateConfirmCredentials = this.generateConfirmCredentials();
    _builder.append(_generateConfirmCredentials, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateEditProperties = this.generateEditProperties();
    _builder.append(_generateEditProperties, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateGetAuthToken = this.generateGetAuthToken();
    _builder.append(_generateGetAuthToken, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateGetAuthTokenLabel = this.generateGetAuthTokenLabel();
    _builder.append(_generateGetAuthTokenLabel, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateHasFeatures = this.generateHasFeatures();
    _builder.append(_generateHasFeatures, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateUpdateCredentials = this.generateUpdateCredentials();
    _builder.append(_generateUpdateCredentials, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateAddAccountMethod(final String appName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* The user has requested to add a new account to the system. We return an intent that will launch our login screen");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* if the user has not logged in yet, otherwise our activity will just pass the user\'s credentials on to the account");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* manager.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType,");
    _builder.newLine();
    _builder.append("\t      ");
    _builder.append("String[] requiredFeatures, Bundle options) throws NetworkErrorException {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Bundle reply = new Bundle();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// create an intent");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("//the Activity.class has to be replaced with a concrete Activity");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Intent i = new Intent(mContext, Activity.class);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i.setAction(");
    _builder.append(appName, "		");
    _builder.append("Constants.loginActivityAction);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("i.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("reply.putParcelable(AccountManager.KEY_INTENT, i);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return reply;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateConfirmCredentials() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateEditProperties() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateGetAuthToken() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType,");
    _builder.newLine();
    _builder.append("\t      ");
    _builder.append("Bundle options) throws NetworkErrorException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateGetAuthTokenLabel() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public String getAuthTokenLabel(String authTokenType) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (authTokenType != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return mContext.getString(R.string.app_name);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateHasFeatures() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features)");
    _builder.newLine();
    _builder.append("\t    \t");
    _builder.append("throws NetworkErrorException {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateUpdateCredentials() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Bundle options) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
