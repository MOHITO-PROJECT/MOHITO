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
 * @see de.modagile.metamodel.app.domain.DomainPackage
 * @generated
 */
public class DomainSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DomainPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainSwitch() {
		if (modelPackage == null) {
			modelPackage = DomainPackage.eINSTANCE;
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
			case DomainPackage.DOMAIN_BINDING: {
				DomainBinding domainBinding = (DomainBinding)theEObject;
				T result = caseDomainBinding(domainBinding);
				if (result == null) result = caseFeaturedElement(domainBinding);
				if (result == null) result = caseEntity(domainBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DomainPackage.COMPLEX_BINDING: {
				ComplexBinding complexBinding = (ComplexBinding)theEObject;
				T result = caseComplexBinding(complexBinding);
				if (result == null) result = caseDomainBinding(complexBinding);
				if (result == null) result = caseFeaturedElement(complexBinding);
				if (result == null) result = caseEntity(complexBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DomainPackage.BINDING_REPOSITORY: {
				BindingRepository bindingRepository = (BindingRepository)theEObject;
				T result = caseBindingRepository(bindingRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DomainPackage.STRING_BINDING: {
				StringBinding stringBinding = (StringBinding)theEObject;
				T result = caseStringBinding(stringBinding);
				if (result == null) result = casePrimitiveBinding(stringBinding);
				if (result == null) result = caseDomainBinding(stringBinding);
				if (result == null) result = caseFeaturedElement(stringBinding);
				if (result == null) result = caseEntity(stringBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DomainPackage.BOOLEAN_BINDING: {
				BooleanBinding booleanBinding = (BooleanBinding)theEObject;
				T result = caseBooleanBinding(booleanBinding);
				if (result == null) result = casePrimitiveBinding(booleanBinding);
				if (result == null) result = caseDomainBinding(booleanBinding);
				if (result == null) result = caseFeaturedElement(booleanBinding);
				if (result == null) result = caseEntity(booleanBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DomainPackage.PRIMITIVE_BINDING: {
				PrimitiveBinding primitiveBinding = (PrimitiveBinding)theEObject;
				T result = casePrimitiveBinding(primitiveBinding);
				if (result == null) result = caseDomainBinding(primitiveBinding);
				if (result == null) result = caseFeaturedElement(primitiveBinding);
				if (result == null) result = caseEntity(primitiveBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DomainPackage.LIST_BINDING: {
				ListBinding listBinding = (ListBinding)theEObject;
				T result = caseListBinding(listBinding);
				if (result == null) result = caseDomainBinding(listBinding);
				if (result == null) result = caseFeaturedElement(listBinding);
				if (result == null) result = caseEntity(listBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DomainPackage.SELECTION_BINDING: {
				SelectionBinding selectionBinding = (SelectionBinding)theEObject;
				T result = caseSelectionBinding(selectionBinding);
				if (result == null) result = caseDomainBinding(selectionBinding);
				if (result == null) result = caseFeaturedElement(selectionBinding);
				if (result == null) result = caseEntity(selectionBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDomainBinding(DomainBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Complex Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Complex Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComplexBinding(ComplexBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingRepository(BindingRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringBinding(StringBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanBinding(BooleanBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveBinding(PrimitiveBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListBinding(ListBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Selection Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Selection Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSelectionBinding(SelectionBinding object) {
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

} //DomainSwitch
