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
package de.b2m.software.tasks;

import info.multiplatform.mohito.framework.MohitoEntity;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import info.multiplatform.mohito.framework.exceptions.DataLayerException;
import info.multiplatform.mohito.framework.mql.Criteria;
import info.multiplatform.mohito.framework.synchronization.MohitoEntitySynchronizer;
import info.multiplatform.mohito.framework.synchronization.SynchronizationConflict;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import de.b2m.software.dataitem.MohitoTaskDataHolder;
import de.b2m.software.dataitem.MohitoTaskDataHolder.ModeType;
import de.b2m.software.model.Book;
import de.b2m.software.model.Library;
import de.b2m.software.model.Writer;
import de.b2m.software.model.util.LibraryStorageManager;



/** Shows all tasks which are used within the MOHITO Library Model
 * @author egailus, theuser (B2M Software AG)
 *
 */
public class MohitoTask <T extends MohitoEntity<PK>, PK> extends AsyncTask<MohitoTaskDataHolder<T,PK>, Void, List<T>> {

	private static String LOG_TAG = MohitoTask.class.getSimpleName();
    private IMohitoTaskHandler<T, PK> mMohitoTaskHandler;

	/** Type / class managed by this DAO. */
	@SuppressWarnings("unused")
	private Class<T> type;
	private ModeType taskMode;
	private ProgressDialog waitProgressDialog;
	

	public MohitoTask(IMohitoTaskHandler<T, PK> mohitoTaskHandler, Class<T> type, ProgressDialog pd) {
        mMohitoTaskHandler = mohitoTaskHandler;
        this.type = type;
        waitProgressDialog = pd;
    }

	
    public ModeType getTaskMode() {
		return taskMode;
	}

    
	@Override
	protected void onPreExecute() {
		waitProgressDialog.show();
	}
    
    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param dataHolders The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected List<T> doInBackground(MohitoTaskDataHolder<T,PK>... dataHolders) {
        List<T> dataItems = new ArrayList<T>();

        if(dataHolders.length < 1)
            return null;

        if(isCancelled())
            return null;

        MohitoTaskDataHolder<T,PK> dataHolder = dataHolders[0];

        if(dataHolder == null )
            return null;

        MohitoEntityDao<T,PK> entityDao = dataHolder.getEntityDao();
        taskMode = dataHolder.getMode();
        
        if(entityDao == null && taskMode != ModeType.Sync)
            return null;

        Criteria criteria = null;
		try {
	        switch(taskMode)
	        {
	            case Create:
	            	PK key = entityDao.create(dataHolder.getDataItem());
	            	if(key != null) {
	            		Log.i(LOG_TAG, "key of newly added entity is: " + key.toString() );
	            	}
	                break;
	            case GetById:
	                PK Id = dataHolder.getIdToGet();
					T data = entityDao.getById(Id);
	                if(data != null) {
	                    dataItems.add(data);
	                }
	                break;
	            case GetByCriteria:
	            	criteria = dataHolder.getCriteria();
	            	dataItems = entityDao.getByCriteria(criteria);
	                break;
	            case SearchForDeletion:
	            	criteria = dataHolder.getCriteria();
	            	dataItems = entityDao.getByCriteria(criteria);
	                break;
	            case Update:
	            	entityDao.update(dataHolder.getDataItem());
	                break;
	            case Delete:
	            	entityDao.delete(dataHolder.getDataItem());
	                break;
	            case Sync:
	            	sync();
	                break;
	            default:
	                break;
	        }
		}
        catch (DataLayerException ex) {
        	Log.e(LOG_TAG, "A DataLayerException occured: " + ex.getMessage());
		}
		
        return dataItems;
    }


	protected void onPostExecute(List<T> dataItems) {
		waitProgressDialog.dismiss();
        mMohitoTaskHandler.signalTaskFinished(this, dataItems);
    }

	
	private void sync() {
		@SuppressWarnings("unchecked")
		Class<? extends MohitoEntity<?>>[] managedClasses = new Class[] { Book.class, Writer.class, Library.class };
		MohitoEntitySynchronizer mohitoEntitySynchronizer = 
				new MohitoEntitySynchronizer(LibraryStorageManager.mINSTANCE, managedClasses);
		
		final Collection<SynchronizationConflict> previousConflicts = new ArrayList<SynchronizationConflict>();
		try {
			mohitoEntitySynchronizer.synchronize(previousConflicts);
		} catch (DataLayerException e) {
			Log.e(LOG_TAG, "syncing failed with error: " + e.getMessage());
		}
	}
}
