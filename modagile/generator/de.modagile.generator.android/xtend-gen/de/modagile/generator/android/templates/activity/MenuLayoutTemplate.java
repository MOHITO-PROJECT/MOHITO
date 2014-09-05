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
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.Image;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.Input;
import de.modagile.metamodel.app.ui.Label;
import de.modagile.metamodel.app.ui.LocationPicker;
import de.modagile.metamodel.app.ui.MenuBar;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import de.modagile.metamodel.app.ui.TextContaining;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * The existing ocl constraint in modagile.ecore allows only Images, Buttons, Labels, ImageButtons and Checkboxes in the Menubar.
 * Grouping is not supported and also not planned to be in future.
 */
@SuppressWarnings("all")
public class MenuLayoutTemplate {
  public void generateMenuLayouts(final IFileSystemAccess fsa, final String outputConfiguration, final StoryBoard storyBoard) {
    EList<Screen> _screens = storyBoard.getScreens();
    for (final Screen screen : _screens) {
      MenuBar _menuBar = screen.getMenuBar();
      boolean _notEquals = (!Objects.equal(_menuBar, null));
      if (_notEquals) {
        String _name = screen.getName();
        String _lowerCase = _name.toLowerCase();
        String _plus = ("menu/" + _lowerCase);
        String _plus_1 = (_plus + "_menu.xml");
        MenuBar _menuBar_1 = screen.getMenuBar();
        CharSequence _generateMenuForScreen = this.generateMenuForScreen(_menuBar_1);
        fsa.generateFile(_plus_1, outputConfiguration, _generateMenuForScreen);
      }
    }
  }
  
  private CharSequence generateMenuForScreen(final MenuBar menuBar) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<menu xmlns:android=\"http://schemas.android.com/apk/res/android\">");
    _builder.newLine();
    {
      EList<DisplayElement> _menuBarElements = menuBar.getMenuBarElements();
      for(final DisplayElement displayElement : _menuBarElements) {
        CharSequence _generateDisplayElementMenuItem = this.generateDisplayElementMenuItem(displayElement, menuBar, null);
        _builder.append(_generateDisplayElementMenuItem, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</menu>");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateDisplayElementMenuItem(final DisplayElement menuItemDisplayElement, final MenuBar menuBar, final CompositeDisplayElement compositeDisplayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _or = false;
      boolean _or_1 = false;
      boolean _or_2 = false;
      boolean _or_3 = false;
      if ((menuItemDisplayElement instanceof Button)) {
        _or_3 = true;
      } else {
        _or_3 = ((menuItemDisplayElement instanceof Button) || (menuItemDisplayElement instanceof Datepicker));
      }
      if (_or_3) {
        _or_2 = true;
      } else {
        _or_2 = (_or_3 || (menuItemDisplayElement instanceof ImageButton));
      }
      if (_or_2) {
        _or_1 = true;
      } else {
        _or_1 = (_or_2 || (menuItemDisplayElement instanceof DynamicList));
      }
      if (_or_1) {
        _or = true;
      } else {
        _or = (_or_1 || (menuItemDisplayElement instanceof LocationPicker));
      }
      if (_or) {
        CharSequence _generateItemWithAction = this.generateItemWithAction(menuItemDisplayElement, menuBar);
        _builder.append(_generateItemWithAction, "");
        _builder.newLineIfNotEmpty();
      } else {
        boolean _or_4 = false;
        boolean _or_5 = false;
        if ((menuItemDisplayElement instanceof Label)) {
          _or_5 = true;
        } else {
          _or_5 = ((menuItemDisplayElement instanceof Label) || (menuItemDisplayElement instanceof Input));
        }
        if (_or_5) {
          _or_4 = true;
        } else {
          _or_4 = (_or_5 || (menuItemDisplayElement instanceof Image));
        }
        if (_or_4) {
          CharSequence _generateItemWithoutAction = this.generateItemWithoutAction(menuItemDisplayElement, menuBar);
          _builder.append(_generateItemWithoutAction, "");
          _builder.newLineIfNotEmpty();
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateItemWithAction(final DisplayElement displayElement, final MenuBar menuBar) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateItemID = this.generateItemID(displayElement, menuBar);
    _builder.append(_generateItemID, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateItemTitle = this.generateItemTitle(displayElement);
    _builder.append(_generateItemTitle, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDrawableIfNeeded = this.generateDrawableIfNeeded(displayElement);
    _builder.append(_generateDrawableIfNeeded, "");
    _builder.newLineIfNotEmpty();
    _builder.append("android:showAsAction=\"ifRoom|collapseActionView\"");
    _builder.newLine();
    CharSequence _generateActionView = this.generateActionView(displayElement);
    _builder.append(_generateActionView, "");
    _builder.append("/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateItemWithoutAction(final DisplayElement displayElement, final MenuBar menuBar) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateItemID = this.generateItemID(displayElement, menuBar);
    _builder.append(_generateItemID, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateItemTitle = this.generateItemTitle(displayElement);
    _builder.append(_generateItemTitle, "");
    _builder.newLineIfNotEmpty();
    CharSequence _generateDrawableIfNeeded = this.generateDrawableIfNeeded(displayElement);
    _builder.append(_generateDrawableIfNeeded, "");
    _builder.append("/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateItemTitle(final DisplayElement displayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((displayElement instanceof TextContaining)) {
        {
          if ((displayElement instanceof Label)) {
            _builder.append("android:title=\"@string/textView");
            String _name = displayElement.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name);
            _builder.append(_firstUpper, "");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
          } else {
            if ((displayElement instanceof Button)) {
              _builder.append("android:title=\"@string/button");
              String _name_1 = displayElement.getName();
              String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
              _builder.append(_firstUpper_1, "");
              _builder.append("\"");
              _builder.newLineIfNotEmpty();
            } else {
              if ((displayElement instanceof Image)) {
                _builder.append("android:title=\"@string/img");
                String _name_2 = displayElement.getName();
                String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
                _builder.append(_firstUpper_2, "");
                _builder.append("\"");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  private CharSequence generateDrawableIfNeeded(final DisplayElement displayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _or = false;
      if ((displayElement instanceof ImageButton)) {
        _or = true;
      } else {
        _or = ((displayElement instanceof ImageButton) || (displayElement instanceof Image));
      }
      if (_or) {
        _builder.append("android:icon=\"@drawable/");
        String _name = displayElement.getName();
        String _lowerCase = _name.toLowerCase();
        _builder.append(_lowerCase, "");
        _builder.append("\"");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  private CharSequence generateItemID(final DisplayElement displayElement, final MenuBar menuBar) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<item android:id=\"@+id/menu_item_");
    Screen _screen = menuBar.getScreen();
    String _name = _screen.getName();
    _builder.append(_name, "");
    _builder.append("_");
    String _name_1 = displayElement.getName();
    String _lowerCase = _name_1.toLowerCase();
    _builder.append(_lowerCase, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence generateActionView(final DisplayElement displayElement) {
    StringConcatenation _builder = new StringConcatenation();
    {
      if ((displayElement instanceof ImageButton)) {
        _builder.append("android:actionViewClass=\"android.widget.ImageButton\"");
        _builder.newLine();
      } else {
        if ((displayElement instanceof Button)) {
          _builder.append("android:actionViewClass=\"android.widget.Button\"");
          _builder.newLine();
        }
      }
    }
    return _builder;
  }
}
