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
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.metamodel.app.ui.Datepicker;
import de.modagile.metamodel.app.ui.DisplayElement;
import de.modagile.metamodel.app.ui.ImageButton;
import de.modagile.metamodel.app.ui.UIFactory;
import info.multiplatform.generator.java.helper.Triple;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Creates layout and source code for a datepicker.
 */
@SuppressWarnings("all")
public class DatepickerAdditionsTemplate {
  @Inject
  @Extension
  private JavaUtils javaUtilities;
  
  @Inject
  @Extension
  private SmallUIElementsTemplate smallUIElements;
  
  public CharSequence generateDatepickerAdditions(final String containerName, final List<Datepicker> datepickers) {
    CharSequence _xblockexpression = null;
    {
      ArrayList<Triple<String,String,String>> _arrayList = new ArrayList<Triple<String,String,String>>();
      List<Triple<String,String,String>> datePickerAttributes = _arrayList;
      Triple<String,String,String> _triple = new Triple<String,String,String>("DateFormat", "fmtDate", "DateFormat.getDateInstance()");
      datePickerAttributes.add(_triple);
      Triple<String,String,String> _triple_1 = new Triple<String,String,String>("Context", "ctx", "this");
      datePickerAttributes.add(_triple_1);
      StringConcatenation _builder = new StringConcatenation();
      Triple<String,String,String> _get = datePickerAttributes.get(0);
      CharSequence _generateAdditionalAttributesWithExpression = this.javaUtilities.generateAdditionalAttributesWithExpression(_get, false, false);
      _builder.append(_generateAdditionalAttributesWithExpression, "");
      _builder.newLineIfNotEmpty();
      Triple<String,String,String> _get_1 = datePickerAttributes.get(1);
      CharSequence _generateAdditionalAttributesWithExpression_1 = this.javaUtilities.generateAdditionalAttributesWithExpression(_get_1, false, false);
      _builder.append(_generateAdditionalAttributesWithExpression_1, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateOnDateSetListener(final List<Datepicker> datepickers) {
    StringConcatenation _builder = new StringConcatenation();
    {
      for(final Datepicker datepicker : datepickers) {
        CharSequence _generateOnDateSet = this.generateOnDateSet(datepicker);
        _builder.append(_generateOnDateSet, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence generateDatePickerLayout(final Datepicker datepicker) {
    CharSequence _xblockexpression = null;
    {
      ImageButton imageButton = UIFactory.eINSTANCE.createImageButton();
      String _name = datepicker.getName();
      imageButton.setName(_name);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("  ");
      _builder.append("<EditText");
      _builder.newLine();
      _builder.append("android:id=\"@+id/");
      CharSequence _datePickerEditTxtID = this.getDatePickerEditTxtID(datepicker);
      _builder.append(_datePickerEditTxtID, "");
      _builder.append("\"");
      _builder.newLineIfNotEmpty();
      _builder.append("android:layout_width=\"wrap_content\"");
      _builder.newLine();
      _builder.append("android:layout_height=\"wrap_content\"");
      _builder.newLine();
      _builder.append("android:layout_weight=\"1.57\"");
      _builder.newLine();
      _builder.append("android:editable=\"false\"");
      _builder.newLine();
      _builder.append("android:focusable=\"false\"");
      _builder.newLine();
      _builder.append("android:inputType=\"date\">");
      _builder.newLine();
      _builder.append("</EditText>");
      _builder.newLine();
      CharSequence _generateImageButton = this.smallUIElements.generateImageButton(imageButton);
      _builder.append(_generateImageButton, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateOnDateSet(final Datepicker datePicker) {
    CharSequence _xblockexpression = null;
    {
      final String datePickerName = datePicker.getName();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("onDateSetListener");
      String _name = datePicker.getName();
      _builder.append(_name, "");
      _builder.append(" = new DatePickerDialog.OnDateSetListener() {");
      _builder.newLineIfNotEmpty();
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void onDateSet(DatePicker view, int y, int m, int d) {");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("// set the date in the date picker");
      _builder.newLine();
      _builder.append("\t\t");
      String _displayElementType = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
      String _firstLower = StringExtensions.toFirstLower(_displayElementType);
      _builder.append(_firstLower, "		");
      _builder.append(datePickerName, "		");
      _builder.append(".set(Calendar.YEAR, y);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      String _displayElementType_1 = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
      String _firstLower_1 = StringExtensions.toFirstLower(_displayElementType_1);
      _builder.append(_firstLower_1, "		");
      _builder.append(datePickerName, "		");
      _builder.append(".set(Calendar.MONTH, m);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      String _displayElementType_2 = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
      String _firstLower_2 = StringExtensions.toFirstLower(_displayElementType_2);
      _builder.append(_firstLower_2, "		");
      _builder.append(datePickerName, "		");
      _builder.append(".set(Calendar.DAY_OF_MONTH, d);");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("// we will need the UI elements");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("EditText ");
      CharSequence _datePickerEditTxtID = this.getDatePickerEditTxtID(datePicker);
      _builder.append(_datePickerEditTxtID, "		");
      _builder.append(" = (EditText) findViewById(R.id.");
      CharSequence _datePickerEditTxtID_1 = this.getDatePickerEditTxtID(datePicker);
      _builder.append(_datePickerEditTxtID_1, "		");
      _builder.append(");");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("// set the content for the UI elements");
      _builder.newLine();
      _builder.append("\t\t");
      CharSequence _datePickerEditTxtID_2 = this.getDatePickerEditTxtID(datePicker);
      _builder.append(_datePickerEditTxtID_2, "		");
      _builder.append(".setText(fmtDate.format(");
      String _displayElementType_3 = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
      String _firstLower_3 = StringExtensions.toFirstLower(_displayElementType_3);
      _builder.append(_firstLower_3, "		");
      _builder.append(datePickerName, "		");
      _builder.append(".getTime()));");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("Log.d(TAG, \"date set on saving: \" + String.valueOf(");
      String _displayElementType_4 = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
      String _firstLower_4 = StringExtensions.toFirstLower(_displayElementType_4);
      _builder.append(_firstLower_4, "		");
      _builder.append(datePickerName, "		");
      _builder.append(".getTimeInMillis()));");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t   ");
      _builder.append("};");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence generateShowDatePickerDialog(final Datepicker datePicker) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This will pop up the Date Picker Dialog, and let the user enter the date in a convenient way.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param view");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public void show");
    String _name = datePicker.getName();
    _builder.append(_name, "");
    _builder.append("DatePickerDialog(View view) {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// this is a hidden UI element that holds the long value of the selected date");
    _builder.newLine();
    _builder.append("\t\t");
    String _displayElementType = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
    String _firstLower = StringExtensions.toFirstLower(_displayElementType);
    _builder.append(_firstLower, "		");
    String _name_1 = datePicker.getName();
    _builder.append(_name_1, "		");
    _builder.append(".setTimeInMillis(System.currentTimeMillis());");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// show the date picker dialog");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("DatePickerDialog ");
    String _name_2 = datePicker.getName();
    String _firstLower_1 = StringExtensions.toFirstLower(_name_2);
    _builder.append(_firstLower_1, "	");
    _builder.append("dialog = new DatePickerDialog(this, onDateSetListener");
    String _name_3 = datePicker.getName();
    _builder.append(_name_3, "	");
    _builder.append(", ");
    String _displayElementType_1 = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
    String _firstLower_2 = StringExtensions.toFirstLower(_displayElementType_1);
    _builder.append(_firstLower_2, "	");
    String _name_4 = datePicker.getName();
    _builder.append(_name_4, "	");
    _builder.append(".get(Calendar.YEAR),");
    _builder.newLineIfNotEmpty();
    _builder.append("\t      ");
    String _displayElementType_2 = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
    String _firstLower_3 = StringExtensions.toFirstLower(_displayElementType_2);
    _builder.append(_firstLower_3, "	      ");
    String _name_5 = datePicker.getName();
    _builder.append(_name_5, "	      ");
    _builder.append(".get(Calendar.MONTH), ");
    String _displayElementType_3 = this.javaUtilities.getDisplayElementType(((DisplayElement) datePicker));
    String _firstLower_4 = StringExtensions.toFirstLower(_displayElementType_3);
    _builder.append(_firstLower_4, "	      ");
    String _name_6 = datePicker.getName();
    _builder.append(_name_6, "	      ");
    _builder.append(".get(Calendar.DAY_OF_MONTH));");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _name_7 = datePicker.getName();
    String _firstLower_5 = StringExtensions.toFirstLower(_name_7);
    _builder.append(_firstLower_5, "	");
    _builder.append("dialog.show();");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence getDatePickerEditTxtID(final Datepicker datepicker) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("editText");
    String _name = datepicker.getName();
    _builder.append(_name, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
