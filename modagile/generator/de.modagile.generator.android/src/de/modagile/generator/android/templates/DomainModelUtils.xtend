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

import java.util.List
import de.modagile.metamodel.app.ui.Button
import de.modagile.metamodel.app.ui.Screen
import java.util.ArrayList
import de.modagile.metamodel.app.ui.CompositeDisplayElement
import de.modagile.metamodel.app.ui.ImageButton
import de.modagile.metamodel.app.ui.Label
import de.modagile.metamodel.app.ui.DynamicList
import de.modagile.metamodel.app.ui.LocationPicker
import de.modagile.metamodel.app.ui.Datepicker
import de.modagile.metamodel.app.ui.DisplayElement
import de.modagile.metamodel.app.ui.CheckBox

class DomainModelUtils {
	
	/* returns the list of buttons that is contained in a screen. includes the list of Buttons that are contained within a composite display element.*/ 
	def public getButtons(Screen screen) {
		var List<Button> buttonList = new ArrayList<Button>();
		buttonList.addAll(screen.displayElements.filter(element| element instanceof Button).map(d | d as Button));
		val cdeList = screen.displayElements
									.filter(element | element instanceof CompositeDisplayElement)
									.map(c | c as CompositeDisplayElement);
		for (cde : cdeList) {
			buttonList.addAll(cde.type.containedDisplayElements.filter(element | element instanceof Button).map(e | e as Button));
		}
		return buttonList;
	}
	
	/* returns the list of buttons that is contained in a screen. includes the list of Buttons that are contained within a composite display element.*/ 
    def public List<CheckBox> getCheckBoxes(Screen screen) {
        var List<CheckBox> checkBoxList = new ArrayList<CheckBox>();
        checkBoxList.addAll(screen.displayElements.filter(element| element instanceof CheckBox).map(d | d as CheckBox));
        val cdeList = screen.displayElements
                                    .filter(element | element instanceof CompositeDisplayElement)
                                    .map(c | c as CompositeDisplayElement);
        for (cde : cdeList) {
            checkBoxList.addAll(cde.type.containedDisplayElements.filter(element | element instanceof CheckBox).map(e | e as CheckBox));
        }
        return checkBoxList;
    }
    
        /* returns the list of buttons that is contained in a screen. includes the list of Buttons that are contained within a composite display element.*/ 
    def public List<CheckBox> getDirectAssociatedCheckBoxes(Screen screen) {
        var List<CheckBox> checkBoxList = new ArrayList<CheckBox>();
        checkBoxList.addAll(screen.displayElements.filter(element| element instanceof CheckBox).map(d | d as CheckBox));
        return checkBoxList;
    }

	/* returns the list of image buttons that is contained in a screen. includes the list of ImageButtons that are contained within a composite display element.*/ 
	def public getImageButton(Screen screen) {
		var List<ImageButton> buttonList = new ArrayList<ImageButton>();
		buttonList.addAll(screen.displayElements.filter(element| element instanceof ImageButton).map(d | d as ImageButton));
		val cdeList = screen.displayElements
									.filter(element | element instanceof CompositeDisplayElement)
									.map(c | c as CompositeDisplayElement);
		for (cde : cdeList) {
			buttonList.addAll(cde.type.containedDisplayElements.filter(element | element instanceof ImageButton).map(e | e as ImageButton));
		}
		return buttonList;
	}

	/* returns the list of labels that is contained in a screen. includes the list of labels that are contained within a composite display element.*/ 
	def public getLabels(Screen screen) {
		var List<Label> labelList = new ArrayList<Label>();
		labelList.addAll(screen.displayElements.filter(element| element instanceof Label).map(d | d as Label));
		val cdeList = screen.displayElements
									.filter(element | element instanceof CompositeDisplayElement)
									.map(c | c as CompositeDisplayElement);
		for (cde : cdeList) {
			labelList.addAll(cde.type.containedDisplayElements.filter(element | element instanceof Label).map(e | e as Label));
		}
		return labelList;
	}

	
	/* returns the list of DynamicLists that is contained in a screen. includes the list of DynamicLists that are contained within a composite display element.*/ 
	def public getDynamicList(Screen screen) {
		var List<DynamicList> dynamicListList = new ArrayList<DynamicList>();
		dynamicListList.addAll(screen.displayElements.filter(element| element instanceof DynamicList).map(d | d as DynamicList));
		val cdeList = screen.displayElements
									.filter(element | element instanceof CompositeDisplayElement)
									.map(c | c as CompositeDisplayElement);
		for (cde : cdeList) {
			dynamicListList.addAll(cde.type.containedDisplayElements.filter(element | element instanceof DynamicList).map(e | e as DynamicList));
		}
		return dynamicListList;
	}
	
	
	/* returns the list of LocationPickers that is contained in a screen. includes the list of LocationPickers that are contained within a composite display element.*/ 
	def public getLocationPicker(Screen screen) {
		var List<LocationPicker> dynamicListList = new ArrayList<LocationPicker>();
		dynamicListList.addAll(screen.displayElements.filter(element| element instanceof LocationPicker).map(d | d as LocationPicker));
		val cdeList = screen.displayElements
									.filter(element | element instanceof CompositeDisplayElement)
									.map(c | c as CompositeDisplayElement);
		for (cde : cdeList) {
			dynamicListList.addAll(cde.type.containedDisplayElements.filter(element | element instanceof LocationPicker).map(e | e as LocationPicker));
		}
		return dynamicListList;
	}
	
	/* returns the list of Datepicker that is contained in a screen. includes the list of Datepicker that are contained within a composite display element.*/ 
	def public getDatepicker(Screen screen) {
		var List<Datepicker> dynamicListList = new ArrayList<Datepicker>();
		dynamicListList.addAll(getDirectAssociatedDatepicker(screen));
		val cdeList = screen.displayElements
									.filter(element | element instanceof CompositeDisplayElement)
									.map(c | c as CompositeDisplayElement);
		for (cde : cdeList) {
			dynamicListList.addAll(cde.type.containedDisplayElements.filter(element | element instanceof Datepicker).map(e | e as Datepicker));
		}
		return dynamicListList;
	}
	
	/* returns the list of Datepicker that is contained in a screen. includes the list of Datepicker that are contained within a composite display element.*/ 
    def public getDirectAssociatedDatepicker(Screen screen) {
        var List<Datepicker> dynamicListList = new ArrayList<Datepicker>();
        dynamicListList.addAll(screen.displayElements.filter(element| element instanceof Datepicker).map(d | d as Datepicker));
        return dynamicListList;
    }
	
	/* returns the list of ALL DisplayElements that are contained in a screen. includes the list of DisplayElements that are contained within a composite display element.*/ 
	def public getAllDisplayElements(Screen screen) {
		var List<DisplayElement> displayElements = new ArrayList<DisplayElement>();
		displayElements.addAll(screen.displayElements);
		val cdeList = screen.displayElements
									.filter(element | element instanceof CompositeDisplayElement)
									.map(c | c as CompositeDisplayElement);
		for (cde : cdeList) {
			displayElements.addAll(cde.type.containedDisplayElements);
		}
		return displayElements;
	}
}