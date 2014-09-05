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
package de.modagile.generator.android.templates.activity;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import de.modagile.generator.android.templates.DomainModelUtils;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.activity.ActivitiesTemplate;
import de.modagile.generator.android.templates.activity.DatepickerAdditionsTemplate;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.domain.BooleanBinding;
import de.modagile.metamodel.app.domain.DomainBinding;
import de.modagile.metamodel.app.domain.PrimitiveBinding;
import de.modagile.metamodel.app.domain.StringBinding;
import de.modagile.metamodel.app.domain.UpdateStrategy;
import de.modagile.metamodel.app.event.ButtonClickEvent;
import de.modagile.metamodel.app.event.Event;
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CheckBox;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.Flow;
import de.modagile.metamodel.app.ui.Input;
import de.modagile.metamodel.app.ui.InputContext;
import de.modagile.metamodel.app.ui.LocationPicker;
import de.modagile.metamodel.app.ui.MenuBar;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import de.modagile.metamodel.app.ui.TextContaining;
import info.multiplatform.generator.java.helper.Triple;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Responsible to create hooks for activities.
 */
@SuppressWarnings("all")
public class ActivitiesHooksTemplate {
  @Inject
  @Extension
  private ActivitiesTemplate activitiesTemplate;
  
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  @Inject
  @Extension
  private DatepickerAdditionsTemplate datepickerAdditions;
  
  @Inject
  @Extension
  private DomainModelUtils domainModelUtils;
  
