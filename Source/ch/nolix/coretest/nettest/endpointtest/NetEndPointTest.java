//package declaration
package ch.nolix.coretest.nettest.endpointtest;

import ch.nolix.core.net.endpoint.Server;
//own imports
import ch.nolix.core.net.endpoint.SocketEndPoint;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;

//class
public final class NetEndPointTest extends Test {

  //constant
  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  //constant
  private static final class TestEndPointTaker implements ISlot {

    //optional attribute
    private String receivedMessage;

    //method
    @Override
    public String getName() {
      return "EndPointTaker";
    }

    //method
    public String getReceivedMessage() {
      return receivedMessage;
    }

    //method
    @Override
    public void takeBackendEndPoint(final IEndPoint endPoint) {
      endPoint.setReceiver(this::setMessage);
    }

    //method
    private void setMessage(final String receivedMessage) {
      this.receivedMessage = receivedMessage;
    }
  }

  //method
  @TestCase
  public void testCase_constructor() {

    //parameter definition
    final var port = 50000;

    //setup
    try (final var server = Server.forPort(port)) {

      //setup
      server.addDefaultSlot(new TestEndPointTaker());

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
  @TestCase
  public void testCase_send() {

    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(port)) {

      //setup
      final var endPointTaker = new TestEndPointTaker();
      server.addDefaultSlot(endPointTaker);

      try (final var testUnit = new SocketEndPoint(port)) {

        //execution
        testUnit.sendMessage("MESSAGE");
        GlobalSequencer.waitForMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS);

        //verification
        expect(endPointTaker.getReceivedMessage()).isEqualTo("MESSAGE");
      }
    }
  }
}
