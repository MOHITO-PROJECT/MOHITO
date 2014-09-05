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
import java.util.List
import java.util.ArrayList
import info.multiplatform.generator.java.helper.Pair
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.emf.ecore.EClass
import info.multiplatform.generator.java.templates.PackageInfo
import de.modagile.generator.android.templates.ModagileFolderConstants
import java.util.Set
import java.util.HashSet

class DomainEntityManagerImplTemplate {
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
		
	/**
	 * Creates the SuperClass SyncEntity 
	 */
	def generateDomainEntityManagerImpl(IFileSystemAccess fsa, EClass domainEntity, PackageInfo packageInfo) {
		fsa.generateFile(packageInfo.packageDir + "manager/impl/"+domainEntity.name + "ManagerImpl.java", ModagileFolderConstants::SRC_GEN, generateDomainEntityManagerImplCode(packageInfo, domainEntity))
	}
	
	/*
	 * DomainEntityManger Implementations
	 * */
	def generateDomainEntityManagerImplCode(PackageInfo packageInfo, EClass domainEntity){
		var Set<String> imports = new HashSet<String>();
		var List<String> interfaces = new ArrayList<String>();
		interfaces.add(domainEntity.name+"Manager");
		getDomainEntityManagerImplImports(imports, domainEntity, packageInfo);
	 	'''
	 	«generateDomainEntityManagerImplClass("DomainEntityManagerTemplate", packageInfo.packageName+".manager.impl", imports, interfaces, null, domainEntity)»
	 	'''
	} 
	
