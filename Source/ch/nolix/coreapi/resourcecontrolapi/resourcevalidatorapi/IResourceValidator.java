package ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;

/**
 * @author Silvan Wyss
 * @version 2024-12-19
 * @param <R> is the type of the {@link CloseStateRequestable}s a
 *            {@link IResourceValidator} can handle.
 */
public interface IResourceValidator<R extends CloseStateRequestable> {

  /**
   * @param resource
   * @throws RuntimeException if the given resource is null.
   * @throws RuntimeException if the given resource is closed.
   */
  void assertIsOpen(R resource);
}
