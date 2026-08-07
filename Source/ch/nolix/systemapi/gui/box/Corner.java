/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.box;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum Corner {
  TOP_LEFT,
  TOP_RIGHT,
  BOTTOM_LEFT,
  BOTTOM_RIGHT;

  /**
   * @param specification
   * @return a {@link Corner} from the given specification
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Corner}.
   */
  public static Corner fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
