/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.font;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum LineDecoration {
  UNDERLINE,
  MIDLINE,
  OVERLINE;

  /**
   * @param specification
   * @return a new {@link LineDecoration} from the given specification
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link LineDecoration}.
   */
  public static LineDecoration fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
