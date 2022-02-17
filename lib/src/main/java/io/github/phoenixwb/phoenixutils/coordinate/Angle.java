package io.github.phoenixwb.phoenixutils.coordinate;

import java.io.Serializable;

/**
 * An Angle in degrees read and interacted with in the same manner as a compass bearing
 * @author Phoenix WB
 */
public class Angle implements Serializable {
	private static final long serialVersionUID = 4387821434290856441L;
	public static final Angle D0 = new Angle(YPlane.NORTH, 90, XPlane.EAST);
	public static final Angle D90 = new Angle(YPlane.NORTH, 0, XPlane.WEST);
	public static final Angle D180 = new Angle(YPlane.SOUTH, 90, XPlane.WEST);
	public static final Angle D270 = new Angle(YPlane.SOUTH, 0, XPlane.EAST);
	
	public final XPlane xplane;
	public final YPlane yplane;
	public final double degrees;

	public Angle(YPlane yplane, double degrees, XPlane xplane) {
		this.yplane = yplane;
		this.degrees = degrees;
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
	 * @return Radians
	 */
	public double getRadians() {
		return Math.toRadians(getDegreesTrue());
	}

	/**
	 * Creates a new angle the given degrees of distance from the original angle
	 * 
	 * @param degrees Change in Degrees
	 * @return New Angle
	 */
	public Angle newAngle(double degrees, boolean clockwise) {
		double deg = clockwise ? getDegreesTrue() - degrees : getDegreesTrue() + degrees;
		return Angle.toAngle(deg, false);
	}

	/**
	 * Creates a new angle the given angle worth of distance from the original angle
	 * 
	 * @param angle Change in Angle
	 * @return New Angle
	 */
	public Angle newAngle(Angle angle, boolean clockwise) {
		double deg = clockwise ? getDegreesTrue() - angle.getDegreesTrue() : getDegreesTrue() + angle.getDegreesTrue();
		return Angle.toAngle(deg, false);
	}

	/**
	 * Creates a new angle from the inputed Degrees True
	 * 
	 * @param degrees Degrees True
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

	public static enum YPlane {
		NORTH, SOUTH;
	}

	public static enum XPlane {
		EAST, WEST;
	}
}
