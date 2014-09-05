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
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.event.ButtonClickEvent;
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.Flow;
import de.modagile.metamodel.app.ui.InputContext;
import de.modagile.metamodel.app.ui.MenuBar;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class OnClickHookTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtils;
  
  private void generateOnClickHook(final IFileSystemAccess fsa, final DisplayElement displayElement, final PackageInfo packageInfo, final Screen screen, final MobileApp m) {
    String _packageDir = packageInfo.getPackageDir();
    String _plus = (_packageDir + "activity/listener/");
    String _name = displayElement.getName();
    String _plus_1 = (_plus + _name);
    String _plus_2 = (_plus_1 + "OnClickListener.java");
    String _packageName = packageInfo.getPackageName();
    String _plus_3 = (_packageName + ".activity.listener");
    CharSequence _generateSource = this.generateSource(_plus_3, displayElement, screen, m, packageInfo);
    fsa.generateFile(_plus_2, ModagileFolderConstants.SRC_MAN, _generateSource);
  }
  
  private Object generateDatePickerOnClickHook(final IFileSystemAccess fsa, final Datepicker datepicker, final PackageInfo packageInfo, final Screen screen, final MobileApp m) {
    return null;
  }
  
  public void generateOnClickHookListener(final IFileSystemAccess fsa, final Screen screen, final MobileApp m, final PackageInfo packageInfo) {
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Procedure1<DisplayElement> _function = new Procedure1<DisplayElement>() {
        public void apply(final DisplayElement displayElement) {
          if ((displayElement instanceof Button)) {
            OnClickHookTemplate.this.generateOnClickHook(fsa, displayElement, packageInfo, screen, m);
          } else {
            if ((displayElement instanceof Datepicker)) {
              OnClickHookTemplate.this.generateDatePickerOnClickHook(fsa, ((Datepicker) displayElement), packageInfo, screen, m);
            }
          }
        }
      };
    IterableExtensions.<DisplayElement>forEach(_displayElements, _function);
    MenuBar _menuBar = screen.getMenuBar();
    boolean _notEquals = (!Objects.equal(_menuBar, null));
    if (_notEquals) {
      MenuBar _menuBar_1 = screen.getMenuBar();
      EList<DisplayElement> _menuBarElements = _menuBar_1.getMenuBarElements();
      final Procedure1<DisplayElement> _function_1 = new Procedure1<DisplayElement>() {
          public void apply(final DisplayElement displayElement) {
            if ((displayElement instanceof Button)) {
              OnClickHookTemplate.this.generateOnClickHook(fsa, displayElement, packageInfo, screen, m);
            } else {
              if ((displayElement instanceof Datepicker)) {
                OnClickHookTemplate.this.generateDatePickerOnClickHook(fsa, ((Datepicker) displayElement), packageInfo, screen, m);
              }
            }
          }
        };
      IterableExtensions.<DisplayElement>forEach(_menuBarElements, _function_1);
    }
  }
  
  public CharSequence generateSource(final String eventsPackageName, final DisplayElement displayElement, final Screen screen, final MobileApp m, final PackageInfo packageInfo) {
    CharSequence _xblockexpression = null;
    {
      String screenName = "";
      EObject _eContainer = displayElement.eContainer();
      if ((_eContainer instanceof Screen)) {
        Screen _screen = displayElement.getScreen();
        String _name = _screen.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        screenName = _firstUpper;
      } else {
        EObject _eContainer_1 = displayElement.eContainer();
        if ((_eContainer_1 instanceof MenuBar)) {
          EObject _eContainer_2 = displayElement.eContainer();
          EObject _eContainer_3 = _eContainer_2.eContainer();
          String _name_1 = ((Screen) _eContainer_3).getName();
          String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
          screenName = _firstUpper_1;
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("     ");
      CharSequence _statementGenerated = this.javaUtils.statementGenerated("OnClickHookTemplate");
      _builder.append(_statementGenerated, "     ");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      CharSequence _packageStatement = this.javaUtils.packageStatement(eventsPackageName);
      _builder.append(_packageStatement, "     ");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("     ");
      _builder.append("import android.view.View;");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("import android.view.View.OnClickListener;");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("import android.content.Intent;");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("import ");
      String _packageName = packageInfo.getPackageName();
      _builder.append(_packageName, "     ");
      _builder.append(".activity.");
      _builder.append(screenName, "     ");
      _builder.append("HookActivity;");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("import ");
      String _packageName_1 = packageInfo.getPackageName();
      _builder.append(_packageName_1, "     ");
      _builder.append(".activity.");
      EList<ButtonClickEvent> _buttonClickEvents = ((Button) displayElement).getButtonClickEvents();
      ButtonClickEvent _get = _buttonClickEvents.get(0);
      Flow _triggers = _get.getTriggers();
      Screen _to = _triggers.getTo();
      String _name_2 = _to.getName();
      String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
      _builder.append(_firstUpper_2, "     ");
      _builder.append("HookActivity;");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("import ");
      String _packageName_2 = packageInfo.getPackageName();
      _builder.append(_packageName_2, "     ");
      _builder.append(".constants.");
      String _name_3 = m.getName();
      String _firstUpper_3 = StringExtensions.toFirstUpper(_name_3);
      _builder.append(_firstUpper_3, "     ");
      _builder.append("Constants;");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.newLine();
      _builder.append("     ");
      _builder.append("public class ");
      String _name_4 = displayElement.getName();
      String _firstUpper_4 = StringExtensions.toFirstUpper(_name_4);
      _builder.append(_firstUpper_4, "     ");
      _builder.append("OnClickListener implements OnClickListener{");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("private ");
      _builder.append(screenName, "     ");
      _builder.append("HookActivity mActivity;");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      CharSequence _constructor = this.constructor(displayElement, screenName);
      _builder.append(_constructor, "     ");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("     ");
      StoryBoard _storyBoard = screen.getStoryBoard();
      EList<Flow> _flows = _storyBoard.getFlows();
      BindingRepository _bindingRepository = m.getBindingRepository();
      CharSequence _generateButtonClickMethod = this.generateButtonClickMethod(m, ((Button) displayElement), _flows, _bindingRepository);
      _builder.append(_generateButtonClickMethod, "     ");
      _builder.newLineIfNotEmpty();
      _builder.append("     ");
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence constructor(final DisplayElement displayElement, final String screenName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    String _name = displayElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    _builder.append("OnClickListener(");
    _builder.append(screenName, "");
    _builder.append("HookActivity activity){");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    _builder.append("mActivity = activity;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateButtonClickMethod(final MobileApp app, final Button button, final List<Flow> flows, final BindingRepository bindingRepository) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _and = false;
      EList<ButtonClickEvent> _buttonClickEvents = button.getButtonClickEvents();
      ButtonClickEvent _get = _buttonClickEvents.get(0);
      boolean _notEquals = (!Objects.equal(_get, null));
      if (!_notEquals) {
        _and = false;
      } else {
        EList<ButtonClickEvent> _buttonClickEvents_1 = button.getButtonClickEvents();
        ButtonClickEvent _get_1 = _buttonClickEvents_1.get(0);
        Flow _triggers = _get_1.getTriggers();
        boolean _equals = Objects.equal(_triggers, null);
        _and = (_notEquals && _equals);
      }
      if (_and) {
        CharSequence _generateOnClickMethodsWithoutExistingFlow = this.generateOnClickMethodsWithoutExistingFlow(button);
        _builder.append(_generateOnClickMethodsWithoutExistingFlow, "");
        _builder.newLineIfNotEmpty();
      } else {
        EList<ButtonClickEvent> _buttonClickEvents_2 = button.getButtonClickEvents();
        ButtonClickEvent _get_2 = _buttonClickEvents_2.get(0);
        boolean _notEquals_1 = (!Objects.equal(_get_2, null));
        if (_notEquals_1) {
          CharSequence _generateOnClickMethodsWithExistingFlow = this.generateOnClickMethodsWithExistingFlow(app, button, flows, bindingRepository);
          _builder.append(_generateOnClickMethodsWithExistingFlow, "");
          _builder.newLineIfNotEmpty();
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateOnClickMethodsWithoutExistingFlow(final Button button) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void onClick(View v){");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateOnClickMethodsWithExistingFlow(final MobileApp app, final Button button, final List<Flow> flows, final BindingRepository bindingRepository) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void onClick(View v){");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("//TODO change behavior if needed");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("Intent intent = new Intent(mActivity, ");
    EList<ButtonClickEvent> _buttonClickEvents = button.getButtonClickEvents();
    ButtonClickEvent _get = _buttonClickEvents.get(0);
    Flow _triggers = _get.getTriggers();
    Screen _to = _triggers.getTo();
    String _name = _to.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "    ");
    _builder.append("HookActivity.class);");
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(bindingRepository, null));
      if (_notEquals) {
        _builder.append("    ");
        _builder.append("mActivity.saveUIDataToEntity();");
        _builder.newLine();
      }
    }
    _builder.append("   ");
    CharSequence _generateContextIfExists = this.generateContextIfExists(button);
    _builder.append(_generateContextIfExists, "   ");
    _builder.newLineIfNotEmpty();
    _builder.append("   ");
    EList<ButtonClickEvent> _buttonClickEvents_1 = button.getButtonClickEvents();
    ButtonClickEvent _get_1 = _buttonClickEvents_1.get(0);
    Flow _triggers_1 = _get_1.getTriggers();
    CharSequence _generateOnClickMethodStartActivity = this.generateOnClickMethodStartActivity(app, _triggers_1);
    _builder.append(_generateOnClickMethodStartActivity, "   ");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateOnClickMethodStartActivity(final MobileApp app, final Flow flow) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isReturnsResult = flow.isReturnsResult();
      if (_isReturnsResult) {
        _builder.append("mActivity.startActivityForResult(intent, ");
        String _name = app.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        _builder.append(_firstUpper, "");
        _builder.append("Constants.REQUEST_CODE_");
        String _name_1 = flow.getName();
        String _upperCase = _name_1.toUpperCase();
        String _replace = _upperCase.replace("->", "_TO_");
        String _replace_1 = _replace.replace(" ", "");
        _builder.append(_replace_1, "");
        _builder.append(");");
        _builder.newLineIfNotEmpty();
      } else {
        _builder.append("mActivity.startActivity(intent);");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence generateContextIfExists(final Button button) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _and = false;
      boolean _and_1 = false;
      EList<ButtonClickEvent> _buttonClickEvents = button.getButtonClickEvents();
      int _size = _buttonClickEvents.size();
      boolean _greaterThan = (_size > 0);
      if (!_greaterThan) {
        _and_1 = false;
      } else {
        EList<ButtonClickEvent> _buttonClickEvents_1 = button.getButtonClickEvents();
        ButtonClickEvent _get = _buttonClickEvents_1.get(0);
        Flow _triggers = _get.getTriggers();
        boolean _notEquals = (!Objects.equal(_triggers, null));
        _and_1 = (_greaterThan && _notEquals);
      }
      if (!_and_1) {
        _and = false;
      } else {
        EList<ButtonClickEvent> _buttonClickEvents_2 = button.getButtonClickEvents();
        ButtonClickEvent _get_1 = _buttonClickEvents_2.get(0);
        Flow _triggers_1 = _get_1.getTriggers();
        InputContext _flowContext = _triggers_1.getFlowContext();
        boolean _notEquals_1 = (!Objects.equal(_flowContext, null));
        _and = (_and_1 && _notEquals_1);
      }
      if (_and) {
        EList<ButtonClickEvent> _buttonClickEvents_3 = button.getButtonClickEvents();
        ButtonClickEvent _get_2 = _buttonClickEvents_3.get(0);
        Flow _triggers_2 = _get_2.getTriggers();
        final InputContext context = _triggers_2.getFlowContext();
        _builder.newLineIfNotEmpty();
        {
          EList<EClass> _contextType = context.getContextType();
          boolean _notEquals_2 = (!Objects.equal(_contextType, null));
          if (_notEquals_2) {
            {
              EList<EClass> _contextType_1 = context.getContextType();
              for(final EClass contextEntity : _contextType_1) {
                _builder.append("intent.putExtra(\"");
                String _name = contextEntity.getName();
                _builder.append(_name, "");
                _builder.append("\", mActivity.get");
                String _name_1 = contextEntity.getName();
                _builder.append(_name_1, "");
                _builder.append("());");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
}
