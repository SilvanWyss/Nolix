package ch.nolix.systemapi.gui.box;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 * @version 2025-08-09
 */
public enum Corner {
  TOP_LEFT,
  TOP_RIGHT,
  BOTTOM_LEFT,
  BOTTOM_RIGHT;

  /**
   * @param specification
   * @return a {@link Corner} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Corner}.
   */
  public static Corner fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
