package ch.nolix.core.resourcecontrol.closecontroller;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programcontrol.processproperty.CloseState;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.IClosePool;

public final class UncloseableClosePool implements IClosePool {

  @Override
  public void addElements(final IContainer<GroupCloseable> elements) {
    //Does nothing.
  }

  @Override
  public void closeElementsIfStateIsOpen() {
    //Does nothing.
  }

  @Override
  public CloseState getState() {
    return CloseState.OPEN;
  }

  @Override
  public IContainer<GroupCloseable> getStoredElements() {
    return ImmutableList.createEmpty();
  }
}
