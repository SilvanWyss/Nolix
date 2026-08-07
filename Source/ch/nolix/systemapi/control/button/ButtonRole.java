/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.button;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum ButtonRole {
  ACTION_BUTTON,
  LINK_BUTTON,
  CREATE_BUTTON,
  DELETE_BUTTON,
  SAVE_BUTTON,
  CONFIRM_BUTTON,
  CANCEL_BUTTON;

  /**
   * @param specification
   * @return a new {@link ButtonRole} from the given specification
   * @throws RuntimeException if the given specification does nor represent
   *                          {@link ButtonRole}.
   */
  public static ButtonRole fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
