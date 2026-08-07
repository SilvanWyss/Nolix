/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.sessionserver;

import ch.nolix.base.net.sessionserver.AbstractSession;

/**
 * @author Silvan Wyss
 */
public final class MockBackendClientSession extends AbstractSession<MockBackendClient, Object> {
  /**
   * {@inheritDoc}
   */
  @Override
  public void refresh() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fullInitialize() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?> getClientClass() {
    return MockBackendClient.class;
  }
}
