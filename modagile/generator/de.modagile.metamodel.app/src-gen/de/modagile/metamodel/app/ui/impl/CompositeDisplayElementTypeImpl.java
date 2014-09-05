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

import de.modagile.metamodel.app.impl.FeaturedElementImpl;

import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.UIPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Display Element Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.CompositeDisplayElementTypeImpl#getContainedDisplayElements <em>Contained Display Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeDisplayElementTypeImpl extends FeaturedElementImpl implements CompositeDisplayElementType {
	/**
	 * The cached value of the '{@link #getContainedDisplayElements() <em>Contained Display Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedDisplayElements()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayElement> containedDisplayElements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeDisplayElementTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.COMPOSITE_DISPLAY_ELEMENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DisplayElement> getContainedDisplayElements() {
		if (containedDisplayElements == null) {
			containedDisplayElements = new EObjectContainmentWithInverseEList<DisplayElement>(DisplayElement.class, this, UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS, UIPackage.DISPLAY_ELEMENT__COMPOSITE_TYPE);
		}
		return containedDisplayElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContainedDisplayElements()).basicAdd(otherEnd, msgs);
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
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS:
				return ((InternalEList<?>)getContainedDisplayElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS:
				return getContainedDisplayElements();
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
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS:
				getContainedDisplayElements().clear();
				getContainedDisplayElements().addAll((Collection<? extends DisplayElement>)newValue);
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
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS:
				getContainedDisplayElements().clear();
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
			case UIPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE__CONTAINED_DISPLAY_ELEMENTS:
				return containedDisplayElements != null && !containedDisplayElements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CompositeDisplayElementTypeImpl
