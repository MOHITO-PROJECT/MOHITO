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
package info.multiplatform.mohito.generator

import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import info.multiplatform.mohito.generator.environment.java.PackageInfo

interface IStorageGenerator {
	// Domain Model
	/**Generates additional artifacts (if required) in order to handle the domain model.
	 * @param fsa Access to the file system.
	 * @param packageInfo Information on the target package for implementations.
	 * @param domainModel The domain model.
	 */
	def void generateAdditionalDomainModelArtifacts(IFileSystemAccess fsa, PackageInfo packageInfo, EPackage domainModel);
	
	/** Generates the annotations for the MOHITO management information stored in [DomainModel]MohitoEntity. */
	def CharSequence generateDomainModelMohitoEntityAttributeAnnotationStatement();
	/** Generates the import statements for the annotations for the MOHITO management information stored in [DomainModel]MohitoEntity. */
	def CharSequence generateDomainModelMohitoEntityAttributeAnnotationImportStatement();

	// Domain Entities
	/**Generates import statements for the provided domain entity.
	 * @param packageInfo Information on the package of the domain entity.
	 * @param domainEntity The domain entity.
	 * @param domainModel The domain model.
	 */
	def CharSequence generateDomainEntityImportStatements(PackageInfo packageInfo, EClass domainEntity, EPackage domainModel);

	/**Generates annotation statements for the implementation class of the domain entity.
	 * @param packageInfo Information on the package of the domain entity.
	 * @param domainEntity The domain entity.
	 * @param domainModel The domain model.
	 */
	def CharSequence generateDomainEntityClassAnnotationStatement(PackageInfo packageInfo, EClass domainEntity, EPackage domainModel);

	/**Generates annotation statements for the implementation attribute of the structural feature.
	 * @param packageInfo Information on the package of the domain entity.
	 * @param feature The feature.
	 * @param domainModel The domain model.
	 */
	def CharSequence generateDomainEntityStructuralFeatureAnnotationStatement(PackageInfo packageInfo, EStructuralFeature feature, EPackage domainModel);
	
	/**Generates additional artifacts (if required) in order to handle a domain entity.
	 * @param fsa Access to the file system.
	 * @param packageInfo Information on the target package for implementations.
	 * @param domainEntity The domain entity.
	 * @param domainModel The domain model.
	 */
	def void generateAdditionalDomainEntityArtifacts(IFileSystemAccess fsa, PackageInfo packageInfo, EClass domainEntity, EPackage domainModel);
	
	// StorageManager
	/** Generates the import statements used in the storage manager. */
	def CharSequence generateManagerImportStatements(PackageInfo packageInfo, EPackage domainModel);
	
	/** Generates the statements used to create entity DAOs in the storage manager.*/
	def CharSequence generateManagerEntityDaoCreation(EPackage domainModel);
	
	/** Generates additional statements of the entity DAO manager.*/
	def CharSequence generateAdditionalManagerEntityDaoStatements(PackageInfo packageInfo, EPackage domainModel);
	
	/** Generates additional statements of the entity DAO manager.*/
	def CharSequence generateAdditionalManagerEntityDaoConstructorStatements(PackageInfo packageInfo, EPackage domainModel);
	
	/** Generates additional domain entity statements */
	def CharSequence generateAdditionalDomainEntityStatements(EClass domainEntity);
	
	/** Generates an optional implements statement */
	def CharSequence generateDaoManagerImplementsStatement();
}