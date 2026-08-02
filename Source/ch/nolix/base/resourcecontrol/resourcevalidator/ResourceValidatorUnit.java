/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.resourcecontrol.resourcevalidator;

import ch.nolix.baseapi.resourcecontrol.resourcerequest.OpennessRequestable;
import ch.nolix.baseapi.resourcecontrol.resourcevalidator.IResourceValidator;

/**
 * @author Silvan Wyss
 */
public class ResourceValidatorUnit implements IResourceValidator {
  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsOpen(final OpennessRequestable resource) {
    ResourceValidator.assertIsOpen(resource);
  }
}
