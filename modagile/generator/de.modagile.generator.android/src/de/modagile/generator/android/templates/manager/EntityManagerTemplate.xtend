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
package de.modagile.generator.android.templates.manager

import com.google.inject.Inject
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.generator.android.templates.ModagileFolderConstants
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.HashSet
import java.util.Set

class EntityManagerTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
		
	/**
	 * Creates the SuperClass SyncEntity 
	 */
	def generateEntityManager(IFileSystemAccess fsa, PackageInfo packageInfo) {
		fsa.generateFile(packageInfo.getPackageDir + "manager/EntityManager.java", ModagileFolderConstants::SRC_GEN, generateEntityManagerCode(packageInfo))
	}
	
	def private generateEntityManagerCode(PackageInfo packageInfo){
		var Set<String> imports = new HashSet<String>();
		getImports(imports, packageInfo);
		'''
		«generateEntityManagerClass(packageInfo.getPackageName+".manager", imports, null)»
		'''
	}
	
	def private getImports(Set<String> imports, PackageInfo packageInfo){
		imports.add("java.io.Serializable");
		imports.add("java.util.Collection");
		imports.add("android.net.Uri");
		imports.add(packageInfo.getPackageName + ".task.AsyncCallback");
	}
	
	def private generateEntityManagerClass(String packageName, Set<String> imports,
													 							    String inheritance){
		'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.interfaceDecl("EntityManager<T extends Serializable>", inheritance)»
		«generateInterfaceMethods()»
		}
		'''
	}
	
	def private generateInterfaceMethods(){
		'''
		«getById()»
		«getById_Asynchronous()»
		«getByUri()»
		«getByUri_Asynchronous()»
		«getByUuid()»
		«getByUuid_Asynchronous()»
		«getAll()»
		«getAll_Asynchronous()»
		«updateEntity()»
		«updateEntity_Asynchronous()»
		«create()»
		«create_Asynchronous()»
		«delete()»
		«delete_Asynchronous()»
		'''
	}
	
	def private getById(){
		'''
			/**
		 * Get an entity by its id
		 * 
		 * @param entityId
		 *           - id of the entity
		 * @return the entity
		 */
		public abstract T getById(final Long entityId);
		'''
	}
	def private getById_Asynchronous(){
		'''
	    /**
	     * Get an entity by its id asynchron.
	     * 
	     * @param entityId
	     *            - id of the entity
	     * 
	     * @param callback
	     *            - callback for result
	     */
	    public abstract void getById(final Long entityId, final AsyncCallback<T> callback);
		'''
	}	
	
	def private getByUri(){
		'''
		/**
		 * Get an entity by its Uri
		 * 
		 * @param uri
		 *           - Uri pointing to the entity
		 * @return an entity, or null if no entity was found with the given Uri
		 */
		public abstract T getByUri(final Uri uri);
		'''
	}
	
	def private getByUri_Asynchronous(){
		'''
	    /**
	     * Get an entity by its Uri asynchron.
	     * 
	     * @param uri
	     *            - Uri pointing to the entity
	     * @param callback
	     *            - callback for result
	     */
	    public abstract void getByUri(final Uri uri, final AsyncCallback<T> callback);
		'''
	}
	
	def private getByUuid(){
		'''
		/**
		 * Get an entity by its uuid
		 * 
		 * @param uuid
		 *           - UUID of the entity
		 * @return an entity or null
		 */
		public abstract T getByUuid(final String uuid);
		'''
	}
	
	def private getByUuid_Asynchronous(){
		'''
	    /**
	     * Get an entity by its uuid asynchron.
	     * 
	     * @param uuid
	     *            - UUID of the entity
	     * @param callback
	     *            - callback for result
	     */
	    public abstract void getByUuid(final String uuid, final AsyncCallback<T> callback);
		'''
	}
	
	def private getAll(){
		'''
		/**
		 * Get all entities
		 * 
		 * @return - a collection of all entities, collection could be empty
		 */
		public abstract Collection<T> getAll();
		'''		
	}
	
	def private getAll_Asynchronous(){
		'''
	    /**
	     * Get all entities asynchron.
	     * 
	     * @param callback
	     *            - callback for result
	     */
	    public abstract void getAll(final AsyncCallback<Collection<T>> callback);
		'''		
	}
	
	def private updateEntity(){
		'''
		/**
		 * Update an entity.
		 * 
		 * @param entity
		 *           - entity to update
		 */
		public abstract T update(T entity);
		'''
	}
	
	def private updateEntity_Asynchronous(){
		'''
	    /**
	     * Update an entity asynchron.
	     * 
	     * @param entity
	     *            - entity to update
	     * @param callback
	     *            - callback for result
	     */
	    public abstract void update(T entity, final AsyncCallback<T> callback);
		'''
	}
	
	def private create(){
		'''
		/**
		 * Create an entity
		 * 
		 * @param entity
		 */
		public abstract Uri create(final T entity);
		'''
	}
	
	def private create_Asynchronous(){
		'''
	    /**
	     * Create an entity asynchron.
	     * 
	     * @param entity
	     *            - entity to create.
	     * @param callback
	     *            - callback for result.
	     */
	    public abstract void create(final T entity, final AsyncCallback<Uri> callback);
		'''
	}
	
	def private delete(){
		'''
		/**
		 * Delete an entity from the database
		 * 
		 * @param entity
		 */
		public abstract boolean delete(final T entity);
		'''
	}
	
	def private delete_Asynchronous(){
		'''
	    /**
	     * Delete an entity from the database asynchron.
	     * 
	     * @param entity
	     *            - entity to delete.
	     * @param callback
	     *            - callback for result.
	     */
	    public abstract void delete(final T entity, final AsyncCallback<Boolean> callback);
		'''
	}
	
	
}