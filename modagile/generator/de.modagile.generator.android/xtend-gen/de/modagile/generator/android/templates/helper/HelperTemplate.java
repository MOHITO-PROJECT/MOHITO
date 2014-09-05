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
package de.modagile.generator.android.templates.helper;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.java.JavaUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * Contains service specific generator methods and calls all service sub de.modagile.generator.android.templates
 */
@SuppressWarnings("all")
public class HelperTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateDomainHelper(final String packagePrefix, final EClass domainEntity, final IFileSystemAccess fileSystemAccess, final String outputConfiguration) {
    String _plus = (packagePrefix + "helper/DateHelper.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "helper");
    CharSequence _generateDateHelperCode = this.generateDateHelperCode(domainEntity, _plus_1);
    fileSystemAccess.generateFile(_plus, outputConfiguration, _generateDateHelperCode);
    String _plus_2 = (packagePrefix + "helper/RestHelper.java");
    String _replaceAll_1 = packagePrefix.replaceAll("/", ".");
    String _plus_3 = (_replaceAll_1 + "helper");
    CharSequence _generateRestHelperCode = this.generateRestHelperCode(domainEntity, _plus_3);
    fileSystemAccess.generateFile(_plus_2, outputConfiguration, _generateRestHelperCode);
  }
  
