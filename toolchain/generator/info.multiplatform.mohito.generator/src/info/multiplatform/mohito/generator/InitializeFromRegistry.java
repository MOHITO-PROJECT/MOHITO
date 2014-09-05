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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.xtext.generator.IGenerator;

/**
 * Initializes and registers all generators from the eclipse registry.
 * 
 * @author hgroenda
 * 
 */
public class InitializeFromRegistry {
	/** Logger for this class. */
	private static final Logger logger = Logger
			.getLogger(InitializeFromRegistry.class.getCanonicalName());
	/** Single for access to the registry. */
	public static final InitializeFromRegistry GENERATOR_REGISTRY = new InitializeFromRegistry();
	
	/**Information on a local generator.
	 * @author hgroenda
	 *
	 */
	public class LocalGenerator {
		/** Human readable name or identifier of the generator. */
		public final String name;
		/** The implementation of the code generation. */
		public final IStorageGenerator transformationClass;
		/** The eclipse dependencies of this generator required in order to compile the project. */
		public final Collection<String> eclipseDependency;
		/** The plain java dependencies of this generator required in order to compile the project. */
		public final Collection<String> plainJavaDependency;

		/**Initializes a new generator instance.
		 * @param name Human readable name or identifier of the generator.
		 * @param transformationClass The implementation of the code generation.
		 * @param eclipseDependency The eclipse dependency of the project.
		 */
		public LocalGenerator(String name, IStorageGenerator transformationClass, Collection<String> eclipseDependency, Collection<String> plainJavaDependency) {
			this.name = name;
			this.transformationClass = transformationClass;
			this.eclipseDependency = eclipseDependency;
			this.plainJavaDependency = plainJavaDependency;
		}
	}

	/**Information on a remote generator.
	 * @author hgroenda
	 *
	 */
	public class RemoteGenerator extends LocalGenerator {
		/** Remote role - Is the remote access to data provided (Server) or required (Client). */
		public final String accessRole;

		/**Initializes a new generator instance.
		 * @param name Human readable name or identifier of the generator.
		 * @param transformationClass The implementation of the code generation.
		 * @param eclipseDependency The eclipse dependency of the project.
		 * @param accessRole Is the remote access to data provided (Server) or required (Client).
		 */
		public RemoteGenerator(String name,
				IStorageGenerator transformationClass,
				Collection<String> eclipseDependency,
				Collection<String> plainJavaDependency, String accessRole) {
			super(name, transformationClass, eclipseDependency, plainJavaDependency);
			this.accessRole = accessRole;
		}
	}
	
	/**Information on the target generation environment and project initialization.
	 *
	 */
	public class ProjectInitializer {
		/** readable name or identifier of the initializer*/
		public final String name;
		/** The implementation of the initializer for the eclipse project and targeted environment. */
		public final AbstractProjectInitializer projectInitializationClass;
		/** The implementation used to start the generation of the environment-specific representation of the domain model. */
		public final IGenerator generatorClass;
		
		/**Initializes a new project initialization instance.
		 * @param name Readable name or identifier of the initializer.
		 * @param projectInitializationClass The implementation of the initializer for the eclipse project and targeted environment.
		 * @param generatorClass The implementation used to start the generation of the environment-specific representation of the domain model.
		 */
		public ProjectInitializer(String name, AbstractProjectInitializer projectInitializationClass, IGenerator generatorClass) {
			this.name = name;
			this.projectInitializationClass = projectInitializationClass;
			this.generatorClass = generatorClass;
		}
	}

	/**Default constructor loading the available generators from the eclipse registry.
	 */
	public InitializeFromRegistry() {
		this.localGenerators = new ArrayList<InitializeFromRegistry.LocalGenerator>();
		this.remoteClientGenerators = new ArrayList<InitializeFromRegistry.RemoteGenerator>();
		this.remoteServerGenerators = new ArrayList<InitializeFromRegistry.RemoteGenerator>();
		this.projectInitializers = new ArrayList<InitializeFromRegistry.ProjectInitializer>();
		readRegistry(Platform.getExtensionRegistry());
	}

