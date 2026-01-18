package ch.nolix.core.resourcecontrol.closecontroller;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programcontrol.processproperty.CloseState;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.IClosePool;

/**
 * @author Silvan Wyss
 */
public final class UncloseableClosePool implements IClosePool {
  @Override
  public void addElements(final IContainer<GroupCloseable> elements) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void closeElementsIfStateIsOpen() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CloseState getState() {
    return CloseState.OPEN;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<GroupCloseable> getStoredElements() {
    return ImmutableList.createEmpty();
  }
}
