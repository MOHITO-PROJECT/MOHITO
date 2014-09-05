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
package de.b2m.software.model;

import info.multiplatform.mohito.framework.exceptions.DataLayerException;
import info.multiplatform.mohito.framework.MohitoEntity;
import de.b2m.software.model.util.LibraryStorageManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**Model entity Book of Library.
 * @generated info.multiplatform.mohito.generator.environment.java.DomainModelTemplate (Xtend) 
 */
@DatabaseTable(tableName = "Book")
public class Book extends LibraryMohitoEntity<String> {
	// Attributes
	@DatabaseField(id = true, index = false, columnName = "title_id", foreign = false, canBeNull = true)
	/** Attribute title. 
			Identifier of this MOHITO-Entity. 
	 */
	protected String title;
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	/** Attribute pages. 
	 */
	protected Integer pages;
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	/** Attribute category. 
	 */
	protected Category category;
	@DatabaseField(index = false, foreign = false, canBeNull = true)
	/** Attribute blurb. 
	 */
	protected String blurb;
	// References
	@DatabaseField(foreign = true, canBeNull = true)
	/** Reference writer. */
	protected Writer writer;
	@DatabaseField(foreign = true, canBeNull = true)
	/** Reference library. */
	protected Library library;

	// Incoming unnamed references (reverse of containment references without opposite). 		
	// Constructors
	/**Default constructor.
	 */
	public Book() {
		this.title = generateUUID();
	}

	/**Proxy constructor.
	 * Marks that this instance only contains a valid id but the values for the entity with that id mus still be retrieved. Allows deferred loading.
	 * @param The id of the entity.
	 */
	public Book(String id) {
		this.title = id;
		this.mIsProxy = true;
	}

	/**Full constructor.
	 * @param title The title.
	 * @param pages The pages.
	 * @param category The category.
	 * @param writer The writer.
	 * @param library The library.
	 * @param blurb The blurb.
	 */
	public Book(String title, Integer pages, Category category, Writer writer,
			Library library, String blurb) {

		this.title = title;
		this.pages = pages;
		this.category = category;
		if (writer == null) {
			throw new IllegalArgumentException(
					"The 'writer' of this entity must not be NULL or empty.");
		}
		this.writer = writer;
		if (library == null) {
			throw new IllegalArgumentException(
					"The 'library' of this entity must not be NULL or empty.");
		}
		this.library = library;
		this.blurb = blurb;
	}

	// doCheckProxyAndResolveAssignment and doCheckProxyAndResolveGetReferenceEntity
	@Override
	protected void doCheckProxyAndResolveAssignment(
			MohitoEntity<String> reference) {
		Book ref = (Book) reference;
		this.title = ref.title;
		this.pages = ref.pages;
		this.category = ref.category;
		this.writer = ref.writer;
		this.library = ref.library;
		this.blurb = ref.blurb;
	}

	@Override
	protected MohitoEntity<String> doCheckProxyAndResolveGetReferenceEntity()
			throws DataLayerException {
		return LibraryStorageManager.mINSTANCE.getAvailableStorageManager()
				.getBookDao().getById(getId());
	}

	// getter and setter

	@Override
	public String getId() {
		return title;
	}

	// No setId(...) method for implicit identifier.
	// No setTitle(...) method for an identifier
	/**
	 * Get method for the attribute Pages
	 */
	public Integer getPages() {
		checkProxyAndResolve();
		return this.pages;
	}

	/**
	 * Set method for the attribute Pages
	 */
	public void setPages(Integer pages) {
		// release
		// set
		this.pages = pages;
		mSetDirty(true);
	}

	/**
	 * Get method for the attribute Category
	 */
	public Category getCategory() {
		checkProxyAndResolve();
		return this.category;
	}

	/**
	 * Set method for the attribute Category
	 */
	public void setCategory(Category category) {
		// release
		// set
		this.category = category;
		mSetDirty(true);
	}

	/**
	 * Get method for the attribute Writer
	 */
	public Writer getWriter() {
		checkProxyAndResolve();
		return this.writer;
	}

	/**
	 * Set method for the attribute Writer
	 */
	public void setWriter(Writer writer) {
		// release
		if (this.writer != null) {
			this.writer.getBooks().remove(this);
		}
		// set
		this.writer = writer;
		if (writer != null) {
			writer.getBooks().add(this);
		}
		mSetDirty(true);
	}

	/**
	 * Get method for the attribute Library
	 */
	public Library getLibrary() {
		checkProxyAndResolve();
		return this.library;
	}

	/**
	 * Set method for the attribute Library
	 */
	public void setLibrary(Library library) {
		// release
		if (this.library != null) {
			this.library.getBooks().remove(this);
		}
		// set
		this.library = library;
		if (library != null) {
			library.getBooks().add(this);
		}
		mSetDirty(true);
	}

	/**
	 * Get method for the attribute Blurb
	 */
	public String getBlurb() {
		checkProxyAndResolve();
		return this.blurb;
	}

	/**
	 * Set method for the attribute Blurb
	 */
	public void setBlurb(String blurb) {
		// release
		// set
		this.blurb = blurb;
		mSetDirty(true);
	}

	// domainContentEquals
	@Override
	public boolean domainContentEquals(MohitoEntity<String> reference) {
		if (reference instanceof Book) {
			Book ref = (Book) reference;
			boolean result = true;

			result &= this.title.equals(ref.title);
			result &= this.pages.equals(ref.pages);
			result &= this.category.equals(ref.category);
			result &= this.writer.equals(ref.writer);
			result &= this.library.equals(ref.library);
			result &= this.blurb.equals(ref.blurb);
			return result;
		}
		return false;
	}
}
