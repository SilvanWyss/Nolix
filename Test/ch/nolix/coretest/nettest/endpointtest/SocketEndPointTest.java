//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.net.endpoint.Server;
import ch.nolix.core.net.endpoint.SocketEndPoint;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.test.StandardTest;

//class
final class SocketEndPointTest extends StandardTest {

  //constant
  private static final int WAITING_TIME_IN_MILLISECONDS = 100;

  //method
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
          try (final var result = new SocketEndPoint(port)) {
            GlobalSequencer.waitForMilliseconds(1);
          }
        })
        .doesNotThrowException();
    }
  }

  //method
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
        GlobalSequencer.waitForMilliseconds(WAITING_TIME_IN_MILLISECONDS);

        //verification
        expect(slot.getLatestReceivedMessage()).isEqualTo("MESSAGE");
      }
    }
  }
}
