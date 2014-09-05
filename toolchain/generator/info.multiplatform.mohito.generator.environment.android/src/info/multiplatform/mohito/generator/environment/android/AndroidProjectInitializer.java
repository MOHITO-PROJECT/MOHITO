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
package info.multiplatform.mohito.generator.environment.android;


import info.multiplatform.generator.android.templates.AndroidFolderConstants;
import info.multiplatform.mohito.generator.AbstractProjectInitializer;
import info.multiplatform.mohito.generator.environment.java.MohitoJavaFolderConstants;

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;

import com.google.inject.Inject;

import de.modagile.generator.android.templates.configs.ProjectPropertiesTemplate;
import de.modagile.generator.common.JavaIoFileSystemAccess2;

/**
 * Provides creation instructions for an Android project
 * 
 * Derived from the ModagileMobile ProjectInitializer
 * 
 * @author groenda
 * @author Benjamin Klatt
 * @author bejan
 */
public class AndroidProjectInitializer extends AbstractProjectInitializer {
	/** Logger for this class. */
	public static final Logger logger = Logger.getLogger(AndroidProjectInitializer.class.getCanonicalName());

	@Inject
	ProjectPropertiesTemplate projectPropertiesTemplate;

	/** Id of this bundle (the android generator plugin). Used to query the plug-in and access resources. */
	public static final String PLUGIN_ID = "info.multiplatform.mohito.generator.android";

	@Override
	protected void addGeneratorSpecificFiles(IProject project,
			IFileSystemAccess fsa, List<IClasspathEntry> entries) throws CoreException {
		// create folders and Android-specific files not belonging to the model
		createFolder(project, "res");
		createFolder(project, "res/values");
		createFolder(project, "res/xml");
		// this is done twice: first without reference to com.actionbarsherlock and afterwards with reference
		// TODO: check why this is necessary
		projectPropertiesTemplate.generateProjectProperties(fsa);
		projectPropertiesTemplate.generateProjectPropertiesWithSherlock(fsa);

		// add library containers for Android libraries
		entries.add(JavaCore.newContainerEntry(new Path("com.android.ide.eclipse.adt.ANDROID_FRAMEWORK")));
		entries.add(JavaCore.newContainerEntry(new Path("com.android.ide.eclipse.adt.LIBRARIES")));
	}

	@Override
	protected void applyGeneratorSpecificNatures(IProject project) throws CoreException {
		// set Java and Android natures
		IProjectDescription description = project.getDescription();
		description.setNatureIds(new String[] {"com.android.ide.eclipse.adt.AndroidNature", JavaCore.NATURE_ID });
		project.setDescription(description, null);
	}

	@Override
	protected void registerGeneratorSpecificOutputConfigurations(
			JavaIoFileSystemAccess2 fsa) {
		OutputConfiguration resourceOutPutConf = new OutputConfiguration(AndroidFolderConstants.RESOURCE);
		resourceOutPutConf.setOutputDirectory("./res");
		resourceOutPutConf.setOverrideExistingResources(false);
		fsa.addOutPutConfig(resourceOutPutConf);

		OutputConfiguration srcOutPutConf = new OutputConfiguration(MohitoJavaFolderConstants.SRC);
		srcOutPutConf.setOutputDirectory("./src");
		srcOutPutConf.setOverrideExistingResources(true);
		fsa.addOutPutConfig(srcOutPutConf);

		OutputConfiguration srcGenOutPutConf = new OutputConfiguration(MohitoJavaFolderConstants.SRC_GEN);
		srcGenOutPutConf.setOutputDirectory("./src-gen");
		srcGenOutPutConf.setOverrideExistingResources(true);
		fsa.addOutPutConfig(srcGenOutPutConf);
	}

	@Override
	protected void addGeneratorSpecificEclipseDependencies() {
		// do nothing
	}

	@Override
	protected void addGeneratorSpecificPlainJavaDependencies() {
		getPlainJavaDependencies().add("info.multiplatform.mohito.framework/deployable/info.multiplatform.mohito.framework.jar");
		getPlainJavaDependencies().add("info.multiplatform.mohito.framework.android/deployable/info.multiplatform.mohito.framework.android.jar");
	}
}
