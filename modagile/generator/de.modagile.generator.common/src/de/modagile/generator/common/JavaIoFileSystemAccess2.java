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
package de.modagile.generator.common;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;

/**
 * @author tlutz
 * @author kuester
 */
public class JavaIoFileSystemAccess2 extends JavaIoFileSystemAccess {

    private IProject project;
    private IProgressMonitor monitor;

    public JavaIoFileSystemAccess2() {
        // ensure the output field is inititalized correctly.
        this.setOutputConfigurations(new LinkedHashMap<String, OutputConfiguration>());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.xtext.generator.IFileSystemAccess#generateFile(java.lang. String, java.lang.String,
     * java.lang.CharSequence)
     */
    @Override
	public void generateFile(String fileName, String outputConfigName,
			CharSequence contents) {
		OutputConfiguration outputConfig = getOutputConfig(outputConfigName);
		IFile file = project.getFile(outputConfig.getOutputDirectory() + '/'
				+ fileName);
		try {
			if (!file.exists()) {
				createRecursively(file.getParent());
				file.create(new ByteArrayInputStream(contents.toString()
						.getBytes(Charset.defaultCharset())), true, monitor);
			} else { // file already exists
				if (outputConfig.isOverrideExistingResources()) {
					createRecursively(file.getParent());
					file.delete(true, monitor);
					file.create(new ByteArrayInputStream(contents.toString()
							.getBytes(Charset.defaultCharset())), true, monitor);
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

    private void createRecursively(IContainer container) throws CoreException {
        if (container.exists()) {
            return;
        }
        if (!container.getParent().exists()) {
            createRecursively(container.getParent());
        }
        IFolder folder = (IFolder) container;
        folder.create(true, true, monitor);
    }

    protected IFolder getIFolder(OutputConfiguration outputConfig) {
        return project.getFolder(new Path(outputConfig.getOutputDirectory()));
    }

    public void setProject(IProject project) {
        this.project = project;
    }

    protected void ensureParentExists(IFile file) throws CoreException {
        if (!file.exists()) {
            ensureExists(file.getParent());
        }
    }

    protected void ensureExists(IContainer container) throws CoreException {
        if (container.exists()) {
            return;
        }
        if (container instanceof IFolder) {
            ensureExists(container.getParent());
            ((IFolder) container).create(true, true, monitor);
        }
    }

    public void addOutPutConfig(OutputConfiguration newOutputConf) {
        if (super.getOutputConfigurations().get(newOutputConf.getName()) == null)
        	super.getOutputConfigurations().put(newOutputConf.getName(), newOutputConf);
    }

}
