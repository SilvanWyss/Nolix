/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modelmutationvalidator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IColumnMutationValidator {
  /**
   * @param column
   * @throws RuntimeException if the given column cannot be deleted.
   */
  void assertCanBeDeleted(IColumn column);

  /**
   * @param column
   * @param fieldType
   * @param dataType
   * @param referenceableTables
   * @param backReferenceableColumns
   * @throws RuntimeException if the given contentModel cannot be set to the given
   *                          column.
   */
  void assertCanSetContentModel(
    IColumn column,
    FieldType fieldType,
    DataType dataType,
    IContainer<? extends ITable> referenceableTables,
    IContainer<? extends IColumn> backReferenceableColumns);

  /**
   * @param column
   * @param name
   * 
   * @throws RuntimeException if the given name cannot be set to the given column.
   */
  void assertCanSetName(IColumn column, String name);
}
