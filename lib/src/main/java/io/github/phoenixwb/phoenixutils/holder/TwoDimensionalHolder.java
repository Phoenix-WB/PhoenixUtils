package io.github.phoenixwb.phoenixutils.holder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class TwoDimensionalHolder<T> implements Serializable {
	private static final long serialVersionUID = 3300275764715367025L;
	ArrayList<ArrayList<T>> dimension;

	public TwoDimensionalHolder(int x, int y) {
		dimension = new ArrayList<ArrayList<T>>(x);
		Collections.fill(dimension, new ArrayList<T>(y));
		fillAll(null);
	}
	
	public T get(int x, int y) {
		return dimension.get(x).get(y);
	}
	
	public TwoDimensionalHolder<T> set(T val, int x, int y) {
		dimension.get(x).set(y, val);
		return this;
	}

	public TwoDimensionalHolder<T> fillAll(T val) {
		dimension.forEach(list -> {
			Collections.fill(list, val);
		});
		return this;
	}
}
