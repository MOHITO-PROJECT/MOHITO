/**
 * Copyright (c) 2012-2014 MOHITO Project
 * 
 * Licensed under the EUPL V.1.1
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package info.multiplatform.mohito.generator.environment.java;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import info.multiplatform.generator.java.templates.JavaFolderConstants;
import info.multiplatform.mohito.generator.IStorageGenerator;
import info.multiplatform.mohito.generator.environment.java.JavaUtils;
import info.multiplatform.mohito.generator.environment.java.MohitoJavaFolderConstants;
import info.multiplatform.mohito.generator.environment.java.PackageInfo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Generator template for common java domain model classes.
 * 
 * The template is self contained and can be executed as it is.
 * In addition, it provides extension points by overloading the template
 * methods explicitly declared with protected access modifiers.
 */
@SuppressWarnings("all")
public class DomainModelTemplate {
  /**
   * Textual identifier for the injection of the local storage generator. Required for target selection within dependency injection. @see http://code.google.com/p/google-guice/wiki/BindingAnnotations
   */
  public final static String NAME_INJECTOR_LOCAL = "Local";
  
  /**
   * Textual identifier for the injection of the remote storage generator. Required for target selection within dependency injection. @see http://code.google.com/p/google-guice/wiki/BindingAnnotations
   */
  public final static String NAME_INJECTOR_REMOTE = "Remote";
  
  /**
   * Utilities for generating Java code.
   */
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  /**
   * The template for generation of artifacts for the local storage. Can be <code>null</code> if no local storage is used.
   */
  @Inject
  @Named(DomainModelTemplate.NAME_INJECTOR_LOCAL)
  @Extension
  private IStorageGenerator localStorageGenerator;
  
  /**
   * The template for generation of artifacts for the remote storage. Can be <code>null</code> if no remote storage is used.
   */
  @Inject
  @Named(DomainModelTemplate.NAME_INJECTOR_REMOTE)
  @Extension
  private IStorageGenerator remoteStorageGenerator;
  
  /**
   * Generates the artifacts for the provided domain entity.
   * @param fsa Access to the files system for generating files.
   * @param domainModel The domain model.
   * @param packageInfo Information on the target package for generation.
   */
  public void generateDomainEntityArtifacts(final IFileSystemAccess fsa, final EPackage domainModel, final PackageInfo packageInfo) {
    String _name = domainModel.getName();
    this.javaUtilities.ensureJavaCompatibleName(_name);
    PackageInfo _append = packageInfo.append(MohitoJavaFolderConstants.MODEL_LOCAL);
    this.localStorageGenerator.generateAdditionalDomainModelArtifacts(fsa, _append, domainModel);
    PackageInfo _append_1 = packageInfo.append(MohitoJavaFolderConstants.MODEL_REMOTE);
    this.remoteStorageGenerator.generateAdditionalDomainModelArtifacts(fsa, _append_1, domainModel);
    this.generateMohitoEntityClassFile(fsa, domainModel, packageInfo);
    EList<EClassifier> _eClassifiers = domainModel.getEClassifiers();
    final Procedure1<EClassifier> _function = new Procedure1<EClassifier>() {
        public void apply(final EClassifier entity) {
          String _name = entity.getName();
          DomainModelTemplate.this.javaUtilities.ensureJavaCompatibleName(_name);
          boolean _matched = false;
          if (!_matched) {
            if (entity instanceof EEnum) {
              final EEnum _eEnum = (EEnum)entity;
              _matched=true;
              DomainModelTemplate.this.generateEnumEntityFile(fsa, packageInfo, _eEnum, domainModel);
            }
          }
          if (!_matched) {
            if (entity instanceof EClass) {
              final EClass _eClass = (EClass)entity;
              _matched=true;
              DomainModelTemplate.this.generateDomainEntityFile(fsa, packageInfo, _eClass, domainModel);
              PackageInfo _append = packageInfo.append(MohitoJavaFolderConstants.MODEL_LOCAL);
              DomainModelTemplate.this.localStorageGenerator.generateAdditionalDomainEntityArtifacts(fsa, _append, _eClass, domainModel);
              PackageInfo _append_1 = packageInfo.append(MohitoJavaFolderConstants.MODEL_REMOTE);
              DomainModelTemplate.this.remoteStorageGenerator.generateAdditionalDomainEntityArtifacts(fsa, _append_1, _eClass, domainModel);
            }
          }
        }
      };
    IterableExtensions.<EClassifier>forEach(_eClassifiers, _function);
  }
  
  /**
   * Generates the file for the enumeration implementing the domain entity.
   * @param fsa Access to the files system for generating files.
   * @param packageInfo Information on the target package for generation.
   * @param enumEntity The domain entity.
   * @param domainModel The domain model.
   * @see #generateEnumEntityImplementation
   */
  private void generateEnumEntityFile(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EEnum enumEntity, final EPackage domainModel) {
    String _packageDir = packageInfo.getPackageDir();
    String _name = enumEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = (_packageDir + _firstUpper);
    String _plus_1 = (_plus + ".java");
    CharSequence _generateEnumEntityImplementation = this.generateEnumEntityImplementation(enumEntity, packageInfo, domainModel);
    fsa.generateFile(_plus_1, 
      JavaFolderConstants.SRC_GEN, _generateEnumEntityImplementation);
  }
  
