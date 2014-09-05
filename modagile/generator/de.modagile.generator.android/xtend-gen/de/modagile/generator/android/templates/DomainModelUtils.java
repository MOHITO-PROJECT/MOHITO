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

import com.google.common.collect.Iterables;
import de.modagile.metamodel.app.ui.Button;
import de.modagile.metamodel.app.ui.CheckBox;
import de.modagile.metamodel.app.ui.CompositeDisplayElement;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.Label;
import de.modagile.metamodel.app.ui.LocationPicker;
import de.modagile.metamodel.app.ui.Screen;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class DomainModelUtils {
  /**
   * returns the list of buttons that is contained in a screen. includes the list of Buttons that are contained within a composite display element.
   */
  public List<Button> getButtons(final Screen screen) {
    ArrayList<Button> _arrayList = new ArrayList<Button>();
    List<Button> buttonList = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof Button));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,Button> _function_1 = new Function1<DisplayElement,Button>() {
        public Button apply(final DisplayElement d) {
          return ((Button) d);
        }
      };
    Iterable<Button> _map = IterableExtensions.<DisplayElement, Button>map(_filter, _function_1);
    Iterables.<Button>addAll(buttonList, _map);
    EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function_2 = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CompositeDisplayElement));
        }
      };
    Iterable<DisplayElement> _filter_1 = IterableExtensions.<DisplayElement>filter(_displayElements_1, _function_2);
    final Function1<DisplayElement,CompositeDisplayElement> _function_3 = new Function1<DisplayElement,CompositeDisplayElement>() {
        public CompositeDisplayElement apply(final DisplayElement c) {
          return ((CompositeDisplayElement) c);
        }
      };
    final Iterable<CompositeDisplayElement> cdeList = IterableExtensions.<DisplayElement, CompositeDisplayElement>map(_filter_1, _function_3);
    for (final CompositeDisplayElement cde : cdeList) {
      CompositeDisplayElementType _type = cde.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      final Function1<DisplayElement,Boolean> _function_4 = new Function1<DisplayElement,Boolean>() {
          public Boolean apply(final DisplayElement element) {
            return Boolean.valueOf((element instanceof Button));
          }
        };
      Iterable<DisplayElement> _filter_2 = IterableExtensions.<DisplayElement>filter(_containedDisplayElements, _function_4);
      final Function1<DisplayElement,Button> _function_5 = new Function1<DisplayElement,Button>() {
          public Button apply(final DisplayElement e) {
            return ((Button) e);
          }
        };
      Iterable<Button> _map_1 = IterableExtensions.<DisplayElement, Button>map(_filter_2, _function_5);
      Iterables.<Button>addAll(buttonList, _map_1);
    }
    return buttonList;
  }
  
  /**
   * returns the list of buttons that is contained in a screen. includes the list of Buttons that are contained within a composite display element.
   */
  public List<CheckBox> getCheckBoxes(final Screen screen) {
    ArrayList<CheckBox> _arrayList = new ArrayList<CheckBox>();
    List<CheckBox> checkBoxList = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CheckBox));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,CheckBox> _function_1 = new Function1<DisplayElement,CheckBox>() {
        public CheckBox apply(final DisplayElement d) {
          return ((CheckBox) d);
        }
      };
    Iterable<CheckBox> _map = IterableExtensions.<DisplayElement, CheckBox>map(_filter, _function_1);
    Iterables.<CheckBox>addAll(checkBoxList, _map);
    EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function_2 = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CompositeDisplayElement));
        }
      };
    Iterable<DisplayElement> _filter_1 = IterableExtensions.<DisplayElement>filter(_displayElements_1, _function_2);
    final Function1<DisplayElement,CompositeDisplayElement> _function_3 = new Function1<DisplayElement,CompositeDisplayElement>() {
        public CompositeDisplayElement apply(final DisplayElement c) {
          return ((CompositeDisplayElement) c);
        }
      };
    final Iterable<CompositeDisplayElement> cdeList = IterableExtensions.<DisplayElement, CompositeDisplayElement>map(_filter_1, _function_3);
    for (final CompositeDisplayElement cde : cdeList) {
      CompositeDisplayElementType _type = cde.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      final Function1<DisplayElement,Boolean> _function_4 = new Function1<DisplayElement,Boolean>() {
          public Boolean apply(final DisplayElement element) {
            return Boolean.valueOf((element instanceof CheckBox));
          }
        };
      Iterable<DisplayElement> _filter_2 = IterableExtensions.<DisplayElement>filter(_containedDisplayElements, _function_4);
      final Function1<DisplayElement,CheckBox> _function_5 = new Function1<DisplayElement,CheckBox>() {
          public CheckBox apply(final DisplayElement e) {
            return ((CheckBox) e);
          }
        };
      Iterable<CheckBox> _map_1 = IterableExtensions.<DisplayElement, CheckBox>map(_filter_2, _function_5);
      Iterables.<CheckBox>addAll(checkBoxList, _map_1);
    }
    return checkBoxList;
  }
  
  /**
   * returns the list of buttons that is contained in a screen. includes the list of Buttons that are contained within a composite display element.
   */
  public List<CheckBox> getDirectAssociatedCheckBoxes(final Screen screen) {
    ArrayList<CheckBox> _arrayList = new ArrayList<CheckBox>();
    List<CheckBox> checkBoxList = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CheckBox));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,CheckBox> _function_1 = new Function1<DisplayElement,CheckBox>() {
        public CheckBox apply(final DisplayElement d) {
          return ((CheckBox) d);
        }
      };
    Iterable<CheckBox> _map = IterableExtensions.<DisplayElement, CheckBox>map(_filter, _function_1);
    Iterables.<CheckBox>addAll(checkBoxList, _map);
    return checkBoxList;
  }
  
  /**
   * returns the list of image buttons that is contained in a screen. includes the list of ImageButtons that are contained within a composite display element.
   */
  public List<ImageButton> getImageButton(final Screen screen) {
    ArrayList<ImageButton> _arrayList = new ArrayList<ImageButton>();
    List<ImageButton> buttonList = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof ImageButton));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,ImageButton> _function_1 = new Function1<DisplayElement,ImageButton>() {
        public ImageButton apply(final DisplayElement d) {
          return ((ImageButton) d);
        }
      };
    Iterable<ImageButton> _map = IterableExtensions.<DisplayElement, ImageButton>map(_filter, _function_1);
    Iterables.<ImageButton>addAll(buttonList, _map);
    EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function_2 = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CompositeDisplayElement));
        }
      };
    Iterable<DisplayElement> _filter_1 = IterableExtensions.<DisplayElement>filter(_displayElements_1, _function_2);
    final Function1<DisplayElement,CompositeDisplayElement> _function_3 = new Function1<DisplayElement,CompositeDisplayElement>() {
        public CompositeDisplayElement apply(final DisplayElement c) {
          return ((CompositeDisplayElement) c);
        }
      };
    final Iterable<CompositeDisplayElement> cdeList = IterableExtensions.<DisplayElement, CompositeDisplayElement>map(_filter_1, _function_3);
    for (final CompositeDisplayElement cde : cdeList) {
      CompositeDisplayElementType _type = cde.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      final Function1<DisplayElement,Boolean> _function_4 = new Function1<DisplayElement,Boolean>() {
          public Boolean apply(final DisplayElement element) {
            return Boolean.valueOf((element instanceof ImageButton));
          }
        };
      Iterable<DisplayElement> _filter_2 = IterableExtensions.<DisplayElement>filter(_containedDisplayElements, _function_4);
      final Function1<DisplayElement,ImageButton> _function_5 = new Function1<DisplayElement,ImageButton>() {
          public ImageButton apply(final DisplayElement e) {
            return ((ImageButton) e);
          }
        };
      Iterable<ImageButton> _map_1 = IterableExtensions.<DisplayElement, ImageButton>map(_filter_2, _function_5);
      Iterables.<ImageButton>addAll(buttonList, _map_1);
    }
    return buttonList;
  }
  
  /**
   * returns the list of labels that is contained in a screen. includes the list of labels that are contained within a composite display element.
   */
  public List<Label> getLabels(final Screen screen) {
    ArrayList<Label> _arrayList = new ArrayList<Label>();
    List<Label> labelList = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof Label));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,Label> _function_1 = new Function1<DisplayElement,Label>() {
        public Label apply(final DisplayElement d) {
          return ((Label) d);
        }
      };
    Iterable<Label> _map = IterableExtensions.<DisplayElement, Label>map(_filter, _function_1);
    Iterables.<Label>addAll(labelList, _map);
    EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function_2 = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CompositeDisplayElement));
        }
      };
    Iterable<DisplayElement> _filter_1 = IterableExtensions.<DisplayElement>filter(_displayElements_1, _function_2);
    final Function1<DisplayElement,CompositeDisplayElement> _function_3 = new Function1<DisplayElement,CompositeDisplayElement>() {
        public CompositeDisplayElement apply(final DisplayElement c) {
          return ((CompositeDisplayElement) c);
        }
      };
    final Iterable<CompositeDisplayElement> cdeList = IterableExtensions.<DisplayElement, CompositeDisplayElement>map(_filter_1, _function_3);
    for (final CompositeDisplayElement cde : cdeList) {
      CompositeDisplayElementType _type = cde.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      final Function1<DisplayElement,Boolean> _function_4 = new Function1<DisplayElement,Boolean>() {
          public Boolean apply(final DisplayElement element) {
            return Boolean.valueOf((element instanceof Label));
          }
        };
      Iterable<DisplayElement> _filter_2 = IterableExtensions.<DisplayElement>filter(_containedDisplayElements, _function_4);
      final Function1<DisplayElement,Label> _function_5 = new Function1<DisplayElement,Label>() {
          public Label apply(final DisplayElement e) {
            return ((Label) e);
          }
        };
      Iterable<Label> _map_1 = IterableExtensions.<DisplayElement, Label>map(_filter_2, _function_5);
      Iterables.<Label>addAll(labelList, _map_1);
    }
    return labelList;
  }
  
  /**
   * returns the list of DynamicLists that is contained in a screen. includes the list of DynamicLists that are contained within a composite display element.
   */
  public List<DynamicList> getDynamicList(final Screen screen) {
    ArrayList<DynamicList> _arrayList = new ArrayList<DynamicList>();
    List<DynamicList> dynamicListList = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof DynamicList));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,DynamicList> _function_1 = new Function1<DisplayElement,DynamicList>() {
        public DynamicList apply(final DisplayElement d) {
          return ((DynamicList) d);
        }
      };
    Iterable<DynamicList> _map = IterableExtensions.<DisplayElement, DynamicList>map(_filter, _function_1);
    Iterables.<DynamicList>addAll(dynamicListList, _map);
    EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function_2 = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CompositeDisplayElement));
        }
      };
    Iterable<DisplayElement> _filter_1 = IterableExtensions.<DisplayElement>filter(_displayElements_1, _function_2);
    final Function1<DisplayElement,CompositeDisplayElement> _function_3 = new Function1<DisplayElement,CompositeDisplayElement>() {
        public CompositeDisplayElement apply(final DisplayElement c) {
          return ((CompositeDisplayElement) c);
        }
      };
    final Iterable<CompositeDisplayElement> cdeList = IterableExtensions.<DisplayElement, CompositeDisplayElement>map(_filter_1, _function_3);
    for (final CompositeDisplayElement cde : cdeList) {
      CompositeDisplayElementType _type = cde.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      final Function1<DisplayElement,Boolean> _function_4 = new Function1<DisplayElement,Boolean>() {
          public Boolean apply(final DisplayElement element) {
            return Boolean.valueOf((element instanceof DynamicList));
          }
        };
      Iterable<DisplayElement> _filter_2 = IterableExtensions.<DisplayElement>filter(_containedDisplayElements, _function_4);
      final Function1<DisplayElement,DynamicList> _function_5 = new Function1<DisplayElement,DynamicList>() {
          public DynamicList apply(final DisplayElement e) {
            return ((DynamicList) e);
          }
        };
      Iterable<DynamicList> _map_1 = IterableExtensions.<DisplayElement, DynamicList>map(_filter_2, _function_5);
      Iterables.<DynamicList>addAll(dynamicListList, _map_1);
    }
    return dynamicListList;
  }
  
  /**
   * returns the list of LocationPickers that is contained in a screen. includes the list of LocationPickers that are contained within a composite display element.
   */
  public List<LocationPicker> getLocationPicker(final Screen screen) {
    ArrayList<LocationPicker> _arrayList = new ArrayList<LocationPicker>();
    List<LocationPicker> dynamicListList = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof LocationPicker));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,LocationPicker> _function_1 = new Function1<DisplayElement,LocationPicker>() {
        public LocationPicker apply(final DisplayElement d) {
          return ((LocationPicker) d);
        }
      };
    Iterable<LocationPicker> _map = IterableExtensions.<DisplayElement, LocationPicker>map(_filter, _function_1);
    Iterables.<LocationPicker>addAll(dynamicListList, _map);
    EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function_2 = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CompositeDisplayElement));
        }
      };
    Iterable<DisplayElement> _filter_1 = IterableExtensions.<DisplayElement>filter(_displayElements_1, _function_2);
    final Function1<DisplayElement,CompositeDisplayElement> _function_3 = new Function1<DisplayElement,CompositeDisplayElement>() {
        public CompositeDisplayElement apply(final DisplayElement c) {
          return ((CompositeDisplayElement) c);
        }
      };
    final Iterable<CompositeDisplayElement> cdeList = IterableExtensions.<DisplayElement, CompositeDisplayElement>map(_filter_1, _function_3);
    for (final CompositeDisplayElement cde : cdeList) {
      CompositeDisplayElementType _type = cde.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      final Function1<DisplayElement,Boolean> _function_4 = new Function1<DisplayElement,Boolean>() {
          public Boolean apply(final DisplayElement element) {
            return Boolean.valueOf((element instanceof LocationPicker));
          }
        };
      Iterable<DisplayElement> _filter_2 = IterableExtensions.<DisplayElement>filter(_containedDisplayElements, _function_4);
      final Function1<DisplayElement,LocationPicker> _function_5 = new Function1<DisplayElement,LocationPicker>() {
          public LocationPicker apply(final DisplayElement e) {
            return ((LocationPicker) e);
          }
        };
      Iterable<LocationPicker> _map_1 = IterableExtensions.<DisplayElement, LocationPicker>map(_filter_2, _function_5);
      Iterables.<LocationPicker>addAll(dynamicListList, _map_1);
    }
    return dynamicListList;
  }
  
  /**
   * returns the list of Datepicker that is contained in a screen. includes the list of Datepicker that are contained within a composite display element.
   */
  public List<Datepicker> getDatepicker(final Screen screen) {
    ArrayList<Datepicker> _arrayList = new ArrayList<Datepicker>();
    List<Datepicker> dynamicListList = _arrayList;
    List<Datepicker> _directAssociatedDatepicker = this.getDirectAssociatedDatepicker(screen);
    dynamicListList.addAll(_directAssociatedDatepicker);
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CompositeDisplayElement));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,CompositeDisplayElement> _function_1 = new Function1<DisplayElement,CompositeDisplayElement>() {
        public CompositeDisplayElement apply(final DisplayElement c) {
          return ((CompositeDisplayElement) c);
        }
      };
    final Iterable<CompositeDisplayElement> cdeList = IterableExtensions.<DisplayElement, CompositeDisplayElement>map(_filter, _function_1);
    for (final CompositeDisplayElement cde : cdeList) {
      CompositeDisplayElementType _type = cde.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      final Function1<DisplayElement,Boolean> _function_2 = new Function1<DisplayElement,Boolean>() {
          public Boolean apply(final DisplayElement element) {
            return Boolean.valueOf((element instanceof Datepicker));
          }
        };
      Iterable<DisplayElement> _filter_1 = IterableExtensions.<DisplayElement>filter(_containedDisplayElements, _function_2);
      final Function1<DisplayElement,Datepicker> _function_3 = new Function1<DisplayElement,Datepicker>() {
          public Datepicker apply(final DisplayElement e) {
            return ((Datepicker) e);
          }
        };
      Iterable<Datepicker> _map = IterableExtensions.<DisplayElement, Datepicker>map(_filter_1, _function_3);
      Iterables.<Datepicker>addAll(dynamicListList, _map);
    }
    return dynamicListList;
  }
  
  /**
   * returns the list of Datepicker that is contained in a screen. includes the list of Datepicker that are contained within a composite display element.
   */
  public List<Datepicker> getDirectAssociatedDatepicker(final Screen screen) {
    ArrayList<Datepicker> _arrayList = new ArrayList<Datepicker>();
    List<Datepicker> dynamicListList = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof Datepicker));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements, _function);
    final Function1<DisplayElement,Datepicker> _function_1 = new Function1<DisplayElement,Datepicker>() {
        public Datepicker apply(final DisplayElement d) {
          return ((Datepicker) d);
        }
      };
    Iterable<Datepicker> _map = IterableExtensions.<DisplayElement, Datepicker>map(_filter, _function_1);
    Iterables.<Datepicker>addAll(dynamicListList, _map);
    return dynamicListList;
  }
  
  /**
   * returns the list of ALL DisplayElements that are contained in a screen. includes the list of DisplayElements that are contained within a composite display element.
   */
  public List<DisplayElement> getAllDisplayElements(final Screen screen) {
    ArrayList<DisplayElement> _arrayList = new ArrayList<DisplayElement>();
    List<DisplayElement> displayElements = _arrayList;
    EList<DisplayElement> _displayElements = screen.getDisplayElements();
    displayElements.addAll(_displayElements);
    EList<DisplayElement> _displayElements_1 = screen.getDisplayElements();
    final Function1<DisplayElement,Boolean> _function = new Function1<DisplayElement,Boolean>() {
        public Boolean apply(final DisplayElement element) {
          return Boolean.valueOf((element instanceof CompositeDisplayElement));
        }
      };
    Iterable<DisplayElement> _filter = IterableExtensions.<DisplayElement>filter(_displayElements_1, _function);
    final Function1<DisplayElement,CompositeDisplayElement> _function_1 = new Function1<DisplayElement,CompositeDisplayElement>() {
        public CompositeDisplayElement apply(final DisplayElement c) {
          return ((CompositeDisplayElement) c);
        }
      };
    final Iterable<CompositeDisplayElement> cdeList = IterableExtensions.<DisplayElement, CompositeDisplayElement>map(_filter, _function_1);
    for (final CompositeDisplayElement cde : cdeList) {
      CompositeDisplayElementType _type = cde.getType();
      EList<DisplayElement> _containedDisplayElements = _type.getContainedDisplayElements();
      displayElements.addAll(_containedDisplayElements);
    }
    return displayElements;
  }
}
