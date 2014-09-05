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

import de.modagile.metamodel.app.impl.EntityImpl;

import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.UIPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Display Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DisplayElementImpl#getScreen <em>Screen</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DisplayElementImpl#getCompositeType <em>Composite Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DisplayElementImpl extends EntityImpl implements DisplayElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DisplayElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.DISPLAY_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen getScreen() {
		if (eContainerFeatureID() != UIPackage.DISPLAY_ELEMENT__SCREEN) return null;
		return (Screen)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScreen(Screen newScreen, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScreen, UIPackage.DISPLAY_ELEMENT__SCREEN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScreen(Screen newScreen) {
		if (newScreen != eInternalContainer() || (eContainerFeatureID() != UIPackage.DISPLAY_ELEMENT__SCREEN && newScreen != null)) {
			if (EcoreUtil.isAncestor(this, newScreen))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScreen != null)
				msgs = ((InternalEObject)newScreen).eInverseAdd(this, UIPackage.SCREEN__DISPLAY_ELEMENTS, Screen.class, msgs);
			msgs = basicSetScreen(newScreen, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.DISPLAY_ELEMENT__SCREEN, newScreen, newScreen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElementType getCompositeType() {
		if (eContainerFeatureID() != UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE) return null;
		return (CompositeDisplayElementType)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompositeType(CompositeDisplayElementType newCompositeType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCompositeType, UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompositeType(CompositeDisplayElementType newCompositeType) {
		if (newCompositeType != eInternalContainer() || (eContainerFeatureID() != UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE && newCompositeType != null)) {
			if (EcoreUtil.isAncestor(this, newCompositeType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCompositeType != null)
				msgs = ((InternalEObject)newCompositeType).eInverseAdd(this, UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS, CompositeDisplayElementType.class, msgs);
			msgs = basicSetCompositeType(newCompositeType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE, newCompositeType, newCompositeType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIPackage.DISPLAY_ELEMENT__SCREEN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScreen((Screen)otherEnd, msgs);
			case UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCompositeType((CompositeDisplayElementType)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIPackage.DISPLAY_ELEMENT__SCREEN:
				return basicSetScreen(null, msgs);
			case UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE:
				return basicSetCompositeType(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case UIPackage.DISPLAY_ELEMENT__SCREEN:
				return eInternalContainer().eInverseRemove(this, UIPackage.SCREEN__DISPLAY_ELEMENTS, Screen.class, msgs);
			case UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE:
				return eInternalContainer().eInverseRemove(this, UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS, CompositeDisplayElementType.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UIPackage.DISPLAY_ELEMENT__SCREEN:
				return getScreen();
			case UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE:
				return getCompositeType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UIPackage.DISPLAY_ELEMENT__SCREEN:
				setScreen((Screen)newValue);
				return;
			case UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE:
				setCompositeType((CompositeDisplayElementType)newValue);
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
			case UIPackage.DISPLAY_ELEMENT__SCREEN:
				setScreen((Screen)null);
				return;
			case UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE:
				setCompositeType((CompositeDisplayElementType)null);
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
			case UIPackage.DISPLAY_ELEMENT__SCREEN:
				return getScreen() != null;
			case UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE:
				return getCompositeType() != null;
		}
		return super.eIsSet(featureID);
	}

} //DisplayElementImpl
