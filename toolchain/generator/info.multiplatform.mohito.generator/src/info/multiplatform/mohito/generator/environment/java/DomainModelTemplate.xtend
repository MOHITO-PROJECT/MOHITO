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
import com.google.inject.name.Named
import info.multiplatform.generator.java.templates.JavaFolderConstants
import java.util.HashSet
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.generator.IFileSystemAccess
import info.multiplatform.mohito.generator.IStorageGenerator
import org.eclipse.emf.ecore.EEnum
import org.eclipse.emf.ecore.EEnumLiteral
import org.eclipse.emf.ecore.EAttribute

/**
 * Generator template for common java domain model classes.
 * 
 * The template is self contained and can be executed as it is.
 * In addition, it provides extension points by overloading the template
 * methods explicitly declared with protected access modifiers.
 * 
 * */
class DomainModelTemplate {
	/** Textual identifier for the injection of the local storage generator. Required for target selection within dependency injection. @see http://code.google.com/p/google-guice/wiki/BindingAnnotations*/
	val public static final String NAME_INJECTOR_LOCAL = "Local";
	/** Textual identifier for the injection of the remote storage generator. Required for target selection within dependency injection. @see http://code.google.com/p/google-guice/wiki/BindingAnnotations*/
	val public static final String NAME_INJECTOR_REMOTE = "Remote";

	/** Utilities for generating Java code. */
	@Inject extension JavaUtils javaUtilities
	/** The template for generation of artifacts for the local storage. Can be <code>null</code> if no local storage is used. */
	@Inject @Named(NAME_INJECTOR_LOCAL) extension IStorageGenerator localStorageGenerator;
	/** The template for generation of artifacts for the remote storage. Can be <code>null</code> if no remote storage is used. */
	@Inject @Named(NAME_INJECTOR_REMOTE) extension IStorageGenerator remoteStorageGenerator;
	
	/**Generates the artifacts for the provided domain entity.
	 * @param fsa Access to the files system for generating files.
	 * @param domainModel The domain model.
	 * @param packageInfo Information on the target package for generation.
	 */
	def generateDomainEntityArtifacts(IFileSystemAccess fsa, EPackage domainModel, PackageInfo packageInfo) {
		domainModel.name.ensureJavaCompatibleName
		localStorageGenerator.generateAdditionalDomainModelArtifacts(fsa, packageInfo.append(MohitoJavaFolderConstants::MODEL_LOCAL), domainModel);
		remoteStorageGenerator.generateAdditionalDomainModelArtifacts(fsa, packageInfo.append(MohitoJavaFolderConstants::MODEL_REMOTE), domainModel);
		fsa.generateMohitoEntityClassFile(domainModel, packageInfo)
		domainModel.EClassifiers.forEach [ entity |
				entity.name.ensureJavaCompatibleName
				switch (entity) {
					EEnum: {
								fsa.generateEnumEntityFile(packageInfo,entity,domainModel);
							}
					EClass: {
								fsa.generateDomainEntityFile(packageInfo, entity, domainModel)
								localStorageGenerator.generateAdditionalDomainEntityArtifacts(fsa, packageInfo.append(MohitoJavaFolderConstants::MODEL_LOCAL), entity, domainModel);
								remoteStorageGenerator.generateAdditionalDomainEntityArtifacts(fsa, packageInfo.append(MohitoJavaFolderConstants::MODEL_REMOTE), entity, domainModel);
							}
				}
			]
	}
	
	 // DomainEntity 
	 
	/**Generates the file for the enumeration implementing the domain entity.
	 * @param fsa Access to the files system for generating files.
	 * @param packageInfo Information on the target package for generation.
	 * @param enumEntity The domain entity.
	 * @param domainModel The domain model.
	 * @see #generateEnumEntityImplementation
	 */
	def private generateEnumEntityFile(IFileSystemAccess fsa, PackageInfo packageInfo, EEnum enumEntity, EPackage domainModel) {
		fsa.generateFile(packageInfo.getPackageDir + enumEntity.name.toFirstUpper + ".java", 
				JavaFolderConstants::SRC_GEN, 
		generateEnumEntityImplementation(enumEntity,packageInfo,domainModel));
	}
	
