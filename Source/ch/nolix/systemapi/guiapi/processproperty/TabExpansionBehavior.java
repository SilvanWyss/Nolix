//package declaration
package ch.nolix.systemapi.guiapi.processproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * A {@link TabExpansionBehavior} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2018-08-13
 */
public enum TabExpansionBehavior {
  OPEN_ONE_TAB_OR_NONE,
  OPEN_ONE_TAB,
  OPEN_SEVERAL_TABS_OR_NONE,
  OPEN_SEVERAL_TABS;

  // static method
  /**
   * @param specification
   * @return a new {@link TabExpansionBehavior} from the given specification.
   * @throws RuntimeException if the given specification is not valid.
   */
  public static TabExpansionBehavior fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
