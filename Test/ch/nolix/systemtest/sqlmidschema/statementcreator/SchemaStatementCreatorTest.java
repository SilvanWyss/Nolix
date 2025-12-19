package ch.nolix.systemtest.sqlmidschema.statementcreator;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.sqlschema.statementcreator.StatementCreator;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.DataTypeDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;

/**
 * @author Silvan Wyss
 */
final class SchemaStatementCreatorTest extends StandardTest {
  @Test
  void testCase_createStatementToAddColumn() {
    //setup
    final var dataTypeDto = new DataTypeDto("nvarchar", "100");
    final var columnDto = new ColumnDto("Name", dataTypeDto, ImmutableList.createEmpty());
    final var testUnit = new StatementCreator();

    //execution
    final var result = testUnit.createStatementToAddColumn("Pet", columnDto);

    //verification
    expect(result).isEqualTo("ALTER TABLE Pet ADD Name nvarchar(100);");
  }

  @Test
  void testCase_createStatementToAddTable() {
    //setup
    final var tableDto = new TableDto(
      "Pet",
      ImmutableList.withElements(
        new ColumnDto("Name", new DataTypeDto("nvarchar", "100"), ImmutableList.createEmpty()),
        new ColumnDto("WeightInKilogram", new DataTypeDto("float", null), ImmutableList.createEmpty())));
    final var testUnit = new StatementCreator();

    //execution
    final var result = testUnit.createStatementToAddTable(tableDto);

    //verification
    expect(result).isEqualTo("CREATE TABLE Pet (Name nvarchar(100), WeightInKilogram float);");
  }
}
