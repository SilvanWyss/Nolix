//package declaration
package ch.nolix.coretest.nettest.endpoint3test;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.net.endpoint3.NetEndPoint;
import ch.nolix.core.net.endpoint3.Server;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class NetEndPointTest extends StandardTest {

  //method
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
          try (final var result = new NetEndPoint(port)) {
            GlobalSequencer.waitForMilliseconds(1);
          }
        })
        .doesNotThrowException();
    }
  }

  //method
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

  //method
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
