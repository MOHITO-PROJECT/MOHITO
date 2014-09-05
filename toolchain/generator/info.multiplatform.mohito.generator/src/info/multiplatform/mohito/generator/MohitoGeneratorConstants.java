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

/**Constants related to generic MOHITO generators.
 * @author hgroenda
 *
 */
public interface MohitoGeneratorConstants {
	/** ID of this plug-in. */
	public static final String PLUGIN_ID = "info.multiplatform.mohito.generator";
	/** ID of the extension point at which generators can be registered. */
	public static final String EXTENSION_POINT_ID = "info.multiplatform.mohito.generator";
	/** Tag marking a local generator description element. */
	public static final String LOCAL_GENERATOR_TAG = "localGenerator";
	/** Tag marking a remote generator decription element. */
	public static final String REMOTE_GENERATOR_TAG = "remoteGenerator";
	/** Tag marking the transformation class for any generator. */
	public static final String TRANSFORMATION_CLASS_TAG = "transformationClass";
	/** Tag marking the eclipse dependency for any generator*/
	public static final String ECLIPSE_DEPENDENCY_TAG = "eclipseDependency";
	/** Tag marking the plaina java dependency for any generator*/
	public static final String PLAIN_JAVA_DEPENDENCY_TAG = "plainJavaDependency";
	/** Tag marking the access type of a remote generator. */
	public static final String ACCESS_ROLE_TAG = "accessRole";
	/** Identifier for the access type client. */
	public static final String ACCESS_ROLE_CLIENT = "Client";
	/** Identifier for the access type server. */
	public static final String ACCESS_ROLE_SERVER = "Server";
	/** Tag marking a project initializer description element. */
	public static final String PROJECT_INITIALIZER_TAG = "projectInitializer";
	/** Tag marking the eclipse project initialization class for the generator of a target platform. */
	public static final String PROJECT_INITIALIZATION_CLASS_TAG = "projectInitializationClass";
	/** Tag marking the eclipse project initialization class for the generator of a target platform. */
	public static final String PROJECT_INITIALIZATION_GENERATION_CLASS_TAG = "generatorClass";
	

}
