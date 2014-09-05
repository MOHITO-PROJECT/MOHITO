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
import de.modagile.metamodel.app.ui.MenuBar
import de.modagile.metamodel.app.ui.Screen
import de.modagile.metamodel.app.ui.StoryBoard
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.metamodel.app.ui.Label
import de.modagile.metamodel.app.ui.Button
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.ui.Input
import de.modagile.metamodel.app.ui.Image
import de.modagile.metamodel.app.ui.ImageButton
import de.modagile.metamodel.app.ui.DynamicList
import de.modagile.metamodel.app.ui.LocationPicker
import de.modagile.metamodel.app.ui.CompositeDisplayElement
import de.modagile.metamodel.app.ui.TextContaining

/**
 * 
 * The existing ocl constraint in modagile.ecore allows only Images, Buttons, Labels, ImageButtons and Checkboxes in the Menubar.
 * Grouping is not supported and also not planned to be in future.
 */
class MenuLayoutTemplate {
    
    def generateMenuLayouts( IFileSystemAccess fsa, String outputConfiguration, StoryBoard storyBoard ) { 
        for(Screen screen: storyBoard.screens){
            if(screen.menuBar != null){
                fsa.generateFile("menu/"+ screen.name.toLowerCase+"_menu.xml", outputConfiguration, generateMenuForScreen(screen.menuBar));
            }
        }
    }
    
    def private generateMenuForScreen(MenuBar menuBar){
        '''
        <?xml version="1.0" encoding="utf-8"?>
        <menu xmlns:android="http://schemas.android.com/apk/res/android">
        «FOR DisplayElement displayElement: menuBar.menuBarElements»
            «generateDisplayElementMenuItem(displayElement, menuBar, null)»
        «ENDFOR»
        </menu>
        '''
    }
    
    
    def private generateDisplayElementMenuItem(DisplayElement menuItemDisplayElement, MenuBar menuBar, CompositeDisplayElement compositeDisplayElement){
        '''
        «IF menuItemDisplayElement instanceof Button || menuItemDisplayElement instanceof Datepicker || menuItemDisplayElement instanceof ImageButton  || menuItemDisplayElement instanceof DynamicList || menuItemDisplayElement instanceof LocationPicker»
            «generateItemWithAction(menuItemDisplayElement, menuBar)»
        «ELSEIF menuItemDisplayElement instanceof Label || menuItemDisplayElement instanceof Input || menuItemDisplayElement instanceof Image »
            «generateItemWithoutAction(menuItemDisplayElement, menuBar)»
        «ENDIF»
        '''
    }
    
    def private generateItemWithAction(DisplayElement displayElement, MenuBar menuBar){
        '''
          «generateItemID(displayElement, menuBar)»
          «generateItemTitle(displayElement)»
          «generateDrawableIfNeeded(displayElement)»
          android:showAsAction="ifRoom|collapseActionView"
          «generateActionView(displayElement)»/>
        '''
    }
    
    def private generateItemWithoutAction(DisplayElement displayElement, MenuBar menuBar){
        '''
        «generateItemID(displayElement, menuBar)»
        «generateItemTitle(displayElement)»
        «generateDrawableIfNeeded(displayElement)»/>
        '''
    }
    
    def private generateItemTitle(DisplayElement displayElement){
        '''
         «IF displayElement instanceof TextContaining»
            «IF displayElement instanceof Label»
            android:title="@string/textView«displayElement.name.toFirstUpper»"
            «ELSEIF displayElement instanceof Button»
            android:title="@string/button«displayElement.name.toFirstUpper»"
            «ELSEIF displayElement instanceof Image»
             android:title="@string/img«displayElement.name.toFirstUpper»"
            «ENDIF»
        «ENDIF»
        '''
    }
    
    def private generateDrawableIfNeeded(DisplayElement displayElement){
        '''
        «IF displayElement instanceof ImageButton || displayElement instanceof Image»
         android:icon="@drawable/«displayElement.name.toLowerCase»"
        «ENDIF»
        '''
    }
    
    def private generateItemID(DisplayElement displayElement, MenuBar menuBar){
        '''
         <item android:id="@+id/menu_item_«menuBar.screen.name»_«displayElement.name.toLowerCase»"
        '''
    }
    
    def private generateActionView(DisplayElement displayElement){
        '''
        «IF displayElement instanceof ImageButton»
        android:actionViewClass="android.widget.ImageButton"
        «ELSEIF displayElement instanceof Button»
        android:actionViewClass="android.widget.Button"
        «ENDIF»
        '''
    }
    
}