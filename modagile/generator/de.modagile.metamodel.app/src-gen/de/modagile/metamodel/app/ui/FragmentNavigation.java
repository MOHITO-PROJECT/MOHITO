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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment Navigation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.FragmentNavigation#getFrom <em>From</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.FragmentNavigation#getTo <em>To</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.FragmentNavigation#getNavigationType <em>Navigation Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getFragmentNavigation()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='navigationWithinSameScreen'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot navigationWithinSameScreen='to.screen = from.screen'"
 * @generated
 */
public interface FragmentNavigation extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(CompositeDisplayElement)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFragmentNavigation_From()
	 * @model required="true"
	 * @generated
	 */
	CompositeDisplayElement getFrom();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.FragmentNavigation#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(CompositeDisplayElement value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(CompositeDisplayElement)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFragmentNavigation_To()
	 * @model required="true"
	 * @generated
	 */
	CompositeDisplayElement getTo();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.FragmentNavigation#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(CompositeDisplayElement value);

	/**
	 * Returns the value of the '<em><b>Navigation Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.modagile.metamodel.app.ui.DisplayType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigation Type</em>' attribute.
	 * @see de.modagile.metamodel.app.ui.DisplayType
	 * @see #setNavigationType(DisplayType)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getFragmentNavigation_NavigationType()
	 * @model
	 * @generated
	 */
	DisplayType getNavigationType();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.FragmentNavigation#getNavigationType <em>Navigation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation Type</em>' attribute.
	 * @see de.modagile.metamodel.app.ui.DisplayType
	 * @see #getNavigationType()
	 * @generated
	 */
	void setNavigationType(DisplayType value);

} // FragmentNavigation
