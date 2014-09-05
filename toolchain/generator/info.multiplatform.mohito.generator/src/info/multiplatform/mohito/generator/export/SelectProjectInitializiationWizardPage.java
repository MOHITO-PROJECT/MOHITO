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
package info.multiplatform.mohito.generator.export;

import info.multiplatform.mohito.generator.InitializeFromRegistry;
import info.multiplatform.mohito.generator.InitializeFromRegistry.ProjectInitializer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**Wizard page for selecting the implementation generation the eclipse project for a target environment.
 * That project will then receive the generated code.
 * @author hgroenda
 *
 */
public class SelectProjectInitializiationWizardPage extends StatusWizardPage {
	
	/** Combo box for selecting the target environment for project initialization. */
	private Combo comboTarget;

	/**
	 * Create the wizard.
	 */
	public SelectProjectInitializiationWizardPage() {
		super("selectProjectInitializer");
		setTitle("Select Generation Target Environment");
		setDescription("Select the target environment used to initialize the eclipse project.");
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(2, false));
		
		Label lblTarget = new Label(container, SWT.NONE);
		lblTarget.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTarget.setText("Project:");
		
		comboTarget = new Combo(container, SWT.NONE);
		comboTarget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateTargetItems();
			}
		});
		comboTarget.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		for (InitializeFromRegistry.ProjectInitializer initializer : InitializeFromRegistry.GENERATOR_REGISTRY.getProjectInitializers()) {
			comboTarget.add(initializer.name);
		}
		
		if  (comboTarget.getItemCount() > 0) {
			comboTarget.select(0);
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.OK, null));
		} else {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.ERROR, "A ProjectInitializer must be selected."));
		}
		updateTargetItems();

	}

	/**Updates the items of the target combo box and according messages.
	 */
	private void updateTargetItems() {
		if (comboTarget.getSelectionIndex() == -1) {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.ERROR, "A ProjectInitializer must be selected."));
		} else {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.OK, null));
		}
	}
	
	/**
	 * @return The selected local generator for the target.
	 */
	public ProjectInitializer getSelectedTarget() {
		if (comboTarget == null || comboTarget.getSelectionIndex() == -1) {
			return null;
		} else {
			if (InitializeFromRegistry.GENERATOR_REGISTRY.getProjectInitializers().size() == comboTarget.getItemCount()) {
				// unmodified and valid relative position within list
				return InitializeFromRegistry.GENERATOR_REGISTRY.getProjectInitializers().get(comboTarget.getSelectionIndex());
			} else {
				return null;
			}
		}
	}
}
