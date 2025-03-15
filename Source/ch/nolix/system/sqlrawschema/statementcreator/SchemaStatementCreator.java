package ch.nolix.system.sqlrawschema.statementcreator;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.system.sqlrawschema.columntable.ContentModelSqlRecordMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.ColumnTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.statementcreatorapi.ISchemaStatementCreator;

public final class SchemaStatementCreator implements ISchemaStatementCreator {

  private static final ContentModelSqlRecordMapper CONTENT_MODEL_SQL_RECORD_MAPPER = new ContentModelSqlRecordMapper();

  @Override
  public String createStatementToAddColumn(final String tableName, final ColumnDto column) {

    final var contentModel = column.contentModel();
    final var contentModelSqlDto = CONTENT_MODEL_SQL_RECORD_MAPPER.mapContentModelDtoToContentModelSqlDto(contentModel);

    return //
    "INSERT INTO "
    + FixTableType.COLUMN.getName()
    + " ("
    + ColumnTableColumn.ID.getName()
    + ", "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.NAME.getName()
    + ", "
    + ColumnTableColumn.CONTENT_TYPE.getName()
    + ", "
    + ColumnTableColumn.DATA_TYPE.getName()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + ") SELECT '"
    + column.id()
    + "', "
    + TableTableColumn.ID.getName()
    + ", '"
    + column.name()
    + "', "
    + contentModelSqlDto.contentType()
    + ", "
    + contentModelSqlDto.dataType()
    + ", "
    + contentModelSqlDto.backReferencedColumnId()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableTableColumn.NAME.getName()
    + " = '"
    + tableName
    + "'";
  }

  @Override
  public String createStatementToAddTable(final String tableId, final String tableName) {
    return //
    "INSERT INTO "
    + FixTableType.TABLE.getName()
    + " ("
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
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
    + FixTableType.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + " = "
    + tableName
    + " AND "
    + ColumnTableColumn.NAME.getName()
    + " = '"
    + columnName
    + "'";
  }

  @Override
  public String createStatementToDeleteTable(final String tableName) {
    return //
    "DELETE FROM "
    + FixTableType.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableTableColumn.NAME
    + " = '"
    + tableName
    + "'";
  }

  @Override
  public String createStatementToSetColumnName(final String tableName, final String columnName,
    final String newColumnName) {
    return //
    "UPDATE "
    + FixTableType.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.SET
    + ColumnTableColumn.NAME
    + " = '"
    + newColumnName
    + "' WHERE "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableName
    + "' AND "
    + ColumnTableColumn.NAME.getName()
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
    + FixTableType.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.SET
    + ColumnTableColumn.DATA_TYPE
    + " = "
    + contentModelSqlDto.dataType()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + " = "
    + contentModelSqlDto.backReferencedColumnId()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + ColumnTableColumn.PARENT_TABLE_ID
    + " = '"
    + tableName
    + "' AND "
    + ColumnTableColumn.NAME.getName()
    + " = '"
    + columnName
    + "'";
  }

  @Override
  public String createStatementToSetTableName(final String tableName, final String newTableName) {
    return //
    "UPDATE "
    + FixTableType.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.SET
    + TableTableColumn.NAME.getName()
    + " = '"
    + newTableName
    + "' WHERE "
    + TableTableColumn.NAME.getName()
    + " = '"
    + tableName
    + "'";
  }
}
