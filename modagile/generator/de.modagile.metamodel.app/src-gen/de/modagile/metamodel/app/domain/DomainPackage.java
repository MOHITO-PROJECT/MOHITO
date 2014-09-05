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

import de.modagile.metamodel.app.AppPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.modagile.metamodel.app.domain.DomainFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface DomainPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "domain";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modagile-mobile.de/metamodel#domain";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "de.modagile.metamodel.app";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DomainPackage eINSTANCE = de.modagile.metamodel.app.domain.impl.DomainPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.impl.DomainBindingImpl <em>Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.impl.DomainBindingImpl
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getDomainBinding()
	 * @generated
	 */
	int DOMAIN_BINDING = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_BINDING__NAME = AppPackage.FEATURED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Update Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_BINDING__UPDATE_STRATEGY = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_BINDING_FEATURE_COUNT = AppPackage.FEATURED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.impl.ComplexBindingImpl <em>Complex Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.impl.ComplexBindingImpl
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getComplexBinding()
	 * @generated
	 */
	int COMPLEX_BINDING = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLEX_BINDING__NAME = DOMAIN_BINDING__NAME;

	/**
	 * The feature id for the '<em><b>Update Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLEX_BINDING__UPDATE_STRATEGY = DOMAIN_BINDING__UPDATE_STRATEGY;

	/**
	 * The feature id for the '<em><b>Domain Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLEX_BINDING__DOMAIN_ENTITY = DOMAIN_BINDING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ui Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLEX_BINDING__UI_ELEMENT = DOMAIN_BINDING_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Inner Bindings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLEX_BINDING__INNER_BINDINGS = DOMAIN_BINDING_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Complex Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPLEX_BINDING_FEATURE_COUNT = DOMAIN_BINDING_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.impl.BindingRepositoryImpl <em>Binding Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.impl.BindingRepositoryImpl
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getBindingRepository()
	 * @generated
	 */
	int BINDING_REPOSITORY = 2;

	/**
	 * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_REPOSITORY__BINDINGS = 0;

	/**
	 * The number of structural features of the '<em>Binding Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINDING_REPOSITORY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.impl.PrimitiveBindingImpl <em>Primitive Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.impl.PrimitiveBindingImpl
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getPrimitiveBinding()
	 * @generated
	 */
	int PRIMITIVE_BINDING = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING__NAME = DOMAIN_BINDING__NAME;

	/**
	 * The feature id for the '<em><b>Update Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING__UPDATE_STRATEGY = DOMAIN_BINDING__UPDATE_STRATEGY;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING__ATTRIBUTE = DOMAIN_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_BINDING_FEATURE_COUNT = DOMAIN_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.impl.StringBindingImpl <em>String Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.impl.StringBindingImpl
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getStringBinding()
	 * @generated
	 */
	int STRING_BINDING = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_BINDING__NAME = PRIMITIVE_BINDING__NAME;

	/**
	 * The feature id for the '<em><b>Update Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_BINDING__UPDATE_STRATEGY = PRIMITIVE_BINDING__UPDATE_STRATEGY;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_BINDING__ATTRIBUTE = PRIMITIVE_BINDING__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Ui Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_BINDING__UI_ELEMENT = PRIMITIVE_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_BINDING_FEATURE_COUNT = PRIMITIVE_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.impl.BooleanBindingImpl <em>Boolean Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.impl.BooleanBindingImpl
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getBooleanBinding()
	 * @generated
	 */
	int BOOLEAN_BINDING = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINDING__NAME = PRIMITIVE_BINDING__NAME;

	/**
	 * The feature id for the '<em><b>Update Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINDING__UPDATE_STRATEGY = PRIMITIVE_BINDING__UPDATE_STRATEGY;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINDING__ATTRIBUTE = PRIMITIVE_BINDING__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Check Box</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINDING__CHECK_BOX = PRIMITIVE_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_BINDING_FEATURE_COUNT = PRIMITIVE_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.impl.ListBindingImpl <em>List Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.impl.ListBindingImpl
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getListBinding()
	 * @generated
	 */
	int LIST_BINDING = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_BINDING__NAME = DOMAIN_BINDING__NAME;

	/**
	 * The feature id for the '<em><b>Update Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_BINDING__UPDATE_STRATEGY = DOMAIN_BINDING__UPDATE_STRATEGY;

	/**
	 * The feature id for the '<em><b>List Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_BINDING__LIST_ELEMENT = DOMAIN_BINDING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Domain Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_BINDING__DOMAIN_REFERENCE = DOMAIN_BINDING_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Inner Bindings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_BINDING__INNER_BINDINGS = DOMAIN_BINDING_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>List Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_BINDING_FEATURE_COUNT = DOMAIN_BINDING_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.impl.SelectionBindingImpl <em>Selection Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.impl.SelectionBindingImpl
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getSelectionBinding()
	 * @generated
	 */
	int SELECTION_BINDING = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_BINDING__NAME = DOMAIN_BINDING__NAME;

	/**
	 * The feature id for the '<em><b>Update Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_BINDING__UPDATE_STRATEGY = DOMAIN_BINDING__UPDATE_STRATEGY;

	/**
	 * The feature id for the '<em><b>Domain Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_BINDING__DOMAIN_REFERENCE = DOMAIN_BINDING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Selection Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELECTION_BINDING_FEATURE_COUNT = DOMAIN_BINDING_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.domain.UpdateStrategy <em>Update Strategy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.domain.UpdateStrategy
	 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getUpdateStrategy()
	 * @generated
	 */
	int UPDATE_STRATEGY = 8;


	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.domain.DomainBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding</em>'.
	 * @see de.modagile.metamodel.app.domain.DomainBinding
	 * @generated
	 */
	EClass getDomainBinding();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.domain.DomainBinding#getUpdateStrategy <em>Update Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Update Strategy</em>'.
	 * @see de.modagile.metamodel.app.domain.DomainBinding#getUpdateStrategy()
	 * @see #getDomainBinding()
	 * @generated
	 */
	EAttribute getDomainBinding_UpdateStrategy();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.domain.ComplexBinding <em>Complex Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Complex Binding</em>'.
	 * @see de.modagile.metamodel.app.domain.ComplexBinding
	 * @generated
	 */
	EClass getComplexBinding();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.domain.ComplexBinding#getDomainEntity <em>Domain Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Domain Entity</em>'.
	 * @see de.modagile.metamodel.app.domain.ComplexBinding#getDomainEntity()
	 * @see #getComplexBinding()
	 * @generated
	 */
	EReference getComplexBinding_DomainEntity();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.domain.ComplexBinding#getUiElement <em>Ui Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ui Element</em>'.
	 * @see de.modagile.metamodel.app.domain.ComplexBinding#getUiElement()
	 * @see #getComplexBinding()
	 * @generated
	 */
	EReference getComplexBinding_UiElement();

	/**
	 * Returns the meta object for the reference list '{@link de.modagile.metamodel.app.domain.ComplexBinding#getInnerBindings <em>Inner Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inner Bindings</em>'.
	 * @see de.modagile.metamodel.app.domain.ComplexBinding#getInnerBindings()
	 * @see #getComplexBinding()
	 * @generated
	 */
	EReference getComplexBinding_InnerBindings();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.domain.BindingRepository <em>Binding Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Repository</em>'.
	 * @see de.modagile.metamodel.app.domain.BindingRepository
	 * @generated
	 */
	EClass getBindingRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.domain.BindingRepository#getBindings <em>Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bindings</em>'.
	 * @see de.modagile.metamodel.app.domain.BindingRepository#getBindings()
	 * @see #getBindingRepository()
	 * @generated
	 */
	EReference getBindingRepository_Bindings();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.domain.StringBinding <em>String Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Binding</em>'.
	 * @see de.modagile.metamodel.app.domain.StringBinding
	 * @generated
	 */
	EClass getStringBinding();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.domain.StringBinding#getUiElement <em>Ui Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ui Element</em>'.
	 * @see de.modagile.metamodel.app.domain.StringBinding#getUiElement()
	 * @see #getStringBinding()
	 * @generated
	 */
	EReference getStringBinding_UiElement();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.domain.BooleanBinding <em>Boolean Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Binding</em>'.
	 * @see de.modagile.metamodel.app.domain.BooleanBinding
	 * @generated
	 */
	EClass getBooleanBinding();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.domain.BooleanBinding#getCheckBox <em>Check Box</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Check Box</em>'.
	 * @see de.modagile.metamodel.app.domain.BooleanBinding#getCheckBox()
	 * @see #getBooleanBinding()
	 * @generated
	 */
	EReference getBooleanBinding_CheckBox();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.domain.PrimitiveBinding <em>Primitive Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Binding</em>'.
	 * @see de.modagile.metamodel.app.domain.PrimitiveBinding
	 * @generated
	 */
	EClass getPrimitiveBinding();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.domain.PrimitiveBinding#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see de.modagile.metamodel.app.domain.PrimitiveBinding#getAttribute()
	 * @see #getPrimitiveBinding()
	 * @generated
	 */
	EReference getPrimitiveBinding_Attribute();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.domain.ListBinding <em>List Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Binding</em>'.
	 * @see de.modagile.metamodel.app.domain.ListBinding
	 * @generated
	 */
	EClass getListBinding();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.domain.ListBinding#getListElement <em>List Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>List Element</em>'.
	 * @see de.modagile.metamodel.app.domain.ListBinding#getListElement()
	 * @see #getListBinding()
	 * @generated
	 */
	EReference getListBinding_ListElement();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.domain.ListBinding#getDomainReference <em>Domain Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Domain Reference</em>'.
	 * @see de.modagile.metamodel.app.domain.ListBinding#getDomainReference()
	 * @see #getListBinding()
	 * @generated
	 */
	EReference getListBinding_DomainReference();

	/**
	 * Returns the meta object for the reference list '{@link de.modagile.metamodel.app.domain.ListBinding#getInnerBindings <em>Inner Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inner Bindings</em>'.
	 * @see de.modagile.metamodel.app.domain.ListBinding#getInnerBindings()
	 * @see #getListBinding()
	 * @generated
	 */
	EReference getListBinding_InnerBindings();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.domain.SelectionBinding <em>Selection Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Selection Binding</em>'.
	 * @see de.modagile.metamodel.app.domain.SelectionBinding
	 * @generated
	 */
	EClass getSelectionBinding();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.domain.SelectionBinding#getDomainReference <em>Domain Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Domain Reference</em>'.
	 * @see de.modagile.metamodel.app.domain.SelectionBinding#getDomainReference()
	 * @see #getSelectionBinding()
	 * @generated
	 */
	EReference getSelectionBinding_DomainReference();

	/**
	 * Returns the meta object for enum '{@link de.modagile.metamodel.app.domain.UpdateStrategy <em>Update Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Update Strategy</em>'.
	 * @see de.modagile.metamodel.app.domain.UpdateStrategy
	 * @generated
	 */
	EEnum getUpdateStrategy();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DomainFactory getDomainFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.impl.DomainBindingImpl <em>Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.impl.DomainBindingImpl
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getDomainBinding()
		 * @generated
		 */
		EClass DOMAIN_BINDING = eINSTANCE.getDomainBinding();

		/**
		 * The meta object literal for the '<em><b>Update Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN_BINDING__UPDATE_STRATEGY = eINSTANCE.getDomainBinding_UpdateStrategy();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.impl.ComplexBindingImpl <em>Complex Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.impl.ComplexBindingImpl
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getComplexBinding()
		 * @generated
		 */
		EClass COMPLEX_BINDING = eINSTANCE.getComplexBinding();

		/**
		 * The meta object literal for the '<em><b>Domain Entity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLEX_BINDING__DOMAIN_ENTITY = eINSTANCE.getComplexBinding_DomainEntity();

		/**
		 * The meta object literal for the '<em><b>Ui Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLEX_BINDING__UI_ELEMENT = eINSTANCE.getComplexBinding_UiElement();

		/**
		 * The meta object literal for the '<em><b>Inner Bindings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLEX_BINDING__INNER_BINDINGS = eINSTANCE.getComplexBinding_InnerBindings();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.impl.BindingRepositoryImpl <em>Binding Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.impl.BindingRepositoryImpl
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getBindingRepository()
		 * @generated
		 */
		EClass BINDING_REPOSITORY = eINSTANCE.getBindingRepository();

		/**
		 * The meta object literal for the '<em><b>Bindings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINDING_REPOSITORY__BINDINGS = eINSTANCE.getBindingRepository_Bindings();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.impl.StringBindingImpl <em>String Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.impl.StringBindingImpl
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getStringBinding()
		 * @generated
		 */
		EClass STRING_BINDING = eINSTANCE.getStringBinding();

		/**
		 * The meta object literal for the '<em><b>Ui Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_BINDING__UI_ELEMENT = eINSTANCE.getStringBinding_UiElement();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.impl.BooleanBindingImpl <em>Boolean Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.impl.BooleanBindingImpl
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getBooleanBinding()
		 * @generated
		 */
		EClass BOOLEAN_BINDING = eINSTANCE.getBooleanBinding();

		/**
		 * The meta object literal for the '<em><b>Check Box</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOLEAN_BINDING__CHECK_BOX = eINSTANCE.getBooleanBinding_CheckBox();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.impl.PrimitiveBindingImpl <em>Primitive Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.impl.PrimitiveBindingImpl
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getPrimitiveBinding()
		 * @generated
		 */
		EClass PRIMITIVE_BINDING = eINSTANCE.getPrimitiveBinding();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIMITIVE_BINDING__ATTRIBUTE = eINSTANCE.getPrimitiveBinding_Attribute();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.impl.ListBindingImpl <em>List Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.impl.ListBindingImpl
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getListBinding()
		 * @generated
		 */
		EClass LIST_BINDING = eINSTANCE.getListBinding();

		/**
		 * The meta object literal for the '<em><b>List Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_BINDING__LIST_ELEMENT = eINSTANCE.getListBinding_ListElement();

		/**
		 * The meta object literal for the '<em><b>Domain Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_BINDING__DOMAIN_REFERENCE = eINSTANCE.getListBinding_DomainReference();

		/**
		 * The meta object literal for the '<em><b>Inner Bindings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_BINDING__INNER_BINDINGS = eINSTANCE.getListBinding_InnerBindings();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.impl.SelectionBindingImpl <em>Selection Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.impl.SelectionBindingImpl
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getSelectionBinding()
		 * @generated
		 */
		EClass SELECTION_BINDING = eINSTANCE.getSelectionBinding();

		/**
		 * The meta object literal for the '<em><b>Domain Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SELECTION_BINDING__DOMAIN_REFERENCE = eINSTANCE.getSelectionBinding_DomainReference();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.domain.UpdateStrategy <em>Update Strategy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.domain.UpdateStrategy
		 * @see de.modagile.metamodel.app.domain.impl.DomainPackageImpl#getUpdateStrategy()
		 * @generated
		 */
		EEnum UPDATE_STRATEGY = eINSTANCE.getUpdateStrategy();

	}

} //DomainPackage
