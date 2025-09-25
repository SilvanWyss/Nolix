package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

public interface ISchemaDataStatementCreator {
  String createStatementToAddBackReferenceableColumn(
    String parentBaseBackReferenceColumnId,
    String referenceableColumnId);

  IContainer<String> createStatementsToAddColumn(TableIdentification table, ColumnDto column);

  String createStatementToAddReferenceableTable(String parentBaseReferenceColumnId, String referenceableTableId);

  String createStatementToAddTable(String tableId, String tableName);

  IContainer<String> createStatementsToAddTable(TableDto table);

  String createStatementToDeleteColumn(TableIdentification table, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  String createStatementToRenameTable(String tableName, String newTableName);

  String createStatementToSetContentModel(String tableName, String columnName, ContentModelDto contentModel);
}
