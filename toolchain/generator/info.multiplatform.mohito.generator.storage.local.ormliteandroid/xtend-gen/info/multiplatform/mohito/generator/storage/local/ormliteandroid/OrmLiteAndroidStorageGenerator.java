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
package info.multiplatform.mohito.generator.storage.local.ormliteandroid;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import info.multiplatform.mohito.generator.IStorageGenerator;
import info.multiplatform.mohito.generator.environment.java.AnnotationUtils;
import info.multiplatform.mohito.generator.environment.java.JavaUtils;
import info.multiplatform.mohito.generator.environment.java.MohitoJavaFolderConstants;
import info.multiplatform.mohito.generator.environment.java.PackageInfo;
import info.multiplatform.mohito.modeling.annotation.database.DatabaseMohitoAnnotationCategory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Generator for a local storage using OrmLite and the built-in Android database (SQLite).
 * @author groenda
 */
@SuppressWarnings("all")
public class OrmLiteAndroidStorageGenerator implements IStorageGenerator {
  /**
   * Utilities for generating Java code.
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
  
  public CharSequence generateManagerImportStatements(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import info.multiplatform.mohito.framework.android.MohitoEntityDaoOrmLiteImpl;");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateManagerEntityDaoCreation(final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("return new MohitoEntityDaoOrmLiteImpl<T, PK>(mohitoEntityClass, this);");
    _builder.newLine();
    return _builder;
  }
  
  public void generateAdditionalDomainEntityArtifacts(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel) {
    EList<EStructuralFeature> _eAllStructuralFeatures = domainEntity.getEAllStructuralFeatures();
    final Function1<EStructuralFeature,Boolean> _function = new Function1<EStructuralFeature,Boolean>() {
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
    Iterable<EStructuralFeature> _filter = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures, _function);
    for (final EStructuralFeature feature : _filter) {
      String _packageDir = packageInfo.getPackageDir();
      String _name = domainEntity.getName();
      String _firstUpper = StringExtensions.toFirstUpper(_name);
      String _plus = (_packageDir + _firstUpper);
      String _plus_1 = (_plus + "DaoImpl.java");
      CharSequence _generateAdditionalDomainEntityDaoImplementation = this.generateAdditionalDomainEntityDaoImplementation(packageInfo, domainEntity);
      fsa.generateFile(_plus_1, 
        MohitoJavaFolderConstants.SRC_GEN, _generateAdditionalDomainEntityDaoImplementation);
    }
    EList<EStructuralFeature> _eAllStructuralFeatures_1 = domainEntity.getEAllStructuralFeatures();
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
    Iterable<EStructuralFeature> _filter_1 = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures_1, _function_1);
    for (final EStructuralFeature feature_1 : _filter_1) {
      String _packageDir_1 = packageInfo.getPackageDir();
      String _name_1 = domainEntity.getName();
      String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
      String _plus_2 = (_packageDir_1 + _firstUpper_1);
      String _plus_3 = (_plus_2 + "_");
      String _name_2 = feature_1.getName();
      String _plus_4 = (_plus_3 + _name_2);
      String _plus_5 = (_plus_4 + ".java");
      CharSequence _generateAdditionalDomainEntityMappingClassImplementation = this.generateAdditionalDomainEntityMappingClassImplementation(packageInfo, domainEntity, feature_1, domainModel);
      fsa.generateFile(_plus_5, 
        MohitoJavaFolderConstants.SRC_GEN, _generateAdditionalDomainEntityMappingClassImplementation);
    }
  }
  
  /**
   * Generate the required DAO implementations to access attributes or references with an upper bound greater than 1 with OrmLite.
   * @param packageInfo The package of the DAO.
   * @param domainEntity The domain entity for which the DAO implementation is to be generated.
   */
  private CharSequence generateAdditionalDomainEntityDaoImplementation(final PackageInfo packageInfo, final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<String> _arrayList = new ArrayList<String>();
      ArrayList<String> list = _arrayList;
      EList<EStructuralFeature> _eAllStructuralFeatures = domainEntity.getEAllStructuralFeatures();
      final Function1<EStructuralFeature,Boolean> _function = new Function1<EStructuralFeature,Boolean>() {
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
      Iterable<EStructuralFeature> _filter = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures, _function);
      for (final EStructuralFeature feature : _filter) {
        String _type = this.javaUtilities.getType(feature);
        boolean _equals = _type.equals("String");
        boolean _not = (!_equals);
        if (_not) {
          PackageInfo _strip = packageInfo.strip();
          String _packageName = _strip.getPackageName();
          String _plus = (_packageName + PackageInfo.SEPARATOR_PACKAGE);
          String _type_1 = this.javaUtilities.getType(feature);
          String _firstUpper = StringExtensions.toFirstUpper(_type_1);
          String _plus_1 = (_plus + _firstUpper);
          list.add(_plus_1);
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
      _builder.append(_statementPackage, "");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      final ArrayList<String> _converted_list = (ArrayList<String>)list;
      Set<String> _set = this.javaUtilities.toSet(((String[])Conversions.unwrapArray(_converted_list, String.class)));
      CharSequence _statementImports = this.javaUtilities.statementImports(_set);
      _builder.append(_statementImports, "");
      _builder.newLineIfNotEmpty();
      PackageInfo _strip_1 = packageInfo.strip();
      String _packageName_1 = _strip_1.getPackageName();
      String _plus_2 = (_packageName_1 + PackageInfo.SEPARATOR_PACKAGE);
      String _name = domainEntity.getName();
      String _firstUpper_1 = StringExtensions.toFirstUpper(_name);
      String _plus_3 = (_plus_2 + _firstUpper_1);
      Set<String> _set_1 = this.javaUtilities.toSet(_plus_3, 
        "java.sql.SQLException", 
        "java.util.List", 
        "java.util.logging.Level", 
        "java.util.logging.Logger", 
        "com.j256.ormlite.dao.BaseDaoImpl", 
        "com.j256.ormlite.dao.Dao", 
        "com.j256.ormlite.dao.DaoManager", 
        "com.j256.ormlite.stmt.DeleteBuilder", 
        "com.j256.ormlite.stmt.QueryBuilder", 
        "com.j256.ormlite.support.ConnectionSource", 
        "com.j256.ormlite.table.TableUtils");
      CharSequence _statementImports_1 = this.javaUtilities.statementImports(_set_1);
      _builder.append(_statementImports_1, "");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.newLine();
      _builder.append("/**Workaround for OrmLite in order to store attributes with an upper bound greater than one in the database.");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* Handles loading/storing the attributes.");
      _builder.newLine();
      _builder.append(" ");
      Class<? extends OrmLiteAndroidStorageGenerator> _class = this.getClass();
      CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
      _builder.append(_statementGenerated, " ");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("public class ");
      String _name_1 = domainEntity.getName();
      String _firstUpper_2 = StringExtensions.toFirstUpper(_name_1);
      _builder.append(_firstUpper_2, "");
      _builder.append("DaoImpl extends BaseDaoImpl<");
      String _name_2 = domainEntity.getName();
      String _firstUpper_3 = StringExtensions.toFirstUpper(_name_2);
      _builder.append(_firstUpper_3, "");
      _builder.append(", ");
      String _typeOfDomainEntityIdentifier = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
      _builder.append(_typeOfDomainEntityIdentifier, "");
      _builder.append("> {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("// initialize additional database tables");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/** Logger for this class. */");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("private static final Logger logger = Logger.getLogger(");
      String _name_3 = domainEntity.getName();
      String _firstUpper_4 = StringExtensions.toFirstUpper(_name_3);
      _builder.append(_firstUpper_4, "	");
      _builder.append("DaoImpl.class.getCanonicalName());");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("// DAOs for each attribute with an upper bound greater than 1");
      _builder.newLine();
      {
        EList<EStructuralFeature> _eAllStructuralFeatures_1 = domainEntity.getEAllStructuralFeatures();
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
        Iterable<EStructuralFeature> _filter_1 = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures_1, _function_1);
        for(final EStructuralFeature feature_1 : _filter_1) {
          _builder.append("\t");
          _builder.append("/** Dao for the attribute behind {@link ");
          String _name_4 = domainEntity.getName();
          String _firstUpper_5 = StringExtensions.toFirstUpper(_name_4);
          _builder.append(_firstUpper_5, "	");
          _builder.append("#get");
          String _name_5 = feature_1.getName();
          String _firstUpper_6 = StringExtensions.toFirstUpper(_name_5);
          _builder.append(_firstUpper_6, "	");
          _builder.append("()}. */");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("private final Dao<");
          String _name_6 = domainEntity.getName();
          String _firstUpper_7 = StringExtensions.toFirstUpper(_name_6);
          _builder.append(_firstUpper_7, "	");
          _builder.append("_");
          String _name_7 = feature_1.getName();
          _builder.append(_name_7, "	");
          _builder.append(", ");
          {
            Boolean _isPrimitiveType = this.javaUtilities.isPrimitiveType(feature_1);
            if ((_isPrimitiveType).booleanValue()) {
              String _type_2 = this.javaUtilities.getType(feature_1);
              _builder.append(_type_2, "	");
            } else {
              EClassifier _eType = feature_1.getEType();
              String _typeOfDomainEntityIdentifier_1 = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) _eType));
              _builder.append(_typeOfDomainEntityIdentifier_1, "	");
            }
          }
          _builder.append("> ");
          String _name_8 = feature_1.getName();
          _builder.append(_name_8, "	");
          _builder.append("Dao; ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public ");
      String _name_9 = domainEntity.getName();
      String _firstUpper_8 = StringExtensions.toFirstUpper(_name_9);
      _builder.append(_firstUpper_8, "	");
      _builder.append("DaoImpl(ConnectionSource connectionSource) throws SQLException {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("super(connectionSource, ");
      String _name_10 = domainEntity.getName();
      String _firstUpper_9 = StringExtensions.toFirstUpper(_name_10);
      _builder.append(_firstUpper_9, "		");
      _builder.append(".class);");
      _builder.newLineIfNotEmpty();
      {
        EList<EStructuralFeature> _eAllStructuralFeatures_2 = domainEntity.getEAllStructuralFeatures();
        final Function1<EStructuralFeature,Boolean> _function_2 = new Function1<EStructuralFeature,Boolean>() {
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
        Iterable<EStructuralFeature> _filter_2 = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures_2, _function_2);
        for(final EStructuralFeature feature_2 : _filter_2) {
          _builder.append("\t\t");
          _builder.append("// ");
          String _name_11 = feature_2.getName();
          _builder.append(_name_11, "		");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("this.");
          String _name_12 = feature_2.getName();
          _builder.append(_name_12, "		");
          _builder.append("Dao = DaoManager.createDao(connectionSource,");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t\t");
          String _name_13 = domainEntity.getName();
          String _firstUpper_10 = StringExtensions.toFirstUpper(_name_13);
          _builder.append(_firstUpper_10, "				");
          _builder.append("_");
          String _name_14 = feature_2.getName();
          _builder.append(_name_14, "				");
          _builder.append(".class);");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public int create(");
      String _name_15 = domainEntity.getName();
      String _firstUpper_11 = StringExtensions.toFirstUpper(_name_15);
      _builder.append(_firstUpper_11, "	");
      _builder.append(" data) throws SQLException {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("int rows = super.create(data);");
      _builder.newLine();
      {
        EList<EStructuralFeature> _eAllStructuralFeatures_3 = domainEntity.getEAllStructuralFeatures();
        final Function1<EStructuralFeature,Boolean> _function_3 = new Function1<EStructuralFeature,Boolean>() {
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
        Iterable<EStructuralFeature> _filter_3 = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures_3, _function_3);
        for(final EStructuralFeature feature_3 : _filter_3) {
          _builder.append("\t\t");
          _builder.append("// ");
          String _name_16 = feature_3.getName();
          _builder.append(_name_16, "		");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _name_17 = domainEntity.getName();
          String _firstUpper_12 = StringExtensions.toFirstUpper(_name_17);
          _builder.append(_firstUpper_12, "		");
          _builder.append("_");
          String _name_18 = feature_3.getName();
          _builder.append(_name_18, "		");
          _builder.append(" ");
          String _name_19 = domainEntity.getName();
          String _firstLower = StringExtensions.toFirstLower(_name_19);
          _builder.append(_firstLower, "		");
          _builder.append("_");
          String _name_20 = feature_3.getName();
          _builder.append(_name_20, "		");
          _builder.append("; ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("for (");
          String _type_3 = this.javaUtilities.getType(feature_3);
          _builder.append(_type_3, "		");
          _builder.append(" value : data.get");
          String _name_21 = feature_3.getName();
          String _firstUpper_13 = StringExtensions.toFirstUpper(_name_21);
          _builder.append(_firstUpper_13, "		");
          _builder.append("()) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t");
          String _name_22 = domainEntity.getName();
          String _firstLower_1 = StringExtensions.toFirstLower(_name_22);
          _builder.append(_firstLower_1, "			");
          _builder.append("_");
          String _name_23 = feature_3.getName();
          _builder.append(_name_23, "			");
          _builder.append(" = new ");
          String _name_24 = domainEntity.getName();
          String _firstUpper_14 = StringExtensions.toFirstUpper(_name_24);
          _builder.append(_firstUpper_14, "			");
          _builder.append("_");
          String _name_25 = feature_3.getName();
          _builder.append(_name_25, "			");
          _builder.append("(data.getId(), value");
          {
            String _type_4 = this.javaUtilities.getType(feature_3);
            boolean _equals_1 = _type_4.equals("String");
            if (_equals_1) {
            } else {
              _builder.append(".getId()");
            }
          }
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t");
          _builder.append("rows += ");
          String _name_26 = feature_3.getName();
          _builder.append(_name_26, "			");
          _builder.append("Dao.create(");
          String _name_27 = domainEntity.getName();
          String _firstLower_2 = StringExtensions.toFirstLower(_name_27);
          _builder.append(_firstLower_2, "			");
          _builder.append("_");
          String _name_28 = feature_3.getName();
          _builder.append(_name_28, "			");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t\t");
      _builder.append("return rows; ");
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
      _builder.append("public int update(");
      String _name_29 = domainEntity.getName();
      String _firstUpper_15 = StringExtensions.toFirstUpper(_name_29);
      _builder.append(_firstUpper_15, "	");
      _builder.append(" data) throws SQLException {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("int rows = super.update(data);");
      _builder.newLine();
      {
        EList<EStructuralFeature> _eAllStructuralFeatures_4 = domainEntity.getEAllStructuralFeatures();
        final Function1<EStructuralFeature,Boolean> _function_4 = new Function1<EStructuralFeature,Boolean>() {
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
        Iterable<EStructuralFeature> _filter_4 = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures_4, _function_4);
        for(final EStructuralFeature feature_4 : _filter_4) {
          _builder.append("\t\t");
          _builder.append("// ");
          String _name_30 = feature_4.getName();
          _builder.append(_name_30, "		");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _name_31 = domainEntity.getName();
          String _firstUpper_16 = StringExtensions.toFirstUpper(_name_31);
          _builder.append(_firstUpper_16, "		");
          _builder.append("_");
          String _name_32 = feature_4.getName();
          _builder.append(_name_32, "		");
          _builder.append(" ");
          String _name_33 = domainEntity.getName();
          String _firstLower_3 = StringExtensions.toFirstLower(_name_33);
          _builder.append(_firstLower_3, "		");
          _builder.append("_");
          String _name_34 = feature_4.getName();
          _builder.append(_name_34, "		");
          _builder.append("; ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("DeleteBuilder<");
          String _name_35 = domainEntity.getName();
          String _firstUpper_17 = StringExtensions.toFirstUpper(_name_35);
          _builder.append(_firstUpper_17, "		");
          _builder.append("_");
          String _name_36 = feature_4.getName();
          _builder.append(_name_36, "		");
          _builder.append(", ");
          {
            Boolean _isPrimitiveType_1 = this.javaUtilities.isPrimitiveType(feature_4);
            if ((_isPrimitiveType_1).booleanValue()) {
              String _type_5 = this.javaUtilities.getType(feature_4);
              _builder.append(_type_5, "		");
            } else {
              EClassifier _eType_1 = feature_4.getEType();
              String _typeOfDomainEntityIdentifier_2 = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) _eType_1));
              _builder.append(_typeOfDomainEntityIdentifier_2, "		");
            }
          }
          _builder.append("> ");
          String _name_37 = feature_4.getName();
          _builder.append(_name_37, "		");
          _builder.append("DeleteBuilder =");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t\t  ");
          String _name_38 = feature_4.getName();
          _builder.append(_name_38, "				  ");
          _builder.append("Dao.deleteBuilder();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _name_39 = feature_4.getName();
          _builder.append(_name_39, "		");
          _builder.append("DeleteBuilder.where().eq(\"containmentId\", data.getId());");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("rows += ");
          String _name_40 = feature_4.getName();
          _builder.append(_name_40, "		");
          _builder.append("DeleteBuilder.delete();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("for (");
          String _type_6 = this.javaUtilities.getType(feature_4);
          _builder.append(_type_6, "		");
          _builder.append(" ");
          String _name_41 = feature_4.getName();
          _builder.append(_name_41, "		");
          _builder.append(" : data.get");
          String _name_42 = feature_4.getName();
          String _firstUpper_18 = StringExtensions.toFirstUpper(_name_42);
          _builder.append(_firstUpper_18, "		");
          _builder.append("()) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t");
          String _name_43 = domainEntity.getName();
          String _firstLower_4 = StringExtensions.toFirstLower(_name_43);
          _builder.append(_firstLower_4, "			");
          _builder.append("_");
          String _name_44 = feature_4.getName();
          _builder.append(_name_44, "			");
          _builder.append(" = new ");
          String _name_45 = domainEntity.getName();
          String _firstUpper_19 = StringExtensions.toFirstUpper(_name_45);
          _builder.append(_firstUpper_19, "			");
          _builder.append("_");
          String _name_46 = feature_4.getName();
          _builder.append(_name_46, "			");
          _builder.append("(data.getId(), ");
          String _name_47 = feature_4.getName();
          _builder.append(_name_47, "			");
          {
            String _type_7 = this.javaUtilities.getType(feature_4);
            boolean _equals_2 = _type_7.equals("String");
            if (_equals_2) {
            } else {
              _builder.append(".getId()");
            }
          }
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t");
          _builder.append("rows += ");
          String _name_48 = feature_4.getName();
          _builder.append(_name_48, "			");
          _builder.append("Dao.create(");
          String _name_49 = domainEntity.getName();
          String _firstLower_5 = StringExtensions.toFirstLower(_name_49);
          _builder.append(_firstLower_5, "			");
          _builder.append("_");
          String _name_50 = feature_4.getName();
          _builder.append(_name_50, "			");
          _builder.append(");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t\t");
      _builder.append("return rows;");
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
      _builder.append("public int delete(");
      String _name_51 = domainEntity.getName();
      String _firstUpper_20 = StringExtensions.toFirstUpper(_name_51);
      _builder.append(_firstUpper_20, "	");
      _builder.append(" data) throws SQLException {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("int rows = super.delete(data);");
      _builder.newLine();
      {
        EList<EStructuralFeature> _eAllStructuralFeatures_5 = domainEntity.getEAllStructuralFeatures();
        final Function1<EStructuralFeature,Boolean> _function_5 = new Function1<EStructuralFeature,Boolean>() {
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
        Iterable<EStructuralFeature> _filter_5 = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures_5, _function_5);
        for(final EStructuralFeature feature_5 : _filter_5) {
          _builder.append("\t\t");
          _builder.append("// ");
          String _name_52 = feature_5.getName();
          _builder.append(_name_52, "		");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("DeleteBuilder<");
          String _name_53 = domainEntity.getName();
          String _firstUpper_21 = StringExtensions.toFirstUpper(_name_53);
          _builder.append(_firstUpper_21, "		");
          _builder.append("_");
          String _name_54 = feature_5.getName();
          _builder.append(_name_54, "		");
          _builder.append(", ");
          {
            Boolean _isPrimitiveType_2 = this.javaUtilities.isPrimitiveType(feature_5);
            if ((_isPrimitiveType_2).booleanValue()) {
              String _type_8 = this.javaUtilities.getType(feature_5);
              _builder.append(_type_8, "		");
            } else {
              EClassifier _eType_2 = feature_5.getEType();
              String _typeOfDomainEntityIdentifier_3 = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) _eType_2));
              _builder.append(_typeOfDomainEntityIdentifier_3, "		");
            }
          }
          _builder.append("> ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("\t\t");
          String _name_55 = feature_5.getName();
          _builder.append(_name_55, "				");
          _builder.append("DeleteBuilder = ");
          String _name_56 = feature_5.getName();
          _builder.append(_name_56, "				");
          _builder.append("Dao.deleteBuilder();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _name_57 = feature_5.getName();
          _builder.append(_name_57, "		");
          _builder.append("DeleteBuilder.where().eq(\"containmentId\", data.getId());");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("rows += ");
          String _name_58 = feature_5.getName();
          _builder.append(_name_58, "		");
          _builder.append("DeleteBuilder.delete();");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t\t");
      _builder.append("return rows;");
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
      _builder.append("public int refresh(");
      String _name_59 = domainEntity.getName();
      String _firstUpper_22 = StringExtensions.toFirstUpper(_name_59);
      _builder.append(_firstUpper_22, "	");
      _builder.append(" data) throws SQLException {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("throw new IllegalStateException(\"Not Supported by this implementation. It is not necessary to refresh generated helper objects.\");");
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
      _builder.append("public ");
      String _name_60 = domainEntity.getName();
      String _firstUpper_23 = StringExtensions.toFirstUpper(_name_60);
      _builder.append(_firstUpper_23, "	");
      _builder.append(" queryForId(");
      String _typeOfDomainEntityIdentifier_4 = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
      _builder.append(_typeOfDomainEntityIdentifier_4, "	");
      _builder.append(" id) throws SQLException {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      String _name_61 = domainEntity.getName();
      String _firstUpper_24 = StringExtensions.toFirstUpper(_name_61);
      _builder.append(_firstUpper_24, "		");
      _builder.append(" result = super.queryForId(id);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      _builder.append("if (result != null) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("boolean oldMIsDirty = result.mIsDirty();");
      _builder.newLine();
      {
        EList<EStructuralFeature> _eAllStructuralFeatures_6 = domainEntity.getEAllStructuralFeatures();
        final Function1<EStructuralFeature,Boolean> _function_6 = new Function1<EStructuralFeature,Boolean>() {
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
        Iterable<EStructuralFeature> _filter_6 = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures_6, _function_6);
        for(final EStructuralFeature feature_6 : _filter_6) {
          _builder.append("\t\t\t");
          _builder.append("// ");
          String _name_62 = feature_6.getName();
          _builder.append(_name_62, "			");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("QueryBuilder<");
          String _name_63 = domainEntity.getName();
          String _firstUpper_25 = StringExtensions.toFirstUpper(_name_63);
          _builder.append(_firstUpper_25, "			");
          _builder.append("_");
          String _name_64 = feature_6.getName();
          _builder.append(_name_64, "			");
          _builder.append(", ");
          {
            Boolean _isPrimitiveType_3 = this.javaUtilities.isPrimitiveType(feature_6);
            if ((_isPrimitiveType_3).booleanValue()) {
              String _type_9 = this.javaUtilities.getType(feature_6);
              _builder.append(_type_9, "			");
            } else {
              EClassifier _eType_3 = feature_6.getEType();
              String _typeOfDomainEntityIdentifier_5 = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) _eType_3));
              _builder.append(_typeOfDomainEntityIdentifier_5, "			");
            }
          }
          _builder.append("> ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("\t\t");
          String _name_65 = feature_6.getName();
          _builder.append(_name_65, "					");
          _builder.append("QueryBuilder = ");
          String _name_66 = feature_6.getName();
          _builder.append(_name_66, "					");
          _builder.append("Dao.queryBuilder();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          String _name_67 = feature_6.getName();
          _builder.append(_name_67, "			");
          _builder.append("QueryBuilder.where().eq(\"containmentId\", id);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("List<");
          String _name_68 = domainEntity.getName();
          String _firstUpper_26 = StringExtensions.toFirstUpper(_name_68);
          _builder.append(_firstUpper_26, "			");
          _builder.append("_");
          String _name_69 = feature_6.getName();
          _builder.append(_name_69, "			");
          _builder.append("> ");
          String _name_70 = feature_6.getName();
          _builder.append(_name_70, "			");
          _builder.append("List = ");
          String _name_71 = feature_6.getName();
          _builder.append(_name_71, "			");
          _builder.append("QueryBuilder.query();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("for (");
          String _name_72 = domainEntity.getName();
          String _firstUpper_27 = StringExtensions.toFirstUpper(_name_72);
          _builder.append(_firstUpper_27, "			");
          _builder.append("_");
          String _name_73 = feature_6.getName();
          _builder.append(_name_73, "			");
          _builder.append(" data : ");
          String _name_74 = feature_6.getName();
          _builder.append(_name_74, "			");
          _builder.append("List) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          String _type_10 = this.javaUtilities.getType(feature_6);
          _builder.append(_type_10, "			");
          _builder.append(" value = ");
          {
            String _type_11 = this.javaUtilities.getType(feature_6);
            boolean _equals_3 = _type_11.equals("String");
            if (_equals_3) {
              _builder.append("data.getValue()");
            } else {
              _builder.append("new ");
              String _type_12 = this.javaUtilities.getType(feature_6);
              String _firstUpper_28 = StringExtensions.toFirstUpper(_type_12);
              _builder.append(_firstUpper_28, "			");
              _builder.append("(data.getValue())");
            }
          }
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("\t");
          _builder.append("result.get");
          String _name_75 = feature_6.getName();
          String _firstUpper_29 = StringExtensions.toFirstUpper(_name_75);
          _builder.append(_firstUpper_29, "				");
          _builder.append("().add(value);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t\t\t");
      _builder.append("result.mSetDirty(oldMIsDirty);");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return result;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  /**
   * Generate the additionally required mapping class for a domain entity with a feature with an upper bound greater than 1.
   * @param packageInfo The package for the implementation.
   * @param domainEntity The domain entity.
   * @param feature The feature.
   */
  private CharSequence generateAdditionalDomainEntityMappingClassImplementation(final PackageInfo packageInfo, final EClass domainEntity, final EStructuralFeature feature, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    Set<String> _set = this.javaUtilities.toSet(
      "com.j256.ormlite.field.DatabaseField", 
      "com.j256.ormlite.table.DatabaseTable");
    CharSequence _statementImports = this.javaUtilities.statementImports(_set);
    _builder.append(_statementImports, "");
    _builder.newLineIfNotEmpty();
    _builder.append("import ");
    PackageInfo _strip = packageInfo.strip();
    String _packageName = _strip.getPackageName();
    _builder.append(_packageName, "");
    _builder.append(".");
    String _name = domainEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    {
      EList<EClassifier> _eClassifiers = domainModel.getEClassifiers();
      EClassifier _eType = feature.getEType();
      boolean _contains = _eClassifiers.contains(_eType);
      if (_contains) {
        _builder.append("import ");
        PackageInfo _strip_1 = packageInfo.strip();
        String _packageName_1 = _strip_1.getPackageName();
        _builder.append(_packageName_1, "");
        _builder.append(".");
        EClassifier _eType_1 = feature.getEType();
        String _name_1 = _eType_1.getName();
        _builder.append(_name_1, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("/**Workaround for OrmLite in order to store attributes with an upper bound greater than one in the database.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This handles the attribute behind {@link ");
    String _name_2 = domainEntity.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_1, " ");
    _builder.append("#get");
    String _name_3 = feature.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_2, " ");
    _builder.append("()}.");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    Class<? extends OrmLiteAndroidStorageGenerator> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("@DatabaseTable(tableName = \"");
    String _name_4 = domainEntity.getName();
    String _firstUpper_3 = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper_3, "");
    _builder.append("_");
    String _name_5 = feature.getName();
    String _firstLower = StringExtensions.toFirstLower(_name_5);
    _builder.append(_firstLower, "");
    _builder.append("\")");
    _builder.newLineIfNotEmpty();
    _builder.append("public class ");
    String _name_6 = domainEntity.getName();
    String _firstUpper_4 = StringExtensions.toFirstUpper(_name_6);
    _builder.append(_firstUpper_4, "");
    _builder.append("_");
    String _name_7 = feature.getName();
    String _firstLower_1 = StringExtensions.toFirstLower(_name_7);
    _builder.append(_firstLower_1, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("/** Unused technical identifier. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@DatabaseField(generatedId = true)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected Long id;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** ID of the MOHITO-Entity to which this entity belongs. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@DatabaseField(index = true)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected ");
    String _typeOfDomainEntityIdentifier = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
    _builder.append(_typeOfDomainEntityIdentifier, "	");
    _builder.append(" containmentId;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("/** Id of the referenced entity. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@DatabaseField(canBeNull = false)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("protected ");
    {
      Boolean _isPrimitiveType = this.javaUtilities.isPrimitiveType(feature);
      if ((_isPrimitiveType).booleanValue()) {
        String _type = this.javaUtilities.getType(feature);
        _builder.append(_type, "	");
      } else {
        EClassifier _eType_2 = feature.getEType();
        String _typeOfDomainEntityIdentifier_1 = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) _eType_2));
        _builder.append(_typeOfDomainEntityIdentifier_1, "	");
      }
    }
    _builder.append(" value;");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_8 = domainEntity.getName();
    String _firstUpper_5 = StringExtensions.toFirstUpper(_name_8);
    _builder.append(_firstUpper_5, "	");
    _builder.append("_");
    String _name_9 = feature.getName();
    String _firstLower_2 = StringExtensions.toFirstLower(_name_9);
    _builder.append(_firstLower_2, "	");
    _builder.append("() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_10 = domainEntity.getName();
    String _firstUpper_6 = StringExtensions.toFirstUpper(_name_10);
    _builder.append(_firstUpper_6, "	");
    _builder.append("_");
    String _name_11 = feature.getName();
    String _firstLower_3 = StringExtensions.toFirstLower(_name_11);
    _builder.append(_firstLower_3, "	");
    _builder.append("(");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    String _typeOfDomainEntityIdentifier_2 = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
    _builder.append(_typeOfDomainEntityIdentifier_2, "			");
    _builder.append(" containmentId, ");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    {
      Boolean _isPrimitiveType_1 = this.javaUtilities.isPrimitiveType(feature);
      if ((_isPrimitiveType_1).booleanValue()) {
        String _type_1 = this.javaUtilities.getType(feature);
        _builder.append(_type_1, "			");
      } else {
        EClassifier _eType_3 = feature.getEType();
        String _typeOfDomainEntityIdentifier_3 = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) _eType_3));
        _builder.append(_typeOfDomainEntityIdentifier_3, "			");
      }
    }
    _builder.append(" value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.containmentId = containmentId;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.value = value;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public Long getId() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return id;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @return The id of the referenced object {@link ");
    EClassifier _eType_4 = feature.getEType();
    String _name_12 = _eType_4.getName();
    String _firstUpper_7 = StringExtensions.toFirstUpper(_name_12);
    _builder.append(_firstUpper_7, "	 ");
    _builder.append("}. The real value for basic types.");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    {
      Boolean _isPrimitiveType_2 = this.javaUtilities.isPrimitiveType(feature);
      if ((_isPrimitiveType_2).booleanValue()) {
        String _type_2 = this.javaUtilities.getType(feature);
        _builder.append(_type_2, "	");
      } else {
        EClassifier _eType_5 = feature.getEType();
        String _typeOfDomainEntityIdentifier_4 = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) _eType_5));
        _builder.append(_typeOfDomainEntityIdentifier_4, "	");
      }
    }
    _builder.append(" getValue() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return value;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param value The id of the referenced {@link ");
    EClassifier _eType_6 = feature.getEType();
    String _name_13 = _eType_6.getName();
    String _firstUpper_8 = StringExtensions.toFirstUpper(_name_13);
    _builder.append(_firstUpper_8, "	 ");
    _builder.append("}. The real value for basic types.");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void setValue(");
    {
      Boolean _isPrimitiveType_3 = this.javaUtilities.isPrimitiveType(feature);
      if ((_isPrimitiveType_3).booleanValue()) {
        String _type_3 = this.javaUtilities.getType(feature);
        _builder.append(_type_3, "	");
      } else {
        EClassifier _eType_7 = feature.getEType();
        String _typeOfDomainEntityIdentifier_5 = this.javaUtilities.getTypeOfDomainEntityIdentifier(((EClass) _eType_7));
        _builder.append(_typeOfDomainEntityIdentifier_5, "	");
      }
    }
    _builder.append(" value) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("this.value = value;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @param value The id of the referenced {@link ");
    String _name_14 = domainEntity.getName();
    String _firstUpper_9 = StringExtensions.toFirstUpper(_name_14);
    _builder.append(_firstUpper_9, "	 ");
    _builder.append("}.");
    _builder.newLineIfNotEmpty();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _typeOfDomainEntityIdentifier_6 = this.javaUtilities.getTypeOfDomainEntityIdentifier(domainEntity);
    _builder.append(_typeOfDomainEntityIdentifier_6, "	");
    _builder.append(" getContainmentId() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("return containmentId;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//REMARK No setter for containment id;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}\t\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateDomainEntityImportStatements(final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    Set<String> _set = this.javaUtilities.toSet(
      "com.j256.ormlite.field.DatabaseField", 
      "com.j256.ormlite.table.DatabaseTable");
    CharSequence _statementImports = this.javaUtilities.statementImports(_set);
    _builder.append(_statementImports, "");
    _builder.newLineIfNotEmpty();
    CharSequence _xifexpression = null;
    EList<EStructuralFeature> _eAllStructuralFeatures = domainEntity.getEAllStructuralFeatures();
    final Function1<EStructuralFeature,Boolean> _function = new Function1<EStructuralFeature,Boolean>() {
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
    Iterable<EStructuralFeature> _filter = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures, _function);
    int _size = IterableExtensions.size(_filter);
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + PackageInfo.SEPARATOR_PACKAGE);
      String _plus_1 = (_plus + MohitoJavaFolderConstants.MODEL_LOCAL);
      String _plus_2 = (_plus_1 + PackageInfo.SEPARATOR_PACKAGE);
      String _name = domainEntity.getName();
      String _firstUpper = StringExtensions.toFirstUpper(_name);
      String _plus_3 = (_plus_2 + _firstUpper);
      String _plus_4 = (_plus_3 + "DaoImpl");
      Set<String> _set_1 = this.javaUtilities.toSet(_plus_4);
      CharSequence _statementImports_1 = this.javaUtilities.statementImports(_set_1);
      _xifexpression = _statementImports_1;
    }
    _builder.append(_xifexpression, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateDomainEntityClassAnnotationStatement(final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel) {
    CharSequence _xblockexpression = null;
    {
      HashMap<String,String> _hashMap = new HashMap<String,String>();
      final Map<String,String> settings = _hashMap;
      CharSequence _xifexpression = null;
      Boolean _valueForAnnotationAsBoolean = this.annotationUtilities.getValueForAnnotationAsBoolean(domainEntity, DatabaseMohitoAnnotationCategory.SERIALIZE_IN_DB);
      boolean _equals = ((_valueForAnnotationAsBoolean).booleanValue() == true);
      if (_equals) {
        CharSequence _xblockexpression_1 = null;
        {
          String _name = domainEntity.getName();
          String _firstUpper = StringExtensions.toFirstUpper(_name);
          String _plus = ("\"" + _firstUpper);
          String _plus_1 = (_plus + "\"");
          settings.put("tableName", _plus_1);
          EList<EStructuralFeature> _eAllStructuralFeatures = domainEntity.getEAllStructuralFeatures();
          final Function1<EStructuralFeature,Boolean> _function = new Function1<EStructuralFeature,Boolean>() {
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
          Iterable<EStructuralFeature> _filter = IterableExtensions.<EStructuralFeature>filter(_eAllStructuralFeatures, _function);
          int _size = IterableExtensions.size(_filter);
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            String _name_1 = domainEntity.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
            String _plus_2 = (_firstUpper_1 + "DaoImpl.class");
            settings.put("daoClass", _plus_2);
          }
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("@DatabaseTable(");
          {
            Set<Entry<String,String>> _entrySet = settings.entrySet();
            boolean _hasElements = false;
            for(final Entry<String,String> setting : _entrySet) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate(", ", "");
              }
              String _key = setting.getKey();
              _builder.append(_key, "");
              _builder.append(" = ");
              String _value = setting.getValue();
              _builder.append(_value, "");
            }
          }
          _builder.append(")");
          _xblockexpression_1 = (_builder);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateDomainEntityStructuralFeatureAnnotationStatement(final PackageInfo packageInfo, final EStructuralFeature feature, final EPackage domainModel) {
    CharSequence _xblockexpression = null;
    {
      HashMap<String,String> _hashMap = new HashMap<String,String>();
      final Map<String,String> settings = _hashMap;
      CharSequence _xifexpression = null;
      boolean _or = false;
      if ((feature instanceof EReference)) {
        _or = true;
      } else {
        Boolean _valueForAnnotationAsBoolean = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_SERIALIZE_IN_DB);
        boolean _equals = ((_valueForAnnotationAsBoolean).booleanValue() == true);
        _or = ((feature instanceof EReference) || _equals);
      }
      if (_or) {
        CharSequence _xblockexpression_1 = null;
        {
          if ((feature instanceof EReference)) {
            final EReference ref = ((EReference) feature);
            boolean _or_1 = false;
            int _upperBound = ref.getUpperBound();
            boolean _greaterThan = (_upperBound > 1);
            if (_greaterThan) {
              _or_1 = true;
            } else {
              int _upperBound_1 = ref.getUpperBound();
              int _minus = (-1);
              boolean _equals_1 = (_upperBound_1 == _minus);
              _or_1 = (_greaterThan || _equals_1);
            }
            if (_or_1) {
              return "";
            } else {
              String _string = Boolean.TRUE.toString();
              settings.put("foreign", _string);
            }
          } else {
            Boolean _valueForAnnotationAsBoolean_1 = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_BUILD_INDEX);
            String _string_1 = _valueForAnnotationAsBoolean_1.toString();
            settings.put("index", _string_1);
            Boolean _valueForAnnotationAsBoolean_2 = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_IS_FOREIGN_KEY);
            String _string_2 = _valueForAnnotationAsBoolean_2.toString();
            settings.put("foreign", _string_2);
          }
          String _valueForAnnotationAsString = this.annotationUtilities.getValueForAnnotationAsString(feature, DatabaseMohitoAnnotationCategory.FIELD_COLUMN_NAME_IN_DB);
          String _defaultValue = DatabaseMohitoAnnotationCategory.FIELD_COLUMN_NAME_IN_DB.getDefaultValue();
          boolean _notEquals = (!Objects.equal(_valueForAnnotationAsString, _defaultValue));
          if (_notEquals) {
            String _valueForAnnotationAsString_1 = this.annotationUtilities.getValueForAnnotationAsString(feature, DatabaseMohitoAnnotationCategory.FIELD_COLUMN_NAME_IN_DB);
            String _plus = ("\"" + _valueForAnnotationAsString_1);
            String _plus_1 = (_plus + "\"");
            settings.put("columnName", _plus_1);
          }
          boolean _or_2 = false;
          Boolean _valueForAnnotationAsBoolean_3 = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_IS_IDENTIFIER);
          boolean _equals_2 = ((_valueForAnnotationAsBoolean_3).booleanValue() == true);
          if (_equals_2) {
            _or_2 = true;
          } else {
            Boolean _valueForAnnotationAsBoolean_4 = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_MARK_AS_GENERATED);
            boolean _equals_3 = ((_valueForAnnotationAsBoolean_4).booleanValue() == true);
            _or_2 = (_equals_2 || _equals_3);
          }
          if (_or_2) {
            Boolean _valueForAnnotationAsBoolean_5 = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_MARK_AS_GENERATED);
            boolean _equals_4 = ((_valueForAnnotationAsBoolean_5).booleanValue() == true);
            if (_equals_4) {
              Boolean _valueForAnnotationAsBoolean_6 = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_MARK_AS_GENERATED);
              String _string_3 = _valueForAnnotationAsBoolean_6.toString();
              settings.put("generatedId", _string_3);
            } else {
              Boolean _valueForAnnotationAsBoolean_7 = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_IS_IDENTIFIER);
              String _string_4 = _valueForAnnotationAsBoolean_7.toString();
              settings.put("id", _string_4);
            }
          }
          Boolean _valueForAnnotationAsBoolean_8 = this.annotationUtilities.getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory.FIELD_IS_NULLABLE);
          String _string_5 = _valueForAnnotationAsBoolean_8.toString();
          settings.put("canBeNull", _string_5);
          int _lowerBound = feature.getLowerBound();
          boolean _equals_5 = (_lowerBound == 0);
          if (_equals_5) {
            settings.put("canBeNull", "true");
          }
          CharSequence _xifexpression_1 = null;
          boolean _or_3 = false;
          int _upperBound_2 = feature.getUpperBound();
          boolean _greaterThan_1 = (_upperBound_2 > 1);
          if (_greaterThan_1) {
            _or_3 = true;
          } else {
            int _upperBound_3 = feature.getUpperBound();
            int _minus_1 = (-1);
            boolean _equals_6 = (_upperBound_3 == _minus_1);
            _or_3 = (_greaterThan_1 || _equals_6);
          }
          if (_or_3) {
            _xifexpression_1 = null;
          } else {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("@DatabaseField(");
            {
              Set<Entry<String,String>> _entrySet = settings.entrySet();
              boolean _hasElements = false;
              for(final Entry<String,String> setting : _entrySet) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(", ", "");
                }
                String _key = setting.getKey();
                _builder.append(_key, "");
                _builder.append(" = ");
                String _value = setting.getValue();
                _builder.append(_value, "");
              }
            }
            _builder.append(")");
            _xifexpression_1 = _builder;
          }
          _xblockexpression_1 = (_xifexpression_1);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public void generateAdditionalDomainModelArtifacts(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EPackage domainModel) {
    String _packageDir = packageInfo.getPackageDir();
    String _name = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = (_packageDir + _firstUpper);
    String _plus_1 = (_plus + "DatabaseHelper.java");
    CharSequence _generateDatabaseHelperImplementation = this.generateDatabaseHelperImplementation(packageInfo, domainModel);
    fsa.generateFile(_plus_1, 
      MohitoJavaFolderConstants.SRC_GEN, _generateDatabaseHelperImplementation);
  }
  
  /**
   * Generates the implementation of the database helper, which allows automatic connection to the database used in Android.
   * @param info Information on the package for the implementation.
   * @param domainModel The domain model.
   */
  public CharSequence generateDatabaseHelperImplementation(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementPackage = this.javaUtilities.statementPackage(packageInfo);
    _builder.append(_statementPackage, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    PackageInfo _strip = packageInfo.strip();
    String _packageName = _strip.getPackageName();
    String _plus = (_packageName + PackageInfo.SEPARATOR_PACKAGE);
    String _plus_1 = (_plus + "*");
    Set<String> _set = this.javaUtilities.toSet(_plus_1, 
      "info.multiplatform.mohito.framework.android.IAndroidDatabaseHelper", 
      "java.sql.SQLException", 
      "android.content.Context", 
      "android.database.sqlite.SQLiteDatabase", 
      "android.util.Log", 
      "com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper", 
      "com.j256.ormlite.support.ConnectionSource", 
      "com.j256.ormlite.table.TableUtils");
    CharSequence _statementImports = this.javaUtilities.statementImports(_set);
    _builder.append(_statementImports, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Database helper class used to manage the creation and upgrading of your database. This class also usually provides");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* the DAOs used by the other classes.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    String _name = domainModel.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("DatabaseHelper extends OrmLiteSqliteOpenHelper implements IAndroidDatabaseHelper {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("private static String LOG_TAG = ");
    String _name_1 = domainModel.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper_1, "	");
    _builder.append("DatabaseHelper.class.getSimpleName();");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** Name of the database, also used as file name on Android to store the database. */");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static final String DATABASE_NAME = \"");
    String _name_2 = domainModel.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_2, "	");
    _builder.append(".db\";");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/** any time you make changes to your database objects, you may have to increase the database version");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static final int DATABASE_VERSION = 1;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public ");
    String _name_3 = domainModel.getName();
    String _firstUpper_3 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_3, "	");
    _builder.append("DatabaseHelper(Context context) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("super(context, DATABASE_NAME, null, DATABASE_VERSION);");
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
    _builder.append("* This is called when the database must be created for the first time. ");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Usually you should call createTable statements here to create");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* the tables that will store your data.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.i(LOG_TAG, \"onCreate: Creating tables in the database for storing the MOHITO domain model ");
    String _name_4 = domainModel.getName();
    String _firstUpper_4 = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper_4, "		");
    _builder.append(".\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    CharSequence _createStatementsForHandlingDatabaseTable = this.createStatementsForHandlingDatabaseTable(domainModel, true, true);
    _builder.append(_createStatementsForHandlingDatabaseTable, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* This is called when your application is upgraded and it has a higher version number. This allows you to adjust");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* the various data to match the new version number.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.i(LOG_TAG, \"onUpgrade: Upgrading tables in the database for storing the MOHITO domain model ");
    String _name_5 = domainModel.getName();
    String _firstUpper_5 = StringExtensions.toFirstUpper(_name_5);
    _builder.append(_firstUpper_5, "		");
    _builder.append(".\");");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    CharSequence _createStatementsForHandlingDatabaseTable_1 = this.createStatementsForHandlingDatabaseTable(domainModel, false, true);
    _builder.append(_createStatementsForHandlingDatabaseTable_1, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("// after we drop the old databases, we create the new ones");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _createStatementsForHandlingDatabaseTable_2 = this.createStatementsForHandlingDatabaseTable(domainModel, true, false);
    _builder.append(_createStatementsForHandlingDatabaseTable_2, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void deleteDb() {");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _createStatementsForHandlingDatabaseTable_3 = this.createStatementsForHandlingDatabaseTable(domainModel, false, true);
    _builder.append(_createStatementsForHandlingDatabaseTable_3, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("// after we drop the old databases, we create the new ones");
    _builder.newLine();
    _builder.append("\t\t");
    CharSequence _createStatementsForHandlingDatabaseTable_4 = this.createStatementsForHandlingDatabaseTable(domainModel, true, false);
    _builder.append(_createStatementsForHandlingDatabaseTable_4, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void registerDatabaseHelper(Context arg0, IAndroidDatabaseHelper arg1) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// not required by this version of the helper.");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new UnsupportedOperationException();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void unregisterDatabaseHelper(Context arg0) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// not required by this version of the helper.");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new UnsupportedOperationException();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * creates the statements for using table utility functions on all elements requiring database tables.
   * @param The domain model.
   * @param create Table creation if equal to true, table deletion otherwise.
   * @param continueOnError Flag if error message should only be logged or an exceptions should be thrown.
   */
  public CharSequence createStatementsForHandlingDatabaseTable(final EPackage domainModel, final boolean create, final boolean continueOnError) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<EClassifier> _eClassifiers = domainModel.getEClassifiers();
      final Function1<EClassifier,Boolean> _function = new Function1<EClassifier,Boolean>() {
          public Boolean apply(final EClassifier it) {
            boolean _not = (!(it instanceof EEnum));
            return Boolean.valueOf(_not);
          }
        };
      Iterable<EClassifier> _filter = IterableExtensions.<EClassifier>filter(_eClassifiers, _function);
      for(final EClassifier classifier : _filter) {
        {
          if (create) {
            String _name = classifier.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name);
            String _plus = ("TableUtils.createTable(connectionSource, " + _firstUpper);
            String _plus_1 = (_plus + ".class);");
            CharSequence _executeStatementsForHandlingDatabaseTable = this.executeStatementsForHandlingDatabaseTable(_plus_1, continueOnError);
            _builder.append(_executeStatementsForHandlingDatabaseTable, "");
            _builder.newLineIfNotEmpty();
          } else {
            String _name_1 = classifier.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
            String _plus_2 = ("TableUtils.dropTable(connectionSource, " + _firstUpper_1);
            String _plus_3 = (_plus_2 + ".class, true);");
            CharSequence _executeStatementsForHandlingDatabaseTable_1 = this.executeStatementsForHandlingDatabaseTable(_plus_3, continueOnError);
            _builder.append(_executeStatementsForHandlingDatabaseTable_1, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          if ((classifier instanceof EClass)) {
            {
              EList<EStructuralFeature> _eAllStructuralFeatures = ((EClass) classifier).getEAllStructuralFeatures();
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
              for(final EStructuralFeature feature : _filter_1) {
                {
                  if (create) {
                    String _name_2 = classifier.getName();
                    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
                    String _plus_4 = ("TableUtils.createTable(connectionSource, " + _firstUpper_2);
                    String _plus_5 = (_plus_4 + "_");
                    String _name_3 = feature.getName();
                    String _plus_6 = (_plus_5 + _name_3);
                    String _plus_7 = (_plus_6 + ".class);");
                    CharSequence _executeStatementsForHandlingDatabaseTable_2 = this.executeStatementsForHandlingDatabaseTable(_plus_7, continueOnError);
                    _builder.append(_executeStatementsForHandlingDatabaseTable_2, "");
                    _builder.newLineIfNotEmpty();
                  } else {
                    String _name_4 = classifier.getName();
                    String _firstUpper_3 = StringExtensions.toFirstUpper(_name_4);
                    String _plus_8 = ("TableUtils.dropTable(connectionSource, " + _firstUpper_3);
                    String _plus_9 = (_plus_8 + "_");
                    String _name_5 = feature.getName();
                    String _plus_10 = (_plus_9 + _name_5);
                    String _plus_11 = (_plus_10 + ".class, true);");
                    CharSequence _executeStatementsForHandlingDatabaseTable_3 = this.executeStatementsForHandlingDatabaseTable(_plus_11, continueOnError);
                    _builder.append(_executeStatementsForHandlingDatabaseTable_3, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  /**
   * creates the statements for executing database function in a safe way.
   * @param statement The executed statement.
   * @param continueOnError Flag if error message should only be logged or an exceptions should be thrown.
   */
  public CharSequence executeStatementsForHandlingDatabaseTable(final String statement, final boolean continueOnError) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(statement, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("} catch (SQLException e) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Log.e(LOG_TAG, \"Can\'t execute statement: ");
    _builder.append(statement, "	");
    _builder.append("\", e);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      boolean _not = (!continueOnError);
      if (_not) {
        _builder.append("throw new RuntimeException(e);");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateAdditionalDomainEntityStatements(final EClass domainEntity) {
    return null;
  }
  
  public CharSequence generateAdditionalManagerEntityDaoStatements(final PackageInfo packageInfo, final EPackage domainModel) {
    return null;
  }
  
  public CharSequence generateAdditionalManagerEntityDaoConstructorStatements(final PackageInfo packageInfo, final EPackage domainModel) {
    return null;
  }
  
  public CharSequence generateDaoManagerImplementsStatement() {
    return null;
  }
  
  public CharSequence generateDomainModelMohitoEntityAttributeAnnotationStatement() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@DatabaseField(index = false, foreign = false, canBeNull = true)");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateDomainModelMohitoEntityAttributeAnnotationImportStatement() {
    StringConcatenation _builder = new StringConcatenation();
    Set<String> _set = this.javaUtilities.toSet(
      "com.j256.ormlite.field.DatabaseField");
    CharSequence _statementImports = this.javaUtilities.statementImports(_set);
    _builder.append(_statementImports, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
