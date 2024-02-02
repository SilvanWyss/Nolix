//package declaration
package ch.nolix.systemtest.sqlrawdatatest.sqlsyntaxtest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.system.sqlrawdata.datadto.EntityHeadDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.system.sqlrawdata.datadto.NewEntityDto;
import ch.nolix.system.sqlrawdata.sqlsyntax.EntityStatementCreator;

//class
public final class EntityStatementCreatorTest extends Test {

  //method
  @TestCase
  public void testCase_createStatementToDeleteEntity() {

    //setup
    final var testUnit = new EntityStatementCreator();
    final var entityHeadDto = new EntityHeadDto("my_id", "100");

    //execution
    final var result = testUnit.createStatementToDeleteEntity("MyTable", entityHeadDto);

    //verification
    final var expectedResult = "DELETE FROM EMyTable WHERE Id = 'my_id' AND SaveStamp = '100';"
    + "IF @@RowCount = 0 BEGIN THROW error(100000, 'The data was changed in the meanwhile.', 0) END;";
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @TestCase
  public void testCase_createStatementToExpectTableContainsEntity() {

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

  //method
  @TestCase
  public void testCase_createStatementToInsertNewEntity() {

    //setup
    final var testUnit = new EntityStatementCreator();
    final var newEntityDto = new NewEntityDto(
      "my_id",
      ImmutableList.withElement(
        new ContentFieldDto("MyColumn1", "my_value1"),
        new ContentFieldDto("MyColumn2", "my_value2"),
        new ContentFieldDto("MyColumn3", "my_value3")));

    //execution
    final var result = testUnit.createStatementToInsertNewEntity("MyTable", newEntityDto);

    //verification
    final var expectedResult = "INSERT INTO EMyTable (Id, SaveStamp, MyColumn1, MyColumn2, MyColumn3) "
    + "VALUES ('my_id', '1', 'my_value1', 'my_value2', 'my_value3');";
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @TestCase
  public void testCase_createStatementToUpdateEntityOnTable() {

    //setup
    final var testUnit = new EntityStatementCreator();
    final var newEntityDto = new EntityUpdateDto(
      "my_id",
      "100",
      ImmutableList.withElement(
        new ContentFieldDto("MyColumn1", "my_value1"),
        new ContentFieldDto("MyColumn2", "my_value2"),
        new ContentFieldDto("MyColumn3", "my_value3")));

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
