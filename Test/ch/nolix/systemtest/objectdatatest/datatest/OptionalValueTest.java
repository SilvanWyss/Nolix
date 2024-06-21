//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.OptionalValue;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

//class
final class OptionalValueTest extends StandardTest {

  //method
  @Test
  void testCase_clear() {

    //setup
    final var testUnit = OptionalValue.withInitialValue("LoremIpsum");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @Test
  void testCase_constructor() {

    //execution
    final var result = OptionalValue.withValueType(String.class);

    //verification
    expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
    expect(result.isOpen());
    expectNot(result.isDeleted());
    expectNot(result.isLinkedWithRealDatabase());
    expect(result.isOptional());
    expect(result.isEmpty());
  }

  //method
  @Test
  void testCase_setValue() {

    //setup
    final var testUnit = OptionalValue.withValueType(String.class);

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny());
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
