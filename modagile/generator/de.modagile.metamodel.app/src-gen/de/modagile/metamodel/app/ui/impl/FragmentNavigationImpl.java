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

import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.DisplayType;
import de.modagile.metamodel.app.ui.FragmentNavigation;
import de.modagile.metamodel.app.ui.UIPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fragment Navigation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FragmentNavigationImpl#getFrom <em>From</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FragmentNavigationImpl#getTo <em>To</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FragmentNavigationImpl#getNavigationType <em>Navigation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FragmentNavigationImpl extends EObjectImpl implements FragmentNavigation {
	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected CompositeDisplayElement from;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected CompositeDisplayElement to;

	/**
	 * The default value of the '{@link #getNavigationType() <em>Navigation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationType()
	 * @generated
	 * @ordered
	 */
	protected static final DisplayType NAVIGATION_TYPE_EDEFAULT = DisplayType.SEPARATE;

	/**
	 * The cached value of the '{@link #getNavigationType() <em>Navigation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNavigationType()
	 * @generated
	 * @ordered
	 */
	protected DisplayType navigationType = NAVIGATION_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FragmentNavigationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.FRAGMENT_NAVIGATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElement getFrom() {
		if (from != null && from.eIsProxy()) {
			InternalEObject oldFrom = (InternalEObject)from;
			from = (CompositeDisplayElement)eResolveProxy(oldFrom);
			if (from != oldFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIPackage.FRAGMENT_NAVIGATION__FROM, oldFrom, from));
			}
		}
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElement basicGetFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrom(CompositeDisplayElement newFrom) {
		CompositeDisplayElement oldFrom = from;
		from = newFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.FRAGMENT_NAVIGATION__FROM, oldFrom, from));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElement getTo() {
		if (to != null && to.eIsProxy()) {
			InternalEObject oldTo = (InternalEObject)to;
			to = (CompositeDisplayElement)eResolveProxy(oldTo);
			if (to != oldTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIPackage.FRAGMENT_NAVIGATION__TO, oldTo, to));
			}
		}
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElement basicGetTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(CompositeDisplayElement newTo) {
		CompositeDisplayElement oldTo = to;
		to = newTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.FRAGMENT_NAVIGATION__TO, oldTo, to));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisplayType getNavigationType() {
		return navigationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNavigationType(DisplayType newNavigationType) {
		DisplayType oldNavigationType = navigationType;
		navigationType = newNavigationType == null ? NAVIGATION_TYPE_EDEFAULT : newNavigationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.FRAGMENT_NAVIGATION__NAVIGATION_TYPE, oldNavigationType, navigationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UIPackage.FRAGMENT_NAVIGATION__FROM:
				if (resolve) return getFrom();
				return basicGetFrom();
			case UIPackage.FRAGMENT_NAVIGATION__TO:
				if (resolve) return getTo();
				return basicGetTo();
			case UIPackage.FRAGMENT_NAVIGATION__NAVIGATION_TYPE:
				return getNavigationType();
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
			case UIPackage.FRAGMENT_NAVIGATION__FROM:
				setFrom((CompositeDisplayElement)newValue);
				return;
			case UIPackage.FRAGMENT_NAVIGATION__TO:
				setTo((CompositeDisplayElement)newValue);
				return;
			case UIPackage.FRAGMENT_NAVIGATION__NAVIGATION_TYPE:
				setNavigationType((DisplayType)newValue);
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
			case UIPackage.FRAGMENT_NAVIGATION__FROM:
				setFrom((CompositeDisplayElement)null);
				return;
			case UIPackage.FRAGMENT_NAVIGATION__TO:
				setTo((CompositeDisplayElement)null);
				return;
			case UIPackage.FRAGMENT_NAVIGATION__NAVIGATION_TYPE:
				setNavigationType(NAVIGATION_TYPE_EDEFAULT);
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
			case UIPackage.FRAGMENT_NAVIGATION__FROM:
				return from != null;
			case UIPackage.FRAGMENT_NAVIGATION__TO:
				return to != null;
			case UIPackage.FRAGMENT_NAVIGATION__NAVIGATION_TYPE:
				return navigationType != NAVIGATION_TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (navigationType: ");
		result.append(navigationType);
		result.append(')');
		return result.toString();
	}

} //FragmentNavigationImpl
