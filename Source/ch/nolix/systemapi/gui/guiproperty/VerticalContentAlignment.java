/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.guiproperty;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum VerticalContentAlignment {
  TOP,
  CENTER,
  BOTTOM;

  /**
   * @param specification
   * @return the {@link VerticalContentAlignment} the given specification
   *         represents
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link VerticalContentAlignment}
   */
  public static VerticalContentAlignment fromSpecification(final Node<?> specification) {
    final var verticalContentAlignmentString = specification.getSingleChildNodeHeader();

    return valueOf(verticalContentAlignmentString);
  }
}
