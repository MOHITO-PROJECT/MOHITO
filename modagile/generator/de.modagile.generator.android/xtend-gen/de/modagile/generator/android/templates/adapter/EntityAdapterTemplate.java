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

import com.google.inject.Inject;
import de.modagile.generator.android.templates.java.JavaUtils;
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
public class EntityAdapterTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateDomainAdapters(final String packagePrefix, final EClass domainEntity, final IFileSystemAccess fsa, final String outputConfiguration, final String appName, final String appVersion) {
    String _plus = (packagePrefix + "adapter/");
    String _name = domainEntity.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "Adapter.java");
    String _replaceAll = packagePrefix.replaceAll("/", ".");
    String _plus_3 = (_replaceAll + "adapter");
    CharSequence _generateDomainCode = this.generateDomainCode(domainEntity, _plus_3, appName, appVersion);
    fsa.generateFile(_plus_2, outputConfiguration, _generateDomainCode);
  }
  
  public CharSequence generateDomainCode(final EClass domainEntity, final String packageName, final String appName, final String appVersion) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      String inheritance = "CursorAdapter";
      imports.add("android.accounts.Account");
      imports.add("android.accounts.AccountManager");
      imports.add("android.app.Activity");
      imports.add("android.content.ContentResolver");
      imports.add("android.content.Context");
      imports.add("android.database.Cursor");
      imports.add("android.graphics.Color");
      imports.add("android.os.Bundle");
      imports.add("android.util.Log");
      imports.add("android.view.LayoutInflater");
      imports.add("android.view.View");
      imports.add("android.view.ViewGroup");
      imports.add("android.widget.CursorAdapter");
      imports.add("android.widget.TextView");
      String _replaceAll = packageName.replaceAll(".adapter", "");
      String _plus = (_replaceAll + ".R");
      imports.add(_plus);
      String _replaceAll_1 = packageName.replaceAll(".adapter", "");
      String _plus_1 = (_replaceAll_1 + ".constants.");
      String _plus_2 = (_plus_1 + appName);
      String _plus_3 = (_plus_2 + "Constants");
      imports.add(_plus_3);
      String _replaceAll_2 = packageName.replaceAll(".adapter", "");
      String _plus_4 = (_replaceAll_2 + ".constants.");
      String _name = domainEntity.getName();
      String _firstUpper = StringExtensions.toFirstUpper(_name);
      String _plus_5 = (_plus_4 + _firstUpper);
      String _plus_6 = (_plus_5 + "Constants");
      imports.add(_plus_6);
      String _replaceAll_3 = packageName.replaceAll(".adapter", "");
      String _plus_7 = (_replaceAll_3 + ".manager.impl.");
      String _name_1 = domainEntity.getName();
      String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
      String _plus_8 = (_plus_7 + _firstUpper_1);
      String _plus_9 = (_plus_8 + "ManagerImpl");
      imports.add(_plus_9);
      String _replaceAll_4 = packageName.replaceAll(".adapter", "");
      String _plus_10 = (_replaceAll_4 + ".model.");
      String _name_2 = domainEntity.getName();
      String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
      String _plus_11 = (_plus_10 + _firstUpper_2);
      imports.add(_plus_11);
      ArrayList<String> _arrayList = new ArrayList<String>();
      CharSequence _generateClass = this.generateClass("EntityAdapterTemplate", packageName, domainEntity, imports, inheritance, _arrayList, 
        null, appName, appVersion);
      _xblockexpression = (_generateClass);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateClass(final String templateName, final String packageName, final EClass domainEntity, final Set<String> imports, final String inheritance, final List<String> interfaces, final EReference obj, final String appName, final String appVersion) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(templateName);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    String _name = domainEntity.getName();
    String _plus = (_name + "Adapter");
    CharSequence _classDecl = this.javaUtilities.classDecl(_plus, inheritance, interfaces, false);
    _builder.append(_classDecl, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    String _name_1 = domainEntity.getName();
    CharSequence _generateAttributes = this.generateAttributes(_name_1, appName);
    _builder.append(_generateAttributes, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    String _name_2 = domainEntity.getName();
    CharSequence _generateConstructor = this.generateConstructor(_name_2, appName, appVersion);
    _builder.append(_generateConstructor, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    CharSequence _generateMethodsToBeImplemented = this.generateMethodsToBeImplemented(domainEntity, appName, appVersion);
    _builder.append(_generateMethodsToBeImplemented, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("}");
    return _builder;
  }
  
  public CharSequence generateAttributes(final String domainEntityName, final String appName) {
    StringConcatenation _builder = new StringConcatenation();
    String _plus = (domainEntityName + ".class.getName()");
    CharSequence _generateCodeForAttribute = this.javaUtilities.generateCodeForAttribute("String", "TAG", true, _plus);
    _builder.append(_generateCodeForAttribute, "");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    CharSequence _generateCodeForAttribute_1 = this.javaUtilities.generateCodeForAttribute("LayoutInflater", "mInflater", true, null);
    _builder.append(_generateCodeForAttribute_1, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    String _plus_1 = (domainEntityName + "ManagerImpl");
    String _plus_2 = ("m" + domainEntityName);
    String _plus_3 = (_plus_2 + "Manager");
    CharSequence _generateCodeForAttribute_2 = this.javaUtilities.generateCodeForAttribute(_plus_1, _plus_3, true, null);
    _builder.append(_generateCodeForAttribute_2, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    CharSequence _generateCodeForAttribute_3 = this.javaUtilities.generateCodeForAttribute("Context", "mCtx", true, null);
    _builder.append(_generateCodeForAttribute_3, "			");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    CharSequence _generateCodeForAttribute_4 = this.javaUtilities.generateCodeForAttribute("Account", "mAccount", false, null);
    _builder.append(_generateCodeForAttribute_4, "			");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateConstructor(final String domainEntityName, final String appName, final String appVersion) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    String _firstUpper = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper, "");
    _builder.append("Adapter(Context context, boolean autoRequery) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("super(context, null, autoRequery);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("mCtx = context;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("mAccount = null;");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Account[] accounts = AccountManager.get(mCtx).getAccountsByType(");
    _builder.append(appName, "	");
    _builder.append("Constants.");
    String _upperCase = appName.toUpperCase();
    String _plus = (_upperCase + appVersion);
    _builder.append(_plus, "	");
    _builder.append("AccountString);");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("for (Account account : accounts) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("mAccount = account;");
    _builder.newLine();
    _builder.append(" \t\t");
    _builder.append("break;");
    _builder.newLine();
    _builder.append(" \t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append(" \t");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("// create a cursor that the Adapter should use");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("String selection = ");
    String _firstUpper_1 = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper_1, " 	");
    _builder.append("Constants.DELETED + \"=0\";");
    _builder.newLineIfNotEmpty();
    _builder.append(" \t");
    _builder.append("Cursor c = context.getContentResolver().query(");
    String _firstUpper_2 = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper_2, " 	");
    _builder.append("Constants.CONTENT_URI, null, selection, null, null);");
    _builder.newLineIfNotEmpty();
    _builder.append(" \t");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("if (context instanceof Activity) {");
    _builder.newLine();
    _builder.append(" \t\t");
    _builder.append("((Activity) context).startManagingCursor(c);");
    _builder.newLine();
    _builder.append(" \t\t");
    _builder.append("changeCursor(c);");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("} else {");
    _builder.newLine();
    _builder.append(" \t\t");
    _builder.append("throw new IllegalArgumentException(\"Context cannot be cast to Activity\");");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("}");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("mInflater = LayoutInflater.from(context);");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("m");
    String _firstUpper_3 = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper_3, " 	");
    _builder.append("Manager = new ");
    String _firstUpper_4 = StringExtensions.toFirstUpper(domainEntityName);
    _builder.append(_firstUpper_4, " 	");
    _builder.append("ManagerImpl(context);");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateMethodsToBeImplemented(final EClass domainEntity, final String appName, final String appVersion) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateBindViewMethod = this.generateBindViewMethod(domainEntity);
    _builder.append(_generateBindViewMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateNewViewMethod = this.generateNewViewMethod(domainEntity);
    _builder.append(_generateNewViewMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateSyncDateBase = this.generateSyncDateBase(appName, appVersion);
    _builder.append(_generateSyncDateBase, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateBindViewMethod(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(" \t\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Reuse our existing View objects for Views (rows) if they already have been created");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public void bindView(View view, Context context, Cursor cursor) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// get the holder object from the row");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ViewHolder holder = (ViewHolder) view.getTag();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// get the ");
    String _name = domainEntity.getName();
    _builder.append(_name, "		");
    _builder.append(" we are dealing with from the cursor");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("Long id = cursor.getLong(cursor.getColumnIndex( ");
    String _name_1 = domainEntity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name_1);
    _builder.append(_firstUpper, "		");
    _builder.append("Constants.ID));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t ");
    String _name_2 = domainEntity.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_2);
    _builder.append(_firstUpper_1, "		 ");
    _builder.append(" p = m");
    String _name_3 = domainEntity.getName();
    String _firstUpper_2 = StringExtensions.toFirstUpper(_name_3);
    _builder.append(_firstUpper_2, "		 ");
    _builder.append("Manager.getById(id);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// fill the UI components in the row with the ");
    String _name_4 = domainEntity.getName();
    _builder.append(_name_4, "		");
    _builder.append("\'s data");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateNewViewMethod(final EClass domainEntity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Method is called when there is a new view (row) to be displayed for the first time. This is part of the ViewHolder");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* Pattern, we can cache to the View objects so they are not created and thrown away so often when scrolling the");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* list.");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public View newView(Context context, Cursor cursor, ViewGroup parent) {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// inflating means letting android create a view tree from the XML definition for us automatically");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("View row = mInflater.inflate(R.layout.");
    String _name = domainEntity.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "		");
    _builder.append("row, null);");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// save the created views to a holder instance");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ViewHolder holder = new ViewHolder();");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// attach the holder instance to the row so we can easily retrieve that any time from the row itself");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("row.setTag(holder);");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// make the list more eye friendly with alternating row colors");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (cursor.getPosition() % 2 != 0) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("row.setBackgroundColor(Color.DKGRAY);");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Log.d(TAG, \"Row has been cached\");");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return row;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateViewHolderClass(final EReference classifier) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* The ViewHolder class to store the cached views <br>");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Design decision: Static to save the reference to the outer class and to avoid access to any members of the");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* containing class");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("protected static class ViewHolder {");
    _builder.newLine();
    {
      boolean _isContainment = classifier.isContainment();
      if (_isContainment) {
        {
          EClass _eReferenceType = classifier.getEReferenceType();
          EList<EAttribute> _eAttributes = _eReferenceType.getEAttributes();
          for(final EAttribute attr : _eAttributes) {
            _builder.append("private TextView ");
            String _name = attr.getName();
            _builder.append(_name, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      } else {
        _builder.append(" \t\t\t");
        _builder.append("private TextView ");
        EClassifier _eType = classifier.getEType();
        String _name_1 = _eType.getName();
        String _lowerCase = _name_1.toLowerCase();
        _builder.append(_lowerCase, " 			");
        _builder.append("UUID;");
        _builder.newLineIfNotEmpty();
        _builder.append(" \t\t\t");
        _builder.append("private TextView ");
        String _name_2 = classifier.getName();
        _builder.append(_name_2, " 			");
        _builder.append("UUID;");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateSyncDateBase(final String appName, final String appVersion) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Initiates an asynchronous refresh for the list.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void syncDatabase() {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("// trigger the content resolver to do its job on demand");
    _builder.newLine();
    _builder.append("ContentResolver.requestSync(mAccount, ");
    _builder.append(appName, "");
    _builder.append("Constants.");
    String _upperCase = appName.toUpperCase();
    String _plus = (_upperCase + appVersion);
    _builder.append(_plus, "");
    _builder.append("_SYNC_AUTHORITY, new Bundle());");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
