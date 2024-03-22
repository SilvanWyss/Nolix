//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.application.main.BackendClientSessionManager;

//class
public final class BackendClientSessionManagerTest extends StandardTest {

  //method
  @Test
  public void testCase_forClient() {

    //setup
    final var mockBackendClient = new MockBackendClient();

    //execution
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    //verification
    expect(testUnit.getSessionStackSize()).isEqualTo(0);
    expectNot(testUnit.containsPreviousSession());
    expectNot(testUnit.containsCurrentSession());
    expectNot(testUnit.containsNextSession());
  }

  //method
  @Test
  public void testCase_forClient_whenTheGivenClientIsNull() {

    //execution & verification
    expectRunning(() -> BackendClientSessionManager.forClient(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given parent client is null.");
  }

  //method
  @Test
  public void testCase_popCurrentSession_whenDoesNotContainSession() {

    //setup
    final var mockBackendClient = new MockBackendClient();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    //execution & verification
    expectRunning(testUnit::popCurrentSession).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  public void testCase_popCurrentSession_whenContains1Session() {

    //setup
    final var mockBackendClient = new MockBackendClient();
    final var mockBackendClientSession = new MockBackendClientSession();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);
    testUnit.pushSession(mockBackendClientSession);

    //execution
    testUnit.popCurrentSession();

    //verification
    expect(testUnit.getSessionStackSize()).isEqualTo(0);
    expectNot(testUnit.containsPreviousSession());
    expectNot(testUnit.containsCurrentSession());
    expectNot(testUnit.containsNextSession());
  }

  //method
  @Test
  public void testCase_pushSession_whenDoesNotContainSession() {

    //setup
    final var mockBackendClient = new MockBackendClient();
    final var mockBackendClientSession = new MockBackendClientSession();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    //execution
    testUnit.pushSession(mockBackendClientSession);

    //verification
    expect(testUnit.getSessionStackSize()).isEqualTo(1);
    expectNot(testUnit.containsPreviousSession());
    expect(testUnit.containsCurrentSession());
    expectNot(testUnit.containsNextSession());
    expect(testUnit.getStoredCurrentSession()).is(mockBackendClientSession);
  }

  //method
  @Test
  public void testCase_pushSession_whenContains1Session() {

    //setup
    final var mockBackendClient = new MockBackendClient();
    final var mockBackendClientSession1 = new MockBackendClientSession();
    final var mockBackendClientSession2 = new MockBackendClientSession();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);
    testUnit.pushSession(mockBackendClientSession1);

    //execution
    testUnit.pushSession(mockBackendClientSession2);

    //verification
    expect(testUnit.getSessionStackSize()).isEqualTo(2);
    expect(testUnit.containsPreviousSession());
    expect(testUnit.containsCurrentSession());
    expectNot(testUnit.containsNextSession());
    expect(testUnit.getStoredCurrentSession()).is(mockBackendClientSession2);
  }
}
