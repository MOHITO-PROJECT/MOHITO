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
package de.modagile.metamodel.app.domain.impl;

import de.modagile.metamodel.app.AppPackage;

import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.domain.BooleanBinding;
import de.modagile.metamodel.app.domain.ComplexBinding;
import de.modagile.metamodel.app.domain.DomainBinding;
import de.modagile.metamodel.app.domain.DomainFactory;
import de.modagile.metamodel.app.domain.DomainPackage;
import de.modagile.metamodel.app.domain.ListBinding;
import de.modagile.metamodel.app.domain.PrimitiveBinding;
import de.modagile.metamodel.app.domain.SelectionBinding;
import de.modagile.metamodel.app.domain.StringBinding;
import de.modagile.metamodel.app.domain.UpdateStrategy;

import de.modagile.metamodel.app.domain.util.DomainValidator;
import de.modagile.metamodel.app.event.EventPackage;

import de.modagile.metamodel.app.event.impl.EventPackageImpl;

import de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage;

import de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl;

import de.modagile.metamodel.app.impl.AppPackageImpl;

import de.modagile.metamodel.app.platform.PlatformPackage;

import de.modagile.metamodel.app.platform.impl.PlatformPackageImpl;

import de.modagile.metamodel.app.ui.UIPackage;

import de.modagile.metamodel.app.ui.impl.UIPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DomainPackageImpl extends EPackageImpl implements DomainPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass complexBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bindingRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selectionBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum updateStrategyEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.modagile.metamodel.app.domain.DomainPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DomainPackageImpl() {
		super(eNS_URI, DomainFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link DomainPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DomainPackage init() {
		if (isInited) return (DomainPackage)EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI);

		// Obtain or create and register package
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DomainPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		AppPackageImpl theAppPackage = (AppPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI) instanceof AppPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI) : AppPackage.eINSTANCE);
		UIPackageImpl theUIPackage = (UIPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI) instanceof UIPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI) : UIPackage.eINSTANCE);
		EventPackageImpl theEventPackage = (EventPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI) instanceof EventPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI) : EventPackage.eINSTANCE);
		GeneratorconfigPackageImpl theGeneratorconfigPackage = (GeneratorconfigPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneratorconfigPackage.eNS_URI) instanceof GeneratorconfigPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneratorconfigPackage.eNS_URI) : GeneratorconfigPackage.eINSTANCE);

		// Create package meta-data objects
		theDomainPackage.createPackageContents();
		theAppPackage.createPackageContents();
		theUIPackage.createPackageContents();
		theEventPackage.createPackageContents();
		theGeneratorconfigPackage.createPackageContents();

		// Initialize created meta-data
		theDomainPackage.initializePackageContents();
		theAppPackage.initializePackageContents();
		theUIPackage.initializePackageContents();
		theEventPackage.initializePackageContents();
		theGeneratorconfigPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theDomainPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return DomainValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theDomainPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DomainPackage.eNS_URI, theDomainPackage);
		return theDomainPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainBinding() {
		return domainBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainBinding_UpdateStrategy() {
		return (EAttribute)domainBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComplexBinding() {
		return complexBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComplexBinding_DomainEntity() {
		return (EReference)complexBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComplexBinding_UiElement() {
		return (EReference)complexBindingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComplexBinding_InnerBindings() {
		return (EReference)complexBindingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBindingRepository() {
		return bindingRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBindingRepository_Bindings() {
		return (EReference)bindingRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringBinding() {
		return stringBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringBinding_UiElement() {
		return (EReference)stringBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanBinding() {
		return booleanBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBooleanBinding_CheckBox() {
		return (EReference)booleanBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveBinding() {
		return primitiveBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPrimitiveBinding_Attribute() {
		return (EReference)primitiveBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListBinding() {
		return listBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListBinding_ListElement() {
		return (EReference)listBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListBinding_DomainReference() {
		return (EReference)listBindingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListBinding_InnerBindings() {
		return (EReference)listBindingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSelectionBinding() {
		return selectionBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSelectionBinding_DomainReference() {
		return (EReference)selectionBindingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUpdateStrategy() {
		return updateStrategyEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainFactory getDomainFactory() {
		return (DomainFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		domainBindingEClass = createEClass(DOMAIN_BINDING);
		createEAttribute(domainBindingEClass, DOMAIN_BINDING__UPDATE_STRATEGY);

		complexBindingEClass = createEClass(COMPLEX_BINDING);
		createEReference(complexBindingEClass, COMPLEX_BINDING__DOMAIN_ENTITY);
		createEReference(complexBindingEClass, COMPLEX_BINDING__UI_ELEMENT);
		createEReference(complexBindingEClass, COMPLEX_BINDING__INNER_BINDINGS);

		bindingRepositoryEClass = createEClass(BINDING_REPOSITORY);
		createEReference(bindingRepositoryEClass, BINDING_REPOSITORY__BINDINGS);

		stringBindingEClass = createEClass(STRING_BINDING);
		createEReference(stringBindingEClass, STRING_BINDING__UI_ELEMENT);

		booleanBindingEClass = createEClass(BOOLEAN_BINDING);
		createEReference(booleanBindingEClass, BOOLEAN_BINDING__CHECK_BOX);

		primitiveBindingEClass = createEClass(PRIMITIVE_BINDING);
		createEReference(primitiveBindingEClass, PRIMITIVE_BINDING__ATTRIBUTE);

		listBindingEClass = createEClass(LIST_BINDING);
		createEReference(listBindingEClass, LIST_BINDING__LIST_ELEMENT);
		createEReference(listBindingEClass, LIST_BINDING__DOMAIN_REFERENCE);
		createEReference(listBindingEClass, LIST_BINDING__INNER_BINDINGS);

		selectionBindingEClass = createEClass(SELECTION_BINDING);
		createEReference(selectionBindingEClass, SELECTION_BINDING__DOMAIN_REFERENCE);

		// Create enums
		updateStrategyEEnum = createEEnum(UPDATE_STRATEGY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		AppPackage theAppPackage = (AppPackage)EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI);
		UIPackage theUIPackage = (UIPackage)EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		domainBindingEClass.getESuperTypes().add(theAppPackage.getFeaturedElement());
		complexBindingEClass.getESuperTypes().add(this.getDomainBinding());
		stringBindingEClass.getESuperTypes().add(this.getPrimitiveBinding());
		booleanBindingEClass.getESuperTypes().add(this.getPrimitiveBinding());
		primitiveBindingEClass.getESuperTypes().add(this.getDomainBinding());
		listBindingEClass.getESuperTypes().add(this.getDomainBinding());
		selectionBindingEClass.getESuperTypes().add(this.getDomainBinding());

		// Initialize classes and features; add operations and parameters
		initEClass(domainBindingEClass, DomainBinding.class, "DomainBinding", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDomainBinding_UpdateStrategy(), this.getUpdateStrategy(), "updateStrategy", null, 0, 1, DomainBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(complexBindingEClass, ComplexBinding.class, "ComplexBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComplexBinding_DomainEntity(), ecorePackage.getEClass(), null, "domainEntity", null, 1, 1, ComplexBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComplexBinding_UiElement(), theUIPackage.getCompositeDisplayElementType(), null, "uiElement", null, 1, 1, ComplexBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComplexBinding_InnerBindings(), this.getPrimitiveBinding(), null, "innerBindings", null, 0, -1, ComplexBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bindingRepositoryEClass, BindingRepository.class, "BindingRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBindingRepository_Bindings(), this.getDomainBinding(), null, "bindings", null, 0, -1, BindingRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringBindingEClass, StringBinding.class, "StringBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStringBinding_UiElement(), theUIPackage.getTextContaining(), null, "uiElement", null, 1, 1, StringBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanBindingEClass, BooleanBinding.class, "BooleanBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBooleanBinding_CheckBox(), theUIPackage.getCheckBox(), null, "checkBox", null, 1, 1, BooleanBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(primitiveBindingEClass, PrimitiveBinding.class, "PrimitiveBinding", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPrimitiveBinding_Attribute(), ecorePackage.getEAttribute(), null, "attribute", null, 1, 1, PrimitiveBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listBindingEClass, ListBinding.class, "ListBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getListBinding_ListElement(), theUIPackage.getDynamicList(), null, "listElement", null, 1, 1, ListBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListBinding_DomainReference(), ecorePackage.getEReference(), null, "domainReference", null, 1, 1, ListBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListBinding_InnerBindings(), this.getDomainBinding(), null, "innerBindings", null, 0, -1, ListBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(selectionBindingEClass, SelectionBinding.class, "SelectionBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSelectionBinding_DomainReference(), ecorePackage.getEReference(), null, "domainReference", null, 1, 1, SelectionBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(updateStrategyEEnum, UpdateStrategy.class, "UpdateStrategy");
		addEEnumLiteral(updateStrategyEEnum, UpdateStrategy.READ);
		addEEnumLiteral(updateStrategyEEnum, UpdateStrategy.WRITE);
		addEEnumLiteral(updateStrategyEEnum, UpdateStrategy.WRITE_THROUGH);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot
		createPivotAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "invocationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
			 "settingDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
			 "validationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot"
		   });		
		addAnnotation
		  (complexBindingEClass, 
		   source, 
		   new String[] {
			 "constraints", "ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings"
		   });			
		addAnnotation
		  (stringBindingEClass, 
		   source, 
		   new String[] {
			 "constraints", "StringBindingAttributeMustBeOfTypeString"
		   });			
		addAnnotation
		  (booleanBindingEClass, 
		   source, 
		   new String[] {
			 "constraints", "BooleanBindingAttributeMustBeOfTypeBoolean"
		   });	
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createPivotAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot";				
		addAnnotation
		  (complexBindingEClass, 
		   source, 
		   new String[] {
			 "ComplexBinding_domainEntityAttributes_MustInclude_InnerBindingAttributes", "self.innerBindings->forAll(binding : PrimitiveBinding | self.domainEntity.eAttributes->includes(binding.attribute))",
			 "ComplexBinding_UiElement_MustInclude_BoundUiElementsOf_InnerBindings", "self.innerBindings->forAll(binding : PrimitiveBinding | binding.oclIsKindOf(StringBinding) and self.uiElement.containedDisplayElements->includes(binding.oclAsType(StringBinding).uiElement) or binding.oclIsKindOf(BooleanBinding) and self.uiElement.containedDisplayElements->includes(binding.oclAsType(BooleanBinding).checkBox))"
		   });			
		addAnnotation
		  (stringBindingEClass, 
		   source, 
		   new String[] {
			 "StringBindingAttributeMustBeOfTypeString", "self.attribute.eType.name = \'EString\'"
		   });			
		addAnnotation
		  (booleanBindingEClass, 
		   source, 
		   new String[] {
			 "BooleanBindingAttributeMustBeOfTypeBoolean", "self.attribute.eType.name = \'EBoolean\'"
		   });
	}

} //DomainPackageImpl