	/**Generates the implementation of the enumeration representing a domain entity.
	 * @param enumEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 * @param domainModel The domain model.
	 */
	def private generateEnumEntityImplementation(EEnum enumEntity, PackageInfo packageInfo, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»
		
		/**
		 * Model entity «enumEntity.name.toFirstUpper» of «domainModel.name.toFirstUpper».
		 */
		public enum «enumEntity.name.toFirstUpper» {
			«FOR EEnumLiteral enumLiteral : enumEntity.ELiterals SEPARATOR ", "»
			«enumLiteral.name.toUpperCase»(«enumLiteral.value»)
			«ENDFOR»;
					
			/**The value of the literal. */
			private int value;
			
			/**
			 * Create a new literal of the enumeration.
			 * @param value The value for this literal.
			 */
			private «enumEntity.name.toFirstUpper»(int value) {
				this.value = value;
			}
			
			/**
			 * Gets the value for a literal.
			 * @return The value.
			 */
			public int getValue(){
				return value;
			}
		}
		'''
	}
	
	/**Generates the file for the class implementing the domain entity.
	 * @param fsa Access to the files system for generating files.
	 * @param domainModel The domain model.
	 * @param domainEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 * @see #generateDomainEntityImplementation
	 */
	def private generateDomainEntityFile(IFileSystemAccess fsa, PackageInfo packageInfo, EClass domainEntity, EPackage domainModel){
		fsa.generateFile(packageInfo.getPackageDir + domainEntity.name.toFirstUpper + ".java", 
				JavaFolderConstants::SRC_GEN, 
				generateDomainEntityImplementation(domainEntity, packageInfo, domainModel));

	}

	/**Generates the implementation of the class representing a domain entity.
	 * @param domainModel The domain model.
	 * @param domainEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 * @see #generateDomainEntityFile
	 */
	def private generateDomainEntityImplementation(EClass domainEntity, PackageInfo packageInfo, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»
		
		«statementImports(calculateImports(packageInfo, domainEntity, domainModel))»
		«localStorageGenerator.generateDomainEntityImportStatements(packageInfo, domainEntity, domainModel)»
		«remoteStorageGenerator.generateDomainEntityImportStatements(packageInfo, domainEntity, domainModel)»
		
		/**Model entity «domainEntity.name.toFirstUpper» of «domainModel.name.toFirstUpper».
		«statementGenerated(this.class)»
		 */
		«localStorageGenerator.generateDomainEntityClassAnnotationStatement(packageInfo, domainEntity, domainModel)»
		«remoteStorageGenerator.generateDomainEntityClassAnnotationStatement(packageInfo, domainEntity, domainModel)»
		public «IF domainEntity.abstract» abstract «ENDIF»class «domainEntity.name.toFirstUpper»
				extends «IF domainEntity.ESuperTypes.empty»«domainModel.name.toFirstUpper»MohitoEntity<«getTypeOfDomainEntityIdentifier(domainEntity)»>
						«ELSE»«domainEntity.ESuperTypes.findFirst[true].name.toFirstUpper»«ENDIF» {
			// Attributes
			«generateDomainEntityAttributesForAttributes(domainEntity, packageInfo, domainModel)»
			// References
			«generateDomainEntityAttributesForReferences(domainEntity, packageInfo, domainModel)»
			// Constructors
			«generateDomainEntityConstructors(domainEntity, packageInfo, domainModel)»
			// doCheckProxyAndResolveAssignment and doCheckProxyAndResolveGetReferenceEntity
			«generateDomainEntityTransparentProxyResolution(domainEntity, packageInfo, domainModel)»
			// getter and setter
			«generateDomainEntityGetterAndSetter(domainEntity, packageInfo, domainModel)»
			// domainContentEquals
			«generateDomainEntityDomainEquals(domainEntity, packageInfo, domainModel)»
		}
		'''
	}
	
