package io.github.phoenixwb.phoenixutils.holder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Holds several values in a two-dimensional space
 * 
 * @author Phoenix WB
 *
 * @param <T> Type of held value
 */
public class TwoDimensionalHolder<T> implements Serializable, ValueHolder {
	private static final long serialVersionUID = 3300275764715367025L;
	private int storage;
	protected ArrayList<ArrayList<T>> dimension;

	/**
	 * Constructs a new TwoDimensionalHolder of specified capacity
	 * 
	 * @param x Holder width
	 * @param y Holder Length
	 */
	public TwoDimensionalHolder(int x, int y) {
		if (x <= 0 || y <= 0)
			throw new IllegalArgumentException("Impossible Capacity: " + x * y);
		dimension = new ArrayList<ArrayList<T>>(x);
		Collections.fill(dimension, new ArrayList<T>(y));
		fillAll(null);
		storage = x * y;
	}

	/**
	 * Retrieves the value from the specified position
	 * 
	 * @param x X position
	 * @param y Y position
	 * @return Value at position
	 */
	public T get(int x, int y) {
		return dimension.get(x).get(y);
	}

	/**
	 * Sets the value at the specified position
	 * 
	 * @param val Input value
	 * @param x   X position
	 * @param y   Y position
	 * @return Current instance of the TwoDimensionalHolder
	 */
	public TwoDimensionalHolder<T> set(T val, int x, int y) {
		dimension.get(x).set(y, val);
		return this;
	}

	/**
	 * Sets all the values in the TwoDimensionalHolder
	 * 
	 * @param val Input Value
	 * @return Current instance of the TwoDimensionalHolder
	 */
	public TwoDimensionalHolder<T> fillAll(T val) {
		dimension.forEach(list -> {
			Collections.fill(list, val);
		});
		return this;
	}

	/**
	 * Modify a single value in the TwoDimensionalHolder
	 * 
	 * @param fc Input Function
	 * @param x  X position
	 * @param y  Y position
	 * @return Current instance of the TwoDimensionalHolder
	 */
	public TwoDimensionalHolder<T> modify(Function<T, T> fc, int x, int y) {
		dimension.get(x).set(y, fc.apply(dimension.get(x).get(y)));
		return this;
	}

	/**
	 * Modify all values in the TwoDimensionalHolder
	 * 
	 * @param fc Input Function
	 * @return Current instance of the TwoDimensionalHolder
	 */
	public TwoDimensionalHolder<T> modifyAll(Function<T, T> fc) {
		dimension.forEach(list -> {
			for (int i = 0; i < list.size(); i++) {
				list.set(i, fc.apply(list.get(i)));
			}
		});
		return this;
	}

	/**
	 * Access each value in the TwoDimensionalHolder
	 * 
	 * @param c Input Consumer
	 * @return Current instance of the TwoDimensionalHolder
	 */
	public TwoDimensionalHolder<T> foreach(Consumer<T> c) {
		dimension.forEach(list -> {
			list.forEach(c::accept);
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
