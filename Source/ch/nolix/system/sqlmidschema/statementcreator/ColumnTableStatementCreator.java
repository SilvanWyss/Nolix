/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
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
    + FixTable.COLUMN.getName()
    + " ("
    + ColumnColumn.ID.getName()
    + ", "
    + ColumnColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnColumn.NAME.getName()
    + ", "
    + ColumnColumn.FIELD_TYPE.getName()
    + ", "
    + ColumnColumn.DATA_TYPE.getName()
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
    + FixTable.COLUMN.getName()
    + " SET "
    + ColumnColumn.FIELD_TYPE.getName()
    + " = '"
    + fieldType.name()
    + "', "
    + ColumnColumn.DATA_TYPE.getName()
    + " = '"
    + dataType.name()
    + "' WHERE "
    + ColumnColumn.ID
    + " = '"
    + column.columnId()
    + "';";
  }
}
