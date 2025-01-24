package ch.nolix.system.sqlrawschema.statementcreator;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.system.sqlrawschema.columntable.ContentModelSqlRecordMapper;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IAbstractReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableReferenceDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.ColumnTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableReferenceTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.statementcreatorapi.ISchemaStatementCreator;

public final class SchemaStatementCreator implements ISchemaStatementCreator {

  private static final ContentModelSqlRecordMapper PARAMETERIZED_FIELD_TYPE_SQL_RECORD_MAPPER = //
  new ContentModelSqlRecordMapper();

  @Override
  public IContainer<String> createStatementsToAddColumn(final String parentTableName, final ColumnDto column) {

    final ILinkedList<String> statements = LinkedList.createEmpty();
    final var contentModel = column.contentModel();

    final var contentModelSqlDto = //
    PARAMETERIZED_FIELD_TYPE_SQL_RECORD_MAPPER.mapContentModelDtoToContentModelSqlDto(contentModel);

    final var statement = //
    "INSERT INTO "
    + FixTableType.COLUMN.getQualifiedName()
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
    + " FROM "
    + FixTableType.TABLE.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableTableColumn.NAME.getName()
    + " = '"
    + parentTableName
    + "'";

    statements.addAtEnd(statement);

    if (contentModel instanceof final IAbstractReferenceModelDto abstractReferenceModel) {
      for (final var i : abstractReferenceModel.getReferencedTableIds()) {

        final var columnId = column.id();
        final var tableReference = new TableReferenceDto(columnId, i);
        final var addTableReferenceStatement = createStatementToAddTableReference(tableReference);

        statements.addAtEnd(addTableReferenceStatement);
      }
    }

    return statements;
  }

  @Override
  public ILinkedList<String> createStatementsToAddTable(final TableDto table) {

    final ILinkedList<String> statements = LinkedList.createEmpty();

    statements.addAtEnd(createStatementToAddTableIgnoringColumns(table));

    for (final var c : table.columns()) {
      statements.addAtEnd(createStatementsToAddColumn(table.name(), c));
    }

    return statements;
  }

  @Override
  public String createStatementToAddTableReference(final TableReferenceDto tableReferenceDto) {
    return //
    "INSERT INTO "
    + FixTableType.TABLE_REFERENCE.getName()
    + " ("
    + TableReferenceTableColumn.REFERENCE_COLUMN_ID.getName()
    + ", "
    + TableReferenceTableColumn.REFERENCED_TABLIE_ID.getName()
    + ") VALUES ('"
    + tableReferenceDto.referenceColumnId()
    + "', '"
    + tableReferenceDto.referencedTableId()
    + "');";
  }

  @Override
  public String createStatementToDeleteColumn(final String tableName, final String columnName) {
    return //
    "DELETE FROM "
    + FixTableType.COLUMN.getQualifiedName()
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
    + FixTableType.TABLE.getQualifiedName()
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
    + FixTableType.COLUMN.getQualifiedName()
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
  public String createStatementToSetColumnContentModel(final String columnId, final IContentModelDto contentModel) {

    final var contentModelSqlDto = //
    PARAMETERIZED_FIELD_TYPE_SQL_RECORD_MAPPER.mapContentModelDtoToContentModelSqlDto(contentModel);

    return //
    "UPDATE "
    + FixTableType.COLUMN.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalog.SET
    + ColumnTableColumn.DATA_TYPE
    + " = "
    + contentModelSqlDto.dataType()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID
    + " = "
    + contentModelSqlDto.backReferencedColumnId()
    + "WHERE"
    + ColumnTableColumn.ID
    + " = '"
    + columnId
    + "'";
  }

  @Override
  public String createStatementToSetTableName(final String tableName, final String newTableName) {
    return //
    "UPDATE "
    + FixTableType.TABLE.getQualifiedName()
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

  private String createStatementToAddTableIgnoringColumns(final TableDto tableDto) {
    return //
    "INSERT INTO "
    + FixTableType.TABLE.getQualifiedName()
    + " ("
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + ") VALUES ('"
    + tableDto.id()
    + "', '"
    + tableDto.name()
    + "')";
  }
}
