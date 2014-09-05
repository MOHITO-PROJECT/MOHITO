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
package de.modagile.generator.android.templates.helper
import com.google.inject.Inject

import java.util.ArrayList
import java.util.List

import org.eclipse.emf.ecore.EClass

import org.eclipse.xtext.generator.IFileSystemAccess
import java.util.Set
import java.util.HashSet

/**
 * Contains service specific generator methods and calls all service sub de.modagile.generator.android.templates
 * */
class HelperTemplate {
	
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateDomainHelper(String packagePrefix, EClass domainEntity, IFileSystemAccess fileSystemAccess, String outputConfiguration) {
		
		fileSystemAccess.generateFile(packagePrefix+"helper/DateHelper.java", outputConfiguration, domainEntity.generateDateHelperCode(packagePrefix.replaceAll("/",".")+"helper"))
		fileSystemAccess.generateFile(packagePrefix+"helper/RestHelper.java", outputConfiguration, domainEntity.generateRestHelperCode(packagePrefix.replaceAll("/",".")+"helper"))
	}
	
	// 	generate methods for RestHelper.java
	def generateRestHelperCode(EClass domainEntity, String packageName){
		

		var Set<String> imports  = new HashSet<String>();
		

		imports.add("java.io.BufferedReader");
		imports.add("java.io.IOException");
		imports.add("java.io.InputStream");
		imports.add("java.io.InputStreamReader");
		
		imports.add("org.apache.http.HttpEntity");
		imports.add("org.apache.http.HttpResponse");
		imports.add("org.apache.http.client.ClientProtocolException");
		imports.add("org.apache.http.client.HttpClient");
		
		imports.add("org.apache.http.client.methods.HttpRequestBase");
		imports.add("org.apache.http.impl.client.DefaultHttpClient");
		imports.add("org.apache.http.params.HttpConnectionParams");
		imports.add("org.apache.http.params.HttpParams");
		

		imports.add(packageName.replace(".helper",".constants.MamexConstants"));
		
		imports.add("android.util.Log");

		
		'''
		«generateRestHelperClass("HelperTemplate", packageName, domainEntity.name, imports, null, new ArrayList<String>())»
		'''	
	}
		def generateRestHelperClass(String templateName, String packageName, String domainEntityName, 
													 Set<String> imports,
													 String inheritance,
													 List<String> interfaces) {
		'''
		«javaUtilities.statementGenerated(templateName)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("RestHelper", inheritance, interfaces, false)»
		«generateRestHelperMethods(domainEntityName, domainEntityName)»
		}
		'''
	}
	
	
	def generateRestHelperMethods(String domainEntityName, String entityName){
		'''
			«restHelperAttribut()»
			«checkResponse()»
			«isResponsePositive()»
			«createHttpClientWithParams()»
			«setHttpClientParams()»
			«executeClient()»
			«convertStreamToString()»
			«handleClosingHttpComponents()»
		'''
	}
	
	def restHelperAttribut() {
	'''	
	private static final String TAG = RestHelper.class.getSimpleName();
	
	'''
	}
	
	def checkResponse() {
	'''	
		public static void checkResponse(HttpResponse response) throws IOException {

		if (response == null) {
			throw new IOException("Response from the server is null");
		}

		if (response.getEntity() == null) {
			throw new IOException("Entity in server response is null");
		}

		if (!isResponsePositive(response)) {
			Log.i(TAG, "Negative response: " + response.getStatusLine().toString());
		}
	}'''
	}
	
	def isResponsePositive() {
	'''	
	/**
	 * Check if the HTTP response is positive
	 * 
	 * @param r
	 * @return true if result code is 200, false otherwise
	 */
	private static boolean isResponsePositive(HttpResponse r) {
		if (r != null && r.getStatusLine() != null && r.getStatusLine().getStatusCode() == 200) {
			return true;
		}
		return false;
	}
	'''
	}	
	
	def createHttpClientWithParams() {
	'''	/**
	 * Create a HttpClient with some useful parameters like timeout
	 * 
	 * @return the configured httpclient
	 */
	public static HttpClient createHttpClientWithParams() {
		HttpClient httpclient = new DefaultHttpClient();
		setHttpClientParams(httpclient);
		return httpclient;
	}
	'''
	}
	
