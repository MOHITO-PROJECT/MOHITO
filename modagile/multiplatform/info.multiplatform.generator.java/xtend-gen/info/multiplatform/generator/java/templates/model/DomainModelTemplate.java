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
package info.multiplatform.generator.java.templates.model;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import info.multiplatform.generator.java.templates.JavaFolderConstants;
import info.multiplatform.generator.java.templates.JavaUtils;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Generator template for common java domain model classes.
 * 
 * The template is self contained and can be executed as it is.
 * In addition, it provides extension points by overloading the template
 * methods explicitly declared with protected access modifiers.
 */
@SuppressWarnings("all")
public class DomainModelTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  /**
   * Generates an entity class for the given domainEntity
   * @param fsa The file system access instance two generate the code output.
   * @param packageInfo The infos for the base package and code directory to work with
   * @param domainModelSubPackage The sub package place the domain model code in (should start with '.').
   * @param domainModelSubDirectory The sub directory of the source gen folder two write the code files to (should end with /).
   */
  public void generateDomainEntity(final IFileSystemAccess fsa, final EClass domainEntity, final PackageInfo packageInfo, final String domainModelSubPackage, final String domainModelSubDirectory) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + domainModelSubDirectory);
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + ".java");
    CharSequence _generateDomainEntityModelCode = this.generateDomainEntityModelCode(packageInfo, domainEntity, domainModelSubPackage);
    fsa.generateFile(_plus_2, JavaFolderConstants.SRC_GEN, _generateDomainEntityModelCode);
  }
  
  /**
   * DomainEntity methods
   */
  private CharSequence generateDomainEntityModelCode(final PackageInfo packageInfo, final EClass domainEntity, final String domainModelSubPackage) {
    CharSequence _xblockexpression = null;
    {
      String inheritance = this.getSuperClass(packageInfo, domainEntity);
      List<String> interfaces = this.getInterfaces(packageInfo, domainEntity);
      Set<String> imports = this.getImports(packageInfo, domainEntity);
      StringConcatenation _builder = new StringConcatenation();
      EList<EClass> _eSuperTypes = domainEntity.getESuperTypes();
      EClass _get = _eSuperTypes.get(0);
      String _name = _get.getName();
      boolean _notEquals = (!Objects.equal(_name, ""));
      CharSequence _generateDomainEntityModelClass = this.generateDomainEntityModelClass("DomainModelTemplate", packageInfo, imports, inheritance, interfaces, domainEntity, domainModelSubPackage, Boolean.valueOf(_notEquals));
      _builder.append(_generateDomainEntityModelClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateDomainEntityModelClass(final String templateName, final PackageInfo packageInfo, final Set<String> imports, final String inheritance, final List<String> interfaces, final EClass domainEntity, final String domainModelSubPackage, final Boolean inherited) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    String _packageName = packageInfo.getPackageName();
    String _plus = (_packageName + domainModelSubPackage);
    CharSequence _packageStatement = this.javaUtilities.packageStatement(_plus);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    Object _generateClassAnnotations = this.generateClassAnnotations(packageInfo, domainEntity);
    _builder.append(_generateClassAnnotations, "");
    _builder.newLineIfNotEmpty();
    String _name = domainEntity.getName();
    CharSequence _classDecl = this.javaUtilities.classDecl(_name, inheritance, interfaces, (inherited).booleanValue());
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    EList<EAttribute> _eAttributes = domainEntity.getEAttributes();
    CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl(templateName, _eAttributes, inherited);
    _builder.append(_generateConstructorDecl, "");
    _builder.newLineIfNotEmpty();
    Object _generateAdditionalFields = this.generateAdditionalFields(packageInfo, domainEntity);
    _builder.append(_generateAdditionalFields, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateCodeForClassifier = this.generateCodeForClassifier(domainEntity);
    _builder.append(_generateCodeForClassifier, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Get the imports for the domain model.
   */
  private Set<String> getImports(final PackageInfo packageInfo, final EClass domainEntity) {
    HashSet<String> _hashSet = new HashSet<String>();
    Set<String> imports = _hashSet;
    EList<EAttribute> _eAttributes = domainEntity.getEAttributes();
    boolean _hasDate = this.hasDate(_eAttributes);
    if (_hasDate) {
      imports.add("java.util.Date");
    }
    imports.add("java.util.List");
    List<String> _additionalImports = this.getAdditionalImports(packageInfo, domainEntity);
    imports.addAll(_additionalImports);
    return imports;
  }
  
  /**
   * Generate the body of a class with field declarations
   * and getters and setters.
   */
  public CharSequence generateCodeForClassifier(final EClass clazz) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _notEquals = (!Objects.equal(clazz, null));
      if (_notEquals) {
        ArrayList<EAttribute> _arrayList = new ArrayList<EAttribute>();
        List<EAttribute> attsToGenerateGettersFor = _arrayList;
        _builder.newLineIfNotEmpty();
        {
          EList<EAttribute> _eAttributes = clazz.getEAttributes();
          for(final EAttribute att : _eAttributes) {
            Object _generateAttributeAnnotations = this.generateAttributeAnnotations(clazz, att);
            _builder.append(_generateAttributeAnnotations, "");
            _builder.newLineIfNotEmpty();
            CharSequence _generateCodeForAttributeOrReference = this.javaUtilities.generateCodeForAttributeOrReference(att);
            _builder.append(_generateCodeForAttributeOrReference, "");
            _builder.newLineIfNotEmpty();
            boolean bugHelperVariable = attsToGenerateGettersFor.add(att);
            _builder.newLineIfNotEmpty();
          }
        }
        {
          for(final EAttribute attr : attsToGenerateGettersFor) {
            CharSequence _generateGetterSetter = this.javaUtilities.generateGetterSetter(attr);
            _builder.append(_generateGetterSetter, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          EList<EReference> _eReferences = clazz.getEReferences();
          for(final EReference ref : _eReferences) {
            CharSequence _generateCodeForAttributeOrReference_1 = this.javaUtilities.generateCodeForAttributeOrReference(ref);
            _builder.append(_generateCodeForAttributeOrReference_1, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          EList<EReference> _eReferences_1 = clazz.getEReferences();
          for(final EReference ref_1 : _eReferences_1) {
            CharSequence _generateGetterSetter_1 = this.javaUtilities.generateGetterSetter(ref_1);
            _builder.append(_generateGetterSetter_1, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public boolean hasDate(final EList<EAttribute> attributes) {
    final Function1<EAttribute,Boolean> _function = new Function1<EAttribute,Boolean>() {
        public Boolean apply(final EAttribute attribute) {
          EClassifier _eType = attribute.getEType();
          String _instanceClassName = _eType.getInstanceClassName();
          boolean _equals = _instanceClassName.equals("java.util.Date");
          boolean _not = (!_equals);
          return Boolean.valueOf(_not);
        }
      };
    boolean _forall = IterableExtensions.<EAttribute>forall(attributes, _function);
    boolean _not = (!_forall);
    return _not;
  }
  
  /**
   * Get the imports for the domain model.
   */
  protected List<String> getAdditionalImports(final PackageInfo packageInfo, final EClass domainEntity) {
    ArrayList<String> _arrayList = new ArrayList<String>();
    return _arrayList;
  }
  
  /**
   * Get the interfaces the domain model class should implement.
   * @return The names of the interfaces only without any control characters.
   */
  protected List<String> getInterfaces(final PackageInfo packageInfo, final EClass domainEntity) {
    ArrayList<String> _arrayList = new ArrayList<String>();
    return _arrayList;
  }
  
  /**
   * Get the super class the domain model class should extend.
   * @return The name of the class to be extended. Without any code characters such as ; or "extends".
   */
  protected String getSuperClass(final PackageInfo packageInfo, final EClass domainEntity) {
    EList<EClass> _eSuperTypes = domainEntity.getESuperTypes();
    EClass _get = _eSuperTypes.get(0);
    return _get.getName();
  }
  
  /**
   * Generate any annotations to be attached to the class.
   * The method should directly generate the code.
   */
  protected Object generateClassAnnotations(final PackageInfo packageInfo, final EClass domainEntity) {
    return null;
  }
  
  /**
   * Generate the annotations to be attached to an attribute.
   * The method should directly generate the code.
   */
  protected Object generateAttributeAnnotations(final EClass domainEntity, final EAttribute att) {
    return null;
  }
  
  /**
   * Generate additional fields to the domain model class which are not related
   * to the classes attributes or references.
   * The method should directly generate the code.
   */
  protected Object generateAdditionalFields(final PackageInfo packageInfo, final EClass domainEntity) {
    return null;
  }
}
