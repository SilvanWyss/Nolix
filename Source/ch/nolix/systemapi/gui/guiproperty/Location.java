/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.guiproperty;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum Location {
  INSIDE,
  OUTSIDE;

  /**
   * @param specification
   * @return a {@link Location} from the given specification
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Location}.
   */
  public static Location fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
