package ch.nolix.systemapi.objectschema.modelexaminer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IColumnExaminer {
  /**
   * @param column
   * @param fieldType
   * @param dataType
   * @param referenceableTables
   * @param backReferenceableColumns
   * @return true if the given column can set the given content model, false
   *         otherwise.
   */
  boolean canSetContentModel(
    IColumn column,
    FieldType fieldType,
    DataType dataType,
    IContainer<? extends ITable> referenceableTables,
    IContainer<? extends IColumn> backReferenceableColumns);

  /**
   * @param column
   * @return true if the given column is a base reference column, false otherwise.
   */
  boolean isBaseReferenceColumn(IColumn column);
}