	/**Generates the attributes for a domain entity.
	 * @param domainModel The domain model.
	 * @param domainEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 */
	def private generateDomainEntityAttributesForAttributes(EClass domainEntity, PackageInfo packageInfo, EPackage domainModel) {
		'''
		«IF domainEntity.ESuperTypes.size == 0»
		«IF getAttributeWithAnnotationDatabaseId(domainEntity) == null»
		/** Identifier of this MOHITO-Entity. */
		protected «getTypeOfDomainEntityIdentifier(domainEntity)» id;
		«ENDIF»
		«localStorageGenerator.generateAdditionalDomainEntityStatements(domainEntity)»
		«remoteStorageGenerator.generateAdditionalDomainEntityStatements(domainEntity)»
		«ENDIF»
		«FOR attribute : domainEntity.EAttributes»
		«localStorageGenerator.generateDomainEntityStructuralFeatureAnnotationStatement(packageInfo, attribute, domainModel)»
		«remoteStorageGenerator.generateDomainEntityStructuralFeatureAnnotationStatement(packageInfo, attribute, domainModel)»
		/** Attribute «attribute.name.toFirstLower». 
				«IF getAttributeWithAnnotationDatabaseId(domainEntity) == attribute»Identifier of this MOHITO-Entity. 
				«ELSE»
					«IF attribute.name.toLowerCase.equals("id")»«ensureAttibuteNameIsNotId(attribute)»
					«ENDIF»
				«ENDIF»*/
		«IF attribute.upperBound > 1 || attribute.upperBound == -1»
		protected final MohitoList<«getType(attribute)»> «attribute.name.toFirstLower»;
		«ELSE»
		protected «getType(attribute)» «attribute.name.toFirstLower»;
		«ENDIF»
		«ENDFOR»
		'''
	}
	
	/**Ensures that the name of an attribute is not 'id' in order to prevent clashes with the name convention of MOHITO-Entities.
	 * @throws IllegalArgumentException  
	 */
	def private ensureAttibuteNameIsNotId(EAttribute attribute) {
		throw new IllegalArgumentException("The domain entity " + attribute.EContainingClass.name + " must not have an attribute with name 'id', which is not used as identifier in the database. This is an convention for MOHITO-Entities.");
	}
	
	/**Generates the references for a domain entity.
	 * @param domainModel The domain model.
	 * @param domainEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 */
	def private generateDomainEntityAttributesForReferences(EClass domainEntity, PackageInfo packageInfo, EPackage domainModel) {
		'''
		«FOR reference : domainEntity.EReferences»
		«localStorageGenerator.generateDomainEntityStructuralFeatureAnnotationStatement(packageInfo, reference, domainModel)»
		«remoteStorageGenerator.generateDomainEntityStructuralFeatureAnnotationStatement(packageInfo, reference, domainModel)»
		/** «IF reference.containment»Containment reference.«ELSE»Reference«ENDIF» «reference.name.toFirstLower». */
		«IF reference.upperBound > 1 || reference.upperBound == -1»
		protected final MohitoList<«getType(reference)»> «reference.name.toFirstLower»;
		«ELSE»
		protected «getType(reference)» «reference.name.toFirstLower»;
		«ENDIF»
		«ENDFOR»
		// Incoming unnamed references (reverse of containment references without opposite). «««Cannot be lists because of containment relation.
		«FOR reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null]»
		/** Inverse «reference.name.toFirstUpper» */
		protected «reference.EContainingClass.name.toFirstUpper» mInverse«reference.name.toFirstUpper»;
		«ENDFOR»
		'''
	}

