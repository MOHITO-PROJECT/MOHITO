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
package de.b2m.software.adapter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.b2m.software.R;
import de.b2m.software.dataitem.BookData;
import de.b2m.software.dataitem.BookShelfViewMode;
import de.b2m.software.model.Book;

/**Adapts functionality and UI of a book in MOHITO demonstrator.
 * @author egailus, theuser (B2M Software AG)
 *
 */
public class BookAdapter extends ArrayAdapter<Book>{

	@SuppressWarnings("unused")
	static private String LOG_TAG = BookAdapter.class.getSimpleName();
	
	private Context context; 
	private int layoutResourceId;    
	private Book data[] = null;
	private BookShelfViewMode mBookShelfViewMode;
	private final List<BookData> mMarkedBooks = new ArrayList<BookData>();
    
	
    public BookAdapter(Context context, int layoutResourceId, Book[] data, BookShelfViewMode viewMode) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.mBookShelfViewMode = viewMode;
    }

    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View bookItem = convertView;
        BookData bookData = null;
        
        if(bookItem == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            bookItem = inflater.inflate(layoutResourceId, parent, false); 
            
            bookData = new BookData();
            bookData.setBookCover((ImageView)bookItem.findViewById(R.id.bookImageView));
            bookData.setTitle((TextView)bookItem.findViewById(R.id.bookNameTextView));
            
            bookItem.setTag(bookData);
        }
        else {
            bookData = (BookData)bookItem.getTag();
        }
        
        Book book = data[position];
        bookData.setBookId(book.getId());
        
        String bookTitle = " ";
		try {
			bookTitle = URLDecoder.decode(book.getId(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			bookTitle = "unknown";
		}
        bookData.getTitle().setText(bookTitle);
        bookData.getBookCover().setImageResource(R.drawable.bookcover);
        bookData.getBookCover().setAdjustViewBounds(true);
        
        final BookData finalBookData = bookData;
        final ImageView coverImageView = (ImageView)bookItem.findViewById(R.id.bookImageView);
        
        coverImageView.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View view) {
				if(mBookShelfViewMode != BookShelfViewMode.MANAGEBOOKS) {
					return false;
				}
				
				ImageView coverView = (ImageView) view;
				
				if (mMarkedBooks.contains(finalBookData)) {
					mMarkedBooks.remove(finalBookData);
					coverView.setImageResource(R.drawable.bookcover);
				} 
				else {
					mMarkedBooks.add(finalBookData);
					coverView.setImageResource(R.drawable.bookcover_marked);
				}
					
				return true;
			}
		});
        
        return bookItem;
    }
    
    public List<BookData> getMarkedBooks() {
    	return mMarkedBooks;
    }
    
    
    public void clearMarkedBooks() {
    	mMarkedBooks.clear();
    }
}