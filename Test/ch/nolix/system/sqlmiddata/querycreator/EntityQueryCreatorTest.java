package ch.nolix.system.sqlmiddata.querycreator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.midschemaapi.fieldproperty.ContentType;
import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;

final class EntityQueryCreatorTest extends StandardTest {

  @Test
  void testCase_createQueryToCountEntitiesWithGivenValueAtGivenColumn() {

    //setup
    final var testUnit = new EntityQueryCreator();

    //execution
    final var result = testUnit.createQueryToCountEntitiesWithGivenValueAtGivenColumn("MyTable", "MyColumn",
      "my_value");

    //verification
    final var expectedResult = "SELECT COUNT(MyColumn) FROM MyTable WHERE MyColumn = 'my_value';";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createQueryToLoadEntitiesOfTable() {

    //setup
    final var testUnit = new EntityQueryCreator();
    final var tableInfo = new TableViewDto(
      "ttttttt1",
      "Cat",
      ImmutableList.withElement(
        new ColumnViewDto("ccccccc1", "name", 0, ContentType.VALUE, DataType.STRING),
        new ColumnViewDto("ccccccc2", "year_of_birth", 0, ContentType.VALUE, DataType.INTEGER_4BYTE)));

    //execution
    final var result = testUnit.createQueryToLoadEntitiesOfTable(tableInfo);

    //verification
    final var expectedResult = "SELECT Id, SaveStamp, name, year_of_birth FROM Cat;";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createQueryToLoadEntity() {

    //setup
    final var testUnit = new EntityQueryCreator();
    final var tableInfo = new TableViewDto(
      "ttttttt1",
      "Cat",
      ImmutableList.withElement(
        new ColumnViewDto("ccccccc1", "name", 0, ContentType.VALUE, DataType.STRING),
        new ColumnViewDto("ccccccc2", "year_of_birth", 0, ContentType.VALUE, DataType.INTEGER_4BYTE)));

    //execution
    final var result = testUnit.createQueryToLoadEntity("eeeeeee1", tableInfo);

    //verification
    final var expectedResult = "SELECT Id, SaveStamp, name, year_of_birth FROM Cat WHERE Id = 'eeeeeee1';";
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_createQueryToLoadSchemaTimestamp() {

    //setup
    final var testUnit = new EntityQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadSchemaTimestamp();

    //verification setup
    final var expectedResult = "SELECT Value_ FROM DatabaseProperty WHERE ValueKey = 'SchemaTimestamp';";

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}
