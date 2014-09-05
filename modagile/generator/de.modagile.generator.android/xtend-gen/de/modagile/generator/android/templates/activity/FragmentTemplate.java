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
import de.modagile.generator.android.templates.activity.SmallUIElementsTemplate;
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.Image;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.Input;
import de.modagile.metamodel.app.ui.Label;
import de.modagile.metamodel.app.ui.LocationPicker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * Generates all Fragem Android-Code Classes for the existing CompositeDisplayElements in the model.
 */
@SuppressWarnings("all")
public class FragmentTemplate {
  @Inject
  @Extension
  private DatepickerAdditionsTemplate datepickerAdditions;
  
  @Inject
  @Extension
  private SmallUIElementsTemplate smallUIElements;
  
  private CharSequence generateCompositeDisplayElementLayoutCode(final CompositeDisplayElement compositeDisplayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      CompositeDisplayElementType _type = compositeDisplayElement.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      for(final DisplayElement displayElement : _containedDisplayElements) {
        {
          boolean _and = false;
          if (!(displayElement instanceof Button)) {
            _and = false;
          } else {
            boolean _not = (!(displayElement instanceof ImageButton));
            _and = ((displayElement instanceof Button) && _not);
          }
          if (_and) {
            String _name = compositeDisplayElement.getName();
            CharSequence _generateButtonFromCompositeDisplayElement = this.smallUIElements.generateButtonFromCompositeDisplayElement(_name, ((Button) displayElement));
            _builder.append(_generateButtonFromCompositeDisplayElement, "");
            _builder.newLineIfNotEmpty();
          } else {
            if ((displayElement instanceof Label)) {
              String _name_1 = compositeDisplayElement.getName();
              CharSequence _generateLabelFromCompositeDisplayElement = this.smallUIElements.generateLabelFromCompositeDisplayElement(_name_1, ((Label) displayElement));
              _builder.append(_generateLabelFromCompositeDisplayElement, "");
              _builder.newLineIfNotEmpty();
            } else {
              if ((displayElement instanceof Datepicker)) {
                CharSequence _generateDatePickerLayout = this.datepickerAdditions.generateDatePickerLayout(((Datepicker) displayElement));
                _builder.append(_generateDatePickerLayout, "");
                _builder.newLineIfNotEmpty();
              } else {
                if ((displayElement instanceof Input)) {
                  String _name_2 = compositeDisplayElement.getName();
                  CharSequence _generateEditTextFromCompositeDisplayElement = this.smallUIElements.generateEditTextFromCompositeDisplayElement(_name_2, ((Input) displayElement));
                  _builder.append(_generateEditTextFromCompositeDisplayElement, "");
                  _builder.newLineIfNotEmpty();
                } else {
                  if ((displayElement instanceof Image)) {
                    String _name_3 = compositeDisplayElement.getName();
                    CharSequence _generateImageFromCompositeDisplayElement = this.smallUIElements.generateImageFromCompositeDisplayElement(_name_3, ((Image) displayElement));
                    _builder.append(_generateImageFromCompositeDisplayElement, "");
                    _builder.newLineIfNotEmpty();
                  } else {
                    if ((displayElement instanceof ImageButton)) {
                      String _name_4 = compositeDisplayElement.getName();
                      CharSequence _generateImageButtonFromCompositeDisplayElement = this.smallUIElements.generateImageButtonFromCompositeDisplayElement(_name_4, ((ImageButton) displayElement));
                      _builder.append(_generateImageButtonFromCompositeDisplayElement, "");
                      _builder.newLineIfNotEmpty();
                    } else {
                      if ((displayElement instanceof DynamicList)) {
                        CharSequence _generateListView = this.smallUIElements.generateListView();
                        _builder.append(_generateListView, "");
                        _builder.newLineIfNotEmpty();
                      } else {
                        if ((displayElement instanceof LocationPicker)) {
                          String _name_5 = compositeDisplayElement.getName();
                          CharSequence _generateLocationPicker = this.smallUIElements.generateLocationPicker(_name_5, ((LocationPicker) displayElement));
                          _builder.append(_generateLocationPicker, "");
                          _builder.newLineIfNotEmpty();
                        } else {
                          if ((displayElement instanceof CompositeDisplayElement)) {
                            Object _generateCompositeDisplayElementLayoutCode = this.generateCompositeDisplayElementLayoutCode(((CompositeDisplayElement) displayElement));
                            _builder.append(_generateCompositeDisplayElementLayoutCode, "");
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
      }
    }
    return _builder;
  }
}
