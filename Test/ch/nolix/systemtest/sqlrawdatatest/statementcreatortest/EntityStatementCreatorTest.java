package ch.nolix.systemtest.sqlrawdatatest.statementcreatortest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlrawdata.statementcreator.EntityStatementCreator;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.model.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.model.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.model.StringContentFieldDto;

final class EntityStatementCreatorTest extends StandardTest {

  @Test
  void testCase_createStatementToDeleteEntity() {

    //setup
    final var testUnit = new EntityStatementCreator();
    final var entityHeadDto = new EntityDeletionDto("my_id", "100");

    //execution
    final var result = testUnit.createStatementToDeleteEntity("MyTable", entityHeadDto);

    //verification
    final var expectedResult = "DELETE FROM EMyTable WHERE Id = 'my_id' AND SaveStamp = '100';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createStatementToDeleteEntityHead() {

    //setup
    final var testUnit = new EntityStatementCreator();

    //execution
    final var result = testUnit.createStatementToDeleteEntityIndex("test_id");

    //verification
    final var expectedResult = "DELETE FROM SEntityHead WHERE EntityId = 'test_id';";
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
        StringContentFieldDto.withColumnNameAndContent("MyColumn1", "my_value1"),
        StringContentFieldDto.withColumnNameAndContent("MyColumn2", "my_value2"),
        StringContentFieldDto.withColumnNameAndContent("MyColumn3", "my_value3")));

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
        StringContentFieldDto.withColumnNameAndContent("MyColumn1", "my_value1"),
        StringContentFieldDto.withColumnNameAndContent("MyColumn2", "my_value2"),
        StringContentFieldDto.withColumnNameAndContent("MyColumn3", "my_value3")));

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
