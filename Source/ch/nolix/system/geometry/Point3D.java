//package declaration
package ch.nolix.system.geometry;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalDoubleHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.math.algebra.Vector;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;

//class
/**
 * A 3D point is a point with 3 coordinates that are called x-coordinate,
 * y-coordinate and z-coordinate.
 * 
 * A 3D point is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public class Point3D extends Element {

  //constant
  public static final double DEFAULT_X = 0.0;

  //constant
  public static final double DEFAULT_Y = 0.0;

  //constant
  public static final double DEFAULT_Z = 0.0;

  //static method
  /**
   * @param specification
   * @return a new {@link Point3D} from the given specification.
   */
  public static Point3D fromSpecification(final INode<?> specification) {

    final var attributes = specification.getStoredChildNodes();

    return new Point3D(
        attributes.getStoredAt1BasedIndex(1).toDouble(),
        attributes.getStoredAt1BasedIndex(2).toDouble(),
        attributes.getStoredAt1BasedIndex(3).toDouble());
  }

  //attribute
  private final double x;

  //attribute
  private final double y;

  //attribute
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
  public IContainer<INode<?>> getAttributes() {
    return LinkedList.withElement(
        Node.withHeader(GlobalDoubleHelper.toString(getX())),
        Node.withHeader(GlobalDoubleHelper.toString(getY())),
        Node.withHeader(GlobalDoubleHelper.toString(getZ())));
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
            Math.pow(getZ() - point.getZ(), 2));
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
    return Vector.withValue(getX(), getY(), getZ());
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
    return (float) getX();
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
    return (float) getY();
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
    return (float) getZ();
  }
}
