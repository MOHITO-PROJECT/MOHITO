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
package info.multiplatform.mohito.generator.environment.java;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import de.modagile.generator.common.JavaIoFileSystemAccess2;
import info.multiplatform.generator.java.helper.JavaFormatter;
import info.multiplatform.mohito.generator.AbstractProjectInitializer;
import info.multiplatform.mohito.generator.environment.java.AnnotationUtils;
import info.multiplatform.mohito.generator.environment.java.DomainModelTemplate;
import info.multiplatform.mohito.generator.environment.java.JavaUtils;
import info.multiplatform.mohito.generator.environment.java.MohitoJavaFolderConstants;
import info.multiplatform.mohito.generator.environment.java.PackageInfo;
import info.multiplatform.mohito.generator.environment.java.UtilitiesTemplate;
import info.multiplatform.mohito.modeling.annotation.generator.GeneratorMohitoAnnotationCategory;
import java.util.ArrayList;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Generates the domain model-specific code for the Java platform.
 */
@SuppressWarnings("all")
public class MohitoModelGenerator implements IGenerator {
  /**
   * Utilities for generating Java code.
   */
  @Inject
  @Extension
  private JavaUtils javaUtils;
  
  /**
   * Utilities for MOHITO-Models and annotations.
   */
  @Inject
  @Extension
  private AnnotationUtils annotationUtilities;
  
  /**
   * The implementation for initializing an eclipse project and starting the generation of code for MOHITO-Entities.
   */
  @Inject
  @Extension
  private AbstractProjectInitializer projectInitializer;
  
  /**
   * Generator for the domain-specific model.
   */
  @Inject
  @Extension
  private DomainModelTemplate modelTemplate;
  
  /**
   * Generator for the utilities-package of MOHITO-Models.
   */
  @Inject
  @Extension
  private UtilitiesTemplate utilitiesTemplate;
  
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    boolean _or = false;
    EList<EObject> _contents = input.getContents();
    int _size = _contents.size();
    boolean _notEquals = (_size != 1);
    if (_notEquals) {
      _or = true;
    } else {
      EList<EObject> _contents_1 = input.getContents();
      final Function1<EObject,Boolean> _function = new Function1<EObject,Boolean>() {
          public Boolean apply(final EObject it) {
            return Boolean.valueOf((it instanceof EPackage));
          }
        };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(_contents_1, _function);
      boolean _equals = Objects.equal(_findFirst, null);
      _or = (_notEquals || _equals);
    }
    if (_or) {
      URI _uRI = input.getURI();
      String _plus = ("The model file with the URI " + _uRI);
      String _plus_1 = (_plus + " must contain exactly one element of type EPackage as root.");
      IllegalArgumentException _illegalArgumentException = new IllegalArgumentException(_plus_1);
      throw _illegalArgumentException;
    }
    EList<EObject> _contents_2 = input.getContents();
    for (final EObject o : _contents_2) {
      if ((o instanceof EPackage)) {
        this.generateArtifactsForModel(((EPackage) o), fsa);
      }
    }
  }
  
  /**
   * Generates artifacts for the given model.
   * 
   * @param domainModel The MOHITO-Model.
   * @param fsa Access to the file system used as output for generated files.
   */
  public void generateArtifactsForModel(final EPackage domainModel, final IFileSystemAccess fsa) {
    try {
      String _name = domainModel.getName();
      boolean _contains = _name.contains(".");
      if (_contains) {
        String _name_1 = domainModel.getName();
        String _plus = ("The domain model name must not contain a \'.\'. The offending name is " + _name_1);
        String _plus_1 = (_plus + ".");
        IllegalArgumentException _illegalArgumentException = new IllegalArgumentException(_plus_1);
        throw _illegalArgumentException;
      }
      String packagePrefix = this.annotationUtilities.getValueForAnnotationAsString(domainModel, GeneratorMohitoAnnotationCategory.PACKAGE_NAME);
      String projectName = this.annotationUtilities.getValueForAnnotationAsString(domainModel, GeneratorMohitoAnnotationCategory.TARGET_PROJECT_NAME);
      boolean _equals = projectName.equals("");
      if (_equals) {
        projectName = "MohitoProject";
      }
      final JavaIoFileSystemAccess2 jfsa = ((JavaIoFileSystemAccess2) fsa);
      this.projectInitializer.registerOutputConfigurations(jfsa);
      final IProject project = this.projectInitializer.openProject(projectName);
      IProject _project = project.getProject();
      jfsa.setProject(_project);
      ArrayList<String> _arrayList = new ArrayList<String>();
      final ArrayList<String> sourceFolders = _arrayList;
      String _lowerCase = MohitoJavaFolderConstants.SRC.toLowerCase();
      sourceFolders.add(_lowerCase);
      String _lowerCase_1 = MohitoJavaFolderConstants.SRC_GEN.toLowerCase();
      sourceFolders.add(_lowerCase_1);
      final IJavaProject javaProject = this.projectInitializer.initializeProject(project, fsa, sourceFolders);
      IProject _project_1 = javaProject.getProject();
      jfsa.setProject(_project_1);
      String _packageNameToFolder = this.javaUtils.packageNameToFolder(packagePrefix);
      PackageInfo _packageInfo = new PackageInfo(packagePrefix, _packageNameToFolder);
      final PackageInfo packageInfo = _packageInfo.append(MohitoJavaFolderConstants.MODEL);
      this.modelTemplate.generateDomainEntityArtifacts(fsa, domainModel, packageInfo);
      PackageInfo _append = packageInfo.append(MohitoJavaFolderConstants.MODEL_UTIL);
      this.utilitiesTemplate.generateUtilityArtifacts(fsa, _append, domainModel);
      JavaFormatter _javaFormatter = new JavaFormatter();
      _javaFormatter.formatAll(javaProject);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
