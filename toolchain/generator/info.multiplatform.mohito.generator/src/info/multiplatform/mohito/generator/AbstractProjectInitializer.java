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
package info.multiplatform.mohito.generator;

import info.multiplatform.mohito.generator.environment.java.MohitoJavaFolderConstants;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;

import de.modagile.generator.common.JavaIoFileSystemAccess2;

/**Utility functions for accessing and initializing projects, which will receive generated code.
 * Based on previous work in Modagile Mobile. 
 * 
 * @author hgroenda
 * @author klatt
 * @author kuester
 * @author bejan
 *
 */
public abstract class AbstractProjectInitializer {
	/** Logger for this class. */
	public static final Logger logger = Logger.getLogger(AbstractProjectInitializer.class.getCanonicalName());
	/** Name of the folder used to store libraries required for the compilation of the project. */
	protected static final String LIBRARY_FOLDER = "libs";

	/** Eclipse progress monitor used when modifing projects. */
	protected IProgressMonitor monitor = new NullProgressMonitor();
	/** Eclipse dependencies of the project. */
	protected final Collection<String> eclipseDependencies = new ArrayList<String>();
	/** Plain java dependencies of the project. */
	protected final Collection<String> plainJavaDependencies = new ArrayList<String>();
	
	/**
	 * Opens a project. The project is created if it does not exist yet. The project will not have any
	 * nature yet.
	 * 
	 * @param name Name of the project.
	 * @return The project.
	 * @throws CoreException
	 */
	public IProject openProject(String name) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();

