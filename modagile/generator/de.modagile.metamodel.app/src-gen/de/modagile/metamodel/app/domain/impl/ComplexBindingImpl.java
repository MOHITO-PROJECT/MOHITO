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
import de.modagile.metamodel.app.domain.ComplexBinding;
import de.modagile.metamodel.app.domain.DomainPackage;

import de.modagile.metamodel.app.domain.PrimitiveBinding;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import java.util.Collection;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complex Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.domain.impl.ComplexBindingImpl#getDomainEntity <em>Domain Entity</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.domain.impl.ComplexBindingImpl#getUiElement <em>Ui Element</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.domain.impl.ComplexBindingImpl#getInnerBindings <em>Inner Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComplexBindingImpl extends DomainBindingImpl implements ComplexBinding {
	/**
	 * The cached value of the '{@link #getDomainEntity() <em>Domain Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainEntity()
	 * @generated
	 * @ordered
	 */
	protected EClass domainEntity;

	/**
	 * The cached value of the '{@link #getUiElement() <em>Ui Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUiElement()
	 * @generated
	 * @ordered
	 */
	protected CompositeDisplayElementType uiElement;

	/**
	 * The cached value of the '{@link #getInnerBindings() <em>Inner Bindings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInnerBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<PrimitiveBinding> innerBindings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComplexBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DomainPackage.Literals.COMPLEX_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainEntity() {
		if (domainEntity != null && domainEntity.eIsProxy()) {
			InternalEObject oldDomainEntity = (InternalEObject)domainEntity;
			domainEntity = (EClass)eResolveProxy(oldDomainEntity);
			if (domainEntity != oldDomainEntity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.COMPLEX_BINDING__DOMAIN_ENTITY, oldDomainEntity, domainEntity));
			}
		}
		return domainEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass basicGetDomainEntity() {
		return domainEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	public void setDomainEntity(EClass newDomainEntity) {
		EClass oldDomainEntity = domainEntity;
		domainEntity = newDomainEntity;
		eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.ENTITY__NAME, "", name));
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.COMPLEX_BINDING__DOMAIN_ENTITY, oldDomainEntity, domainEntity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElementType getUiElement() {
		if (uiElement != null && uiElement.eIsProxy()) {
			InternalEObject oldUiElement = (InternalEObject)uiElement;
			uiElement = (CompositeDisplayElementType)eResolveProxy(oldUiElement);
			if (uiElement != oldUiElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DomainPackage.COMPLEX_BINDING__UI_ELEMENT, oldUiElement, uiElement));
			}
		}
		return uiElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElementType basicGetUiElement() {
		return uiElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated not
	 */
	public void setUiElement(CompositeDisplayElementType newUiElement) {
		CompositeDisplayElementType oldUiElement = uiElement;
		uiElement = newUiElement;
		eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.ENTITY__NAME, "", name));
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DomainPackage.COMPLEX_BINDING__UI_ELEMENT, oldUiElement, uiElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PrimitiveBinding> getInnerBindings() {
		if (innerBindings == null) {
			innerBindings = new EObjectResolvingEList<PrimitiveBinding>(PrimitiveBinding.class, this, DomainPackage.COMPLEX_BINDING__INNER_BINDINGS);
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
			case DomainPackage.COMPLEX_BINDING__DOMAIN_ENTITY:
				if (resolve) return getDomainEntity();
				return basicGetDomainEntity();
			case DomainPackage.COMPLEX_BINDING__UI_ELEMENT:
				if (resolve) return getUiElement();
				return basicGetUiElement();
			case DomainPackage.COMPLEX_BINDING__INNER_BINDINGS:
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
			case DomainPackage.COMPLEX_BINDING__DOMAIN_ENTITY:
				setDomainEntity((EClass)newValue);
				return;
			case DomainPackage.COMPLEX_BINDING__UI_ELEMENT:
				setUiElement((CompositeDisplayElementType)newValue);
				return;
			case DomainPackage.COMPLEX_BINDING__INNER_BINDINGS:
				getInnerBindings().clear();
				getInnerBindings().addAll((Collection<? extends PrimitiveBinding>)newValue);
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
			case DomainPackage.COMPLEX_BINDING__DOMAIN_ENTITY:
				setDomainEntity((EClass)null);
				return;
			case DomainPackage.COMPLEX_BINDING__UI_ELEMENT:
				setUiElement((CompositeDisplayElementType)null);
				return;
			case DomainPackage.COMPLEX_BINDING__INNER_BINDINGS:
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
			case DomainPackage.COMPLEX_BINDING__DOMAIN_ENTITY:
				return domainEntity != null;
			case DomainPackage.COMPLEX_BINDING__UI_ELEMENT:
				return uiElement != null;
			case DomainPackage.COMPLEX_BINDING__INNER_BINDINGS:
				return innerBindings != null && !innerBindings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ComplexBindingImpl
