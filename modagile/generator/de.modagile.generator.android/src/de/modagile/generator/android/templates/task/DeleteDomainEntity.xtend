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
package de.modagile.generator.android.templates.task

import com.google.inject.Inject
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.ArrayList
import info.multiplatform.generator.java.helper.Pair
import info.multiplatform.generator.java.helper.Triple
import de.modagile.generator.android.templates.ModagileFolderConstants
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.HashSet
import java.util.Set

class DeleteDomainEntity {
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateDeleteDomainEntity(IFileSystemAccess fsa, EClass domainEntity, PackageInfo packageInfo){
		fsa.generateFile(packageInfo.getPackageDir+"task/Delete"+ domainEntity.name +".java", 
							ModagileFolderConstants::SRC_GEN, generateClassCode(packageInfo, domainEntity)
		);
	}
	
	def private generateClassCode(PackageInfo packageInfo, EClass domainEntity) {
		'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageInfo.getPackageName+".task")»
		«javaUtilities.importStatements(generateImports(packageInfo))»
		«javaUtilities.classDecl("Delete"+ domainEntity.name, null, null, false)»
		«generateMemberAttributes(domainEntity)»
		«generateConstructor(domainEntity)»
		«generateDeleteDomainEntityMethod(domainEntity)»
		«generateDeleteDomainEntityMethod_Async(domainEntity)»
		«generateAsyncTaskClass(domainEntity)»
		}
		'''
	}
	
	def private generateImports(PackageInfo packageInfo) {
		var Set imports = new HashSet<String>();
		imports.add("android.content.Context");
		imports.add("android.content.ContentUris");
		imports.add("android.content.ContentResolver");
		imports.add("android.net.Uri");
		imports.add("android.os.AsyncTask");
		imports.add("android.util.Log");
		imports.add(packageInfo.packageName+".model.*");
		imports.add(packageInfo.packageName+".constants.DBConstants");
		return imports;
	}
	
	
	def private generateMemberAttributes(EClass domainEntity) {
		'''
		«javaUtilities.generateAdditionalAttributesWithExpression(new Triple("String", "TAG", "Delete" + domainEntity.name + ".class.getSimpleName()"), true, true)»
		«javaUtilities.generateAdditionalAttribute(new Pair("Context", "mContext"), false, true)»
		
		'''
	}

	def private generateConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("Context", "context"))
		'''
		«javaUtilities.generateConstructorDecl("Delete"+ domainEntity.name, params, generateConstructorBody())»
		'''
	}
	
	def private generateConstructorBody() {
		'''
		if (context == null) {
			throw new IllegalArgumentException("Context must not be null!");
		}

		mContext = context;
		'''
	}
	
	def private generateDeleteDomainEntityMethod(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public boolean delete«domainEntityName»(final «domainEntityName» «domainEntityVariable») {
			boolean result = false;

			if («domainEntityVariable» == null) {
				throw new IllegalArgumentException("Paramter must not be null or empty!");
			}

			if («domainEntityVariable».getId() == null) {
				throw new IllegalArgumentException("Id is null! Only a persisted «domainEntityName» can be deleted.");
			}

			final Uri uri = ContentUris.withAppendedId(DBConstants.CONTENT_URI_«domainEntityVariable.toUpperCase», «domainEntityVariable».getId());
			final ContentResolver cResolver = mContext.getContentResolver();

			int resultLocal = cResolver.delete(uri, null, null);
			Log.d(TAG, "Local delete result: " + resultLocal);

			if (resultLocal == 1) {
				result = true;
			} else if (resultLocal > 1) {
				throw new IllegalStateException("Found more than one row to delete. Excactly one row was expected!");
			}

			return result;
		}
	'''
	}	
	
	def private generateDeleteDomainEntityMethod_Async(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public void delete«domainEntityName»Async(final «domainEntityName» «domainEntityVariable», final AsyncCallback<Boolean> callback) {
			Delete«domainEntityName»Task task = new Delete«domainEntityName»Task(callback);
			task.execute(«domainEntityVariable»);
		}
		'''
	}
	
	def private generateAsyncTaskClass(EClass domainEntity) {
		'''
		«javaUtilities.innerClassDecl("private", "Delete"+ domainEntity.name + "Task", "AsyncTask<"+ domainEntity.name+ ", Void, Boolean>", null, false, false)»
		«generateTaskMembers(domainEntity)»
		«generateTaskConstructor(domainEntity)»
		«generateDoInBackgroundMethod(domainEntity)»
		«generateOnPostExecuteMethod(domainEntity)»
		}
		'''
	}

	def private generateTaskMembers(EClass domainEntity) {
		'''
		«javaUtilities.generateAdditionalAttribute(new Pair("AsyncCallback<Boolean>", "mCallback"), false, true)»
		'''
	}
	
	def private generateTaskConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("AsyncCallback<Boolean>", "callback"));
		'''
		«javaUtilities.generateConstructorDecl("Delete"+ domainEntity.name + "Task", params, generateTaskConstructorBody())»
		'''
	}

	def private generateTaskConstructorBody() {
		'''
		if (callback == null) {
			throw new IllegalArgumentException("Callback must not be null!");
		}
		mCallback = callback;
		'''
	}
	
	
	def private generateDoInBackgroundMethod(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		@Override
		protected Boolean doInBackground(final «domainEntityName»... params) {

			if (params == null || params.length != 1) {
				throw new IllegalArgumentException("Paramter must not be null or empty!");
			}

			final «domainEntityName» «domainEntityVariable» = params[0];

			if («domainEntityVariable».getId() == null) {
				throw new IllegalArgumentException("Id is null! Only a persisted «domainEntityName» can be deleted.");
			}

			return delete«domainEntityName»(«domainEntityVariable»);
		}
		'''
	}	
	
	def private generateOnPostExecuteMethod(EClass domainEntity) {
		'''
        @Override
        protected void onPostExecute(final Boolean result) {
            if (mCallback == null) {
            	throw new IllegalStateException("Callback must be set for async task execution!");
            }
            mCallback.onResult(result);
        }
		'''
	}

}