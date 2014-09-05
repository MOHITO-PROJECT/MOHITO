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
package de.modagile.generator.android.templates.rest

import com.google.inject.Inject
import java.util.ArrayList
import java.util.List
import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.Set
import java.util.HashSet

class BaseRestClientTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateBaseRestClient(String packagePrefix, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"rest/BaseRestClient.java", outputConfiguration, generateBaseRestClientCode(packagePrefix.replaceAll("/",".")+"rest"))
	}
	
	def generateBaseRestClientImpl(String packagePrefix, IFileSystemAccess fsa, String outputConfiguration){
		fsa.generateFile(packagePrefix+"rest/BaseRestClientImpl.java", outputConfiguration, generateBaseRestClientImplCode(packagePrefix.replaceAll("/",".")+"rest"))
	}
	
	/** BaseRestClientInterface Methods*/
	def generateBaseRestClientCode(String packageName) {
		var Set<String> imports = new HashSet<String>();
		imports.add("java.io.Serializable");
		imports.add("java.util.List");
		'''
		«generateBaseRestClientInterface("BaseRestClientTemplate", packageName, imports, null, new ArrayList())»
		'''
	}
	
	def generateBaseRestClientInterface(String templateName, String packageName, Set<String> imports,
													 							   String inheritance,
													 						List<String> interfaces) {
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.interfaceDecl("BaseRestClient<T extends Serializable>", null)»
		«generateBaseRestClientMethods»
		}
		'''
	}
	
	
	def generateBaseRestClientMethods(){
		'''
		public abstract List<T> getAll();

		public abstract T getByUuid(String uuid);

		public abstract String update(T entity);

		public abstract String create(T entity);

		public abstract boolean delete(String uuid, String rev);
		'''
	}
	
	/*BaseRestClientImpl Methods*/
	
		def generateBaseRestClientImplCode(String packageName) {
		var Set<String> imports = new HashSet<String>();
		var List<String> interfaces = new ArrayList<String>();
		getBaseRestClientImplImports(imports, packageName);
		interfaces.add("BaseRestClient<ENTITY>");
		
		'''
		«generateBaseRestClientImplClass("BaseRestClientTemplate", packageName, imports, null, interfaces)»
		'''
	}
	
	def generateBaseRestClientImplClass(String templateName, String packageName, Set<String> imports,
													 							   String inheritance,
													 						List<String> interfaces) {
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("BaseRestClientImpl<ENTITY extends Entity>", null, interfaces, true)»
		«generateBaseRestClientImplAttributes»
		«generateBaseRestClientImplConstructor»
		«generateBaseRestClientImplMethods»
		}
		'''
	}
	
	def getBaseRestClientImplImports(Set<String> imports, String packageName) {
		imports.add("java.io.IOException");
		imports.add("java.util.List");
		imports.add("org.apache.http.HttpEntity");
		imports.add("org.apache.http.client.ClientProtocolException");
		imports.add("org.apache.http.client.HttpClient");
		imports.add("org.apache.http.client.methods.HttpDelete");
		imports.add("org.apache.http.client.methods.HttpGet");
		imports.add("org.apache.http.client.methods.HttpPost");
		imports.add("org.apache.http.client.methods.HttpPut");
		imports.add("org.apache.http.entity.StringEntity");
		imports.add("org.json.JSONArray");
		imports.add("org.json.JSONException");
		imports.add("org.json.JSONObject");
		imports.add("android.content.Context");
		imports.add("android.content.SharedPreferences");
		imports.add("android.preference.PreferenceManager");
		imports.add("android.util.Log");
		imports.add(packageName.replace("rest", "")+"convert.JsonConverter");
		imports.add(packageName.replace("rest", "")+"helper.RestHelper");
		imports.add(packageName.replace("rest", "")+"model.Entity");
	}
	
	def generateBaseRestClientImplAttributes(){
		'''
		private static final String TAG = BaseRestClientImpl.class.getSimpleName();
	
		private HttpClientFactory httpClientFactory;
		private String urlBase;
		private String urlForEntityList; 
		private JsonConverter<ENTITY> jsonConverter;
		'''
	}
	
	def generateBaseRestClientImplConstructor(){
		'''
		public BaseRestClientImpl(Context ctx, HttpClientFactory httpClientFactory) {
			this.httpClientFactory = httpClientFactory;
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
			String prefIp = prefs.getString("prefIp", "10.0.2.2");
			String prefPort = prefs.getString("prefPort", "8000");
			urlBase = "http://" + prefIp + ':' + prefPort;
			urlForEntityList = urlBase + getEntityListSuffix();
			jsonConverter = createJsonConverter();
		}
		'''
	}

	def generateBaseRestClientImplMethods(){
		'''
		«generateBaseRestClientImplAbstractMethods»
		«createFromJson»
		«getAll»
		«deletes»
		«getByUuid»
		«create»
		«update»
		'''
	}
	
		def generateBaseRestClientImplAbstractMethods(){
		'''
		protected abstract String getEntityListSuffix();
		protected abstract JsonConverter<ENTITY> createJsonConverter();
		'''
	}
	
	
	
	def createFromJson() {
	'''
	 	//Create a new entry on the server (Generalized for different types)
		public String createFromJson(String jsonStr, String urlBase) {

		HttpClient httpclient = RestHelper.createHttpClientWithParams();
		HttpPost httpost = new HttpPost(urlBase);
		HttpEntity entity = null;
		try {

			StringEntity se = new StringEntity(jsonStr);
			se.setContentType("application/json");
			entity = se;
			httpost.setEntity(entity);
			// execute the method and hold on to the response
			entity = RestHelper.executeClient(httpclient, httpost);

			// inspect the received response
			// JSON Response Read
			String result = RestHelper.convertStreamToString(entity.getContent());
			JSONObject jo = new JSONObject(result);
			Log.i(TAG, "Response from server after create: " + result);
			return jo.getString("rev");

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			Log.d(TAG, "I/O Error on network: " + e.getMessage());
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			RestHelper.handleClosingHttpComponents(httpclient, entity);
		}

		return null;
	}
		
	'''
	}
	
	def getAll() {
		'''
		@Override
		public List<ENTITY> getAll() {

			HttpClient httpclient = httpClientFactory.createHttpClientWithParams();
			HttpGet httpget = new HttpGet(urlForEntityList);
			HttpEntity entity = null;
			try {
				entity = RestHelper.executeClient(httpclient, httpget);
				// A Simple JSON Response Read
				String entityString = RestHelper.convertStreamToString(entity.getContent());
				// Log.i(TAG, "response entity as String: " + entityString);
				// JSONObject Creation
				JSONArray jsonArr = new JSONArray(entityString);
				List<ENTITY> list = jsonConverter.parseEntityList(jsonArr);
		
				return list;
			} catch (ClientProtocolException e) {
				Log.d(TAG, "ERROR: " + e.getMessage());
			} catch (IOException e) {
				Log.d(TAG, "ERROR: " + e.getMessage());
			} catch (JSONException e) {
				Log.d(TAG, "ERROR: " + e.getMessage());
			} finally {
				RestHelper.handleClosingHttpComponents(httpclient, entity);
			}
			return null;
		}	
		'''
		}
	
	
	def getByUuid(){
		'''
		public ENTITY getByUuid(String uuid) {
			
			HttpClient httpclient = httpClientFactory.createHttpClientWithParams();
		
			HttpGet httpget = new HttpGet(urlForEntityList + "/" + uuid);
		
			HttpEntity entity = null;
			try {
				entity = RestHelper.executeClient(httpclient, httpget);
		
				// JSON Response Read
				String entityString = RestHelper.convertStreamToString(entity.getContent());
				// Log.i(TAG, "response entity as String: " + entityString);
		
				// JSONObject Creation
				JSONObject json = new JSONObject(entityString);
				ENTITY e = jsonConverter.parseEntity(json);
		
				return e;
		
			} catch (ClientProtocolException e) {
				Log.d(TAG, "ERROR: " + e.getMessage());
			} catch (IOException e) {
				Log.d(TAG, "ERROR: " + e.getMessage());
			} catch (JSONException e) {
				Log.d(TAG, "ERROR: " + e.getMessage());
			} finally {
				RestHelper.handleClosingHttpComponents(httpclient, entity);
			}
		
			return null;
		}
		'''
	}
	
	def update() {
		'''
		public String update(ENTITY entity) {
			String jsonStr = jsonConverter.getJson(entity);
			HttpClient httpclient = httpClientFactory.createHttpClientWithParams();
			HttpPut httpput = new HttpPut(urlForEntityList + "/" + entity.getUuid());
			JSONObject jasonResult = null;
			HttpEntity httpEntity = null;
		
			try {
		
				// set the content type to JSON
				StringEntity se = new StringEntity(jsonStr, "UTF-8");
				se.setContentType("application/json");
				httpEntity = se;
				httpput.setEntity(httpEntity);
		
				// execute the method and hold on to the response
				httpEntity = RestHelper.executeClient(httpclient, httpput);
		
				// JSON Response Read
				String strResult = RestHelper.convertStreamToString(httpEntity.getContent());
				Log.d(TAG, "Response from server after update: " + strResult);
				jasonResult = new JSONObject(strResult);
		
				if (jasonResult != null) {
					return jasonResult.getString("rev");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				Log.d(TAG, "I/O Error on network: " + e.getMessage());
			} catch (JSONException e) {
				e.printStackTrace();
			} finally {
				RestHelper.handleClosingHttpComponents(httpclient, httpEntity);
			}
		
			return null;
		}
		'''
	}
	
	def deletes() {
		'''
		/**
		 * Delete an entry on the server
		 * 
		 * @param uuid
		 *           UUID of the entry
		 * @param rev
		 *           Revision of the entry, must match the revision on the server to succeed.
		 * @param urlToList
		 *           URL to the entity list
		 * @return true if successful, false if not
		 */
		protected boolean delete(String uuid, String rev, String urlToList) {
			HttpClient httpclient = httpClientFactory.createHttpClientWithParams();
			String urlToAddress = urlToList + "/" + uuid + "?rev=" + rev;
			HttpDelete httpdelete = new HttpDelete(urlToAddress);
		
			HttpEntity entity = null;
		
			try {
				entity = RestHelper.executeClient(httpclient, httpdelete);
				String strResult = RestHelper.convertStreamToString(entity.getContent());
				Log.d(TAG, "Response from server after delete: " + strResult);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				Log.d(TAG, "I/O Error on network: " + e.getMessage());
			} finally {
				RestHelper.handleClosingHttpComponents(httpclient, entity);
			}
		
			if (entity == null) {
				return false;
			}
		
			return true;
		}
		
		public boolean delete(String uuid, String rev) {
			return delete(uuid, rev, urlForEntityList);
		}
		'''
	}
	
	def create(){
		'''
		public String create(ENTITY entity) {
			String jsonStr = jsonConverter.getJson(entity);
			return createFromJson(jsonStr, urlForEntityList);
		}
		'''
	}
	
}