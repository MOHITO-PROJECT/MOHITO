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
import info.multiplatform.mohito.generator.InitializeFromRegistry.LocalGenerator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class SelectLocalStorageGeneratorWizardPage extends StatusWizardPage {
	/** Combo box for target selection. */
	private Combo comboTarget;

	/**
	 * Create the wizard.
	 */
	public SelectLocalStorageGeneratorWizardPage() {
		super("selectLocalStorageWizardPage");
		setTitle("Select Local Storage");
		setDescription("Select the generator target used for accessing local storage.");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(2, false));
		
		Label lblTarget = new Label(container, SWT.NONE);
		lblTarget.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTarget.setText("Target:");
		
		comboTarget = new Combo(container, SWT.NONE);
		comboTarget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateTargetItems();
			}
		});
		comboTarget.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		for (InitializeFromRegistry.LocalGenerator generator : InitializeFromRegistry.GENERATOR_REGISTRY.getLocalGenerators()) {
			comboTarget.add(generator.name);
		}
		if  (comboTarget.getItemCount() > 0) {
			comboTarget.select(0);
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.OK, null));
		} else {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.ERROR, "A local target must be selected."));
		}
		updateTargetItems();
	}
	
	/**
	 * @return The selected local generator for the target.
	 */
	public LocalGenerator getSelectedTarget() {
		if (comboTarget == null || comboTarget.getSelectionIndex() == -1) {
			return null;
		} else {
			if (InitializeFromRegistry.GENERATOR_REGISTRY.getLocalGenerators().size() == comboTarget.getItemCount()) {
				// unmodified and valid relative position within list
				return InitializeFromRegistry.GENERATOR_REGISTRY.getLocalGenerators().get(comboTarget.getSelectionIndex());
			} else {
				return null;
			}
		}
	}

	/**Update the status for the target items.
	 */
	private void updateTargetItems() {
		if (comboTarget.getSelectionIndex() == -1) {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.ERROR, "A local target must be selected."));
		} else {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.OK, null));
		}
	}
}
