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
import info.multiplatform.mohito.generator.IStorageGenerator;
import info.multiplatform.mohito.generator.environment.java.AnnotationUtils;
import info.multiplatform.mohito.generator.environment.java.DomainModelTemplate;
import info.multiplatform.mohito.generator.environment.java.JavaUtils;
import info.multiplatform.mohito.generator.environment.java.MohitoJavaFolderConstants;
import info.multiplatform.mohito.generator.environment.java.PackageInfo;
import info.multiplatform.mohito.modeling.annotation.synchronization.SynchronizationMohitoAnnotationCategory;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class UtilitiesTemplate {
  /**
   * Utilities for MOHITO-Models and Java generation.
   */
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  /**
   * Utilities for MOHITO-Models and annotations.
   */
  @Inject
  @Extension
  private AnnotationUtils annotationUtilities;
  
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
   * Generates the utility artifacts.
   */
  public Object generateUtilityArtifacts(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EPackage domainModel) {
    Object _xblockexpression = null;
    {
      String _packageDir = packageInfo.getPackageDir();
      String _name = domainModel.getName();
      String _firstUpper = StringExtensions.toFirstUpper(_name);
      String _plus = (_packageDir + _firstUpper);
      String _plus_1 = (_plus + "DaoManager.java");
      CharSequence _generateModelDaoManagerImplementation = this.generateModelDaoManagerImplementation(packageInfo, domainModel);
      fsa.generateFile(_plus_1, 
        MohitoJavaFolderConstants.SRC_GEN, _generateModelDaoManagerImplementation);
      String _packageDir_1 = packageInfo.getPackageDir();
      String _name_1 = domainModel.getName();
      String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
      String _plus_2 = (_packageDir_1 + _firstUpper_1);
      String _plus_3 = (_plus_2 + "StorageManager.java");
      CharSequence _generateModelStorageMangerImplementation = this.generateModelStorageMangerImplementation(packageInfo, domainModel);
      fsa.generateFile(_plus_3, 
        MohitoJavaFolderConstants.SRC_GEN, _generateModelStorageMangerImplementation);
      boolean _notEquals = (!Objects.equal(this.localStorageGenerator, null));
      if (_notEquals) {
        String _packageDir_2 = packageInfo.getPackageDir();
        String _plus_4 = (_packageDir_2 + "LocalDaoManager.java");
        CharSequence _generateLocalDaoManagerImplementation = this.generateLocalDaoManagerImplementation(packageInfo, domainModel);
        fsa.generateFile(_plus_4, 
          MohitoJavaFolderConstants.SRC_GEN, _generateLocalDaoManagerImplementation);
      }
      boolean _notEquals_1 = (!Objects.equal(this.remoteStorageGenerator, null));
      if (_notEquals_1) {
        String _packageDir_3 = packageInfo.getPackageDir();
        String _plus_5 = (_packageDir_3 + "RemoteDaoManager.java");
        CharSequence _generateRemoteDaoManagerImplementation = this.generateRemoteDaoManagerImplementation(packageInfo, domainModel);
        fsa.generateFile(_plus_5, 
          MohitoJavaFolderConstants.SRC_GEN, _generateRemoteDaoManagerImplementation);
      }
      Object _xifexpression = null;
      boolean _and = false;
      boolean _notEquals_2 = (!Objects.equal(this.localStorageGenerator, null));
      if (!_notEquals_2) {
        _and = false;
      } else {
        boolean _notEquals_3 = (!Objects.equal(this.remoteStorageGenerator, null));
        _and = (_notEquals_2 && _notEquals_3);
      }
      if (_and) {
        String _packageDir_4 = packageInfo.getPackageDir();
        String _plus_6 = (_packageDir_4 + "SynchronizingDaoManager.java");
        CharSequence _generateSynchronizingDaoManagerImplementation = this.generateSynchronizingDaoManagerImplementation(packageInfo, domainModel);
        fsa.generateFile(_plus_6, 
          MohitoJavaFolderConstants.SRC_GEN, _generateSynchronizingDaoManagerImplementation);
      } else {
        _xifexpression = null;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  /**
   * Generates the DaoManager implementation for the domain model.
   * @param packageInfo The package for the implementation.
   * @param domainModel The domain model.
   */
  public CharSequence generateModelDaoManagerImplementation(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    PackageInfo _strip = packageInfo.strip();
    CharSequence _statementImportsNonAbstractDomainEntities = this.javaUtilities.statementImportsNonAbstractDomainEntities(_strip, domainModel);
    _builder.append(_statementImportsNonAbstractDomainEntities, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**Model-specific part of DAO-Managers with convenient access to DAOs of MOHITO-Entities of this model.");
    _builder.newLine();
    Class<? extends UtilitiesTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public abstract class ");
    String _name = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("DaoManager extends DaoManager {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**Creates a new instance.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.");
    _builder.newLine();
    _builder.append("\t");
    Class<? extends UtilitiesTemplate> _class_1 = this.getClass();
    CharSequence _statementGenerated_1 = this.javaUtilities.statementGenerated(_class_1);
    _builder.append(_statementGenerated_1, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_1 = domainModel.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper_1, "	");
    _builder.append("DaoManager(boolean useCache) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(useCache);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    {
      EList<EClassifier> _eClassifiers = domainModel.getEClassifiers();
      for(final EClassifier entity : _eClassifiers) {
        {
          boolean _and = false;
          if (!(entity instanceof EClass)) {
            _and = false;
          } else {
            boolean _isAbstract = ((EClass) entity).isAbstract();
            boolean _not = (!_isAbstract);
            _and = ((entity instanceof EClass) && _not);
          }
          if (_and) {
            _builder.append("\t");
            _builder.append("/**");
            _builder.newLine();
            _builder.append("\t");
            _builder.append(" ");
            _builder.append("* @return DAO for {@link ");
            String _name_2 = entity.getName();
            String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
            _builder.append(_firstUpper_2, "	 ");
            _builder.append("} MOHITO-Entities.");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append(" ");
            _builder.append("*/");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("public MohitoEntityDao<");
            String _name_3 = entity.getName();
            String _firstUpper_3 = StringExtensions.toFirstUpper(_name_3);
            _builder.append(_firstUpper_3, "	");
            _builder.append(", ");
            String _typeOfDomainEntityIdentifier = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) entity));
            _builder.append(_typeOfDomainEntityIdentifier, "	");
            _builder.append("> ");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t\t");
            _builder.append("get");
            String _name_4 = entity.getName();
            String _firstUpper_4 = StringExtensions.toFirstUpper(_name_4);
            _builder.append(_firstUpper_4, "			");
            _builder.append("Dao() {");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("return getEntityDao(");
            String _name_5 = entity.getName();
            String _firstUpper_5 = StringExtensions.toFirstUpper(_name_5);
            _builder.append(_firstUpper_5, "		");
            _builder.append(".class); ");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates the StorageManager implementation for the domain model.
   * @param packageInfo The package for the implementation.
   * @param domainModel The domain model.
   */
  public CharSequence generateModelStorageMangerImplementation(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("import info.multiplatform.mohito.framework.StorageManager;");
    _builder.newLine();
    PackageInfo _strip = packageInfo.strip();
    CharSequence _statementImportsNonAbstractDomainEntities = this.javaUtilities.statementImportsNonAbstractDomainEntities(_strip, domainModel);
    _builder.append(_statementImportsNonAbstractDomainEntities, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**Entity Manager for ");
    String _name = domainModel.getName();
    _builder.append(_name, "");
    _builder.append(" entities.");
    _builder.newLineIfNotEmpty();
    Class<? extends UtilitiesTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    String _name_1 = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper, "");
    _builder.append("StorageManager extends StorageManager<");
    String _name_2 = domainModel.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_1, "");
    _builder.append("DaoManager>{");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("/**Singleton representing this manager.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static final ");
    String _name_3 = domainModel.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_2, "	");
    _builder.append("StorageManager mINSTANCE = new ");
    String _name_4 = domainModel.getName();
    String _firstUpper_3 = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper_3, "	");
    _builder.append("StorageManager();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**Default constructor creating local and remote DAO managers.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@SuppressWarnings({ \"unchecked\", \"rawtypes\" })");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_5 = domainModel.getName();
    String _firstUpper_4 = StringExtensions.toFirstUpper(_name_5);
    _builder.append(_firstUpper_4, "	");
    _builder.append("StorageManager() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(");
    _builder.newLine();
    _builder.append("\t\t\t");
    {
      boolean _equals = Objects.equal(this.localStorageGenerator, null);
      if (_equals) {
        _builder.append("null");
      } else {
        _builder.append("new LocalDaoManager(true)");
      }
    }
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    {
      boolean _equals_1 = Objects.equal(this.remoteStorageGenerator, null);
      if (_equals_1) {
        _builder.append("null");
      } else {
        _builder.append("new RemoteDaoManager(");
        {
          boolean _equals_2 = Objects.equal(this.localStorageGenerator, null);
          if (_equals_2) {
            _builder.append("true");
          } else {
            _builder.append("false");
          }
        }
        _builder.append(")");
      }
    }
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    {
      boolean _or = false;
      boolean _equals_3 = Objects.equal(this.localStorageGenerator, null);
      if (_equals_3) {
        _or = true;
      } else {
        boolean _equals_4 = Objects.equal(this.remoteStorageGenerator, null);
        _or = (_equals_3 || _equals_4);
      }
      if (_or) {
        _builder.append("null");
      } else {
        _builder.append("new SynchronizingDaoManager(false)");
      }
    }
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    Boolean _valueForAnnotationAsBoolean = this.annotationUtilities.getValueForAnnotationAsBoolean(domainModel, SynchronizationMohitoAnnotationCategory.PREFER_WORKING_LOCAL);
    _builder.append(_valueForAnnotationAsBoolean, "			");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    Boolean _valueForAnnotationAsBoolean_1 = this.annotationUtilities.getValueForAnnotationAsBoolean(domainModel, SynchronizationMohitoAnnotationCategory.RESOLVE_CONFLICTS_AUTOMATICALLY);
    _builder.append(_valueForAnnotationAsBoolean_1, "			");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    Boolean _valueForAnnotationAsBoolean_2 = this.annotationUtilities.getValueForAnnotationAsBoolean(domainModel, SynchronizationMohitoAnnotationCategory.SERVER_WINS_AUTOMATIC_CONFLICT_RESOLUTION);
    _builder.append(_valueForAnnotationAsBoolean_2, "			");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    Boolean _valueForAnnotationAsBoolean_3 = this.annotationUtilities.getValueForAnnotationAsBoolean(domainModel, SynchronizationMohitoAnnotationCategory.AUTO_SYNC_ON_REMOTE_AVAILABLE);
    _builder.append(_valueForAnnotationAsBoolean_3, "			");
    _builder.append(", ");
    _builder.newLineIfNotEmpty();
    {
      EList<EClassifier> _eClassifiers = domainModel.getEClassifiers();
      final Function1<EClassifier,Boolean> _function = new Function1<EClassifier,Boolean>() {
          public Boolean apply(final EClassifier it) {
            boolean _and = false;
            if (!(it instanceof EClass)) {
              _and = false;
            } else {
              boolean _isAbstract = ((EClass) it).isAbstract();
              boolean _equals = (_isAbstract == false);
              _and = ((it instanceof EClass) && _equals);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<EClassifier> _filter = IterableExtensions.<EClassifier>filter(_eClassifiers, _function);
      boolean _hasElements = false;
      for(final EClassifier entity : _filter) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",", "			");
        }
        _builder.append("\t\t\t");
        String _name_6 = entity.getName();
        String _firstUpper_5 = StringExtensions.toFirstUpper(_name_6);
        _builder.append(_firstUpper_5, "			");
        _builder.append(".class");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t\t\t");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append(");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("getLocalStorageManager().setStorageManager((StorageManager)this);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("getRemoteStorageManager().setStorageManager((StorageManager)this);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates the LocalDaoManager implementation for the domain model.
   * @param packageInfo The package for the implementation.
   * @param domainModel The domain model.
   */
  public CharSequence generateLocalDaoManagerImplementation(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    Set<String> _set = this.javaUtilities.toSet("info.multiplatform.mohito.framework.MohitoEntity", 
      "info.multiplatform.mohito.framework.MohitoEntityDao");
    CharSequence _statementImports = this.javaUtilities.statementImports(_set);
    _builder.append(_statementImports, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateManagerImportStatements = this.localStorageGenerator.generateManagerImportStatements(packageInfo, domainModel);
    _builder.append(_generateManagerImportStatements, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**DAO Manager for remote access.");
    _builder.newLine();
    Class<? extends UtilitiesTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class LocalDaoManager extends ");
    String _name = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("DaoManager ");
    CharSequence _generateDaoManagerImplementsStatement = this.localStorageGenerator.generateDaoManagerImplementsStatement();
    _builder.append(_generateDaoManagerImplementsStatement, "");
    _builder.append("  {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**Creates a new instance.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public LocalDaoManager(boolean useCache) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super(useCache);");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateAdditionalManagerEntityDaoConstructorStatements = this.localStorageGenerator.generateAdditionalManagerEntityDaoConstructorStatements(packageInfo, domainModel);
    _builder.append(_generateAdditionalManagerEntityDaoConstructorStatements, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> doGetEntityDao(Class<T> mohitoEntityClass) {");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateManagerEntityDaoCreation = this.localStorageGenerator.generateManagerEntityDaoCreation(domainModel);
    _builder.append(_generateManagerEntityDaoCreation, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateAdditionalManagerEntityDaoStatements = this.localStorageGenerator.generateAdditionalManagerEntityDaoStatements(packageInfo, domainModel);
    _builder.append(_generateAdditionalManagerEntityDaoStatements, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates the RemoteDaoManager implementation for the domain model.
   * @param packageInfo The package for the implementation.
   * @param domainModel The domain model.
   */
  public CharSequence generateRemoteDaoManagerImplementation(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    Set<String> _set = this.javaUtilities.toSet("info.multiplatform.mohito.framework.MohitoEntity", 
      "info.multiplatform.mohito.framework.MohitoEntityDao");
    CharSequence _statementImports = this.javaUtilities.statementImports(_set);
    _builder.append(_statementImports, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateManagerImportStatements = this.remoteStorageGenerator.generateManagerImportStatements(packageInfo, domainModel);
    _builder.append(_generateManagerImportStatements, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**DAO Manager for remote access.");
    _builder.newLine();
    Class<? extends UtilitiesTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class RemoteDaoManager extends ");
    String _name = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("DaoManager ");
    CharSequence _generateDaoManagerImplementsStatement = this.remoteStorageGenerator.generateDaoManagerImplementsStatement();
    _builder.append(_generateDaoManagerImplementsStatement, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**Creates a new instance.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public RemoteDaoManager(boolean useCache) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super(useCache);");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateAdditionalManagerEntityDaoConstructorStatements = this.remoteStorageGenerator.generateAdditionalManagerEntityDaoConstructorStatements(packageInfo, domainModel);
    _builder.append(_generateAdditionalManagerEntityDaoConstructorStatements, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> doGetEntityDao(Class<T> mohitoEntityClass) {");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _generateManagerEntityDaoCreation = this.remoteStorageGenerator.generateManagerEntityDaoCreation(domainModel);
    _builder.append(_generateManagerEntityDaoCreation, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _generateAdditionalManagerEntityDaoStatements = this.remoteStorageGenerator.generateAdditionalManagerEntityDaoStatements(packageInfo, domainModel);
    _builder.append(_generateAdditionalManagerEntityDaoStatements, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates the SynchonizingDaoManager implementation for the domain model.
   * @param packageInfo The package for the implementation.
   * @param domainModel The domain model.
   */
  public CharSequence generateSynchronizingDaoManagerImplementation(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    Set<String> _set = this.javaUtilities.toSet("info.multiplatform.mohito.framework.MohitoEntity", 
      "info.multiplatform.mohito.framework.MohitoEntityDao", 
      "info.multiplatform.mohito.framework.synchronization.MohitoEntityDaoSynchronizingImpl");
    CharSequence _statementImports = this.javaUtilities.statementImports(_set);
    _builder.append(_statementImports, "	");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**DAO manager handling the synchronization between local and remote storages.");
    _builder.newLine();
    _builder.append("\t");
    Class<? extends UtilitiesTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public class SynchronizingDaoManager extends ");
    String _name = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "	");
    _builder.append("DaoManager {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("/**Creates a new instance.");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("* @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("public SynchronizingDaoManager(boolean useCache) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("super(useCache);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("protected <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> doGetEntityDao(");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("Class<T> mohitoEntityClass) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return new MohitoEntityDaoSynchronizingImpl<T, PK>(mohitoEntityClass, this);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
}