	def setHttpClientParams() {
	'''	private static void setHttpClientParams(HttpClient httpclient) {
		HttpParams httpParams = httpclient.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, MamexConstants.TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, MamexConstants.TIMEOUT);
	}
	'''
	}
	
	def executeClient() {
	'''	
	public static HttpEntity executeClient(HttpClient httpclient, HttpRequestBase httpMethod) throws IOException,
	      ClientProtocolException {

		HttpResponse response = httpclient.execute(httpMethod);

		RestHelper.checkResponse(response);

		return response.getEntity();
	}
	'''
	}
	
	def convertStreamToString() {
	'''public static String convertStreamToString(InputStream is) {

		if (is == null) {
			Log.e(TAG, "InputStream was null. Cannot convert to String");
			return null;
		}
				/*
		 * To convert the InputStream to String we use the BufferedReader.readLine() method. We iterate until the
		 * BufferedReader return null which means there's no more data to read. Each line will appended to a StringBuilder
		 * and returned as String.
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
		'''
	}
	
	
	def handleClosingHttpComponents() {
	'''	
	/**
	 * Close HTTP components after an error
	 * 
	 * @param entity
	 */
	public static void handleClosingHttpComponents(HttpClient httpclient, HttpEntity entity) {

		Log.d(TAG, "Closing HTTP components");

		InputStream is = null;

		if (entity != null) {
			try {
				entity.consumeContent();
				is = entity.getContent();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// entity was already consumed, it is OK
			}
		}

		if (is != null) {
			try {
				// Closing the input stream will trigger connection release
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (httpclient != null && httpclient.getConnectionManager() != null) {
			// release allocated resources
			httpclient.getConnectionManager().shutdown();
		}

	}
	'''
	}
	


	// generate methods for DateHelper.java
	
	
	def generateDateHelperCode(EClass domainEntity, String packageName){
		

		var Set<String> imports  = new HashSet<String>();
		

		imports.add("java.text.DateFormat");
		imports.add("java.util.Calendar");
		
		'''
		«generateDateHelperClass("HelperTemplate", packageName, domainEntity.name, imports, null, new ArrayList<String>())»
		'''	
	}
		def generateDateHelperClass(String caller, String packageName, String domainEntityName, 
													 Set<String> imports,
													 String inheritance,
													 List<String> interfaces) {
		'''
		«javaUtilities.statementGenerated(caller)»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl("DateHelper", inheritance, interfaces, false)»
		«generateDateHelperMethods()»
		}
		'''
	}
	

	def generateDateHelperMethods(){
		'''
			«calculateAge()»
			«convertLongToDateString()»
		'''
	}
	def calculateAge() {
	'''	
		/**
	 * Convenience method for calculating the age of a person.
	 * 
	 * @param birthdate
	 *           The timestamp of the birth date.
	 * @return The current age of a person in years as a String. Only whole and filled years are counted.
	 */
		public static String calculateAge(long birthdate) {
		Calendar calBirth = Calendar.getInstance();
		Calendar calNow = Calendar.getInstance();
		calBirth.setTimeInMillis(birthdate);
		calNow.setTimeInMillis(System.currentTimeMillis());

		int year = calNow.getTime().getYear() - calBirth.getTime().getYear();
		int month = calNow.getTime().getMonth() - calBirth.getTime().getMonth();
		int day = calNow.getTime().getDay() - calBirth.getTime().getDay();
		if (day < 0) {
			month--;
		}
		if (month < 0) {
			year--;
		}

		return String.valueOf(year);
	}
	'''
	}
		def convertLongToDateString() {
	'''	
		/**
	 * Converts a timestamp to the string representation for human reading.
	 * 
	 * @param birthdate
	 *           The timestamp of the date that needs to be converted.
	 * @return The human readable date as String.
	 */
		public static String convertLongToDateString(long birthdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(birthdate);
		DateFormat fmtDate = DateFormat.getDateInstance();
		fmtDate.setCalendar(cal);
		String s = fmtDate.format(birthdate);
		return s;
	}
	'''
	}

}