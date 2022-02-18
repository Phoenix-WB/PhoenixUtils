package io.github.phoenixwb.phoenixutils.builder;

import java.util.ArrayList;

/**
 * Allows trailing of attachments one after another
 * 
 * @author Phoenix WB
 *
 * @param <E> Element type
 */
public class ListBuilder<E> extends ArrayList<E> {
	private static final long serialVersionUID = -4952686333624914652L;

	/**
	 * Attaches an element to this list
	 * 
	 * @param element Element to be appended to this list
	 * @return Current instance of the list
	 */
	public ListBuilder<E> attach(E element) {
		this.add(element);
		return this;
	}
}
