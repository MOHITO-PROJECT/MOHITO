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
import info.multiplatform.mohito.generator.environment.java.AnnotationUtils;
import info.multiplatform.mohito.generator.environment.java.PackageInfo;
import info.multiplatform.mohito.generator.environment.java.StringArray;
import info.multiplatform.mohito.modeling.annotation.database.DatabaseMohitoAnnotationCategory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class JavaUtils {
  /**
   * Utilities for handling annotations of MOHITO-Models.
   */
  @Inject
  @Extension
  private AnnotationUtils annotationUtilities;
  
  /**
   * Ensure that a provided identifier is a java-compatible.
   * @throw IllegalArgumentException If the identifier is not compatible.
   */
  public void ensureJavaCompatibleName(final String identifier) {
    final char[] chars = identifier.toCharArray();
    boolean first = true;
    boolean _or = false;
    boolean _equals = Objects.equal(identifier, null);
    if (_equals) {
      _or = true;
    } else {
      int _length = identifier.length();
      boolean _equals_1 = (_length == 0);
      _or = (_equals || _equals_1);
    }
    if (_or) {
      String _plus = ("The provided identifier \'" + identifier);
      String _plus_1 = (_plus + "\' is not compatible with the Java naming convention. The identifier is NULL or empty.");
      IllegalArgumentException _illegalArgumentException = new IllegalArgumentException(_plus_1);
      throw _illegalArgumentException;
    }
    for (final char chr : chars) {
      if (first) {
        boolean _isJavaIdentifierStart = Character.isJavaIdentifierStart(chr);
        boolean _not = (!_isJavaIdentifierStart);
        if (_not) {
          String _plus_2 = ("The provided identifier \'" + identifier);
          String _plus_3 = (_plus_2 + "\' is not compatible with the Java naming convention. The first character is an illegal start character.");
          IllegalArgumentException _illegalArgumentException_1 = new IllegalArgumentException(_plus_3);
          throw _illegalArgumentException_1;
        }
        first = false;
      } else {
        boolean _isJavaIdentifierPart = Character.isJavaIdentifierPart(chr);
        boolean _not_1 = (!_isJavaIdentifierPart);
        if (_not_1) {
          String _plus_4 = ("The provided identifier \'" + identifier);
          String _plus_5 = (_plus_4 + "\' is not compatible with the Java naming convention. The character \'");
          String _plus_6 = (_plus_5 + Character.valueOf(chr));
          String _plus_7 = (_plus_6 + "\' is an illegal character.");
          IllegalArgumentException _illegalArgumentException_2 = new IllegalArgumentException(_plus_7);
          throw _illegalArgumentException_2;
        }
      }
    }
    StringArray _stringArray = new StringArray("abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const", 
      "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "if", "goto", 
      "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", 
      "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", 
      "transient", "try", "void", "volatile", "while", "false", "true", "null");
    final String[] forbiddenNames = _stringArray.getArray();
    for (final String str : forbiddenNames) {
      boolean _equals_2 = str.equals(identifier);
      if (_equals_2) {
        String _plus_8 = ("The provided identifier \'" + identifier);
        String _plus_9 = (_plus_8 + "\' is not compatible with the Java naming convention. The identifier matches a keyword, boolean literal, or the null literal.");
        IllegalArgumentException _illegalArgumentException_3 = new IllegalArgumentException(_plus_9);
        throw _illegalArgumentException_3;
      }
    }
  }
  
  /**
   * @param packageName of the package.
   * @return Directory corresponding to the package, including trailing slash.
   */
  public String packageNameToFolder(final String packageName) {
    final String result = packageName.replace(".", "/");
    boolean _endsWith = result.endsWith("/");
    if (_endsWith) {
      return result;
    } else {
      return (result + "/");
    }
  }
  
  /**
   * @param templateName Name of the template generating the output.
   * @return generated-Tag for javadoc with name of the template.
   */
  public CharSequence statementGenerated(final String templateName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("* @generated ");
    _builder.append(templateName, "");
    _builder.append(" (Xtend)");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  /**
   * @param templateName Name of the class generating the output.
   * @return generated-Tag for javadoc with name of the class.
   */
  public CharSequence statementGenerated(final Class<? extends Object> callingClass) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("* @generated ");
    String _name = callingClass.getName();
    _builder.append(_name, "");
    _builder.append(" (Xtend) ");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  /**
   * @param packageInfo Information on the package.
   * @return Java statement for the package definition.
   */
  public CharSequence statementPackage(final PackageInfo packageInfo) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _or = false;
      String _packageName = packageInfo.getPackageName();
      boolean _equals = Objects.equal(_packageName, "");
      if (_equals) {
        _or = true;
      } else {
        String _packageName_1 = packageInfo.getPackageName();
        boolean _equals_1 = Objects.equal(_packageName_1, null);
        _or = (_equals || _equals_1);
      }
      if (_or) {
      } else {
        _builder.append("package ");
        String _packageName_2 = packageInfo.getPackageName();
        _builder.append(_packageName_2, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  /**
   * @param imports Fully qualified names of classes to import.
   * @return import statements.
   */
  public CharSequence statementImports(final Set<String> imports) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final String importStmt : imports) {
        _builder.append("import ");
        _builder.append(importStmt, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  /**
   * Returns if the provided feature has a primitive type. Primitive Types are all non-domain-model types.
   * @param feature The structural feature.
   * @return <code>true</code> only if the feature has a primitive type.
   */
  public Boolean isPrimitiveType(final EStructuralFeature feature) {
    boolean _or = false;
    boolean _or_1 = false;
    boolean _or_2 = false;
    boolean _or_3 = false;
    boolean _or_4 = false;
    boolean _or_5 = false;
    boolean _or_6 = false;
    boolean _or_7 = false;
    EClassifier _eType = feature.getEType();
    String _instanceClassName = _eType.getInstanceClassName();
    boolean _equals = "byte".equals(_instanceClassName);
    if (_equals) {
      _or_7 = true;
    } else {
      EClassifier _eType_1 = feature.getEType();
      String _instanceClassName_1 = _eType_1.getInstanceClassName();
      boolean _equals_1 = "short".equals(_instanceClassName_1);
      _or_7 = (_equals || _equals_1);
    }
    if (_or_7) {
      _or_6 = true;
    } else {
      EClassifier _eType_2 = feature.getEType();
      String _instanceClassName_2 = _eType_2.getInstanceClassName();
      boolean _equals_2 = "int".equals(_instanceClassName_2);
      _or_6 = (_or_7 || _equals_2);
    }
    if (_or_6) {
      _or_5 = true;
    } else {
      EClassifier _eType_3 = feature.getEType();
      String _instanceClassName_3 = _eType_3.getInstanceClassName();
      boolean _equals_3 = "long".equals(_instanceClassName_3);
      _or_5 = (_or_6 || _equals_3);
    }
    if (_or_5) {
      _or_4 = true;
    } else {
      EClassifier _eType_4 = feature.getEType();
      String _instanceClassName_4 = _eType_4.getInstanceClassName();
      boolean _equals_4 = "float".equals(_instanceClassName_4);
      _or_4 = (_or_5 || _equals_4);
    }
    if (_or_4) {
      _or_3 = true;
    } else {
      EClassifier _eType_5 = feature.getEType();
      String _instanceClassName_5 = _eType_5.getInstanceClassName();
      boolean _equals_5 = "double".equals(_instanceClassName_5);
      _or_3 = (_or_4 || _equals_5);
    }
    if (_or_3) {
      _or_2 = true;
    } else {
      EClassifier _eType_6 = feature.getEType();
      String _instanceClassName_6 = _eType_6.getInstanceClassName();
      boolean _equals_6 = "boolean".equals(_instanceClassName_6);
      _or_2 = (_or_3 || _equals_6);
    }
    if (_or_2) {
      _or_1 = true;
    } else {
      EClassifier _eType_7 = feature.getEType();
      String _instanceClassName_7 = _eType_7.getInstanceClassName();
      boolean _equals_7 = "char".equals(_instanceClassName_7);
      _or_1 = (_or_2 || _equals_7);
    }
    if (_or_1) {
      _or = true;
    } else {
      EClassifier _eType_8 = feature.getEType();
      String _instanceClassName_8 = _eType_8.getInstanceClassName();
      boolean _notEquals = (!Objects.equal(_instanceClassName_8, null));
      _or = (_or_1 || _notEquals);
    }
    if (_or) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  /**
   * Returns the type of the provided feature.
   * @param feature The structural feature.
   * @return The unqualified name.
   */
  public String getType(final EStructuralFeature feature) {
    String _xifexpression = null;
    if ((feature instanceof EAttribute)) {
      EClassifier _eType = feature.getEType();
      boolean _notEquals = (!Objects.equal(_eType, null));
      if (_notEquals) {
        EClassifier _eType_1 = feature.getEType();
        String _instanceClassName = _eType_1.getInstanceClassName();
        boolean _equals = Objects.equal(_instanceClassName, null);
        if (_equals) {
          EClassifier _eType_2 = feature.getEType();
          return _eType_2.getName();
        } else {
          EClassifier _eType_3 = feature.getEType();
          String _instanceClassName_1 = _eType_3.getInstanceClassName();
          boolean _equals_1 = _instanceClassName_1.equals("long");
          if (_equals_1) {
            return "Long";
          } else {
            EClassifier _eType_4 = feature.getEType();
            String _instanceClassName_2 = _eType_4.getInstanceClassName();
            boolean _equals_2 = _instanceClassName_2.equals("int");
            if (_equals_2) {
              return "Integer";
            } else {
              EClassifier _eType_5 = feature.getEType();
              String _instanceClassName_3 = _eType_5.getInstanceClassName();
              boolean _equals_3 = _instanceClassName_3.equals("float");
              if (_equals_3) {
                return "Float";
              } else {
                EClassifier _eType_6 = feature.getEType();
                String _instanceClassName_4 = _eType_6.getInstanceClassName();
                boolean _equals_4 = _instanceClassName_4.equals("double");
                if (_equals_4) {
                  return "Double";
                } else {
                  EClassifier _eType_7 = feature.getEType();
                  String _instanceClassName_5 = _eType_7.getInstanceClassName();
                  boolean _equals_5 = _instanceClassName_5.equals("byte");
                  if (_equals_5) {
                    return "Byte";
                  } else {
                    EClassifier _eType_8 = feature.getEType();
                    String _instanceClassName_6 = _eType_8.getInstanceClassName();
                    boolean _equals_6 = _instanceClassName_6.equals("short");
                    if (_equals_6) {
                      return "Short";
                    } else {
                      EClassifier _eType_9 = feature.getEType();
                      String _instanceClassName_7 = _eType_9.getInstanceClassName();
                      boolean _equals_7 = _instanceClassName_7.equals("char");
                      if (_equals_7) {
                        return "Character";
                      } else {
                        EClassifier _eType_10 = feature.getEType();
                        String _instanceClassName_8 = _eType_10.getInstanceClassName();
                        boolean _equals_8 = _instanceClassName_8.equals("java.lang.String");
                        if (_equals_8) {
                          return "String";
                        } else {
                          EClassifier _eType_11 = feature.getEType();
                          String _instanceClassName_9 = _eType_11.getInstanceClassName();
                          boolean _equals_9 = _instanceClassName_9.equals("boolean");
                          if (_equals_9) {
                            return "Boolean";
                          } else {
                            EClassifier _eType_12 = feature.getEType();
                            String _instanceClassName_10 = _eType_12.getInstanceClassName();
                            boolean _equals_10 = _instanceClassName_10.equals("java.util.Date");
                            if (_equals_10) {
                              return "Date";
                            } else {
                              EClassifier _eType_13 = feature.getEType();
                              return _eType_13.getInstanceClassName();
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      } else {
        String _name = feature.getName();
        String _plus = ("Type must be provided for each feature. Ensure that the domain model is a valid ecore file. The offending feature was " + _name);
        String _plus_1 = (_plus + ". ");
        IllegalArgumentException _illegalArgumentException = new IllegalArgumentException(_plus_1);
        throw _illegalArgumentException;
      }
    } else {
      String _xifexpression_1 = null;
      if ((feature instanceof EReference)) {
        EClassifier _eType_14 = feature.getEType();
        return _eType_14.getName();
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  /**
   * Get all classifiers of the supplied domain package.
   * @param EPackage the package to get the classifiers for.
   * @return The list of contained classifiers.
   */
  public List<EClass> getAllDomainEntities(final EPackage domainPackage) {
    ArrayList<EClass> _arrayList = new ArrayList<EClass>();
    List<EClass> domainClasses = _arrayList;
    EList<EClassifier> _eClassifiers = domainPackage.getEClassifiers();
    for (final EClassifier classifier : _eClassifiers) {
      if ((classifier instanceof EClass)) {
        domainClasses.add(((EClass) classifier));
      }
    }
    return domainClasses;
  }
  
  /**
   * Get all references for a List of EClass.
   * @param EClass The list of EClass.
   * @return The list of contained references.
   */
  public List<EReference> getAllReferences(final List<EClass> eClasses) {
    ArrayList<EReference> _arrayList = new ArrayList<EReference>();
    List<EReference> references = _arrayList;
    for (final EClass eClass : eClasses) {
      EList<EStructuralFeature> _eAllStructuralFeatures = eClass.getEAllStructuralFeatures();
      for (final EStructuralFeature structuralFeature : _eAllStructuralFeatures) {
        if ((structuralFeature instanceof EReference)) {
          references.add(((EReference) structuralFeature));
        }
      }
    }
    return references;
  }
  
  /**
   * Returns the attribute of a domain entity which is marked as the identifier of the entity.
   * @param The domain entity.
   * @return The attribute or <code>null</code>
   */
  public EAttribute getAttributeWithAnnotationDatabaseId(final EClass entity) {
    EList<EAttribute> _eAllAttributes = entity.getEAllAttributes();
    for (final EAttribute attribute : _eAllAttributes) {
      Boolean _valueForAnnotationAsBoolean = this.annotationUtilities.getValueForAnnotationAsBoolean(attribute, DatabaseMohitoAnnotationCategory.FIELD_IS_IDENTIFIER);
      boolean _equals = ((_valueForAnnotationAsBoolean).booleanValue() == true);
      if (_equals) {
        return attribute;
      }
    }
    return null;
  }
  
  /**
   * Return the textual name of the type of the identifier of a domain entity.
   * @param entity The entity.
   * @return The textual name of the type.
   */
  public String getTypeOfDomainEntityIdentifier(final EClass entity) {
    EAttribute _attributeWithAnnotationDatabaseId = this.getAttributeWithAnnotationDatabaseId(entity);
    boolean _notEquals = (!Objects.equal(_attributeWithAnnotationDatabaseId, null));
    if (_notEquals) {
      EAttribute _attributeWithAnnotationDatabaseId_1 = this.getAttributeWithAnnotationDatabaseId(entity);
      return this.getType(_attributeWithAnnotationDatabaseId_1);
    } else {
      return "String";
    }
  }
  
  /**
   * Import statements for all non-abstract domain entities.
   * @param packageInfo Information on the package for the domain classes.
   * @param Domain model.
   * @return import statements.
   */
  public CharSequence statementImportsNonAbstractDomainEntities(final PackageInfo packageInfo, final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import info.multiplatform.mohito.framework.DaoManager;");
    _builder.newLine();
    _builder.append("import info.multiplatform.mohito.framework.MohitoEntityDao;");
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
            _builder.append("import ");
            String _packageName = packageInfo.getPackageName();
            _builder.append(_packageName, "");
            _builder.append(".");
            String _name = entity.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name);
            _builder.append(_firstUpper, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  /**
   * Converts the given String to a Set<String>.
   */
  public Set<String> toSet(final String string) {
    ArrayList<String> _arrayList = new ArrayList<String>(1);
    final ArrayList<String> result = _arrayList;
    result.add(string);
    return IterableExtensions.<String>toSet(result);
  }
  
  /**
   * Converts the given array to a Set<String>.
   */
  public Set<String> toSet(final String... strings) {
    ArrayList<String> _arrayList = new ArrayList<String>(1);
    final ArrayList<String> result = _arrayList;
    for (final String str : strings) {
      result.add(str);
    }
    return IterableExtensions.<String>toSet(result);
  }
}
