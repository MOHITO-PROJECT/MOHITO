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
package de.modagile.generator.android.templates.manager;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import info.multiplatform.generator.java.helper.Pair;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class DomainEntityManagerImplTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  /**
   * Creates the SuperClass SyncEntity
   */
  public void generateDomainEntityManagerImpl(final IFileSystemAccess fsa, final EClass domainEntity, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "manager/impl/");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "ManagerImpl.java");
    CharSequence _generateDomainEntityManagerImplCode = this.generateDomainEntityManagerImplCode(packageInfo, domainEntity);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateDomainEntityManagerImplCode);
  }
  
  /**
   * DomainEntityManger Implementations
   */
  public CharSequence generateDomainEntityManagerImplCode(final PackageInfo packageInfo, final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> interfaces = _arrayList;
      String _name = domainEntity.getName();
      String _plus = (_name + "Manager");
      interfaces.add(_plus);
      this.getDomainEntityManagerImplImports(imports, domainEntity, packageInfo);
      StringConcatenation _builder = new StringConcatenation();
      String _packageName = packageInfo.getPackageName();
      String _plus_1 = (_packageName + ".manager.impl");
      CharSequence _generateDomainEntityManagerImplClass = this.generateDomainEntityManagerImplClass("DomainEntityManagerTemplate", _plus_1, imports, interfaces, null, domainEntity);
      _builder.append(_generateDomainEntityManagerImplClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private boolean getDomainEntityManagerImplImports(final Set<String> imports, final EClass domainEntity, final PackageInfo packageInfo) {
    boolean _xblockexpression = false;
    {
      imports.add("java.util.ArrayList");
      imports.add("java.util.Collection");
      imports.add("java.util.List");
      imports.add("android.content.ContentUris");
      imports.add("android.content.Context");
      imports.add("android.net.Uri");
      imports.add("android.util.Log");
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".model.*");
      imports.add(_plus);
      String _packageName_1 = packageInfo.getPackageName();
      String _plus_1 = (_packageName_1 + ".manager.");
      String _name = domainEntity.getName();
      String _plus_2 = (_plus_1 + _name);
      String _plus_3 = (_plus_2 + "Manager");
      imports.add(_plus_3);
      String _packageName_2 = packageInfo.getPackageName();
      String _plus_4 = (_packageName_2 + ".task.*");
      imports.add(_plus_4);
      String _packageName_3 = packageInfo.getPackageName();
      String _plus_5 = (_packageName_3 + ".constants.DBConstants");
      boolean _add = imports.add(_plus_5);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateDomainEntityManagerImplClass(final String templateName, final String packageName, final Set<String> imports, final List<String> interfaces, final String inheritance, final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      Pair<String,String> _pair = new Pair<String,String>("Context", "mContext");
      Pair<String,String> mtCxAttribute = _pair;
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
      String _name = domainEntity.getName();
      String _plus = (_name + "ManagerImpl");
      CharSequence _classDecl = this.javaUtilities.classDecl(_plus, inheritance, interfaces, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateAdditionalAttribute = this.javaUtilities.generateAdditionalAttribute(mtCxAttribute, false, true);
      _builder.append(_generateAdditionalAttribute, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateInterfaceImplMethods = this.generateInterfaceImplMethods(domainEntity);
      _builder.append(_generateInterfaceImplMethods, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateStaticMethods = this.generateStaticMethods(domainEntity);
      _builder.append(_generateStaticMethods, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateInterfaceImplMethods(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = domainEntity.getName();
    CharSequence _constructorDecl = this.constructorDecl(_name);
    _builder.append(_constructorDecl, "");
    _builder.newLineIfNotEmpty();
    String _name_1 = domainEntity.getName();
    CharSequence _entityById = this.getEntityById(_name_1);
    _builder.append(_entityById, "");
    _builder.newLineIfNotEmpty();
    String _name_2 = domainEntity.getName();
    CharSequence _entityById_Async = this.getEntityById_Async(_name_2);
    _builder.append(_entityById_Async, "");
    _builder.newLineIfNotEmpty();
    String _name_3 = domainEntity.getName();
    CharSequence _entityByUri = this.getEntityByUri(_name_3);
    _builder.append(_entityByUri, "");
    _builder.newLineIfNotEmpty();
    String _name_4 = domainEntity.getName();
    CharSequence _entityByUri_Async = this.getEntityByUri_Async(_name_4);
    _builder.append(_entityByUri_Async, "");
    _builder.newLineIfNotEmpty();
    String _name_5 = domainEntity.getName();
    CharSequence _entityByUuid = this.getEntityByUuid(_name_5);
    _builder.append(_entityByUuid, "");
    _builder.newLineIfNotEmpty();
    String _name_6 = domainEntity.getName();
    CharSequence _entityByUuid_Async = this.getEntityByUuid_Async(_name_6);
    _builder.append(_entityByUuid_Async, "");
    _builder.newLineIfNotEmpty();
    String _name_7 = domainEntity.getName();
    CharSequence _allEntities = this.getAllEntities(_name_7);
    _builder.append(_allEntities, "");
    _builder.newLineIfNotEmpty();
    String _name_8 = domainEntity.getName();
    CharSequence _allEntities_Async = this.getAllEntities_Async(_name_8);
    _builder.append(_allEntities_Async, "");
    _builder.newLineIfNotEmpty();
    String _name_9 = domainEntity.getName();
    CharSequence _updateEntity = this.getUpdateEntity(_name_9);
    _builder.append(_updateEntity, "");
    _builder.newLineIfNotEmpty();
    String _name_10 = domainEntity.getName();
    CharSequence _updateEntity_Async = this.getUpdateEntity_Async(_name_10);
    _builder.append(_updateEntity_Async, "");
    _builder.newLineIfNotEmpty();
    String _name_11 = domainEntity.getName();
    CharSequence _createEntity = this.getCreateEntity(_name_11);
    _builder.append(_createEntity, "");
    _builder.newLineIfNotEmpty();
    String _name_12 = domainEntity.getName();
    CharSequence _createEntity_Async = this.getCreateEntity_Async(_name_12);
    _builder.append(_createEntity_Async, "");
    _builder.newLineIfNotEmpty();
    String _name_13 = domainEntity.getName();
    CharSequence _deleteEntity = this.getDeleteEntity(_name_13);
    _builder.append(_deleteEntity, "");
    _builder.newLineIfNotEmpty();
    String _name_14 = domainEntity.getName();
    CharSequence _deleteEntity_Async = this.getDeleteEntity_Async(_name_14);
    _builder.append(_deleteEntity_Async, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence constructorDecl(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public  ");
    _builder.append(domainEntityName, "");
    _builder.append("ManagerImpl(final Context mContext) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (mContext == null) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("throw new IllegalArgumentException(\"Context shouldn\'t be null!\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.mContext = mContext;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getEntityById(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(entityName, "");
    _builder.append(" getById(final Long ");
    _builder.append(entityName, "");
    _builder.append("ID) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (");
    _builder.append(entityName, "	");
    _builder.append("ID == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("throw new IllegalArgumentException(\"");
    _builder.append(entityName, "		");
    _builder.append("ID shouldn\'t be null!\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("final Uri uri = ContentUris.withAppendedId(DBConstants.CONTENT_URI_");
    String _upperCase = entityName.toUpperCase();
    _builder.append(_upperCase, "	");
    _builder.append(", ");
    _builder.append(entityName, "	");
    _builder.append("ID);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return getByUri(uri);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getEntityById_Async(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void getById(final Long ");
    _builder.append(entityName, "");
    _builder.append("ID, final AsyncCallback<");
    _builder.append(entityName, "");
    _builder.append("> callback) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("if (");
    _builder.append(entityName, "    ");
    _builder.append("ID == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"");
    _builder.append(entityName, "        ");
    _builder.append("ID shouldn\'t be null!\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("final Uri uri = ContentUris.withAppendedId(DBConstants.CONTENT_URI_");
    String _upperCase = entityName.toUpperCase();
    _builder.append(_upperCase, "    ");
    _builder.append(", ");
    _builder.append(entityName, "    ");
    _builder.append("ID);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("getByUri(uri, callback);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getEntityByUri(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(entityName, "");
    _builder.append(" getByUri(final Uri uri) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (uri == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new IllegalArgumentException(\"uri cannot be null!\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Get");
    _builder.append(entityName, "	");
    _builder.append("ByUri task = new Get");
    _builder.append(entityName, "	");
    _builder.append("ByUri(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return task.get");
    _builder.append(entityName, "	");
    _builder.append("ByUri(uri);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getEntityByUri_Async(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void getByUri(final Uri uri, final AsyncCallback<");
    _builder.append(entityName, "");
    _builder.append("> callback) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("if (uri == null) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"uri cannot be null!\");");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Get");
    _builder.append(entityName, "    ");
    _builder.append("ByUri task = new Get");
    _builder.append(entityName, "    ");
    _builder.append("ByUri(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("task.get");
    _builder.append(entityName, "    ");
    _builder.append("ByUriAsync(uri, callback);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getEntityByUuid(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(entityName, "");
    _builder.append(" getByUuid(final String uuid) {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (uuid == null) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"");
    _builder.append(entityName, "        ");
    _builder.append("ID shouldn\'t be null!\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Get");
    _builder.append(entityName, "    ");
    _builder.append("ByUuid task = new Get");
    _builder.append(entityName, "    ");
    _builder.append("ByUuid(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("return task.get");
    _builder.append(entityName, "    ");
    _builder.append("ByUuid(uuid);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getEntityByUuid_Async(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void getByUuid(final String uuid, final AsyncCallback<");
    _builder.append(entityName, "");
    _builder.append("> callback) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("if (uuid == null) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"");
    _builder.append(entityName, "        ");
    _builder.append("ID shouldn\'t be null!\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Get");
    _builder.append(entityName, "    ");
    _builder.append("ByUuid task = new Get");
    _builder.append(entityName, "    ");
    _builder.append("ByUuid(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("task.get");
    _builder.append(entityName, "    ");
    _builder.append("ByUuidAsync(uuid, callback);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getAllEntities(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Collection<");
    _builder.append(entityName, "");
    _builder.append("> getAll() {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("GetAll");
    _builder.append(entityName, "    ");
    _builder.append("s task = new GetAll");
    _builder.append(entityName, "    ");
    _builder.append("s(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("return task.getAll");
    _builder.append(entityName, "    ");
    _builder.append("s();");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getAllEntities_Async(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void getAll(final AsyncCallback<Collection<");
    _builder.append(entityName, "");
    _builder.append(">> callback) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("GetAll");
    _builder.append(entityName, "    ");
    _builder.append("s task = new GetAll");
    _builder.append(entityName, "    ");
    _builder.append("s(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("task.getAll");
    _builder.append(entityName, "    ");
    _builder.append("sAsync(callback);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getUpdateEntity(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public ");
    _builder.append(entityName, "");
    _builder.append(" update(");
    _builder.append(entityName, "");
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower, "");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("Update");
    _builder.append(entityName, "    ");
    _builder.append(" task = new Update");
    _builder.append(entityName, "    ");
    _builder.append("(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("return task.update");
    _builder.append(entityName, "    ");
    _builder.append("(");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1, "    ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getUpdateEntity_Async(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void update(final ");
    _builder.append(entityName, "");
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower, "");
    _builder.append(", final AsyncCallback<");
    _builder.append(entityName, "");
    _builder.append("> callback) {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("Update");
    _builder.append(entityName, "   ");
    _builder.append(" task = new Update");
    _builder.append(entityName, "   ");
    _builder.append("(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("task.update");
    _builder.append(entityName, "   ");
    _builder.append("Async(");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1, "   ");
    _builder.append(", callback);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getCreateEntity(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Uri create(final ");
    _builder.append(entityName, "");
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower, "");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("if (");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1, "    ");
    _builder.append(" == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"");
    String _firstLower_2 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_2, "        ");
    _builder.append(" shouldn\'t be null!\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Create");
    _builder.append(entityName, "    ");
    _builder.append(" task = new Create");
    _builder.append(entityName, "    ");
    _builder.append("(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("return task.create");
    _builder.append(entityName, "    ");
    _builder.append("(");
    String _firstLower_3 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_3, "    ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getCreateEntity_Async(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void create(final ");
    _builder.append(entityName, "");
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower, "");
    _builder.append(", final AsyncCallback<Uri> callback) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("Create");
    _builder.append(entityName, "    ");
    _builder.append(" task = new Create");
    _builder.append(entityName, "    ");
    _builder.append("(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("task.create");
    _builder.append(entityName, "    ");
    _builder.append("Async(");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1, "    ");
    _builder.append(", callback);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getDeleteEntity(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public boolean delete(final ");
    _builder.append(entityName, "");
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower, "");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("// input validation");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1, "    ");
    _builder.append(" == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"");
    _builder.append(entityName, "        ");
    _builder.append(" to be deleted cannot be null\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (");
    String _firstLower_2 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_2, "    ");
    _builder.append(".getId() == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"Id is null! Only a persisted ");
    _builder.append(entityName, "        ");
    _builder.append(" can be deleted.\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Delete");
    _builder.append(entityName, "    ");
    _builder.append(" task = new Delete");
    _builder.append(entityName, "    ");
    _builder.append("(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("return task.delete");
    _builder.append(entityName, "    ");
    _builder.append("(");
    String _firstLower_3 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_3, "    ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getDeleteEntity_Async(final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void delete(final ");
    _builder.append(entityName, "");
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower, "");
    _builder.append(", final AsyncCallback<Boolean> callback) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("// input validation");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (");
    String _firstLower_1 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_1, "    ");
    _builder.append(" == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"");
    _builder.append(entityName, "        ");
    _builder.append(" to be deleted cannot be null\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("if (");
    String _firstLower_2 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_2, "    ");
    _builder.append(".getId() == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("throw new IllegalArgumentException(\"Id is null! Only a persisted ");
    _builder.append(entityName, "        ");
    _builder.append(" can be deleted.\");");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Delete");
    _builder.append(entityName, "    ");
    _builder.append(" task = new Delete");
    _builder.append(entityName, "    ");
    _builder.append("(mContext);");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("task.delete");
    _builder.append(entityName, "    ");
    _builder.append("Async(");
    String _firstLower_3 = StringExtensions.toFirstLower(entityName);
    _builder.append(_firstLower_3, "    ");
    _builder.append(", callback);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateStaticMethods(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t    ");
    _builder.newLine();
    return _builder;
  }
}
