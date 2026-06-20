/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.level3server;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.chainednode.ChainedNode;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.net.level3server.NetEndPoint;
import ch.nolix.base.net.level3server.Server;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class NetEndPointTest extends StandardTest {
  @Test
  void testCase_constructor() {
    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(port)) {
      //setup
      server.addDefaultSlot(new TestSlot());

      //execution & verification
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
    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(port)) {
      //setup
      final var slot = new TestSlot();
      server.addDefaultSlot(slot);

      try (final var testUnit = NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port)) {
        //execution
        testUnit.runCommand(ChainedNode.fromString("test_command"));

        //verification
        expect(slot.getLatestCreatedReceivingDataProviderController().getLatestReceivedCommand())
          .hasStringRepresentation("test_command");
      }
    }
  }

  @Test
  void testCase_getData() {
    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(port)) {
      //setup
      final var slot = new TestSlot();
      server.addDefaultSlot(slot);

      try (final var testUnit = NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port)) {
        //execution
        final var result = testUnit.getDataForRequest(ChainedNode.fromString("test_request"));

        //verification
        expect(slot.getLatestCreatedReceivingDataProviderController().getLatestReceivedRequest())
          .hasStringRepresentation("test_request");
        expect(result).isEqualTo(ImmutableNode.withHeader("test_data"));
      }
    }
  }
}
