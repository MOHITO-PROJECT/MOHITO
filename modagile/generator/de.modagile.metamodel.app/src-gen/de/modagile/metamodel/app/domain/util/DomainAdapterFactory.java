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

import de.modagile.metamodel.app.Entity;
import de.modagile.metamodel.app.FeaturedElement;

import de.modagile.metamodel.app.domain.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.modagile.metamodel.app.domain.DomainPackage
 * @generated
 */
public class DomainAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DomainPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DomainPackage.eINSTANCE;
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
	protected DomainSwitch<Adapter> modelSwitch =
		new DomainSwitch<Adapter>() {
			@Override
			public Adapter caseDomainBinding(DomainBinding object) {
				return createDomainBindingAdapter();
			}
			@Override
			public Adapter caseComplexBinding(ComplexBinding object) {
				return createComplexBindingAdapter();
			}
			@Override
			public Adapter caseBindingRepository(BindingRepository object) {
				return createBindingRepositoryAdapter();
			}
			@Override
			public Adapter caseStringBinding(StringBinding object) {
				return createStringBindingAdapter();
			}
			@Override
			public Adapter caseBooleanBinding(BooleanBinding object) {
				return createBooleanBindingAdapter();
			}
			@Override
			public Adapter casePrimitiveBinding(PrimitiveBinding object) {
				return createPrimitiveBindingAdapter();
			}
			@Override
			public Adapter caseListBinding(ListBinding object) {
				return createListBindingAdapter();
			}
			@Override
			public Adapter caseSelectionBinding(SelectionBinding object) {
				return createSelectionBindingAdapter();
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
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.domain.DomainBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.domain.DomainBinding
	 * @generated
	 */
	public Adapter createDomainBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.domain.ComplexBinding <em>Complex Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.domain.ComplexBinding
	 * @generated
	 */
	public Adapter createComplexBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.domain.BindingRepository <em>Binding Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.domain.BindingRepository
	 * @generated
	 */
	public Adapter createBindingRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.domain.StringBinding <em>String Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.domain.StringBinding
	 * @generated
	 */
	public Adapter createStringBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.domain.BooleanBinding <em>Boolean Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.domain.BooleanBinding
	 * @generated
	 */
	public Adapter createBooleanBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.domain.PrimitiveBinding <em>Primitive Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.domain.PrimitiveBinding
	 * @generated
	 */
	public Adapter createPrimitiveBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.domain.ListBinding <em>List Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.domain.ListBinding
	 * @generated
	 */
	public Adapter createListBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.modagile.metamodel.app.domain.SelectionBinding <em>Selection Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.modagile.metamodel.app.domain.SelectionBinding
	 * @generated
	 */
	public Adapter createSelectionBindingAdapter() {
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

} //DomainAdapterFactory
