package io.github.phoenixwb.phoenixutils.builder;

import java.util.HashSet;

/**
 * Allows trailing of insertions one after another
 * 
 * @author Phoenix WB
 *
 * @param <E> Element type
 */
public class SetBuilder<E> extends HashSet<E> {
	private static final long serialVersionUID = 1621695155903314554L;

	/**
	 * Inserts an element in this set
	 * 
	 * @param element Element added to this set
	 * @return Current instance of the set
	 */
	public SetBuilder<E> insert(E element) {
		this.add(element);
		return this;
	}
}
