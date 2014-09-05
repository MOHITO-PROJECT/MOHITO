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
package de.modagile.metamodel.app.event;

import de.modagile.metamodel.app.AppPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.modagile.metamodel.app.event.EventFactory
 * @model kind="package"
 * @generated
 */
public interface EventPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "event";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modagile-mobile.de/metamodel#event";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "de.modagile.metamodel.app";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EventPackage eINSTANCE = de.modagile.metamodel.app.event.impl.EventPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.event.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.event.impl.EventImpl
	 * @see de.modagile.metamodel.app.event.impl.EventPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__NAME = AppPackage.ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__TRIGGERS = AppPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = AppPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.event.impl.ButtonClickEventImpl <em>Button Click Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.event.impl.ButtonClickEventImpl
	 * @see de.modagile.metamodel.app.event.impl.EventPackageImpl#getButtonClickEvent()
	 * @generated
	 */
	int BUTTON_CLICK_EVENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON_CLICK_EVENT__NAME = EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON_CLICK_EVENT__TRIGGERS = EVENT__TRIGGERS;

	/**
	 * The feature id for the '<em><b>Button</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON_CLICK_EVENT__BUTTON = EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Button Click Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUTTON_CLICK_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.event.impl.EventContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.event.impl.EventContainerImpl
	 * @see de.modagile.metamodel.app.event.impl.EventPackageImpl#getEventContainer()
	 * @generated
	 */
	int EVENT_CONTAINER = 2;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONTAINER__EVENTS = 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.event.impl.ListClickEventImpl <em>List Click Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.event.impl.ListClickEventImpl
	 * @see de.modagile.metamodel.app.event.impl.EventPackageImpl#getListClickEvent()
	 * @generated
	 */
	int LIST_CLICK_EVENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_CLICK_EVENT__NAME = EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_CLICK_EVENT__TRIGGERS = EVENT__TRIGGERS;

	/**
	 * The feature id for the '<em><b>List</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_CLICK_EVENT__LIST = EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>List Click Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_CLICK_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.event.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see de.modagile.metamodel.app.event.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.event.Event#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Triggers</em>'.
	 * @see de.modagile.metamodel.app.event.Event#getTriggers()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Triggers();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.event.ButtonClickEvent <em>Button Click Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Button Click Event</em>'.
	 * @see de.modagile.metamodel.app.event.ButtonClickEvent
	 * @generated
	 */
	EClass getButtonClickEvent();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.event.ButtonClickEvent#getButton <em>Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Button</em>'.
	 * @see de.modagile.metamodel.app.event.ButtonClickEvent#getButton()
	 * @see #getButtonClickEvent()
	 * @generated
	 */
	EReference getButtonClickEvent_Button();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.event.EventContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see de.modagile.metamodel.app.event.EventContainer
	 * @generated
	 */
	EClass getEventContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.event.EventContainer#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see de.modagile.metamodel.app.event.EventContainer#getEvents()
	 * @see #getEventContainer()
	 * @generated
	 */
	EReference getEventContainer_Events();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.event.ListClickEvent <em>List Click Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Click Event</em>'.
	 * @see de.modagile.metamodel.app.event.ListClickEvent
	 * @generated
	 */
	EClass getListClickEvent();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.event.ListClickEvent#getList <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>List</em>'.
	 * @see de.modagile.metamodel.app.event.ListClickEvent#getList()
	 * @see #getListClickEvent()
	 * @generated
	 */
	EReference getListClickEvent_List();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EventFactory getEventFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.event.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.event.impl.EventImpl
		 * @see de.modagile.metamodel.app.event.impl.EventPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__TRIGGERS = eINSTANCE.getEvent_Triggers();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.event.impl.ButtonClickEventImpl <em>Button Click Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.event.impl.ButtonClickEventImpl
		 * @see de.modagile.metamodel.app.event.impl.EventPackageImpl#getButtonClickEvent()
		 * @generated
		 */
		EClass BUTTON_CLICK_EVENT = eINSTANCE.getButtonClickEvent();

		/**
		 * The meta object literal for the '<em><b>Button</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUTTON_CLICK_EVENT__BUTTON = eINSTANCE.getButtonClickEvent_Button();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.event.impl.EventContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.event.impl.EventContainerImpl
		 * @see de.modagile.metamodel.app.event.impl.EventPackageImpl#getEventContainer()
		 * @generated
		 */
		EClass EVENT_CONTAINER = eINSTANCE.getEventContainer();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_CONTAINER__EVENTS = eINSTANCE.getEventContainer_Events();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.event.impl.ListClickEventImpl <em>List Click Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.event.impl.ListClickEventImpl
		 * @see de.modagile.metamodel.app.event.impl.EventPackageImpl#getListClickEvent()
		 * @generated
		 */
		EClass LIST_CLICK_EVENT = eINSTANCE.getListClickEvent();

		/**
		 * The meta object literal for the '<em><b>List</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_CLICK_EVENT__LIST = eINSTANCE.getListClickEvent_List();

	}

} //EventPackage
