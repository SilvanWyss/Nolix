/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.BackReferenceableColumnColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.ColumnColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.ReferenceableTableColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.TableColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.SchemaTable;
import ch.nolix.systemapi.sqlmidschema.statementcreator.ISchemaDataStatementCreator;

/**
 * @author Silvan Wyss
 */
public final class SchemaDataStatementCreator implements ISchemaDataStatementCreator {
  private static final ColumnTableStatementCreator COLUMN_TABLE_STATEMENT_CREATOR = new ColumnTableStatementCreator();

  @Override
  public String createStatementToAddBackReferenceableColumn(
    final ColumnIdentification parentBaseBackReferenceColumn,
    final String referenceableColumnId) {
    return //
    "INSERT INTO "
    + SchemaTable.BACK_REFERENCEABLE_COLUMN
    + " ("
    + BackReferenceableColumnColumn.PARENT_BASE_BACK_REFERENCE_COLUMN_ID
    + ", "
    + BackReferenceableColumnColumn.BACK_REFERENCEABLE_COLUMN_ID
    + ") VALUES ('"
    + parentBaseBackReferenceColumn.columnId()
    + "', '"
    + referenceableColumnId
    + "');";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<String> createStatementsToAddColumn(final TableIdentification table, final ColumnDto column) {
    final ILinkedList<String> statements = LinkedList.createEmpty();
    final var columnId = column.id();
    final var columnName = column.name();
    final var columnIdentification = new ColumnIdentification(columnId, columnName);

    statements.addAtEnd(COLUMN_TABLE_STATEMENT_CREATOR.createStatementToAddColumnIntoColumnTable(table, column));

    for (final var t : column.referenceableTableIds()) {
      statements.addAtEnd(createStatementToAddReferenceableTable(columnIdentification, t));
    }

    for (final var c : column.backReferenceableColumnIds()) {
      statements.addAtEnd(createStatementToAddBackReferenceableColumn(columnIdentification, c));
    }

    return statements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToAddReferenceableTable(
    final ColumnIdentification parentBaseReferenceColumn,
    final String referenceableTableId) {
    return //
    "INSERT INTO "
    + SchemaTable.REFERENCEABLE_TABLE
    + " ("
    + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ", "
    + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID
    + ") VALUES ('"
    + parentBaseReferenceColumn.columnId()
    + "', '"
    + referenceableTableId
    + "');";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToAddTable(final String tableId, final String tableName) {
    return //
    "INSERT INTO "
    + SchemaTable.TABLE
    + " ("
    + TableColumn.ID
    + ", "
    + TableColumn.NAME
    + ") VALUES ('"
    + tableId
    + "', '"
    + tableName
    + "')";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILinkedList<String> createStatementsToAddTable(final TableDto table) {
    final ILinkedList<String> statements = LinkedList.createEmpty();

    statements.addAtEnd(createStatementToAddTable(table.id(), table.name()));

    for (final var c : table.columns()) {
      final var tableId = table.id();
      final var tablename = table.name();
      final var tableIdentification = new TableIdentification(tableId, tablename);
      statements.addAtEnd(createStatementsToAddColumn(tableIdentification, c));
    }

    return statements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteColumn(final TableIdentification table, final String columnName) {
    return //
    "DELETE FROM "
    + SchemaTable.COLUMN
    + " WHERE "
    + ColumnColumn.PARENT_TABLE_ID
    + " = '"
    + table.tableId()
    + "' AND "
    + ColumnColumn.NAME
    + " = '"
    + columnName
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteTable(final String tableName) {
    return //
    "DELETE FROM "
    + SchemaTable.TABLE
    + " WHERE "
    + TableColumn.NAME
    + " = '"
    + tableName
    + "'";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToRenameColumn(final String tableName, final String columnName,
    final String newColumnName) {
    return //
    "UPDATE "
    + SchemaTable.COLUMN
    + " SET "
    + ColumnColumn.NAME
    + " = '"
    + newColumnName
    + "' WHERE "
    + ColumnColumn.PARENT_TABLE_ID
    + " = '"
    + tableName
    + "' AND "
    + ColumnColumn.NAME
    + " = '"
    + columnName
    + "'";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToRenameTable(final String tableName, final String newTableName) {
    return //
    "UPDATE "
    + SchemaTable.TABLE
    + " SET "
    + TableColumn.NAME
    + " = '"
    + newTableName
    + "' WHERE "
    + TableColumn.NAME
    + " = '"
    + tableName
    + "'";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<String> createStatementsToSetContentModel(
    final TableIdentification table,
    final ColumnIdentification column,
    final FieldType fieldType,
    final DataType dataType,
    final ExtendedIterable<String> referenceableTableIds,
    final ExtendedIterable<String> backReferenceableColumnIds) {
    final ILinkedList<String> statements = LinkedList.createEmpty();

    final var statementToSetContentModelInColumnTable = // 
    COLUMN_TABLE_STATEMENT_CREATOR.createStatementToSetContentModelInColumnTable(column, fieldType, dataType);

    statements.addAtEnd(statementToSetContentModelInColumnTable);

    final var statementsToAddReferenceableTables = //
    referenceableTableIds.getViewOf(t -> createStatementToAddReferenceableTable(column, t));

    statements.addAtEnd(statementsToAddReferenceableTables);

    final var statementsToAddBackReferenceableColumns = //
    backReferenceableColumnIds.getViewOf(c -> createStatementToAddBackReferenceableColumn(column, c));

    statements.addAtEnd(statementsToAddBackReferenceableColumns);

    return statements;
  }

}
