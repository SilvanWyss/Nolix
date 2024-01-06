//package declaration
package ch.nolix.coretest.nettest.netpropertytest;

import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.netapi.netproperty.BaseConnectionType;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class ConnectionTypeTest extends Test {

  //method
  @TestCase
  public void testCase_getBaseType_whenIsLocal() {

    //setup
    final var testUnit = ConnectionType.LOCAL;

    //execution
    final var result = testUnit.getBaseType();

    //verification
    expect(result).is(BaseConnectionType.LOCAL);
  }

  //method
  @TestCase
  public void testCase_getBaseType_whenIsSocket() {

    //setup
    final var testUnit = ConnectionType.SOCKET;

    //execution
    final var result = testUnit.getBaseType();

    //verification
    expect(result).is(BaseConnectionType.NET);
  }

  //method
  @TestCase
  public void testCase_getBaseType_whenIsWebSocket() {

    //setup
    final var testUnit = ConnectionType.WEB_SOCKET;

    //execution
    final var result = testUnit.getBaseType();

    //verification
    expect(result).is(BaseConnectionType.NET);
  }
}
