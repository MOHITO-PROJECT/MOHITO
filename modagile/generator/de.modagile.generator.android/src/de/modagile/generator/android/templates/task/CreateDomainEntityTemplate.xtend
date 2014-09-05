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

class CreateDomainEntityTemplate {
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateCreateDomainEntity(IFileSystemAccess fsa, EClass domainEntity, PackageInfo packageInfo){
		fsa.generateFile(packageInfo.getPackageDir+"task/Create"+ domainEntity.name +".java", 
							ModagileFolderConstants::SRC_GEN, generateClassCode(packageInfo, domainEntity)
		);
	}
	
	def private generateClassCode(PackageInfo packageInfo, EClass domainEntity) {
		'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageInfo.getPackageName+".task")»
		«javaUtilities.importStatements(generateImports(packageInfo))»
		«javaUtilities.classDecl("Create"+ domainEntity.name, null, null, false)»
		«generateMemberAttributes(domainEntity)»
		«generateConstructor(domainEntity)»
		«generateCreateMethod(domainEntity)»
		«generateCreateMethod_Async(domainEntity)»
		«generateAsyncTaskClass(domainEntity)»
		}
		'''
	}
	
	def private generateImports(PackageInfo packageInfo) {
		var Set imports = new HashSet<String>();
		imports.add("android.content.Context");
		imports.add("android.content.ContentValues");
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
		«javaUtilities.generateAdditionalAttributesWithExpression(new Triple("String", "TAG", "Create" + domainEntity.name + ".class.getSimpleName()"), true, true)»
		«javaUtilities.generateAdditionalAttribute(new Pair("Context", "mContext"), false, false)»
		
		'''
	}

	def private generateConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("Context", "context"))
		'''
		«javaUtilities.generateConstructorDecl("Create"+ domainEntity.name, params, generateConstructorBody())»
		'''
	}
	
	def private generateConstructorBody() {
		'''
		if (context == null) {
			throw new IllegalArgumentException("Context and callback must not be null!");
		}

		mContext = context;
		'''
	}
	

	def private generateCreateMethod(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public Uri create«domainEntityName»(final «domainEntityName» «domainEntityVariable») {
		Uri uri = null;

		final ContentResolver cResolver = mContext.getContentResolver();

		if («domainEntityVariable» == null) {
			throw new IllegalStateException("«domainEntityName» must not be null!");
		} else {
			try {

			if («domainEntityVariable».getId() != null) {
				throw new IllegalArgumentException(
					"ID is not null! Create does not handle already persisted objects!");
			}

			final ContentValues values = «domainEntityName»Converter.«domainEntityVariable»ToValues(«domainEntityVariable»);

			uri = cResolver.insert(DBConstants.CONTENT_URI_«domainEntityName.toUpperCase», values);

			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
		return uri;
		}
		'''
	}	
	
	def private generateCreateMethod_Async(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public void create«domainEntityName»Async(final «domainEntityName» «domainEntityVariable», final AsyncCallback<Uri> callback) {
			Create«domainEntityName»Task task = new Create«domainEntityName»Task(mContext, callback);
			task.execute(«domainEntityVariable»);
		}
		'''
	}
	
	def private generateAsyncTaskClass(EClass domainEntity) {
		'''
		«javaUtilities.innerClassDecl("private", "Create"+ domainEntity.name + "Task", "AsyncTask<"+domainEntity.name +", Void, Uri>", null, false, false)»
		«generateTaskMembers(domainEntity)»
		«generateTaskConstructor(domainEntity)»
		«generateDoInBackgroundMethod(domainEntity)»
		«generateOnPostExecuteMethod()»
		«generateCreatePersonSyncMethod(domainEntity)»
		}
		'''
	}

	def private generateTaskMembers(EClass domainEntity) {
		'''
		«javaUtilities.generateAdditionalAttributesWithExpression(new Triple("String", "TAG", "Create" + domainEntity.name + "Task.class.getSimpleName()"), false, true)»
		«javaUtilities.generateAdditionalAttribute(new Pair("Context", "mContext"), false, true)»
		«javaUtilities.generateAdditionalAttribute(new Pair("AsyncCallback<Uri>", "mCallback"), false, true)»
		'''
	}
	
	def private generateTaskConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("Context", "context"));
		params.add(new Pair("AsyncCallback<Uri>", "callback"));
		'''
		«javaUtilities.generateConstructorDecl("Create"+ domainEntity.name + "Task", params, generateTaskConstructorBody())»
		'''
	}

	def private generateTaskConstructorBody() {
		'''
		if (context == null || callback == null) {
			throw new IllegalArgumentException("Context and callback must not be null!");
		}

		mContext = context;
		mCallback = callback;
		'''
	}
	
	
	def private generateDoInBackgroundMethod(EClass domainEntity) {
		'''
		@Override
		protected Uri doInBackground(«domainEntity.name»... params) {
			return create«domainEntity.name»Sync(params);
		}
		'''
	}	
	
	def private generateOnPostExecuteMethod() {
		'''
		@Override
		protected void onPostExecute(Uri result) {
			if (mCallback == null) {
				return;
			}
			mCallback.onResult(result);
		}
		'''
	}

	def private generateCreatePersonSyncMethod(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public Uri create«domainEntityName»Sync(«domainEntityName»... params) {
			Uri uri = null;

			if (params == null || params.length != 1) {
				throw new IllegalArgumentException("Paramter must not be null or empty!");
			}

			final ContentResolver cResolver = mContext.getContentResolver();

			final «domainEntityName» «domainEntityVariable» = params[0];

			if («domainEntityVariable» == null) {
				throw new IllegalStateException("«domainEntityName» must not be null!");
			} else {
				try {

					if («domainEntityVariable».getId() != null) {
						throw new IllegalArgumentException(
							"ID is not null! Create does not handle already persisted objects!");
					}
					final ContentValues values = «domainEntityName»Converter.«domainEntityVariable»ToValues(«domainEntityVariable»);

					uri = cResolver.insert(DBConstants.CONTENT_URI_«domainEntityName.toUpperCase», values);

				} catch (Exception e) {
					Log.e(TAG, e.getMessage(), e);
				}
			}
			return uri;
		}
		'''
	}
	
}