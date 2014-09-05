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
import info.multiplatform.mohito.framework.mql.Criteria;
import info.multiplatform.mohito.framework.mql.MohitoQueryLanguage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import net.hydromatic.linq4j.function.Predicate1;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.google.inject.Inject;

import de.b2m.software.adapter.BookAdapter;
import de.b2m.software.contracts.Constants;
import de.b2m.software.dataitem.BookData;
import de.b2m.software.dataitem.BookDetailsViewMode;
import de.b2m.software.dataitem.BookShelfViewMode;
import de.b2m.software.dataitem.MohitoTaskDataHolder;
import de.b2m.software.dataitem.MohitoTaskDataHolder.ModeType;
import de.b2m.software.model.Book;
import de.b2m.software.model.Category;
import de.b2m.software.model.util.LibraryStorageManager;
import de.b2m.software.tasks.IMohitoTaskHandler;
import de.b2m.software.tasks.MohitoTask;
import de.b2m.software.utils.AppDataDump;


/**Functionality and UI of the bookshelf in MOHITO demonstrator.
 * @author egailus, theuser (B2M Software AG)
 *
 */
@SuppressWarnings("rawtypes")
public class BookShelfActivity extends RoboActivity implements IMohitoTaskHandler {
	
	static private String LOG_TAG = BookShelfActivity.class.getSimpleName();
	
	@Inject
    private IAndroidDatabaseHelper mDatabaseHelper;
	
	@Nullable @InjectView(R.id.bookShelfView) GridView mQueryResultListView;
	@Nullable @InjectView(R.id.deleteBookButton) Button mDeleteBookButton;
	
