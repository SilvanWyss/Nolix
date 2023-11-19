//package declaration
package ch.nolix.systemapi.guiapi.contentalignmentproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public enum ContentAlignment {
  TOP_LEFT,
  TOP,
  TOP_RIGHT,
  LEFT,
  CENTER,
  RIGHT,
  BOTTOM_LEFT,
  BOTTOM,
  BOTTOM_RIGHT;

  //static method
  /**
   * @param specification
   * @return a new {@link ContentAlignment} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link ContentAlignment}.
   */
  public static ContentAlignment fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
