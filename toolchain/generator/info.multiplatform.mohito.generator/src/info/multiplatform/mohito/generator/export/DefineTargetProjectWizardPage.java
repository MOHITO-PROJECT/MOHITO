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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**Wizard page for providing details on the eclipse project, which is used as a generation target.
 * @author hgroenda
 *
 */
public class DefineTargetProjectWizardPage extends StatusWizardPage {
	private Text txtProjectName;
	private Text txtPackageName;
	private String defaultProjectName;
	private String defaultPackageName;

	/**
	 * Create the wizard.
	 */
	public DefineTargetProjectWizardPage(String defaultProjectName, String defaultPackageName) {
		super("defineTargetProjectWizardPage");
		setTitle("Define Target Project");
		setDescription("Provide details for the project receiving the generated implementation.");
		if (defaultPackageName == null) {
			this.defaultPackageName = "";
		} else {
			this.defaultPackageName = defaultPackageName;
		}
		if (defaultProjectName == null) {
			this.defaultProjectName = "";
		} else {
			this.defaultProjectName = defaultProjectName;
		}
	}
	

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(2, false));
		
		Label lblProjectName = new Label(container, SWT.NONE);
		lblProjectName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProjectName.setText("Project name:");
		
		txtProjectName = new Text(container, SWT.BORDER);
		txtProjectName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (txtProjectName.getText().isEmpty()) {
					updateStatus(txtProjectName, new WidgetEntryStatus(IStatus.ERROR, "Project name must not be empty"));
				} else {
					updateStatus(txtProjectName, new WidgetEntryStatus(IStatus.OK, null));
				}
			}
		});
		txtProjectName.setToolTipText("Name of the eclipse project, in which the generated files are stored.");
		txtProjectName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtProjectName.setText(defaultProjectName);
		
		Label lblPackageForModel = new Label(container, SWT.NONE);
		lblPackageForModel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPackageForModel.setText("Package for model:");
		
		txtPackageName = new Text(container, SWT.BORDER);
		txtPackageName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateStatus(txtPackageName, new WidgetEntryStatus(IStatus.OK, null));
			}
		});
		txtPackageName.setToolTipText("Namespace or package used for the model elements.");
		txtPackageName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtPackageName.setText(defaultPackageName);
	}
	
	/**
	 * @return The selected project name.
	 */
	public String getProjectName() {
		return txtProjectName.getText();
	}
	
	/**
	 * @return The selected package name.
	 */
	public String getPackageName() {
		return txtPackageName.getText();
	}
}
