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

import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.ui.Screen
import org.eclipse.xtext.generator.IFileSystemAccess
import info.multiplatform.generator.java.templates.PackageInfo

class ManifestTemplate {
	
	
	def generateAndroidManifestFile(IFileSystemAccess fsa, MobileApp app, PackageInfo packageInfo){
		fsa.generateFile("AndroidManifest.xml", IFileSystemAccess::DEFAULT_OUTPUT, generateManifestCode(app, packageInfo));
	}
	
	def generateManifestCode(MobileApp app, PackageInfo packageInfo){
		'''
		<?xml version="1.0" encoding="utf-8"?>
		<!--
		     Define the application's version via a code for the Android Market (integer, should be manually incremented)
		     and a version name which is the version intended for the end-user
		-->
		<manifest xmlns:android="http://schemas.android.com/apk/res/android"
		package="«packageInfo.packageName»"
		android:versionCode="12"
		android:versionName="1.0.1">

		<!-- For Internet access -->
		<uses-permission android:name="android.permission.INTERNET"/>

		<!-- For network and GPS location -->
		<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
		<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
		<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

		<!-- For the account manager -->
		<uses-permission android:name="android.permission.GET_ACCOUNTS"/>
		<uses-permission android:name="android.permission.MANAGE_ACCOUNTS"/>
		<uses-permission android:name="android.permission.AUTHENTICATE_ACCOUNTS"/>

		<!-- For data synchronization -->
		<uses-permission android:name="android.permission.READ_SYNC_SETTINGS"/>
		<uses-permission android:name="android.permission.WRITE_SYNC_SETTINGS"/>
		
		 <!-- define the application icon and the application name via resources -->
		<uses-sdk android:minSdkVersion="11"/>
		<application
			android:icon="@drawable/icon"
			android:label="@string/app_name"
			android:theme="@style/Theme.Sherlock" >
			<uses-library
				android:name="com.google.android.maps"
				android:required="true"/>
			<uses-library android:name="android.test.runner"/>
		
		«IF app.storyBoard != null»
		 «FOR Screen screen : app.storyBoard.screens»
			   <activity
			      android:label="@string/«screen.name.toFirstUpper»"
			   android:name=".activity.«screen.name.toFirstUpper»Activity">
			   </activity>
			    <activity
                  android:label="@string/«screen.name.toFirstUpper»"
               android:name=".activity.«screen.name.toFirstUpper»HookActivity">
                  «IF screen.equals(app.storyBoard.startScreen)»
                  <intent-filter>
                        <action android:name="android.intent.action.MAIN"/>
                        <category android:name="android.intent.category.LAUNCHER"/>
                  </intent-filter>
                  «ENDIF»
               </activity>
		 «ENDFOR»
		«ENDIF»
		
		<!-- Define the ContentProvider by the class inside the package and it's authority -->
		<provider
		 	android:authorities="«packageInfo.packageName».«app.name.toFirstUpper»Provider"
			android:name=".contentprovider.«app.name.toFirstUpper»ContentProvider">
		</provider>
		
		 </application>
		</manifest>

		'''
	}
}