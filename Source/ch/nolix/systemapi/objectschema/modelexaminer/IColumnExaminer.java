package ch.nolix.systemapi.objectschema.modelexaminer;

import ch.nolix.systemapi.objectschema.model.IColumn;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IColumnExaminer {
  /**
   * @param column
   * @return true if the given column is a base reference column, false otherwise.
   */
  boolean isBaseReferenceColumn(IColumn column);
}
