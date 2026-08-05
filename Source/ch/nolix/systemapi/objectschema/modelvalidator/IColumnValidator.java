/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelvalidator;

import ch.nolix.baseapi.datamodel.fieldproperty.DataType;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IColumnValidator {
  /**
   * @param column
   * @throws RuntimeException if the given column does not belong to a table
   */
  void assertBelongsToTable(IColumn column);

  /**
   * @param column
   * @param fieldType
   * @param dataType
   * @param referenceableTables
   * @param backReferenceableColumns
   * @throws RuntimeException if the given column cannot set the given content
   *                          model.
   */
  void assertCanSetContentModel(
    IColumn column,
    FieldType fieldType,
    DataType dataType,
    ExtendedIterable<? extends ITable> referenceableTables,
    ExtendedIterable<? extends IColumn> backReferenceableColumns);

  /**
   * @param column
   * @throws RuntimeException if the given column is not a base reference column
   */
  void assertIsBaseReferenceColumn(IColumn column);
}
