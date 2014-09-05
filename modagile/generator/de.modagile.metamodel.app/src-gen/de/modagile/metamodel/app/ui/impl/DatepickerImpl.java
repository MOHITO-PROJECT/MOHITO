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
import de.modagile.metamodel.app.Entity;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.Enableable;
import de.modagile.metamodel.app.ui.Picker;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.TextContaining;
import de.modagile.metamodel.app.ui.UIPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Datepicker</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DatepickerImpl#getText <em>Text</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DatepickerImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DatepickerImpl#getScreen <em>Screen</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DatepickerImpl#getCompositeType <em>Composite Type</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DatepickerImpl#getMaxDate <em>Max Date</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DatepickerImpl#getMinDate <em>Min Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DatepickerImpl extends EObjectImpl implements Datepicker {
	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected String text = TEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaxDate() <em>Max Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDate()
	 * @generated
	 * @ordered
	 */
	protected static final String MAX_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxDate() <em>Max Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxDate()
	 * @generated
	 * @ordered
	 */
	protected String maxDate = MAX_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinDate() <em>Min Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinDate()
	 * @generated
	 * @ordered
	 */
	protected static final String MIN_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMinDate() <em>Min Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinDate()
	 * @generated
	 * @ordered
	 */
	protected String minDate = MIN_DATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatepickerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.DATEPICKER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText() {
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.DATEPICKER__TEXT, oldText, text));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.DATEPICKER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen getScreen() {
		if (eContainerFeatureID() != UIPackage.DATEPICKER__SCREEN) return null;
		return (Screen)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScreen(Screen newScreen, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScreen, UIPackage.DATEPICKER__SCREEN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScreen(Screen newScreen) {
		if (newScreen != eInternalContainer() || (eContainerFeatureID() != UIPackage.DATEPICKER__SCREEN && newScreen != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.DATEPICKER__SCREEN, newScreen, newScreen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElementType getCompositeType() {
		if (eContainerFeatureID() != UIPackage.DATEPICKER__COMPOSITE_TYPE) return null;
		return (CompositeDisplayElementType)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompositeType(CompositeDisplayElementType newCompositeType, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCompositeType, UIPackage.DATEPICKER__COMPOSITE_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompositeType(CompositeDisplayElementType newCompositeType) {
		if (newCompositeType != eInternalContainer() || (eContainerFeatureID() != UIPackage.DATEPICKER__COMPOSITE_TYPE && newCompositeType != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.DATEPICKER__COMPOSITE_TYPE, newCompositeType, newCompositeType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMaxDate() {
		return maxDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaxDate(String newMaxDate) {
		String oldMaxDate = maxDate;
		maxDate = newMaxDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.DATEPICKER__MAX_DATE, oldMaxDate, maxDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMinDate() {
		return minDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinDate(String newMinDate) {
		String oldMinDate = minDate;
		minDate = newMinDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.DATEPICKER__MIN_DATE, oldMinDate, minDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIPackage.DATEPICKER__SCREEN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScreen((Screen)otherEnd, msgs);
			case UIPackage.DATEPICKER__COMPOSITE_TYPE:
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
			case UIPackage.DATEPICKER__SCREEN:
				return basicSetScreen(null, msgs);
			case UIPackage.DATEPICKER__COMPOSITE_TYPE:
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
			case UIPackage.DATEPICKER__SCREEN:
				return eInternalContainer().eInverseRemove(this, UIPackage.SCREEN__DISPLAY_ELEMENTS, Screen.class, msgs);
			case UIPackage.DATEPICKER__COMPOSITE_TYPE:
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
			case UIPackage.DATEPICKER__TEXT:
				return getText();
			case UIPackage.DATEPICKER__NAME:
				return getName();
			case UIPackage.DATEPICKER__SCREEN:
				return getScreen();
			case UIPackage.DATEPICKER__COMPOSITE_TYPE:
				return getCompositeType();
			case UIPackage.DATEPICKER__MAX_DATE:
				return getMaxDate();
			case UIPackage.DATEPICKER__MIN_DATE:
				return getMinDate();
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
			case UIPackage.DATEPICKER__TEXT:
				setText((String)newValue);
				return;
			case UIPackage.DATEPICKER__NAME:
				setName((String)newValue);
				return;
			case UIPackage.DATEPICKER__SCREEN:
				setScreen((Screen)newValue);
				return;
			case UIPackage.DATEPICKER__COMPOSITE_TYPE:
				setCompositeType((CompositeDisplayElementType)newValue);
				return;
			case UIPackage.DATEPICKER__MAX_DATE:
				setMaxDate((String)newValue);
				return;
			case UIPackage.DATEPICKER__MIN_DATE:
				setMinDate((String)newValue);
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
			case UIPackage.DATEPICKER__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case UIPackage.DATEPICKER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UIPackage.DATEPICKER__SCREEN:
				setScreen((Screen)null);
				return;
			case UIPackage.DATEPICKER__COMPOSITE_TYPE:
				setCompositeType((CompositeDisplayElementType)null);
				return;
			case UIPackage.DATEPICKER__MAX_DATE:
				setMaxDate(MAX_DATE_EDEFAULT);
				return;
			case UIPackage.DATEPICKER__MIN_DATE:
				setMinDate(MIN_DATE_EDEFAULT);
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
			case UIPackage.DATEPICKER__TEXT:
				return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
			case UIPackage.DATEPICKER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UIPackage.DATEPICKER__SCREEN:
				return getScreen() != null;
			case UIPackage.DATEPICKER__COMPOSITE_TYPE:
				return getCompositeType() != null;
			case UIPackage.DATEPICKER__MAX_DATE:
				return MAX_DATE_EDEFAULT == null ? maxDate != null : !MAX_DATE_EDEFAULT.equals(maxDate);
			case UIPackage.DATEPICKER__MIN_DATE:
				return MIN_DATE_EDEFAULT == null ? minDate != null : !MIN_DATE_EDEFAULT.equals(minDate);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Entity.class) {
			switch (derivedFeatureID) {
				case UIPackage.DATEPICKER__NAME: return AppPackage.ENTITY__NAME;
				default: return -1;
			}
		}
		if (baseClass == Enableable.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DisplayElement.class) {
			switch (derivedFeatureID) {
				case UIPackage.DATEPICKER__SCREEN: return UIPackage.DISPLAY_ELEMENT__SCREEN;
				case UIPackage.DATEPICKER__COMPOSITE_TYPE: return UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE;
				default: return -1;
			}
		}
		if (baseClass == Picker.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Entity.class) {
			switch (baseFeatureID) {
				case AppPackage.ENTITY__NAME: return UIPackage.DATEPICKER__NAME;
				default: return -1;
			}
		}
		if (baseClass == Enableable.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DisplayElement.class) {
			switch (baseFeatureID) {
				case UIPackage.DISPLAY_ELEMENT__SCREEN: return UIPackage.DATEPICKER__SCREEN;
				case UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE: return UIPackage.DATEPICKER__COMPOSITE_TYPE;
				default: return -1;
			}
		}
		if (baseClass == Picker.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (text: ");
		result.append(text);
		result.append(", name: ");
		result.append(name);
		result.append(", maxDate: ");
		result.append(maxDate);
		result.append(", minDate: ");
		result.append(minDate);
		result.append(')');
		return result.toString();
	}

} //DatepickerImpl
