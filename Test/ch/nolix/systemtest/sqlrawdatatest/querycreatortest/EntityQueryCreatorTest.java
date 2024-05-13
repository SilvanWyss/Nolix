//package declaration
package ch.nolix.systemtest.sqlrawdatatest.querycreatortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.sqlrawdata.querycreator.EntityQueryCreator;
import ch.nolix.system.sqlrawdata.schemainfo.ColumnInfo;
import ch.nolix.system.sqlrawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

//class
final class EntityQueryCreatorTest extends StandardTest {

  //method
  @Test
  void testCase_createQueryToCountEntitiesWithGivenValueAtGivenColumn() {

    //setup
    final var testUnit = new EntityQueryCreator();

    //execution
    final var result = testUnit.createQueryToCountEntitiesWithGivenValueAtGivenColumn("MyTable", "MyColumn",
      "my_value");

    //verification
    final var expectedResult = "SELECT COUNT(MyColumn) FROM EMyTable WHERE MyColumn = 'my_value';";
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_createQueryToLoadEntitiesOfTable() {

    //setup
    final var testUnit = new EntityQueryCreator();
    final var tableInfo = new TableInfo(
      "ttttttt1",
      "Cat",
      ImmutableList.withElement(
        new ColumnInfo("ccccccc1", "name", ContentType.VALUE, DataType.STRING, 0),
        new ColumnInfo("ccccccc2", "year_of_birth", ContentType.VALUE, DataType.INTEGER_4BYTE, 0)));

    //execution
    final var result = testUnit.createQueryToLoadEntitiesOfTable(tableInfo);

    //verification
    final var expectedResult = "SELECT Id, SaveStamp, name, year_of_birth FROM ECat;";
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_createQueryToLoadEntity() {

    //setup
    final var testUnit = new EntityQueryCreator();
    final var tableInfo = new TableInfo(
      "ttttttt1",
      "Cat",
      ImmutableList.withElement(
        new ColumnInfo("ccccccc1", "name", ContentType.VALUE, DataType.STRING, 0),
        new ColumnInfo("ccccccc2", "year_of_birth", ContentType.VALUE, DataType.INTEGER_4BYTE, 0)));

    //execution
    final var result = testUnit.createQueryToLoadEntity("eeeeeee1", tableInfo);

    //verification
    final var expectedResult = "SELECT Id, SaveStamp, name, year_of_birth FROM ECat WHERE Id = 'eeeeeee1';";
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_createQueryToLoadSchemaTimestamp() {

    //setup
    final var testUnit = new EntityQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadSchemaTimestamp();

    //verification
    final var expectedResult = "SELECT Value FROM MDatabaseProperty WHERE ValueKey = 'SchemaTimestamp';";
    expect(result).isEqualTo(expectedResult);
  }
}
