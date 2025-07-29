package ch.nolix.core.resourcecontrol.resourcevalidator;

import ch.nolix.coreapi.resourcecontrol.closecontroller.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrol.resourcevalidator.IResourceValidator;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
 */
public class ResourceValidatorUnit implements IResourceValidator {

  /**
   * {@inheritDoc}
   */
  @Override
  public final void assertIsOpen(final CloseStateRequestable resource) {
    ResourceValidator.assertIsOpen(resource);
  }
}
