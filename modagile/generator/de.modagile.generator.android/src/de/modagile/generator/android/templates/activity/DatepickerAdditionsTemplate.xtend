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
import de.modagile.generator.android.templates.java.JavaUtils
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.ui.UIFactory
import de.modagile.metamodel.app.ui.ImageButton
import de.modagile.metamodel.app.ui.DisplayElement
import info.multiplatform.generator.java.helper.Triple
import java.util.ArrayList
import java.util.List
import de.modagile.metamodel.app.ui.ImageButton

/**
 * Creates layout and source code for a datepicker.
 */

class DatepickerAdditionsTemplate {
	
		@Inject extension de.modagile.generator.android.templates.java.JavaUtils javaUtilities
		@Inject extension SmallUIElementsTemplate smallUIElements
	
	 
	def generateDatepickerAdditions(String containerName,  List<Datepicker>  datepickers){
		var List<Triple<String, String, String>> datePickerAttributes = new ArrayList<Triple<String, String, String>>();
		datePickerAttributes.add(new Triple<String, String, String>("DateFormat","fmtDate", "DateFormat.getDateInstance()"));
		datePickerAttributes.add(new Triple<String, String, String>("Context","ctx", "this"));
		'''
		«javaUtilities.generateAdditionalAttributesWithExpression(datePickerAttributes.get(0), false, false)»
		«javaUtilities.generateAdditionalAttributesWithExpression(datePickerAttributes.get(1), false, false)»
		 '''
	}
	
	def public generateOnDateSetListener(List<Datepicker> datepickers){
        '''    
	     «FOR Datepicker datepicker : datepickers»
            «generateOnDateSet(datepicker)»
        «ENDFOR»
	    '''
	}
	
	def generateDatePickerLayout(Datepicker datepicker) {
	    var ImageButton imageButton = UIFactory::eINSTANCE.createImageButton;
	    imageButton.setName(datepicker.name);
	    			'''
		  <EditText
		android:id="@+id/«getDatePickerEditTxtID(datepicker)»"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:layout_weight="1.57"
		android:editable="false"
		android:focusable="false"
		android:inputType="date">
		</EditText>
		«smallUIElements.generateImageButton(imageButton)»
		'''
	}
	
	def generateOnDateSet(Datepicker datePicker){
	    val String datePickerName = datePicker.name;
		'''
		onDateSetListener«datePicker.name» = new DatePickerDialog.OnDateSetListener() {
		@Override
			public void onDateSet(DatePicker view, int y, int m, int d) {
		
				// set the date in the date picker
				«(datePicker as DisplayElement).displayElementType.toFirstLower»«datePickerName».set(Calendar.YEAR, y);
				«(datePicker as DisplayElement).displayElementType.toFirstLower»«datePickerName».set(Calendar.MONTH, m);
				«(datePicker as DisplayElement).displayElementType.toFirstLower»«datePickerName».set(Calendar.DAY_OF_MONTH, d);
		
		
				// we will need the UI elements
				EditText «getDatePickerEditTxtID(datePicker)» = (EditText) findViewById(R.id.«getDatePickerEditTxtID(datePicker)»);
		
				// set the content for the UI elements
				«getDatePickerEditTxtID(datePicker)».setText(fmtDate.format(«(datePicker as DisplayElement).displayElementType.toFirstLower»«datePickerName».getTime()));
		
				Log.d(TAG, "date set on saving: " + String.valueOf(«(datePicker as DisplayElement).displayElementType.toFirstLower»«datePickerName».getTimeInMillis()));
			}
	   };
		'''
	}
	
	
	def generateShowDatePickerDialog(Datepicker datePicker){
		'''
		/**
		 * This will pop up the Date Picker Dialog, and let the user enter the date in a convenient way.
		 * 
		 * @param view
		 */
		public void show«datePicker.name»DatePickerDialog(View view) {

			// this is a hidden UI element that holds the long value of the selected date
				«(datePicker as DisplayElement).displayElementType.toFirstLower»«datePicker.name».setTimeInMillis(System.currentTimeMillis());
		
			// show the date picker dialog
			DatePickerDialog «datePicker.name.toFirstLower»dialog = new DatePickerDialog(this, onDateSetListener«datePicker.name», «(datePicker as DisplayElement).displayElementType.toFirstLower»«datePicker.name».get(Calendar.YEAR),
			      «(datePicker as DisplayElement).displayElementType.toFirstLower»«datePicker.name».get(Calendar.MONTH), «(datePicker as DisplayElement).displayElementType.toFirstLower»«datePicker.name».get(Calendar.DAY_OF_MONTH));
			«datePicker.name.toFirstLower»dialog.show();
		}
		'''
	}
	
	def private getDatePickerEditTxtID(Datepicker datepicker){
	    '''
	    editText«datepicker.name»
	    '''
	}
	
}