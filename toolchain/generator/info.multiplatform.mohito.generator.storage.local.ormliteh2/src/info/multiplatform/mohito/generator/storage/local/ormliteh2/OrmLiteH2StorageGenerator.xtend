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
package info.multiplatform.mohito.generator.storage.local.ormliteh2

import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import com.google.inject.Inject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.EPackage
import info.multiplatform.mohito.modeling.annotation.database.DatabaseMohitoAnnotationCategory
import java.util.Map
import java.util.HashMap
import org.eclipse.emf.ecore.EReference
import info.multiplatform.mohito.generator.IStorageGenerator
import info.multiplatform.mohito.generator.environment.java.JavaUtils
import info.multiplatform.mohito.generator.environment.java.AnnotationUtils
import info.multiplatform.mohito.generator.environment.java.PackageInfo
import info.multiplatform.mohito.generator.environment.java.MohitoJavaFolderConstants
import java.util.ArrayList

/**Generator for a local storage using OrmLite and a H2 database.
 * @author groenda 
 */
class OrmLiteH2StorageGenerator implements IStorageGenerator {
	/** Utilities for generating Java code. */
	@Inject extension JavaUtils javaUtilities
	/** Utilities for MOHITO-Models and annotations. */
	@Inject extension AnnotationUtils annotationUtilities

	override CharSequence generateManagerImportStatements(PackageInfo packageInfo, EPackage domainModel) {
		'''
		import info.multiplatform.mohito.framework.desktop.MohitoEntityDaoOrmLiteImpl;
		'''
	}
	
	override CharSequence generateManagerEntityDaoCreation(EPackage domainModel) {
		'''
		return new MohitoEntityDaoOrmLiteImpl<T, PK>(mohitoEntityClass, this);
		'''
	}
	
