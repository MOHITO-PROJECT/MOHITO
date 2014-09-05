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

import javax.annotation.Nullable;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import de.b2m.software.contracts.Constants;
import de.b2m.software.dataitem.BookShelfViewMode;
import de.b2m.software.model.Category;

/**Main functionalities and UI of the MOHITO demonstrator.
 * @author egailus, theuser (B2M Software AG)
 *
 */
public class MainActivity extends RoboActivity {
	
	static private String LOG_TAG = MainActivity.class.getSimpleName();

	@Nullable @InjectView(R.id.searchTextEdit) EditText mSearchTextEdit;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	 public void onClick(View view) {
		 Intent libraryManagerIntent = null;
		 Intent bookShelfIntent = null; 
		 Bundle bundle = new Bundle();
		 
		 switch(view.getId())	{
		 	case R.id.showAllButton:
		 		bookShelfIntent = new Intent(this, BookShelfActivity.class);
		 		bundle.putInt(Constants.BUNDLE_CATEGORY_ID, Category.ALL.getValue());
		 		bundle.putInt(Constants.BUNDLE_BOOKSHELF_VIEWMODE, BookShelfViewMode.SHOWBOOKS.getValue());
		 		break;
			 
			 case R.id.showScienceButton:
				 bookShelfIntent = new Intent(this, BookShelfActivity.class);
				 bundle.putInt(Constants.BUNDLE_CATEGORY_ID, Category.SCIENCE.getValue());
				 bundle.putInt(Constants.BUNDLE_BOOKSHELF_VIEWMODE, BookShelfViewMode.SHOWBOOKS.getValue());
				 break;
				 
			 case R.id.showFictionButton:
				 bookShelfIntent = new Intent(this, BookShelfActivity.class);
				 bundle.putInt(Constants.BUNDLE_CATEGORY_ID, Category.FICTION.getValue());
				 bundle.putInt(Constants.BUNDLE_BOOKSHELF_VIEWMODE, BookShelfViewMode.SHOWBOOKS.getValue());
				 break;
				 
			 case R.id.showPoetryButton:
				 bookShelfIntent = new Intent(this, BookShelfActivity.class);
				 bundle.putInt(Constants.BUNDLE_CATEGORY_ID, Category.POETRY.getValue());
				 bundle.putInt(Constants.BUNDLE_BOOKSHELF_VIEWMODE, BookShelfViewMode.SHOWBOOKS.getValue());
				 break;
				 
			 case R.id.searchButton:
				 bookShelfIntent = new Intent(this, BookShelfActivity.class);
				 bundle.putString(Constants.BUNDLE_BOOKSHELF_SEARCH_TERM, mSearchTextEdit.getText().toString());
				 bundle.putInt(Constants.BUNDLE_BOOKSHELF_VIEWMODE, BookShelfViewMode.SHOWBOOKS.getValue());
				break;
				
			 case R.id.switchToLibraryManager:
				 libraryManagerIntent = new Intent(this, LibraryManagerActivity.class);
				 break;
				 
			 default :
				 Log.w(LOG_TAG, "unknown button event");
				 break;
				 
		 }
		 
		 if(bookShelfIntent != null)
		 {
			 bookShelfIntent.putExtras(bundle);
			 this.startActivity(bookShelfIntent);
		 }
		 else {
			 libraryManagerIntent.putExtras(bundle);
			 this.startActivity(libraryManagerIntent);
		 }
	 }
	 
}
