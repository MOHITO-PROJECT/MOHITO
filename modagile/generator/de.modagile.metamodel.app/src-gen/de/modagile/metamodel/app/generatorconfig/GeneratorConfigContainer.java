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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generator Config Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer#getGeneratorConfigs <em>Generator Configs</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage#getGeneratorConfigContainer()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='OnlyOneGeneratorConfigPerPlatform'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot OnlyOneGeneratorConfigPerPlatform='generatorConfigs->isUnique(targetPlatform)'"
 * @generated
 */
public interface GeneratorConfigContainer extends EObject {

	/**
	 * Returns the value of the '<em><b>Generator Configs</b></em>' containment reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.generatorconfig.GeneratorConfig}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generator Configs</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generator Configs</em>' containment reference list.
	 * @see de.modagile.metamodel.app.generatorconfig.GeneratorconfigPackage#getGeneratorConfigContainer_GeneratorConfigs()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<GeneratorConfig> getGeneratorConfigs();
} // GeneratorConfigContainer