	/** List of all available local generators. */
	private final List<LocalGenerator> localGenerators;
	/** List of all available remote client generators. */
	private final List<RemoteGenerator> remoteClientGenerators;
	/** List of all available remote server generators. */
	private final List<RemoteGenerator> remoteServerGenerators;
	/**List of all available project initializers. */
	private final List<ProjectInitializer> projectInitializers;
	
	/**Returns the executable extension for a given configuration element and attribute.
	 * @param ice The configuration.
	 * @param attributeTag Tag for the attribute.
	 * @return Object after conversion from configuration.
	 */
	private Object getObjectFromConfiguration(IConfigurationElement ice, String attributeTag) {
		if (ice.getAttribute(attributeTag) != null
				&& ! ice.getAttribute(attributeTag).isEmpty()) {
			try {
				return ice.createExecutableExtension(attributeTag);
			} catch (CoreException e) {
				logger.log(
						Level.WARNING,
						"Cannot create executable extension for " + attributeTag + ".",
						e);
			}
		} 
		return null;
	}
	
	/**
	 * Returns the eclipse dependencies for a given generator configuration
	 * element.
	 * 
	 * @param generatorIce
	 *            The generator configuration.
	 * @return The eclipse dependencies.
	 */
	private Collection<String> getEclipseDependency(IConfigurationElement generatorIce) {
		Collection<String> eclipseDependencies = new ArrayList<String>();
		IConfigurationElement[] iceChildren = generatorIce.getChildren();
		for (IConfigurationElement e : iceChildren) {
			if (e.getName().equals(
					MohitoGeneratorConstants.ECLIPSE_DEPENDENCY_TAG)) {
				String[] attributes = e.getAttributeNames();
				for (String attribute : attributes) {
					eclipseDependencies.add(e.getAttribute(attribute));
				}
			}
		}
		return eclipseDependencies;
	}

	/**
	 * Returns the plainJava dependencies for a given generator configuration element.
	 * 
	 * @param ice
	 *            The generator configuration.
	 * @return The plain java dependencies.
	 */
	private Collection<String> getPlainJavaDependency(IConfigurationElement generatorIce) {
		ArrayList<String> plainJavaDependencies = new ArrayList<String>();
		IConfigurationElement[] ice_children = generatorIce.getChildren();
		for (IConfigurationElement e : ice_children) {
			if (e.getName().equals(
					MohitoGeneratorConstants.PLAIN_JAVA_DEPENDENCY_TAG)) {
				String[] attributes = e.getAttributeNames();
				for (String attribute : attributes) {
					plainJavaDependencies.add(generatorIce.getContributor().getName() + "/" + e.getAttribute(attribute));
				}
			}
		}
		return plainJavaDependencies;
	}
	