	/**Generates the constructors for a domain entity.
	 * @param domainModel The domain model.
	 * @param domainEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 */
	def private generateDomainEntityConstructors(EClass domainEntity, PackageInfo packageInfo, EPackage domainModel) {
		'''
		/**Default constructor.
		 */
		public «domainEntity.name.toFirstUpper» () {
			«IF domainEntity.ESuperTypes.size == 0»
				«IF getAttributeWithAnnotationDatabaseId(domainEntity) != null»
				this.«getAttributeWithAnnotationDatabaseId(domainEntity).name.toFirstLower»«ELSE»id«ENDIF» = generateUUID();
			«ELSE»
				super();
			«ENDIF»
			«FOR att : domainEntity.EAttributes»
				«IF att.upperBound > 1 || att.upperBound == -1»this.«att.name.toFirstLower» = new MohitoList<«getType(att)»>(«getType(att)».class, this, null);«ENDIF»
			«ENDFOR»
			«FOR ref : domainEntity.EReferences»
				«IF ref.upperBound > 1 || ref.upperBound == -1»this.«ref.name.toFirstLower» = new MohitoList<«getType(ref)»>(«getType(ref)».class, this,  
						«IF ref.EOpposite != null»"«ref.EOpposite.name.toFirstLower»"«ELSE»null«ENDIF»);
				«ENDIF»
			«ENDFOR»
			««« mInverse* cannot be lists and do not need to be handled here.
		}
		
		/**Proxy constructor.
		 * Marks that this instance only contains a valid id but the values for the entity with that id mus still be retrieved. Allows deferred loading.
		 * @param The id of the entity.
		 */
		public «domainEntity.name.toFirstUpper» (
				«IF getAttributeWithAnnotationDatabaseId(domainEntity) != null»«getType(getAttributeWithAnnotationDatabaseId(domainEntity))»«ELSE»String«ENDIF» id) {
			«IF domainEntity.ESuperTypes.size == 0»
				«IF getAttributeWithAnnotationDatabaseId(domainEntity) == null»
				this.id = id;
				«ELSE»
				this.«getAttributeWithAnnotationDatabaseId(domainEntity).name.toFirstLower» = id;
				«ENDIF»
				this.mIsProxy = true;
			«ELSE»
				super(id); 
			«ENDIF»
			«FOR att : domainEntity.EAttributes»
				«IF att.upperBound > 1 || att.upperBound == -1»this.«att.name.toFirstLower» = new MohitoList<«getType(att)»>(«getType(att)».class, this, null);«ENDIF»
			«ENDFOR»
			«FOR ref : domainEntity.EReferences»
				«IF ref.upperBound > 1 || ref.upperBound == -1»this.«ref.name.toFirstLower» = new MohitoList<«getType(ref)»>(«getType(ref)».class, this, «IF ref.EOpposite != null»"«ref.EOpposite.name.toFirstLower»"«ELSE»«IF ref.containment»"mInverse«ref.name.toFirstUpper»"«ELSE»null«ENDIF»«ENDIF»);«ENDIF»
			«ENDFOR»
			««« mInverse* cannot be lists and do not need to be handled here.
		}
		
		/**Full constructor.
		«IF getAttributeWithAnnotationDatabaseId(domainEntity) == null»* @param id Identifier of this MOHITO-Entity.«ENDIF»
		«FOR feature : domainEntity.EAllStructuralFeatures»
		 * @param «feature.name.toFirstLower» The «feature.name.toFirstLower».
		«ENDFOR» 
		«FOR reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null]»
		 * @param mInverse«reference.name.toFirstUpper» The inverse of the containment relation «reference.name.toFirstUpper» of class «reference.EContainingClass.name.toFirstUpper». 
		«ENDFOR»
		 */
		public «domainEntity.name.toFirstUpper» (
				«IF getAttributeWithAnnotationDatabaseId(domainEntity) == null»String id, «ENDIF»
				«FOR feature : domainEntity.EAllStructuralFeatures SEPARATOR ", "»
				«IF feature.upperBound > 1 || feature.upperBound == -1»List<«getType(feature)»>«ELSE»«getType(feature)»«ENDIF» «feature.name.toFirstLower»
				«ENDFOR»
				«IF domainEntity.EAllStructuralFeatures.size != 0 && domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null].size != 0»
				, «ENDIF»
				«FOR reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null] SEPARATOR ", "»
				 «reference.EContainingClass.name.toFirstUpper» mInverse«reference.name.toFirstUpper» 
				«ENDFOR»
				) {
					
			«IF domainEntity.ESuperTypes.size > 0»
			«val EClass superDomainEntity = domainEntity.ESuperTypes.get(0)»
			super(
				«IF getAttributeWithAnnotationDatabaseId(superDomainEntity) == null»id «ENDIF»
				«IF superDomainEntity.EAllStructuralFeatures.size != 0», «ENDIF»
				«FOR feature : superDomainEntity.EAllStructuralFeatures SEPARATOR ", "»«feature.name.toFirstLower»«ENDFOR»
				«IF superDomainEntity.EAllStructuralFeatures.size != 0 && domainModel.allDomainEntities.allReferences.filter[containment == true && EType == superDomainEntity && EOpposite == null].size != 0»
				, «ENDIF»
				«FOR reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == superDomainEntity && EOpposite == null] SEPARATOR ", "»
				 mInverse«reference.name.toFirstUpper» 
				«ENDFOR»
				);
			«ELSE»
				«IF getAttributeWithAnnotationDatabaseId(domainEntity) == null»this.id = id;«ENDIF»
			«ENDIF»
			«««Attributes and references
			«FOR sf : domainEntity.EStructuralFeatures»
				«IF sf.lowerBound > 0»if («sf.name.toFirstLower»==null«IF sf.upperBound > 1 || sf.upperBound == -1»&& «sf.name.toFirstLower».size() == 0«ENDIF») {
					throw new IllegalArgumentException("The '«sf.name.toFirstLower»' of this entity must not be NULL or empty.");
				}
				«ENDIF»
				«IF sf.upperBound > 1 || sf.upperBound == -1»
					this.«sf.name.toFirstLower» = new MohitoList<«getType(sf)»>(«getType(sf)».class, this, «IF sf instanceof EReference && (sf as EReference).EOpposite != null»"«(sf as EReference).EOpposite.name.toFirstLower»"«ELSE»«IF sf instanceof EReference && (sf as EReference).containment»"mInverse«sf.name.toFirstUpper»"«ELSE»null«ENDIF»«ENDIF»);
					if («sf.name.toFirstLower» != null) {
						this.«sf.name.toFirstLower».addAll(«sf.name.toFirstLower»);
					}
				«ELSE»
					this.«sf.name.toFirstLower» = «sf.name.toFirstLower»;
				«ENDIF»
			«ENDFOR»
			««« mInverse* implicit references
			«FOR reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null]»
				this.mInverse«reference.name.toFirstUpper» = mInverse«reference.name.toFirstUpper»;
			«ENDFOR»
		}
		'''
	}

