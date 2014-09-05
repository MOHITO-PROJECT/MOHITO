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

import info.multiplatform.mohito.framework.MohitoEntityDao;
import info.multiplatform.mohito.framework.android.DatabaseHelperManager;
import info.multiplatform.mohito.framework.android.IAndroidDatabaseHelper;
import info.multiplatform.mohito.framework.exceptions.DataLayerException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.inject.Inject;

import de.b2m.software.contracts.Constants;
import de.b2m.software.dataitem.BookDetailsViewMode;
import de.b2m.software.dataitem.MohitoTaskDataHolder;
import de.b2m.software.model.Book;
import de.b2m.software.model.Writer;
import de.b2m.software.model.util.LibraryStorageManager;
import de.b2m.software.tasks.IMohitoTaskHandler;
import de.b2m.software.tasks.MohitoTask;

/**Functionality and UI of the detailed view of books.
 * @author egailus, theuser (B2M Software AG)
 *
 */
@SuppressWarnings("rawtypes")
public class BookDetailsActivity extends RoboActivity implements IMohitoTaskHandler {
	
	@Nullable @InjectView(R.id.saveButton) Button mSaveButton;
	@Nullable @InjectView(R.id.loanOutButton) Button mCheckOutButton;
	@Nullable @InjectView(R.id.ReturnButton) Button mReturnButton;

	@Nullable @InjectView(R.id.detailsViewBookCoverTitle) TextView mBookCoverTextView;
	@Nullable @InjectView(R.id.detailsViewTitle) EditText mBookTitleTextView;
	@Nullable @InjectView(R.id.detailsViewAuthor) EditText mAuthorTextView;
	@Nullable @InjectView(R.id.detailsViewPages) EditText mPagesTextView;
	@Nullable @InjectView(R.id.detailsViewReading) EditText mReadingPaneTextView;
	
	@Inject
    private IAndroidDatabaseHelper mDatabaseHelper;
	
