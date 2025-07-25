package ch.nolix.systemapi.graphic.imageproperty;

import ch.nolix.coreapi.document.node.INode;

/**
 * A {@link Alignment} defines the way between two points of a
 * rectangle. A {@link Alignment} does not (!) depend on the order of
 * the start point and end point.
 * 
 * @author Silvan Wyss
 * @version 2017-09-16
 */
public enum Alignment {
  HORIZONTAL,
  VERTICAL,
  DIAGONAL_LEFT_UP,
  DIAGONAL_LEFT_DOWN;

  /**
   * @param specification
   * @return a new {@link Alignment} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Alignment}.
   */
  public static Alignment fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
