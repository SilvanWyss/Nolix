package ch.nolix.systemapi.gui.font;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public enum LineDecoration {
  UNDERLINE,
  MIDLINE,
  OVERLINE;

  /**
   * @param specification
   * @return a new {@link LineDecoration} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link LineDecoration}.
   */
  public static LineDecoration fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
