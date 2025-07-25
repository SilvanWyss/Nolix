package ch.nolix.coreapi.resourcecontrol.resourcepool;

import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;

/**
 * A {@link IResourcePool} manages resources that are {@link AutoCloseable}s.
 * 
 * @author Silvan Wyss
 * @version 2024-01-24
 * @param <R> is the type of the resources of a {@link IResourcePool}.
 */
public interface IResourcePool<R extends AutoCloseable> extends GroupCloseable {

  /**
   * @return resource from the current {@link IResourcePool} which will be
   *         borrowed. When The resource is closed its internal resource will be
   *         given back to the current {@link IResourcePool} and will be available
   *         again.
   */
  R borrowResource();
}