  /**
   * Generates the implementation of the enumeration representing a domain entity.
   * @param enumEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   * @param domainModel The domain model.
   */
  private CharSequence generateEnumEntityImplementation(final EEnum enumEntity, final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Model entity ");
    String _name = enumEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, " ");
    _builder.append(" of ");
    String _name_1 = domainModel.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper_1, " ");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public enum ");
    String _name_2 = enumEntity.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_2, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    {
      EList<EEnumLiteral> _eLiterals = enumEntity.getELiterals();
      boolean _hasElements = false;
      for(final EEnumLiteral enumLiteral : _eLiterals) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "	");
        }
        _builder.append("\t");
        String _name_3 = enumLiteral.getName();
        String _upperCase = _name_3.toUpperCase();
        _builder.append(_upperCase, "	");
        _builder.append("(");
        int _value = enumLiteral.getValue();
        _builder.append(_value, "	");
        _builder.append(")");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
      }
    }
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**The value of the literal. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private int value;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Create a new literal of the enumeration.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param value The value for this literal.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private ");
    String _name_4 = enumEntity.getName();
    String _firstUpper_3 = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper_3, "	");
    _builder.append("(int value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.value = value;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Gets the value for a literal.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @return The value.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public int getValue(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return value;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates the file for the class implementing the domain entity.
   * @param fsa Access to the files system for generating files.
   * @param domainModel The domain model.
   * @param domainEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   * @see #generateDomainEntityImplementation
   */
  private void generateDomainEntityFile(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel) {
    String _packageDir = packageInfo.getPackageDir();
    String _name = domainEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = (_packageDir + _firstUpper);
    String _plus_1 = (_plus + ".java");
    CharSequence _generateDomainEntityImplementation = this.generateDomainEntityImplementation(domainEntity, packageInfo, domainModel);
    fsa.generateFile(_plus_1, 
      JavaFolderConstants.SRC_GEN, _generateDomainEntityImplementation);
  }
  
  /**
   * Generates the implementation of the class representing a domain entity.
   * @param domainModel The domain model.
   * @param domainEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   * @see #generateDomainEntityFile
   */
  private CharSequence generateDomainEntityImplementation(final EClass domainEntity, final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    Set<String> _calculateImports = this.calculateImports(packageInfo, domainEntity, domainModel);
    CharSequence _statementImports = this.javaUtilities.statementImports(_calculateImports);
    _builder.append(_statementImports, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDomainEntityImportStatements = this.localStorageGenerator.generateDomainEntityImportStatements(packageInfo, domainEntity, domainModel);
    _builder.append(_generateDomainEntityImportStatements, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDomainEntityImportStatements_1 = this.remoteStorageGenerator.generateDomainEntityImportStatements(packageInfo, domainEntity, domainModel);
    _builder.append(_generateDomainEntityImportStatements_1, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**Model entity ");
    String _name = domainEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append(" of ");
    String _name_1 = domainModel.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper_1, "");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    Class<? extends DomainModelTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    CharSequence _generateDomainEntityClassAnnotationStatement = this.localStorageGenerator.generateDomainEntityClassAnnotationStatement(packageInfo, domainEntity, domainModel);
    _builder.append(_generateDomainEntityClassAnnotationStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDomainEntityClassAnnotationStatement_1 = this.remoteStorageGenerator.generateDomainEntityClassAnnotationStatement(packageInfo, domainEntity, domainModel);
    _builder.append(_generateDomainEntityClassAnnotationStatement_1, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public ");
    {
      boolean _isAbstract = domainEntity.isAbstract();
      if (_isAbstract) {
        _builder.append(" abstract ");
      }
    }
    _builder.append("class ");
    String _name_2 = domainEntity.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_2, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("extends ");
    {
      EList<EClass> _eSuperTypes = domainEntity.getESuperTypes();
      boolean _isEmpty = _eSuperTypes.isEmpty();
      if (_isEmpty) {
        String _name_3 = domainModel.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_3);
        _builder.append(_firstUpper_3, "		");
        _builder.append("MohitoEntity<");
        String _typeOfDomainEntityIdentifier = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
        _builder.append(_typeOfDomainEntityIdentifier, "		");
        _builder.append(">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("\t\t");
      } else {
        EList<EClass> _eSuperTypes_1 = domainEntity.getESuperTypes();
        final Function1<EClass,Boolean> _function = new Function1<EClass,Boolean>() {
            public Boolean apply(final EClass it) {
              return Boolean.valueOf(true);
            }
          };
        EClass _findFirst = IterableExtensions.<EClass>findFirst(_eSuperTypes_1, _function);
        String _name_4 = _findFirst.getName();
        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_4);
        _builder.append(_firstUpper_4, "		");
      }
    }
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// Attributes");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainEntityAttributesForAttributes = this.generateDomainEntityAttributesForAttributes(domainEntity, packageInfo, domainModel);
    _builder.append(_generateDomainEntityAttributesForAttributes, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// References");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainEntityAttributesForReferences = this.generateDomainEntityAttributesForReferences(domainEntity, packageInfo, domainModel);
    _builder.append(_generateDomainEntityAttributesForReferences, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// Constructors");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainEntityConstructors = this.generateDomainEntityConstructors(domainEntity, packageInfo, domainModel);
    _builder.append(_generateDomainEntityConstructors, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// doCheckProxyAndResolveAssignment and doCheckProxyAndResolveGetReferenceEntity");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainEntityTransparentProxyResolution = this.generateDomainEntityTransparentProxyResolution(domainEntity, packageInfo, domainModel);
    _builder.append(_generateDomainEntityTransparentProxyResolution, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// getter and setter");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainEntityGetterAndSetter = this.generateDomainEntityGetterAndSetter(domainEntity, packageInfo, domainModel);
    _builder.append(_generateDomainEntityGetterAndSetter, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("// domainContentEquals");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainEntityDomainEquals = this.generateDomainEntityDomainEquals(domainEntity, packageInfo, domainModel);
    _builder.append(_generateDomainEntityDomainEquals, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates the attributes for a domain entity.
   * @param domainModel The domain model.
   * @param domainEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   */
  private CharSequence generateDomainEntityAttributesForAttributes(final EClass domainEntity, final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<EClass> _eSuperTypes = domainEntity.getESuperTypes();
      int _size = _eSuperTypes.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        {
          EAttribute _attributeWithAnnotationDatabaseId = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
          boolean _equals_1 = Objects.equal(_attributeWithAnnotationDatabaseId, null);
          if (_equals_1) {
            _builder.append("/** Identifier of this MOHITO-Entity. */");
            _builder.newLine();
            _builder.append("protected ");
            String _typeOfDomainEntityIdentifier = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
            _builder.append(_typeOfDomainEntityIdentifier, "");
            _builder.append(" id;");
            _builder.newLineIfNotEmpty();
          }
        }
        CharSequence _generateAdditionalDomainEntityStatements = this.localStorageGenerator.generateAdditionalDomainEntityStatements(domainEntity);
        _builder.append(_generateAdditionalDomainEntityStatements, "");
        _builder.newLineIfNotEmpty();
        CharSequence _generateAdditionalDomainEntityStatements_1 = this.remoteStorageGenerator.generateAdditionalDomainEntityStatements(domainEntity);
        _builder.append(_generateAdditionalDomainEntityStatements_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<EAttribute> _eAttributes = domainEntity.getEAttributes();
      for(final EAttribute attribute : _eAttributes) {
        CharSequence _generateDomainEntityStructuralFeatureAnnotationStatement = this.localStorageGenerator.generateDomainEntityStructuralFeatureAnnotationStatement(packageInfo, attribute, domainModel);
        _builder.append(_generateDomainEntityStructuralFeatureAnnotationStatement, "");
        _builder.newLineIfNotEmpty();
        CharSequence _generateDomainEntityStructuralFeatureAnnotationStatement_1 = this.remoteStorageGenerator.generateDomainEntityStructuralFeatureAnnotationStatement(packageInfo, attribute, domainModel);
        _builder.append(_generateDomainEntityStructuralFeatureAnnotationStatement_1, "");
        _builder.newLineIfNotEmpty();
        _builder.append("/** Attribute ");
        String _name = attribute.getName();
        String _firstLower = StringExtensions.toFirstLower(_name);
        _builder.append(_firstLower, "");
        _builder.append(". ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        {
          EAttribute _attributeWithAnnotationDatabaseId_1 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
          boolean _equals_2 = Objects.equal(_attributeWithAnnotationDatabaseId_1, attribute);
          if (_equals_2) {
            _builder.append("Identifier of this MOHITO-Entity. ");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t\t");
            {
              String _name_1 = attribute.getName();
              String _lowerCase = _name_1.toLowerCase();
              boolean _equals_3 = _lowerCase.equals("id");
              if (_equals_3) {
                this.ensureAttibuteNameIsNotId(attribute);
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t\t\t\t");
          }
        }
        _builder.append("*/");
        _builder.newLineIfNotEmpty();
        {
          boolean _or = false;
          int _upperBound = attribute.getUpperBound();
          boolean _greaterThan = (_upperBound > 1);
          if (_greaterThan) {
            _or = true;
          } else {
            int _upperBound_1 = attribute.getUpperBound();
            int _minus = (-1);
            boolean _equals_4 = (_upperBound_1 == _minus);
            _or = (_greaterThan || _equals_4);
          }
          if (_or) {
            _builder.append("protected final MohitoList<");
            String _type = this.javaUtilities.getType(attribute);
            _builder.append(_type, "");
            _builder.append("> ");
            String _name_2 = attribute.getName();
            String _firstLower_1 = StringExtensions.toFirstLower(_name_2);
            _builder.append(_firstLower_1, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("protected ");
            String _type_1 = this.javaUtilities.getType(attribute);
            _builder.append(_type_1, "");
            _builder.append(" ");
            String _name_3 = attribute.getName();
            String _firstLower_2 = StringExtensions.toFirstLower(_name_3);
            _builder.append(_firstLower_2, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  /**
   * Ensures that the name of an attribute is not 'id' in order to prevent clashes with the name convention of MOHITO-Entities.
   * @throws IllegalArgumentException
   */
  private void ensureAttibuteNameIsNotId(final EAttribute attribute) {
    EClass _eContainingClass = attribute.getEContainingClass();
    String _name = _eContainingClass.getName();
    String _plus = ("The domain entity " + _name);
    String _plus_1 = (_plus + " must not have an attribute with name \'id\', which is not used as identifier in the database. This is an convention for MOHITO-Entities.");
    IllegalArgumentException _illegalArgumentException = new IllegalArgumentException(_plus_1);
    throw _illegalArgumentException;
  }
  
  /**
   * Generates the references for a domain entity.
   * @param domainModel The domain model.
   * @param domainEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   */
  private CharSequence generateDomainEntityAttributesForReferences(final EClass domainEntity, final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<EReference> _eReferences = domainEntity.getEReferences();
      for(final EReference reference : _eReferences) {
        CharSequence _generateDomainEntityStructuralFeatureAnnotationStatement = this.localStorageGenerator.generateDomainEntityStructuralFeatureAnnotationStatement(packageInfo, reference, domainModel);
        _builder.append(_generateDomainEntityStructuralFeatureAnnotationStatement, "");
        _builder.newLineIfNotEmpty();
        CharSequence _generateDomainEntityStructuralFeatureAnnotationStatement_1 = this.remoteStorageGenerator.generateDomainEntityStructuralFeatureAnnotationStatement(packageInfo, reference, domainModel);
        _builder.append(_generateDomainEntityStructuralFeatureAnnotationStatement_1, "");
        _builder.newLineIfNotEmpty();
        _builder.append("/** ");
        {
          boolean _isContainment = reference.isContainment();
          if (_isContainment) {
            _builder.append("Containment reference.");
          } else {
            _builder.append("Reference");
          }
        }
        _builder.append(" ");
        String _name = reference.getName();
        String _firstLower = StringExtensions.toFirstLower(_name);
        _builder.append(_firstLower, "");
        _builder.append(". */");
        _builder.newLineIfNotEmpty();
        {
          boolean _or = false;
          int _upperBound = reference.getUpperBound();
          boolean _greaterThan = (_upperBound > 1);
          if (_greaterThan) {
            _or = true;
          } else {
            int _upperBound_1 = reference.getUpperBound();
            int _minus = (-1);
            boolean _equals = (_upperBound_1 == _minus);
            _or = (_greaterThan || _equals);
          }
          if (_or) {
            _builder.append("protected final MohitoList<");
            String _type = this.javaUtilities.getType(reference);
            _builder.append(_type, "");
            _builder.append("> ");
            String _name_1 = reference.getName();
            String _firstLower_1 = StringExtensions.toFirstLower(_name_1);
            _builder.append(_firstLower_1, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("protected ");
            String _type_1 = this.javaUtilities.getType(reference);
            _builder.append(_type_1, "");
            _builder.append(" ");
            String _name_2 = reference.getName();
            String _firstLower_2 = StringExtensions.toFirstLower(_name_2);
            _builder.append(_firstLower_2, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("// Incoming unnamed references (reverse of containment references without opposite). ");
    _builder.append("\t\t");
    {
      List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(domainModel);
      List<EReference> _allReferences = this.javaUtilities.getAllReferences(_allDomainEntities);
      final Function1<EReference,Boolean> _function = new Function1<EReference,Boolean>() {
          public Boolean apply(final EReference it) {
            boolean _and = false;
            boolean _and_1 = false;
            boolean _isContainment = it.isContainment();
            boolean _equals = (_isContainment == true);
            if (!_equals) {
              _and_1 = false;
            } else {
              EClassifier _eType = it.getEType();
              boolean _equals_1 = Objects.equal(_eType, domainEntity);
              _and_1 = (_equals && _equals_1);
            }
            if (!_and_1) {
              _and = false;
            } else {
              EReference _eOpposite = it.getEOpposite();
              boolean _equals_2 = Objects.equal(_eOpposite, null);
              _and = (_and_1 && _equals_2);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EReference> _filter = IterableExtensions.<EReference>filter(_allReferences, _function);
      for(final EReference reference_1 : _filter) {
        _builder.newLineIfNotEmpty();
        _builder.append("/** Inverse ");
        String _name_3 = reference_1.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_3);
        _builder.append(_firstUpper, "");
        _builder.append(" */");
        _builder.newLineIfNotEmpty();
        _builder.append("protected ");
        EClass _eContainingClass = reference_1.getEContainingClass();
        String _name_4 = _eContainingClass.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_4);
        _builder.append(_firstUpper_1, "");
        _builder.append(" mInverse");
        String _name_5 = reference_1.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_5);
        _builder.append(_firstUpper_2, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  /**
   * Generates the constructors for a domain entity.
   * @param domainModel The domain model.
   * @param domainEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   */
  private CharSequence generateDomainEntityConstructors(final EClass domainEntity, final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**Default constructor.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public ");
    String _name = domainEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append(" () {");
    _builder.newLineIfNotEmpty();
    {
      EList<EClass> _eSuperTypes = domainEntity.getESuperTypes();
      int _size = _eSuperTypes.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        {
          EAttribute _attributeWithAnnotationDatabaseId = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
          boolean _notEquals = (!Objects.equal(_attributeWithAnnotationDatabaseId, null));
          if (_notEquals) {
            _builder.append("\t");
            _builder.append("this.");
            EAttribute _attributeWithAnnotationDatabaseId_1 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
            String _name_1 = _attributeWithAnnotationDatabaseId_1.getName();
            String _firstLower = StringExtensions.toFirstLower(_name_1);
            _builder.append(_firstLower, "	");
          } else {
            _builder.append("id");
          }
        }
        _builder.append(" = generateUUID();");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("\t");
        _builder.append("super();");
        _builder.newLine();
      }
    }
    {
      EList<EAttribute> _eAttributes = domainEntity.getEAttributes();
      for(final EAttribute att : _eAttributes) {
        _builder.append("\t");
        {
          boolean _or = false;
          int _upperBound = att.getUpperBound();
          boolean _greaterThan = (_upperBound > 1);
          if (_greaterThan) {
            _or = true;
          } else {
            int _upperBound_1 = att.getUpperBound();
            int _minus = (-1);
            boolean _equals_1 = (_upperBound_1 == _minus);
            _or = (_greaterThan || _equals_1);
          }
          if (_or) {
            _builder.append("this.");
            String _name_2 = att.getName();
            String _firstLower_1 = StringExtensions.toFirstLower(_name_2);
            _builder.append(_firstLower_1, "	");
            _builder.append(" = new MohitoList<");
            String _type = this.javaUtilities.getType(att);
            _builder.append(_type, "	");
            _builder.append(">(");
            String _type_1 = this.javaUtilities.getType(att);
            _builder.append(_type_1, "	");
            _builder.append(".class, this, null);");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<EReference> _eReferences = domainEntity.getEReferences();
      for(final EReference ref : _eReferences) {
        _builder.append("\t");
        {
          boolean _or_1 = false;
          int _upperBound_2 = ref.getUpperBound();
          boolean _greaterThan_1 = (_upperBound_2 > 1);
          if (_greaterThan_1) {
            _or_1 = true;
          } else {
            int _upperBound_3 = ref.getUpperBound();
            int _minus_1 = (-1);
            boolean _equals_2 = (_upperBound_3 == _minus_1);
            _or_1 = (_greaterThan_1 || _equals_2);
          }
          if (_or_1) {
            _builder.append("this.");
            String _name_3 = ref.getName();
            String _firstLower_2 = StringExtensions.toFirstLower(_name_3);
            _builder.append(_firstLower_2, "	");
            _builder.append(" = new MohitoList<");
            String _type_2 = this.javaUtilities.getType(ref);
            _builder.append(_type_2, "	");
            _builder.append(">(");
            String _type_3 = this.javaUtilities.getType(ref);
            _builder.append(_type_3, "	");
            _builder.append(".class, this,  ");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            {
              EReference _eOpposite = ref.getEOpposite();
              boolean _notEquals_1 = (!Objects.equal(_eOpposite, null));
              if (_notEquals_1) {
                _builder.append("\"");
                EReference _eOpposite_1 = ref.getEOpposite();
                String _name_4 = _eOpposite_1.getName();
                String _firstLower_3 = StringExtensions.toFirstLower(_name_4);
                _builder.append(_firstLower_3, "			");
                _builder.append("\"");
              } else {
                _builder.append("null");
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**Proxy constructor.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Marks that this instance only contains a valid id but the values for the entity with that id mus still be retrieved. Allows deferred loading.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param The id of the entity.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public ");
    String _name_5 = domainEntity.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_5);
    _builder.append(_firstUpper_1, "");
    _builder.append(" (");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    {
      EAttribute _attributeWithAnnotationDatabaseId_2 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
      boolean _notEquals_2 = (!Objects.equal(_attributeWithAnnotationDatabaseId_2, null));
      if (_notEquals_2) {
        EAttribute _attributeWithAnnotationDatabaseId_3 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
        String _type_4 = this.javaUtilities.getType(_attributeWithAnnotationDatabaseId_3);
        _builder.append(_type_4, "		");
      } else {
        _builder.append("String");
      }
    }
    _builder.append(" id) {");
    _builder.newLineIfNotEmpty();
    {
      EList<EClass> _eSuperTypes_1 = domainEntity.getESuperTypes();
      int _size_1 = _eSuperTypes_1.size();
      boolean _equals_3 = (_size_1 == 0);
      if (_equals_3) {
        {
          EAttribute _attributeWithAnnotationDatabaseId_4 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
          boolean _equals_4 = Objects.equal(_attributeWithAnnotationDatabaseId_4, null);
          if (_equals_4) {
            _builder.append("\t");
            _builder.append("this.id = id;");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("this.");
            EAttribute _attributeWithAnnotationDatabaseId_5 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
            String _name_6 = _attributeWithAnnotationDatabaseId_5.getName();
            String _firstLower_4 = StringExtensions.toFirstLower(_name_6);
            _builder.append(_firstLower_4, "	");
            _builder.append(" = id;");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("this.mIsProxy = true;");
        _builder.newLine();
      } else {
        _builder.append("\t");
        _builder.append("super(id); ");
        _builder.newLine();
      }
    }
    {
      EList<EAttribute> _eAttributes_1 = domainEntity.getEAttributes();
      for(final EAttribute att_1 : _eAttributes_1) {
        _builder.append("\t");
        {
          boolean _or_2 = false;
          int _upperBound_4 = att_1.getUpperBound();
          boolean _greaterThan_2 = (_upperBound_4 > 1);
          if (_greaterThan_2) {
            _or_2 = true;
          } else {
            int _upperBound_5 = att_1.getUpperBound();
            int _minus_2 = (-1);
            boolean _equals_5 = (_upperBound_5 == _minus_2);
            _or_2 = (_greaterThan_2 || _equals_5);
          }
          if (_or_2) {
            _builder.append("this.");
            String _name_7 = att_1.getName();
            String _firstLower_5 = StringExtensions.toFirstLower(_name_7);
            _builder.append(_firstLower_5, "	");
            _builder.append(" = new MohitoList<");
            String _type_5 = this.javaUtilities.getType(att_1);
            _builder.append(_type_5, "	");
            _builder.append(">(");
            String _type_6 = this.javaUtilities.getType(att_1);
            _builder.append(_type_6, "	");
            _builder.append(".class, this, null);");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<EReference> _eReferences_1 = domainEntity.getEReferences();
      for(final EReference ref_1 : _eReferences_1) {
        _builder.append("\t");
        {
          boolean _or_3 = false;
          int _upperBound_6 = ref_1.getUpperBound();
          boolean _greaterThan_3 = (_upperBound_6 > 1);
          if (_greaterThan_3) {
            _or_3 = true;
          } else {
            int _upperBound_7 = ref_1.getUpperBound();
            int _minus_3 = (-1);
            boolean _equals_6 = (_upperBound_7 == _minus_3);
            _or_3 = (_greaterThan_3 || _equals_6);
          }
          if (_or_3) {
            _builder.append("this.");
            String _name_8 = ref_1.getName();
            String _firstLower_6 = StringExtensions.toFirstLower(_name_8);
            _builder.append(_firstLower_6, "	");
            _builder.append(" = new MohitoList<");
            String _type_7 = this.javaUtilities.getType(ref_1);
            _builder.append(_type_7, "	");
            _builder.append(">(");
            String _type_8 = this.javaUtilities.getType(ref_1);
            _builder.append(_type_8, "	");
            _builder.append(".class, this, ");
            {
              EReference _eOpposite_2 = ref_1.getEOpposite();
              boolean _notEquals_3 = (!Objects.equal(_eOpposite_2, null));
              if (_notEquals_3) {
                _builder.append("\"");
                EReference _eOpposite_3 = ref_1.getEOpposite();
                String _name_9 = _eOpposite_3.getName();
                String _firstLower_7 = StringExtensions.toFirstLower(_name_9);
                _builder.append(_firstLower_7, "	");
                _builder.append("\"");
              } else {
                {
                  boolean _isContainment = ref_1.isContainment();
                  if (_isContainment) {
                    _builder.append("\"mInverse");
                    String _name_10 = ref_1.getName();
                    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_10);
                    _builder.append(_firstUpper_2, "	");
                    _builder.append("\"");
                  } else {
                    _builder.append("null");
                  }
                }
              }
            }
            _builder.append(");");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**Full constructor.");
    _builder.newLine();
    {
      EAttribute _attributeWithAnnotationDatabaseId_6 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
      boolean _equals_7 = Objects.equal(_attributeWithAnnotationDatabaseId_6, null);
      if (_equals_7) {
        _builder.append("* @param id Identifier of this MOHITO-Entity.");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      EList<EStructuralFeature> _eAllStructuralFeatures = domainEntity.getEAllStructuralFeatures();
      for(final EStructuralFeature feature : _eAllStructuralFeatures) {
        _builder.append("* @param ");
        String _name_11 = feature.getName();
        String _firstLower_8 = StringExtensions.toFirstLower(_name_11);
        _builder.append(_firstLower_8, "");
        _builder.append(" The ");
        String _name_12 = feature.getName();
        String _firstLower_9 = StringExtensions.toFirstLower(_name_12);
        _builder.append(_firstLower_9, "");
        _builder.append(".");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(domainModel);
      List<EReference> _allReferences = this.javaUtilities.getAllReferences(_allDomainEntities);
      final Function1<EReference,Boolean> _function = new Function1<EReference,Boolean>() {
          public Boolean apply(final EReference it) {
            boolean _and = false;
            boolean _and_1 = false;
            boolean _isContainment = it.isContainment();
            boolean _equals = (_isContainment == true);
            if (!_equals) {
              _and_1 = false;
            } else {
              EClassifier _eType = it.getEType();
              boolean _equals_1 = Objects.equal(_eType, domainEntity);
              _and_1 = (_equals && _equals_1);
            }
            if (!_and_1) {
              _and = false;
            } else {
              EReference _eOpposite = it.getEOpposite();
              boolean _equals_2 = Objects.equal(_eOpposite, null);
              _and = (_and_1 && _equals_2);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EReference> _filter = IterableExtensions.<EReference>filter(_allReferences, _function);
      for(final EReference reference : _filter) {
        _builder.append("* @param mInverse");
        String _name_13 = reference.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_13);
        _builder.append(_firstUpper_3, "");
        _builder.append(" The inverse of the containment relation ");
        String _name_14 = reference.getName();
        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_14);
        _builder.append(_firstUpper_4, "");
        _builder.append(" of class ");
        EClass _eContainingClass = reference.getEContainingClass();
        String _name_15 = _eContainingClass.getName();
        String _firstUpper_5 = StringExtensions.toFirstUpper(_name_15);
        _builder.append(_firstUpper_5, "");
        _builder.append(". ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public ");
    String _name_16 = domainEntity.getName();
    String _firstUpper_6 = StringExtensions.toFirstUpper(_name_16);
    _builder.append(_firstUpper_6, "");
    _builder.append(" (");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    {
      EAttribute _attributeWithAnnotationDatabaseId_7 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
      boolean _equals_8 = Objects.equal(_attributeWithAnnotationDatabaseId_7, null);
      if (_equals_8) {
        _builder.append("String id, ");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      EList<EStructuralFeature> _eAllStructuralFeatures_1 = domainEntity.getEAllStructuralFeatures();
      boolean _hasElements = false;
      for(final EStructuralFeature feature_1 : _eAllStructuralFeatures_1) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(", ", "		");
        }
        _builder.append("\t\t");
        {
          boolean _or_4 = false;
          int _upperBound_8 = feature_1.getUpperBound();
          boolean _greaterThan_4 = (_upperBound_8 > 1);
          if (_greaterThan_4) {
            _or_4 = true;
          } else {
            int _upperBound_9 = feature_1.getUpperBound();
            int _minus_4 = (-1);
            boolean _equals_9 = (_upperBound_9 == _minus_4);
            _or_4 = (_greaterThan_4 || _equals_9);
          }
          if (_or_4) {
            _builder.append("List<");
            String _type_9 = this.javaUtilities.getType(feature_1);
            _builder.append(_type_9, "		");
            _builder.append(">");
          } else {
            String _type_10 = this.javaUtilities.getType(feature_1);
            _builder.append(_type_10, "		");
          }
        }
        _builder.append(" ");
        String _name_17 = feature_1.getName();
        String _firstLower_10 = StringExtensions.toFirstLower(_name_17);
        _builder.append(_firstLower_10, "		");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      boolean _and = false;
      EList<EStructuralFeature> _eAllStructuralFeatures_2 = domainEntity.getEAllStructuralFeatures();
      int _size_2 = _eAllStructuralFeatures_2.size();
      boolean _notEquals_4 = (_size_2 != 0);
      if (!_notEquals_4) {
        _and = false;
      } else {
        List<EClass> _allDomainEntities_1 = this.javaUtilities.getAllDomainEntities(domainModel);
        List<EReference> _allReferences_1 = this.javaUtilities.getAllReferences(_allDomainEntities_1);
        final Function1<EReference,Boolean> _function_1 = new Function1<EReference,Boolean>() {
            public Boolean apply(final EReference it) {
              boolean _and = false;
              boolean _and_1 = false;
              boolean _isContainment = it.isContainment();
              boolean _equals = (_isContainment == true);
              if (!_equals) {
                _and_1 = false;
              } else {
                EClassifier _eType = it.getEType();
                boolean _equals_1 = Objects.equal(_eType, domainEntity);
                _and_1 = (_equals && _equals_1);
              }
              if (!_and_1) {
                _and = false;
              } else {
                EReference _eOpposite = it.getEOpposite();
                boolean _equals_2 = Objects.equal(_eOpposite, null);
                _and = (_and_1 && _equals_2);
              }
              return Boolean.valueOf(_and);
            }
          };
        Iterable<EReference> _filter_1 = IterableExtensions.<EReference>filter(_allReferences_1, _function_1);
        int _size_3 = IterableExtensions.size(_filter_1);
        boolean _notEquals_5 = (_size_3 != 0);
        _and = (_notEquals_4 && _notEquals_5);
      }
      if (_and) {
        _builder.append("\t\t");
        _builder.append(", ");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      List<EClass> _allDomainEntities_2 = this.javaUtilities.getAllDomainEntities(domainModel);
      List<EReference> _allReferences_2 = this.javaUtilities.getAllReferences(_allDomainEntities_2);
      final Function1<EReference,Boolean> _function_2 = new Function1<EReference,Boolean>() {
          public Boolean apply(final EReference it) {
            boolean _and = false;
            boolean _and_1 = false;
            boolean _isContainment = it.isContainment();
            boolean _equals = (_isContainment == true);
            if (!_equals) {
              _and_1 = false;
            } else {
              EClassifier _eType = it.getEType();
              boolean _equals_1 = Objects.equal(_eType, domainEntity);
              _and_1 = (_equals && _equals_1);
            }
            if (!_and_1) {
              _and = false;
            } else {
              EReference _eOpposite = it.getEOpposite();
              boolean _equals_2 = Objects.equal(_eOpposite, null);
              _and = (_and_1 && _equals_2);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EReference> _filter_2 = IterableExtensions.<EReference>filter(_allReferences_2, _function_2);
      boolean _hasElements_1 = false;
      for(final EReference reference_1 : _filter_2) {
        if (!_hasElements_1) {
          _hasElements_1 = true;
        } else {
          _builder.appendImmediate(", ", "		");
        }
        _builder.append("\t\t");
        EClass _eContainingClass_1 = reference_1.getEContainingClass();
        String _name_18 = _eContainingClass_1.getName();
        String _firstUpper_7 = StringExtensions.toFirstUpper(_name_18);
        _builder.append(_firstUpper_7, "		");
        _builder.append(" mInverse");
        String _name_19 = reference_1.getName();
        String _firstUpper_8 = StringExtensions.toFirstUpper(_name_19);
        _builder.append(_firstUpper_8, "		");
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append(") {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.newLine();
    {
      EList<EClass> _eSuperTypes_2 = domainEntity.getESuperTypes();
      int _size_4 = _eSuperTypes_2.size();
      boolean _greaterThan_5 = (_size_4 > 0);
      if (_greaterThan_5) {
        _builder.append("\t");
        EList<EClass> _eSuperTypes_3 = domainEntity.getESuperTypes();
        final EClass superDomainEntity = _eSuperTypes_3.get(0);
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("super(");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("\t");
        {
          EAttribute _attributeWithAnnotationDatabaseId_8 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(superDomainEntity);
          boolean _equals_10 = Objects.equal(_attributeWithAnnotationDatabaseId_8, null);
          if (_equals_10) {
            _builder.append("id ");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        {
          EList<EStructuralFeature> _eAllStructuralFeatures_3 = superDomainEntity.getEAllStructuralFeatures();
          int _size_5 = _eAllStructuralFeatures_3.size();
          boolean _notEquals_6 = (_size_5 != 0);
          if (_notEquals_6) {
            _builder.append(", ");
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        {
          EList<EStructuralFeature> _eAllStructuralFeatures_4 = superDomainEntity.getEAllStructuralFeatures();
          boolean _hasElements_2 = false;
          for(final EStructuralFeature feature_2 : _eAllStructuralFeatures_4) {
            if (!_hasElements_2) {
              _hasElements_2 = true;
            } else {
              _builder.appendImmediate(", ", "		");
            }
            String _name_20 = feature_2.getName();
            String _firstLower_11 = StringExtensions.toFirstLower(_name_20);
            _builder.append(_firstLower_11, "		");
          }
        }
        _builder.newLineIfNotEmpty();
        {
          boolean _and_1 = false;
          EList<EStructuralFeature> _eAllStructuralFeatures_5 = superDomainEntity.getEAllStructuralFeatures();
          int _size_6 = _eAllStructuralFeatures_5.size();
          boolean _notEquals_7 = (_size_6 != 0);
          if (!_notEquals_7) {
            _and_1 = false;
          } else {
            List<EClass> _allDomainEntities_3 = this.javaUtilities.getAllDomainEntities(domainModel);
            List<EReference> _allReferences_3 = this.javaUtilities.getAllReferences(_allDomainEntities_3);
            final Function1<EReference,Boolean> _function_3 = new Function1<EReference,Boolean>() {
                public Boolean apply(final EReference it) {
                  boolean _and = false;
                  boolean _and_1 = false;
                  boolean _isContainment = it.isContainment();
                  boolean _equals = (_isContainment == true);
                  if (!_equals) {
                    _and_1 = false;
                  } else {
                    EClassifier _eType = it.getEType();
                    boolean _equals_1 = Objects.equal(_eType, superDomainEntity);
                    _and_1 = (_equals && _equals_1);
                  }
                  if (!_and_1) {
                    _and = false;
                  } else {
                    EReference _eOpposite = it.getEOpposite();
                    boolean _equals_2 = Objects.equal(_eOpposite, null);
                    _and = (_and_1 && _equals_2);
                  }
                  return Boolean.valueOf(_and);
                }
              };
            Iterable<EReference> _filter_3 = IterableExtensions.<EReference>filter(_allReferences_3, _function_3);
            int _size_7 = IterableExtensions.size(_filter_3);
            boolean _notEquals_8 = (_size_7 != 0);
            _and_1 = (_notEquals_7 && _notEquals_8);
          }
          if (_and_1) {
            _builder.append("\t");
            _builder.append("\t");
            _builder.append(", ");
          }
        }
        _builder.newLineIfNotEmpty();
        {
          List<EClass> _allDomainEntities_4 = this.javaUtilities.getAllDomainEntities(domainModel);
          List<EReference> _allReferences_4 = this.javaUtilities.getAllReferences(_allDomainEntities_4);
          final Function1<EReference,Boolean> _function_4 = new Function1<EReference,Boolean>() {
              public Boolean apply(final EReference it) {
                boolean _and = false;
                boolean _and_1 = false;
                boolean _isContainment = it.isContainment();
                boolean _equals = (_isContainment == true);
                if (!_equals) {
                  _and_1 = false;
                } else {
                  EClassifier _eType = it.getEType();
                  boolean _equals_1 = Objects.equal(_eType, superDomainEntity);
                  _and_1 = (_equals && _equals_1);
                }
                if (!_and_1) {
                  _and = false;
                } else {
                  EReference _eOpposite = it.getEOpposite();
                  boolean _equals_2 = Objects.equal(_eOpposite, null);
                  _and = (_and_1 && _equals_2);
                }
                return Boolean.valueOf(_and);
              }
            };
          Iterable<EReference> _filter_4 = IterableExtensions.<EReference>filter(_allReferences_4, _function_4);
          boolean _hasElements_3 = false;
          for(final EReference reference_2 : _filter_4) {
            if (!_hasElements_3) {
              _hasElements_3 = true;
            } else {
              _builder.appendImmediate(", ", "		");
            }
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("mInverse");
            String _name_21 = reference_2.getName();
            String _firstUpper_9 = StringExtensions.toFirstUpper(_name_21);
            _builder.append(_firstUpper_9, "		");
            _builder.append(" ");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("\t");
        _builder.append(");");
        _builder.newLine();
      } else {
        _builder.append("\t");
        {
          EAttribute _attributeWithAnnotationDatabaseId_9 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
          boolean _equals_11 = Objects.equal(_attributeWithAnnotationDatabaseId_9, null);
          if (_equals_11) {
            _builder.append("this.id = id;");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<EStructuralFeature> _eStructuralFeatures = domainEntity.getEStructuralFeatures();
      for(final EStructuralFeature sf : _eStructuralFeatures) {
        {
          int _lowerBound = sf.getLowerBound();
          boolean _greaterThan_6 = (_lowerBound > 0);
          if (_greaterThan_6) {
            _builder.append("if (");
            String _name_22 = sf.getName();
            String _firstLower_12 = StringExtensions.toFirstLower(_name_22);
            _builder.append(_firstLower_12, "");
            _builder.append("==null");
            {
              boolean _or_5 = false;
              int _upperBound_10 = sf.getUpperBound();
              boolean _greaterThan_7 = (_upperBound_10 > 1);
              if (_greaterThan_7) {
                _or_5 = true;
              } else {
                int _upperBound_11 = sf.getUpperBound();
                int _minus_5 = (-1);
                boolean _equals_12 = (_upperBound_11 == _minus_5);
                _or_5 = (_greaterThan_7 || _equals_12);
              }
              if (_or_5) {
                _builder.append("&& ");
                String _name_23 = sf.getName();
                String _firstLower_13 = StringExtensions.toFirstLower(_name_23);
                _builder.append(_firstLower_13, "");
                _builder.append(".size() == 0");
              }
            }
            _builder.append(") {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("throw new IllegalArgumentException(\"The \'");
            String _name_24 = sf.getName();
            String _firstLower_14 = StringExtensions.toFirstLower(_name_24);
            _builder.append(_firstLower_14, "	");
            _builder.append("\' of this entity must not be NULL or empty.\");");
            _builder.newLineIfNotEmpty();
            _builder.append("}");
            _builder.newLine();
          }
        }
        {
          boolean _or_6 = false;
          int _upperBound_12 = sf.getUpperBound();
          boolean _greaterThan_8 = (_upperBound_12 > 1);
          if (_greaterThan_8) {
            _or_6 = true;
          } else {
            int _upperBound_13 = sf.getUpperBound();
            int _minus_6 = (-1);
            boolean _equals_13 = (_upperBound_13 == _minus_6);
            _or_6 = (_greaterThan_8 || _equals_13);
          }
          if (_or_6) {
            _builder.append("this.");
            String _name_25 = sf.getName();
            String _firstLower_15 = StringExtensions.toFirstLower(_name_25);
            _builder.append(_firstLower_15, "");
            _builder.append(" = new MohitoList<");
            String _type_11 = this.javaUtilities.getType(sf);
            _builder.append(_type_11, "");
            _builder.append(">(");
            String _type_12 = this.javaUtilities.getType(sf);
            _builder.append(_type_12, "");
            _builder.append(".class, this, ");
            {
              boolean _and_2 = false;
              if (!(sf instanceof EReference)) {
                _and_2 = false;
              } else {
                EReference _eOpposite_4 = ((EReference) sf).getEOpposite();
                boolean _notEquals_9 = (!Objects.equal(_eOpposite_4, null));
                _and_2 = ((sf instanceof EReference) && _notEquals_9);
              }
              if (_and_2) {
                _builder.append("\"");
                EReference _eOpposite_5 = ((EReference) sf).getEOpposite();
                String _name_26 = _eOpposite_5.getName();
                String _firstLower_16 = StringExtensions.toFirstLower(_name_26);
                _builder.append(_firstLower_16, "");
                _builder.append("\"");
              } else {
                {
                  boolean _and_3 = false;
                  if (!(sf instanceof EReference)) {
                    _and_3 = false;
                  } else {
                    boolean _isContainment_1 = ((EReference) sf).isContainment();
                    _and_3 = ((sf instanceof EReference) && _isContainment_1);
                  }
                  if (_and_3) {
                    _builder.append("\"mInverse");
                    String _name_27 = sf.getName();
                    String _firstUpper_10 = StringExtensions.toFirstUpper(_name_27);
                    _builder.append(_firstUpper_10, "");
                    _builder.append("\"");
                  } else {
                    _builder.append("null");
                  }
                }
              }
            }
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("if (");
            String _name_28 = sf.getName();
            String _firstLower_17 = StringExtensions.toFirstLower(_name_28);
            _builder.append(_firstLower_17, "");
            _builder.append(" != null) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("this.");
            String _name_29 = sf.getName();
            String _firstLower_18 = StringExtensions.toFirstLower(_name_29);
            _builder.append(_firstLower_18, "	");
            _builder.append(".addAll(");
            String _name_30 = sf.getName();
            String _firstLower_19 = StringExtensions.toFirstLower(_name_30);
            _builder.append(_firstLower_19, "	");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
            _builder.append("}");
            _builder.newLine();
          } else {
            _builder.append("this.");
            String _name_31 = sf.getName();
            String _firstLower_20 = StringExtensions.toFirstLower(_name_31);
            _builder.append(_firstLower_20, "");
            _builder.append(" = ");
            String _name_32 = sf.getName();
            String _firstLower_21 = StringExtensions.toFirstLower(_name_32);
            _builder.append(_firstLower_21, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      List<EClass> _allDomainEntities_5 = this.javaUtilities.getAllDomainEntities(domainModel);
      List<EReference> _allReferences_5 = this.javaUtilities.getAllReferences(_allDomainEntities_5);
      final Function1<EReference,Boolean> _function_5 = new Function1<EReference,Boolean>() {
          public Boolean apply(final EReference it) {
            boolean _and = false;
            boolean _and_1 = false;
            boolean _isContainment = it.isContainment();
            boolean _equals = (_isContainment == true);
            if (!_equals) {
              _and_1 = false;
            } else {
              EClassifier _eType = it.getEType();
              boolean _equals_1 = Objects.equal(_eType, domainEntity);
              _and_1 = (_equals && _equals_1);
            }
            if (!_and_1) {
              _and = false;
            } else {
              EReference _eOpposite = it.getEOpposite();
              boolean _equals_2 = Objects.equal(_eOpposite, null);
              _and = (_and_1 && _equals_2);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EReference> _filter_5 = IterableExtensions.<EReference>filter(_allReferences_5, _function_5);
      for(final EReference reference_3 : _filter_5) {
        _builder.append("this.mInverse");
        String _name_33 = reference_3.getName();
        String _firstUpper_11 = StringExtensions.toFirstUpper(_name_33);
        _builder.append(_firstUpper_11, "");
        _builder.append(" = mInverse");
        String _name_34 = reference_3.getName();
        String _firstUpper_12 = StringExtensions.toFirstUpper(_name_34);
        _builder.append(_firstUpper_12, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates the methods for transparent proxy resolution at runtime.
   * @param domainModel The domain model.
   * @param domainEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   */
  private CharSequence generateDomainEntityTransparentProxyResolution(final EClass domainEntity, final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void doCheckProxyAndResolveAssignment(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("MohitoEntity<");
    String _typeOfDomainEntityIdentifier = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
    _builder.append(_typeOfDomainEntityIdentifier, "		");
    _builder.append("> reference) {");
    _builder.newLineIfNotEmpty();
    {
      EList<EClass> _eAllSuperTypes = domainEntity.getEAllSuperTypes();
      int _size = _eAllSuperTypes.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("\t");
        _builder.append("super.doCheckProxyAndResolveAssignment(reference);");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    String _name = domainEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "	");
    _builder.append(" ref = (");
    String _name_1 = domainEntity.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper_1, "	");
    _builder.append(") reference;");
    _builder.newLineIfNotEmpty();
    {
      EList<EStructuralFeature> _eStructuralFeatures = domainEntity.getEStructuralFeatures();
      for(final EStructuralFeature sf : _eStructuralFeatures) {
        {
          boolean _or = false;
          int _upperBound = sf.getUpperBound();
          boolean _greaterThan_1 = (_upperBound > 1);
          if (_greaterThan_1) {
            _or = true;
          } else {
            int _upperBound_1 = sf.getUpperBound();
            int _minus = (-1);
            boolean _equals = (_upperBound_1 == _minus);
            _or = (_greaterThan_1 || _equals);
          }
          if (_or) {
            _builder.append("this.");
            String _name_2 = sf.getName();
            String _firstLower = StringExtensions.toFirstLower(_name_2);
            _builder.append(_firstLower, "");
            _builder.append(".clear();");
            _builder.newLineIfNotEmpty();
            _builder.append("this.");
            String _name_3 = sf.getName();
            String _firstLower_1 = StringExtensions.toFirstLower(_name_3);
            _builder.append(_firstLower_1, "");
            _builder.append(".addAll(ref.");
            String _name_4 = sf.getName();
            String _firstLower_2 = StringExtensions.toFirstLower(_name_4);
            _builder.append(_firstLower_2, "");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("this.");
            String _name_5 = sf.getName();
            String _firstLower_3 = StringExtensions.toFirstLower(_name_5);
            _builder.append(_firstLower_3, "");
            _builder.append(" = ref.");
            String _name_6 = sf.getName();
            String _firstLower_4 = StringExtensions.toFirstLower(_name_6);
            _builder.append(_firstLower_4, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(domainModel);
      List<EReference> _allReferences = this.javaUtilities.getAllReferences(_allDomainEntities);
      final Function1<EReference,Boolean> _function = new Function1<EReference,Boolean>() {
          public Boolean apply(final EReference it) {
            boolean _and = false;
            boolean _and_1 = false;
            boolean _isContainment = it.isContainment();
            boolean _equals = (_isContainment == true);
            if (!_equals) {
              _and_1 = false;
            } else {
              EClassifier _eType = it.getEType();
              boolean _equals_1 = Objects.equal(_eType, domainEntity);
              _and_1 = (_equals && _equals_1);
            }
            if (!_and_1) {
              _and = false;
            } else {
              EReference _eOpposite = it.getEOpposite();
              boolean _equals_2 = Objects.equal(_eOpposite, null);
              _and = (_and_1 && _equals_2);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EReference> _filter = IterableExtensions.<EReference>filter(_allReferences, _function);
      for(final EReference reference : _filter) {
        _builder.append("this.mInverse");
        String _name_7 = reference.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_7);
        _builder.append(_firstUpper_2, "");
        _builder.append(" = ref.mInverse");
        String _name_8 = reference.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_8);
        _builder.append(_firstUpper_3, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    {
      boolean _isAbstract = domainEntity.isAbstract();
      if (_isAbstract) {
        _builder.append("// REMARK no doCheckProxyAndResolveGetReferenceEntity for abstract classes required.");
        _builder.newLine();
      } else {
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("protected MohitoEntity<");
        String _typeOfDomainEntityIdentifier_1 = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
        _builder.append(_typeOfDomainEntityIdentifier_1, "");
        _builder.append("> doCheckProxyAndResolveGetReferenceEntity() throws DataLayerException {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        String _name_9 = domainModel.getName();
        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_9);
        _builder.append(_firstUpper_4, "	");
        _builder.append("StorageManager.mINSTANCE.getAvailableStorageManager().get");
        String _name_10 = domainEntity.getName();
        String _firstUpper_5 = StringExtensions.toFirstUpper(_name_10);
        _builder.append(_firstUpper_5, "	");
        _builder.append("Dao().getById(getId());");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  /**
   * Generates the methods getters and setters of a domain entity.
   * @param domainModel The domain model.
   * @param domainEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   */
  private CharSequence generateDomainEntityGetterAndSetter(final EClass domainEntity, final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t\t");
    _builder.newLine();
    {
      EList<EClass> _eSuperTypes = domainEntity.getESuperTypes();
      int _size = _eSuperTypes.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("public ");
        {
          EAttribute _attributeWithAnnotationDatabaseId = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
          boolean _equals_1 = Objects.equal(_attributeWithAnnotationDatabaseId, null);
          if (_equals_1) {
            _builder.append("String");
          } else {
            EAttribute _attributeWithAnnotationDatabaseId_1 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
            String _type = this.javaUtilities.getType(_attributeWithAnnotationDatabaseId_1);
            _builder.append(_type, "");
          }
        }
        _builder.append(" ");
        _builder.newLineIfNotEmpty();
        _builder.append("\t\t");
        _builder.append("getId() {");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("return ");
        {
          EAttribute _attributeWithAnnotationDatabaseId_2 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
          boolean _equals_2 = Objects.equal(_attributeWithAnnotationDatabaseId_2, null);
          if (_equals_2) {
            _builder.append("id");
          } else {
            EAttribute _attributeWithAnnotationDatabaseId_3 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
            String _name = _attributeWithAnnotationDatabaseId_3.getName();
            String _firstLower = StringExtensions.toFirstLower(_name);
            _builder.append(_firstLower, "	");
          }
        }
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("// No setId(...) method for implicit identifier.");
        _builder.newLine();
      }
    }
    {
      EList<EStructuralFeature> _eStructuralFeatures = domainEntity.getEStructuralFeatures();
      for(final EStructuralFeature feature : _eStructuralFeatures) {
        {
          EAttribute _attributeWithAnnotationDatabaseId_4 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
          boolean _notEquals = (!Objects.equal(_attributeWithAnnotationDatabaseId_4, feature));
          if (_notEquals) {
            _builder.append("/**");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("* Get method for the attribute ");
            String _name_1 = feature.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_1);
            _builder.append(_firstUpper, " ");
            _builder.newLineIfNotEmpty();
            _builder.append(" ");
            _builder.append("*/");
            _builder.newLine();
            _builder.append("public ");
            {
              boolean _or = false;
              int _upperBound = feature.getUpperBound();
              boolean _greaterThan = (_upperBound > 1);
              if (_greaterThan) {
                _or = true;
              } else {
                int _upperBound_1 = feature.getUpperBound();
                int _minus = (-1);
                boolean _equals_3 = (_upperBound_1 == _minus);
                _or = (_greaterThan || _equals_3);
              }
              if (_or) {
                _builder.append("MohitoList<");
                String _type_1 = this.javaUtilities.getType(feature);
                _builder.append(_type_1, "");
                _builder.append(">");
              } else {
                String _type_2 = this.javaUtilities.getType(feature);
                _builder.append(_type_2, "");
              }
            }
            _builder.append(" ");
            _builder.newLineIfNotEmpty();
            _builder.append("\t\t");
            _builder.append("get");
            String _name_2 = feature.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_2);
            _builder.append(_firstUpper_1, "		");
            _builder.append("() {");
            _builder.newLineIfNotEmpty();
            {
              EAttribute _attributeWithAnnotationDatabaseId_5 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
              boolean _notEquals_1 = (!Objects.equal(_attributeWithAnnotationDatabaseId_5, feature));
              if (_notEquals_1) {
                _builder.append("\t");
                _builder.append("checkProxyAndResolve();");
                _builder.newLine();
              }
            }
            _builder.append("\t");
            _builder.append("return this.");
            String _name_3 = feature.getName();
            String _firstLower_1 = StringExtensions.toFirstLower(_name_3);
            _builder.append(_firstLower_1, "	");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("}");
            _builder.newLine();
          }
        }
        {
          boolean _or_1 = false;
          int _upperBound_2 = feature.getUpperBound();
          boolean _greaterThan_1 = (_upperBound_2 > 1);
          if (_greaterThan_1) {
            _or_1 = true;
          } else {
            int _upperBound_3 = feature.getUpperBound();
            int _minus_1 = (-1);
            boolean _equals_4 = (_upperBound_3 == _minus_1);
            _or_1 = (_greaterThan_1 || _equals_4);
          }
          if (_or_1) {
            _builder.append("// No set");
            String _name_4 = feature.getName();
            String _firstUpper_2 = StringExtensions.toFirstUpper(_name_4);
            _builder.append(_firstUpper_2, "");
            _builder.append("(...) method for a collection");
            _builder.newLineIfNotEmpty();
          } else {
            EAttribute _attributeWithAnnotationDatabaseId_6 = this.javaUtilities.getAttributeWithAnnotationDatabaseId(domainEntity);
            boolean _equals_5 = Objects.equal(_attributeWithAnnotationDatabaseId_6, feature);
            if (_equals_5) {
              _builder.append("// No set");
              String _name_5 = feature.getName();
              String _firstUpper_3 = StringExtensions.toFirstUpper(_name_5);
              _builder.append(_firstUpper_3, "");
              _builder.append("(...) method for an identifier");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("/**");
              _builder.newLine();
              _builder.append(" ");
              _builder.append("* Set method for the attribute ");
              String _name_6 = feature.getName();
              String _firstUpper_4 = StringExtensions.toFirstUpper(_name_6);
              _builder.append(_firstUpper_4, " ");
              _builder.newLineIfNotEmpty();
              _builder.append(" ");
              _builder.append("*/");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("public void set");
              String _name_7 = feature.getName();
              String _firstUpper_5 = StringExtensions.toFirstUpper(_name_7);
              _builder.append(_firstUpper_5, "	");
              _builder.append("(");
              String _type_3 = this.javaUtilities.getType(feature);
              _builder.append(_type_3, "	");
              _builder.append(" ");
              String _name_8 = feature.getName();
              String _firstLower_2 = StringExtensions.toFirstLower(_name_8);
              _builder.append(_firstLower_2, "	");
              _builder.append(") {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              _builder.append("// release");
              _builder.newLine();
              {
                boolean _and = false;
                EList<EClassifier> _eClassifiers = domainModel.getEClassifiers();
                EClassifier _eType = feature.getEType();
                boolean _contains = _eClassifiers.contains(_eType);
                if (!_contains) {
                  _and = false;
                } else {
                  EClassifier _eType_1 = feature.getEType();
                  boolean _not = (!(_eType_1 instanceof EEnum));
                  _and = (_contains && _not);
                }
                if (_and) {
                  _builder.append("\t\t");
                  _builder.append("if (this.");
                  String _name_9 = feature.getName();
                  String _firstLower_3 = StringExtensions.toFirstLower(_name_9);
                  _builder.append(_firstLower_3, "		");
                  _builder.append(" != null) {");
                  _builder.newLineIfNotEmpty();
                  {
                    boolean _and_1 = false;
                    if (!(feature instanceof EReference)) {
                      _and_1 = false;
                    } else {
                      boolean _isContainment = ((EReference) feature).isContainment();
                      _and_1 = ((feature instanceof EReference) && _isContainment);
                    }
                    if (_and_1) {
                      _builder.append("\t\t");
                      _builder.append("\t");
                      _builder.append("this.");
                      String _name_10 = feature.getName();
                      String _firstLower_4 = StringExtensions.toFirstLower(_name_10);
                      _builder.append(_firstLower_4, "			");
                      _builder.append(".mSetDeletionPending(true);");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                  {
                    boolean _and_2 = false;
                    if (!(feature instanceof EReference)) {
                      _and_2 = false;
                    } else {
                      EReference _eOpposite = ((EReference) feature).getEOpposite();
                      boolean _notEquals_2 = (!Objects.equal(_eOpposite, null));
                      _and_2 = ((feature instanceof EReference) && _notEquals_2);
                    }
                    if (_and_2) {
                      {
                        boolean _or_2 = false;
                        EReference _eOpposite_1 = ((EReference) feature).getEOpposite();
                        int _upperBound_4 = _eOpposite_1.getUpperBound();
                        boolean _greaterThan_2 = (_upperBound_4 > 1);
                        if (_greaterThan_2) {
                          _or_2 = true;
                        } else {
                          EReference _eOpposite_2 = ((EReference) feature).getEOpposite();
                          int _upperBound_5 = _eOpposite_2.getUpperBound();
                          int _minus_2 = (-1);
                          boolean _equals_6 = (_upperBound_5 == _minus_2);
                          _or_2 = (_greaterThan_2 || _equals_6);
                        }
                        if (_or_2) {
                          _builder.append("\t\t");
                          _builder.append("\t");
                          _builder.append("this.");
                          String _name_11 = feature.getName();
                          String _firstLower_5 = StringExtensions.toFirstLower(_name_11);
                          _builder.append(_firstLower_5, "			");
                          _builder.append(".get");
                          EReference _eOpposite_3 = ((EReference) feature).getEOpposite();
                          String _name_12 = _eOpposite_3.getName();
                          String _firstUpper_6 = StringExtensions.toFirstUpper(_name_12);
                          _builder.append(_firstUpper_6, "			");
                          _builder.append("().remove(this);");
                          _builder.newLineIfNotEmpty();
                        } else {
                          _builder.append("\t\t");
                          _builder.append("\t");
                          _builder.append("this.");
                          String _name_13 = feature.getName();
                          String _firstLower_6 = StringExtensions.toFirstLower(_name_13);
                          _builder.append(_firstLower_6, "			");
                          _builder.append(".set");
                          EReference _eOpposite_4 = ((EReference) feature).getEOpposite();
                          String _name_14 = _eOpposite_4.getName();
                          String _firstUpper_7 = StringExtensions.toFirstUpper(_name_14);
                          _builder.append(_firstUpper_7, "			");
                          _builder.append("(null);");
                          _builder.newLineIfNotEmpty();
                        }
                      }
                    }
                  }
                  _builder.append("\t\t");
                  _builder.append("}");
                  _builder.newLine();
                }
              }
              _builder.append("\t\t");
              _builder.append("// set");
              _builder.newLine();
              _builder.append("\t\t");
              _builder.append("this.");
              String _name_15 = feature.getName();
              String _firstLower_7 = StringExtensions.toFirstLower(_name_15);
              _builder.append(_firstLower_7, "		");
              _builder.append(" = ");
              String _name_16 = feature.getName();
              String _firstLower_8 = StringExtensions.toFirstLower(_name_16);
              _builder.append(_firstLower_8, "		");
              _builder.append(";");
              _builder.newLineIfNotEmpty();
              {
                boolean _and_3 = false;
                if (!(feature instanceof EReference)) {
                  _and_3 = false;
                } else {
                  EReference _eOpposite_5 = ((EReference) feature).getEOpposite();
                  boolean _notEquals_3 = (!Objects.equal(_eOpposite_5, null));
                  _and_3 = ((feature instanceof EReference) && _notEquals_3);
                }
                if (_and_3) {
                  _builder.append("\t\t");
                  _builder.append("if (");
                  String _name_17 = feature.getName();
                  String _firstLower_9 = StringExtensions.toFirstLower(_name_17);
                  _builder.append(_firstLower_9, "		");
                  _builder.append(" != null) {");
                  _builder.newLineIfNotEmpty();
                  {
                    boolean _or_3 = false;
                    EReference _eOpposite_6 = ((EReference) feature).getEOpposite();
                    int _upperBound_6 = _eOpposite_6.getUpperBound();
                    boolean _greaterThan_3 = (_upperBound_6 > 1);
                    if (_greaterThan_3) {
                      _or_3 = true;
                    } else {
                      EReference _eOpposite_7 = ((EReference) feature).getEOpposite();
                      int _upperBound_7 = _eOpposite_7.getUpperBound();
                      int _minus_3 = (-1);
                      boolean _equals_7 = (_upperBound_7 == _minus_3);
                      _or_3 = (_greaterThan_3 || _equals_7);
                    }
                    if (_or_3) {
                      _builder.append("\t\t");
                      _builder.append("\t");
                      String _name_18 = feature.getName();
                      String _firstLower_10 = StringExtensions.toFirstLower(_name_18);
                      _builder.append(_firstLower_10, "			");
                      _builder.append(".get");
                      EReference _eOpposite_8 = ((EReference) feature).getEOpposite();
                      String _name_19 = _eOpposite_8.getName();
                      String _firstUpper_8 = StringExtensions.toFirstUpper(_name_19);
                      _builder.append(_firstUpper_8, "			");
                      _builder.append("().add(this);");
                      _builder.newLineIfNotEmpty();
                    } else {
                      _builder.append("\t\t");
                      _builder.append("\t");
                      String _name_20 = feature.getName();
                      String _firstLower_11 = StringExtensions.toFirstLower(_name_20);
                      _builder.append(_firstLower_11, "			");
                      _builder.append(".set");
                      EReference _eOpposite_9 = ((EReference) feature).getEOpposite();
                      String _name_21 = _eOpposite_9.getName();
                      String _firstUpper_9 = StringExtensions.toFirstUpper(_name_21);
                      _builder.append(_firstUpper_9, "			");
                      _builder.append("(this);");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                  _builder.append("\t\t");
                  _builder.append("}");
                  _builder.newLine();
                }
              }
              _builder.append("\t\t");
              _builder.append("mSetDirty(true);");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
            }
          }
        }
      }
    }
    {
      List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(domainModel);
      List<EReference> _allReferences = this.javaUtilities.getAllReferences(_allDomainEntities);
      final Function1<EReference,Boolean> _function = new Function1<EReference,Boolean>() {
          public Boolean apply(final EReference it) {
            boolean _and = false;
            boolean _and_1 = false;
            boolean _isContainment = it.isContainment();
            boolean _equals = (_isContainment == true);
            if (!_equals) {
              _and_1 = false;
            } else {
              EClassifier _eType = it.getEType();
              boolean _equals_1 = Objects.equal(_eType, domainEntity);
              _and_1 = (_equals && _equals_1);
            }
            if (!_and_1) {
              _and = false;
            } else {
              EReference _eOpposite = it.getEOpposite();
              boolean _equals_2 = Objects.equal(_eOpposite, null);
              _and = (_and_1 && _equals_2);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EReference> _filter = IterableExtensions.<EReference>filter(_allReferences, _function);
      for(final EReference reference : _filter) {
        _builder.append("\t\t\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("* Get method for the inverse ");
        String _name_22 = reference.getName();
        String _firstUpper_10 = StringExtensions.toFirstUpper(_name_22);
        _builder.append(_firstUpper_10, " ");
        _builder.newLineIfNotEmpty();
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("public ");
        EClass _eContainingClass = reference.getEContainingClass();
        String _name_23 = _eContainingClass.getName();
        _builder.append(_name_23, "");
        _builder.append(" getMInverse");
        String _name_24 = reference.getName();
        String _firstUpper_11 = StringExtensions.toFirstUpper(_name_24);
        _builder.append(_firstUpper_11, "");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("checkProxyAndResolve();");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("return mInverse");
        String _name_25 = reference.getName();
        String _firstUpper_12 = StringExtensions.toFirstUpper(_name_25);
        _builder.append(_firstUpper_12, "	");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t\t\t");
        _builder.append("/**");
        _builder.newLine();
        _builder.append(" ");
        _builder.append("* Set method for the attribute ");
        String _name_26 = reference.getName();
        String _firstUpper_13 = StringExtensions.toFirstUpper(_name_26);
        _builder.append(_firstUpper_13, " ");
        _builder.newLineIfNotEmpty();
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("public void setMInverse");
        String _name_27 = reference.getName();
        String _firstUpper_14 = StringExtensions.toFirstUpper(_name_27);
        _builder.append(_firstUpper_14, "");
        _builder.append("(");
        EClass _eContainingClass_1 = reference.getEContainingClass();
        String _name_28 = _eContainingClass_1.getName();
        _builder.append(_name_28, "");
        _builder.append(" mInverse");
        String _name_29 = reference.getName();
        String _firstUpper_15 = StringExtensions.toFirstUpper(_name_29);
        _builder.append(_firstUpper_15, "");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("// sanity check");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("if (mInverse");
        String _name_30 = reference.getName();
        String _firstUpper_16 = StringExtensions.toFirstUpper(_name_30);
        _builder.append(_firstUpper_16, "	");
        _builder.append(" == null &&");
        _builder.newLineIfNotEmpty();
        {
          List<EClass> _allDomainEntities_1 = this.javaUtilities.getAllDomainEntities(domainModel);
          List<EReference> _allReferences_1 = this.javaUtilities.getAllReferences(_allDomainEntities_1);
          final Function1<EReference,Boolean> _function_1 = new Function1<EReference,Boolean>() {
              public Boolean apply(final EReference it) {
                boolean _and = false;
                boolean _and_1 = false;
                boolean _isContainment = it.isContainment();
                boolean _equals = (_isContainment == true);
                if (!_equals) {
                  _and_1 = false;
                } else {
                  EClassifier _eType = it.getEType();
                  boolean _equals_1 = Objects.equal(_eType, domainEntity);
                  _and_1 = (_equals && _equals_1);
                }
                if (!_and_1) {
                  _and = false;
                } else {
                  EReference _eOpposite = it.getEOpposite();
                  boolean _equals_2 = Objects.equal(_eOpposite, null);
                  _and = (_and_1 && _equals_2);
                }
                return Boolean.valueOf(_and);
              }
            };
          Iterable<EReference> _filter_1 = IterableExtensions.<EReference>filter(_allReferences_1, _function_1);
          boolean _hasElements = false;
          for(final EReference invRef : _filter_1) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate(" && ", "	");
            }
            {
              boolean _notEquals_4 = (!Objects.equal(invRef, reference));
              if (_notEquals_4) {
                _builder.append("\t");
                _builder.append("this.mInverse");
                String _name_31 = invRef.getName();
                String _firstUpper_17 = StringExtensions.toFirstUpper(_name_31);
                _builder.append(_firstUpper_17, "	");
                _builder.append(" == null");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t");
                _builder.append("true");
                _builder.newLine();
              }
            }
          }
        }
        _builder.append("\t\t\t");
        _builder.append("&& !mIsDeletionPending()) {");
        _builder.newLine();
        _builder.append("\t\t");
        _builder.append("throw new IllegalArgumentException(\"At least one of the containment relations of the MOHITO-Entity ");
        String _name_32 = domainEntity.getName();
        String _firstUpper_18 = StringExtensions.toFirstUpper(_name_32);
        _builder.append(_firstUpper_18, "		");
        _builder.append(" must not be NULL.\");");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// release / rewire instance relation if necessary");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("if (this.mInverse");
        String _name_33 = reference.getName();
        String _firstUpper_19 = StringExtensions.toFirstUpper(_name_33);
        _builder.append(_firstUpper_19, "	");
        _builder.append(" != null) {");
        _builder.newLineIfNotEmpty();
        {
          boolean _or_4 = false;
          int _upperBound_8 = reference.getUpperBound();
          boolean _greaterThan_4 = (_upperBound_8 > 1);
          if (_greaterThan_4) {
            _or_4 = true;
          } else {
            int _upperBound_9 = reference.getUpperBound();
            int _minus_4 = (-1);
            boolean _equals_8 = (_upperBound_9 == _minus_4);
            _or_4 = (_greaterThan_4 || _equals_8);
          }
          if (_or_4) {
            _builder.append("\t\t");
            _builder.append("this.mInverse");
            String _name_34 = reference.getName();
            String _firstUpper_20 = StringExtensions.toFirstUpper(_name_34);
            _builder.append(_firstUpper_20, "		");
            _builder.append(".get");
            String _name_35 = reference.getName();
            String _firstUpper_21 = StringExtensions.toFirstUpper(_name_35);
            _builder.append(_firstUpper_21, "		");
            _builder.append("().remove(this);");
            _builder.newLineIfNotEmpty();
          } else {
            _builder.append("\t\t");
            _builder.append("this.mInverse");
            String _name_36 = reference.getName();
            String _firstUpper_22 = StringExtensions.toFirstUpper(_name_36);
            _builder.append(_firstUpper_22, "		");
            _builder.append(".set");
            String _name_37 = reference.getName();
            String _firstUpper_23 = StringExtensions.toFirstUpper(_name_37);
            _builder.append(_firstUpper_23, "		");
            _builder.append("(null);");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// assign new value");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("this.mInverse");
        String _name_38 = reference.getName();
        String _firstUpper_24 = StringExtensions.toFirstUpper(_name_38);
        _builder.append(_firstUpper_24, "	");
        _builder.append(" = mInverse");
        String _name_39 = reference.getName();
        String _firstUpper_25 = StringExtensions.toFirstUpper(_name_39);
        _builder.append(_firstUpper_25, "	");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        {
          boolean _or_5 = false;
          int _upperBound_10 = reference.getUpperBound();
          boolean _greaterThan_5 = (_upperBound_10 > 1);
          if (_greaterThan_5) {
            _or_5 = true;
          } else {
            int _upperBound_11 = reference.getUpperBound();
            int _minus_5 = (-1);
            boolean _equals_9 = (_upperBound_11 == _minus_5);
            _or_5 = (_greaterThan_5 || _equals_9);
          }
          if (_or_5) {
            _builder.append("\t");
            _builder.append("if (this.mInverse");
            String _name_40 = reference.getName();
            String _firstUpper_26 = StringExtensions.toFirstUpper(_name_40);
            _builder.append(_firstUpper_26, "	");
            _builder.append(" != null && ! this.mInverse");
            String _name_41 = reference.getName();
            String _firstUpper_27 = StringExtensions.toFirstUpper(_name_41);
            _builder.append(_firstUpper_27, "	");
            _builder.append(".get");
            String _name_42 = reference.getName();
            String _firstUpper_28 = StringExtensions.toFirstUpper(_name_42);
            _builder.append(_firstUpper_28, "	");
            _builder.append("().contains(this)) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("this.mInverse");
            String _name_43 = reference.getName();
            String _firstUpper_29 = StringExtensions.toFirstUpper(_name_43);
            _builder.append(_firstUpper_29, "		");
            _builder.append(".get");
            String _name_44 = reference.getName();
            String _firstUpper_30 = StringExtensions.toFirstUpper(_name_44);
            _builder.append(_firstUpper_30, "		");
            _builder.append("().add(this);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          } else {
            _builder.append("\t");
            _builder.append("if (this.mInverse");
            String _name_45 = reference.getName();
            String _firstUpper_31 = StringExtensions.toFirstUpper(_name_45);
            _builder.append(_firstUpper_31, "	");
            _builder.append(" == null) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("this.mInverse");
            String _name_46 = reference.getName();
            String _firstUpper_32 = StringExtensions.toFirstUpper(_name_46);
            _builder.append(_firstUpper_32, "		");
            _builder.append(".set");
            String _name_47 = reference.getName();
            String _firstUpper_33 = StringExtensions.toFirstUpper(_name_47);
            _builder.append(_firstUpper_33, "		");
            _builder.append("(this);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
        _builder.append("\t");
        _builder.append("mSetDirty(true);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("// ensure consistency - set other containment relations to null");
        _builder.newLine();
        {
          List<EClass> _allDomainEntities_2 = this.javaUtilities.getAllDomainEntities(domainModel);
          List<EReference> _allReferences_2 = this.javaUtilities.getAllReferences(_allDomainEntities_2);
          final Function1<EReference,Boolean> _function_2 = new Function1<EReference,Boolean>() {
              public Boolean apply(final EReference it) {
                boolean _and = false;
                boolean _and_1 = false;
                boolean _isContainment = it.isContainment();
                boolean _equals = (_isContainment == true);
                if (!_equals) {
                  _and_1 = false;
                } else {
                  EClassifier _eType = it.getEType();
                  boolean _equals_1 = Objects.equal(_eType, domainEntity);
                  _and_1 = (_equals && _equals_1);
                }
                if (!_and_1) {
                  _and = false;
                } else {
                  EReference _eOpposite = it.getEOpposite();
                  boolean _equals_2 = Objects.equal(_eOpposite, null);
                  _and = (_and_1 && _equals_2);
                }
                return Boolean.valueOf(_and);
              }
            };
          Iterable<EReference> _filter_2 = IterableExtensions.<EReference>filter(_allReferences_2, _function_2);
          for(final EReference invRef_1 : _filter_2) {
            {
              boolean _notEquals_5 = (!Objects.equal(invRef_1, reference));
              if (_notEquals_5) {
                _builder.append("\t");
                _builder.append("this.mInverse");
                String _name_48 = invRef_1.getName();
                String _firstUpper_34 = StringExtensions.toFirstUpper(_name_48);
                _builder.append(_firstUpper_34, "	");
                _builder.append(" = null;");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  /**
   * Generates the method for checking the equality of a domain object.
   * @param domainModel The domain model.
   * @param domainEntity The domain entity.
   * @param packageInfo Information on the target package for generation.
   */
  private CharSequence generateDomainEntityDomainEquals(final EClass domainEntity, final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public boolean domainContentEquals(MohitoEntity<");
    String _typeOfDomainEntityIdentifier = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
    _builder.append(_typeOfDomainEntityIdentifier, "");
    _builder.append("> reference) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("if (reference instanceof ");
    String _name = domainEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "	");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    String _name_1 = domainEntity.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper_1, "		");
    _builder.append(" ref = (");
    String _name_2 = domainEntity.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_2, "		");
    _builder.append(") reference;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("boolean result = true;");
    _builder.newLine();
    _builder.append("\t\t");
    {
      EList<EClass> _eSuperTypes = domainEntity.getESuperTypes();
      int _size = _eSuperTypes.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("result &= super.domainContentEquals(ref);");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      EList<EStructuralFeature> _eStructuralFeatures = domainEntity.getEStructuralFeatures();
      for(final EStructuralFeature sf : _eStructuralFeatures) {
        {
          boolean _or = false;
          int _upperBound = sf.getUpperBound();
          boolean _greaterThan_1 = (_upperBound > 1);
          if (_greaterThan_1) {
            _or = true;
          } else {
            int _upperBound_1 = sf.getUpperBound();
            int _minus = (-1);
            boolean _equals = (_upperBound_1 == _minus);
            _or = (_greaterThan_1 || _equals);
          }
          if (_or) {
            _builder.append("if (this.");
            String _name_3 = sf.getName();
            String _firstLower = StringExtensions.toFirstLower(_name_3);
            _builder.append(_firstLower, "");
            _builder.append(".size() == ref.");
            String _name_4 = sf.getName();
            String _firstLower_1 = StringExtensions.toFirstLower(_name_4);
            _builder.append(_firstLower_1, "");
            _builder.append(".size()) {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("for (int i = 0; i < this.");
            String _name_5 = sf.getName();
            String _firstLower_2 = StringExtensions.toFirstLower(_name_5);
            _builder.append(_firstLower_2, "	");
            _builder.append(".size(); i++) {");
            _builder.newLineIfNotEmpty();
            {
              Boolean _isPrimitiveType = this.javaUtilities.isPrimitiveType(sf);
              if ((_isPrimitiveType).booleanValue()) {
                _builder.append("\t\t");
                _builder.append("result &= this.");
                String _name_6 = sf.getName();
                String _firstLower_3 = StringExtensions.toFirstLower(_name_6);
                _builder.append(_firstLower_3, "		");
                _builder.append(".get(i).equals(ref.");
                String _name_7 = sf.getName();
                String _firstLower_4 = StringExtensions.toFirstLower(_name_7);
                _builder.append(_firstLower_4, "		");
                _builder.append(".get(i));");
                _builder.newLineIfNotEmpty();
              } else {
                _builder.append("\t\t");
                _builder.append("result &= this.");
                String _name_8 = sf.getName();
                String _firstLower_5 = StringExtensions.toFirstLower(_name_8);
                _builder.append(_firstLower_5, "		");
                _builder.append(".get(i).getId().equals(ref.");
                String _name_9 = sf.getName();
                String _firstLower_6 = StringExtensions.toFirstLower(_name_9);
                _builder.append(_firstLower_6, "		");
                _builder.append(".get(i).getId());");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
            _builder.append("} else {");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("result = false;");
            _builder.newLine();
            _builder.append("}");
            _builder.newLine();
          } else {
            {
              EClassifier _eType = sf.getEType();
              String _instanceClassName = _eType.getInstanceClassName();
              boolean _equals_1 = Objects.equal(_instanceClassName, null);
              if (_equals_1) {
                _builder.append("result &= this.");
                String _name_10 = sf.getName();
                String _firstLower_7 = StringExtensions.toFirstLower(_name_10);
                _builder.append(_firstLower_7, "");
                _builder.append(".equals(ref.");
                String _name_11 = sf.getName();
                String _firstLower_8 = StringExtensions.toFirstLower(_name_11);
                _builder.append(_firstLower_8, "");
                _builder.append(");");
                _builder.newLineIfNotEmpty();
              } else {
                Boolean _isPrimitiveType_1 = this.javaUtilities.isPrimitiveType(sf);
                if ((_isPrimitiveType_1).booleanValue()) {
                  _builder.append("result &= this.");
                  String _name_12 = sf.getName();
                  String _firstLower_9 = StringExtensions.toFirstLower(_name_12);
                  _builder.append(_firstLower_9, "");
                  _builder.append(".equals(ref.");
                  String _name_13 = sf.getName();
                  String _firstLower_10 = StringExtensions.toFirstLower(_name_13);
                  _builder.append(_firstLower_10, "");
                  _builder.append(");");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("result &= this.");
                  String _name_14 = sf.getName();
                  String _firstLower_11 = StringExtensions.toFirstLower(_name_14);
                  _builder.append(_firstLower_11, "");
                  _builder.append(".getId().equals(ref.");
                  String _name_15 = sf.getName();
                  String _firstLower_12 = StringExtensions.toFirstLower(_name_15);
                  _builder.append(_firstLower_12, "");
                  _builder.append(".getId());");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
        }
      }
    }
    {
      List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(domainModel);
      List<EReference> _allReferences = this.javaUtilities.getAllReferences(_allDomainEntities);
      final Function1<EReference,Boolean> _function = new Function1<EReference,Boolean>() {
          public Boolean apply(final EReference it) {
            boolean _and = false;
            boolean _and_1 = false;
            boolean _isContainment = it.isContainment();
            boolean _equals = (_isContainment == true);
            if (!_equals) {
              _and_1 = false;
            } else {
              EClassifier _eType = it.getEType();
              boolean _equals_1 = Objects.equal(_eType, domainEntity);
              _and_1 = (_equals && _equals_1);
            }
            if (!_and_1) {
              _and = false;
            } else {
              EReference _eOpposite = it.getEOpposite();
              boolean _equals_2 = Objects.equal(_eOpposite, null);
              _and = (_and_1 && _equals_2);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EReference> _filter = IterableExtensions.<EReference>filter(_allReferences, _function);
      for(final EReference reference : _filter) {
        _builder.append("result &= this.mInverse");
        String _name_16 = reference.getName();
        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_16);
        _builder.append(_firstUpper_3, "");
        _builder.append(".getId().equals(((");
        String _name_17 = domainEntity.getName();
        String _firstUpper_4 = StringExtensions.toFirstUpper(_name_17);
        _builder.append(_firstUpper_4, "");
        _builder.append(")reference).getMInverse");
        String _name_18 = reference.getName();
        String _firstUpper_5 = StringExtensions.toFirstUpper(_name_18);
        _builder.append(_firstUpper_5, "");
        _builder.append("().getId());");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t");
    _builder.append("return result;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} ");
    _builder.newLine();
    {
      EList<EClass> _eSuperTypes_1 = domainEntity.getESuperTypes();
      int _size_1 = _eSuperTypes_1.size();
      boolean _greaterThan_2 = (_size_1 > 0);
      if (_greaterThan_2) {
        _builder.append("\t");
        _builder.append("else if (reference instanceof ");
        EList<EClass> _eSuperTypes_2 = domainEntity.getESuperTypes();
        EClass _get = _eSuperTypes_2.get(0);
        String _name_19 = _get.getName();
        String _firstUpper_6 = StringExtensions.toFirstUpper(_name_19);
        _builder.append(_firstUpper_6, "	");
        _builder.append(") {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return super.domainContentEquals(reference);");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("\t");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Determines the imports for the provided domain entity.
   * @param packageInfo Information on the current package.
   * @param domainEntity The domain entity.
   * @param domainModel The domain model.
   */
  private Set<String> calculateImports(final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel) {
    HashSet<String> _hashSet = new HashSet<String>();
    Set<String> imports = _hashSet;
    boolean _and = false;
    EList<EAttribute> _eAttributes = domainEntity.getEAttributes();
    int _size = _eAttributes.size();
    boolean _greaterThan = (_size > 0);
    if (!_greaterThan) {
      _and = false;
    } else {
      EList<EAttribute> _eAttributes_1 = domainEntity.getEAttributes();
      final Function1<EAttribute,Boolean> _function = new Function1<EAttribute,Boolean>() {
          public Boolean apply(final EAttribute it) {
            boolean _and = false;
            EClassifier _eType = it.getEType();
            boolean _notEquals = (!Objects.equal(_eType, null));
            if (!_notEquals) {
              _and = false;
            } else {
              EClassifier _eType_1 = it.getEType();
              String _instanceClassName = _eType_1.getInstanceClassName();
              boolean _equals = "java.util.Date".equals(_instanceClassName);
              _and = (_notEquals && _equals);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EAttribute> _filter = IterableExtensions.<EAttribute>filter(_eAttributes_1, _function);
      int _size_1 = IterableExtensions.size(_filter);
      boolean _greaterThan_1 = (_size_1 > 0);
      _and = (_greaterThan && _greaterThan_1);
    }
    if (_and) {
      imports.add("java.util.Date");
    }
    EList<EStructuralFeature> _eAllStructuralFeatures = domainEntity.getEAllStructuralFeatures();
    final Function1<EStructuralFeature,Boolean> _function_1 = new Function1<EStructuralFeature,Boolean>() {
        public Boolean apply(final EStructuralFeature it) {
          boolean _or = false;
          int _upperBound = it.getUpperBound();
          boolean _greaterThan = (_upperBound > 1);
          if (_greaterThan) {
            _or = true;
          } else {
            int _upperBound_1 = it.getUpperBound();
            int _minus = (-1);
            boolean _equals = (_upperBound_1 == _minus);
            _or = (_greaterThan || _equals);
          }
          return Boolean.valueOf(_or);
        }
      };
    Iterable<EStructuralFeature> _filter_1 = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures, _function_1);
    boolean _isEmpty = IterableExtensions.isEmpty(_filter_1);
    boolean _not = (!_isEmpty);
    if (_not) {
      imports.add("java.util.List");
      imports.add("info.multiplatform.mohito.framework.MohitoList");
    }
    imports.add("info.multiplatform.mohito.framework.MohitoEntity");
    imports.add("info.multiplatform.mohito.framework.exceptions.DataLayerException");
    PackageInfo _append = packageInfo.append(MohitoJavaFolderConstants.MODEL_UTIL);
    String _packageName = _append.getPackageName();
    String _plus = (_packageName + PackageInfo.SEPARATOR_PACKAGE);
    String _name = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + "StorageManager");
    imports.add(_plus_2);
    List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(domainModel);
    List<EReference> _allReferences = this.javaUtilities.getAllReferences(_allDomainEntities);
    final Function1<EReference,Boolean> _function_2 = new Function1<EReference,Boolean>() {
        public Boolean apply(final EReference it) {
          boolean _and = false;
          boolean _and_1 = false;
          boolean _isContainment = it.isContainment();
          boolean _equals = (_isContainment == true);
          if (!_equals) {
            _and_1 = false;
          } else {
            EClassifier _eType = it.getEType();
            boolean _equals_1 = Objects.equal(_eType, domainEntity);
            _and_1 = (_equals && _equals_1);
          }
          if (!_and_1) {
            _and = false;
          } else {
            EReference _eOpposite = it.getEOpposite();
            boolean _equals_2 = Objects.equal(_eOpposite, null);
            _and = (_and_1 && _equals_2);
          }
          return Boolean.valueOf(_and);
        }
      };
    Iterable<EReference> _filter_2 = IterableExtensions.<EReference>filter(_allReferences, _function_2);
    for (final EReference reference : _filter_2) {
      String _packageName_1 = packageInfo.getPackageName();
      String _plus_3 = (_packageName_1 + PackageInfo.SEPARATOR_PACKAGE);
      EClass _eContainingClass = reference.getEContainingClass();
      String _name_1 = _eContainingClass.getName();
      String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
      String _plus_4 = (_plus_3 + _firstUpper_1);
      imports.add(_plus_4);
    }
    return imports;
  }
  
  /**
   * Generates the file for the class marking the model of domain elements.
   * 
   * @see #generateModelClassImplementation
   */
  private void generateMohitoEntityClassFile(final IFileSystemAccess fsa, final EPackage domainModel, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _name = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = (_packageDir + _firstUpper);
    String _plus_1 = (_plus + "MohitoEntity.java");
    CharSequence _generateMohitoEntityClassImplementation = this.generateMohitoEntityClassImplementation(packageInfo, domainModel);
    fsa.generateFile(_plus_1, 
      MohitoJavaFolderConstants.SRC_GEN, _generateMohitoEntityClassImplementation);
  }
  
  /**
   * Generates the implementation for the class marking the model of domain elements.
   * @param packageInfo Information on the package used for the model.
   * @param domainModel The package defining the model.
   * 
   * @see #generateModelClassFile
   */
  private CharSequence generateMohitoEntityClassImplementation(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    Set<String> _set = this.javaUtilities.toSet("info.multiplatform.mohito.framework.MohitoEntity");
    CharSequence _statementImports = this.javaUtilities.statementImports(_set);
    _builder.append(_statementImports, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationImportStatement = this.localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationImportStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationImportStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationImportStatement_1 = this.remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationImportStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationImportStatement_1, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**Marker for model entities of the ");
    String _name = domainModel.getName();
    _builder.append(_name, "");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("* {@link #generateUUID()} can be overrridden to provide model-specific generator implementations for generation UUIDs.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    Class<? extends DomainModelTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract class ");
    String _name_1 = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper, "");
    _builder.append("MohitoEntity<PK> extends MohitoEntity<PK> {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**Default constructor.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_2 = domainModel.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_1, "	");
    _builder.append("MohitoEntity() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super ();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Required helper to allow annotations. See #creationTime */");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement = this.localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_1 = this.remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_1, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("protected Long presistedCreationTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Required helper to allow annotations. See #lastUpdateTime */");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_2 = this.localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_2, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_3 = this.remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_3, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("protected Long presistedLastUpdateTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Required helper to allow annotations. See #lastModificationTime */");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_4 = this.localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_4, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_5 = this.remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_5, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("protected Long presistedLastModificationTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Required helper to allow annotations. See #mIsProxy */");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_6 = this.localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_6, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_7 = this.remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_7, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("protected boolean presistedIsProxy;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Required helper to allow annotations. See #mIsDirty */");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_8 = this.localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_8, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_9 = this.remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_9, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("protected boolean presistedIsDirty;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Required helper to allow annotations. See #mDeletionPending */");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_10 = this.localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_10, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateDomainModelMohitoEntityAttributeAnnotationStatement_11 = this.remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement();
    _builder.append(_generateDomainModelMohitoEntityAttributeAnnotationStatement_11, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("protected boolean presistedDeletionPending;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Long getCreationTime() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return presistedCreationTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setCreationTime(Long creationTime) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("presistedCreationTime = creationTime;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.creationTime = creationTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Long getLastUpdateTime() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return presistedLastUpdateTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setLastUpdateTime(Long lastUpdateTime) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.presistedLastUpdateTime = lastUpdateTime;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.lastUpdateTime = lastUpdateTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Long getLastModificationTime() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return presistedLastModificationTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setLastModificationTime(Long lastModificationTime) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.presistedLastModificationTime = lastModificationTime;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.lastModificationTime = lastModificationTime;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean mIsProxy() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return presistedIsProxy;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void mSetProxy(boolean isProxy) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("presistedIsProxy = isProxy;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("mIsProxy = isProxy;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean mIsDirty() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return presistedIsDirty;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void mSetDirty(boolean isDirty) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("presistedIsDirty = isDirty;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("mIsDirty = isDirty;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public boolean mIsDeletionPending() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return presistedDeletionPending;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void mSetDeletionPending(boolean deletionPending) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("presistedIsDirty = true;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("mIsDirty = true;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("presistedDeletionPending = deletionPending;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("mDeletionPending = deletionPending;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
