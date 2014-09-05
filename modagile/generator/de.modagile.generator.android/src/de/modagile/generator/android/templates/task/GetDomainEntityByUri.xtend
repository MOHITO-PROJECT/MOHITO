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
import info.multiplatform.generator.java.templates.PackageInfo
import de.modagile.generator.android.templates.ModagileFolderConstants
import java.util.HashSet
import java.util.Set

class GetDomainEntityByUri {
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateGetDomainEntityByUri(IFileSystemAccess fsa, EClass domainEntity, PackageInfo packageInfo){
		fsa.generateFile(packageInfo.getPackageDir+"task/Get"+ domainEntity.name +"ByUri.java", 
							ModagileFolderConstants::SRC_GEN, generateClassCode(packageInfo, domainEntity)
		);
	}
	
def private generateClassCode(PackageInfo packageInfo, EClass domainEntity) {
		'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageInfo.getPackageName+".task")»
		«javaUtilities.importStatements(generateImports(packageInfo))»
		«javaUtilities.classDecl("Get"+ domainEntity.name + "ByUri", null, null, false)»
		«generateMemberAttributes(domainEntity)»
		«generateConstructor(domainEntity)»
		«generateGetDomainEntityByUriMethod(domainEntity)»
		«generateGetDomainEntityByUriMethod_Async(domainEntity)»
		«generateAsyncTaskClass(domainEntity)»
		}
		'''
	}
	
	def private generateImports(PackageInfo packageInfo) {
		var Set imports = new HashSet<String>();
		imports.add("android.content.Context");
		imports.add("android.content.ContentResolver");
		imports.add("android.database.Cursor");
		imports.add("android.net.Uri");
		imports.add("android.os.AsyncTask");
		imports.add(packageInfo.packageName+".model.*");
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
		«javaUtilities.generateConstructorDecl("Get"+ domainEntity.name + "ByUri", params, generateConstructorBody())»
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
	
	def private generateGetDomainEntityByUriMethod(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		val String domainEntityVariable = domainEntity.name.toFirstLower;
		'''
		public «domainEntityName» get«domainEntityName»ByUri(Uri uri) {
			if (uri == null) {
				throw new IllegalArgumentException("uri must not be null or empty!");
			}

			final ContentResolver cResolver = mContext.getContentResolver();
			final Cursor cursor = cResolver.query(uri, null, null, null, null);

			if (cursor.getCount() == 0) {
				if (!cursor.isClosed()) {
					cursor.close();
				}
			return null;
			}

			cursor.moveToFirst();

			final «domainEntityName» «domainEntityVariable» = «domainEntityName»Converter.cursorTo«domainEntityName»(cursor);

			if (!cursor.isClosed()) {
				cursor.close();
			}
			return «domainEntityVariable»;
		}
		'''
	}	
	
	def private generateGetDomainEntityByUriMethod_Async(EClass domainEntity) {
		val String domainEntityName = domainEntity.name;
		'''
		public void get«domainEntityName»ByUriAsync(final Uri uri, final AsyncCallback<«domainEntityName»> callback) {
			Get«domainEntityName»ByUriTask task = new Get«domainEntityName»ByUriTask(callback);
			task.execute(uri);
		}
		'''
	}
	
	def private generateAsyncTaskClass(EClass domainEntity) {
		'''
		«javaUtilities.innerClassDecl("private", "Get"+ domainEntity.name + "ByUriTask", "AsyncTask<Uri, Void, "+domainEntity.name+">", null, false, false)»
		«generateTaskMembers(domainEntity)»
		«generateTaskConstructor(domainEntity)»
		«generateDoInBackgroundMethod(domainEntity)»
		«generateOnPostExecuteMethod(domainEntity)»
		}
		'''
	}

	def private generateTaskMembers(EClass domainEntity) {
		'''
		«javaUtilities.generateAdditionalAttribute(new Pair("AsyncCallback<"+ domainEntity.name + ">", "mCallback"), false, true)»
		'''
	}
	
	def private generateTaskConstructor(EClass domainEntity) {
		var params = new ArrayList<Pair<String, String>>();
		params.add(new Pair("AsyncCallback<" + domainEntity.name +">", "callback"));
		'''
		«javaUtilities.generateConstructorDecl("Get"+ domainEntity.name + "ByUriTask", params, generateTaskConstructorBody())»
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
		'''
        @Override
        protected «domainEntity.name» doInBackground(Uri... params) {

            if (params == null || params.length != 1) {
                throw new IllegalArgumentException("Paramter must not be null or empty!");
            }

            final Uri uri = params[0];

            return get«domainEntity.name»ByUri(uri);
        }
		'''
	}	
	
	def private generateOnPostExecuteMethod(EClass domainEntity) {
		'''
        @Override
        protected void onPostExecute(«domainEntity.name» result) {
            mCallback.onResult(result);
        }
		'''
	}

}