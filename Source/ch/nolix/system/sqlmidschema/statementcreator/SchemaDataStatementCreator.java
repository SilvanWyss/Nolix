package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.TableIdentification;
import ch.nolix.systemapi.sqlmidschema.databasestructure.BackReferenceableColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ReferenceableTableColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.TableColumn;
import ch.nolix.systemapi.sqlmidschema.statementcreator.ISchemaDataStatementCreator;

public final class SchemaDataStatementCreator implements ISchemaDataStatementCreator {
  @Override
  public String createStatementToAddBackReferenceableColumn(
    final String parentBaseBackReferenceColumnId,
    final String referenceableColumnId) {
    return //
    "INSERT INTO "
    + FixTable.BACK_REFERENCEABLE_COLUMN.getName()
    + " ("
    + BackReferenceableColumnColumn.PARENT_BASE_BACK_REFERENCE_COLUMN_ID.getName()
    + ", "
    + BackReferenceableColumnColumn.BACK_REFERENCEABLE_COLUMN_ID.getName()
    + ") VALUES ('"
    + parentBaseBackReferenceColumnId
    + "', '"
    + referenceableColumnId
    + "');";
  }

  @Override
  public IContainer<String> createStatementsToAddColumn(final TableIdentification table, final ColumnDto column) {
    final ILinkedList<String> statements = LinkedList.createEmpty();
    final var columnId = column.id();

    statements.addAtEnd(createStatementToAddColumnIntoColumnTable(table, column));

    for (final var t : column.referenceableTableIds()) {
      statements.addAtEnd(createStatementToAddReferenceableTable(columnId, t));
    }

    for (final var c : column.backReferenceableColumnIds()) {
      statements.addAtEnd(createStatementToAddBackReferenceableColumn(columnId, c));
    }

    return statements;
  }

  @Override
  public String createStatementToAddReferenceableTable(
    final String parentBaseReferenceColumnId,
    final String referenceableTableId) {
    return //
    "INSERT INTO "
    + FixTable.REFERENCEABLE_TABLE.getName()
    + " ("
    + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + ", "
    + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID.getName()
    + ") VALUES ('"
    + parentBaseReferenceColumnId
    + "', '"
    + referenceableTableId
    + "');";
  }

  @Override
  public String createStatementToAddTable(final String tableId, final String tableName) {
    return //
    "INSERT INTO "
    + FixTable.TABLE.getName()
    + " ("
    + TableColumn.ID.getName()
    + ", "
    + TableColumn.NAME.getName()
    + ") VALUES ('"
    + tableId
    + "', '"
    + tableName
    + "')";
  }

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

  @Override
  public String createStatementToDeleteColumn(final String tableName, final String columnName) {
    return //
    "DELETE FROM "
    + FixTable.COLUMN.getName()
    + " WHERE "
    + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = "
    + tableName
    + " AND "
    + ColumnColumn.NAME.getName()
    + " = '"
    + columnName
    + "'";
  }

  @Override
  public String createStatementToDeleteTable(final String tableName) {
    return //
    "DELETE FROM "
    + FixTable.TABLE.getName()
    + " WHERE "
    + TableColumn.NAME
    + " = '"
    + tableName
    + "'";
  }

  @Override
  public String createStatementToRenameColumn(final String tableName, final String columnName,
    final String newColumnName) {
    return //
    "UPDATE "
    + FixTable.COLUMN.getName()
    + " SET "
    + ColumnColumn.NAME
    + " = '"
    + newColumnName
    + "' WHERE "
    + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableName
    + "' AND "
    + ColumnColumn.NAME.getName()
    + " = '"
    + columnName
    + "'";
  }

  @Override
  public String createStatementToSetContentModel(
    final String tableName,
    final String columnName,
    final ContentModelDto contentModel) {

    return //
    "UPDATE "
    + FixTable.COLUMN.getName()
    + " SET "
    + ColumnColumn.DATA_TYPE
    + " = "
    + contentModel.dataType()
    + " WHERE "
    + ColumnColumn.PARENT_TABLE_ID
    + " = '"
    + tableName
    + "' AND "
    + ColumnColumn.NAME.getName()
    + " = '"
    + columnName
    + "'";
  }

  @Override
  public String createStatementToRenameTable(final String tableName, final String newTableName) {
    return //
    "UPDATE "
    + FixTable.TABLE.getName()
    + " SET "
    + TableColumn.NAME.getName()
    + " = '"
    + newTableName
    + "' WHERE "
    + TableColumn.NAME.getName()
    + " = '"
    + tableName
    + "'";
  }

  private String createStatementToAddColumnIntoColumnTable(final TableIdentification table, final ColumnDto column) {
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
}
