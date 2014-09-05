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
package de.modagile.generator.android.templates.adapter;

import com.google.inject.Inject;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.activity.SmallUIElementsTemplate;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.ListBinding;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.DynamicList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class AdapterRowLayoutTemplate {
  @Inject
  @Extension
  private SmallUIElementsTemplate smallUIElements;
  
  public void generateEntityAdapterLayouts(final IFileSystemAccess fsa, final ListBinding listBinding, final MobileApp m) {
    DynamicList _listElement = listBinding.getListElement();
    String _name = _listElement.getName();
    String _lowerCase = _name.toLowerCase();
    String _plus = ("layout/" + _lowerCase);
    String _plus_1 = (_plus + "row.xml");
    CharSequence _generateEntityRowLayoutXML = this.generateEntityRowLayoutXML(listBinding, m);
    fsa.generateFile(_plus_1, ModagileFolderConstants.RESOURCE, _generateEntityRowLayoutXML);
  }
  
  private CharSequence generateEntityRowLayoutXML(final ListBinding listBinding, final MobileApp m) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
    _builder.newLine();
    _builder.append("<RelativeLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("android:orientation=\"horizontal\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("android:layout_width=\"match_parent\"");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("android:layout_height=\"wrap_content\">");
    _builder.newLine();
    {
      DynamicList _listElement = listBinding.getListElement();
      EList<CompositeDisplayElementType> _displayedElements = _listElement.getDisplayedElements();
      for(final CompositeDisplayElementType cdeType : _displayedElements) {
        {
          EList<DisplayElement> _containedDisplayElements = cdeType.getContainedDisplayElements();
          for(final DisplayElement displayElement : _containedDisplayElements) {
            CharSequence _generateLayoutCodeForDisplayElement = this.smallUIElements.generateLayoutCodeForDisplayElement(displayElement, m);
            _builder.append(_generateLayoutCodeForDisplayElement, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("</RelativeLayout>");
    _builder.newLine();
    return _builder;
  }
}