	def private getDomainEntityManagerImplImports(Set<String> imports, EClass domainEntity, PackageInfo packageInfo) {
		imports.add("java.util.ArrayList");
		imports.add("java.util.Collection");
		imports.add("java.util.List");
		imports.add("android.content.ContentUris");
		imports.add("android.content.Context");
		imports.add("android.net.Uri");
		imports.add("android.util.Log");
		imports.add(packageInfo.packageName + ".model.*");
		imports.add(packageInfo.packageName  + ".manager." + domainEntity.name + "Manager");
		imports.add(packageInfo.packageName  + ".task.*");
		imports.add(packageInfo.packageName  + ".constants.DBConstants");
	} 
	
	
	def private generateDomainEntityManagerImplClass(String templateName, String packageName, Set<String> imports, List<String> interfaces,
													 							    String inheritance, EClass domainEntity){
		var Pair<String, String> mtCxAttribute =  new Pair<String, String> ("Context","mContext")
	 
	 	'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl(domainEntity.name+"ManagerImpl", inheritance, interfaces, false)»
		«javaUtilities.generateAdditionalAttribute(mtCxAttribute, false, true)»
		«generateInterfaceImplMethods(domainEntity)»
		«generateStaticMethods(domainEntity)»
		}
	 	'''
	}
	
	def private generateInterfaceImplMethods(EClass domainEntity){
	 	'''
		«constructorDecl(domainEntity.name)»
		«getEntityById(domainEntity.name)»
		«getEntityById_Async(domainEntity.name)»
		«getEntityByUri(domainEntity.name)»
		«getEntityByUri_Async(domainEntity.name)»
		«getEntityByUuid(domainEntity.name)»
		«getEntityByUuid_Async(domainEntity.name)»
		«getAllEntities(domainEntity.name)»
		«getAllEntities_Async(domainEntity.name)»
		«getUpdateEntity(domainEntity.name)»
		«getUpdateEntity_Async(domainEntity.name)»
		«getCreateEntity(domainEntity.name)»
		«getCreateEntity_Async(domainEntity.name)»
		«getDeleteEntity(domainEntity.name)»
		«getDeleteEntity_Async(domainEntity.name)»
	 	'''
	 }
	 
	  def constructorDecl(String domainEntityName) {
		'''
		public  «domainEntityName»ManagerImpl(final Context mContext) {
			if (mContext == null) {
			throw new IllegalArgumentException("Context shouldn't be null!");
			}
			this.mContext = mContext;
			}
		'''
	}
	
	def private getEntityById(String entityName) {
		'''
		@Override
		public «entityName» getById(final Long «entityName»ID) {
			if («entityName»ID == null) {
				throw new IllegalArgumentException("«entityName»ID shouldn't be null!");
			}
			final Uri uri = ContentUris.withAppendedId(DBConstants.CONTENT_URI_«entityName.toUpperCase», «entityName»ID);
			return getByUri(uri);
		}
		'''
	}
	
	def private getEntityById_Async(String entityName) {
		'''
	    @Override
	    public void getById(final Long «entityName»ID, final AsyncCallback<«entityName»> callback) {
	        if («entityName»ID == null) {
	            throw new IllegalArgumentException("«entityName»ID shouldn't be null!");
	        }
	        final Uri uri = ContentUris.withAppendedId(DBConstants.CONTENT_URI_«entityName.toUpperCase», «entityName»ID);
	        getByUri(uri, callback);
	    }
		'''
	}
	
	def private getEntityByUri(String entityName) {
		'''
		@Override
		public «entityName» getByUri(final Uri uri) {
			if (uri == null) {
				throw new IllegalArgumentException("uri cannot be null!");
			}
			
			Get«entityName»ByUri task = new Get«entityName»ByUri(mContext);
			return task.get«entityName»ByUri(uri);
		}
		'''				
	}
	
	def private getEntityByUri_Async(String entityName) {
		'''
	    @Override
	    public void getByUri(final Uri uri, final AsyncCallback<«entityName»> callback) {
	        if (uri == null) {
	            throw new IllegalArgumentException("uri cannot be null!");
	        }
	    
	        Get«entityName»ByUri task = new Get«entityName»ByUri(mContext);
	        task.get«entityName»ByUriAsync(uri, callback);
	    }
		'''				
	}
	
	def private getEntityByUuid(String entityName) {
		'''
	    @Override
	    public «entityName» getByUuid(final String uuid) {
	    
	        if (uuid == null) {
	            throw new IllegalArgumentException("«entityName»ID shouldn't be null!");
	        }
	    
	        Get«entityName»ByUuid task = new Get«entityName»ByUuid(mContext);
	        return task.get«entityName»ByUuid(uuid);
	    }
		'''				
	}

	def private getEntityByUuid_Async(String entityName) {
		'''
	    @Override
	    public void getByUuid(final String uuid, final AsyncCallback<«entityName»> callback) {
	        if (uuid == null) {
	            throw new IllegalArgumentException("«entityName»ID shouldn't be null!");
	        }
	    
	        Get«entityName»ByUuid task = new Get«entityName»ByUuid(mContext);
	        task.get«entityName»ByUuidAsync(uuid, callback);
	    }
		'''				
	}	
	def private getAllEntities(String entityName) {
		'''
	    @Override
	    public Collection<«entityName»> getAll() {
	        GetAll«entityName»s task = new GetAll«entityName»s(mContext);
	        return task.getAll«entityName»s();
	    }
		'''
	}

	def private getAllEntities_Async(String entityName) {
		'''
	    @Override
	    public void getAll(final AsyncCallback<Collection<«entityName»>> callback) {
	        GetAll«entityName»s task = new GetAll«entityName»s(mContext);
	        task.getAll«entityName»sAsync(callback);
	    }
		'''
	}
	
	def private getUpdateEntity(String entityName) {
		'''
	    @Override
	    public «entityName» update(«entityName» «entityName.toFirstLower») {
	        Update«entityName» task = new Update«entityName»(mContext);
	        return task.update«entityName»(«entityName.toFirstLower»);
	    }
	    '''
	}
	
	def private getUpdateEntity_Async(String entityName) {
		'''
	    @Override
	    public void update(final «entityName» «entityName.toFirstLower», final AsyncCallback<«entityName»> callback) {
	       Update«entityName» task = new Update«entityName»(mContext);
	       task.update«entityName»Async(«entityName.toFirstLower», callback);
	    }
	    '''
	}
	
	def private getCreateEntity(String entityName) {
		'''
	    @Override
	    public Uri create(final «entityName» «entityName.toFirstLower») {
	        if («entityName.toFirstLower» == null) {
	            throw new IllegalArgumentException("«entityName.toFirstLower» shouldn't be null!");
	        }
	    
	        Create«entityName» task = new Create«entityName»(mContext);
	        return task.create«entityName»(«entityName.toFirstLower»);
	    }
	    '''
	}

	def private getCreateEntity_Async(String entityName) {
		'''
	    @Override
	    public void create(final «entityName» «entityName.toFirstLower», final AsyncCallback<Uri> callback) {
	        Create«entityName» task = new Create«entityName»(mContext);
	        task.create«entityName»Async(«entityName.toFirstLower», callback);
	    }
	    '''
	}
	
	def private getDeleteEntity(String entityName) {
		'''
	    @Override
	    public boolean delete(final «entityName» «entityName.toFirstLower») {
	        // input validation
	        if («entityName.toFirstLower» == null) {
	            throw new IllegalArgumentException("«entityName» to be deleted cannot be null");
	        }
	    
	        if («entityName.toFirstLower».getId() == null) {
	            throw new IllegalArgumentException("Id is null! Only a persisted «entityName» can be deleted.");
	        }
	    
	        Delete«entityName» task = new Delete«entityName»(mContext);
	        return task.delete«entityName»(«entityName.toFirstLower»);
	    }
	    '''
	}
	
	def private getDeleteEntity_Async(String entityName) {
		'''
	    @Override
	    public void delete(final «entityName» «entityName.toFirstLower», final AsyncCallback<Boolean> callback) {
	        // input validation
	        if («entityName.toFirstLower» == null) {
	            throw new IllegalArgumentException("«entityName» to be deleted cannot be null");
	        }
	    
	        if («entityName.toFirstLower».getId() == null) {
	            throw new IllegalArgumentException("Id is null! Only a persisted «entityName» can be deleted.");
	        }
	    
	        Delete«entityName» task = new Delete«entityName»(mContext);
	        task.delete«entityName»Async(«entityName.toFirstLower», callback);
	    }
	    '''
	}
	
	def private generateStaticMethods(EClass domainEntity) {
		'''
	    
	    '''
	}
	

}