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
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.domain.StringBinding
import de.modagile.metamodel.app.event.Event
import de.modagile.metamodel.app.ui.Button
import de.modagile.metamodel.app.ui.DisplayElement
import de.modagile.metamodel.app.ui.Flow
import de.modagile.metamodel.app.ui.Screen
import de.modagile.metamodel.app.domain.UpdateStrategy
import info.multiplatform.generator.java.helper.Triple
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.ArrayList
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.metamodel.app.domain.PrimitiveBinding
import de.modagile.metamodel.app.domain.BooleanBinding
import de.modagile.metamodel.app.domain.DomainBinding
import de.modagile.metamodel.app.domain.BindingRepository
import de.modagile.metamodel.app.ui.Input
import de.modagile.metamodel.app.ui.MenuBar
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EReference

/**
 * Responsible to create hooks for activities.
 */
class ActivitiesHooksTemplate {
	@Inject extension ActivitiesTemplate activitiesTemplate
	@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	@Inject extension DatepickerAdditionsTemplate datepickerAdditions
	@Inject extension DomainModelUtils domainModelUtils
	
	def generateActivitiesHooks( IFileSystemAccess fsa, Screen screen , MobileApp m, PackageInfo packageInfo) { 
 		fsa.generateFile(packageInfo.packageDir+"activity/"+screen.name.toFirstUpper + "HookActivity.java", ModagileFolderConstants::SRC_MAN, 
 			screen.generateActivityHookCode(packageInfo,  m)
 		)
 	} 
 	 	
 	def private generateActivityHookCode(Screen screen,  PackageInfo packageInfo, MobileApp mobileApp) {
  		var Set<String> imports  = new HashSet<String>();
  		activitiesTemplate.getAllImports(screen, packageInfo, mobileApp, imports);
 		var String inheritance = screen.name.toFirstUpper+"Activity";
	 	'''
 		«generateClass(packageInfo.packageName+".activity", screen, imports, packageInfo, inheritance, null, mobileApp)»
 		'''
 	}
 	
 	def private getRequiredWriteAndWriteThroughImports(Screen screen, PackageInfo packageinfo, BindingRepository bindingRepository, Set<String> imports){
 	   var Set<EClass> entitiesInScreen =  javaUtilities.collectEntitiesForScreenByPrimitiveBindings(screen, bindingRepository);
 	   for (EClass entity: entitiesInScreen){
 	       imports.add(packageinfo.packageName+".manager."+entity.name+"Manager");
 	       imports.add(packageinfo.packageName+".manager.impl."+entity.name+"ManagerImpl");
 	       imports.add(packageinfo.packageName+".task.AsyncCallback");
 	       imports.add(packageinfo.packageName+".task.Create"+entity.name);
 	       for(EReference entityClassReference: entity.EReferences){
 	           imports.add(packageinfo.packageName+".task.Update"+entityClassReference.EReferenceType.name);
 	       }
 	   }
 	}
 	
