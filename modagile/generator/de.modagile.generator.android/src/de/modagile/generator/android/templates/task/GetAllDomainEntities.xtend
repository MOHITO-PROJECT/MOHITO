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
import de.modagile.generator.android.templates.ModagileFolderConstants
import info.multiplatform.generator.java.helper.Pair
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.ArrayList
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.Set
import java.util.HashSet

class GetAllDomainEntities {
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateGetAllDomainEntities(IFileSystemAccess fsa, EClass domainEntity, PackageInfo packageInfo){
		fsa.generateFile(packageInfo.getPackageDir+"task/GetAll"+ domainEntity.name +"s.java", 
							ModagileFolderConstants::SRC_GEN, generateClassCode(packageInfo, domainEntity)
		);
	}
	
def private generateClassCode(PackageInfo packageInfo, EClass domainEntity) {
		'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageInfo.getPackageName+".task")»
		«javaUtilities.importStatements(generateImports(packageInfo))»
		«javaUtilities.classDecl("GetAll"+ domainEntity.name + "s", null, null, false)»
		«generateMemberAttributes(domainEntity)»
		«generateConstructor(domainEntity)»
		«generateGetAllMethod(domainEntity)»
		«generateGetAllMethod_Async(domainEntity)»
		«generateAsyncTaskClass(domainEntity)»
		}
		'''
	}
	
	def private generateImports(PackageInfo packageInfo) {
		var Set imports = new HashSet<String>();
		imports.add("android.content.Context");
		imports.add("android.content.ContentValues");
		imports.add("android.content.ContentResolver");
		imports.add("android.database.Cursor");
		imports.add("android.net.Uri");
		imports.add("android.os.AsyncTask");
		imports.add("android.util.Log");
		imports.add("java.util.Collection");
		imports.add("java.util.ArrayList");
		imports.add(packageInfo.packageName+".model.*");
		imports.add(packageInfo.packageName+".constants.DBConstants");
		return imports;
	}
	
	
	def private generateMemberAttributes(EClass domainEntity) {
		'''
		«javaUtilities.generateAdditionalAttribute(new Pair("Context", "mContext"), false, false)»
		
		'''
	}

	def private generateConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("Context", "context"))
		'''
		«javaUtilities.generateConstructorDecl("GetAll"+ domainEntity.name + "s", params, generateConstructorBody())»
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
	
	def private generateGetAllMethod(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public Collection<«domainEntityName»> getAll«domainEntityName»s() {

			final ContentResolver cResolver = mContext.getContentResolver();
			final Cursor cursor = cResolver.query(DBConstants.CONTENT_URI_«domainEntityName.toUpperCase», null, null, null, null);
			final Collection<«domainEntityName»> list = new ArrayList<«domainEntityName»>();

			while (cursor.moveToNext()) {
				final «domainEntityName» «domainEntityVariable» = «domainEntityName»Converter.cursorTo«domainEntityName»(cursor);
				list.add(«domainEntityVariable»);
			}

			if (!cursor.isClosed()) {
				cursor.close();
			}

			return list;
		}
		'''
	}	
	
	def private generateGetAllMethod_Async(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		'''
    	public void getAll«domainEntityName»sAsync(final AsyncCallback<Collection<«domainEntityName»>> callback) {
    		GetAll«domainEntityName»sTask task = new GetAll«domainEntityName»sTask(mContext, callback);
    		task.execute();
    	}
		'''
	}
	
	def private generateAsyncTaskClass(EClass domainEntity) {
		'''
		«javaUtilities.innerClassDecl("private", "GetAll"+ domainEntity.name + "sTask", "AsyncTask<String, Void, Collection<"+domainEntity.name+">>", null, false, false)»
		«generateTaskMembers(domainEntity)»
		«generateTaskConstructor(domainEntity)»
		«generateDoInBackgroundMethod(domainEntity)»
		«generateOnPostExecuteMethod(domainEntity)»
		«generateGetAllDomainEntitiesMethod(domainEntity)»
		}
		'''
	}

	def private generateTaskMembers(EClass domainEntity) {
		'''
		«javaUtilities.generateAdditionalAttribute(new Pair("Context", "mContext"), false, true)»
		«javaUtilities.generateAdditionalAttribute(new Pair("AsyncCallback<Collection<"+ domainEntity.name + ">>", "mCallback"), false, true)»
		'''
	}
	
	def private generateTaskConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("Context", "context"));
		params.add(new Pair("AsyncCallback<Collection<" + domainEntity.name +">>", "callback"));
		'''
		«javaUtilities.generateConstructorDecl("GetAll"+ domainEntity.name + "sTask", params, generateTaskConstructorBody())»
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
        protected Collection<«domainEntity.name»> doInBackground(String... params) {
            return getAll«domainEntity.name»s();
        }
		'''
	}	
	
	def private generateGetAllDomainEntitiesMethod(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public Collection<«domainEntityName»> getAll«domainEntityName»s() {

			final ContentResolver cResolver = mContext.getContentResolver();
			final Cursor cursor = cResolver.query(DBConstants.CONTENT_URI_«domainEntityName.toUpperCase», null, null, null, null);

			final Collection<«domainEntityName»> list = new ArrayList<«domainEntityName»>();

			while (cursor.moveToNext()) {
				final «domainEntityName» «domainEntityVariable» = «domainEntityName»Converter.cursorTo«domainEntityName»(cursor);
				list.add(«domainEntityVariable»);
			}

			if (!cursor.isClosed()) {
				cursor.close();
			}

			return list;
		}
        '''
	}
	
	def private generateOnPostExecuteMethod(EClass domainEntity) {
		'''
        @Override
        protected void onPostExecute(Collection<«domainEntity.name»> result) {
            mCallback.onResult(result);
        }
		'''
	}

}