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
package de.modagile.generator.android.templates

import com.google.inject.Inject
import de.modagile.generator.android.javahelperclasses.ProjectInitializer
import de.modagile.generator.android.templates.activity.ActivitiesHooksTemplate
import de.modagile.generator.android.templates.activity.ActivitiesLayoutTemplate
import de.modagile.generator.android.templates.activity.ActivitiesTemplate
import de.modagile.generator.android.templates.activity.MenuLayoutTemplate
import de.modagile.generator.android.templates.activity.OnClickHookTemplate
import de.modagile.generator.android.templates.adapter.AdapterRowLayoutTemplate
import de.modagile.generator.android.templates.adapter.ListAdapterTemplate
import de.modagile.generator.android.templates.adapter.ListAdapterViewHolderImplTemplate
import de.modagile.generator.android.templates.constants.AppConstantsTemplate
import de.modagile.generator.android.templates.constants.DomainEntityConstantsTemplate
import de.modagile.generator.android.templates.contentprovider.ContentProviderTemplate
import de.modagile.generator.android.templates.contentprovider.DatabaseHelperTemplate
import de.modagile.generator.android.templates.contentprovider.IndicatorConstantsTemplate
import de.modagile.generator.android.templates.converter.ModelConverterTemplate
import de.modagile.generator.android.templates.java.JavaUtils
import de.modagile.generator.android.templates.manager.DomainEntityManagerImplTemplate
import de.modagile.generator.android.templates.manager.DomainEntityManagerTemplate
import de.modagile.generator.android.templates.manager.EntityManagerTemplate
import de.modagile.generator.android.templates.model.ModelTemplate
import de.modagile.generator.android.templates.task.AsyncCallbackTemplate
import de.modagile.generator.android.templates.task.CreateDomainEntityTemplate
import de.modagile.generator.android.templates.task.DeleteDomainEntity
import de.modagile.generator.android.templates.task.GetAllDomainEntities
import de.modagile.generator.android.templates.task.GetDomainEntityByUri
import de.modagile.generator.android.templates.task.GetDomainEntityByUuid
import de.modagile.generator.android.templates.task.UpdateDomainEntity
import de.modagile.generator.common.JavaIoFileSystemAccess2
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.generatorconfig.GeneratorConfig
import de.modagile.metamodel.app.generatorconfig.PlatformType
import de.modagile.metamodel.app.ui.Flow
import info.multiplatform.generator.java.helper.JavaFormatter
import info.multiplatform.generator.java.templates.PackageInfo
import java.util.ArrayList
import java.util.LinkedList
import org.eclipse.core.resources.IFile
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import de.modagile.metamodel.app.domain.ListBinding
import de.modagile.metamodel.app.ui.Screen
import org.eclipse.xtext.generator.OutputConfiguration
import java.util.LinkedHashMap
import org.eclipse.core.resources.IProject
import de.modagile.metamodel.app.ui.Image

/**
 * Contains activity specific generator methods and calls all activity sub de.modagile.generator.android.templates
 * */
class ModelTraverser implements IGenerator {

	@Inject extension ActivitiesLayoutTemplate activitiesLayoutFunktions
	@Inject extension AdapterRowLayoutTemplate adapterRowLayoutFunktions
	@Inject extension ListAdapterTemplate listAdapterTemplate
	@Inject extension ListAdapterViewHolderImplTemplate listAdapterViewHolderImplTemplate
	@Inject extension ModelTemplate modelTemplate
	@Inject extension DomainEntityConstantsTemplate constantsTemplates
	@Inject extension DomainEntityManagerTemplate domainEntityManagerTemplate
	@Inject extension DomainEntityManagerImplTemplate domainEntityManagerImplTemplate
	@Inject extension EntityManagerTemplate entityManagerTemplate
	@Inject extension DatabaseHelperTemplate databaseHelperTemplate
	@Inject extension ModelConverterTemplate modelConverterTemplate
	@Inject extension AsyncCallbackTemplate asyncCallbackTemplate
	@Inject extension CreateDomainEntityTemplate createDomainEntityTemplate
	@Inject extension GetAllDomainEntities getAllDomainEntities
	@Inject extension GetDomainEntityByUri getDomainEntityByUri
	@Inject extension GetDomainEntityByUuid getDomainEntityByUuid
	@Inject extension DeleteDomainEntity deleteDomainEntity
	@Inject extension UpdateDomainEntity updateDomainEntity
	@Inject extension ContentProviderTemplate contentProviderTemplate
	@Inject extension IndicatorConstantsTemplate contentProviderConstantsTemplate
	@Inject extension ActivitiesTemplate activitiesTemplate
	@Inject extension ActivitiesHooksTemplate activitiesHookTemplate
    @Inject extension AppConstantsTemplate appConstantsTemplate
    @Inject extension OnClickHookTemplate onClickHookTemplate
	@Inject extension StringFileTemplate stringfunction
	@Inject extension ManifestTemplate manifestfuctions
	@Inject extension JavaUtils javaUtils
	@Inject extension EcoreModelUtils ecoreModelUtils
	@Inject extension ProjectInitializer projectInitializer
	@Inject extension MenuLayoutTemplate menuLayoutTemplate
	
