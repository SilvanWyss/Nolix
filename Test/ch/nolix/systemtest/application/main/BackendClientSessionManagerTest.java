package ch.nolix.systemtest.application.main;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.application.main.BackendClientSessionManager;

final class BackendClientSessionManagerTest extends StandardTest {

  @Test
  void testCase_forClient() {

    //setup
    final var mockBackendClient = new MockBackendClient();

    //execution
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    //verification
    expect(testUnit.getSessionStackSize()).isEqualTo(0);
    expect(testUnit.containsPreviousSession()).isFalse();
    expect(testUnit.containsCurrentSession()).isFalse();
    expect(testUnit.containsNextSession()).isFalse();
  }

  @Test
  void testCase_forClient_whenTheGivenClientIsNull() {

    //execution & verification
    expectRunning(() -> BackendClientSessionManager.forClient(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given parent client is null.");
  }

  @Test
  void testCase_popCurrentSession_whenDoesNotContainSession() {

    //setup
    final var mockBackendClient = new MockBackendClient();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    //execution & verification
    expectRunning(testUnit::popCurrentSession).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_popCurrentSession_whenContains1Session() {

    //setup
    final var mockBackendClient = new MockBackendClient();
    final var mockBackendClientSession = new MockBackendClientSession();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);
    testUnit.pushSession(mockBackendClientSession);

    //execution
    testUnit.popCurrentSession();

    //verification
    expect(testUnit.getSessionStackSize()).isEqualTo(0);
    expect(testUnit.containsPreviousSession()).isFalse();
    expect(testUnit.containsCurrentSession()).isFalse();
    expect(testUnit.containsNextSession()).isFalse();
  }

  @Test
  void testCase_pushSession_whenDoesNotContainSession() {

    //setup
    final var mockBackendClient = new MockBackendClient();
    final var mockBackendClientSession = new MockBackendClientSession();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    //execution
    testUnit.pushSession(mockBackendClientSession);

    //verification
    expect(testUnit.getSessionStackSize()).isEqualTo(1);
    expect(testUnit.containsPreviousSession()).isFalse();
    expect(testUnit.containsCurrentSession()).isTrue();
    expect(testUnit.containsNextSession()).isFalse();
    expect(testUnit.getStoredCurrentSession()).is(mockBackendClientSession);
  }

  @Test
  void testCase_pushSession_whenContains1Session() {

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
    expect(testUnit.containsPreviousSession()).isTrue();
    expect(testUnit.containsCurrentSession()).isTrue();
    expect(testUnit.containsNextSession()).isFalse();
    expect(testUnit.getStoredCurrentSession()).is(mockBackendClientSession2);
  }
}
