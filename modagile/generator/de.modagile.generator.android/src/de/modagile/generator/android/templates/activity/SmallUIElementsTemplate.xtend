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

import de.modagile.metamodel.app.ui.Button
import de.modagile.metamodel.app.ui.Label
import de.modagile.metamodel.app.ui.Image
import de.modagile.metamodel.app.ui.ImageButton
import de.modagile.metamodel.app.ui.Input
import de.modagile.metamodel.app.ui.Flow
import de.modagile.metamodel.app.ui.CheckBox
import de.modagile.metamodel.app.ui.LocationPicker
import java.util.List
import de.modagile.metamodel.app.ui.DisplayElement
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.ui.DynamicList
import com.google.inject.Inject
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.event.Event

class SmallUIElementsTemplate {
    
    @Inject extension DatepickerAdditionsTemplate datepickerAdditions
    @Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
    
    def generateLayoutCodeForDisplayElement(DisplayElement displayElement, MobileApp m){
        '''
        «IF displayElement instanceof Button && !(displayElement instanceof ImageButton)»
                «generateButton(displayElement as Button, getFlowToButton(m, displayElement as Button))»
            «ELSEIF displayElement instanceof Label»
                «generateLabel(displayElement as Label)»
            «ELSEIF displayElement instanceof Datepicker»
                «datepickerAdditions.generateDatePickerLayout(displayElement as Datepicker)»
            «ELSEIF displayElement instanceof Input»
                «generateEditText(displayElement as Input)»
            «ELSEIF displayElement instanceof Image»
                «generateImage(displayElement as Image)»
            «ELSEIF displayElement instanceof ImageButton»
                «generateImageButton(displayElement as ImageButton)»
            «ELSEIF displayElement instanceof DynamicList»
                «generateListView()»
            «ELSEIF displayElement instanceof LocationPicker»
                «generateLocationPicker(displayElement as LocationPicker)»
            «ELSEIF displayElement instanceof CheckBox»
                «generateCheckBox(displayElement as CheckBox)»
            «ELSE»
            «ENDIF»
        '''
    }
    
    def private Flow getFlowToButton( MobileApp app ,Button button){
        for(Flow flow: app.storyBoard.flows){
            for(Event event: flow.events){
                if(button.buttonClickEvents.contains(event)){
                    return flow;
                }
            }
        }
        return null;
    }
	
	def generateButtons(List<Button> buttons, Flow flow){
		'''
		«FOR Button button : buttons»
			«generateButton(button, flow)»
        «ENDFOR»
		'''
	}
	
	def generateButton(Button button, Flow flow){
		'''
		<Button
			android:id="@+id/«javaUtilities.getDisplayElementPrefix(button)»«button.name»"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:text="@string/button«button.name»"/>
		'''
	}
	
	def generateButtonFromCompositeDisplayElement(String compositeDisplayElementName, Button button){
		'''
		<Button
			android:id="@+id/«javaUtilities.getDisplayElementPrefix(button)»«(compositeDisplayElementName + button.name).toFirstUpper»"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:text="@string/button«(compositeDisplayElementName +button.name).toFirstUpper»"/>
		'''
	}
	
	def generateLabels(List<Label> labels){
		'''
		«FOR Label label : labels»
			«generateLabel(label)»
		«ENDFOR»
		'''
	}
	
	def generateLabel(Label label){
		'''
		<TextView
			android:id="@+id/«javaUtilities.getDisplayElementPrefix(label)»«label.name»"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:text="@string/textView«label.name»"/>
		'''
	}
	
	def generateLabelFromCompositeDisplayElement(String compositeDisplayElementName, Label label){
		'''
		<TextView
			android:id="@+id/«javaUtilities.getDisplayElementPrefix(label)»«(compositeDisplayElementName + label.name).toFirstUpper»"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:text="@string/textView«(compositeDisplayElementName+  label.name).toFirstUpper»"/>
		'''
	}
	
