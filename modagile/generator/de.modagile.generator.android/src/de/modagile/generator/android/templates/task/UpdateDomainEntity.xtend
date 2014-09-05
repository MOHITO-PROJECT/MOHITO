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

class UpdateDomainEntity {
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateUpdateDomainEntity(IFileSystemAccess fsa, EClass domainEntity, PackageInfo packageInfo){
		fsa.generateFile(packageInfo.getPackageDir+"task/Update"+ domainEntity.name +".java", 
							ModagileFolderConstants::SRC_GEN, generateClassCode(packageInfo, domainEntity)
		);
	}
	
	def private generateClassCode(PackageInfo packageInfo, EClass domainEntity) {
		'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageInfo.getPackageName+".task")»
		«javaUtilities.importStatements(generateImports(packageInfo))»
		«javaUtilities.classDecl("Update"+ domainEntity.name, null, null, false)»
		«generateMemberAttributes(domainEntity)»
		«generateConstructor(domainEntity)»
		«generateUpdateDomainEntityMethod(domainEntity)»
		«generateUpdateDomainEntityMethod_Async(domainEntity)»
		«generateAsyncTaskClass(domainEntity)»
		}
		'''
	}
	
	def private generateImports(PackageInfo packageInfo) {
		var Set imports = new HashSet<String>();
		imports.add("android.content.Context");
		imports.add("android.content.ContentUris");
		imports.add("android.content.ContentResolver");
		imports.add("android.content.ContentValues");
		imports.add("android.database.SQLException");
		imports.add("android.net.Uri");
		imports.add("android.os.AsyncTask");
		imports.add("android.util.Log");
		imports.add(packageInfo.packageName+".model.*");
		imports.add(packageInfo.packageName+".constants.DBConstants");
		return imports;
	}
	
	
	def private generateMemberAttributes(EClass domainEntity) {
		'''
		«javaUtilities.generateAdditionalAttributesWithExpression(new Triple("String", "TAG", "Update" + domainEntity.name + ".class.getSimpleName()"), true, true)»
		«javaUtilities.generateAdditionalAttribute(new Pair("Context", "mContext"), false, true)»
		
		'''
	}

	def private generateConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("Context", "context"))
		'''
		«javaUtilities.generateConstructorDecl("Update"+ domainEntity.name, params, generateConstructorBody())»
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
	
	def private generateUpdateDomainEntityMethod(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public «domainEntityName» update«domainEntityName»(final «domainEntityName» «domainEntityVariable») {
		if («domainEntityVariable» == null) {
			throw new IllegalArgumentException("Paramter must not be null or empty!");
		}

		final ContentResolver cResolver = mContext.getContentResolver();
		final ContentValues values = «domainEntityName»Converter.«domainEntityVariable»ToValues(«domainEntityVariable»);

		final Uri uri = ContentUris.withAppendedId(DBConstants.CONTENT_URI_«domainEntityName.toUpperCase», «domainEntityVariable».getId());

		final int updatedRows = cResolver.update(uri, values, null, null);

		if (updatedRows < 1) {
			Log.d(TAG, "«domainEntityName» update failed!");
			throw new SQLException("Update failed");
		}

		mContext.getContentResolver().notifyChange(uri, null);

		return «domainEntityVariable»;
		}
	'''
	}	
	
	def private generateUpdateDomainEntityMethod_Async(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public void update«domainEntityName»Async(final «domainEntityName» «domainEntityVariable», final AsyncCallback<«domainEntityName»> callback) {
			Update«domainEntityName»Task task = new Update«domainEntityName»Task(callback);
			task.execute(«domainEntityVariable»);
		}
		'''
	}
	
	def private generateAsyncTaskClass(EClass domainEntity) {
		'''
		«javaUtilities.innerClassDecl("private", "Update"+ domainEntity.name + "Task", "AsyncTask<"+ domainEntity.name+ ", Void, "+ domainEntity.name +">", null, false, false)»
		«generateTaskMembers(domainEntity)»
		«generateTaskConstructor(domainEntity)»
		«generateDoInBackgroundMethod(domainEntity)»
		«generateOnPostExecuteMethod(domainEntity)»
		}
		'''
	}

	def private generateTaskMembers(EClass domainEntity) {
		'''
		«javaUtilities.generateAdditionalAttribute(new Pair("AsyncCallback<"+ domainEntity.name+">", "mCallback"), false, true)»
		'''
	}
	
	def private generateTaskConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("AsyncCallback<" + domainEntity.name + ">", "callback"));
		'''
		«javaUtilities.generateConstructorDecl("Update"+ domainEntity.name + "Task", params, generateTaskConstructorBody())»
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
        protected «domainEntityName» doInBackground(«domainEntityName»... params) {
            if (params == null || params.length != 1) {
                throw new IllegalArgumentException("Paramter must not be null or empty!");
            }

            final «domainEntityName» «domainEntityVariable» = params[0];
            return update«domainEntityName»(«domainEntityVariable»);
        }
		'''
	}	
	
	def private generateOnPostExecuteMethod(EClass domainEntity) {
		'''
        @Override
        protected void onPostExecute(«domainEntity.name» result) {
            if (mCallback == null) {
                return;
            }
            mCallback.onResult(result);
        }		
        '''
	}

}