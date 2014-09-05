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
package de.modagile.generator.android.templates.java;

import com.google.common.base.Objects;
import de.modagile.metamodel.app.CompositeDisplayElementTypeRepository;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.domain.BooleanBinding;
import de.modagile.metamodel.app.domain.DomainBinding;
import de.modagile.metamodel.app.domain.ListBinding;
import de.modagile.metamodel.app.domain.PrimitiveBinding;
import de.modagile.metamodel.app.domain.StringBinding;
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CheckBox;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.Image;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.Input;
import de.modagile.metamodel.app.ui.InputType;
import de.modagile.metamodel.app.ui.Label;
import de.modagile.metamodel.app.ui.MenuBar;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import de.modagile.metamodel.app.ui.TextContaining;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.html.ListView;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Modagile specific java generation utilities.
 */
@SuppressWarnings("all")
public class JavaUtils extends info.multiplatform.generator.java.templates.JavaUtils {
  public List<String> getAllDomainEntityNames(final MobileApp app) {
    EPackage _domainPackage = app.getDomainPackage();
    return this.getAllDomainEntityNames(_domainPackage);
  }
  
  public List<EClass> getAllDomainEntities(final MobileApp app) {
    EPackage _domainPackage = app.getDomainPackage();
    return this.getAllDomainEntities(_domainPackage);
  }
  
  public List<EReference> getMappingReferences(final MobileApp app) {
    EPackage _domainPackage = app.getDomainPackage();
    return this.getMappingReferences(_domainPackage);
  }
  