	/**Generates the methods for transparent proxy resolution at runtime.
	 * @param domainModel The domain model.
	 * @param domainEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 */
	def private generateDomainEntityTransparentProxyResolution(EClass domainEntity, PackageInfo packageInfo, EPackage domainModel) {
		'''
		@Override
		protected void doCheckProxyAndResolveAssignment(
				MohitoEntity<«getTypeOfDomainEntityIdentifier(domainEntity)»> reference) {
			«IF domainEntity.EAllSuperTypes.size > 0»
			super.doCheckProxyAndResolveAssignment(reference);
			«ENDIF»
			«domainEntity.name.toFirstUpper» ref = («domainEntity.name.toFirstUpper») reference;
			«««Attributes and references
			«FOR sf : domainEntity.EStructuralFeatures»
				«IF sf.upperBound > 1 || sf.upperBound == -1»
					this.«sf.name.toFirstLower».clear();
					this.«sf.name.toFirstLower».addAll(ref.«sf.name.toFirstLower»);
				«ELSE»
					this.«sf.name.toFirstLower» = ref.«sf.name.toFirstLower»;
				«ENDIF»
			«ENDFOR»
			««« mInverse* implicit references
			«FOR reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null]»
				this.mInverse«reference.name.toFirstUpper» = ref.mInverse«reference.name.toFirstUpper»;
			«ENDFOR»
		}

		«IF domainEntity.abstract»
		// REMARK no doCheckProxyAndResolveGetReferenceEntity for abstract classes required.
		«ELSE»
		@Override
		protected MohitoEntity<«getTypeOfDomainEntityIdentifier(domainEntity)»> doCheckProxyAndResolveGetReferenceEntity() throws DataLayerException {
			return «domainModel.name.toFirstUpper»StorageManager.mINSTANCE.getAvailableStorageManager().get«domainEntity.name.toFirstUpper»Dao().getById(getId());
		}
		«ENDIF»
		'''
	}

