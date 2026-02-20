/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.endpoint;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.endpoint.Server;
import ch.nolix.base.net.endpoint.SocketEndPoint;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class SocketEndPointTest extends StandardTest {
  private static final int WAITING_TIME_IN_MILLISECONDS = 100;

  @Test
  void testCase_constructor() {
    //parameter definition
    final var port = 50000;

    //setup
    try (final var server = Server.forPort(port)) {
      //setup
      server.addDefaultSlot(new MockSlot());

      //execution & verification
      expectRunning(
        () -> {
          try (final var _ = new SocketEndPoint(port)) {
            FlowController.waitForMilliseconds(1);
          }
        })
        .doesNotThrowException();
    }
  }

  @Test
  void testCase_sendMessage() {
    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(port)) {
      //setup
      final var slot = new MockSlot();
      server.addDefaultSlot(slot);

      try (final var testUnit = new SocketEndPoint(port)) {
        //execution
        testUnit.sendMessage("MESSAGE");
        FlowController.waitForMilliseconds(WAITING_TIME_IN_MILLISECONDS);

        //verification
        expect(slot.getLatestReceivedMessage()).isEqualTo("MESSAGE");
      }
    }
  }
}
