package ch.nolix.system.nodemidschema.adapter;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ValueModelDto;

final class NodeSchemaAdapterTest extends StandardTest {

  @Test
  void testCase_addColumn() {

    //parameter definition
    final var tableName = "table_name";
    final var columnName = "column_name";

    //Setups nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();

    //Setups tableDto.
    final var tableDto = new TableDto("table_id", tableName, ImmutableList.createEmpty());

    //Setups columnDto
    final var columnDto = //
    new ColumnDto(
      "column_id",
      columnName,
      new ValueModelDto(DataType.INTEGER_4BYTE));

    //Setups testUnit.
    final var testUnit = NodeSchemaAdapter.forNodeDatabase(nodeDatabase);
    testUnit.addTable(tableDto);

    //execution
    testUnit.addColumn(tableName, columnDto);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree()).isTrue();
    expect(testUnit.loadTableByName(tableName).columns()).contains(c -> c.name().equals(columnName));
  }

  @Test
  void testCase_addTable() {

    //parameter definition
    final var tableName = "table_name";

    //Setups nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();

    //Setups tableDto.
    final var tableDto = new TableDto("table_id", tableName, ImmutableList.createEmpty());

    //Setups testUnit.
    final var testUnit = NodeSchemaAdapter.forNodeDatabase(nodeDatabase);

    //setup verification
    expect(testUnit.isChangeFree()).isTrue();

    //execution
    testUnit.addTable(tableDto);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree());
    expect(testUnit.loadTables()).contains(t -> t.name().equals(tableName));
  }

  @Test
  void testCase_deleteColumn() {

    //parameter definition
    final var tableName = "table_name";
    final var columnName = "column_name";

    //Setups nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();

    //Setups tableDto.
    final var tableDto = new TableDto("table_id", tableName, ImmutableList.createEmpty());

    //Setups columnDto
    final var columnDto = //
    new ColumnDto(
      "column_id",
      columnName,
      new ValueModelDto(DataType.INTEGER_4BYTE));

    //Setups testUnit.
    final var testUnit = NodeSchemaAdapter.forNodeDatabase(nodeDatabase);
    testUnit.addTable(tableDto);
    testUnit.addColumn(tableName, columnDto);
    testUnit.saveChanges();

    //execution
    testUnit.deleteColumn(tableName, columnName);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree()).isTrue();
    expect(testUnit.loadTableByName(tableName).columns()).isEmpty();
  }

  @Test
  void testCase_deleteTable() {

    //parameter definition
    final var tableName = "table_name";

    //Setups nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();

    //Setups testUnit.
    final var testUnit = NodeSchemaAdapter.forNodeDatabase(nodeDatabase);

    //Setups tableDto.
    final var tableDto = new TableDto("table_id", tableName, ImmutableList.createEmpty());
    testUnit.addTable(tableDto);
    testUnit.saveChanges();

    //execution
    testUnit.deleteTable(tableName);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree()).isTrue();
    expect(testUnit.loadTables()).isEmpty();
  }
}