	/**Generates the methods getters and setters of a domain entity.
	 * @param domainModel The domain model.
	 * @param domainEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 */
	def private generateDomainEntityGetterAndSetter(EClass domainEntity, PackageInfo packageInfo, EPackage domainModel) { 
		'''
		««« Implicit identifier
		
		«IF domainEntity.ESuperTypes.size == 0»
		@Override
		public «IF getAttributeWithAnnotationDatabaseId(domainEntity) == null»String«ELSE»«getType(getAttributeWithAnnotationDatabaseId(domainEntity))»«ENDIF» 
				getId() {
			return «IF getAttributeWithAnnotationDatabaseId(domainEntity) == null»id«ELSE»«getAttributeWithAnnotationDatabaseId(domainEntity).name.toFirstLower»«ENDIF»;
		}
		
		// No setId(...) method for implicit identifier.
		«ENDIF»
		««« Structural features
		«FOR feature : domainEntity.EStructuralFeatures»
			««« Structural features: get
			«IF getAttributeWithAnnotationDatabaseId(domainEntity) != feature »
			/**
			 * Get method for the attribute «feature.name.toFirstUpper»
			 */
			public «IF feature.upperBound > 1 || feature.upperBound == -1»MohitoList<«getType(feature)»>«ELSE»«getType(feature)»«ENDIF» 
					get«feature.name.toFirstUpper»() {
				«IF getAttributeWithAnnotationDatabaseId(domainEntity) != feature»
				checkProxyAndResolve();
				«ENDIF»
				return this.«feature.name.toFirstLower»;
			}
			«ENDIF»
			««« Structural features: set
			«IF feature.upperBound > 1 || feature.upperBound == -1»
				// No set«feature.name.toFirstUpper»(...) method for a collection
			«ELSEIF getAttributeWithAnnotationDatabaseId(domainEntity) == feature»
				// No set«feature.name.toFirstUpper»(...) method for an identifier
			«ELSE»
			/**
			 * Set method for the attribute «feature.name.toFirstUpper»
			 */
				public void set«feature.name.toFirstUpper»(«getType(feature)» «feature.name.toFirstLower») {
					// release
					«IF domainModel.EClassifiers.contains(feature.EType) && ! (feature.EType instanceof EEnum) »
					if (this.«feature.name.toFirstLower» != null) {
						«IF feature instanceof EReference && (feature as EReference).containment»
						this.«feature.name.toFirstLower».mSetDeletionPending(true);
						«ENDIF»
						«IF feature instanceof EReference && (feature as EReference).EOpposite != null»
							«IF (feature as EReference).EOpposite.upperBound > 1 || (feature as EReference).EOpposite.upperBound == -1»
							this.«feature.name.toFirstLower».get«(feature as EReference).EOpposite.name.toFirstUpper»().remove(this);
							«ELSE»
							this.«feature.name.toFirstLower».set«(feature as EReference).EOpposite.name.toFirstUpper»(null);
							«ENDIF»
						«ENDIF»
					}
					«ENDIF»
					// set
					this.«feature.name.toFirstLower» = «feature.name.toFirstLower»;
					«IF feature instanceof EReference && (feature as EReference).EOpposite != null»
					if («feature.name.toFirstLower» != null) {
						«IF (feature as EReference).EOpposite.upperBound > 1 || (feature as EReference).EOpposite.upperBound == -1»
						«feature.name.toFirstLower».get«(feature as EReference).EOpposite.name.toFirstUpper»().add(this);
						«ELSE»
						«feature.name.toFirstLower».set«(feature as EReference).EOpposite.name.toFirstUpper»(this);
						«ENDIF»
					}
					«ENDIF»
					mSetDirty(true);
				}
			«ENDIF»
		«ENDFOR»
		««« mInverse*
		«FOR reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null]»
			««« mInverse*: get
			/**
			 * Get method for the inverse «reference.name.toFirstUpper»
			 */
			public «reference.EContainingClass.name» getMInverse«reference.name.toFirstUpper»() {
				checkProxyAndResolve();
				return mInverse«reference.name.toFirstUpper»;
			}
			««« mInverse*: set
			/**
			 * Set method for the attribute «reference.name.toFirstUpper»
			 */
			public void setMInverse«reference.name.toFirstUpper»(«reference.EContainingClass.name» mInverse«reference.name.toFirstUpper») {
				// sanity check
				if (mInverse«reference.name.toFirstUpper» == null &&
				«FOR invRef : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null] SEPARATOR " && "»
					«IF invRef != reference»
						this.mInverse«invRef.name.toFirstUpper» == null
					«ELSE»
						true
					«ENDIF»
				«ENDFOR»
						&& !mIsDeletionPending()) {
					throw new IllegalArgumentException("At least one of the containment relations of the MOHITO-Entity «domainEntity.name.toFirstUpper» must not be NULL.");
				}
				// release / rewire instance relation if necessary
				if (this.mInverse«reference.name.toFirstUpper» != null) {
					«IF reference.upperBound > 1 || reference.upperBound == -1»
					this.mInverse«reference.name.toFirstUpper».get«reference.name.toFirstUpper»().remove(this);
					«ELSE»
					this.mInverse«reference.name.toFirstUpper».set«reference.name.toFirstUpper»(null);
					«ENDIF»
				}
				// assign new value
				this.mInverse«reference.name.toFirstUpper» = mInverse«reference.name.toFirstUpper»;
				«IF reference.upperBound > 1 || reference.upperBound == -1»
					if (this.mInverse«reference.name.toFirstUpper» != null && ! this.mInverse«reference.name.toFirstUpper».get«reference.name.toFirstUpper»().contains(this)) {
						this.mInverse«reference.name.toFirstUpper».get«reference.name.toFirstUpper»().add(this);
					}
				«ELSE»
					if (this.mInverse«reference.name.toFirstUpper» == null) {
						this.mInverse«reference.name.toFirstUpper».set«reference.name.toFirstUpper»(this);
					}
				«ENDIF»
				mSetDirty(true);
				// ensure consistency - set other containment relations to null
				«FOR invRef : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null]»
					«IF invRef != reference»
						this.mInverse«invRef.name.toFirstUpper» = null;
					«ENDIF»
				«ENDFOR»
			}
		«ENDFOR»
		'''
	}

