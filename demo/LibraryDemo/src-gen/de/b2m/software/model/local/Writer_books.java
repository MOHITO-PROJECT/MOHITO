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
import de.b2m.software.model.Writer;
import de.b2m.software.model.Book;

/**Workaround for OrmLite in order to store attributes with an upper bound greater than one in the database.
 * This handles the attribute behind {@link Writer#getBooks()}.
 * @generated info.multiplatform.mohito.generator.storage.local.ormliteandroid.OrmLiteAndroidStorageGenerator (Xtend) 
 */
@DatabaseTable(tableName = "Writer_books")
public class Writer_books {
	/** Unused technical identifier. */
	@DatabaseField(generatedId = true)
	protected Long id;
	/** ID of the MOHITO-Entity to which this entity belongs. */
	@DatabaseField(index = true)
	protected Integer containmentId;
	/** Id of the referenced entity. */
	@DatabaseField(canBeNull = false)
	protected String value;

	public Writer_books() {
	}

	public Writer_books(Integer containmentId, String value) {
		this.containmentId = containmentId;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	/**
	 * @return The id of the referenced object {@link Book}. The real value for basic types.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value The id of the referenced {@link Book}. The real value for basic types.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param value The id of the referenced {@link Writer}.
	 */
	public Integer getContainmentId() {
		return containmentId;
	}
	//REMARK No setter for containment id;

}
