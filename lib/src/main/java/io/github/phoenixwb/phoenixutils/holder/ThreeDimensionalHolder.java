package io.github.phoenixwb.phoenixutils.holder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ThreeDimensionalHolder<T> implements Serializable {
	private static final long serialVersionUID = 2635594124830685745L;
	ArrayList<ArrayList<ArrayList<T>>> dimension;

	public ThreeDimensionalHolder(int x, int y, int z) {
		dimension = new ArrayList<ArrayList<ArrayList<T>>>(x);
		Collections.fill(dimension, new ArrayList<ArrayList<T>>(y));
		dimension.forEach(list -> {
			Collections.fill(list, new ArrayList<T>(z));
		});
		fillAll(null);
	}
	
	public T get(int x, int y, int z) {
		return dimension.get(x).get(y).get(z);
	}
	
	public ThreeDimensionalHolder<T> set(T val, int x, int y, int z) {
		dimension.get(x).get(y).set(z, val);
		return this;
	}
	
	public ThreeDimensionalHolder<T> fillAll(T val) {
		dimension.forEach(list -> {
			list.forEach(sublist -> {
				Collections.fill(sublist, val);
			});
		});
		return this;
	}
}
