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
package de.modagile.metamodel.app.generatorconfig.impl;

import de.modagile.metamodel.app.AppPackage;

import de.modagile.metamodel.app.domain.DomainPackage;

import de.modagile.metamodel.app.domain.impl.DomainPackageImpl;

import de.modagile.metamodel.app.event.EventPackage;

import de.modagile.metamodel.app.event.impl.EventPackageImpl;

import de.modagile.metamodel.app.generatorconfig.GeneratorConfig;
import de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer;
import de.modagile.metamodel.app.generatorconfig.GeneratorconfigFactory;
import de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage;

import de.modagile.metamodel.app.generatorconfig.PlatformType;
import de.modagile.metamodel.app.generatorconfig.util.GeneratorconfigValidator;
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
public class GeneratorconfigPackageImpl extends EPackageImpl implements GeneratorconfigPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generatorConfigEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass generatorConfigContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum platformTypeEEnum = null;

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
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private GeneratorconfigPackageImpl() {
		super(eNS_URI, GeneratorconfigFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link GeneratorconfigPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static GeneratorconfigPackage init() {
		if (isInited) return (GeneratorconfigPackage)EPackage.Registry.INSTANCE.getEPackage(GeneratorconfigPackage.eNS_URI);

		// Obtain or create and register package
		GeneratorconfigPackageImpl theGeneratorconfigPackage = (GeneratorconfigPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GeneratorconfigPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GeneratorconfigPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		AppPackageImpl theAppPackage = (AppPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI) instanceof AppPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI) : AppPackage.eINSTANCE);
		UIPackageImpl theUIPackage = (UIPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI) instanceof UIPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UIPackage.eNS_URI) : UIPackage.eINSTANCE);
		DomainPackageImpl theDomainPackage = (DomainPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) instanceof DomainPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DomainPackage.eNS_URI) : DomainPackage.eINSTANCE);
		EventPackageImpl theEventPackage = (EventPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI) instanceof EventPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EventPackage.eNS_URI) : EventPackage.eINSTANCE);

		// Create package meta-data objects
		theGeneratorconfigPackage.createPackageContents();
		theAppPackage.createPackageContents();
		theUIPackage.createPackageContents();
		theDomainPackage.createPackageContents();
		theEventPackage.createPackageContents();

		// Initialize created meta-data
		theGeneratorconfigPackage.initializePackageContents();
		theAppPackage.initializePackageContents();
		theUIPackage.initializePackageContents();
		theDomainPackage.initializePackageContents();
		theEventPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theGeneratorconfigPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return GeneratorconfigValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theGeneratorconfigPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GeneratorconfigPackage.eNS_URI, theGeneratorconfigPackage);
		return theGeneratorconfigPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneratorConfig() {
		return generatorConfigEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratorConfig_ProjectName() {
		return (EAttribute)generatorConfigEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratorConfig_Namespace() {
		return (EAttribute)generatorConfigEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratorConfig_ProjectPath() {
		return (EAttribute)generatorConfigEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneratorConfig_TargetPlatform() {
		return (EAttribute)generatorConfigEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGeneratorConfigContainer() {
		return generatorConfigContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneratorConfigContainer_GeneratorConfigs() {
		return (EReference)generatorConfigContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPlatformType() {
		return platformTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneratorconfigFactory getGeneratorconfigFactory() {
		return (GeneratorconfigFactory)getEFactoryInstance();
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
		generatorConfigEClass = createEClass(GENERATOR_CONFIG);
		createEAttribute(generatorConfigEClass, GENERATOR_CONFIG__PROJECT_NAME);
		createEAttribute(generatorConfigEClass, GENERATOR_CONFIG__NAMESPACE);
		createEAttribute(generatorConfigEClass, GENERATOR_CONFIG__PROJECT_PATH);
		createEAttribute(generatorConfigEClass, GENERATOR_CONFIG__TARGET_PLATFORM);

		generatorConfigContainerEClass = createEClass(GENERATOR_CONFIG_CONTAINER);
		createEReference(generatorConfigContainerEClass, GENERATOR_CONFIG_CONTAINER__GENERATOR_CONFIGS);

		// Create enums
		platformTypeEEnum = createEEnum(PLATFORM_TYPE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(generatorConfigEClass, GeneratorConfig.class, "GeneratorConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeneratorConfig_ProjectName(), ecorePackage.getEString(), "projectName", null, 1, 1, GeneratorConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorConfig_Namespace(), ecorePackage.getEString(), "namespace", null, 1, 1, GeneratorConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorConfig_ProjectPath(), ecorePackage.getEString(), "projectPath", null, 0, 1, GeneratorConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneratorConfig_TargetPlatform(), this.getPlatformType(), "targetPlatform", null, 1, 1, GeneratorConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generatorConfigContainerEClass, GeneratorConfigContainer.class, "GeneratorConfigContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeneratorConfigContainer_GeneratorConfigs(), this.getGeneratorConfig(), null, "generatorConfigs", null, 1, -1, GeneratorConfigContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(platformTypeEEnum, PlatformType.class, "PlatformType");
		addEEnumLiteral(platformTypeEEnum, PlatformType.ANDROID);
		addEEnumLiteral(platformTypeEEnum, PlatformType.IOS);
		addEEnumLiteral(platformTypeEEnum, PlatformType.WINDOWS_PHONE);

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
		  (generatorConfigEClass, 
		   source, 
		   new String[] {
			 "constraints", "namespaceMustHasAtLeastTwoSegments"
		   });			
		addAnnotation
		  (generatorConfigContainerEClass, 
		   source, 
		   new String[] {
			 "constraints", "OnlyOneGeneratorConfigPerPlatform"
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
		  (generatorConfigEClass, 
		   source, 
		   new String[] {
			 "namespaceMustHasAtLeastTwoSegments", "namespace.matches(\'[a-z0-9]+([.][a-z0-9]+)+\')"
		   });			
		addAnnotation
		  (generatorConfigContainerEClass, 
		   source, 
		   new String[] {
			 "OnlyOneGeneratorConfigPerPlatform", "generatorConfigs->isUnique(targetPlatform)"
		   });
	}

} //GeneratorconfigPackageImpl
