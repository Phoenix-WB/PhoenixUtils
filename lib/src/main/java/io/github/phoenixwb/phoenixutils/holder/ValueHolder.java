package io.github.phoenixwb.phoenixutils.holder;

/**
 * Declares an object with capacity to hold values
 * 
 * @author Phoenix WB
 */
public interface ValueHolder {
	/**
	 * Total space in the ValueHolder
	 *
	 * @return Storage Space
	 */
	int storage();
	
	/**
	 * Available space in the ValueHolder
	 * 	
	 * @return Free Space
	 */
	int availableSpace();
}
