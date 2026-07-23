/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.sqlmiddata.statementcreator;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.sqlmiddata.statementcreator.EntityStatementCreator;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;

/**
 * @author Silvan Wyss
 */
final class EntityStatementCreatorTest extends StandardTest {
  @Test
  void testCase_createStatementToDeleteEntity() {
    // setup
    final var testUnit = new EntityStatementCreator();
    final var entityDeletionDto = new EntityDeletionDto("my_id", "100");

    // execute
    final var result = testUnit.createStatementToDeleteEntity("MyTable", entityDeletionDto);

    // verify
    final var expectedResult = "DELETE FROM MyTable WHERE Id = 'my_id' AND SaveStamp = '100';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToDeleteEntityIndex() {
    // setup
    final var testUnit = new EntityStatementCreator();

    // execute
    final var result = testUnit.createStatementToDeleteEntityIndex("test_id");

    // verify
    final var expectedResult = "DELETE FROM EntityIndex WHERE EntityId = 'test_id';";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToExpectTableContainsEntity() {
    // setup
    final var testUnit = new EntityStatementCreator();

    // execute
    final var result = testUnit.createStatementToExpectTableContainsEntity("MyTable", "my_id");

    // verify
    final var expectedResult = //
    "SELECT Id FROM MyTable WHERE Id = 'my_id'; "
    + "IF @@RowCount = 0 BEGIN "
    + "THROW error(100000, 'The database does not contain a MyTable with the id my_id.', 0)"
    + " END;";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToInsertNewEntity() {
    // setup
    final var testUnit = new EntityStatementCreator();
    final var newEntityDto = new EntityCreationDto(
      "my_id",
      ImmutableList.withElements(
        new ValueStringFieldDto("MyColumn1", "my_value1", null),
        new ValueStringFieldDto("MyColumn2", "my_value2", null),
        new ValueStringFieldDto("MyColumn3", "my_value3", null)));

    // execute
    final var result = testUnit.createStatementToInsertEntity("MyTable", newEntityDto);

    // verify
    final var expectedResult = "INSERT INTO MyTable (Id, SaveStamp, MyColumn1, MyColumn2, MyColumn3) "
    + "VALUES ('my_id', '1', 'my_value1', 'my_value2', 'my_value3');";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToUpdateEntityOnTable() {
    // setup
    final var testUnit = new EntityStatementCreator();
    final var newEntityDto = new EntityUpdateDto(
      "my_id",
      "100",
      ImmutableList.withElements(
        new ValueStringFieldDto("MyColumn1", "my_value1", null),
        new ValueStringFieldDto("MyColumn2", "my_value2", null),
        new ValueStringFieldDto("MyColumn3", "my_value3", null)));

    // execute
    final var result = testUnit.createStatementToUpdateEntityOnTable("MyTable", newEntityDto);

    // verify
    final var expectedResult = "UPDATE MyTable "
    + "SET SaveStamp = '101', MyColumn1 = 'my_value1', MyColumn2 = 'my_value2', MyColumn3 = 'my_value3' "
    + "WHERE Id = 'my_id' AND SaveStamp = '100';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
    expect(result).isEqualTo(expectedResult);
  }
}
