package ch.nolix.systemapi.objectschema.modelmutationexaminer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-27
 */
public interface IColumnMutationExaminer {
  /**
   * @param column
   * @return true if the given column can be deleted, false otherwise.
   */
  boolean canBeDeleted(IColumn column);

  /**
   * @param column
   * @param fieldType
   * @param dataType
   * @param referenceableTables
   * @param backReferenceableColumns
   * @param contentModel
   * @return true if the given contentModel can be set to the given column, false
   *         otherwise.
   */
  boolean canSetContentModel(
    IColumn column,
    FieldType fieldType,
    DataType dataType,
    IContainer<? extends ITable> referenceableTables,
    IContainer<? extends IColumn> backReferenceableColumns,
    IContentModel contentModel);

  /**
   * @param column
   * @param name
   * @return true if the given name can be set to the given column, false
   *         otherwise.
   */
  boolean canSetName(IColumn column, String name);
}
