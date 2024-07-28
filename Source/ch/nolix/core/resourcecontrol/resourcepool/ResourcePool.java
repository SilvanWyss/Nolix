//package declaration
package ch.nolix.core.resourcecontrol.resourcepool;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.Closeable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourcepoolapi.IResourcePool;

//class
public abstract class ResourcePool<WR extends WrapperResource<WR, R>, R extends GroupCloseable>
implements IResourcePool<WR> {

  //attribute
  private final ICloseController closeController = CloseController.forElement(this);

  //multi-attribute
  private final LinkedList<R> resources = LinkedList.createEmpty();

  //multi-attribute
  private final LinkedList<R> availableResources = LinkedList.createEmpty();

  //method
  @Override
  public final synchronized WR borrowResource() {

    final var resource = provideResource();

    return createWrapperResourceWithCurrentResourcePoolAndResource(resource);
  }

  //method
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public final synchronized void noteClose() {
    resources.forEach(Closeable::close);
  }

  //method declaration
  protected abstract R createResource();

  //method declaration
  protected abstract WR createWrapperResourceWithResource(R resource);

  //method
  final synchronized void internalTakeBackResource(final R resource) {
    if (resource.isOpen()) {
      availableResources.addAtEnd(resource);
    }
  }

  //method
  private WR createWrapperResourceWithCurrentResourcePoolAndResource(final R resource) {

    final var wrapperResource = createWrapperResourceWithResource(resource);

    wrapperResource.internalSetParentResourcePool(this);

    return wrapperResource;
  }

  //method
  private R provideNewResource() {

    final var resource = createResource();

    resources.addAtEnd(resource);

    return resource;
  }

  //method
  private R provideResource() {

    if (availableResources.containsAny()) {
      return availableResources.removeAndGetStoredLast();
    }

    return provideNewResource();
  }
}
