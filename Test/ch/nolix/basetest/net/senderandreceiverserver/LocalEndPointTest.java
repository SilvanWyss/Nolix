/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.senderandreceiverserver;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.senderandreceiverserver.LocalEndPoint;
import ch.nolix.base.net.senderandreceiverserver.LocalServer;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
final class LocalEndPointTest extends StandardTest {
  @Test
  void testCase_sendMessage_whenSends1Message() {
    // setup
    final var slot = new MockSlot();
    final var testUnit = LocalEndPoint.toSlot(slot);

    // execute 
    testUnit.sendMessage("my_message");

    // verify
    expect(slot.getLatestReceivedMessage()).is("my_message");
  }

  @Test
  void testCase_sendMessage_whenSends3Messages() {
    // setup
    final var slot = new MockSlot();
    final var testUnit = LocalEndPoint.toSlot(slot);

    // execute 
    testUnit.sendMessage("my_message1");
    testUnit.sendMessage("my_message2");
    testUnit.sendMessage("my_message3");

    // verify
    expect(slot.getLatestReceivedMessage()).is("my_message3");
  }

  @Test
  void testCase_sendMessage_whenTheGivenMessageIsNull() {
    // setup
    final var slot = new MockSlot();
    final var testUnit = LocalEndPoint.toSlot(slot);

    // setup verification
    expect(slot.getLatestReceivedMessage()).isNull();

    // execute & verify
    expectRunning(() -> testUnit.sendMessage(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given message is null.");
    expect(slot.getLatestReceivedMessage()).isNull();
  }

  @Test
  void testCase_sendMessage_whenIsClosed() {
    // setup step 1: create slot.
    final var slot = new MockSlot();

    try (final var testUnit = LocalEndPoint.toSlot(slot)) {
      // setup step 2: Closes testUnit.
      testUnit.close(); // NOSONAR: This test case tests the close method.

      // setup verification
      expect(testUnit.isClosed()).isTrue();
      expect(slot.getLatestReceivedMessage()).isNull();

      // execute & verify
      expectRunning(() -> testUnit.sendMessage("my_message"))
        .throwsException()
        .ofType(ClosedArgumentException.class)
        .withMessageThatMatches("The given LocalEndPoint .* is closed.");
    }

    // verify
    expect(slot.getLatestReceivedMessage()).isNull();
  }

  @Test
  void testCase_toSlot() {
    // setup
    final var slot = new MockSlot();

    // execute
    final var result = LocalEndPoint.toSlot(slot);

    // verify
    expect(result.getConnectionType()).is(ConnectionType.LOCAL);
    expect(result.getPeerType()).is(PeerType.FRONTEND);
    expect(result.getSecurityMode()).is(SecurityMode.NONE);
  }

  @Test
  void testCase_toSlot_whenTheGivenSlotIsNull() {
    // execute & verify
    expectRunning(() -> LocalEndPoint.toSlot(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given Slot is null.");
  }

  @Test
  void testCase_toTargetSlotOnServer() {
    try (final var server = new LocalServer()) {
      // setup
      final var slot = new MockSlot();
      server.addSlot(slot);

      // setup verification
      expect(server.containsDefaultSlot()).isFalse();

      // execute
      final var result = LocalEndPoint.toTargetSlotOnServer(server, slot.getName());

      // verify
      expect(result.getCustomTargetSlot()).is(slot.getName());
      expect(result.getConnectionType()).is(ConnectionType.LOCAL);
      expect(result.getPeerType()).is(PeerType.FRONTEND);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
    }
  }

  @Test
  void testCase_toTargetSlotOnServer_whenTheGivenTargetSlotIsBlank() {
    try (final var server = new LocalServer()) {
      // execute & verify
      expectRunning(() -> LocalEndPoint.toTargetSlotOnServer(server, " "))
        .throwsException()
        .ofType(InvalidArgumentException.class)
        .withMessage("The given custom target slot is blank.");
    }
  }
}
