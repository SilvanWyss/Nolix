//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//own imports
import ch.nolix.system.application.main.Session;

//class
public final class MockBackendClientSession extends Session<MockBackendClient, Object> {

  //method
  @Override
  public void refresh() {
    //Does nothing.
  }

  //method
  @Override
  protected void fullInitialize() {
    //Does nothing.
  }

  //method
  @Override
  protected Class<?> getClientClass() {
    return MockBackendClient.class;
  }
}
