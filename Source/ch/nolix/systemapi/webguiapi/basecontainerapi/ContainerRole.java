//package declaration
package ch.nolix.systemapi.webguiapi.basecontainerapi;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public enum ContainerRole {
  DIALOG_CONTAINER,
  OVERALL_CONTAINER,
  MAIN_CONTENT_CONTAINER,
  HEADER_CONTAINER,
  FOOTER_CONTAINER,
  COMPONENT_CONTAINER,
  TITLE_CONTAINER;

  //static method
  /**
   * @param specification
   * @return a new {@link ContainerRole} from the given specification.
   * @throws InvalidArgumentException if the given specification does not
   *                                  represent a {@link ContainerRole}.
   */
  public static ContainerRole fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }
}