  public void generateActivitiesHooks(final IFileSystemAccess fsa, final Screen screen, final MobileApp m, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "activity/");
    String _name = screen.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + "HookActivity.java");
    CharSequence _generateActivityHookCode = this.generateActivityHookCode(screen, packageInfo, m);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_MAN, _generateActivityHookCode);
  }
  
  private CharSequence generateActivityHookCode(final Screen screen, final PackageInfo packageInfo, final MobileApp mobileApp) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      this.activitiesTemplate.getAllImports(screen, packageInfo, mobileApp, imports);
      String _name = screen.getName();
      String _firstUpper = StringExtensions.toFirstUpper(_name);
      String inheritance = (_firstUpper + "Activity");
      StringConcatenation _builder = new StringConcatenation();
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".activity");
      CharSequence _generateClass = this.generateClass(_plus, screen, imports, packageInfo, inheritance, null, mobileApp);
      _builder.append(_generateClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private void getRequiredWriteAndWriteThroughImports(final Screen screen, final PackageInfo packageinfo, final BindingRepository bindingRepository, final Set<String> imports) {
    Set<EClass> entitiesInScreen = this.javaUtilities.collectEntitiesForScreenByPrimitiveBindings(screen, bindingRepository);
    for (final EClass entity : entitiesInScreen) {
      {
        String _packageName = packageinfo.getPackageName();
        String _plus = (_packageName + ".manager.");
        String _name = entity.getName();
        String _plus_1 = (_plus + _name);
        String _plus_2 = (_plus_1 + "Manager");
        imports.add(_plus_2);
        String _packageName_1 = packageinfo.getPackageName();
        String _plus_3 = (_packageName_1 + ".manager.impl.");
        String _name_1 = entity.getName();
        String _plus_4 = (_plus_3 + _name_1);
        String _plus_5 = (_plus_4 + "ManagerImpl");
        imports.add(_plus_5);
        String _packageName_2 = packageinfo.getPackageName();
        String _plus_6 = (_packageName_2 + ".task.AsyncCallback");
        imports.add(_plus_6);
        String _packageName_3 = packageinfo.getPackageName();
        String _plus_7 = (_packageName_3 + ".task.Create");
        String _name_2 = entity.getName();
        String _plus_8 = (_plus_7 + _name_2);
        imports.add(_plus_8);
        EList<EReference> _eReferences = entity.getEReferences();
        for (final EReference entityClassReference : _eReferences) {
          String _packageName_4 = packageinfo.getPackageName();
          String _plus_9 = (_packageName_4 + ".task.Update");
          EClass _eReferenceType = entityClassReference.getEReferenceType();
          String _name_3 = _eReferenceType.getName();
          String _plus_10 = (_plus_9 + _name_3);
          imports.add(_plus_10);
        }
      }
    }
  }
  
  private CharSequence generateClass(final String packageName, final Screen screen, final Set<String> imports, final PackageInfo packageInfo, final String inheritance, final List<String> interfaces, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Triple<String,String,String>> _arrayList = new ArrayList<Triple<String,String,String>>();
      List<Triple<String,String,String>> attributes = _arrayList;
      HashSet<EClass> _hashSet = new HashSet<EClass>();
      Set<EClass> entitiesToGenerateGettersFor = _hashSet;
      boolean writethroughImportsRequired = false;
      BindingRepository _bindingRepository = m.getBindingRepository();
      boolean _notEquals = (!Objects.equal(_bindingRepository, null));
      if (_notEquals) {
        BindingRepository _bindingRepository_1 = m.getBindingRepository();
        Set<EClass> _collectEntitiesForScreenByPrimitiveBindings = this.javaUtilities.collectEntitiesForScreenByPrimitiveBindings(screen, _bindingRepository_1);
        entitiesToGenerateGettersFor = _collectEntitiesForScreenByPrimitiveBindings;
        BindingRepository _bindingRepository_2 = m.getBindingRepository();
        EList<DomainBinding> _bindings = _bindingRepository_2.getBindings();
        for (final DomainBinding binding : _bindings) {
          UpdateStrategy _updateStrategy = binding.getUpdateStrategy();
          boolean _equals = Objects.equal(_updateStrategy, UpdateStrategy.WRITE_THROUGH);
          if (_equals) {
            writethroughImportsRequired = true;
          }
        }
        if (writethroughImportsRequired) {
          BindingRepository _bindingRepository_3 = m.getBindingRepository();
          this.getRequiredWriteAndWriteThroughImports(screen, packageInfo, _bindingRepository_3, imports);
        }
      }
      Triple<String,String,String> _triple = new Triple<String,String,String>("Context", "mContext", null);
      attributes.add(_triple);
      String _name = screen.getName();
      String _firstUpper = StringExtensions.toFirstUpper(_name);
      String _plus = (_firstUpper + "HookActivity.class.getSimpleName()");
      Triple<String,String,String> _triple_1 = new Triple<String,String,String>("String", "TAG", _plus);
      attributes.add(_triple_1);
      StringConcatenation _builder = new StringConcatenation();
      Class<? extends ActivitiesHooksTemplate> _class = this.getClass();
      CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
      _builder.append(_statementGenerated, "");
      _builder.newLineIfNotEmpty();
      CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
      _builder.append(_packageStatement, "");
      _builder.newLineIfNotEmpty();
      CharSequence _importStatements = this.javaUtilities.importStatements(imports);
      _builder.append(_importStatements, "");
      _builder.newLineIfNotEmpty();
      String _name_1 = screen.getName();
      String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
      String _plus_1 = (_firstUpper_1 + "HookActivity");
      CharSequence _classDecl = this.javaUtilities.classDecl(_plus_1, inheritance, interfaces, false);
      _builder.append(_classDecl, "");
      _builder.newLineIfNotEmpty();
      Triple<String,String,String> _get = attributes.get(0);
      CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(_get, false, false);
      _builder.append(_generateAdditionalAttributesWithExpression, "");
      _builder.newLineIfNotEmpty();
      Triple<String,String,String> _get_1 = attributes.get(1);
      CharSequence _generateAdditionalAttributesWithExpression_1 = this.javaUtilities.generateAdditionalAttributesWithExpression(_get_1, false, true);
      _builder.append(_generateAdditionalAttributesWithExpression_1, "");
      _builder.newLineIfNotEmpty();
      {
        List<Datepicker> _directAssociatedDatepicker = this.domainModelUtils.getDirectAssociatedDatepicker(screen);
        int _size = _directAssociatedDatepicker.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          String _name_2 = screen.getName();
          String _plus_2 = (_name_2 + "HookActivity");
          List<Datepicker> _directAssociatedDatepicker_1 = this.domainModelUtils.getDirectAssociatedDatepicker(screen);
          CharSequence _generateDatepickerAdditions = this.datepickerAdditions.generateDatepickerAdditions(_plus_2, _directAssociatedDatepicker_1);
          _builder.append(_generateDatepickerAdditions, "");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        List<DynamicList> _dynamicList = this.domainModelUtils.getDynamicList(screen);
        int _size_1 = _dynamicList.size();
        boolean _greaterThan_1 = (_size_1 > 0);
        if (_greaterThan_1) {
          {
            List<DynamicList> _dynamicList_1 = this.domainModelUtils.getDynamicList(screen);
            for(final DynamicList dynamicList : _dynamicList_1) {
              CharSequence _generateListActivityMethods = this.generateListActivityMethods();
              _builder.append(_generateListActivityMethods, "");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      {
        List<LocationPicker> _locationPicker = this.domainModelUtils.getLocationPicker(screen);
        int _size_2 = _locationPicker.size();
        boolean _greaterThan_2 = (_size_2 > 0);
        if (_greaterThan_2) {
          CharSequence _generateIsRouteDisplayed = this.generateIsRouteDisplayed();
          _builder.append(_generateIsRouteDisplayed, "");
          _builder.newLineIfNotEmpty();
        }
      }
      CharSequence _generateActivityLifeCycleMethods = this.generateActivityLifeCycleMethods(screen, m, writethroughImportsRequired);
      _builder.append(_generateActivityLifeCycleMethods, "");
      _builder.newLineIfNotEmpty();
      List<Button> _buttons = this.domainModelUtils.getButtons(screen);
      ArrayList<Flow> _arrayList_1 = new ArrayList<Flow>();
      CharSequence _generateButtonOnClickMethods = this.generateButtonOnClickMethods(_buttons, _arrayList_1);
      _builder.append(_generateButtonOnClickMethods, "");
      _builder.newLineIfNotEmpty();
      CharSequence _generateOnActivityResultMethod = this.generateOnActivityResultMethod();
      _builder.append(_generateOnActivityResultMethod, "");
      _builder.newLineIfNotEmpty();
      {
        for(final EClass entity : entitiesToGenerateGettersFor) {
          CharSequence _generateGetDomainEntity = this.generateGetDomainEntity(entity, screen);
          _builder.append(_generateGetDomainEntity, "");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        MenuBar _menuBar = screen.getMenuBar();
        boolean _notEquals_1 = (!Objects.equal(_menuBar, null));
        if (_notEquals_1) {
          _builder.append("          ");
          MenuBar _menuBar_1 = screen.getMenuBar();
          CharSequence _generateMenuBarMethods = this.generateMenuBarMethods(_menuBar_1, m, screen);
          _builder.append(_generateMenuBarMethods, "          ");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("}");
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateListActivityMethods() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void onListItemClick(ListView l, View v, int position, long id) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("super.onListItemClick(l, v, position, id);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateActivityLifeCycleMethods(final Screen activity, final MobileApp m, final boolean writethroughImportsRequired) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateOnCreateMethod = this.generateOnCreateMethod(activity, m);
    _builder.append(_generateOnCreateMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateOnStartMethod = this.generateOnStartMethod();
    _builder.append(_generateOnStartMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateOnResume = this.generateOnResume();
    _builder.append(_generateOnResume, "");
    _builder.newLineIfNotEmpty();
    BindingRepository _bindingRepository = m.getBindingRepository();
    CharSequence _generateOnPause = this.generateOnPause(activity, _bindingRepository);
    _builder.append(_generateOnPause, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateOnDestroy = this.generateOnDestroy();
    _builder.append(_generateOnDestroy, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateOnRestart = this.generateOnRestart();
    _builder.append(_generateOnRestart, "");
    _builder.newLineIfNotEmpty();
    {
      BindingRepository _bindingRepository_1 = m.getBindingRepository();
      boolean _notEquals = (!Objects.equal(_bindingRepository_1, null));
      if (_notEquals) {
        BindingRepository _bindingRepository_2 = m.getBindingRepository();
        EList<DomainBinding> _bindings = _bindingRepository_2.getBindings();
        CharSequence _writeDisplayElementValuesToEntities = this.writeDisplayElementValuesToEntities(activity, _bindings);
        _builder.append(_writeDisplayElementValuesToEntities, "");
        _builder.newLineIfNotEmpty();
        {
          if (writethroughImportsRequired) {
            BindingRepository _bindingRepository_3 = m.getBindingRepository();
            CharSequence _writeDisplayElementValuesToDatabase = this.writeDisplayElementValuesToDatabase(activity, _bindingRepository_3);
            _builder.append(_writeDisplayElementValuesToDatabase, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateOnCreateMethod(final Screen screen, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void onCreate(Bundle savedInstantsState){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("super.onCreate(savedInstantsState);");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("mContext = this.getApplicationContext();");
    _builder.newLine();
    {
      EList<DisplayElement> _displayElements = screen.getDisplayElements();
      for(final DisplayElement displayElement : _displayElements) {
        String _name = screen.getName();
        CharSequence _generateDisplayElementInOnCreateMethod = this.activitiesTemplate.generateDisplayElementInOnCreateMethod(_name, displayElement, m);
        _builder.append(_generateDisplayElementInOnCreateMethod, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    List<Datepicker> _directAssociatedDatepicker = this.domainModelUtils.getDirectAssociatedDatepicker(screen);
    CharSequence _generateOnDateSetListener = this.datepickerAdditions.generateOnDateSetListener(_directAssociatedDatepicker);
    _builder.append(_generateOnDateSetListener, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    CharSequence _generateSetIntentExtras = this.generateSetIntentExtras(screen, m);
    _builder.append(_generateSetIntentExtras, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Generates the behavior for all possible extras that can be in the intent when this activity starts.
   */
  private CharSequence generateSetIntentExtras(final Screen screen, final MobileApp m) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Flow> _arrayList = new ArrayList<Flow>();
      List<Flow> flowsToThisActivity = _arrayList;
      HashSet<EClass> _hashSet = new HashSet<EClass>();
      Set<EClass> requiredEntities = this.activitiesTemplate.determindeEntitiesTroughBindigns(screen, m, _hashSet);
      StoryBoard _storyBoard = m.getStoryBoard();
      EList<Flow> _flows = _storyBoard.getFlows();
      final Function1<Flow,Boolean> _function = new Function1<Flow,Boolean>() {
          public Boolean apply(final Flow f) {
            Screen _to = f.getTo();
            boolean _equals = _to.equals(screen);
            return Boolean.valueOf(_equals);
          }
        };
      Iterable<Flow> _filter = IterableExtensions.<Flow>filter(_flows, _function);
      for (final Flow flow : _filter) {
        flowsToThisActivity.add(flow);
      }
      StringConcatenation _builder = new StringConcatenation();
      {
        int _size = flowsToThisActivity.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          _builder.append("Intent intent = this.getIntent();");
          _builder.newLine();
          CharSequence _setAttributesViaIntentExtras = this.setAttributesViaIntentExtras(flowsToThisActivity);
          _builder.append(_setAttributesViaIntentExtras, "");
          _builder.newLineIfNotEmpty();
          {
            for(final EClass contextType : requiredEntities) {
              _builder.append("if(this.");
              String _name = contextType.getName();
              String _firstLower = StringExtensions.toFirstLower(_name);
              _builder.append(_firstLower, "");
              _builder.append(" == null) {");
              _builder.newLineIfNotEmpty();
              _builder.append("    ");
              _builder.append("this.");
              String _name_1 = contextType.getName();
              String _firstLower_1 = StringExtensions.toFirstLower(_name_1);
              _builder.append(_firstLower_1, "    ");
              _builder.append(" = new ");
              String _name_2 = contextType.getName();
              _builder.append(_name_2, "    ");
              _builder.append("();");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
              _builder.newLine();
            }
          }
          _builder.append("            ");
          CharSequence _fillUIElementsViaEntityAttributes = this.fillUIElementsViaEntityAttributes(screen, m);
          _builder.append(_fillUIElementsViaEntityAttributes, "            ");
          _builder.newLineIfNotEmpty();
        }
      }
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence setAttributesViaIntentExtras(final List<Flow> flows) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Flow flow : flows) {
        {
          InputContext _flowContext = flow.getFlowContext();
          boolean _notEquals = (!Objects.equal(_flowContext, null));
          if (_notEquals) {
            {
              InputContext _flowContext_1 = flow.getFlowContext();
              EList<EClass> _contextType = _flowContext_1.getContextType();
              for(final EClass contextType : _contextType) {
                _builder.append("if(intent.hasExtra(\"");
                String _name = contextType.getName();
                _builder.append(_name, "");
                _builder.append("\") && intent.getExtras().get(\"");
                String _name_1 = contextType.getName();
                _builder.append(_name_1, "");
                _builder.append("\") instanceof ");
                String _name_2 = contextType.getName();
                _builder.append(_name_2, "");
                _builder.append("){");
                _builder.newLineIfNotEmpty();
                _builder.append("    ");
                _builder.append("this.");
                String _name_3 = contextType.getName();
                String _firstLower = StringExtensions.toFirstLower(_name_3);
                _builder.append(_firstLower, "    ");
                _builder.append(" = (");
                String _name_4 = contextType.getName();
                _builder.append(_name_4, "    ");
                _builder.append(") intent.getExtras().get(\"");
                String _name_5 = contextType.getName();
                _builder.append(_name_5, "    ");
                _builder.append("\");");
                _builder.newLineIfNotEmpty();
                _builder.append("}");
                _builder.newLine();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  /**
   * Fills the ui elements in the screen that are bound to the attributes of entities with the updatestragety "read"
   */
  private CharSequence fillUIElementsViaEntityAttributes(final Screen screen, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _and = false;
      BindingRepository _bindingRepository = m.getBindingRepository();
      boolean _notEquals = (!Objects.equal(_bindingRepository, null));
      if (!_notEquals) {
        _and = false;
      } else {
        BindingRepository _bindingRepository_1 = m.getBindingRepository();
        EList<DomainBinding> _bindings = _bindingRepository_1.getBindings();
        boolean _notEquals_1 = (!Objects.equal(_bindings, null));
        _and = (_notEquals && _notEquals_1);
      }
      if (_and) {
        {
          BindingRepository _bindingRepository_2 = m.getBindingRepository();
          EList<DomainBinding> _bindings_1 = _bindingRepository_2.getBindings();
          final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
              public Boolean apply(final DomainBinding binding) {
                boolean _and = false;
                if (!(binding instanceof PrimitiveBinding)) {
                  _and = false;
                } else {
                  UpdateStrategy _updateStrategy = binding.getUpdateStrategy();
                  boolean _equals = Objects.equal(_updateStrategy, UpdateStrategy.READ);
                  _and = ((binding instanceof PrimitiveBinding) && _equals);
                }
                return Boolean.valueOf(_and);
              }
            };
          Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings_1, _function);
          final Function1<DomainBinding,PrimitiveBinding> _function_1 = new Function1<DomainBinding,PrimitiveBinding>() {
              public PrimitiveBinding apply(final DomainBinding binding) {
                return ((PrimitiveBinding) binding);
              }
            };
          Iterable<PrimitiveBinding> _map = IterableExtensions.<DomainBinding, PrimitiveBinding>map(_filter, _function_1);
          for(final PrimitiveBinding primitiveBinding : _map) {
            _builder.newLine();
            {
              boolean _and_1 = false;
              if (!(primitiveBinding instanceof StringBinding)) {
                _and_1 = false;
              } else {
                EList<DisplayElement> _displayElements = screen.getDisplayElements();
                TextContaining _uiElement = ((StringBinding) primitiveBinding).getUiElement();
                boolean _contains = _displayElements.contains(((DisplayElement) _uiElement));
                _and_1 = ((primitiveBinding instanceof StringBinding) && _contains);
              }
              if (_and_1) {
                TextContaining _uiElement_1 = ((StringBinding) primitiveBinding).getUiElement();
                String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(((DisplayElement) _uiElement_1));
                _builder.append(_displayElementPrefix, "");
                TextContaining _uiElement_2 = ((StringBinding) primitiveBinding).getUiElement();
                String _name = ((DisplayElement) _uiElement_2).getName();
                _builder.append(_name, "");
                _builder.append(".setText(this.");
                EAttribute _attribute = ((StringBinding) primitiveBinding).getAttribute();
                EClass _eContainingClass = _attribute.getEContainingClass();
                String _name_1 = _eContainingClass.getName();
                String _firstLower = StringExtensions.toFirstLower(_name_1);
                _builder.append(_firstLower, "");
                _builder.append(".get");
                EAttribute _attribute_1 = ((StringBinding) primitiveBinding).getAttribute();
                String _name_2 = _attribute_1.getName();
                String _firstUpper = StringExtensions.toFirstUpper(_name_2);
                _builder.append(_firstUpper, "");
                _builder.append("());");
                _builder.newLineIfNotEmpty();
              } else {
                boolean _and_2 = false;
                if (!(primitiveBinding instanceof BooleanBinding)) {
                  _and_2 = false;
                } else {
                  EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
                  CheckBox _checkBox = ((BooleanBinding) primitiveBinding).getCheckBox();
                  boolean _contains_1 = _displayElements_1.contains(_checkBox);
                  _and_2 = ((primitiveBinding instanceof BooleanBinding) && _contains_1);
                }
                if (_and_2) {
                  CheckBox _checkBox_1 = ((BooleanBinding) primitiveBinding).getCheckBox();
                  String _displayElementPrefix_1 = this.javaUtilities.getDisplayElementPrefix(_checkBox_1);
                  _builder.append(_displayElementPrefix_1, "");
                  CheckBox _checkBox_2 = ((BooleanBinding) primitiveBinding).getCheckBox();
                  String _name_3 = _checkBox_2.getName();
                  _builder.append(_name_3, "");
                  _builder.append(".setChecked(this.");
                  EAttribute _attribute_2 = ((StringBinding) primitiveBinding).getAttribute();
                  EClass _eContainingClass_1 = _attribute_2.getEContainingClass();
                  String _name_4 = _eContainingClass_1.getName();
                  String _firstLower_1 = StringExtensions.toFirstLower(_name_4);
                  _builder.append(_firstLower_1, "");
                  _builder.append(".get");
                  EAttribute _attribute_3 = ((StringBinding) primitiveBinding).getAttribute();
                  String _name_5 = _attribute_3.getName();
                  String _firstUpper_1 = StringExtensions.toFirstUpper(_name_5);
                  _builder.append(_firstUpper_1, "");
                  _builder.append("());");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateOnStartMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void onStart(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("super.onStart();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateOnResume() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void onResume(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("super.onResume();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateOnPause(final Screen screen, final BindingRepository bindingRepository) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void onPause(){");
    _builder.newLine();
    _builder.append("             ");
    _builder.append("super.onPause();");
    _builder.newLine();
    {
      boolean _notEquals = (!Objects.equal(bindingRepository, null));
      if (_notEquals) {
        _builder.append("saveUIDataToEntity();");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Creates a save method that stored all displayElement data in the bound entity attributes.
   */
  private CharSequence writeDisplayElementValuesToEntities(final Screen screen, final List<DomainBinding> bindings) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("* Saves all values of the displayed ui elements that where bound with an UpdateStrategy write/writethrough to the entity");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void saveUIDataToEntity(){");
    _builder.newLine();
    {
      final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
          public Boolean apply(final DomainBinding binding) {
            boolean _and = false;
            if (!(binding instanceof PrimitiveBinding)) {
              _and = false;
            } else {
              boolean _or = false;
              UpdateStrategy _updateStrategy = binding.getUpdateStrategy();
              boolean _equals = Objects.equal(_updateStrategy, UpdateStrategy.WRITE);
              if (_equals) {
                _or = true;
              } else {
                UpdateStrategy _updateStrategy_1 = binding.getUpdateStrategy();
                boolean _equals_1 = Objects.equal(_updateStrategy_1, UpdateStrategy.WRITE_THROUGH);
                _or = (_equals || _equals_1);
              }
              _and = ((binding instanceof PrimitiveBinding) && _or);
            }
            return Boolean.valueOf(_and);
          }
        };
      Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(bindings, _function);
      final Function1<DomainBinding,PrimitiveBinding> _function_1 = new Function1<DomainBinding,PrimitiveBinding>() {
          public PrimitiveBinding apply(final DomainBinding binding) {
            return ((PrimitiveBinding) binding);
          }
        };
      Iterable<PrimitiveBinding> _map = IterableExtensions.<DomainBinding, PrimitiveBinding>map(_filter, _function_1);
      for(final PrimitiveBinding primitiveBinding : _map) {
        {
          boolean _and = false;
          if (!(primitiveBinding instanceof StringBinding)) {
            _and = false;
          } else {
            EList<DisplayElement> _displayElements = screen.getDisplayElements();
            TextContaining _uiElement = ((StringBinding) primitiveBinding).getUiElement();
            boolean _contains = _displayElements.contains(((DisplayElement) _uiElement));
            _and = ((primitiveBinding instanceof StringBinding) && _contains);
          }
          if (_and) {
            StringBinding stringBinding = ((StringBinding) primitiveBinding);
            _builder.newLineIfNotEmpty();
            _builder.append("this.");
            EAttribute _attribute = stringBinding.getAttribute();
            EClass _eContainingClass = _attribute.getEContainingClass();
            String _name = _eContainingClass.getName();
            String _firstLower = StringExtensions.toFirstLower(_name);
            _builder.append(_firstLower, "");
            _builder.append(".set");
            EAttribute _attribute_1 = stringBinding.getAttribute();
            String _name_1 = _attribute_1.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_1);
            _builder.append(_firstUpper, "");
            _builder.append("(");
            TextContaining _uiElement_1 = stringBinding.getUiElement();
            String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(((DisplayElement) _uiElement_1));
            _builder.append(_displayElementPrefix, "");
            TextContaining _uiElement_2 = stringBinding.getUiElement();
            String _name_2 = ((DisplayElement) _uiElement_2).getName();
            _builder.append(_name_2, "");
            _builder.append(".getText()");
            TextContaining _uiElement_3 = stringBinding.getUiElement();
            CharSequence _determineDisplayElement = this.determineDisplayElement(((DisplayElement) _uiElement_3));
            _builder.append(_determineDisplayElement, "");
            _builder.append(");");
            _builder.newLineIfNotEmpty();
          } else {
            boolean _and_1 = false;
            if (!(primitiveBinding instanceof BooleanBinding)) {
              _and_1 = false;
            } else {
              EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
              CheckBox _checkBox = ((BooleanBinding) primitiveBinding).getCheckBox();
              boolean _contains_1 = _displayElements_1.contains(((DisplayElement) _checkBox));
              _and_1 = ((primitiveBinding instanceof BooleanBinding) && _contains_1);
            }
            if (_and_1) {
              BooleanBinding booleanBinding = ((BooleanBinding) primitiveBinding);
              _builder.newLineIfNotEmpty();
              _builder.append("this.");
              EAttribute _attribute_2 = booleanBinding.getAttribute();
              EClass _eContainingClass_1 = _attribute_2.getEContainingClass();
              String _name_3 = _eContainingClass_1.getName();
              String _firstLower_1 = StringExtensions.toFirstLower(_name_3);
              _builder.append(_firstLower_1, "");
              _builder.append(".set");
              EAttribute _attribute_3 = booleanBinding.getAttribute();
              String _name_4 = _attribute_3.getName();
              String _firstUpper_1 = StringExtensions.toFirstUpper(_name_4);
              _builder.append(_firstUpper_1, "");
              _builder.append("(");
              CheckBox _checkBox_1 = booleanBinding.getCheckBox();
              String _displayElementPrefix_1 = this.javaUtilities.getDisplayElementPrefix(((DisplayElement) _checkBox_1));
              _builder.append(_displayElementPrefix_1, "");
              CheckBox _checkBox_2 = booleanBinding.getCheckBox();
              String _name_5 = _checkBox_2.getName();
              _builder.append(_name_5, "");
              _builder.append(".isChecked());");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence writeDisplayElementValuesToDatabase(final Screen screen, final BindingRepository bindingRepository) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("* Stores the values of the entities. The persistence layer(.task) is responsible to create the class and update the related/referenced other classes.");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("public void saveEntityDataToDatabase(){");
    _builder.newLine();
    _builder.append("            ");
    Set<EClass> entitiesInScreen = this.javaUtilities.collectEntitiesForScreenByPrimitiveBindings(screen, bindingRepository);
    _builder.newLineIfNotEmpty();
    {
      for(final EClass entity : entitiesInScreen) {
        {
          EList<DomainBinding> _bindings = bindingRepository.getBindings();
          boolean _checkEntityNeedsDBPersistence = this.checkEntityNeedsDBPersistence(entity, _bindings);
          if (_checkEntityNeedsDBPersistence) {
            _builder.append("Create");
            String _name = entity.getName();
            _builder.append(_name, "");
            _builder.append(" create");
            String _name_1 = entity.getName();
            _builder.append(_name_1, "");
            _builder.append(" = new Create");
            String _name_2 = entity.getName();
            _builder.append(_name_2, "");
            _builder.append("(mContext);");
            _builder.newLineIfNotEmpty();
            _builder.append("    ");
            _builder.append("create");
            String _name_3 = entity.getName();
            _builder.append(_name_3, "    ");
            _builder.append(".create");
            String _name_4 = entity.getName();
            _builder.append(_name_4, "    ");
            _builder.append("Async(");
            String _name_5 = entity.getName();
            String _lowerCase = _name_5.toLowerCase();
            _builder.append(_lowerCase, "    ");
            _builder.append(", new AsyncCallback<Uri>(){");
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("@Override");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("public void onResult(Uri result){");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("}");
            _builder.newLine();
            _builder.append("    ");
            _builder.append("});");
            _builder.newLine();
          }
        }
      }
    }
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private boolean checkEntityNeedsDBPersistence(final EClass entity, final List<DomainBinding> bindings) {
    boolean dbPersistenceRequired = false;
    final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
        public Boolean apply(final DomainBinding binding) {
          boolean _and = false;
          if (!(binding instanceof PrimitiveBinding)) {
            _and = false;
          } else {
            UpdateStrategy _updateStrategy = binding.getUpdateStrategy();
            boolean _equals = Objects.equal(_updateStrategy, UpdateStrategy.WRITE_THROUGH);
            _and = ((binding instanceof PrimitiveBinding) && _equals);
          }
          return Boolean.valueOf(_and);
        }
      };
    Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(bindings, _function);
    final Function1<DomainBinding,PrimitiveBinding> _function_1 = new Function1<DomainBinding,PrimitiveBinding>() {
        public PrimitiveBinding apply(final DomainBinding binding) {
          return ((PrimitiveBinding) binding);
        }
      };
    Iterable<PrimitiveBinding> _map = IterableExtensions.<DomainBinding, PrimitiveBinding>map(_filter, _function_1);
    for (final PrimitiveBinding primitiveBinding : _map) {
      EList<EAttribute> _eAttributes = entity.getEAttributes();
      EAttribute _attribute = ((StringBinding) primitiveBinding).getAttribute();
      boolean _contains = _eAttributes.contains(_attribute);
      if (_contains) {
        dbPersistenceRequired = true;
      }
    }
    return dbPersistenceRequired;
  }
  
  private CharSequence determineDisplayElement(final DisplayElement displayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((displayElement instanceof Input)) {
        _builder.append(".toString()");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  private CharSequence generateOnDestroy() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void onDestroy(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("super.onDestroy();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateOnRestart() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected void onRestart(){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("super.onRestart();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateIsRouteDisplayed() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected boolean isRouteDisplayed() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// TODO Auto-generated method stub");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateGetDomainEntity(final EClass entity, final Screen screen) {
    CharSequence _xblockexpression = null;
    {
      final String classname = entity.getName();
      StringConcatenation _builder = new StringConcatenation();
      {
        List<DynamicList> _dynamicList = this.domainModelUtils.getDynamicList(screen);
        int _size = _dynamicList.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          _builder.append("public ");
          _builder.append(classname, "");
          _builder.append("[] get");
          _builder.append(classname, "");
          _builder.append("s() {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("// TODO Auto-generated method stub");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append(classname, "			");
          _builder.append("[] ");
          String _firstLower = StringExtensions.toFirstLower(classname);
          _builder.append(_firstLower, "			");
          _builder.append("Array = new ");
          _builder.append(classname, "			");
          _builder.append("[10];");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("for(int i=0; i < 10; i++){");
          _builder.newLine();
          _builder.append("\t\t\t \t\t");
          String _firstLower_1 = StringExtensions.toFirstLower(classname);
          _builder.append(_firstLower_1, "			 		");
          _builder.append("Array[i] = new ");
          _builder.append(classname, "			 		");
          _builder.append("();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("return ");
          String _firstLower_2 = StringExtensions.toFirstLower(classname);
          _builder.append(_firstLower_2, "			");
          _builder.append("Array;");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
        } else {
          _builder.append("public ");
          _builder.append(classname, "");
          _builder.append(" get");
          _builder.append(classname, "");
          _builder.append("() {");
          _builder.newLineIfNotEmpty();
          _builder.append("            ");
          _builder.append("// TODO Auto-generated method stub");
          _builder.newLine();
          _builder.append("            ");
          _builder.append("return ");
          String _firstLower_3 = StringExtensions.toFirstLower(classname);
          _builder.append(_firstLower_3, "            ");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
          _builder.append("        ");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  private CharSequence generateButtonOnClickMethods(final List<Button> buttons, final List<Flow> flows) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Button button : buttons) {
        {
          for(final Flow flow : flows) {
            {
              EList<Event> _events = flow.getEvents();
              for(final Event event : _events) {
                {
                  EList<ButtonClickEvent> _buttonClickEvents = button.getButtonClickEvents();
                  int _size = _buttonClickEvents.size();
                  boolean _greaterThan = (_size > 0);
                  if (_greaterThan) {
                    {
                      EList<ButtonClickEvent> _buttonClickEvents_1 = button.getButtonClickEvents();
                      boolean _contains = _buttonClickEvents_1.contains(event);
                      if (_contains) {
                        _builder.append("@Override");
                        _builder.newLine();
                        _builder.append("public void ");
                        String _name = button.getName();
                        String _firstUpper = StringExtensions.toFirstUpper(_name);
                        String _plus = (_firstUpper + "To");
                        Screen _to = flow.getTo();
                        String _name_1 = _to.getName();
                        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
                        String _plus_1 = (_plus + _firstUpper_1);
                        _builder.append(_plus_1, "");
                        _builder.append("(View view){");
                        _builder.newLineIfNotEmpty();
                        _builder.append("super.");
                        String _name_2 = button.getName();
                        String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
                        String _plus_2 = (_firstUpper_2 + "To");
                        Screen _to_1 = flow.getTo();
                        String _name_3 = _to_1.getName();
                        String _firstUpper_3 = StringExtensions.toFirstUpper(_name_3);
                        String _plus_3 = (_plus_2 + _firstUpper_3);
                        _builder.append(_plus_3, "");
                        _builder.append("(view);");
                        _builder.newLineIfNotEmpty();
                        _builder.append("}");
                        _builder.newLine();
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  private boolean uiElementIsInScreen(final DisplayElement uiElement, final Screen screen) {
    boolean elementIsInScreen = false;
    List<DisplayElement> _allDisplayElements = this.domainModelUtils.getAllDisplayElements(screen);
    for (final DisplayElement displayElement : _allDisplayElements) {
      boolean _equals = displayElement.equals(uiElement);
      if (_equals) {
        elementIsInScreen = true;
      }
    }
    boolean _and = false;
    boolean _equals_1 = (elementIsInScreen == false);
    if (!_equals_1) {
      _and = false;
    } else {
      MenuBar _menuBar = screen.getMenuBar();
      boolean _notEquals = (!Objects.equal(_menuBar, null));
      _and = (_equals_1 && _notEquals);
    }
    if (_and) {
      MenuBar _menuBar_1 = screen.getMenuBar();
      EList<DisplayElement> _menuBarElements = _menuBar_1.getMenuBarElements();
      for (final DisplayElement displayElement_1 : _menuBarElements) {
        boolean _equals_2 = displayElement_1.equals(uiElement);
        if (_equals_2) {
          elementIsInScreen = true;
        }
      }
    }
    return elementIsInScreen;
  }
  
  private CharSequence generateMenuBarMethods(final MenuBar menuBar, final MobileApp m, final Screen screen) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateOnCreateOptionsMenuMethod = this.generateOnCreateOptionsMenuMethod(menuBar, m, screen);
    _builder.append(_generateOnCreateOptionsMenuMethod, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateMenuOnOptionsItemSelectedMethod = this.generateMenuOnOptionsItemSelectedMethod(menuBar);
    _builder.append(_generateMenuOnOptionsItemSelectedMethod, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateMenuOnOptionsItemSelectedMethod(final MenuBar menuBar) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public boolean onOptionsItemSelected(MenuItem item) {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("switch (item.getItemId()) {");
    _builder.newLine();
    {
      EList<DisplayElement> _menuBarElements = menuBar.getMenuBarElements();
      for(final DisplayElement displayElementInMenu : _menuBarElements) {
        {
          boolean _checkIfDisplayElementHasAction = this.javaUtilities.checkIfDisplayElementHasAction(displayElementInMenu);
          if (_checkIfDisplayElementHasAction) {
            _builder.append("    ");
            _builder.append("case R.id.menu_item_");
            Screen _screen = menuBar.getScreen();
            String _name = _screen.getName();
            _builder.append(_name, "    ");
            _builder.append("_");
            String _name_1 = displayElementInMenu.getName();
            String _lowerCase = _name_1.toLowerCase();
            _builder.append(_lowerCase, "    ");
            _builder.append(": ");
            String _displayElementType = this.javaUtilities.getDisplayElementType(displayElementInMenu);
            String _firstLower = StringExtensions.toFirstLower(_displayElementType);
            _builder.append(_firstLower, "    ");
            String _name_2 = displayElementInMenu.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name_2);
            _builder.append(_firstUpper, "    ");
            _builder.append(".performClick(); break;");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateOnCreateOptionsMenuMethod(final MenuBar menuBar, final MobileApp m, final Screen screen) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public boolean onCreateOptionsMenu(Menu menu) {");
    _builder.newLine();
    {
      List<LocationPicker> _locationPicker = this.domainModelUtils.getLocationPicker(screen);
      int _size = _locationPicker.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _builder.append("  ");
        _builder.append("MenuInflater inflater  = getMenuInflater();");
        _builder.newLine();
      } else {
        _builder.append("  ");
        _builder.append("MenuInflater inflater = getSupportMenuInflater();");
        _builder.newLine();
      }
    }
    _builder.append("  ");
    _builder.append("inflater.inflate(R.menu.");
    Screen _screen = menuBar.getScreen();
    String _name = _screen.getName();
    String _lowerCase = _name.toLowerCase();
    _builder.append(_lowerCase, "  ");
    _builder.append("_menu, menu);");
    _builder.newLineIfNotEmpty();
    {
      EList<DisplayElement> _menuBarElements = menuBar.getMenuBarElements();
      for(final DisplayElement displayElement : _menuBarElements) {
        _builder.append("  ");
        EObject _eContainer = menuBar.eContainer();
        CharSequence _generateDisplayElementInOnCreateOptionsMenuMethod = this.generateDisplayElementInOnCreateOptionsMenuMethod(((Screen) _eContainer), displayElement, m);
        _builder.append(_generateDisplayElementInOnCreateOptionsMenuMethod, "  ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("  ");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateDisplayElementInOnCreateOptionsMenuMethod(final Screen screen, final DisplayElement displayElement, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    final String type = this.javaUtilities.getDisplayElementType(displayElement);
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    String _firstLower = StringExtensions.toFirstLower(type);
    _builder.append(_firstLower, " ");
    String _name = displayElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, " ");
    _builder.append(" = (");
    _builder.append(type, " ");
    _builder.append(")menu.findItem(R.id.menu_item_");
    EObject _eContainer = displayElement.eContainer();
    EObject _eContainer_1 = _eContainer.eContainer();
    String _name_1 = ((Screen) _eContainer_1).getName();
    _builder.append(_name_1, " ");
    _builder.append("_");
    String _name_2 = displayElement.getName();
    String _lowerCase = _name_2.toLowerCase();
    _builder.append(_lowerCase, " ");
    _builder.append(").getActionView();");
    _builder.newLineIfNotEmpty();
    CharSequence _generateAddOnClickListener = this.activitiesTemplate.generateAddOnClickListener(type, displayElement);
    _builder.append(_generateAddOnClickListener, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private List<EAttribute> getBoundAttributesForUpdateStrategyWriteThrough(final Screen screen, final BindingRepository bindingRepository) {
    ArrayList<EAttribute> _arrayList = new ArrayList<EAttribute>();
    List<EAttribute> writeThroughAttributes = _arrayList;
    EList<DomainBinding> _bindings = bindingRepository.getBindings();
    final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
        public Boolean apply(final DomainBinding binding) {
          boolean _and = false;
          UpdateStrategy _updateStrategy = binding.getUpdateStrategy();
          boolean _equals = Objects.equal(_updateStrategy, UpdateStrategy.WRITE_THROUGH);
          if (!_equals) {
            _and = false;
          } else {
            _and = (_equals && (binding instanceof PrimitiveBinding));
          }
          return Boolean.valueOf(_and);
        }
      };
    Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings, _function);
    final Function1<DomainBinding,PrimitiveBinding> _function_1 = new Function1<DomainBinding,PrimitiveBinding>() {
        public PrimitiveBinding apply(final DomainBinding binding) {
          return ((PrimitiveBinding) binding);
        }
      };
    Iterable<PrimitiveBinding> _map = IterableExtensions.<DomainBinding, PrimitiveBinding>map(_filter, _function_1);
    for (final PrimitiveBinding binding : _map) {
    }
    return writeThroughAttributes;
  }
  
  public CharSequence generateOnActivityResultMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void onActivityResult(int requestCode, int resultCode, Intent intent) {");
    _builder.newLine();
    _builder.append("\t    \t");
    _builder.append("// TODO implement manually");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
