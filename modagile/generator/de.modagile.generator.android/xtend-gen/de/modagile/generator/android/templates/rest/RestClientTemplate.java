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
import de.modagile.metamodel.app.MobileApp;
import info.multiplatform.generator.java.helper.Pair;
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
public class RestClientTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateRestClient(final String packagePrefix, final MobileApp m, final IFileSystemAccess fsa, final String outputConfiguration) {
    String _plus = (packagePrefix + "rest/RestClient.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_1 = (_replaceAll + "rest");
    CharSequence _generateRestClientCode = this.generateRestClientCode(m, _plus_1);
    fsa.generateFile(_plus, outputConfiguration, _generateRestClientCode);
  }
  
  public CharSequence generateRestClientCode(final MobileApp m, final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      imports.add("android.content.Context");
      StringConcatenation _builder = new StringConcatenation();
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateClass = this.generateClass("RestClientTemplate", packageName, m, imports, null, _arrayList);
      _builder.append(_generateClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateClass(final String templateName, final String packageName, final MobileApp m, final Set<String> imports, final String inheritance, final List<String> interfaces) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> usedEntities = _arrayList;
      ArrayList<Pair<String,String>> _arrayList_1 = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> restAttributes = _arrayList_1;
      StringConcatenation _builder = new StringConcatenation();
      this.getUsedEntitiesInApp(m, usedEntities);
      _builder.newLineIfNotEmpty();
      this.getAdditionalAttributes(usedEntities, restAttributes);
      _builder.newLineIfNotEmpty();
      CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
      _builder.append(_statementGenerated, "");
      _builder.newLineIfNotEmpty();
      CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
      _builder.append(_packageStatement, "");
      _builder.newLineIfNotEmpty();
      CharSequence _importStatements = this.javaUtilities.importStatements(imports);
      _builder.append(_importStatements, "");
      _builder.newLineIfNotEmpty();
      CharSequence _classDecl = this.javaUtilities.classDecl("RestClient", inheritance, interfaces, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateAdditionalAttributes = this.javaUtilities.generateAdditionalAttributes(restAttributes);
      _builder.append(_generateAdditionalAttributes, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateRestConstructors = this.generateRestConstructors(usedEntities);
      _builder.append(_generateRestConstructors, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public void getUsedEntitiesInApp(final MobileApp m, final List<String> usedEntites) {
    List<EClass> _allDomainEntities = this.javaUtilities.getAllDomainEntities(m);
    for (final EClass domainEntity : _allDomainEntities) {
      String _name = domainEntity.getName();
      String _plus = (_name + "RestClient");
      usedEntites.add(_plus);
    }
  }
  
  public void getAdditionalAttributes(final List<String> usedEntities, final List<Pair<String,String>> restAttributes) {
    for (final String entity : usedEntities) {
      String _firstUpper = StringExtensions.toFirstUpper(entity);
      Pair<String,String> _pair = new Pair<String,String>(_firstUpper, entity);
      restAttributes.add(_pair);
    }
  }
  
  public CharSequence generateRestConstructors(final List<String> entityRestClients) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Pair<String,String>> _arrayList = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> defconstructorParameters = _arrayList;
      String defContstructorBody = "";
      ArrayList<Pair<String,String>> _arrayList_1 = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> constructorParameters = _arrayList_1;
      String contructorBody = "";
      Pair<String,String> _pair = new Pair<String,String>("Context", "ctx");
      defconstructorParameters.add(_pair);
      Pair<String,String> _get = defconstructorParameters.get(0);
      constructorParameters.add(_get);
      Pair<String,String> _pair_1 = new Pair<String,String>("HttpClientFactory", "httpClientFactory");
      constructorParameters.add(_pair_1);
      Pair<String,String> _get_1 = defconstructorParameters.get(0);
      String _secondElement = _get_1.getSecondElement();
      String _plus = ("this(" + _secondElement);
      String _plus_1 = (_plus + ", HttpClientFactoryImpl.getInstance());");
      defContstructorBody = _plus_1;
      for (final String entity : entityRestClients) {
        String _plus_2 = (contructorBody + entity);
        String _plus_3 = (_plus_2 + " = new ");
        String _firstUpper = StringExtensions.toFirstUpper(entity);
        String _plus_4 = (_plus_3 + _firstUpper);
        String _plus_5 = (_plus_4 + "(");
        Pair<String,String> _get_2 = constructorParameters.get(0);
        String _secondElement_1 = _get_2.getSecondElement();
        String _plus_6 = (_plus_5 + _secondElement_1);
        String _plus_7 = (_plus_6 + ",");
        Pair<String,String> _get_3 = constructorParameters.get(1);
        String _secondElement_2 = _get_3.getSecondElement();
        String _plus_8 = (_plus_7 + _secondElement_2);
        String _plus_9 = (_plus_8 + ");");
        contructorBody = _plus_9;
      }
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateConstructorDecl = this.javaUtilities.generateConstructorDecl("RestClient", defconstructorParameters, defContstructorBody);
      _builder.append(_generateConstructorDecl, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateConstructorDecl_1 = this.javaUtilities.generateConstructorDecl("RestClient", constructorParameters, contructorBody);
      _builder.append(_generateConstructorDecl_1, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateMethodsForAttributes = this.generateMethodsForAttributes(entityRestClients);
      _builder.append(_generateMethodsForAttributes, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateMethodsForAttributes(final List<String> entityRestClients) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final String entityRestClient : entityRestClients) {
        _builder.append("public ");
        _builder.append(entityRestClient, "");
        _builder.append(" get");
        _builder.append(entityRestClient, "");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return ");
        _builder.append(entityRestClient, "	");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
}
