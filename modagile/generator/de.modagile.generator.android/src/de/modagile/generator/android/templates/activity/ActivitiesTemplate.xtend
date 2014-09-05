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
package de.modagile.generator.android.templates.activity

import com.google.inject.Inject
import de.modagile.generator.android.templates.DomainModelUtils
import de.modagile.generator.android.templates.ModagileFolderConstants
import de.modagile.generator.android.templates.adapter.BindingUtils
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.domain.DomainBinding
import de.modagile.metamodel.app.domain.ListBinding
import de.modagile.metamodel.app.domain.PrimitiveBinding
import de.modagile.metamodel.app.ui.Button
import de.modagile.metamodel.app.ui.CheckBox
import de.modagile.metamodel.app.ui.CompositeDisplayElement
import de.modagile.metamodel.app.ui.CompositeDisplayElementType
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.ui.DisplayElement
import de.modagile.metamodel.app.ui.DynamicList
import de.modagile.metamodel.app.ui.Flow
import de.modagile.metamodel.app.ui.ImageButton
import de.modagile.metamodel.app.ui.Input
import de.modagile.metamodel.app.ui.Label
import de.modagile.metamodel.app.ui.LocationPicker
import de.modagile.metamodel.app.ui.Screen
import info.multiplatform.generator.java.helper.Triple
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.metamodel.app.domain.StringBinding
import de.modagile.metamodel.app.domain.BooleanBinding
import de.modagile.metamodel.app.domain.ComplexBinding
import de.modagile.metamodel.app.domain.SelectionBinding

/**
 * Contains activity specific generator methods 
 */
class ActivitiesTemplate {
	
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	@Inject extension DatepickerAdditionsTemplate datepickerAdditions
	@Inject extension DomainModelUtils domainModelUtils
	@Inject extension BindingUtils bindingUtils
	
	def generateActivities( IFileSystemAccess fsa, Screen activity , MobileApp m, PackageInfo packageInfo) { 
 		fsa.generateFile(packageInfo.packageDir+"activity/"+activity.name.toFirstUpper + "Activity.java", ModagileFolderConstants::SRC_GEN, activity.generateActivityCode(packageInfo.packageName+".activity", activity.displayElements,  m, packageInfo))
 	} 
 	
 	 def private generateActivityCode(Screen screen, String packageName,  List<DisplayElement> displayElements, MobileApp mobileApp, PackageInfo packageInfo) {
  		var Set<String> imports  = new HashSet<String>();
 		getAllImports(screen, packageInfo, mobileApp, imports);
 		var String inheritance = "SherlockActivity";
		if (screen.dynamicList.size > 0) {
			inheritance = "SherlockListActivity";
		} else if (screen.locationPicker.size > 0 ) {
			inheritance = "MapActivity";
		}
 		'''
 		«generateClass(packageName, screen, imports, inheritance, mobileApp)»
 		'''
 	}
 	
 	def getAllImports(Screen screen, PackageInfo packageInfo, MobileApp m, Set<String> imports) {
 	    screen.buttons
 		getGeneralImports(screen, packageInfo, m, imports);
 		getButtonImportsIfNeeded(screen, imports, m, packageInfo);
 		getCheckBoxImportsIfNeeded(screen, imports, m , packageInfo);
 		getImageButtonImportsIfNeeded(screen, imports);
 		getLabelImportsIfNeeded(screen.labels, imports);
 		getDatepickerImportsIfNeeded(screen.datepicker, imports);
 		getLabelAndInputImportsIfNeeded(screen.displayElements, screen.labels, imports);
 		getMenuBarImportsIfNeeded(screen, imports);
 		getDynamicListImportsIfNeeded(screen.dynamicList, imports, m,  packageInfo);
 		if(m.bindingRepository != null){
 	  	   getDomainImports(m.bindingRepository.bindings, imports, packageInfo);
 		}
 		getLocationPickerImportsIfNeeeded(screen.locationPicker, screen, imports);
 	}
 	
