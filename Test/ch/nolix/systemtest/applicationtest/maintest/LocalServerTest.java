//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//Mockito imports
import static org.mockito.Mockito.mock;

//Java imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.LocalServer;

//class
final class LocalServerTest extends StandardTest {

  //method
  @Test
  void testCase_addApplication() {

    final var applicationMock = mock(Application.class);

    try (final var testUnit = new LocalServer()) {

      //execution
      testUnit.addApplication(applicationMock);

      //verification
      expectNot(testUnit.containsDefaultApplication());
      expect(testUnit.getStoredApplications()).containsExactly(applicationMock);
    }
  }

  //method
  @Test
  @SuppressWarnings("unchecked")
  void testCase_addDefaultApplication() {

    final var applicationMock = mock(Application.class);

    try (final var testUnit = new LocalServer()) {

      //execution
      testUnit.addDefaultApplication(applicationMock);

      //verification
      expect(testUnit.containsDefaultApplication());
      expect(testUnit.getStoredApplications()).containsExactly(applicationMock);
    }
  }

  //method
  @Test
  void testCase_asTarget() {
    try (final var testUnit = new LocalServer()) {

      //verification & execution
      expectRunning(testUnit::asTarget).throwsException().ofType(ArgumentDoesNotSupportMethodException.class);
    }
  }

  //method
  @Test
  void testCase_constructor() {
    try (final var testUnit = new LocalServer()) {

      //verification
      expect(testUnit.getSecurityMode()).is(SecurityMode.NONE);
      expect(testUnit.isEmpty());
      expectNot(testUnit.containsDefaultApplication());
      expectNot(testUnit.hasClientConnected());
    }
  }
}
