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
package info.multiplatform.mohito.framework.mql;

import java.util.Comparator;

import net.hydromatic.linq4j.function.Function1;
import net.hydromatic.linq4j.function.Predicate1;

public class Criterion<T, TKey> {
	
	public enum OperationType {
		 WHERE(1),
		 ORDERBY(2),
		 GROUPBY(3);
		 
		 private int id;
		 
		 private OperationType(int c) {
		   id = c;
		 }
		 
		 public int getId() {
		   return id;
		 }
	}
	
	private OperationType mOperationType;
	private Predicate1<T> mPredicate;
	private Function1<T, TKey> mKeySelector;
	private Comparator<TKey> mComparator;
	
	
	public Criterion(OperationType operationType) {
		mOperationType = operationType;
	}
	
	
	public Criterion(OperationType operationType, Predicate1<T> predicate) {
		mOperationType = operationType;
		mPredicate = predicate;
	}
	
	
	public Criterion(OperationType operationType, Function1<T, TKey> keySelector) {
		mOperationType = operationType;
		mKeySelector = keySelector;
	}
	
	
	public Criterion(OperationType operationType, Function1<T, TKey> keySelector, Comparator<TKey> comparator) {
		mOperationType = operationType;
		mKeySelector = keySelector;
		mComparator = comparator;
	}
	
	
	public OperationType getOperationType() {
		return mOperationType;
	}


	public void setOperationType(OperationType operationType) {
		this.mOperationType = operationType;
	}


	public Predicate1<T> getPredicate() {
		return mPredicate;
	}


	public void setPredicate(Predicate1<T> predicate) {
		this.mPredicate = predicate;
	}
	
	
	public Function1<T, TKey> getKeySelector() {
		return mKeySelector;
	}


	public void setKeySelector(Function1<T, TKey> keySelector) {
		mKeySelector = keySelector;
	}


	public Comparator<TKey> getComparator() {
		return mComparator;
	}


	public void setComparator(Comparator<TKey> comparator) {
		mComparator = comparator;
	}

}
