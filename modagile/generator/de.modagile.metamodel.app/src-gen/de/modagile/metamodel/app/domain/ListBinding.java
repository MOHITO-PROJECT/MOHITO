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
package de.modagile.metamodel.app.domain;

import de.modagile.metamodel.app.ui.DynamicList;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.domain.ListBinding#getListElement <em>List Element</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.domain.ListBinding#getDomainReference <em>Domain Reference</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.domain.ListBinding#getInnerBindings <em>Inner Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.domain.DomainPackage#getListBinding()
 * @model
 * @generated
 */
public interface ListBinding extends DomainBinding {
	/**
	 * Returns the value of the '<em><b>List Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>List Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>List Element</em>' reference.
	 * @see #setListElement(DynamicList)
	 * @see de.modagile.metamodel.app.domain.DomainPackage#getListBinding_ListElement()
	 * @model required="true"
	 * @generated
	 */
	DynamicList getListElement();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.domain.ListBinding#getListElement <em>List Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>List Element</em>' reference.
	 * @see #getListElement()
	 * @generated
	 */
	void setListElement(DynamicList value);

	/**
	 * Returns the value of the '<em><b>Domain Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Reference</em>' reference.
	 * @see #setDomainReference(EReference)
	 * @see de.modagile.metamodel.app.domain.DomainPackage#getListBinding_DomainReference()
	 * @model required="true"
	 * @generated
	 */
	EReference getDomainReference();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.domain.ListBinding#getDomainReference <em>Domain Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Reference</em>' reference.
	 * @see #getDomainReference()
	 * @generated
	 */
	void setDomainReference(EReference value);

	/**
	 * Returns the value of the '<em><b>Inner Bindings</b></em>' reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.domain.DomainBinding}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Bindings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inner Bindings</em>' reference list.
	 * @see de.modagile.metamodel.app.domain.DomainPackage#getListBinding_InnerBindings()
	 * @model
	 * @generated
	 */
	EList<DomainBinding> getInnerBindings();

} // ListBinding