  /**
   * Returns the domain entity for a given list in a screen that is binded in a listbinding element in the binding repository
   */
  public EClass getDomainEntityNameForDynamicList(final MobileApp app, final DynamicList list) {
    BindingRepository _bindingRepository = app.getBindingRepository();
    EList<DomainBinding> _bindings = _bindingRepository.getBindings();
    final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
        public Boolean apply(final DomainBinding b) {
          return Boolean.valueOf((b instanceof ListBinding));
        }
      };
    Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings, _function);
    final Function1<DomainBinding,ListBinding> _function_1 = new Function1<DomainBinding,ListBinding>() {
        public ListBinding apply(final DomainBinding binding) {
          return ((ListBinding) binding);
        }
      };
    Iterable<ListBinding> _map = IterableExtensions.<DomainBinding, ListBinding>map(_filter, _function_1);
    for (final ListBinding listBinding : _map) {
      {
        EReference _domainReference = listBinding.getDomainReference();
        EClass _eReferenceType = _domainReference.getEReferenceType();
        System.out.println(_eReferenceType);
        EReference _domainReference_1 = listBinding.getDomainReference();
        return _domainReference_1.getEReferenceType();
      }
    }
    return null;
  }
  
  public DisplayElement getFirstBoundUiElementForDynamicList(final MobileApp app, final DynamicList list) {
    EList<CompositeDisplayElementType> _displayedElements = list.getDisplayedElements();
    for (final CompositeDisplayElementType displayElementTypes : _displayedElements) {
      EList<DisplayElement> _containedDisplayElements = displayElementTypes.getContainedDisplayElements();
      for (final DisplayElement displayElement : _containedDisplayElements) {
        BindingRepository _bindingRepository = app.getBindingRepository();
        EList<DomainBinding> _bindings = _bindingRepository.getBindings();
        final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
            public Boolean apply(final DomainBinding bindings) {
              return Boolean.valueOf((bindings instanceof StringBinding));
            }
          };
        Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings, _function);
        final Function1<DomainBinding,StringBinding> _function_1 = new Function1<DomainBinding,StringBinding>() {
            public StringBinding apply(final DomainBinding bindings) {
              return ((StringBinding) bindings);
            }
          };
        Iterable<StringBinding> _map = IterableExtensions.<DomainBinding, StringBinding>map(_filter, _function_1);
        for (final StringBinding stringBinding : _map) {
          TextContaining _uiElement = stringBinding.getUiElement();
          boolean _equals = displayElement.equals(((DisplayElement) _uiElement));
          if (_equals) {
            return ((DisplayElement) displayElement);
          }
        }
      }
    }
    return null;
  }
  
  public ListBinding getListBindingForDisplayElement(final MobileApp m, final DisplayElement displayElement) {
    ListBinding _xifexpression = null;
    BindingRepository _bindingRepository = m.getBindingRepository();
    boolean _notEquals = (!Objects.equal(_bindingRepository, null));
    if (_notEquals) {
      BindingRepository _bindingRepository_1 = m.getBindingRepository();
      EList<DomainBinding> _bindings = _bindingRepository_1.getBindings();
      final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
          public Boolean apply(final DomainBinding binding) {
            return Boolean.valueOf((binding instanceof ListBinding));
          }
        };
      Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings, _function);
      final Function1<DomainBinding,ListBinding> _function_1 = new Function1<DomainBinding,ListBinding>() {
          public ListBinding apply(final DomainBinding binding) {
            return ((ListBinding) binding);
          }
        };
      Iterable<ListBinding> _map = IterableExtensions.<DomainBinding, ListBinding>map(_filter, _function_1);
      for (final ListBinding listBinding : _map) {
        DynamicList _listElement = listBinding.getListElement();
        boolean _equals = _listElement.equals(displayElement);
        if (_equals) {
          return listBinding;
        }
      }
    }
    return _xifexpression;
  }
  
  public DisplayElement getRelatedStringBoundDisplayElementForListAttribute(final BindingRepository bindingRepository, final EAttribute entityAttributeInList, final ListBinding listBinding) {
    EList<DomainBinding> _bindings = bindingRepository.getBindings();
    final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
        public Boolean apply(final DomainBinding binding) {
          return Boolean.valueOf((binding instanceof StringBinding));
        }
      };
    Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings, _function);
    final Function1<DomainBinding,StringBinding> _function_1 = new Function1<DomainBinding,StringBinding>() {
        public StringBinding apply(final DomainBinding binding) {
          return ((StringBinding) binding);
        }
      };
    Iterable<StringBinding> _map = IterableExtensions.<DomainBinding, StringBinding>map(_filter, _function_1);
    for (final StringBinding stringBinding : _map) {
      {
        TextContaining _uiElement = stringBinding.getUiElement();
        DisplayElement displayElement = ((DisplayElement) _uiElement);
        boolean _and = false;
        EAttribute _attribute = stringBinding.getAttribute();
        boolean _equals = _attribute.equals(entityAttributeInList);
        if (!_equals) {
          _and = false;
        } else {
          DynamicList _listElement = listBinding.getListElement();
          boolean _checkIfUiElementIsInList = this.checkIfUiElementIsInList(displayElement, _listElement);
          _and = (_equals && _checkIfUiElementIsInList);
        }
        if (_and) {
          return displayElement;
        }
      }
    }
    return null;
  }
  
  public Set<EClass> collectEntitiesForScreenByPrimitiveBindings(final Screen screen, final BindingRepository bindingRepository) {
    HashSet<EClass> _hashSet = new HashSet<EClass>();
    Set<EClass> entitiesInPrimitiveBindings = _hashSet;
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(bindingRepository, null));
    if (!_notEquals) {
      _and = false;
    } else {
      EList<DomainBinding> _bindings = bindingRepository.getBindings();
      boolean _notEquals_1 = (!Objects.equal(_bindings, null));
      _and = (_notEquals && _notEquals_1);
    }
    if (_and) {
      EList<DomainBinding> _bindings_1 = bindingRepository.getBindings();
      final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
          public Boolean apply(final DomainBinding binding) {
            return Boolean.valueOf((binding instanceof PrimitiveBinding));
          }
        };
      Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings_1, _function);
      final Function1<DomainBinding,PrimitiveBinding> _function_1 = new Function1<DomainBinding,PrimitiveBinding>() {
          public PrimitiveBinding apply(final DomainBinding binding) {
            return ((PrimitiveBinding) binding);
          }
        };
      Iterable<PrimitiveBinding> _map = IterableExtensions.<DomainBinding, PrimitiveBinding>map(_filter, _function_1);
      for (final PrimitiveBinding primitiveBinding : _map) {
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
          EAttribute _attribute = ((StringBinding) primitiveBinding).getAttribute();
          EClass _eContainingClass = _attribute.getEContainingClass();
          entitiesInPrimitiveBindings.add(_eContainingClass);
        } else {
          boolean _and_2 = false;
          if (!(primitiveBinding instanceof BooleanBinding)) {
            _and_2 = false;
          } else {
            EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
            CheckBox _checkBox = ((BooleanBinding) primitiveBinding).getCheckBox();
            boolean _contains_1 = _displayElements_1.contains(((DisplayElement) _checkBox));
            _and_2 = ((primitiveBinding instanceof BooleanBinding) && _contains_1);
          }
          if (_and_2) {
            EAttribute _attribute_1 = ((BooleanBinding) primitiveBinding).getAttribute();
            EClass _eContainingClass_1 = _attribute_1.getEContainingClass();
            entitiesInPrimitiveBindings.add(_eContainingClass_1);
          }
        }
      }
    }
    return entitiesInPrimitiveBindings;
  }
  
  private boolean checkIfUiElementIsInList(final DisplayElement displayElement, final DynamicList list) {
    EList<CompositeDisplayElementType> _displayedElements = list.getDisplayedElements();
    for (final CompositeDisplayElementType cdeType : _displayedElements) {
      EList<DisplayElement> _containedDisplayElements = cdeType.getContainedDisplayElements();
      for (final DisplayElement displayElementInCDE : _containedDisplayElements) {
        boolean _equals = displayElementInCDE.equals(displayElement);
        if (_equals) {
          return true;
        }
      }
    }
    return false;
  }
  
  public String getDisplayElementPrefix(final DisplayElement displayElement) {
    String _xifexpression = null;
    if ((displayElement instanceof Label)) {
      return "textView";
    } else {
      String _xifexpression_1 = null;
      if ((displayElement instanceof ImageButton)) {
        return "imageButton";
      } else {
        String _xifexpression_2 = null;
        if ((displayElement instanceof Image)) {
          return "image";
        } else {
          String _xifexpression_3 = null;
          if ((displayElement instanceof CheckBox)) {
            return "checkBox";
          } else {
            String _xifexpression_4 = null;
            if ((displayElement instanceof Button)) {
              return "button";
            } else {
              String _xifexpression_5 = null;
              if ((displayElement instanceof Input)) {
                return "editText";
              } else {
                String _xifexpression_6 = null;
                if ((displayElement instanceof Datepicker)) {
                  return "datePicker";
                } else {
                  String _xifexpression_7 = null;
                  if ((displayElement instanceof ListView)) {
                    return "listView";
                  }
                  _xifexpression_6 = _xifexpression_7;
                }
                _xifexpression_5 = _xifexpression_6;
              }
              _xifexpression_4 = _xifexpression_5;
            }
            _xifexpression_3 = _xifexpression_4;
          }
          _xifexpression_2 = _xifexpression_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public String getDisplayElementType(final DisplayElement displayElement) {
    String _switchResult = null;
    EClass _eClass = displayElement.eClass();
    String _name = _eClass.getName();
    final String _switchValue = _name;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(_switchValue,"Button")) {
        _matched=true;
        _switchResult = "Button";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"Image")) {
        _matched=true;
        _switchResult = "ImageView";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"Input")) {
        _matched=true;
        _switchResult = "EditText";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"Label")) {
        _matched=true;
        _switchResult = "TextView";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"CheckBox")) {
        _matched=true;
        _switchResult = "CheckBox";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"DynamicList")) {
        _matched=true;
        _switchResult = "ListView";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"LocationPicker")) {
        _matched=true;
        _switchResult = "MapView";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"Datepicker")) {
        _matched=true;
        _switchResult = "Calendar";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,"ImageButton")) {
        _matched=true;
        _switchResult = "ImageButton";
      }
    }
    return _switchResult;
  }
  
  public String getEditTextInputType(final Input input) {
    String _switchResult = null;
    InputType _inputType = input.getInputType();
    final InputType _switchValue = _inputType;
    boolean _matched = false;
    if (!_matched) {
      if (Objects.equal(_switchValue,InputType.TEXT)) {
        _matched=true;
        _switchResult = "text";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,InputType.NUMERICS)) {
        _matched=true;
        _switchResult = "number";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,InputType.MAIL)) {
        _matched=true;
        _switchResult = "textEmailAddress";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,InputType.WEB_ADDRESS)) {
        _matched=true;
        _switchResult = "textUri";
      }
    }
    if (!_matched) {
      if (Objects.equal(_switchValue,InputType.CUSTOM)) {
        _matched=true;
        _switchResult = "none";
      }
    }
    return _switchResult;
  }
  
  public boolean checkIfDisplayElementHasAction(final DisplayElement displayElement) {
    boolean _or = false;
    if ((displayElement instanceof Button)) {
      _or = true;
    } else {
      _or = ((displayElement instanceof Button) || (displayElement instanceof CheckBox));
    }
    return _or;
  }
  
  /**
   * Returns a list of all Images contained in the MobileApp
   */
  public List<Image> getAllImages(final MobileApp app) {
    ArrayList<Image> _arrayList = new ArrayList<Image>();
    List<Image> allImages = _arrayList;
    StoryBoard _storyBoard = app.getStoryBoard();
    boolean _notEquals = (!Objects.equal(_storyBoard, null));
    if (_notEquals) {
      StoryBoard _storyBoard_1 = app.getStoryBoard();
      EList<Screen> _screens = _storyBoard_1.getScreens();
      for (final Screen screen : _screens) {
        {
          EList<DisplayElement> _displayElements = screen.getDisplayElements();
          for (final DisplayElement element : _displayElements) {
            if ((element instanceof Image)) {
              allImages.add(((Image) element));
            }
          }
          MenuBar _menuBar = screen.getMenuBar();
          boolean _notEquals_1 = (!Objects.equal(_menuBar, null));
          if (_notEquals_1) {
            MenuBar _menuBar_1 = screen.getMenuBar();
            EList<DisplayElement> _menuBarElements = _menuBar_1.getMenuBarElements();
            for (final DisplayElement element_1 : _menuBarElements) {
              if ((element_1 instanceof Image)) {
                allImages.add(((Image) element_1));
              }
            }
          }
        }
      }
    }
    CompositeDisplayElementTypeRepository _compositeDisplayElementTypeRepository = app.getCompositeDisplayElementTypeRepository();
    boolean _notEquals_1 = (!Objects.equal(_compositeDisplayElementTypeRepository, null));
    if (_notEquals_1) {
      CompositeDisplayElementTypeRepository _compositeDisplayElementTypeRepository_1 = app.getCompositeDisplayElementTypeRepository();
      EList<CompositeDisplayElementType> _compositeDisplayElementTypes = _compositeDisplayElementTypeRepository_1.getCompositeDisplayElementTypes();
      for (final CompositeDisplayElementType cdet : _compositeDisplayElementTypes) {
        EList<DisplayElement> _containedDisplayElements = cdet.getContainedDisplayElements();
        for (final DisplayElement element : _containedDisplayElements) {
          if ((element instanceof Image)) {
            allImages.add(((Image) element));
          }
        }
      }
    }
    return allImages;
  }
}
