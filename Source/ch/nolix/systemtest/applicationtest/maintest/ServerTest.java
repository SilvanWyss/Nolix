//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//own imports
import ch.nolix.core.environment.localcomputer.LocalComputer;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.application.main.Server;

//class
public final class ServerTest extends Test {

  //method
  @TestCase
  public void testCase_forHttpPort() {
    try (final var testUnit = Server.forHttpPort()) {

      //verification
      expect(testUnit.getIp()).isEqualTo(LocalComputer.getLanIp());
      expect(testUnit.getPort()).isEqualTo(PortCatalogue.HTTP);
      expect(testUnit.getSecurityLevel()).is(SecurityMode.NONE);
      expect(testUnit.getStoredApplications()).isEmpty();
      expectNot(testUnit.containsDefaultApplication());
    }
  }
}
