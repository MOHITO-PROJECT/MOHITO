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

import info.multiplatform.mohito.generator.AbstractProjectInitializer;
import info.multiplatform.mohito.generator.InitializeFromRegistry.RemoteGenerator;
import info.multiplatform.mohito.generator.MohitoCodeGenerationWorkflowStarter;
import info.multiplatform.mohito.generator.MohitoGeneratorConstants;
import info.multiplatform.mohito.generator.MohitoGeneratorSetup;
import info.multiplatform.mohito.generator.InitializeFromRegistry.LocalGenerator;
import info.multiplatform.mohito.generator.IStorageGenerator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.xtext.generator.IGenerator;

import de.modagile.generator.common.JavaIoFileSystemAccess2;

/**Wizard for selecting the target generation settings, e.g. which environment, local, and remote storage is used.
 * @author hgroenda
 *
 */
public class GenerateMohitoImplementationWizard extends Wizard implements IExportWizard {
	/** Logger for this class. */
	private static final Logger logger = Logger.getLogger(GenerateMohitoImplementationWizard.class.getCanonicalName());
	/** Valid extension for the selected file when invoking the wizard. */
	private static final String validExtension = "ecore";
	
	/** Page for local storage configuration. */
	private SelectLocalStorageGeneratorWizardPage localStoragePage;
	/** Page for remote storage configuration. */
	private SelectRemoteStorageGeneratorWizardPage remoteStoragePage;
	/** Page for selecting the generation target environment. */
	private SelectProjectInitializiationWizardPage projectInitializationPage;
	
	/** Selection for which the wizard is invoked. */
	private IFile selectedEcoreFile;

	public GenerateMohitoImplementationWizard() {
		setWindowTitle("Generate MOHITO Implementation");
	}

	@Override
	public void addPages() {
		assert(selectedEcoreFile != null);
		localStoragePage = new SelectLocalStorageGeneratorWizardPage();
		remoteStoragePage = new SelectRemoteStorageGeneratorWizardPage();
		projectInitializationPage = new SelectProjectInitializiationWizardPage();
//		// Page for project settings if no annotations are used in the model.
//		String projectName = selectedEcoreFile.getName().substring(0, selectedEcoreFile.getName().length() - validExtension.length() - 1< 0 ? selectedEcoreFile.getName().length() : selectedEcoreFile.getName().length() - validExtension.length() - 1);
//		projectPage = new DefineTargetProjectWizardPage(projectName, "");
//		addPage(projectPage);
		addPage(projectInitializationPage);
		addPage(localStoragePage);
		addPage(remoteStoragePage);
	}

	@Override
	public boolean performFinish() {
		final Class<? extends IGenerator> environmentGenerator = projectInitializationPage.getSelectedTarget().generatorClass.getClass();
		final LocalGenerator localGenerator = localStoragePage.getSelectedTarget();
		final RemoteGenerator remoteGenerator = remoteStoragePage.getSelectedTarget();
		AbstractProjectInitializer tempProjectInitializer;
		try {
			tempProjectInitializer = projectInitializationPage
				.getSelectedTarget().projectInitializationClass
				.getClass().newInstance();
		} catch (InstantiationException e) {
			logger.log(Level.SEVERE, "Could not create and initialize generator.", e);
			return false;
		} catch (IllegalAccessException e) {
			logger.log(Level.SEVERE, "Could not create and initialize generator.", e);
			return false;
		}
		final AbstractProjectInitializer projectInitializer = tempProjectInitializer;
		Job job = new Job(
				"Background Job creating the environment-specific MOHITO domain model implementation.") {

			@SuppressWarnings("unused")
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					projectInitializer.getEclipseDependencies().addAll(
							localGenerator.eclipseDependency);
					projectInitializer.getEclipseDependencies().addAll(
							remoteGenerator.eclipseDependency);
					projectInitializer.getPlainJavaDependencies().addAll(
							localGenerator.plainJavaDependency);
					projectInitializer.getPlainJavaDependencies().addAll(
							remoteGenerator.plainJavaDependency);
					MohitoGeneratorSetup generatorSetup = new MohitoGeneratorSetup(
							environmentGenerator,
							(Class<? extends IStorageGenerator>) localGenerator.transformationClass
									.getClass(),
							(Class<? extends IStorageGenerator>) remoteGenerator.transformationClass
									.getClass(), projectInitializer);
					MohitoCodeGenerationWorkflowStarter starter = new MohitoCodeGenerationWorkflowStarter(
							generatorSetup.createInjectorAndDoEMFRegistration(),
							new JavaIoFileSystemAccess2());
					starter.execute(selectedEcoreFile);
					// workaround for compilation: Java does not recognize that this type of exception could be thrown.
					if (false) throw new JavaModelException(null, 0);
					return Status.OK_STATUS;
				} catch (JavaModelException jme) {
					logger.log(Level.INFO, "Modification on project did not work properly.", jme);
					return Status.OK_STATUS;
				} catch (Exception e) {
					return new Status(Status.ERROR, MohitoGeneratorConstants.PLUGIN_ID, "Code generation failed. Not all required artifacts are generated.", e);
				}
			}
			
		};
		// Start the Job in the background for keeping the UI responsive
		job.schedule();
		return true;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selectedEcoreFile = null;
		if (selection instanceof IStructuredSelection) {
			if (((IStructuredSelection)selection).size() != 1) {
				logger.warning("This wizard should only be invoked for exactly one selected .ecore-File. These restrictions must be configured in the corresponding extension definition. The definition or invocation must most probably be fixed in order to work properly.");
			} else {
				Object selectedObject = ((IStructuredSelection)selection).getFirstElement();
				if (selectedObject instanceof IFile) {
					if (((IFile)selectedObject).getFileExtension().equals(validExtension)) {
						this.selectedEcoreFile = (IFile)selectedObject;
					} else {
						logger.warning("This wizard should only be invoked for exactly one selected .ecore-File. The selection was not an .ecore-File. These restrictions must be configured in the corresponding extension definition. The definition or invocation must most probably be fixed in order to work properly.");
					}
				} else {
					logger.warning("This wizard should only be invoked for exactly one selected .ecore-File. The selection was not an IFile. These restrictions must be configured in the corresponding extension definition. The definition or invocation must most probably be fixed in order to work properly.");
				}
			}
		}
	}

}
