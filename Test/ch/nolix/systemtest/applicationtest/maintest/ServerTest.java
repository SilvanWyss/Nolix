package ch.nolix.systemtest.applicationtest.maintest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.environment.localcomputer.LocalComputer;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.system.application.main.Server;

final class ServerTest extends StandardTest {

  @Test
  void testCase_forHttpPort() {
    try (final var testUnit = Server.forHttpPort()) {

      //verification
      expect(testUnit.getIp()).isEqualTo(LocalComputer.getLanIp());
      expect(testUnit.getPort()).isEqualTo(PortCatalogue.HTTP);
      expect(testUnit.getSecurityMode()).is(SecurityMode.NONE);
      expect(testUnit.getStoredApplications()).isEmpty();
      expect(testUnit.containsDefaultApplication()).isFalse();
    }
  }
}
