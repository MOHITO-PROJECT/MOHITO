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
package de.modagile.metamodel.app.domain.impl;

import de.modagile.metamodel.app.AppPackage;
import de.modagile.metamodel.app.domain.DomainBinding;
import de.modagile.metamodel.app.domain.DomainPackage;
import de.modagile.metamodel.app.domain.ListBinding;

import de.modagile.metamodel.app.ui.DynamicList;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.domain.impl.ListBindingImpl#getListElement <em>List Element</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.domain.impl.ListBindingImpl#getDomainReference <em>Domain Reference</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.domain.impl.ListBindingImpl#getInnerBindings <em>Inner Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ListBindingImpl extends DomainBindingImpl implements ListBinding {
	/**
	 * The cached value of the '{@link #getListElement() <em>List Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListElement()
	 * @generated
	 * @ordered
	 */
	protected DynamicList listElement;

	/**
	 * The cached value of the '{@link #getDomainReference() <em>Domain Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainReference()
	 * @generated
	 * @ordered
	 */
	protected EReference domainReference;

	/**
	 * The cached value of the '{@link #getInnerBindings() <em>Inner Bindings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInnerBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<DomainBinding> innerBindings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DomainPackage.Literals.LIST_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicList getListElement() {
		if (listElement != null && listElement.eIsProxy()) {
			InternalEObject oldListElement = (InternalEObject)listElement;
			listElement = (DynamicList)eResolveProxy(oldListElement);
			if (listElement != oldListElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.LIST_BINDING__LIST_ELEMENT, oldListElement, listElement));
			}
		}
		return listElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicList basicGetListElement() {
		return listElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	public void setListElement(DynamicList newListElement) {
		DynamicList oldListElement = listElement;
		listElement = newListElement;
		eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.ENTITY__NAME, "", name));
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.LIST_BINDING__LIST_ELEMENT, oldListElement, listElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainReference() {
		if (domainReference != null && domainReference.eIsProxy()) {
			InternalEObject oldDomainReference = (InternalEObject)domainReference;
			domainReference = (EReference)eResolveProxy(oldDomainReference);
			if (domainReference != oldDomainReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.LIST_BINDING__DOMAIN_REFERENCE, oldDomainReference, domainReference));
			}
		}
		return domainReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference basicGetDomainReference() {
		return domainReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	public void setDomainReference(EReference newDomainReference) {
		EReference oldDomainReference = domainReference;
		domainReference = newDomainReference;
		eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.ENTITY__NAME, "", name));
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.LIST_BINDING__DOMAIN_REFERENCE, oldDomainReference, domainReference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DomainBinding> getInnerBindings() {
		if (innerBindings == null) {
			innerBindings = new EObjectResolvingEList<DomainBinding>(DomainBinding.class, this, DomainPackage.LIST_BINDING__INNER_BINDINGS);
		}
		return innerBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DomainPackage.LIST_BINDING__LIST_ELEMENT:
				if (resolve) return getListElement();
				return basicGetListElement();
			case DomainPackage.LIST_BINDING__DOMAIN_REFERENCE:
				if (resolve) return getDomainReference();
				return basicGetDomainReference();
			case DomainPackage.LIST_BINDING__INNER_BINDINGS:
				return getInnerBindings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DomainPackage.LIST_BINDING__LIST_ELEMENT:
				setListElement((DynamicList)newValue);
				return;
			case DomainPackage.LIST_BINDING__DOMAIN_REFERENCE:
				setDomainReference((EReference)newValue);
				return;
			case DomainPackage.LIST_BINDING__INNER_BINDINGS:
				getInnerBindings().clear();
				getInnerBindings().addAll((Collection<? extends DomainBinding>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DomainPackage.LIST_BINDING__LIST_ELEMENT:
				setListElement((DynamicList)null);
				return;
			case DomainPackage.LIST_BINDING__DOMAIN_REFERENCE:
				setDomainReference((EReference)null);
				return;
			case DomainPackage.LIST_BINDING__INNER_BINDINGS:
				getInnerBindings().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DomainPackage.LIST_BINDING__LIST_ELEMENT:
				return listElement != null;
			case DomainPackage.LIST_BINDING__DOMAIN_REFERENCE:
				return domainReference != null;
			case DomainPackage.LIST_BINDING__INNER_BINDINGS:
				return innerBindings != null && !innerBindings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ListBindingImpl