 	def private generateClass(String packageName, Screen screen, 
							Set<String> imports,
							PackageInfo packageInfo, 
							String inheritance, 
							List<String> interfaces,
							MobileApp m) {
		var List<Triple<String, String, String>> attributes = new ArrayList<Triple<String, String, String>>();
		var Set<EClass> entitiesToGenerateGettersFor = new HashSet<EClass>();
		var boolean writethroughImportsRequired = false;
		if (m.bindingRepository != null){
		  entitiesToGenerateGettersFor = javaUtilities.collectEntitiesForScreenByPrimitiveBindings(screen, m.bindingRepository);
		  
		  for (DomainBinding binding : m.bindingRepository.bindings) {
		        if(binding.updateStrategy == UpdateStrategy::WRITE_THROUGH){
		            writethroughImportsRequired = true;
		        }
		  }
		  if(writethroughImportsRequired){
		  getRequiredWriteAndWriteThroughImports(screen, packageInfo, m.bindingRepository, imports);
		  }
		}
		attributes.add(new Triple<String, String, String>("Context","mContext", null));
		attributes.add(new Triple<String, String, String>("String","TAG", screen.name.toFirstUpper + "HookActivity.class.getSimpleName()"));
		'''
		«javaUtilities.statementGenerated(this.getClass())»
		«javaUtilities.packageStatement(packageName)»
		«javaUtilities.importStatements(imports)»
		«javaUtilities.classDecl(screen.name.toFirstUpper + "HookActivity", inheritance, interfaces, false)»
		«javaUtilities.generateAdditionalAttributesWithExpression(attributes.get(0), false, false)»
		«javaUtilities.generateAdditionalAttributesWithExpression(attributes.get(1), false, true)»
		«IF screen.directAssociatedDatepicker.size > 0»
			«datepickerAdditions.generateDatepickerAdditions(screen.name+"HookActivity", screen.directAssociatedDatepicker)»
		«ENDIF»
		«IF screen.dynamicList.size > 0»
			«FOR dynamicList : screen.dynamicList»
				«generateListActivityMethods()»
			«ENDFOR»
		«ENDIF»
		«IF screen.locationPicker.size() > 0»
			«generateIsRouteDisplayed()»
		«ENDIF»
		«generateActivityLifeCycleMethods(screen, m , writethroughImportsRequired)»
		«generateButtonOnClickMethods(screen.buttons, new ArrayList<Flow>())»
		«generateOnActivityResultMethod»
		«FOR EClass entity: entitiesToGenerateGettersFor»
            «generateGetDomainEntity(entity, screen)»
        «ENDFOR»
          «IF screen.menuBar != null»
          «generateMenuBarMethods(screen.menuBar, m, screen)»
        «ENDIF»
		}'''			
	}
	
	def private generateListActivityMethods(){
		'''
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
		}
		'''
	}
	
	def private generateActivityLifeCycleMethods(Screen activity, MobileApp m, boolean writethroughImportsRequired){
		'''
		«generateOnCreateMethod(activity, m )»
		«generateOnStartMethod»
		«generateOnResume»
		«generateOnPause(activity, m.bindingRepository)»
		«generateOnDestroy»
		«generateOnRestart»
		«IF m.bindingRepository != null»
            «writeDisplayElementValuesToEntities(activity, m.bindingRepository.bindings)»
            «IF writethroughImportsRequired»
                «writeDisplayElementValuesToDatabase(activity , m.bindingRepository)»
            «ENDIF»
        «ENDIF»
		'''
	}
	
	def private generateOnCreateMethod(Screen screen, MobileApp m){
		'''
		@Override
		public void onCreate(Bundle savedInstantsState){
			super.onCreate(savedInstantsState);
			mContext = this.getApplicationContext();
			 «FOR displayElement: screen.displayElements»
               «generateDisplayElementInOnCreateMethod(screen.name, displayElement, m)»
            «ENDFOR»
			«generateOnDateSetListener(screen.directAssociatedDatepicker)»
«««			Fill the entities with the flow context and fill the ui fields with the entity's values
			«generateSetIntentExtras(screen, m)»
		}
		'''
	}
	
	/**
	 * Generates the behavior for all possible extras that can be in the intent when this activity starts.
	 */
	def private generateSetIntentExtras(Screen screen, MobileApp m){
	    var List<Flow> flowsToThisActivity = new ArrayList<Flow>();
	    var Set<EClass> requiredEntities = activitiesTemplate.determindeEntitiesTroughBindigns(screen, m, new HashSet<EClass>)
	    for(Flow flow: m.storyBoard.flows.filter(f | f.to.equals(screen))){
	        flowsToThisActivity.add(flow);
	    }
	    '''
	       «IF flowsToThisActivity.size > 0»
	       Intent intent = this.getIntent();
	       «setAttributesViaIntentExtras(flowsToThisActivity)»
	       «FOR EClass contextType: requiredEntities»
                if(this.«contextType.name.toFirstLower» == null) {
                    this.«contextType.name.toFirstLower» = new «contextType.name»();
                }
           «ENDFOR»
	       ««« Also fill the ui elements that are bound to thr entity which can be null
            «fillUIElementsViaEntityAttributes(screen, m)»
	       «ENDIF»
	    '''
	}
	
