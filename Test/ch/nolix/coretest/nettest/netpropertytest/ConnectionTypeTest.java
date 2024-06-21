//package declaration
package ch.nolix.coretest.nettest.netpropertytest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.netapi.netproperty.BaseConnectionType;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;

//class
final class ConnectionTypeTest extends StandardTest {

  //method
  @Test
  void testCase_getBaseType_whenIsLocal() {

    //setup
    final var testUnit = ConnectionType.LOCAL;

    //execution
    final var result = testUnit.getBaseType();

    //verification
    expect(result).is(BaseConnectionType.LOCAL);
  }

  //method
  @Test
  void testCase_getBaseType_whenIsSocket() {

    //setup
    final var testUnit = ConnectionType.SOCKET;

    //execution
    final var result = testUnit.getBaseType();

    //verification
    expect(result).is(BaseConnectionType.NET);
  }

  //method
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
