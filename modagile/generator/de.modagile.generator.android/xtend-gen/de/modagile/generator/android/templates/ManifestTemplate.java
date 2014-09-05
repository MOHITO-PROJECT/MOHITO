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
package de.modagile.generator.android.templates;

import com.google.common.base.Objects;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import info.multiplatform.generator.java.templates.PackageInfo;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ManifestTemplate {
  public void generateAndroidManifestFile(final IFileSystemAccess fsa, final MobileApp app, final PackageInfo packageInfo) {
    CharSequence _generateManifestCode = this.generateManifestCode(app, packageInfo);
    fsa.generateFile("AndroidManifest.xml", IFileSystemAccess.DEFAULT_OUTPUT, _generateManifestCode);
  }
  
  public CharSequence generateManifestCode(final MobileApp app, final PackageInfo packageInfo) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<!--");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("Define the application\'s version via a code for the Android Market (integer, should be manually incremented)");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("and a version name which is the version intended for the end-user");
    _builder.newLine();
    _builder.append("-->");
    _builder.newLine();
    _builder.append("<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"");
    _builder.newLine();
    _builder.append("package=\"");
    String _packageName = packageInfo.getPackageName();
    _builder.append(_packageName, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    _builder.append("android:versionCode=\"12\"");
    _builder.newLine();
    _builder.append("android:versionName=\"1.0.1\">");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<!-- For Internet access -->");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.INTERNET\"/>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<!-- For network and GPS location -->");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\"/>");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.ACCESS_COARSE_LOCATION\"/>");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\"/>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<!-- For the account manager -->");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.GET_ACCOUNTS\"/>");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.MANAGE_ACCOUNTS\"/>");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.AUTHENTICATE_ACCOUNTS\"/>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<!-- For data synchronization -->");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.READ_SYNC_SETTINGS\"/>");
    _builder.newLine();
    _builder.append("<uses-permission android:name=\"android.permission.WRITE_SYNC_SETTINGS\"/>");
    _builder.newLine();
    _builder.newLine();
    _builder.append(" ");
    _builder.append("<!-- define the application icon and the application name via resources -->");
    _builder.newLine();
    _builder.append("<uses-sdk android:minSdkVersion=\"11\"/>");
    _builder.newLine();
    _builder.append("<application");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:icon=\"@drawable/icon\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:label=\"@string/app_name\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:theme=\"@style/Theme.Sherlock\" >");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<uses-library");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("android:name=\"com.google.android.maps\"");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("android:required=\"true\"/>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<uses-library android:name=\"android.test.runner\"/>");
    _builder.newLine();
    _builder.newLine();
    {
      StoryBoard _storyBoard = app.getStoryBoard();
      boolean _notEquals = (!Objects.equal(_storyBoard, null));
      if (_notEquals) {
        {
          StoryBoard _storyBoard_1 = app.getStoryBoard();
          EList<Screen> _screens = _storyBoard_1.getScreens();
          for(final Screen screen : _screens) {
            _builder.append("<activity");
            _builder.newLine();
            _builder.append("   ");
            _builder.append("android:label=\"@string/");
            String _name = screen.getName();
            String _firstUpper = StringExtensions.toFirstUpper(_name);
            _builder.append(_firstUpper, "   ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("android:name=\".activity.");
            String _name_1 = screen.getName();
            String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
            _builder.append(_firstUpper_1, "");
            _builder.append("Activity\">");
            _builder.newLineIfNotEmpty();
            _builder.append("</activity>");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("<activity");
            _builder.newLine();
            _builder.append("                  ");
            _builder.append("android:label=\"@string/");
            String _name_2 = screen.getName();
            String _firstUpper_2 = StringExtensions.toFirstUpper(_name_2);
            _builder.append(_firstUpper_2, "                  ");
            _builder.append("\"");
            _builder.newLineIfNotEmpty();
            _builder.append("               ");
            _builder.append("android:name=\".activity.");
            String _name_3 = screen.getName();
            String _firstUpper_3 = StringExtensions.toFirstUpper(_name_3);
            _builder.append(_firstUpper_3, "               ");
            _builder.append("HookActivity\">");
            _builder.newLineIfNotEmpty();
            {
              StoryBoard _storyBoard_2 = app.getStoryBoard();
              Screen _startScreen = _storyBoard_2.getStartScreen();
              boolean _equals = screen.equals(_startScreen);
              if (_equals) {
                _builder.append("                  ");
                _builder.append("<intent-filter>");
                _builder.newLine();
                _builder.append("                  ");
                _builder.append("      ");
                _builder.append("<action android:name=\"android.intent.action.MAIN\"/>");
                _builder.newLine();
                _builder.append("                  ");
                _builder.append("      ");
                _builder.append("<category android:name=\"android.intent.category.LAUNCHER\"/>");
                _builder.newLine();
                _builder.append("                  ");
                _builder.append("</intent-filter>");
                _builder.newLine();
              }
            }
            _builder.append("               ");
            _builder.append("</activity>");
            _builder.newLine();
          }
        }
      }
    }
    _builder.newLine();
    _builder.append("<!-- Define the ContentProvider by the class inside the package and it\'s authority -->");
    _builder.newLine();
    _builder.append("<provider");
    _builder.newLine();
    _builder.append(" \t");
    _builder.append("android:authorities=\"");
    String _packageName_1 = packageInfo.getPackageName();
    _builder.append(_packageName_1, " 	");
    _builder.append(".");
    String _name_4 = app.getName();
    String _firstUpper_4 = StringExtensions.toFirstUpper(_name_4);
    _builder.append(_firstUpper_4, " 	");
    _builder.append("Provider\"");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("android:name=\".contentprovider.");
    String _name_5 = app.getName();
    String _firstUpper_5 = StringExtensions.toFirstUpper(_name_5);
    _builder.append(_firstUpper_5, "	");
    _builder.append("ContentProvider\">");
    _builder.newLineIfNotEmpty();
    _builder.append("</provider>");
    _builder.newLine();
    _builder.newLine();
    _builder.append(" ");
    _builder.append("</application>");
    _builder.newLine();
    _builder.append("</manifest>");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
