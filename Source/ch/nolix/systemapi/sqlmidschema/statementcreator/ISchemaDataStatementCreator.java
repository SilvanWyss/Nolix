/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

/**
 * @author Silvan Wyss
 */
public interface ISchemaDataStatementCreator {
  ExtendedIterable<String> createStatementsToAddColumn(TableIdentification table, ColumnDto column);

  ExtendedIterable<String> createStatementsToAddTable(TableDto table);

  ExtendedIterable<String> createStatementsToSetContentModel(
    TableIdentification table,
    ColumnIdentification column,
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<String> referenceableTableIds,
    ExtendedIterable<String> backReferenceableColumnIds);

  String createStatementToAddBackReferenceableColumn(
    ColumnIdentification parentBaseBackReferenceColumn,
    String referenceableColumnId);

  String createStatementToAddReferenceableTable(
    ColumnIdentification parentBaseReferenceColumn,
    String referenceableTableId);

  String createStatementToAddTable(String tableId, String tableName);

  String createStatementToDeleteColumn(TableIdentification table, String columnName);

  String createStatementToDeleteTable(String tableName);

  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  String createStatementToRenameTable(String tableName, String newTableName);
}
