package ch.nolix.systemapi.objectschemaapi.modelmutationvalidatorapi;

import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;

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
   * @param contentModel
   * @throws RuntimeException if the given contentModel cannot be set to the given
   *                          column.
   */
  void assertCanSetContentModel(IColumn column, IContentModel contentModel);

  /**
   * @param column
   * @param name
   * @throws RuntimeException if the given name cannot be set to the given column.
   */
  void assertCanSetName(IColumn column, String name);
}