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
package de.modagile.metamodel.app;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see de.modagile.metamodel.app.AppFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface AppPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "app";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modagile-mobile.de/metamodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "de.modagile.metamodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AppPackage eINSTANCE = de.modagile.metamodel.app.impl.AppPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.impl.EntityImpl
	 * @see de.modagile.metamodel.app.impl.AppPackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.impl.FeaturedElementImpl <em>Featured Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.impl.FeaturedElementImpl
	 * @see de.modagile.metamodel.app.impl.AppPackageImpl#getFeaturedElement()
	 * @generated
	 */
	int FEATURED_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURED_ELEMENT__NAME = ENTITY__NAME;

	/**
	 * The number of structural features of the '<em>Featured Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURED_ELEMENT_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.impl.MobileAppImpl <em>Mobile App</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.impl.MobileAppImpl
	 * @see de.modagile.metamodel.app.impl.AppPackageImpl#getMobileApp()
	 * @generated
	 */
	int MOBILE_APP = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__NAME = ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Story Board</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__STORY_BOARD = ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Binding Repository</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__BINDING_REPOSITORY = ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>App Database Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__APP_DATABASE_VERSION = ENTITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>App Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__APP_VERSION = ENTITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Composite Display Element Type Repository</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY = ENTITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Domain Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__DOMAIN_PACKAGE = ENTITY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Generator Config Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__GENERATOR_CONFIG_CONTAINER = ENTITY_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Event Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP__EVENT_CONTAINER = ENTITY_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Mobile App</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOBILE_APP_FEATURE_COUNT = ENTITY_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.impl.CompositeDisplayElementTypeRepositoryImpl <em>Composite Display Element Type Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.impl.CompositeDisplayElementTypeRepositoryImpl
	 * @see de.modagile.metamodel.app.impl.AppPackageImpl#getCompositeDisplayElementTypeRepository()
	 * @generated
	 */
	int COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY = 3;

	/**
	 * The feature id for the '<em><b>Composite Display Element Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES = 0;

	/**
	 * The number of structural features of the '<em>Composite Display Element Type Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.FeaturedElement <em>Featured Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Featured Element</em>'.
	 * @see de.modagile.metamodel.app.FeaturedElement
	 * @generated
	 */
	EClass getFeaturedElement();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see de.modagile.metamodel.app.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.Entity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.modagile.metamodel.app.Entity#getName()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Name();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.MobileApp <em>Mobile App</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mobile App</em>'.
	 * @see de.modagile.metamodel.app.MobileApp
	 * @generated
	 */
	EClass getMobileApp();

	/**
	 * Returns the meta object for the containment reference '{@link de.modagile.metamodel.app.MobileApp#getStoryBoard <em>Story Board</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Story Board</em>'.
	 * @see de.modagile.metamodel.app.MobileApp#getStoryBoard()
	 * @see #getMobileApp()
	 * @generated
	 */
	EReference getMobileApp_StoryBoard();

	/**
	 * Returns the meta object for the containment reference '{@link de.modagile.metamodel.app.MobileApp#getBindingRepository <em>Binding Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Binding Repository</em>'.
	 * @see de.modagile.metamodel.app.MobileApp#getBindingRepository()
	 * @see #getMobileApp()
	 * @generated
	 */
	EReference getMobileApp_BindingRepository();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.MobileApp#getAppDatabaseVersion <em>App Database Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>App Database Version</em>'.
	 * @see de.modagile.metamodel.app.MobileApp#getAppDatabaseVersion()
	 * @see #getMobileApp()
	 * @generated
	 */
	EAttribute getMobileApp_AppDatabaseVersion();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.MobileApp#getAppVersion <em>App Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>App Version</em>'.
	 * @see de.modagile.metamodel.app.MobileApp#getAppVersion()
	 * @see #getMobileApp()
	 * @generated
	 */
	EAttribute getMobileApp_AppVersion();

	/**
	 * Returns the meta object for the containment reference '{@link de.modagile.metamodel.app.MobileApp#getCompositeDisplayElementTypeRepository <em>Composite Display Element Type Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Composite Display Element Type Repository</em>'.
	 * @see de.modagile.metamodel.app.MobileApp#getCompositeDisplayElementTypeRepository()
	 * @see #getMobileApp()
	 * @generated
	 */
	EReference getMobileApp_CompositeDisplayElementTypeRepository();

	/**
	 * Returns the meta object for the reference '{@link de.modagile.metamodel.app.MobileApp#getDomainPackage <em>Domain Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Domain Package</em>'.
	 * @see de.modagile.metamodel.app.MobileApp#getDomainPackage()
	 * @see #getMobileApp()
	 * @generated
	 */
	EReference getMobileApp_DomainPackage();

	/**
	 * Returns the meta object for the containment reference '{@link de.modagile.metamodel.app.MobileApp#getGeneratorConfigContainer <em>Generator Config Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Generator Config Container</em>'.
	 * @see de.modagile.metamodel.app.MobileApp#getGeneratorConfigContainer()
	 * @see #getMobileApp()
	 * @generated
	 */
	EReference getMobileApp_GeneratorConfigContainer();

	/**
	 * Returns the meta object for the containment reference '{@link de.modagile.metamodel.app.MobileApp#getEventContainer <em>Event Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Container</em>'.
	 * @see de.modagile.metamodel.app.MobileApp#getEventContainer()
	 * @see #getMobileApp()
	 * @generated
	 */
	EReference getMobileApp_EventContainer();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.CompositeDisplayElementTypeRepository <em>Composite Display Element Type Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Display Element Type Repository</em>'.
	 * @see de.modagile.metamodel.app.CompositeDisplayElementTypeRepository
	 * @generated
	 */
	EClass getCompositeDisplayElementTypeRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.CompositeDisplayElementTypeRepository#getCompositeDisplayElementTypes <em>Composite Display Element Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Composite Display Element Types</em>'.
	 * @see de.modagile.metamodel.app.CompositeDisplayElementTypeRepository#getCompositeDisplayElementTypes()
	 * @see #getCompositeDisplayElementTypeRepository()
	 * @generated
	 */
	EReference getCompositeDisplayElementTypeRepository_CompositeDisplayElementTypes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AppFactory getAppFactory();

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
		 * The meta object literal for the '{@link de.modagile.metamodel.app.impl.FeaturedElementImpl <em>Featured Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.impl.FeaturedElementImpl
		 * @see de.modagile.metamodel.app.impl.AppPackageImpl#getFeaturedElement()
		 * @generated
		 */
		EClass FEATURED_ELEMENT = eINSTANCE.getFeaturedElement();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.impl.EntityImpl
		 * @see de.modagile.metamodel.app.impl.AppPackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.impl.MobileAppImpl <em>Mobile App</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.impl.MobileAppImpl
		 * @see de.modagile.metamodel.app.impl.AppPackageImpl#getMobileApp()
		 * @generated
		 */
		EClass MOBILE_APP = eINSTANCE.getMobileApp();

		/**
		 * The meta object literal for the '<em><b>Story Board</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOBILE_APP__STORY_BOARD = eINSTANCE.getMobileApp_StoryBoard();

		/**
		 * The meta object literal for the '<em><b>Binding Repository</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOBILE_APP__BINDING_REPOSITORY = eINSTANCE.getMobileApp_BindingRepository();

		/**
		 * The meta object literal for the '<em><b>App Database Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOBILE_APP__APP_DATABASE_VERSION = eINSTANCE.getMobileApp_AppDatabaseVersion();

		/**
		 * The meta object literal for the '<em><b>App Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MOBILE_APP__APP_VERSION = eINSTANCE.getMobileApp_AppVersion();

		/**
		 * The meta object literal for the '<em><b>Composite Display Element Type Repository</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY = eINSTANCE.getMobileApp_CompositeDisplayElementTypeRepository();

		/**
		 * The meta object literal for the '<em><b>Domain Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOBILE_APP__DOMAIN_PACKAGE = eINSTANCE.getMobileApp_DomainPackage();

		/**
		 * The meta object literal for the '<em><b>Generator Config Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOBILE_APP__GENERATOR_CONFIG_CONTAINER = eINSTANCE.getMobileApp_GeneratorConfigContainer();

		/**
		 * The meta object literal for the '<em><b>Event Container</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOBILE_APP__EVENT_CONTAINER = eINSTANCE.getMobileApp_EventContainer();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.impl.CompositeDisplayElementTypeRepositoryImpl <em>Composite Display Element Type Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.impl.CompositeDisplayElementTypeRepositoryImpl
		 * @see de.modagile.metamodel.app.impl.AppPackageImpl#getCompositeDisplayElementTypeRepository()
		 * @generated
		 */
		EClass COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY = eINSTANCE.getCompositeDisplayElementTypeRepository();

		/**
		 * The meta object literal for the '<em><b>Composite Display Element Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES = eINSTANCE.getCompositeDisplayElementTypeRepository_CompositeDisplayElementTypes();

	}

} //AppPackage
