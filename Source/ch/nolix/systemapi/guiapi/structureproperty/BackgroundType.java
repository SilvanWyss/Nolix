//package declaration
package ch.nolix.systemapi.guiapi.structureproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * @author Silvan Wyss
 * @date 2022-07-15
 */
public enum BackgroundType {
  COLOR,
  COLOR_GRADIENT,
  IMAGE,
  TRANSPARENCY;

  //static method
  /**
   * @param specification
   * @return a new {@link BackgroundType} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link BackgroundType}.
   */
  public static BackgroundType fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
