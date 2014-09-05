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

import de.modagile.metamodel.app.ui.CompositeDisplayElementType;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Display Element Type Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.CompositeDisplayElementTypeRepository#getCompositeDisplayElementTypes <em>Composite Display Element Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.AppPackage#getCompositeDisplayElementTypeRepository()
 * @model
 * @generated
 */
public interface CompositeDisplayElementTypeRepository extends EObject {
	/**
	 * Returns the value of the '<em><b>Composite Display Element Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.ui.CompositeDisplayElementType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite Display Element Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Display Element Types</em>' containment reference list.
	 * @see de.modagile.metamodel.app.AppPackage#getCompositeDisplayElementTypeRepository_CompositeDisplayElementTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<CompositeDisplayElementType> getCompositeDisplayElementTypes();

} // CompositeDisplayElementTypeRepository
