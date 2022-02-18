package io.github.phoenixwb.phoenixutils.holder;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Holds a single value, modifiable value
 * 
 * @author Phoenix WB
 *
 * @param <T> Type of held value
 */
public class Holder<T> implements Serializable, ValueHolder {
	private static final long serialVersionUID = -3474427772844665843L;
	protected T val;
	
	/**
	 * Constructs a holder with a null value
	 */
	public Holder() {
		set(null);
	}
	
	/**
	 * Constructs a holder with a set value
	 *  
	 * @param val Value set
	 */
	public Holder(T val) {
		set(val);
	}
	
	/**
	 * Returns the stored value
	 * 
	 * @return Value
	 */
	public T get() {
		return val;
	}
	
	/**
	 * Sets the stored value
	 * 
	 * @param val New value
	 * @return Current instance of the Holder
	 */
	public Holder<T> set(T val) {
		this.val = val;
		return this;
	}
	
	/**
	 * Interact with the stored value
	 * 
	 * @param fc Input Function
	 * @return Current instance of the Holder
	 */
	public Holder<T> modify(Function<T,T> fc) {
		set(fc.apply(val));
		return this;
	}

	@Override
	public int storage() {
		return 1;
	}

	@Override
	public int availableSpace() {
		return val == null ? 1 : 0;
	}
}
