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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Story Board</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.StoryBoard#getFlows <em>Flows</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.StoryBoard#getScreens <em>Screens</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.StoryBoard#getStartScreen <em>Start Screen</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.StoryBoard#getResolutionX <em>Resolution X</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.StoryBoard#getResolutionY <em>Resolution Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getStoryBoard()
 * @model
 * @generated
 */
public interface StoryBoard extends FeaturedElement {
	/**
	 * Returns the value of the '<em><b>Flows</b></em>' containment reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.ui.Flow}.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.Flow#getStoryBoard <em>Story Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flows</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flows</em>' containment reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getStoryBoard_Flows()
	 * @see de.modagile.metamodel.app.ui.Flow#getStoryBoard
	 * @model opposite="storyBoard" containment="true"
	 * @generated
	 */
	EList<Flow> getFlows();

	/**
	 * Returns the value of the '<em><b>Screens</b></em>' containment reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.ui.Screen}.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.Screen#getStoryBoard <em>Story Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Screens</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Screens</em>' containment reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getStoryBoard_Screens()
	 * @see de.modagile.metamodel.app.ui.Screen#getStoryBoard
	 * @model opposite="storyBoard" containment="true"
	 * @generated
	 */
	EList<Screen> getScreens();

	/**
	 * Returns the value of the '<em><b>Start Screen</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Screen</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Screen</em>' reference.
	 * @see #setStartScreen(Screen)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getStoryBoard_StartScreen()
	 * @model required="true"
	 * @generated
	 */
	Screen getStartScreen();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.StoryBoard#getStartScreen <em>Start Screen</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Screen</em>' reference.
	 * @see #getStartScreen()
	 * @generated
	 */
	void setStartScreen(Screen value);

	/**
	 * Returns the value of the '<em><b>Resolution X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolution X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolution X</em>' attribute.
	 * @see #setResolutionX(int)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getStoryBoard_ResolutionX()
	 * @model
	 * @generated
	 */
	int getResolutionX();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.StoryBoard#getResolutionX <em>Resolution X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolution X</em>' attribute.
	 * @see #getResolutionX()
	 * @generated
	 */
	void setResolutionX(int value);

	/**
	 * Returns the value of the '<em><b>Resolution Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resolution Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resolution Y</em>' attribute.
	 * @see #setResolutionY(int)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getStoryBoard_ResolutionY()
	 * @model
	 * @generated
	 */
	int getResolutionY();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.StoryBoard#getResolutionY <em>Resolution Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resolution Y</em>' attribute.
	 * @see #getResolutionY()
	 * @generated
	 */
	void setResolutionY(int value);

} // StoryBoard
