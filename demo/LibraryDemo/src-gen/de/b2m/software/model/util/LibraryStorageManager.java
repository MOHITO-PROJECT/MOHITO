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

import info.multiplatform.mohito.framework.StorageManager;
import info.multiplatform.mohito.framework.DaoManager;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import de.b2m.software.model.Book;
import de.b2m.software.model.Library;
import de.b2m.software.model.Writer;

/**Entity Manager for Library entities.
 * @generated info.multiplatform.mohito.generator.environment.java.UtilitiesTemplate (Xtend) 
 */
public class LibraryStorageManager extends StorageManager<LibraryDaoManager> {
	/**Singleton representing this manager.
	 */
	public static final LibraryStorageManager mINSTANCE = new LibraryStorageManager();

	/**Default constructor creating local and remote DAO managers.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LibraryStorageManager() {
		super(new LocalDaoManager(true), new RemoteDaoManager(false),
				new SynchronizingDaoManager(false), true, false, true, false,
				Book.class, Library.class, Writer.class

		);
		getLocalStorageManager().setStorageManager((StorageManager) this);
		getRemoteStorageManager().setStorageManager((StorageManager) this);
	}

}
