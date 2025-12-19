package ch.nolix.coretest.net.endpoint3;

import org.junit.jupiter.api.Test;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.net.endpoint3.NetEndPoint;
import ch.nolix.core.net.endpoint3.Server;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.core.testing.standardtest.StandardTest;

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
          try (final var _ = new NetEndPoint(port)) {
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

      try (final var testUnit = new NetEndPoint(port)) {
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

      try (final var testUnit = new NetEndPoint(port)) {
        //execution
        final var result = testUnit.getDataForRequest(ChainedNode.fromString("test_request"));

        //verification
        expect(slot.getLatestCreatedReceivingDataProviderController().getLatestReceivedRequest())
          .hasStringRepresentation("test_request");
        expect(result).isEqualTo(Node.withHeader("test_data"));
      }
    }
  }
}
