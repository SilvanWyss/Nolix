/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.box;

import ch.nolix.baseapi.document.node.Node;

/**
 * A {@link Direction} defines the horizontal, vertical or diagonals options in
 * a box.
 * 
 * @author Silvan Wyss
 */
public enum Direction {
  HORIZONTAL,
  VERTICAL,
  DIAGONAL_LEFT_UP,
  DIAGONAL_LEFT_DOWN;

  /**
   * @param specification
   * @return a {@link Direction} from the given specification
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Direction}.
   */
  public static Direction fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
