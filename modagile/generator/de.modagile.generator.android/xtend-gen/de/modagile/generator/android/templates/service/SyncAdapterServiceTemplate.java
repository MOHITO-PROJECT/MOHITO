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
import info.multiplatform.generator.java.helper.Triple;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncAdapterServiceTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateSyncAdapterService(final String packagePrefix, final String appName, final IFileSystemAccess fileSystemAccess, final String outputConfiguration) {
    String _plus = (packagePrefix + "service/SyncAdapterService.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    CharSequence _generateSyncAdapterCode = this.generateSyncAdapterCode(_replaceAll, appName, "SyncAdapterService");
    fileSystemAccess.generateFile(_plus, outputConfiguration, _generateSyncAdapterCode);
  }
  
  public CharSequence generateSyncAdapterCode(final String packagePrefix, final String appName, final String domainEntityName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      String inheritance = "Service";
      imports.add("java.text.Format");
      imports.add("java.text.SimpleDateFormat");
      imports.add("java.util.Date");
      imports.add("android.accounts.Account");
      imports.add("android.accounts.NetworkErrorException");
      imports.add("android.accounts.OperationCanceledException");
      imports.add("android.app.Service");
      imports.add("android.content.AbstractThreadedSyncAdapter");
      imports.add("android.content.ContentProviderClient");
      imports.add("android.content.Context");
      imports.add("android.content.Intent");
      imports.add("android.content.SyncResult");
      imports.add("android.os.Bundle");
      imports.add("android.os.IBinder");
      imports.add("android.util.Log");
      String _plus = (packagePrefix + "constants.");
      String _plus_1 = (_plus + appName);
      String _plus_2 = (_plus_1 + "Constants");
      imports.add(_plus_2);
      String _plus_3 = (packagePrefix + "manager.SyncManager");
      imports.add(_plus_3);
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
      ArrayList<Triple<String,String,String>> _arrayList = new ArrayList<Triple<String,String,String>>();
      List<Triple<String,String,String>> attributes = _arrayList;
      String innerClass = "SyncAdapterImpl";
      ArrayList<Pair<String,String>> _arrayList_1 = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> innterClassConstrParameters = _arrayList_1;
      ArrayList<Pair<String,String>> _arrayList_2 = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> innterClassAttributes = _arrayList_2;
      Pair<String,String> _pair = new Pair<String,String>("Context", "context");
      innterClassConstrParameters.add(_pair);
      Pair<String,String> _pair_1 = new Pair<String,String>("Context", "mContext");
      innterClassAttributes.add(_pair_1);
      String _plus = (domainEntityName + ".class.getSimpleName()");
      Triple<String,String,String> _triple = new Triple<String,String,String>("String", "TAG", _plus);
      attributes.add(_triple);
      Triple<String,String,String> _triple_1 = new Triple<String,String,String>(innerClass, "sSyncAdapter", "null");
      attributes.add(_triple_1);
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
      CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl(domainEntityName, null, "super();");
      _builder.append(_generateConstructorDecl, "");
      _builder.newLineIfNotEmpty();
      Triple<String,String,String> _get = attributes.get(0);
      CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(_get, true, true);
      _builder.append(_generateAdditionalAttributesWithExpression, "");
      _builder.newLineIfNotEmpty();
      Triple<String,String,String> _get_1 = attributes.get(1);
      CharSequence _generateAdditionalAttributesWithExpression_1 = this.javaUtilities.generateAdditionalAttributesWithExpression(_get_1, true, false);
      _builder.append(_generateAdditionalAttributesWithExpression_1, "");
      _builder.newLineIfNotEmpty();
      ArrayList<String> _arrayList_3 = new ArrayList<String>();
      CharSequence _innerClassDecl = this.javaUtilities.innerClassDecl("", innerClass, "AbstractThreadedSyncAdapter", _arrayList_3, true, false);
      _builder.append(_innerClassDecl, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateAdditionalAttributes = this.javaUtilities.generateAdditionalAttributes(innterClassAttributes, true, false);
      _builder.append(_generateAdditionalAttributes, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateConstructorDecl_1 = this.javaUtilities.generateConstructorDecl(innerClass, innterClassConstrParameters, "super(context, true); mContext = context;");
      _builder.append(_generateConstructorDecl_1, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateInnerClassOnPerformSyncMethod = this.generateInnerClassOnPerformSyncMethod();
      _builder.append(_generateInnerClassOnPerformSyncMethod, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      CharSequence _gernerateMethodsToBeImplemented = this.gernerateMethodsToBeImplemented(appName);
      _builder.append(_gernerateMethodsToBeImplemented, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateInnerClassOnPerformSyncMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider,");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("SyncResult syncResult) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("SyncAdapterService.performSync(mContext, account, extras, authority, provider, syncResult);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (OperationCanceledException e) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence gernerateMethodsToBeImplemented(final String appName) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateOnBind = this.generateOnBind();
    _builder.append(_generateOnBind, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateGetSyncAdapter = this.generateGetSyncAdapter();
    _builder.append(_generateGetSyncAdapter, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generatePerformSync = this.generatePerformSync(appName);
    _builder.append(_generatePerformSync, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateOnBind() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public IBinder onBind(Intent intent) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("IBinder ret = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ret = getSyncAdapter().getSyncAdapterBinder();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return ret;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateGetSyncAdapter() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private SyncAdapterImpl getSyncAdapter() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (sSyncAdapter == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sSyncAdapter = new SyncAdapterImpl(this);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return sSyncAdapter;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generatePerformSync(final String appName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private static void performSync(Context context, Account account, Bundle extras, String authority,");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("ContentProviderClient provider, SyncResult syncResult) throws OperationCanceledException {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// create an intent with action for starting the sync");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Intent intent = new Intent(");
    _builder.append(appName, "	");
    _builder.append("Constants.SYNC_START);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// send broadcast");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("context.sendBroadcast(intent);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Log.i(TAG, \"Performing sync: \" + account.toString());");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// prepare synchronization");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Format f = new SimpleDateFormat(");
    _builder.append(appName, "	");
    _builder.append("Constants.LOG_TIME_FORMAT);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("Date date = new Date();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("SyncManager sm = new SyncManager(context);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"Background sync started at \" + f.format(date));");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// execute synchronization");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sm.executeAllSync();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("date = new Date();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"Background sync stopped at \" + f.format(date));");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (NetworkErrorException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.e(TAG, \"Network error! Reason: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// create an intent with action for stopping the sync");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("intent = new Intent(");
    _builder.append(appName, "	");
    _builder.append("Constants.SYNC_STOP);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// send broadcast");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("context.sendBroadcast(intent);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
