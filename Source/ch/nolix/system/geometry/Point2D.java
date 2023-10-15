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
 * A 2D point is a point with 2 coordinates that are called x-coordinate and
 * y-coordinate.
 * 
 * A 2D point is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public final class Point2D extends Element {

  // constant
  public static final double DEFAULT_X = 0.0;

  // constant
  public static final double DEFAULT_Y = 0.0;

  // attribute
  private final double x;

  // attribute
  private final double y;

  // constructor
  /**
   * Creates a new 2D point with default coordinates.
   */
  public Point2D() {

    // Calls other constructor
    this(DEFAULT_X, DEFAULT_Y);
  }

  // method
  /**
   * Creates a new 2D point with the given coordinates.
   * 
   * @param x
   * @param y
   */
  public Point2D(final double x, final double y) {
    this.x = x;
    this.y = y;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    return LinkedList.withElement(
        Node.withHeader(GlobalDoubleHelper.toString(getX())),
        Node.withHeader(GlobalDoubleHelper.toString(getY())));
  }

  // method
  /**
   * @param point
   * @return the distance of this 2D point to the given point
   */
  public double getDistanceTo(final Point2D point) {
    return Math.sqrt(
        Math.pow(getX() - point.getY(), 2)
            + Math.pow(getY() - point.getY(), 2));
  }

  // method
  /**
   * @return the distance of this 2D point to the origin.
   */
  public double getDistanceToOrigin() {
    return getPositionVector().getEuclidNorm();
  }

  // method
  /**
   * @return the position vector of this 2D point.
   */
  public Vector getPositionVector() {
    return Vector.withValue(getX(), getY());
  }

  // method
  /**
   * @return the x-coordinate of this 2D point.
   */
  public double getX() {
    return x;
  }

  // method
  /**
   * @return the x-coordinate of this 2D point as float.
   */
  public float getXAsFloat() {
    return (float) getX();
  }

  // method
  /**
   * @return the y-coordinate of this 2D point.
   */
  public double getY() {
    return y;
  }

  // method
  /**
   * @return the y-coordinate of this 3D point as float.
   */
  public float getYAsFloat() {
    return (float) getY();
  }

  // method
  /**
   * @return a new 3D point with the x-coordinate and the y-coordinate of this 2D
   *         point and a z-coordinate=0.
   */
  public Point3D to3DPoint() {
    return new Point3D(getX(), getY(), 0.0);
  }
}
