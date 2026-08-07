/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.executoranddataproviderserver;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.chainednode.ChainedNode;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.net.executoranddataproviderserver.NetEndPoint;
import ch.nolix.base.net.executoranddataproviderserver.Server;
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

    try (final var server = Server.forPort(port)) {
      // setup
      server.addDefaultSlot(new TestSlot());

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
  void testCase_runCommand() {
    // define test parameters
    final var port = 50000;

    try (final var server = Server.forPort(port)) {
      // setup
      final var slot = new TestSlot();
      server.addDefaultSlot(slot);

      try (final var testUnit = NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port)) {
        // execute
        testUnit.runCommand(ChainedNode.fromString("test_command"));

        // verify
        expect(slot.getLatestCreatedReceivingDataProviderController().getLatestReceivedCommand())
          .hasStringRepresentation("test_command");
      }
    }
  }

  @Test
  void testCase_getData() {
    // define test parameters
    final var port = 50000;

    try (final var server = Server.forPort(port)) {
      // setup
      final var slot = new TestSlot();
      server.addDefaultSlot(slot);

      try (final var testUnit = NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port)) {
        // execute
        final var result = testUnit.getDataForRequest(ChainedNode.fromString("test_request"));

        // verify
        expect(slot.getLatestCreatedReceivingDataProviderController().getLatestReceivedRequest())
          .hasStringRepresentation("test_request");
        expect(result).isEqualTo(ImmutableNode.withHeader("test_data"));
      }
    }
  }
}
