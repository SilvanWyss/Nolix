package ch.nolix.core.resourcecontrol.resourcepool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.resourcecontrol.closecontroller.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;

public abstract class AbstractWrapperResource<W extends AbstractWrapperResource<W, R>, R extends GroupCloseable>
implements GroupCloseable {
  private AbstractResourcePool<W, R> parentResourcePool;

  private final R resource;

  private final ICloseController closeController = CloseController.forElement(this);

  protected AbstractWrapperResource(final R resource) {
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

  final void internalSetParentResourcePool(final AbstractResourcePool<W, R> parentResourcePool) {
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
