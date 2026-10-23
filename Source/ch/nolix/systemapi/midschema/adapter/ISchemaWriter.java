/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.midschema.adapter;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

/**
 * @author Silvan Wyss
 */
public interface ISchemaWriter extends IResettableChangeSaver {
  /**
   * Adds the given column to the given table.
   * 
   * @param table
   * @param column
   */
  void addColumn(TableIdentification table, ColumnDto column);

  /**
   * Adds the given table.
   * 
   * @param table
   */
  void addTable(TableDto table);

  /**
   * Deletes the column with the given columnName from the given table.
   * 
   * @param table
   * @param columnName
   */
  void deleteColumn(TableIdentification table, String columnName);

  /**
   * Deletes the table with the given tableName.
   * 
   * @param tableName
   */
  void deleteTable(String tableName);

  /**
   * Renames the column with the given columnName of the table with the given
   * tableName to the given newColumnName.
   * 
   * @param tableName
   * @param columnName
   * @param newColumnName
   */
  void renameColumn(String tableName, String columnName, String newColumnName);

  /**
   * Renames the table with the given tableName to the given newTableName.
   * 
   * @param tableName
   * @param newTableName
   */
  void renameTable(String tableName, String newTableName);

  /**
   * Sets the given fieldType, dataType, referenceableTableIds and
   * backReferenceableColumnIds as model of the given column of the given table.
   * 
   * @param table
   * @param columnIdentification
   * @param fieldType
   * @param dataType
   * @param referenceableTableIds
   * @param backReferenceableColumnIds
   */
  void setColumnModel(
    TableIdentification table,
    ColumnIdentification columnIdentification,
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<String> referenceableTableIds,
    ExtendedIterable<String> backReferenceableColumnIds);
}
