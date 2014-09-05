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
package info.multiplatform.mohito.framework.android;

import android.content.Context;


/**
 * Helper class, which enables direct access to the underlying database.
 * 
 * @author egailus
 */
public class DatabaseHelperManager {

	/**
	 * Singleton instance to the database helper 
	 */
	private static DatabaseHelperManager mINSTANCE = new DatabaseHelperManager();
	
	private IAndroidDatabaseHelper mRegisteredDbHelper;
	private Context mActiveContext;
	
	
	private DatabaseHelperManager() {
		mActiveContext = null;
	}
	
	
	/**
	 * @return	returns the singleton instance
	 */
	public static DatabaseHelperManager getInstance() {
		return mINSTANCE;
	}
	
	
	/**
	 * @return	returns the active context bound to that database helper
	 */
	public Context getActiveContext() {
		return mActiveContext;
	}
	

	/**
	 * @return	returns the database helper
	 */
	public synchronized IAndroidDatabaseHelper getDbHelper() {		
		return mRegisteredDbHelper;
	}
	
	
	/**
	 * Register a given Android context with an instance of a data base helper which is granting access the the 
	 * underlying database during the life cycle of the context. 
	 * @param context	The Android context being registered 
	 * @param dbhelper	The instance of a data base helper which is granting access the the 
	 * underlying database
	 */
	public synchronized void registerDatabaseHelper(Context context, IAndroidDatabaseHelper dbhelper) {
		mRegisteredDbHelper = dbhelper;
		mActiveContext = context;
	}

	
	/**
	 * Unregister a given Android context with an instance of a data base helper when the life cycle of the context ends 
	 * @param context	The Android context being unregistered 
	 */
	public synchronized void unregisterDatabaseHelper(Context context) {
		mRegisteredDbHelper = null;
		mActiveContext = null;
	}

}
