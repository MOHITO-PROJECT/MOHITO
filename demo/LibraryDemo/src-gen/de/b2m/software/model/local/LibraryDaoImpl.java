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

import de.b2m.software.model.Book;
import de.b2m.software.model.Writer;
import de.b2m.software.model.Library;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**Workaround for OrmLite in order to store attributes with an upper bound greater than one in the database.
 * Handles loading/storing the attributes.
 * @generated info.multiplatform.mohito.generator.storage.local.ormliteandroid.OrmLiteAndroidStorageGenerator (Xtend) 
 */
public class LibraryDaoImpl extends BaseDaoImpl<Library, Integer> {
	// initialize additional database tables
	/** Logger for this class. */
	private static final Logger logger = Logger.getLogger(LibraryDaoImpl.class
			.getCanonicalName());

	// DAOs for each attribute with an upper bound greater than 1
	/** Dao for the attribute behind {@link Library#getBooks()}. */
	private final Dao<Library_books, String> booksDao;
	/** Dao for the attribute behind {@link Library#getWriters()}. */
	private final Dao<Library_writers, Integer> writersDao;

	public LibraryDaoImpl(ConnectionSource connectionSource)
			throws SQLException {
		super(connectionSource, Library.class);
		// books
		this.booksDao = DaoManager.createDao(connectionSource,
				Library_books.class);
		// writers
		this.writersDao = DaoManager.createDao(connectionSource,
				Library_writers.class);
	}

	@Override
	public int create(Library data) throws SQLException {
		int rows = super.create(data);
		// books
		Library_books library_books;
		for (Book value : data.getBooks()) {
			library_books = new Library_books(data.getId(), value.getId());
			rows += booksDao.create(library_books);
		}
		// writers
		Library_writers library_writers;
		for (Writer value : data.getWriters()) {
			library_writers = new Library_writers(data.getId(), value.getId());
			rows += writersDao.create(library_writers);
		}
		return rows;
	}

	@Override
	public int update(Library data) throws SQLException {
		int rows = super.update(data);
		// books
		Library_books library_books;
		DeleteBuilder<Library_books, String> booksDeleteBuilder = booksDao
				.deleteBuilder();
		booksDeleteBuilder.where().eq("containmentId", data.getId());
		rows += booksDeleteBuilder.delete();
		for (Book books : data.getBooks()) {
			library_books = new Library_books(data.getId(), books.getId());
			rows += booksDao.create(library_books);
		}
		// writers
		Library_writers library_writers;
		DeleteBuilder<Library_writers, Integer> writersDeleteBuilder = writersDao
				.deleteBuilder();
		writersDeleteBuilder.where().eq("containmentId", data.getId());
		rows += writersDeleteBuilder.delete();
		for (Writer writers : data.getWriters()) {
			library_writers = new Library_writers(data.getId(), writers.getId());
			rows += writersDao.create(library_writers);
		}
		return rows;
	}

	@Override
	public int delete(Library data) throws SQLException {
		int rows = super.delete(data);
		// books
		DeleteBuilder<Library_books, String> booksDeleteBuilder = booksDao
				.deleteBuilder();
		booksDeleteBuilder.where().eq("containmentId", data.getId());
		rows += booksDeleteBuilder.delete();
		// writers
		DeleteBuilder<Library_writers, Integer> writersDeleteBuilder = writersDao
				.deleteBuilder();
		writersDeleteBuilder.where().eq("containmentId", data.getId());
		rows += writersDeleteBuilder.delete();
		return rows;
	}

	@Override
	public int refresh(Library data) throws SQLException {
		throw new IllegalStateException(
				"Not Supported by this implementation. It is not necessary to refresh generated helper objects.");
	}

	@Override
	public Library queryForId(Integer id) throws SQLException {
		Library result = super.queryForId(id);
		if (result != null) {
			boolean oldMIsDirty = result.mIsDirty();
			// books
			QueryBuilder<Library_books, String> booksQueryBuilder = booksDao
					.queryBuilder();
			booksQueryBuilder.where().eq("containmentId", id);
			List<Library_books> booksList = booksQueryBuilder.query();
			for (Library_books data : booksList) {
				Book value = new Book(data.getValue());
				result.getBooks().add(value);
			}
			// writers
			QueryBuilder<Library_writers, Integer> writersQueryBuilder = writersDao
					.queryBuilder();
			writersQueryBuilder.where().eq("containmentId", id);
			List<Library_writers> writersList = writersQueryBuilder.query();
			for (Library_writers data : writersList) {
				Writer value = new Writer(data.getValue());
				result.getWriters().add(value);
			}
			result.mSetDirty(oldMIsDirty);
		}
		return result;
	}

}
