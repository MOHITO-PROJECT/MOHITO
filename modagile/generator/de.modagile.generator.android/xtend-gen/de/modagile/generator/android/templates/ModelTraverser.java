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
package de.modagile.generator.android.templates;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import de.modagile.generator.android.javahelperclasses.ProjectInitializer;
import de.modagile.generator.android.templates.EcoreModelUtils;
import de.modagile.generator.android.templates.ManifestTemplate;
import de.modagile.generator.android.templates.ModagileFolderConstants;
import de.modagile.generator.android.templates.StringFileTemplate;
import de.modagile.generator.android.templates.activity.ActivitiesHooksTemplate;
import de.modagile.generator.android.templates.activity.ActivitiesLayoutTemplate;
import de.modagile.generator.android.templates.activity.ActivitiesTemplate;
import de.modagile.generator.android.templates.activity.MenuLayoutTemplate;
import de.modagile.generator.android.templates.activity.OnClickHookTemplate;
import de.modagile.generator.android.templates.adapter.AdapterRowLayoutTemplate;
import de.modagile.generator.android.templates.adapter.ListAdapterTemplate;
import de.modagile.generator.android.templates.adapter.ListAdapterViewHolderImplTemplate;
import de.modagile.generator.android.templates.constants.AppConstantsTemplate;
import de.modagile.generator.android.templates.constants.DomainEntityConstantsTemplate;
import de.modagile.generator.android.templates.contentprovider.ContentProviderTemplate;
import de.modagile.generator.android.templates.contentprovider.DatabaseHelperTemplate;
import de.modagile.generator.android.templates.contentprovider.IndicatorConstantsTemplate;
import de.modagile.generator.android.templates.converter.ModelConverterTemplate;
import de.modagile.generator.android.templates.java.JavaUtils;
import de.modagile.generator.android.templates.manager.DomainEntityManagerImplTemplate;
import de.modagile.generator.android.templates.manager.DomainEntityManagerTemplate;
import de.modagile.generator.android.templates.manager.EntityManagerTemplate;
import de.modagile.generator.android.templates.model.ModelTemplate;
import de.modagile.generator.android.templates.task.AsyncCallbackTemplate;
import de.modagile.generator.android.templates.task.CreateDomainEntityTemplate;
import de.modagile.generator.android.templates.task.DeleteDomainEntity;
import de.modagile.generator.android.templates.task.GetAllDomainEntities;
import de.modagile.generator.android.templates.task.GetDomainEntityByUri;
import de.modagile.generator.android.templates.task.GetDomainEntityByUuid;
import de.modagile.generator.android.templates.task.UpdateDomainEntity;
import de.modagile.generator.common.JavaIoFileSystemAccess2;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.domain.DomainBinding;
import de.modagile.metamodel.app.domain.ListBinding;
import de.modagile.metamodel.app.generatorconfig.GeneratorConfig;
import de.modagile.metamodel.app.generatorconfig.GeneratorConfigContainer;
import de.modagile.metamodel.app.generatorconfig.PlatformType;
import de.modagile.metamodel.app.ui.Flow;
import de.modagile.metamodel.app.ui.Image;
import de.modagile.metamodel.app.ui.Screen;
import de.modagile.metamodel.app.ui.StoryBoard;
import info.multiplatform.generator.java.helper.JavaFormatter;
import info.multiplatform.generator.java.templates.PackageInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * Contains activity specific generator methods and calls all activity sub de.modagile.generator.android.templates
 */
@SuppressWarnings("all")
public class ModelTraverser implements IGenerator {
  @Inject
  @Extension
  private ActivitiesLayoutTemplate activitiesLayoutFunktions;
  
  @Inject
  @Extension
  private AdapterRowLayoutTemplate adapterRowLayoutFunktions;
  
  @Inject
  @Extension
  private ListAdapterTemplate listAdapterTemplate;
  
  @Inject
  @Extension
  private ListAdapterViewHolderImplTemplate listAdapterViewHolderImplTemplate;
  
  @Inject
  @Extension
  private ModelTemplate modelTemplate;
  
  @Inject
  @Extension
  private DomainEntityConstantsTemplate constantsTemplates;
  
  @Inject
  @Extension
  private DomainEntityManagerTemplate domainEntityManagerTemplate;
  
  @Inject
  @Extension
  private DomainEntityManagerImplTemplate domainEntityManagerImplTemplate;
  
