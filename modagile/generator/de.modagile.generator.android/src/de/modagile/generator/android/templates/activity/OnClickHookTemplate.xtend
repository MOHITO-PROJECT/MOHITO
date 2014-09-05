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

import de.modagile.generator.android.templates.ModagileFolderConstants
import de.modagile.generator.android.templates.java.JavaUtils
import de.modagile.metamodel.app.ui.DisplayElement
import info.multiplatform.generator.java.templates.PackageInfo
import javax.inject.Inject
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.metamodel.app.ui.Screen
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.ui.Button
import java.util.List
import de.modagile.metamodel.app.ui.Flow
import de.modagile.metamodel.app.ui.MenuBar
import de.modagile.metamodel.app.ui.InputContext
import org.eclipse.emf.ecore.EClass
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.domain.BindingRepository

class OnClickHookTemplate {
      
   @Inject extension JavaUtils javaUtils
   
   def private generateOnClickHook(IFileSystemAccess fsa, DisplayElement displayElement, PackageInfo packageInfo, Screen screen, MobileApp m){
      fsa.generateFile(packageInfo.packageDir + "activity/listener/" + displayElement.name + "OnClickListener.java", ModagileFolderConstants::SRC_MAN, generateSource(packageInfo.packageName + ".activity.listener", displayElement, screen, m, packageInfo) )
   }
   
     def private generateDatePickerOnClickHook(IFileSystemAccess fsa, Datepicker datepicker, PackageInfo packageInfo, Screen screen, MobileApp m){
//      fsa.generateFile(packageInfo.packageDir + "activity/listener/" + datepicker.name + "OnClickListener.java", ModagileFolderConstants::SRC_MAN, generateDatePickerSource(packageInfo.packageName + ".activity.listener", datepicker, screen, m, packageInfo) )
   }
   
   def generateOnClickHookListener(IFileSystemAccess fsa, Screen screen, MobileApp m, PackageInfo packageInfo){
           screen.displayElements.forEach[displayElement | 
              if(displayElement instanceof Button ){
                 fsa.generateOnClickHook(displayElement,packageInfo, screen, m)
              }else if (displayElement instanceof Datepicker){
                      fsa.generateDatePickerOnClickHook(displayElement as Datepicker,packageInfo, screen, m)
              }
           ]
           if(screen.menuBar != null){
               screen.menuBar.menuBarElements.forEach[displayElement | 
                  if(displayElement instanceof Button ){
                     fsa.generateOnClickHook(displayElement,packageInfo, screen, m)
                  } else if (displayElement instanceof Datepicker){
                      fsa.generateDatePickerOnClickHook(displayElement as Datepicker,packageInfo, screen, m)
                  }
               ]
           }
   }
   
   def CharSequence generateSource(String eventsPackageName, DisplayElement displayElement, Screen screen, MobileApp m, PackageInfo packageInfo) { 
       var String screenName = "";
       if( displayElement.eContainer instanceof Screen){
           screenName = displayElement.screen.name.toFirstUpper;
       } else if (displayElement.eContainer instanceof MenuBar){
           screenName = (displayElement.eContainer.eContainer as Screen).name.toFirstUpper;
       }
   '''
        «javaUtils.statementGenerated("OnClickHookTemplate")»
        «javaUtils.packageStatement(eventsPackageName)»
   
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.content.Intent;
        import «packageInfo.packageName».activity.«screenName»HookActivity;
        import «packageInfo.packageName».activity.«(displayElement as Button).buttonClickEvents.get(0).triggers.to.name.toFirstUpper»HookActivity;
        import «packageInfo.packageName».constants.«m.name.toFirstUpper»Constants;
        
        public class «displayElement.name.toFirstUpper»OnClickListener implements OnClickListener{
        private «screenName»HookActivity mActivity;
        «constructor(displayElement, screenName)»
   
        «generateButtonClickMethod(m, displayElement as Button, screen.storyBoard.flows, m.bindingRepository)»
        }
   '''
   }

   def constructor(DisplayElement displayElement, String screenName) {
   '''
      public «displayElement.name.toFirstUpper»OnClickListener(«screenName»HookActivity activity){
         mActivity = activity;
      }
   '''
   }
   
    def private generateButtonClickMethod(MobileApp app, Button button, List<Flow> flows, BindingRepository bindingRepository){
        '''
        «IF button.buttonClickEvents.get(0) != null && button.buttonClickEvents.get(0).triggers == null»
            «generateOnClickMethodsWithoutExistingFlow(button)»
        «ELSEIF  button.buttonClickEvents.get(0) != null»
            «generateOnClickMethodsWithExistingFlow(app, button, flows, bindingRepository)»
        «ENDIF»
        '''
    }

    def private generateOnClickMethodsWithoutExistingFlow(Button button){
        '''
        public void onClick(View v){
        }
        '''
    }
    
    def  generateOnClickMethodsWithExistingFlow(MobileApp app, Button button, List<Flow> flows, BindingRepository bindingRepository){
        
        '''
         public void onClick(View v){
             //TODO change behavior if needed
             Intent intent = new Intent(mActivity, «button.buttonClickEvents.get(0).triggers.to.name.toFirstUpper»HookActivity.class);
             «IF bindingRepository != null»
                mActivity.saveUIDataToEntity();
             «ENDIF»
            «generateContextIfExists(button)»
            «generateOnClickMethodStartActivity(app, button.buttonClickEvents.get(0).triggers)»
         }
        '''
    }
    
    def generateOnClickMethodStartActivity(MobileApp app, Flow flow) {
    	'''
    	«IF flow.returnsResult»
    		mActivity.startActivityForResult(intent, «app.name.toFirstUpper»Constants.REQUEST_CODE_«flow.name.toUpperCase.replace("->", "_TO_").replace(" ", "")»);
    	«ELSE»
    		mActivity.startActivity(intent);
    	«ENDIF»
    	'''
    }
    
    def  generateContextIfExists(Button button){
        '''
        «IF button.buttonClickEvents.size > 0  && button.buttonClickEvents.get(0).triggers!= null && button.buttonClickEvents.get(0).triggers.flowContext != null»
            «val InputContext context = button.buttonClickEvents.get(0).triggers.flowContext»
            «IF context.contextType != null»
                «FOR EClass  contextEntity : context.contextType»
                    intent.putExtra("«contextEntity.name»", mActivity.get«contextEntity.name»());
                «ENDFOR»
            «ENDIF»
        «ENDIF»
        '''
    }

}