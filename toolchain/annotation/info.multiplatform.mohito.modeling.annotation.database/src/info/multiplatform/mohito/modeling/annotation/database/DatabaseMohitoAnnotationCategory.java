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
package info.multiplatform.mohito.modeling.annotation.database;

import info.multiplatform.mohito.modeling.annotation.AbstractMohitoAnnotationCategoryDefinition;
import info.multiplatform.mohito.modeling.annotation.AbstractMohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationDefinition;
import info.multiplatform.mohito.modeling.annotation.MohitoAnnotationsDefinitionsConstants;

/**Definitions for the MOHITO-Annotation category 'Database'.
 * 
 * @author hgroenda
 *
 */
public class DatabaseMohitoAnnotationCategory extends
		AbstractMohitoAnnotationCategoryDefinition {
	/** Namespace of this category. */
	public static final String NAMESPACE = "http://www.multiplatform.info/mohito/annotations/database";
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition SERIALIZE_IN_DB  = new AbstractMohitoAnnotationDefinition(
			"Serialize in Database", 
			"Toggles if the business object is stored in a database.", 
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_ECLASS,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"isTable",
			Boolean.TRUE.toString());
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition FIELD_SERIALIZE_IN_DB = new AbstractMohitoAnnotationDefinition(
			"Serialize in Database",
			"Toggles if the property of the business object is stored in a database.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"isField",
			Boolean.FALSE.toString());
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition FIELD_COLUMN_NAME_IN_DB = new AbstractMohitoAnnotationDefinition(
			"Force column name in Database",
			"The name of the column in the database (if the default needs to be changed).",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_STRING,
			"columnname",
			"");
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition FIELD_IS_IDENTIFIER = new AbstractMohitoAnnotationDefinition(
			"Serialize as Identifier",
			"This field is part of the unique identifier in the database.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"id",
			Boolean.FALSE.toString());
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition FIELD_BUILD_INDEX = new AbstractMohitoAnnotationDefinition(
			"Build index",
			"Determines if an index should be build for this property in the database.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"index",
			Boolean.FALSE.toString());
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition FIELD_IS_FOREIGN_KEY = new AbstractMohitoAnnotationDefinition(
			"Is foreign key",
			"Determines if this property is a foreign key.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"foreign",
			Boolean.FALSE.toString());
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition FIELD_IS_NULLABLE = new AbstractMohitoAnnotationDefinition(
			"Can be null",
			"Determines if the field is allowed to contain null values.",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"nullable",
			Boolean.TRUE.toString());
	/** MOHITO-Annotation entry. */
	public static final MohitoAnnotationDefinition FIELD_MARK_AS_GENERATED = new AbstractMohitoAnnotationDefinition(
			"Is generated (?)",
			"???",
			MohitoAnnotationsDefinitionsConstants.TARGET_TYPE_EATTRIBUTE,
			MohitoAnnotationsDefinitionsConstants.DATA_TYPE_BOOLEAN,
			"generateId",
			Boolean.FALSE.toString());

	/**Creates the definition(s) of the category 'database'.
	 */
	public DatabaseMohitoAnnotationCategory() {
		super(NAMESPACE, SERIALIZE_IN_DB, FIELD_SERIALIZE_IN_DB,
				FIELD_COLUMN_NAME_IN_DB, FIELD_IS_IDENTIFIER,
				FIELD_BUILD_INDEX, FIELD_IS_FOREIGN_KEY, FIELD_IS_NULLABLE,
				FIELD_MARK_AS_GENERATED);
	}

}
