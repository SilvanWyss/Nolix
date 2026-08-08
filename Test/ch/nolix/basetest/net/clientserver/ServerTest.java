/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.clientserver;

import org.junit.jupiter.api.Test;

import ch.nolix.base.environment.localcomputer.LocalComputer;
import ch.nolix.base.net.clientserver.Server;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
final class ServerTest extends StandardTest {
  @Test
  void testCase_forHttpPort() {
    try (final var testUnit = Server.forHttpPort()) {
      // verify
      expect(testUnit.getIp()).isEqualTo(LocalComputer.getLanIp());
      expect(testUnit.getPort()).isEqualTo(PortCatalog.HTTP);
      expect(testUnit.getSecurityMode()).is(SecurityMode.NONE);
      expect(testUnit.getStoredApplications()).isEmpty();
      expect(testUnit.containsDefaultApplication()).isFalse();
    }
  }
}
