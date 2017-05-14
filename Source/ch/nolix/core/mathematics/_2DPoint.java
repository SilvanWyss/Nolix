/*
 * file:	_2DPoint.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	90
 */

//package declaration
package ch.nolix.core.mathematics;

//class
public final class _2DPoint {

	//attributes
	private double x = 0.0;
	private double y = 0.0;
	
	//constructor
	/**
	 * Creates new 2D point with default values.
	 */
	public _2DPoint() {}
	
	//method
	/**
	 * Creates new 2D point with the given values.
	 * 
	 * @param x
	 * @param y
	 */
	public _2DPoint(double x, double y) {
		setXandY(x, y);
	}
	
	//method
	/**
	 * @param point
	 * @return the distance of this 2D point to the given point
	 */
	public final double getDistanceTo(_2DPoint point) {
		return Math.sqrt(
			Math.pow(getX() - point.getX(), 2) +
			Math.pow(getY() - point.getY(), 2)
		);
	}
	
	//method
	/**
	 * @return the position vector of this 2D point.
	 */
	public final Vector getPositionVector() {
		return new Vector(2).setValues(getX(), getY());
	}
	
	//method
	/**
	 * @return the x-value of this 2D point
	 */
	public final double getX() {
		return x;
	}
	
	//method
	/**
	 * @return the y-value of this 2D point
	 */
	public final double getY() {
		return y;
	}
	
	//method
	/**
	 * Sets the x-value of this 2D point.
	 * 
	 * @param x
	 */
	public final void setX(double x) {
		this.x = x;
	}
	
	//method
	/**
	 * Sets the y-value of this 2D point.
	 * 
	 * @param y
	 */
	public final void setY(double y) {
		this.y = y;
	}
	
	//method
	/**
	 * Sets the x- and y-value of this 2D point.
	 * 
	 * @param x
	 * @param y
	 */
	public final void setXandY(double x, double y) {
		setX(x);
		setY(y);
	}
}
