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

import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.DisplayElement;
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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Screen</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.ScreenImpl#getDisplayElements <em>Display Elements</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.ScreenImpl#getStoryBoard <em>Story Board</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.ScreenImpl#getMenuBar <em>Menu Bar</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.ScreenImpl#getFragmentNavigations <em>Fragment Navigations</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.impl.ScreenImpl#getStartFragment <em>Start Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScreenImpl extends FeaturedElementImpl implements Screen {
	/**
	 * The cached value of the '{@link #getDisplayElements() <em>Display Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayElements()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayElement> displayElements;

	/**
	 * The cached value of the '{@link #getMenuBar() <em>Menu Bar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMenuBar()
	 * @generated
	 * @ordered
	 */
	protected MenuBar menuBar;

	/**
	 * The cached value of the '{@link #getFragmentNavigations() <em>Fragment Navigations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFragmentNavigations()
	 * @generated
	 * @ordered
	 */
	protected EList<FragmentNavigation> fragmentNavigations;

	/**
	 * The cached value of the '{@link #getStartFragment() <em>Start Fragment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartFragment()
	 * @generated
	 * @ordered
	 */
	protected CompositeDisplayElement startFragment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScreenImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIPackage.Literals.SCREEN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DisplayElement> getDisplayElements() {
		if (displayElements == null) {
			displayElements = new EObjectContainmentWithInverseEList<DisplayElement>(DisplayElement.class, this, UIPackage.SCREEN__DISPLAY_ELEMENTS, UIPackage.DISPLAY_ELEMENT__SCREEN);
		}
		return displayElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoryBoard getStoryBoard() {
		if (eContainerFeatureID() != UIPackage.SCREEN__STORY_BOARD) return null;
		return (StoryBoard)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStoryBoard(StoryBoard newStoryBoard, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStoryBoard, UIPackage.SCREEN__STORY_BOARD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStoryBoard(StoryBoard newStoryBoard) {
		if (newStoryBoard != eInternalContainer() || (eContainerFeatureID() != UIPackage.SCREEN__STORY_BOARD && newStoryBoard != null)) {
			if (EcoreUtil.isAncestor(this, newStoryBoard))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStoryBoard != null)
				msgs = ((InternalEObject)newStoryBoard).eInverseAdd(this, UIPackage.STORY_BOARD__SCREENS, StoryBoard.class, msgs);
			msgs = basicSetStoryBoard(newStoryBoard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.SCREEN__STORY_BOARD, newStoryBoard, newStoryBoard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMenuBar(MenuBar newMenuBar, NotificationChain msgs) {
		MenuBar oldMenuBar = menuBar;
		menuBar = newMenuBar;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UIPackage.SCREEN__MENU_BAR, oldMenuBar, newMenuBar);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMenuBar(MenuBar newMenuBar) {
		if (newMenuBar != menuBar) {
			NotificationChain msgs = null;
			if (menuBar != null)
				msgs = ((InternalEObject)menuBar).eInverseRemove(this, UIPackage.MENU_BAR__SCREEN, MenuBar.class, msgs);
			if (newMenuBar != null)
				msgs = ((InternalEObject)newMenuBar).eInverseAdd(this, UIPackage.MENU_BAR__SCREEN, MenuBar.class, msgs);
			msgs = basicSetMenuBar(newMenuBar, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.SCREEN__MENU_BAR, newMenuBar, newMenuBar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FragmentNavigation> getFragmentNavigations() {
		if (fragmentNavigations == null) {
			fragmentNavigations = new EObjectContainmentEList<FragmentNavigation>(FragmentNavigation.class, this, UIPackage.SCREEN__FRAGMENT_NAVIGATIONS);
		}
		return fragmentNavigations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElement getStartFragment() {
		if (startFragment != null && startFragment.eIsProxy()) {
			InternalEObject oldStartFragment = (InternalEObject)startFragment;
			startFragment = (CompositeDisplayElement)eResolveProxy(oldStartFragment);
			if (startFragment != oldStartFragment) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIPackage.SCREEN__START_FRAGMENT, oldStartFragment, startFragment));
			}
		}
		return startFragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElement basicGetStartFragment() {
		return startFragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartFragment(CompositeDisplayElement newStartFragment) {
		CompositeDisplayElement oldStartFragment = startFragment;
		startFragment = newStartFragment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIPackage.SCREEN__START_FRAGMENT, oldStartFragment, startFragment));
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
			case UIPackage.SCREEN__DISPLAY_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDisplayElements()).basicAdd(otherEnd, msgs);
			case UIPackage.SCREEN__STORY_BOARD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStoryBoard((StoryBoard)otherEnd, msgs);
			case UIPackage.SCREEN__MENU_BAR:
				if (menuBar != null)
					msgs = ((InternalEObject)menuBar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UIPackage.SCREEN__MENU_BAR, null, msgs);
				return basicSetMenuBar((MenuBar)otherEnd, msgs);
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
			case UIPackage.SCREEN__DISPLAY_ELEMENTS:
				return ((InternalEList<?>)getDisplayElements()).basicRemove(otherEnd, msgs);
			case UIPackage.SCREEN__STORY_BOARD:
				return basicSetStoryBoard(null, msgs);
			case UIPackage.SCREEN__MENU_BAR:
				return basicSetMenuBar(null, msgs);
			case UIPackage.SCREEN__FRAGMENT_NAVIGATIONS:
				return ((InternalEList<?>)getFragmentNavigations()).basicRemove(otherEnd, msgs);
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
			case UIPackage.SCREEN__STORY_BOARD:
				return eInternalContainer().eInverseRemove(this, UIPackage.STORY_BOARD__SCREENS, StoryBoard.class, msgs);
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
			case UIPackage.SCREEN__DISPLAY_ELEMENTS:
				return getDisplayElements();
			case UIPackage.SCREEN__STORY_BOARD:
				return getStoryBoard();
			case UIPackage.SCREEN__MENU_BAR:
				return getMenuBar();
			case UIPackage.SCREEN__FRAGMENT_NAVIGATIONS:
				return getFragmentNavigations();
			case UIPackage.SCREEN__START_FRAGMENT:
				if (resolve) return getStartFragment();
				return basicGetStartFragment();
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
			case UIPackage.SCREEN__DISPLAY_ELEMENTS:
				getDisplayElements().clear();
				getDisplayElements().addAll((Collection<? extends DisplayElement>)newValue);
				return;
			case UIPackage.SCREEN__STORY_BOARD:
				setStoryBoard((StoryBoard)newValue);
				return;
			case UIPackage.SCREEN__MENU_BAR:
				setMenuBar((MenuBar)newValue);
				return;
			case UIPackage.SCREEN__FRAGMENT_NAVIGATIONS:
				getFragmentNavigations().clear();
				getFragmentNavigations().addAll((Collection<? extends FragmentNavigation>)newValue);
				return;
			case UIPackage.SCREEN__START_FRAGMENT:
				setStartFragment((CompositeDisplayElement)newValue);
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
			case UIPackage.SCREEN__DISPLAY_ELEMENTS:
				getDisplayElements().clear();
				return;
			case UIPackage.SCREEN__STORY_BOARD:
				setStoryBoard((StoryBoard)null);
				return;
			case UIPackage.SCREEN__MENU_BAR:
				setMenuBar((MenuBar)null);
				return;
			case UIPackage.SCREEN__FRAGMENT_NAVIGATIONS:
				getFragmentNavigations().clear();
				return;
			case UIPackage.SCREEN__START_FRAGMENT:
				setStartFragment((CompositeDisplayElement)null);
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
			case UIPackage.SCREEN__DISPLAY_ELEMENTS:
				return displayElements != null && !displayElements.isEmpty();
			case UIPackage.SCREEN__STORY_BOARD:
				return getStoryBoard() != null;
			case UIPackage.SCREEN__MENU_BAR:
				return menuBar != null;
			case UIPackage.SCREEN__FRAGMENT_NAVIGATIONS:
				return fragmentNavigations != null && !fragmentNavigations.isEmpty();
			case UIPackage.SCREEN__START_FRAGMENT:
				return startFragment != null;
		}
		return super.eIsSet(featureID);
	}

} //ScreenImpl
