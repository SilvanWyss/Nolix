package ch.nolix.systemtest.nodemidschema.adapter;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.system.nodemidschema.adapter.NodeSchemaAdapter;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

/**
 * @author Silvan Wyss
 */
final class NodeSchemaAdapterTest extends StandardTest {
  @Test
  void testCase_addColumn() {
    //parameter definition
    final var tableId = "table_id";
    final var tableName = "table_name";
    final var columnName = "column_name";

    //Setups nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();

    //Setups tableDto.
    final var tableDto = new TableDto(tableId, tableName, ImmutableList.createEmpty());

    //Setups columnDto.
    final var columnDto = //
    new ColumnDto(
      "column_id",
      columnName,
      FieldType.VALUE_FIELD,
      DataType.INTEGER_4BYTE,
      ImmutableList.createEmpty(),
      ImmutableList.createEmpty());

    //Setups testUnit.
    final var testUnit = NodeSchemaAdapter.forNodeDatabase(nodeDatabase);
    testUnit.addTable(tableDto);

    //execution
    testUnit.addColumn(new TableIdentification(tableId, tableName), columnDto);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree()).isTrue();
    expect(testUnit.loadTable(tableName).columns()).contains(c -> c.name().equals(columnName));
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
    final var tableId = "table_id";
    final var tableName = "table_name";
    final var columnName = "column_name";

    //Setups nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();

    //Setups tableDto.
    final var tableDto = new TableDto(tableId, tableName, ImmutableList.createEmpty());

    //Setups columnDto
    final var columnDto = //
    new ColumnDto(
      "column_id",
      columnName,
      FieldType.VALUE_FIELD,
      DataType.INTEGER_4BYTE,
      ImmutableList.createEmpty(),
      ImmutableList.createEmpty());

    //Setups testUnit.
    final var testUnit = NodeSchemaAdapter.forNodeDatabase(nodeDatabase);
    testUnit.addTable(tableDto);
    testUnit.addColumn(new TableIdentification(tableId, tableName), columnDto);
    testUnit.saveChanges();

    //execution
    testUnit.deleteColumn(new TableIdentification(tableId, tableName), columnName);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree()).isTrue();
    expect(testUnit.loadTable(tableName).columns()).isEmpty();
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
