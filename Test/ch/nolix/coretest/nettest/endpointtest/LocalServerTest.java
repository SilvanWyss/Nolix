//package declaration
package ch.nolix.coretest.nettest.endpointtest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.net.endpoint.LocalServer;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

//class
final class LocalServerTest extends StandardTest {

  //method
  @Test
  void testCase_constructor() {
    try (final var server = new LocalServer()) {

      //verification
      expect(server.getSecurityMode()).is(SecurityMode.NONE);
      expect(server.isOpen());
      expect(server.isEmpty());
      expectNot(server.containsDefaultSlot());
    }
  }
}
