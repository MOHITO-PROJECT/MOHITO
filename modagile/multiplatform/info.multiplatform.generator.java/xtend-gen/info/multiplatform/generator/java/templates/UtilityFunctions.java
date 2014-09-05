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
package info.multiplatform.generator.java.templates;

import info.multiplatform.generator.java.helper.Pair;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class UtilityFunctions {
  public CharSequence commaSeparated(final String prefix, final List<String> list) {
    CharSequence _xblockexpression = null;
    {
      boolean second = false;
      StringConcatenation _builder = new StringConcatenation();
      String _xifexpression = null;
      int _size = list.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        _xifexpression = prefix;
      }
      _builder.append(_xifexpression, "");
      _builder.newLineIfNotEmpty();
      {
        for(final String s : list) {
          String _xifexpression_1 = null;
          if (second) {
            _xifexpression_1 = ",";
          }
          _builder.append(_xifexpression_1, "");
          _builder.append("  ");
          _builder.newLineIfNotEmpty();
          _builder.append(s, "");
          _builder.newLineIfNotEmpty();
          boolean bugHelperVariable = second = false;
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
        }
      }
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateSetterGetterForAdditionalAttributes(final List<Pair<String,String>> pairs) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Pair<String,String> pair : pairs) {
        _builder.append("public void set");
        String _secondElement = pair.getSecondElement();
        String _firstUpper = StringExtensions.toFirstUpper(_secondElement);
        _builder.append(_firstUpper, "");
        _builder.append("(");
        String _firstElement = pair.getFirstElement();
        _builder.append(_firstElement, "");
        _builder.append(" ");
        String _secondElement_1 = pair.getSecondElement();
        _builder.append(_secondElement_1, "");
        _builder.append("){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("this.");
        String _secondElement_2 = pair.getSecondElement();
        _builder.append(_secondElement_2, "	");
        _builder.append(" = ");
        String _secondElement_3 = pair.getSecondElement();
        _builder.append(_secondElement_3, "	");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
        _builder.newLine();
        _builder.append("public ");
        String _firstElement_1 = pair.getFirstElement();
        _builder.append(_firstElement_1, "");
        _builder.append(" get");
        String _secondElement_4 = pair.getSecondElement();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_secondElement_4);
        _builder.append(_firstUpper_1, "");
        _builder.append("(){");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("return this.");
        String _secondElement_5 = pair.getSecondElement();
        _builder.append(_secondElement_5, "	");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
}
