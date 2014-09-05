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
package info.multiplatform.mohito.generator;

import info.multiplatform.mohito.generator.environment.java.PackageInfo;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.generator.IFileSystemAccess;

@SuppressWarnings("all")
public interface IStorageGenerator {
  /**
   * Generates additional artifacts (if required) in order to handle the domain model.
   * @param fsa Access to the file system.
   * @param packageInfo Information on the target package for implementations.
   * @param domainModel The domain model.
   */
  public abstract void generateAdditionalDomainModelArtifacts(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EPackage domainModel);
  
  /**
   * Generates the annotations for the MOHITO management information stored in [DomainModel]MohitoEntity.
   */
  public abstract CharSequence generateDomainModelMohitoEntityAttributeAnnotationStatement();
  
  /**
   * Generates the import statements for the annotations for the MOHITO management information stored in [DomainModel]MohitoEntity.
   */
  public abstract CharSequence generateDomainModelMohitoEntityAttributeAnnotationImportStatement();
  
  /**
   * Generates import statements for the provided domain entity.
   * @param packageInfo Information on the package of the domain entity.
   * @param domainEntity The domain entity.
   * @param domainModel The domain model.
   */
  public abstract CharSequence generateDomainEntityImportStatements(final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel);
  
  /**
   * Generates annotation statements for the implementation class of the domain entity.
   * @param packageInfo Information on the package of the domain entity.
   * @param domainEntity The domain entity.
   * @param domainModel The domain model.
   */
  public abstract CharSequence generateDomainEntityClassAnnotationStatement(final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel);
  
  /**
   * Generates annotation statements for the implementation attribute of the structural feature.
   * @param packageInfo Information on the package of the domain entity.
   * @param feature The feature.
   * @param domainModel The domain model.
   */
  public abstract CharSequence generateDomainEntityStructuralFeatureAnnotationStatement(final PackageInfo packageInfo, final EStructuralFeature feature, final EPackage domainModel);
  
  /**
   * Generates additional artifacts (if required) in order to handle a domain entity.
   * @param fsa Access to the file system.
   * @param packageInfo Information on the target package for implementations.
   * @param domainEntity The domain entity.
   * @param domainModel The domain model.
   */
  public abstract void generateAdditionalDomainEntityArtifacts(final IFileSystemAccess fsa, final PackageInfo packageInfo, final EClass domainEntity, final EPackage domainModel);
  
  /**
   * Generates the import statements used in the storage manager.
   */
  public abstract CharSequence generateManagerImportStatements(final PackageInfo packageInfo, final EPackage domainModel);
  
  /**
   * Generates the statements used to create entity DAOs in the storage manager.
   */
  public abstract CharSequence generateManagerEntityDaoCreation(final EPackage domainModel);
  
  /**
   * Generates additional statements of the entity DAO manager.
   */
  public abstract CharSequence generateAdditionalManagerEntityDaoStatements(final PackageInfo packageInfo, final EPackage domainModel);
  
  /**
   * Generates additional statements of the entity DAO manager.
   */
  public abstract CharSequence generateAdditionalManagerEntityDaoConstructorStatements(final PackageInfo packageInfo, final EPackage domainModel);
  
  /**
   * Generates additional domain entity statements
   */
  public abstract CharSequence generateAdditionalDomainEntityStatements(final EClass domainEntity);
  
  /**
   * Generates an optional implements statement
   */
  public abstract CharSequence generateDaoManagerImplementsStatement();
}
