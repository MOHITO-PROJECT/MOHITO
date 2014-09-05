package info.multiplatform.mohito.generator.storage

import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import info.multiplatform.mohito.generator.IStorageGenerator
import info.multiplatform.mohito.generator.environment.java.PackageInfo

/**Storage generator allowing no storage collection.
 * Useful for testing purposes only.
 * @author groenda
 */
class NoStorageGenerator implements IStorageGenerator {

	override CharSequence generateManagerImportStatements(PackageInfo packageInfo, EPackage domainModel) {
		// no import statements required if no storage is used.
	}
	
	override CharSequence generateManagerEntityDaoCreation(EPackage domainModel) {
		'''
		throw new IllegalStateException("Not implemented yet.");
		'''
	}
	
	override generateAdditionalDomainEntityArtifacts(IFileSystemAccess fsa, PackageInfo packageInfo, EClass domainEntity, EPackage domainModel) {
		// Nothing needs to be generated
	}
	
	override generateDomainEntityImportStatements(PackageInfo packageInfo, EClass domainEntity, EPackage domainModel) {
		// no import statements required if no storage is used.
	}
	
	override generateDomainEntityClassAnnotationStatement(PackageInfo packageInfo, EClass domainEntity, EPackage domainModel) {
		// no class annotation statements required if no storage is used.
	}
	
	override generateDomainEntityStructuralFeatureAnnotationStatement(PackageInfo packageInfo, EStructuralFeature feature, EPackage domainModel) {
		// no structural feature annotation statements required if no storage is used.
	}
	
	override generateAdditionalDomainModelArtifacts(IFileSystemAccess fsa, PackageInfo packageInfo, EPackage domainModel) {
		// Nothing needs to be generated
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
		// Nothing needs to be generated
	}
	
	override generateDomainModelMohitoEntityAttributeAnnotationImportStatement() {
		// Nothing needs to be generated
	}

}