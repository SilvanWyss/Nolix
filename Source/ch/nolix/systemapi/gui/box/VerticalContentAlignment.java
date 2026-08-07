/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.box;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum VerticalContentAlignment {
  TOP,
  CENTER,
  BOTTOM;

  public static VerticalContentAlignment fromSpecification(final Node<?> specification) {
    final var verticalContentAlignmentString = specification.getSingleChildNodeHeader();

    return valueOf(verticalContentAlignmentString);
  }
}
