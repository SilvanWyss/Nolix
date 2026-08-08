/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

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
  public void noteClose() {
    // Does nothing.
  }
}
