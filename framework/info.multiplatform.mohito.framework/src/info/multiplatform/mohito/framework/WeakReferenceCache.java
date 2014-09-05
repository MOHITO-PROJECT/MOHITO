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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Cache storing weak references to instances of MOHITO-entities. The weak
 * references are required in order to allow garbage collection of instances
 * referenced by this cache. Supports deferred loading of information for objects
 * and references between objects.
 * 
 * @see http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/ref/WeakReference.html
 * @author hgroenda
 * 
 */
public class WeakReferenceCache {
	
	/**Map containing maps for each registered class. For each class representing a MOHITO-Entity, the identifier of the class is mapped to the instance representing the information in memory. */
	private final ConcurrentMap<Class<? extends MohitoEntity<?>>, ConcurrentMap<Object, WeakReference<Object>>> classSpecificMaps 
			= new ConcurrentHashMap<Class<? extends MohitoEntity<?>>, ConcurrentMap<Object, WeakReference<Object>>>();

	/**Register handling instance of the provided class representing a MOHITO-Entity by this cache.
	 * @param clazz The class.
	 */
	public synchronized WeakReferenceCache registerClass(Class<? extends MohitoEntity<?>> clazz) {
		if (! classSpecificMaps.containsKey(clazz)) {
			classSpecificMaps.put(clazz, new ConcurrentHashMap<Object, WeakReference<Object>>());
		}
		return this;
	}
	
	/**Unregisters handling instance of the provided class by this cache.
	 * @param clazz The class.
	 */
	public synchronized WeakReferenceCache unregisterClass(Class<? extends MohitoEntity<?>> clazz) {
		classSpecificMaps.remove(clazz);
		return this;
	}
	
	/**Retrieves the instance with the given id and MOHITO-Entity class from the cache.
	 * @param clazz The class.
	 * @param id The id.
	 * @return The object or <code>null</code> if no object is cached for the id.
	 */
	@SuppressWarnings("unchecked")
	public <T extends MohitoEntity<PK>, PK> T get(Class<T> clazz, PK id) {
		if (classSpecificMaps.containsKey(clazz)) {
			
			ConcurrentMap<Object, WeakReference<Object>> referenceMap = classSpecificMaps.get(clazz);
			if(referenceMap == null) {
				return null;
			}
			
			boolean isEmpty = referenceMap.isEmpty();
			if(isEmpty)
			{
				return null;
			}
			
			boolean containsId = false;
			try {
				containsId = referenceMap.containsKey(id);
			} catch (NullPointerException ex) {
				return null;
			}
			
			if (containsId) {
				WeakReference<Object> ref = classSpecificMaps.get(clazz).get(id);
				 return (T) ref.get();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**Retrieves all instances of a MOHITO-Entity class from the cache.
	 * @param clazz The class.
	 * @return A list of objects or an empty list, if no objects of that type are cached.
	 */
	@SuppressWarnings("unchecked")
	public <T extends MohitoEntity<PK>, PK> List<T> getAll(Class<T> clazz) {
		List<T> values = new ArrayList<T>();
		
		if (classSpecificMaps.containsKey(clazz)) {
			List<WeakReference<Object>> references = new ArrayList<WeakReference<Object>>(classSpecificMaps.get(clazz).values());
			for (WeakReference<Object> ref : references) {
				values.add((T) ref.get());
			}
		}
		
		return values;
	}

	/**Puts a new instance of the given class and id in the cache.
	 * @param clazz The class representing the MOHITO-Entity.
	 * @param id The id of the instance.
	 * @param instance The instance.
	 * @return The prior cached value or <code>null</code>.
	 */
	@SuppressWarnings("unchecked")
	public <T extends MohitoEntity<PK>, PK> T put(Class<T> clazz, PK id, T instance) {
		if (classSpecificMaps.containsKey(clazz)) {
			if (classSpecificMaps.get(clazz) != null) {
				WeakReference<Object> old = classSpecificMaps.get(clazz).put(id, new WeakReference<Object>(instance));
				if (old != null && old.get() != null) {
					return (T) old.get();
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
	
	/**Removes an instance of the given class from the cache.
	 * @param clazz The class representing the MOHITO-Entity.
	 * @param id The id of the instance.
	 */
	public <T extends MohitoEntity<PK>, PK> void remove(Class<T> clazz, PK id) {
		if (classSpecificMaps.containsKey(clazz)) {
			classSpecificMaps.get(clazz).remove(id);
		}
	}
}
