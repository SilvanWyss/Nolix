package ch.nolix.coretest.nettest.endpoint2test;

import org.junit.jupiter.api.Test;

import ch.nolix.core.net.endpoint2.NetEndPoint;
import ch.nolix.core.net.endpoint2.Server;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.standardtest.StandardTest;

final class NetEndPointTest extends StandardTest {

  @Test
  void testCase_constructor() {

    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(50000)) {

      //setup
      server.addDefaultSlot(new MockSlot());

      //execution & verification
      expectRunning(
        () -> {
          try (final var result = new NetEndPoint(port)) {
            GlobalSequencer.waitForMilliseconds(1);
          }
        })
        .doesNotThrowException();
    }
  }

  @Test
  void testCase_sendAndGetReply() {

    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(50000)) {

      //setup
      final var slot = new MockSlot();
      server.addDefaultSlot(slot);

      try (final var testUnit = new NetEndPoint(port)) {

        //execution
        final var result = testUnit.getReplyForRequest("message");

        //verification
        expect(slot.getLatestReceivedMessage()).isEqualTo("message");
        expect(result).isEqualTo(MockSlot.REPLY);
      }
    }
  }
}
