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
import java.util.ListIterator;

import net.hydromatic.linq4j.function.Function1;
import net.hydromatic.linq4j.function.Predicate1;

public interface Criteria {

	/**
	 * Resets the criteria expression
	 */
	void reset();
	
	/**
	 * Counts the number of criterion in this criteria expression
	 * 
	 * @return number of criterion in this criteria expression
	 */
	int count();
	
	/**
	 * Filters a sequence of values based on a predicate.
	 * 
	 * @param 	The predicate which is used to filter the sequence.
	 * @return	the criteria itself
	 */
	<T> Criteria where(Predicate1<T> predicate);
	
	/**
	 * Sorts the elements of a sequence in a specific order by using a specified comparator function.
	 * 
	 * @param keySelector 	The function to select the key which is used in the comparator function
	 * @param comparator	The comparator function to sort the elements in the sequence.
	 * @return	the criteria itself
	 */
	<T, TKey> Criteria orderBy(Function1<T, TKey> keySelector, Comparator<TKey> comparator);
	
	/**
	 * Groups the elements of a sequence according to a specified key selector function.
	 * 
	 * @param keySelector	The function to select the key after which the elements are grouped.
	 * @return the criteria itself
	 */
	<T, TKey> Criteria groupBy(Function1<T, TKey> keySelector);
	
	/**
	 * Returns an criterion iterator, which can be used to iterate over all criterion in this criteria expression.
	 * 
	 * @return	a criterion iterator
	 */
	<T, TKey> ListIterator<Criterion<T, TKey>> getCriterionIterator();
	
}
