//package declaration
package ch.nolix.core.resourcecontrol.resourcepool;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.functionapi.requestapi.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;

//class
public abstract class WrapperResource<WR extends WrapperResource<WR, R>, R extends GroupCloseable>
implements GroupCloseable {

  //attribute
  private ResourcePool<WR, R> parentResourcePool;

  //attribute
  private final R resource;

  //attribute
  private final ICloseController closeController = CloseController.forElement(this);

  //constructor
  protected WrapperResource(final R resource) {

    GlobalValidator.assertThat(resource).fulfills(CloseStateRequestable::isOpen);

    this.resource = resource;
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public final void noteClose() {
    parentResourcePool.internalTakeBackResource(resource);
  }

  //method
  protected final R getStoredResource() {
    return resource;
  }

  //method
  final void internalSetParentResourcePool(final ResourcePool<WR, R> parentResourcePool) {

    GlobalValidator
      .assertThat(parentResourcePool)
      .thatIsNamed("parent resource pool")
      .fulfills(CloseStateRequestable::isOpen);

    assertDoesNotBelongToResourcePool();

    this.parentResourcePool = parentResourcePool;
  }

  //method
  private void assertDoesNotBelongToResourcePool() {
    if (belongsToResourcePool()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, parentResourcePool);
    }
  }

  //method
  private boolean belongsToResourcePool() {
    return (parentResourcePool != null);
  }
}