	def private setAttributesViaIntentExtras(List<Flow> flows){
	    '''
	    «FOR Flow flow : flows»
               «IF flow.flowContext != null»
                   «FOR EClass contextType: flow.flowContext.contextType»
                       if(intent.hasExtra("«contextType.name»") && intent.getExtras().get("«contextType.name»") instanceof «contextType.name»){
                           this.«contextType.name.toFirstLower» = («contextType.name») intent.getExtras().get("«contextType.name»");
                       }
                   «ENDFOR»
               «ENDIF»
           «ENDFOR»
	    '''
	}
	
	/**
	 * Fills the ui elements in the screen that are bound to the attributes of entities with the updatestragety "read"
	 */
	def private fillUIElementsViaEntityAttributes(Screen screen, MobileApp m){
	    '''
	    «IF m.bindingRepository != null && m.bindingRepository.bindings != null»
	       «FOR PrimitiveBinding primitiveBinding : m.bindingRepository.bindings.filter(binding | binding instanceof PrimitiveBinding && binding.updateStrategy== UpdateStrategy::READ ).map(binding | binding as PrimitiveBinding)»
	           
	           «IF primitiveBinding instanceof StringBinding && screen.displayElements.contains((primitiveBinding as StringBinding).uiElement as DisplayElement)»
	               «getDisplayElementPrefix((primitiveBinding as StringBinding).uiElement as DisplayElement)»«((primitiveBinding as StringBinding).uiElement as DisplayElement).name».setText(this.«(primitiveBinding as StringBinding).attribute.EContainingClass.name.toFirstLower».get«(primitiveBinding as StringBinding).attribute.name.toFirstUpper()»());
	           «ELSEIF primitiveBinding instanceof BooleanBinding && screen.displayElements.contains((primitiveBinding as BooleanBinding).checkBox)»
	               «getDisplayElementPrefix((primitiveBinding as BooleanBinding).checkBox)»«(primitiveBinding as BooleanBinding).checkBox.name».setChecked(this.«(primitiveBinding as StringBinding).attribute.EContainingClass.name.toFirstLower».get«(primitiveBinding as StringBinding).attribute.name.toFirstUpper()»());
	           «ENDIF»

	       «ENDFOR»
	    «ENDIF»
	    '''
	}
	
	def private generateOnStartMethod(){
		'''
		@Override
		protected void onStart(){
			super.onStart();
		}
		'''
	}
	
	def private generateOnResume(){
		'''
		@Override
		protected void onResume(){
			super.onResume();
		}
		'''
	}
	
	def private generateOnPause(Screen screen, BindingRepository bindingRepository){
		'''
		@Override
		protected void onPause(){
«««		    //Write DisplayElement values to entity or in database depending on updatestragety
             super.onPause();
            «IF bindingRepository != null»
			 saveUIDataToEntity();
			«ENDIF»
		}
		'''
	}
	
	/**
	 * Creates a save method that stored all displayElement data in the bound entity attributes.
	 */
	def private writeDisplayElementValuesToEntities(Screen screen, List<DomainBinding> bindings){
	    '''
	    /**
	    * Saves all values of the displayed ui elements that where bound with an UpdateStrategy write/writethrough to the entity
	    */
	    public void saveUIDataToEntity(){
	    «FOR PrimitiveBinding primitiveBinding: bindings.filter(binding | binding instanceof PrimitiveBinding 
	       && (binding.updateStrategy == UpdateStrategy::WRITE || binding.updateStrategy == UpdateStrategy::WRITE_THROUGH))
	       .map(binding | binding as PrimitiveBinding)»
	       «IF primitiveBinding instanceof StringBinding && screen.displayElements.contains((primitiveBinding as StringBinding).uiElement as DisplayElement)»
	           «var StringBinding stringBinding = primitiveBinding as StringBinding»
	           this.«stringBinding.attribute.EContainingClass.name.toFirstLower».set«stringBinding.attribute.name.toFirstUpper»(«getDisplayElementPrefix((stringBinding.uiElement as DisplayElement))»«(stringBinding.uiElement as DisplayElement).name».getText()«determineDisplayElement(stringBinding.uiElement as DisplayElement)»);
	       «ELSEIF primitiveBinding instanceof BooleanBinding && screen.displayElements.contains((primitiveBinding as BooleanBinding).checkBox as DisplayElement)»
	           «var BooleanBinding booleanBinding = primitiveBinding as BooleanBinding»
	           this.«booleanBinding.attribute.EContainingClass.name.toFirstLower».set«booleanBinding.attribute.name.toFirstUpper»(«getDisplayElementPrefix((booleanBinding.checkBox as DisplayElement))»«booleanBinding.checkBox.name».isChecked());
	           «ENDIF»
	       «ENDFOR»
	    }
	    '''
	}
	
