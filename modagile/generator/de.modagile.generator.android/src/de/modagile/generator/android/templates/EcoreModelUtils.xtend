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
package de.modagile.generator.android.templates

import org.eclipse.emf.ecore.EReference
import java.util.List
import org.eclipse.emf.ecore.EClass
import java.util.ArrayList
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EcorePackage

class EcoreModelUtils {
	
	/* Checks whether an EReference is a Many-To-Many Reference */
	def public boolean isManytoManyReference(EReference reference) {
	   	if ((reference.EOpposite != null) &&
	   	    (reference.upperBound != 1) &&
	   	    (reference.EOpposite.upperBound != 1)) {
	   		return true;
	   	} else {
	   		return false;
	   	}
    }
	
	/* Returns only outgoing To-One EReferences */
	def public Iterable<EReference> outgoingToOneEReferences (EClass currentEClass) {
		return currentEClass.EReferences.filter(e | e.upperBound == 1);
	}
	
	/* Checks all EClasses for potential incoming EReferences to the EClass currentEClass */
	def public List<EReference> incomingOneToManyEReferences (EClass currentEClass, List<EClass> allEClasses) {
		var List<EReference> incomingOneToManyEReferences = new ArrayList<EReference>();
		for (potentialReferencingEClass : allEClasses) {
			for (potentialIncomingEReference : potentialReferencingEClass.EReferences) {
				if (potentialIncomingEReference.EReferenceType.equals(currentEClass)) {
					if ((potentialIncomingEReference.upperBound != 1) &&
						 (potentialIncomingEReference.EOpposite == null)) {
						incomingOneToManyEReferences.add(potentialIncomingEReference);
					}	
				}
			}
		}
		return incomingOneToManyEReferences;
	}

	/* Provides a readable name for incoming EReferences */
	def public String incomingReferenceName(EReference incomingRef) {
		return incomingRef.name.toUpperCase + "_INVERSE"
	}

	/* Provides a readable name for incoming EReferences */
	def public String manyToManyReferenceTableName(EReference manyToManyRef) {
		return manyToManyRef.EContainingClass.name + "_" + manyToManyRef.EOpposite.EContainingClass.name + "_" + "Mapping"
	}

	/* Provides a readable name for incoming EReferences */
	def public String manyToManyReferenceClassName(EReference manyToManyRef) {
		return "" + manyToManyRef.EContainingClass.name.toFirstUpper + manyToManyRef.EOpposite.EContainingClass.name.toFirstUpper + "Mapping"
	}
	
	/* Provides a list of unique many-to-many EReferences (without their opposites) */
	def public List<EReference> uniqueManyToManyEReferences(List<EClass> allEClasses) {
		var List<EReference> manyToManyEReferences = new ArrayList<EReference>();
		for (potentialReferencingEClass : allEClasses) {
			for (potentialManyToManyEReference : potentialReferencingEClass.EReferences) {
				if (potentialManyToManyEReference.manytoManyReference) {
					if (potentialManyToManyEReference.EContainingClass.name.compareToIgnoreCase(
						potentialManyToManyEReference.EOpposite.EContainingClass.name
					) < 0) {
						manyToManyEReferences.add(potentialManyToManyEReference);			
					}
 				}
			}
		}
		return manyToManyEReferences;
	}
	
	def public EClass manyToManyEReferenceMappingEntity (EReference ref) {
		var EClass mappingEntity = EcoreFactory::eINSTANCE.createEClass;
		mappingEntity.setName(ref.manyToManyReferenceClassName);
		var EAttribute sourceAttribute = EcoreFactory::eINSTANCE.createEAttribute;
		sourceAttribute.setName(ref.EContainingClass.name.toLowerCase + "_id");
		sourceAttribute.setEType(EcorePackage::eINSTANCE.EInt);
		mappingEntity.EStructuralFeatures.add(sourceAttribute);
		var EAttribute targetAttribute = EcoreFactory::eINSTANCE.createEAttribute;
		targetAttribute.setName(ref.EOpposite.EContainingClass.name.toLowerCase + "_id");
		targetAttribute.setEType(EcorePackage::eINSTANCE.EInt);
		mappingEntity.EStructuralFeatures.add(targetAttribute);
		return mappingEntity
	}
   	  
}