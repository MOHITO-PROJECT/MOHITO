/** 
 * Copyright (c) 2012-2014 MOHITO Project
 *
 * Licensed under the EUPL V.1.1
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package info.multiplatform.mohito.modeling.annotation;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**Tabbed property section for displaying and modifying the details of MOHITO-Annotation.
 * This is a convenience class providing solutions for annotations with the data types Boolean and String. The 
 * categories, which should be displayed, must be provided using {@link #getCategoryDefinitions()}.
 * 
 * @author hgroenda
 *
 */
public abstract class AbstractAnnotationPropertiesSection extends
		AbstractPropertySection {
	/** The element for which the annotations are shown and can be modified in this properties section. */
	private EModelElement selectedElement = null;
	/** SWT elements for providing settings for each annotation definition. */
	private List<Control> controlElements = null;
	/** Editing domain for issuing command, if available. */
	private TransactionalEditingDomain editingDomain;
	
	/**
	 * @return The category definitions, for which the tab is built.
	 */
	abstract protected Collection<MohitoAnnotationCategoryDefinition> getCategoryDefinitions();
	
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}
	
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		composite.setLayout(new GridLayout(2, false));
		composite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		
		controlElements = new ArrayList<Control>();
		for (MohitoAnnotationCategoryDefinition categoryDefinition : getCategoryDefinitions()) {
			for (MohitoAnnotationDefinition annotationDefinition : categoryDefinition.getMohitoAnnotationDefinitions()){
				createSectionForAnnotation(composite, annotationDefinition);
			}
		}
	}
	
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        if (input instanceof GraphicalEditPart) {
       		editingDomain = ((IGraphicalEditPart) input)
       				.getEditingDomain();
           	if (editingDomain != null){
        		input = ((GraphicalEditPart) input).getModel();
        		if (input instanceof Node) {
        			input = ((Node)input).getElement();
        		} 
        	} else {
				throw new IllegalArgumentException("No editing domain is available. You need an editing domain if you want to edit parts of the ecore editior. This can't happen for ecore diagram.");
			}
        }
        if (input instanceof EModelElement) {
        	if (input instanceof Diagram) {
        		this.selectedElement = (EModelElement) ((Diagram) input).getElement();
        	} else {
                this.selectedElement = (EModelElement) input;
        	}
        } else {
        	this.selectedElement = null;
        }
	}
	
	@Override
	public void refresh() {
		assert(controlElements != null);
		assert(editingDomain != null);
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				int counter = 0;
				for (MohitoAnnotationCategoryDefinition categoryDefinition : getCategoryDefinitions()) {
					for (MohitoAnnotationDefinition annotationDefinition : categoryDefinition.getMohitoAnnotationDefinitions()) {
						// update control for this definition
						if (MohitoAnnotationManager.hasAnnotation(selectedElement, annotationDefinition)) {
								if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN)) {
									Button button = (Button) controlElements.get(counter);
									button.setGrayed(false);
									button.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
									button.setSelection((Boolean) MohitoAnnotationManager.getAnnotation(selectedElement, annotationDefinition));
								} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_STRING)) {
									Text text = (Text) controlElements.get(counter);
									text.setBackground(null);
									text.setText((String) MohitoAnnotationManager.getAnnotation(selectedElement, annotationDefinition));
								} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_INTEGER)) {
									Text text = (Text) controlElements.get(counter);
									text.setBackground(null);
									text.setText(((Integer) MohitoAnnotationManager.getAnnotation(selectedElement, annotationDefinition)).toString());
								} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_LONG)) {
									Text text = (Text) controlElements.get(counter);
									text.setBackground(null);
									text.setText(((Long) MohitoAnnotationManager.getAnnotation(selectedElement, annotationDefinition)).toString());
								} else {
									throw new IllegalArgumentException("Target type not supported. Target type is: " + annotationDefinition.getDataType());
								}
						} else {
							if(controlElements.get(counter)!=null){
							// Mark as undefined
								if (controlElements.get(counter) instanceof Button) {
								// Third state: UNDEFINED / NO_ANNOTATION
								((Button)controlElements.get(counter)).setGrayed(true);
								((Button)controlElements.get(counter)).setSelection(true);
								} else if ( controlElements.get(counter) instanceof Text)
								{
								controlElements.get(counter).setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
								((Text)controlElements.get(counter)).setText("");
								} else {
									throw new IllegalArgumentException("The number of controls do not match the number of annotations. Every annotation has exactly one control.");
								}
							}
						}
						// mark fields inactive, which are not applicable to the selection
						if (annotationDefinition.getScope().equals(MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_ECLASS)) {
							controlElements.get(counter).setEnabled(selectedElement instanceof EClass);
						} else if (annotationDefinition.getScope().equals(MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE)) {
							controlElements.get(counter).setEnabled(selectedElement instanceof EAttribute);
						} else if (annotationDefinition.getScope().equals(MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EPACKAGE)) {
							controlElements.get(counter).setEnabled(selectedElement instanceof EPackage);
						} else if (annotationDefinition.getScope().equals(MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EENUM)) {
							controlElements.get(counter).setEnabled(selectedElement instanceof EEnum);
						} else {
							throw new IllegalArgumentException("This implementation does not support the provided scope definition. Scope definition is: " + annotationDefinition.getScope());
						}
						counter++;
					}
				}
			}
		});
	}
	
	/**Creates 
	 * @param composite
	 * @param data
	 */
	private void createSectionForAnnotation(Composite composite, final MohitoAnnotationDefinition annotationDefinition) {
		assert(controlElements != null);
		assert(annotationDefinition != null);
		assert (composite != null);
		// Create label
		Label labelEntry = new Label(composite, SWT.LEFT);
		labelEntry.setText(annotationDefinition.getName());
		labelEntry.setToolTipText(annotationDefinition.getDescription());
		labelEntry.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		// Create control
		if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN)) {
			final Button button = getWidgetFactory().createButton(composite, null, SWT.CHECK);
			button.setToolTipText(annotationDefinition.getDescription());
			button.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					// 3-state button cyclic behavior: UNDEFINED/NO_ANNOTATION, SELECTED, NOTSELECTED
					if (MohitoAnnotationManager.hasAnnotation(selectedElement, annotationDefinition)
							&& MohitoAnnotationManager.getAnnotation(selectedElement, annotationDefinition) == Boolean.FALSE
							&& button.getSelection() == Boolean.TRUE) {
						button.setGrayed(true);
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								MohitoAnnotationManager.removeAnnotation(selectedElement, annotationDefinition);
							}
						});
					} else {
						if (button.getGrayed()) { // UNDEFINED/NO_ANNOTATION -> Selected
							button.setGrayed(false);
							button.setSelection(true);
						}
						editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

							@Override
							protected void doExecute() {
								MohitoAnnotationManager.setAnnotation(selectedElement, annotationDefinition, button.getSelection());
							}
						});
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// do nothing
				}
			});
			controlElements.add(button);
		} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_STRING)) {
			final Text text = getWidgetFactory().createText(composite, annotationDefinition.getDefaultValue());
			text.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));
			text.setToolTipText(annotationDefinition.getDescription());
			text.addKeyListener(new KeyListener() {
				
				@Override
				public void keyReleased(KeyEvent e) {
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							if (text.getText().equals(annotationDefinition.getDefaultValue())) {
								text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
								MohitoAnnotationManager.removeAnnotation(selectedElement, annotationDefinition);
							} else {
								text.setBackground(null);
								MohitoAnnotationManager.setAnnotation(selectedElement, annotationDefinition, text.getText());
							}
						}
					});
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// do nothing
				}
			});
			controlElements.add(text);
		} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_INTEGER)) {
			final Text text = getWidgetFactory().createText(composite, annotationDefinition.getDefaultValue());
			text.setToolTipText(annotationDefinition.getDescription());
			text.addKeyListener(new KeyListener() {
				
				@Override
				public void keyReleased(KeyEvent e) {
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							if (text.getText().equals(annotationDefinition.getDefaultValue())) {
								text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
								MohitoAnnotationManager.removeAnnotation(selectedElement, annotationDefinition);
							} else {
								text.setBackground(null);
								try {
									MohitoAnnotationManager.setAnnotation(selectedElement, annotationDefinition, Integer.parseInt(text.getText()));
								} catch (NumberFormatException nfe) {
									// do nothing, invalid values are not stored as annotation
								}
							}
						}
					});
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// do nothing
				}
			});
			controlElements.add(text);
		} else if (annotationDefinition.getDataType().equals(MohitoAnnotationsDefinitionsConstants.DATA_TYPE_LONG)) {
			final Text text = getWidgetFactory().createText(composite, annotationDefinition.getDefaultValue());
			text.setToolTipText(annotationDefinition.getDescription());
			text.addKeyListener(new KeyListener() {
				
				@Override
				public void keyReleased(KeyEvent e) {
					editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							if (text.getText().equals(annotationDefinition.getDefaultValue())) {
								text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
								MohitoAnnotationManager.removeAnnotation(selectedElement, annotationDefinition);
							} else {
								text.setBackground(null);
								try {
									MohitoAnnotationManager.setAnnotation(selectedElement, annotationDefinition, Long.parseLong(text.getText()));
								} catch (NumberFormatException nfe) {
									// do nothing, invalid values are not stored as annotation
								}
							}
						}
					});
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// do nothing
				}
			});
			controlElements.add(text);
		} else {
			throw new IllegalArgumentException("Target type not supported. Target type is: " + annotationDefinition.getDataType());
		}
	}

}
