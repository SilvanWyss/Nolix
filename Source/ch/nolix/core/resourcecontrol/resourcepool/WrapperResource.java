package ch.nolix.core.resourcecontrol.resourcepool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;

public abstract class WrapperResource<W extends WrapperResource<W, R>, R extends GroupCloseable>
implements GroupCloseable {

  private ResourcePool<W, R> parentResourcePool;

  private final R resource;

  private final ICloseController closeController = CloseController.forElement(this);

  protected WrapperResource(final R resource) {

    Validator.assertThat(resource).fulfills(CloseStateRequestable::isOpen);

    this.resource = resource;
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final void noteClose() {
    parentResourcePool.internalTakeBackResource(resource);
  }

  protected final R getStoredResource() {
    return resource;
  }

  final void internalSetParentResourcePool(final ResourcePool<W, R> parentResourcePool) {

    Validator
      .assertThat(parentResourcePool)
      .thatIsNamed("parent resource pool")
      .fulfills(CloseStateRequestable::isOpen);

    assertDoesNotBelongToResourcePool();

    this.parentResourcePool = parentResourcePool;
  }

  private void assertDoesNotBelongToResourcePool() {
    if (belongsToResourcePool()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, parentResourcePool);
    }
  }

  private boolean belongsToResourcePool() {
    return (parentResourcePool != null);
  }
}
