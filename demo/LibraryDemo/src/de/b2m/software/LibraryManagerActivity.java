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
package de.b2m.software;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import de.b2m.software.contracts.Constants;
import de.b2m.software.dataitem.BookShelfViewMode;


/**Functionality and UI for managing books within the MOHITO demonstrator.
 * @author egailus, theuser (B2M Software AG)
 *
 */
public class LibraryManagerActivity extends Activity {

	static private String LOG_TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_library_manager);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.library_manager, menu);
		return true;
	}


	public void onClick(View view) {
		Intent intentManageBooks = new Intent(this, BookShelfActivity.class);
		Bundle bundle = new Bundle();

		switch(view.getId())	{
		case R.id.manageBooks:
			bundle.putInt(Constants.BUNDLE_BOOKSHELF_VIEWMODE, BookShelfViewMode.MANAGEBOOKS.getValue());
			intentManageBooks.putExtras(bundle);
			this.startActivity(intentManageBooks);
			break;

		case R.id.sycWithRemote:
			bundle.putInt(Constants.BUNDLE_BOOKSHELF_VIEWMODE, BookShelfViewMode.SYNCBOOKS.getValue());
			intentManageBooks.putExtras(bundle);
			this.startActivity(intentManageBooks);
			break;

		default :
			Log.w(LOG_TAG, "unknown button event");
			break;
		}
	}

}
