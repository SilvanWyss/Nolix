//package declaration
package ch.nolix.systemtest.sqldatabaserawdatatest.sqlsyntaxtest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.sqldatabaserawdata.schemainfo.ColumnInfo;
import ch.nolix.system.sqldatabaserawdata.schemainfo.TableInfo;
import ch.nolix.system.sqldatabaserawdata.sqlsyntax.EntityQueryCreator;
import ch.nolix.systemapi.entitypropertyapi.datatypeapi.DataType;
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//class
public final class EntityQueryCreatorTest extends Test {

  //method
  @TestCase
  public void testCase_createQueryToCountEntitiesWithGivenValueAtGivenColumn() {

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
  @TestCase
  public void testCase_createQueryToLoadEntitiesOfTable() {

    //setup
    final var testUnit = new EntityQueryCreator();
    final var tableInfo = new TableInfo(
      "ttttttt1",
      "Cat",
      ImmutableList.withElement(
        new ColumnInfo("ccccccc1", "name", PropertyType.VALUE, DataType.STRING, 0),
        new ColumnInfo("ccccccc2", "year_of_birth", PropertyType.VALUE, DataType.INTEGER_4BYTE, 0)));

    //execution
    final var result = testUnit.createQueryToLoadEntitiesOfTable(tableInfo);

    //verification
    final var expectedResult = "SELECT Id, SaveStamp, name, year_of_birth FROM ECat;";
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @TestCase
  public void testCase_createQueryToLoadEntity() {

    //setup
    final var testUnit = new EntityQueryCreator();
    final var tableInfo = new TableInfo(
      "ttttttt1",
      "Cat",
      ImmutableList.withElement(
        new ColumnInfo("ccccccc1", "name", PropertyType.VALUE, DataType.STRING, 0),
        new ColumnInfo("ccccccc2", "year_of_birth", PropertyType.VALUE, DataType.INTEGER_4BYTE, 0)));

    //execution
    final var result = testUnit.createQueryToLoadEntity("eeeeeee1", tableInfo);

    //verification
    final var expectedResult = "SELECT Id, SaveStamp, name, year_of_birth FROM ECat WHERE Id = 'eeeeeee1';";
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @TestCase
  public void testCase_createQueryToLoadSchemaTimestamp() {

    //setup
    final var testUnit = new EntityQueryCreator();

    //execution
    final var result = testUnit.createQueryToLoadSchemaTimestamp();

    //verification
    final var expectedResult = "SELECT Value FROM SDatabaseProperty WHERE ValueKey = 'SchemaTimestamp';";
    expect(result).isEqualTo(expectedResult);
  }
}
