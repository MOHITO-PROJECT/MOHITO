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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class EntityRestClientTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateEntityRestClient(final String packagePrefix, final EClass domainEntity, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "rest/");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "RestClient.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_3 = (_replaceAll + "rest");
    CharSequence _generateEntityRestClientCode = this.generateEntityRestClientCode(_plus_3, domainEntity);
    fsa.generateFile(_plus_2, outputConfiguration, _generateEntityRestClientCode);
  }
  
  public CharSequence generateEntityRestClientCode(final String packageName, final EClass domainEntity) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      String _name = domainEntity.getName();
      String _firstUpper = StringExtensions.toFirstUpper(_name);
      String _plus = ("BaseRestClientImpl<" + _firstUpper);
      String inheritance = (_plus + ">");
      String _name_1 = domainEntity.getName();
      String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
      this.getImports(imports, _firstUpper_1, packageName);
      StringConcatenation _builder = new StringConcatenation();
      String _name_2 = domainEntity.getName();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateEntityRestClientClass = this.generateEntityRestClientClass("EntityRestClientTemplate", packageName, imports, _name_2, inheritance, _arrayList);
      _builder.append(_generateEntityRestClientClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public boolean getImports(final Set<String> imports, final String domainEntityName, final String packageName) {
    boolean _xblockexpression = false;
    {
      imports.add("android.content.Context");
      String _replace = packageName.replace("rest", "convert.");
      String _plus = (_replace + domainEntityName);
      String _plus_1 = (_plus + "Converter");
      imports.add(_plus_1);
      String _replace_1 = packageName.replace("rest", "convert.");
      String _plus_2 = (_replace_1 + "JsonConverter");
      imports.add(_plus_2);
      String _replace_2 = packageName.replace("rest", "model.");
      String _plus_3 = (_replace_2 + domainEntityName);
      boolean _add = imports.add(_plus_3);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateEntityRestClientClass(final String templateName, final String packageName, final Set<String> imports, final String domainEntityName, final String inheritance, final List<String> interfaces) {
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
    String _firstUpper = StringExtensions.toFirstUpper(domainEntityName);
    String _plus = (_firstUpper + "RestClient");
    CharSequence _classDecl = this.javaUtilities.classDecl(_plus, inheritance, interfaces, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateConstructors = this.generateConstructors(domainEntityName);
    _builder.append(_generateConstructors, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateMethodsToBeImplemented = this.generateMethodsToBeImplemented(domainEntityName);
    _builder.append(_generateMethodsToBeImplemented, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateConstructors(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    String _firstUpper = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper, "");
    _builder.append("RestClient(Context ctx, HttpClientFactory httpClientFactory) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super(ctx, httpClientFactory);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public ");
    String _firstUpper_1 = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper_1, "");
    _builder.append("RestClient(Context ctx) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("this(ctx, new HttpClientFactoryImpl());");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateMethodsToBeImplemented(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _entitiyListSuffix = this.getEntitiyListSuffix(domainEntityName);
    _builder.append(_entitiyListSuffix, "");
    _builder.newLineIfNotEmpty();
    CharSequence _createJSonConverter = this.createJSonConverter(domainEntityName);
    _builder.append(_createJSonConverter, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence getEntitiyListSuffix(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected String getEntityListSuffix(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return \"/");
    String _lowerCase = domainEntityName.toLowerCase();
    _builder.append(_lowerCase, "	");
    _builder.append("s\";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence createJSonConverter(final String domainEntityName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected JsonConverter<");
    String _firstUpper = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper, "");
    _builder.append("> createJsonConverter() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("return new ");
    String _firstUpper_1 = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper_1, "	");
    _builder.append("Converter();");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
