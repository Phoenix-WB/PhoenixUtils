package io.github.phoenixwb.phoenixutils;

import io.github.phoenixwb.phoenixutils.coordinate.Coordinate;

final class Test {
	public static void main(String[] args) {
		Coordinate coord = new Coordinate(13, 24);
		Coordinate axis = new Coordinate(9, 17);
		coord.rotate(axis, 90, true);
		System.out.println(encapsulate(coord.x, coord.y));
	}
	
	static String encapsulate(Object o1, Object o2) {
		return "(" + o1 + ", " + o2 + ")";
	}
}
