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

import de.modagile.metamodel.app.event.Event;
import de.modagile.metamodel.app.event.EventPackage;

import de.modagile.metamodel.app.impl.EntityImpl;

import de.modagile.metamodel.app.ui.Flow;
import de.modagile.metamodel.app.ui.InputContext;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import de.modagile.metamodel.app.ui.UIPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FlowImpl#getFrom <em>From</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FlowImpl#getTo <em>To</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FlowImpl#getStoryBoard <em>Story Board</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FlowImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FlowImpl#getFlowContext <em>Flow Context</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.FlowImpl#isReturnsResult <em>Returns Result</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FlowImpl extends EntityImpl implements Flow {
	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected Screen from;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected Screen to;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> events;

	/**
	 * The cached value of the '{@link #getFlowContext() <em>Flow Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlowContext()
	 * @generated
	 * @ordered
	 */
	protected InputContext flowContext;

	/**
	 * The default value of the '{@link #isReturnsResult() <em>Returns Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnsResult()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RETURNS_RESULT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReturnsResult() <em>Returns Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnsResult()
	 * @generated
	 * @ordered
	 */
	protected boolean returnsResult = RETURNS_RESULT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen getFrom() {
		if (from != null && from.eIsProxy()) {
			InternalEObject oldFrom = (InternalEObject)from;
			from = (Screen)eResolveProxy(oldFrom);
			if (from != oldFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIPackage.FLOW__FROM, oldFrom, from));
			}
		}
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen basicGetFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrom(Screen newFrom) {
		Screen oldFrom = from;
		from = newFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.FLOW__FROM, oldFrom, from));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen getTo() {
		if (to != null && to.eIsProxy()) {
			InternalEObject oldTo = (InternalEObject)to;
			to = (Screen)eResolveProxy(oldTo);
			if (to != oldTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIPackage.FLOW__TO, oldTo, to));
			}
		}
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen basicGetTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(Screen newTo) {
		Screen oldTo = to;
		to = newTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.FLOW__TO, oldTo, to));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoryBoard getStoryBoard() {
		if (eContainerFeatureID() != UIPackage.FLOW__STORY_BOARD) return null;
		return (StoryBoard)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStoryBoard(StoryBoard newStoryBoard, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStoryBoard, UIPackage.FLOW__STORY_BOARD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStoryBoard(StoryBoard newStoryBoard) {
		if (newStoryBoard != eInternalContainer() || (eContainerFeatureID() != UIPackage.FLOW__STORY_BOARD && newStoryBoard != null)) {
			if (EcoreUtil.isAncestor(this, newStoryBoard))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStoryBoard != null)
				msgs = ((InternalEObject)newStoryBoard).eInverseAdd(this, UIPackage.STORY_BOARD__FLOWS, StoryBoard.class, msgs);
			msgs = basicSetStoryBoard(newStoryBoard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.FLOW__STORY_BOARD, newStoryBoard, newStoryBoard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getEvents() {
		if (events == null) {
			events = new EObjectWithInverseResolvingEList<Event>(Event.class, this, UIPackage.FLOW__EVENTS, EventPackage.EVENT__TRIGGERS);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputContext getFlowContext() {
		return flowContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFlowContext(InputContext newFlowContext, NotificationChain msgs) {
		InputContext oldFlowContext = flowContext;
		flowContext = newFlowContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIPackage.FLOW__FLOW_CONTEXT, oldFlowContext, newFlowContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFlowContext(InputContext newFlowContext) {
		if (newFlowContext != flowContext) {
			NotificationChain msgs = null;
			if (flowContext != null)
				msgs = ((InternalEObject)flowContext).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIPackage.FLOW__FLOW_CONTEXT, null, msgs);
			if (newFlowContext != null)
				msgs = ((InternalEObject)newFlowContext).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UIPackage.FLOW__FLOW_CONTEXT, null, msgs);
			msgs = basicSetFlowContext(newFlowContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.FLOW__FLOW_CONTEXT, newFlowContext, newFlowContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReturnsResult() {
		return returnsResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnsResult(boolean newReturnsResult) {
		boolean oldReturnsResult = returnsResult;
		returnsResult = newReturnsResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.FLOW__RETURNS_RESULT, oldReturnsResult, returnsResult));
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
			case UIPackage.FLOW__STORY_BOARD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStoryBoard((StoryBoard)otherEnd, msgs);
			case UIPackage.FLOW__EVENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEvents()).basicAdd(otherEnd, msgs);
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
			case UIPackage.FLOW__STORY_BOARD:
				return basicSetStoryBoard(null, msgs);
			case UIPackage.FLOW__EVENTS:
				return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
			case UIPackage.FLOW__FLOW_CONTEXT:
				return basicSetFlowContext(null, msgs);
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
			case UIPackage.FLOW__STORY_BOARD:
				return eInternalContainer().eInverseRemove(this, UIPackage.STORY_BOARD__FLOWS, StoryBoard.class, msgs);
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
			case UIPackage.FLOW__FROM:
				if (resolve) return getFrom();
				return basicGetFrom();
			case UIPackage.FLOW__TO:
				if (resolve) return getTo();
				return basicGetTo();
			case UIPackage.FLOW__STORY_BOARD:
				return getStoryBoard();
			case UIPackage.FLOW__EVENTS:
				return getEvents();
			case UIPackage.FLOW__FLOW_CONTEXT:
				return getFlowContext();
			case UIPackage.FLOW__RETURNS_RESULT:
				return isReturnsResult();
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
			case UIPackage.FLOW__FROM:
				setFrom((Screen)newValue);
				return;
			case UIPackage.FLOW__TO:
				setTo((Screen)newValue);
				return;
			case UIPackage.FLOW__STORY_BOARD:
				setStoryBoard((StoryBoard)newValue);
				return;
			case UIPackage.FLOW__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection<? extends Event>)newValue);
				return;
			case UIPackage.FLOW__FLOW_CONTEXT:
				setFlowContext((InputContext)newValue);
				return;
			case UIPackage.FLOW__RETURNS_RESULT:
				setReturnsResult((Boolean)newValue);
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
			case UIPackage.FLOW__FROM:
				setFrom((Screen)null);
				return;
			case UIPackage.FLOW__TO:
				setTo((Screen)null);
				return;
			case UIPackage.FLOW__STORY_BOARD:
				setStoryBoard((StoryBoard)null);
				return;
			case UIPackage.FLOW__EVENTS:
				getEvents().clear();
				return;
			case UIPackage.FLOW__FLOW_CONTEXT:
				setFlowContext((InputContext)null);
				return;
			case UIPackage.FLOW__RETURNS_RESULT:
				setReturnsResult(RETURNS_RESULT_EDEFAULT);
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
			case UIPackage.FLOW__FROM:
				return from != null;
			case UIPackage.FLOW__TO:
				return to != null;
			case UIPackage.FLOW__STORY_BOARD:
				return getStoryBoard() != null;
			case UIPackage.FLOW__EVENTS:
				return events != null && !events.isEmpty();
			case UIPackage.FLOW__FLOW_CONTEXT:
				return flowContext != null;
			case UIPackage.FLOW__RETURNS_RESULT:
				return returnsResult != RETURNS_RESULT_EDEFAULT;
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
		result.append(" (returnsResult: ");
		result.append(returnsResult);
		result.append(')');
		return result.toString();
	}

} //FlowImpl
