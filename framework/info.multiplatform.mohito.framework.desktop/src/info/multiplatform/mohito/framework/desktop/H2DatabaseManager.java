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
package info.multiplatform.mohito.framework.desktop;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

/**Manages access to the H2 In-Memory database.
 * @author hgroenda
 *
 */
public class H2DatabaseManager {
	/** Logger for this class.*/
	private static final Logger logger = Logger.getLogger(H2DatabaseManager.class.getCanonicalName());
	
	/** URL determine the location and type of the database. 
	 * For a list of supported types see {@link http://www.h2database.com/html/cheatSheet.html}. 
	 * Current selection: Unnamed private, one connection. */
	protected static final String databaseUrl = "jdbc:h2:mem";
	/** Connection source. */
	protected static final ConnectionSource connectionSource;
	
	/** Initializes the class. */
	static {
		ConnectionSource source = null;
		try {
			source = new JdbcConnectionSource(databaseUrl);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Could not establish connection to database.", e);
			source = null;
		} finally {
			connectionSource = source;
		}
	}

	/**
	 * @return The connection source to the database.
	 */
	public static final ConnectionSource getConnectionSource() {
			return connectionSource;
	}
}
