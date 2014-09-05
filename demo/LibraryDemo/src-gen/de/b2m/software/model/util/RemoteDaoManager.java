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

import info.multiplatform.mohito.framework.MohitoEntity;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import info.multiplatform.mohito.framework.DaoManager;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import de.b2m.software.model.Book;
import de.b2m.software.model.Library;
import de.b2m.software.model.Writer;
import info.multiplatform.mohito.framework.remote.mml.MmlServerConfiguration;
import info.multiplatform.mohito.framework.remote.mml.MmlActionConfiguration;
import info.multiplatform.mohito.framework.remote.mml.MohitoEntityMmlDaoImpl;
import info.multiplatform.mohito.framework.remote.mml.IMMLRemoteDaoManager;
import info.multiplatform.mohito.framework.remote.mml.MmlRemoteRequestHandler;

/**DAO Manager for remote access.
 * @generated info.multiplatform.mohito.generator.environment.java.UtilitiesTemplate (Xtend) 
 */
public class RemoteDaoManager extends LibraryDaoManager implements
		IMMLRemoteDaoManager {

	/**Creates a new instance.
	 * @param useCache Determines if all data objects returned by any managed DAO are cached. If caching is enabled, references to existing objects are returned instead of the generation of new instances.
	 */
	public RemoteDaoManager(boolean useCache) {
		super(useCache);
		this.mmlRemoteRequestHandler = new MmlRemoteRequestHandler(
				new MmlServerConfiguration("http", "dodo", "8080",
						"/LibraryHost/LibraryService.svc", 8000, 20000));
	}

	@Override
	protected <T extends MohitoEntity<PK>, PK> MohitoEntityDao<T, PK> doGetEntityDao(
			Class<T> mohitoEntityClass) {
		if (mohitoEntityClass == Book.class) {
			return new MohitoEntityMmlDaoImpl(this, Book.class,
					new MmlActionConfiguration("?action=create&target=dao",
							"?action=delete&target=dao&id=",
							"?action=update&target=dao&id=", "/",
							"?action=getbycriteria&target=dao", ""));
		}
		if (mohitoEntityClass == Library.class) {
			return new MohitoEntityMmlDaoImpl(this, Library.class,
					new MmlActionConfiguration("?action=create&target=dao",
							"?action=delete&target=dao&id=",
							"?action=update&target=dao&id=", "/",
							"?action=getbycriteria&target=dao", ""));
		}
		if (mohitoEntityClass == Writer.class) {
			return new MohitoEntityMmlDaoImpl(this, Writer.class,
					new MmlActionConfiguration("?action=create&target=dao",
							"?action=delete&target=dao&id=",
							"?action=update&target=dao&id=", "/",
							"?action=getbycriteria&target=dao", ""));
		}
		String msg;
		if (mohitoEntityClass == null) {
			msg = "A DAO cannot be created without class information. Make a proper request to the API.";
		} else {
			msg = "DAO for the type "
					+ mohitoEntityClass.getCanonicalName()
					+ " cannot be provided. A request can only be made for domain classes, no enumerations. Ensure a proper API usage.";
		}
		throw new IllegalStateException(msg);
	}

	/** The handler for MML remote requests. */
	protected final MmlRemoteRequestHandler mmlRemoteRequestHandler;

	public MmlRemoteRequestHandler getMmlRemoteRequestHandler() {
		return this.mmlRemoteRequestHandler;
	}
}
