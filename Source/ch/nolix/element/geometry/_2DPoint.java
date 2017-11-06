//package declaration
package ch.nolix.element.geometry;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.helper.DoubleHelper;
import ch.nolix.core.mathematics.Vector;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.core.Element;

//class
/**
 * A 2D point is a point with 2 coordinates
 * that are called x-coordinate and y-coordinate.
 * 
 * A 2D point is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 140
 */
public final class _2DPoint extends Element {

	//default values.
	public static final double DEFAULT_X = 0.0;
	public static final double DEFAULT_Y = 0.0;
	
	//attributes
	private final double x;
	private final double y;
	
	//constructor
	/**
	 * Creates new 2D point with default coordinates.
	 */
	public _2DPoint() {
		
		//Calls other constructor
		this(DEFAULT_X, DEFAULT_Y);
	}
	
	//method
	/**
	 * Creates new 2D point with the given coordinates.
	 * 
	 * @param x
	 * @param y
	 */
	public _2DPoint(final double x,	final double y) {
		this.x = x;
		this.y = y;
	}
	
	//method
	/**
	 * @return the attributes of this 2D point.
	 */
	public List<StandardSpecification> getAttributes() {
		return
		new List<StandardSpecification>(
			StandardSpecification
			.createSpecificationWithHeaderOnly(
				DoubleHelper.toString(getX())
			),
			StandardSpecification
			.createSpecificationWithHeaderOnly(
				DoubleHelper.toString(getY())
			)
		);
	}
	
	//method
	/**
	 * @param point
	 * @return the distance of this 2D point to the given point
	 */
	public double getDistanceTo(final _2DPoint point) {
		return
		Math.sqrt(
			Math.pow(getX() - point.getY(), 2)
			+ Math.pow(getY() - point.getY(), 2)
		);
	}
	
	//method
	/**
	 * @return the distance of this 2D point to the origin.
	 */
	public double getDistanceToOrigin() {
		return getPositionVector().getEuclidNorm();
	}
	
	//method
	/**
	 * @return the position vector of this 2D point.
	 */
	public Vector getPositionVector() {
		return new Vector(2).setValues(getX(), getY());
	}
	
	//method
	/**
	 * @return the x-coordinate of this 2D point.
	 */
	public double getX() {
		return x;
	}
	
	//method
	/**
	 * @return the x-coordinate of this 2D point as float.
	 */
	public float getXAsFloat() {
		return (float)getX();
	}
	
	//method
	/**
	 * @return the y-coordinate of this 2D point.
	 */
	public double getY() {
		return y;
	}
	
	//method
	/**
	 * @return the y-coordinate of this 3D point as float.
	 */
	public float getYAsFloat() {
		return (float)getY();
	}
	
	//method
	/**
	 * @return a new 3D point
	 * with the x-coordinate and the y-coordinate of this 2D point
	 * and a z-coordinate=0.
	 */
	public _3DPoint to3DPoint() {
		return
		new _3DPoint(getX(), getY(), 0.0);
	}
}
