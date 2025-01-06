package ch.nolix.systemtest.sqlschematest.sqlsyntaxtest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlschema.statementcreator.StatementCreator;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.DataTypeDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;

final class SchemaStatementCreatorTest extends StandardTest {

  @Test
  void testCase_createStatementToAddColumn() {

    //setup
    final var dataTypeDto = DataTypeDto.withNameAndParameter("nvarchar", "100");
    final var columnDto = ColumnDto.withNameAndDataType("Name", dataTypeDto);
    final var testUnit = new StatementCreator();

    //execution
    final var result = testUnit.createStatementToAddColumn("Pet", columnDto);

    //setup verification
    expect(result).isEqualTo("ALTER TABLE Pet ADD Name nvarchar(100);");
  }

  @Test
  void testCase_createStatementToAddTable() {

    //setup
    final var tableDto = TableDto.withNameAndColumn(
      "Pet",
      ColumnDto.withNameAndDataType("Name", DataTypeDto.withNameAndParameter("nvarchar", "100")),
      ColumnDto.withNameAndDataType("WeightInKilogram", DataTypeDto.withName("Float")));
    final var testUnit = new StatementCreator();

    //execution
    final var result = testUnit.createStatementToAddTable(tableDto);

    //setup verification
    expect(result).isEqualTo("CREATE TABLE Pet (Name nvarchar(100),WeightInKilogram Float);");
  }
}
