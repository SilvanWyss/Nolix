//package declaration
package ch.nolix.systemapi.guiapi.structureproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * A {@link DirectionInRectangle} defines the way between two points of a
 * rectangle. A {@link DirectionInRectangle} does not (!) depend on the order of
 * the start point and end point.
 * 
 * @author Silvan Wyss
 * @date 2017-09-16
 */
public enum DirectionInRectangle {
  HORIZONTAL,
  VERTICAL,
  DIAGONAL_UP,
  DIAGONAL_DOWN;

  // method
  /**
   * @param specification
   * @return a new {@link DirectionInRectangle} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link DirectionInRectangle}.
   */
  public static DirectionInRectangle fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
