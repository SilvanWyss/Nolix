/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.grid;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum GridType {
  INNER_LINES,
  INNER_AND_OUTER_LINES,
  NO_LINES;

  public static GridType fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
