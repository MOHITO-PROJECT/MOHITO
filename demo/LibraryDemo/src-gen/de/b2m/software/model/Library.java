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
import de.b2m.software.model.local.LibraryDaoImpl;

/**Model entity Library of Library.
 * @generated info.multiplatform.mohito.generator.environment.java.DomainModelTemplate (Xtend) 
 */
@DatabaseTable(daoClass = LibraryDaoImpl.class, tableName = "Library")
public class Library extends LibraryMohitoEntity<Integer> {
	// Attributes
	@DatabaseField(index = false, columnName = "name", foreign = false, canBeNull = true)
	/** Attribute name. 
	 */
	protected String name;
	@DatabaseField(id = true, index = false, foreign = false, canBeNull = false)
	/** Attribute id. 
			Identifier of this MOHITO-Entity. 
	 */
	protected Integer id;
	// References
	/** Containment reference. books. */
	protected final MohitoList<Book> books;
	/** Containment reference. writers. */
	protected final MohitoList<Writer> writers;

	// Incoming unnamed references (reverse of containment references without opposite). 		
	// Constructors
	/**Default constructor.
	 */
	public Library() {
		this.id = generateUUID();
		this.books = new MohitoList<Book>(Book.class, this, "library");
		this.writers = new MohitoList<Writer>(Writer.class, this, "library");
	}

	/**Proxy constructor.
	 * Marks that this instance only contains a valid id but the values for the entity with that id mus still be retrieved. Allows deferred loading.
	 * @param The id of the entity.
	 */
	public Library(Integer id) {
		this.id = id;
		this.mIsProxy = true;
		this.books = new MohitoList<Book>(Book.class, this, "library");
		this.writers = new MohitoList<Writer>(Writer.class, this, "library");
	}

	/**Full constructor.
	 * @param name The name.
	 * @param books The books.
	 * @param writers The writers.
	 * @param id The id.
	 */
	public Library(String name, List<Book> books, List<Writer> writers,
			Integer id) {

		this.name = name;
		this.books = new MohitoList<Book>(Book.class, this, "library");
		if (books != null) {
			this.books.addAll(books);
		}
		this.writers = new MohitoList<Writer>(Writer.class, this, "library");
		if (writers != null) {
			this.writers.addAll(writers);
		}
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
		Library ref = (Library) reference;
		this.name = ref.name;
		this.books.clear();
		this.books.addAll(ref.books);
		this.writers.clear();
		this.writers.addAll(ref.writers);
		this.id = ref.id;
	}

	@Override
	protected MohitoEntity<Integer> doCheckProxyAndResolveGetReferenceEntity()
			throws DataLayerException {
		return LibraryStorageManager.mINSTANCE.getAvailableStorageManager()
				.getLibraryDao().getById(getId());
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
	 * Get method for the attribute Writers
	 */
	public MohitoList<Writer> getWriters() {
		checkProxyAndResolve();
		return this.writers;
	}

	// No setWriters(...) method for a collection
	// No setId(...) method for an identifier
	// domainContentEquals
	@Override
	public boolean domainContentEquals(MohitoEntity<Integer> reference) {
		if (reference instanceof Library) {
			Library ref = (Library) reference;
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
			if (this.writers.size() == ref.writers.size()) {
				for (int i = 0; i < this.writers.size(); i++) {
					result &= this.writers.get(i).getId()
							.equals(ref.writers.get(i).getId());
				}
			} else {
				result = false;
			}
			result &= this.id.equals(ref.id);
			return result;
		}
		return false;
	}
}
