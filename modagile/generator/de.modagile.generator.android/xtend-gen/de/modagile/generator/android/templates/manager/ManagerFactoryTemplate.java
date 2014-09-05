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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class ManagerFactoryTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  /**
   * Creates the Interface ManagerFactory
   */
  public void generateManagerFactory(final String packagePrefix, final MobileApp m, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "manager/ManagerFactory.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "manager");
    CharSequence _generateManagerFactoryCode = this.generateManagerFactoryCode(_plus_1, m);
    fsa.generateFile(_plus, outputConfiguration, _generateManagerFactoryCode);
  }
  
  public void generateManagerFactoryImpl(final String packagePrefix, final MobileApp m, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "manager/impl/ManagerFactoryImpl.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "manager.impl");
    CharSequence _generateManagerFactoryImplCode = this.generateManagerFactoryImplCode(_plus_1, m);
    fsa.generateFile(_plus, outputConfiguration, _generateManagerFactoryImplCode);
  }
  
  /**
   * ManagerFactory Methods
   */
  public CharSequence generateManagerFactoryCode(final String packageName, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      this.getImports(imports);
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateManagerFactoryClass = this.generateManagerFactoryClass("ManagerFactoryTemplate", packageName, imports, null, m);
      _builder.append(_generateManagerFactoryClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public boolean getImports(final Set<String> imports) {
    boolean _xblockexpression = false;
    {
      imports.add("java.io.Serializable");
      imports.add("java.util.Collection");
      imports.add("android.net.Uri");
      boolean _add = imports.add("android.content.Context");
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateManagerFactoryClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final MobileApp m) {
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
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl("ManagerFactory", inheritance);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateInterfaceMethods = this.generateInterfaceMethods(m);
    _builder.append(_generateInterfaceMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateInterfaceMethods(final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    {
      List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(m);
      for(final EClass domainEntity : _allDomainEntities) {
        _builder.append("public ");
        String _name = domainEntity.getName();
        _builder.append(_name, "");
        _builder.append("Manager create");
        String _name_1 = domainEntity.getName();
        _builder.append(_name_1, "");
        _builder.append("Manager(Context ctx);");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  /**
   * ManagerFactoryImpl Methods
   */
  public CharSequence generateManagerFactoryImplCode(final String packageName, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> interfaces = _arrayList;
      this.getManagerFactoryImplImports(imports, packageName, m);
      interfaces.add("ManagerFactory");
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateManagerFactoryImplClass = this.generateManagerFactoryImplClass("ManagerFactoryTemplate", packageName, imports, null, interfaces, m);
      _builder.append(_generateManagerFactoryImplClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public void getManagerFactoryImplImports(final Set<String> imports, final String packageName, final MobileApp m) {
    imports.add("android.content.Context");
    List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(m);
    for (final EClass domainEntity : _allDomainEntities) {
      String _replace = packageName.replace("impl", "");
      String _name = domainEntity.getName();
      String _plus = (_replace + _name);
      String _plus_1 = (_plus + "Manager");
      imports.add(_plus_1);
    }
  }
  
  public CharSequence generateManagerFactoryImplClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces, final MobileApp m) {
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
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl("ManagerFactoryImpl", inheritance);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateInterfaceMethods = this.generateInterfaceMethods(m);
    _builder.append(_generateInterfaceMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateInterfaceImplementationMethods(final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    {
      List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(m);
      for(final EClass domainEntity : _allDomainEntities) {
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("public ");
        String _name = domainEntity.getName();
        _builder.append(_name, "");
        _builder.append("Manager create");
        String _name_1 = domainEntity.getName();
        _builder.append(_name_1, "");
        _builder.append("Manager(Context ctx){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return new ");
        String _name_2 = domainEntity.getName();
        _builder.append(_name_2, "	");
        _builder.append("ManagerImpl(ctx);");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
}