	def generateImages(List<Image> images){
		'''
		«FOR Image image : images»
			«generateImage(image)»
        «ENDFOR»
		'''
	}
	
	def generateImage(Image image){
		'''
		<ImageView
            android:id="@+id/«javaUtilities.getDisplayElementPrefix(image)»«image.name»"
            android:layout_width="«image.width»dp"
            android:layout_height="«image.height»dp"
            android:adjustViewBounds="true"
            android:scaleType="fitStart"
            android:contentDescription="@string/«image.name.toFirstLower»"
            android:src="@drawable/«image.name.toLowerCase»"/>
		'''
	}
	
	def generateImageFromCompositeDisplayElement(String compositeDisplayElementName, Image image){
		'''
		 <Image
		 	android:id="@+id/«javaUtilities.getDisplayElementPrefix(image)»«compositeDisplayElementName + image.name»"
		 	android:layout_width="«image.width»dp"
		 	android:layout_height="«image.height»dp"
		 	android:background="#000000"
		 	android:scaleType="centerInside"
		 	android:contentDescription="@string/«compositeDisplayElementName.toFirstLower +image.name»"
		 	android:src="@drawable/«image.name.toLowerCase»"/>
		'''
	}
	
	def generateImageButton(ImageButton imageButton){
		'''
		 <ImageButton
		 	android:id="@+id/«javaUtilities.getDisplayElementPrefix(imageButton)»«imageButton.name»"
		 	android:layout_width="«100»dp"
		 	android:layout_height="«100»dp"
		 	android:background="#000000"
		 	android:onClick="onClick«imageButton.name»"
		 	android:scaleType="centerInside"
		 	android:src="@drawable/«imageButton.name.toLowerCase»"/>
		'''
	}
	
	def generateImageButtonFromCompositeDisplayElement(String compositeDisplayElementName, ImageButton imageButton){
		'''
		 <ImageButton
		 	android:id="@+id/«javaUtilities.getDisplayElementPrefix(imageButton)»«compositeDisplayElementName+imageButton.name»"
		 	android:layout_width="«100»dp"
		 	android:layout_height="«100»dp"
		 	android:background="#000000"
		 	android:onClick="onClick«compositeDisplayElementName+imageButton.name»"
		 	android:scaleType="centerInside"
		 	android:src="@drawable/«imageButton.name.toLowerCase»"/>
		'''
	}
	
	def generateEditTexts(List<Input> inputs){
		'''
		«FOR Input input : inputs»
			«generateEditText(input)»
        «ENDFOR»
		'''
	}
	
	def generateLocationPicker(LocationPicker locationPicker){
		'''
		'''
	}
	
	def generateLocationPicker(String compositeDisplayElementName, LocationPicker locationPicker){
		'''
		'''
	}
	
	def generateEditText(Input input){
		'''
		 <EditText
		  	android:id="@+id/«javaUtilities.getDisplayElementPrefix(input)»«input.name»"
		 	android:layout_width="100dp"
		 	android:layout_height="100dp"
		 	android:inputType="«javaUtilities.getEditTextInputType(input)»"
		 	android:focusable="true"/>
		'''
	}
	
	def generateEditTextFromCompositeDisplayElement(String compositeDisplayElementName, Input input){
		'''
		 <EditText
		  	android:id="@+id/«javaUtilities.getDisplayElementPrefix(input)»«compositeDisplayElementName + input.name»"
		 	android:layout_width="100dp"
		 	android:layout_height="100dp"
		 	android:inputType="«javaUtilities.getEditTextInputType(input)»"
		 	android:focusable="true"/>
		'''
	}
		
	def generateListView(){
		'''
		<ListView
			android:id="@android:id/list"
		    android:layout_width="match_parent"
		    android:layout_height="wrap_content"/>
		'''
	}
	
	def generateCheckBox(CheckBox checkBox){
		'''
		<CheckBox
			android:id="@+id/«javaUtilities.getDisplayElementPrefix(checkBox)»«checkBox.name»"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="«checkBox.name»" />
		'''
	}
	
}