package io.github.phoenixwb.phoenixutils.builder;

import java.util.ArrayList;

public class ListBuilder<E> extends ArrayList<E> {
	private static final long serialVersionUID = -4952686333624914652L;

	public ListBuilder<E> attach(E element) {
		this.add(element);
		return this;
	}
}
