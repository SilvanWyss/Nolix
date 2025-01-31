package ch.nolix.system.application.main;

public final class MockBackendClientSession extends Session<MockBackendClient, Object> {

  @Override
  public void refresh() {
    //Does nothing.
  }

  @Override
  protected void fullInitialize() {
    //Does nothing.
  }

  @Override
  protected Class<?> getClientClass() {
    return MockBackendClient.class;
  }
}
