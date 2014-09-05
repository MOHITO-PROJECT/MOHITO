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

import java.util.List;
import info.multiplatform.mohito.framework.exceptions.DataLayerException;
import info.multiplatform.mohito.framework.MohitoList;
import info.multiplatform.mohito.framework.MohitoEntity;
import de.b2m.software.model.util.LibraryStorageManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import de.b2m.software.model.local.WriterDaoImpl;

/**Model entity Writer of Library.
 * @generated info.multiplatform.mohito.generator.environment.java.DomainModelTemplate (Xtend) 
 */
@DatabaseTable(daoClass = WriterDaoImpl.class, tableName = "Writer")
public class Writer extends LibraryMohitoEntity<Integer> {
	// Attributes
	@DatabaseField(index = false, columnName = "name", foreign = false, canBeNull = true)
	/** Attribute name. 
	 */
	protected String name;
	@DatabaseField(id = true, index = false, foreign = false, canBeNull = true)
	/** Attribute id. 
			Identifier of this MOHITO-Entity. 
	 */
	protected Integer id;
	// References
	/** Reference books. */
	protected final MohitoList<Book> books;
	@DatabaseField(foreign = true, canBeNull = true)
	/** Reference library. */
	protected Library library;

	// Incoming unnamed references (reverse of containment references without opposite). 		
	// Constructors
	/**Default constructor.
	 */
	public Writer() {
		this.id = generateUUID();
		this.books = new MohitoList<Book>(Book.class, this, "writer");
	}

	/**Proxy constructor.
	 * Marks that this instance only contains a valid id but the values for the entity with that id mus still be retrieved. Allows deferred loading.
	 * @param The id of the entity.
	 */
	public Writer(Integer id) {
		this.id = id;
		this.mIsProxy = true;
		this.books = new MohitoList<Book>(Book.class, this, "writer");
	}

	/**Full constructor.
	 * @param name The name.
	 * @param books The books.
	 * @param library The library.
	 * @param id The id.
	 */
	public Writer(String name, List<Book> books, Library library, Integer id) {

		this.name = name;
		this.books = new MohitoList<Book>(Book.class, this, "writer");
		if (books != null) {
			this.books.addAll(books);
		}
		if (library == null) {
			throw new IllegalArgumentException(
					"The 'library' of this entity must not be NULL or empty.");
		}
		this.library = library;
		if (id == null) {
			throw new IllegalArgumentException(
					"The 'id' of this entity must not be NULL or empty.");
		}
		this.id = id;
	}

	// doCheckProxyAndResolveAssignment and doCheckProxyAndResolveGetReferenceEntity
	@Override
	protected void doCheckProxyAndResolveAssignment(
			MohitoEntity<Integer> reference) {
		Writer ref = (Writer) reference;
		this.name = ref.name;
		this.books.clear();
		this.books.addAll(ref.books);
		this.library = ref.library;
		this.id = ref.id;
	}

	@Override
	protected MohitoEntity<Integer> doCheckProxyAndResolveGetReferenceEntity()
			throws DataLayerException {
		return LibraryStorageManager.mINSTANCE.getAvailableStorageManager()
				.getWriterDao().getById(getId());
	}

	// getter and setter

	@Override
	public Integer getId() {
		return id;
	}

	// No setId(...) method for implicit identifier.
	/**
	 * Get method for the attribute Name
	 */
	public String getName() {
		checkProxyAndResolve();
		return this.name;
	}

	/**
	 * Set method for the attribute Name
	 */
	public void setName(String name) {
		// release
		// set
		this.name = name;
		mSetDirty(true);
	}

	/**
	 * Get method for the attribute Books
	 */
	public MohitoList<Book> getBooks() {
		checkProxyAndResolve();
		return this.books;
	}

	// No setBooks(...) method for a collection
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
			this.library.getWriters().remove(this);
		}
		// set
		this.library = library;
		if (library != null) {
			library.getWriters().add(this);
		}
		mSetDirty(true);
	}

	// No setId(...) method for an identifier
	// domainContentEquals
	@Override
	public boolean domainContentEquals(MohitoEntity<Integer> reference) {
		if (reference instanceof Writer) {
			Writer ref = (Writer) reference;
			boolean result = true;

			result &= this.name.equals(ref.name);
			if (this.books.size() == ref.books.size()) {
				for (int i = 0; i < this.books.size(); i++) {
					result &= this.books.get(i).getId()
							.equals(ref.books.get(i).getId());
				}
			} else {
				result = false;
			}
			result &= this.library.equals(ref.library);
			result &= this.id.equals(ref.id);
			return result;
		}
		return false;
	}
}
