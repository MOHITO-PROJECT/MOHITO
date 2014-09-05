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

/**Convenvience class for the definition of MOHITO-Annotation entries of categories.
 * @author hgroenda
 *
 */
public class AbstractMohitoAnnotationDefinition implements
		MohitoAnnotationDefinition {
	
	/** Human readable short identifier for the property, which is described by the annotation. */
	private final String name;
	/** Human readable description of the meaning of the property. */
	private final String description;
	/** Type of elements which can be annotated. */
	private final String scope;
	/** Data type of the annotation itself. */
	private final String dataType;
	/** Textual identifier used for the (de-)serialization of the property. */
	private final String property;
	/** Default value of the serialized property. */
	private final String defaultValue;
	/** Namespace of the category, which this definition is part of. */
	private String namespace;

	/**Creates a new annotation definition.
	 * @param name Human readable short identifier for the property, which is described by the annotation.
	 * @param description Human readable description of the meaning of the property.
	 * @param scope Type of elements which can be annotated.
	 * @param dataType Data type of the annotation itself.
	 * @param property Textual identifier used for the (de-)serialization of the property.
	 * @param defaultValue Default value of the serialized property.
	 */
	public AbstractMohitoAnnotationDefinition(String name,
			String description, String scope, String dataType, String property,
			String defaultValue) {
		this.scope = scope;
		this.property = property;
		this.description = description;
		this.dataType = dataType;
		this.name = name;
		this.defaultValue = defaultValue;
	}
	
	/* (non-Javadoc)
	 * @see info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition#getScope()
	 */
	@Override
	public String getScope() {
		return scope;
	}

	/* (non-Javadoc)
	 * @see info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition#getProperty()
	 */
	@Override
	public String getProperty() {
		return property;
	}

	/* (non-Javadoc)
	 * @see info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition#getDataType()
	 */
	@Override
	public String getDataType() {
		return dataType;
	}

	/* (non-Javadoc)
	 * @see info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition#getDefaultValue()
	 */
	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	/* (non-Javadoc)
	 * @see info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition#getDefaultValue()
	 */
	@Override
	public String getNamespace() {
		return this.namespace;
	}

	/* (non-Javadoc)
	 * @see info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition#getDefaultValue()
	 */
	@Override
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
}
