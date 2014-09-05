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

import de.modagile.metamodel.app.Entity;
import de.modagile.metamodel.app.FeaturedElement;

import de.modagile.metamodel.app.ui.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.modagile.metamodel.app.ui.UIPackage
 * @generated
 */
public class UISwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UIPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UISwitch() {
		if (modelPackage == null) {
			modelPackage = UIPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case UIPackage.SCREEN: {
				Screen screen = (Screen)theEObject;
				T result = caseScreen(screen);
				if (result == null) result = caseFeaturedElement(screen);
				if (result == null) result = caseEntity(screen);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.CONTROL: {
				Control control = (Control)theEObject;
				T result = caseControl(control);
				if (result == null) result = caseFeaturedElement(control);
				if (result == null) result = caseDisplayElement(control);
				if (result == null) result = caseEntity(control);
				if (result == null) result = caseEnableable(control);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.FLOW: {
				Flow flow = (Flow)theEObject;
				T result = caseFlow(flow);
				if (result == null) result = caseEntity(flow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.INPUT: {
				Input input = (Input)theEObject;
				T result = caseInput(input);
				if (result == null) result = caseDisplayElement(input);
				if (result == null) result = caseTextContaining(input);
				if (result == null) result = caseEntity(input);
				if (result == null) result = caseEnableable(input);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.LABEL: {
				Label label = (Label)theEObject;
				T result = caseLabel(label);
				if (result == null) result = caseDisplayElement(label);
				if (result == null) result = caseTextContaining(label);
				if (result == null) result = caseEntity(label);
				if (result == null) result = caseEnableable(label);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.BUTTON: {
				Button button = (Button)theEObject;
				T result = caseButton(button);
				if (result == null) result = caseControl(button);
				if (result == null) result = caseTextContaining(button);
				if (result == null) result = caseFeaturedElement(button);
				if (result == null) result = caseDisplayElement(button);
				if (result == null) result = caseEntity(button);
				if (result == null) result = caseEnableable(button);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.STORY_BOARD: {
				StoryBoard storyBoard = (StoryBoard)theEObject;
				T result = caseStoryBoard(storyBoard);
				if (result == null) result = caseFeaturedElement(storyBoard);
				if (result == null) result = caseEntity(storyBoard);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.DISPLAY_ELEMENT: {
				DisplayElement displayElement = (DisplayElement)theEObject;
				T result = caseDisplayElement(displayElement);
				if (result == null) result = caseEntity(displayElement);
				if (result == null) result = caseEnableable(displayElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.DYNAMIC_LIST: {
				DynamicList dynamicList = (DynamicList)theEObject;
				T result = caseDynamicList(dynamicList);
				if (result == null) result = caseDisplayElement(dynamicList);
				if (result == null) result = caseEntity(dynamicList);
				if (result == null) result = caseEnableable(dynamicList);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.INPUT_CONTEXT: {
				InputContext inputContext = (InputContext)theEObject;
				T result = caseInputContext(inputContext);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.DATEPICKER: {
				Datepicker datepicker = (Datepicker)theEObject;
				T result = caseDatepicker(datepicker);
				if (result == null) result = caseTextContaining(datepicker);
				if (result == null) result = casePicker(datepicker);
				if (result == null) result = caseDisplayElement(datepicker);
				if (result == null) result = caseEntity(datepicker);
				if (result == null) result = caseEnableable(datepicker);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.ENABLEABLE: {
				Enableable enableable = (Enableable)theEObject;
				T result = caseEnableable(enableable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.TEXT_CONTAINING: {
				TextContaining textContaining = (TextContaining)theEObject;
				T result = caseTextContaining(textContaining);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.IMAGE: {
				Image image = (Image)theEObject;
				T result = caseImage(image);
				if (result == null) result = caseDisplayElement(image);
				if (result == null) result = caseEntity(image);
				if (result == null) result = caseEnableable(image);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.LOCATION_PICKER: {
				LocationPicker locationPicker = (LocationPicker)theEObject;
				T result = caseLocationPicker(locationPicker);
				if (result == null) result = casePicker(locationPicker);
				if (result == null) result = caseDisplayElement(locationPicker);
				if (result == null) result = caseEntity(locationPicker);
				if (result == null) result = caseEnableable(locationPicker);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE: {
				CompositeDisplayElementType compositeDisplayElementType = (CompositeDisplayElementType)theEObject;
				T result = caseCompositeDisplayElementType(compositeDisplayElementType);
				if (result == null) result = caseFeaturedElement(compositeDisplayElementType);
				if (result == null) result = caseEntity(compositeDisplayElementType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT: {
				CompositeDisplayElement compositeDisplayElement = (CompositeDisplayElement)theEObject;
				T result = caseCompositeDisplayElement(compositeDisplayElement);
				if (result == null) result = caseDisplayElement(compositeDisplayElement);
				if (result == null) result = caseEntity(compositeDisplayElement);
				if (result == null) result = caseEnableable(compositeDisplayElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.CHECK_BOX: {
				CheckBox checkBox = (CheckBox)theEObject;
				T result = caseCheckBox(checkBox);
				if (result == null) result = caseDisplayElement(checkBox);
				if (result == null) result = caseEntity(checkBox);
				if (result == null) result = caseEnableable(checkBox);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.IMAGE_BUTTON: {
				ImageButton imageButton = (ImageButton)theEObject;
				T result = caseImageButton(imageButton);
				if (result == null) result = caseButton(imageButton);
				if (result == null) result = caseControl(imageButton);
				if (result == null) result = caseTextContaining(imageButton);
				if (result == null) result = caseFeaturedElement(imageButton);
				if (result == null) result = caseDisplayElement(imageButton);
				if (result == null) result = caseEntity(imageButton);
				if (result == null) result = caseEnableable(imageButton);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.MENU_BAR: {
				MenuBar menuBar = (MenuBar)theEObject;
				T result = caseMenuBar(menuBar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.FRAGMENT_NAVIGATION: {
				FragmentNavigation fragmentNavigation = (FragmentNavigation)theEObject;
				T result = caseFragmentNavigation(fragmentNavigation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UIPackage.PICKER: {
				Picker picker = (Picker)theEObject;
				T result = casePicker(picker);
				if (result == null) result = caseDisplayElement(picker);
				if (result == null) result = caseEntity(picker);
				if (result == null) result = caseEnableable(picker);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Screen</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Screen</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScreen(Screen object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Control</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Control</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControl(Control object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Flow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Flow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFlow(Flow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInput(Input object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabel(Label object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseButton(Button object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Story Board</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Story Board</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStoryBoard(StoryBoard object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Display Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Display Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDisplayElement(DisplayElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic List</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic List</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDynamicList(DynamicList object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputContext(InputContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Datepicker</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Datepicker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDatepicker(Datepicker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enableable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enableable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnableable(Enableable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Containing</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Containing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextContaining(TextContaining object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Image</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Image</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImage(Image object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Location Picker</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Location Picker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocationPicker(LocationPicker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Display Element Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Display Element Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompositeDisplayElementType(CompositeDisplayElementType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Display Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Display Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompositeDisplayElement(CompositeDisplayElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Check Box</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Check Box</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCheckBox(CheckBox object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Image Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Image Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImageButton(ImageButton object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Menu Bar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Menu Bar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMenuBar(MenuBar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fragment Navigation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fragment Navigation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFragmentNavigation(FragmentNavigation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Picker</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Picker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePicker(Picker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Featured Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Featured Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeaturedElement(FeaturedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //UISwitch
