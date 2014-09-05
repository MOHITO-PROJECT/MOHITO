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
package de.modagile.metamodel.app.ui;

import de.modagile.metamodel.app.AppPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.modagile.metamodel.app.ui.UIFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface UIPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ui";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modagile-mobile.de/metamodel#ui";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "de.modagile.metamodel.app";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UIPackage eINSTANCE = de.modagile.metamodel.app.ui.impl.UIPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.ScreenImpl <em>Screen</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.ScreenImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getScreen()
	 * @generated
	 */
	int SCREEN = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCREEN__NAME = AppPackage.FEATURED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Display Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCREEN__DISPLAY_ELEMENTS = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Story Board</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCREEN__STORY_BOARD = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Menu Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCREEN__MENU_BAR = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fragment Navigations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCREEN__FRAGMENT_NAVIGATIONS = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Start Fragment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCREEN__START_FRAGMENT = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Screen</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCREEN_FEATURE_COUNT = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.ControlImpl <em>Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.ControlImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getControl()
	 * @generated
	 */
	int CONTROL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__NAME = AppPackage.FEATURED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__SCREEN = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__COMPOSITE_TYPE = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FEATURE_COUNT = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.FlowImpl <em>Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.FlowImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getFlow()
	 * @generated
	 */
	int FLOW = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__NAME = AppPackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__FROM = AppPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__TO = AppPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Story Board</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__STORY_BOARD = AppPackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__EVENTS = AppPackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Flow Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__FLOW_CONTEXT = AppPackage.ENTITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Returns Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__RETURNS_RESULT = AppPackage.ENTITY_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_FEATURE_COUNT = AppPackage.ENTITY_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.DisplayElementImpl <em>Display Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.DisplayElementImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getDisplayElement()
	 * @generated
	 */
	int DISPLAY_ELEMENT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_ELEMENT__NAME = AppPackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_ELEMENT__SCREEN = AppPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_ELEMENT__COMPOSITE_TYPE = AppPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Display Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_ELEMENT_FEATURE_COUNT = AppPackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.InputImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getInput()
	 * @generated
	 */
	int INPUT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__NAME = DISPLAY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__SCREEN = DISPLAY_ELEMENT__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__COMPOSITE_TYPE = DISPLAY_ELEMENT__COMPOSITE_TYPE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__TEXT = DISPLAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Input Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__INPUT_TYPE = DISPLAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_FEATURE_COUNT = DISPLAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.LabelImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NAME = DISPLAY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__SCREEN = DISPLAY_ELEMENT__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__COMPOSITE_TYPE = DISPLAY_ELEMENT__COMPOSITE_TYPE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TEXT = DISPLAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = DISPLAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.ButtonImpl <em>Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.ButtonImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getButton()
	 * @generated
	 */
	int BUTTON = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON__NAME = CONTROL__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON__SCREEN = CONTROL__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON__COMPOSITE_TYPE = CONTROL__COMPOSITE_TYPE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON__TEXT = CONTROL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Caption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON__CAPTION = CONTROL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Button Click Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON__BUTTON_CLICK_EVENTS = CONTROL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON_FEATURE_COUNT = CONTROL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.StoryBoardImpl <em>Story Board</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.StoryBoardImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getStoryBoard()
	 * @generated
	 */
	int STORY_BOARD = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_BOARD__NAME = AppPackage.FEATURED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Flows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_BOARD__FLOWS = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Screens</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_BOARD__SCREENS = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Start Screen</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_BOARD__START_SCREEN = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Resolution X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_BOARD__RESOLUTION_X = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Resolution Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_BOARD__RESOLUTION_Y = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Story Board</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_BOARD_FEATURE_COUNT = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.DynamicListImpl <em>Dynamic List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.DynamicListImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getDynamicList()
	 * @generated
	 */
	int DYNAMIC_LIST = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_LIST__NAME = DISPLAY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_LIST__SCREEN = DISPLAY_ELEMENT__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_LIST__COMPOSITE_TYPE = DISPLAY_ELEMENT__COMPOSITE_TYPE;

	/**
	 * The feature id for the '<em><b>Displayed Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_LIST__DISPLAYED_ELEMENTS = DISPLAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>List Click Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_LIST__LIST_CLICK_EVENTS = DISPLAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Dynamic List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_LIST_FEATURE_COUNT = DISPLAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.InputContextImpl <em>Input Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.InputContextImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getInputContext()
	 * @generated
	 */
	int INPUT_CONTEXT = 9;

	/**
	 * The feature id for the '<em><b>Context Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_CONTEXT__CONTEXT_TYPE = 0;

	/**
	 * The number of structural features of the '<em>Input Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_CONTEXT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.DatepickerImpl <em>Datepicker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.DatepickerImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getDatepicker()
	 * @generated
	 */
	int DATEPICKER = 10;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.Enableable <em>Enableable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.Enableable
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getEnableable()
	 * @generated
	 */
	int ENABLEABLE = 11;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.TextContaining <em>Text Containing</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.TextContaining
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getTextContaining()
	 * @generated
	 */
	int TEXT_CONTAINING = 12;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINING__TEXT = 0;

	/**
	 * The number of structural features of the '<em>Text Containing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CONTAINING_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATEPICKER__TEXT = TEXT_CONTAINING__TEXT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATEPICKER__NAME = TEXT_CONTAINING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATEPICKER__SCREEN = TEXT_CONTAINING_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATEPICKER__COMPOSITE_TYPE = TEXT_CONTAINING_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Max Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATEPICKER__MAX_DATE = TEXT_CONTAINING_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Min Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATEPICKER__MIN_DATE = TEXT_CONTAINING_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Datepicker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATEPICKER_FEATURE_COUNT = TEXT_CONTAINING_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Enableable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENABLEABLE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.ImageImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__NAME = DISPLAY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__SCREEN = DISPLAY_ELEMENT__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__COMPOSITE_TYPE = DISPLAY_ELEMENT__COMPOSITE_TYPE;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__WIDTH = DISPLAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__HEIGHT = DISPLAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = DISPLAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.PickerImpl <em>Picker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.PickerImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getPicker()
	 * @generated
	 */
	int PICKER = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICKER__NAME = DISPLAY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICKER__SCREEN = DISPLAY_ELEMENT__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICKER__COMPOSITE_TYPE = DISPLAY_ELEMENT__COMPOSITE_TYPE;

	/**
	 * The number of structural features of the '<em>Picker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PICKER_FEATURE_COUNT = DISPLAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.LocationPickerImpl <em>Location Picker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.LocationPickerImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getLocationPicker()
	 * @generated
	 */
	int LOCATION_PICKER = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_PICKER__NAME = PICKER__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_PICKER__SCREEN = PICKER__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_PICKER__COMPOSITE_TYPE = PICKER__COMPOSITE_TYPE;

	/**
	 * The number of structural features of the '<em>Location Picker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_PICKER_FEATURE_COUNT = PICKER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.CompositeDisplayElementTypeImpl <em>Composite Display Element Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.CompositeDisplayElementTypeImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getCompositeDisplayElementType()
	 * @generated
	 */
	int COMPOSITE_DISPLAY_ELEMENT_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT_TYPE__NAME = AppPackage.FEATURED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Contained Display Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite Display Element Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT_TYPE_FEATURE_COUNT = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.CompositeDisplayElementImpl <em>Composite Display Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.CompositeDisplayElementImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getCompositeDisplayElement()
	 * @generated
	 */
	int COMPOSITE_DISPLAY_ELEMENT = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT__NAME = DISPLAY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT__SCREEN = DISPLAY_ELEMENT__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT__COMPOSITE_TYPE = DISPLAY_ELEMENT__COMPOSITE_TYPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT__TYPE = DISPLAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Display Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT__DISPLAY_TYPE = DISPLAY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Composite Display Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT_FEATURE_COUNT = DISPLAY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.CheckBoxImpl <em>Check Box</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.CheckBoxImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getCheckBox()
	 * @generated
	 */
	int CHECK_BOX = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_BOX__NAME = DISPLAY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_BOX__SCREEN = DISPLAY_ELEMENT__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_BOX__COMPOSITE_TYPE = DISPLAY_ELEMENT__COMPOSITE_TYPE;

	/**
	 * The number of structural features of the '<em>Check Box</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHECK_BOX_FEATURE_COUNT = DISPLAY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.ImageButtonImpl <em>Image Button</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.ImageButtonImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getImageButton()
	 * @generated
	 */
	int IMAGE_BUTTON = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON__NAME = BUTTON__NAME;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON__SCREEN = BUTTON__SCREEN;

	/**
	 * The feature id for the '<em><b>Composite Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON__COMPOSITE_TYPE = BUTTON__COMPOSITE_TYPE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON__TEXT = BUTTON__TEXT;

	/**
	 * The feature id for the '<em><b>Caption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON__CAPTION = BUTTON__CAPTION;

	/**
	 * The feature id for the '<em><b>Button Click Events</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON__BUTTON_CLICK_EVENTS = BUTTON__BUTTON_CLICK_EVENTS;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON__WIDTH = BUTTON_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON__HEIGHT = BUTTON_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Image Button</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_BUTTON_FEATURE_COUNT = BUTTON_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.MenuBarImpl <em>Menu Bar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.MenuBarImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getMenuBar()
	 * @generated
	 */
	int MENU_BAR = 19;

	/**
	 * The feature id for the '<em><b>Menu Bar Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_BAR__MENU_BAR_ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Screen</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_BAR__SCREEN = 1;

	/**
	 * The number of structural features of the '<em>Menu Bar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MENU_BAR_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.impl.FragmentNavigationImpl <em>Fragment Navigation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.impl.FragmentNavigationImpl
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getFragmentNavigation()
	 * @generated
	 */
	int FRAGMENT_NAVIGATION = 20;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_NAVIGATION__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_NAVIGATION__TO = 1;

	/**
	 * The feature id for the '<em><b>Navigation Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_NAVIGATION__NAVIGATION_TYPE = 2;

	/**
	 * The number of structural features of the '<em>Fragment Navigation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_NAVIGATION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.DisplayType <em>Display Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.DisplayType
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getDisplayType()
	 * @generated
	 */
	int DISPLAY_TYPE = 22;


	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.ui.InputType <em>Input Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.ui.InputType
	 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getInputType()
	 * @generated
	 */
	int INPUT_TYPE = 23;


	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Screen <em>Screen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Screen</em>'.
	 * @see de.modagile.metamodel.app.ui.Screen
	 * @generated
	 */
	EClass getScreen();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.ui.Screen#getDisplayElements <em>Display Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Elements</em>'.
	 * @see de.modagile.metamodel.app.ui.Screen#getDisplayElements()
	 * @see #getScreen()
	 * @generated
	 */
	EReference getScreen_DisplayElements();

	/**
	 * Returns the meta object for the container reference '{@link de.modagile.metamodel.app.ui.Screen#getStoryBoard <em>Story Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Story Board</em>'.
	 * @see de.modagile.metamodel.app.ui.Screen#getStoryBoard()
	 * @see #getScreen()
	 * @generated
	 */
	EReference getScreen_StoryBoard();

	/**
	 * Returns the meta object for the containment reference '{@link de.modagile.metamodel.app.ui.Screen#getMenuBar <em>Menu Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Menu Bar</em>'.
	 * @see de.modagile.metamodel.app.ui.Screen#getMenuBar()
	 * @see #getScreen()
	 * @generated
	 */
	EReference getScreen_MenuBar();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.ui.Screen#getFragmentNavigations <em>Fragment Navigations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragment Navigations</em>'.
	 * @see de.modagile.metamodel.app.ui.Screen#getFragmentNavigations()
	 * @see #getScreen()
	 * @generated
	 */
	EReference getScreen_FragmentNavigations();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.ui.Screen#getStartFragment <em>Start Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start Fragment</em>'.
	 * @see de.modagile.metamodel.app.ui.Screen#getStartFragment()
	 * @see #getScreen()
	 * @generated
	 */
	EReference getScreen_StartFragment();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Control <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control</em>'.
	 * @see de.modagile.metamodel.app.ui.Control
	 * @generated
	 */
	EClass getControl();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Flow <em>Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flow</em>'.
	 * @see de.modagile.metamodel.app.ui.Flow
	 * @generated
	 */
	EClass getFlow();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.ui.Flow#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see de.modagile.metamodel.app.ui.Flow#getFrom()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_From();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.ui.Flow#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see de.modagile.metamodel.app.ui.Flow#getTo()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_To();

	/**
	 * Returns the meta object for the container reference '{@link de.modagile.metamodel.app.ui.Flow#getStoryBoard <em>Story Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Story Board</em>'.
	 * @see de.modagile.metamodel.app.ui.Flow#getStoryBoard()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_StoryBoard();

	/**
	 * Returns the meta object for the reference list '{@link de.modagile.metamodel.app.ui.Flow#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Events</em>'.
	 * @see de.modagile.metamodel.app.ui.Flow#getEvents()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_Events();

	/**
	 * Returns the meta object for the containment reference '{@link de.modagile.metamodel.app.ui.Flow#getFlowContext <em>Flow Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Flow Context</em>'.
	 * @see de.modagile.metamodel.app.ui.Flow#getFlowContext()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_FlowContext();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.Flow#isReturnsResult <em>Returns Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Returns Result</em>'.
	 * @see de.modagile.metamodel.app.ui.Flow#isReturnsResult()
	 * @see #getFlow()
	 * @generated
	 */
	EAttribute getFlow_ReturnsResult();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input</em>'.
	 * @see de.modagile.metamodel.app.ui.Input
	 * @generated
	 */
	EClass getInput();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.Input#getInputType <em>Input Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input Type</em>'.
	 * @see de.modagile.metamodel.app.ui.Input#getInputType()
	 * @see #getInput()
	 * @generated
	 */
	EAttribute getInput_InputType();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label</em>'.
	 * @see de.modagile.metamodel.app.ui.Label
	 * @generated
	 */
	EClass getLabel();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Button <em>Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Button</em>'.
	 * @see de.modagile.metamodel.app.ui.Button
	 * @generated
	 */
	EClass getButton();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.Button#getCaption <em>Caption</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Caption</em>'.
	 * @see de.modagile.metamodel.app.ui.Button#getCaption()
	 * @see #getButton()
	 * @generated
	 */
	EAttribute getButton_Caption();

	/**
	 * Returns the meta object for the reference list '{@link de.modagile.metamodel.app.ui.Button#getButtonClickEvents <em>Button Click Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Button Click Events</em>'.
	 * @see de.modagile.metamodel.app.ui.Button#getButtonClickEvents()
	 * @see #getButton()
	 * @generated
	 */
	EReference getButton_ButtonClickEvents();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.StoryBoard <em>Story Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Story Board</em>'.
	 * @see de.modagile.metamodel.app.ui.StoryBoard
	 * @generated
	 */
	EClass getStoryBoard();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.ui.StoryBoard#getFlows <em>Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Flows</em>'.
	 * @see de.modagile.metamodel.app.ui.StoryBoard#getFlows()
	 * @see #getStoryBoard()
	 * @generated
	 */
	EReference getStoryBoard_Flows();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.ui.StoryBoard#getScreens <em>Screens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Screens</em>'.
	 * @see de.modagile.metamodel.app.ui.StoryBoard#getScreens()
	 * @see #getStoryBoard()
	 * @generated
	 */
	EReference getStoryBoard_Screens();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.ui.StoryBoard#getStartScreen <em>Start Screen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start Screen</em>'.
	 * @see de.modagile.metamodel.app.ui.StoryBoard#getStartScreen()
	 * @see #getStoryBoard()
	 * @generated
	 */
	EReference getStoryBoard_StartScreen();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.StoryBoard#getResolutionX <em>Resolution X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolution X</em>'.
	 * @see de.modagile.metamodel.app.ui.StoryBoard#getResolutionX()
	 * @see #getStoryBoard()
	 * @generated
	 */
	EAttribute getStoryBoard_ResolutionX();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.StoryBoard#getResolutionY <em>Resolution Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolution Y</em>'.
	 * @see de.modagile.metamodel.app.ui.StoryBoard#getResolutionY()
	 * @see #getStoryBoard()
	 * @generated
	 */
	EAttribute getStoryBoard_ResolutionY();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.DisplayElement <em>Display Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Display Element</em>'.
	 * @see de.modagile.metamodel.app.ui.DisplayElement
	 * @generated
	 */
	EClass getDisplayElement();

	/**
	 * Returns the meta object for the container reference '{@link de.modagile.metamodel.app.ui.DisplayElement#getScreen <em>Screen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Screen</em>'.
	 * @see de.modagile.metamodel.app.ui.DisplayElement#getScreen()
	 * @see #getDisplayElement()
	 * @generated
	 */
	EReference getDisplayElement_Screen();

	/**
	 * Returns the meta object for the container reference '{@link de.modagile.metamodel.app.ui.DisplayElement#getCompositeType <em>Composite Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Composite Type</em>'.
	 * @see de.modagile.metamodel.app.ui.DisplayElement#getCompositeType()
	 * @see #getDisplayElement()
	 * @generated
	 */
	EReference getDisplayElement_CompositeType();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.DynamicList <em>Dynamic List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic List</em>'.
	 * @see de.modagile.metamodel.app.ui.DynamicList
	 * @generated
	 */
	EClass getDynamicList();

	/**
	 * Returns the meta object for the reference list '{@link de.modagile.metamodel.app.ui.DynamicList#getDisplayedElements <em>Displayed Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Displayed Elements</em>'.
	 * @see de.modagile.metamodel.app.ui.DynamicList#getDisplayedElements()
	 * @see #getDynamicList()
	 * @generated
	 */
	EReference getDynamicList_DisplayedElements();

	/**
	 * Returns the meta object for the reference list '{@link de.modagile.metamodel.app.ui.DynamicList#getListClickEvents <em>List Click Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>List Click Events</em>'.
	 * @see de.modagile.metamodel.app.ui.DynamicList#getListClickEvents()
	 * @see #getDynamicList()
	 * @generated
	 */
	EReference getDynamicList_ListClickEvents();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.InputContext <em>Input Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Context</em>'.
	 * @see de.modagile.metamodel.app.ui.InputContext
	 * @generated
	 */
	EClass getInputContext();

	/**
	 * Returns the meta object for the reference list '{@link de.modagile.metamodel.app.ui.InputContext#getContextType <em>Context Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Context Type</em>'.
	 * @see de.modagile.metamodel.app.ui.InputContext#getContextType()
	 * @see #getInputContext()
	 * @generated
	 */
	EReference getInputContext_ContextType();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Datepicker <em>Datepicker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Datepicker</em>'.
	 * @see de.modagile.metamodel.app.ui.Datepicker
	 * @generated
	 */
	EClass getDatepicker();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.Datepicker#getMaxDate <em>Max Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Date</em>'.
	 * @see de.modagile.metamodel.app.ui.Datepicker#getMaxDate()
	 * @see #getDatepicker()
	 * @generated
	 */
	EAttribute getDatepicker_MaxDate();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.Datepicker#getMinDate <em>Min Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Min Date</em>'.
	 * @see de.modagile.metamodel.app.ui.Datepicker#getMinDate()
	 * @see #getDatepicker()
	 * @generated
	 */
	EAttribute getDatepicker_MinDate();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Enableable <em>Enableable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enableable</em>'.
	 * @see de.modagile.metamodel.app.ui.Enableable
	 * @generated
	 */
	EClass getEnableable();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.TextContaining <em>Text Containing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Containing</em>'.
	 * @see de.modagile.metamodel.app.ui.TextContaining
	 * @generated
	 */
	EClass getTextContaining();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.TextContaining#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see de.modagile.metamodel.app.ui.TextContaining#getText()
	 * @see #getTextContaining()
	 * @generated
	 */
	EAttribute getTextContaining_Text();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see de.modagile.metamodel.app.ui.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.Image#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see de.modagile.metamodel.app.ui.Image#getWidth()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Width();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.Image#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see de.modagile.metamodel.app.ui.Image#getHeight()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Height();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.LocationPicker <em>Location Picker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Location Picker</em>'.
	 * @see de.modagile.metamodel.app.ui.LocationPicker
	 * @generated
	 */
	EClass getLocationPicker();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.CompositeDisplayElementType <em>Composite Display Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Display Element Type</em>'.
	 * @see de.modagile.metamodel.app.ui.CompositeDisplayElementType
	 * @generated
	 */
	EClass getCompositeDisplayElementType();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.ui.CompositeDisplayElementType#getContainedDisplayElements <em>Contained Display Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contained Display Elements</em>'.
	 * @see de.modagile.metamodel.app.ui.CompositeDisplayElementType#getContainedDisplayElements()
	 * @see #getCompositeDisplayElementType()
	 * @generated
	 */
	EReference getCompositeDisplayElementType_ContainedDisplayElements();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.CompositeDisplayElement <em>Composite Display Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Display Element</em>'.
	 * @see de.modagile.metamodel.app.ui.CompositeDisplayElement
	 * @generated
	 */
	EClass getCompositeDisplayElement();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.ui.CompositeDisplayElement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see de.modagile.metamodel.app.ui.CompositeDisplayElement#getType()
	 * @see #getCompositeDisplayElement()
	 * @generated
	 */
	EReference getCompositeDisplayElement_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.CompositeDisplayElement#getDisplayType <em>Display Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Type</em>'.
	 * @see de.modagile.metamodel.app.ui.CompositeDisplayElement#getDisplayType()
	 * @see #getCompositeDisplayElement()
	 * @generated
	 */
	EAttribute getCompositeDisplayElement_DisplayType();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.CheckBox <em>Check Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Check Box</em>'.
	 * @see de.modagile.metamodel.app.ui.CheckBox
	 * @generated
	 */
	EClass getCheckBox();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.ImageButton <em>Image Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image Button</em>'.
	 * @see de.modagile.metamodel.app.ui.ImageButton
	 * @generated
	 */
	EClass getImageButton();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.ImageButton#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see de.modagile.metamodel.app.ui.ImageButton#getWidth()
	 * @see #getImageButton()
	 * @generated
	 */
	EAttribute getImageButton_Width();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.ImageButton#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see de.modagile.metamodel.app.ui.ImageButton#getHeight()
	 * @see #getImageButton()
	 * @generated
	 */
	EAttribute getImageButton_Height();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.MenuBar <em>Menu Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Menu Bar</em>'.
	 * @see de.modagile.metamodel.app.ui.MenuBar
	 * @generated
	 */
	EClass getMenuBar();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.ui.MenuBar#getMenuBarElements <em>Menu Bar Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Menu Bar Elements</em>'.
	 * @see de.modagile.metamodel.app.ui.MenuBar#getMenuBarElements()
	 * @see #getMenuBar()
	 * @generated
	 */
	EReference getMenuBar_MenuBarElements();

	/**
	 * Returns the meta object for the container reference '{@link de.modagile.metamodel.app.ui.MenuBar#getScreen <em>Screen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Screen</em>'.
	 * @see de.modagile.metamodel.app.ui.MenuBar#getScreen()
	 * @see #getMenuBar()
	 * @generated
	 */
	EReference getMenuBar_Screen();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.FragmentNavigation <em>Fragment Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fragment Navigation</em>'.
	 * @see de.modagile.metamodel.app.ui.FragmentNavigation
	 * @generated
	 */
	EClass getFragmentNavigation();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.ui.FragmentNavigation#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see de.modagile.metamodel.app.ui.FragmentNavigation#getFrom()
	 * @see #getFragmentNavigation()
	 * @generated
	 */
	EReference getFragmentNavigation_From();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.ui.FragmentNavigation#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see de.modagile.metamodel.app.ui.FragmentNavigation#getTo()
	 * @see #getFragmentNavigation()
	 * @generated
	 */
	EReference getFragmentNavigation_To();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.ui.FragmentNavigation#getNavigationType <em>Navigation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Navigation Type</em>'.
	 * @see de.modagile.metamodel.app.ui.FragmentNavigation#getNavigationType()
	 * @see #getFragmentNavigation()
	 * @generated
	 */
	EAttribute getFragmentNavigation_NavigationType();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.ui.Picker <em>Picker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Picker</em>'.
	 * @see de.modagile.metamodel.app.ui.Picker
	 * @generated
	 */
	EClass getPicker();

	/**
	 * Returns the meta object for enum '{@link de.modagile.metamodel.app.ui.DisplayType <em>Display Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Display Type</em>'.
	 * @see de.modagile.metamodel.app.ui.DisplayType
	 * @generated
	 */
	EEnum getDisplayType();

	/**
	 * Returns the meta object for enum '{@link de.modagile.metamodel.app.ui.InputType <em>Input Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Input Type</em>'.
	 * @see de.modagile.metamodel.app.ui.InputType
	 * @generated
	 */
	EEnum getInputType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UIFactory getUIFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.ScreenImpl <em>Screen</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.ScreenImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getScreen()
		 * @generated
		 */
		EClass SCREEN = eINSTANCE.getScreen();

		/**
		 * The meta object literal for the '<em><b>Display Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCREEN__DISPLAY_ELEMENTS = eINSTANCE.getScreen_DisplayElements();

		/**
		 * The meta object literal for the '<em><b>Story Board</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCREEN__STORY_BOARD = eINSTANCE.getScreen_StoryBoard();

		/**
		 * The meta object literal for the '<em><b>Menu Bar</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCREEN__MENU_BAR = eINSTANCE.getScreen_MenuBar();

		/**
		 * The meta object literal for the '<em><b>Fragment Navigations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCREEN__FRAGMENT_NAVIGATIONS = eINSTANCE.getScreen_FragmentNavigations();

		/**
		 * The meta object literal for the '<em><b>Start Fragment</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCREEN__START_FRAGMENT = eINSTANCE.getScreen_StartFragment();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.ControlImpl <em>Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.ControlImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getControl()
		 * @generated
		 */
		EClass CONTROL = eINSTANCE.getControl();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.FlowImpl <em>Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.FlowImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getFlow()
		 * @generated
		 */
		EClass FLOW = eINSTANCE.getFlow();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__FROM = eINSTANCE.getFlow_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__TO = eINSTANCE.getFlow_To();

		/**
		 * The meta object literal for the '<em><b>Story Board</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__STORY_BOARD = eINSTANCE.getFlow_StoryBoard();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__EVENTS = eINSTANCE.getFlow_Events();

		/**
		 * The meta object literal for the '<em><b>Flow Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__FLOW_CONTEXT = eINSTANCE.getFlow_FlowContext();

		/**
		 * The meta object literal for the '<em><b>Returns Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW__RETURNS_RESULT = eINSTANCE.getFlow_ReturnsResult();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.InputImpl <em>Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.InputImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getInput()
		 * @generated
		 */
		EClass INPUT = eINSTANCE.getInput();

		/**
		 * The meta object literal for the '<em><b>Input Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INPUT__INPUT_TYPE = eINSTANCE.getInput_InputType();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.LabelImpl <em>Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.LabelImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getLabel()
		 * @generated
		 */
		EClass LABEL = eINSTANCE.getLabel();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.ButtonImpl <em>Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.ButtonImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getButton()
		 * @generated
		 */
		EClass BUTTON = eINSTANCE.getButton();

		/**
		 * The meta object literal for the '<em><b>Caption</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUTTON__CAPTION = eINSTANCE.getButton_Caption();

		/**
		 * The meta object literal for the '<em><b>Button Click Events</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUTTON__BUTTON_CLICK_EVENTS = eINSTANCE.getButton_ButtonClickEvents();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.StoryBoardImpl <em>Story Board</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.StoryBoardImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getStoryBoard()
		 * @generated
		 */
		EClass STORY_BOARD = eINSTANCE.getStoryBoard();

		/**
		 * The meta object literal for the '<em><b>Flows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORY_BOARD__FLOWS = eINSTANCE.getStoryBoard_Flows();

		/**
		 * The meta object literal for the '<em><b>Screens</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORY_BOARD__SCREENS = eINSTANCE.getStoryBoard_Screens();

		/**
		 * The meta object literal for the '<em><b>Start Screen</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STORY_BOARD__START_SCREEN = eINSTANCE.getStoryBoard_StartScreen();

		/**
		 * The meta object literal for the '<em><b>Resolution X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORY_BOARD__RESOLUTION_X = eINSTANCE.getStoryBoard_ResolutionX();

		/**
		 * The meta object literal for the '<em><b>Resolution Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STORY_BOARD__RESOLUTION_Y = eINSTANCE.getStoryBoard_ResolutionY();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.DisplayElementImpl <em>Display Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.DisplayElementImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getDisplayElement()
		 * @generated
		 */
		EClass DISPLAY_ELEMENT = eINSTANCE.getDisplayElement();

		/**
		 * The meta object literal for the '<em><b>Screen</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISPLAY_ELEMENT__SCREEN = eINSTANCE.getDisplayElement_Screen();

		/**
		 * The meta object literal for the '<em><b>Composite Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISPLAY_ELEMENT__COMPOSITE_TYPE = eINSTANCE.getDisplayElement_CompositeType();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.DynamicListImpl <em>Dynamic List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.DynamicListImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getDynamicList()
		 * @generated
		 */
		EClass DYNAMIC_LIST = eINSTANCE.getDynamicList();

		/**
		 * The meta object literal for the '<em><b>Displayed Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_LIST__DISPLAYED_ELEMENTS = eINSTANCE.getDynamicList_DisplayedElements();

		/**
		 * The meta object literal for the '<em><b>List Click Events</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_LIST__LIST_CLICK_EVENTS = eINSTANCE.getDynamicList_ListClickEvents();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.InputContextImpl <em>Input Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.InputContextImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getInputContext()
		 * @generated
		 */
		EClass INPUT_CONTEXT = eINSTANCE.getInputContext();

		/**
		 * The meta object literal for the '<em><b>Context Type</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INPUT_CONTEXT__CONTEXT_TYPE = eINSTANCE.getInputContext_ContextType();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.DatepickerImpl <em>Datepicker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.DatepickerImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getDatepicker()
		 * @generated
		 */
		EClass DATEPICKER = eINSTANCE.getDatepicker();

		/**
		 * The meta object literal for the '<em><b>Max Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATEPICKER__MAX_DATE = eINSTANCE.getDatepicker_MaxDate();

		/**
		 * The meta object literal for the '<em><b>Min Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATEPICKER__MIN_DATE = eINSTANCE.getDatepicker_MinDate();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.Enableable <em>Enableable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.Enableable
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getEnableable()
		 * @generated
		 */
		EClass ENABLEABLE = eINSTANCE.getEnableable();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.TextContaining <em>Text Containing</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.TextContaining
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getTextContaining()
		 * @generated
		 */
		EClass TEXT_CONTAINING = eINSTANCE.getTextContaining();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_CONTAINING__TEXT = eINSTANCE.getTextContaining_Text();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.ImageImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__WIDTH = eINSTANCE.getImage_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__HEIGHT = eINSTANCE.getImage_Height();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.LocationPickerImpl <em>Location Picker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.LocationPickerImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getLocationPicker()
		 * @generated
		 */
		EClass LOCATION_PICKER = eINSTANCE.getLocationPicker();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.CompositeDisplayElementTypeImpl <em>Composite Display Element Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.CompositeDisplayElementTypeImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getCompositeDisplayElementType()
		 * @generated
		 */
		EClass COMPOSITE_DISPLAY_ELEMENT_TYPE = eINSTANCE.getCompositeDisplayElementType();

		/**
		 * The meta object literal for the '<em><b>Contained Display Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS = eINSTANCE.getCompositeDisplayElementType_ContainedDisplayElements();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.CompositeDisplayElementImpl <em>Composite Display Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.CompositeDisplayElementImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getCompositeDisplayElement()
		 * @generated
		 */
		EClass COMPOSITE_DISPLAY_ELEMENT = eINSTANCE.getCompositeDisplayElement();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_DISPLAY_ELEMENT__TYPE = eINSTANCE.getCompositeDisplayElement_Type();

		/**
		 * The meta object literal for the '<em><b>Display Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_DISPLAY_ELEMENT__DISPLAY_TYPE = eINSTANCE.getCompositeDisplayElement_DisplayType();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.CheckBoxImpl <em>Check Box</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.CheckBoxImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getCheckBox()
		 * @generated
		 */
		EClass CHECK_BOX = eINSTANCE.getCheckBox();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.ImageButtonImpl <em>Image Button</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.ImageButtonImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getImageButton()
		 * @generated
		 */
		EClass IMAGE_BUTTON = eINSTANCE.getImageButton();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE_BUTTON__WIDTH = eINSTANCE.getImageButton_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE_BUTTON__HEIGHT = eINSTANCE.getImageButton_Height();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.MenuBarImpl <em>Menu Bar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.MenuBarImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getMenuBar()
		 * @generated
		 */
		EClass MENU_BAR = eINSTANCE.getMenuBar();

		/**
		 * The meta object literal for the '<em><b>Menu Bar Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_BAR__MENU_BAR_ELEMENTS = eINSTANCE.getMenuBar_MenuBarElements();

		/**
		 * The meta object literal for the '<em><b>Screen</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MENU_BAR__SCREEN = eINSTANCE.getMenuBar_Screen();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.FragmentNavigationImpl <em>Fragment Navigation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.FragmentNavigationImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getFragmentNavigation()
		 * @generated
		 */
		EClass FRAGMENT_NAVIGATION = eINSTANCE.getFragmentNavigation();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT_NAVIGATION__FROM = eINSTANCE.getFragmentNavigation_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FRAGMENT_NAVIGATION__TO = eINSTANCE.getFragmentNavigation_To();

		/**
		 * The meta object literal for the '<em><b>Navigation Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FRAGMENT_NAVIGATION__NAVIGATION_TYPE = eINSTANCE.getFragmentNavigation_NavigationType();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.impl.PickerImpl <em>Picker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.impl.PickerImpl
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getPicker()
		 * @generated
		 */
		EClass PICKER = eINSTANCE.getPicker();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.DisplayType <em>Display Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.DisplayType
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getDisplayType()
		 * @generated
		 */
		EEnum DISPLAY_TYPE = eINSTANCE.getDisplayType();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.ui.InputType <em>Input Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.ui.InputType
		 * @see de.modagile.metamodel.app.ui.impl.UIPackageImpl#getInputType()
		 * @generated
		 */
		EEnum INPUT_TYPE = eINSTANCE.getInputType();

	}

} //UIPackage
