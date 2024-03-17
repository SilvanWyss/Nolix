//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.net.endpoint.LocalEndPoint;
import ch.nolix.core.net.endpoint.LocalServer;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class LocalEndPointTest extends StandardTest {

  //method
  @TestCase
  public void testCase_sendMessage_whenSends1Message() {

    //setup
    final var slot = new MockSlot();
    final var testUnit = LocalEndPoint.toSlot(slot);

    //execution 
    testUnit.sendMessage("my_message");

    //verification
    expect(slot.getLatestReceivedMessage()).is("my_message");
  }

  //method
  @TestCase
  public void testCase_sendMessage_whenSends3Messages() {

    //setup
    final var slot = new MockSlot();
    final var testUnit = LocalEndPoint.toSlot(slot);

    //execution 
    testUnit.sendMessage("my_message1");
    testUnit.sendMessage("my_message2");
    testUnit.sendMessage("my_message3");

    //verification
    expect(slot.getLatestReceivedMessage()).is("my_message3");
  }

  //method
  @TestCase
  public void testCase_sendMessage_whenTheGivenMessageIsNull() {

    //setup
    final var slot = new MockSlot();
    final var testUnit = LocalEndPoint.toSlot(slot);

    //setup verification
    expect(slot.getLatestReceivedMessage()).isNull();

    //execution & verification
    expectRunning(() -> testUnit.sendMessage(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given message is null.");
    expect(slot.getLatestReceivedMessage()).isNull();
  }

  //method
  @TestCase
  public void testCase_sendMessage_whenIsClosed() {

    //setup part 1: Creates slot.
    final var slot = new MockSlot();

    try (final var testUnit = LocalEndPoint.toSlot(slot)) {

      //setup part 2: Closes testUnit.
      testUnit.close(); //NOSONAR: This test case tests the close method.

      //setup verification
      expect(testUnit.isClosed());
      expect(slot.getLatestReceivedMessage()).isNull();

      //execution & verification
      expectRunning(() -> testUnit.sendMessage("my_message"))
        .throwsException()
        .ofType(ClosedArgumentException.class)
        .withMessageThatMatches("The given LocalEndPoint .* is closed.");
    }

    //verification
    expect(slot.getLatestReceivedMessage()).isNull();
  }

  //method
  @TestCase
  public void testCase_toSlot() {

    //setup
    final var slot = new MockSlot();

    //execution
    final var result = LocalEndPoint.toSlot(slot);

    //verification
    expect(result.getConnectionType()).is(ConnectionType.LOCAL);
    expect(result.getPeerType()).is(PeerType.FRONTEND);
    expect(result.getSecurityLevel()).is(SecurityMode.NONE);
  }

  //method
  @TestCase
  public void testCase_toSlot_whenTheGivenSlotIsNull() {

    //execution & verification
    expectRunning(() -> LocalEndPoint.toSlot(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given ISlot is null.");
  }

  //method
  @TestCase
  public void testCase_toTargetSlotOnServer() {
    try (final var server = new LocalServer()) {

      //setup
      final var slot = new MockSlot();
      server.addSlot(slot);

      //setup verification
      expectNot(server.containsDefaultSlot());

      //execution
      final var result = LocalEndPoint.toTargetSlotOnServer(server, slot.getName());

      //verification
      expect(result.getCustomTargetSlot()).is(slot.getName());
      expect(result.getConnectionType()).is(ConnectionType.LOCAL);
      expect(result.getPeerType()).is(PeerType.FRONTEND);
      expect(result.getSecurityLevel()).is(SecurityMode.NONE);
    }
  }

  //method
  @TestCase
  public void testCase_toTargetSlotOnServer_whenTheGivenTargetSlotIsBlank() {
    try (final var server = new LocalServer()) {

      //execution & verification
      expectRunning(() -> LocalEndPoint.toTargetSlotOnServer(server, " "))
        .throwsException()
        .ofType(InvalidArgumentException.class)
        .withMessage("The given custom target slot is blank.");
    }
  }
}
