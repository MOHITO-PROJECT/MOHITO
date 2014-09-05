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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Datepicker</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.modagile.metamodel.app.ui.Datepicker#getMaxDate <em>Max Date</em>}</li>
 *   <li>{@link de.modagile.metamodel.app.ui.Datepicker#getMinDate <em>Min Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.modagile.metamodel.app.ui.UIPackage#getDatepicker()
 * @model
 * @generated
 */
public interface Datepicker extends TextContaining, Picker {
	/**
	 * Returns the value of the '<em><b>Max Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Max Date</em>' attribute.
	 * @see #setMaxDate(String)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getDatepicker_MaxDate()
	 * @model
	 * @generated
	 */
	String getMaxDate();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Datepicker#getMaxDate <em>Max Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Max Date</em>' attribute.
	 * @see #getMaxDate()
	 * @generated
	 */
	void setMaxDate(String value);

	/**
	 * Returns the value of the '<em><b>Min Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Date</em>' attribute.
	 * @see #setMinDate(String)
	 * @see de.modagile.metamodel.app.ui.UIPackage#getDatepicker_MinDate()
	 * @model
	 * @generated
	 */
	String getMinDate();

	/**
	 * Sets the value of the '{@link de.modagile.metamodel.app.ui.Datepicker#getMinDate <em>Min Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Date</em>' attribute.
	 * @see #getMinDate()
	 * @generated
	 */
	void setMinDate(String value);

} // Datepicker
