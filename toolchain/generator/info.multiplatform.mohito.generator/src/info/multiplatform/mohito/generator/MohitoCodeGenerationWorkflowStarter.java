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

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;

import com.google.inject.Injector;

/**Starts the code generation workflow.
 * 
 * Variant of {@link MohitoCodeGenerationWorkflowStarter.modagile.generator.common.WorkflowStarter} without ModagileMobile specific model parts.
 * 
 * @author klatt
 * @author hgroenda
 *
 */
public class MohitoCodeGenerationWorkflowStarter {

	/** File system access for code generation. */
	private IFileSystemAccess fileAccessProvider;
	
	/**Code generator. */
	private Injector injectedGenerator;
	
	/**Initializes a code generation workflow.
	 * @param injectedGenerator Injected code generator.
	 * @param fsa File system access for code generation.
	 */
	public MohitoCodeGenerationWorkflowStarter(Injector injectedGenerator, IFileSystemAccess fsa) {
		this.fileAccessProvider = fsa;
		this.injectedGenerator = injectedGenerator;
	}
	
	/** Load the model from the provided file and starts the code generation.
	 * @param modelFile The file containing the MOHITO-Model.
	 */
	public void execute(IFile modelFile) {
	    ResourceSet resSet = new ResourceSetImpl();
	    Resource resource = resSet.getResource(URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true), true);
		// generate
		this.getGenerator().doGenerate(resource, fileAccessProvider);
	}

	protected IGenerator getGenerator() {
		return injectedGenerator.getInstance(IGenerator.class);
	}
	
}
