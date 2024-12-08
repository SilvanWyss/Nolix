package ch.nolix.systemtest.noderawschematest.schemaadaptertest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.system.noderawschema.schemaadapter.SchemaAdapter;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.system.objectschema.schemadto.TableDto;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

final class SchemaAdapterTest extends StandardTest {

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
      new BaseParameterizedValueTypeDto(ContentType.VALUE, DataType.INTEGER_4BYTE));

    //Setups testUnit.
    final var testUnit = SchemaAdapter.forNodeDatabase(nodeDatabase);
    testUnit.addTable(tableDto);

    //execution
    testUnit.addColumn(tableName, columnDto);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree());
    expect(testUnit.loadColumnsByTableName(tableName)).contains(t -> t.getName().equals(columnName));
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
    final var testUnit = SchemaAdapter.forNodeDatabase(nodeDatabase);

    //setup verification
    expect(testUnit.isChangeFree());

    //execution
    testUnit.addTable(tableDto);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree());
    expect(testUnit.loadTables()).contains(t -> t.getName().equals(tableName));
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
      new BaseParameterizedValueTypeDto(ContentType.VALUE, DataType.INTEGER_4BYTE));

    //Setups testUnit.
    final var testUnit = SchemaAdapter.forNodeDatabase(nodeDatabase);
    testUnit.addTable(tableDto);
    testUnit.addColumn(tableName, columnDto);
    testUnit.saveChanges();

    //execution
    testUnit.deleteColumn(tableName, columnName);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree());
    expect(testUnit.loadColumnsByTableName(tableName)).isEmpty();
  }

  @Test
  void testCase_deleteTable() {

    //parameter definition
    final var tableName = "table_name";

    //Setups nodeDatabase.
    final var nodeDatabase = MutableNode.createEmpty();

    //Setups testUnit.
    final var testUnit = SchemaAdapter.forNodeDatabase(nodeDatabase);

    //Setups tableDto.
    final var tableDto = new TableDto("table_id", tableName, ImmutableList.createEmpty());
    testUnit.addTable(tableDto);
    testUnit.saveChanges();

    //execution
    testUnit.deleteTable(tableName);
    testUnit.saveChanges();

    //verification
    expect(testUnit.isChangeFree());
    expect(testUnit.loadTables()).isEmpty();
  }
}
