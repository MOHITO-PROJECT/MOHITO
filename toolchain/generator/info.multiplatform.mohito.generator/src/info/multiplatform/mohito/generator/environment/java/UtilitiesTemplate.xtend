/** 
 * Copyright (c) 2012-2014 MOHITO Project
 *
 * Licensed under the EUPL V.1.1
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package info.multiplatform.mohito.generator.environment.java

import com.google.inject.Inject
import info.multiplatform.mohito.modeling.annotation.synchronization.SynchronizationMohitoAnnotationCategory
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.xtext.generator.IFileSystemAccess
import com.google.inject.name.Named
import info.multiplatform.mohito.generator.IStorageGenerator

class UtilitiesTemplate {
	
	/** Utilities for MOHITO-Models and Java generation. */
	@Inject extension JavaUtils javaUtilities
	/** Utilities for MOHITO-Models and annotations. */
	@Inject extension AnnotationUtils annotationUtilities
	/** The template for generation of artifacts for the local storage. Can be <code>null</code> if no local storage is used. */
	@Inject @Named(DomainModelTemplate::NAME_INJECTOR_LOCAL) extension IStorageGenerator localStorageGenerator;
	/** The template for generation of artifacts for the remote storage. Can be <code>null</code> if no remote storage is used. */
	@Inject @Named(DomainModelTemplate::NAME_INJECTOR_REMOTE) extension IStorageGenerator remoteStorageGenerator;
	
	/**Generates the utility artifacts.
	 */
	def generateUtilityArtifacts(IFileSystemAccess fsa, 
			PackageInfo packageInfo, 
			EPackage domainModel) {
		fsa.generateFile(packageInfo.getPackageDir + domainModel.name.toFirstUpper + "DaoManager.java", 
				MohitoJavaFolderConstants::SRC_GEN, generateModelDaoManagerImplementation(packageInfo, domainModel));
		fsa.generateFile(packageInfo.getPackageDir + domainModel.name.toFirstUpper + "StorageManager.java", 
				MohitoJavaFolderConstants::SRC_GEN, generateModelStorageMangerImplementation(packageInfo, domainModel));
		if (localStorageGenerator != null) {
			fsa.generateFile(packageInfo.getPackageDir + "LocalDaoManager.java", 
					MohitoJavaFolderConstants::SRC_GEN, generateLocalDaoManagerImplementation(packageInfo, domainModel));
		}
		if (remoteStorageGenerator != null) {
			fsa.generateFile(packageInfo.getPackageDir + "RemoteDaoManager.java", 
					MohitoJavaFolderConstants::SRC_GEN, generateRemoteDaoManagerImplementation(packageInfo, domainModel));
		}
		if (localStorageGenerator != null && remoteStorageGenerator != null) {
			fsa.generateFile(packageInfo.getPackageDir + "SynchronizingDaoManager.java", 
					MohitoJavaFolderConstants::SRC_GEN, generateSynchronizingDaoManagerImplementation(packageInfo, domainModel));
		} else {
			// nothing to synchronize
		}
	}

	/** Generates the DaoManager implementation for the domain model.
	 * @param packageInfo The package for the implementation.
	 * @param domainModel The domain model.
	 */
	def generateModelDaoManagerImplementation(PackageInfo packageInfo, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»
		«statementImportsNonAbstractDomainEntities(packageInfo.strip, domainModel)»

		/**Model-specific part of DAO-Managers with convenient access to DAOs of MOHITO-Entities of this model.
		«statementGenerated(this.class)»
		 */
		public abstract class «domainModel.name.toFirstUpper»DaoManager extends DaoManager {
		
			/**Creates a new instance.
			 * @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.
			«statementGenerated(this.class)»
			 */
			public «domainModel.name.toFirstUpper»DaoManager(boolean useCache) {
				super(useCache);
			}
			
			«FOR entity : domainModel.getEClassifiers»
			«IF entity instanceof EClass && ! (entity as EClass).abstract»
			/**
			 * @return DAO for {@link «entity.name.toFirstUpper»} MOHITO-Entities.
			 */
			public MohitoEntityDao<«entity.name.toFirstUpper», «getTypeOfDomainEntityIdentifier(entity as EClass)»> 
					get«entity.name.toFirstUpper»Dao() {
				return getEntityDao(«entity.name.toFirstUpper».class); 
			}
			«ENDIF»
			«ENDFOR»
		}
		'''
	}

	/** Generates the StorageManager implementation for the domain model.
	 * @param packageInfo The package for the implementation.
	 * @param domainModel The domain model.
	 */
	def generateModelStorageMangerImplementation(PackageInfo packageInfo, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»
		
		import info.multiplatform.mohito.framework.StorageManager;
		«statementImportsNonAbstractDomainEntities(packageInfo.strip, domainModel)»
		
		/**Entity Manager for «domainModel.name» entities.
		«statementGenerated(this.class)»
		 */
		public class «domainModel.name.toFirstUpper»StorageManager extends StorageManager<«domainModel.name.toFirstUpper»DaoManager>{
			/**Singleton representing this manager.
			 */
			public static final «domainModel.name.toFirstUpper»StorageManager mINSTANCE = new «domainModel.name.toFirstUpper»StorageManager();
			
			/**Default constructor creating local and remote DAO managers.
			 */
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public «domainModel.name.toFirstUpper»StorageManager() {
				super(
					«IF localStorageGenerator == null»null«ELSE»new LocalDaoManager(true)«ENDIF», 
					«IF remoteStorageGenerator == null»null«ELSE»new RemoteDaoManager(«IF localStorageGenerator == null»true«ELSE»false«ENDIF»)«ENDIF», 
					«IF localStorageGenerator == null || remoteStorageGenerator == null»null«ELSE»new SynchronizingDaoManager(false)«ENDIF», 
					«getValueForAnnotationAsBoolean(domainModel,SynchronizationMohitoAnnotationCategory::PREFER_WORKING_LOCAL)», 
					«getValueForAnnotationAsBoolean(domainModel,SynchronizationMohitoAnnotationCategory::RESOLVE_CONFLICTS_AUTOMATICALLY)», 
					«getValueForAnnotationAsBoolean(domainModel,SynchronizationMohitoAnnotationCategory::SERVER_WINS_AUTOMATIC_CONFLICT_RESOLUTION)», 
					«getValueForAnnotationAsBoolean(domainModel,SynchronizationMohitoAnnotationCategory::AUTO_SYNC_ON_REMOTE_AVAILABLE)», 
					«FOR entity : domainModel.getEClassifiers.filter[it instanceof EClass && (it as EClass).abstract==false] SEPARATOR ","»
					«entity.name.toFirstUpper».class
					«ENDFOR»
					
					);
				getLocalStorageManager().setStorageManager((StorageManager)this);
				getRemoteStorageManager().setStorageManager((StorageManager)this);
			}
		
		}
		
		'''
	}			
				
	/** Generates the LocalDaoManager implementation for the domain model.
	 * @param packageInfo The package for the implementation.
	 * @param domainModel The domain model.
	 */
	def generateLocalDaoManagerImplementation(PackageInfo packageInfo, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»
		
		«statementImports(toSet("info.multiplatform.mohito.framework.MohitoEntity",
		"info.multiplatform.mohito.framework.MohitoEntityDao"))»
		«localStorageGenerator.generateManagerImportStatements(packageInfo, domainModel)»
		
		/**DAO Manager for remote access.
		«statementGenerated(this.class)»
		 */
		public class LocalDaoManager extends «domainModel.name.toFirstUpper»DaoManager «localStorageGenerator.generateDaoManagerImplementsStatement»  {
			
			/**Creates a new instance.
			 * @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.
			 */
			public LocalDaoManager(boolean useCache) {
				super(useCache);
				«localStorageGenerator.generateAdditionalManagerEntityDaoConstructorStatements(packageInfo, domainModel)»
			}
		
			@Override
			protected <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> doGetEntityDao(Class<T> mohitoEntityClass) {
				«localStorageGenerator.generateManagerEntityDaoCreation(domainModel)»
			}
			
			«localStorageGenerator.generateAdditionalManagerEntityDaoStatements(packageInfo, domainModel)»
		}
		
		'''
	}
	
	/** Generates the RemoteDaoManager implementation for the domain model.
	 * @param packageInfo The package for the implementation.
	 * @param domainModel The domain model.
	 */
	def generateRemoteDaoManagerImplementation(PackageInfo packageInfo, EPackage domainModel){
		'''
		«statementPackage(packageInfo)»

		«statementImports(toSet("info.multiplatform.mohito.framework.MohitoEntity",
		"info.multiplatform.mohito.framework.MohitoEntityDao"))»
		«remoteStorageGenerator.generateManagerImportStatements(packageInfo,domainModel)»
		
		/**DAO Manager for remote access.
		«statementGenerated(this.class)»
		 */
		public class RemoteDaoManager extends «domainModel.name.toFirstUpper»DaoManager «remoteStorageGenerator.generateDaoManagerImplementsStatement» {
			
			/**Creates a new instance.
			 * @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.
			 */
			public RemoteDaoManager(boolean useCache) {
				super(useCache);
				«remoteStorageGenerator.generateAdditionalManagerEntityDaoConstructorStatements(packageInfo, domainModel)»
			}
		
			@Override
			protected <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> doGetEntityDao(Class<T> mohitoEntityClass) {
				«remoteStorageGenerator.generateManagerEntityDaoCreation(domainModel)»
			}
			
			«remoteStorageGenerator.generateAdditionalManagerEntityDaoStatements(packageInfo, domainModel)»
		}
		
		'''
	}
	
	/** Generates the SynchonizingDaoManager implementation for the domain model.
	 * @param packageInfo The package for the implementation.
	 * @param domainModel The domain model.
	 */
	def generateSynchronizingDaoManagerImplementation(PackageInfo packageInfo, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»

		«statementImports(toSet("info.multiplatform.mohito.framework.MohitoEntity", 
			"info.multiplatform.mohito.framework.MohitoEntityDao", 
			"info.multiplatform.mohito.framework.synchronization.MohitoEntityDaoSynchronizingImpl"))»
	
		/**DAO manager handling the synchronization between local and remote storages.
		«statementGenerated(this.class)»
		 */
		public class SynchronizingDaoManager extends «domainModel.name.toFirstUpper»DaoManager {
		
			/**Creates a new instance.
			 * @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.
			 */
			public SynchronizingDaoManager(boolean useCache) {
				super(useCache);
			}
		
			@Override
			protected <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> doGetEntityDao(
					Class<T> mohitoEntityClass) {
				return new MohitoEntityDaoSynchronizingImpl<T, PK>(mohitoEntityClass, this);
			}
		}
		
		'''
	}
}
	
	