/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.resourcecontrol.resourcepool;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.baseapi.resourcecontrol.closecontroller.CloseStateRequestable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;

/**
 * @author Silvan Wyss
 * @param <W> is the type of a {@link AbstractWrapperResource}.
 * @param <R> is the type of the resource of a {@link AbstractWrapperResource}.
 */
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
