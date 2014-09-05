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

import de.modagile.metamodel.app.ui.Button;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Button Click Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.event.ButtonClickEvent#getButton <em>Button</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.event.EventPackage#getButtonClickEvent()
 * @model
 * @generated
 */
public interface ButtonClickEvent extends Event {
	/**
	 * Returns the value of the '<em><b>Button</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.Button#getButtonClickEvents <em>Button Click Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Button</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Button</em>' reference.
	 * @see #setButton(Button)
	 * @see de.modagile.metamodel.app.event.EventPackage#getButtonClickEvent_Button()
	 * @see de.modagile.metamodel.app.ui.Button#getButtonClickEvents
	 * @model opposite="buttonClickEvents" required="true"
	 * @generated
	 */
	Button getButton();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.event.ButtonClickEvent#getButton <em>Button</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Button</em>' reference.
	 * @see #getButton()
	 * @generated
	 */
	void setButton(Button value);

} // ButtonClickEvent
