package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.system.sqlmidschema.columntable.ContentModelSqlRecordMapper;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.sqlmidschema.databasestructure.BackReferenceableColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ReferenceableTableColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.TableColumn;
import ch.nolix.systemapi.sqlmidschema.statementcreator.ISchemaDataStatementCreator;

public final class SchemaDataStatementCreator implements ISchemaDataStatementCreator {
  private static final ContentModelSqlRecordMapper CONTENT_MODEL_SQL_RECORD_MAPPER = new ContentModelSqlRecordMapper();

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
  public IContainer<String> createStatementsToAddColumn(final String tableName, final ColumnDto column) {
    final ILinkedList<String> statements = LinkedList.createEmpty();
    final var contentModel = column.contentModel();
    final var contentModelSqlDto = CONTENT_MODEL_SQL_RECORD_MAPPER.mapContentModelDtoToContentModelSqlDto(contentModel);

    final var createStatementToAddColumnForColumnTable = //
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
    + ", "
    + ColumnColumn.BACK_REFERENCED_COLUM_ID.getName()
    + ") SELECT '"
    + column.id()
    + "', "
    + TableColumn.ID.getName()
    + ", '"
    + column.name()
    + "', "
    + contentModelSqlDto.fieldType()
    + ", "
    + contentModelSqlDto.dataType()
    + ", "
    + contentModelSqlDto.backReferencedColumnId()
    + " FROM "
    + FixTable.TABLE.getName()
    + " WHERE "
    + TableColumn.NAME.getName()
    + " = '"
    + tableName
    + "'";

    statements.addAtEnd(createStatementToAddColumnForColumnTable);

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
      statements.addAtEnd(createStatementsToAddColumn(table.name(), c));
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
    final var contentModelSqlDto = CONTENT_MODEL_SQL_RECORD_MAPPER.mapContentModelDtoToContentModelSqlDto(contentModel);

    return //
    "UPDATE "
    + FixTable.COLUMN.getName()
    + " SET "
    + ColumnColumn.DATA_TYPE
    + " = "
    + contentModelSqlDto.dataType()
    + ", "
    + ColumnColumn.BACK_REFERENCED_COLUM_ID.getName()
    + " = "
    + contentModelSqlDto.backReferencedColumnId()
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
}
