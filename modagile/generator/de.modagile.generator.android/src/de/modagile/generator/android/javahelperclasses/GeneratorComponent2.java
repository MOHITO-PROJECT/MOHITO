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

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;


import com.google.inject.Injector;


public class GeneratorComponent2 implements IWorkflowComponent{

	private Injector injector;
	private List<String> slotNames = newArrayList();
	private Map<String, Outlet> outlets = newHashMap();
	/**
	 * registering an {@link ISetup}, which causes the execution of {@link ISetup#createInjectorAndDoEMFRegistration()}
	 * the resulting {@link com.google.inject.Inject} is stored and used to obtain the used {@link IGenerator}. 
	 */
	public void setRegister(ISetup setup) {
		injector = setup.createInjectorAndDoEMFRegistration();
	}
	
	/**
	 * sets the {@link Injector} to be used to obtain the used {@link IGenerator} instance.
	 */
	public void setInjector(Injector injector) {
		this.injector = injector;
	}
	
	/**
	 * adds a slot name to look for {@link Resource}s (the slot's contents might be a Resource or an Iterable of Resources).
	 */
	public void addSlot(String slot) {
		this.slotNames.add(slot);
	}

	public void preInvoke() {
		if (slotNames.isEmpty())
			throw new IllegalStateException("no 'slot' has been configured.");
		if (injector == null)
			throw new IllegalStateException("no Injector has been configured. Use 'register' with an ISetup or 'injector' directly.");
		if (outlets.isEmpty())
			throw new IllegalStateException("no 'outlet' has been configured.");
			
		for (Entry<String, Outlet> outlet : outlets.entrySet()) {
			if (outlet.getKey()==null)
				throw new IllegalStateException("One of the outlets was configured without a name");
			if (outlet.getValue()==null)
				throw new IllegalStateException("The path of outle '"+outlet.getKey()+"' was null.");
		}
	}
	
	/**
	 * Represents the properties for the OutputConfiguration
	 * */
	public static class Outlet {
		
		private String outletName = IFileSystemAccess.DEFAULT_OUTPUT;
		private String path;
		
		public void setOutletName(String outputName) {
			this.outletName = outputName;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getOutletName() {
			return outletName;
		}
		public String getPath() {
			return path;
		}
	}
	
	/**
	 * an outlet is defined by a name and a path.
	 * The generator will internally choose one of the configured outlets when generating a file.
	 * the given path defines the root directory of the outlet.
	 */
	public void addOutlet(Outlet out) {
		outlets.put(out.outletName, out);
	}
	
	public void invoke(IWorkflowContext ctx) {
		IGenerator instance = getCompiler();
		//IFileSystemAccess fileSystemAccess = getConfiguredFileSystemAccess();
		for (String slot : slotNames) {
			Object object = ctx.get(slot);
			if (object == null) {
				throw new IllegalStateException("Slot '"+slot+"' was empty!");
			}
			if (object instanceof Iterable) {
				Iterable<?> iterable = (Iterable<?>) object;
				for (Object object2 : iterable) {
					if (!(object2 instanceof Resource)) {
						throw new IllegalStateException("Slot contents was not a Resource but a '"+object.getClass().getSimpleName()+"'!");
					}
					instance.doGenerate((Resource) object2, null);
				}
			} else if (object instanceof Resource) {
				instance.doGenerate((Resource) object, null);
			} else {
				throw new IllegalStateException("Slot contents was not a Resource but a '"+object.getClass().getSimpleName()+"'!");
			}
		}
	}

	protected IGenerator getCompiler() {
		return injector.getInstance(IGenerator.class);
	}

	public void postInvoke() {
	}

}
