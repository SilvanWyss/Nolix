package ch.nolix.core.resourcecontrol.resourcevalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
 * @param <R> is the type of the {@link CloseStateRequestable}s a
 *            {@link IResourceValidator} can handle.
 */
public final class ResourceValidator<R extends CloseStateRequestable> implements IResourceValidator<R> {

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertIsOpen(final R resource) {
    if (resource.isClosed()) {
      throw ClosedArgumentException.forArgument(resource);
    }
  }
}
