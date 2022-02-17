package io.github.phoenixwb.phoenixutils;

import io.github.phoenixwb.phoenixutils.functionalinterface.Performer;

public class ObjectUtil {
	public static <T> boolean is(T obj, T equalTo, Performer per) {
		if(obj == equalTo) {
			per.execute();
			return true;
		}
		return false;
	}
	
	public static boolean isZero(double val) {
		return val == 0;
	}
	
	public static boolean isPositive(double val) {
		return val > 0;
	}
	
	public static boolean isNegative(double val) {
		return val < 0;
	}
	
	public static <T> boolean isNull(T obj) {
		return obj == null;
	}
	
	public static double convertNegative(double val, double range) {
		if(isNegative(val)) {
			return ((val % range) + range) % range;
		}
		return val;
	}
}
