package io.github.phoenixwb.phoenixutils.coordinate;

import java.io.Serializable;

import io.github.phoenixwb.phoenixutils.ObjectUtil;
import io.github.phoenixwb.phoenixutils.coordinate.Angle.XPlane;
import io.github.phoenixwb.phoenixutils.coordinate.Angle.YPlane;

/**
 * Holds an X and Y position
 * 
 * @author Phoenix WB
 */
public class Coordinate implements Serializable {
	private static final long serialVersionUID = 1929366792987762556L;
	public double x, y;

	/**
	 * Creates a new Coordinate with the given X and Y values
	 * 
	 * @param x Magnitude along X axis
	 * @param y Magnitude along Y axis
	 */
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void rotate(Coordinate axis, double degrees, boolean clockwise) {
	}

	public void setRotated(Coordinate axis, double degrees) {
	}

	/**
	 * Calculates the angle between this point based around the axis point provided
	 * 
	 * @param axis Axis to revolve this point around
	 * @return Angle between this point and the axis
	 */
	public Angle getRotation(Coordinate axis) {
		double xDif = x - axis.x;
		double yDif = y - axis.y;

		if (ObjectUtil.isZero(xDif) && ObjectUtil.isZero(yDif)) {
			return new Angle(null, 0, null);
		}

		if (ObjectUtil.isZero(xDif)) {
			return ObjectUtil.isPositive(yDif) ? Angle.D90 : Angle.D270;
		}

		if (ObjectUtil.isZero(yDif)) {
			return ObjectUtil.isPositive(xDif) ? Angle.D0 : Angle.D180;
		}

		if (ObjectUtil.isPositive(xDif) && ObjectUtil.isPositive(yDif)) {
			return new Angle(YPlane.NORTH, 90 - Math.toDegrees(Math.atan(Math.abs(yDif) / Math.abs(xDif))),
					XPlane.EAST);
		}

		if (ObjectUtil.isNegative(xDif) && ObjectUtil.isPositive(yDif)) {
			return new Angle(YPlane.NORTH, Math.toDegrees(Math.atan(Math.abs(yDif) / Math.abs(xDif))), XPlane.WEST);
		}

		if (ObjectUtil.isPositive(xDif) && ObjectUtil.isNegative(yDif)) {
			return new Angle(YPlane.SOUTH, Math.toDegrees(Math.atan(Math.abs(yDif) / Math.abs(xDif))), XPlane.WEST);
		}
		
		return new Angle(YPlane.SOUTH, 90 - Math.toDegrees(Math.atan(Math.abs(yDif) / Math.abs(xDif))), XPlane.EAST);
	}
}
