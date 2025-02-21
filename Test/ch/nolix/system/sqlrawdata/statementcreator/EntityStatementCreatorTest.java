package ch.nolix.system.sqlrawdata.statementcreator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.modelapi.StringContentFieldDto;

final class EntityStatementCreatorTest extends StandardTest {

  @Test
  void testCase_createStatementToDeleteEntity() {

    //setup
    final var testUnit = new EntityStatementCreator();
    final var entityDeletionDto = new EntityDeletionDto("my_id", "100");

    //execution
    final var result = testUnit.createStatementToDeleteEntity("MyTable", entityDeletionDto);

    //verification
    final var expectedResult = "DELETE FROM EMyTable WHERE Id = 'my_id' AND SaveStamp = '100';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToDeleteEntityIndex() {

    //setup
    final var testUnit = new EntityStatementCreator();

    //execution
    final var result = testUnit.createStatementToDeleteEntityIndex("test_id");

    //verification
    final var expectedResult = "DELETE FROM FEntityIndex WHERE EntityId = 'test_id';";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToExpectTableContainsEntity() {

    //setup
    final var testUnit = new EntityStatementCreator();

    //execution
    final var result = testUnit.createStatementToExpectTableContainsEntity("MyTable", "my_id");

    //verification
    final var expectedResult = //
    "SELECT Id FROM EMyTable WHERE Id = 'my_id'; "
    + "IF @@RowCount = 0 BEGIN "
    + "THROW error(100000, 'The database does not contain a MyTable with the id my_id.', 0)"
    + " END;";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToInsertNewEntity() {

    //setup
    final var testUnit = new EntityStatementCreator();
    final var newEntityDto = new EntityCreationDto(
      "my_id",
      ImmutableList.withElement(
        new StringContentFieldDto("MyColumn1", "my_value1"),
        new StringContentFieldDto("MyColumn2", "my_value2"),
        new StringContentFieldDto("MyColumn3", "my_value3")));

    //execution
    final var result = testUnit.createStatementToInsertEntity("MyTable", newEntityDto);

    //verification
    final var expectedResult = "INSERT INTO EMyTable (Id, SaveStamp, MyColumn1, MyColumn2, MyColumn3) "
    + "VALUES ('my_id', '1', 'my_value1', 'my_value2', 'my_value3');";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToUpdateEntityOnTable() {

    //setup
    final var testUnit = new EntityStatementCreator();
    final var newEntityDto = new EntityUpdateDto(
      "my_id",
      "100",
      ImmutableList.withElement(
        new StringContentFieldDto("MyColumn1", "my_value1"),
        new StringContentFieldDto("MyColumn2", "my_value2"),
        new StringContentFieldDto("MyColumn3", "my_value3")));

    //execution
    final var result = testUnit.createStatementToUpdateEntityOnTable("MyTable", newEntityDto);

    //verification
    final var expectedResult = "UPDATE EMyTable "
    + "SET SaveStamp = '101', MyColumn1 = 'my_value1', MyColumn2 = 'my_value2', MyColumn3 = 'my_value3' "
    + "WHERE Id = 'my_id' AND SaveStamp = '100';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
    expect(result).isEqualTo(expectedResult);
  }
}
