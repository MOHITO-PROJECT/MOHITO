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
import info.multiplatform.mohito.modeling.annotation.database.DatabaseMohitoAnnotationCategory
import java.util.ArrayList
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

class JavaUtils {
		
	/** Utilities for handling annotations of MOHITO-Models. */
	@Inject extension AnnotationUtils annotationUtilities
	
	/**Ensure that a provided identifier is a java-compatible.
	 * @throw IllegalArgumentException If the identifier is not compatible.
	 */
	def ensureJavaCompatibleName(String identifier) {
		val char[] chars = identifier.toCharArray();
		var boolean first = true;
		if ((identifier == null) || (identifier.length == 0)) {
			throw new IllegalArgumentException("The provided identifier '" + identifier + "' is not compatible with the Java naming convention. The identifier is NULL or empty.");
		}
		// name must be a valid Java identifier
		for (char chr : chars) {
			if (first) {
				if (! Character.isJavaIdentifierStart(chr)) {
					throw new IllegalArgumentException("The provided identifier '" + identifier + "' is not compatible with the Java naming convention. The first character is an illegal start character.");
				}
				first = false;
			} else {
				if (! Character.isJavaIdentifierPart(chr)) {
					throw new IllegalArgumentException("The provided identifier '" + identifier + "' is not compatible with the Java naming convention. The character '" + chr + "' is an illegal character.");
				}
			}
		}
		// name must not be a Java keyword, boolean literal, or null literal
		val String[] forbiddenNames = new StringArray("abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
			"continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "if", "goto", 
			"implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", 
			"public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
			"transient", "try", "void", "volatile", "while", "false", "true", "null").getArray();
		for (String str : forbiddenNames) {
			if (str.equals(identifier)) {
				throw new IllegalArgumentException("The provided identifier '" + identifier + "' is not compatible with the Java naming convention. The identifier matches a keyword, boolean literal, or the null literal.");
			}
		}
	}
	
	/**@param packageName of the package. 
	 * @return Directory corresponding to the package, including trailing slash.
	 */
	def packageNameToFolder(String packageName) {
		val String result = packageName.replace('.', '/');
		if (result.endsWith('/')) {
			return result;
		} else {
			return result + '/';
		}
	}
	
	/**@param templateName Name of the template generating the output.
	 * @return generated-Tag for javadoc with name of the template. */
	def statementGenerated(String templateName) {
		'''
		* @generated «templateName» (Xtend)
		'''
	}

	/**@param templateName Name of the class generating the output.
	 * @return generated-Tag for javadoc with name of the class. */
	def statementGenerated(Class<?> callingClass) {
		'''
		* @generated «callingClass.name» (Xtend) 
		'''
	}
	
	/**@param packageInfo Information on the package.
	 * @return Java statement for the package definition.
	 */
	def statementPackage(PackageInfo packageInfo) {
		'''
		«IF (packageInfo.packageName == "" || packageInfo.packageName == null)»
		«ELSE» 
			package «packageInfo.packageName»;
		«ENDIF»
		'''
	} 
	
	/**@param imports Fully qualified names of classes to import.
	 * @return import statements.
	 */
	def statementImports(Set<String> imports) {
		'''
		«FOR String importStmt : imports»
			import «importStmt»;
		«ENDFOR»
		'''
	}
	
	/**Returns if the provided feature has a primitive type. Primitive Types are all non-domain-model types.
	 * @param feature The structural feature.
	 * @return <code>true</code> only if the feature has a primitive type.
	 */
	 def Boolean isPrimitiveType(EStructuralFeature feature) {
	 	if ('byte'.equals(feature.EType.instanceClassName)
	 		|| 'short'.equals(feature.EType.instanceClassName)
	 		|| 'int'.equals(feature.EType.instanceClassName)
	 		|| 'long'.equals(feature.EType.instanceClassName)
	 		|| 'float'.equals(feature.EType.instanceClassName)
	 		|| 'double'.equals(feature.EType.instanceClassName)
	 		|| 'boolean'.equals(feature.EType.instanceClassName)
	 		|| 'char'.equals(feature.EType.instanceClassName)
	 		|| feature.EType.instanceClassName != null) {
	 		return true
	 	} 
		return false; 
	 }

