/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.senderandreceiverserver;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.senderandreceiverserver.LocalServer;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
final class LocalServerTest extends StandardTest {
  @Test
  void testCase_constructor() {
    try (final var server = new LocalServer()) {
      // verify
      expect(server.getSecurityMode()).is(SecurityMode.NONE);
      expect(server.isOpen());
      expect(server.isEmpty());
      expect(server.containsDefaultSlot()).isFalse();
    }
  }
}
