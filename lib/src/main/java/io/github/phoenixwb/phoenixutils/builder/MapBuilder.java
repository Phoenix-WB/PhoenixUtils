package io.github.phoenixwb.phoenixutils.builder;

import java.util.HashMap;

/**
 * Allows trailing of placements one after another
 * 
 * @author Phoenix WB
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class MapBuilder<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = -8481598873777764280L;

	/**
	 * Places an element in this list
	 * 
	 * @param key Key associated with value
	 * @param value Value associated with key
	 * @return Current instance of the map
	 */
	public MapBuilder<K, V> place(K key, V value) {
		this.put(key, value);
		return this;
	}
}