	private static final String LOG_TAG = MainActivity.class.getSimpleName();
	private String mCurrentBookId;
	private Book mCurrentBook;
	private BookDetailsViewMode mBookDetailsAddMode;
	private List<MohitoTask<?,?>> mMohitoTasks;
	private ProgressDialog mWaitProgressDialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_details);
		
		Bundle extrasBundle = getIntent().getExtras();
		mCurrentBookId = extrasBundle.getString(Constants.BUNDLE_BOOK_ID);
		mBookDetailsAddMode = retrieveViewMode(extrasBundle);
		
		mMohitoTasks = new ArrayList<MohitoTask<?,?>>();
		mWaitProgressDialog = new ProgressDialog(this);
		mWaitProgressDialog.setTitle("Processing...");
		mWaitProgressDialog.setMessage("Please wait.");
		mWaitProgressDialog.setCancelable(false);
		mWaitProgressDialog.setIndeterminate(true);
	}
	
	
    @Override
    protected void onResume() {
        super.onResume();
        
		if (mBookDetailsAddMode == BookDetailsViewMode.EDIT) {
			mSaveButton.setVisibility(View.VISIBLE);
			mReadingPaneTextView.setVisibility(View.VISIBLE);
			mReadingPaneTextView.setEnabled(true);
			
			mBookTitleTextView.setEnabled(true);
			mAuthorTextView.setEnabled(true);
			mPagesTextView.setEnabled(true);
		} 
		else if (mBookDetailsAddMode == BookDetailsViewMode.READ) {
			mCheckOutButton.setVisibility(View.VISIBLE);
			mReturnButton.setVisibility(View.VISIBLE);
		}
        
        DatabaseHelperManager.getInstance().registerDatabaseHelper(this, mDatabaseHelper);
		MohitoEntityDao<Book, String> bookDao = LibraryStorageManager.mINSTANCE.getLocalStorageManager().getBookDao();
		try {
			mCurrentBook = bookDao.getById(mCurrentBookId);
		} catch (DataLayerException e) {
			// TODO: handle invalid book in UI
			return;
		}
		
		if(mCurrentBook == null) {
			return;
		}
		
		MohitoEntityDao<Writer, Integer> authorDao = LibraryStorageManager.mINSTANCE.getLocalStorageManager().getWriterDao();
		Writer author;
		String authorName = "";
		
		try {
			author = authorDao.getById(mCurrentBook.getWriter().getId());
			authorName = author.getName();
		} catch (DataLayerException e) {
			authorName = "unknown";
		}
		
        String bookTitle = " ";
		try {
			bookTitle = URLDecoder.decode(mCurrentBook.getId(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			bookTitle = "unknown";
		}
		
		mBookTitleTextView.setText(bookTitle);
		mBookCoverTextView.setText(bookTitle);
		mAuthorTextView.setText(authorName);
		mPagesTextView.setText(mCurrentBook.getPages().toString());
		mReadingPaneTextView.setMovementMethod(new ScrollingMovementMethod());
		mReadingPaneTextView.setText(mCurrentBook.getBlurb());
    }
    
    
    @Override
    public void onPause() {
        super.onPause();

        synchronized (mMohitoTasks) {
            for (MohitoTask<?,?> task : mMohitoTasks)
            {
                task.cancel(true);
            }
            mMohitoTasks.clear();
            
            if(mWaitProgressDialog != null) {
            	mWaitProgressDialog.dismiss();
            }
        }
        
        DatabaseHelperManager.getInstance().unregisterDatabaseHelper(this);
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
		return true;
	}

	
	public void onClick(View view) {
		Intent intent;
		
		 switch(view.getId())	{
			 case R.id.ReturnButton:
				 intent = new Intent(this, BookReturnActivity.class);
				 this.startActivity(intent);
				 break;
				 
			 case R.id.loanOutButton:
				 intent = new Intent(this, BookCheckOutActivity.class);
				 this.startActivity(intent);
				 break;
				 
			 case R.id.saveButton:
				 saveCurrentBook();
				 break;
				 
			 default :
				 Log.w(LOG_TAG, "unknown button event");
				 break;
				 
		 }
	 }
	

	private BookDetailsViewMode retrieveViewMode(Bundle extrasBundle) {
		BookDetailsViewMode bookDetailsViewMode;
		
		int bookdetailsViewMode = extrasBundle.getInt(Constants.BUNDLE_BOOKDETAILS_VIEWMODE);
		
		switch (bookdetailsViewMode) {
		case 1:
			bookDetailsViewMode = BookDetailsViewMode.READ;
			break;
		case 2:
			bookDetailsViewMode = BookDetailsViewMode.EDIT;
			break;
		default:
			bookDetailsViewMode = BookDetailsViewMode.UNKNOWN;
			break;
		}
		
		return bookDetailsViewMode;
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void saveCurrentBook() {
		int pages = Integer.parseInt(mPagesTextView.getText().toString());
		mCurrentBook.setPages(pages);

		MohitoEntityDao<Book, String> localBookDao =  LibraryStorageManager.mINSTANCE.getLocalStorageManager().getBookDao();
		MohitoTask<Book, String> bookTask = new MohitoTask<Book, String>(this, Book.class, mWaitProgressDialog);
        
        MohitoTaskDataHolder<Book, String> mohitoTaskDataHolder = new MohitoTaskDataHolder<Book, String>(localBookDao);
        mohitoTaskDataHolder.setMode(MohitoTaskDataHolder.ModeType.Update);
        mohitoTaskDataHolder.setDataItem(mCurrentBook);
        
        synchronized (mMohitoTasks) {
            mMohitoTasks.add(bookTask);
        }

        bookTask.execute(mohitoTaskDataHolder);
	}


	@Override
	public void signalTaskFinished(MohitoTask task, List resultList) {
        synchronized (mMohitoTasks) {
        	mMohitoTasks.remove(task);
        }
	}
	
}
