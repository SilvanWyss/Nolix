/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.textbox;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum TextMode {
  NORMAL,
  SECRET;

  /**
   * @param specification
   * @return the {@link TextMode} the given specification represents
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link TextMode}
   */
  public static TextMode fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
