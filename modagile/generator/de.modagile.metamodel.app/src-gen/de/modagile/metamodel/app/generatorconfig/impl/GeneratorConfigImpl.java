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

import de.modagile.metamodel.app.generatorconfig.GeneratorConfig;
import de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage;

import de.modagile.metamodel.app.generatorconfig.PlatformType;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Generator Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigImpl#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigImpl#getProjectPath <em>Project Path</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.impl.GeneratorConfigImpl#getTargetPlatform <em>Target Platform</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GeneratorConfigImpl extends EObjectImpl implements GeneratorConfig {
	/**
	 * The default value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectName() <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectName()
	 * @generated
	 * @ordered
	 */
	protected String projectName = PROJECT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNamespace() <em>Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected String namespace = NAMESPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectPath() <em>Project Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PROJECT_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectPath() <em>Project Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectPath()
	 * @generated
	 * @ordered
	 */
	protected String projectPath = PROJECT_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetPlatform() <em>Target Platform</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetPlatform()
	 * @generated
	 * @ordered
	 */
	protected static final PlatformType TARGET_PLATFORM_EDEFAULT = PlatformType.ANDROID;

	/**
	 * The cached value of the '{@link #getTargetPlatform() <em>Target Platform</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetPlatform()
	 * @generated
	 * @ordered
	 */
	protected PlatformType targetPlatform = TARGET_PLATFORM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeneratorConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GeneratorconfigPackage.Literals.GENERATOR_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProjectName(String newProjectName) {
		String oldProjectName = projectName;
		projectName = newProjectName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_NAME, oldProjectName, projectName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNamespace(String newNamespace) {
		String oldNamespace = namespace;
		namespace = newNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneratorconfigPackage.GENERATOR_CONFIG__NAMESPACE, oldNamespace, namespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProjectPath() {
		return projectPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProjectPath(String newProjectPath) {
		String oldProjectPath = projectPath;
		projectPath = newProjectPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_PATH, oldProjectPath, projectPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlatformType getTargetPlatform() {
		return targetPlatform;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetPlatform(PlatformType newTargetPlatform) {
		PlatformType oldTargetPlatform = targetPlatform;
		targetPlatform = newTargetPlatform == null ? TARGET_PLATFORM_EDEFAULT : newTargetPlatform;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GeneratorconfigPackage.GENERATOR_CONFIG__TARGET_PLATFORM, oldTargetPlatform, targetPlatform));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_NAME:
				return getProjectName();
			case GeneratorconfigPackage.GENERATOR_CONFIG__NAMESPACE:
				return getNamespace();
			case GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_PATH:
				return getProjectPath();
			case GeneratorconfigPackage.GENERATOR_CONFIG__TARGET_PLATFORM:
				return getTargetPlatform();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_NAME:
				setProjectName((String)newValue);
				return;
			case GeneratorconfigPackage.GENERATOR_CONFIG__NAMESPACE:
				setNamespace((String)newValue);
				return;
			case GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_PATH:
				setProjectPath((String)newValue);
				return;
			case GeneratorconfigPackage.GENERATOR_CONFIG__TARGET_PLATFORM:
				setTargetPlatform((PlatformType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_NAME:
				setProjectName(PROJECT_NAME_EDEFAULT);
				return;
			case GeneratorconfigPackage.GENERATOR_CONFIG__NAMESPACE:
				setNamespace(NAMESPACE_EDEFAULT);
				return;
			case GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_PATH:
				setProjectPath(PROJECT_PATH_EDEFAULT);
				return;
			case GeneratorconfigPackage.GENERATOR_CONFIG__TARGET_PLATFORM:
				setTargetPlatform(TARGET_PLATFORM_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_NAME:
				return PROJECT_NAME_EDEFAULT == null ? projectName != null : !PROJECT_NAME_EDEFAULT.equals(projectName);
			case GeneratorconfigPackage.GENERATOR_CONFIG__NAMESPACE:
				return NAMESPACE_EDEFAULT == null ? namespace != null : !NAMESPACE_EDEFAULT.equals(namespace);
			case GeneratorconfigPackage.GENERATOR_CONFIG__PROJECT_PATH:
				return PROJECT_PATH_EDEFAULT == null ? projectPath != null : !PROJECT_PATH_EDEFAULT.equals(projectPath);
			case GeneratorconfigPackage.GENERATOR_CONFIG__TARGET_PLATFORM:
				return targetPlatform != TARGET_PLATFORM_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (projectName: ");
		result.append(projectName);
		result.append(", namespace: ");
		result.append(namespace);
		result.append(", projectPath: ");
		result.append(projectPath);
		result.append(", targetPlatform: ");
		result.append(targetPlatform);
		result.append(')');
		return result.toString();
	}

} //GeneratorConfigImpl
