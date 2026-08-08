/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.senderandreplierserver;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.senderandreplierserver.NetEndPoint;
import ch.nolix.base.net.senderandreplierserver.NetServer;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class NetEndPointTest extends StandardTest {
  @Test
  void testCase_constructor() {
    // define test parameters
    final var port = 50000;

    try (final var server = NetServer.forPort(50000)) {
      // setup
      server.addDefaultSlot(new MockSlot());

      // execute & verify
      expectRunning(
        () -> {
          try (final var _ = NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port)) {
            FlowController.waitForMilliseconds(1);
          }
        })
        .doesNotThrowException();
    }
  }

  @Test
  void testCase_sendAndGetReply() {
    // define test parameters
    final var port = 50000;

    try (final var server = NetServer.forPort(50000)) {
      // setup
      final var slot = new MockSlot();
      server.addDefaultSlot(slot);

      try (final var testUnit = NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port)) {
        // execute
        final var result = testUnit.getReplyForMessage("message");

        // verify
        expect(slot.getLatestReceivedMessage()).isEqualTo("message");
        expect(result).isEqualTo(MockSlot.REPLY);
      }
    }
  }
}
