package ch.nolix.systemapi.gui.box;

import ch.nolix.coreapi.document.node.INode;

/**
 * A {@link Direction} defines the horizontal, vertical or diagonals options in
 * a box.
 * 
 * @author Silvan Wyss
 * @version 2017-09-16
 */
public enum Direction {
  HORIZONTAL,
  VERTICAL,
  DIAGONAL_LEFT_UP,
  DIAGONAL_LEFT_DOWN;

  /**
   * @param specification
   * @return a {@link Direction} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Direction}.
   */
  public static Direction fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
