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

import info.multiplatform.mohito.framework.mql.Criterion.OperationType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import net.hydromatic.linq4j.function.Function1;
import net.hydromatic.linq4j.function.Predicate1;

public class DefaultCriteria implements Criteria {

	private List<Criterion<?, ?>> mCriteria;
	
	
	public DefaultCriteria() {
		mCriteria = new ArrayList<Criterion<?, ?>>();
	}
	
	
	@Override
	public void reset() {
		mCriteria.clear();
	}

	
	@Override
	public int count() {
		return mCriteria.size();
	}

	
	@Override
	public <T> Criteria where(Predicate1<T> predicate) {
		Criterion<T, ?> criterion = new Criterion<T, Object>(OperationType.WHERE, predicate);
		mCriteria.add(criterion);  
		
		return this;
	}
	

	@Override
	public <T, TKey> Criteria orderBy(Function1<T, TKey> keySelector, Comparator<TKey> comparator) {
		Criterion<T, TKey> criterion = new Criterion<T, TKey>(OperationType.ORDERBY, keySelector, comparator);
		mCriteria.add(criterion);
		
		return this;
	}
	
	
	@Override
	public <T, TKey> Criteria groupBy(Function1<T, TKey> keySelector) {
		Criterion<T, TKey> criterion = new Criterion<T, TKey>(OperationType.GROUPBY, keySelector);
		mCriteria.add(criterion);
		
		return this;
	}
	
	
	@Override
	public ListIterator<Criterion<?, ?>> getCriterionIterator() {
		return mCriteria.listIterator();
	}
	
}
