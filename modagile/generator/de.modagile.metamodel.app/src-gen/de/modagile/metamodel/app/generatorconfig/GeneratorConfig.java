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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generator Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getProjectName <em>Project Name</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getProjectPath <em>Project Path</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getTargetPlatform <em>Target Platform</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage#getGeneratorConfig()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='namespaceMustHasAtLeastTwoSegments'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot namespaceMustHasAtLeastTwoSegments='namespace.matches(\'[a-z0-9]+([.][a-z0-9]+)+\')'"
 * @generated
 */
public interface GeneratorConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Project Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Name</em>' attribute.
	 * @see #setProjectName(String)
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage#getGeneratorConfig_ProjectName()
	 * @model required="true"
	 * @generated
	 */
	String getProjectName();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getProjectName <em>Project Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Name</em>' attribute.
	 * @see #getProjectName()
	 * @generated
	 */
	void setProjectName(String value);

	/**
	 * Returns the value of the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Namespace</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace</em>' attribute.
	 * @see #setNamespace(String)
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage#getGeneratorConfig_Namespace()
	 * @model required="true"
	 * @generated
	 */
	String getNamespace();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getNamespace <em>Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Namespace</em>' attribute.
	 * @see #getNamespace()
	 * @generated
	 */
	void setNamespace(String value);

	/**
	 * Returns the value of the '<em><b>Project Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Project Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Project Path</em>' attribute.
	 * @see #setProjectPath(String)
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage#getGeneratorConfig_ProjectPath()
	 * @model
	 * @generated
	 */
	String getProjectPath();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getProjectPath <em>Project Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Project Path</em>' attribute.
	 * @see #getProjectPath()
	 * @generated
	 */
	void setProjectPath(String value);

	/**
	 * Returns the value of the '<em><b>Target Platform</b></em>' attribute.
	 * The literals are from the enumeration {@link de.modagile.metamodel.app.generatorconfig.PlatformType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Platform</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Platform</em>' attribute.
	 * @see de.modagile.metamodel.app.generatorconfig.PlatformType
	 * @see #setTargetPlatform(PlatformType)
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage#getGeneratorConfig_TargetPlatform()
	 * @model required="true"
	 * @generated
	 */
	PlatformType getTargetPlatform();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig#getTargetPlatform <em>Target Platform</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Platform</em>' attribute.
	 * @see de.modagile.metamodel.app.generatorconfig.PlatformType
	 * @see #getTargetPlatform()
	 * @generated
	 */
	void setTargetPlatform(PlatformType value);

} // GeneratorConfig
