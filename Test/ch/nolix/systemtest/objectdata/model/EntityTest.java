package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;

/**
 * @author Silvan Wyss
 */
final class EntityTest extends StandardTest {
  private static final class Thing extends Entity {
    //This class is just a sub class without additional methods.
  }

  @Test
  void testCase_constructor() {
    //execution
    final var result = new Thing();

    //verification part 1: parents
    expect(result.belongsToDatabase()).isFalse();
    expect(result.belongsToTable()).isFalse();

    //verification part 2: attributes
    expect(result.getId()).hasLength(10);
    expect(result.internalGetStoredFields()).isEmpty();
    expect(result.hasSaveStamp()).isFalse();

    //verification part 3: state
    expect(result.getState()).is(DatabaseObjectState.NEW);
    expect(result.isNew()).isTrue();
    expect(result.isLoaded()).isFalse();
    expect(result.isEdited()).isFalse();
    expect(result.isDeleted()).isFalse();
    expect(result.isOpen()).isTrue();
    expect(result.isConnectedWithRealDatabase()).isFalse();
    expect(result.isReferencedInPersistedData()).isFalse();
  }

  @Test
  void testCase_getShortDescription() {
    //setup
    final var testUnit = new Thing();

    //execution
    final var result = testUnit.getShortDescription();

    //verification
    expect(result).isEqualTo("Thing (id: " + testUnit.getId() + ")");
  }

  @Test
  void testCase_toString() {
    //setup
    final var testUnit = new Thing();

    //execution
    final var result = testUnit.toString();

    //verification
    final var shortDescription = testUnit.getShortDescription();
    expect(result).isEqualTo(shortDescription);
  }
}
