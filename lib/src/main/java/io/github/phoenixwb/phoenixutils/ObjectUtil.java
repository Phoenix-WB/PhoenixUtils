package io.github.phoenixwb.phoenixutils;

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
}
