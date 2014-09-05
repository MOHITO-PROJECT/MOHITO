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

import de.modagile.metamodel.app.Entity;

import de.modagile.metamodel.app.event.Event;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.Flow#getFrom <em>From</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Flow#getTo <em>To</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Flow#getStoryBoard <em>Story Board</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Flow#getEvents <em>Events</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Flow#getFlowContext <em>Flow Context</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Flow#isReturnsResult <em>Returns Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getFlow()
 * @model
 * @generated
 */
public interface Flow extends Entity {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(Screen)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFlow_From()
	 * @model required="true"
	 * @generated
	 */
	Screen getFrom();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Flow#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(Screen value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(Screen)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFlow_To()
	 * @model required="true"
	 * @generated
	 */
	Screen getTo();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Flow#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(Screen value);

	/**
	 * Returns the value of the '<em><b>Story Board</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.StoryBoard#getFlows <em>Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Story Board</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Story Board</em>' container reference.
	 * @see #setStoryBoard(StoryBoard)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFlow_StoryBoard()
	 * @see de.modagile.metamodel.app.ui.StoryBoard#getFlows
	 * @model opposite="flows" required="true" transient="false"
	 * @generated
	 */
	StoryBoard getStoryBoard();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Flow#getStoryBoard <em>Story Board</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Story Board</em>' container reference.
	 * @see #getStoryBoard()
	 * @generated
	 */
	void setStoryBoard(StoryBoard value);

	/**
	 * Returns the value of the '<em><b>Events</b></em>' reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.event.Event}.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.event.Event#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Events</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFlow_Events()
	 * @see de.modagile.metamodel.app.event.Event#getTriggers
	 * @model opposite="triggers"
	 * @generated
	 */
	EList<Event> getEvents();

	/**
	 * Returns the value of the '<em><b>Flow Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Context</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Context</em>' containment reference.
	 * @see #setFlowContext(InputContext)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFlow_FlowContext()
	 * @model containment="true"
	 * @generated
	 */
	InputContext getFlowContext();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Flow#getFlowContext <em>Flow Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flow Context</em>' containment reference.
	 * @see #getFlowContext()
	 * @generated
	 */
	void setFlowContext(InputContext value);

	/**
	 * Returns the value of the '<em><b>Returns Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Returns Result</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Returns Result</em>' attribute.
	 * @see #setReturnsResult(boolean)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFlow_ReturnsResult()
	 * @model required="true"
	 * @generated
	 */
	boolean isReturnsResult();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Flow#isReturnsResult <em>Returns Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Returns Result</em>' attribute.
	 * @see #isReturnsResult()
	 * @generated
	 */
	void setReturnsResult(boolean value);

} // Flow
