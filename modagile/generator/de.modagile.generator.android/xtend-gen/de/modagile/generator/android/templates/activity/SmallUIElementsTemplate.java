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

import com.google.inject.Inject;
import de.modagile.generator.android.templates.activity.DatepickerAdditionsTemplate;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.event.ButtonClickEvent;
import de.modagile.metamodel.app.event.Event;
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CheckBox;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.Flow;
import de.modagile.metamodel.app.ui.Image;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.Input;
import de.modagile.metamodel.app.ui.Label;
import de.modagile.metamodel.app.ui.LocationPicker;
import de.modagile.metamodel.app.ui.StoryBoard;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class SmallUIElementsTemplate {
  @Inject
  @Extension
  private DatepickerAdditionsTemplate datepickerAdditions;
  
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  public CharSequence generateLayoutCodeForDisplayElement(final DisplayElement displayElement, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _and = false;
      if (!(displayElement instanceof Button)) {
        _and = false;
      } else {
        boolean _not = (!(displayElement instanceof ImageButton));
        _and = ((displayElement instanceof Button) && _not);
      }
      if (_and) {
        Flow _flowToButton = this.getFlowToButton(m, ((Button) displayElement));
        CharSequence _generateButton = this.generateButton(((Button) displayElement), _flowToButton);
        _builder.append(_generateButton, "");
        _builder.newLineIfNotEmpty();
      } else {
        if ((displayElement instanceof Label)) {
          CharSequence _generateLabel = this.generateLabel(((Label) displayElement));
          _builder.append(_generateLabel, "");
          _builder.newLineIfNotEmpty();
        } else {
          if ((displayElement instanceof Datepicker)) {
            CharSequence _generateDatePickerLayout = this.datepickerAdditions.generateDatePickerLayout(((Datepicker) displayElement));
            _builder.append(_generateDatePickerLayout, "");
            _builder.newLineIfNotEmpty();
          } else {
            if ((displayElement instanceof Input)) {
              CharSequence _generateEditText = this.generateEditText(((Input) displayElement));
              _builder.append(_generateEditText, "");
              _builder.newLineIfNotEmpty();
            } else {
              if ((displayElement instanceof Image)) {
                CharSequence _generateImage = this.generateImage(((Image) displayElement));
                _builder.append(_generateImage, "");
                _builder.newLineIfNotEmpty();
              } else {
                if ((displayElement instanceof ImageButton)) {
                  CharSequence _generateImageButton = this.generateImageButton(((ImageButton) displayElement));
                  _builder.append(_generateImageButton, "");
                  _builder.newLineIfNotEmpty();
                } else {
                  if ((displayElement instanceof DynamicList)) {
                    CharSequence _generateListView = this.generateListView();
                    _builder.append(_generateListView, "");
                    _builder.newLineIfNotEmpty();
                  } else {
                    if ((displayElement instanceof LocationPicker)) {
                      CharSequence _generateLocationPicker = this.generateLocationPicker(((LocationPicker) displayElement));
                      _builder.append(_generateLocationPicker, "");
                      _builder.newLineIfNotEmpty();
                    } else {
                      if ((displayElement instanceof CheckBox)) {
                        CharSequence _generateCheckBox = this.generateCheckBox(((CheckBox) displayElement));
                        _builder.append(_generateCheckBox, "");
                        _builder.newLineIfNotEmpty();
                      } else {
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
  
  private Flow getFlowToButton(final MobileApp app, final Button button) {
    StoryBoard _storyBoard = app.getStoryBoard();
    EList<Flow> _flows = _storyBoard.getFlows();
    for (final Flow flow : _flows) {
      EList<Event> _events = flow.getEvents();
      for (final Event event : _events) {
        EList<ButtonClickEvent> _buttonClickEvents = button.getButtonClickEvents();
        boolean _contains = _buttonClickEvents.contains(event);
        if (_contains) {
          return flow;
        }
      }
    }
    return null;
  }
  
  public CharSequence generateButtons(final List<Button> buttons, final Flow flow) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Button button : buttons) {
        CharSequence _generateButton = this.generateButton(button, flow);
        _builder.append(_generateButton, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence generateButton(final Button button, final Flow flow) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Button");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(button);
    _builder.append(_displayElementPrefix, "	");
    String _name = button.getName();
    _builder.append(_name, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"wrap_content\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:layout_height=\"wrap_content\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:text=\"@string/button");
    String _name_1 = button.getName();
    _builder.append(_name_1, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateButtonFromCompositeDisplayElement(final String compositeDisplayElementName, final Button button) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Button");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(button);
    _builder.append(_displayElementPrefix, "	");
    String _name = button.getName();
    String _plus = (compositeDisplayElementName + _name);
    String _firstUpper = StringExtensions.toFirstUpper(_plus);
    _builder.append(_firstUpper, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"wrap_content\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:layout_height=\"wrap_content\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:text=\"@string/button");
    String _name_1 = button.getName();
    String _plus_1 = (compositeDisplayElementName + _name_1);
    String _firstUpper_1 = StringExtensions.toFirstUpper(_plus_1);
    _builder.append(_firstUpper_1, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateLabels(final List<Label> labels) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Label label : labels) {
        CharSequence _generateLabel = this.generateLabel(label);
        _builder.append(_generateLabel, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence generateLabel(final Label label) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<TextView");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(label);
    _builder.append(_displayElementPrefix, "	");
    String _name = label.getName();
    _builder.append(_name, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"wrap_content\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:layout_height=\"wrap_content\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:text=\"@string/textView");
    String _name_1 = label.getName();
    _builder.append(_name_1, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateLabelFromCompositeDisplayElement(final String compositeDisplayElementName, final Label label) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<TextView");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(label);
    _builder.append(_displayElementPrefix, "	");
    String _name = label.getName();
    String _plus = (compositeDisplayElementName + _name);
    String _firstUpper = StringExtensions.toFirstUpper(_plus);
    _builder.append(_firstUpper, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"wrap_content\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:layout_height=\"wrap_content\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:text=\"@string/textView");
    String _name_1 = label.getName();
    String _plus_1 = (compositeDisplayElementName + _name_1);
    String _firstUpper_1 = StringExtensions.toFirstUpper(_plus_1);
    _builder.append(_firstUpper_1, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateImages(final List<Image> images) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Image image : images) {
        CharSequence _generateImage = this.generateImage(image);
        _builder.append(_generateImage, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence generateImage(final Image image) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ImageView");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(image);
    _builder.append(_displayElementPrefix, "            ");
    String _name = image.getName();
    _builder.append(_name, "            ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("android:layout_width=\"");
    int _width = image.getWidth();
    _builder.append(_width, "            ");
    _builder.append("dp\"");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("android:layout_height=\"");
    int _height = image.getHeight();
    _builder.append(_height, "            ");
    _builder.append("dp\"");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("android:adjustViewBounds=\"true\"");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("android:scaleType=\"fitStart\"");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("android:contentDescription=\"@string/");
    String _name_1 = image.getName();
    String _firstLower = StringExtensions.toFirstLower(_name_1);
    _builder.append(_firstLower, "            ");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("android:src=\"@drawable/");
    String _name_2 = image.getName();
    String _lowerCase = _name_2.toLowerCase();
    _builder.append(_lowerCase, "            ");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateImageFromCompositeDisplayElement(final String compositeDisplayElementName, final Image image) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Image");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(image);
    _builder.append(_displayElementPrefix, "	");
    String _name = image.getName();
    String _plus = (compositeDisplayElementName + _name);
    _builder.append(_plus, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"");
    int _width = image.getWidth();
    _builder.append(_width, "	");
    _builder.append("dp\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_height=\"");
    int _height = image.getHeight();
    _builder.append(_height, "	");
    _builder.append("dp\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:background=\"#000000\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:scaleType=\"centerInside\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:contentDescription=\"@string/");
    String _firstLower = StringExtensions.toFirstLower(compositeDisplayElementName);
    String _name_1 = image.getName();
    String _plus_1 = (_firstLower + _name_1);
    _builder.append(_plus_1, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:src=\"@drawable/");
    String _name_2 = image.getName();
    String _lowerCase = _name_2.toLowerCase();
    _builder.append(_lowerCase, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateImageButton(final ImageButton imageButton) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ImageButton");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(imageButton);
    _builder.append(_displayElementPrefix, "	");
    String _name = imageButton.getName();
    _builder.append(_name, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"");
    _builder.append(100, "	");
    _builder.append("dp\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_height=\"");
    _builder.append(100, "	");
    _builder.append("dp\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:background=\"#000000\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:onClick=\"onClick");
    String _name_1 = imageButton.getName();
    _builder.append(_name_1, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:scaleType=\"centerInside\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:src=\"@drawable/");
    String _name_2 = imageButton.getName();
    String _lowerCase = _name_2.toLowerCase();
    _builder.append(_lowerCase, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateImageButtonFromCompositeDisplayElement(final String compositeDisplayElementName, final ImageButton imageButton) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ImageButton");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(imageButton);
    _builder.append(_displayElementPrefix, "	");
    String _name = imageButton.getName();
    String _plus = (compositeDisplayElementName + _name);
    _builder.append(_plus, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"");
    _builder.append(100, "	");
    _builder.append("dp\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_height=\"");
    _builder.append(100, "	");
    _builder.append("dp\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:background=\"#000000\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:onClick=\"onClick");
    String _name_1 = imageButton.getName();
    String _plus_1 = (compositeDisplayElementName + _name_1);
    _builder.append(_plus_1, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:scaleType=\"centerInside\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:src=\"@drawable/");
    String _name_2 = imageButton.getName();
    String _lowerCase = _name_2.toLowerCase();
    _builder.append(_lowerCase, "	");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateEditTexts(final List<Input> inputs) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Input input : inputs) {
        CharSequence _generateEditText = this.generateEditText(input);
        _builder.append(_generateEditText, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence generateLocationPicker(final LocationPicker locationPicker) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence generateLocationPicker(final String compositeDisplayElementName, final LocationPicker locationPicker) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence generateEditText(final Input input) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<EditText");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(input);
    _builder.append(_displayElementPrefix, " 	");
    String _name = input.getName();
    _builder.append(_name, " 	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"100dp\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:layout_height=\"100dp\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:inputType=\"");
    String _editTextInputType = this.javaUtilities.getEditTextInputType(input);
    _builder.append(_editTextInputType, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:focusable=\"true\"/>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateEditTextFromCompositeDisplayElement(final String compositeDisplayElementName, final Input input) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<EditText");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(input);
    _builder.append(_displayElementPrefix, " 	");
    String _name = input.getName();
    String _plus = (compositeDisplayElementName + _name);
    _builder.append(_plus, " 	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:layout_width=\"100dp\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:layout_height=\"100dp\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:inputType=\"");
    String _editTextInputType = this.javaUtilities.getEditTextInputType(input);
    _builder.append(_editTextInputType, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:focusable=\"true\"/>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateListView() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ListView");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@android:id/list\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("android:layout_width=\"match_parent\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("android:layout_height=\"wrap_content\"/>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence generateCheckBox(final CheckBox checkBox) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<CheckBox");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:id=\"@+id/");
    String _displayElementPrefix = this.javaUtilities.getDisplayElementPrefix(checkBox);
    _builder.append(_displayElementPrefix, "	");
    String _name = checkBox.getName();
    _builder.append(_name, "	");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("            ");
    _builder.append("android:layout_width=\"wrap_content\"");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("android:layout_height=\"wrap_content\"");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("android:text=\"");
    String _name_1 = checkBox.getName();
    _builder.append(_name_1, "            ");
    _builder.append("\" />");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
