/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.ColumnColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.SchemaTable;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IColumnTableStatementCreator;

/**
 * @author Silvan Wyss
 */
public final class ColumnTableStatementCreator implements IColumnTableStatementCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToAddColumnIntoColumnTable(final TableIdentification table, final ColumnDto column) {
    return //
    "INSERT INTO "
    + SchemaTable.COLUMN
    + " ("
    + ColumnColumn.ID
    + ", "
    + ColumnColumn.PARENT_TABLE_ID
    + ", "
    + ColumnColumn.NAME
    + ", "
    + ColumnColumn.FIELD_TYPE
    + ", "
    + ColumnColumn.DATA_TYPE
    + ") VALUES ('"
    + column.id()
    + "', '"
    + table.tableId()
    + "', '"
    + column.name()
    + "', '"
    + column.fieldType().name()
    + "', '"
    + column.dataType()
    + "');";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToSetContentModelInColumnTable(
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType) {
    return //
    "UPDATE "
    + SchemaTable.COLUMN
    + " SET "
    + ColumnColumn.FIELD_TYPE
    + " = '"
    + fieldType.name()
    + "', "
    + ColumnColumn.DATA_TYPE
    + " = '"
    + dataType.name()
    + "' WHERE "
    + ColumnColumn.ID
    + " = '"
    + column.columnId()
    + "';";
  }
}
