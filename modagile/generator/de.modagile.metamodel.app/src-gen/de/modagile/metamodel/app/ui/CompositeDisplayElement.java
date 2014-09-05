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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Display Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.CompositeDisplayElement#getType <em>Type</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.CompositeDisplayElement#getDisplayType <em>Display Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getCompositeDisplayElement()
 * @model
 * @generated
 */
public interface CompositeDisplayElement extends DisplayElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(CompositeDisplayElementType)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getCompositeDisplayElement_Type()
	 * @model required="true"
	 * @generated
	 */
	CompositeDisplayElementType getType();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.CompositeDisplayElement#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(CompositeDisplayElementType value);

	/**
	 * Returns the value of the '<em><b>Display Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.modagile.metamodel.app.ui.DisplayType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Type</em>' attribute.
	 * @see de.modagile.metamodel.app.ui.DisplayType
	 * @see #setDisplayType(DisplayType)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getCompositeDisplayElement_DisplayType()
	 * @model
	 * @generated
	 */
	DisplayType getDisplayType();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.CompositeDisplayElement#getDisplayType <em>Display Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Type</em>' attribute.
	 * @see de.modagile.metamodel.app.ui.DisplayType
	 * @see #getDisplayType()
	 * @generated
	 */
	void setDisplayType(DisplayType value);

} // CompositeDisplayElement