	/**Generates the method for checking the equality of a domain object.
	 * @param domainModel The domain model.
	 * @param domainEntity The domain entity.
	 * @param packageInfo Information on the target package for generation.
	 */
	def private generateDomainEntityDomainEquals(EClass domainEntity, PackageInfo packageInfo, EPackage domainModel) {
		'''
		@Override
		public boolean domainContentEquals(MohitoEntity<«getTypeOfDomainEntityIdentifier(domainEntity)»> reference) {
			if (reference instanceof «domainEntity.name.toFirstUpper») {
				«domainEntity.name.toFirstUpper» ref = («domainEntity.name.toFirstUpper») reference;
				boolean result = true;
				«IF domainEntity.ESuperTypes.size > 0»result &= super.domainContentEquals(ref);«ENDIF»

				«««Attributes and references
				«FOR sf : domainEntity.EStructuralFeatures»
					«IF sf.upperBound > 1 || sf.upperBound == -1»
						if (this.«sf.name.toFirstLower».size() == ref.«sf.name.toFirstLower».size()) {
							for (int i = 0; i < this.«sf.name.toFirstLower».size(); i++) {
								«IF sf.isPrimitiveType»
								result &= this.«sf.name.toFirstLower».get(i).equals(ref.«sf.name.toFirstLower».get(i));
								«ELSE»
								result &= this.«sf.name.toFirstLower».get(i).getId().equals(ref.«sf.name.toFirstLower».get(i).getId());
								«ENDIF»
							}
						} else {
							result = false;
						}
					«ELSE»
«««						enum types -> instanceClassName null checked
						«IF (sf.EType.instanceClassName == null)» 
						result &= this.«sf.name.toFirstLower».equals(ref.«sf.name.toFirstLower»);
						«ELSEIF sf.isPrimitiveType»
						result &= this.«sf.name.toFirstLower».equals(ref.«sf.name.toFirstLower»);
						«ELSE»
						result &= this.«sf.name.toFirstLower».getId().equals(ref.«sf.name.toFirstLower».getId());
						«ENDIF»
					«ENDIF»
				«ENDFOR»
				««« mInverse* implicit references
				«FOR reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null]»
					result &= this.mInverse«reference.name.toFirstUpper».getId().equals(((«domainEntity.name.toFirstUpper»)reference).getMInverse«reference.name.toFirstUpper»().getId());
			«ENDFOR»
				return result;
			} 
			«IF domainEntity.ESuperTypes.size > 0»
			else if (reference instanceof «domainEntity.ESuperTypes.get(0).name.toFirstUpper») {
				return super.domainContentEquals(reference);
			}
			«ENDIF»
			return false;
		}
		'''
	}

   /**
    * Determines the imports for the provided domain entity.
    * @param packageInfo Information on the current package.
    * @param domainEntity The domain entity.
    * @param domainModel The domain model.
    */
	def private Set<String> calculateImports(PackageInfo packageInfo, EClass domainEntity, EPackage domainModel) {
		var Set<String> imports = new HashSet<String>();
		if (domainEntity.EAttributes.size > 0 && domainEntity.EAttributes.filter[it.getEType != null && 'java.util.Date'.equals(it.getEType.instanceClassName)].size > 0) {
			imports.add("java.util.Date");
		}
		if (! domainEntity.EAllStructuralFeatures.filter[upperBound > 1 || upperBound == -1].empty) {
			imports.add("java.util.List");
			imports.add("info.multiplatform.mohito.framework.MohitoList")
		}
		imports.add("info.multiplatform.mohito.framework.MohitoEntity")
		imports.add("info.multiplatform.mohito.framework.exceptions.DataLayerException")
		imports.add(packageInfo.append(MohitoJavaFolderConstants::MODEL_UTIL).getPackageName + PackageInfo::SEPARATOR_PACKAGE + domainModel.name.toFirstUpper +"StorageManager")
		for (reference : domainModel.allDomainEntities.allReferences.filter[containment == true && EType == domainEntity && EOpposite == null]) {
			imports.add(packageInfo.getPackageName + PackageInfo::SEPARATOR_PACKAGE + reference.EContainingClass.name.toFirstUpper);
		}
		return imports;
	}
		
