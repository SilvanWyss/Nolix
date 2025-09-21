package ch.nolix.systemapi.objectschema.modelmutationvalidator;

import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IContentModel;

/**
 * @author Silvan Wyss
 * @version 2024-12-27
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
   * @param contentModel
   * @throws RuntimeException if the given fieldType is null.
   * @throws RuntimeException if the given contentModel cannot be set to the given
   *                          column.
   */
  void assertCanSetContentModel(IColumn column, FieldType fieldType, IContentModel contentModel);

  /**
   * @param column
   * @param name
   * 
   * @throws RuntimeException if the given name cannot be set to the given column.
   */
  void assertCanSetName(IColumn column, String name);
}
