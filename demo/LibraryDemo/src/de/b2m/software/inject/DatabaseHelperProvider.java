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
package de.b2m.software.inject;

import info.multiplatform.mohito.framework.android.IAndroidDatabaseHelper;
import android.app.Application;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import de.b2m.software.model.local.LibraryDatabaseHelper;

/**
 * @author egailus, theuser (B2M Software AG)
 *
 */
@Singleton
public class DatabaseHelperProvider implements Provider<IAndroidDatabaseHelper> {

	private static IAndroidDatabaseHelper sDbHelperInstance = null;

    @Inject
    private Application appContext;
    
    
    public synchronized IAndroidDatabaseHelper get() {
    	if(sDbHelperInstance != null) {
    		return sDbHelperInstance;
    	}
    	
    	sDbHelperInstance = OpenHelperManager.getHelper(appContext, LibraryDatabaseHelper.class);
		
		return sDbHelperInstance;
	}
    
    
    public static synchronized void resetInstance () {
    	if(sDbHelperInstance != null) {
    		sDbHelperInstance = null;
    	}
    	
    	OpenHelperManager.releaseHelper();
    }
}
