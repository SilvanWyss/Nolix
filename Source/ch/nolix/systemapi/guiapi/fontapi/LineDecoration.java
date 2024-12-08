package ch.nolix.systemapi.guiapi.fontapi;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

/**
 * @author Silvan Wyss
 * @version 2024-12-08
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
