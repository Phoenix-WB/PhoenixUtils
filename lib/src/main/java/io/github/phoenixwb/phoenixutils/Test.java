package io.github.phoenixwb.phoenixutils;

import io.github.phoenixwb.phoenixutils.coordinate.Coordinate;

final class Test {
	public static void main(String[] args) {
		Coordinate coord = new Coordinate(3, 5);
		Coordinate axis = new Coordinate(6, 12);
		System.out.println(coord.getRotation(axis).getDegreesTrue());
	}
}
