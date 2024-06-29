//package declaration
package ch.nolix.systemapi.guiapi.canvasapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * A {@link DirectionInCanvas} defines the way between two points of a
 * rectangle. A {@link DirectionInCanvas} does not (!) depend on the order of
 * the start point and end point.
 * 
 * @author Silvan Wyss
 * @version 2017-09-16
 */
public enum DirectionInCanvas {
  HORIZONTAL,
  VERTICAL,
  DIAGONAL_UP,
  DIAGONAL_DOWN;

  //method
  /**
   * @param specification
   * @return a new {@link DirectionInCanvas} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link DirectionInCanvas}.
   */
  public static DirectionInCanvas fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
