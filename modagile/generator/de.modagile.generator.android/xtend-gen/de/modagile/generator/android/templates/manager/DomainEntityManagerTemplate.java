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
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class DomainEntityManagerTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateDomainEntityManager(final IFileSystemAccess fsa, final EClass domainEntity, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "manager/");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "Manager.java");
    CharSequence _generateDomainEntityManagerCode = this.generateDomainEntityManagerCode(packageInfo, domainEntity);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateDomainEntityManagerCode);
  }
  
  /**
   * DomainEntityManger Interfaces
   */
  private CharSequence generateDomainEntityManagerCode(final PackageInfo packageInfo, final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      String _name = domainEntity.getName();
      String _plus = ("EntityManager<" + _name);
      String inheritence = (_plus + ">");
      String _packageName = packageInfo.getPackageName();
      this.getImports(imports, _packageName, domainEntity);
      StringConcatenation _builder = new StringConcatenation();
      String _packageName_1 = packageInfo.getPackageName();
      String _plus_1 = (_packageName_1 + ".manager");
      CharSequence _generateDomainEntityManagerClass = this.generateDomainEntityManagerClass(_plus_1, imports, inheritence, domainEntity);
      _builder.append(_generateDomainEntityManagerClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private boolean getImports(final Set<String> imports, final String packageName, final EClass domainEntity) {
    boolean _xblockexpression = false;
    {
      imports.add("java.util.Collection");
      imports.add("java.util.List");
      imports.add("java.util.List");
      String _plus = (packageName + ".model.");
      String _name = domainEntity.getName();
      String _plus_1 = (_plus + _name);
      boolean _add = imports.add(_plus_1);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateDomainEntityManagerClass(final String packageName, final Set<String> imports, final String inheritance, final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    Class<? extends DomainEntityManagerTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    String _name = domainEntity.getName();
    String _plus = (_name + "Manager");
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl(_plus, inheritance);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    Object _generateInterfaceMethods = this.generateInterfaceMethods(domainEntity);
    _builder.append(_generateInterfaceMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private Object generateInterfaceMethods(final EClass domainEntity) {
    return null;
  }
}
