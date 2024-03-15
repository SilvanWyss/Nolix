//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//own imports
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.objectdata.data.OptionalValue;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;

//class
public final class OptionalValueTest extends Test {

  //method
  @TestCase
  public void testCase_clear() {

    //setup
    final var testUnit = OptionalValue.withInitialValue("LoremIpsum");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @TestCase
  public void testCase_constructor() {

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
  @TestCase
  public void testCase_setValue() {

    //setup
    final var testUnit = OptionalValue.withValueType(String.class);

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny());
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
