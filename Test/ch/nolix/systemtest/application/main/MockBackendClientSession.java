package ch.nolix.systemtest.application.main;

import ch.nolix.system.application.main.AbstractSession;

/**
 * @author Silvan Wyss
 */
public final class MockBackendClientSession extends AbstractSession<MockBackendClient, Object> {
  @Override
  public void refresh() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fullInitialize() {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<?> getClientClass() {
    return MockBackendClient.class;
  }
}
