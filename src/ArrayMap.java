/**
* @author: Mark Oakeson
* FILE: CryptogramModel.java
* @version ASSIGNMENT: Project 3 - ArrayMap
* COURSE: CSc 335; Fall 2020
* PURPOSE: The purpose of this file is to create an ArrayMap by implementing the methods of an
* Abstract Map.  The class takes generic arguments to implement the ArrayMap, then can create a Set
* to group all the mappings together, then has a private Iterator class to iterate over all the mappings.
* 
* @usage With the Cryptogram.java Programs
*/
import java.util.*;

public class ArrayMap<K, V> extends AbstractMap<K, V> {
	private K[] keys;
	private V[] vals;
	private ArrayMapEntrySet set;
	private int size;
	
	/**
	 * Constructor for the ArrayMap Class
	 */
	public ArrayMap() {
			this.keys = (K[]) (new Object[10]);
			//this.stack = (E[]) (new Object[10]);
			this.vals = (V[]) (new Object[10]);
			this.size = 0;
			this.set = null;
		}
	
	
	@Override
	/** 
	 * This method adds key and value to your map. If key already exists, the new value 
	 * replaces the old one, and the old one is returned.
	 * 
	 * @param key  a Generic type K that is the key for the map
	 * @param value  a Generic type V that is the value for the map
	 * 
	 * @return a generic type V of either the old value if the key already existed or null if not
	 */
	public V put(K key, V value) {
			int count = 0;
			for(int i = 0; i < size; i++) {
				count++;
				if(keys[i] == key) {
					V retval = (V) vals[i];
					vals[i] = value;
					return retval;
				}
			}
			if(count == keys.length) {
				resize();
			}
			keys[count] = key;
			vals[count] = value;
			size++;
			return null;
		}
	
	
	
	
	/**
	 * Method resizes the key and value arrays when they are full
	 */
	public void resize() {
		K[] newKeys = (K[]) (new Object[2*keys.length]);
		V[] newVals = (V[]) (new Object[2*vals.length]);
		for(int i = 0; i < size; i++) {
			newKeys[i] = keys[i];
			newVals[i] = vals[i];
		}
		this.keys = newKeys;
		this.vals = newVals;
		
	}
		
	
	
		@Override
		/**
		 * This method returns the number of mappings that the object contains.
		 */
		public int size() {
			return size;
		}
	
		
		
		
		@Override
		/**
		 * Method returns a Set of key, value pairs contained in an Entry object.  Uses the 
		 * ArrayMapEntrySet to create the set for this.
		 * 
		 * @return a Set of the key, value pairs in the array map
		 */
		public Set<Entry<K, V>> entrySet(){
			ArrayMapEntrySet set = new ArrayMapEntrySet();
			this.set = set;
			return set;
	}
	
	
	
	
	/**
	 * Purpose is to create a class for the ArryMapEntrySet to create a 
	 * concrete set for the mappings
	 *
	 */
	private class ArrayMapEntrySet extends AbstractSet<Entry<K,V>>{
		public Set<Entry<K, V>> entrySet;
		
		public ArrayMapEntrySet() {
			this.entrySet = new HashSet<Entry<K, V>>();
			for(int i = 0; i < size; i++) {
				SimpleEntry<K, V> entry = new SimpleEntry<K, V>(keys[i],vals[i]);
				entrySet.add(entry);
			}
		}
		
		/**
		 * This method returns the size of the set (and of the Map).
		 * @return the size of the set/map
		 */
		@Override
		public int size() {
			return size;
		}
		
		
		
		@Override
		/**
		 * This method should return true if the Set contains an Entry equal to the the one 
		 * represented by the parameter. If o is not an Entry, this is trivially false. 
		 * If it is, validate that the key and the value are actually part of the Map.
		 * 
		 * @param o  An object that is an Entry object to be checked if it is in the set
		 * 
		 * @return A boolean on if the object is in the set or not
		 */
		public boolean contains(Object o) {
			if(entrySet.contains(o)) {
				return true;
			}
			return false;
		}
		
		
		
		
		
		
		/**
		 * This returns an iterator that walks over the Set of Entries in the Map.
		 * Uses the ArrayMapSetIterator<T> to implement the iterator
		 * 
		 * @return An iterator that walks over the Set of Entries
		 */
		@Override
		public Iterator<Entry<K,V>> iterator(){
			ArrayMapEntrySetIterator iter = new ArrayMapEntrySetIterator<Entry<K, V>>();
			return iter;
		}

		
		
	
	}
	
	
	
	/**
	 * 
	 * Purpose is to create the iterator to iterate through the key value pairings in the
	 * class
	 */
	private class ArrayMapEntrySetIterator<T> implements Iterator<T>{
		private int setCount;
		private ArrayMapEntrySet copySet;
		private boolean removeable;
		
		public ArrayMapEntrySetIterator() {
			this.setCount = 0;
			this.removeable = false;
			this.copySet = new ArrayMapEntrySet();
		}
			
		
	
		
		
		@Override
		/**
		 * Method returns true if there are more items in the Set of Entries being iterated over.
		 * @return boolean on if the set is empty or not
		 */
		public boolean hasNext() {
			if(setCount < size) {
				return true;
			}
			return false;
		}
		
		
		
		
		@Override
		/**
		 * Returns an Entry (an AbstractMap.SimpleEntry<V,E> for us) that represents the next 
		 * mapping in our Map.
		 * 
		 * @return An object T that is an Entry of the next Key, Value pair
		 */
		public T next() {
			if(hasNext()) {
				K key = keys[setCount];
				V val = vals[setCount];
				T entry = (T) new SimpleEntry<K, V>(key, val);
				T retval = entry;
				this.removeable = true;
				remove();
				return retval;
				
			}
			else {
				return null;
			}
		}
		
		
		/**
		 * Method removes the last element returned by the iterator.  Called only once
		 * per call of the next() method.  
		 * @throws IllegalStateException
		 */
		@Override
		public void remove() {
			if(removeable) {
				setCount++;
			}
			else {
				throw new IllegalStateException("Cannot call remove directly.  Must call .next() first!");
			}
		}
	}
	
}
