package ch.nolix.systemapi.webguiapi.atomiccontrolapi.buttonapi;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 * @version 2018-05-18
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
   * @return a new {@link ButtonRole} from the given specification.
   * @throws RuntimeException if the given specification does nor represent
   *                          {@link ButtonRole}.
   */
  public static ButtonRole fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
