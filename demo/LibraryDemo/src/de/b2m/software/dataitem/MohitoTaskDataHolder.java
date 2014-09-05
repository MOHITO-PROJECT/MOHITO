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
package de.b2m.software.dataitem;

import android.app.ProgressDialog;
import info.multiplatform.mohito.framework.MohitoEntity;
import info.multiplatform.mohito.framework.MohitoEntityDao;
import info.multiplatform.mohito.framework.mql.Criteria;

/** 
 * @author egailus, theuser (B2M Software AG)
 *
 */
public class MohitoTaskDataHolder <T extends MohitoEntity<PK>, PK> {

    public enum ModeType
    {
        Unknown,
        Create,
        GetById,
        GetByCriteria,
        SearchForDeletion,
        Update,
        Delete,
        Sync
    }
   
	/** Remote storage access entity DAO. */
	private MohitoEntityDao<T, PK> entityDao;
    private PK IdToGet;
    private T dataItem;
    private ModeType mode;
    private Criteria criteria;
    

	public MohitoTaskDataHolder(MohitoEntityDao<T, PK> entityDao)
    {
		this.entityDao = entityDao;
    }

	public MohitoEntityDao<T, PK> getEntityDao() {
		return entityDao;
	}
	
    public ModeType getMode() {
        return mode;
    }

    public void setMode(ModeType mode) {
        this.mode = mode;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public PK getIdToGet() {
        return IdToGet;
    }

    public void setIdToGet(PK idToGet) {
        IdToGet = idToGet;
    }

    public T getDataItem() {
        return dataItem;
    }

    public void setDataItem(T dataItem) {
        this.dataItem = dataItem;
    }
    
}
