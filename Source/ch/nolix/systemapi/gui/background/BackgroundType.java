/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.background;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum BackgroundType {
  COLOR,
  COLOR_GRADIENT,
  IMAGE,
  TRANSPARENCY;

  /**
   * @param specification
   * @return a new {@link BackgroundType} from the given specification
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link BackgroundType}.
   */
  public static BackgroundType fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
