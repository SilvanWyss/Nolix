/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.resourcecontrol.resourcepool;

import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * A {@link ResourcePool} manages resources that are {@link AutoCloseable}s.
 * 
 * @author Silvan Wyss
 * @param <R> the type of the resources of a {@link ResourcePool}.
 */
public interface ResourcePool<R extends AutoCloseable> extends GroupCloseable {
  /**
   * @return resource from the current {@link ResourcePool} which will be
   *         borrowed. When The resource is closed its internal resource will be
   *         given back to the current {@link ResourcePool} and will be available
   *         again.
   */
  R borrowResource();
}
