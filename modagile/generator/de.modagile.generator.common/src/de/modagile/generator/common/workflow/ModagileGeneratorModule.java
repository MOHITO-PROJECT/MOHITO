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
package de.modagile.generator.common.workflow;


import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;

public class ModagileGeneratorModule extends AbstractGenericResourceRuntimeModule {

	private Class<? extends IGenerator> generator;
	
	public ModagileGeneratorModule(Class<? extends IGenerator> generator) {
		this.generator = generator;
	}
	
	@Override
	protected String getLanguageName() {
		return "de.fzi.mam.dsl.xtend";
	}

	@Override
	protected String getFileExtensions() {
		return "app";
	}
	
	public Class<? extends IGenerator> bindIGenerator() {
		return generator;
	}

	public Class<? extends ResourceSet> bindResourceSet() {
		return ResourceSetImpl.class;
	}
	
}
