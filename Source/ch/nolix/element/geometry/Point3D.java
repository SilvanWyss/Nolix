//package declaration
package ch.nolix.element.geometry;

//own imports
import ch.nolix.common.commontype.commontypehelper.GlobalDoubleHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.math.Vector;
import ch.nolix.element.elementapi.IElement;

//class
/**
 * A 3D point is a point with 3 coordinates
 * that are called x-coordinate, y-coordinate and z-coordinate.
 * 
 * A 3D point is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 * @lines 160
 */
public class Point3D implements IElement<Point3D> {
	
	//constants
	public static final double DEFAULT_X = 0.0;
	public static final double DEFAULT_Y = 0.0;
	public static final double DEFAULT_Z = 0.0;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Point3D} from the given specification.
	 */
	public static Point3D fromSpecification(final BaseNode specification) {
		
		final var attributes = specification.getRefAttributes();
		
		return new Point3D(
			attributes.getRefAt(1).toDouble(),
			attributes.getRefAt(2).toDouble(),
			attributes.getRefAt(3).toDouble()
		);
	}
	
	//attributes
	private final double x;
	private final double y;
	private final double z;
	
	//constructor
	/**
	 * Creates a new 3D point with default coordinates.
	 */
	public Point3D() {
		
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
	public Point3D(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(
			Node.withHeader(GlobalDoubleHelper.toString(getX())),
			Node.withHeader(GlobalDoubleHelper.toString(getY())),
			Node.withHeader(GlobalDoubleHelper.toString(getZ()))
		);
	}
	
	//method
	/**
	 * @param point
	 * @return the distance of this 3D point to the given point.
	 */
	public double getDistanceTo(final Point3D point) {
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
		return Vector.withValues(getX(), getY(), getZ());
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
