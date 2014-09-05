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
package de.modagile.metamodel.app.ui.util;

import de.modagile.metamodel.app.ui.*;

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
 * @see de.modagile.metamodel.app.ui.UIPackage
 * @generated
 */
public class UIValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final UIValidator INSTANCE = new UIValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "de.modagile.metamodel.app.ui";

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
	public UIValidator() {
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
	  return UIPackage.eINSTANCE;
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
			case UIPackage.SCREEN:
				return validateScreen((Screen)value, diagnostics, context);
			case UIPackage.CONTROL:
				return validateControl((Control)value, diagnostics, context);
			case UIPackage.FLOW:
				return validateFlow((Flow)value, diagnostics, context);
			case UIPackage.INPUT:
				return validateInput((Input)value, diagnostics, context);
			case UIPackage.LABEL:
				return validateLabel((Label)value, diagnostics, context);
			case UIPackage.BUTTON:
				return validateButton((Button)value, diagnostics, context);
			case UIPackage.STORY_BOARD:
				return validateStoryBoard((StoryBoard)value, diagnostics, context);
			case UIPackage.DISPLAY_ELEMENT:
				return validateDisplayElement((DisplayElement)value, diagnostics, context);
			case UIPackage.DYNAMIC_LIST:
				return validateDynamicList((DynamicList)value, diagnostics, context);
			case UIPackage.INPUT_CONTEXT:
				return validateInputContext((InputContext)value, diagnostics, context);
			case UIPackage.DATEPICKER:
				return validateDatepicker((Datepicker)value, diagnostics, context);
			case UIPackage.ENABLEABLE:
				return validateEnableable((Enableable)value, diagnostics, context);
			case UIPackage.TEXT_CONTAINING:
				return validateTextContaining((TextContaining)value, diagnostics, context);
			case UIPackage.IMAGE:
				return validateImage((Image)value, diagnostics, context);
			case UIPackage.LOCATION_PICKER:
				return validateLocationPicker((LocationPicker)value, diagnostics, context);
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE:
				return validateCompositeDisplayElementType((CompositeDisplayElementType)value, diagnostics, context);
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT:
				return validateCompositeDisplayElement((CompositeDisplayElement)value, diagnostics, context);
			case UIPackage.CHECK_BOX:
				return validateCheckBox((CheckBox)value, diagnostics, context);
			case UIPackage.IMAGE_BUTTON:
				return validateImageButton((ImageButton)value, diagnostics, context);
			case UIPackage.MENU_BAR:
				return validateMenuBar((MenuBar)value, diagnostics, context);
			case UIPackage.FRAGMENT_NAVIGATION:
				return validateFragmentNavigation((FragmentNavigation)value, diagnostics, context);
			case UIPackage.PICKER:
				return validatePicker((Picker)value, diagnostics, context);
			case UIPackage.DISPLAY_TYPE:
				return validateDisplayType((DisplayType)value, diagnostics, context);
			case UIPackage.INPUT_TYPE:
				return validateInputType((InputType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScreen(Screen screen, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(screen, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(screen, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(screen, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(screen, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(screen, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(screen, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(screen, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(screen, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(screen, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(screen, diagnostics, context);
		if (result || diagnostics != null) result &= validateScreen_onlyAlreadContainedStartFragmentAllowed(screen, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the onlyAlreadContainedStartFragmentAllowed constraint of '<em>Screen</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String SCREEN__ONLY_ALREAD_CONTAINED_START_FRAGMENT_ALLOWED__EEXPRESSION = "startFragment = null or displayElements->includes(startFragment)";

	/**
	 * Validates the onlyAlreadContainedStartFragmentAllowed constraint of '<em>Screen</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScreen_onlyAlreadContainedStartFragmentAllowed(Screen screen, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(UIPackage.Literals.SCREEN,
				 screen,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "onlyAlreadContainedStartFragmentAllowed",
				 SCREEN__ONLY_ALREAD_CONTAINED_START_FRAGMENT_ALLOWED__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateControl(Control control, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(control, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(control, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(control, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(control, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(control, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(control, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(control, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(control, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(control, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(control, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFlow(Flow flow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(flow, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(flow, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(flow, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(flow, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInput(Input input, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(input, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(input, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(input, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(input, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(input, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(input, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(input, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(input, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(input, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(input, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLabel(Label label, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(label, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(label, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(label, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateButton(Button button, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(button, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(button, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(button, diagnostics, context);
		if (result || diagnostics != null) result &= validateButton_Button_Can_Only_Have_One_Event_If_In_Screen(button, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the Button_Can_Only_Have_One_Event_If_In_Screen constraint of '<em>Button</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String BUTTON__BUTTON_CAN_ONLY_HAVE_ONE_EVENT_IF_IN_SCREEN__EEXPRESSION = "self.screen <> null and buttonClickEvents->size() <= 1 or self.screen = null";

	/**
	 * Validates the Button_Can_Only_Have_One_Event_If_In_Screen constraint of '<em>Button</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateButton_Button_Can_Only_Have_One_Event_If_In_Screen(Button button, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(UIPackage.Literals.BUTTON,
				 button,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "Button_Can_Only_Have_One_Event_If_In_Screen",
				 BUTTON__BUTTON_CAN_ONLY_HAVE_ONE_EVENT_IF_IN_SCREEN__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStoryBoard(StoryBoard storyBoard, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(storyBoard, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(storyBoard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(storyBoard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(storyBoard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(storyBoard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(storyBoard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(storyBoard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(storyBoard, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(storyBoard, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(storyBoard, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDisplayElement(DisplayElement displayElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(displayElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(displayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(displayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(displayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(displayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(displayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(displayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(displayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(displayElement, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(displayElement, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDynamicList(DynamicList dynamicList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dynamicList, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(dynamicList, diagnostics, context);
		if (result || diagnostics != null) result &= validateDynamicList_List_Can_Only_Have_One_Event_If_In_Screen(dynamicList, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the List_Can_Only_Have_One_Event_If_In_Screen constraint of '<em>Dynamic List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DYNAMIC_LIST__LIST_CAN_ONLY_HAVE_ONE_EVENT_IF_IN_SCREEN__EEXPRESSION = "self.screen <> null and listClickEvents->size() <= 1 or self.screen = null";

	/**
	 * Validates the List_Can_Only_Have_One_Event_If_In_Screen constraint of '<em>Dynamic List</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDynamicList_List_Can_Only_Have_One_Event_If_In_Screen(DynamicList dynamicList, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(UIPackage.Literals.DYNAMIC_LIST,
				 dynamicList,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "List_Can_Only_Have_One_Event_If_In_Screen",
				 DYNAMIC_LIST__LIST_CAN_ONLY_HAVE_ONE_EVENT_IF_IN_SCREEN__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInputContext(InputContext inputContext, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(inputContext, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDatepicker(Datepicker datepicker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(datepicker, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(datepicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(datepicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(datepicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(datepicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(datepicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(datepicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(datepicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(datepicker, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(datepicker, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnableable(Enableable enableable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(enableable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTextContaining(TextContaining textContaining, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(textContaining, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateImage(Image image, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(image, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(image, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(image, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(image, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(image, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(image, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(image, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(image, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(image, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(image, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocationPicker(LocationPicker locationPicker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(locationPicker, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(locationPicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(locationPicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(locationPicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(locationPicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(locationPicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(locationPicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(locationPicker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(locationPicker, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(locationPicker, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompositeDisplayElementType(CompositeDisplayElementType compositeDisplayElementType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(compositeDisplayElementType, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(compositeDisplayElementType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(compositeDisplayElementType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(compositeDisplayElementType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(compositeDisplayElementType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(compositeDisplayElementType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(compositeDisplayElementType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(compositeDisplayElementType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(compositeDisplayElementType, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(compositeDisplayElementType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompositeDisplayElement(CompositeDisplayElement compositeDisplayElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(compositeDisplayElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(compositeDisplayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(compositeDisplayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(compositeDisplayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(compositeDisplayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(compositeDisplayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(compositeDisplayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(compositeDisplayElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(compositeDisplayElement, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(compositeDisplayElement, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCheckBox(CheckBox checkBox, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(checkBox, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(checkBox, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(checkBox, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(checkBox, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(checkBox, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(checkBox, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(checkBox, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(checkBox, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(checkBox, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(checkBox, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateImageButton(ImageButton imageButton, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(imageButton, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(imageButton, diagnostics, context);
		if (result || diagnostics != null) result &= validateButton_Button_Can_Only_Have_One_Event_If_In_Screen(imageButton, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMenuBar(MenuBar menuBar, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(menuBar, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(menuBar, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(menuBar, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(menuBar, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(menuBar, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(menuBar, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(menuBar, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(menuBar, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(menuBar, diagnostics, context);
		if (result || diagnostics != null) result &= validateMenuBar_onlyLabelsButtonsAndCheckBoxesAllowed(menuBar, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the onlyLabelsButtonsAndCheckBoxesAllowed constraint of '<em>Menu Bar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String MENU_BAR__ONLY_LABELS_BUTTONS_AND_CHECK_BOXES_ALLOWED__EEXPRESSION = "self.menuBarElements->forAll(menubarItem : DisplayElement | menubarItem.oclIsTypeOf(Button) or menubarItem.oclIsTypeOf(ImageButton) or menubarItem.oclIsTypeOf(Label) or menubarItem.oclIsTypeOf(CheckBox) or menubarItem.oclIsTypeOf(Image))";

	/**
	 * Validates the onlyLabelsButtonsAndCheckBoxesAllowed constraint of '<em>Menu Bar</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMenuBar_onlyLabelsButtonsAndCheckBoxesAllowed(MenuBar menuBar, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(UIPackage.Literals.MENU_BAR,
				 menuBar,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "onlyLabelsButtonsAndCheckBoxesAllowed",
				 MENU_BAR__ONLY_LABELS_BUTTONS_AND_CHECK_BOXES_ALLOWED__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFragmentNavigation(FragmentNavigation fragmentNavigation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(fragmentNavigation, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(fragmentNavigation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fragmentNavigation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fragmentNavigation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(fragmentNavigation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fragmentNavigation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fragmentNavigation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fragmentNavigation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fragmentNavigation, diagnostics, context);
		if (result || diagnostics != null) result &= validateFragmentNavigation_navigationWithinSameScreen(fragmentNavigation, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the navigationWithinSameScreen constraint of '<em>Fragment Navigation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String FRAGMENT_NAVIGATION__NAVIGATION_WITHIN_SAME_SCREEN__EEXPRESSION = "to.screen = from.screen";

	/**
	 * Validates the navigationWithinSameScreen constraint of '<em>Fragment Navigation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFragmentNavigation_navigationWithinSameScreen(FragmentNavigation fragmentNavigation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(UIPackage.Literals.FRAGMENT_NAVIGATION,
				 fragmentNavigation,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "navigationWithinSameScreen",
				 FRAGMENT_NAVIGATION__NAVIGATION_WITHIN_SAME_SCREEN__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePicker(Picker picker, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(picker, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(picker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(picker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(picker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(picker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(picker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(picker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(picker, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(picker, diagnostics, context);
		if (result || diagnostics != null) result &= appValidator.validateEntity_NamingConvention(picker, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDisplayType(DisplayType displayType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInputType(InputType inputType, DiagnosticChain diagnostics, Map<Object, Object> context) {
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

} //UIValidator
