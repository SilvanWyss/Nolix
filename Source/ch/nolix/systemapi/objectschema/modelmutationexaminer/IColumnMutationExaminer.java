/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelmutationexaminer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.database.databaseproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IColumnMutationExaminer {
  /**
   * @param column
   * @return true if the given column can be deleted, false otherwise
   */
  boolean canBeDeleted(IColumn column);

  /**
   * @param column
   * @param fieldType
   * @param dataType
   * @param referenceableTables
   * @param backReferenceableColumns
   * @return true if the given contentModel can be set to the given column, false
   *         otherwise
   */
  boolean canSetContentModel(
    IColumn column,
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<? extends ITable> referenceableTables,
    ExtendedIterable<? extends IColumn> backReferenceableColumns);

  /**
   * @param column
   * @param name
   * @return true if the given name can be set to the given column, false
   *         otherwise
   */
  boolean canSetName(IColumn column, String name);
}
