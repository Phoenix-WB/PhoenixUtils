package io.github.phoenixwb.phoenixutils.functionalinterface;

import java.util.function.Function;

import io.github.phoenixwb.phoenixutils.builder.ListBuilder;

/**
 * Represents a repeater of a function
 * 
 * @author Phoenix WB
 *
 * @param <T> The received type
 */
@FunctionalInterface
public interface Repeater<T> {
	/**
	 * Receives an input
	 * @param t Input argument
	 */
	void receive(T t);
	
	/**
	 * Repeats through the input argument
	 * @param t Input argument
	 * @param times Times input argument received
	 */
	default void repeat(T t, int times) {
		for(int i = 0; i < times; i++) {
			receive(t);
		}
	}
	
	/**
	 * Repeats through the inputted arguments
	 * @param ts ListBuilder to loop through
	 */
	default void repeat(Function<ListBuilder<T>, ListBuilder<T>> ts) {
		ts.apply(new ListBuilder<T>()).forEach(this::receive);
	}
}
