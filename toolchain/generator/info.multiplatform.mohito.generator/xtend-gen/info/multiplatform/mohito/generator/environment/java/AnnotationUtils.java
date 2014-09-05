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
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationsDefinitionsConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Utility class for handling annotations of domain elements.
 * 
 * @author groenda
 */
@SuppressWarnings("all")
public class AnnotationUtils {
  /**
   * @param element The element, which may contain annotations.
   * @param annotationDefinition The definition of the annotation.
   * @return The value for the annotation. The default is returned if the annotation is not explicitly set.
   */
  public String getValueForAnnotation(final EModelElement element, final MohitoAnnotationDefinition annotationDefinition) {
    boolean _and = false;
    String _scope = annotationDefinition.getScope();
    boolean _equals = Objects.equal(_scope, MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_ECLASS);
    if (!_equals) {
      _and = false;
    } else {
      boolean _not = (!(element instanceof EClass));
      _and = (_equals && _not);
    }
    if (_and) {
      IllegalArgumentException _illegalArgumentException = new IllegalArgumentException("Requested an annotation for EClasses on another type of object.");
      throw _illegalArgumentException;
    }
    boolean _and_1 = false;
    String _scope_1 = annotationDefinition.getScope();
    boolean _equals_1 = Objects.equal(_scope_1, MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE);
    if (!_equals_1) {
      _and_1 = false;
    } else {
      boolean _or = false;
      if ((element instanceof EAttribute)) {
        _or = true;
      } else {
        _or = ((element instanceof EAttribute) || (element instanceof EReference));
      }
      boolean _not_1 = (!_or);
      _and_1 = (_equals_1 && _not_1);
    }
    if (_and_1) {
      IllegalArgumentException _illegalArgumentException_1 = new IllegalArgumentException("Requested an annotation for EAttributes on another type of object.");
      throw _illegalArgumentException_1;
    }
    EList<EAnnotation> _eAnnotations = element.getEAnnotations();
    final Function1<EAnnotation,Boolean> _function = new Function1<EAnnotation,Boolean>() {
        public Boolean apply(final EAnnotation it) {
          String _source = it.getSource();
          String _namespace = annotationDefinition.getNamespace();
          boolean _equals = Objects.equal(_source, _namespace);
          return Boolean.valueOf(_equals);
        }
      };
    final EAnnotation categoryAnnotation = IterableExtensions.<EAnnotation>findFirst(_eAnnotations, _function);
    boolean _equals_2 = Objects.equal(categoryAnnotation, null);
    if (_equals_2) {
      return annotationDefinition.getDefaultValue();
    } else {
      EMap<String,String> _details = categoryAnnotation.getDetails();
      String _property = annotationDefinition.getProperty();
      final String value = _details.get(_property);
      boolean _equals_3 = Objects.equal(value, null);
      if (_equals_3) {
        return annotationDefinition.getDefaultValue();
      } else {
        return value;
      }
    }
  }
  
  /**
   * @param element The element, which may contain annotations.
   * @param annotationDefinition The definition of the annotation.
   * @return The value for the annotation. The default is returned if the annotation is not explicitly set.
   */
  public Boolean getValueForAnnotationAsBoolean(final EModelElement element, final MohitoAnnotationDefinition annotationDefinition) {
    boolean _xblockexpression = false;
    {
      String _dataType = annotationDefinition.getDataType();
      boolean _notEquals = (!Objects.equal(_dataType, MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN));
      if (_notEquals) {
        IllegalArgumentException _illegalArgumentException = new IllegalArgumentException("Boolean values can only be requested for definitions with data type boolean.");
        throw _illegalArgumentException;
      }
      String _valueForAnnotation = this.getValueForAnnotation(element, annotationDefinition);
      boolean _equals = "true".equals(_valueForAnnotation);
      _xblockexpression = (_equals);
    }
    return Boolean.valueOf(_xblockexpression);
  }
  
  /**
   * @param element The element, which may contain annotations.
   * @param annotationDefinition The definition of the annotation.
   * @return The value for the annotation. The default is returned if the annotation is not explicitly set.
   */
  public String getValueForAnnotationAsString(final EModelElement element, final MohitoAnnotationDefinition annotationDefinition) {
    String _xblockexpression = null;
    {
      String _dataType = annotationDefinition.getDataType();
      boolean _notEquals = (!Objects.equal(_dataType, MohitoAnnotationsDefinitionsConstants.DATA_TYPE_STRING));
      if (_notEquals) {
        IllegalArgumentException _illegalArgumentException = new IllegalArgumentException("Boolean values can only be requested for definitions with data type boolean.");
        throw _illegalArgumentException;
      }
      String _valueForAnnotation = this.getValueForAnnotation(element, annotationDefinition);
      _xblockexpression = (_valueForAnnotation);
    }
    return _xblockexpression;
  }
}
