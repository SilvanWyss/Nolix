package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

public interface ISchemaDataStatementCreator {
  String createStatementToAddBackReferenceableColumn(
    ColumnIdentification parentBaseBackReferenceColumn,
    String referenceableColumnId);

  IContainer<String> createStatementsToAddColumn(TableIdentification table, ColumnDto column);

  String createStatementToAddReferenceableTable(
    ColumnIdentification parentBaseReferenceColumn,
    String referenceableTableId);

  String createStatementToAddTable(String tableId, String tableName);

  IContainer<String> createStatementsToAddTable(TableDto table);

  String createStatementToDeleteColumn(TableIdentification table, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  String createStatementToRenameTable(String tableName, String newTableName);

  IContainer<String> createStatementsToSetContentModel(
    TableIdentification table,
    ColumnIdentification column,
    FieldType fieldType,
    DataType dataType,
    IContainer<String> referenceableTableIds,
    IContainer<String> backReferenceableColumnIds);
}
