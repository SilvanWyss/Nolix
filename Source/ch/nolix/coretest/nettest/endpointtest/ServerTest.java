//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//own imports
import ch.nolix.core.net.endpoint.Server;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.netapi.securityapi.SecurityMode;

//class
public final class ServerTest extends Test {

  //method
  @TestCase
  public void testCase_addDefaultSlot() {

    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(port)) {

      //setup
      final var mockSlot = new MockSlot();

      //execution
      server.addDefaultSlot(mockSlot);

      //verification
      expect(server.containsDefaultSlot());
    }
  }

  //method
  @TestCase
  public void testCase_close() {

    //parameter definition
    final var port = 50000;

    //setup
    try (final var server = Server.forPort(port)) {

      //setup verification
      expect(server.getPort()).isEqualTo(port);
      expect(server.isOpen());

      //execution & verification
      expectRunning(server::close).doesNotThrowException();

      //verification
      expect(server.isClosed());
    }
  }

  //method
  @TestCase
  public void testCase_forPort() {

    //parameter definition
    final var port = 50000;

    try (final var result = Server.forPort(port)) {

      //verification
      expect(result.getPort()).isEqualTo(port);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
      expectNot(result.containsDefaultSlot());
      expect(result.isOpen());
    }
  }

  //method
  @TestCase
  public void testCase_forHttpPort() {
    try (final var result = Server.forHttpPort()) {

      //verification
      expect(result.getPort()).isEqualTo(80);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
      expect(result.isOpen());
    }
  }
}
