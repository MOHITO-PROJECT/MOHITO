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

import de.modagile.metamodel.app.ui.DisplayElement
import de.modagile.metamodel.app.ui.CompositeDisplayElement
import de.modagile.metamodel.app.ui.Button
import de.modagile.metamodel.app.ui.ImageButton
import de.modagile.metamodel.app.ui.Label
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.ui.Input
import de.modagile.metamodel.app.ui.Image
import de.modagile.metamodel.app.ui.LocationPicker
import com.google.inject.Inject
import de.modagile.metamodel.app.ui.DynamicList

/**
 * Generates all Fragem Android-Code Classes for the existing CompositeDisplayElements in the model.
 */
class FragmentTemplate {
    
    @Inject extension DatepickerAdditionsTemplate datepickerAdditions
        @Inject extension SmallUIElementsTemplate smallUIElements
    
        def private generateCompositeDisplayElementLayoutCode(CompositeDisplayElement compositeDisplayElement){
        '''
        «FOR DisplayElement displayElement : compositeDisplayElement.type.containedDisplayElements»
            «IF displayElement instanceof Button && !(displayElement instanceof ImageButton)»
                «smallUIElements.generateButtonFromCompositeDisplayElement(compositeDisplayElement.name, displayElement as Button)»
            «ELSEIF displayElement instanceof Label»
                «smallUIElements.generateLabelFromCompositeDisplayElement(compositeDisplayElement.name, displayElement as Label)»
            «ELSEIF displayElement instanceof Datepicker»
                «datepickerAdditions.generateDatePickerLayout(displayElement as Datepicker)»
            «ELSEIF displayElement instanceof Input»
                «smallUIElements.generateEditTextFromCompositeDisplayElement(compositeDisplayElement.name, displayElement as Input)»
            «ELSEIF displayElement instanceof Image»
                «smallUIElements.generateImageFromCompositeDisplayElement(compositeDisplayElement.name, displayElement as Image)»
            «ELSEIF displayElement instanceof ImageButton»
                «smallUIElements.generateImageButtonFromCompositeDisplayElement(compositeDisplayElement.name, displayElement as ImageButton)»
            «ELSEIF displayElement instanceof DynamicList»
                «smallUIElements.generateListView()»
            «ELSEIF displayElement instanceof LocationPicker»
                «smallUIElements.generateLocationPicker(compositeDisplayElement.name, displayElement as LocationPicker)»
             «ELSEIF displayElement instanceof CompositeDisplayElement»
                «generateCompositeDisplayElementLayoutCode(displayElement as CompositeDisplayElement)»
             «ELSE»
           «ENDIF»
        «ENDFOR»
        '''
    }
    
    
}