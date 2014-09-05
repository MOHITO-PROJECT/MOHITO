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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.modagile.metamodel.app.ui.UIPackage
 * @generated
 */
public class UIAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UIPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UIAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = UIPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UISwitch<Adapter> modelSwitch =
		new UISwitch<Adapter>() {
			@Override
			public Adapter caseScreen(Screen object) {
				return createScreenAdapter();
			}
			@Override
			public Adapter caseControl(Control object) {
				return createControlAdapter();
			}
			@Override
			public Adapter caseFlow(Flow object) {
				return createFlowAdapter();
			}
			@Override
			public Adapter caseInput(Input object) {
				return createInputAdapter();
			}
			@Override
			public Adapter caseLabel(Label object) {
				return createLabelAdapter();
			}
			@Override
			public Adapter caseButton(Button object) {
				return createButtonAdapter();
			}
			@Override
			public Adapter caseStoryBoard(StoryBoard object) {
				return createStoryBoardAdapter();
			}
			@Override
			public Adapter caseDisplayElement(DisplayElement object) {
				return createDisplayElementAdapter();
			}
			@Override
			public Adapter caseDynamicList(DynamicList object) {
				return createDynamicListAdapter();
			}
			@Override
			public Adapter caseInputContext(InputContext object) {
				return createInputContextAdapter();
			}
			@Override
			public Adapter caseDatepicker(Datepicker object) {
				return createDatepickerAdapter();
			}
			@Override
			public Adapter caseEnableable(Enableable object) {
				return createEnableableAdapter();
			}
			@Override
			public Adapter caseTextContaining(TextContaining object) {
				return createTextContainingAdapter();
			}
			@Override
			public Adapter caseImage(Image object) {
				return createImageAdapter();
			}
			@Override
			public Adapter caseLocationPicker(LocationPicker object) {
				return createLocationPickerAdapter();
			}
			@Override
			public Adapter caseCompositeDisplayElementType(CompositeDisplayElementType object) {
				return createCompositeDisplayElementTypeAdapter();
			}
			@Override
			public Adapter caseCompositeDisplayElement(CompositeDisplayElement object) {
				return createCompositeDisplayElementAdapter();
			}
			@Override
			public Adapter caseCheckBox(CheckBox object) {
				return createCheckBoxAdapter();
			}
			@Override
			public Adapter caseImageButton(ImageButton object) {
				return createImageButtonAdapter();
			}
			@Override
			public Adapter caseMenuBar(MenuBar object) {
				return createMenuBarAdapter();
			}
			@Override
			public Adapter caseFragmentNavigation(FragmentNavigation object) {
				return createFragmentNavigationAdapter();
			}
			@Override
			public Adapter casePicker(Picker object) {
				return createPickerAdapter();
			}
			@Override
			public Adapter caseEntity(Entity object) {
				return createEntityAdapter();
			}
			@Override
			public Adapter caseFeaturedElement(FeaturedElement object) {
				return createFeaturedElementAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Screen <em>Screen</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Screen
	 * @generated
	 */
	public Adapter createScreenAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Control <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Control
	 * @generated
	 */
	public Adapter createControlAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Flow <em>Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Flow
	 * @generated
	 */
	public Adapter createFlowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Input
	 * @generated
	 */
	public Adapter createInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Label
	 * @generated
	 */
	public Adapter createLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Button <em>Button</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Button
	 * @generated
	 */
	public Adapter createButtonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.StoryBoard <em>Story Board</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.StoryBoard
	 * @generated
	 */
	public Adapter createStoryBoardAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.DisplayElement <em>Display Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.DisplayElement
	 * @generated
	 */
	public Adapter createDisplayElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.DynamicList <em>Dynamic List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.DynamicList
	 * @generated
	 */
	public Adapter createDynamicListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.InputContext <em>Input Context</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.InputContext
	 * @generated
	 */
	public Adapter createInputContextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Datepicker <em>Datepicker</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Datepicker
	 * @generated
	 */
	public Adapter createDatepickerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Enableable <em>Enableable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Enableable
	 * @generated
	 */
	public Adapter createEnableableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.TextContaining <em>Text Containing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.TextContaining
	 * @generated
	 */
	public Adapter createTextContainingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Image
	 * @generated
	 */
	public Adapter createImageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.LocationPicker <em>Location Picker</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.LocationPicker
	 * @generated
	 */
	public Adapter createLocationPickerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.CompositeDisplayElementType <em>Composite Display Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.CompositeDisplayElementType
	 * @generated
	 */
	public Adapter createCompositeDisplayElementTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.CompositeDisplayElement <em>Composite Display Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.CompositeDisplayElement
	 * @generated
	 */
	public Adapter createCompositeDisplayElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.CheckBox <em>Check Box</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.CheckBox
	 * @generated
	 */
	public Adapter createCheckBoxAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.ImageButton <em>Image Button</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.ImageButton
	 * @generated
	 */
	public Adapter createImageButtonAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.MenuBar <em>Menu Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.MenuBar
	 * @generated
	 */
	public Adapter createMenuBarAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.FragmentNavigation <em>Fragment Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.FragmentNavigation
	 * @generated
	 */
	public Adapter createFragmentNavigationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.ui.Picker <em>Picker</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.ui.Picker
	 * @generated
	 */
	public Adapter createPickerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.Entity
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.FeaturedElement <em>Featured Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.FeaturedElement
	 * @generated
	 */
	public Adapter createFeaturedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //UIAdapterFactory
