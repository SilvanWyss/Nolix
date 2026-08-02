/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.resourcecontrol.closecontroller;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.IClosePool;
import ch.nolix.baseapi.state.stateproperty.Openness;

/**
 * @author Silvan Wyss
 */
public final class UncloseableClosePool implements IClosePool {
  /**
   * {@inheritDoc}
   */
  @Override
  public void addElements(final ExtendedIterable<GroupCloseable> elements) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void closeElementsIfStateIsOpen() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Openness getState() {
    return Openness.OPEN;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<GroupCloseable> getStoredElements() {
    return ImmutableList.createEmpty();
  }
}
