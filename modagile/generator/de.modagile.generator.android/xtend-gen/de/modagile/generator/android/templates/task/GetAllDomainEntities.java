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
public class GetAllDomainEntities {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateGetAllDomainEntities(final IFileSystemAccess fsa, final EClass domainEntity, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "task/GetAll");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "s.java");
    CharSequence _generateClassCode = this.generateClassCode(packageInfo, domainEntity);
    fsa.generateFile(_plus_2, 
      ModagileFolderConstants.SRC_GEN, _generateClassCode);
  }
  
  private CharSequence generateClassCode(final PackageInfo packageInfo, final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    Class<? extends GetAllDomainEntities> _class = this.getClass();
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
    String _plus_1 = ("GetAll" + _name);
    String _plus_2 = (_plus_1 + "s");
    CharSequence _classDecl = this.javaUtilities.classDecl(_plus_2, null, null, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateMemberAttributes = this.generateMemberAttributes(domainEntity);
    _builder.append(_generateMemberAttributes, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateConstructor = this.generateConstructor(domainEntity);
    _builder.append(_generateConstructor, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateGetAllMethod = this.generateGetAllMethod(domainEntity);
    _builder.append(_generateGetAllMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateGetAllMethod_Async = this.generateGetAllMethod_Async(domainEntity);
    _builder.append(_generateGetAllMethod_Async, "");
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
    imports.add("android.database.Cursor");
    imports.add("android.net.Uri");
    imports.add("android.os.AsyncTask");
    imports.add("android.util.Log");
    imports.add("java.util.Collection");
    imports.add("java.util.ArrayList");
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
      String _plus = ("GetAll" + _name);
      String _plus_1 = (_plus + "s");
      CharSequence _generateConstructorBody = this.generateConstructorBody();
      CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl(_plus_1, params, _generateConstructorBody);
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
  
  private CharSequence generateGetAllMethod(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      String _name = domainEntity.getName();
      final String domainEntityVariable = StringExtensions.toFirstLower(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public Collection<");
      _builder.append(domainEntityName, "");
      _builder.append("> getAll");
      _builder.append(domainEntityName, "");
      _builder.append("s() {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final ContentResolver cResolver = mContext.getContentResolver();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final Cursor cursor = cResolver.query(DBConstants.CONTENT_URI_");
      String _upperCase = domainEntityName.toUpperCase();
      _builder.append(_upperCase, "	");
      _builder.append(", null, null, null, null);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("final Collection<");
      _builder.append(domainEntityName, "	");
      _builder.append("> list = new ArrayList<");
      _builder.append(domainEntityName, "	");
      _builder.append(">();");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("while (cursor.moveToNext()) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("final ");
      _builder.append(domainEntityName, "		");
      _builder.append(" ");
      _builder.append(domainEntityVariable, "		");
      _builder.append(" = ");
      _builder.append(domainEntityName, "		");
      _builder.append("Converter.cursorTo");
      _builder.append(domainEntityName, "		");
      _builder.append("(cursor);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("list.add(");
      _builder.append(domainEntityVariable, "		");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if (!cursor.isClosed()) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("cursor.close();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return list;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateGetAllMethod_Async(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public void getAll");
      _builder.append(domainEntityName, "");
      _builder.append("sAsync(final AsyncCallback<Collection<");
      _builder.append(domainEntityName, "");
      _builder.append(">> callback) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("GetAll");
      _builder.append(domainEntityName, "	");
      _builder.append("sTask task = new GetAll");
      _builder.append(domainEntityName, "	");
      _builder.append("sTask(mContext, callback);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("task.execute();");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateAsyncTaskClass(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = domainEntity.getName();
    String _plus = ("GetAll" + _name);
    String _plus_1 = (_plus + "sTask");
    String _name_1 = domainEntity.getName();
    String _plus_2 = ("AsyncTask<String, Void, Collection<" + _name_1);
    String _plus_3 = (_plus_2 + ">>");
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
    CharSequence _generateGetAllDomainEntitiesMethod = this.generateGetAllDomainEntitiesMethod(domainEntity);
    _builder.append(_generateGetAllDomainEntitiesMethod, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateTaskMembers(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    Pair<String,String> _pair = new Pair<String,String>("Context", "mContext");
    CharSequence _generateAdditionalAttribute = this.javaUtilities.generateAdditionalAttribute(_pair, false, true);
    _builder.append(_generateAdditionalAttribute, "");
    _builder.newLineIfNotEmpty();
    String _name = domainEntity.getName();
    String _plus = ("AsyncCallback<Collection<" + _name);
    String _plus_1 = (_plus + ">>");
    Pair<String,String> _pair_1 = new Pair<String,String>(_plus_1, "mCallback");
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
      String _name = domainEntity.getName();
      String _plus = ("AsyncCallback<Collection<" + _name);
      String _plus_1 = (_plus + ">>");
      Pair<String,String> _pair_1 = new Pair<String,String>(_plus_1, "callback");
      params.add(_pair_1);
      StringConcatenation _builder = new StringConcatenation();
      String _name_1 = domainEntity.getName();
      String _plus_2 = ("GetAll" + _name_1);
      String _plus_3 = (_plus_2 + "sTask");
      CharSequence _generateTaskConstructorBody = this.generateTaskConstructorBody();
      CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl(_plus_3, params, _generateTaskConstructorBody);
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
    _builder.append("protected Collection<");
    String _name = domainEntity.getName();
    _builder.append(_name, "");
    _builder.append("> doInBackground(String... params) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("return getAll");
    String _name_1 = domainEntity.getName();
    _builder.append(_name_1, "    ");
    _builder.append("s();");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateGetAllDomainEntitiesMethod(final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      final String domainEntityName = domainEntity.getName();
      String _name = domainEntity.getName();
      final String domainEntityVariable = StringExtensions.toFirstLower(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public Collection<");
      _builder.append(domainEntityName, "");
      _builder.append("> getAll");
      _builder.append(domainEntityName, "");
      _builder.append("s() {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final ContentResolver cResolver = mContext.getContentResolver();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final Cursor cursor = cResolver.query(DBConstants.CONTENT_URI_");
      String _upperCase = domainEntityName.toUpperCase();
      _builder.append(_upperCase, "	");
      _builder.append(", null, null, null, null);");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final Collection<");
      _builder.append(domainEntityName, "	");
      _builder.append("> list = new ArrayList<");
      _builder.append(domainEntityName, "	");
      _builder.append(">();");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("while (cursor.moveToNext()) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("final ");
      _builder.append(domainEntityName, "		");
      _builder.append(" ");
      _builder.append(domainEntityVariable, "		");
      _builder.append(" = ");
      _builder.append(domainEntityName, "		");
      _builder.append("Converter.cursorTo");
      _builder.append(domainEntityName, "		");
      _builder.append("(cursor);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("list.add(");
      _builder.append(domainEntityVariable, "		");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if (!cursor.isClosed()) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("cursor.close();");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return list;");
      _builder.newLine();
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
    _builder.append("protected void onPostExecute(Collection<");
    String _name = domainEntity.getName();
    _builder.append(_name, "");
    _builder.append("> result) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("mCallback.onResult(result);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
