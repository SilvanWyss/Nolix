//package declaration
package ch.nolix.systemtest.sqldatabasebasicschematest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.DataTypeDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDto;
import ch.nolix.system.sqldatabasebasicschema.sqlsyntax.SchemaStatementCreator;

//class
public final class SchemaStatementCreatorTest extends Test {

  // method
  @TestCase
  public void testCase_createStatementToAddColumn() {

    // setup
    final var columnDto = new ColumnDto("Name", new DataTypeDto("nvarchar(100)"));
    final var testUnit = new SchemaStatementCreator();

    // execution
    final var result = testUnit.createStatementToAddColumn("Pet", columnDto);

    // setup verification
    expect(result).isEqualTo("ALTER TABLE Pet ADD Name nvarchar(100);");
  }

  // method
  @TestCase
  public void testCase_createStatementToAddTable() {

    // setup
    final var tableDto = new TableDto(
        "Pet",
        new ColumnDto("Name", new DataTypeDto("nvarchar(100)")),
        new ColumnDto("WeightInKilogram", new DataTypeDto("Float")));
    final var testUnit = new SchemaStatementCreator();

    // execution
    final var result = testUnit.createStatementToAddTable(tableDto);

    // setup verification
    expect(result).isEqualTo("CREATE TABLE Pet (Name nvarchar(100),WeightInKilogram Float);");
  }
}
