/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.container;

import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 */
public enum ContainerRole {
  DIALOG_CONTAINER,
  OVERALL_CONTAINER,
  MAIN_CONTENT_CONTAINER,
  HEADER_CONTAINER,
  FOOTER_CONTAINER,
  COMPONENT_CONTAINER,
  TITLE_CONTAINER;

  /**
   * @param specification
   * @return a new {@link ContainerRole} from the given specification
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link ContainerRole}.
   */
  public static ContainerRole fromSpecification(final Node<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