    def private writeDisplayElementValuesToDatabase (Screen screen, BindingRepository bindingRepository) {
        '''
	    /**
        * Stores the values of the entities. The persistence layer(.task) is responsible to create the class and update the related/referenced other classes.
        */
        public void saveEntityDataToDatabase(){
            «var Set<EClass> entitiesInScreen =  javaUtilities.collectEntitiesForScreenByPrimitiveBindings(screen, bindingRepository)»
            «FOR EClass entity: entitiesInScreen»
               «IF checkEntityNeedsDBPersistence(entity, bindingRepository.bindings)»«««««WriteThrough Check
            
            Create«entity.name» create«entity.name» = new Create«entity.name»(mContext);
                create«entity.name».create«entity.name»Async(«entity.name.toLowerCase», new AsyncCallback<Uri>(){
                    
                    @Override
                    public void onResult(Uri result){
                    }
                });
                «ENDIF»
            «ENDFOR»
        }
        '''
	}
	
	def private boolean checkEntityNeedsDBPersistence(EClass entity, List<DomainBinding> bindings){
	   var boolean dbPersistenceRequired = false;
	   for(PrimitiveBinding primitiveBinding: bindings.filter(binding | binding instanceof PrimitiveBinding 
        && ( binding.updateStrategy == UpdateStrategy::WRITE_THROUGH)) .map(binding | binding as PrimitiveBinding)){
             if( entity.EAttributes.contains((primitiveBinding as StringBinding).attribute)){
                 dbPersistenceRequired = true;
             }
       }
       return dbPersistenceRequired;
	}
	
	def private determineDisplayElement(DisplayElement displayElement){
	    '''
	    «IF displayElement instanceof Input»
	       .toString()
	    «ENDIF»
	    '''
	}
 	
 	def private generateOnDestroy(){
 		'''
 		@Override
 		protected void onDestroy(){
 			super.onDestroy();
 		}
 		'''
 	}
 	
 	def private generateOnRestart(){
 		'''
 		@Override
 		protected void onRestart(){
««« 		    Refill ui fields throught the entity
 			super.onRestart();
 		}
 		'''
 	}
 	
 	def private generateIsRouteDisplayed(){
 		'''
		@Override
		protected boolean isRouteDisplayed() {
			// TODO Auto-generated method stub
			return false;
		}
 		'''
 	}
 	
 	def private generateGetDomainEntity(EClass entity, Screen screen) {
 		val classname = entity.name
 		'''
 		«IF screen.dynamicList.size > 0 »
 		public «classname»[] get«classname»s() {
			// TODO Auto-generated method stub
			«classname»[] «classname.toFirstLower»Array = new «classname»[10];
			for(int i=0; i < 10; i++){
			 		«classname.toFirstLower»Array[i] = new «classname»();
			}
			return «classname.toFirstLower»Array;
		}
		«ELSE»
     		public «classname» get«classname»() {
            // TODO Auto-generated method stub
«««            «classname» «classname.toFirstLower» = new «classname»();
            return «classname.toFirstLower»;
        }
		«ENDIF»
		'''
 	}
 	

