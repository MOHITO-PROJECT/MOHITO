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
package de.modagile.generator.android.templates.adapter;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.adapter.ListAdapterTemplate;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.domain.ListBinding;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ListAdapterViewHolderImplTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  @Inject
  @Extension
  private ListAdapterTemplate listAdapter;
  
  public void generateListAdapterViewHolderImpl(final IFileSystemAccess fsa, final ListBinding listBinding, final PackageInfo packageInfo, final MobileApp m) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "/adapter/");
    DynamicList _listElement = listBinding.getListElement();
    String _name = _listElement.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "ListAdapterViewHolderImpl.java");
    CharSequence _generateListAdapterViewHolderImplCode = this.generateListAdapterViewHolderImplCode(listBinding, packageInfo, m);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_MAN, _generateListAdapterViewHolderImplCode);
  }
  
  private CharSequence generateListAdapterViewHolderImplCode(final ListBinding listBinding, final PackageInfo packageInfo, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      DynamicList _listElement = listBinding.getListElement();
      String _name = _listElement.getName();
      final String inheritedClass = (_name + "ListAdapterViewHolder");
      String className = (inheritedClass + "Impl");
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      this.listAdapter.getListAdapterImports(imports, packageInfo, listBinding);
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".adapter.");
      DynamicList _listElement_1 = listBinding.getListElement();
      String _name_1 = _listElement_1.getName();
      String _plus_1 = (_plus + _name_1);
      String _plus_2 = (_plus_1 + "ListAdapter.");
      String _plus_3 = (_plus_2 + inheritedClass);
      imports.add(_plus_3);
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _statementGenerated = this.javaUtilities.statementGenerated("AppConstantsTemplate");
      _builder.append(_statementGenerated, "");
      _builder.newLineIfNotEmpty();
      String _packageName_1 = packageInfo.getPackageName();
      String _plus_4 = (_packageName_1 + ".adapter");
      CharSequence _packageStatement = this.javaUtilities.packageStatement(_plus_4);
      _builder.append(_packageStatement, "");
      _builder.newLineIfNotEmpty();
      CharSequence _importStatements = this.javaUtilities.importStatements(imports);
      _builder.append(_importStatements, "");
      _builder.newLineIfNotEmpty();
      CharSequence _classDecl = this.javaUtilities.classDecl(className, inheritedClass, null, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      {
        EReference _domainReference = listBinding.getDomainReference();
        EClass _eReferenceType = _domainReference.getEReferenceType();
        EList<EAttribute> _eAttributes = _eReferenceType.getEAttributes();
        for(final EAttribute attribute : _eAttributes) {
          BindingRepository _bindingRepository = m.getBindingRepository();
          final DisplayElement displayElementInBindingForAttribute = this.javaUtilities.getRelatedStringBoundDisplayElementForListAttribute(_bindingRepository, attribute, listBinding);
          _builder.newLineIfNotEmpty();
          {
            boolean _notEquals = (!Objects.equal(displayElementInBindingForAttribute, null));
            if (_notEquals) {
              CharSequence _generateRequiredAttributeHookMethod = this.generateRequiredAttributeHookMethod(listBinding, attribute, displayElementInBindingForAttribute);
              _builder.append(_generateRequiredAttributeHookMethod, "");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("}     ");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateRequiredAttributeHookMethod(final ListBinding listBinding, final EAttribute attribute, final DisplayElement displayElement) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    String _viewHolderRequiredMethodReturnType = this.listAdapter.getViewHolderRequiredMethodReturnType(displayElement);
    _builder.append(_viewHolderRequiredMethodReturnType, "");
    _builder.append(" ");
    String _viewHolderAttributeMethodForDisplayElement = this.listAdapter.getViewHolderAttributeMethodForDisplayElement(attribute, displayElement);
    _builder.append(_viewHolderAttributeMethodForDisplayElement, "");
    _builder.append("(");
    EReference _domainReference = listBinding.getDomainReference();
    EClass _eReferenceType = _domainReference.getEReferenceType();
    String _name = _eReferenceType.getName();
    _builder.append(_name, "");
    _builder.append(" ");
    EReference _domainReference_1 = listBinding.getDomainReference();
    EClass _eReferenceType_1 = _domainReference_1.getEReferenceType();
    String _name_1 = _eReferenceType_1.getName();
    String _lowerCase = _name_1.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append(", Context context){");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("//TODO add app specific behavoir manually and remove the default return value.");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return  ");
    EReference _domainReference_2 = listBinding.getDomainReference();
    EClass _eReferenceType_2 = _domainReference_2.getEReferenceType();
    String _name_2 = _eReferenceType_2.getName();
    String _lowerCase_1 = _name_2.toLowerCase();
    _builder.append(_lowerCase_1, "    ");
    _builder.append(".get");
    String _name_3 = attribute.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper, "    ");
    _builder.append("();");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.newLine();
    return _builder;
  }
}
