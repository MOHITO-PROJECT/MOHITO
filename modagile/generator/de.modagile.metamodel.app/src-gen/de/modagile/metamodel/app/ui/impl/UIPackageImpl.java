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
package de.modagile.metamodel.app.ui.impl;

import de.modagile.metamodel.app.AppPackage;

import de.modagile.metamodel.app.domain.DomainPackage;

import de.modagile.metamodel.app.domain.impl.DomainPackageImpl;

import de.modagile.metamodel.app.event.EventPackage;

import de.modagile.metamodel.app.event.impl.EventPackageImpl;

import de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage;

import de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl;

import de.modagile.metamodel.app.impl.AppPackageImpl;

import de.modagile.metamodel.app.platform.PlatformPackage;

import de.modagile.metamodel.app.platform.impl.PlatformPackageImpl;

import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CheckBox;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.Control;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DisplayType;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.Enableable;
import de.modagile.metamodel.app.ui.Flow;
import de.modagile.metamodel.app.ui.FragmentNavigation;
import de.modagile.metamodel.app.ui.Image;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.Input;
import de.modagile.metamodel.app.ui.InputContext;
import de.modagile.metamodel.app.ui.InputType;
import de.modagile.metamodel.app.ui.Label;
import de.modagile.metamodel.app.ui.LocationPicker;
import de.modagile.metamodel.app.ui.MenuBar;
import de.modagile.metamodel.app.ui.Picker;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import de.modagile.metamodel.app.ui.TextContaining;
import de.modagile.metamodel.app.ui.UIFactory;
import de.modagile.metamodel.app.ui.UIPackage;

