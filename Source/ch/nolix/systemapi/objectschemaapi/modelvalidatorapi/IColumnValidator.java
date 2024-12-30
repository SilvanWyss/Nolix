package ch.nolix.systemapi.objectschemaapi.modelvalidatorapi;

import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;

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
