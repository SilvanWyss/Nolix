package ch.nolix.systemtest.application.main;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.LocalServer;

/**
 * @author Silvan Wyss
 */
final class LocalServerTest extends StandardTest {
  @Test
  void testCase_addApplication() {
    final var applicationMock = mock(Application.class);

    try (final var testUnit = new LocalServer()) {
      //execution
      testUnit.addApplication(applicationMock);

      //verification
      expect(testUnit.containsDefaultApplication()).isFalse();
      expect(testUnit.getStoredApplications()).containsExactly(applicationMock);
    }
  }

  @Test
  @SuppressWarnings("unchecked")
  void testCase_addDefaultApplication() {
    final var applicationMock = mock(Application.class);

    try (final var testUnit = new LocalServer()) {
      //execution
      testUnit.addDefaultApplication(applicationMock);

      //verification
      expect(testUnit.containsDefaultApplication()).isTrue();
      expect(testUnit.getStoredApplications()).containsExactly(applicationMock);
    }
  }

  @Test
  void testCase_asTarget() {
    try (final var testUnit = new LocalServer()) {
      //verification & execution
      expectRunning(testUnit::asTarget).throwsException().ofType(ArgumentDoesNotSupportMethodException.class);
    }
  }

  @Test
  void testCase_constructor() {
    try (final var testUnit = new LocalServer()) {
      //verification
      expect(testUnit.getSecurityMode()).is(SecurityMode.NONE);
      expect(testUnit.isEmpty()).isTrue();
      expect(testUnit.containsDefaultApplication()).isFalse();
      expect(testUnit.hasClientConnected()).isFalse();
    }
  }
}
