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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.modagile.metamodel.app.AppPackage;
import de.modagile.metamodel.app.CompositeDisplayElementTypeRepository;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.event.EventContainer;
import de.modagile.metamodel.app.event.AppEventContainer;
import de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer;
import de.modagile.metamodel.app.ui.StoryBoard;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mobile App</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.impl.MobileAppImpl#getStoryBoard <em>Story Board</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.impl.MobileAppImpl#getBindingRepository <em>Binding Repository</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.impl.MobileAppImpl#getAppDatabaseVersion <em>App Database Version</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.impl.MobileAppImpl#getAppVersion <em>App Version</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.impl.MobileAppImpl#getCompositeDisplayElementTypeRepository <em>Composite Display Element Type Repository</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.impl.MobileAppImpl#getDomainPackage <em>Domain Package</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.impl.MobileAppImpl#getGeneratorConfigContainer <em>Generator Config Container</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.impl.MobileAppImpl#getEventContainer <em>Event Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MobileAppImpl extends EntityImpl implements MobileApp {
	/**
	 * The cached value of the '{@link #getStoryBoard() <em>Story Board</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoryBoard()
	 * @generated
	 * @ordered
	 */
	protected StoryBoard storyBoard;

	/**
	 * The cached value of the '{@link #getBindingRepository() <em>Binding Repository</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBindingRepository()
	 * @generated
	 * @ordered
	 */
	protected BindingRepository bindingRepository;

	/**
	 * The default value of the '{@link #getAppDatabaseVersion() <em>App Database Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppDatabaseVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int APP_DATABASE_VERSION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAppDatabaseVersion() <em>App Database Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppDatabaseVersion()
	 * @generated
	 * @ordered
	 */
	protected int appDatabaseVersion = APP_DATABASE_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAppVersion() <em>App Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String APP_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAppVersion() <em>App Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAppVersion()
	 * @generated
	 * @ordered
	 */
	protected String appVersion = APP_VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCompositeDisplayElementTypeRepository() <em>Composite Display Element Type Repository</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositeDisplayElementTypeRepository()
	 * @generated
	 * @ordered
	 */
	protected CompositeDisplayElementTypeRepository compositeDisplayElementTypeRepository;

	/**
	 * The cached value of the '{@link #getDomainPackage() <em>Domain Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomainPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage domainPackage;

	/**
	 * The cached value of the '{@link #getGeneratorConfigContainer() <em>Generator Config Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGeneratorConfigContainer()
	 * @generated
	 * @ordered
	 */
	protected GeneratorConfigContainer generatorConfigContainer;

	/**
	 * The cached value of the '{@link #getEventContainer() <em>Event Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventContainer()
	 * @generated
	 * @ordered
	 */
	protected EventContainer eventContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MobileAppImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.MOBILE_APP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoryBoard getStoryBoard() {
		return storyBoard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStoryBoard(StoryBoard newStoryBoard, NotificationChain msgs) {
		StoryBoard oldStoryBoard = storyBoard;
		storyBoard = newStoryBoard;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__STORY_BOARD, oldStoryBoard, newStoryBoard);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStoryBoard(StoryBoard newStoryBoard) {
		if (newStoryBoard != storyBoard) {
			NotificationChain msgs = null;
			if (storyBoard != null)
				msgs = ((InternalEObject)storyBoard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__STORY_BOARD, null, msgs);
			if (newStoryBoard != null)
				msgs = ((InternalEObject)newStoryBoard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__STORY_BOARD, null, msgs);
			msgs = basicSetStoryBoard(newStoryBoard, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__STORY_BOARD, newStoryBoard, newStoryBoard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingRepository getBindingRepository() {
		return bindingRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBindingRepository(BindingRepository newBindingRepository, NotificationChain msgs) {
		BindingRepository oldBindingRepository = bindingRepository;
		bindingRepository = newBindingRepository;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__BINDING_REPOSITORY, oldBindingRepository, newBindingRepository);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBindingRepository(BindingRepository newBindingRepository) {
		if (newBindingRepository != bindingRepository) {
			NotificationChain msgs = null;
			if (bindingRepository != null)
				msgs = ((InternalEObject)bindingRepository).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__BINDING_REPOSITORY, null, msgs);
			if (newBindingRepository != null)
				msgs = ((InternalEObject)newBindingRepository).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__BINDING_REPOSITORY, null, msgs);
			msgs = basicSetBindingRepository(newBindingRepository, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__BINDING_REPOSITORY, newBindingRepository, newBindingRepository));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAppDatabaseVersion() {
		return appDatabaseVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAppDatabaseVersion(int newAppDatabaseVersion) {
		int oldAppDatabaseVersion = appDatabaseVersion;
		appDatabaseVersion = newAppDatabaseVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__APP_DATABASE_VERSION, oldAppDatabaseVersion, appDatabaseVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAppVersion(String newAppVersion) {
		String oldAppVersion = appVersion;
		appVersion = newAppVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__APP_VERSION, oldAppVersion, appVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeDisplayElementTypeRepository getCompositeDisplayElementTypeRepository() {
		return compositeDisplayElementTypeRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompositeDisplayElementTypeRepository(CompositeDisplayElementTypeRepository newCompositeDisplayElementTypeRepository, NotificationChain msgs) {
		CompositeDisplayElementTypeRepository oldCompositeDisplayElementTypeRepository = compositeDisplayElementTypeRepository;
		compositeDisplayElementTypeRepository = newCompositeDisplayElementTypeRepository;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY, oldCompositeDisplayElementTypeRepository, newCompositeDisplayElementTypeRepository);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompositeDisplayElementTypeRepository(CompositeDisplayElementTypeRepository newCompositeDisplayElementTypeRepository) {
		if (newCompositeDisplayElementTypeRepository != compositeDisplayElementTypeRepository) {
			NotificationChain msgs = null;
			if (compositeDisplayElementTypeRepository != null)
				msgs = ((InternalEObject)compositeDisplayElementTypeRepository).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY, null, msgs);
			if (newCompositeDisplayElementTypeRepository != null)
				msgs = ((InternalEObject)newCompositeDisplayElementTypeRepository).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY, null, msgs);
			msgs = basicSetCompositeDisplayElementTypeRepository(newCompositeDisplayElementTypeRepository, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY, newCompositeDisplayElementTypeRepository, newCompositeDisplayElementTypeRepository));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getDomainPackage() {
		if (domainPackage != null && domainPackage.eIsProxy()) {
			InternalEObject oldDomainPackage = (InternalEObject)domainPackage;
			domainPackage = (EPackage)eResolveProxy(oldDomainPackage);
			if (domainPackage != oldDomainPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AppPackage.MOBILE_APP__DOMAIN_PACKAGE, oldDomainPackage, domainPackage));
			}
		}
		return domainPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetDomainPackage() {
		return domainPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainPackage(EPackage newDomainPackage) {
		EPackage oldDomainPackage = domainPackage;
		domainPackage = newDomainPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__DOMAIN_PACKAGE, oldDomainPackage, domainPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneratorConfigContainer getGeneratorConfigContainer() {
		return generatorConfigContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGeneratorConfigContainer(GeneratorConfigContainer newGeneratorConfigContainer, NotificationChain msgs) {
		GeneratorConfigContainer oldGeneratorConfigContainer = generatorConfigContainer;
		generatorConfigContainer = newGeneratorConfigContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER, oldGeneratorConfigContainer, newGeneratorConfigContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGeneratorConfigContainer(GeneratorConfigContainer newGeneratorConfigContainer) {
		if (newGeneratorConfigContainer != generatorConfigContainer) {
			NotificationChain msgs = null;
			if (generatorConfigContainer != null)
				msgs = ((InternalEObject)generatorConfigContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER, null, msgs);
			if (newGeneratorConfigContainer != null)
				msgs = ((InternalEObject)newGeneratorConfigContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER, null, msgs);
			msgs = basicSetGeneratorConfigContainer(newGeneratorConfigContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER, newGeneratorConfigContainer, newGeneratorConfigContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventContainer getEventContainer() {
		return eventContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEventContainer(EventContainer newEventContainer, NotificationChain msgs) {
		EventContainer oldEventContainer = eventContainer;
		eventContainer = newEventContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__EVENT_CONTAINER, oldEventContainer, newEventContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventContainer(EventContainer newEventContainer) {
		if (newEventContainer != eventContainer) {
			NotificationChain msgs = null;
			if (eventContainer != null)
				msgs = ((InternalEObject)eventContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__EVENT_CONTAINER, null, msgs);
			if (newEventContainer != null)
				msgs = ((InternalEObject)newEventContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AppPackage.MOBILE_APP__EVENT_CONTAINER, null, msgs);
			msgs = basicSetEventContainer(newEventContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AppPackage.MOBILE_APP__EVENT_CONTAINER, newEventContainer, newEventContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.MOBILE_APP__STORY_BOARD:
				return basicSetStoryBoard(null, msgs);
			case AppPackage.MOBILE_APP__BINDING_REPOSITORY:
				return basicSetBindingRepository(null, msgs);
			case AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY:
				return basicSetCompositeDisplayElementTypeRepository(null, msgs);
			case AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER:
				return basicSetGeneratorConfigContainer(null, msgs);
			case AppPackage.MOBILE_APP__EVENT_CONTAINER:
				return basicSetEventContainer(null, msgs);
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
			case AppPackage.MOBILE_APP__STORY_BOARD:
				return getStoryBoard();
			case AppPackage.MOBILE_APP__BINDING_REPOSITORY:
				return getBindingRepository();
			case AppPackage.MOBILE_APP__APP_DATABASE_VERSION:
				return getAppDatabaseVersion();
			case AppPackage.MOBILE_APP__APP_VERSION:
				return getAppVersion();
			case AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY:
				return getCompositeDisplayElementTypeRepository();
			case AppPackage.MOBILE_APP__DOMAIN_PACKAGE:
				if (resolve) return getDomainPackage();
				return basicGetDomainPackage();
			case AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER:
				return getGeneratorConfigContainer();
			case AppPackage.MOBILE_APP__EVENT_CONTAINER:
				return getEventContainer();
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
			case AppPackage.MOBILE_APP__STORY_BOARD:
				setStoryBoard((StoryBoard)newValue);
				return;
			case AppPackage.MOBILE_APP__BINDING_REPOSITORY:
				setBindingRepository((BindingRepository)newValue);
				return;
			case AppPackage.MOBILE_APP__APP_DATABASE_VERSION:
				setAppDatabaseVersion((Integer)newValue);
				return;
			case AppPackage.MOBILE_APP__APP_VERSION:
				setAppVersion((String)newValue);
				return;
			case AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY:
				setCompositeDisplayElementTypeRepository((CompositeDisplayElementTypeRepository)newValue);
				return;
			case AppPackage.MOBILE_APP__DOMAIN_PACKAGE:
				setDomainPackage((EPackage)newValue);
				return;
			case AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER:
				setGeneratorConfigContainer((GeneratorConfigContainer)newValue);
				return;
			case AppPackage.MOBILE_APP__EVENT_CONTAINER:
				setEventContainer((EventContainer)newValue);
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
			case AppPackage.MOBILE_APP__STORY_BOARD:
				setStoryBoard((StoryBoard)null);
				return;
			case AppPackage.MOBILE_APP__BINDING_REPOSITORY:
				setBindingRepository((BindingRepository)null);
				return;
			case AppPackage.MOBILE_APP__APP_DATABASE_VERSION:
				setAppDatabaseVersion(APP_DATABASE_VERSION_EDEFAULT);
				return;
			case AppPackage.MOBILE_APP__APP_VERSION:
				setAppVersion(APP_VERSION_EDEFAULT);
				return;
			case AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY:
				setCompositeDisplayElementTypeRepository((CompositeDisplayElementTypeRepository)null);
				return;
			case AppPackage.MOBILE_APP__DOMAIN_PACKAGE:
				setDomainPackage((EPackage)null);
				return;
			case AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER:
				setGeneratorConfigContainer((GeneratorConfigContainer)null);
				return;
			case AppPackage.MOBILE_APP__EVENT_CONTAINER:
				setEventContainer((EventContainer)null);
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
			case AppPackage.MOBILE_APP__STORY_BOARD:
				return storyBoard != null;
			case AppPackage.MOBILE_APP__BINDING_REPOSITORY:
				return bindingRepository != null;
			case AppPackage.MOBILE_APP__APP_DATABASE_VERSION:
				return appDatabaseVersion != APP_DATABASE_VERSION_EDEFAULT;
			case AppPackage.MOBILE_APP__APP_VERSION:
				return APP_VERSION_EDEFAULT == null ? appVersion != null : !APP_VERSION_EDEFAULT.equals(appVersion);
			case AppPackage.MOBILE_APP__COMPOSITE_DISPLAY_ELEMENT_TYPE_REPOSITORY:
				return compositeDisplayElementTypeRepository != null;
			case AppPackage.MOBILE_APP__DOMAIN_PACKAGE:
				return domainPackage != null;
			case AppPackage.MOBILE_APP__GENERATOR_CONFIG_CONTAINER:
				return generatorConfigContainer != null;
			case AppPackage.MOBILE_APP__EVENT_CONTAINER:
				return eventContainer != null;
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
		result.append(" (appDatabaseVersion: ");
		result.append(appDatabaseVersion);
		result.append(", appVersion: ");
		result.append(appVersion);
		result.append(')');
		return result.toString();
	}

} //MobileAppImpl
