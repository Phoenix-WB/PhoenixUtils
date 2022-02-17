package io.github.phoenixwb.phoenixutils;

/**
 * Represents a looper of a function
 * 
 * @author Phoenix WB
 */
@FunctionalInterface
public interface Looper {
	/**
	 * Conducts a void function
	 */
	void conduct();
	
	/**
	 * Conducts function multiple times
	 * @param times Amount of times to conduct function
	 */
	default void loop(int times) {
		for(int i = 0; i < times; i++) {
			conduct();
		}
	}
}
