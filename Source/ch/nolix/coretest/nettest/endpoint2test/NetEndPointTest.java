//package declaration
package ch.nolix.coretest.nettest.endpoint2test;

//own imports
import ch.nolix.core.net.endpoint2.NetEndPoint;
import ch.nolix.core.net.endpoint2.Server;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.netapi.endpoint2api.IEndPoint;
import ch.nolix.coreapi.netapi.endpoint2api.ISlot;

//class
public final class NetEndPointTest extends Test {

  //constant
  private static final class SlotMock implements ISlot {

    //optional attribute
    private String receivedMessage;

    //method
    @Override
    public String getName() {
      return "slot";
    }

    //method
    public String getReceivedMessageOrNull() {
      return receivedMessage;
    }

    //method
    @Override
    public void takeBackendEndPoint(final IEndPoint endPoint) {
      endPoint.setReplier(this::getReply);
    }

    //method
    private String getReply(final String message) {
      receivedMessage = message;
      return "REPLY";
    }
  }

  //method
  @TestCase
  public void testCase_constructor() {

    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(50000)) {

      //setup
      server.addDefaultSlot(new SlotMock());

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
  @TestCase
  public void testCase_sendAndGetReply() {

    //parameter definition
    final var port = 50000;

    try (final var server = Server.forPort(50000)) {

      //setup
      final var slot = new SlotMock();
      server.addDefaultSlot(slot);

      try (final var testUnit = new NetEndPoint(port)) {

        //execution
        final var result = testUnit.getReplyForRequest("MESSAGE");

        //verification
        expect(slot.getReceivedMessageOrNull()).isEqualTo("MESSAGE");
        expect(result).isEqualTo("REPLY");
      }
    }
  }
}
