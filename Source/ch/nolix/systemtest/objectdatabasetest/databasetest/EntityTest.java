//package declaration
package ch.nolix.systemtest.objectdatabasetest.databasetest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;

//class
public final class EntityTest extends Test {

  // static class
  private static final class Thing extends Entity {
  }

  // method
  @TestCase
  public void testCase_constructor() {

    // execution
    final var result = new Thing();

    // verification
    expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
    expectNot(result.isClosed());
    expectNot(result.isDeleted());
    expect(result.getId()).hasLength(10);
    expect(result.getShortDescription()).startsWith("Thing");
    expectNot(result.isLinkedWithRealDatabase());
    expectNot(result.belongsToTable());
  }

  // method
  @TestCase
  public void testCase_toString() {

    // setup
    final var testUnit = new Thing();

    // execution
    final var result = testUnit.toString();

    // verification
    expect(result).isEqualTo("Thing (id: " + testUnit.getId() + ")");
  }
}
