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
package info.multiplatform.mohito.generator.storage.local.ormliteandroid

import com.google.inject.Inject
import info.multiplatform.mohito.modeling.annotation.database.DatabaseMohitoAnnotationCategory
import java.util.ArrayList
import java.util.HashMap
import java.util.Map
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EEnum
import info.multiplatform.mohito.generator.IStorageGenerator
import info.multiplatform.mohito.generator.environment.java.JavaUtils
import info.multiplatform.mohito.generator.environment.java.AnnotationUtils
import info.multiplatform.mohito.generator.environment.java.PackageInfo
import info.multiplatform.mohito.generator.environment.java.MohitoJavaFolderConstants

/**Generator for a local storage using OrmLite and the built-in Android database (SQLite).
 * @author groenda 
 */
class OrmLiteAndroidStorageGenerator implements IStorageGenerator {
	/** Utilities for generating Java code. */
	@Inject extension JavaUtils javaUtilities
	/** Utilities for MOHITO-Models and annotations. */
	@Inject extension AnnotationUtils annotationUtilities

	override CharSequence generateManagerImportStatements(PackageInfo packageInfo, EPackage domainModel) {
		'''
		import info.multiplatform.mohito.framework.android.MohitoEntityDaoOrmLiteImpl;
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
			// initialize additional database tables
			/** Logger for this class. */
			private static final Logger logger = Logger.getLogger(«domainEntity.name.toFirstUpper»DaoImpl.class.getCanonicalName());
			
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
					«domainEntity.name.toFirstLower»_«feature.name» = new «domainEntity.name.toFirstUpper»_«feature.name»(data.getId(), «feature.name»«IF getType(feature).equals("String")»«ELSE».getId()«ENDIF»);
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
			public «domainEntity.name.toFirstUpper» queryForId(«getTypeOfDomainEntityIdentifier(domainEntity)» id) throws SQLException {
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
					«getType(feature)» value = «IF getType(feature).equals("String")»data.getValue()«ELSE»new «getType(feature).toFirstUpper»(data.getValue())«ENDIF»;
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
			"com.j256.ormlite.field.DatabaseField",
			"com.j256.ormlite.table.DatabaseTable"
		))»
		import «packageInfo.strip.packageName».«domainEntity.name.toFirstUpper»;
		«IF (domainModel.EClassifiers.contains(feature.EType))»
		import «packageInfo.strip().packageName».«feature.EType.name»;
		«ENDIF»		
		
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
			}
		} 
	}
	
	override generateAdditionalDomainModelArtifacts(IFileSystemAccess fsa, PackageInfo packageInfo, EPackage domainModel) {
			fsa.generateFile(packageInfo.getPackageDir + domainModel.name.toFirstUpper + "DatabaseHelper.java", 
					MohitoJavaFolderConstants::SRC_GEN, 
					generateDatabaseHelperImplementation(packageInfo, domainModel));
	}
	
	/**Generates the implementation of the database helper, which allows automatic connection to the database used in Android.
	 * @param info Information on the package for the implementation.
	 * @param domainModel The domain model.
	 */
	def CharSequence generateDatabaseHelperImplementation(PackageInfo packageInfo, EPackage domainModel) {
		'''
		«statementPackage(packageInfo)»

		«statementImports(toSet(
			packageInfo.strip.packageName + PackageInfo::SEPARATOR_PACKAGE + "*", 
			"info.multiplatform.mohito.framework.android.IAndroidDatabaseHelper",
			"java.sql.SQLException",
			"android.content.Context",
			"android.database.sqlite.SQLiteDatabase",
			"android.util.Log",
			"com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper",
			"com.j256.ormlite.support.ConnectionSource",
			"com.j256.ormlite.table.TableUtils"
		))»
		
		/**
		 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
		 * the DAOs used by the other classes.
		 */
		public class «domainModel.name.toFirstUpper»DatabaseHelper extends OrmLiteSqliteOpenHelper implements IAndroidDatabaseHelper {
			
			private static String LOG_TAG = «domainModel.name.toFirstUpper»DatabaseHelper.class.getSimpleName();
			
			/** Name of the database, also used as file name on Android to store the database. */
			public static final String DATABASE_NAME = "«domainModel.name.toFirstUpper».db";
				
			/** any time you make changes to your database objects, you may have to increase the database version
			 */
			public static final int DATABASE_VERSION = 1;
			
			public «domainModel.name.toFirstUpper»DatabaseHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}
			
			/**
			 * This is called when the database must be created for the first time. 
			 * Usually you should call createTable statements here to create
			 * the tables that will store your data.
			 */
			@Override
			public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
				Log.i(LOG_TAG, "onCreate: Creating tables in the database for storing the MOHITO domain model «domainModel.name.toFirstUpper».");
				«createStatementsForHandlingDatabaseTable(domainModel, true, true)»
			}
			
