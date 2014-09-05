package info.multiplatform.mohito.generator.storage;

import info.multiplatform.mohito.generator.IStorageGenerator;
import info.multiplatform.mohito.generator.environment.java.PackageInfo;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;

/**
 * Storage generator allowing no storage collection.
 * Useful for testing purposes only.
 * @author groenda
 */
@SuppressWarnings("all")
public class NoStorageGenerator implements IStorageGenerator {
  public CharSequence generateManagerImportStatements(final PackageInfo packageInfo, final EPackage domainModel) {
    return null;
  }
  
  public CharSequence generateManagerEntityDaoCreation(final EPackage domainModel) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("throw new IllegalStateException(\"Not implemented yet.\");");
    _builder.newLine();
    return _builder;
  }
  
  public void generateAdditionalDomainEntityArtifacts(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel) {
  }
  
  public CharSequence generateDomainEntityImportStatements(final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel) {
    return null;
  }
  
  public CharSequence generateDomainEntityClassAnnotationStatement(final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel) {
    return null;
  }
  
  public CharSequence generateDomainEntityStructuralFeatureAnnotationStatement(final PackageInfo packageInfo, final EStructuralFeature feature, final EPackage domainModel) {
    return null;
  }
  
  public void generateAdditionalDomainModelArtifacts(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EPackage domainModel) {
  }
  
  public CharSequence generateAdditionalDomainEntityStatements(final EClass domainEntity) {
    return null;
  }
  
  public CharSequence generateAdditionalManagerEntityDaoStatements(final PackageInfo packageInfo, final EPackage domainModel) {
    return null;
  }
  
  public CharSequence generateAdditionalManagerEntityDaoConstructorStatements(final PackageInfo packageInfo, final EPackage domainModel) {
    return null;
  }
  
  public CharSequence generateDaoManagerImplementsStatement() {
    return null;
  }
  
  public CharSequence generateDomainModelMohitoEntityAttributeAnnotationStatement() {
    return null;
  }
  
  public CharSequence generateDomainModelMohitoEntityAttributeAnnotationImportStatement() {
    return null;
  }
}
