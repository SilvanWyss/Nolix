/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.clientserver;

import ch.nolix.base.net.clientserver.AbstractSession;

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
