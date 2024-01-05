//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//own imports
import ch.nolix.core.net.endpoint.LocalServer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.netapi.securityapi.SecurityMode;

//class
public final class LocalServerTest extends Test {

  //method
  @TestCase
  public void testCase_constructor() {
    try (final var server = new LocalServer()) {

      //verification
      expect(server.getSecurityMode()).is(SecurityMode.NONE);
      expect(server.isOpen());
      expect(server.isEmpty());
      expectNot(server.containsDefaultSlot());
    }
  }
}
