package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

final class EntityTest extends StandardTest {

  private static final class Thing extends Entity {
  }

  @Test
  void testCase_constructor() {

    //execution
    final var result = new Thing();

    //verification
    expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
    expect(result.isClosed()).isFalse();
    expect(result.isDeleted()).isFalse();
    expect(result.getId()).hasLength(10);
    expect(result.getShortDescription()).startsWith("Thing");
    expect(result.isLinkedWithRealDatabase()).isFalse();
    expect(result.belongsToTable()).isFalse();
  }

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