	/**Processes the registry and stores the found generators.
	 * @param registry The eclipse registry.
	 */
	private void readRegistry(IExtensionRegistry registry) {
		IConfigurationElement[] config = registry
				.getConfigurationElementsFor(MohitoGeneratorConstants.EXTENSION_POINT_ID);
		for (IConfigurationElement e : config) {
			String registrationLabel = e.getDeclaringExtension().getLabel();
			try {
				if (e.getName().equals(MohitoGeneratorConstants.LOCAL_GENERATOR_TAG)) {
					IStorageGenerator generatorClass = (IStorageGenerator) getObjectFromConfiguration(e, MohitoGeneratorConstants.TRANSFORMATION_CLASS_TAG);
					Collection<String> eclipseDependencies = this.getEclipseDependency(e);
					Collection<String> plainJavaDependencies = this.getPlainJavaDependency(e);
					if (generatorClass == null) {
						logger.info("Local generator registered with name " + registrationLabel + " did not provide a class. This is required and the defaulting generator is not added to the list of available generators.");
					} else {
						// local
						localGenerators
						.add(new LocalGenerator(
								registrationLabel,
								generatorClass,
								eclipseDependencies,
								plainJavaDependencies));
					}
				} else if (e.getName().equals(MohitoGeneratorConstants.REMOTE_GENERATOR_TAG)) {
					IStorageGenerator generatorClass = (IStorageGenerator) getObjectFromConfiguration(e, MohitoGeneratorConstants.TRANSFORMATION_CLASS_TAG);
					Collection<String> eclipseDependencies = this.getEclipseDependency(e);
					Collection<String> plainJavaDependencies = this.getPlainJavaDependency(e);
					if (generatorClass == null) {
						logger.info("Remote generator registered with name " + registrationLabel + " did not provide a class. This is required and the defaulting generator is not added to the list of available generators.");
					} else {
						if (e.getAttribute(MohitoGeneratorConstants.ACCESS_ROLE_TAG) != null 
								&& e.getAttribute(MohitoGeneratorConstants.ACCESS_ROLE_TAG)
								.equals(MohitoGeneratorConstants.ACCESS_ROLE_CLIENT)) {
							// remote client
							remoteClientGenerators
									.add(new RemoteGenerator(
											registrationLabel,
											generatorClass,
											eclipseDependencies,
											plainJavaDependencies,
											MohitoGeneratorConstants.ACCESS_ROLE_CLIENT));
						} else if (e.getAttribute(MohitoGeneratorConstants.ACCESS_ROLE_TAG) != null
								&& e.getAttribute(MohitoGeneratorConstants.ACCESS_ROLE_TAG).equals(
								MohitoGeneratorConstants.ACCESS_ROLE_SERVER)) {
							// remote server
							remoteServerGenerators
									.add(new RemoteGenerator(
											registrationLabel,
											generatorClass,
											eclipseDependencies,
											plainJavaDependencies,
											MohitoGeneratorConstants.ACCESS_ROLE_SERVER));
						} else {
							if (e.getAttribute(MohitoGeneratorConstants.ACCESS_ROLE_TAG) == null) {
								logger.log(
										Level.SEVERE,
										"Unknown access type for remote generator: "
												+ e.getAttribute(MohitoGeneratorConstants.ACCESS_ROLE_TAG)
												+ ".");
							} else {
								logger.info("No access type provided for remote generator with name " + registrationLabel);
							}
						}
					}
				} else if (e.getName().equals(MohitoGeneratorConstants.PROJECT_INITIALIZER_TAG)){
					AbstractProjectInitializer initializer = (AbstractProjectInitializer) getObjectFromConfiguration(e, MohitoGeneratorConstants.PROJECT_INITIALIZATION_CLASS_TAG);
					IGenerator generator =  (IGenerator) getObjectFromConfiguration(e, MohitoGeneratorConstants.PROJECT_INITIALIZATION_GENERATION_CLASS_TAG);
					if (initializer != null && generator != null) {
						projectInitializers.add(new ProjectInitializer(
								registrationLabel, initializer, generator));
					} else {
						logger.info("Project initializer registered with name " + registrationLabel + " did not provide a class. This is required and the defaulting initializer is not added to the list of available project initializers.");
					}
				} else {
					logger.severe("Unknown child in extension registry for point "
							+ MohitoGeneratorConstants.EXTENSION_POINT_ID
							+ ". Most probably only implementation or registration point definition was modified. Make the extension and the implementation compatible.");
				}
			} catch (InvalidRegistryObjectException e1) {
				logger.log(Level.SEVERE, "Error access the registry.", e1);
			} 
		}
	}

	/**
	 * @return the localGenerators
	 */
	public List<LocalGenerator> getLocalGenerators() {
		return localGenerators;
	}

	/**
	 * @return the remoteClientGenerators
	 */
	public List<RemoteGenerator> getRemoteClientGenerators() {
		return remoteClientGenerators;
	}

	/**
	 * @return the remoteServerGenerators
	 */
	public List<RemoteGenerator> getRemoteServerGenerators() {
		return remoteServerGenerators;
	}
	
	/**
	 * @return the projectInitializers
	 */
	public List<ProjectInitializer> getProjectInitializers(){
		return projectInitializers;
	}

}
