package ch.nolix.coretest.net.endpoint;

import org.junit.jupiter.api.Test;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.net.endpoint.LocalEndPoint;
import ch.nolix.core.net.endpoint.LocalServer;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.net.netproperty.ConnectionType;
import ch.nolix.coreapi.net.netproperty.PeerType;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;

final class LocalEndPointTest extends StandardTest {

  @Test
  void testCase_sendMessage_whenSends1Message() {

    //setup
    final var slot = new MockSlot();
    final var testUnit = LocalEndPoint.toSlot(slot);

    //execution 
    testUnit.sendMessage("my_message");

    //verification
    expect(slot.getLatestReceivedMessage()).is("my_message");
  }

  @Test
  void testCase_sendMessage_whenSends3Messages() {

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

  @Test
  void testCase_sendMessage_whenTheGivenMessageIsNull() {

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

  @Test
  void testCase_sendMessage_whenIsClosed() {

    //setup part 1: Creates slot.
    final var slot = new MockSlot();

    try (final var testUnit = LocalEndPoint.toSlot(slot)) {

      //setup part 2: Closes testUnit.
      testUnit.close(); //NOSONAR: This test case tests the close method.

      //setup verification
      expect(testUnit.isClosed()).isTrue();
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

  @Test
  void testCase_toSlot() {

    //setup
    final var slot = new MockSlot();

    //execution
    final var result = LocalEndPoint.toSlot(slot);

    //verification
    expect(result.getConnectionType()).is(ConnectionType.LOCAL);
    expect(result.getPeerType()).is(PeerType.FRONTEND);
    expect(result.getSecurityMode()).is(SecurityMode.NONE);
  }

  @Test
  void testCase_toSlot_whenTheGivenSlotIsNull() {

    //execution & verification
    expectRunning(() -> LocalEndPoint.toSlot(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given ISlot is null.");
  }

  @Test
  void testCase_toTargetSlotOnServer() {
    try (final var server = new LocalServer()) {

      //setup
      final var slot = new MockSlot();
      server.addSlot(slot);

      //setup verification
      expect(server.containsDefaultSlot()).isFalse();

      //execution
      final var result = LocalEndPoint.toTargetSlotOnServer(server, slot.getName());

      //verification
      expect(result.getCustomTargetSlot()).is(slot.getName());
      expect(result.getConnectionType()).is(ConnectionType.LOCAL);
      expect(result.getPeerType()).is(PeerType.FRONTEND);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
    }
  }

  @Test
  void testCase_toTargetSlotOnServer_whenTheGivenTargetSlotIsBlank() {
    try (final var server = new LocalServer()) {

      //execution & verification
      expectRunning(() -> LocalEndPoint.toTargetSlotOnServer(server, " "))
        .throwsException()
        .ofType(InvalidArgumentException.class)
        .withMessage("The given custom target slot is blank.");
    }
  }
}
