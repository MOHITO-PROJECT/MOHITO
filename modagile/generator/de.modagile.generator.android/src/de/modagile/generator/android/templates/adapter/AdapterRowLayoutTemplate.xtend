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
package de.modagile.generator.android.templates.adapter

import com.google.inject.Inject
import de.modagile.generator.android.templates.ModagileFolderConstants
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.generator.android.templates.activity.SmallUIElementsTemplate
import de.modagile.metamodel.app.domain.ListBinding
import de.modagile.metamodel.app.ui.CompositeDisplayElementType
import de.modagile.metamodel.app.ui.DisplayElement
import de.modagile.metamodel.app.MobileApp

class AdapterRowLayoutTemplate {
    
    @Inject extension SmallUIElementsTemplate smallUIElements
	
	def generateEntityAdapterLayouts(IFileSystemAccess fsa, ListBinding listBinding,  MobileApp m){
 		fsa.generateFile("layout/" + listBinding.listElement.name.toLowerCase+"row.xml", ModagileFolderConstants::RESOURCE, 
 			generateEntityRowLayoutXML(listBinding, m)
 		);
 	}
 	
 	def private generateEntityRowLayoutXML(ListBinding listBinding, MobileApp m){
 		'''
		<?xml version="1.0" encoding="utf-8"?>
		<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
		    android:orientation="horizontal"
		    android:layout_width="match_parent"
		    android:layout_height="wrap_content">
		    «FOR CompositeDisplayElementType cdeType: listBinding.listElement.displayedElements»
    		    «FOR DisplayElement displayElement: cdeType.containedDisplayElements»
    		      «smallUIElements.generateLayoutCodeForDisplayElement(displayElement, m)»
    		    «ENDFOR»
		    «ENDFOR»
		</RelativeLayout>
 		'''
 	}
 	
}