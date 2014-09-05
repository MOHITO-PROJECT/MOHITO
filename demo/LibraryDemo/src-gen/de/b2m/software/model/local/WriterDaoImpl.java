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
public class WriterDaoImpl extends BaseDaoImpl<Writer, Integer> {
	// initialize additional database tables
	/** Logger for this class. */
	private static final Logger logger = Logger.getLogger(WriterDaoImpl.class
			.getCanonicalName());

	// DAOs for each attribute with an upper bound greater than 1
	/** Dao for the attribute behind {@link Writer#getBooks()}. */
	private final Dao<Writer_books, String> booksDao;

	public WriterDaoImpl(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Writer.class);
		// books
		this.booksDao = DaoManager.createDao(connectionSource,
				Writer_books.class);
	}

	@Override
	public int create(Writer data) throws SQLException {
		int rows = super.create(data);
		// books
		Writer_books writer_books;
		for (Book value : data.getBooks()) {
			writer_books = new Writer_books(data.getId(), value.getId());
			rows += booksDao.create(writer_books);
		}
		return rows;
	}

	@Override
	public int update(Writer data) throws SQLException {
		int rows = super.update(data);
		// books
		Writer_books writer_books;
		DeleteBuilder<Writer_books, String> booksDeleteBuilder = booksDao
				.deleteBuilder();
		booksDeleteBuilder.where().eq("containmentId", data.getId());
		rows += booksDeleteBuilder.delete();
		for (Book books : data.getBooks()) {
			writer_books = new Writer_books(data.getId(), books.getId());
			rows += booksDao.create(writer_books);
		}
		return rows;
	}

	@Override
	public int delete(Writer data) throws SQLException {
		int rows = super.delete(data);
		// books
		DeleteBuilder<Writer_books, String> booksDeleteBuilder = booksDao
				.deleteBuilder();
		booksDeleteBuilder.where().eq("containmentId", data.getId());
		rows += booksDeleteBuilder.delete();
		return rows;
	}

	@Override
	public int refresh(Writer data) throws SQLException {
		throw new IllegalStateException(
				"Not Supported by this implementation. It is not necessary to refresh generated helper objects.");
	}

	@Override
	public Writer queryForId(Integer id) throws SQLException {
		Writer result = super.queryForId(id);
		if (result != null) {
			boolean oldMIsDirty = result.mIsDirty();
			// books
			QueryBuilder<Writer_books, String> booksQueryBuilder = booksDao
					.queryBuilder();
			booksQueryBuilder.where().eq("containmentId", id);
			List<Writer_books> booksList = booksQueryBuilder.query();
			for (Writer_books data : booksList) {
				Book value = new Book(data.getValue());
				result.getBooks().add(value);
			}
			result.mSetDirty(oldMIsDirty);
		}
		return result;
	}

}
