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

import de.modagile.metamodel.app.event.ButtonClickEvent;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Button</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.Button#getCaption <em>Caption</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Button#getButtonClickEvents <em>Button Click Events</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getButton()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='Button_Can_Only_Have_One_Event_If_In_Screen'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot Button_Can_Only_Have_One_Event_If_In_Screen='self.screen <> null and buttonClickEvents->size() <= 1 or self.screen = null'"
 * @generated
 */
public interface Button extends Control, TextContaining {
	/**
	 * Returns the value of the '<em><b>Caption</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caption</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caption</em>' attribute.
	 * @see #setCaption(String)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getButton_Caption()
	 * @model
	 * @generated
	 */
	String getCaption();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Button#getCaption <em>Caption</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caption</em>' attribute.
	 * @see #getCaption()
	 * @generated
	 */
	void setCaption(String value);

	/**
	 * Returns the value of the '<em><b>Button Click Events</b></em>' reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.event.ButtonClickEvent}.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.event.ButtonClickEvent#getButton <em>Button</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Button Click Events</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Button Click Events</em>' reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getButton_ButtonClickEvents()
	 * @see de.modagile.metamodel.app.event.ButtonClickEvent#getButton
	 * @model opposite="button"
	 * @generated
	 */
	EList<ButtonClickEvent> getButtonClickEvents();

} // Button