 	def private generateButtonOnClickMethods(List<Button> buttons, List<Flow> flows){
 		'''
 		«FOR Button button : buttons»
 			«FOR Flow flow: flows»
 				«FOR Event event : flow.events»
 					«IF  button.buttonClickEvents.size > 0»
	 					«IF button.buttonClickEvents.contains(event)»
            @Override
            public void « button.name.toFirstUpper +"To"+ flow.to.name.toFirstUpper»(View view){
            super.«button.name.toFirstUpper +"To"+ flow.to.name.toFirstUpper»(view);
            }
 						«ENDIF»
			 		«ENDIF»
			 	«ENDFOR»
 			«ENDFOR»
 		«ENDFOR»
 		'''
 	}
 	
 	def private boolean uiElementIsInScreen(DisplayElement uiElement, Screen screen){
 	    var boolean elementIsInScreen = false;
 	    for(DisplayElement displayElement: screen.allDisplayElements){
 	        if(displayElement.equals(uiElement)) {
     	        elementIsInScreen = true;
 	        }
 	    }
 	    if(elementIsInScreen == false && screen.menuBar != null){//If the DisplayElemen was not found in the normal de/cdes the menubar displayelements also need to be checked
     	    for(DisplayElement displayElement : screen.menuBar.menuBarElements){
     	         if(displayElement.equals(uiElement)) {
                    elementIsInScreen = true;
                }
     	    }
 	    }
 	    return elementIsInScreen;
 	}
 	
 	  def private generateMenuBarMethods(MenuBar menuBar, MobileApp m, Screen screen){
        '''
        «generateOnCreateOptionsMenuMethod(menuBar, m, screen)»
        «generateMenuOnOptionsItemSelectedMethod(menuBar)»
        '''
    }
 	
 	def private generateMenuOnOptionsItemSelectedMethod(MenuBar menuBar){
        '''
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
            «FOR DisplayElement displayElementInMenu : menuBar.menuBarElements»
                «IF javaUtilities.checkIfDisplayElementHasAction(displayElementInMenu)»
                  case R.id.menu_item_«menuBar.screen.name»_«displayElementInMenu.name.toLowerCase»: «displayElementInMenu.displayElementType.toFirstLower»«displayElementInMenu.name.toFirstUpper».performClick(); break;
                «ENDIF»
            «ENDFOR» 
            }
           return true;
        }
        '''
    }
    
        def private generateOnCreateOptionsMenuMethod(MenuBar menuBar, MobileApp m, Screen screen){
        '''
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
          «IF screen.locationPicker.size > 0 »
          MenuInflater inflater  = getMenuInflater();
          «ELSE»
          MenuInflater inflater = getSupportMenuInflater();
          «ENDIF»
          inflater.inflate(R.menu.«menuBar.screen.name.toLowerCase»_menu, menu);
          «FOR displayElement: menuBar.menuBarElements»
             «generateDisplayElementInOnCreateOptionsMenuMethod((menuBar.eContainer as Screen), displayElement, m)»
          «ENDFOR»
          return true;
        }
        '''
    }
    
    def private generateDisplayElementInOnCreateOptionsMenuMethod(Screen screen, DisplayElement displayElement, MobileApp m){
        '''
        «val String type = displayElement.displayElementType»
         «type.toFirstLower»«displayElement.name.toFirstUpper» = («type»)menu.findItem(R.id.menu_item_«(displayElement.eContainer.eContainer as Screen).name»_«displayElement.name.toLowerCase»).getActionView();
        «generateAddOnClickListener(type, displayElement)»
        '''
    }
    
    //TODO fix me to return the Attributes that are bound to a ui element withing this screen and their binding updatestrategy is writethrough
    def private List<EAttribute> getBoundAttributesForUpdateStrategyWriteThrough(Screen screen, BindingRepository bindingRepository){
        var List<EAttribute> writeThroughAttributes = new ArrayList<EAttribute>();
        for (PrimitiveBinding binding : bindingRepository.bindings.filter(binding | binding.updateStrategy == UpdateStrategy::WRITE_THROUGH 
         && binding instanceof PrimitiveBinding).map(binding | binding as PrimitiveBinding )) {
//            imports.add(packageName+".task.Create"+ binding.attribute.EContainingClass.name);
        }
        return writeThroughAttributes;
    }
        
    def generateOnActivityResultMethod(){
    	'''
    	@Override
    	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    	// TODO implement manually
    	}
    	'''
    }
 	
}