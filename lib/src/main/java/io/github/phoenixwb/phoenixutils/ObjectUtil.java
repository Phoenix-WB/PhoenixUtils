package io.github.phoenixwb.phoenixutils;

import io.github.phoenixwb.phoenixutils.functionalinterface.Performer;

/**
 * Utility class for a variety of objects
 * 
 * @author Phoenix WB
 */
public class ObjectUtil {
	/**
	 * Checks whether one object matches another, then performs an action if so
	 * 
	 * @param <O> Object type
	 * @param obj Comparable Object
	 * @param equalTo Object tested equal to
	 * @param per Action executed
	 * @return obj equals equalTo
	 */
	public static <O> boolean is(Comparable<O> obj, O equalTo, Performer per) {
		if(obj.compareTo(equalTo) == 0) {
			per.execute();
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the value inputed is zero
	 * 
	 * @param val Inputed value
	 * @return val equals 0
	 */
	public static boolean isZero(double val) {
		return val == 0;
	}
	/**
	 * Checks if the value inputed is positive
	 * 
	 * @param val Inputed value
	 * @return val is more than zero
	 */
	public static boolean isPositive(double val) {
		return val > 0;
	}
	
	/**
	 * Checks if the value inputed is negative
	 * 
	 * @param val Inputed value
	 * @return val is less than zero
	 */
	public static boolean isNegative(double val) {
		return val < 0;
	}
	
	/**
	 * Checks if the object inputted is null
	 * 
	 * @param <O> Object type
	 * @param obj Inputed object
	 * @return object equals null
	 */
	public static <O> boolean isNull(O obj) {
		return obj == null;
	}
	/**
	 * Checks if a value is negative, then converts it to the maximum - take the absolute of the value - in a range
	 * 
	 * @param val Inputed value
	 * @param range Maximum value
	 * @return Value converted
	 */
	public static double convertNegative(double val, double range) {
		if(isNegative(val)) {
			return ((val % range) + range) % range;
		}
		return val;
	}
	
	/**
	 * Ensures that a value is within a range, and reduces the value to the minimum value if less, or increases to the maximum value if more, than the range
	 * 
	 * @param val Inputed value
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return Value converted
	 */
	public static double constrain(double val, double min, double max) {
		return (val >= min && val <= max) ? val : (val < min ? min : max);
	}
	
	/**
	 * Checks whether a value is close to another by a specified uncertainty
	 * 
	 * @param val Inputed value
	 * @param target Targeted value
	 * @param uncertainty Range Uncertainty
	 * @return Value around target value
	 */
	public static boolean around(double val, double target, double uncertainty) {
		return val == target || val >= target - uncertainty || val <= target + uncertainty;
	}
}
