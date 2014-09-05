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
package de.modagile.metamodel.app.generatorconfig.util;

import de.modagile.metamodel.app.generatorconfig.*;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage
 * @generated
 */
public class GeneratorconfigValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final GeneratorconfigValidator INSTANCE = new GeneratorconfigValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "de.modagile.metamodel.app.generatorconfig";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneratorconfigValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return GeneratorconfigPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case GeneratorconfigPackage.GENERATOR_CONFIG:
				return validateGeneratorConfig((GeneratorConfig)value, diagnostics, context);
			case GeneratorconfigPackage.GENERATOR_CONFIG_CONTAINER:
				return validateGeneratorConfigContainer((GeneratorConfigContainer)value, diagnostics, context);
			case GeneratorconfigPackage.PLATFORM_TYPE:
				return validatePlatformType((PlatformType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneratorConfig(GeneratorConfig generatorConfig, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(generatorConfig, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(generatorConfig, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(generatorConfig, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(generatorConfig, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(generatorConfig, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(generatorConfig, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(generatorConfig, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(generatorConfig, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(generatorConfig, diagnostics, context);
		if (result || diagnostics != null) result &= validateGeneratorConfig_namespaceMustHasAtLeastTwoSegments(generatorConfig, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the namespaceMustHasAtLeastTwoSegments constraint of '<em>Generator Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String GENERATOR_CONFIG__NAMESPACE_MUST_HAS_AT_LEAST_TWO_SEGMENTS__EEXPRESSION = "namespace.matches('[a-z0-9]+([.][a-z0-9]+)+')";

	/**
	 * Validates the namespaceMustHasAtLeastTwoSegments constraint of '<em>Generator Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneratorConfig_namespaceMustHasAtLeastTwoSegments(GeneratorConfig generatorConfig, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(GeneratorconfigPackage.Literals.GENERATOR_CONFIG,
				 generatorConfig,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "namespaceMustHasAtLeastTwoSegments",
				 GENERATOR_CONFIG__NAMESPACE_MUST_HAS_AT_LEAST_TWO_SEGMENTS__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneratorConfigContainer(GeneratorConfigContainer generatorConfigContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(generatorConfigContainer, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(generatorConfigContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(generatorConfigContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(generatorConfigContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(generatorConfigContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(generatorConfigContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(generatorConfigContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(generatorConfigContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(generatorConfigContainer, diagnostics, context);
		if (result || diagnostics != null) result &= validateGeneratorConfigContainer_OnlyOneGeneratorConfigPerPlatform(generatorConfigContainer, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the OnlyOneGeneratorConfigPerPlatform constraint of '<em>Generator Config Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String GENERATOR_CONFIG_CONTAINER__ONLY_ONE_GENERATOR_CONFIG_PER_PLATFORM__EEXPRESSION = "generatorConfigs->isUnique(targetPlatform)";

	/**
	 * Validates the OnlyOneGeneratorConfigPerPlatform constraint of '<em>Generator Config Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGeneratorConfigContainer_OnlyOneGeneratorConfigPerPlatform(GeneratorConfigContainer generatorConfigContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(GeneratorconfigPackage.Literals.GENERATOR_CONFIG_CONTAINER,
				 generatorConfigContainer,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "OnlyOneGeneratorConfigPerPlatform",
				 GENERATOR_CONFIG_CONTAINER__ONLY_ONE_GENERATOR_CONFIG_PER_PLATFORM__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePlatformType(PlatformType platformType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //GeneratorconfigValidator
