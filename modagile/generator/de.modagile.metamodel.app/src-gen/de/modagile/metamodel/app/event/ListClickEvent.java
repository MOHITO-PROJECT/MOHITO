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

import de.modagile.metamodel.app.ui.DynamicList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Click Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.event.ListClickEvent#getList <em>List</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.event.EventPackage#getListClickEvent()
 * @model
 * @generated
 */
public interface ListClickEvent extends Event {
	/**
	 * Returns the value of the '<em><b>List</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.DynamicList#getListClickEvents <em>List Click Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>List</em>' reference.
	 * @see #setList(DynamicList)
	 * @see de.modagile.metamodel.app.event.EventPackage#getListClickEvent_List()
	 * @see de.modagile.metamodel.app.ui.DynamicList#getListClickEvents
	 * @model opposite="listClickEvents" required="true"
	 * @generated
	 */
	DynamicList getList();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.event.ListClickEvent#getList <em>List</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List</em>' reference.
	 * @see #getList()
	 * @generated
	 */
	void setList(DynamicList value);

} // ListClickEvent