  @Inject
  @Extension
  private EntityManagerTemplate entityManagerTemplate;
  
  @Inject
  @Extension
  private DatabaseHelperTemplate databaseHelperTemplate;
  
  @Inject
  @Extension
  private ModelConverterTemplate modelConverterTemplate;
  
  @Inject
  @Extension
  private AsyncCallbackTemplate asyncCallbackTemplate;
  
  @Inject
  @Extension
  private CreateDomainEntityTemplate createDomainEntityTemplate;
  
  @Inject
  @Extension
  private GetAllDomainEntities getAllDomainEntities;
  
  @Inject
  @Extension
  private GetDomainEntityByUri getDomainEntityByUri;
  
  @Inject
  @Extension
  private GetDomainEntityByUuid getDomainEntityByUuid;
  
  @Inject
  @Extension
  private DeleteDomainEntity deleteDomainEntity;
  
  @Inject
  @Extension
  private UpdateDomainEntity updateDomainEntity;
  
  @Inject
  @Extension
  private ContentProviderTemplate contentProviderTemplate;
  
  @Inject
  @Extension
  private IndicatorConstantsTemplate contentProviderConstantsTemplate;
  
  @Inject
  @Extension
  private ActivitiesTemplate activitiesTemplate;
  
  @Inject
  @Extension
  private ActivitiesHooksTemplate activitiesHookTemplate;
  
  @Inject
  @Extension
  private AppConstantsTemplate appConstantsTemplate;
  
  @Inject
  @Extension
  private OnClickHookTemplate onClickHookTemplate;
  
  @Inject
  @Extension
  private StringFileTemplate stringfunction;
  
  @Inject
  @Extension
  private ManifestTemplate manifestfuctions;
  
  @Inject
  @Extension
  private JavaUtils javaUtils;
  
  @Inject
  @Extension
  private EcoreModelUtils ecoreModelUtils;
  
  @Inject
  @Extension
  private ProjectInitializer projectInitializer;
  
