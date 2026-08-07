/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.box;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum HorizontalContentAlignment {
  LEFT,
  CENTER,
  RIGHT;

  public static HorizontalContentAlignment fromSpecification(final Node<?> specification) {
    final var horizontalContentAlignmentString = specification.getSingleChildNodeHeader();

    return valueOf(horizontalContentAlignmentString);
  }
}
