package ch.nolix.coretest.nettest.endpointtest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.core.net.endpoint.Server;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

final class ServerTest extends StandardTest {

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
      expect(testUnit.containsAny()).isTrue();
      expect(testUnit.containsDefaultSlot());
    }
  }

  @Test
  void testCase_clear_whenIsEmpty() {

    //parameter definition
    final var port = 50000;

    try (final var testUnit = Server.forPort(port)) {

      //setup verification
      expect(testUnit.isEmpty()).isTrue();

      //execution
      testUnit.clear();

      //verification
      expect(testUnit.isEmpty()).isTrue();
    }
  }

  @Test
  void testCase_clear_whenContainsAny() {

    //parameter definition
    final var port = 50000;

    try (final var testUnit = Server.forPort(port)) {

      //setup
      GlobalSequencer.forCount(5).run(() -> testUnit.addDefaultSlot(Mockito.mock(ISlot.class)));

      //setup verification
      expect(testUnit.containsAny()).isTrue();

      //execution
      testUnit.clear();

      //verification
      expect(testUnit.isEmpty()).isTrue();
    }
  }

  @Test
  void testCase_close() {

    //parameter definition
    final var port = 50000;

    //setup
    try (final var testUnit = Server.forPort(port)) {

      //execution
      testUnit.close(); //NOSONAR: This test case tests the close method.

      //verification
      expect(testUnit.isClosed()).isTrue();
    }
  }

  @Test
  void testCase_forHttpPort() {
    try (final var result = Server.forHttpPort()) {

      //verification
      expect(result.getPort()).isEqualTo(80);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
      expect(result.getInitialHttpMessage()).is(Server.DEFAULT_INITIAL_HTTP_MESSAGE);
      expect(result.isOpen()).isTrue();
      expect(result.isEmpty()).isTrue();
      expect(result.containsDefaultSlot()).isFalse();
    }
  }

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
      expect(result.isEmpty()).isTrue();
      expect(result.containsDefaultSlot()).isFalse();
    }
  }
}
