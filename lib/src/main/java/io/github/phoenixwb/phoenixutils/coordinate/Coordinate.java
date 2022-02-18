package io.github.phoenixwb.phoenixutils.coordinate;

import java.io.Serializable;

import static io.github.phoenixwb.phoenixutils.ObjectUtil.isZero;
import static io.github.phoenixwb.phoenixutils.ObjectUtil.isPositive;
import static io.github.phoenixwb.phoenixutils.ObjectUtil.isNegative;

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

	/**
	 * Rotates the coordinate around the given axis the set number of degrees
	 * 
	 * @param axis      Axis to rotate around
	 * @param degrees   Degrees rotated
	 * @param clockwise Direction rotated
	 */
	public void rotate(Coordinate axis, double degrees, boolean clockwise) {
		if (isZero(x - axis.x) && isZero(y - axis.y))
			return;

		Angle newRotation = getRotation(axis).newAngle(degrees, clockwise);
		double rad = Math.sqrt(Math.pow(x - axis.x, 2) + Math.pow(y - axis.y, 2));

		if (isZero(newRotation.compareTo(Angle.D0))) {
			this.x = rad + axis.x;
			this.y = axis.y;
		}

		if (isZero(newRotation.compareTo(Angle.D90))) {
			this.x = axis.x;
			this.y = rad + axis.y;
		}

		if (isZero(newRotation.compareTo(Angle.D180))) {
			this.x = -rad + axis.x;
			this.y = axis.y;
		}

		if (isZero(newRotation.compareTo(Angle.D270))) {
			this.x = axis.x;
			this.y = -rad + axis.y;
		}

		if (newRotation.yplane == YPlane.NORTH) {
			if (newRotation.xplane == XPlane.EAST) {
				this.x = (Math.sin(Math.toRadians(newRotation.degrees)) * rad) + axis.x;
				this.y = (Math.cos(Math.toRadians(newRotation.degrees)) * rad) + axis.y;
			}

			if (newRotation.xplane == XPlane.WEST) {
				this.x = -(Math.sin(Math.toRadians(newRotation.degrees)) * rad) + axis.x;
				this.y = (Math.cos(Math.toRadians(newRotation.degrees)) * rad) + axis.y;
			}
		}

		if (newRotation.yplane == YPlane.SOUTH) {
			if (newRotation.xplane == XPlane.EAST) {
				this.x = (Math.sin(Math.toRadians(newRotation.degrees)) * rad) + axis.x;
				this.y = -(Math.cos(Math.toRadians(newRotation.degrees)) * rad) + axis.y;
			}

			if (newRotation.xplane == XPlane.WEST) {
				this.x = -(Math.sin(Math.toRadians(newRotation.degrees)) * rad) + axis.x;
				this.y = -(Math.cos(Math.toRadians(newRotation.degrees)) * rad) + axis.y;
			}
		}
	}

	/**
	 * Rotates this coordinate to the given angle
	 * 
	 * @param axis  Axis based around
	 * @param angle Angle rotated to
	 */
	public void setRotation(Coordinate axis, Angle angle) {
		setRotation(axis, angle.getDegreesTrue());
	}

	/**
	 * Rotates this coordinate to the given angle in degrees
	 * 
	 * @param axis    Axis based around
	 * @param degrees Degrees True rotated to
	 */
	public void setRotation(Coordinate axis, double degrees) {
		if (isZero(x - axis.x) && isZero(y - axis.y))
			return;

		double rotation = ObjectUtil.convertNegative(degrees - this.getRotation(axis).getDegreesTrue(), 360);

		if (!isZero(rotation)) {
			rotate(axis, rotation, false);
		}
	}

	/**
	 * Rotates this coordinate to the given coordinate around the given axis
	 * 
	 * @param coord Coordinate rotated to
	 * @param axis  Coordinate rotated from
	 */
	public void rotateTo(Coordinate coord, Coordinate axis) {
		if (isZero(coord.x - axis.x) && isZero(coord.y - axis.y))
			return;

		Angle rotation = coord.getRotation(axis);
		this.setRotation(axis, rotation);
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

		if (isZero(xDif) && isZero(yDif)) {
			return new Angle(null, 0, null);
		}

		if (isZero(xDif)) {
			return isPositive(yDif) ? Angle.D90 : Angle.D270;
		}

		if (isZero(yDif)) {
			return isPositive(xDif) ? Angle.D0 : Angle.D180;
		}

		if (isPositive(xDif) && isPositive(yDif)) {
			return new Angle(YPlane.NORTH, 90 - Math.toDegrees(Math.atan(Math.abs(yDif) / Math.abs(xDif))),
					XPlane.EAST);
		}

		if (isNegative(xDif) && isPositive(yDif)) {
			return new Angle(YPlane.NORTH, 90 - Math.toDegrees(Math.atan(Math.abs(yDif) / Math.abs(xDif))), XPlane.WEST);
		}

		if (isPositive(xDif) && isNegative(yDif)) {
			return new Angle(YPlane.SOUTH, 90 - Math.toDegrees(Math.atan(Math.abs(yDif) / Math.abs(xDif))), XPlane.EAST);
		}
		
		return new Angle(YPlane.SOUTH, 90 - Math.toDegrees(Math.atan(Math.abs(yDif) / Math.abs(xDif))), XPlane.WEST);
	}
}
