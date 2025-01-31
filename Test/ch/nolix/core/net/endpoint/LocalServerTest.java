package ch.nolix.core.net.endpoint;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

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
