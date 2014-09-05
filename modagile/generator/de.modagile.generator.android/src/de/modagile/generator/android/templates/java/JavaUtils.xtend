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
package de.modagile.generator.android.templates.java

import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.domain.BindingRepository
import de.modagile.metamodel.app.domain.ListBinding
import de.modagile.metamodel.app.domain.StringBinding
import de.modagile.metamodel.app.ui.Button
import de.modagile.metamodel.app.ui.CheckBox
import de.modagile.metamodel.app.ui.CompositeDisplayElementType
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.ui.DisplayElement
import de.modagile.metamodel.app.ui.DynamicList
import de.modagile.metamodel.app.ui.Image
import de.modagile.metamodel.app.ui.ImageButton
import de.modagile.metamodel.app.ui.Input
import de.modagile.metamodel.app.ui.Label
import java.util.List
import javax.swing.text.html.ListView
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import java.util.Set
import java.util.HashSet
import de.modagile.metamodel.app.domain.PrimitiveBinding
import de.modagile.metamodel.app.ui.Screen
import de.modagile.metamodel.app.domain.BooleanBinding
import de.modagile.metamodel.app.ui.InputType
import java.util.ArrayList

/**
 * Modagile specific java generation utilities.
 */
class JavaUtils extends info.multiplatform.generator.java.templates.JavaUtils {
	
	def public List<String> getAllDomainEntityNames(MobileApp app) {
		return getAllDomainEntityNames(app.domainPackage);
	}  
	
	def public List<EClass> getAllDomainEntities(MobileApp app) {
		return getAllDomainEntities(app.domainPackage);
	} 
	
	def public List<EReference> getMappingReferences(MobileApp app) {
		return getMappingReferences(app.domainPackage);
	}

    /**
     * Returns the domain entity for a given list in a screen that is binded in a listbinding element in the binding repository
     */
    def public EClass getDomainEntityNameForDynamicList(MobileApp app, DynamicList list) {
        for (ListBinding listBinding : app.bindingRepository.bindings.filter(b | b instanceof ListBinding).map( binding | binding as ListBinding)) {
            System::out.println(listBinding.domainReference.EReferenceType)
            return listBinding.domainReference.EReferenceType;
        }
    }
	
	def public DisplayElement getFirstBoundUiElementForDynamicList(MobileApp app, DynamicList list) {
		for (CompositeDisplayElementType displayElementTypes : list.displayedElements) {
			for (DisplayElement displayElement : displayElementTypes.containedDisplayElements) {
				for (StringBinding stringBinding : app.bindingRepository.bindings.filter(bindings | bindings instanceof StringBinding).map(bindings | bindings as StringBinding)) {
					if (displayElement.equals(stringBinding.uiElement as DisplayElement)) {
					   return displayElement as DisplayElement;
					}
				}
			}
		}
		return null;
	}
	
	def public ListBinding getListBindingForDisplayElement(MobileApp m, DisplayElement displayElement){
	   if(m.bindingRepository != null) {
        for(ListBinding listBinding : m.bindingRepository.bindings.filter(binding | binding instanceof ListBinding).map(binding | binding as ListBinding)) {
            if(listBinding.listElement.equals(displayElement)) {
                return listBinding;
            }
        }
       }
	}
	
	def public DisplayElement getRelatedStringBoundDisplayElementForListAttribute(BindingRepository bindingRepository, EAttribute entityAttributeInList, ListBinding listBinding){
	    for(StringBinding stringBinding : bindingRepository.bindings.filter(binding | binding instanceof StringBinding).map(binding | binding as StringBinding)) {
	       var DisplayElement displayElement = stringBinding.uiElement as DisplayElement;
	       if(stringBinding.attribute.equals(entityAttributeInList) && checkIfUiElementIsInList(displayElement, listBinding.listElement) ){
	           return displayElement;
	       }
	    }
        return null;
	}
	