 	def private getGeneralImports(Screen activity, PackageInfo packageInfo, MobileApp mobileApp, Set<String> imports){
 		imports.add(packageInfo.packageName + ".R");
 		imports.add(packageInfo.packageName + ".constants.DBConstants");
 		for (clazz : mobileApp.domainPackage.EClassifiers.filter(c | c instanceof EClass)) {
	 		imports.add(packageInfo.packageName + ".constants."+ clazz.name + "DBConstants");
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
	   	imports.add("com.actionbarsherlock.app.SherlockFragmentActivity");
 	}
 	
 	def private getLocationPickerImportsIfNeeeded(List<LocationPicker> usedLocationpickerInScreen , Screen activity, Set<String> imports){
 		if(usedLocationpickerInScreen.size > 0){
		  imports.add("java.util.Locale");
		  imports.add("android.graphics.Bitmap");
		  imports.add("android.graphics.BitmapFactory");
		  imports.add("android.graphics.Canvas");
		  imports.add("android.graphics.Point");
		  var boolean isConflicting = false;
		  for(String testDuplicate: imports){
			if(testDuplicate.endsWith(".Address")){
			    isConflicting = true;
			}
		  }
		  if(!isConflicting){
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
		  imports.add("com.google.android.maps.Overlay");
		}
 	}
 	
 	def private getButtonImportsIfNeeded(Screen screen, Set<String> imports ,MobileApp m, PackageInfo packageInfo){
 	    var boolean menuContainsButtons = false;
 	    // FIX for [MAM-173]
 	    if (screen.menuBar != null) {
	        for(DisplayElement displayElement: screen.menuBar.menuBarElements){
	            if(displayElement instanceof Button){
	                imports.add(packageInfo.packageName+".activity.listener."+displayElement.name.toFirstUpper+"OnClickListener")
	                menuContainsButtons =  true;
	            }
	        }
		}
		for(Button button: screen.buttons){
		    if(!(button.eContainer instanceof CompositeDisplayElementType))
		     imports.add(packageInfo.packageName+".activity.listener."+button.name.toFirstUpper+"OnClickListener")
		}
	 	if(screen.buttons.size > 0  || menuContainsButtons){
		  imports.add("android.widget.Button");
		}

 	}
 	def private getCheckBoxImportsIfNeeded(Screen screen, Set<String> imports ,MobileApp m, PackageInfo packageInfo){
 	    var boolean menuContainsCheckBoxes = false;
 	    if (screen.menuBar != null) {
	        for(DisplayElement displayElement: screen.menuBar.menuBarElements){
	            if(displayElement instanceof CheckBox){
	                menuContainsCheckBoxes =  true;
	            }
	        }
		}
	 	if(screen.getDirectAssociatedCheckBoxes.size > 0  || menuContainsCheckBoxes){
		  imports.add("android.widget.CheckBox");
		}
 	}
 	
 	def private getImageButtonImportsIfNeeded(Screen screen, Set<String> imports){
 	    var boolean menuContainsImageButtons = false;
 	    // FIX for [MAM-173]
 	    if (screen.menuBar != null) {
	 	    for(DisplayElement displayElement: screen.menuBar.menuBarElements){
	 	        if(displayElement instanceof ImageButton){
	 	            menuContainsImageButtons =  true;
	 	        }
	 	    }
 	    }
	 	if(screen.imageButton.size > 0 || menuContainsImageButtons ){
	 	    imports.add("android.widget.ImageButton")
	 	}
 	}
 	
 	def private getLabelImportsIfNeeded(List<Label> usedLabelsInScreen, Set<String> imports ){
 		if(usedLabelsInScreen.size > 0){
			imports.add("android.widget.EditText");
			imports.add("android.widget.ImageView");
			imports.add("android.widget.TextView");
		}
 	}
 	
 	def private getMenuBarImportsIfNeeded(Screen screen, Set<String> imports){
 	    if(screen.menuBar !=  null ){
 	        if(screen.locationPicker.size > 0){
 	            //Common android for MapActivities
 	            imports.add("android.view.Menu");
 	            imports.add("android.view.MenuInflater");
 	            imports.add("android.view.MenuItem");
 	        } else {
 	        imports.add("com.actionbarsherlock.view.Menu");
 	        imports.add("com.actionbarsherlock.view.MenuInflater");
 	        imports.add("com.actionbarsherlock.view.MenuItem");
 	        }
 	    }
 	}
 	
 	def private getLabelAndInputImportsIfNeeded(List<DisplayElement> displayElements, List<Label> usedLabelsInScreen, Set<String> imports ){
 	    var boolean usedInputs = false;
 	    for(DisplayElement displayElement: displayElements){
 	        if(displayElement instanceof Input){
 	            usedInputs = true;
 	        }
 	    }
        if((!(usedLabelsInScreen.size > 0) )|| usedInputs){
            imports.add("android.widget.EditText");
        }
    }
 	

 	def private getDatepickerImportsIfNeeded(List<Datepicker> usedDatepickersInScreen, Set<String> imports ){
 		if(usedDatepickersInScreen.size > 0 ){
			imports.add("java.util.Calendar");
			imports.add("android.app.DatePickerDialog");
			imports.add("android.widget.DatePicker");
			imports.add("java.text.DateFormat");
		}
 	}
 	
 	def private getDynamicListImportsIfNeeded(List<DynamicList> usedDynamicListsInScreen, Set<String> imports, MobileApp m, PackageInfo packageInfo){
	 	if(usedDynamicListsInScreen.size > 0){
			imports.add("android.app.ListActivity");
			imports.add("android.widget.ListView");
			imports.add("android.database.CursorWrapper");
			imports.add("android.widget.CursorAdapter");
		}
	}
	
	def private getDomainImports(List<DomainBinding> bindings, Set<String> imports, PackageInfo packageInfo) {
	    for(PrimitiveBinding binding:  bindings.filter(binding | binding instanceof PrimitiveBinding).map(binding | binding as PrimitiveBinding)){
	        imports.add(packageInfo.getPackageName() + ".model." +  binding.attribute.EContainingClass.name);
	    }
		for (ListBinding listBinding : bindings.filter(b | b instanceof ListBinding).map(b | b as ListBinding)) {
			if (! imports.contains(packageInfo.getPackageName() + ".model." + listBinding.domainReference.EType.eClass.name)){
				  imports.add(packageInfo.getPackageName() + ".adapter." + listBinding.listElement.name +"ListAdapter");
			}
		}
	}
	
 	def private generateClass(String packageName, Screen screen, 
							Set<String> imports, 
							String inheritance, 
							MobileApp m) {
		'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl(screen.name.toFirstUpper+"Activity", inheritance, null, false)»
		«generateRequiredAttributes(screen)»
«««	    Entity attributes for PrimitiveBindings, ComplexBindings and SelectionBindings
		«var Set<EClass> requiredEntities = getRequiredEntities(screen, m)»
		«IF requiredEntities != null»
            «FOR EClass entity : requiredEntities»
               protected «entity.name» «entity.name.toFirstLower»;
            «ENDFOR»
        «ENDIF»
«««     Missing Entity attribute arrays for ListBinding?
        
        «IF screen.directAssociatedDatepicker.size > 0»
            «FOR Datepicker datePicker: screen.directAssociatedDatepicker»
                protected ImageButton «datePicker.name.toFirstLower»ImageButton;
                protected DatePickerDialog.OnDateSetListener onDateSetListener«datePicker.name» = null;
              «ENDFOR»
        «ENDIF»
        
		«generateActivityLifeCycleMethods(screen, m)»
		«generateDisplayElementGetterMethods(screen)»
        «generateRequiredEntityGetterMethods(requiredEntities)»

        «IF screen.locationPicker.size > 0 »
            «generateLocationPickerMethodsAndClasses()»
        «ENDIF»
          «IF screen.directAssociatedDatepicker.size > 0»
            «FOR Datepicker datePicker: screen.directAssociatedDatepicker»
                      «datepickerAdditions.generateShowDatePickerDialog(datePicker)»
            «ENDFOR»
        «ENDIF»
		}'''			
	}
	
	def generateRequiredAttributes(Screen screen){
	    var List<Triple<String, String, String>> attributes = new ArrayList<Triple<String, String, String>>();
        attributes.add(new Triple<String, String, String>("Context","mContext", null));
        attributes.add(new Triple<String, String, String>("String","TAG", screen.name.toFirstUpper+"Activity.class.getSimpleName()"));
	    '''
	    «generateDisplayElementAttributes(screen)»
        «javaUtilities.generateAdditionalAttributesWithExpression(attributes.get(0), false, false)»
        «javaUtilities.generateAdditionalAttributesWithExpression(attributes.get(1), false, true)»
	    '''
	}
	
	/**
	 * Generates the entity attributes that are required for the bindings or 
	 */
	def private Set<EClass> getRequiredEntities(Screen screen, MobileApp m) {
	    var Set<EClass> requiredEntities = new HashSet<EClass>();
	    //Check all flows if they have a context that flows to or from the current screen
	    if(m.storyBoard != null) {
    	    for(Flow flow : m.storyBoard.flows){
                if(flow.flowContext != null && flow.flowContext.contextType.size > 0 && (flow.to.equals(screen) || flow.from.equals(screen)) ){
                    requiredEntities.addAll(flow.flowContext.contextType);
                }   	        
    	    }
	    }
	    //also check if a entity is bound on the screen that isn't contained within a flow context
	    return requiredEntities = determindeEntitiesTroughBindigns(screen, m, requiredEntities);
	}
	
	/**
	 * Determines the required entities through the bindings with the direct associated displayelements in the screen.
	 */
    def public Set<EClass> determindeEntitiesTroughBindigns(Screen screen, MobileApp m, Set<EClass> requiredEntities){
	   if(m.bindingRepository != null) {
            for(DomainBinding binding : m.bindingRepository.bindings){
                if(binding instanceof PrimitiveBinding) {
                    val PrimitiveBinding  primitiveBinding = binding as PrimitiveBinding;
                    if( primitiveBinding instanceof StringBinding && screen.displayElements.contains((primitiveBinding as StringBinding).uiElement) 
                        || primitiveBinding instanceof BooleanBinding && screen.displayElements.contains((primitiveBinding as BooleanBinding).checkBox)) {
                        requiredEntities.add(primitiveBinding.attribute.EContainingClass);
                    }
                } else if(binding instanceof ComplexBinding){
                    requiredEntities.add((binding as ComplexBinding).domainEntity);
                } else if(binding instanceof SelectionBinding){
                    requiredEntities.add((binding as SelectionBinding).domainReference.EReferenceType);
                }
            }
        }
        return requiredEntities;
	}
	
	   def private generateActivityLifeCycleMethods(Screen screen, MobileApp m){
        '''
        «generateOnCreateMethod(screen.name, screen, m)»
        '''
    }
    
    def private generateOnCreateMethod(String activityName, Screen screen, MobileApp m){
        '''
        @Override
        public void onCreate(Bundle savedInstantsState){
            super.onCreate(savedInstantsState);
            mContext = this.getApplicationContext();
            setContentView(R.layout.«activityName.toLowerCase»);

«««            «FOR displayElement: screen.displayElements»
«««               «generateDisplayElementInOnCreateMethod(activityName, displayElement, m)»
«««            «ENDFOR»
        }
        '''
    }
    
    def public generateDisplayElementInOnCreateMethod(String activityName, DisplayElement displayElement, MobileApp m){
        '''
        «IF displayElement instanceof CompositeDisplayElement»
«««                TODO initialize fragment attributes by R.class ?
        «ELSE»
            «val String type = displayElement.displayElementType»
            «val String prefix = displayElement.displayElementPrefix»
            «IF !type.nullOrEmpty»
                «IF displayElement instanceof DynamicList»
                        «activityName»HookActivity hook = new «activityName»HookActivity();
«««                  We don't generate listadapters that have no Entity or do not contain an ui-element that is bound
                    «IF javaUtilities.getDomainEntityNameForDynamicList(m, displayElement as DynamicList) != null && javaUtilities.getFirstBoundUiElementForDynamicList(m, displayElement as DynamicList) != null»
                        «javaUtilities.getListBindingForDisplayElement(m, displayElement).listElement.name»ListAdapter adapter = new «javaUtilities.getListBindingForDisplayElement(m, displayElement).listElement.name»ListAdapter(this, true);
                        setListAdapter(adapter);
                        «type.toFirstLower»«displayElement.name» = getListView();
                        getLoaderManager().restartLoader(0, null, adapter);
                    «ENDIF»
                «ELSEIF displayElement instanceof Datepicker»
                    «type.toFirstLower»«displayElement.name» =  Calendar.getInstance();
                       «displayElement.name.toFirstLower»ImageButton = (ImageButton) findViewById(R.id.imageButton«displayElement.name»);
                «ELSE»
                    «type.toFirstLower»«displayElement.name» = («type»)findViewById(R.id.«prefix»«displayElement.name»);
                «ENDIF»
                «generateAddOnClickListener(type, displayElement)»
           «ENDIF»
        «ENDIF»
        '''
    }
    
    def generateAddOnClickListener(String type, DisplayElement displayElement){
        '''
        «IF type.equals("Button") || type.equals("ImageButton")  »
            «type.toFirstLower»«displayElement.name.toFirstUpper».setOnClickListener(new «displayElement.name.toFirstUpper»OnClickListener(this));
        «ELSEIF type.equals("Calendar") »
            «displayElement.name.toFirstLower»ImageButton.setOnClickListener(new View.OnClickListener() {
                
                public void onClick(View view){
                  show«displayElement.name»DatePickerDialog(view);
                }
            });
        «ENDIF»
        '''
    }
	
	def private generateDisplayElementGetterMethods(Screen screen){
	    '''
	    «FOR displayElement: screen.displayElements»
	       «IF !( displayElement instanceof CompositeDisplayElement)»
    	       «val type = displayElement.displayElementType»
               «type» get«type»«displayElement.name.toFirstUpper»(){
                 return «type.toFirstLower»«displayElement.name.toFirstUpper»;
               }
           «ENDIF»
        «ENDFOR»
	    '''
	}
	
	def private generateRequiredEntityGetterMethods(Set<EClass> requiredEntities){
	    '''
	    «FOR EClass entity : requiredEntities»
	    /**
	    * Returns the entity that is set through ui fields or by incoming intents.
	    * @return = null if entity was not set by the intent of the input field. Else the entity.
	    **/
           public «entity.name» get«entity.name»(){
                return «entity.name.toFirstLower»;
           }    
        «ENDFOR»
	    '''
	}
	
	def private generateDisplayElementAttributes(Screen screen){
	    '''
	    «FOR displayElement: screen.displayElements»
    	  «generateDisplayElementAttribute(displayElement)»
        «ENDFOR»
        «IF screen.menuBar != null»
            «FOR displayElement: screen.menuBar.menuBarElements»
                «generateDisplayElementAttribute(displayElement)»
            «ENDFOR»
        «ENDIF»
	    '''
	}
	
	def private generateDisplayElementAttribute(DisplayElement displayElement){
	    '''
	    «IF displayElement instanceof CompositeDisplayElement»
«««        TODO generate private Attribute with type of CDE-Fragment Class
        «ELSE»
	       «val type = displayElement.displayElementType»
            protected «type» «type.toFirstLower»«displayElement.name.toFirstUpper»;
       «ENDIF»
	    '''
	}
	
    def private generateLocationPickerMethodsAndClasses(){
        '''
        «generateMethodIsRouteDisplayed()»
        '''
    }
 	
    def private generateMethodIsRouteDisplayed(){
        '''
        @Override
        protected boolean isRouteDisplayed() {
            return false;
        }
       '''
   } 

}