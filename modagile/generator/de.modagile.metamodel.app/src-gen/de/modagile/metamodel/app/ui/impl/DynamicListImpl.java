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

import de.modagile.metamodel.app.event.EventPackage;
import de.modagile.metamodel.app.event.ListClickEvent;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.UIPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DynamicListImpl#getDisplayedElements <em>Displayed Elements</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.DynamicListImpl#getListClickEvents <em>List Click Events</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicListImpl extends DisplayElementImpl implements DynamicList {
	/**
	 * The cached value of the '{@link #getDisplayedElements() <em>Displayed Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<CompositeDisplayElementType> displayedElements;

	/**
	 * The cached value of the '{@link #getListClickEvents() <em>List Click Events</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getListClickEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<ListClickEvent> listClickEvents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.DYNAMIC_LIST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompositeDisplayElementType> getDisplayedElements() {
		if (displayedElements == null) {
			displayedElements = new EObjectResolvingEList<CompositeDisplayElementType>(CompositeDisplayElementType.class, this, UIPackage.DYNAMIC_LIST__DISPLAYED_ELEMENTS);
		}
		return displayedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ListClickEvent> getListClickEvents() {
		if (listClickEvents == null) {
			listClickEvents = new EObjectWithInverseResolvingEList<ListClickEvent>(ListClickEvent.class, this, UIPackage.DYNAMIC_LIST__LIST_CLICK_EVENTS, EventPackage.LIST_CLICK_EVENT__LIST);
		}
		return listClickEvents;
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
			case UIPackage.DYNAMIC_LIST__LIST_CLICK_EVENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getListClickEvents()).basicAdd(otherEnd, msgs);
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
			case UIPackage.DYNAMIC_LIST__LIST_CLICK_EVENTS:
				return ((InternalEList<?>)getListClickEvents()).basicRemove(otherEnd, msgs);
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
			case UIPackage.DYNAMIC_LIST__DISPLAYED_ELEMENTS:
				return getDisplayedElements();
			case UIPackage.DYNAMIC_LIST__LIST_CLICK_EVENTS:
				return getListClickEvents();
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
			case UIPackage.DYNAMIC_LIST__DISPLAYED_ELEMENTS:
				getDisplayedElements().clear();
				getDisplayedElements().addAll((Collection<? extends CompositeDisplayElementType>)newValue);
				return;
			case UIPackage.DYNAMIC_LIST__LIST_CLICK_EVENTS:
				getListClickEvents().clear();
				getListClickEvents().addAll((Collection<? extends ListClickEvent>)newValue);
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
			case UIPackage.DYNAMIC_LIST__DISPLAYED_ELEMENTS:
				getDisplayedElements().clear();
				return;
			case UIPackage.DYNAMIC_LIST__LIST_CLICK_EVENTS:
				getListClickEvents().clear();
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
			case UIPackage.DYNAMIC_LIST__DISPLAYED_ELEMENTS:
				return displayedElements != null && !displayedElements.isEmpty();
			case UIPackage.DYNAMIC_LIST__LIST_CLICK_EVENTS:
				return listClickEvents != null && !listClickEvents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DynamicListImpl
