package ch.nolix.core.resourcecontrol.resourceclosing;

import ch.nolix.core.errorcontrol.logging.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.Closeable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.IResourceManager;
import ch.nolix.coreapi.resourcecontrolapi.resourcepoolapi.IResourcePool;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;

/**
 * @author Silvan Wyss
 * @version 2025-01-04
 * @param <R> is the type of the resource of a {@link IResourceManager}.
 */
public final class ResourceManager<R extends AutoCloseable> implements IResourceManager<R> {

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidator();

  private final ICloseController closeController = CloseController.forElement(this);

  private final boolean needsToCloseResourceTransitively;

  private final R resource;

  /**
   * Creates a new {@link ResourceManager} for the given resource.
   * 
   * @param resource
   * @param resourceAsCloseable
   * @throws RuntimeException if the given resource is null or closed.
   * @throws RuntimeException if the given resource is not the given
   *                          resourceAsCloseable.
   */
  private ResourceManager(final R resource, final Closeable resourceAsCloseable) {

    RESOURCE_VALIDATOR.assertIsOpen(resourceAsCloseable);
    GlobalValidator.assertThat(resource).is(resourceAsCloseable);

    this.needsToCloseResourceTransitively = false;
    this.resource = resource;
  }

  /**
   * Creates a new {@link ResourceManager} for a resource from the given
   * resourcePool.
   * 
   * @param resourcePool
   * @throws RuntimeException if the given resourcePool is null.
   */
  private ResourceManager(final IResourcePool<? extends R> resourcePool) {
    needsToCloseResourceTransitively = true;
    resource = resourcePool.borrowResource();
  }

  /**
   * @param resource
   * @param <R2>     is the type of the given resource.
   * @return a new {@link ResourceManager} for the given resource.
   * @throws RuntimeException if the given resource is null or closed.
   */
  public static <R2 extends Closeable> ResourceManager<R2> forResource(final R2 resource) {
    return new ResourceManager<>(resource, resource);
  }

  /**
   * @param resourcePool
   * @param <R2>         is the type of the resources of the given resourcePool.
   * @return a new {@link ResourceManager} for a resource from the given
   *         resourcePool.
   * @throws RuntimeException if the given resourcePool is null.
   */
  public static <R2 extends AutoCloseable> ResourceManager<R2> forResourceFromResourcePool(
    final IResourcePool<? extends R2> resourcePool) {
    return new ResourceManager<>(resourcePool);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    if (needsToCloseResourceTransitively) {
      try {
        resource.close();
      } catch (final Throwable exception) { //NOSONAR: All Throwables must be caught.
        GlobalLogger.logError(exception);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public R get() {
    return resource;
  }
}
