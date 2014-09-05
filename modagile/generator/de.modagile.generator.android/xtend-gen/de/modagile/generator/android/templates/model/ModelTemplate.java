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
package de.modagile.generator.android.templates.model;

import com.google.inject.Inject;
import info.multiplatform.generator.java.helper.Pair;
import info.multiplatform.generator.java.templates.JavaFolderConstants;
import info.multiplatform.generator.java.templates.JavaUtils;
import info.multiplatform.generator.java.templates.PackageInfo;
import info.multiplatform.generator.java.templates.UtilityFunctions;
import info.multiplatform.generator.java.templates.model.DomainModelTemplate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * Contains modagile mobile specific specific model generator methods.
 * 
 * It provides template methods to generate the Entity super class and extends
 * the generic model template to customize the generated data model in terms
 * of extending the super entity class etc.
 */
@SuppressWarnings("all")
public class ModelTemplate extends DomainModelTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  @Inject
  @Extension
  private UtilityFunctions utilities;
  
  /**
   * Generates an abstract Entity as a super class for all domain entities
   */
  public void generateAbstractEntity(final IFileSystemAccess fsa, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "model/Entity.java");
    String _packageName = packageInfo.getPackageName();
    String _plus_1 = (_packageName + ".model");
    CharSequence _generateEntityModelCode = this.generateEntityModelCode(_plus_1);
    fsa.generateFile(_plus, JavaFolderConstants.SRC_GEN, _generateEntityModelCode);
  }
  
  /**
   * Generate the code for the Entity super class.
   */
  private CharSequence generateEntityModelCode(final String packageName) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> interfaces = _arrayList;
      ArrayList<Pair<String,String>> _arrayList_1 = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> entityAttributes = _arrayList_1;
      imports.add("java.io.Serializable");
      interfaces.add("Serializable");
      this.getEntityAttributes(entityAttributes);
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateEntityAttributesClass = this.generateEntityAttributesClass("ModelTemplate", packageName, imports, null, interfaces, entityAttributes);
      _builder.append(_generateEntityAttributesClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  /**
   * Get the attributes the Entity super class should have.
   */
  private boolean getEntityAttributes(final List<Pair<String,String>> entityAttributes) {
    boolean _xblockexpression = false;
    {
      Pair<String,String> _pair = new Pair<String,String>("Long", "id");
      entityAttributes.add(_pair);
      Pair<String,String> _pair_1 = new Pair<String,String>("Long", "lastUpdate");
      boolean _add = entityAttributes.add(_pair_1);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  /**
   * Generate the code of the abstract Entity super class.
   */
  private CharSequence generateEntityAttributesClass(final String templateName, final String packageName, final Set<String> imports, final String inheritance, final List<String> interfaces, final List<Pair<String,String>> entityAttributes) {
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
    CharSequence _classDecl = this.javaUtilities.classDecl("Entity", inheritance, interfaces, true);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _gernerateSerializeableID = this.javaUtilities.gernerateSerializeableID(packageName, packageName, "Entity");
    _builder.append(_gernerateSerializeableID, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateAdditionalAttributes = this.javaUtilities.generateAdditionalAttributes(entityAttributes);
    _builder.append(_generateAdditionalAttributes, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateSetterGetterForAdditionalAttributes = this.utilities.generateSetterGetterForAdditionalAttributes(entityAttributes);
    _builder.append(_generateSetterGetterForAdditionalAttributes, "");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Customize the generic template method to let the domain classes extend the entity class.
   */
  public String getSuperClass(final PackageInfo packageInfo, final EClass domainEntity) {
    return "Entity";
  }
  
  protected Object generateAdditionalFields(final PackageInfo packageInfo, final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    String _packageName = packageInfo.getPackageName();
    String _packageName_1 = packageInfo.getPackageName();
    String _name = domainEntity.getName();
    CharSequence _gernerateSerializeableID = this.javaUtilities.gernerateSerializeableID(_packageName, _packageName_1, _name);
    _builder.append(_gernerateSerializeableID, "");
    _builder.newLineIfNotEmpty();
    _builder.append("public static final String TAG = ");
    String _name_1 = domainEntity.getName();
    _builder.append(_name_1, "");
    _builder.append(".class.getSimpleName();");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  protected List<String> getAdditionalImports(final PackageInfo packageInfo, final EClass domainEntity) {
    ArrayList<String> _arrayList = new ArrayList<String>();
    List<String> imports = _arrayList;
    imports.add("android.content.ContentValues");
    String _packageName = packageInfo.getPackageName();
    String _plus = (_packageName + ".constants.");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "DBConstants");
    imports.add(_plus_2);
    return imports;
  }
}
