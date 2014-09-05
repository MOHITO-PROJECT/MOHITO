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
package de.modagile.metamodel.app.ui;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Menu Bar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.MenuBar#getMenuBarElements <em>Menu Bar Elements</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.MenuBar#getScreen <em>Screen</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getMenuBar()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='onlyLabelsButtonsAndCheckBoxesAllowed'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot onlyLabelsButtonsAndCheckBoxesAllowed='self.menuBarElements->forAll(menubarItem : DisplayElement | menubarItem.oclIsTypeOf(Button) or menubarItem.oclIsTypeOf(ImageButton) or menubarItem.oclIsTypeOf(Label) or menubarItem.oclIsTypeOf(CheckBox) or menubarItem.oclIsTypeOf(Image))'"
 * @generated
 */
public interface MenuBar extends EObject {
	/**
	 * Returns the value of the '<em><b>Menu Bar Elements</b></em>' containment reference list.
	 * The list contents are of type {@link de.modagile.metamodel.app.ui.DisplayElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Menu Bar Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Menu Bar Elements</em>' containment reference list.
	 * @see de.modagile.metamodel.app.ui.UIPackage#getMenuBar_MenuBarElements()
	 * @model containment="true"
	 * @generated
	 */
	EList<DisplayElement> getMenuBarElements();

	/**
	 * Returns the value of the '<em><b>Screen</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.modagile.metamodel.app.ui.Screen#getMenuBar <em>Menu Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Screen</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Screen</em>' container reference.
	 * @see #setScreen(Screen)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getMenuBar_Screen()
	 * @see de.modagile.metamodel.app.ui.Screen#getMenuBar
	 * @model opposite="menuBar" required="true" transient="false"
	 * @generated
	 */
	Screen getScreen();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.MenuBar#getScreen <em>Screen</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Screen</em>' container reference.
	 * @see #getScreen()
	 * @generated
	 */
	void setScreen(Screen value);

} // MenuBar
