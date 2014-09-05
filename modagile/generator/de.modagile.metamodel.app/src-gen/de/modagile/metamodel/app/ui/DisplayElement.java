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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Display Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.DisplayElement#getScreen <em>Screen</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.DisplayElement#getCompositeType <em>Composite Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getDisplayElement()
 * @model abstract="true"
 * @generated
 */
public interface DisplayElement extends Entity, Enableable {

	/**
	 * Returns the value of the '<em><b>Screen</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.Screen#getDisplayElements <em>Display Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Screen</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Screen</em>' container reference.
	 * @see #setScreen(Screen)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getDisplayElement_Screen()
	 * @see de.modagile.metamodel.app.ui.Screen#getDisplayElements
	 * @model opposite="displayElements" transient="false"
	 * @generated
	 */
	Screen getScreen();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.DisplayElement#getScreen <em>Screen</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Screen</em>' container reference.
	 * @see #getScreen()
	 * @generated
	 */
	void setScreen(Screen value);

	/**
	 * Returns the value of the '<em><b>Composite Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.CompositeDisplayElementType#getContainedDisplayElements <em>Contained Display Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Type</em>' container reference.
	 * @see #setCompositeType(CompositeDisplayElementType)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getDisplayElement_CompositeType()
	 * @see de.modagile.metamodel.app.ui.CompositeDisplayElementType#getContainedDisplayElements
	 * @model opposite="containedDisplayElements" transient="false"
	 * @generated
	 */
	CompositeDisplayElementType getCompositeType();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.DisplayElement#getCompositeType <em>Composite Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Composite Type</em>' container reference.
	 * @see #getCompositeType()
	 * @generated
	 */
	void setCompositeType(CompositeDisplayElementType value);
} // DisplayElement
