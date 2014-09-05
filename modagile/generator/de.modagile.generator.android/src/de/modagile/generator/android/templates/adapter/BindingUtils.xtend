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
package de.modagile.generator.android.templates.adapter

import de.modagile.metamodel.app.ui.CompositeDisplayElementType
import de.modagile.metamodel.app.MobileApp
import de.modagile.metamodel.app.domain.DomainBinding
import de.modagile.metamodel.app.domain.ComplexBinding
import org.eclipse.emf.ecore.EClass

class BindingUtils {
	
	/** For a composite display element type, find a EClass that is bound to the element (or null if nothing is bound). */
	def public EClass getDomainEntityForCDEType(CompositeDisplayElementType type, MobileApp app) {
		if (app.bindingRepository == null || app.bindingRepository.bindings == null || app.bindingRepository.bindings.size == 0) return null;

		for (DomainBinding binding : app.bindingRepository.bindings) {
			if (binding instanceof ComplexBinding) {
				val complexBinding = binding as ComplexBinding;
				if (complexBinding.uiElement.equals(type)) 
					return complexBinding.domainEntity;
			}
		}
		return null as EClass;
	}
	
}	