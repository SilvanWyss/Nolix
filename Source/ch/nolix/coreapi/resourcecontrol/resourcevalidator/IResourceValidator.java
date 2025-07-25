package ch.nolix.coreapi.resourcecontrol.resourcevalidator;

import ch.nolix.coreapi.resourcecontrol.resourceclosing.CloseStateRequestable;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
 */
public interface IResourceValidator {

  /**
   * @param resource
   * @throws RuntimeException if the given resource is null or closed.
   */
  void assertIsOpen(CloseStateRequestable resource);
}
