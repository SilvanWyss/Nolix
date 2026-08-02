/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.resourcecontrol.resourcepool;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.baseapi.resourcecontrol.closecontroller.Closeable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.resourcecontrol.resourcepool.ResourcePool;

/**
 * @author Silvan Wyss
 * @param <W> the type of the {@link AbstractWrapperResource}s of a
 *            {@link AbstractResourcePool}.
 * @param <R> the type of the resource of the {@link AbstractWrapperResource}s
 *            of a {@link AbstractResourcePool}.
 */
public abstract class AbstractResourcePool<W extends AbstractWrapperResource<W, R>, R extends GroupCloseable>
implements ResourcePool<W> {
  private final ICloseController closeController = CloseController.forElement(this);

  private final LinkedList<R> resources = LinkedList.createEmpty();

  private final LinkedList<R> availableResources = LinkedList.createEmpty();

  @Override
  public final synchronized W borrowResource() {
    final var resource = provideResource();

    return createWrapperResourceWithCurrentResourcePoolAndResource(resource);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final synchronized void noteClose() {
    resources.forEach(Closeable::close);
  }

  protected abstract R createResource();

  protected abstract W createWrapperResourceWithResource(R resource);

  final synchronized void internalTakeBackResource(final R resource) {
    if (resource.isOpen()) {
      availableResources.addAtEnd(resource);
    }
  }

  private W createWrapperResourceWithCurrentResourcePoolAndResource(final R resource) {
    final var wrapperResource = createWrapperResourceWithResource(resource);

    wrapperResource.internalSetParentResourcePool(this);

    return wrapperResource;
  }

  private R provideNewResource() {
    final var resource = createResource();

    resources.addAtEnd(resource);

    return resource;
  }

  private R provideResource() {
    if (availableResources.containsAny()) {
      return availableResources.removeAndGetStoredLast();
    }

    return provideNewResource();
  }
}
