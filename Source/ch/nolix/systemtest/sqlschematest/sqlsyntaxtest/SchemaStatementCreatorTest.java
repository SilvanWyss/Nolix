//package declaration
package ch.nolix.systemtest.sqlschematest.sqlsyntaxtest;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.DataTypeDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.system.sqlschema.sqlsyntax.SchemaStatementCreator;

//class
public final class SchemaStatementCreatorTest extends StandardTest {

  //method
  @TestCase
  public void testCase_createStatementToAddColumn() {

    //setup
    final var columnDto = ColumnDto.withNameAndDataType("Name", new DataTypeDto("nvarchar(100)"));
    final var testUnit = new SchemaStatementCreator();

    //execution
    final var result = testUnit.createStatementToAddColumn("Pet", columnDto);

    //setup verification
    expect(result).isEqualTo("ALTER TABLE Pet ADD Name nvarchar(100);");
  }

  //method
  @TestCase
  public void testCase_createStatementToAddTable() {

    //setup
    final var tableDto = TableDto.withNameAndColumn(
      "Pet",
      ColumnDto.withNameAndDataType("Name", new DataTypeDto("nvarchar(100)")),
      ColumnDto.withNameAndDataType("WeightInKilogram", new DataTypeDto("Float")));
    final var testUnit = new SchemaStatementCreator();

    //execution
    final var result = testUnit.createStatementToAddTable(tableDto);

    //setup verification
    expect(result).isEqualTo("CREATE TABLE Pet (Name nvarchar(100),WeightInKilogram Float);");
  }
}
