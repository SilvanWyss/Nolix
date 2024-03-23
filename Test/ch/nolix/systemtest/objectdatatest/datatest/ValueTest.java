//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

//class
final class ValueTest extends StandardTest {

  //method
  @Test
  void testCase_constructor() {

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
  @Test
  void testCase_setValue() {

    //setup
    final var testUnit = Value.withValueType(String.class);

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny());
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
