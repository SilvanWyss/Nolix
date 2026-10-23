/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

/**
 * @author Silvan Wyss
 */
public interface IColumnTableStatementCreator {
  /**
   * @param table
   * @param column
   * @return a statement to add the given column of the given table into the
   *         column table
   */
  String createStatementToAddColumnIntoColumnTable(TableIdentification table, ColumnDto column);

  /**
   * @param column
   * @param fieldType
   * @param dataType
   * @return a statement to set the given fieldType and dataType as content model
   *         of the given column in the column table
   */
  String createStatementToSetContentModelInColumnTable(
    ColumnIdentification column,
    FieldType fieldType,
    DataType dataType);
}
