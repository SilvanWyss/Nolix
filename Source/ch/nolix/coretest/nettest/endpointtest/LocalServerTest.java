//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//own imports
import ch.nolix.core.net.endpoint.LocalServer;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class LocalServerTest extends StandardTest {

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
