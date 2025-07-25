package ch.nolix.coretest.net.endpoint;

import org.junit.jupiter.api.Test;

import ch.nolix.core.net.endpoint.LocalServer;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;

final class LocalServerTest extends StandardTest {

  @Test
  void testCase_constructor() {
    try (final var server = new LocalServer()) {

      //verification
      expect(server.getSecurityMode()).is(SecurityMode.NONE);
      expect(server.isOpen());
      expect(server.isEmpty());
      expect(server.containsDefaultSlot()).isFalse();
    }
  }
}
