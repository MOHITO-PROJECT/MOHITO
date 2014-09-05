/** 
 * Copyright (c) 2012-2014 B2M Software AG
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
package de.b2m.software.model.local;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.b2m.software.model.Library;
import de.b2m.software.model.Writer;

/**Workaround for OrmLite in order to store attributes with an upper bound greater than one in the database.
 * This handles the attribute behind {@link Library#getWriters()}.
 * @generated info.multiplatform.mohito.generator.storage.local.ormliteandroid.OrmLiteAndroidStorageGenerator (Xtend) 
 */
@DatabaseTable(tableName = "Library_writers")
public class Library_writers {
	/** Unused technical identifier. */
	@DatabaseField(generatedId = true)
	protected Long id;
	/** ID of the MOHITO-Entity to which this entity belongs. */
	@DatabaseField(index = true)
	protected Integer containmentId;
	/** Id of the referenced entity. */
	@DatabaseField(canBeNull = false)
	protected Integer value;

	public Library_writers() {
	}

	public Library_writers(Integer containmentId, Integer value) {
		this.containmentId = containmentId;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	/**
	 * @return The id of the referenced object {@link Writer}. The real value for basic types.
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value The id of the referenced {@link Writer}. The real value for basic types.
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @param value The id of the referenced {@link Library}.
	 */
	public Integer getContainmentId() {
		return containmentId;
	}
	//REMARK No setter for containment id;

}
