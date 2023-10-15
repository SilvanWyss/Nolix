//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.OptionalValue;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;

//class
public final class OptionalValueTest extends Test {

  // method
  @TestCase
  public void testCase_clear() {

    // setup
    final var testUnit = new OptionalValue<String>();
    testUnit.setValue("LoremIpsum");

    // execution
    testUnit.clear();

    // verification
    expect(testUnit.isEmpty());
  }

  // method
  @TestCase
  public void testCase_constructor() {

    // execution
    final var result = new OptionalValue<String>();

    // verification
    expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
    expect(result.isOpen());
    expectNot(result.isDeleted());
    expectNot(result.isLinkedWithRealDatabase());
    expect(result.isOptional());
    expect(result.isEmpty());
  }

  // method
  @TestCase
  public void testCase_setValue() {

    // setup
    final var testUnit = new OptionalValue<String>();

    // execution
    testUnit.setValue("LoremIpsum");

    // verification
    expect(testUnit.containsAny());
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