import de.modagile.metamodel.app.ui.util.UIValidator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UIPackageImpl extends EPackageImpl implements UIPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass screenEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass flowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass buttonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass storyBoardEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass displayElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicListEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass inputContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass datepickerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enableableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textContainingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass locationPickerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeDisplayElementTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeDisplayElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checkBoxEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageButtonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass menuBarEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fragmentNavigationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pickerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum displayTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum inputTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.modagile.metamodel.app.ui.UIPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UIPackageImpl() {
		super(eNS_URI, UIFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link UIPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UIPackage init() {
		if (isInited) return (UIPackage)EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI);

		// Obtain or create and register package
		UIPackageImpl theUIPackage = (UIPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UIPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UIPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		AppPackageImpl theAppPackage = (AppPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI) instanceof AppPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI) : AppPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);
		EventPackageImpl theEventPackage = (EventPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI) instanceof EventPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI) : EventPackage.eINSTANCE);
		GeneratorconfigPackageImpl theGeneratorconfigPackage = (GeneratorconfigPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneratorconfigPackage.eNS_URI) instanceof GeneratorconfigPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneratorconfigPackage.eNS_URI) : GeneratorconfigPackage.eINSTANCE);

		// Create package meta-data objects
		theUIPackage.createPackageContents();
		theAppPackage.createPackageContents();
		theDomainPackage.createPackageContents();
		theEventPackage.createPackageContents();
		theGeneratorconfigPackage.createPackageContents();

		// Initialize created meta-data
		theUIPackage.initializePackageContents();
		theAppPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();
		theEventPackage.initializePackageContents();
		theGeneratorconfigPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theUIPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return UIValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theUIPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UIPackage.eNS_URI, theUIPackage);
		return theUIPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScreen() {
		return screenEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScreen_DisplayElements() {
		return (EReference)screenEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScreen_StoryBoard() {
		return (EReference)screenEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScreen_MenuBar() {
		return (EReference)screenEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScreen_FragmentNavigations() {
		return (EReference)screenEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScreen_StartFragment() {
		return (EReference)screenEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getControl() {
		return controlEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFlow() {
		return flowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFlow_From() {
		return (EReference)flowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFlow_To() {
		return (EReference)flowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFlow_StoryBoard() {
		return (EReference)flowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFlow_Events() {
		return (EReference)flowEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFlow_FlowContext() {
		return (EReference)flowEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFlow_ReturnsResult() {
		return (EAttribute)flowEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInput() {
		return inputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInput_InputType() {
		return (EAttribute)inputEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabel() {
		return labelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getButton() {
		return buttonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getButton_Caption() {
		return (EAttribute)buttonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getButton_ButtonClickEvents() {
		return (EReference)buttonEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStoryBoard() {
		return storyBoardEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStoryBoard_Flows() {
		return (EReference)storyBoardEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStoryBoard_Screens() {
		return (EReference)storyBoardEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStoryBoard_StartScreen() {
		return (EReference)storyBoardEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoryBoard_ResolutionX() {
		return (EAttribute)storyBoardEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoryBoard_ResolutionY() {
		return (EAttribute)storyBoardEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDisplayElement() {
		return displayElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDisplayElement_Screen() {
		return (EReference)displayElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDisplayElement_CompositeType() {
		return (EReference)displayElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicList() {
		return dynamicListEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicList_DisplayedElements() {
		return (EReference)dynamicListEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicList_ListClickEvents() {
		return (EReference)dynamicListEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInputContext() {
		return inputContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInputContext_ContextType() {
		return (EReference)inputContextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDatepicker() {
		return datepickerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatepicker_MaxDate() {
		return (EAttribute)datepickerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatepicker_MinDate() {
		return (EAttribute)datepickerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnableable() {
		return enableableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextContaining() {
		return textContainingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextContaining_Text() {
		return (EAttribute)textContainingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImage() {
		return imageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Width() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Height() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocationPicker() {
		return locationPickerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeDisplayElementType() {
		return compositeDisplayElementTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeDisplayElementType_ContainedDisplayElements() {
		return (EReference)compositeDisplayElementTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeDisplayElement() {
		return compositeDisplayElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeDisplayElement_Type() {
		return (EReference)compositeDisplayElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompositeDisplayElement_DisplayType() {
		return (EAttribute)compositeDisplayElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCheckBox() {
		return checkBoxEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImageButton() {
		return imageButtonEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImageButton_Width() {
		return (EAttribute)imageButtonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImageButton_Height() {
		return (EAttribute)imageButtonEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMenuBar() {
		return menuBarEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMenuBar_MenuBarElements() {
		return (EReference)menuBarEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMenuBar_Screen() {
		return (EReference)menuBarEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFragmentNavigation() {
		return fragmentNavigationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragmentNavigation_From() {
		return (EReference)fragmentNavigationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFragmentNavigation_To() {
		return (EReference)fragmentNavigationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFragmentNavigation_NavigationType() {
		return (EAttribute)fragmentNavigationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPicker() {
		return pickerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDisplayType() {
		return displayTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInputType() {
		return inputTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIFactory getUIFactory() {
		return (UIFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		screenEClass = createEClass(SCREEN);
		createEReference(screenEClass, SCREEN__DISPLAY_ELEMENTS);
		createEReference(screenEClass, SCREEN__STORY_BOARD);
		createEReference(screenEClass, SCREEN__MENU_BAR);
		createEReference(screenEClass, SCREEN__FRAGMENT_NAVIGATIONS);
		createEReference(screenEClass, SCREEN__START_FRAGMENT);

		controlEClass = createEClass(CONTROL);

		flowEClass = createEClass(FLOW);
		createEReference(flowEClass, FLOW__FROM);
		createEReference(flowEClass, FLOW__TO);
		createEReference(flowEClass, FLOW__STORY_BOARD);
		createEReference(flowEClass, FLOW__EVENTS);
		createEReference(flowEClass, FLOW__FLOW_CONTEXT);
		createEAttribute(flowEClass, FLOW__RETURNS_RESULT);

		inputEClass = createEClass(INPUT);
		createEAttribute(inputEClass, INPUT__INPUT_TYPE);

		labelEClass = createEClass(LABEL);

		buttonEClass = createEClass(BUTTON);
		createEAttribute(buttonEClass, BUTTON__CAPTION);
		createEReference(buttonEClass, BUTTON__BUTTON_CLICK_EVENTS);

		storyBoardEClass = createEClass(STORY_BOARD);
		createEReference(storyBoardEClass, STORY_BOARD__FLOWS);
		createEReference(storyBoardEClass, STORY_BOARD__SCREENS);
		createEReference(storyBoardEClass, STORY_BOARD__START_SCREEN);
		createEAttribute(storyBoardEClass, STORY_BOARD__RESOLUTION_X);
		createEAttribute(storyBoardEClass, STORY_BOARD__RESOLUTION_Y);

		displayElementEClass = createEClass(DISPLAY_ELEMENT);
		createEReference(displayElementEClass, DISPLAY_ELEMENT__SCREEN);
		createEReference(displayElementEClass, DISPLAY_ELEMENT__COMPOSITE_TYPE);

		dynamicListEClass = createEClass(DYNAMIC_LIST);
		createEReference(dynamicListEClass, DYNAMIC_LIST__DISPLAYED_ELEMENTS);
		createEReference(dynamicListEClass, DYNAMIC_LIST__LIST_CLICK_EVENTS);

		inputContextEClass = createEClass(INPUT_CONTEXT);
		createEReference(inputContextEClass, INPUT_CONTEXT__CONTEXT_TYPE);

		datepickerEClass = createEClass(DATEPICKER);
		createEAttribute(datepickerEClass, DATEPICKER__MAX_DATE);
		createEAttribute(datepickerEClass, DATEPICKER__MIN_DATE);

		enableableEClass = createEClass(ENABLEABLE);

		textContainingEClass = createEClass(TEXT_CONTAINING);
		createEAttribute(textContainingEClass, TEXT_CONTAINING__TEXT);

		imageEClass = createEClass(IMAGE);
		createEAttribute(imageEClass, IMAGE__WIDTH);
		createEAttribute(imageEClass, IMAGE__HEIGHT);

		locationPickerEClass = createEClass(LOCATION_PICKER);

		compositeDisplayElementTypeEClass = createEClass(COMPOSITE_DISPLAY_ELEMENT_TYPE);
		createEReference(compositeDisplayElementTypeEClass, COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS);

		compositeDisplayElementEClass = createEClass(COMPOSITE_DISPLAY_ELEMENT);
		createEReference(compositeDisplayElementEClass, COMPOSITE_DISPLAY_ELEMENT__TYPE);
		createEAttribute(compositeDisplayElementEClass, COMPOSITE_DISPLAY_ELEMENT__DISPLAY_TYPE);

		checkBoxEClass = createEClass(CHECK_BOX);

		imageButtonEClass = createEClass(IMAGE_BUTTON);
		createEAttribute(imageButtonEClass, IMAGE_BUTTON__WIDTH);
		createEAttribute(imageButtonEClass, IMAGE_BUTTON__HEIGHT);

		menuBarEClass = createEClass(MENU_BAR);
		createEReference(menuBarEClass, MENU_BAR__MENU_BAR_ELEMENTS);
		createEReference(menuBarEClass, MENU_BAR__SCREEN);

		fragmentNavigationEClass = createEClass(FRAGMENT_NAVIGATION);
		createEReference(fragmentNavigationEClass, FRAGMENT_NAVIGATION__FROM);
		createEReference(fragmentNavigationEClass, FRAGMENT_NAVIGATION__TO);
		createEAttribute(fragmentNavigationEClass, FRAGMENT_NAVIGATION__NAVIGATION_TYPE);

		pickerEClass = createEClass(PICKER);

		// Create enums
		displayTypeEEnum = createEEnum(DISPLAY_TYPE);
		inputTypeEEnum = createEEnum(INPUT_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		AppPackage theAppPackage = (AppPackage)EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI);
		EventPackage theEventPackage = (EventPackage)EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		screenEClass.getESuperTypes().add(theAppPackage.getFeaturedElement());
		controlEClass.getESuperTypes().add(theAppPackage.getFeaturedElement());
		controlEClass.getESuperTypes().add(this.getDisplayElement());
		flowEClass.getESuperTypes().add(theAppPackage.getEntity());
		inputEClass.getESuperTypes().add(this.getDisplayElement());
		inputEClass.getESuperTypes().add(this.getTextContaining());
		labelEClass.getESuperTypes().add(this.getDisplayElement());
		labelEClass.getESuperTypes().add(this.getTextContaining());
		buttonEClass.getESuperTypes().add(this.getControl());
		buttonEClass.getESuperTypes().add(this.getTextContaining());
		storyBoardEClass.getESuperTypes().add(theAppPackage.getFeaturedElement());
		displayElementEClass.getESuperTypes().add(theAppPackage.getEntity());
		displayElementEClass.getESuperTypes().add(this.getEnableable());
		dynamicListEClass.getESuperTypes().add(this.getDisplayElement());
		datepickerEClass.getESuperTypes().add(this.getTextContaining());
		datepickerEClass.getESuperTypes().add(this.getPicker());
		imageEClass.getESuperTypes().add(this.getDisplayElement());
		locationPickerEClass.getESuperTypes().add(this.getPicker());
		compositeDisplayElementTypeEClass.getESuperTypes().add(theAppPackage.getFeaturedElement());
		compositeDisplayElementEClass.getESuperTypes().add(this.getDisplayElement());
		checkBoxEClass.getESuperTypes().add(this.getDisplayElement());
		imageButtonEClass.getESuperTypes().add(this.getButton());
		pickerEClass.getESuperTypes().add(this.getDisplayElement());

		// Initialize classes and features; add operations and parameters
		initEClass(screenEClass, Screen.class, "Screen", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScreen_DisplayElements(), this.getDisplayElement(), this.getDisplayElement_Screen(), "displayElements", null, 0, -1, Screen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScreen_StoryBoard(), this.getStoryBoard(), this.getStoryBoard_Screens(), "storyBoard", null, 1, 1, Screen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScreen_MenuBar(), this.getMenuBar(), this.getMenuBar_Screen(), "menuBar", null, 0, 1, Screen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScreen_FragmentNavigations(), this.getFragmentNavigation(), null, "fragmentNavigations", null, 0, -1, Screen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScreen_StartFragment(), this.getCompositeDisplayElement(), null, "startFragment", null, 0, 1, Screen.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlEClass, Control.class, "Control", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(flowEClass, Flow.class, "Flow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFlow_From(), this.getScreen(), null, "from", null, 1, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_To(), this.getScreen(), null, "to", null, 1, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_StoryBoard(), this.getStoryBoard(), this.getStoryBoard_Flows(), "storyBoard", null, 1, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_Events(), theEventPackage.getEvent(), theEventPackage.getEvent_Triggers(), "events", null, 0, -1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFlow_FlowContext(), this.getInputContext(), null, "flowContext", null, 0, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFlow_ReturnsResult(), ecorePackage.getEBoolean(), "returnsResult", null, 1, 1, Flow.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputEClass, Input.class, "Input", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInput_InputType(), this.getInputType(), "inputType", "0", 1, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelEClass, Label.class, "Label", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(buttonEClass, Button.class, "Button", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getButton_Caption(), ecorePackage.getEString(), "caption", null, 0, 1, Button.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getButton_ButtonClickEvents(), theEventPackage.getButtonClickEvent(), theEventPackage.getButtonClickEvent_Button(), "buttonClickEvents", null, 0, -1, Button.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(storyBoardEClass, StoryBoard.class, "StoryBoard", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStoryBoard_Flows(), this.getFlow(), this.getFlow_StoryBoard(), "flows", null, 0, -1, StoryBoard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStoryBoard_Screens(), this.getScreen(), this.getScreen_StoryBoard(), "screens", null, 0, -1, StoryBoard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStoryBoard_StartScreen(), this.getScreen(), null, "startScreen", null, 1, 1, StoryBoard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStoryBoard_ResolutionX(), ecorePackage.getEInt(), "resolutionX", null, 0, 1, StoryBoard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStoryBoard_ResolutionY(), ecorePackage.getEInt(), "resolutionY", null, 0, 1, StoryBoard.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(displayElementEClass, DisplayElement.class, "DisplayElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDisplayElement_Screen(), this.getScreen(), this.getScreen_DisplayElements(), "screen", null, 0, 1, DisplayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDisplayElement_CompositeType(), this.getCompositeDisplayElementType(), this.getCompositeDisplayElementType_ContainedDisplayElements(), "compositeType", null, 0, 1, DisplayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dynamicListEClass, DynamicList.class, "DynamicList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDynamicList_DisplayedElements(), this.getCompositeDisplayElementType(), null, "displayedElements", null, 0, -1, DynamicList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicList_ListClickEvents(), theEventPackage.getListClickEvent(), theEventPackage.getListClickEvent_List(), "listClickEvents", null, 0, -1, DynamicList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputContextEClass, InputContext.class, "InputContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInputContext_ContextType(), ecorePackage.getEClass(), null, "contextType", null, 0, -1, InputContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(datepickerEClass, Datepicker.class, "Datepicker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDatepicker_MaxDate(), ecorePackage.getEString(), "maxDate", null, 0, 1, Datepicker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDatepicker_MinDate(), ecorePackage.getEString(), "minDate", null, 0, 1, Datepicker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enableableEClass, Enableable.class, "Enableable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(textContainingEClass, TextContaining.class, "TextContaining", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextContaining_Text(), ecorePackage.getEString(), "text", null, 0, 1, TextContaining.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(imageEClass, Image.class, "Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImage_Width(), ecorePackage.getEInt(), "width", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImage_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(locationPickerEClass, LocationPicker.class, "LocationPicker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeDisplayElementTypeEClass, CompositeDisplayElementType.class, "CompositeDisplayElementType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeDisplayElementType_ContainedDisplayElements(), this.getDisplayElement(), this.getDisplayElement_CompositeType(), "containedDisplayElements", null, 0, -1, CompositeDisplayElementType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeDisplayElementEClass, CompositeDisplayElement.class, "CompositeDisplayElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeDisplayElement_Type(), this.getCompositeDisplayElementType(), null, "type", null, 1, 1, CompositeDisplayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCompositeDisplayElement_DisplayType(), this.getDisplayType(), "displayType", null, 0, 1, CompositeDisplayElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(checkBoxEClass, CheckBox.class, "CheckBox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(imageButtonEClass, ImageButton.class, "ImageButton", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImageButton_Width(), ecorePackage.getEInt(), "width", null, 0, 1, ImageButton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImageButton_Height(), ecorePackage.getEInt(), "height", null, 0, 1, ImageButton.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(menuBarEClass, MenuBar.class, "MenuBar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMenuBar_MenuBarElements(), this.getDisplayElement(), null, "menuBarElements", null, 0, -1, MenuBar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMenuBar_Screen(), this.getScreen(), this.getScreen_MenuBar(), "screen", null, 1, 1, MenuBar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fragmentNavigationEClass, FragmentNavigation.class, "FragmentNavigation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFragmentNavigation_From(), this.getCompositeDisplayElement(), null, "from", null, 1, 1, FragmentNavigation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFragmentNavigation_To(), this.getCompositeDisplayElement(), null, "to", null, 1, 1, FragmentNavigation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFragmentNavigation_NavigationType(), this.getDisplayType(), "navigationType", null, 0, 1, FragmentNavigation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pickerEClass, Picker.class, "Picker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(displayTypeEEnum, DisplayType.class, "DisplayType");
		addEEnumLiteral(displayTypeEEnum, DisplayType.SEPARATE);
		addEEnumLiteral(displayTypeEEnum, DisplayType.PANORAMIC);

		initEEnum(inputTypeEEnum, InputType.class, "InputType");
		addEEnumLiteral(inputTypeEEnum, InputType.TEXT);
		addEEnumLiteral(inputTypeEEnum, InputType.NUMERICS);
		addEEnumLiteral(inputTypeEEnum, InputType.MAIL);
		addEEnumLiteral(inputTypeEEnum, InputType.WEB_ADDRESS);
		addEEnumLiteral(inputTypeEEnum, InputType.CUSTOM);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot
		createPivotAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "invocationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
			 "settingDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
			 "validationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot"
		   });		
		addAnnotation
		  (screenEClass, 
		   source, 
		   new String[] {
			 "constraints", "onlyAlreadContainedStartFragmentAllowed"
		   });			
		addAnnotation
		  (buttonEClass, 
		   source, 
		   new String[] {
			 "constraints", "Button_Can_Only_Have_One_Event_If_In_Screen"
		   });			
		addAnnotation
		  (dynamicListEClass, 
		   source, 
		   new String[] {
			 "constraints", "List_Can_Only_Have_One_Event_If_In_Screen"
		   });			
		addAnnotation
		  (menuBarEClass, 
		   source, 
		   new String[] {
			 "constraints", "onlyLabelsButtonsAndCheckBoxesAllowed"
		   });			
		addAnnotation
		  (fragmentNavigationEClass, 
		   source, 
		   new String[] {
			 "constraints", "navigationWithinSameScreen"
		   });	
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createPivotAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot";				
		addAnnotation
		  (screenEClass, 
		   source, 
		   new String[] {
			 "onlyAlreadContainedStartFragmentAllowed", "startFragment = null or displayElements->includes(startFragment)"
		   });			
		addAnnotation
		  (buttonEClass, 
		   source, 
		   new String[] {
			 "Button_Can_Only_Have_One_Event_If_In_Screen", "self.screen <> null and buttonClickEvents->size() <= 1 or self.screen = null"
		   });			
		addAnnotation
		  (dynamicListEClass, 
		   source, 
		   new String[] {
			 "List_Can_Only_Have_One_Event_If_In_Screen", "self.screen <> null and listClickEvents->size() <= 1 or self.screen = null"
		   });			
		addAnnotation
		  (menuBarEClass, 
		   source, 
		   new String[] {
			 "onlyLabelsButtonsAndCheckBoxesAllowed", "self.menuBarElements->forAll(menubarItem : DisplayElement | menubarItem.oclIsTypeOf(Button) or menubarItem.oclIsTypeOf(ImageButton) or menubarItem.oclIsTypeOf(Label) or menubarItem.oclIsTypeOf(CheckBox) or menubarItem.oclIsTypeOf(Image))"
		   });			
		addAnnotation
		  (fragmentNavigationEClass, 
		   source, 
		   new String[] {
			 "navigationWithinSameScreen", "to.screen = from.screen"
		   });
	}

} //UIPackageImpl
