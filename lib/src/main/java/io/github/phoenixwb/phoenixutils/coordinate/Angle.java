package io.github.phoenixwb.phoenixutils.coordinate;

import java.io.Serializable;

import io.github.phoenixwb.phoenixutils.ObjectUtil;

/**
 * An Angle in degrees read and interacted with in the same manner as a compass
 * bearing
 * 
 * @author Phoenix WB
 */
public class Angle implements Serializable, Comparable<Angle> {
	private static final long serialVersionUID = 4387821434290856441L;
	public static final Angle D0 = new Angle(YPlane.NORTH, 90, XPlane.EAST);
	public static final Angle D90 = new Angle(YPlane.NORTH, 0, XPlane.WEST);
	public static final Angle D180 = new Angle(YPlane.SOUTH, 90, XPlane.WEST);
	public static final Angle D270 = new Angle(YPlane.SOUTH, 0, XPlane.EAST);

	public final XPlane xplane;
	public final YPlane yplane;
	public final double degrees;

	/**
	 * Creates a new Angle with the given bearing rotation
	 * 
	 * @param yplane  Direction of the y axis
	 * @param degrees Rotation towards the x axis from the y axis
	 * @param xplane  Direction of the x axis
	 */
	public Angle(YPlane yplane, double degrees, XPlane xplane) {
		this.yplane = yplane;
		this.degrees = ObjectUtil.constrain(degrees, 0, 90);
		this.xplane = xplane;
	}

	/**
	 * Converts the angle into Degrees True, from the positive x axis at y = 0
	 * 
	 * @return Degrees
	 */
	public double getDegreesTrue() {
		YPlane y = this.yplane;
		XPlane x = this.xplane;
		double deg = y == YPlane.NORTH ? (x == XPlane.WEST ? 90 + degrees : 90 - degrees)
				: (x == XPlane.WEST ? 270 - degrees : 270 + degrees);
		return deg;
	}

	/**
	 * Converts the Degrees True into Radians
	 * 
	 * @return Radians
	 */
	public double getRadiansTrue() {
		return Math.toRadians(getDegreesTrue());
	}

	/**
	 * Creates a new angle the given degrees of distance from the original angle
	 * 
	 * @param degrees Change in Degrees
	 * @return New Angle
	 */
	public Angle newAngle(double degrees, boolean clockwise) {
		double deg = clockwise ? ObjectUtil.convertNegative(getDegreesTrue() - degrees, 360)
				: getDegreesTrue() + degrees;
		return Angle.toAngle(deg, false);
	}

	/**
	 * Creates a new angle the given angle worth of distance from the original angle
	 * 
	 * @param angle Change in Angle
	 * @return New Angle
	 */
	public Angle newAngle(Angle angle, boolean clockwise) {
		double deg = clockwise ? ObjectUtil.convertNegative(getDegreesTrue() - angle.getDegreesTrue(), 360)
				: getDegreesTrue() + angle.getDegreesTrue();
		return Angle.toAngle(deg, false);
	}

	/**
	 * Creates a new angle from the inputed Degrees True
	 * 
	 * @param degrees   Positive Degrees True
	 * @param clockwise Direction
	 * @return New Angle
	 */
	public static Angle toAngle(double degrees, boolean clockwise) {
		double dg = clockwise ? (((-1 * degrees) % 360) + 360) % 360 : degrees % 360;

		if (dg == 0)
			return D0;

		if (dg == 90)
			return D90;

		if (dg == 180)
			return D180;

		if (dg == 270)
			return D270;

		YPlane y = (dg < 180 && dg > 0) ? YPlane.NORTH : YPlane.SOUTH;
		XPlane x = (dg > 90 && dg < 270) ? XPlane.WEST : XPlane.EAST;
		double deg = y == YPlane.NORTH ? (x == XPlane.WEST ? dg - 90 : 90 - dg)
				: (x == XPlane.WEST ? 270 - dg : dg - 270);
		return new Angle(y, deg, x);
	}

	@Override
	public int compareTo(Angle o) {
		if (getDegreesTrue() < o.getDegreesTrue()) {
			return -1;
		}
		if (getDegreesTrue() > o.getDegreesTrue()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Returns a String representation of this object's bearing
	 */
	@Override
	public String toString() {
		return yplane + " " + degrees + " " + xplane;
	}

	public static enum YPlane {
		NORTH, SOUTH;
	}

	public static enum XPlane {
		EAST, WEST;
	}
}
