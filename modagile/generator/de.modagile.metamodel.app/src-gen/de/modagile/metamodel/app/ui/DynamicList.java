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

import de.modagile.metamodel.app.event.ListClickEvent;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.DynamicList#getDisplayedElements <em>Displayed Elements</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.DynamicList#getListClickEvents <em>List Click Events</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getDynamicList()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='List_Can_Only_Have_One_Event_If_In_Screen'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot List_Can_Only_Have_One_Event_If_In_Screen='self.screen <> null and listClickEvents->size() <= 1 or self.screen = null'"
 * @generated
 */
public interface DynamicList extends DisplayElement {
	/**
	 * Returns the value of the '<em><b>Displayed Elements</b></em>' reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.ui.CompositeDisplayElementType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Displayed Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Displayed Elements</em>' reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getDynamicList_DisplayedElements()
	 * @model
	 * @generated
	 */
	EList<CompositeDisplayElementType> getDisplayedElements();

	/**
	 * Returns the value of the '<em><b>List Click Events</b></em>' reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.event.ListClickEvent}.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.event.ListClickEvent#getList <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List Click Events</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>List Click Events</em>' reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getDynamicList_ListClickEvents()
	 * @see de.modagile.metamodel.app.event.ListClickEvent#getList
	 * @model opposite="list"
	 * @generated
	 */
	EList<ListClickEvent> getListClickEvents();

} // DynamicList
