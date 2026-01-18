/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.resourcecontrol.resourcevalidator;

import ch.nolix.coreapi.resourcecontrol.closecontroller.CloseStateRequestable;

/**
 * @author Silvan Wyss
 */
public interface IResourceValidator {
  /**
   * @param resource
   * @throws RuntimeException if the given resource is null or closed.
   */
  void assertIsOpen(CloseStateRequestable resource);
}
