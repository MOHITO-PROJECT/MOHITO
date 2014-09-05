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
package de.modagile.metamodel.app.generatorconfig;

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
 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface GeneratorconfigPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "generatorconfig";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.modagile-mobile.de/metamodel#generatorconfig";

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
	GeneratorconfigPackage eINSTANCE = de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigImpl <em>Generator Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigImpl
	 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl#getGeneratorConfig()
	 * @generated
	 */
	int GENERATOR_CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIG__PROJECT_NAME = 0;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIG__NAMESPACE = 1;

	/**
	 * The feature id for the '<em><b>Project Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIG__PROJECT_PATH = 2;

	/**
	 * The feature id for the '<em><b>Target Platform</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIG__TARGET_PLATFORM = 3;

	/**
	 * The number of structural features of the '<em>Generator Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIG_FEATURE_COUNT = 4;


	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigContainerImpl <em>Generator Config Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigContainerImpl
	 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl#getGeneratorConfigContainer()
	 * @generated
	 */
	int GENERATOR_CONFIG_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Generator Configs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIG_CONTAINER__GENERATOR_CONFIGS = 0;

	/**
	 * The number of structural features of the '<em>Generator Config Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_CONFIG_CONTAINER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.modagile.metamodel.app.generatorconfig.PlatformType <em>Platform Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.modagile.metamodel.app.generatorconfig.PlatformType
	 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl#getPlatformType()
	 * @generated
	 */
	int PLATFORM_TYPE = 2;


	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig <em>Generator Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generator Config</em>'.
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorConfig
	 * @generated
	 */
	EClass getGeneratorConfig();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getProjectName <em>Project Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Name</em>'.
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getProjectName()
	 * @see #getGeneratorConfig()
	 * @generated
	 */
	EAttribute getGeneratorConfig_ProjectName();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getNamespace()
	 * @see #getGeneratorConfig()
	 * @generated
	 */
	EAttribute getGeneratorConfig_Namespace();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getProjectPath <em>Project Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Project Path</em>'.
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getProjectPath()
	 * @see #getGeneratorConfig()
	 * @generated
	 */
	EAttribute getGeneratorConfig_ProjectPath();

	/**
	 * Returns the meta object for the attribute '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getTargetPlatform <em>Target Platform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Platform</em>'.
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getTargetPlatform()
	 * @see #getGeneratorConfig()
	 * @generated
	 */
	EAttribute getGeneratorConfig_TargetPlatform();

	/**
	 * Returns the meta object for class '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer <em>Generator Config Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generator Config Container</em>'.
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer
	 * @generated
	 */
	EClass getGeneratorConfigContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer#getGeneratorConfigs <em>Generator Configs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Generator Configs</em>'.
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer#getGeneratorConfigs()
	 * @see #getGeneratorConfigContainer()
	 * @generated
	 */
	EReference getGeneratorConfigContainer_GeneratorConfigs();

	/**
	 * Returns the meta object for enum '{@link de.modagile.metamodel.app.generatorconfig.PlatformType <em>Platform Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Platform Type</em>'.
	 * @see de.modagile.metamodel.app.generatorconfig.PlatformType
	 * @generated
	 */
	EEnum getPlatformType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	GeneratorconfigFactory getGeneratorconfigFactory();

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
		 * The meta object literal for the '{@link de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigImpl <em>Generator Config</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigImpl
		 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl#getGeneratorConfig()
		 * @generated
		 */
		EClass GENERATOR_CONFIG = eINSTANCE.getGeneratorConfig();

		/**
		 * The meta object literal for the '<em><b>Project Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_CONFIG__PROJECT_NAME = eINSTANCE.getGeneratorConfig_ProjectName();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_CONFIG__NAMESPACE = eINSTANCE.getGeneratorConfig_Namespace();

		/**
		 * The meta object literal for the '<em><b>Project Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_CONFIG__PROJECT_PATH = eINSTANCE.getGeneratorConfig_ProjectPath();

		/**
		 * The meta object literal for the '<em><b>Target Platform</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATOR_CONFIG__TARGET_PLATFORM = eINSTANCE.getGeneratorConfig_TargetPlatform();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigContainerImpl <em>Generator Config Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigContainerImpl
		 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl#getGeneratorConfigContainer()
		 * @generated
		 */
		EClass GENERATOR_CONFIG_CONTAINER = eINSTANCE.getGeneratorConfigContainer();

		/**
		 * The meta object literal for the '<em><b>Generator Configs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERATOR_CONFIG_CONTAINER__GENERATOR_CONFIGS = eINSTANCE.getGeneratorConfigContainer_GeneratorConfigs();

		/**
		 * The meta object literal for the '{@link de.modagile.metamodel.app.generatorconfig.PlatformType <em>Platform Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.modagile.metamodel.app.generatorconfig.PlatformType
		 * @see de.modagile.metamodel.app.generatorconfig.impl.GeneratorconfigPackageImpl#getPlatformType()
		 * @generated
		 */
		EEnum PLATFORM_TYPE = eINSTANCE.getPlatformType();

	}

} //GeneratorconfigPackage
