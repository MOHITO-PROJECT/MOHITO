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

import com.google.inject.Binder;
import com.google.inject.Module;

/**GUICE module resolving the dependencies to the implementation for initializing an eclipse project and starting the generation of
 * code for MOHITO-Entities. 
 * @author hgroenda
 *
 */
public class MohitoProjectGeneratorModule implements Module {
	/** The implementation for initializing an eclipse project and starting the generation of code for MOHITO-Entities. */
 	private AbstractProjectInitializer projectInitializer;
	
	/**Initializes the MOHITO-Entity generator and resolves the implementation for creating and initializing a project for the generated code.
	 * @param projectInitializer The implementation for initializing an eclipse project and starting the generation of code for MOHITO-Entities. 
	 */
	public MohitoProjectGeneratorModule(
			AbstractProjectInitializer projectInitializer) 
	{
		this.projectInitializer = projectInitializer;
	}
	
	@Override
	public void configure(Binder binder) {
		if (projectInitializer == null) {
			throw new IllegalArgumentException("project initialization implementation must not be NULL.");
		}
	    binder.bind(AbstractProjectInitializer.class).toInstance(projectInitializer);  
	}

}
