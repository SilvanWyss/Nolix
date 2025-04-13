package ch.nolix.core.resourcecontrol.resourcevalidator;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;

/**
 * @author Silvan Wyss
 * @version 2025-04-13
 */
public final class ResourceValidator {

  private static final IResourceValidator RESOURCE_VALIDATOR_UNIT = new ResourceValidatorUnit();

  private ResourceValidator() {
  }

  /**
   * @param resource
   * @throws RuntimeException if the given resource is null or closed.
   */
  public static void assertIsOpen(final CloseStateRequestable resource) {
    RESOURCE_VALIDATOR_UNIT.assertIsOpen(resource);
  }
}