			/**
			 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
			 * the various data to match the new version number.
			 */
			@Override
			public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
				Log.i(LOG_TAG, "onUpgrade: Upgrading tables in the database for storing the MOHITO domain model «domainModel.name.toFirstUpper».");
				«createStatementsForHandlingDatabaseTable(domainModel, false, true)»
				// after we drop the old databases, we create the new ones
				«createStatementsForHandlingDatabaseTable(domainModel, true, false)»
			}
		
		    @Override
			public void deleteDb() {
				«createStatementsForHandlingDatabaseTable(domainModel, false, true)»
				// after we drop the old databases, we create the new ones
				«createStatementsForHandlingDatabaseTable(domainModel, true, false)»
			}

			public void registerDatabaseHelper(Context arg0, IAndroidDatabaseHelper arg1) {
				// not required by this version of the helper.
				throw new UnsupportedOperationException();
			}
		
			public void unregisterDatabaseHelper(Context arg0) {
				// not required by this version of the helper.
				throw new UnsupportedOperationException();
			}
		
		}
		'''
	}

	/**creates the statements for using table utility functions on all elements requiring database tables.
	 * @param The domain model.
	 * @param create Table creation if equal to true, table deletion otherwise.
	 * @param continueOnError Flag if error message should only be logged or an exceptions should be thrown.
	 */
	def CharSequence createStatementsForHandlingDatabaseTable(EPackage domainModel, boolean create, boolean continueOnError) {
		'''
		«FOR classifier : domainModel.EClassifiers.filter[! (it instanceof EEnum)]»
		«IF create»
		«executeStatementsForHandlingDatabaseTable("TableUtils.createTable(connectionSource, " + classifier.name.toFirstUpper + ".class);", continueOnError)»
		«ELSE»
		«executeStatementsForHandlingDatabaseTable("TableUtils.dropTable(connectionSource, " + classifier.name.toFirstUpper + ".class, true);", continueOnError)»
		«ENDIF»
		«IF classifier instanceof EClass»
			«FOR feature : (classifier as EClass).EAllStructuralFeatures.filter[it.upperBound > 1 || it.upperBound == -1]»
			«IF create»
			«executeStatementsForHandlingDatabaseTable("TableUtils.createTable(connectionSource, " + classifier.name.toFirstUpper + "_" + feature.name + ".class);", continueOnError)»
			«ELSE»
			«executeStatementsForHandlingDatabaseTable("TableUtils.dropTable(connectionSource, " + classifier.name.toFirstUpper + "_" + feature.name + ".class, true);", continueOnError)»
			«ENDIF»
			«ENDFOR»
		«ENDIF»
		«ENDFOR»
		'''
	}

	/**creates the statements for executing database function in a safe way.
	 * @param statement The executed statement.
	 * @param continueOnError Flag if error message should only be logged or an exceptions should be thrown.
	 */
	def CharSequence executeStatementsForHandlingDatabaseTable(String statement, boolean continueOnError) {
		'''
		try {
			«statement»
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Can't execute statement: «statement»", e);
			«IF ! continueOnError»throw new RuntimeException(e);«ENDIF»
		}
		'''
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