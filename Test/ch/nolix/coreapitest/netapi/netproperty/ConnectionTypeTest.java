package ch.nolix.coreapitest.netapi.netproperty;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.net.netproperty.BaseConnectionType;
import ch.nolix.coreapi.net.netproperty.ConnectionType;

/**
 * @author Silvan Wyss
 */
final class ConnectionTypeTest extends StandardTest {
  @Test
  void testCase_getBaseType_whenIsLocal() {
    //setup
    final var testUnit = ConnectionType.LOCAL;

    //execution
    final var result = testUnit.getBaseType();

    //verification
    expect(result).is(BaseConnectionType.LOCAL);
  }

  @Test
  void testCase_getBaseType_whenIsSocket() {
    //setup
    final var testUnit = ConnectionType.SOCKET;

    //execution
    final var result = testUnit.getBaseType();

    //verification
    expect(result).is(BaseConnectionType.NET);
  }

  @Test
  void testCase_getBaseType_whenIsWebSocket() {
    //setup
    final var testUnit = ConnectionType.WEB_SOCKET;

    //execution
    final var result = testUnit.getBaseType();

    //verification
    expect(result).is(BaseConnectionType.NET);
  }
}
