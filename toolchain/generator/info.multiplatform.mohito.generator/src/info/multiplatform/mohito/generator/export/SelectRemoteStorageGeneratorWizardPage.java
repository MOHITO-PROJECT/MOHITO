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
import info.multiplatform.mohito.generator.MohitoGeneratorConstants;
import info.multiplatform.mohito.generator.InitializeFromRegistry.RemoteGenerator;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**Wizard page for selecting the generator used for the remote storage.
 * @author hgroenda
 *
 */
public class SelectRemoteStorageGeneratorWizardPage extends StatusWizardPage {
	/** Combo box for selection of the target role. */
	private Combo comboRole;
	/** Combo box for selection of the target generator. */
	private Combo comboTarget;

	/**
	 * Create the wizard.
	 */
	public SelectRemoteStorageGeneratorWizardPage() {
		super("selectRemoteStorageWizardPage");
		setTitle("Select Remote Storage");
		setDescription("Select the generator target used for accessing remote storage.");
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
		updateTargetItems();
		
		Label lblRole = new Label(container, SWT.NONE);
		lblRole.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRole.setText("Role: ");
		
		comboRole = new Combo(container, SWT.NONE);
		comboRole.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateTargetRoleItems();
			}
		});
		comboRole.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboRole.add(MohitoGeneratorConstants.ACCESS_ROLE_CLIENT);
		comboRole.add(MohitoGeneratorConstants.ACCESS_ROLE_SERVER);
		comboRole.select(0);
		updateTargetRoleItems();
	}

	/**
	 * @return The selected remote generator for the target.
	 */
	public RemoteGenerator getSelectedTarget() {
		if (comboRole == null || comboTarget == null || comboTarget.getSelectionIndex() == -1) {
			return null;
		} else {
			if (comboRole.getText().equals(MohitoGeneratorConstants.ACCESS_ROLE_CLIENT)) {
				if (InitializeFromRegistry.GENERATOR_REGISTRY.getRemoteClientGenerators().size() == comboTarget.getItemCount()) {
					return InitializeFromRegistry.GENERATOR_REGISTRY.getRemoteClientGenerators().get(comboTarget.getSelectionIndex());
				}
			} else if (comboRole.getText().equals(MohitoGeneratorConstants.ACCESS_ROLE_SERVER)) {
				if (InitializeFromRegistry.GENERATOR_REGISTRY.getRemoteServerGenerators().size() == comboTarget.getItemCount()) {
					return InitializeFromRegistry.GENERATOR_REGISTRY.getRemoteServerGenerators().get(comboTarget.getSelectionIndex());
				}
			}
		}
		return null;
	}

	/**Updates the target role items for the given role selection.
	 * 
	 */
	private void updateTargetRoleItems() {
		comboTarget.removeAll();
		// get list of available generators
		List<RemoteGenerator> availableGenerators = null;
		if (comboRole.getText().equals(MohitoGeneratorConstants.ACCESS_ROLE_CLIENT)) {
			availableGenerators = InitializeFromRegistry.GENERATOR_REGISTRY.getRemoteClientGenerators();
		} else if (comboRole.getText().equals(MohitoGeneratorConstants.ACCESS_ROLE_SERVER)) {
			availableGenerators = InitializeFromRegistry.GENERATOR_REGISTRY.getRemoteServerGenerators();
		} else {
			updateStatus(comboRole, new WidgetEntryStatus(IStatus.ERROR, "You must either select a client or server role."));
		}
		// update combo for target selection
		for (RemoteGenerator generator : availableGenerators) {
			comboTarget.add(generator.name);
		}
		if (comboTarget.getItemCount() > 0) {
			comboTarget.select(0);
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.OK, null));
		} else {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.ERROR, "You must select a target."));
		}
		updateStatus(comboRole, new WidgetEntryStatus(IStatus.OK, null));
	}

	/**Updates the items of the target combo box and according messages.
	 */
	private void updateTargetItems() {
		if (comboTarget.getSelectionIndex() == -1) {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.ERROR, "You must select a target."));
		} else {
			updateStatus(comboTarget, new WidgetEntryStatus(IStatus.OK, null));
		}
	}
	
}
