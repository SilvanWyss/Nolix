//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.application.main.ClientSessionManager;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class ClientSessionManagerTest extends Test {

  //method
  @TestCase
  public void testCase_constructor() {

    //setup
    final var client = new WebClient<>(); //TODO: Create MockClient

    //execution
    final var testUnit = new ClientSessionManager<>(client);

    //verification
    expect(testUnit.getSessionStackSize()).isEqualTo(0);
    expectNot(testUnit.containsPreviousSession());
    expectNot(testUnit.containsCurrentSession());
    expectNot(testUnit.containsNextSession());
  }
}
