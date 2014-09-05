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
import de.modagile.metamodel.app.ui.Flow;
import de.modagile.metamodel.app.ui.FragmentNavigation;
import de.modagile.metamodel.app.ui.MenuBar;
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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Story Board</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.StoryBoardImpl#getFlows <em>Flows</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.StoryBoardImpl#getScreens <em>Screens</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.StoryBoardImpl#getStartScreen <em>Start Screen</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.StoryBoardImpl#getResolutionX <em>Resolution X</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.StoryBoardImpl#getResolutionY <em>Resolution Y</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StoryBoardImpl extends FeaturedElementImpl implements StoryBoard {
	/**
	 * The cached value of the '{@link #getFlows() <em>Flows</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlows()
	 * @generated
	 * @ordered
	 */
	protected EList<Flow> flows;

	/**
	 * The cached value of the '{@link #getScreens() <em>Screens</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScreens()
	 * @generated
	 * @ordered
	 */
	protected EList<Screen> screens;

	/**
	 * The cached value of the '{@link #getStartScreen() <em>Start Screen</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartScreen()
	 * @generated
	 * @ordered
	 */
	protected Screen startScreen;

	/**
	 * The default value of the '{@link #getResolutionX() <em>Resolution X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionX()
	 * @generated
	 * @ordered
	 */
	protected static final int RESOLUTION_X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getResolutionX() <em>Resolution X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionX()
	 * @generated
	 * @ordered
	 */
	protected int resolutionX = RESOLUTION_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getResolutionY() <em>Resolution Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionY()
	 * @generated
	 * @ordered
	 */
	protected static final int RESOLUTION_Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getResolutionY() <em>Resolution Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResolutionY()
	 * @generated
	 * @ordered
	 */
	protected int resolutionY = RESOLUTION_Y_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StoryBoardImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.STORY_BOARD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Flow> getFlows() {
		if (flows == null) {
			flows = new EObjectContainmentWithInverseEList<Flow>(Flow.class, this, UIPackage.STORY_BOARD__FLOWS, UIPackage.FLOW__STORY_BOARD);
		}
		return flows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Screen> getScreens() {
		if (screens == null) {
			screens = new EObjectContainmentWithInverseEList<Screen>(Screen.class, this, UIPackage.STORY_BOARD__SCREENS, UIPackage.SCREEN__STORY_BOARD);
		}
		return screens;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen getStartScreen() {
		if (startScreen != null && startScreen.eIsProxy()) {
			InternalEObject oldStartScreen = (InternalEObject)startScreen;
			startScreen = (Screen)eResolveProxy(oldStartScreen);
			if (startScreen != oldStartScreen) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIPackage.STORY_BOARD__START_SCREEN, oldStartScreen, startScreen));
			}
		}
		return startScreen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Screen basicGetStartScreen() {
		return startScreen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartScreen(Screen newStartScreen) {
		Screen oldStartScreen = startScreen;
		startScreen = newStartScreen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.STORY_BOARD__START_SCREEN, oldStartScreen, startScreen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getResolutionX() {
		return resolutionX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolutionX(int newResolutionX) {
		int oldResolutionX = resolutionX;
		resolutionX = newResolutionX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.STORY_BOARD__RESOLUTION_X, oldResolutionX, resolutionX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getResolutionY() {
		return resolutionY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResolutionY(int newResolutionY) {
		int oldResolutionY = resolutionY;
		resolutionY = newResolutionY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.STORY_BOARD__RESOLUTION_Y, oldResolutionY, resolutionY));
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
			case UIPackage.STORY_BOARD__FLOWS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFlows()).basicAdd(otherEnd, msgs);
			case UIPackage.STORY_BOARD__SCREENS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getScreens()).basicAdd(otherEnd, msgs);
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
			case UIPackage.STORY_BOARD__FLOWS:
				return ((InternalEList<?>)getFlows()).basicRemove(otherEnd, msgs);
			case UIPackage.STORY_BOARD__SCREENS:
				return ((InternalEList<?>)getScreens()).basicRemove(otherEnd, msgs);
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
			case UIPackage.STORY_BOARD__FLOWS:
				return getFlows();
			case UIPackage.STORY_BOARD__SCREENS:
				return getScreens();
			case UIPackage.STORY_BOARD__START_SCREEN:
				if (resolve) return getStartScreen();
				return basicGetStartScreen();
			case UIPackage.STORY_BOARD__RESOLUTION_X:
				return getResolutionX();
			case UIPackage.STORY_BOARD__RESOLUTION_Y:
				return getResolutionY();
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
			case UIPackage.STORY_BOARD__FLOWS:
				getFlows().clear();
				getFlows().addAll((Collection<? extends Flow>)newValue);
				return;
			case UIPackage.STORY_BOARD__SCREENS:
				getScreens().clear();
				getScreens().addAll((Collection<? extends Screen>)newValue);
				return;
			case UIPackage.STORY_BOARD__START_SCREEN:
				setStartScreen((Screen)newValue);
				return;
			case UIPackage.STORY_BOARD__RESOLUTION_X:
				setResolutionX((Integer)newValue);
				return;
			case UIPackage.STORY_BOARD__RESOLUTION_Y:
				setResolutionY((Integer)newValue);
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
			case UIPackage.STORY_BOARD__FLOWS:
				getFlows().clear();
				return;
			case UIPackage.STORY_BOARD__SCREENS:
				getScreens().clear();
				return;
			case UIPackage.STORY_BOARD__START_SCREEN:
				setStartScreen((Screen)null);
				return;
			case UIPackage.STORY_BOARD__RESOLUTION_X:
				setResolutionX(RESOLUTION_X_EDEFAULT);
				return;
			case UIPackage.STORY_BOARD__RESOLUTION_Y:
				setResolutionY(RESOLUTION_Y_EDEFAULT);
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
			case UIPackage.STORY_BOARD__FLOWS:
				return flows != null && !flows.isEmpty();
			case UIPackage.STORY_BOARD__SCREENS:
				return screens != null && !screens.isEmpty();
			case UIPackage.STORY_BOARD__START_SCREEN:
				return startScreen != null;
			case UIPackage.STORY_BOARD__RESOLUTION_X:
				return resolutionX != RESOLUTION_X_EDEFAULT;
			case UIPackage.STORY_BOARD__RESOLUTION_Y:
				return resolutionY != RESOLUTION_Y_EDEFAULT;
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
		result.append(" (resolutionX: ");
		result.append(resolutionX);
		result.append(", resolutionY: ");
		result.append(resolutionY);
		result.append(')');
		return result.toString();
	}

} //StoryBoardImpl
