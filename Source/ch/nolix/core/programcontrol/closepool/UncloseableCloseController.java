package ch.nolix.core.programcontrol.closepool;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.IClosePool;

public final class UncloseableCloseController implements ICloseController {

  private static final UncloseableClosePool UNCLOSEABLE_CLOSE_POOL = new UncloseableClosePool();

  @Override
  public void close() {
    //Does nothing.
  }

  @Override
  public void createCloseDependencyTo(final GroupCloseable element) {
    //Does nothing.
  }

  @Override
  public IClosePool getParentClosePool() {
    return UNCLOSEABLE_CLOSE_POOL;
  }

  @Override
  public boolean hasClosed() {
    return false;
  }

  @Override
  public void setParentClosePool(final IClosePool parentClosePool) {
    //Does nothing.
  }
}
