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

import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;

import de.modagile.generator.common.JavaIoFileSystemAccess2;

/**
 * Provides creation instructions for an Android project with CAS Open extensions
 * 
 * @author groenda
 */
public class CasOpenProjectInitializer extends AndroidProjectInitializer {
	/** Logger for this class. */
	public static final Logger logger = Logger.getLogger(CasOpenProjectInitializer.class.getCanonicalName());

	/** Id of this bundle. */
	public static final String PLUGIN_ID = "info.multiplatform.mohito.generator.casopen";

	/** Configuration file directory. */ 
	public static final String CONFIG_DIR = "config";
	
	@Override
	protected void addGeneratorSpecificFiles(IProject project,
			IFileSystemAccess fsa, List<IClasspathEntry> entries)
			throws CoreException {
		super.addGeneratorSpecificFiles(project, fsa, entries);
		createFolder(project, CasOpenProjectInitializer.CONFIG_DIR);
	}

	@Override
	protected void applyGeneratorSpecificNatures(IProject project)
			throws CoreException {
		super.applyGeneratorSpecificNatures(project);
		// there are no CAS Open specific natures
	}

	@Override
	protected void registerGeneratorSpecificOutputConfigurations(
			JavaIoFileSystemAccess2 fsa) {
		super.registerGeneratorSpecificOutputConfigurations(fsa);
		OutputConfiguration configOutPutConf = new OutputConfiguration(CasOpenProjectInitializer.CONFIG_DIR);
		configOutPutConf.setOutputDirectory("./" + CasOpenProjectInitializer.CONFIG_DIR);
		configOutPutConf.setOverrideExistingResources(true);
		fsa.addOutPutConfig(configOutPutConf);
	}

}
