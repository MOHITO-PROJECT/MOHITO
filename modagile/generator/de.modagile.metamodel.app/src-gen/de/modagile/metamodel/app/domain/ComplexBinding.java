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

import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import org.eclipse.emf.common.util.EList;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Complex Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.domain.ComplexBinding#getDomainEntity <em>Domain Entity</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.domain.ComplexBinding#getUiElement <em>Ui Element</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.domain.ComplexBinding#getInnerBindings <em>Inner Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.domain.DomainPackage#getComplexBinding()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes='self.innerBindings->forAll(binding : PrimitiveBinding | self.domainEntity.eAttributes->includes(binding.attribute))' ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings='self.innerBindings->forAll(binding : PrimitiveBinding | binding.oclIsKindOf(StringBinding) and self.uiElement.containedDisplayElements->includes(binding.oclAsType(StringBinding).uiElement) or binding.oclIsKindOf(BooleanBinding) and self.uiElement.containedDisplayElements->includes(binding.oclAsType(BooleanBinding).checkBox))'"
 * @generated
 */
public interface ComplexBinding extends DomainBinding {
	/**
	 * Returns the value of the '<em><b>Domain Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Entity</em>' reference.
	 * @see #setDomainEntity(EClass)
	 * @see de.modagile.metamodel.app.domain.DomainPackage#getComplexBinding_DomainEntity()
	 * @model required="true"
	 * @generated
	 */
	EClass getDomainEntity();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.domain.ComplexBinding#getDomainEntity <em>Domain Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Entity</em>' reference.
	 * @see #getDomainEntity()
	 * @generated
	 */
	void setDomainEntity(EClass value);

	/**
	 * Returns the value of the '<em><b>Ui Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ui Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ui Element</em>' reference.
	 * @see #setUiElement(CompositeDisplayElementType)
	 * @see de.modagile.metamodel.app.domain.DomainPackage#getComplexBinding_UiElement()
	 * @model required="true"
	 * @generated
	 */
	CompositeDisplayElementType getUiElement();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.domain.ComplexBinding#getUiElement <em>Ui Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ui Element</em>' reference.
	 * @see #getUiElement()
	 * @generated
	 */
	void setUiElement(CompositeDisplayElementType value);

	/**
	 * Returns the value of the '<em><b>Inner Bindings</b></em>' reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.domain.PrimitiveBinding}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inner Bindings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inner Bindings</em>' reference list.
	 * @see de.modagile.metamodel.app.domain.DomainPackage#getComplexBinding_InnerBindings()
	 * @model
	 * @generated
	 */
	EList<PrimitiveBinding> getInnerBindings();

} // ComplexBinding
