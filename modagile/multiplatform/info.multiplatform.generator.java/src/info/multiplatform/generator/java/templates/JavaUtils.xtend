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
package info.multiplatform.generator.java.templates

import com.google.inject.Inject
import info.multiplatform.generator.java.helper.Pair
import info.multiplatform.generator.java.helper.Triple
import java.util.ArrayList
import java.util.List
import java.util.UUID
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import java.util.Set
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.common.util.EList

class JavaUtils {
	
	@Inject extension UtilityFunctions utilities
	
	def packageNameToFolder(String packageName) {
		return packageName.replace('.', '/') + '/'
	}
	
	def statementGenerated(String templateName) '''
	/* generated by «templateName» (xtend) */ 
	'''

	def statementGenerated(Class callingClass) '''
	/* generated by «callingClass.name» (xtend) */ 
	'''
	
	def packageStatement(String packageName) {
		'''
		«IF (packageName == "" || packageName == null)»
		
		«ELSE» 
			package «packageName»;
		«ENDIF»
		'''
	} 
	
	def importStatements(Set<String> imports) {
		'''
		«FOR String importStmt : imports»
			import «importStmt»;
		«ENDFOR»
		'''
	}
	
	def classDecl(String className, String inheritance, List<String> interfaces, boolean isAbstract) {
	'''
	public «IF isAbstract» abstract «ENDIF»class «className»
	 «IF inheritance != null && !"".equals(inheritance)»
	 extends «inheritance»
	 «ENDIF» «IF interfaces != null && !"".equals(interfaces)»«utilities.commaSeparated('implements ', interfaces)»«ENDIF» {
	
	'''
	}
	
	def innerClassDecl(String visibility, String className, String inheritance, List<String> interfaces, boolean isStatic, boolean isAbstract){
		'''
		«visibility» «IF isAbstract»abstract«ENDIF» «IF isStatic» static «ENDIF» class «className»
		 «IF inheritance != null && !"".equals(inheritance)»
		 extends «inheritance»
		 «ENDIF» «IF interfaces != null && !"".equals(interfaces)»«utilities.commaSeparated('implements ', interfaces)»«ENDIF» {
		
		'''
	}
	
	def interfaceDecl(String className, String inheritanceInterface) {
	'''
	public interface «className» «IF inheritanceInterface != null && !"".equals(inheritanceInterface)» extends «inheritanceInterface»
	«ENDIF»	
	{
	
	'''
	}
				
	def generateConstructorDecl(String className, EList<EAttribute> attributes, Boolean inherited ){
		//generateConstructorDecl(className, arguments, null);
		'''
		«IF inherited»
		public «className» (String id) {
			super(id); 
		}
		«ENDIF»
		
		«var int counter = 0»
		public «className» ( String id, «FOR att: attributes»«getType(att)» «att.name» 
		«IF ( (counter = counter + 1) < attributes.size )» , 
		«ENDIF»
		«ENDFOR») 
		«counter =  0»
		{
			super(id, «FOR att: attributes»«getType(att)»«att.name» 
			«ENDFOR»
			«FOR att: attributes»
			this.«att.name» = «att.name» 
			«IF ( (counter = counter + 1) < attributes.size )» ,
			«ENDIF»
			«ENDFOR»
		}
		'''
	}
	
	def generateConstructorDecl(String className, List<Pair<String, String>> arguments, CharSequence constrBody){
		'''
		«var int counter = 0»
		public «className»( 
			«IF arguments != null»
				«FOR Pair<String, String> pairIter : arguments »
					«pairIter.firstElement» «pairIter.secondElement»
					«IF ( (counter = counter + 1) < arguments.size )» ,
					«ENDIF»
				«ENDFOR»
			«ENDIF») {
			«IF constrBody != null» 
				«constrBody»
			«ENDIF»
		}
		'''
	}
	
	def generateNonCompositeBody(EReference classifier){
		'''
			private String «classifier.EType.name.toLowerCase»UUID;
			private String «classifier.name»UUID;
			
			public void set«classifier.EType.name.toLowerCase.toFirstUpper»UUID(String «classifier.EType.name.toLowerCase»UUID) {
				this.«classifier.EType.name.toLowerCase»UUID = «classifier.EType.name.toLowerCase»UUID;
			}
			
			public String get«classifier.EType.name.toLowerCase.toFirstUpper»UUID() {
				return this.«classifier.EType.name.toLowerCase»UUID;
			}
			
			public void set«classifier.name.toFirstUpper»UUID(String «classifier.name»UUID) {
				this.«classifier.name»UUID = «classifier.name»UUID;				
			}
			
			public String get«classifier.name.toFirstUpper»UUID() {
				return this.«classifier.name»UUID;
			}
			'''
	}
	
	/**
	 * Writes the constants into the stream
	 */
	def generateCodeForConstants(List<Triple<String, String, String>> constants) {
		'''
		«FOR Triple<String, String, String> constant: constants»
		«IF !"int".equals(constant.firstElement)»
		 public static final «constant.firstElement» «constant.secondElement» «if(constant.thirdElement != null) "= "+constant.thirdElement»;
		 «ELSE»
		  public static final «constant.firstElement» «constant.secondElement» «if(constant.thirdElement != null) '= ' + constant.thirdElement»;
		 «ENDIF»
		 «ENDFOR»		
		'''
	}
	
	def generateGetterSetter(EStructuralFeature att) {
		if (att.upperBound == -1) {
			generateGetterSetterToMany(att)
		} else {
			generateGetterSetterToOne(att)
		}
	}
	
