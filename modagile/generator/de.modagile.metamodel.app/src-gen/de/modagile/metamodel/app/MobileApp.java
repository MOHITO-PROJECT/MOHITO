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

import org.eclipse.emf.ecore.EPackage;

import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.event.EventContainer;
import de.modagile.metamodel.app.event.AppEventContainer;
import de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer;
import de.modagile.metamodel.app.ui.StoryBoard;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mobile App</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.MobileApp#getStoryBoard <em>Story Board</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.MobileApp#getBindingRepository <em>Binding Repository</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.MobileApp#getAppDatabaseVersion <em>App Database Version</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.MobileApp#getAppVersion <em>App Version</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.MobileApp#getCompositeDisplayElementTypeRepository <em>Composite Display Element Type Repository</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.MobileApp#getDomainPackage <em>Domain Package</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.MobileApp#getGeneratorConfigContainer <em>Generator Config Container</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.MobileApp#getEventContainer <em>Event Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.AppPackage#getMobileApp()
 * @model
 * @generated
 */
public interface MobileApp extends Entity {
	/**
	 * Returns the value of the '<em><b>Story Board</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Story Board</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Story Board</em>' containment reference.
	 * @see #setStoryBoard(StoryBoard)
	 * @see de.modagile.metamodel.app.AppPackage#getMobileApp_StoryBoard()
	 * @model containment="true"
	 * @generated
	 */
	StoryBoard getStoryBoard();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.MobileApp#getStoryBoard <em>Story Board</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Story Board</em>' containment reference.
	 * @see #getStoryBoard()
	 * @generated
	 */
	void setStoryBoard(StoryBoard value);

	/**
	 * Returns the value of the '<em><b>Binding Repository</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding Repository</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding Repository</em>' containment reference.
	 * @see #setBindingRepository(BindingRepository)
	 * @see de.modagile.metamodel.app.AppPackage#getMobileApp_BindingRepository()
	 * @model containment="true"
	 * @generated
	 */
	BindingRepository getBindingRepository();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.MobileApp#getBindingRepository <em>Binding Repository</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding Repository</em>' containment reference.
	 * @see #getBindingRepository()
	 * @generated
	 */
	void setBindingRepository(BindingRepository value);

	/**
	 * Returns the value of the '<em><b>App Database Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>App Database Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>App Database Version</em>' attribute.
	 * @see #setAppDatabaseVersion(int)
	 * @see de.modagile.metamodel.app.AppPackage#getMobileApp_AppDatabaseVersion()
	 * @model
	 * @generated
	 */
	int getAppDatabaseVersion();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.MobileApp#getAppDatabaseVersion <em>App Database Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>App Database Version</em>' attribute.
	 * @see #getAppDatabaseVersion()
	 * @generated
	 */
	void setAppDatabaseVersion(int value);

	/**
	 * Returns the value of the '<em><b>App Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>App Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>App Version</em>' attribute.
	 * @see #setAppVersion(String)
	 * @see de.modagile.metamodel.app.AppPackage#getMobileApp_AppVersion()
	 * @model
	 * @generated
	 */
	String getAppVersion();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.MobileApp#getAppVersion <em>App Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>App Version</em>' attribute.
	 * @see #getAppVersion()
	 * @generated
	 */
	void setAppVersion(String value);

	/**
	 * Returns the value of the '<em><b>Composite Display Element Type Repository</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Composite Display Element Type Repository</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Composite Display Element Type Repository</em>' containment reference.
	 * @see #setCompositeDisplayElementTypeRepository(CompositeDisplayElementTypeRepository)
	 * @see de.modagile.metamodel.app.AppPackage#getMobileApp_CompositeDisplayElementTypeRepository()
	 * @model containment="true"
	 * @generated
	 */
	CompositeDisplayElementTypeRepository getCompositeDisplayElementTypeRepository();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.MobileApp#getCompositeDisplayElementTypeRepository <em>Composite Display Element Type Repository</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Composite Display Element Type Repository</em>' containment reference.
	 * @see #getCompositeDisplayElementTypeRepository()
	 * @generated
	 */
	void setCompositeDisplayElementTypeRepository(CompositeDisplayElementTypeRepository value);

	/**
	 * Returns the value of the '<em><b>Domain Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Package</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Package</em>' reference.
	 * @see #setDomainPackage(EPackage)
	 * @see de.modagile.metamodel.app.AppPackage#getMobileApp_DomainPackage()
	 * @model required="true"
	 * @generated
	 */
	EPackage getDomainPackage();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.MobileApp#getDomainPackage <em>Domain Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Package</em>' reference.
	 * @see #getDomainPackage()
	 * @generated
	 */
	void setDomainPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>Generator Config Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generator Config Container</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generator Config Container</em>' containment reference.
	 * @see #setGeneratorConfigContainer(GeneratorConfigContainer)
	 * @see de.modagile.metamodel.app.AppPackage#getMobileApp_GeneratorConfigContainer()
	 * @model containment="true" required="true"
	 * @generated
	 */
	GeneratorConfigContainer getGeneratorConfigContainer();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.MobileApp#getGeneratorConfigContainer <em>Generator Config Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generator Config Container</em>' containment reference.
	 * @see #getGeneratorConfigContainer()
	 * @generated
	 */
	void setGeneratorConfigContainer(GeneratorConfigContainer value);

	/**
	 * Returns the value of the '<em><b>Event Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Container</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Container</em>' containment reference.
	 * @see #setEventContainer(EventContainer)
	 * @see de.modagile.metamodel.app.AppPackage#getMobileApp_EventContainer()
	 * @model containment="true"
	 * @generated
	 */
	EventContainer getEventContainer();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.MobileApp#getEventContainer <em>Event Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Container</em>' containment reference.
	 * @see #getEventContainer()
	 * @generated
	 */
	void setEventContainer(EventContainer value);

} // MobileApp