	private Book[] mBooksArray;
	@SuppressWarnings("unused")
	private Book[] mBooksArrayMarked;
	private Category mCurrentBookCategory;
	private BookShelfViewMode mCurrentViewMode;
	private String mSearchTerm;
	private List<MohitoTask<?,?>> mMohitoTasks;
	private BookAdapter mAdapter;
	private ProgressDialog mWaitProgressDialog;
    
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_shelf);
		
		Bundle extrasBundle = getIntent().getExtras();
		mCurrentBookCategory = retrieveCategory(extrasBundle);
		mCurrentViewMode = retrieveModeView(extrasBundle);
		mSearchTerm = extrasBundle.getString(Constants.BUNDLE_BOOKSHELF_SEARCH_TERM, null);
				
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
        DatabaseHelperManager.getInstance().registerDatabaseHelper(this, mDatabaseHelper);
        
		if (mCurrentViewMode == BookShelfViewMode.MANAGEBOOKS) {
			mDeleteBookButton.setVisibility(View.VISIBLE);
		}
		
		if (mCurrentViewMode == BookShelfViewMode.SYNCBOOKS) {
			syncWithRemote();
		}
		else if (mCurrentViewMode == BookShelfViewMode.SHOWBOOKS ||
				 mCurrentViewMode == BookShelfViewMode.MANAGEBOOKS){
			retrieveLocalBooks();
		}
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
		getMenuInflater().inflate(R.menu.book_shelf, menu);
		return true;
	}
	
	
	@Override
	public void signalTaskFinished(MohitoTask task, List resultList) {
        synchronized (mMohitoTasks) {
        	mMohitoTasks.remove(task);
        }
        
        if(task.getTaskMode() == ModeType.Sync) {
        	// dump DB for debugging
        	AppDataDump.dumpDatabasesToExternalMemory(this);
        	
        	mCurrentViewMode = BookShelfViewMode.SHOWBOOKS;
        	retrieveLocalBooks();
        	return;
        }
        else if(task.getTaskMode() == ModeType.Update) {
        	retrieveLocalBooks();
        }
        
        if(resultList == null || resultList.size() < 1) {
        	return;
        }
        
        if(task.getTaskMode() == ModeType.SearchForDeletion) {
        	if(resultList.size() > 1) {
        		Log.w(LOG_TAG, "Found ambiguous entries to delete!");
        		return;
        	}
        	
        	Book book = (Book) resultList.get(0);
        	book.mSetDeletionPending(true);
        	book.mSetDirty(true);
        	updateLocalBook(book);
        	
        	mAdapter.clearMarkedBooks();
        }
                
        Object item = resultList.get(0);
        if(item instanceof Book) {
        	
            for (Iterator<?> it = resultList.iterator(); it.hasNext(); ) {
            	Book book = (Book) it.next();
            	if(book.mIsDeletionPending()) {
            		it.remove();
            	}
            }

        	mBooksArray = new Book[resultList.size()];        	
        	for(int i = 0; i < resultList.size(); i++) {
        		mBooksArray[i] = (Book) resultList.get(i);
        	}
        	
        	mAdapter = new BookAdapter(this, R.layout.book_item_view, mBooksArray, mCurrentViewMode);
    		mQueryResultListView.setAdapter(mAdapter);
        }
	}
	
	
	public void onClick(View view) {
		 switch(view.getId()) {
			 case R.id.bookImageView:
				 startBookDetailActivity(view);
				 break;
			 case R.id.deleteBookButton:
				 removeBooksFromLibraray();
				 break; 
			 default :
				 Log.w(LOG_TAG, "unknown button event");
				 break;
		 }
	}


	private void startBookDetailActivity(View view) {
		View parent = (View) view.getParent();
		BookData bookData = (BookData)parent.getTag();
		BookDetailsViewMode detailsMode = BookDetailsViewMode.READ;

		if (mCurrentViewMode == BookShelfViewMode.MANAGEBOOKS) {
			detailsMode = BookDetailsViewMode.EDIT;
		}

		Intent intent = new Intent(this, BookDetailsActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt(Constants.BUNDLE_BOOKDETAILS_VIEWMODE, detailsMode.getValue());
		bundle.putString(Constants.BUNDLE_BOOK_ID, bookData.getBookId());

		intent.putExtras(bundle);
		this.startActivity(intent);
	}
		
	
    @SuppressWarnings("unused")
	private String addBookToLibrary(Book book) throws DataLayerException {
        MohitoEntityDao<Book, String> bookDao = LibraryStorageManager.mINSTANCE.getLocalStorageManager().getBookDao();
        return bookDao.create(book);
    }
    
    
	private Category retrieveCategory(Bundle extrasBundle) {
		Category category;
		
		int categoryId = extrasBundle.getInt(Constants.BUNDLE_CATEGORY_ID);
		
		
		switch (categoryId) {
		case 101:
			category = Category.SCIENCE;
			break;
		case 102:
			category = Category.FICTION;
			break;
		case 103:
			category = Category.POETRY;
			break;
		default:
			category = Category.ALL;
			break;
		}
		
		return category;
	}
	
	private BookShelfViewMode retrieveModeView(Bundle extrasBundle) {
		BookShelfViewMode bookshelfmodel;
		
		int bookshelfViewMode = extrasBundle.getInt(Constants.BUNDLE_BOOKSHELF_VIEWMODE);
		
		switch (bookshelfViewMode) {
		case 1:
			bookshelfmodel = BookShelfViewMode.MANAGEBOOKS;
			break;
		case 2:
			bookshelfmodel = BookShelfViewMode.SHOWBOOKS;
			break;
		case 3:
			bookshelfmodel = BookShelfViewMode.SYNCBOOKS;
			break;
		default:
			bookshelfmodel = BookShelfViewMode.UNKNOWN;
			break;
		}
		
		return bookshelfmodel;
	}

	
	@SuppressWarnings("unchecked")
	private void retrieveLocalBooks() {
		Criteria criteria = createCriteria(mSearchTerm, false);

		MohitoEntityDao<Book, String> localBookDao =  LibraryStorageManager.mINSTANCE.getLocalStorageManager().getBookDao();
		MohitoTask<Book, String> retrieveBookTask = new MohitoTask<Book, String>(this, Book.class, mWaitProgressDialog);     
        
        MohitoTaskDataHolder<Book, String> mohitoTaskDataHolder = new MohitoTaskDataHolder<Book, String>(localBookDao);
        mohitoTaskDataHolder.setMode(MohitoTaskDataHolder.ModeType.GetByCriteria);
        mohitoTaskDataHolder.setCriteria(criteria);
		
        synchronized (mMohitoTasks) {
            mMohitoTasks.add(retrieveBookTask);
        }

        retrieveBookTask.execute(mohitoTaskDataHolder);
	}

	
	@SuppressWarnings({ "unchecked", "unused" })
	private void retrieveRemoteBooks() {
		MohitoEntityDao<Book, String> remoteBookDao =  LibraryStorageManager.mINSTANCE.getRemoteStorageManager().getBookDao();
		MohitoTask<Book, String> bookTask = new MohitoTask<Book, String>(this, Book.class, mWaitProgressDialog);     
        
        MohitoTaskDataHolder<Book, String> mohitoTaskDataHolder = new MohitoTaskDataHolder<Book, String>(remoteBookDao);
        mohitoTaskDataHolder.setMode(MohitoTaskDataHolder.ModeType.GetByCriteria);
        mohitoTaskDataHolder.setCriteria(null);
		
        synchronized (mMohitoTasks) {
            mMohitoTasks.add(bookTask);
        }

        bookTask.execute(mohitoTaskDataHolder);
	}
			
	
	@SuppressWarnings("unchecked")
	private void syncWithRemote() {
		MohitoTask<Book, String> syncBooksTask = new MohitoTask<Book, String>(this, Book.class, mWaitProgressDialog);

        MohitoTaskDataHolder<Book, String> mohitoTaskDataHolder = new MohitoTaskDataHolder<Book, String>(null);
        mohitoTaskDataHolder.setMode(MohitoTaskDataHolder.ModeType.Sync);
		
        synchronized (mMohitoTasks) {
            mMohitoTasks.add(syncBooksTask);
        }
        
		syncBooksTask.execute(mohitoTaskDataHolder);
	}

	
	@SuppressWarnings("unchecked")
	private void removeBooksFromLibraray() {
		List<BookData> booksToDelete = mAdapter.getMarkedBooks();
		
		if(booksToDelete.size() < 1) {
			return;
		}
		
		for(BookData data : booksToDelete) {
			Criteria criteria = createCriteria(data.getBookId(), true);

			MohitoEntityDao<Book, String> localBookDao =  LibraryStorageManager.mINSTANCE.getLocalStorageManager().getBookDao();
			MohitoTask<Book, String> seekAndDestroyTask = new MohitoTask<Book, String>(this, Book.class, mWaitProgressDialog);     
	        
	        MohitoTaskDataHolder<Book, String> mohitoTaskDataHolder = new MohitoTaskDataHolder<Book, String>(localBookDao);
	        mohitoTaskDataHolder.setMode(MohitoTaskDataHolder.ModeType.SearchForDeletion);
	        mohitoTaskDataHolder.setCriteria(criteria);
			
	        synchronized (mMohitoTasks) {
	            mMohitoTasks.add(seekAndDestroyTask);
	        }

	        seekAndDestroyTask.execute(mohitoTaskDataHolder);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void updateLocalBook(Book bookToUpdate) {
				
		MohitoEntityDao<Book, String> localBookDao =  LibraryStorageManager.mINSTANCE.getLocalStorageManager().getBookDao();
		MohitoTask<Book, String> updateTask = new MohitoTask<Book, String>(this, Book.class, mWaitProgressDialog);     
        
        MohitoTaskDataHolder<Book, String> mohitoTaskDataHolder = new MohitoTaskDataHolder<Book, String>(localBookDao);
        mohitoTaskDataHolder.setMode(MohitoTaskDataHolder.ModeType.Update);
        mohitoTaskDataHolder.setDataItem(bookToUpdate);
		
        synchronized (mMohitoTasks) {
            mMohitoTasks.add(updateTask);
        }

        updateTask.execute(mohitoTaskDataHolder);

	}
	
	private Criteria createCriteria(final String searchTerm, final boolean checkForIdentity) {
		Criteria criteria = null;
				
		if(!TextUtils.isEmpty(searchTerm)) {
	        criteria = MohitoQueryLanguage.createCriteria();
	        criteria.where(
	                new Predicate1<Book>() {
	                    public boolean apply(Book book) {
	                    	boolean retaVal;
	                    	if(checkForIdentity) {
	                    		retaVal = book.getId().equalsIgnoreCase(searchTerm);
	                    	}
	                    	else {
	                    		retaVal = book.getId().contains(searchTerm);
	                    	}
	                    	return retaVal;
	                    }
	                });
		}
		else if(mCurrentBookCategory != Category.ALL) {
	        criteria = MohitoQueryLanguage.createCriteria();
	        criteria.where(
	                new Predicate1<Book>() {
	                    public boolean apply(Book book) {
	                        return book.getCategory().getValue() == mCurrentBookCategory.getValue();
	                    }
	                });
		}
		
		return criteria;
	}
	
	
    public Book[] getBooks() {
    	return mBooksArray;
    }

}