	def generateGetterSetterToOne(EStructuralFeature att) {
	'''public «getType(att)» get«att.name.charAt(0).toString.toUpperCase»«att.name.substring(1, att.name.length)»() {
			return «att.name»;
		}
			
		public void set«att.name.charAt(0).toString.toUpperCase»«att.name.substring(1, att.name.length)»(«getType(att)» «att.name») {
			this.«att.name» = «att.name»;
		}
	'''
	}
	
	def generateGetterSetterToMany(EStructuralFeature att) {
	'''public List<«getType(att)»> get«att.name.charAt(0).toString.toUpperCase»«att.name.substring(1, att.name.length)»() {
			return «att.name»;
		}
			
		public void set«att.name.charAt(0).toString.toUpperCase»«att.name.substring(1, att.name.length)»(List<«getType(att)»> «att.name») {
			this.«att.name» = «att.name»;
		}
	'''
	}
	
	def generateCodeForAttributeOrReference(EStructuralFeature att) {
		'''«System::out.println('Entry: generateCodeForAttributeOrReference ' + att.name)»
		«IF att.upperBound == -1»
			private List<«getType(att)»> «att.name»;
		«ELSE»
			private «getType(att)» «att.name»;
		«ENDIF»
		'''
	}
	
	def generateCodeForAttribute(String attType, String attName, boolean isFinal, String expr) {
		'''«System::out.println('Entry: generateCodeForAttribute ' + attName)»
		private «if(isFinal) 'final'» «attType» «attName» «if(expr != null) '= ' + expr»;
		'''
	}
	
	def generateAdditionalAttributes(List<Pair<String, String>> pairs) {
	'''
	«FOR Pair<String, String> pair : pairs»
		private «pair.firstElement» «pair.secondElement»;
	«ENDFOR»
	'''
	} 
	
	def generateAdditionalAttributes(List<Pair<String, String>> pairs, boolean staticQualifier, boolean finalQualifier) {
	'''
	«FOR Pair<String, String> pair : pairs»
		private «IF staticQualifier» static «ENDIF» «IF finalQualifier» final «ENDIF» «pair.firstElement» «pair.secondElement»;
	«ENDFOR»
	'''
	} 
	def generateAdditionalAttribute(Pair<String, String> pair, boolean staticQualifier, boolean finalQualifier) {
	'''
		private «IF staticQualifier» static «ENDIF» «IF finalQualifier» final «ENDIF» «pair.firstElement» «pair.secondElement»;
	'''
	} 
	
	def generateAdditionalAttributesWithExpression(Triple<String, String, String> triple, boolean staticQualifier, boolean finalQualifier){
		'''
		private «IF staticQualifier» static «ENDIF» «IF finalQualifier» final «ENDIF» «triple.firstElement» «triple.secondElement» «IF triple.thirdElement != null» = «triple.thirdElement» «ENDIF»;
	'''
	}

	def String getType(EStructuralFeature att) {
		if (att instanceof EAttribute) {
			if (att.EType.instanceClassName.equals('long')){
				return "long"
			} else if (att.EType.instanceClassName.equals('int')) {
				return 'int'
			} else if (att.EType.instanceClassName.equals('float')) {
				return 'float'
			} else if (att.EType.instanceClassName.equals('double')) {
				return 'double'
			} else if (att.EType.instanceClassName.equals('java.lang.String')) {
				return "String"
			} else if(att.EType.instanceClassName.equals('boolean')) {
				return "boolean"
			} else if(att.EType.instanceClassName.equals('java.util.Date')) {
				return "Date"
			} else if(att.EType.instanceClassName.equals('byte[]')) {
				return "byte[]";
			} else {
				return att.EType.instanceClassName;
			}
		} else if (att instanceof EReference) {
			return att.EType.name;
		}
	}
	
	/**
	 * Get all classifiers of the supplied domain package.
	 * @param EPackage the package to get the classifiers for.
	 * @return The list of contained classifiers.
	 */
	def List<EClass> getAllDomainEntities(EPackage domainPackage) {
		var List<EClass> domainClasses = new ArrayList<EClass>;
		for (classifier : domainPackage.EClassifiers) {
			if (classifier instanceof EClass) domainClasses.add(classifier as EClass);
		}
		return domainClasses;
	}
	
	/**
	 * returns a list of all references that need to be taken into account when 
	 * creating mapping classes.
	 * Only non-containing references with an upper bound greater the one are returned.
	 * TODO Why? 
	 * 
	 * @param domainPackage The package of domain elements to be checked.
	 * @return The list of identified references
	 */ 
	def List<EReference> getMappingReferences(EPackage domainPackage) {
		var List<EReference> referenceList = new ArrayList<EReference>;
		for (classifier : domainPackage.EClassifiers) {
			if (classifier instanceof EClass) {
				var EClass clazz = classifier as EClass;
				for (ref : clazz.EAllReferences) {
					// is it a non-composite * relation?
					if (!ref.containment && ref.upperBound > 1) {
						if (!referenceList.contains(ref) && !referenceList.contains(ref.EOpposite)) {
							referenceList.add(ref);
						}
					}
				}
			} 
		}
		return referenceList;		
	}

	/**
	 * Get the list of all domain entity names including 
	 * data classes and references.
	 */
	def List<String> getAllDomainEntityNames(EPackage domainPackage) {
		var List<String> domainClassNames = new ArrayList<String>;
		for (classifier : getAllDomainEntities(domainPackage)) {
			domainClassNames.add(classifier.name);
		}
		for (ref : getMappingReferences(domainPackage)) {
			domainClassNames.add(ref.name);
		}
		return domainClassNames;
	}  
	
	def gernerateSerializeableID(String packageName, String projectName, String entitiyName ) {
		'''
		private static final long serialVersionUID = 
		«UUID::nameUUIDFromBytes((packageName+ projectName + entitiyName).bytes).mostSignificantBits»L ;
		'''
	} 
}