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
package de.modagile.generator.android.templates

import java.util.List
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.metamodel.app.ui.Screen
import de.modagile.metamodel.app.ui.DisplayElement
import de.modagile.metamodel.app.ui.CompositeDisplayElement
import de.modagile.metamodel.app.ui.Label
import de.modagile.metamodel.app.ui.Button
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.ui.MenuBar
import de.modagile.metamodel.app.ui.DynamicList
import de.modagile.metamodel.app.ui.CompositeDisplayElementType
import com.google.inject.Inject
import de.modagile.metamodel.app.ui.Input
import de.modagile.metamodel.app.ui.ImageButton

class StringFileTemplate {
	    @Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
	
	def generateAndroidStringFile(IFileSystemAccess fsa, MobileApp app){
		fsa.generateFile("values/strings.xml", ModagileFolderConstants::RESOURCE, generateXMLFile(app));
	}
	
	def private generateXMLFile(MobileApp app) {
		'''
		<?xml version="1.0" encoding="utf-8"?>
		<resources>
		«IF app.storyBoard != null»
			«generateStringCode(app.storyBoard.screens, app.name, app.appVersion)»
		«ENDIF»
		    <string name="settingsTitle">Settings</string>
			<string name="settingsServerIp">Server IP</string>
			<string name="settingsServerIpSummary">Server IP Address (e.g. 10.0.2.2)</string>
			<string name="settingsServerPort">Server port</string>
			<string name="settingsServerPortSummary">Port number where the server is listening (e.g. 8000)</string>
			<string name="app_name">«app.name+app.appVersion»</string>
			<string name="dash">-</string>
		</resources>		
		'''		
	}
	
	def private generateStringCode(List<Screen> activities, String appName, String appVersion){
		'''
		«FOR Screen activity : activities»
		<string name="«activity.name.toFirstUpper»">«activity.name.toFirstUpper»</string>
			«FOR DisplayElement displayElement:  activity.displayElements»
				    «generateDisplayElementString(displayElement, null)»
			«ENDFOR»
			«IF activity.menuBar != null»
			 «generateMenubarStrings(activity.menuBar)»
			 «ENDIF»
		«ENDFOR»
		'''
	}
	
	def private generateCompositeDisplayElementStrings(CompositeDisplayElement compositeDisplayElement){
	    '''
	   «FOR DisplayElement displayElementInComposite : compositeDisplayElement.type.containedDisplayElements»
	       «IF displayElementInComposite instanceof CompositeDisplayElement»
	           «generateCompositeDisplayElementStrings(compositeDisplayElement)»
	       «ELSE»
                «generateDisplayElementString(displayElementInComposite, compositeDisplayElement)»
           «ENDIF»
       «ENDFOR»
       '''
	}
	
	def private generateMenubarStrings(MenuBar menuBar){
	    '''
	    
	    «FOR DisplayElement displayElement: menuBar.menuBarElements»
	       «generateDisplayElementString(displayElement, null)»
	    «ENDFOR»
   	    '''
	}
	
	def private generateDisplayElementString(DisplayElement displayElement, CompositeDisplayElement compositeDisplayElement){
	    var String compositeDisplayElementName = "";
	    if(compositeDisplayElement != null){
	       compositeDisplayElementName = compositeDisplayElement.name;
	    }
	    '''
	    «IF displayElement instanceof Label»
            «var Label label = displayElement as Label»
            <string name="«javaUtilities.getDisplayElementPrefix(label)»«(compositeDisplayElementName + label.name.toFirstUpper).toFirstUpper»">«label.text»</string>
         «ELSEIF displayElement instanceof ImageButton»
            «var ImageButton imageButton = displayElement as ImageButton»
            <string name="«javaUtilities.getDisplayElementPrefix(imageButton)»«(compositeDisplayElementName + imageButton.name.toFirstUpper).toFirstLower»">«imageButton.text»</string>
        «ELSEIF displayElement instanceof Button»
            «var Button button = displayElement as Button»
            <string name="«javaUtilities.getDisplayElementPrefix(button)»«(compositeDisplayElementName+ button.name.toFirstUpper).toFirstUpper»">«button.text»</string>
        «ELSEIF displayElement instanceof Datepicker»
            «var Datepicker datepicker = displayElement as Datepicker»
            <string name="«javaUtilities.getDisplayElementPrefix(datepicker)»«(compositeDisplayElementName + datepicker.name.toFirstUpper).toFirstLower»">Cannot use a future date!</string>
        «ELSEIF displayElement instanceof Input»
            «var Input inputfield = displayElement as Input»
            <string name="«javaUtilities.getDisplayElementPrefix(inputfield)»«(compositeDisplayElementName + inputfield.name.toFirstUpper).toFirstLower»">«inputfield.text»</string>
       «ELSEIF displayElement instanceof CompositeDisplayElement»
            «FOR DisplayElement displayElementInCDE : (displayElement as CompositeDisplayElement).type.containedDisplayElements»
                «generateDisplayElementString(displayElementInCDE, displayElement as CompositeDisplayElement)»
            «ENDFOR»
        «ELSEIF displayElement instanceof DynamicList»
            «FOR CompositeDisplayElementType cdeType : (displayElement as DynamicList).displayedElements»
                «FOR DisplayElement displayElementInCDE : cdeType.containedDisplayElements»
                    «generateDisplayElementString(displayElementInCDE, null)»
                «ENDFOR»
            «ENDFOR»
        «ELSE»    
	       <string name="«(compositeDisplayElementName + displayElement.name).toFirstLower»">«displayElement.name»</string>
        «ENDIF»
	    '''
	}
}