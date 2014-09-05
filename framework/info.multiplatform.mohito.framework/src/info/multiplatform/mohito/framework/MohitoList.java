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
package info.multiplatform.mohito.framework;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Manages list, which are part of MOHITO-Entities.
 * Takes care of assigning containment relations for added/removed objects. 
 * @author hgroenda
 * @param <T> Type of elements contained in the list.
 *
 */
public class MohitoList<T> implements List<T> {
	/** Logger for this class. */
	public static final Logger logger = Logger.getLogger(MohitoList.class.getCanonicalName());
	
	/** Type of elements contained in the list. Saved explicitly as reflection does not allow to determine it at runtime for generic parameters. */
	private final Class<T> managedType;
	/** Class instance containing the list. */
	private final MohitoEntity<?> parent;
	/** List actually managing the content. */
	private final List<T> internalList;
	/** Field of the elements contained in the list, which is set to the class containing the list in order to state the containment relation. */
	private final Field containmentClassField; 
	
	/**Creates a new list contained in a MOHITO-Entity.
	 * @param managedType Type of list elements.
	 * @param parent The instance of the MOHITO-Entity containing the list.
	 * @param javaFieldNameForInverseRelation Name of the java field used to set the inverse relation. Must be <code>null</code> for 1-way references. Must be set for 1-way containment relations between two MOHITO-Entities. Should be <code>null</code> for 1-way containment relations between 1 MOHITO-Entity and another entity, e.g. java.lang.String. Inverse relations allow to access the parent in a containment relation between two MOHITO-Entities.
	 */
	public MohitoList(Class<T> managedType, MohitoEntity<?> parent, String javaFieldNameForInverseRelation) {
		this.managedType = managedType;
		this.internalList = new ArrayList<T>();
		this.parent = parent;
		Field classField = null;
		try {
			if (javaFieldNameForInverseRelation != null) {
				classField = managedType.getDeclaredField(javaFieldNameForInverseRelation);
			}
		} catch (NoSuchFieldException e) {
			logger.log(Level.SEVERE, "Could not find the provided field for the inverse relation. Setting the containment relation automatically will not work. Provide a valid field name.", e);
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, "Could not find the provided field for the inverse relation. Setting the containment relation automatically will not work. Provide a valid field name.", e);
		}
		containmentClassField = classField==null ? null : classField;
	}

	/**Sets the containment relation according to the assignment to the list.
	 * @param element Element on which the relation is set via a java field.
	 * @param value Value to set.
	 */
	private void setContainmentField(T element, MohitoEntity<?> value) {
		if (containmentClassField != null && element != null) {
			try {
				if (!containmentClassField.isAccessible()) {
					containmentClassField.setAccessible(true);
					if (containmentClassField.getType().equals(MohitoList.class)){
						@SuppressWarnings("unchecked")
						MohitoList<MohitoEntity<?>> list = (MohitoList<MohitoEntity<?>>) containmentClassField.get(element);
						list.add(value);
					} else {
						containmentClassField.set(element, value);
					}
					containmentClassField.setAccessible(false);
				}
			} catch (IllegalArgumentException e) {
				logger.log(Level.SEVERE, "Could not set the parent as value for the field of the inverse relation.", e);
			} catch (IllegalAccessException e) {
				logger.log(Level.SEVERE, "Could not set the parent as value for the field of the inverse relation.", e);
			}
		}
	}
	
	public void add(int index, T element) {
		internalList.add(index, element);
		setContainmentField(element, parent);
		parent.mSetDirty(true);
	}

	public boolean add(T e) {
		boolean result = internalList.add(e);
		setContainmentField(e, parent);
		parent.mSetDirty(true);
		return result;
	}

	public boolean addAll(Collection<? extends T> c) {
		boolean result = internalList.addAll(c);
		for (T element : c) {
			setContainmentField(element, parent);
		}
		parent.mSetDirty(true);
		return result;
	}

	public boolean addAll(int index, Collection<? extends T> c) {
		boolean result = internalList.addAll(index, c);
		for (T element : c) {
			setContainmentField(element, parent);
		}
		parent.mSetDirty(true);
		return result;
	}

	public void clear() {
		for (T element : internalList){
			setContainmentField(element, null);
		}
		internalList.clear();
		parent.mSetDirty(true);
	}

	public boolean contains(Object o) {
		return internalList.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return internalList.containsAll(c);
	}

	public boolean equals(Object o) {
		return internalList.equals(o);
	}

	public T get(int index) {
		return internalList.get(index);
	}

	public int hashCode() {
		return internalList.hashCode();
	}

	public int indexOf(Object o) {
		return internalList.indexOf(o);
	}

	public boolean isEmpty() {
		return internalList.isEmpty();
	}

	public Iterator<T> iterator() {
		return internalList.iterator();
	}

	public int lastIndexOf(Object o) {
		return internalList.lastIndexOf(o);
	}

	public ListIterator<T> listIterator() {
		return internalList.listIterator();
	}

	public ListIterator<T> listIterator(int index) {
		return internalList.listIterator(index);
	}

	public T remove(int index) {
		T result = internalList.remove(index);
		setContainmentField(result, null);
		parent.mSetDirty(true);
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean remove(Object o) {
		if (o != null &&  this.managedType.getClass().isAssignableFrom(o.getClass())) {
			setContainmentField((T) o, null);
		}
		boolean result = internalList.remove(o);
		parent.mSetDirty(true);
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean removeAll(Collection<?> c) {
		for (Object o : c) {
			if (o != null &&  this.managedType.getClass().isAssignableFrom(o.getClass())) {
				setContainmentField((T) o, null);
			}
		}
		boolean result = internalList.removeAll(c);
		parent.mSetDirty(true);
		return result;
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public T set(int index, T element) {
		T result = internalList.set(index, element);
		setContainmentField(element, parent);
		setContainmentField(result, null);
		parent.mSetDirty(true);
		return result;
	}

	public int size() {
		return internalList.size();
	}

	public List<T> subList(int fromIndex, int toIndex) {
		return internalList.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		return internalList.toArray();
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		return internalList.toArray(a);
	}

}
