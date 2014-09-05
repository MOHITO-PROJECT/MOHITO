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
package info.multiplatform.mohito.modeling.annotation;

import java.util.Collection;

/**Interface of the definition of a MOHITO-Annotation category.
 * @author hgroenda
 *
 */
public interface MohitoAnnotationCategoryDefinition {

	/**
	 * @return The namespace of this category.
	 */
	public String getNamespace();

	/**
	 * @return The MOHITO-Annotation entries defined for this category.
	 */
	public Collection<MohitoAnnotationDefinition> getMohitoAnnotationDefinitions();
}