	// Create and generate [ModelName]MohitoEntity
	 
	/**Generates the file for the class marking the model of domain elements.
	 * 
	 * @see #generateModelClassImplementation
	 * 
	 */
	def private generateMohitoEntityClassFile(IFileSystemAccess fsa, EPackage domainModel,
			PackageInfo packageInfo) {
		fsa.generateFile(packageInfo.getPackageDir + domainModel.name.toFirstUpper + "MohitoEntity.java", 
				MohitoJavaFolderConstants::SRC_GEN, 
				generateMohitoEntityClassImplementation(packageInfo,domainModel) );
	}
	
	/**Generates the implementation for the class marking the model of domain elements.
	 * @param packageInfo Information on the package used for the model.
	 * @param domainModel The package defining the model.
	 * 
	 * @see #generateModelClassFile
	 * 
	 */
	def private generateMohitoEntityClassImplementation(PackageInfo packageInfo, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»
		«statementImports( {"info.multiplatform.mohito.framework.MohitoEntity"}.toSet() )»
		«localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationImportStatement»
		«remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationImportStatement»
		
		/**Marker for model entities of the «domainModel.name».
		 * {@link #generateUUID()} can be overrridden to provide model-specific generator implementations for generation UUIDs.
		 *
		«statementGenerated(this.class)»
		 */
		public abstract class «domainModel.name.toFirstUpper»MohitoEntity<PK> extends MohitoEntity<PK> {
			
			/**Default constructor.
			 */
			public «domainModel.name.toFirstUpper»MohitoEntity() {
				super ();
			}
			
			/** Required helper to allow annotations. See #creationTime */
			«localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			«remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			protected Long presistedCreationTime;
			
			/** Required helper to allow annotations. See #lastUpdateTime */
			«localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			«remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			protected Long presistedLastUpdateTime;
			
			/** Required helper to allow annotations. See #lastModificationTime */
			«localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			«remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			protected Long presistedLastModificationTime;
			
			/** Required helper to allow annotations. See #mIsProxy */
			«localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			«remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			protected boolean presistedIsProxy;
			
			/** Required helper to allow annotations. See #mIsDirty */
			«localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			«remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			protected boolean presistedIsDirty;
			
			/** Required helper to allow annotations. See #mDeletionPending */
			«localStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			«remoteStorageGenerator.generateDomainModelMohitoEntityAttributeAnnotationStatement»
			protected boolean presistedDeletionPending;
			
			@Override
			public Long getCreationTime() {
				return presistedCreationTime;
			}
			
			@Override
			public void setCreationTime(Long creationTime) {
				presistedCreationTime = creationTime;
				this.creationTime = creationTime;
			}
		
			@Override
			public Long getLastUpdateTime() {
				return presistedLastUpdateTime;
			}
			
			@Override
			public void setLastUpdateTime(Long lastUpdateTime) {
				this.presistedLastUpdateTime = lastUpdateTime;
				this.lastUpdateTime = lastUpdateTime;
			}
			
			@Override
			public Long getLastModificationTime() {
				return presistedLastModificationTime;
			}
			
			@Override
			public void setLastModificationTime(Long lastModificationTime) {
				this.presistedLastModificationTime = lastModificationTime;
				this.lastModificationTime = lastModificationTime;
			}
			
			@Override
			public boolean mIsProxy() {
				return presistedIsProxy;
			}
			
			@Override
			public void mSetProxy(boolean isProxy) {
				presistedIsProxy = isProxy;
				mIsProxy = isProxy;
			}
		
			@Override
			public boolean mIsDirty() {
				return presistedIsDirty;
			}
			
			@Override
			public void mSetDirty(boolean isDirty) {
				presistedIsDirty = isDirty;
				mIsDirty = isDirty;
			}
		
			@Override
			public boolean mIsDeletionPending() {
				return presistedDeletionPending;
			}
			
			@Override
			public void mSetDeletionPending(boolean deletionPending) {
				presistedIsDirty = true;
				mIsDirty = true;
				
				presistedDeletionPending = deletionPending;
				mDeletionPending = deletionPending;
			}
		}
		'''
	}	
	
}