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
import info.multiplatform.mohito.generator.environment.java.DomainModelTemplate;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

/**GUICE module resolving the dependencies to local and remote storage generation scripts in the
 * MOHITO-Entity generator script.
 * @author hgroenda
 *
 */
public class MohitoStorageGeneratorModule implements Module {
	/** The implementation to use for generating the annotations and code for local storage. */
	private Class<? extends IStorageGenerator> localGenerator;
	/** The implementation to use for generating the annotations and code for remote storage. */
	private Class<? extends IStorageGenerator> remoteGenerator;
	
	/**Initializes the MOHITO-Entity generator and resolves local and remote storage generation implementations.
	 * @param localGenerator The implementation to use for generating the annotations and code for local storage.
	 * @param remoteGenerator The implementation to use for generating the annotations and code for remote storage.
	 */
	public MohitoStorageGeneratorModule(
			Class<? extends IStorageGenerator> localGenerator,
			Class<? extends IStorageGenerator> remoteGenerator) {
		this.localGenerator = localGenerator;
		this.remoteGenerator = remoteGenerator;
	}
	
	@Override
	public void configure(Binder binder) {
		if (localGenerator == null) {
			throw new IllegalArgumentException("Local generator implementation must not be NULL.");
		}
		if (remoteGenerator == null) {
			throw new IllegalArgumentException("Remote generator implementation must not be NULL.");
		}
		// resolves the local and remote generator implementations used in the main transformation script.
	    binder.bind(IStorageGenerator.class).annotatedWith(Names.named(DomainModelTemplate.NAME_INJECTOR_LOCAL)).to(localGenerator);
	    binder.bind(IStorageGenerator.class).annotatedWith(Names.named(DomainModelTemplate.NAME_INJECTOR_REMOTE)).to(remoteGenerator);
	}

}
