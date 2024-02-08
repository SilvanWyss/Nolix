//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;

//class
public final class ValueTest extends Test {

  //method
  @TestCase
  public void testCase_constructor() {

    //execution
    final var result = Value.withValueType(String.class);

    //verification
    expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
    expect(result.isOpen());
    expectNot(result.isDeleted());
    expectNot(result.isLinkedWithRealDatabase());
    expect(result.isMandatory());
    expect(result.isEmpty());
  }

  //method
  @TestCase
  public void testCase_setValue() {

    //setup
    final var testUnit = Value.withValueType(String.class);

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny());
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