	override generateAdditionalDomainEntityArtifacts(IFileSystemAccess fsa, PackageInfo packageInfo, EClass domainEntity, EPackage domainModel) {
		for (EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]) {
		fsa.generateFile(packageInfo.getPackageDir + domainEntity.name.toFirstUpper + "DaoImpl.java", 
				MohitoJavaFolderConstants::SRC_GEN, 
				generateAdditionalDomainEntityDaoImplementation(packageInfo, domainEntity));
		}
		// Additional mapping entities for attributes or references with an upper bound greater than 1
		for (EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]) {
			fsa.generateFile(packageInfo.getPackageDir + domainEntity.name.toFirstUpper + "_" + feature.name + ".java", 
					MohitoJavaFolderConstants::SRC_GEN, 
					generateAdditionalDomainEntityMappingClassImplementation(packageInfo, domainEntity, feature, domainModel));
		}
	}
	
	/**Generate the required DAO implementations to access attributes or references with an upper bound greater than 1 with OrmLite.
	 * @param packageInfo The package of the DAO.
	 * @param domainEntity The domain entity for which the DAO implementation is to be generated.
	 */
	def private generateAdditionalDomainEntityDaoImplementation(PackageInfo packageInfo, EClass domainEntity) {
		var ArrayList<String>list = new ArrayList<String>();
		for(EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]){
				if (!getType(feature).equals("String")){
				list.add(packageInfo.strip().packageName + PackageInfo::SEPARATOR_PACKAGE + getType(feature).toFirstUpper);
				}
			}
		'''
		«statementPackage(packageInfo)»
		«statementImports(toSet(list))»
		«statementImports(toSet(
			packageInfo.strip().packageName + PackageInfo::SEPARATOR_PACKAGE + domainEntity.name.toFirstUpper, 
			"info.multiplatform.mohito.framework.desktop.H2DatabaseManager", 
			"java.sql.SQLException", 
			"java.util.List", 
			"java.util.logging.Level", 
			"java.util.logging.Logger", 
			"com.j256.ormlite.dao.BaseDaoImpl", 
			"com.j256.ormlite.dao.Dao", 
			"com.j256.ormlite.dao.DaoManager", 
			"com.j256.ormlite.stmt.DeleteBuilder", 
			"com.j256.ormlite.stmt.QueryBuilder", 
			"com.j256.ormlite.support.ConnectionSource", 
			"com.j256.ormlite.table.TableUtils"))»
		
		/**Workaround for OrmLite in order to store attributes with an upper bound greater than one in the database.
		 * Handles loading/storing the attributes.
		 «statementGenerated(this.class)»
		 */
		public class «domainEntity.name.toFirstUpper»DaoImpl extends BaseDaoImpl<«domainEntity.name.toFirstUpper», «getTypeOfDomainEntityIdentifier(domainEntity)»> {
			
			// DAOs for each attribute with an upper bound greater than 1
			«FOR EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]»
			/** Dao for the attribute behind {@link «domainEntity.name.toFirstUpper»#get«feature.name.toFirstUpper»()}. */
			private final Dao<«domainEntity.name.toFirstUpper»_«feature.name», «IF isPrimitiveType(feature)»«getType(feature)»«ELSE»«getTypeOfDomainEntityIdentifier(feature.EType as EClass)»«ENDIF»> «feature.name»Dao; 
			«ENDFOR»
			
			public «domainEntity.name.toFirstUpper»DaoImpl(ConnectionSource connectionSource) throws SQLException {
				super(connectionSource, «domainEntity.name.toFirstUpper».class);
				«FOR EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]»
				// «feature.name»
				this.«feature.name»Dao = DaoManager.createDao(connectionSource,
						«domainEntity.name.toFirstUpper»_«feature.name».class);
				«ENDFOR»
				«FOR EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1|| it.upperBound == -1]»
					// «feature.name»
					TableUtils.createTableIfNotExists(H2DatabaseManager.getConnectionSource(), «domainEntity.name.toFirstUpper»_«feature.name».class);
					«ENDFOR»
			}
			
			@Override
			public int create(«domainEntity.name.toFirstUpper» data) throws SQLException {
				int rows = super.create(data);
				«FOR EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]»
				// «feature.name»
				«domainEntity.name.toFirstUpper»_«feature.name» «domainEntity.name.toFirstLower»_«feature.name»; 
				for («getType(feature)» value : data.get«feature.name.toFirstUpper»()) {
					«domainEntity.name.toFirstLower»_«feature.name» = new «domainEntity.name.toFirstUpper»_«feature.name»(data.getId(), value«IF getType(feature).equals("String")»«ELSE».getId()«ENDIF»);
					rows += «feature.name»Dao.create(«domainEntity.name.toFirstLower»_«feature.name»);
				}
				«ENDFOR»
				return rows; 
			}
			
			@Override
			public int update(«domainEntity.name.toFirstUpper» data) throws SQLException {
				int rows = super.update(data);
				«FOR EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]»
				// «feature.name»
				«domainEntity.name.toFirstUpper»_«feature.name» «domainEntity.name.toFirstLower»_«feature.name»; 
				DeleteBuilder<«domainEntity.name.toFirstUpper»_«feature.name», «IF isPrimitiveType(feature)»«getType(feature)»«ELSE»«getTypeOfDomainEntityIdentifier(feature.EType as EClass)»«ENDIF»> «feature.name»DeleteBuilder =
						  «feature.name»Dao.deleteBuilder();
				«feature.name»DeleteBuilder.where().eq("containmentId", data.getId());
				rows += «feature.name»DeleteBuilder.delete();
				for («getType(feature)» «feature.name» : data.get«feature.name.toFirstUpper»()) {
					«domainEntity.name.toFirstLower»_«feature.name» = new «domainEntity.name.toFirstUpper»_«feature.name»(data.getId(), «feature.name»«IF !getType(feature).equals("String")».getId()«ENDIF»);
					rows += «feature.name»Dao.create(«domainEntity.name.toFirstLower»_«feature.name»);
				}
				«ENDFOR»
				return rows;
			}
			
			@Override
			public int delete(«domainEntity.name.toFirstUpper» data) throws SQLException {
				int rows = super.delete(data);
				«FOR EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]»
				// «feature.name»
				DeleteBuilder<«domainEntity.name.toFirstUpper»_«feature.name», «IF isPrimitiveType(feature)»«getType(feature)»«ELSE»«getTypeOfDomainEntityIdentifier(feature.EType as EClass)»«ENDIF»> 
						«feature.name»DeleteBuilder = «feature.name»Dao.deleteBuilder();
				«feature.name»DeleteBuilder.where().eq("containmentId", data.getId());
				rows += «feature.name»DeleteBuilder.delete();
				«ENDFOR»
				return rows;
			}
			
			@Override
			public int refresh(«domainEntity.name.toFirstUpper» data) throws SQLException {
				throw new IllegalStateException("Not Supported by this implementation. It is not necessary to refresh generated helper objects.");
			}
			
			@Override
			public «domainEntity.name.toFirstUpper» queryForId(«getTypeOfDomainEntityIdentifier(domainEntity).toFirstUpper» id) throws SQLException {
				«domainEntity.name.toFirstUpper» result = super.queryForId(id);
				if (result != null) {
					boolean oldMIsDirty = result.mIsDirty();
					«FOR EStructuralFeature feature : domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]»
					// «feature.name»
					QueryBuilder<«domainEntity.name.toFirstUpper»_«feature.name», «IF isPrimitiveType(feature)»«getType(feature)»«ELSE»«getTypeOfDomainEntityIdentifier(feature.EType as EClass)»«ENDIF»> 
							«feature.name»QueryBuilder = «feature.name»Dao.queryBuilder();
					«feature.name»QueryBuilder.where().eq("containmentId", id);
					List<«domainEntity.name.toFirstUpper»_«feature.name»> «feature.name»List = «feature.name»QueryBuilder.query();
					for («domainEntity.name.toFirstUpper»_«feature.name» data : «feature.name»List) {
					«getType(feature)» value = «IF !getType(feature).equals("String")»new «getType(feature).toFirstUpper»(data.getValue())«ELSE»data.getValue()«ENDIF»;
						result.get«feature.name.toFirstUpper»().add(value);
					}
					«ENDFOR»
					result.mSetDirty(oldMIsDirty);
				}
				return result;
			}
			
		}
		'''
	}

	/**Generate the additionally required mapping class for a domain entity with a feature with an upper bound greater than 1.
	 * @param packageInfo The package for the implementation.
	 * @param domainEntity The domain entity.
	 * @param feature The feature.
	 */
	def private generateAdditionalDomainEntityMappingClassImplementation(PackageInfo packageInfo, EClass domainEntity, EStructuralFeature feature, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»

		«statementImports(toSet(
			packageInfo.strip().packageName + PackageInfo::SEPARATOR_PACKAGE + domainEntity.name.toFirstUpper,
			"com.j256.ormlite.field.DatabaseField",
			"com.j256.ormlite.table.DatabaseTable"
		))»
		«if (domainModel.EClassifiers.contains(feature.EType)) { "import " + packageInfo.strip().packageName+ PackageInfo::SEPARATOR_PACKAGE + feature.EType.name + ";"}»		
		
		/**Workaround for OrmLite in order to store attributes with an upper bound greater than one in the database.
		 * This handles the attribute behind {@link «domainEntity.name.toFirstUpper»#get«feature.name.toFirstUpper»()}.
		 «statementGenerated(this.class)»
		 */
		@DatabaseTable(tableName = "«domainEntity.name.toFirstUpper»_«feature.name.toFirstLower»")
		public class «domainEntity.name.toFirstUpper»_«feature.name.toFirstLower» {
			/** Unused technical identifier. */
			@DatabaseField(generatedId = true)
			protected Long id;
			/** ID of the MOHITO-Entity to which this entity belongs. */
			@DatabaseField(index = true)
			protected «getTypeOfDomainEntityIdentifier(domainEntity)» containmentId;
			/** Id of the referenced entity. */
			@DatabaseField(canBeNull = false)
			protected «IF isPrimitiveType(feature)»«getType(feature)»«ELSE»«getTypeOfDomainEntityIdentifier(feature.EType as EClass)»«ENDIF» value;
			
			public «domainEntity.name.toFirstUpper»_«feature.name.toFirstLower»() {
			}
		
			public «domainEntity.name.toFirstUpper»_«feature.name.toFirstLower»(
					«getTypeOfDomainEntityIdentifier(domainEntity)» containmentId, 
					«IF isPrimitiveType(feature)»«getType(feature)»«ELSE»«getTypeOfDomainEntityIdentifier(feature.EType as EClass)»«ENDIF» value) {
				this.containmentId = containmentId;
				this.value = value;
			}
		
			public Long getId() {
				return id;
			}
		
			/**
			 * @return The id of the referenced object {@link «feature.EType.name.toFirstUpper»}. The real value for basic types.
			 */
			public «IF isPrimitiveType(feature)»«getType(feature)»«ELSE»«getTypeOfDomainEntityIdentifier(feature.EType as EClass)»«ENDIF» getValue() {
				return value;
			}
		
			/**
			 * @param value The id of the referenced {@link «feature.EType.name.toFirstUpper»}. The real value for basic types.
			 */
			public void setValue(«IF isPrimitiveType(feature)»«getType(feature)»«ELSE»«getTypeOfDomainEntityIdentifier(feature.EType as EClass)»«ENDIF» value) {
				this.value = value;
			}
		
			/**
			 * @param value The id of the referenced {@link «domainEntity.name.toFirstUpper»}.
			 */
			public «getTypeOfDomainEntityIdentifier(domainEntity)» getContainmentId() {
				return containmentId;
			}
			//REMARK No setter for containment id;
			
		}		
		'''
	}
	
	override generateDomainEntityImportStatements(PackageInfo packageInfo, EClass domainEntity, EPackage domainModel) {
		'''
		«statementImports(toSet(
		"com.j256.ormlite.field.DatabaseField",
		"com.j256.ormlite.table.DatabaseTable"))»
		«if (domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1].size > 0) {
			statementImports(toSet(packageInfo.packageName + PackageInfo::SEPARATOR_PACKAGE 
				+ MohitoJavaFolderConstants::MODEL_LOCAL + PackageInfo::SEPARATOR_PACKAGE + domainEntity.name.toFirstUpper + "DaoImpl"
			))
		}»
		'''
	}
	
	override CharSequence generateDomainEntityClassAnnotationStatement(PackageInfo packageInfo, EClass domainEntity, EPackage domainModel) {
		val Map<String, String> settings = new HashMap
		if (getValueForAnnotationAsBoolean(domainEntity, DatabaseMohitoAnnotationCategory::SERIALIZE_IN_DB) == true) {
			settings.put("tableName", '\"' + domainEntity.name.toFirstUpper + '\"')
			if (domainEntity.EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1].size > 0) {
				settings.put("daoClass", domainEntity.name.toFirstUpper + "DaoImpl.class")  
			}
			'''@DatabaseTable(«FOR setting : settings.entrySet SEPARATOR ", "»«setting.key» = «setting.value»«ENDFOR»)'''
		}
	}
	
	override CharSequence generateDomainEntityStructuralFeatureAnnotationStatement(PackageInfo packageInfo, EStructuralFeature feature, EPackage domainModel) {
		val Map<String, String> settings = new HashMap
		if (feature instanceof EReference 
				|| getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_SERIALIZE_IN_DB) == true	) {
			if (feature instanceof EReference) {
				val EReference ref = feature as EReference
				if ( ref.upperBound > 1 || ref.upperBound == -1	) {
					// ensures consistent handling for references
					return "";
				} else { // Upper Bound <= 1, direct reference
					settings.put("foreign", Boolean.TRUE.toString());
				}
			} else {
				settings.put("index", getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_BUILD_INDEX).toString);
				settings.put("foreign", getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_IS_FOREIGN_KEY).toString);
			}
			if (getValueForAnnotationAsString(feature, DatabaseMohitoAnnotationCategory::FIELD_COLUMN_NAME_IN_DB) != DatabaseMohitoAnnotationCategory::FIELD_COLUMN_NAME_IN_DB.defaultValue) {
				settings.put("columnName", "\"" + getValueForAnnotationAsString(feature, DatabaseMohitoAnnotationCategory::FIELD_COLUMN_NAME_IN_DB) + "\"")
			}
			// The identifier annotation for OrmLite can only be either generatedId or Id. GeneratedId overrides id. 
			if (getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_IS_IDENTIFIER) == true
					|| getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_MARK_AS_GENERATED) == true) {
				if (getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_MARK_AS_GENERATED) == true) {
					settings.put("generatedId", getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_MARK_AS_GENERATED).toString);
				} else {
					settings.put("id", getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_IS_IDENTIFIER).toString);
				}
			}
			settings.put("canBeNull", getValueForAnnotationAsBoolean(feature, DatabaseMohitoAnnotationCategory::FIELD_IS_NULLABLE).toString);
			if (feature.lowerBound == 0) {
				settings.put("canBeNull", "true");
			}
			if (feature.upperBound > 1 || feature.upperBound == -1) {
				// No direct annotation of the feature. This case is handled by the customized DaoImpl
			} else {
				'''@DatabaseField(«FOR setting : settings.entrySet SEPARATOR ", "»«setting.key» = «setting.value»«ENDFOR»)'''
			}		} 
	}
	
	override generateAdditionalDomainModelArtifacts(IFileSystemAccess fsa, PackageInfo packageInfo, EPackage domainModel) {
		// Nothing needs to be done
	}

	override generateAdditionalDomainEntityStatements(EClass domainEntity) {
		// Nothing needs to be generated
	}

	override generateAdditionalManagerEntityDaoStatements(PackageInfo packageInfo, EPackage domainModel) {
		// Nothing needs to be generated
	}

	override generateAdditionalManagerEntityDaoConstructorStatements(PackageInfo packageInfo, EPackage domainModel) {
		// Nothing needs to be generated
	}

	override generateDaoManagerImplementsStatement() {
		// Nothing needs to be generated
	}
	
	override generateDomainModelMohitoEntityAttributeAnnotationStatement() {
		'''
		@DatabaseField(index = false, foreign = false, canBeNull = true)
		'''
	}
	
	override generateDomainModelMohitoEntityAttributeAnnotationImportStatement() {
		'''
		«statementImports(toSet(
			"com.j256.ormlite.field.DatabaseField"
		))»
		'''
	}
	
}