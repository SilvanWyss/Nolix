/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.resourcecontrol.resourcepool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.resourcecontrol.closecontroller.CloseStateRequestable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;

public abstract class AbstractWrapperResource<W extends AbstractWrapperResource<W, R>, R extends GroupCloseable>
implements GroupCloseable {
  private AbstractResourcePool<W, R> memberParentResourcePool;

  private final R resource;

  private final ICloseController closeController = CloseController.forElement(this);

  protected AbstractWrapperResource(final R resource) {
    Validator.assertThat(resource).fulfills(CloseStateRequestable::isOpen);

    this.resource = resource;
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
  public final void noteClose() {
    memberParentResourcePool.internalTakeBackResource(resource);
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

    memberParentResourcePool = parentResourcePool;
  }

  private void assertDoesNotBelongToResourcePool() {
    if (belongsToResourcePool()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, memberParentResourcePool);
    }
  }

  private boolean belongsToResourcePool() {
    return (memberParentResourcePool != null);
  }
}
