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

/**Interface for the definition of a single entry of an MOHITO-Annotation.
 * @author hgroenda
 *
 */
public interface MohitoAnnotationDefinition {
	/**
	 * @return the namespace of the enclosing category.
	 */
	public String getNamespace();

	/**
	 * @return the scope. It determines which type of objects can be annotated by this definition.
	 */
	public String getScope();

	/**
	 * @return the property. It describes the unique key within the namespace used to load or store the actual value for an annotation.
	 */
	public String getProperty();

	/**
	 * @return the description
	 */
	public String getDescription();

	/**
	 * @return the dataType
	 */
	public String getDataType();

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @return the defaultValue
	 */
	public String getDefaultValue();

	/**
	 * @param namespace Set the namespace coming from the category. This should be done at associatation time.
	 */
	void setNamespace(String namespace);
}
