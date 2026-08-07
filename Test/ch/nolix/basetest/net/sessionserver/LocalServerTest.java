/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.sessionserver;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.sessionserver.Application;
import ch.nolix.base.net.sessionserver.LocalServer;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
final class LocalServerTest extends StandardTest {
  @Test
  void testCase_addApplication() {
    final var applicationMock = mock(Application.class);

    try (final var testUnit = new LocalServer()) {
      // execute
      testUnit.addApplication(applicationMock);

      // verify
      expect(testUnit.containsDefaultApplication()).isFalse();
      expect(testUnit.getStoredApplications()).containsExactly(applicationMock);
    }
  }

  @Test
  @SuppressWarnings("unchecked")
  void testCase_addDefaultApplication() {
    final var applicationMock = mock(Application.class);

    try (final var testUnit = new LocalServer()) {
      // execute
      testUnit.addDefaultApplication(applicationMock);

      // verify
      expect(testUnit.containsDefaultApplication()).isTrue();
      expect(testUnit.getStoredApplications()).containsExactly(applicationMock);
    }
  }

  @Test
  void testCase_asTarget() {
    try (final var testUnit = new LocalServer()) {
      // verify & execution
      expectRunning(testUnit::asTarget).throwsException().ofType(ArgumentDoesNotSupportMethodException.class);
    }
  }

  @Test
  void testCase_constructor() {
    try (final var testUnit = new LocalServer()) {
      // verify
      expect(testUnit.getSecurityMode()).is(SecurityMode.NONE);
      expect(testUnit.isEmpty()).isTrue();
      expect(testUnit.containsDefaultApplication()).isFalse();
      expect(testUnit.hasClientConnected()).isFalse();
    }
  }
}
