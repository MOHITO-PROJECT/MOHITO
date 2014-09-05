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
package de.modagile.generator.android.templates.adapter;

import com.google.common.base.Objects;
import de.modagile.metamodel.app.MobileApp;
import de.modagile.metamodel.app.domain.BindingRepository;
import de.modagile.metamodel.app.domain.ComplexBinding;
import de.modagile.metamodel.app.domain.DomainBinding;
import de.modagile.metamodel.app.ui.CompositeDisplayElementType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

@SuppressWarnings("all")
public class BindingUtils {
  /**
   * For a composite display element type, find a EClass that is bound to the element (or null if nothing is bound).
   */
  public EClass getDomainEntityForCDEType(final CompositeDisplayElementType type, final MobileApp app) {
    boolean _or = false;
    boolean _or_1 = false;
    BindingRepository _bindingRepository = app.getBindingRepository();
    boolean _equals = Objects.equal(_bindingRepository, null);
    if (_equals) {
      _or_1 = true;
    } else {
      BindingRepository _bindingRepository_1 = app.getBindingRepository();
      EList<DomainBinding> _bindings = _bindingRepository_1.getBindings();
      boolean _equals_1 = Objects.equal(_bindings, null);
      _or_1 = (_equals || _equals_1);
    }
    if (_or_1) {
      _or = true;
    } else {
      BindingRepository _bindingRepository_2 = app.getBindingRepository();
      EList<DomainBinding> _bindings_1 = _bindingRepository_2.getBindings();
      int _size = _bindings_1.size();
      boolean _equals_2 = (_size == 0);
      _or = (_or_1 || _equals_2);
    }
    if (_or) {
      return null;
    }
    BindingRepository _bindingRepository_3 = app.getBindingRepository();
    EList<DomainBinding> _bindings_2 = _bindingRepository_3.getBindings();
    for (final DomainBinding binding : _bindings_2) {
      if ((binding instanceof ComplexBinding)) {
        final ComplexBinding complexBinding = ((ComplexBinding) binding);
        CompositeDisplayElementType _uiElement = complexBinding.getUiElement();
        boolean _equals_3 = _uiElement.equals(type);
        if (_equals_3) {
          return complexBinding.getDomainEntity();
        }
      }
    }
    return ((EClass) null);
  }
}
