/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapitest.net.netproperty;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.net.netproperty.BaseConnectionType;
import ch.nolix.baseapi.net.netproperty.ConnectionType;

/**
 * @author Silvan Wyss
 */
final class ConnectionTypeTest extends StandardTest {
  @Test
  void testCase_getBaseType_whenIsLocal() {
    // setup
    final var testUnit = ConnectionType.LOCAL;

   // execute
    final var result = testUnit.getBaseType();

   // verify
    expect(result).is(BaseConnectionType.LOCAL);
  }

  @Test
  void testCase_getBaseType_whenIsSocket() {
    // setup
    final var testUnit = ConnectionType.SOCKET;

   // execute
    final var result = testUnit.getBaseType();

   // verify
    expect(result).is(BaseConnectionType.NET);
  }

  @Test
  void testCase_getBaseType_whenIsWebSocket() {
    // setup
    final var testUnit = ConnectionType.WEB_SOCKET;

   // execute
    final var result = testUnit.getBaseType();

   // verify
    expect(result).is(BaseConnectionType.NET);
  }
}
