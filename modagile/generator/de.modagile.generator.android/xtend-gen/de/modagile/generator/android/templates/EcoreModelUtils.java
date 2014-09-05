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
package de.modagile.generator.android.templates;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EcoreModelUtils {
  /**
   * Checks whether an EReference is a Many-To-Many Reference
   */
  public boolean isManytoManyReference(final EReference reference) {
    boolean _and = false;
    boolean _and_1 = false;
    EReference _eOpposite = reference.getEOpposite();
    boolean _notEquals = (!Objects.equal(_eOpposite, null));
    if (!_notEquals) {
      _and_1 = false;
    } else {
      int _upperBound = reference.getUpperBound();
      boolean _notEquals_1 = (_upperBound != 1);
      _and_1 = (_notEquals && _notEquals_1);
    }
    if (!_and_1) {
      _and = false;
    } else {
      EReference _eOpposite_1 = reference.getEOpposite();
      int _upperBound_1 = _eOpposite_1.getUpperBound();
      boolean _notEquals_2 = (_upperBound_1 != 1);
      _and = (_and_1 && _notEquals_2);
    }
    if (_and) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Returns only outgoing To-One EReferences
   */
  public Iterable<EReference> outgoingToOneEReferences(final EClass currentEClass) {
    EList<EReference> _eReferences = currentEClass.getEReferences();
    final Function1<EReference,Boolean> _function = new Function1<EReference,Boolean>() {
        public Boolean apply(final EReference e) {
          int _upperBound = e.getUpperBound();
          boolean _equals = (_upperBound == 1);
          return Boolean.valueOf(_equals);
        }
      };
    return IterableExtensions.<EReference>filter(_eReferences, _function);
  }
  
  /**
   * Checks all EClasses for potential incoming EReferences to the EClass currentEClass
   */
  public List<EReference> incomingOneToManyEReferences(final EClass currentEClass, final List<EClass> allEClasses) {
    ArrayList<EReference> _arrayList = new ArrayList<EReference>();
    List<EReference> incomingOneToManyEReferences = _arrayList;
    for (final EClass potentialReferencingEClass : allEClasses) {
      EList<EReference> _eReferences = potentialReferencingEClass.getEReferences();
      for (final EReference potentialIncomingEReference : _eReferences) {
        EClass _eReferenceType = potentialIncomingEReference.getEReferenceType();
        boolean _equals = _eReferenceType.equals(currentEClass);
        if (_equals) {
          boolean _and = false;
          int _upperBound = potentialIncomingEReference.getUpperBound();
          boolean _notEquals = (_upperBound != 1);
          if (!_notEquals) {
            _and = false;
          } else {
            EReference _eOpposite = potentialIncomingEReference.getEOpposite();
            boolean _equals_1 = Objects.equal(_eOpposite, null);
            _and = (_notEquals && _equals_1);
          }
          if (_and) {
            incomingOneToManyEReferences.add(potentialIncomingEReference);
          }
        }
      }
    }
    return incomingOneToManyEReferences;
  }
  
  /**
   * Provides a readable name for incoming EReferences
   */
  public String incomingReferenceName(final EReference incomingRef) {
    String _name = incomingRef.getName();
    String _upperCase = _name.toUpperCase();
    return (_upperCase + "_INVERSE");
  }
  
  /**
   * Provides a readable name for incoming EReferences
   */
  public String manyToManyReferenceTableName(final EReference manyToManyRef) {
    EClass _eContainingClass = manyToManyRef.getEContainingClass();
    String _name = _eContainingClass.getName();
    String _plus = (_name + "_");
    EReference _eOpposite = manyToManyRef.getEOpposite();
    EClass _eContainingClass_1 = _eOpposite.getEContainingClass();
    String _name_1 = _eContainingClass_1.getName();
    String _plus_1 = (_plus + _name_1);
    String _plus_2 = (_plus_1 + "_");
    return (_plus_2 + "Mapping");
  }
  
  /**
   * Provides a readable name for incoming EReferences
   */
  public String manyToManyReferenceClassName(final EReference manyToManyRef) {
    EClass _eContainingClass = manyToManyRef.getEContainingClass();
    String _name = _eContainingClass.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = ("" + _firstUpper);
    EReference _eOpposite = manyToManyRef.getEOpposite();
    EClass _eContainingClass_1 = _eOpposite.getEContainingClass();
    String _name_1 = _eContainingClass_1.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    String _plus_1 = (_plus + _firstUpper_1);
    return (_plus_1 + "Mapping");
  }
  
  /**
   * Provides a list of unique many-to-many EReferences (without their opposites)
   */
  public List<EReference> uniqueManyToManyEReferences(final List<EClass> allEClasses) {
    ArrayList<EReference> _arrayList = new ArrayList<EReference>();
    List<EReference> manyToManyEReferences = _arrayList;
    for (final EClass potentialReferencingEClass : allEClasses) {
      EList<EReference> _eReferences = potentialReferencingEClass.getEReferences();
      for (final EReference potentialManyToManyEReference : _eReferences) {
        boolean _isManytoManyReference = this.isManytoManyReference(potentialManyToManyEReference);
        if (_isManytoManyReference) {
          EClass _eContainingClass = potentialManyToManyEReference.getEContainingClass();
          String _name = _eContainingClass.getName();
          EReference _eOpposite = potentialManyToManyEReference.getEOpposite();
          EClass _eContainingClass_1 = _eOpposite.getEContainingClass();
          String _name_1 = _eContainingClass_1.getName();
          int _compareToIgnoreCase = _name.compareToIgnoreCase(_name_1);
          boolean _lessThan = (_compareToIgnoreCase < 0);
          if (_lessThan) {
            manyToManyEReferences.add(potentialManyToManyEReference);
          }
        }
      }
    }
    return manyToManyEReferences;
  }
  
  public EClass manyToManyEReferenceMappingEntity(final EReference ref) {
    EClass mappingEntity = EcoreFactory.eINSTANCE.createEClass();
    String _manyToManyReferenceClassName = this.manyToManyReferenceClassName(ref);
    mappingEntity.setName(_manyToManyReferenceClassName);
    EAttribute sourceAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    EClass _eContainingClass = ref.getEContainingClass();
    String _name = _eContainingClass.getName();
    String _lowerCase = _name.toLowerCase();
    String _plus = (_lowerCase + "_id");
    sourceAttribute.setName(_plus);
    EDataType _eInt = EcorePackage.eINSTANCE.getEInt();
    sourceAttribute.setEType(_eInt);
    EList<EStructuralFeature> _eStructuralFeatures = mappingEntity.getEStructuralFeatures();
    _eStructuralFeatures.add(sourceAttribute);
    EAttribute targetAttribute = EcoreFactory.eINSTANCE.createEAttribute();
    EReference _eOpposite = ref.getEOpposite();
    EClass _eContainingClass_1 = _eOpposite.getEContainingClass();
    String _name_1 = _eContainingClass_1.getName();
    String _lowerCase_1 = _name_1.toLowerCase();
    String _plus_1 = (_lowerCase_1 + "_id");
    targetAttribute.setName(_plus_1);
    EDataType _eInt_1 = EcorePackage.eINSTANCE.getEInt();
    targetAttribute.setEType(_eInt_1);
    EList<EStructuralFeature> _eStructuralFeatures_1 = mappingEntity.getEStructuralFeatures();
    _eStructuralFeatures_1.add(targetAttribute);
    return mappingEntity;
  }
}
