/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.executoranddataproviderserver;

import ch.nolix.baseapi.net.executoranddataproviderserver.Slot;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
public final class LocalServer extends AbstractServer {
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultSlot(final Slot defaultSlot) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final Slot slot) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final Slot slot) {
    // Does nothing.
  }
}
