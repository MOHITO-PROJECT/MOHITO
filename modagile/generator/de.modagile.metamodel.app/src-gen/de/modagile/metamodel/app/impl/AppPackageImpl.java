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
package de.modagile.metamodel.app.impl;

import de.modagile.metamodel.app.AppFactory;
import de.modagile.metamodel.app.AppPackage;
import de.modagile.metamodel.app.CompositeDisplayElementTypeRepository;
import de.modagile.metamodel.app.Entity;
import de.modagile.metamodel.app.FeaturedElement;
import de.modagile.metamodel.app.MobileApp;

import de.modagile.metamodel.app.domain.DomainPackage;

import de.modagile.metamodel.app.domain.impl.DomainPackageImpl;

import de.modagile.metamodel.app.event.EventPackage;

import de.modagile.metamodel.app.event.impl.EventPackageImpl;

import de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage;

import de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl;

import de.modagile.metamodel.app.platform.PlatformPackage;

import de.modagile.metamodel.app.platform.impl.PlatformPackageImpl;

import de.modagile.metamodel.app.ui.UIPackage;

import de.modagile.metamodel.app.ui.impl.UIPackageImpl;

import de.modagile.metamodel.app.util.AppValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
public class AppPackageImpl extends EPackageImpl implements AppPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featuredElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mobileAppEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeDisplayElementTypeRepositoryEClass = null;

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
	 * @see de.modagile.metamodel.app.AppPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AppPackageImpl() {
		super(eNS_URI, AppFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AppPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AppPackage init() {
		if (isInited) return (AppPackage)EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI);

		// Obtain or create and register package
		AppPackageImpl theAppPackage = (AppPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AppPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AppPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		UIPackageImpl theUIPackage = (UIPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI) instanceof UIPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI) : UIPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);
		EventPackageImpl theEventPackage = (EventPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI) instanceof EventPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI) : EventPackage.eINSTANCE);
		GeneratorconfigPackageImpl theGeneratorconfigPackage = (GeneratorconfigPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GeneratorconfigPackage.eNS_URI) instanceof GeneratorconfigPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GeneratorconfigPackage.eNS_URI) : GeneratorconfigPackage.eINSTANCE);

		// Create package meta-data objects
		theAppPackage.createPackageContents();
		theUIPackage.createPackageContents();
		theDomainPackage.createPackageContents();
		theEventPackage.createPackageContents();
		theGeneratorconfigPackage.createPackageContents();

		// Initialize created meta-data
		theAppPackage.initializePackageContents();
		theUIPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();
		theEventPackage.initializePackageContents();
		theGeneratorconfigPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theAppPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return AppValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theAppPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AppPackage.eNS_URI, theAppPackage);
		return theAppPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeaturedElement() {
		return featuredElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntity() {
		return entityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEntity_Name() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMobileApp() {
		return mobileAppEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMobileApp_StoryBoard() {
		return (EReference)mobileAppEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMobileApp_BindingRepository() {
		return (EReference)mobileAppEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMobileApp_AppDatabaseVersion() {
		return (EAttribute)mobileAppEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMobileApp_AppVersion() {
		return (EAttribute)mobileAppEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMobileApp_CompositeDisplayElementTypeRepository() {
		return (EReference)mobileAppEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMobileApp_DomainPackage() {
		return (EReference)mobileAppEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMobileApp_GeneratorConfigContainer() {
		return (EReference)mobileAppEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMobileApp_EventContainer() {
		return (EReference)mobileAppEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompositeDisplayElementTypeRepository() {
		return compositeDisplayElementTypeRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompositeDisplayElementTypeRepository_CompositeDisplayElementTypes() {
		return (EReference)compositeDisplayElementTypeRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AppFactory getAppFactory() {
		return (AppFactory)getEFactoryInstance();
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
		featuredElementEClass = createEClass(FEATURED_ELEMENT);

		entityEClass = createEClass(ENTITY);
		createEAttribute(entityEClass, ENTITY__NAME);

		mobileAppEClass = createEClass(MOBILE_APP);
		createEReference(mobileAppEClass, MOBILE_APP__STORY_BOARD);
		createEReference(mobileAppEClass, MOBILE_APP__BINDING_REPOSITORY);
		createEAttribute(mobileAppEClass, MOBILE_APP__APP_DATABASE_VERSION);
		createEAttribute(mobileAppEClass, MOBILE_APP__APP_VERSION);
		createEReference(mobileAppEClass, MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY);
		createEReference(mobileAppEClass, MOBILE_APP__DOMAIN_PACKAGE);
		createEReference(mobileAppEClass, MOBILE_APP__GENERATOR_CONFIG_CONTAINER);
		createEReference(mobileAppEClass, MOBILE_APP__EVENT_CONTAINER);

		compositeDisplayElementTypeRepositoryEClass = createEClass(COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY);
		createEReference(compositeDisplayElementTypeRepositoryEClass, COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES);
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
		UIPackage theUIPackage = (UIPackage)EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI);
		DomainPackage theDomainPackage = (DomainPackage)EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI);
		EventPackage theEventPackage = (EventPackage)EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI);
		GeneratorconfigPackage theGeneratorconfigPackage = (GeneratorconfigPackage)EPackage.Registry.INSTANCE.getEPackage(GeneratorconfigPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theUIPackage);
		getESubpackages().add(theDomainPackage);
		getESubpackages().add(theEventPackage);
		getESubpackages().add(theGeneratorconfigPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		featuredElementEClass.getESuperTypes().add(this.getEntity());
		mobileAppEClass.getESuperTypes().add(this.getEntity());

		// Initialize classes and features; add operations and parameters
		initEClass(featuredElementEClass, FeaturedElement.class, "FeaturedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(entityEClass, Entity.class, "Entity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntity_Name(), ecorePackage.getEString(), "name", null, 1, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mobileAppEClass, MobileApp.class, "MobileApp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMobileApp_StoryBoard(), theUIPackage.getStoryBoard(), null, "storyBoard", null, 0, 1, MobileApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMobileApp_BindingRepository(), theDomainPackage.getBindingRepository(), null, "bindingRepository", null, 0, 1, MobileApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMobileApp_AppDatabaseVersion(), ecorePackage.getEInt(), "appDatabaseVersion", null, 0, 1, MobileApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMobileApp_AppVersion(), ecorePackage.getEString(), "appVersion", null, 0, 1, MobileApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMobileApp_CompositeDisplayElementTypeRepository(), this.getCompositeDisplayElementTypeRepository(), null, "compositeDisplayElementTypeRepository", null, 0, 1, MobileApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMobileApp_DomainPackage(), ecorePackage.getEPackage(), null, "domainPackage", null, 1, 1, MobileApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMobileApp_GeneratorConfigContainer(), theGeneratorconfigPackage.getGeneratorConfigContainer(), null, "generatorConfigContainer", null, 1, 1, MobileApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMobileApp_EventContainer(), theEventPackage.getEventContainer(), null, "eventContainer", null, 0, 1, MobileApp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeDisplayElementTypeRepositoryEClass, CompositeDisplayElementTypeRepository.class, "CompositeDisplayElementTypeRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompositeDisplayElementTypeRepository_CompositeDisplayElementTypes(), theUIPackage.getCompositeDisplayElementType(), null, "compositeDisplayElementTypes", null, 0, -1, CompositeDisplayElementTypeRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

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
		  (entityEClass, 
		   source, 
		   new String[] {
			 "constraints", "NamingConvention"
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
		  (entityEClass, 
		   source, 
		   new String[] {
			 "NamingConvention", "name.matches(\'[A-Z][a-z0-9_]*([A-Z][a-z0-9_]*)*\')"
		   });
	}

} //AppPackageImpl