    def public Set<EClass> collectEntitiesForScreenByPrimitiveBindings(Screen screen, BindingRepository bindingRepository){
	   var Set<EClass> entitiesInPrimitiveBindings = new HashSet<EClass>();
	   if (bindingRepository != null && bindingRepository.bindings != null){
	       for (PrimitiveBinding primitiveBinding : bindingRepository.bindings.filter(binding | binding instanceof PrimitiveBinding).map(binding | binding as PrimitiveBinding)) {
                if( primitiveBinding instanceof StringBinding && screen.displayElements.contains((primitiveBinding as StringBinding).uiElement as DisplayElement)) {
                    entitiesInPrimitiveBindings.add(( primitiveBinding as StringBinding).attribute.EContainingClass);
                } else if (primitiveBinding instanceof BooleanBinding && screen.displayElements.contains((primitiveBinding as BooleanBinding).checkBox as DisplayElement)){
                    entitiesInPrimitiveBindings.add((primitiveBinding as BooleanBinding).attribute.EContainingClass);
                }
           }    
	   }
       return entitiesInPrimitiveBindings;
    }
	
	def private boolean checkIfUiElementIsInList(DisplayElement displayElement, DynamicList list){
	   for(CompositeDisplayElementType cdeType : list.displayedElements){
    	   for(DisplayElement displayElementInCDE : cdeType.containedDisplayElements){
	           if(displayElementInCDE.equals(displayElement)){
	               return true;
	           }
	       }
	   }
	   return false;
	}
	
	def public String getDisplayElementPrefix(DisplayElement displayElement){
        if (displayElement instanceof Label) {
            return "textView";
        } else if (displayElement instanceof ImageButton) {
             return "imageButton";
        } else if (displayElement instanceof Image) {
             return "image";
        } else if (displayElement instanceof CheckBox) {
             return "checkBox";
        } else if( displayElement instanceof Button) {
            return "button";
        } else if(displayElement instanceof Input) {
             return "editText";
        } else if (displayElement instanceof Datepicker) {
             return "datePicker";
        } else if (displayElement instanceof ListView){
            return "listView";
        }
    }
    
    def public getDisplayElementType(DisplayElement displayElement){
       switch(displayElement.eClass.name){
          case "Button": "Button"
          case "Image": "ImageView"
          case "Input": "EditText"
          case "Label": "TextView"
          case "CheckBox": "CheckBox"
          case "DynamicList": "ListView"
          case "LocationPicker": "MapView"
          case "Datepicker": "Calendar"
          case "ImageButton": "ImageButton"
       }
    }
    
    def public getEditTextInputType(Input input) {
    	switch(input.inputType){
    		case InputType::TEXT : "text"
    		case InputType::NUMERICS : "number"
    		case InputType::MAIL : "textEmailAddress"
    		case InputType::WEB_ADDRESS : "textUri"
    		case InputType::CUSTOM : "none"
    	}
    }
    
     def public boolean checkIfDisplayElementHasAction(DisplayElement displayElement){
        //A ImageButton is also an instance of a Button (Only Elements that are valid in this context are checked.
        return displayElement instanceof Button || displayElement instanceof CheckBox;
    }

    /**
     * Returns a list of all Images contained in the MobileApp
     */
	def public List<Image> getAllImages(MobileApp app) {
		var List<Image> allImages = new ArrayList<Image>();
		if (app.storyBoard != null) {
			for (Screen screen : app.storyBoard.screens) {
				for (DisplayElement element : screen.displayElements) {
					if (element instanceof Image) {
						allImages.add(element as Image);
					}
				}
				if (screen.menuBar != null) {
					for (DisplayElement element : screen.menuBar.menuBarElements) {
						if (element instanceof Image) {
							allImages.add(element as Image);
						}
					}
				}
			}
		}
		if (app.compositeDisplayElementTypeRepository != null) {
			for (CompositeDisplayElementType cdet : app.compositeDisplayElementTypeRepository.compositeDisplayElementTypes) {
				for (DisplayElement element : cdet.containedDisplayElements) {
					if (element instanceof Image) {
						allImages.add(element as Image);
					}
				}			
			}
		}
		return allImages;
	}
	
}