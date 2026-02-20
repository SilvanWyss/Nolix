/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.endpoint3;

import ch.nolix.baseapi.net.endpoint3.ISlot;
import ch.nolix.baseapi.net.securityproperty.SecurityMode;

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
  protected void noteAddedDefaultSlot(final ISlot defaultSlot) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final ISlot slot) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final ISlot slot) {
    //Does nothing.
  }
}
