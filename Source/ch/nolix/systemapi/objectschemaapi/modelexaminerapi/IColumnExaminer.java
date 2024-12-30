package ch.nolix.systemapi.objectschemaapi.modelexaminerapi;

import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IColumnExaminer {

  /**
   * @param column
   * @return true if the given column is an abstract reference column, false
   *         otherwise.
   */
  boolean isAbstractReferenceColumn(IColumn column);
}