  @Inject
  @Extension
  private MenuLayoutTemplate menuLayoutTemplate;
  
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    this.createSherlockProject(fsa);
    EList<EObject> _contents = input.getContents();
    for (final EObject o : _contents) {
      this.compile(o, fsa);
    }
  }
  
  public void createSherlockProject(final IFileSystemAccess fsa) {
    try {
      boolean _existsSherlockProject = this.projectInitializer.existsSherlockProject();
      if (_existsSherlockProject) {
        return;
      }
      final IProject sherlockProject = this.projectInitializer.createProject(ProjectInitializer.ACTIONBARSHERLOCK_PLUGIN_ID);
      final JavaIoFileSystemAccess2 jfsa = ((JavaIoFileSystemAccess2) fsa);
      this.configureFileSystemAccess(jfsa);
      jfsa.setProject(sherlockProject);
      ArrayList<String> _arrayList = new ArrayList<String>();
      final ArrayList<String> sourceFolders = _arrayList;
      sourceFolders.add("src");
      sourceFolders.add("gen");
      ArrayList<String> _arrayList_1 = new ArrayList<String>();
      final ArrayList<String> libs = _arrayList_1;
      libs.add("./lib/android-support-v4.jar");
      this.projectInitializer.initializeProject(sherlockProject, fsa, sourceFolders, libs);
      this.projectInitializer.unpackZipFile(sherlockProject, "res/actionbarsherlock-src.zip", "src");
      this.projectInitializer.unpackZipFile(sherlockProject, "res/actionbarsherlock-res.zip", "res");
      this.projectInitializer.copyFile("res", "AndroidManifest.xml", sherlockProject, null, true);
      this.projectInitializer.copyFile("res", "project.properties", sherlockProject, null, true);
      sherlockProject.refreshLocal(IFile.DEPTH_INFINITE, null);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void _compile(final MobileApp m, final IFileSystemAccess fsa) {
    try {
      GeneratorConfigContainer _generatorConfigContainer = m.getGeneratorConfigContainer();
      EList<GeneratorConfig> _generatorConfigs = _generatorConfigContainer.getGeneratorConfigs();
      for (final GeneratorConfig config : _generatorConfigs) {
        PlatformType _targetPlatform = config.getTargetPlatform();
        boolean _equals = Objects.equal(_targetPlatform, PlatformType.ANDROID);
        if (_equals) {
          final JavaIoFileSystemAccess2 jfsa = ((JavaIoFileSystemAccess2) fsa);
          this.configureFileSystemAccess(jfsa);
          String _projectName = config.getProjectName();
          final IProject project = this.projectInitializer.createProject(_projectName);
          IProject _project = project.getProject();
          jfsa.setProject(_project);
          ArrayList<String> _arrayList = new ArrayList<String>();
          final ArrayList<String> sourceFolders = _arrayList;
          sourceFolders.add("src-gen");
          sourceFolders.add("src-man");
          sourceFolders.add("gen");
          ArrayList<String> _arrayList_1 = new ArrayList<String>();
          final ArrayList<String> libs = _arrayList_1;
          libs.add("./lib/com.actionbarsherlock.jar");
          final IJavaProject javaProject = this.projectInitializer.initializeProject(project, fsa, sourceFolders, libs);
          IProject _project_1 = javaProject.getProject();
          jfsa.setProject(_project_1);
          String _namespace = config.getNamespace();
          String _namespace_1 = config.getNamespace();
          String _packageNameToFolder = this.javaUtils.packageNameToFolder(_namespace_1);
          PackageInfo _packageInfo = new PackageInfo(_namespace, _packageNameToFolder);
          final PackageInfo packageInfo = _packageInfo;
          this.stringfunction.generateAndroidStringFile(jfsa, m);
          this.manifestfuctions.generateAndroidManifestFile(jfsa, m, packageInfo);
          this.generateDummyImages(m, project, this.projectInitializer);
          this.generateAndroidFiles(m, jfsa, packageInfo);
          this.generateAndroidLayoutFiles(m, jfsa, packageInfo);
          JavaFormatter _javaFormatter = new JavaFormatter();
          _javaFormatter.formatAll(javaProject);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void generateDummyImages(final MobileApp app, final IProject project, final ProjectInitializer initializer) {
    List<Image> _allImages = this.javaUtils.getAllImages(app);
    for (final Image image : _allImages) {
      String _name = image.getName();
      initializer.createDummyImage(_name, project);
    }
  }
  
  private void generateCodeForEntity(final MobileApp m, final JavaIoFileSystemAccess2 fsa, final EClass entity, final PackageInfo packageInfo) {
    this.modelTemplate.generateDomainEntity(fsa, entity, packageInfo, ".model", "model/");
    this.modelConverterTemplate.generateModelConverter(fsa, entity, packageInfo);
    List<EClass> _allDomainEntities = this.javaUtils.getAllDomainEntities(m);
    this.constantsTemplates.generateDomainEntityConstants(fsa, entity, _allDomainEntities, packageInfo);
    this.domainEntityManagerTemplate.generateDomainEntityManager(fsa, entity, packageInfo);
    this.domainEntityManagerImplTemplate.generateDomainEntityManagerImpl(fsa, entity, packageInfo);
    this.createDomainEntityTemplate.generateCreateDomainEntity(fsa, entity, packageInfo);
    this.getAllDomainEntities.generateGetAllDomainEntities(fsa, entity, packageInfo);
    this.getDomainEntityByUri.generateGetDomainEntityByUri(fsa, entity, packageInfo);
    this.getDomainEntityByUuid.generateGetDomainEntityByUuid(fsa, entity, packageInfo);
    this.deleteDomainEntity.generateDeleteDomainEntity(fsa, entity, packageInfo);
    this.updateDomainEntity.generateUpdateDomainEntity(fsa, entity, packageInfo);
  }
  
  private void generateAndroidFiles(final MobileApp m, final JavaIoFileSystemAccess2 fsa, final PackageInfo packageInfo) {
    LinkedList<EClass> _linkedList = new LinkedList<EClass>();
    final LinkedList<EClass> allUsedEntites = _linkedList;
    this.modelTemplate.generateAbstractEntity(fsa, packageInfo);
    EPackage _domainPackage = m.getDomainPackage();
    EList<EClassifier> _eClassifiers = _domainPackage.getEClassifiers();
    final Procedure1<EClassifier> _function = new Procedure1<EClassifier>() {
        public void apply(final EClassifier entity) {
          boolean _matched = false;
          if (!_matched) {
            if (entity instanceof EClass) {
              final EClass _eClass = (EClass)entity;
              _matched=true;
              ModelTraverser.this.generateCodeForEntity(m, fsa, _eClass, packageInfo);
              allUsedEntites.add(_eClass);
            }
          }
        }
      };
    IterableExtensions.<EClassifier>forEach(_eClassifiers, _function);
    List<EClass> _allDomainEntities = this.javaUtils.getAllDomainEntities(m);
    List<EReference> _uniqueManyToManyEReferences = this.ecoreModelUtils.uniqueManyToManyEReferences(_allDomainEntities);
    for (final EReference ref : _uniqueManyToManyEReferences) {
      {
        EClass mappingEntity = this.ecoreModelUtils.manyToManyEReferenceMappingEntity(ref);
        this.generateCodeForEntity(m, fsa, mappingEntity, packageInfo);
        allUsedEntites.add(mappingEntity);
      }
    }
    StoryBoard _storyBoard = m.getStoryBoard();
    EList<Screen> _screens = _storyBoard.getScreens();
    final Procedure1<Screen> _function_1 = new Procedure1<Screen>() {
        public void apply(final Screen screen) {
          ModelTraverser.this.activitiesTemplate.generateActivities(fsa, screen, m, packageInfo);
          ModelTraverser.this.onClickHookTemplate.generateOnClickHookListener(fsa, screen, m, packageInfo);
          ModelTraverser.this.activitiesHookTemplate.generateActivitiesHooks(fsa, screen, m, packageInfo);
        }
      };
    IterableExtensions.<Screen>forEach(_screens, _function_1);
    LinkedList<Flow> _linkedList_1 = new LinkedList<Flow>();
    final LinkedList<Flow> allFlows = _linkedList_1;
    StoryBoard _storyBoard_1 = m.getStoryBoard();
    EList<Flow> _flows = _storyBoard_1.getFlows();
    final Procedure1<Flow> _function_2 = new Procedure1<Flow>() {
        public void apply(final Flow flow) {
          allFlows.add(flow);
        }
      };
    IterableExtensions.<Flow>forEach(_flows, _function_2);
    this.appConstantsTemplate.generateAppConstants(fsa, allFlows, m, packageInfo);
    String _name = m.getName();
    this.constantsTemplates.generateDBConstans(fsa, allUsedEntites, _name, packageInfo);
    this.databaseHelperTemplate.generateDatabaseHelper(fsa, allUsedEntites, m, packageInfo);
    this.contentProviderTemplate.generateContentProvider(fsa, allUsedEntites, m, packageInfo);
    this.contentProviderConstantsTemplate.generateIndicatorConstants(fsa, allUsedEntites, m, packageInfo);
    this.asyncCallbackTemplate.generateAsyncCallback(fsa, packageInfo);
    this.entityManagerTemplate.generateEntityManager(fsa, packageInfo);
    BindingRepository _bindingRepository = m.getBindingRepository();
    boolean _notEquals = (!Objects.equal(_bindingRepository, null));
    if (_notEquals) {
      BindingRepository _bindingRepository_1 = m.getBindingRepository();
      EList<DomainBinding> _bindings = _bindingRepository_1.getBindings();
      final Function1<DomainBinding,Boolean> _function_3 = new Function1<DomainBinding,Boolean>() {
          public Boolean apply(final DomainBinding binding) {
            return Boolean.valueOf((binding instanceof ListBinding));
          }
        };
      Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings, _function_3);
      final Function1<DomainBinding,ListBinding> _function_4 = new Function1<DomainBinding,ListBinding>() {
          public ListBinding apply(final DomainBinding binding) {
            return ((ListBinding) binding);
          }
        };
      Iterable<ListBinding> _map = IterableExtensions.<DomainBinding, ListBinding>map(_filter, _function_4);
      for (final ListBinding listBinding : _map) {
        {
          this.listAdapterTemplate.generateListAdapter(fsa, listBinding, packageInfo, m);
          this.listAdapterViewHolderImplTemplate.generateListAdapterViewHolderImpl(fsa, listBinding, packageInfo, m);
        }
      }
    }
  }
  
  private void generateAndroidLayoutFiles(final MobileApp m, final JavaIoFileSystemAccess2 fsa, final PackageInfo packageInfo) {
    BindingRepository _bindingRepository = m.getBindingRepository();
    boolean _notEquals = (!Objects.equal(_bindingRepository, null));
    if (_notEquals) {
      BindingRepository _bindingRepository_1 = m.getBindingRepository();
      EList<DomainBinding> _bindings = _bindingRepository_1.getBindings();
      final Function1<DomainBinding,Boolean> _function = new Function1<DomainBinding,Boolean>() {
          public Boolean apply(final DomainBinding binding) {
            return Boolean.valueOf((binding instanceof ListBinding));
          }
        };
      Iterable<DomainBinding> _filter = IterableExtensions.<DomainBinding>filter(_bindings, _function);
      final Function1<DomainBinding,ListBinding> _function_1 = new Function1<DomainBinding,ListBinding>() {
          public ListBinding apply(final DomainBinding binding) {
            return ((ListBinding) binding);
          }
        };
      Iterable<ListBinding> _map = IterableExtensions.<DomainBinding, ListBinding>map(_filter, _function_1);
      for (final ListBinding listBinding : _map) {
        this.adapterRowLayoutFunktions.generateEntityAdapterLayouts(fsa, listBinding, m);
      }
    }
    StoryBoard _storyBoard = m.getStoryBoard();
    boolean _notEquals_1 = (!Objects.equal(_storyBoard, null));
    if (_notEquals_1) {
      StoryBoard _storyBoard_1 = m.getStoryBoard();
      EList<Screen> _screens = _storyBoard_1.getScreens();
      for (final Screen activity : _screens) {
        this.activitiesLayoutFunktions.generateLayoutForActivity(activity, fsa, ModagileFolderConstants.RESOURCE, m);
      }
      StoryBoard _storyBoard_2 = m.getStoryBoard();
      this.menuLayoutTemplate.generateMenuLayouts(fsa, ModagileFolderConstants.RESOURCE, _storyBoard_2);
    }
  }
  
  private void configureFileSystemAccess(final JavaIoFileSystemAccess2 fsa) {
    Map<String,OutputConfiguration> _outputConfigurations = fsa.getOutputConfigurations();
    boolean _equals = Objects.equal(_outputConfigurations, null);
    if (_equals) {
      LinkedHashMap<String,OutputConfiguration> _linkedHashMap = new LinkedHashMap<String,OutputConfiguration>();
      fsa.setOutputConfigurations(_linkedHashMap);
    }
    OutputConfiguration _outputConfiguration = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
    OutputConfiguration defaultOutPutConf = _outputConfiguration;
    defaultOutPutConf.setOutputDirectory("./");
    defaultOutPutConf.setOverrideExistingResources(true);
    fsa.addOutPutConfig(defaultOutPutConf);
    OutputConfiguration _outputConfiguration_1 = new OutputConfiguration(ModagileFolderConstants.SRC_MAN);
    OutputConfiguration newOutPutConf = _outputConfiguration_1;
    newOutPutConf.setOutputDirectory("./src-man");
    newOutPutConf.setOverrideExistingResources(false);
    fsa.addOutPutConfig(newOutPutConf);
    OutputConfiguration _outputConfiguration_2 = new OutputConfiguration(ModagileFolderConstants.RESOURCE);
    OutputConfiguration resourceOutPutConf = _outputConfiguration_2;
    resourceOutPutConf.setOutputDirectory("./res");
    resourceOutPutConf.setOverrideExistingResources(false);
    fsa.addOutPutConfig(resourceOutPutConf);
    OutputConfiguration _outputConfiguration_3 = new OutputConfiguration(ModagileFolderConstants.SRC);
    OutputConfiguration srcOutPutConf = _outputConfiguration_3;
    srcOutPutConf.setOutputDirectory("./src");
    srcOutPutConf.setOverrideExistingResources(true);
    fsa.addOutPutConfig(srcOutPutConf);
    OutputConfiguration _outputConfiguration_4 = new OutputConfiguration(ModagileFolderConstants.SRC_GEN);
    OutputConfiguration srcGenOutPutConf = _outputConfiguration_4;
    srcGenOutPutConf.setOutputDirectory("./src-gen");
    srcGenOutPutConf.setOverrideExistingResources(true);
    fsa.addOutPutConfig(srcGenOutPutConf);
  }
  
  protected void _compile(final EObject o, final IFileSystemAccess fsa) {
  }
  
  public void compile(final EObject m, final IFileSystemAccess fsa) {
    if (m instanceof MobileApp) {
      _compile((MobileApp)m, fsa);
      return;
    } else if (m != null) {
      _compile(m, fsa);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(m, fsa).toString());
    }
  }
}
