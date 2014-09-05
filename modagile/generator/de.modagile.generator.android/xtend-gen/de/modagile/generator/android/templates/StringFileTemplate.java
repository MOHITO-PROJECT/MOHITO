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
package de.modagile.generator.android.templates;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.Input;
import de.modagile.metamodel.app.ui.Label;
import de.modagile.metamodel.app.ui.MenuBar;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class StringFileTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public void generateAndroidStringFile(final IFileSystemAccess fsa, final MobileApp app) {
    CharSequence _generateXMLFile = this.generateXMLFile(app);
    fsa.generateFile("values/strings.xml", ModagileFolderConstants.RESOURCE, _generateXMLFile);
  }
  
  private CharSequence generateXMLFile(final MobileApp app) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<resources>");
    _builder.newLine();
    {
      StoryBoard _storyBoard = app.getStoryBoard();
      boolean _notEquals = (!Objects.equal(_storyBoard, null));
      if (_notEquals) {
        StoryBoard _storyBoard_1 = app.getStoryBoard();
        EList<Screen> _screens = _storyBoard_1.getScreens();
        String _name = app.getName();
        String _appVersion = app.getAppVersion();
        CharSequence _generateStringCode = this.generateStringCode(_screens, _name, _appVersion);
        _builder.append(_generateStringCode, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("    ");
    _builder.append("<string name=\"settingsTitle\">Settings</string>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<string name=\"settingsServerIp\">Server IP</string>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<string name=\"settingsServerIpSummary\">Server IP Address (e.g. 10.0.2.2)</string>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<string name=\"settingsServerPort\">Server port</string>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<string name=\"settingsServerPortSummary\">Port number where the server is listening (e.g. 8000)</string>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<string name=\"app_name\">");
    String _name_1 = app.getName();
    String _appVersion_1 = app.getAppVersion();
    String _plus = (_name_1 + _appVersion_1);
    _builder.append(_plus, "	");
    _builder.append("</string>");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("<string name=\"dash\">-</string>");
    _builder.newLine();
    _builder.append("</resources>\t\t");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateStringCode(final List<Screen> activities, final String appName, final String appVersion) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Screen activity : activities) {
        _builder.append("<string name=\"");
        String _name = activity.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name);
        _builder.append(_firstUpper, "");
        _builder.append("\">");
        String _name_1 = activity.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
        _builder.append(_firstUpper_1, "");
        _builder.append("</string>");
        _builder.newLineIfNotEmpty();
        {
          EList<DisplayElement> _displayElements = activity.getDisplayElements();
          for(final DisplayElement displayElement : _displayElements) {
            _builder.append("\t");
            CharSequence _generateDisplayElementString = this.generateDisplayElementString(displayElement, null);
            _builder.append(_generateDisplayElementString, "	");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          MenuBar _menuBar = activity.getMenuBar();
          boolean _notEquals = (!Objects.equal(_menuBar, null));
          if (_notEquals) {
            _builder.append("\t");
            MenuBar _menuBar_1 = activity.getMenuBar();
            CharSequence _generateMenubarStrings = this.generateMenubarStrings(_menuBar_1);
            _builder.append(_generateMenubarStrings, "	");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateCompositeDisplayElementStrings(final CompositeDisplayElement compositeDisplayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      CompositeDisplayElementType _type = compositeDisplayElement.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      for(final DisplayElement displayElementInComposite : _containedDisplayElements) {
        {
          if ((displayElementInComposite instanceof CompositeDisplayElement)) {
            Object _generateCompositeDisplayElementStrings = this.generateCompositeDisplayElementStrings(compositeDisplayElement);
            _builder.append(_generateCompositeDisplayElementStrings, "");
            _builder.newLineIfNotEmpty();
          } else {
            CharSequence _generateDisplayElementString = this.generateDisplayElementString(displayElementInComposite, compositeDisplayElement);
            _builder.append(_generateDisplayElementString, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateMenubarStrings(final MenuBar menuBar) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    {
      EList<DisplayElement> _menuBarElements = menuBar.getMenuBarElements();
      for(final DisplayElement displayElement : _menuBarElements) {
        CharSequence _generateDisplayElementString = this.generateDisplayElementString(displayElement, null);
        _builder.append(_generateDisplayElementString, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  private CharSequence generateDisplayElementString(final DisplayElement displayElement, final CompositeDisplayElement compositeDisplayElement) {
    CharSequence _xblockexpression = null;
    {
      String compositeDisplayElementName = "";
      boolean _notEquals = (!Objects.equal(compositeDisplayElement, null));
      if (_notEquals) {
        String _name = compositeDisplayElement.getName();
        compositeDisplayElementName = _name;
      }
      StringConcatenation _builder = new StringConcatenation();
      {
        if ((displayElement instanceof Label)) {
          Label label = ((Label) displayElement);
          _builder.newLineIfNotEmpty();
          _builder.append("<string name=\"");
          String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(label);
          _builder.append(_displayElementPrefix, "");
          String _name_1 = label.getName();
          String _firstUpper = StringExtensions.toFirstUpper(_name_1);
          String _plus = (compositeDisplayElementName + _firstUpper);
          String _firstUpper_1 = StringExtensions.toFirstUpper(_plus);
          _builder.append(_firstUpper_1, "");
          _builder.append("\">");
          String _text = label.getText();
          _builder.append(_text, "");
          _builder.append("</string>");
          _builder.newLineIfNotEmpty();
        } else {
          if ((displayElement instanceof ImageButton)) {
            ImageButton imageButton = ((ImageButton) displayElement);
            _builder.newLineIfNotEmpty();
            _builder.append("<string name=\"");
            String _displayElementPrefix_1 = this.javaUtilities.getDisplayElementPrefix(imageButton);
            _builder.append(_displayElementPrefix_1, "");
            String _name_2 = imageButton.getName();
            String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
            String _plus_1 = (compositeDisplayElementName + _firstUpper_2);
            String _firstLower = StringExtensions.toFirstLower(_plus_1);
            _builder.append(_firstLower, "");
            _builder.append("\">");
            String _text_1 = imageButton.getText();
            _builder.append(_text_1, "");
            _builder.append("</string>");
            _builder.newLineIfNotEmpty();
          } else {
            if ((displayElement instanceof Button)) {
              Button button = ((Button) displayElement);
              _builder.newLineIfNotEmpty();
              _builder.append("<string name=\"");
              String _displayElementPrefix_2 = this.javaUtilities.getDisplayElementPrefix(button);
              _builder.append(_displayElementPrefix_2, "");
              String _name_3 = button.getName();
              String _firstUpper_3 = StringExtensions.toFirstUpper(_name_3);
              String _plus_2 = (compositeDisplayElementName + _firstUpper_3);
              String _firstUpper_4 = StringExtensions.toFirstUpper(_plus_2);
              _builder.append(_firstUpper_4, "");
              _builder.append("\">");
              String _text_2 = button.getText();
              _builder.append(_text_2, "");
              _builder.append("</string>");
              _builder.newLineIfNotEmpty();
            } else {
              if ((displayElement instanceof Datepicker)) {
                Datepicker datepicker = ((Datepicker) displayElement);
                _builder.newLineIfNotEmpty();
                _builder.append("<string name=\"");
                String _displayElementPrefix_3 = this.javaUtilities.getDisplayElementPrefix(datepicker);
                _builder.append(_displayElementPrefix_3, "");
                String _name_4 = datepicker.getName();
                String _firstUpper_5 = StringExtensions.toFirstUpper(_name_4);
                String _plus_3 = (compositeDisplayElementName + _firstUpper_5);
                String _firstLower_1 = StringExtensions.toFirstLower(_plus_3);
                _builder.append(_firstLower_1, "");
                _builder.append("\">Cannot use a future date!</string>");
                _builder.newLineIfNotEmpty();
              } else {
                if ((displayElement instanceof Input)) {
                  Input inputfield = ((Input) displayElement);
                  _builder.newLineIfNotEmpty();
                  _builder.append("<string name=\"");
                  String _displayElementPrefix_4 = this.javaUtilities.getDisplayElementPrefix(inputfield);
                  _builder.append(_displayElementPrefix_4, "");
                  String _name_5 = inputfield.getName();
                  String _firstUpper_6 = StringExtensions.toFirstUpper(_name_5);
                  String _plus_4 = (compositeDisplayElementName + _firstUpper_6);
                  String _firstLower_2 = StringExtensions.toFirstLower(_plus_4);
                  _builder.append(_firstLower_2, "");
                  _builder.append("\">");
                  String _text_3 = inputfield.getText();
                  _builder.append(_text_3, "");
                  _builder.append("</string>");
                  _builder.newLineIfNotEmpty();
                } else {
                  if ((displayElement instanceof CompositeDisplayElement)) {
                    {
                      CompositeDisplayElementType _type = ((CompositeDisplayElement) displayElement).getType();
                      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
                      for(final DisplayElement displayElementInCDE : _containedDisplayElements) {
                        Object _generateDisplayElementString = this.generateDisplayElementString(displayElementInCDE, ((CompositeDisplayElement) displayElement));
                        _builder.append(_generateDisplayElementString, "");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                  } else {
                    if ((displayElement instanceof DynamicList)) {
                      {
                        EList<CompositeDisplayElementType> _displayedElements = ((DynamicList) displayElement).getDisplayedElements();
                        for(final CompositeDisplayElementType cdeType : _displayedElements) {
                          {
                            EList<DisplayElement> _containedDisplayElements_1 = cdeType.getContainedDisplayElements();
                            for(final DisplayElement displayElementInCDE_1 : _containedDisplayElements_1) {
                              Object _generateDisplayElementString_1 = this.generateDisplayElementString(displayElementInCDE_1, null);
                              _builder.append(_generateDisplayElementString_1, "");
                              _builder.newLineIfNotEmpty();
                            }
                          }
                        }
                      }
                    } else {
                      _builder.append("<string name=\"");
                      String _name_6 = displayElement.getName();
                      String _plus_5 = (compositeDisplayElementName + _name_6);
                      String _firstLower_3 = StringExtensions.toFirstLower(_plus_5);
                      _builder.append(_firstLower_3, "");
                      _builder.append("\">");
                      String _name_7 = displayElement.getName();
                      _builder.append(_name_7, "");
                      _builder.append("</string>");
                      _builder.newLineIfNotEmpty();
                    }
                  }
                }
              }
            }
          }
        }
      }
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
}
