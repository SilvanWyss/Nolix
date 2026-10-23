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
  /**
   * @param table
   * @param column
   * @return the statements to add the given column to the given table
   */
  ExtendedIterable<String> createStatementsToAddColumn(TableIdentification table, ColumnDto column);

  /**
   * @param table
   * @return the statements to add the given table
   */
  ExtendedIterable<String> createStatementsToAddTable(TableDto table);

  /**
   * @param table
   * @param column
   * @param fieldType
   * @param dataType
   * @param referenceableTableIds
   * @param backReferenceableColumnIds
   * @return the statements to set the given fieldType, dataType,
   *         referenceableTableIds and backReferenceableColumnIds as content model
   *         of the given column of the given table
   */
  ExtendedIterable<String> createStatementsToSetContentModel(
    TableIdentification table,
    ColumnIdentification column,
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<String> referenceableTableIds,
    ExtendedIterable<String> backReferenceableColumnIds);

  /**
   * @param parentBaseBackReferenceColumn
   * @param referenceableColumnId
   * @return a statement to add the column with the given referenceableColumnId as
   *         back referenceable column to the given parentBaseBackReferenceColumn
   */
  String createStatementToAddBackReferenceableColumn(
    ColumnIdentification parentBaseBackReferenceColumn,
    String referenceableColumnId);

  /**
   * @param parentBaseReferenceColumn
   * @param referenceableTableId
   * @return a statement to add the table with the given referenceableTableId as
   *         referenceable table to the given parentBaseReferenceColumn
   */
  String createStatementToAddReferenceableTable(
    ColumnIdentification parentBaseReferenceColumn,
    String referenceableTableId);

  /**
   * @param tableId
   * @param tableName
   * @return a statement to add a table with the given tableId and tableName
   */
  String createStatementToAddTable(String tableId, String tableName);

  /**
   * @param table
   * @param columnName
   * @return a statement to delete the column with the given columnName from the
   *         given table
   */
  String createStatementToDeleteColumn(TableIdentification table, String columnName);

  /**
   * @param tableName
   * @return a statement to delete the table with the given tableName
   */
  String createStatementToDeleteTable(String tableName);

  /**
   * @param tableName
   * @param columnName
   * @param newColumnName
   * @return a statement to rename the column with the given columnName of the
   *         table with the given tableName to the given newColumnName
   */
  String createStatementToRenameColumn(String tableName, String columnName, String newColumnName);

  /**
   * @param tableName
   * @param newTableName
   * @return a statement to rename the table with the given tableName to the given
   *         newTableName
   */
  String createStatementToRenameTable(String tableName, String newTableName);
}
