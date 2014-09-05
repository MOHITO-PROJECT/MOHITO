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
import de.modagile.generator.android.templates.activity.DatepickerAdditionsTemplate;
import de.modagile.generator.android.templates.adapter.BindingUtils;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.domain.BooleanBinding;
import de.modagile.metamodel.app.domain.ComplexBinding;
import de.modagile.metamodel.app.domain.DomainBinding;
import de.modagile.metamodel.app.domain.ListBinding;
import de.modagile.metamodel.app.domain.PrimitiveBinding;
import de.modagile.metamodel.app.domain.SelectionBinding;
import de.modagile.metamodel.app.domain.StringBinding;
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CheckBox;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.Flow;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.Input;
import de.modagile.metamodel.app.ui.InputContext;
import de.modagile.metamodel.app.ui.Label;
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
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Contains activity specific generator methods
 */
@SuppressWarnings("all")
public class ActivitiesTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  @Inject
  @Extension
  private DatepickerAdditionsTemplate datepickerAdditions;
  
  @Inject
  @Extension
  private DomainModelUtils domainModelUtils;
  
  @Inject
  @Extension
  private BindingUtils bindingUtils;
  
  public void generateActivities(final IFileSystemAccess fsa, final Screen activity, final MobileApp m, final PackageInfo packageInfo) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "activity/");
    String _name = activity.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus_1 = (_plus + _firstUpper);
    String _plus_2 = (_plus_1 + "Activity.java");
    String _packageName = packageInfo.getPackageName();
    String _plus_3 = (_packageName + ".activity");
    EList<DisplayElement> _displayElements = activity.getDisplayElements();
    CharSequence _generateActivityCode = this.generateActivityCode(activity, _plus_3, _displayElements, m, packageInfo);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_GEN, _generateActivityCode);
  }
  
  private CharSequence generateActivityCode(final Screen screen, final String packageName, final List<DisplayElement> displayElements, final MobileApp mobileApp, final PackageInfo packageInfo) {
    CharSequence _xblockexpression = null;
    {
      HashSet<String> _hashSet = new HashSet<String>();
      Set<String> imports = _hashSet;
      this.getAllImports(screen, packageInfo, mobileApp, imports);
      String inheritance = "SherlockActivity";
      List<DynamicList> _dynamicList = this.domainModelUtils.getDynamicList(screen);
      int _size = _dynamicList.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        inheritance = "SherlockListActivity";
      } else {
        List<LocationPicker> _locationPicker = this.domainModelUtils.getLocationPicker(screen);
        int _size_1 = _locationPicker.size();
        boolean _greaterThan_1 = (_size_1 > 0);
        if (_greaterThan_1) {
          inheritance = "MapActivity";
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateClass = this.generateClass(packageName, screen, imports, inheritance, mobileApp);
      _builder.append(_generateClass, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public Boolean getAllImports(final Screen screen, final PackageInfo packageInfo, final MobileApp m, final Set<String> imports) {
    Boolean _xblockexpression = null;
    {
      this.domainModelUtils.getButtons(screen);
      this.getGeneralImports(screen, packageInfo, m, imports);
      this.getButtonImportsIfNeeded(screen, imports, m, packageInfo);
      this.getCheckBoxImportsIfNeeded(screen, imports, m, packageInfo);
      this.getImageButtonImportsIfNeeded(screen, imports);
      List<Label> _labels = this.domainModelUtils.getLabels(screen);
      this.getLabelImportsIfNeeded(_labels, imports);
      List<Datepicker> _datepicker = this.domainModelUtils.getDatepicker(screen);
      this.getDatepickerImportsIfNeeded(_datepicker, imports);
      EList<DisplayElement> _displayElements = screen.getDisplayElements();
      List<Label> _labels_1 = this.domainModelUtils.getLabels(screen);
      this.getLabelAndInputImportsIfNeeded(_displayElements, _labels_1, imports);
      this.getMenuBarImportsIfNeeded(screen, imports);
      List<DynamicList> _dynamicList = this.domainModelUtils.getDynamicList(screen);
      this.getDynamicListImportsIfNeeded(_dynamicList, imports, m, packageInfo);
      BindingRepository _bindingRepository = m.getBindingRepository();
      boolean _notEquals = (!Objects.equal(_bindingRepository, null));
      if (_notEquals) {
        BindingRepository _bindingRepository_1 = m.getBindingRepository();
        EList<DomainBinding> _bindings = _bindingRepository_1.getBindings();
        this.getDomainImports(_bindings, imports, packageInfo);
      }
      List<LocationPicker> _locationPicker = this.domainModelUtils.getLocationPicker(screen);
      Boolean _locationPickerImportsIfNeeeded = this.getLocationPickerImportsIfNeeeded(_locationPicker, screen, imports);
      _xblockexpression = (_locationPickerImportsIfNeeeded);
    }
    return _xblockexpression;
  }
  
  private boolean getGeneralImports(final Screen activity, final PackageInfo packageInfo, final MobileApp mobileApp, final Set<String> imports) {
    boolean _xblockexpression = false;
    {
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".R");
      imports.add(_plus);
      String _packageName_1 = packageInfo.getPackageName();
      String _plus_1 = (_packageName_1 + ".constants.DBConstants");
      imports.add(_plus_1);
      EPackage _domainPackage = mobileApp.getDomainPackage();
      EList<EClassifier> _eClassifiers = _domainPackage.getEClassifiers();
      final Function1<EClassifier,Boolean> _function = new Function1<EClassifier,Boolean>() {
          public Boolean apply(final EClassifier c) {
            return Boolean.valueOf((c instanceof EClass));
          }
        };
      Iterable<EClassifier> _filter = IterableExtensions.<EClassifier>filter(_eClassifiers, _function);
      for (final EClassifier clazz : _filter) {
        String _packageName_2 = packageInfo.getPackageName();
        String _plus_2 = (_packageName_2 + ".constants.");
        String _name = clazz.getName();
        String _plus_3 = (_plus_2 + _name);
        String _plus_4 = (_plus_3 + "DBConstants");
        imports.add(_plus_4);
      }
      imports.add("android.app.Activity");
      imports.add("android.widget.TextView");
      imports.add("android.view.View");
      imports.add("android.content.Context");
      imports.add("android.content.Intent");
      imports.add("android.net.Uri");
      imports.add("android.os.Bundle");
      imports.add("android.util.Log");
      imports.add("java.io.IOException");
      imports.add("android.view.View");
      imports.add("android.widget.Toast");
      imports.add("android.content.IntentFilter");
      imports.add("java.util.ArrayList");
      imports.add("java.util.Collection");
      imports.add("java.util.List");
      imports.add("com.actionbarsherlock.app.SherlockListActivity");
      imports.add("com.actionbarsherlock.app.SherlockActivity");
      boolean _add = imports.add("com.actionbarsherlock.app.SherlockFragmentActivity");
      _xblockexpression = (_add);
    }
    return _xblockexpression;
  }
  
  private Boolean getLocationPickerImportsIfNeeeded(final List<LocationPicker> usedLocationpickerInScreen, final Screen activity, final Set<String> imports) {
    Boolean _xifexpression = null;
    int _size = usedLocationpickerInScreen.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      boolean _xblockexpression = false;
      {
        imports.add("java.util.Locale");
        imports.add("android.graphics.Bitmap");
        imports.add("android.graphics.BitmapFactory");
        imports.add("android.graphics.Canvas");
        imports.add("android.graphics.Point");
        boolean isConflicting = false;
        for (final String testDuplicate : imports) {
          boolean _endsWith = testDuplicate.endsWith(".Address");
          if (_endsWith) {
            isConflicting = true;
          }
        }
        boolean _not = (!isConflicting);
        if (_not) {
          imports.add("android.location.Address");
        }
        imports.add("android.location.Geocoder");
        imports.add("android.location.Location");
        imports.add("android.location.LocationListener");
        imports.add("android.location.LocationManager");
        imports.add("com.google.android.maps.GeoPoint");
        imports.add("com.google.android.maps.MapActivity");
        imports.add("com.google.android.maps.MapController");
        imports.add("com.google.android.maps.MapView");
        boolean _add = imports.add("com.google.android.maps.Overlay");
        _xblockexpression = (_add);
      }
      _xifexpression = Boolean.valueOf(_xblockexpression);
    }
    return _xifexpression;
  }
  
  private Boolean getButtonImportsIfNeeded(final Screen screen, final Set<String> imports, final MobileApp m, final PackageInfo packageInfo) {
    Boolean _xblockexpression = null;
    {
      boolean menuContainsButtons = false;
      MenuBar _menuBar = screen.getMenuBar();
      boolean _notEquals = (!Objects.equal(_menuBar, null));
      if (_notEquals) {
        MenuBar _menuBar_1 = screen.getMenuBar();
        EList<DisplayElement> _menuBarElements = _menuBar_1.getMenuBarElements();
        for (final DisplayElement displayElement : _menuBarElements) {
          if ((displayElement instanceof Button)) {
            String _packageName = packageInfo.getPackageName();
            String _plus = (_packageName + ".activity.listener.");
            String _name = displayElement.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name);
            String _plus_1 = (_plus + _firstUpper);
            String _plus_2 = (_plus_1 + "OnClickListener");
            imports.add(_plus_2);
            menuContainsButtons = true;
          }
        }
      }
      List<Button> _buttons = this.domainModelUtils.getButtons(screen);
      for (final Button button : _buttons) {
        EObject _eContainer = button.eContainer();
        boolean _not = (!(_eContainer instanceof CompositeDisplayElementType));
        if (_not) {
          String _packageName_1 = packageInfo.getPackageName();
          String _plus_3 = (_packageName_1 + ".activity.listener.");
          String _name_1 = button.getName();
          String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
          String _plus_4 = (_plus_3 + _firstUpper_1);
          String _plus_5 = (_plus_4 + "OnClickListener");
          imports.add(_plus_5);
        }
      }
      Boolean _xifexpression = null;
      boolean _or = false;
      List<Button> _buttons_1 = this.domainModelUtils.getButtons(screen);
      int _size = _buttons_1.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _or = true;
      } else {
        _or = (_greaterThan || menuContainsButtons);
      }
      if (_or) {
        boolean _add = imports.add("android.widget.Button");
        _xifexpression = Boolean.valueOf(_add);
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  private Boolean getCheckBoxImportsIfNeeded(final Screen screen, final Set<String> imports, final MobileApp m, final PackageInfo packageInfo) {
    Boolean _xblockexpression = null;
    {
      boolean menuContainsCheckBoxes = false;
      MenuBar _menuBar = screen.getMenuBar();
      boolean _notEquals = (!Objects.equal(_menuBar, null));
      if (_notEquals) {
        MenuBar _menuBar_1 = screen.getMenuBar();
        EList<DisplayElement> _menuBarElements = _menuBar_1.getMenuBarElements();
        for (final DisplayElement displayElement : _menuBarElements) {
          if ((displayElement instanceof CheckBox)) {
            menuContainsCheckBoxes = true;
          }
        }
      }
      Boolean _xifexpression = null;
      boolean _or = false;
      List<CheckBox> _directAssociatedCheckBoxes = this.domainModelUtils.getDirectAssociatedCheckBoxes(screen);
      int _size = _directAssociatedCheckBoxes.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _or = true;
      } else {
        _or = (_greaterThan || menuContainsCheckBoxes);
      }
      if (_or) {
        boolean _add = imports.add("android.widget.CheckBox");
        _xifexpression = Boolean.valueOf(_add);
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  private Boolean getImageButtonImportsIfNeeded(final Screen screen, final Set<String> imports) {
    Boolean _xblockexpression = null;
    {
      boolean menuContainsImageButtons = false;
      MenuBar _menuBar = screen.getMenuBar();
      boolean _notEquals = (!Objects.equal(_menuBar, null));
      if (_notEquals) {
        MenuBar _menuBar_1 = screen.getMenuBar();
        EList<DisplayElement> _menuBarElements = _menuBar_1.getMenuBarElements();
        for (final DisplayElement displayElement : _menuBarElements) {
          if ((displayElement instanceof ImageButton)) {
            menuContainsImageButtons = true;
          }
        }
      }
      Boolean _xifexpression = null;
      boolean _or = false;
      List<ImageButton> _imageButton = this.domainModelUtils.getImageButton(screen);
      int _size = _imageButton.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _or = true;
      } else {
        _or = (_greaterThan || menuContainsImageButtons);
      }
      if (_or) {
        boolean _add = imports.add("android.widget.ImageButton");
        _xifexpression = Boolean.valueOf(_add);
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  private Boolean getLabelImportsIfNeeded(final List<Label> usedLabelsInScreen, final Set<String> imports) {
    Boolean _xifexpression = null;
    int _size = usedLabelsInScreen.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      boolean _xblockexpression = false;
      {
        imports.add("android.widget.EditText");
        imports.add("android.widget.ImageView");
        boolean _add = imports.add("android.widget.TextView");
        _xblockexpression = (_add);
      }
      _xifexpression = Boolean.valueOf(_xblockexpression);
    }
    return _xifexpression;
  }
  
  private Boolean getMenuBarImportsIfNeeded(final Screen screen, final Set<String> imports) {
    Boolean _xifexpression = null;
    MenuBar _menuBar = screen.getMenuBar();
    boolean _notEquals = (!Objects.equal(_menuBar, null));
    if (_notEquals) {
      boolean _xifexpression_1 = false;
      List<LocationPicker> _locationPicker = this.domainModelUtils.getLocationPicker(screen);
      int _size = _locationPicker.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        boolean _xblockexpression = false;
        {
          imports.add("android.view.Menu");
          imports.add("android.view.MenuInflater");
          boolean _add = imports.add("android.view.MenuItem");
          _xblockexpression = (_add);
        }
        _xifexpression_1 = _xblockexpression;
      } else {
        boolean _xblockexpression_1 = false;
        {
          imports.add("com.actionbarsherlock.view.Menu");
          imports.add("com.actionbarsherlock.view.MenuInflater");
          boolean _add = imports.add("com.actionbarsherlock.view.MenuItem");
          _xblockexpression_1 = (_add);
        }
        _xifexpression_1 = _xblockexpression_1;
      }
      _xifexpression = Boolean.valueOf(_xifexpression_1);
    }
    return _xifexpression;
  }
  
  private Boolean getLabelAndInputImportsIfNeeded(final List<DisplayElement> displayElements, final List<Label> usedLabelsInScreen, final Set<String> imports) {
    Boolean _xblockexpression = null;
    {
      boolean usedInputs = false;
      for (final DisplayElement displayElement : displayElements) {
        if ((displayElement instanceof Input)) {
          usedInputs = true;
        }
      }
      Boolean _xifexpression = null;
      boolean _or = false;
      int _size = usedLabelsInScreen.size();
      boolean _greaterThan = (_size > 0);
      boolean _not = (!_greaterThan);
      if (_not) {
        _or = true;
      } else {
        _or = (_not || usedInputs);
      }
      if (_or) {
        boolean _add = imports.add("android.widget.EditText");
        _xifexpression = Boolean.valueOf(_add);
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  private Boolean getDatepickerImportsIfNeeded(final List<Datepicker> usedDatepickersInScreen, final Set<String> imports) {
    Boolean _xifexpression = null;
    int _size = usedDatepickersInScreen.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      boolean _xblockexpression = false;
      {
        imports.add("java.util.Calendar");
        imports.add("android.app.DatePickerDialog");
        imports.add("android.widget.DatePicker");
        boolean _add = imports.add("java.text.DateFormat");
        _xblockexpression = (_add);
      }
      _xifexpression = Boolean.valueOf(_xblockexpression);
    }
    return _xifexpression;
  }
  
  private Boolean getDynamicListImportsIfNeeded(final List<DynamicList> usedDynamicListsInScreen, final Set<String> imports, final MobileApp m, final PackageInfo packageInfo) {
    Boolean _xifexpression = null;
    int _size = usedDynamicListsInScreen.size();
    boolean _greaterThan = (_size > 0);
    if (_greaterThan) {
      boolean _xblockexpression = false;
      {
        imports.add("android.app.ListActivity");
        imports.add("android.widget.ListView");
        imports.add("android.database.CursorWrapper");
        boolean _add = imports.add("android.widget.CursorAdapter");
        _xblockexpression = (_add);
      }
      _xifexpression = Boolean.valueOf(_xblockexpression);
    }
    return _xifexpression;
  }
  
  private void getDomainImports(final List<DomainBinding> bindings, final Set<String> imports, final PackageInfo packageInfo) {
    final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
        public Boolean apply(final DomainBinding binding) {
          return Boolean.valueOf((binding instanceof PrimitiveBinding));
        }
      };
    Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(bindings, _function);
    final Function1<DomainBinding,PrimitiveBinding> _function_1 = new Function1<DomainBinding,PrimitiveBinding>() {
        public PrimitiveBinding apply(final DomainBinding binding) {
          return ((PrimitiveBinding) binding);
        }
      };
    Iterable<PrimitiveBinding> _map = IterableExtensions.<DomainBinding, PrimitiveBinding>map(_filter, _function_1);
    for (final PrimitiveBinding binding : _map) {
      String _packageName = packageInfo.getPackageName();
      String _plus = (_packageName + ".model.");
      EAttribute _attribute = binding.getAttribute();
      EClass _eContainingClass = _attribute.getEContainingClass();
      String _name = _eContainingClass.getName();
      String _plus_1 = (_plus + _name);
      imports.add(_plus_1);
    }
    final Function1<DomainBinding,Boolean> _function_2 = new Function1<DomainBinding,Boolean>() {
        public Boolean apply(final DomainBinding b) {
          return Boolean.valueOf((b instanceof ListBinding));
        }
      };
    Iterable<DomainBinding> _filter_1 = IterableExtensions.<DomainBinding>filter(bindings, _function_2);
    final Function1<DomainBinding,ListBinding> _function_3 = new Function1<DomainBinding,ListBinding>() {
        public ListBinding apply(final DomainBinding b) {
          return ((ListBinding) b);
        }
      };
    Iterable<ListBinding> _map_1 = IterableExtensions.<DomainBinding, ListBinding>map(_filter_1, _function_3);
    for (final ListBinding listBinding : _map_1) {
      String _packageName_1 = packageInfo.getPackageName();
      String _plus_2 = (_packageName_1 + ".model.");
      EReference _domainReference = listBinding.getDomainReference();
      EClassifier _eType = _domainReference.getEType();
      EClass _eClass = _eType.eClass();
      String _name_1 = _eClass.getName();
      String _plus_3 = (_plus_2 + _name_1);
      boolean _contains = imports.contains(_plus_3);
      boolean _not = (!_contains);
      if (_not) {
        String _packageName_2 = packageInfo.getPackageName();
        String _plus_4 = (_packageName_2 + ".adapter.");
        DynamicList _listElement = listBinding.getListElement();
        String _name_2 = _listElement.getName();
        String _plus_5 = (_plus_4 + _name_2);
        String _plus_6 = (_plus_5 + "ListAdapter");
        imports.add(_plus_6);
      }
    }
  }
  
  private CharSequence generateClass(final String packageName, final Screen screen, final Set<String> imports, final String inheritance, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    Class<? extends ActivitiesTemplate> _class = this.getClass();
    CharSequence _statementGenerated = this.javaUtilities.statementGenerated(_class);
    _builder.append(_statementGenerated, "");
    _builder.newLineIfNotEmpty();
    CharSequence _packageStatement = this.javaUtilities.packageStatement(packageName);
    _builder.append(_packageStatement, "");
    _builder.newLineIfNotEmpty();
    CharSequence _importStatements = this.javaUtilities.importStatements(imports);
    _builder.append(_importStatements, "");
    _builder.newLineIfNotEmpty();
    String _name = screen.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = (_firstUpper + "Activity");
    CharSequence _classDecl = this.javaUtilities.classDecl(_plus, inheritance, null, false);
    _builder.append(_classDecl, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateRequiredAttributes = this.generateRequiredAttributes(screen);
    _builder.append(_generateRequiredAttributes, "");
    _builder.newLineIfNotEmpty();
    Set<EClass> requiredEntities = this.getRequiredEntities(screen, m);
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(requiredEntities, null));
      if (_notEquals) {
        {
          for(final EClass entity : requiredEntities) {
            _builder.append("protected ");
            String _name_1 = entity.getName();
            _builder.append(_name_1, "");
            _builder.append(" ");
            String _name_2 = entity.getName();
            String _firstLower = StringExtensions.toFirstLower(_name_2);
            _builder.append(_firstLower, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("        ");
    _builder.newLine();
    {
      List<Datepicker> _directAssociatedDatepicker = this.domainModelUtils.getDirectAssociatedDatepicker(screen);
      int _size = _directAssociatedDatepicker.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        {
          List<Datepicker> _directAssociatedDatepicker_1 = this.domainModelUtils.getDirectAssociatedDatepicker(screen);
          for(final Datepicker datePicker : _directAssociatedDatepicker_1) {
            _builder.append("        ");
            _builder.append("protected ImageButton ");
            String _name_3 = datePicker.getName();
            String _firstLower_1 = StringExtensions.toFirstLower(_name_3);
            _builder.append(_firstLower_1, "        ");
            _builder.append("ImageButton;");
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.append("protected DatePickerDialog.OnDateSetListener onDateSetListener");
            String _name_4 = datePicker.getName();
            _builder.append(_name_4, "        ");
            _builder.append(" = null;");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("        ");
    _builder.newLine();
    CharSequence _generateActivityLifeCycleMethods = this.generateActivityLifeCycleMethods(screen, m);
    _builder.append(_generateActivityLifeCycleMethods, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDisplayElementGetterMethods = this.generateDisplayElementGetterMethods(screen);
    _builder.append(_generateDisplayElementGetterMethods, "");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    CharSequence _generateRequiredEntityGetterMethods = this.generateRequiredEntityGetterMethods(requiredEntities);
    _builder.append(_generateRequiredEntityGetterMethods, "        ");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    {
      List<LocationPicker> _locationPicker = this.domainModelUtils.getLocationPicker(screen);
      int _size_1 = _locationPicker.size();
      boolean _greaterThan_1 = (_size_1 > 0);
      if (_greaterThan_1) {
        _builder.append("        ");
        CharSequence _generateLocationPickerMethodsAndClasses = this.generateLocationPickerMethodsAndClasses();
        _builder.append(_generateLocationPickerMethodsAndClasses, "        ");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      List<Datepicker> _directAssociatedDatepicker_2 = this.domainModelUtils.getDirectAssociatedDatepicker(screen);
      int _size_2 = _directAssociatedDatepicker_2.size();
      boolean _greaterThan_2 = (_size_2 > 0);
      if (_greaterThan_2) {
        {
          List<Datepicker> _directAssociatedDatepicker_3 = this.domainModelUtils.getDirectAssociatedDatepicker(screen);
          for(final Datepicker datePicker_1 : _directAssociatedDatepicker_3) {
            _builder.append("          ");
            CharSequence _generateShowDatePickerDialog = this.datepickerAdditions.generateShowDatePickerDialog(datePicker_1);
            _builder.append(_generateShowDatePickerDialog, "          ");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("}");
    return _builder;
  }
  
  public CharSequence generateRequiredAttributes(final Screen screen) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Triple<String,String,String>> _arrayList = new ArrayList<Triple<String,String,String>>();
      List<Triple<String,String,String>> attributes = _arrayList;
      Triple<String,String,String> _triple = new Triple<String,String,String>("Context", "mContext", null);
      attributes.add(_triple);
      String _name = screen.getName();
      String _firstUpper = StringExtensions.toFirstUpper(_name);
      String _plus = (_firstUpper + "Activity.class.getSimpleName()");
      Triple<String,String,String> _triple_1 = new Triple<String,String,String>("String", "TAG", _plus);
      attributes.add(_triple_1);
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _generateDisplayElementAttributes = this.generateDisplayElementAttributes(screen);
      _builder.append(_generateDisplayElementAttributes, "");
      _builder.newLineIfNotEmpty();
      _builder.append("        ");
      Triple<String,String,String> _get = attributes.get(0);
      CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(_get, false, false);
      _builder.append(_generateAdditionalAttributesWithExpression, "        ");
      _builder.newLineIfNotEmpty();
      _builder.append("        ");
      Triple<String,String,String> _get_1 = attributes.get(1);
      CharSequence _generateAdditionalAttributesWithExpression_1 = this.javaUtilities.generateAdditionalAttributesWithExpression(_get_1, false, true);
      _builder.append(_generateAdditionalAttributesWithExpression_1, "        ");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  /**
   * Generates the entity attributes that are required for the bindings or
   */
  private Set<EClass> getRequiredEntities(final Screen screen, final MobileApp m) {
    HashSet<EClass> _hashSet = new HashSet<EClass>();
    Set<EClass> requiredEntities = _hashSet;
    StoryBoard _storyBoard = m.getStoryBoard();
    boolean _notEquals = (!Objects.equal(_storyBoard, null));
    if (_notEquals) {
      StoryBoard _storyBoard_1 = m.getStoryBoard();
      EList<Flow> _flows = _storyBoard_1.getFlows();
      for (final Flow flow : _flows) {
        boolean _and = false;
        boolean _and_1 = false;
        InputContext _flowContext = flow.getFlowContext();
        boolean _notEquals_1 = (!Objects.equal(_flowContext, null));
        if (!_notEquals_1) {
          _and_1 = false;
        } else {
          InputContext _flowContext_1 = flow.getFlowContext();
          EList<EClass> _contextType = _flowContext_1.getContextType();
          int _size = _contextType.size();
          boolean _greaterThan = (_size > 0);
          _and_1 = (_notEquals_1 && _greaterThan);
        }
        if (!_and_1) {
          _and = false;
        } else {
          boolean _or = false;
          Screen _to = flow.getTo();
          boolean _equals = _to.equals(screen);
          if (_equals) {
            _or = true;
          } else {
            Screen _from = flow.getFrom();
            boolean _equals_1 = _from.equals(screen);
            _or = (_equals || _equals_1);
          }
          _and = (_and_1 && _or);
        }
        if (_and) {
          InputContext _flowContext_2 = flow.getFlowContext();
          EList<EClass> _contextType_1 = _flowContext_2.getContextType();
          requiredEntities.addAll(_contextType_1);
        }
      }
    }
    Set<EClass> _determindeEntitiesTroughBindigns = this.determindeEntitiesTroughBindigns(screen, m, requiredEntities);
    return requiredEntities = _determindeEntitiesTroughBindigns;
  }
  
  /**
   * Determines the required entities through the bindings with the direct associated displayelements in the screen.
   */
  public Set<EClass> determindeEntitiesTroughBindigns(final Screen screen, final MobileApp m, final Set<EClass> requiredEntities) {
    BindingRepository _bindingRepository = m.getBindingRepository();
    boolean _notEquals = (!Objects.equal(_bindingRepository, null));
    if (_notEquals) {
      BindingRepository _bindingRepository_1 = m.getBindingRepository();
      EList<DomainBinding> _bindings = _bindingRepository_1.getBindings();
      for (final DomainBinding binding : _bindings) {
        if ((binding instanceof PrimitiveBinding)) {
          final PrimitiveBinding primitiveBinding = ((PrimitiveBinding) binding);
          boolean _or = false;
          boolean _and = false;
          if (!(primitiveBinding instanceof StringBinding)) {
            _and = false;
          } else {
            EList<DisplayElement> _displayElements = screen.getDisplayElements();
            TextContaining _uiElement = ((StringBinding) primitiveBinding).getUiElement();
            boolean _contains = _displayElements.contains(_uiElement);
            _and = ((primitiveBinding instanceof StringBinding) && _contains);
          }
          if (_and) {
            _or = true;
          } else {
            boolean _and_1 = false;
            if (!(primitiveBinding instanceof BooleanBinding)) {
              _and_1 = false;
            } else {
              EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
              CheckBox _checkBox = ((BooleanBinding) primitiveBinding).getCheckBox();
              boolean _contains_1 = _displayElements_1.contains(_checkBox);
              _and_1 = ((primitiveBinding instanceof BooleanBinding) && _contains_1);
            }
            _or = (_and || _and_1);
          }
          if (_or) {
            EAttribute _attribute = primitiveBinding.getAttribute();
            EClass _eContainingClass = _attribute.getEContainingClass();
            requiredEntities.add(_eContainingClass);
          }
        } else {
          if ((binding instanceof ComplexBinding)) {
            EClass _domainEntity = ((ComplexBinding) binding).getDomainEntity();
            requiredEntities.add(_domainEntity);
          } else {
            if ((binding instanceof SelectionBinding)) {
              EReference _domainReference = ((SelectionBinding) binding).getDomainReference();
              EClass _eReferenceType = _domainReference.getEReferenceType();
              requiredEntities.add(_eReferenceType);
            }
          }
        }
      }
    }
    return requiredEntities;
  }
  
  private CharSequence generateActivityLifeCycleMethods(final Screen screen, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    String _name = screen.getName();
    CharSequence _generateOnCreateMethod = this.generateOnCreateMethod(_name, screen, m);
    _builder.append(_generateOnCreateMethod, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateOnCreateMethod(final String activityName, final Screen screen, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("public void onCreate(Bundle savedInstantsState){");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("super.onCreate(savedInstantsState);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("mContext = this.getApplicationContext();");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("setContentView(R.layout.");
    String _lowerCase = activityName.toLowerCase();
    _builder.append(_lowerCase, "    ");
    _builder.append(");");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateDisplayElementInOnCreateMethod(final String activityName, final DisplayElement displayElement, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((displayElement instanceof CompositeDisplayElement)) {
      } else {
        final String type = this.javaUtilities.getDisplayElementType(displayElement);
        _builder.newLineIfNotEmpty();
        final String prefix = this.javaUtilities.getDisplayElementPrefix(displayElement);
        _builder.newLineIfNotEmpty();
        {
          boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(type);
          boolean _not = (!_isNullOrEmpty);
          if (_not) {
            {
              if ((displayElement instanceof DynamicList)) {
                _builder.append(activityName, "");
                _builder.append("HookActivity hook = new ");
                _builder.append(activityName, "");
                _builder.append("HookActivity();");
                _builder.newLineIfNotEmpty();
                {
                  boolean _and = false;
                  EClass _domainEntityNameForDynamicList = this.javaUtilities.getDomainEntityNameForDynamicList(m, ((DynamicList) displayElement));
                  boolean _notEquals = (!Objects.equal(_domainEntityNameForDynamicList, null));
                  if (!_notEquals) {
                    _and = false;
                  } else {
                    DisplayElement _firstBoundUiElementForDynamicList = this.javaUtilities.getFirstBoundUiElementForDynamicList(m, ((DynamicList) displayElement));
                    boolean _notEquals_1 = (!Objects.equal(_firstBoundUiElementForDynamicList, null));
                    _and = (_notEquals && _notEquals_1);
                  }
                  if (_and) {
                    _builder.append("                    ");
                    ListBinding _listBindingForDisplayElement = this.javaUtilities.getListBindingForDisplayElement(m, displayElement);
                    DynamicList _listElement = _listBindingForDisplayElement.getListElement();
                    String _name = _listElement.getName();
                    _builder.append(_name, "                    ");
                    _builder.append("ListAdapter adapter = new ");
                    ListBinding _listBindingForDisplayElement_1 = this.javaUtilities.getListBindingForDisplayElement(m, displayElement);
                    DynamicList _listElement_1 = _listBindingForDisplayElement_1.getListElement();
                    String _name_1 = _listElement_1.getName();
                    _builder.append(_name_1, "                    ");
                    _builder.append("ListAdapter(this, true);");
                    _builder.newLineIfNotEmpty();
                    _builder.append("                    ");
                    _builder.append("setListAdapter(adapter);");
                    _builder.newLine();
                    _builder.append("                    ");
                    String _firstLower = StringExtensions.toFirstLower(type);
                    _builder.append(_firstLower, "                    ");
                    String _name_2 = displayElement.getName();
                    _builder.append(_name_2, "                    ");
                    _builder.append(" = getListView();");
                    _builder.newLineIfNotEmpty();
                    _builder.append("                    ");
                    _builder.append("getLoaderManager().restartLoader(0, null, adapter);");
                    _builder.newLine();
                  }
                }
              } else {
                if ((displayElement instanceof Datepicker)) {
                  String _firstLower_1 = StringExtensions.toFirstLower(type);
                  _builder.append(_firstLower_1, "");
                  String _name_3 = displayElement.getName();
                  _builder.append(_name_3, "");
                  _builder.append(" =  Calendar.getInstance();");
                  _builder.newLineIfNotEmpty();
                  _builder.append("   ");
                  String _name_4 = displayElement.getName();
                  String _firstLower_2 = StringExtensions.toFirstLower(_name_4);
                  _builder.append(_firstLower_2, "   ");
                  _builder.append("ImageButton = (ImageButton) findViewById(R.id.imageButton");
                  String _name_5 = displayElement.getName();
                  _builder.append(_name_5, "   ");
                  _builder.append(");");
                  _builder.newLineIfNotEmpty();
                } else {
                  String _firstLower_3 = StringExtensions.toFirstLower(type);
                  _builder.append(_firstLower_3, "");
                  String _name_6 = displayElement.getName();
                  _builder.append(_name_6, "");
                  _builder.append(" = (");
                  _builder.append(type, "");
                  _builder.append(")findViewById(R.id.");
                  _builder.append(prefix, "");
                  String _name_7 = displayElement.getName();
                  _builder.append(_name_7, "");
                  _builder.append(");");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
            CharSequence _generateAddOnClickListener = this.generateAddOnClickListener(type, displayElement);
            _builder.append(_generateAddOnClickListener, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence generateAddOnClickListener(final String type, final DisplayElement displayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _or = false;
      boolean _equals = type.equals("Button");
      if (_equals) {
        _or = true;
      } else {
        boolean _equals_1 = type.equals("ImageButton");
        _or = (_equals || _equals_1);
      }
      if (_or) {
        String _firstLower = StringExtensions.toFirstLower(type);
        _builder.append(_firstLower, "");
        String _name = displayElement.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        _builder.append(_firstUpper, "");
        _builder.append(".setOnClickListener(new ");
        String _name_1 = displayElement.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
        _builder.append(_firstUpper_1, "");
        _builder.append("OnClickListener(this));");
        _builder.newLineIfNotEmpty();
      } else {
        boolean _equals_2 = type.equals("Calendar");
        if (_equals_2) {
          String _name_2 = displayElement.getName();
          String _firstLower_1 = StringExtensions.toFirstLower(_name_2);
          _builder.append(_firstLower_1, "");
          _builder.append("ImageButton.setOnClickListener(new View.OnClickListener() {");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.newLine();
          _builder.append("    ");
          _builder.append("public void onClick(View view){");
          _builder.newLine();
          _builder.append("      ");
          _builder.append("show");
          String _name_3 = displayElement.getName();
          _builder.append(_name_3, "      ");
          _builder.append("DatePickerDialog(view);");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("}");
          _builder.newLine();
          _builder.append("});");
          _builder.newLine();
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateDisplayElementGetterMethods(final Screen screen) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<DisplayElement> _displayElements = screen.getDisplayElements();
      for(final DisplayElement displayElement : _displayElements) {
        {
          boolean _not = (!(displayElement instanceof CompositeDisplayElement));
          if (_not) {
            final String type = this.javaUtilities.getDisplayElementType(displayElement);
            _builder.newLineIfNotEmpty();
            _builder.append("               ");
            _builder.append(type, "               ");
            _builder.append(" get");
            _builder.append(type, "               ");
            String _name = displayElement.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name);
            _builder.append(_firstUpper, "               ");
            _builder.append("(){");
            _builder.newLineIfNotEmpty();
            _builder.append("                 ");
            _builder.append("return ");
            String _firstLower = StringExtensions.toFirstLower(type);
            _builder.append(_firstLower, "                 ");
            String _name_1 = displayElement.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
            _builder.append(_firstUpper_1, "                 ");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            _builder.append("               ");
            _builder.append("}");
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateRequiredEntityGetterMethods(final Set<EClass> requiredEntities) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final EClass entity : requiredEntities) {
        _builder.append("/**");
        _builder.newLine();
        _builder.append("* Returns the entity that is set through ui fields or by incoming intents.");
        _builder.newLine();
        _builder.append("* @return = null if entity was not set by the intent of the input field. Else the entity.");
        _builder.newLine();
        _builder.append("**/");
        _builder.newLine();
        _builder.append("           ");
        _builder.append("public ");
        String _name = entity.getName();
        _builder.append(_name, "           ");
        _builder.append(" get");
        String _name_1 = entity.getName();
        _builder.append(_name_1, "           ");
        _builder.append("(){");
        _builder.newLineIfNotEmpty();
        _builder.append("                ");
        _builder.append("return ");
        String _name_2 = entity.getName();
        String _firstLower = StringExtensions.toFirstLower(_name_2);
        _builder.append(_firstLower, "                ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("           ");
        _builder.append("}    ");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  private CharSequence generateDisplayElementAttributes(final Screen screen) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<DisplayElement> _displayElements = screen.getDisplayElements();
      for(final DisplayElement displayElement : _displayElements) {
        CharSequence _generateDisplayElementAttribute = this.generateDisplayElementAttribute(displayElement);
        _builder.append(_generateDisplayElementAttribute, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      MenuBar _menuBar = screen.getMenuBar();
      boolean _notEquals = (!Objects.equal(_menuBar, null));
      if (_notEquals) {
        {
          MenuBar _menuBar_1 = screen.getMenuBar();
          EList<DisplayElement> _menuBarElements = _menuBar_1.getMenuBarElements();
          for(final DisplayElement displayElement_1 : _menuBarElements) {
            _builder.append("        ");
            CharSequence _generateDisplayElementAttribute_1 = this.generateDisplayElementAttribute(displayElement_1);
            _builder.append(_generateDisplayElementAttribute_1, "        ");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateDisplayElementAttribute(final DisplayElement displayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((displayElement instanceof CompositeDisplayElement)) {
      } else {
        final String type = this.javaUtilities.getDisplayElementType(displayElement);
        _builder.newLineIfNotEmpty();
        _builder.append("            ");
        _builder.append("protected ");
        _builder.append(type, "            ");
        _builder.append(" ");
        String _firstLower = StringExtensions.toFirstLower(type);
        _builder.append(_firstLower, "            ");
        String _name = displayElement.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        _builder.append(_firstUpper, "            ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  private CharSequence generateLocationPickerMethodsAndClasses() {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateMethodIsRouteDisplayed = this.generateMethodIsRouteDisplayed();
    _builder.append(_generateMethodIsRouteDisplayed, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateMethodIsRouteDisplayed() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("protected boolean isRouteDisplayed() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return false;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
