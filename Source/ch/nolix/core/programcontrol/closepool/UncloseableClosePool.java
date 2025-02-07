package ch.nolix.core.programcontrol.closepool;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.processproperty.CloseState;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.IClosePool;

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
