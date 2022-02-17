package io.github.phoenixwb.phoenixutils;

import java.util.HashMap;

public class MapBuilder<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = -8481598873777764280L;

	public MapBuilder<K, V> place(K key, V value) {
		put(key, value);
		return this;
	}
}
