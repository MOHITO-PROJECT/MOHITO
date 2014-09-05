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


import info.multiplatform.mohito.generator.IStorageGenerator;

import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.generator.IGenerator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.modagile.generator.common.workflow.ModagileGeneratorModule;

/**Initializes the model transformation used to generate the MOHITO-Entities and model in the target environment.
 * @author hgroenda
 */
public class MohitoGeneratorSetup implements ISetup {
	
	/** The implementation to use for generating the MOHITO-Entities and model in the target environment. */
	private Class<? extends IGenerator> generator;
	/** The implementation to use for generating the annotations and code for local storage. */
	private Class<? extends IStorageGenerator> localGenerator;
	/** The implementation to use for generating the annotations and code for remote storage. */
	private Class<? extends IStorageGenerator> remoteGenerator;
	/** The implementation to use for project initialization */
	private AbstractProjectInitializer projectInitializer;
	
	/**Initializes the model transformation.
	 * @param generatorClass The implementation to use for generating the MOHITO-Entities and model in the target environment.
	 * @param localGenerator The implementation to use for generating the annotations and code for local storage.
	 * @param remoteGenerator The implementation to use for generating the annotations and code for remote storage.
	 * @param projectInitializer The implementation for initializing an eclipse project, which will receive the generated files. 
	 */
	public MohitoGeneratorSetup(Class<? extends IGenerator> generatorClass,
			Class<? extends IStorageGenerator> localGenerator,
			Class<? extends IStorageGenerator> remoteGenerator,
			AbstractProjectInitializer projectInitializer) {
		this.generator = generatorClass;
		this.localGenerator = localGenerator;
		this.remoteGenerator = remoteGenerator;
		this.projectInitializer = projectInitializer;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.xtext.ISetup#createInjectorAndDoEMFRegistration()
	 * creates the Injector with the MohitoStorageGeneratorModule
	 */
	public Injector createInjectorAndDoEMFRegistration() {
		Injector injector = Guice.createInjector(
				new ModagileGeneratorModule(generator), 
				new MohitoStorageGeneratorModule(localGenerator, remoteGenerator),
				new MohitoProjectGeneratorModule(projectInitializer));
		return injector;
	}
}