  public CharSequence generateRestHelperCode(final EClass domainEntity, final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
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
      String _replace = packageName.replace(".helper", ".constants.MamexConstants");
      imports.add(_replace);
      imports.add("android.util.Log");
      StringConcatenation _builder = new StringConcatenation();
      String _name = domainEntity.getName();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateRestHelperClass = this.generateRestHelperClass("HelperTemplate", packageName, _name, imports, null, _arrayList);
      _builder.append(_generateRestHelperClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateRestHelperClass(final String templateName, final String packageName, final String domainEntityName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    CharSequence _classDecl = this.javaUtilities.classDecl("RestHelper", inheritance, interfaces, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateRestHelperMethods = this.generateRestHelperMethods(domainEntityName, domainEntityName);
    _builder.append(_generateRestHelperMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateRestHelperMethods(final String domainEntityName, final String entityName) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _restHelperAttribut = this.restHelperAttribut();
    _builder.append(_restHelperAttribut, "");
    _builder.newLineIfNotEmpty();
    CharSequence _checkResponse = this.checkResponse();
    _builder.append(_checkResponse, "");
    _builder.newLineIfNotEmpty();
    CharSequence _isResponsePositive = this.isResponsePositive();
    _builder.append(_isResponsePositive, "");
    _builder.newLineIfNotEmpty();
    CharSequence _createHttpClientWithParams = this.createHttpClientWithParams();
    _builder.append(_createHttpClientWithParams, "");
    _builder.newLineIfNotEmpty();
    CharSequence _setHttpClientParams = this.setHttpClientParams();
    _builder.append(_setHttpClientParams, "");
    _builder.newLineIfNotEmpty();
    CharSequence _executeClient = this.executeClient();
    _builder.append(_executeClient, "");
    _builder.newLineIfNotEmpty();
    CharSequence _convertStreamToString = this.convertStreamToString();
    _builder.append(_convertStreamToString, "");
    _builder.newLineIfNotEmpty();
    CharSequence _handleClosingHttpComponents = this.handleClosingHttpComponents();
    _builder.append(_handleClosingHttpComponents, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence restHelperAttribut() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private static final String TAG = RestHelper.class.getSimpleName();");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence checkResponse() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("public static void checkResponse(HttpResponse response) throws IOException {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (response == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new IOException(\"Response from the server is null\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (response.getEntity() == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new IOException(\"Entity in server response is null\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (!isResponsePositive(response)) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.i(TAG, \"Negative response: \" + response.getStatusLine().toString());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    return _builder;
  }
  
  public CharSequence isResponsePositive() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Check if the HTTP response is positive");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param r");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return true if result code is 200, false otherwise");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("private static boolean isResponsePositive(HttpResponse r) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (r != null && r.getStatusLine() != null && r.getStatusLine().getStatusCode() == 200) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createHttpClientWithParams() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Create a HttpClient with some useful parameters like timeout");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* @return the configured httpclient");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static HttpClient createHttpClientWithParams() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("HttpClient httpclient = new DefaultHttpClient();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("setHttpClientParams(httpclient);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return httpclient;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence setHttpClientParams() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("private static void setHttpClientParams(HttpClient httpclient) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("HttpParams httpParams = httpclient.getParams();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("HttpConnectionParams.setConnectionTimeout(httpParams, MamexConstants.TIMEOUT);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("HttpConnectionParams.setSoTimeout(httpParams, MamexConstants.TIMEOUT);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence executeClient() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static HttpEntity executeClient(HttpClient httpclient, HttpRequestBase httpMethod) throws IOException,");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("ClientProtocolException {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpResponse response = httpclient.execute(httpMethod);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("RestHelper.checkResponse(response);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return response.getEntity();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence convertStreamToString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static String convertStreamToString(InputStream is) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (is == null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Log.e(TAG, \"InputStream was null. Cannot convert to String\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("* To convert the InputStream to String we use the BufferedReader.readLine() method. We iterate until the");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("* BufferedReader return null which means there\'s no more data to read. Each line will appended to a StringBuilder");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("* and returned as String.");
    _builder.newLine();
    _builder.append("\t\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("BufferedReader reader = new BufferedReader(new InputStreamReader(is));");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("StringBuilder sb = new StringBuilder();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String line = null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("while ((line = reader.readLine()) != null) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("sb.append(line + \"\\n\");");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} finally {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("is.close();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return sb.toString();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence handleClosingHttpComponents() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Close HTTP components after an error");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param entity");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static void handleClosingHttpComponents(HttpClient httpclient, HttpEntity entity) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Log.d(TAG, \"Closing HTTP components\");");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("InputStream is = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (entity != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("entity.consumeContent();");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("is = entity.getContent();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (IllegalStateException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("// entity was already consumed, it is OK");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (is != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("// Closing the input stream will trigger connection release");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("is.close();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (httpclient != null && httpclient.getConnectionManager() != null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// release allocated resources");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpclient.getConnectionManager().shutdown();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateDateHelperCode(final EClass domainEntity, final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      imports.add("java.text.DateFormat");
      imports.add("java.util.Calendar");
      StringConcatenation _builder = new StringConcatenation();
      String _name = domainEntity.getName();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateDateHelperClass = this.generateDateHelperClass("HelperTemplate", packageName, _name, imports, null, _arrayList);
      _builder.append(_generateDateHelperClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateDateHelperClass(final String caller, final String packageName, final String domainEntityName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(caller);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    CharSequence _classDecl = this.javaUtilities.classDecl("DateHelper", inheritance, interfaces, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDateHelperMethods = this.generateDateHelperMethods();
    _builder.append(_generateDateHelperMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateDateHelperMethods() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _calculateAge = this.calculateAge();
    _builder.append(_calculateAge, "");
    _builder.newLineIfNotEmpty();
    CharSequence _convertLongToDateString = this.convertLongToDateString();
    _builder.append(_convertLongToDateString, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence calculateAge() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Convenience method for calculating the age of a person.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param birthdate");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           The timestamp of the birth date.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return The current age of a person in years as a String. Only whole and filled years are counted.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static String calculateAge(long birthdate) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Calendar calBirth = Calendar.getInstance();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Calendar calNow = Calendar.getInstance();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("calBirth.setTimeInMillis(birthdate);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("calNow.setTimeInMillis(System.currentTimeMillis());");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int year = calNow.getTime().getYear() - calBirth.getTime().getYear();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int month = calNow.getTime().getMonth() - calBirth.getTime().getMonth();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int day = calNow.getTime().getDay() - calBirth.getTime().getDay();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (day < 0) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("month--;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (month < 0) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("year--;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return String.valueOf(year);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence convertLongToDateString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Converts a timestamp to the string representation for human reading.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param birthdate");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           The timestamp of the date that needs to be converted.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return The human readable date as String.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public static String convertLongToDateString(long birthdate) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Calendar cal = Calendar.getInstance();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("cal.setTimeInMillis(birthdate);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("DateFormat fmtDate = DateFormat.getDateInstance();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("fmtDate.setCalendar(cal);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String s = fmtDate.format(birthdate);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return s;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
