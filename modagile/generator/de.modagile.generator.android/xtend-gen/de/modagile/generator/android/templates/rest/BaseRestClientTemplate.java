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
package de.modagile.generator.android.templates.rest;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.java.JavaUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class BaseRestClientTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateBaseRestClient(final String packagePrefix, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "rest/BaseRestClient.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "rest");
    CharSequence _generateBaseRestClientCode = this.generateBaseRestClientCode(_plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateBaseRestClientCode);
  }
  
  public void generateBaseRestClientImpl(final String packagePrefix, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "rest/BaseRestClientImpl.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "rest");
    CharSequence _generateBaseRestClientImplCode = this.generateBaseRestClientImplCode(_plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateBaseRestClientImplCode);
  }
  
  /**
   * BaseRestClientInterface Methods
   */
  public CharSequence generateBaseRestClientCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      imports.add("java.io.Serializable");
      imports.add("java.util.List");
      StringConcatenation _builder = new StringConcatenation();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateBaseRestClientInterface = this.generateBaseRestClientInterface("BaseRestClientTemplate", packageName, imports, null, _arrayList);
      _builder.append(_generateBaseRestClientInterface, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateBaseRestClientInterface(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
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
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl("BaseRestClient<T extends Serializable>", null);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateBaseRestClientMethods = this.generateBaseRestClientMethods();
    _builder.append(_generateBaseRestClientMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateBaseRestClientMethods() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public abstract List<T> getAll();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public abstract T getByUuid(String uuid);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public abstract String update(T entity);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public abstract String create(T entity);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public abstract boolean delete(String uuid, String rev);");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * BaseRestClientImpl Methods
   */
  public CharSequence generateBaseRestClientImplCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> interfaces = _arrayList;
      this.getBaseRestClientImplImports(imports, packageName);
      interfaces.add("BaseRestClient<ENTITY>");
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateBaseRestClientImplClass = this.generateBaseRestClientImplClass("BaseRestClientTemplate", packageName, imports, null, interfaces);
      _builder.append(_generateBaseRestClientImplClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateBaseRestClientImplClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
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
    CharSequence _classDecl = this.javaUtilities.classDecl("BaseRestClientImpl<ENTITY extends Entity>", null, interfaces, true);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateBaseRestClientImplAttributes = this.generateBaseRestClientImplAttributes();
    _builder.append(_generateBaseRestClientImplAttributes, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateBaseRestClientImplConstructor = this.generateBaseRestClientImplConstructor();
    _builder.append(_generateBaseRestClientImplConstructor, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateBaseRestClientImplMethods = this.generateBaseRestClientImplMethods();
    _builder.append(_generateBaseRestClientImplMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public boolean getBaseRestClientImplImports(final Set<String> imports, final String packageName) {
    boolean _xblockexpression = false;
    {
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
      String _replace = packageName.replace("rest", "");
      String _plus = (_replace + "convert.JsonConverter");
      imports.add(_plus);
      String _replace_1 = packageName.replace("rest", "");
      String _plus_1 = (_replace_1 + "helper.RestHelper");
      imports.add(_plus_1);
      String _replace_2 = packageName.replace("rest", "");
      String _plus_2 = (_replace_2 + "model.Entity");
      boolean _add = imports.add(_plus_2);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateBaseRestClientImplAttributes() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("private static final String TAG = BaseRestClientImpl.class.getSimpleName();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("private HttpClientFactory httpClientFactory;");
    _builder.newLine();
    _builder.append("private String urlBase;");
    _builder.newLine();
    _builder.append("private String urlForEntityList; ");
    _builder.newLine();
    _builder.append("private JsonConverter<ENTITY> jsonConverter;");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateBaseRestClientImplConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public BaseRestClientImpl(Context ctx, HttpClientFactory httpClientFactory) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("this.httpClientFactory = httpClientFactory;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String prefIp = prefs.getString(\"prefIp\", \"10.0.2.2\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String prefPort = prefs.getString(\"prefPort\", \"8000\");");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("urlBase = \"http://\" + prefIp + \':\' + prefPort;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("urlForEntityList = urlBase + getEntityListSuffix();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("jsonConverter = createJsonConverter();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateBaseRestClientImplMethods() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateBaseRestClientImplAbstractMethods = this.generateBaseRestClientImplAbstractMethods();
    _builder.append(_generateBaseRestClientImplAbstractMethods, "");
    _builder.newLineIfNotEmpty();
    CharSequence _createFromJson = this.createFromJson();
    _builder.append(_createFromJson, "");
    _builder.newLineIfNotEmpty();
    CharSequence _all = this.getAll();
    _builder.append(_all, "");
    _builder.newLineIfNotEmpty();
    CharSequence _deletes = this.deletes();
    _builder.append(_deletes, "");
    _builder.newLineIfNotEmpty();
    CharSequence _byUuid = this.getByUuid();
    _builder.append(_byUuid, "");
    _builder.newLineIfNotEmpty();
    CharSequence _create = this.create();
    _builder.append(_create, "");
    _builder.newLineIfNotEmpty();
    CharSequence _update = this.update();
    _builder.append(_update, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateBaseRestClientImplAbstractMethods() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("protected abstract String getEntityListSuffix();");
    _builder.newLine();
    _builder.append("protected abstract JsonConverter<ENTITY> createJsonConverter();");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createFromJson() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" \t");
    _builder.append("//Create a new entry on the server (Generalized for different types)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public String createFromJson(String jsonStr, String urlBase) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpClient httpclient = RestHelper.createHttpClientWithParams();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpPost httpost = new HttpPost(urlBase);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpEntity entity = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("StringEntity se = new StringEntity(jsonStr);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("se.setContentType(\"application/json\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("entity = se;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpost.setEntity(entity);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// execute the method and hold on to the response");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("entity = RestHelper.executeClient(httpclient, httpost);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// inspect the received response");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// JSON Response Read");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String result = RestHelper.convertStreamToString(entity.getContent());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("JSONObject jo = new JSONObject(result);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.i(TAG, \"Response from server after create: \" + result);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return jo.getString(\"rev\");");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (ClientProtocolException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"I/O Error on network: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (JSONException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} finally {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("RestHelper.handleClosingHttpComponents(httpclient, entity);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getAll() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public List<ENTITY> getAll() {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpClient httpclient = httpClientFactory.createHttpClientWithParams();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpGet httpget = new HttpGet(urlForEntityList);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpEntity entity = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("entity = RestHelper.executeClient(httpclient, httpget);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// A Simple JSON Response Read");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String entityString = RestHelper.convertStreamToString(entity.getContent());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// Log.i(TAG, \"response entity as String: \" + entityString);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// JSONObject Creation");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("JSONArray jsonArr = new JSONArray(entityString);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("List<ENTITY> list = jsonConverter.parseEntityList(jsonArr);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return list;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (ClientProtocolException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"ERROR: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"ERROR: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (JSONException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"ERROR: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} finally {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("RestHelper.handleClosingHttpComponents(httpclient, entity);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence getByUuid() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ENTITY getByUuid(String uuid) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpClient httpclient = httpClientFactory.createHttpClientWithParams();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpGet httpget = new HttpGet(urlForEntityList + \"/\" + uuid);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpEntity entity = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("entity = RestHelper.executeClient(httpclient, httpget);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// JSON Response Read");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String entityString = RestHelper.convertStreamToString(entity.getContent());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// Log.i(TAG, \"response entity as String: \" + entityString);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// JSONObject Creation");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("JSONObject json = new JSONObject(entityString);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ENTITY e = jsonConverter.parseEntity(json);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return e;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (ClientProtocolException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"ERROR: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"ERROR: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (JSONException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"ERROR: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} finally {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("RestHelper.handleClosingHttpComponents(httpclient, entity);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence update() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public String update(ENTITY entity) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String jsonStr = jsonConverter.getJson(entity);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpClient httpclient = httpClientFactory.createHttpClientWithParams();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpPut httpput = new HttpPut(urlForEntityList + \"/\" + entity.getUuid());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("JSONObject jasonResult = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpEntity httpEntity = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// set the content type to JSON");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("StringEntity se = new StringEntity(jsonStr, \"UTF-8\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("se.setContentType(\"application/json\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpEntity = se;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpput.setEntity(httpEntity);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// execute the method and hold on to the response");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("httpEntity = RestHelper.executeClient(httpclient, httpput);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// JSON Response Read");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String strResult = RestHelper.convertStreamToString(httpEntity.getContent());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"Response from server after update: \" + strResult);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("jasonResult = new JSONObject(strResult);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (jasonResult != null) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return jasonResult.getString(\"rev\");");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (ClientProtocolException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"I/O Error on network: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (JSONException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} finally {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("RestHelper.handleClosingHttpComponents(httpclient, httpEntity);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence deletes() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Delete an entry on the server");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param uuid");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           UUID of the entry");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param rev");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           Revision of the entry, must match the revision on the server to succeed.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param urlToList");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*           URL to the entity list");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return true if successful, false if not");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected boolean delete(String uuid, String rev, String urlToList) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpClient httpclient = httpClientFactory.createHttpClientWithParams();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String urlToAddress = urlToList + \"/\" + uuid + \"?rev=\" + rev;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpDelete httpdelete = new HttpDelete(urlToAddress);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("HttpEntity entity = null;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("entity = RestHelper.executeClient(httpclient, httpdelete);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("String strResult = RestHelper.convertStreamToString(entity.getContent());");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"Response from server after delete: \" + strResult);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (ClientProtocolException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("e.printStackTrace();");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} catch (IOException e) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"I/O Error on network: \" + e.getMessage());");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("} finally {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("RestHelper.handleClosingHttpComponents(httpclient, entity);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if (entity == null) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public boolean delete(String uuid, String rev) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return delete(uuid, rev, urlForEntityList);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public String create(ENTITY entity) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String jsonStr = jsonConverter.getJson(entity);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return createFromJson(jsonStr, urlForEntityList);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
