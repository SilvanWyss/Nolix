package ch.nolix.core.resourcecontrol.resourcevalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
 */
public final class ResourceValidator implements IResourceValidator {

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsOpen(final CloseStateRequestable resource) {
    if (resource.isClosed()) {
      throw ClosedArgumentException.forArgument(resource);
    }
  }
}
