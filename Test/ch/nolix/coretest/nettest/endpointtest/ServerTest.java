//package declaration
package ch.nolix.coretest.nettest.endpointtest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.net.endpoint.Server;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

//class
final class ServerTest extends StandardTest {

  //method
  @Test
  void testCase_addDefaultSlot() {

    //parameter definition
    final var port = 50000;

    //setup
    final var mockSlot = new MockSlot();

    try (final var testUnit = Server.forPort(port)) {

      //execution
      testUnit.addDefaultSlot(mockSlot);

      //verification
      expect(testUnit.containsAny());
      expect(testUnit.containsDefaultSlot());
    }
  }

  //method
  @Test
  void testCase_close() {

    //parameter definition
    final var port = 50000;

    //setup
    try (final var testUnit = Server.forPort(port)) {

      //execution
      testUnit.close(); //NOSONAR: This test case tests the close method.

      //verification
      expect(testUnit.isClosed());
    }
  }

  //method
  @Test
  void testCase_forHttpPort() {
    try (final var result = Server.forHttpPort()) {

      //verification
      expect(result.getPort()).isEqualTo(80);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
      expect(result.getInitialHttpMessage()).is(Server.DEFAULT_INITIAL_HTTP_MESSAGE);
      expect(result.isOpen());
      expect(result.isEmpty());
      expectNot(result.containsDefaultSlot());
    }
  }

  //method
  @Test
  void testCase_forPort() {

    //parameter definition
    final var port = 50000;

    try (final var result = Server.forPort(port)) {

      //verification
      expect(result.getPort()).isEqualTo(port);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
      expect(result.getInitialHttpMessage()).is(Server.DEFAULT_INITIAL_HTTP_MESSAGE);
      expect(result.isOpen());
      expect(result.isEmpty());
      expectNot(result.containsDefaultSlot());
    }
  }
}
