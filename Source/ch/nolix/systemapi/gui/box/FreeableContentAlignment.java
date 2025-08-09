package ch.nolix.systemapi.gui.box;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 * @version 2019-05-18
 */
public enum FreeableContentAlignment {
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

  /**
   * @param specification
   * @return a {@link FreeableContentAlignment} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link FreeableContentAlignment}.
   */
  public static FreeableContentAlignment fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
