package ch.nolix.systemapi.objectschema.modelvalidator;

import ch.nolix.systemapi.objectschema.model.IColumn;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IColumnValidator {

  /**
   * 
   * @param column
   * @throws RuntimeException if the given column is not an abstract reference
   *                          column
   */
  void assertIsAbstractReferenceColumn(IColumn column);
}
