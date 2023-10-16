//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;

//class
public final class ValueTest extends Test {

  //method
  @TestCase
  public void testCase_constructor() {

    //execution
    final var result = new Value<String>();

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
    final var testUnit = new Value<String>();

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny());
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
