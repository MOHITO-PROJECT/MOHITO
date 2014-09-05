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

import de.modagile.metamodel.app.ui.Screen
import de.modagile.metamodel.app.ui.DisplayElement
import org.eclipse.xtext.generator.IFileSystemAccess
import de.modagile.metamodel.app.MobileApp

class ActivitiesLayoutTemplate {
	
	@Inject extension SmallUIElementsTemplate smallUIElements
	
	def generateLayoutForActivity(Screen activity, IFileSystemAccess fsa, String outputConfiguration, MobileApp m){
 		//Which layout style?? Linear, Relative, Table ?  -> Up to now its linear
 		fsa.generateFile("layout/" + activity.name.toLowerCase+".xml", outputConfiguration, activity.generateLayoutCode(m));
 	}
 	
 	def private generateLayoutCode(Screen screen, MobileApp m){
 		'''
 		<?xml version="1.0" encoding="utf-8"?>
 		<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 			android:layout_width="match_parent" 
 			android:layout_height="match_parent"
 			android:orientation="vertical"
 			android:paddingBottom="10dp"
 			android:paddingTop="10dp">
 		«FOR DisplayElement displayELement : screen.displayElements»
	 		«smallUIElements.generateLayoutCodeForDisplayElement(displayELement, m)»
 		«ENDFOR»
 		</LinearLayout>
 		'''
 	}
 	
}