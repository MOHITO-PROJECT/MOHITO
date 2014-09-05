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
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.domain.ListBinding;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import info.multiplatform.generator.java.helper.Pair;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ListAdapterTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateListAdapter(final IFileSystemAccess fsa, final ListBinding listBinding, final PackageInfo packageInfo, final MobileApp m) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "/adapter/");
    DynamicList _listElement = listBinding.getListElement();
    String _name = _listElement.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "ListAdapter.java");
    CharSequence _generateListAdapterCode = this.generateListAdapterCode(listBinding, packageInfo, m);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateListAdapterCode);
  }
  
  private CharSequence generateListAdapterCode(final ListBinding listBinding, final PackageInfo packageInfo, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      DynamicList _listElement = listBinding.getListElement();
      String _name = _listElement.getName();
      final String className = (_name + "ListAdapter");
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      ArrayList<String> _arrayList = new ArrayList<String>();
      List<String> interfaces = _arrayList;
      interfaces.add("LoaderCallbacks<Cursor>");
      ArrayList<Pair<String,String>> _arrayList_1 = new ArrayList<Pair<String,String>>();
      List<Pair<String,String>> entityAttributes = _arrayList_1;
      this.getListAdapterImports(imports, packageInfo, listBinding);
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _statementGenerated = this.javaUtilities.statementGenerated("AppConstantsTemplate");
      _builder.append(_statementGenerated, "");
      _builder.newLineIfNotEmpty();
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".adapter");
      CharSequence _packageStatement = this.javaUtilities.packageStatement(_plus);
      _builder.append(_packageStatement, "");
      _builder.newLineIfNotEmpty();
      _builder.append("        ");
      CharSequence _importStatements = this.javaUtilities.importStatements(imports);
      _builder.append(_importStatements, "        ");
      _builder.newLineIfNotEmpty();
      CharSequence _classDecl = this.javaUtilities.classDecl(className, "CursorAdapter", interfaces, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      _builder.append("private static final String TAG = ");
      _builder.append(className, "");
      _builder.append(".class.getName();");
      _builder.newLineIfNotEmpty();
      _builder.append("        ");
      _builder.append("private final LayoutInflater mInflater;");
      _builder.newLine();
      _builder.append("        ");
      _builder.append("private final Context mContext;");
      _builder.newLine();
      _builder.append("        ");
      CharSequence _generateAdditionalAttributes = this.javaUtilities.generateAdditionalAttributes(entityAttributes);
      _builder.append(_generateAdditionalAttributes, "        ");
      _builder.newLineIfNotEmpty();
      CharSequence _generateAdapterConstructor = this.generateAdapterConstructor(className);
      _builder.append(_generateAdapterConstructor, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateCursorAdapterMethods = this.generateCursorAdapterMethods(listBinding, className, m);
      _builder.append(_generateCursorAdapterMethods, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateLoadManagerMethods = this.generateLoadManagerMethods(listBinding);
      _builder.append(_generateLoadManagerMethods, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateAbstractClassViewHolder = this.generateAbstractClassViewHolder(className, listBinding, m);
      _builder.append(_generateAbstractClassViewHolder, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateCursorAdapterMethods(final ListBinding listBinding, final String className, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateMethodNewView = this.generateMethodNewView(listBinding, className, m);
    _builder.append(_generateMethodNewView, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateMethodBindView = this.generateMethodBindView(className, listBinding, m);
    _builder.append(_generateMethodBindView, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateAdapterConstructor(final String className) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    _builder.append(className, "");
    _builder.append("(Context context, boolean autoRequery) {");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("super(context, null, autoRequery);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("mContext = context;");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("mInflater = LayoutInflater.from(context);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodNewView(final ListBinding listBinding, final String className, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      EReference _domainReference = listBinding.getDomainReference();
      EClass domainReferenceType = _domainReference.getEReferenceType();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("        ");
      _builder.append("public View newView(Context context, Cursor cursor, ViewGroup parent) {");
      _builder.newLine();
      _builder.append("            ");
      _builder.append("View row = mInflater.inflate(R.layout.");
      DynamicList _listElement = listBinding.getListElement();
      String _name = _listElement.getName();
      String _lowerCase = _name.toLowerCase();
      _builder.append(_lowerCase, "            ");
      _builder.append("row, null);");
      _builder.newLineIfNotEmpty();
      _builder.append("            ");
      _builder.append(className, "            ");
      _builder.append("ViewHolder holder = new ");
      _builder.append(className, "            ");
      _builder.append("ViewHolderImpl();");
      _builder.newLineIfNotEmpty();
      {
        EList<EAttribute> _eAttributes = domainReferenceType.getEAttributes();
        for(final EAttribute attribute : _eAttributes) {
          _builder.append("            ");
          BindingRepository _bindingRepository = m.getBindingRepository();
          final DisplayElement displayElementInBindingForAttribute = this.javaUtilities.getRelatedStringBoundDisplayElementForListAttribute(_bindingRepository, attribute, listBinding);
          _builder.newLineIfNotEmpty();
          {
            boolean _notEquals = (!Objects.equal(displayElementInBindingForAttribute, null));
            if (_notEquals) {
              _builder.append("            ");
              _builder.append("holder.");
              String _name_1 = attribute.getName();
              _builder.append(_name_1, "            ");
              _builder.append(" = (");
              String _displayElementType = this.javaUtilities.getDisplayElementType(displayElementInBindingForAttribute);
              _builder.append(_displayElementType, "            ");
              _builder.append(") row.findViewById(R.id.");
              String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(displayElementInBindingForAttribute);
              String _name_2 = displayElementInBindingForAttribute.getName();
              String _firstUpper = StringExtensions.toFirstUpper(_name_2);
              String _plus = (_displayElementPrefix + _firstUpper);
              _builder.append(_plus, "            ");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("            ");
      _builder.append("row.setTag(holder);");
      _builder.newLine();
      _builder.append("            ");
      _builder.append("Log.d(TAG, \"Row has been cached\");");
      _builder.newLine();
      _builder.append("            ");
      _builder.append("return row;");
      _builder.newLine();
      _builder.append("        ");
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateMethodBindView(final String className, final ListBinding listBinding, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void bindView(View view, Context context, Cursor cursor) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append(className, "        ");
    _builder.append("ViewHolder holder = (");
    _builder.append(className, "        ");
    _builder.append("ViewHolder) view.getTag();");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("        ");
    EReference _domainReference = listBinding.getDomainReference();
    EClass _eReferenceType = _domainReference.getEReferenceType();
    String _name = _eReferenceType.getName();
    _builder.append(_name, "        ");
    _builder.append(" ");
    EReference _domainReference_1 = listBinding.getDomainReference();
    EClass _eReferenceType_1 = _domainReference_1.getEReferenceType();
    String _name_1 = _eReferenceType_1.getName();
    String _lowerCase = _name_1.toLowerCase();
    _builder.append(_lowerCase, "        ");
    _builder.append(" = ");
    EReference _domainReference_2 = listBinding.getDomainReference();
    EClass _eReferenceType_2 = _domainReference_2.getEReferenceType();
    String _name_2 = _eReferenceType_2.getName();
    _builder.append(_name_2, "        ");
    _builder.append("Converter.cursorTo");
    EReference _domainReference_3 = listBinding.getDomainReference();
    EClass _eReferenceType_3 = _domainReference_3.getEReferenceType();
    String _name_3 = _eReferenceType_3.getName();
    _builder.append(_name_3, "        ");
    _builder.append("(cursor);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      EReference _domainReference_4 = listBinding.getDomainReference();
      EClass _eReferenceType_4 = _domainReference_4.getEReferenceType();
      EList<EAttribute> _eAttributes = _eReferenceType_4.getEAttributes();
      for(final EAttribute attributeInEntityReference : _eAttributes) {
        _builder.append("        ");
        BindingRepository _bindingRepository = m.getBindingRepository();
        final DisplayElement displayElementInBindingForAttribute = this.javaUtilities.getRelatedStringBoundDisplayElementForListAttribute(_bindingRepository, attributeInEntityReference, listBinding);
        _builder.newLineIfNotEmpty();
        {
          boolean _notEquals = (!Objects.equal(displayElementInBindingForAttribute, null));
          if (_notEquals) {
            _builder.append("        ");
            _builder.append("   ");
            _builder.append("holder.");
            String _name_4 = attributeInEntityReference.getName();
            _builder.append(_name_4, "           ");
            _builder.append(".");
            String _viewHolderAttributeMethodOppositeForDisplayElement = this.getViewHolderAttributeMethodOppositeForDisplayElement(attributeInEntityReference, displayElementInBindingForAttribute);
            _builder.append(_viewHolderAttributeMethodOppositeForDisplayElement, "           ");
            _builder.append("(holder.");
            String _viewHolderAttributeMethodForDisplayElement = this.getViewHolderAttributeMethodForDisplayElement(attributeInEntityReference, displayElementInBindingForAttribute);
            _builder.append(_viewHolderAttributeMethodForDisplayElement, "           ");
            _builder.append("(");
            EReference _domainReference_5 = listBinding.getDomainReference();
            EClass _eReferenceType_5 = _domainReference_5.getEReferenceType();
            String _name_5 = _eReferenceType_5.getName();
            String _lowerCase_1 = _name_5.toLowerCase();
            _builder.append(_lowerCase_1, "           ");
            _builder.append(", context));");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateLoadManagerMethods(final ListBinding listBinding) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateMethodOnCreateLoader = this.generateMethodOnCreateLoader(listBinding);
    _builder.append(_generateMethodOnCreateLoader, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateMethodOnLoaderReset = this.generateMethodOnLoaderReset();
    _builder.append(_generateMethodOnLoaderReset, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateMethodOnLoadFinished = this.generateMethodOnLoadFinished();
    _builder.append(_generateMethodOnLoadFinished, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateMethodOnCreateLoader(final ListBinding listBinding) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public Loader<Cursor> onCreateLoader(int id, Bundle args) {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("return new CursorLoader(mContext, DBConstants.CONTENT_URI_");
    EReference _domainReference = listBinding.getDomainReference();
    EClass _eReferenceType = _domainReference.getEReferenceType();
    String _name = _eReferenceType.getName();
    String _upperCase = _name.toUpperCase();
    _builder.append(_upperCase, "   ");
    _builder.append(", null, null, null, null);");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodOnLoaderReset() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void onLoaderReset(final Loader<Cursor> loader) {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("swapCursor(null);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateMethodOnLoadFinished() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void onLoadFinished(final Loader<Cursor> loader, final Cursor cursor) {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("swapCursor(cursor);");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("notifyDataSetChanged();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateAbstractClassViewHolder(final String className, final ListBinding listBinding, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    String _plus = (className + "ViewHolder");
    CharSequence _innerClassDecl = this.javaUtilities.innerClassDecl("public", _plus, null, null, true, true);
    _builder.append(_innerClassDecl, "");
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
            _builder.append("protected ");
            String _displayElementType = this.javaUtilities.getDisplayElementType(displayElementInBindingForAttribute);
            _builder.append(_displayElementType, "");
            _builder.append(" ");
            String _name = attribute.getName();
            _builder.append(_name, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("public abstract ");
            String _viewHolderRequiredMethodReturnType = this.getViewHolderRequiredMethodReturnType(displayElementInBindingForAttribute);
            _builder.append(_viewHolderRequiredMethodReturnType, "");
            _builder.append(" ");
            String _viewHolderAttributeMethodForDisplayElement = this.getViewHolderAttributeMethodForDisplayElement(attribute, displayElementInBindingForAttribute);
            _builder.append(_viewHolderAttributeMethodForDisplayElement, "");
            _builder.append("(");
            EReference _domainReference_1 = listBinding.getDomainReference();
            EClass _eReferenceType_1 = _domainReference_1.getEReferenceType();
            String _name_1 = _eReferenceType_1.getName();
            _builder.append(_name_1, "");
            _builder.append(" ");
            EReference _domainReference_2 = listBinding.getDomainReference();
            EClass _eReferenceType_2 = _domainReference_2.getEReferenceType();
            String _name_2 = _eReferenceType_2.getName();
            String _lowerCase = _name_2.toLowerCase();
            _builder.append(_lowerCase, "");
            _builder.append(", Context context);");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}     ");
    _builder.newLine();
    return _builder;
  }
  
  public boolean getListAdapterImports(final Set<String> imports, final PackageInfo packageInfo, final ListBinding listBinding) {
    boolean _xblockexpression = false;
    {
      imports.add("android.app.Activity");
      imports.add("android.content.Context");
      imports.add("android.view.LayoutInflater");
      imports.add("android.view.View");
      imports.add("android.view.ViewGroup");
      imports.add("android.widget.CursorAdapter");
      imports.add("android.widget.TextView");
      imports.add("com.actionbarsherlock.app.SherlockFragmentActivity");
      imports.add("android.app.LoaderManager.LoaderCallbacks");
      imports.add("android.content.Loader");
      imports.add("android.database.Cursor");
      imports.add("android.content.CursorLoader");
      imports.add("android.os.Bundle");
      imports.add("android.util.Log");
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".R");
      imports.add(_plus);
      String _packageName_1 = packageInfo.getPackageName();
      String _plus_1 = (_packageName_1 + ".constants.DBConstants");
      imports.add(_plus_1);
      String _packageName_2 = packageInfo.getPackageName();
      String _plus_2 = (_packageName_2 + ".model.");
      EReference _domainReference = listBinding.getDomainReference();
      EClassifier _eType = _domainReference.getEType();
      String _name = _eType.getName();
      String _plus_3 = (_plus_2 + _name);
      imports.add(_plus_3);
      String _packageName_3 = packageInfo.getPackageName();
      String _plus_4 = (_packageName_3 + ".model.");
      EReference _domainReference_1 = listBinding.getDomainReference();
      EClassifier _eType_1 = _domainReference_1.getEType();
      String _name_1 = _eType_1.getName();
      String _plus_5 = (_plus_4 + _name_1);
      String _plus_6 = (_plus_5 + "Converter");
      boolean _add = imports.add(_plus_6);
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  public String getViewHolderAttributeMethodForDisplayElement(final EAttribute attribute, final DisplayElement displayElement) {
    String _switchResult = null;
    String _viewHolderRequiredMethodReturnType = this.getViewHolderRequiredMethodReturnType(displayElement);
    final String getViewHolderRequiredMethodReturnType = _viewHolderRequiredMethodReturnType;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(getViewHolderRequiredMethodReturnType,"String")) {
        _matched=true;
        String _name = attribute.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        String _plus = ("getText" + _firstUpper);
        _switchResult = _plus;
      }
    }
    if (!_matched) {
      if (Objects.equal(getViewHolderRequiredMethodReturnType,"boolean")) {
        _matched=true;
        String _name_1 = attribute.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
        String _plus_1 = ("isChecked" + _firstUpper_1);
        _switchResult = _plus_1;
      }
    }
    if (!_matched) {
      if (Objects.equal(getViewHolderRequiredMethodReturnType,"Adress")) {
        _matched=true;
        String _name_2 = attribute.getName();
        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
        String _plus_2 = ("setAdress" + _firstUpper_2);
        _switchResult = _plus_2;
      }
    }
    return _switchResult;
  }
  
  public String getViewHolderAttributeMethodOppositeForDisplayElement(final EAttribute attribute, final DisplayElement displayElement) {
    String _switchResult = null;
    String _viewHolderRequiredMethodReturnType = this.getViewHolderRequiredMethodReturnType(displayElement);
    final String getViewHolderRequiredMethodReturnType = _viewHolderRequiredMethodReturnType;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(getViewHolderRequiredMethodReturnType,"String")) {
        _matched=true;
        _switchResult = "setText";
      }
    }
    if (!_matched) {
      if (Objects.equal(getViewHolderRequiredMethodReturnType,"boolean")) {
        _matched=true;
        _switchResult = "setChecked";
      }
    }
    if (!_matched) {
      if (Objects.equal(getViewHolderRequiredMethodReturnType,"Adress")) {
        _matched=true;
        _switchResult = "setAdress";
      }
    }
    return _switchResult;
  }
  
  public String getViewHolderRequiredMethodReturnType(final DisplayElement displayElement) {
    String _switchResult = null;
    EClass _eClass = displayElement.eClass();
    String _name = _eClass.getName();
    final String _switchValue = _name;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(_switchValue,"Button")) {
        _matched=true;
        _switchResult = "String";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"Image")) {
        _matched=true;
        _switchResult = "String";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"Input")) {
        _matched=true;
        _switchResult = "String";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"Label")) {
        _matched=true;
        _switchResult = "String";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"CheckBox")) {
        _matched=true;
        _switchResult = "boolean";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"DynamicList")) {
        _matched=true;
        _switchResult = "";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"LocationPicker")) {
        _matched=true;
        _switchResult = "Adress";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"Datepicker")) {
        _matched=true;
        _switchResult = "";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"ImageButton")) {
        _matched=true;
        _switchResult = "String";
      }
    }
    return _switchResult;
  }
}
