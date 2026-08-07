/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.box;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
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
   * @return a {@link FreeableContentAlignment} from the given specification
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link FreeableContentAlignment}.
   */
  public static FreeableContentAlignment fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
