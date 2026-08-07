/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.sessionserver;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.sessionserver.BackendClientSessionManager;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * @author Silvan Wyss
 */
final class BackendClientSessionManagerTest extends StandardTest {
  @Test
  void testCase_forClient() {
    // setup
    final var mockBackendClient = new MockBackendClient();

    // execute
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    // verify
    expect(testUnit.getSessionStackSize()).isEqualTo(0);
    expect(testUnit.containsPreviousSession()).isFalse();
    expect(testUnit.containsCurrentSession()).isFalse();
    expect(testUnit.containsNextSession()).isFalse();
  }

  @Test
  void testCase_forClient_whenTheGivenClientIsNull() {
    // execute & verify
    expectRunning(() -> BackendClientSessionManager.forClient(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given parent client is null.");
  }

  @Test
  void testCase_popCurrentSession_whenDoesNotContainSession() {
    // setup
    final var mockBackendClient = new MockBackendClient();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    // execute & verify
    expectRunning(testUnit::popCurrentSession).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_popCurrentSession_whenContains1Session() {
    // setup
    final var mockBackendClient = new MockBackendClient();
    final var mockBackendClientSession = new MockBackendClientSession();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);
    testUnit.pushSession(mockBackendClientSession);

    // execute
    testUnit.popCurrentSession();

    // verify
    expect(testUnit.getSessionStackSize()).isEqualTo(0);
    expect(testUnit.containsPreviousSession()).isFalse();
    expect(testUnit.containsCurrentSession()).isFalse();
    expect(testUnit.containsNextSession()).isFalse();
  }

  @Test
  void testCase_pushSession_whenDoesNotContainSession() {
    // setup
    final var mockBackendClient = new MockBackendClient();
    final var mockBackendClientSession = new MockBackendClientSession();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);

    // execute
    testUnit.pushSession(mockBackendClientSession);

    // verify
    expect(testUnit.getSessionStackSize()).isEqualTo(1);
    expect(testUnit.containsPreviousSession()).isFalse();
    expect(testUnit.containsCurrentSession()).isTrue();
    expect(testUnit.containsNextSession()).isFalse();
    expect(testUnit.getStoredCurrentSession()).is(mockBackendClientSession);
  }

  @Test
  void testCase_pushSession_whenContains1Session() {
    // setup
    final var mockBackendClient = new MockBackendClient();
    final var mockBackendClientSession1 = new MockBackendClientSession();
    final var mockBackendClientSession2 = new MockBackendClientSession();
    final var testUnit = BackendClientSessionManager.forClient(mockBackendClient);
    testUnit.pushSession(mockBackendClientSession1);

    // execute
    testUnit.pushSession(mockBackendClientSession2);

    // verify
    expect(testUnit.getSessionStackSize()).isEqualTo(2);
    expect(testUnit.containsPreviousSession()).isTrue();
    expect(testUnit.containsCurrentSession()).isTrue();
    expect(testUnit.containsNextSession()).isFalse();
    expect(testUnit.getStoredCurrentSession()).is(mockBackendClientSession2);
  }
}
