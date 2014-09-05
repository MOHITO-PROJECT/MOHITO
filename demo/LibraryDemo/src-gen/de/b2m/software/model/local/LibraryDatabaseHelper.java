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

import de.b2m.software.model.*;
import info.multiplatform.mohito.framework.android.IAndroidDatabaseHelper;
import java.sql.SQLException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class LibraryDatabaseHelper extends OrmLiteSqliteOpenHelper implements
		IAndroidDatabaseHelper {

	private static String LOG_TAG = LibraryDatabaseHelper.class.getSimpleName();

	/** Name of the database, also used as file name on Android to store the database. */
	public static final String DATABASE_NAME = "Library.db";

	/** any time you make changes to your database objects, you may have to increase the database version
	 */
	public static final int DATABASE_VERSION = 1;

	public LibraryDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database must be created for the first time. 
	 * Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		Log.i(LOG_TAG,
				"onCreate: Creating tables in the database for storing the MOHITO domain model Library.");
		try {
			TableUtils.createTable(connectionSource, Book.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Book.class);",
					e);
		}
		try {
			TableUtils.createTable(connectionSource, Library.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library.class);",
					e);
		}
		try {
			TableUtils.createTable(connectionSource, Library_books.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library_books.class);",
					e);
		}
		try {
			TableUtils.createTable(connectionSource, Library_writers.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library_writers.class);",
					e);
		}
		try {
			TableUtils.createTable(connectionSource, Writer.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Writer.class);",
					e);
		}
		try {
			TableUtils.createTable(connectionSource, Writer_books.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Writer_books.class);",
					e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		Log.i(LOG_TAG,
				"onUpgrade: Upgrading tables in the database for storing the MOHITO domain model Library.");
		try {
			TableUtils.dropTable(connectionSource, Book.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Book.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Library.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Library.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Library_books.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Library_books.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Library_writers.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Library_writers.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Writer.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Writer.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Writer_books.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Writer_books.class, true);",
					e);
		}
		// after we drop the old databases, we create the new ones
		try {
			TableUtils.createTable(connectionSource, Book.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Book.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Library.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Library_books.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library_books.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Library_writers.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library_writers.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Writer.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Writer.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Writer_books.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Writer_books.class);",
					e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteDb() {
		try {
			TableUtils.dropTable(connectionSource, Book.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Book.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Library.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Library.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Library_books.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Library_books.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Library_writers.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Library_writers.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Writer.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Writer.class, true);",
					e);
		}
		try {
			TableUtils.dropTable(connectionSource, Writer_books.class, true);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.dropTable(connectionSource, Writer_books.class, true);",
					e);
		}
		// after we drop the old databases, we create the new ones
		try {
			TableUtils.createTable(connectionSource, Book.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Book.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Library.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Library_books.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library_books.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Library_writers.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Library_writers.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Writer.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Writer.class);",
					e);
			throw new RuntimeException(e);
		}
		try {
			TableUtils.createTable(connectionSource, Writer_books.class);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Can't execute statement: TableUtils.createTable(connectionSource, Writer_books.class);",
					e);
			throw new RuntimeException(e);
		}
	}

	public void registerDatabaseHelper(Context arg0, IAndroidDatabaseHelper arg1) {
		// not required by this version of the helper.
		throw new UnsupportedOperationException();
	}

	public void unregisterDatabaseHelper(Context arg0) {
		// not required by this version of the helper.
		throw new UnsupportedOperationException();
	}

}
