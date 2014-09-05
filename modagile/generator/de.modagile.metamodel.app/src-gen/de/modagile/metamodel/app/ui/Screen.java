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
package de.modagile.metamodel.app.ui;

import de.modagile.metamodel.app.FeaturedElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Screen</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.Screen#getDisplayElements <em>Display Elements</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Screen#getStoryBoard <em>Story Board</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Screen#getMenuBar <em>Menu Bar</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Screen#getFragmentNavigations <em>Fragment Navigations</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Screen#getStartFragment <em>Start Fragment</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getScreen()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='onlyAlreadContainedStartFragmentAllowed'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot onlyAlreadContainedStartFragmentAllowed='startFragment = null or displayElements->includes(startFragment)'"
 * @generated
 */
public interface Screen extends FeaturedElement {
	/**
	 * Returns the value of the '<em><b>Display Elements</b></em>' containment reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.ui.DisplayElement}.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.DisplayElement#getScreen <em>Screen</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Elements</em>' containment reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getScreen_DisplayElements()
	 * @see de.modagile.metamodel.app.ui.DisplayElement#getScreen
	 * @model opposite="screen" containment="true"
	 * @generated
	 */
	EList<DisplayElement> getDisplayElements();

	/**
	 * Returns the value of the '<em><b>Story Board</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.StoryBoard#getScreens <em>Screens</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Story Board</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Story Board</em>' container reference.
	 * @see #setStoryBoard(StoryBoard)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getScreen_StoryBoard()
	 * @see de.modagile.metamodel.app.ui.StoryBoard#getScreens
	 * @model opposite="screens" required="true" transient="false"
	 * @generated
	 */
	StoryBoard getStoryBoard();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Screen#getStoryBoard <em>Story Board</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Story Board</em>' container reference.
	 * @see #getStoryBoard()
	 * @generated
	 */
	void setStoryBoard(StoryBoard value);

	/**
	 * Returns the value of the '<em><b>Menu Bar</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.MenuBar#getScreen <em>Screen</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menu Bar</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menu Bar</em>' containment reference.
	 * @see #setMenuBar(MenuBar)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getScreen_MenuBar()
	 * @see de.modagile.metamodel.app.ui.MenuBar#getScreen
	 * @model opposite="screen" containment="true"
	 * @generated
	 */
	MenuBar getMenuBar();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Screen#getMenuBar <em>Menu Bar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Menu Bar</em>' containment reference.
	 * @see #getMenuBar()
	 * @generated
	 */
	void setMenuBar(MenuBar value);

	/**
	 * Returns the value of the '<em><b>Fragment Navigations</b></em>' containment reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.ui.FragmentNavigation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragment Navigations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fragment Navigations</em>' containment reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getScreen_FragmentNavigations()
	 * @model containment="true"
	 * @generated
	 */
	EList<FragmentNavigation> getFragmentNavigations();

	/**
	 * Returns the value of the '<em><b>Start Fragment</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Fragment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Fragment</em>' reference.
	 * @see #setStartFragment(CompositeDisplayElement)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getScreen_StartFragment()
	 * @model
	 * @generated
	 */
	CompositeDisplayElement getStartFragment();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Screen#getStartFragment <em>Start Fragment</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Fragment</em>' reference.
	 * @see #getStartFragment()
	 * @generated
	 */
	void setStartFragment(CompositeDisplayElement value);

} // Screen
