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
package de.modagile.metamodel.app.domain.util;

import de.modagile.metamodel.app.domain.*;

import de.modagile.metamodel.app.util.AppValidator;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see de.modagile.metamodel.app.domain.DomainPackage
 * @generated
 */
public class DomainValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final DomainValidator INSTANCE = new DomainValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "de.modagile.metamodel.app.domain";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AppValidator appValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainValidator() {
		super();
		appValidator = AppValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return DomainPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case DomainPackage.DOMAIN_BINDING:
				return validateDomainBinding((DomainBinding)value, diagnostics, context);
			case DomainPackage.COMPLEX_BINDING:
				return validateComplexBinding((ComplexBinding)value, diagnostics, context);
			case DomainPackage.BINDING_REPOSITORY:
				return validateBindingRepository((BindingRepository)value, diagnostics, context);
			case DomainPackage.STRING_BINDING:
				return validateStringBinding((StringBinding)value, diagnostics, context);
			case DomainPackage.BOOLEAN_BINDING:
				return validateBooleanBinding((BooleanBinding)value, diagnostics, context);
			case DomainPackage.PRIMITIVE_BINDING:
				return validatePrimitiveBinding((PrimitiveBinding)value, diagnostics, context);
			case DomainPackage.LIST_BINDING:
				return validateListBinding((ListBinding)value, diagnostics, context);
			case DomainPackage.SELECTION_BINDING:
				return validateSelectionBinding((SelectionBinding)value, diagnostics, context);
			case DomainPackage.UPDATE_STRATEGY:
				return validateUpdateStrategy((UpdateStrategy)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDomainBinding(DomainBinding domainBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(domainBinding, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(domainBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(domainBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(domainBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(domainBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(domainBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(domainBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(domainBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(domainBinding, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(domainBinding, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComplexBinding(ComplexBinding complexBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(complexBinding, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validateComplexBinding_ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes(complexBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validateComplexBinding_ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings(complexBinding, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes constraint of '<em>Complex Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String COMPLEX_BINDING__COMPLEX_BINDING_DOMAIN_ENTITY_ATTRIBUTES_MUST_INCLUDE_INNER_BINDING_ATTRIBUTES__EEXPRESSION = "self.innerBindings->forAll(binding : PrimitiveBinding | self.domainEntity.eAttributes->includes(binding.attribute))";

	/**
	 * Validates the ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes constraint of '<em>Complex Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComplexBinding_ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes(ComplexBinding complexBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DomainPackage.Literals.COMPLEX_BINDING,
				 complexBinding,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes",
				 COMPLEX_BINDING__COMPLEX_BINDING_DOMAIN_ENTITY_ATTRIBUTES_MUST_INCLUDE_INNER_BINDING_ATTRIBUTES__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings constraint of '<em>Complex Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String COMPLEX_BINDING__COMPLEX_BINDING_UI_ELEMENT_MUST_INCLUDE_BOUND_UI_ELEMENTS_OF_INNER_BINDINGS__EEXPRESSION = "self.innerBindings->forAll(binding : PrimitiveBinding | binding.oclIsKindOf(StringBinding) and self.uiElement.containedDisplayElements->includes(binding.oclAsType(StringBinding).uiElement) or binding.oclIsKindOf(BooleanBinding) and self.uiElement.containedDisplayElements->includes(binding.oclAsType(BooleanBinding).checkBox))";

	/**
	 * Validates the ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings constraint of '<em>Complex Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComplexBinding_ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings(ComplexBinding complexBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DomainPackage.Literals.COMPLEX_BINDING,
				 complexBinding,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings",
				 COMPLEX_BINDING__COMPLEX_BINDING_UI_ELEMENT_MUST_INCLUDE_BOUND_UI_ELEMENTS_OF_INNER_BINDINGS__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBindingRepository(BindingRepository bindingRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(bindingRepository, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringBinding(StringBinding stringBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(stringBinding, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(stringBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validateStringBinding_StringBindingAttributeMustBeOfTypeString(stringBinding, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the StringBindingAttributeMustBeOfTypeString constraint of '<em>String Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String STRING_BINDING__STRING_BINDING_ATTRIBUTE_MUST_BE_OF_TYPE_STRING__EEXPRESSION = "self.attribute.eType.name = 'EString'";

	/**
	 * Validates the StringBindingAttributeMustBeOfTypeString constraint of '<em>String Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringBinding_StringBindingAttributeMustBeOfTypeString(StringBinding stringBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DomainPackage.Literals.STRING_BINDING,
				 stringBinding,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "StringBindingAttributeMustBeOfTypeString",
				 STRING_BINDING__STRING_BINDING_ATTRIBUTE_MUST_BE_OF_TYPE_STRING__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanBinding(BooleanBinding booleanBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(booleanBinding, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(booleanBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validateBooleanBinding_BooleanBindingAttributeMustBeOfTypeBoolean(booleanBinding, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the BooleanBindingAttributeMustBeOfTypeBoolean constraint of '<em>Boolean Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String BOOLEAN_BINDING__BOOLEAN_BINDING_ATTRIBUTE_MUST_BE_OF_TYPE_BOOLEAN__EEXPRESSION = "self.attribute.eType.name = 'EBoolean'";

	/**
	 * Validates the BooleanBindingAttributeMustBeOfTypeBoolean constraint of '<em>Boolean Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanBinding_BooleanBindingAttributeMustBeOfTypeBoolean(BooleanBinding booleanBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(DomainPackage.Literals.BOOLEAN_BINDING,
				 booleanBinding,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "BooleanBindingAttributeMustBeOfTypeBoolean",
				 BOOLEAN_BINDING__BOOLEAN_BINDING_ATTRIBUTE_MUST_BE_OF_TYPE_BOOLEAN__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePrimitiveBinding(PrimitiveBinding primitiveBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(primitiveBinding, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(primitiveBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(primitiveBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(primitiveBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(primitiveBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(primitiveBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(primitiveBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(primitiveBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(primitiveBinding, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(primitiveBinding, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateListBinding(ListBinding listBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(listBinding, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(listBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(listBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(listBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(listBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(listBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(listBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(listBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(listBinding, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(listBinding, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSelectionBinding(SelectionBinding selectionBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(selectionBinding, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(selectionBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(selectionBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(selectionBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(selectionBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(selectionBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(selectionBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(selectionBinding, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(selectionBinding, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(selectionBinding, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUpdateStrategy(UpdateStrategy updateStrategy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //DomainValidator
