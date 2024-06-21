//package declaration
package ch.nolix.systemtest.objectdatatest.datatest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

//class
final class EntityTest extends StandardTest {

  //constant
  private static final class Thing extends Entity {
  }

  //method
  @Test
  void testCase_constructor() {

    //execution
    final var result = new Thing();

    //verification
    expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
    expectNot(result.isClosed());
    expectNot(result.isDeleted());
    expect(result.getId()).hasLength(10);
    expect(result.getShortDescription()).startsWith("Thing");
    expectNot(result.isLinkedWithRealDatabase());
    expectNot(result.belongsToTable());
  }

  //method
  @Test
  void testCase_toString() {

    //setup
    final var testUnit = new Thing();

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("Thing (id: " + testUnit.getId() + ")");
  }
}
