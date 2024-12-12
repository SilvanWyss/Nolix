package ch.nolix.core.resourcecontrol.resourcepool;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.Closeable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourcepoolapi.IResourcePool;

public abstract class ResourcePool<W extends WrapperResource<W, R>, R extends GroupCloseable>
implements IResourcePool<W> {

  private final ICloseController closeController = CloseController.forElement(this);

  private final LinkedList<R> resources = LinkedList.createEmpty();

  private final LinkedList<R> availableResources = LinkedList.createEmpty();

  @Override
  public final synchronized W borrowResource() {

    final var resource = provideResource();

    return createWrapperResourceWithCurrentResourcePoolAndResource(resource);
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

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
