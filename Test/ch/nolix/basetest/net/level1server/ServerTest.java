/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.level1server;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.net.level1server.Server;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.net.level1server.ISlot;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
final class ServerTest extends StandardTest {
  @Test
  void testCase_addDefaultSlot() {
    // define test parameters
    final var port = 50000;

    // setup
    final var mockSlot = new MockSlot();

    try (final var testUnit = Server.forPort(port)) {
      // execute
      testUnit.addDefaultSlot(mockSlot);

      // verify
      expect(testUnit.containsAny()).isTrue();
      expect(testUnit.containsDefaultSlot());
    }
  }

  @Test
  void testCase_clear_whenIsEmpty() {
    // define test parameters
    final var port = 50000;

    try (final var testUnit = Server.forPort(port)) {
      // setup verification
      expect(testUnit.isEmpty()).isTrue();

      // execute
      testUnit.clear();

      // verify
      expect(testUnit.isEmpty()).isTrue();
    }
  }

  @Test
  void testCase_clear_whenContainsAny() {
    // define test parameters
    final var port = 50000;

    try (final var testUnit = Server.forPort(port)) {
      // setup
      FlowController.forCount(5).run(() -> testUnit.addDefaultSlot(Mockito.mock(ISlot.class)));

      // setup verification
      expect(testUnit.containsAny()).isTrue();

      // execute
      testUnit.clear();

      // verify
      expect(testUnit.isEmpty()).isTrue();
    }
  }

  @Test
  void testCase_close() {
    // define test parameters
    final var port = 50000;

    // setup
    try (final var testUnit = Server.forPort(port)) {
      // execute
      testUnit.close(); // NOSONAR: This test case tests the close method.

      // verify
      expect(testUnit.isClosed()).isTrue();
    }
  }

  @Test
  void testCase_forHttpPort() {
    try (final var result = Server.forHttpPort()) {
      // verify
      expect(result.getPort()).isEqualTo(80);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
      expect(result.getInitialHttpMessage()).is(Server.DEFAULT_INITIAL_HTTP_MESSAGE);
      expect(result.isOpen()).isTrue();
      expect(result.isEmpty()).isTrue();
      expect(result.containsDefaultSlot()).isFalse();
    }
  }

  @Test
  void testCase_forPort() {
    // define test parameters
    final var port = 50000;

    try (final var result = Server.forPort(port)) {
      // verify
      expect(result.getPort()).isEqualTo(port);
      expect(result.getSecurityMode()).is(SecurityMode.NONE);
      expect(result.getInitialHttpMessage()).is(Server.DEFAULT_INITIAL_HTTP_MESSAGE);
      expect(result.isOpen());
      expect(result.isEmpty()).isTrue();
      expect(result.containsDefaultSlot()).isFalse();
    }
  }
}
