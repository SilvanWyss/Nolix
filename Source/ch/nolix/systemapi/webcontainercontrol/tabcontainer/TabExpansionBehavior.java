package ch.nolix.systemapi.webcontainercontrol.tabcontainer;

import ch.nolix.coreapi.document.node.INode;

/**
 * A {@link TabExpansionBehavior} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2018-08-13
 */
public enum TabExpansionBehavior {
  OPEN_ONE_TAB_OR_NONE,
  OPEN_ONE_TAB,
  OPEN_SEVERAL_TABS_OR_NONE,
  OPEN_SEVERAL_TABS;

  /**
   * @param specification
   * @return a new {@link TabExpansionBehavior} from the given specification.
   * @throws RuntimeException if the given specification is not valid.
   */
  public static TabExpansionBehavior fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
