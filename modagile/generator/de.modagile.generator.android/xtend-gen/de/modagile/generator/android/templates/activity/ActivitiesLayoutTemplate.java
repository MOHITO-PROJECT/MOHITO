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
package de.modagile.generator.android.templates.activity;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.activity.SmallUIElementsTemplate;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.Screen;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class ActivitiesLayoutTemplate {
  @Inject
  @Extension
  private SmallUIElementsTemplate smallUIElements;
  
  public void generateLayoutForActivity(final Screen activity, final IFileSystemAccess fsa, final String outputConfiguration, final MobileApp m) {
    String _name = activity.getName();
    String _lowerCase = _name.toLowerCase();
    String _plus = ("layout/" + _lowerCase);
    String _plus_1 = (_plus + ".xml");
    CharSequence _generateLayoutCode = this.generateLayoutCode(activity, m);
    fsa.generateFile(_plus_1, outputConfiguration, _generateLayoutCode);
  }
  
  private CharSequence generateLayoutCode(final Screen screen, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:layout_width=\"match_parent\" ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:layout_height=\"match_parent\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:orientation=\"vertical\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:paddingBottom=\"10dp\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("android:paddingTop=\"10dp\">");
    _builder.newLine();
    {
      EList<DisplayElement> _displayElements = screen.getDisplayElements();
      for(final DisplayElement displayELement : _displayElements) {
        CharSequence _generateLayoutCodeForDisplayElement = this.smallUIElements.generateLayoutCodeForDisplayElement(displayELement, m);
        _builder.append(_generateLayoutCodeForDisplayElement, "");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</LinearLayout>");
    _builder.newLine();
    return _builder;
  }
}
