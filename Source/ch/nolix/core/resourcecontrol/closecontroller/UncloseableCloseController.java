/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.resourcecontrol.closecontroller;

import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.coreapi.resourcecontrol.closecontroller.IClosePool;

/**
 * @author Silvan Wyss
 */
public final class UncloseableCloseController implements ICloseController {
  private static final UncloseableClosePool UNCLOSEABLE_CLOSE_POOL = new UncloseableClosePool();

  @Override
  public void close() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createCloseDependencyTo(final GroupCloseable element) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IClosePool getParentClosePool() {
    return UNCLOSEABLE_CLOSE_POOL;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasClosed() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setParentClosePool(final IClosePool parentClosePool) {
    //Does nothing.
  }
}
