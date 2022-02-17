package io.github.phoenixwb.phoenixutils;

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
	 * @param ts Receives each value
	 */
	@SuppressWarnings("unchecked")
	default void repeat(T... ts) {
		for(T t : ts) {
			receive(t);
		}
	}
}
