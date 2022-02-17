package io.github.phoenixwb.phoenixutils.holder;

import java.io.Serializable;

public class Holder<T> implements Serializable {
	private static final long serialVersionUID = -3474427772844665843L;
	T val;
	
	public Holder() {
		set(null);
	}
	
	public Holder(T val) {
		set(val);
	}
	
	public T get() {
		return val;
	}
	
	public Holder<T> set(T val) {
		this.val = val;
		return this;
	}
}
