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
package de.b2m.software.model.util;

import info.multiplatform.mohito.framework.DaoManager;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import de.b2m.software.model.Book;
import de.b2m.software.model.Library;
import de.b2m.software.model.Writer;

/**Model-specific part of DAO-Managers with convenient access to DAOs of MOHITO-Entities of this model.
 * @generated info.multiplatform.mohito.generator.environment.java.UtilitiesTemplate (Xtend) 
 */
public abstract class LibraryDaoManager extends DaoManager {

	/**Creates a new instance.
	 * @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.
	 * @generated info.multiplatform.mohito.generator.environment.java.UtilitiesTemplate (Xtend) 
	 */
	public LibraryDaoManager(boolean useCache) {
		super(useCache);
	}

	/**
	 * @return DAO for {@link Book} MOHITO-Entities.
	 */
	public MohitoEntityDao<Book, String> getBookDao() {
		return getEntityDao(Book.class);
	}

	/**
	 * @return DAO for {@link Library} MOHITO-Entities.
	 */
	public MohitoEntityDao<Library, Integer> getLibraryDao() {
		return getEntityDao(Library.class);
	}

	/**
	 * @return DAO for {@link Writer} MOHITO-Entities.
	 */
	public MohitoEntityDao<Writer, Integer> getWriterDao() {
		return getEntityDao(Writer.class);
	}
}
