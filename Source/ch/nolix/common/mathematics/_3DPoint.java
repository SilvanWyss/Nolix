/*
 * file:	_3DPoint.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	120
 */

//package declaration
package ch.nolix.common.mathematics;

//class
public class _3DPoint {

	//attributes
	private double x = 0.0;
	private double y = 0.0;
	private double z = 0.0;
	
	//constructor
	/**
	 * Creates new 3D point with default values.
	 */
	public _3DPoint() {}
	
	//method
	/**
	 * Creates new 3D point with the given values.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public _3DPoint(double x, double y, double z) {
		setXandYandZ(x, y, z);
	}
	
	//method
	/**
	 * @param point
	 * @return the distance of this 3D point to the given point
	 */
	public final double getDistanceTo(_3DPoint point) {
		return Math.sqrt(
			Math.pow(getX() - point.getX(), 2) +
			Math.pow(getY() - point.getY(), 2) +
			Math.pow(getZ() - point.getZ(), 2)
		);		
	}
	
	//method
	/**
	 * @return the position vector of this 3D point.
	 */
	public final Vector getPositionVector() {
		return new Vector(3).setValues(getX(), getY(), getZ());
	}
	
	//method
	/**
	 * @return the x-value of this 3D point
	 */
	public final double getX() {
		return x;
	}
	
	//method
	/**
	 * @return the y-value of this 3D point
	 */
	public final double getY() {
		return y;
	}
	
	//method
	/**
	 * @return the z-value of this 3D point
	 */
	public final double getZ() {
		return z;
	}
	
	//method
	/**
	 * Sets the x-value of this 3D point.
	 * 
	 * @param x
	 */
	public final void setX(double x) {
		this.x = x;
	}
	
	//method
	/**
	 * Sets the y-value of this 3D point.
	 * 
	 * @param y
	 */
	public final void setY(double y) {
		this.y = y;
	}
	
	//method
	/**
	 * Sets the z-value of this 3D point.
	 * 
	 * @param z
	 */
	public final void setZ(double z) {
		this.z = z;
	}
	
	//method
	/**
	 * Sets the x-value, y-value and z-value of this 3D point.
	 * 
	 * @param x
	 * @param y
	 */
	public final void setXandYandZ(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}
}