	/**Returns the type of the provided feature.
	 * @param feature The structural feature.
	 * @return The unqualified name.
	 */
	def String getType(EStructuralFeature feature) {
	if (feature instanceof EAttribute) {
			if (feature.getEType != null) {
				if (feature.EType.instanceClassName == null) { // EEnum Data Types
					return feature.EType.name;
				} else {
					if (feature.getEType.instanceClassName.equals('long')) {
						return "Long"
					} else if (feature.getEType.instanceClassName.equals('int')) {
						return 'Integer'
					} else if (feature.getEType.instanceClassName.equals('float')) {
						return 'Float'
					} else if (feature.getEType.instanceClassName.equals('double')) {
						return 'Double'
					} else if (feature.getEType.instanceClassName.equals('byte')) {
						return 'Byte'
					} else if (feature.getEType.instanceClassName.equals('short')) {
						return 'Short'
					} else if (feature.getEType.instanceClassName.equals('char')) {
						return 'Character'
					} else if (feature.getEType.instanceClassName.equals('java.lang.String')) {
						return "String"
					} else if (feature.getEType.instanceClassName.equals('boolean')) {
						return "Boolean"
					} else if (feature.getEType.instanceClassName.equals('java.util.Date')) {
						return "Date"
					} else {
						return feature.getEType.instanceClassName;
					} 
				}
			} else {
				throw new IllegalArgumentException("Type must be provided for each feature. Ensure that the domain model is a valid ecore file. The offending feature was " + feature.name+ ". ");
			}
		} else if (feature instanceof EReference) {
			return feature.getEType.name;
		}
	}
	
	/**
	 * Get all classifiers of the supplied domain package.
	 * @param EPackage the package to get the classifiers for.
	 * @return The list of contained classifiers.
	 */
	def List<EClass> getAllDomainEntities(EPackage domainPackage) {
		var List<EClass> domainClasses = new ArrayList<EClass>;
		for (classifier : domainPackage.getEClassifiers) {
			if (classifier instanceof EClass) domainClasses.add(classifier as EClass);
		}
		return domainClasses;
	}

	/**
	 * Get all references for a List of EClass.
	 * @param EClass The list of EClass.
	 * @return The list of contained references.
	 */
	def List<EReference> getAllReferences(List<EClass> eClasses) {
		var List<EReference> references = new ArrayList<EReference>;
		for (eClass : eClasses) {
		for (structuralFeature : eClass.EAllStructuralFeatures) {
			if (structuralFeature instanceof EReference) references.add(structuralFeature as EReference)
		}
		}
		return references;
	}
	
	/**Returns the attribute of a domain entity which is marked as the identifier of the entity.
	 * @param The domain entity.
	 * @return The attribute or <code>null</code>
	 */
	def EAttribute getAttributeWithAnnotationDatabaseId(EClass entity) {
		for (EAttribute attribute : entity.EAllAttributes) {
			if (getValueForAnnotationAsBoolean(attribute, DatabaseMohitoAnnotationCategory::FIELD_IS_IDENTIFIER) == true ) {
				return attribute;
			}
		}
		return null
	}

	/**Return the textual name of the type of the identifier of a domain entity.
	 * @param entity The entity.
	 * @return The textual name of the type.
	 */
	def String getTypeOfDomainEntityIdentifier(EClass entity) {
		if (getAttributeWithAnnotationDatabaseId(entity) != null) {
			return getType(getAttributeWithAnnotationDatabaseId(entity));
		} else {
			return "String"; 
		}
	}

	/**Import statements for all non-abstract domain entities.
	 * @param packageInfo Information on the package for the domain classes.
	 * @param Domain model.
	 * @return import statements.
	 */
	def statementImportsNonAbstractDomainEntities(PackageInfo packageInfo, EPackage domainModel) {
		'''
		import info.multiplatform.mohito.framework.DaoManager;
		import info.multiplatform.mohito.framework.MohitoEntityDao;
		«FOR entity : domainModel.EClassifiers»
		«IF entity instanceof EClass && !(entity as EClass).abstract»
		import «packageInfo.packageName».«entity.name.toFirstUpper»;
		«ENDIF»
		«ENDFOR»
		'''
	}				
	
	/**Converts the given String to a Set<String>.
	 */
	def Set<String> toSet(String string) {
		val result = new ArrayList<String>(1);
		result.add(string);
		return result.toSet;
	}

	/**Converts the given array to a Set<String>.
	 */
	def Set<String> toSet(String... strings) {
		val result = new ArrayList<String>(1);
		for (String str : strings) {
			result.add(str);
		}
		return result.toSet;
	}
}