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
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import info.multiplatform.generator.java.helper.Pair;
import info.multiplatform.generator.java.helper.Triple;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class SyncManagerTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateSyncManager(final String packagePrefix, final MobileApp m, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "manager/SyncManager.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "manager");
    CharSequence _generateSyncManagercode = this.generateSyncManagercode(_plus_1, m);
    fsa.generateFile(_plus, outputConfiguration, _generateSyncManagercode);
  }
  
  public void generateSyncManagerFactory(final String packagePrefix, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "manager/SyncManagerFactory.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "manager");
    CharSequence _generateSyncManagerFactoryCode = this.generateSyncManagerFactoryCode(_plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateSyncManagerFactoryCode);
  }
  
  public void generateSyncManagerFactoryImpl(final String packagePrefix, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "manager/impl/SyncManagerFactoryImpl.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "manager.impl");
    CharSequence _generateSyncManagerFactoryImplCode = this.generateSyncManagerFactoryImplCode(_plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateSyncManagerFactoryImplCode);
  }
  
  /**
   * SyncMangaer methods
   */
  public CharSequence generateSyncManagercode(final String packageName, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      this.getSyncManagerImports(imports, packageName, m);
      StringConcatenation _builder = new StringConcatenation();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateSyncManagerClass = this.generateSyncManagerClass("SyncManagerTemplate", packageName, imports, null, _arrayList, m);
      _builder.append(_generateSyncManagerClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public void getSyncManagerImports(final Set<String> imports, final String packageName, final MobileApp m) {
    imports.add("android.accounts.NetworkErrorException");
    imports.add("android.content.Context");
    String _replace = packageName.replace("manager", "rest.");
    String _plus = (_replace + "RestClient");
    imports.add(_plus);
    List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(m);
    for (final EClass domainEntity : _allDomainEntities) {
      String _replace_1 = packageName.replace("manager", "sync.");
      String _plus_1 = (_replace_1 + "Sync");
      String _name = domainEntity.getName();
      String _plus_2 = (_plus_1 + _name);
      imports.add(_plus_2);
    }
  }
  
  public CharSequence generateSyncManagerClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Pair<String,String>> _arrayList = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> attributes = _arrayList;
      Pair<String,String> _pair = new Pair<String,String>("Context", "mCtx");
      attributes.add(_pair);
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
      CharSequence _classDecl = this.javaUtilities.classDecl("SyncManager", inheritance, interfaces, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateAdditionalAttributes = this.javaUtilities.generateAdditionalAttributes(attributes);
      _builder.append(_generateAdditionalAttributes, "");
      _builder.newLineIfNotEmpty();
      CharSequence _syncManagerConstructor = this.syncManagerConstructor();
      _builder.append(_syncManagerConstructor, "");
      _builder.newLineIfNotEmpty();
      CharSequence _executeAllSync = this.executeAllSync(m);
      _builder.append(_executeAllSync, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence syncManagerConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public SyncManager(final Context ctx) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (ctx == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new IllegalArgumentException(\"Context ctx shouldnt be null!\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("mCtx = ctx;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence executeAllSync(final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Handles all sync\'ing tasks from one place");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @throws NetworkErrorException");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*            If a problem happens");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void executeAllSync() throws NetworkErrorException {");
    _builder.newLine();
    _builder.append("RestClient rc = new RestClient(mCtx);");
    _builder.newLine();
    {
      List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(m);
      for(final EClass domainEntity : _allDomainEntities) {
        _builder.append("Sync");
        String _name = domainEntity.getName();
        _builder.append(_name, "");
        _builder.append(" sync");
        String _name_1 = domainEntity.getName();
        _builder.append(_name_1, "");
        _builder.append(" = new Sync");
        String _name_2 = domainEntity.getName();
        _builder.append(_name_2, "");
        _builder.append("(mCtx, rc.get");
        String _name_3 = domainEntity.getName();
        _builder.append(_name_3, "");
        _builder.append("RestClient());");
        _builder.newLineIfNotEmpty();
        _builder.append("sync");
        String _name_4 = domainEntity.getName();
        _builder.append(_name_4, "");
        _builder.append(".sync();");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * SyncManagerFactory methods
   */
  public CharSequence generateSyncManagerFactoryCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      imports.add("android.content.Context");
      StringConcatenation _builder = new StringConcatenation();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateSyncManagerFactoryClass = this.generateSyncManagerFactoryClass("SyncManagerTemplate", packageName, imports, null, _arrayList);
      _builder.append(_generateSyncManagerFactoryClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateSyncManagerFactoryClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
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
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl("SyncManagerFactory", inheritance);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public SyncManager createSyncManager(Context context);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * SyncManagerFactoryImpl methods
   */
  public CharSequence generateSyncManagerFactoryImplCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> interfaces = _arrayList;
      this.getSyncMangerFactoryImplImports(imports, packageName);
      interfaces.add("SyncManagerFactory");
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateSyncManagerFactoryImplClass = this.generateSyncManagerFactoryImplClass("SyncManagerTemplate", packageName, imports, null, interfaces);
      _builder.append(_generateSyncManagerFactoryImplClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateSyncManagerFactoryImplClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
    CharSequence _xblockexpression = null;
    {
      Triple<String,String,String> _triple = new Triple<String,String,String>("SyncManagerFactory", "instance", "new SyncManagerFactoryImpl()");
      Triple<String,String,String> attribute = _triple;
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
      CharSequence _classDecl = this.javaUtilities.classDecl("SyncManagerFactoryImpl", inheritance, interfaces, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(attribute, true, true);
      _builder.append(_generateAdditionalAttributesWithExpression, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateSynManagerFactoryImplMethods = this.generateSynManagerFactoryImplMethods();
      _builder.append(_generateSynManagerFactoryImplMethods, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public boolean getSyncMangerFactoryImplImports(final Set<String> imports, final String packageName) {
    boolean _xblockexpression = false;
    {
      imports.add("android.content.Context");
      String _replace = packageName.replace("impl", "");
      String _plus = (_replace + "SyncManager");
      imports.add(_plus);
      String _replace_1 = packageName.replace("impl", "");
      String _plus_1 = (_replace_1 + "SyncManagerFactory");
      boolean _add = imports.add(_plus_1);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateSynManagerFactoryImplMethods() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _instanceInSyncManagerFactoryImpl = this.getInstanceInSyncManagerFactoryImpl();
    _builder.append(_instanceInSyncManagerFactoryImpl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _createSyncManagerInSyncManagerFactoryImpl = this.createSyncManagerInSyncManagerFactoryImpl();
    _builder.append(_createSyncManagerInSyncManagerFactoryImpl, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence getInstanceInSyncManagerFactoryImpl() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static SyncManagerFactory getInstance(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return instance;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createSyncManagerInSyncManagerFactoryImpl() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public SyncManager createSyncManager(Context context) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return new SyncManager(context);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
