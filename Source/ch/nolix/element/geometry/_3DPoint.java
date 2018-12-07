//package declaration
package ch.nolix.element.geometry;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.helper.DoubleHelper;
import ch.nolix.core.mathematics.Vector;
import ch.nolix.element.core.Element;

//class
/**
 * A 3D point is a point with 3 coordinates
 * that are called x-coordinate, y-coordinate and z-coordinate.
 * 
 * A 3D point is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 150
 */
public class _3DPoint  extends Element<_3DPoint> {
	
	//default values.
	public static final double DEFAULT_X = 0.0;
	public static final double DEFAULT_Y = 0.0;
	public static final double DEFAULT_Z = 0.0;

	//attributes
	private final double x;
	private final double y;
	private final double z;
	
	//constructor
	/**
	 * Creates a new 3D point with default coordinates.
	 */
	public _3DPoint() {
		
		//Calls other constructor.
		this(DEFAULT_X, DEFAULT_Y, DEFAULT_Z);
	}
	
	//method
	/**
	 * Creates a new 3D point with the given coordinates.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public _3DPoint(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//method
	/**
	 * @return the attribtues of this 3D point.
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		return
		new List<DocumentNode>(
			DocumentNode
			.createSpecificationWithHeader(
				DoubleHelper.toString(getX())
			),
			DocumentNode
			.createSpecificationWithHeader(
				DoubleHelper.toString(getY())
			),
			DocumentNode
			.createSpecificationWithHeader(
				DoubleHelper.toString(getZ())
			)
		);
	}
	
	//method
	/**
	 * @param point
	 * @return the distance of this 3D point to the given point.
	 */
	public double getDistanceTo(final _3DPoint point) {
		return Math.sqrt(
			Math.pow(getX() - point.getX(), 2) +
			Math.pow(getY() - point.getY(), 2) +
			Math.pow(getZ() - point.getZ(), 2)
		);		
	}
	
	//method
	/**
	 * @return the distance of this 3D point to the origin.
	 */
	public double getDistanceToOrigin() {
		return getPositionVector().getEuclidNorm();
	}
	
	//method
	/**
	 * @return the position vector of this 3D point.
	 */
	public Vector getPositionVector() {
		return new Vector(3).setValues(getX(), getY(), getZ());
	}
	
	//method
	/**
	 * @return the x-coordinate of this 3D point.
	 */
	public double getX() {
		return x;
	}
	
	//method
	/**
	 * @return the x-coordinate of this 3D point as float.
	 */
	public float getXAsFloat() {
		return (float)getX();
	}
	
	//method
	/**
	 * @return the y-coordinate of this 3D point.
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
	 * @return the z-coordinate of this 3D point.
	 */
	public double getZ() {
		return z;
	}
	
	//method
	/**
	 * @return the z-coordinate of this 3D point as float.
	 */
	public float getZAsFloat() {
		return (float)getZ();
	}
}
