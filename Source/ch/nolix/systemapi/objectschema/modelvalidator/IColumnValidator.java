/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelvalidator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IColumnValidator {
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
    IContainer<? extends ITable> referenceableTables,
    IContainer<? extends IColumn> backReferenceableColumns);

  /**
   * @param column
   * @throws RuntimeException if the given column is not a base reference column.
   */
  void assertIsBaseReferenceColumn(IColumn column);
}
