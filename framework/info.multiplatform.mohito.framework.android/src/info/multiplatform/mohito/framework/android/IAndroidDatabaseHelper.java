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

import info.multiplatform.mohito.framework.IDatabaseHelper;
import android.content.Context;

import com.j256.ormlite.support.ConnectionSource;

/**
 * This class enables direct access to the underlying database.
 * 
 * @author egailus
 *
 */
public interface IAndroidDatabaseHelper extends IDatabaseHelper {
		
	/**
	 * Retrieve a connection to the underlying database.
	 * 
	 * @return	connection to the underlying database.
	 */
	ConnectionSource getConnectionSource();	
	
	/**
	 * Register a given Android context with an instance of a data base helper which is granting access the the 
	 * underlying database during the life cycle of the context. 
	 * @param context	The Android context being registered 
	 * @param dbhelper	The instance of a data base helper which is granting access the the 
	 * underlying database
	 */
	void registerDatabaseHelper(Context context, IAndroidDatabaseHelper dbhelper);
	
	/**
	 * Unregister a given Android context with an instance of a data base helper when the life cycle of the context ends 
	 * @param context	The Android context being unregistered 
	 */
	void unregisterDatabaseHelper(Context context);
}
