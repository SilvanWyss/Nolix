/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.resourcecontrol.resourcevalidator;

import ch.nolix.baseapi.resourcecontrol.resourcerequest.OpennessRequestable;

/**
 * @author Silvan Wyss
 */
public interface IResourceValidator {
  /**
   * @param resource
   * @throws RuntimeException if the given resource is null or closed
   */
  void assertIsOpen(OpennessRequestable resource);
}
