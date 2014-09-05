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
import java.util.List;
import java.util.ListIterator;

import net.hydromatic.linq4j.Enumerable;
import net.hydromatic.linq4j.Linq4j;
import net.hydromatic.linq4j.function.Function1;
import net.hydromatic.linq4j.function.Predicate1;


public class MohitoQueryLanguage {

	/**
	 * @return Creates a new criteria expression without any constraints.
	 */
	public static Criteria createCriteria() {
		return new DefaultCriteria();
	}

	/**Filters a given list of values.
	 * @param values List of values.
	 * @param criteria Criteria used to constrain elements in the list.
	 * @return Elements in the list, which observe the constraints. Full list if there are no constraints provided.
	 */
	public static <T, TKey> List<T> filterByCriteria(final List<T> values, final Criteria criteria) {
		if (criteria == null || criteria.count() == 0) {
			return values;
		}
		Enumerable<T> currentValues = Linq4j.asEnumerable(values);
		ListIterator<Criterion<T, TKey>> criterionIterator = criteria.getCriterionIterator();
		Predicate1<T> predicate;
		Function1<T, TKey> keySelector;
		while (criterionIterator.hasNext()) {
			Criterion<T, TKey> criterion = criterionIterator.next();
			switch (criterion.getOperationType()) {
			case WHERE:
				predicate = criterion.getPredicate();
				currentValues = currentValues.where(predicate);
				break;
			case ORDERBY:
				keySelector = criterion.getKeySelector();
				Comparator<TKey> comparator = criterion.getComparator();
				currentValues = currentValues.orderBy(keySelector, comparator);
				break;
			case GROUPBY:
				keySelector = criterion.getKeySelector();
				currentValues.groupBy(keySelector);
				break;
			default:
				throw new IllegalArgumentException("Unsupported operation type for criterion: " + criterion.getOperationType() + ".");
			}
		}
		return currentValues.toList();
	}
	
}
