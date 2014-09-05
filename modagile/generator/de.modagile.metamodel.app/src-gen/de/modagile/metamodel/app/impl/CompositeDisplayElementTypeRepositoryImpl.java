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

import de.modagile.metamodel.app.AppPackage;
import de.modagile.metamodel.app.CompositeDisplayElementTypeRepository;

import de.modagile.metamodel.app.ui.CompositeDisplayElementType;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Display Element Type Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.impl.CompositeDisplayElementTypeRepositoryImpl#getCompositeDisplayElementTypes <em>Composite Display Element Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeDisplayElementTypeRepositoryImpl extends EObjectImpl implements CompositeDisplayElementTypeRepository {
	/**
	 * The cached value of the '{@link #getCompositeDisplayElementTypes() <em>Composite Display Element Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeDisplayElementTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<CompositeDisplayElementType> compositeDisplayElementTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeDisplayElementTypeRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompositeDisplayElementType> getCompositeDisplayElementTypes() {
		if (compositeDisplayElementTypes == null) {
			compositeDisplayElementTypes = new EObjectContainmentEList<CompositeDisplayElementType>(CompositeDisplayElementType.class, this, AppPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES);
		}
		return compositeDisplayElementTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES:
				return ((InternalEList<?>)getCompositeDisplayElementTypes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AppPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES:
				return getCompositeDisplayElementTypes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AppPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES:
				getCompositeDisplayElementTypes().clear();
				getCompositeDisplayElementTypes().addAll((Collection<? extends CompositeDisplayElementType>)newValue);
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
			case AppPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES:
				getCompositeDisplayElementTypes().clear();
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
			case AppPackage.COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY__COMPOSITE_DISPLAY_ELEMENT_TYPES:
				return compositeDisplayElementTypes != null && !compositeDisplayElementTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CompositeDisplayElementTypeRepositoryImpl
