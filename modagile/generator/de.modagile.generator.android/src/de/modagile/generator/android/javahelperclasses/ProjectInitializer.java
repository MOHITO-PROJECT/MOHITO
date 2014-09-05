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
package de.modagile.generator.android.javahelperclasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
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
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;
import org.eclipse.xtext.generator.IFileSystemAccess;

import de.modagile.generator.android.templates.configs.ProjectPropertiesTemplate;

import com.google.inject.Inject;

/**
 * Provides creation instructions for a Java/Android project
 * 
 * @author alexfrank
 */
public class ProjectInitializer {

	@Inject
	ProjectPropertiesTemplate projectPropertiesTemplate;

	private IProgressMonitor monitor = new NullProgressMonitor();

	/** Id of this bundle (the android generator plugin) */
	public static final String PLUGIN_ID = "de.modagile.generator.android";

	/** Id of the actionbarsherlock bundle (referenced from the generator) */
	public static final String ACTIONBARSHERLOCK_PLUGIN_ID = "com.actionbarsherlock";

	/**
	 * Creates a new project for the given model. The project will not have any
	 * nature yet.
	 * 
	 * @param mobileApp
	 *            Model data
	 * @return An opened project
	 * @throws CoreException
	 */
	public IProject createProject(String mobileAppName) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();

		IProject project = workspaceRoot.getProject(mobileAppName);
		if (!project.exists()) {
			project.create(monitor);
		}
		project.open(monitor);
		return project;
	}
	
	public boolean existsSherlockProject() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();

		IProject project = workspaceRoot.getProject(ACTIONBARSHERLOCK_PLUGIN_ID);
		if (project.exists()) {
			return true;
		} 
		return false;
	}

	/**
	 * Adds the Java and Android nature to the given project. Instead of the src
	 * folder, the project will have a src-man and a src-gen folder
	 * 
	 * @param project
	 *            Project which should be used as generation target
	 * @param fsa
	 *            FilesSytemAccess
	 * @return The created Java project
	 * @throws CoreException
	 */
	public IJavaProject initializeProject(IProject project,
			IFileSystemAccess fsa, List<String> sourceFolders, List<String> libs) throws CoreException {

		if (!project.exists() || project == null) {
			throw new IllegalStateException(
					"The given project must be created first.");
		}
		if (!project.isOpen()) {
			project.open(monitor);
		}
		addAndroidSepcificFiles(project, fsa);
		addActionBarSherlock(project, fsa);
		addJavaAndAndroidNature(project);

		IJavaProject javaProject = JavaCore.create(project);

		IFolder binFolder = project.getFolder("bin");
		javaProject.setOutputLocation(binFolder.getFullPath(), null);

		//List<IClasspathEntry> entries = createJavaSystemLibrary();
		List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		addAndroidLibraries(entries);

		// add sources to project class path
		List<IFolder> folders = new ArrayList<IFolder>();
		for (String folderName : sourceFolders) {
			folders.add(project.getFolder(folderName));
		}

		for (int i = 0; i < folders.size(); i++) {
			IFolder folder = folders.get(i);
			if (!folder.exists()) folder.create(false, true, monitor);
			IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(folder);
			entries.add(JavaCore.newSourceEntry(root.getPath()));
		}
		
		for (int i = 0; i < libs.size(); i++) {
			entries.add(JavaCore.newLibraryEntry(project.getFile(new Path(libs.get(i))).getFullPath(), null, null));
		}

		javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
		projectPropertiesTemplate.generateProjectPropertiesWithSherlock(fsa);
		return javaProject;
	}
	
	

	public void unpackZipFile(IProject targetProject, String zipFilePath, String targetPath) throws IOException, CoreException {
		ZipInputStream inputStream = new ZipInputStream(Platform.getBundle(PLUGIN_ID).getEntry(zipFilePath).openStream());
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

	private void addAndroidSepcificFiles(IProject project, IFileSystemAccess fsa)
			throws CoreException {
		createFolder(project, "res");
		createFolder(project, "res/drawable");
		createFolder(project, "res/layout");
		createFolder(project, "res/menu");
		createFolder(project, "res/values");
		createFolder(project, "res/xml");
		copyFile("res/drawable-mdpi", "icon.png", project, "res/drawable", false);
		// this is done twice: first without reference to com.actionbarsherlock and afterwards with reference
		projectPropertiesTemplate.generateProjectProperties(fsa);

	}

	private void addActionBarSherlock(IProject project, IFileSystemAccess fsa)
			throws CoreException {
		createFolder(project, "lib");
		copyFile("res", "android-support-v4-r6-googlemaps.jar", project, "lib", false);
		copyFile("res", "com.actionbarsherlock.jar", project, "lib", false);
		copyFile("res", "android-support-v4.jar", project, "lib", false);

	}

	private void createFolder(IProject project, String folderName)
			throws CoreException {
		IFolder folder = project.getFolder(folderName);
		if (!folder.exists()) {
			folder.create(true, true, monitor);
		}
	}
	
	/*
	 * copies from sourcePath the given sourceFile to the targetProject at the
	 * given targetPath
	 */
	public void copyFile(String sourcePath, String sourceFile,
			IProject targetProject, String targetPath, boolean overwrite) {
		copyFile(sourcePath, sourceFile, targetProject, targetPath, null, overwrite);
	}

	/*
	 * copies from sourcePath the given sourceFile to the targetProject at the
	 * given targetPath
	 */
	public void copyFile(String sourcePath, String sourceFile,
			IProject targetProject, String targetPath, String targetFile, boolean overwrite) {
		if (targetFile == null) {
			// Use source filename as target filename
			targetFile = sourceFile;
		}
		InputStream stream = null;
		try {
			stream = Platform.getBundle(PLUGIN_ID)
					.getEntry(sourcePath + "/" + sourceFile).openStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		IFile file;
		if (null == targetPath || ("").equals(targetPath) || ("./").equals(targetPath)) {
			file = targetProject.getFile(targetFile);
		} else {
			IFolder folder = targetProject.getFolder(targetPath);
			file = folder.getFile(targetFile);
		}

		try {
			if (file.exists()) {
				if (overwrite) {
					file.delete(true, monitor);
					file.create(stream, true, monitor);
				}
			} else {
				file.create(stream, true, monitor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addJavaAndAndroidNature(IProject project) throws CoreException {
		IProjectDescription description = project.getDescription();
		description.setNatureIds(new String[] {"com.android.ide.eclipse.adt.AndroidNature", JavaCore.NATURE_ID });
		project.setDescription(description, null);
	}

	private List<IClasspathEntry> createJavaSystemLibrary() {
		List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
		IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
		LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
		for (LibraryLocation element : locations) {
			entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
		}
		return entries;
	}

	private List<IClasspathEntry> addAndroidLibraries(List<IClasspathEntry> entries) {
		entries.add(JavaCore.newContainerEntry(new Path("com.android.ide.eclipse.adt.ANDROID_FRAMEWORK")));
		entries.add(JavaCore.newContainerEntry(new Path("com.android.ide.eclipse.adt.LIBRARIES")));
		return entries;
	}
	
	public void createDummyImage(String imageName, IProject project) {
		copyFile("res/drawable-hdpi", "SampleImage.ModAgileLogo.png", project, "res/drawable", imageName.toLowerCase() + ".png", false);
	}
	
}
