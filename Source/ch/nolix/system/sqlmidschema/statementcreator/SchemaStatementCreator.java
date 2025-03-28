package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.system.sqlmidschema.columntable.ContentModelSqlRecordMapper;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.TableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi.ISchemaStatementCreator;

public final class SchemaStatementCreator implements ISchemaStatementCreator {

  private static final ContentModelSqlRecordMapper CONTENT_MODEL_SQL_RECORD_MAPPER = new ContentModelSqlRecordMapper();

  @Override
  public String createStatementToAddColumn(final String tableName, final ColumnDto column) {

    final var contentModel = column.contentModel();
    final var contentModelSqlDto = CONTENT_MODEL_SQL_RECORD_MAPPER.mapContentModelDtoToContentModelSqlDto(contentModel);

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
    + ColumnColumn.CONTENT_TYPE.getName()
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
    + contentModelSqlDto.contentType()
    + ", "
    + contentModelSqlDto.dataType()
    + ", "
    + contentModelSqlDto.backReferencedColumnId()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableColumn.NAME.getName()
    + " = '"
    + tableName
    + "'";
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
      statements.addAtEnd(createStatementToAddColumn(table.name(), c));
    }

    return statements;
  }

  @Override
  public String createStatementToDeleteColumn(final String tableName, final String columnName) {
    return //
    "DELETE FROM "
    + FixTable.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
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
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableColumn.NAME
    + " = '"
    + tableName
    + "'";
  }

  @Override
  public String createStatementToSetColumnName(final String tableName, final String columnName,
    final String newColumnName) {
    return //
    "UPDATE "
    + FixTable.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.SET
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
    final IContentModelDto contentModel) {

    final var contentModelSqlDto = CONTENT_MODEL_SQL_RECORD_MAPPER.mapContentModelDtoToContentModelSqlDto(contentModel);

    return //
    "UPDATE "
    + FixTable.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.SET
    + ColumnColumn.DATA_TYPE
    + " = "
    + contentModelSqlDto.dataType()
    + ", "
    + ColumnColumn.BACK_REFERENCED_COLUM_ID.getName()
    + " = "
    + contentModelSqlDto.backReferencedColumnId()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
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
  public String createStatementToSetTableName(final String tableName, final String newTableName) {
    return //
    "UPDATE "
    + FixTable.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.SET
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