		IProject project = workspaceRoot.getProject(name);
		if (!project.exists()) {
			project.create(monitor);
		}
		project.open(monitor);
		return project;
	}

	/**Creates a folder in the project.
	 * @param project The eclipse project.
	 * @param folderName The name of the folder.
	 * @throws CoreException
	 */
	public void createFolder(IProject project, String folderName)
			throws CoreException {
		IFolder folder = project.getFolder(folderName);
		if (!folder.exists()) {
			folder.create(true, true, monitor);
		}
	}

	/**Generate the (source code) files for the generation target. Must be implemented in subclasses.
	 * @param project The project receiving the files.
	 * @param fsa Access to the file systems of the generation scripts.
	 * @param entries Explicitly required entries for the class path, e.g. library containers.
	 */
	abstract protected void addGeneratorSpecificFiles(IProject project, IFileSystemAccess fsa, List<IClasspathEntry> entries)  throws CoreException;
	
	/**Associate the natures to the project corresponding to the generation target. Must be implemented in subclasses.
	 * @param project The project receiving the natures.
	 */
	abstract protected void applyGeneratorSpecificNatures(IProject project)  throws CoreException;
	
	/**Prepares the file system access and registers the generator specific output configurations.
	 * @param fsa File system access.
	 * @see #registerGeneratorSpecificOutputConfigurations(JavaIoFileSystemAccess2)
	 */
	public void registerOutputConfigurations(JavaIoFileSystemAccess2 fsa) {
		if (fsa.getOutputConfigurations() == null) {
		     fsa.setOutputConfigurations(new LinkedHashMap<String, OutputConfiguration>());
		}
		// generic output configurations
		OutputConfiguration defaultOutPutConf = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
		defaultOutPutConf.setOutputDirectory("./");
		defaultOutPutConf.setOverrideExistingResources(true);
		fsa.addOutPutConfig(defaultOutPutConf);
		// generator-specific output configurations
		registerGeneratorSpecificOutputConfigurations(fsa);
	}
	
	/**Register the required output configurations for the generation target. Must be implemented in subclasses.
	 * @param fsa File system access.
	 */
	abstract protected void registerGeneratorSpecificOutputConfigurations(JavaIoFileSystemAccess2 fsa);
	
	/**Initializes the provided project.
	 * Calls the generator-specific methods {@link #addGeneratorSpecificFiles(IProject, IFileSystemAccess)}, {@link #addMohitoLibraries(IProject)} and {@link #applyGeneratorSpecificNatures(IProject)}. Subclasses should only modify these called methods. 
	 * 
	 * @param project
	 *            Project which should be used as generation target.
	 * @param fsa
	 *            Access to the file systems of the generation scripts. Is forwarded to called generator-specific methods.
	 * @return The created Java project
	 * @throws CoreException
	 */
	public IJavaProject initializeProject(IProject project,
			IFileSystemAccess fsa, List<String> sourceFolders) throws CoreException {
		if (!project.exists() || project == null) {
			throw new IllegalStateException("The given project must exist. Open or create a project and provide it as parameter.");
		}
		if (!project.isOpen()) {
			project.open(monitor);
		}
		addGeneratorSpecificPlainJavaDependencies();
		addGeneratorSpecificEclipseDependencies();
		if (plainJavaDependencies.size() > 0) {
			createFolder(project, AbstractProjectInitializer.LIBRARY_FOLDER);
			for (String pathToLibrary : plainJavaDependencies) {
				copyLibrary(pathToLibrary, project, true);
			}
		}
		List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		if (eclipseDependencies.size() > 0) {
			createFolder(project, "META-INF");
			// create manifest file (if it does not exist)
			IFile file = project.getFolder("META-INF").getFile("MANIFEST.MF");
			if (!file.exists()) {
				try {
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append("Manifest-Version: 1.0\n");
					stringBuilder.append("Bundle-ManifestVersion: 2\n");
					stringBuilder.append("Bundle-SymbolicName: "
							+ project.getName() + "\n");
					stringBuilder.append("Bundle-Version: 1.0.0.qualifier\n");
					stringBuilder.append("Require-Bundle:");
					Iterator<String> it = eclipseDependencies.iterator();
					String currentDependency;
					while (it.hasNext()) {
						currentDependency = it.next();
						stringBuilder.append(" " + currentDependency);
						if (it.hasNext()) {
							stringBuilder.append(",\n");
						}
					}
					stringBuilder.append("\n");
					file.create(new ByteArrayInputStream(stringBuilder
							.toString().getBytes("UTF-8")), true, monitor);
				} catch (Exception e) {
					logger.log(Level.WARNING,
							"Could not create MANIFEST.MF file.", e);
				}
			}
			// add default JRE library container 
			entries.add(JavaCore.newContainerEntry(JavaRuntime.newDefaultJREContainerPath()));
			// add plug-in dependencies container
			entries.add(JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins")));
		}
		addGeneratorSpecificFiles(project, fsa, entries);
		applyGeneratorSpecificNatures(project);

		// set binary folder
		IJavaProject javaProject = JavaCore.create(project);
		IFolder binFolder = project.getFolder(MohitoJavaFolderConstants.BIN);
		javaProject.setOutputLocation(binFolder.getFullPath(), null);

		// add sources to project class path
		List<IFolder> folders = new ArrayList<IFolder>();
		for (String folderName : sourceFolders) {
			folders.add(project.getFolder(folderName));
		}

		for (int i = 0; i < folders.size(); i++) {
			IFolder folder = folders.get(i);
			if (!folder.exists()) 
				folder.create(false, true, monitor);
			IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(folder);
			entries.add(JavaCore.newSourceEntry(root.getPath()));
		}
		
		for (String plainJavaDependency : plainJavaDependencies) {
			entries.add(JavaCore.newLibraryEntry(project.getFile(new Path(AbstractProjectInitializer.LIBRARY_FOLDER + getLibraryName(plainJavaDependency))).getFullPath(), null, null));
		}

		// write dependencies
		javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);

		return javaProject;
	}

	
	/**Template method enabling to set the eclipse dependencies required for the project.
	 * Should modify {@link #eclipseDependencies} if required or do nothing otherwise.
	 */
	abstract protected void addGeneratorSpecificEclipseDependencies();

	/**Template method enabling to set the java dependencies required for the project.
	 * Should modify {@link #plainJavaDependencies} if required or do nothing otherwise.
	 */
	abstract protected void addGeneratorSpecificPlainJavaDependencies();

	/**
	 * @param plainJavaDependency The string describing the plain java dependency (which usually resides in another plug-in).
	 * @return The name of the library itself without any path information.
	 */
	protected String getLibraryName(String plainJavaDependency) {
		int startAt = plainJavaDependency.lastIndexOf('/');
		if (startAt == -1) {
			startAt = 0;
		}
		return plainJavaDependency.substring(startAt);
	}

	/**
	 * @param plainJavaDependency The string describing the plain java dependency (which usually resides in another plug-in).
	 * @return The name of the plug-in containing the library.
	 */
	protected String getPluginName(String plainJavaDependency) {
		int endAt = plainJavaDependency.indexOf('/');
		if (endAt == -1) {
			endAt = plainJavaDependency.length();
		}
		return plainJavaDependency.substring(0,endAt);
	}

	/**
	 * @param plainJavaDependency The string describing the plain java dependency (which usually resides in another plug-in).
	 * @return The name of the path including the name of the library within the plug-in.
	 */
	protected String getPathInPlugin(String plainJavaDependency) {
		return plainJavaDependency.substring(getPluginName(plainJavaDependency).length());
	}
	
	/**Extracts an archive from a given source plug-in to the specified path in the target project.
	 * @param sourcePluginName Name of the plug-in containing the source archive.
	 * @param sourceZipFilePath Path to the ZIP file including the file itself.
	 * @param targetProject Project in which the file should be stored.
	 * @param targetPath Path within targetProject.
	 * @throws IOException
	 * @throws CoreException
	 */
	public void unpackZipFile(String sourcePluginName, String sourceZipFilePath, IProject targetProject, String targetPath) throws IOException, CoreException {
		ZipInputStream inputStream = new ZipInputStream(Platform.getBundle(sourcePluginName).getEntry(sourceZipFilePath).openStream());
		ZipEntry zipEntry = inputStream.getNextEntry();
		IFolder rootFolder = targetProject.getFolder(new Path(targetPath));
		if (!rootFolder.exists()) {
			rootFolder.create(true, true, monitor);
		}
		
		IFolder currentFolder;
		while (zipEntry != null) {
			File file = new File(rootFolder.getLocation().toString(), zipEntry.getName());
			if (!zipEntry.isDirectory()) {
				OutputStream os = null;
				try {
					os = new FileOutputStream(file);
					byte[] buffer = new byte[102400];
					while (true) {
						int len = inputStream.read(buffer);
						if (inputStream.available() == 0)
							break;
						os.write(buffer, 0, len);
					}
				} finally {
					if (null != os) {
						os.close();
					}
				}
			} else {
				currentFolder = targetProject.getFolder(new Path(targetPath+"/"+zipEntry.getName()));
				if (null != currentFolder && !currentFolder.exists()) {
					currentFolder.create(true, true, monitor);
				}
			}
			inputStream.closeEntry();
			zipEntry = inputStream.getNextEntry();
		}
	}

	/**Copies a library file from a given source plug-in to the library folder of the generated project.
	 * @param pathToLibrary Path to the source file including plug-in name, plug-in-relative paths, and name of the library.
	 * @param project Generated project.
	 * @param overwrite Flag determining if existing files should be overwritten or not.
	 */
	protected void copyLibrary(String pathToLibrary, IProject project, boolean overwrite) {
		copyFile(getPluginName(pathToLibrary), getPathInPlugin(pathToLibrary), project, AbstractProjectInitializer.LIBRARY_FOLDER, overwrite);
	}

	/**Copies a file from a given source plug-in to the specified path in the target project.
	 * @param pluginName Name of the plug-in containing the source file.
	 * @param sourcePath Path within the plug-in to the source file.
	 * @param targetProject Project in which the file should be stored.
	 * @param targetPath Path within targetProject.
	 * @param overwrite Flag determining if existing files should be overwritten or not.
	 * @return Plug-in relative name and path of the target file.
	 */
	protected String copyFile(String pluginName, String sourcePath,
			IProject targetProject, String targetPath, boolean overwrite) {
		InputStream stream = null;
		try {
			if (Platform.getBundle(pluginName) == null) {
				logger.log(Level.SEVERE, "Could not resolve required plug-in for library file: " + pluginName);
			}
			if (Platform.getBundle(pluginName)
					.getEntry(sourcePath) == null) {
				logger.log(Level.SEVERE, "Could not resolve required library file: " + pluginName + "/" + sourcePath);
			}
			stream = Platform.getBundle(pluginName)
					.getEntry(sourcePath).openStream();
		} catch (IOException e1) {
			logger.log(Level.SEVERE, "Could not access required library file", e1);
		}

		String relativeName;
		IFile file;
		if (null == targetPath || ("").equals(targetPath) || ("./").equals(targetPath)) {
			file = targetProject.getFile(getLibraryName(sourcePath));
			relativeName = "/" + getLibraryName(sourcePath);
		} else {
			IFolder folder = targetProject.getFolder(targetPath);
			file = folder.getFile(getLibraryName(sourcePath));
			relativeName = targetPath + "/" + getLibraryName(sourcePath);
		}

		try {
			if (file.exists()) {
				if (overwrite) {
					file.delete(true, monitor);
					file.create(stream, true, monitor);
				} else {
					logger.log(Level.INFO, "Did not touch existing file as overwrite is not allowed. The file was: " + file.getName() + ".");
				}
			} else {
				file.create(stream, true, monitor);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not create or overwrite file.", e);
		}
		try {
			stream.close();
		} catch (IOException e) {
		}
		return relativeName;
	}

	/**
	 * @return the eclipseDependencies
	 */
	public Collection<String> getEclipseDependencies() {
		return eclipseDependencies;
	}

	/**
	 * @return the plainJavaDependencies
	 */
	public Collection<String> getPlainJavaDependencies() {
		return plainJavaDependencies;
	}
	
}
