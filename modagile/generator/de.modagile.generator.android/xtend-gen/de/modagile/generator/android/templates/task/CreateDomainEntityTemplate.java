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
import info.multiplatform.generator.java.helper.Pair;
import info.multiplatform.generator.java.helper.Triple;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CreateDomainEntityTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateCreateDomainEntity(final IFileSystemAccess fsa, final EClass domainEntity, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "task/Create");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + ".java");
    CharSequence _generateClassCode = this.generateClassCode(packageInfo, domainEntity);
    fsa.generateFile(_plus_2, 
      ModagileFolderConstants.SRC_GEN, _generateClassCode);
  }
  
  private CharSequence generateClassCode(final PackageInfo packageInfo, final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    Class<? extends CreateDomainEntityTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    String _packageName = packageInfo.getPackageName();
    String _plus = (_packageName + ".task");
    CharSequence _packageStatement = this.javaUtilities.packageStatement(_plus);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    Set _generateImports = this.generateImports(packageInfo);
    CharSequence _importStatements = this.javaUtilities.importStatements(_generateImports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    String _name = domainEntity.getName();
    String _plus_1 = ("Create" + _name);
    CharSequence _classDecl = this.javaUtilities.classDecl(_plus_1, null, null, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateMemberAttributes = this.generateMemberAttributes(domainEntity);
    _builder.append(_generateMemberAttributes, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateConstructor = this.generateConstructor(domainEntity);
    _builder.append(_generateConstructor, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateCreateMethod = this.generateCreateMethod(domainEntity);
    _builder.append(_generateCreateMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateCreateMethod_Async = this.generateCreateMethod_Async(domainEntity);
    _builder.append(_generateCreateMethod_Async, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateAsyncTaskClass = this.generateAsyncTaskClass(domainEntity);
    _builder.append(_generateAsyncTaskClass, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private Set generateImports(final PackageInfo packageInfo) {
    HashSet<String> _hashSet = new HashSet<String>();
    Set imports = _hashSet;
    imports.add("android.content.Context");
    imports.add("android.content.ContentValues");
    imports.add("android.content.ContentResolver");
    imports.add("android.net.Uri");
    imports.add("android.os.AsyncTask");
    imports.add("android.util.Log");
    String _packageName = packageInfo.getPackageName();
    String _plus = (_packageName + ".model.*");
    imports.add(_plus);
    String _packageName_1 = packageInfo.getPackageName();
    String _plus_1 = (_packageName_1 + ".constants.DBConstants");
    imports.add(_plus_1);
    return imports;
  }
  
  private CharSequence generateMemberAttributes(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = domainEntity.getName();
    String _plus = ("Create" + _name);
    String _plus_1 = (_plus + ".class.getSimpleName()");
    Triple<String,String,String> _triple = new Triple<String,String,String>("String", "TAG", _plus_1);
    CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(_triple, true, true);
    _builder.append(_generateAdditionalAttributesWithExpression, "");
    _builder.newLineIfNotEmpty();
    Pair<String,String> _pair = new Pair<String,String>("Context", "mContext");
    CharSequence _generateAdditionalAttribute = this.javaUtilities.generateAdditionalAttribute(_pair, false, false);
    _builder.append(_generateAdditionalAttribute, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateConstructor(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Pair<String,String>> _arrayList = new ArrayList<Pair<String,String>>();
      ArrayList<Pair<String,String>> params = _arrayList;
      Pair<String,String> _pair = new Pair<String,String>("Context", "context");
      params.add(_pair);
      StringConcatenation _builder = new StringConcatenation();
      String _name = domainEntity.getName();
      String _plus = ("Create" + _name);
      CharSequence _generateConstructorBody = this.generateConstructorBody();
      CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl(_plus, params, _generateConstructorBody);
      _builder.append(_generateConstructorDecl, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateConstructorBody() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("if (context == null) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("throw new IllegalArgumentException(\"Context and callback must not be null!\");");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("mContext = context;");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateCreateMethod(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      String _name = domainEntity.getName();
      final String domainEntityVariable = StringExtensions.toFirstLower(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public Uri create");
      _builder.append(domainEntityName, "");
      _builder.append("(final ");
      _builder.append(domainEntityName, "");
      _builder.append(" ");
      _builder.append(domainEntityVariable, "");
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("Uri uri = null;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("final ContentResolver cResolver = mContext.getContentResolver();");
      _builder.newLine();
      _builder.newLine();
      _builder.append("if (");
      _builder.append(domainEntityVariable, "");
      _builder.append(" == null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("throw new IllegalStateException(\"");
      _builder.append(domainEntityName, "	");
      _builder.append(" must not be null!\");");
      _builder.newLineIfNotEmpty();
      _builder.append("} else {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("try {");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if (");
      _builder.append(domainEntityVariable, "	");
      _builder.append(".getId() != null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("throw new IllegalArgumentException(");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("\"ID is not null! Create does not handle already persisted objects!\");");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final ContentValues values = ");
      _builder.append(domainEntityName, "	");
      _builder.append("Converter.");
      _builder.append(domainEntityVariable, "	");
      _builder.append("ToValues(");
      _builder.append(domainEntityVariable, "	");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("uri = cResolver.insert(DBConstants.CONTENT_URI_");
      String _upperCase = domainEntityName.toUpperCase();
      _builder.append(_upperCase, "	");
      _builder.append(", values);");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("} catch (Exception e) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Log.e(TAG, e.getMessage(), e);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("return uri;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateCreateMethod_Async(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      String _name = domainEntity.getName();
      final String domainEntityVariable = StringExtensions.toFirstLower(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public void create");
      _builder.append(domainEntityName, "");
      _builder.append("Async(final ");
      _builder.append(domainEntityName, "");
      _builder.append(" ");
      _builder.append(domainEntityVariable, "");
      _builder.append(", final AsyncCallback<Uri> callback) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Create");
      _builder.append(domainEntityName, "	");
      _builder.append("Task task = new Create");
      _builder.append(domainEntityName, "	");
      _builder.append("Task(mContext, callback);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("task.execute(");
      _builder.append(domainEntityVariable, "	");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateAsyncTaskClass(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = domainEntity.getName();
    String _plus = ("Create" + _name);
    String _plus_1 = (_plus + "Task");
    String _name_1 = domainEntity.getName();
    String _plus_2 = ("AsyncTask<" + _name_1);
    String _plus_3 = (_plus_2 + ", Void, Uri>");
    CharSequence _innerClassDecl = this.javaUtilities.innerClassDecl("private", _plus_1, _plus_3, null, false, false);
    _builder.append(_innerClassDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateTaskMembers = this.generateTaskMembers(domainEntity);
    _builder.append(_generateTaskMembers, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateTaskConstructor = this.generateTaskConstructor(domainEntity);
    _builder.append(_generateTaskConstructor, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDoInBackgroundMethod = this.generateDoInBackgroundMethod(domainEntity);
    _builder.append(_generateDoInBackgroundMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateOnPostExecuteMethod = this.generateOnPostExecuteMethod();
    _builder.append(_generateOnPostExecuteMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateCreatePersonSyncMethod = this.generateCreatePersonSyncMethod(domainEntity);
    _builder.append(_generateCreatePersonSyncMethod, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateTaskMembers(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = domainEntity.getName();
    String _plus = ("Create" + _name);
    String _plus_1 = (_plus + "Task.class.getSimpleName()");
    Triple<String,String,String> _triple = new Triple<String,String,String>("String", "TAG", _plus_1);
    CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(_triple, false, true);
    _builder.append(_generateAdditionalAttributesWithExpression, "");
    _builder.newLineIfNotEmpty();
    Pair<String,String> _pair = new Pair<String,String>("Context", "mContext");
    CharSequence _generateAdditionalAttribute = this.javaUtilities.generateAdditionalAttribute(_pair, false, true);
    _builder.append(_generateAdditionalAttribute, "");
    _builder.newLineIfNotEmpty();
    Pair<String,String> _pair_1 = new Pair<String,String>("AsyncCallback<Uri>", "mCallback");
    CharSequence _generateAdditionalAttribute_1 = this.javaUtilities.generateAdditionalAttribute(_pair_1, false, true);
    _builder.append(_generateAdditionalAttribute_1, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateTaskConstructor(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Pair<String,String>> _arrayList = new ArrayList<Pair<String,String>>();
      ArrayList<Pair<String,String>> params = _arrayList;
      Pair<String,String> _pair = new Pair<String,String>("Context", "context");
      params.add(_pair);
      Pair<String,String> _pair_1 = new Pair<String,String>("AsyncCallback<Uri>", "callback");
      params.add(_pair_1);
      StringConcatenation _builder = new StringConcatenation();
      String _name = domainEntity.getName();
      String _plus = ("Create" + _name);
      String _plus_1 = (_plus + "Task");
      CharSequence _generateTaskConstructorBody = this.generateTaskConstructorBody();
      CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl(_plus_1, params, _generateTaskConstructorBody);
      _builder.append(_generateConstructorDecl, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateTaskConstructorBody() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("if (context == null || callback == null) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("throw new IllegalArgumentException(\"Context and callback must not be null!\");");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("mContext = context;");
    _builder.newLine();
    _builder.append("mCallback = callback;");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateDoInBackgroundMethod(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected Uri doInBackground(");
    String _name = domainEntity.getName();
    _builder.append(_name, "");
    _builder.append("... params) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return create");
    String _name_1 = domainEntity.getName();
    _builder.append(_name_1, "	");
    _builder.append("Sync(params);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateOnPostExecuteMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void onPostExecute(Uri result) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (mCallback == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("mCallback.onResult(result);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateCreatePersonSyncMethod(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      String _name = domainEntity.getName();
      final String domainEntityVariable = StringExtensions.toFirstLower(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public Uri create");
      _builder.append(domainEntityName, "");
      _builder.append("Sync(");
      _builder.append(domainEntityName, "");
      _builder.append("... params) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Uri uri = null;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if (params == null || params.length != 1) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("throw new IllegalArgumentException(\"Paramter must not be null or empty!\");");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final ContentResolver cResolver = mContext.getContentResolver();");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final ");
      _builder.append(domainEntityName, "	");
      _builder.append(" ");
      _builder.append(domainEntityVariable, "	");
      _builder.append(" = params[0];");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if (");
      _builder.append(domainEntityVariable, "	");
      _builder.append(" == null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("throw new IllegalStateException(\"");
      _builder.append(domainEntityName, "		");
      _builder.append(" must not be null!\");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("} else {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("try {");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("if (");
      _builder.append(domainEntityVariable, "			");
      _builder.append(".getId() != null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("throw new IllegalArgumentException(");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("\"ID is not null! Create does not handle already persisted objects!\");");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("final ContentValues values = ");
      _builder.append(domainEntityName, "			");
      _builder.append("Converter.");
      _builder.append(domainEntityVariable, "			");
      _builder.append("ToValues(");
      _builder.append(domainEntityVariable, "			");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("uri = cResolver.insert(DBConstants.CONTENT_URI_");
      String _upperCase = domainEntityName.toUpperCase();
      _builder.append(_upperCase, "			");
      _builder.append(", values);");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("} catch (Exception e) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("Log.e(TAG, e.getMessage(), e);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return uri;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
}