	override void doGenerate(Resource input, IFileSystemAccess fsa) {
		createSherlockProject(fsa);
		for (EObject o : input.contents) {
			o.compile(fsa);
		}
	}
	
	def createSherlockProject(IFileSystemAccess fsa) {
		if (projectInitializer.existsSherlockProject()) return;
		val sherlockProject = projectInitializer.createProject(ProjectInitializer::ACTIONBARSHERLOCK_PLUGIN_ID);
		val JavaIoFileSystemAccess2 jfsa = fsa as JavaIoFileSystemAccess2;
		configureFileSystemAccess(jfsa);
		jfsa.setProject(sherlockProject);
		val sourceFolders = new ArrayList<String>();
		sourceFolders.add("src");
		sourceFolders.add("gen");
		val libs = new ArrayList<String>();
		libs.add("./lib/android-support-v4.jar");
		
	   projectInitializer.initializeProject(sherlockProject, fsa, sourceFolders, libs);
		
		projectInitializer.unpackZipFile(sherlockProject, "res/actionbarsherlock-src.zip", "src");
		projectInitializer.unpackZipFile(sherlockProject, "res/actionbarsherlock-res.zip", "res");
		projectInitializer.copyFile("res", "AndroidManifest.xml", sherlockProject, null, true);
		projectInitializer.copyFile("res", "project.properties", sherlockProject, null, true);

		sherlockProject.refreshLocal(IFile::DEPTH_INFINITE, null);
		
	}
	
	def dispatch compile(MobileApp m, IFileSystemAccess fsa){
	    for (GeneratorConfig config : m.generatorConfigContainer.generatorConfigs){
	        if(config.targetPlatform == PlatformType::ANDROID){
	    
    		val JavaIoFileSystemAccess2 jfsa = fsa as JavaIoFileSystemAccess2;
    		jfsa.configureFileSystemAccess
    		val project = projectInitializer.createProject(config.projectName);
    		jfsa.setProject(project.project);
    		val sourceFolders = new ArrayList<String>();
    		sourceFolders.add("src-gen");
    		sourceFolders.add("src-man");
    		sourceFolders.add("gen");
    		val libs = new ArrayList<String>();
    		libs.add("./lib/com.actionbarsherlock.jar");
    		
    		val javaProject = projectInitializer.initializeProject(project, fsa, sourceFolders, libs); 
    		jfsa.setProject(javaProject.project);
    		
    		val packageInfo = new PackageInfo(config.namespace, config.namespace.packageNameToFolder)
    		jfsa.generateAndroidStringFile(m);
    		jfsa.generateAndroidManifestFile(m, packageInfo);
    		generateDummyImages(m, project, projectInitializer);
    		generateAndroidFiles(m, jfsa, packageInfo);
    		generateAndroidLayoutFiles(m, jfsa, packageInfo);
    		
    		new JavaFormatter().formatAll(javaProject)
            }
		}
	}
	
	def generateDummyImages(MobileApp app, IProject project, ProjectInitializer initializer) {
		for (Image image: app.allImages) {
			initializer.createDummyImage(image.name, project);
		}
	}
	
