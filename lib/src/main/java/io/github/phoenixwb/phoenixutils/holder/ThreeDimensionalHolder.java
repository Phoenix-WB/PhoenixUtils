package io.github.phoenixwb.phoenixutils.holder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Holds multiple values in a three-dimensional space
 * 
 * @author Phoenix WB
 *
 * @param <T> Type of held value
 */
public class ThreeDimensionalHolder<T> implements Serializable, ValueHolder {
	private static final long serialVersionUID = 2635594124830685745L;
	private int storage;
	protected ArrayList<ArrayList<ArrayList<T>>> dimension;

	/**
	 * Constructs a new ThreeDimensionalHolder of specified capacity
	 * 
	 * @param x Holder width
	 * @param y Holder length
	 * @param z Holder depth
	 */
	public ThreeDimensionalHolder(int x, int y, int z) {
		if (x <= 0 || y <= 0 || z <= 0)
			throw new IllegalArgumentException("Impossible Capacity: " + x * y * z);
		dimension = new ArrayList<ArrayList<ArrayList<T>>>(x);
		Collections.fill(dimension, new ArrayList<ArrayList<T>>(y));
		dimension.forEach(list -> {
			Collections.fill(list, new ArrayList<T>(z));
		});
		fillAll(null);
		storage = x * y * z;
	}

	/**
	 * Retrieves the value from the specified position
	 * 
	 * @param x X position
	 * @param y Y position
	 * @param z Z position
	 * @return Value at position
	 */
	public T get(int x, int y, int z) {
		return dimension.get(x).get(y).get(z);
	}

	/**
	 * Sets the value at the specified position
	 * 
	 * @param val Input value
	 * @param x   X position
	 * @param y   Y position
	 * @param z   Z position
	 * @return Current instance of the ThreeDimensionalHolder
	 */
	public ThreeDimensionalHolder<T> set(T val, int x, int y, int z) {
		dimension.get(x).get(y).set(z, val);
		return this;
	}

	/**
	 * Sets all the values in the ThreeDimensionalHolder
	 * 
	 * @param val Input value
	 * @return Current instance of the ThreeDimensionalHolder
	 */
	public ThreeDimensionalHolder<T> fillAll(T val) {
		dimension.forEach(list -> {
			list.forEach(sublist -> {
				Collections.fill(sublist, val);
			});
		});
		return this;
	}

	/**
	 * Modify a single value in the ThreeDimensionalHolder
	 * 
	 * @param fc Input Function
	 * @param x  X position
	 * @param y  Y position
	 * @param z  Z position
	 * @return Current instance of the ThreeDimensionalHolder
	 */
	public ThreeDimensionalHolder<T> modify(Function<T, T> fc, int x, int y, int z) {
		dimension.get(x).get(y).set(z, fc.apply(dimension.get(x).get(y).get(z)));
		return this;
	}

	/**
	 * Modify all values in the ThreeDimensionalHolder
	 * 
	 * @param fc Input Function
	 * @return Current instance of the threeDimensionalHolder
	 */
	public ThreeDimensionalHolder<T> modifyAll(Function<T, T> fc) {
		dimension.forEach(list$1 -> {
			list$1.forEach(list$2 -> {
				for (int i = 0; i < list$2.size(); i++) {
					list$2.set(i, fc.apply(list$2.get(i)));
				}
			});
		});
		return this;
	}

	/**
	 * Access each value in the ThreeDimensionalHolder
	 * 
	 * @param c Input Consumer
	 * @return Current instance of the ThreeDimensionalHolder
	 */
	public ThreeDimensionalHolder<T> foreach(Consumer<T> c) {
		dimension.forEach(list$1 -> {
			list$1.forEach(list$2 -> {
				list$2.forEach(c::accept);
			});
		});
		return this;
	}

	@Override
	public int storage() {
		return storage;
	}

	@Override
	public int availableSpace() {
		Holder<Integer> space = new Holder<Integer>(0);
		foreach(slot -> {
			if (slot != null) {
				space.modify(val -> val++);
			}
		});
		return space.get();
	}
}
