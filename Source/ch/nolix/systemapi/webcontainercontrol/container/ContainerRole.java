package ch.nolix.systemapi.webcontainercontrol.container;

import ch.nolix.coreapi.document.node.INode;

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
   * @return a new {@link ContainerRole} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link ContainerRole}.
   */
  public static ContainerRole fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
