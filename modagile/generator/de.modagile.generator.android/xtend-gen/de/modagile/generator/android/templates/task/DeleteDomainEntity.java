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
public class DeleteDomainEntity {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateDeleteDomainEntity(final IFileSystemAccess fsa, final EClass domainEntity, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "task/Delete");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + ".java");
    CharSequence _generateClassCode = this.generateClassCode(packageInfo, domainEntity);
    fsa.generateFile(_plus_2, 
      ModagileFolderConstants.SRC_GEN, _generateClassCode);
  }
  
  private CharSequence generateClassCode(final PackageInfo packageInfo, final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    Class<? extends DeleteDomainEntity> _class = this.getClass();
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
    String _plus_1 = ("Delete" + _name);
    CharSequence _classDecl = this.javaUtilities.classDecl(_plus_1, null, null, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateMemberAttributes = this.generateMemberAttributes(domainEntity);
    _builder.append(_generateMemberAttributes, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateConstructor = this.generateConstructor(domainEntity);
    _builder.append(_generateConstructor, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDeleteDomainEntityMethod = this.generateDeleteDomainEntityMethod(domainEntity);
    _builder.append(_generateDeleteDomainEntityMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDeleteDomainEntityMethod_Async = this.generateDeleteDomainEntityMethod_Async(domainEntity);
    _builder.append(_generateDeleteDomainEntityMethod_Async, "");
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
    imports.add("android.content.ContentUris");
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
    String _plus = ("Delete" + _name);
    String _plus_1 = (_plus + ".class.getSimpleName()");
    Triple<String,String,String> _triple = new Triple<String,String,String>("String", "TAG", _plus_1);
    CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(_triple, true, true);
    _builder.append(_generateAdditionalAttributesWithExpression, "");
    _builder.newLineIfNotEmpty();
    Pair<String,String> _pair = new Pair<String,String>("Context", "mContext");
    CharSequence _generateAdditionalAttribute = this.javaUtilities.generateAdditionalAttribute(_pair, false, true);
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
      String _plus = ("Delete" + _name);
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
    _builder.append("throw new IllegalArgumentException(\"Context must not be null!\");");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("mContext = context;");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateDeleteDomainEntityMethod(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      String _name = domainEntity.getName();
      final String domainEntityVariable = StringExtensions.toFirstLower(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public boolean delete");
      _builder.append(domainEntityName, "");
      _builder.append("(final ");
      _builder.append(domainEntityName, "");
      _builder.append(" ");
      _builder.append(domainEntityVariable, "");
      _builder.append(") {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("boolean result = false;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if (");
      _builder.append(domainEntityVariable, "	");
      _builder.append(" == null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("throw new IllegalArgumentException(\"Paramter must not be null or empty!\");");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if (");
      _builder.append(domainEntityVariable, "	");
      _builder.append(".getId() == null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("throw new IllegalArgumentException(\"Id is null! Only a persisted ");
      _builder.append(domainEntityName, "		");
      _builder.append(" can be deleted.\");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final Uri uri = ContentUris.withAppendedId(DBConstants.CONTENT_URI_");
      String _upperCase = domainEntityVariable.toUpperCase();
      _builder.append(_upperCase, "	");
      _builder.append(", ");
      _builder.append(domainEntityVariable, "	");
      _builder.append(".getId());");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("final ContentResolver cResolver = mContext.getContentResolver();");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("int resultLocal = cResolver.delete(uri, null, null);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Log.d(TAG, \"Local delete result: \" + resultLocal);");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if (resultLocal == 1) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("result = true;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("} else if (resultLocal > 1) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("throw new IllegalStateException(\"Found more than one row to delete. Excactly one row was expected!\");");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return result;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateDeleteDomainEntityMethod_Async(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      String _name = domainEntity.getName();
      final String domainEntityVariable = StringExtensions.toFirstLower(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public void delete");
      _builder.append(domainEntityName, "");
      _builder.append("Async(final ");
      _builder.append(domainEntityName, "");
      _builder.append(" ");
      _builder.append(domainEntityVariable, "");
      _builder.append(", final AsyncCallback<Boolean> callback) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("Delete");
      _builder.append(domainEntityName, "	");
      _builder.append("Task task = new Delete");
      _builder.append(domainEntityName, "	");
      _builder.append("Task(callback);");
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
    String _plus = ("Delete" + _name);
    String _plus_1 = (_plus + "Task");
    String _name_1 = domainEntity.getName();
    String _plus_2 = ("AsyncTask<" + _name_1);
    String _plus_3 = (_plus_2 + ", Void, Boolean>");
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
    CharSequence _generateOnPostExecuteMethod = this.generateOnPostExecuteMethod(domainEntity);
    _builder.append(_generateOnPostExecuteMethod, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateTaskMembers(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    Pair<String,String> _pair = new Pair<String,String>("AsyncCallback<Boolean>", "mCallback");
    CharSequence _generateAdditionalAttribute = this.javaUtilities.generateAdditionalAttribute(_pair, false, true);
    _builder.append(_generateAdditionalAttribute, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateTaskConstructor(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Pair<String,String>> _arrayList = new ArrayList<Pair<String,String>>();
      ArrayList<Pair<String,String>> params = _arrayList;
      Pair<String,String> _pair = new Pair<String,String>("AsyncCallback<Boolean>", "callback");
      params.add(_pair);
      StringConcatenation _builder = new StringConcatenation();
      String _name = domainEntity.getName();
      String _plus = ("Delete" + _name);
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
    _builder.append("if (callback == null) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("throw new IllegalArgumentException(\"Callback must not be null!\");");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("mCallback = callback;");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateDoInBackgroundMethod(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      String _name = domainEntity.getName();
      final String domainEntityVariable = StringExtensions.toFirstLower(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("protected Boolean doInBackground(final ");
      _builder.append(domainEntityName, "");
      _builder.append("... params) {");
      _builder.newLineIfNotEmpty();
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
      _builder.append(".getId() == null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("throw new IllegalArgumentException(\"Id is null! Only a persisted ");
      _builder.append(domainEntityName, "		");
      _builder.append(" can be deleted.\");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return delete");
      _builder.append(domainEntityName, "	");
      _builder.append("(");
      _builder.append(domainEntityVariable, "	");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateOnPostExecuteMethod(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void onPostExecute(final Boolean result) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (mCallback == null) {");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("throw new IllegalStateException(\"Callback must be set for async task execution!\");");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("mCallback.onResult(result);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
