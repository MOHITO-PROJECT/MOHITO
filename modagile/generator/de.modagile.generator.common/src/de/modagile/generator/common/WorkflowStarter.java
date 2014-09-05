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

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.modagile.metamodel.app.AppPackage;
import de.modagile.metamodel.app.MobileApp;

public class WorkflowStarter {

	@Inject
	IResourceSetProvider resourceSetProvider;
	
	private IFileSystemAccess fileAccessProvider;
	
	protected MobileApp mobileAppSelected = null;
	
	private Injector injector;
	
	public WorkflowStarter(Injector injector, IFileSystemAccess fsa) {
		this.fileAccessProvider = fsa;
		this.injector = injector;
		
	}
	
	public void execute(IFile modelFile) {
		// we dont need the action, just the selected file. 

		AppPackage.eINSTANCE.eClass();
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put("app", new XMIResourceFactoryImpl());

	    ResourceSet resSet = new ResourceSetImpl();
	    Resource resource = resSet.getResource(URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true), true);

	    mobileAppSelected = (MobileApp) resource.getContents().get(0);
		Resource r = mobileAppSelected.eResource();

		this.getGenerator().doGenerate(r, fileAccessProvider);
	}


	protected IGenerator getGenerator() {
		return injector.getInstance(IGenerator.class);
	}
	
}
