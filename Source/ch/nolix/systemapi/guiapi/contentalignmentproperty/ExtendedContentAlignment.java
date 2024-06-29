//package declaration
package ch.nolix.systemapi.guiapi.contentalignmentproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * @author Silvan Wyss
 * @version 2019-05-18
 */
public enum ExtendedContentAlignment {
  TOP_LEFT,
  TOP,
  TOP_RIGHT,
  LEFT,
  CENTER,
  RIGHT,
  BOTTOM_LEFT,
  BOTTOM,
  BOTTOM_RIGHT,
  FREE;

  //static method
  /**
   * @param specification
   * @return a new {@link ExtendedContentAlignment} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link ExtendedContentAlignment}.
   */
  public static ExtendedContentAlignment fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