	def private generateCodeForEntity(MobileApp m, JavaIoFileSystemAccess2 fsa, EClass entity, PackageInfo packageInfo) {
       fsa.generateDomainEntity(entity, packageInfo, ".model", "model/")
       fsa.generateModelConverter(entity, packageInfo)
       fsa.generateDomainEntityConstants(entity, m.allDomainEntities, packageInfo)
       fsa.generateDomainEntityManager(entity, packageInfo)
       fsa.generateDomainEntityManagerImpl(entity, packageInfo)
       fsa.generateCreateDomainEntity(entity, packageInfo)
       fsa.generateGetAllDomainEntities(entity, packageInfo)
       fsa.generateGetDomainEntityByUri(entity, packageInfo)
       fsa.generateGetDomainEntityByUuid(entity, packageInfo)
       fsa.generateDeleteDomainEntity(entity, packageInfo)
       fsa.generateUpdateDomainEntity(entity, packageInfo)
	}

	
	def private generateAndroidFiles(MobileApp m, JavaIoFileSystemAccess2 fsa, PackageInfo packageInfo){
	    val allUsedEntites = new LinkedList<EClass>    
		fsa.generateAbstractEntity(packageInfo)
		m.domainPackage.EClassifiers.forEach[ entity |
		   switch(entity){
            EClass: {
            	generateCodeForEntity(m, fsa, entity, packageInfo)
                allUsedEntites += entity
            }		   
		   }
		]

		for (ref : m.allDomainEntities.uniqueManyToManyEReferences) {
			var EClass mappingEntity = ref.manyToManyEReferenceMappingEntity;
           	generateCodeForEntity(m, fsa, mappingEntity, packageInfo)
           	allUsedEntites += mappingEntity
		}

		m.storyBoard.screens.forEach[ screen |
		   fsa.generateActivities(screen, m, packageInfo);
		   fsa.generateOnClickHookListener(screen, m, packageInfo);
		   fsa.generateActivitiesHooks(screen, m, packageInfo);  
		]

      val allFlows = new LinkedList<Flow>		
		m.storyBoard.flows.forEach[ flow |
		   allFlows += flow
		]
		
		fsa.generateAppConstants(allFlows, m, packageInfo);
		fsa.generateDBConstans(allUsedEntites, m.name, packageInfo);
		fsa.generateDatabaseHelper(allUsedEntites, m, packageInfo);
		fsa.generateContentProvider(allUsedEntites, m, packageInfo);
		fsa.generateIndicatorConstants(allUsedEntites, m, packageInfo);
		fsa.generateAsyncCallback(packageInfo);
		fsa.generateEntityManager(packageInfo);
		if(m.bindingRepository != null){
    		for (ListBinding listBinding: m.bindingRepository.bindings.filter(binding | binding instanceof ListBinding).map(binding | binding as ListBinding)){
        		fsa.generateListAdapter(listBinding, packageInfo, m);
        		fsa.generateListAdapterViewHolderImpl(listBinding, packageInfo, m);
    		}
		}
	}

	def private generateAndroidLayoutFiles(MobileApp m , JavaIoFileSystemAccess2 fsa, PackageInfo packageInfo){
	    if(m.bindingRepository != null){
    		for (ListBinding listBinding: m.bindingRepository.bindings.filter(binding | binding instanceof ListBinding).map(binding | binding as ListBinding)){
    		 	fsa.generateEntityAdapterLayouts(listBinding, m);
    		 }
		 }
		 
		 if(m.storyBoard != null){
		    for(Screen activity: m.storyBoard.screens ) {
			   activitiesLayoutFunktions.generateLayoutForActivity(activity, fsa, ModagileFolderConstants::RESOURCE, m);
			   
		    }
		    menuLayoutTemplate.generateMenuLayouts(fsa, ModagileFolderConstants::RESOURCE, m.storyBoard);		
		 }
	}
	
	def private configureFileSystemAccess(JavaIoFileSystemAccess2 fsa){
		if (fsa.outputConfigurations == null) {
		     fsa.setOutputConfigurations(new LinkedHashMap<String, OutputConfiguration>());
		}
		var OutputConfiguration defaultOutPutConf = new OutputConfiguration(IFileSystemAccess::DEFAULT_OUTPUT);
		defaultOutPutConf.setOutputDirectory("./");
		defaultOutPutConf.setOverrideExistingResources(true);
		fsa.addOutPutConfig(defaultOutPutConf);

		var OutputConfiguration newOutPutConf = new OutputConfiguration(ModagileFolderConstants::SRC_MAN);
		newOutPutConf.setOutputDirectory("./src-man");
		newOutPutConf.setOverrideExistingResources(false);
		fsa.addOutPutConfig(newOutPutConf);

		var OutputConfiguration resourceOutPutConf = new OutputConfiguration(ModagileFolderConstants::RESOURCE);
		resourceOutPutConf.setOutputDirectory("./res");
		resourceOutPutConf.setOverrideExistingResources(false);
		fsa.addOutPutConfig(resourceOutPutConf);

		var OutputConfiguration srcOutPutConf = new OutputConfiguration(ModagileFolderConstants::SRC);
		srcOutPutConf.setOutputDirectory("./src");
		srcOutPutConf.setOverrideExistingResources(true);
		fsa.addOutPutConfig(srcOutPutConf);

		var OutputConfiguration srcGenOutPutConf = new OutputConfiguration(ModagileFolderConstants::SRC_GEN);
		srcGenOutPutConf.setOutputDirectory("./src-gen");
		srcGenOutPutConf.setOverrideExistingResources(true);
		fsa.addOutPutConfig(srcGenOutPutConf);

	}
	
	def dispatch void compile (EObject o, IFileSystemAccess fsa){ 	}
	
}