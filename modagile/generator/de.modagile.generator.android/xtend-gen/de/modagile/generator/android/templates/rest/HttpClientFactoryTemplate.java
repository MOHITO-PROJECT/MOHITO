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
import info.multiplatform.generator.java.helper.Triple;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class HttpClientFactoryTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateHttpClientFactory(final String packagePrefix, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "rest/HttpClientFactory.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "rest");
    CharSequence _generateHttpClientFactoryCode = this.generateHttpClientFactoryCode(_plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateHttpClientFactoryCode);
  }
  
  public void generateHttpClientFactoryImpl(final String packagePrefix, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "rest/HttpClientFactoryImpl.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "rest");
    CharSequence _generateHttpClientFactoryImplCode = this.generateHttpClientFactoryImplCode(_plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateHttpClientFactoryImplCode);
  }
  
  /**
   * HttpClientFactory Methods
   */
  public CharSequence generateHttpClientFactoryCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      imports.add("org.apache.http.client.HttpClient");
      StringConcatenation _builder = new StringConcatenation();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateHttpClientFactoryCodeInterface = this.generateHttpClientFactoryCodeInterface("HttpClientFactoryTemplate", packageName, imports, null, _arrayList);
      _builder.append(_generateHttpClientFactoryCodeInterface, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateHttpClientFactoryCodeInterface(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
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
    CharSequence _interfaceDecl = this.javaUtilities.interfaceDecl("HttpClientFactory", null);
    _builder.append(_interfaceDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateHttpClientFactoryMethod = this.generateHttpClientFactoryMethod();
    _builder.append(_generateHttpClientFactoryMethod, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateHttpClientFactoryMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("HttpClient createHttpClientWithParams();");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * HttpClientFactoryImpl Methods
   */
  public CharSequence generateHttpClientFactoryImplCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> interfaces = _arrayList;
      interfaces.add("HttpClientFactory");
      imports.add("org.apache.http.client.HttpClient");
      String _replace = packageName.replace("rest", "helper");
      String _plus = (_replace + ".RestHelper");
      imports.add(_plus);
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateHttpClientFactoryImplClass = this.generateHttpClientFactoryImplClass("HttpClientFactoryTemplate", packageName, imports, null, interfaces);
      _builder.append(_generateHttpClientFactoryImplClass, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateHttpClientFactoryImplClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Triple<String,String,String>> _arrayList = new ArrayList<Triple<String,String,String>>();
      List<Triple<String,String,String>> attributes = _arrayList;
      Triple<String,String,String> _triple = new Triple<String,String,String>("HttpClientFactory", "instance", "new HttpClientFactoryImpl()");
      attributes.add(_triple);
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
      CharSequence _classDecl = this.javaUtilities.classDecl("HttpClientFactoryImpl", inheritance, interfaces, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      Triple<String,String,String> _get = attributes.get(0);
      CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(_get, true, true);
      _builder.append(_generateAdditionalAttributesWithExpression, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateHttpClientFactoryImplMethods = this.generateHttpClientFactoryImplMethods();
      _builder.append(_generateHttpClientFactoryImplMethods, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateHttpClientFactoryImplMethods() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _instance = this.getInstance();
    _builder.append(_instance, "");
    _builder.newLineIfNotEmpty();
    CharSequence _createHttpClientWithParams = this.createHttpClientWithParams();
    _builder.append(_createHttpClientWithParams, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence getInstance() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public static HttpClientFactory getInstance(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return instance;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createHttpClientWithParams() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public HttpClient createHttpClientWithParams() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return RestHelper.createHttpClientWithParams();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
