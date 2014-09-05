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

import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.MenuBar;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.UIPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Menu Bar</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.MenuBarImpl#getMenuBarElements <em>Menu Bar Elements</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.MenuBarImpl#getScreen <em>Screen</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MenuBarImpl extends EObjectImpl implements MenuBar {
	/**
	 * The cached value of the '{@link #getMenuBarElements() <em>Menu Bar Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMenuBarElements()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayElement> menuBarElements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MenuBarImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.MENU_BAR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DisplayElement> getMenuBarElements() {
		if (menuBarElements == null) {
			menuBarElements = new EObjectContainmentEList<DisplayElement>(DisplayElement.class, this, UIPackage.MENU_BAR__MENU_BAR_ELEMENTS);
		}
		return menuBarElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen getScreen() {
		if (eContainerFeatureID() != UIPackage.MENU_BAR__SCREEN) return null;
		return (Screen)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScreen(Screen newScreen, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScreen, UIPackage.MENU_BAR__SCREEN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScreen(Screen newScreen) {
		if (newScreen != eInternalContainer() || (eContainerFeatureID() != UIPackage.MENU_BAR__SCREEN && newScreen != null)) {
			if (EcoreUtil.isAncestor(this, newScreen))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScreen != null)
				msgs = ((InternalEObject)newScreen).eInverseAdd(this, UIPackage.SCREEN__MENU_BAR, Screen.class, msgs);
			msgs = basicSetScreen(newScreen, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.MENU_BAR__SCREEN, newScreen, newScreen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIPackage.MENU_BAR__SCREEN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScreen((Screen)otherEnd, msgs);
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
			case UIPackage.MENU_BAR__MENU_BAR_ELEMENTS:
				return ((InternalEList<?>)getMenuBarElements()).basicRemove(otherEnd, msgs);
			case UIPackage.MENU_BAR__SCREEN:
				return basicSetScreen(null, msgs);
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
			case UIPackage.MENU_BAR__SCREEN:
				return eInternalContainer().eInverseRemove(this, UIPackage.SCREEN__MENU_BAR, Screen.class, msgs);
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
			case UIPackage.MENU_BAR__MENU_BAR_ELEMENTS:
				return getMenuBarElements();
			case UIPackage.MENU_BAR__SCREEN:
				return getScreen();
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
			case UIPackage.MENU_BAR__MENU_BAR_ELEMENTS:
				getMenuBarElements().clear();
				getMenuBarElements().addAll((Collection<? extends DisplayElement>)newValue);
				return;
			case UIPackage.MENU_BAR__SCREEN:
				setScreen((Screen)newValue);
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
			case UIPackage.MENU_BAR__MENU_BAR_ELEMENTS:
				getMenuBarElements().clear();
				return;
			case UIPackage.MENU_BAR__SCREEN:
				setScreen((Screen)null);
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
			case UIPackage.MENU_BAR__MENU_BAR_ELEMENTS:
				return menuBarElements != null && !menuBarElements.isEmpty();
			case UIPackage.MENU_BAR__SCREEN:
				return getScreen() != null;
		}
		return super.eIsSet(featureID);
	}

} //MenuBarImpl
