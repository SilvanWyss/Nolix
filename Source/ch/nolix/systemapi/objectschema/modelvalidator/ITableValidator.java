package ch.nolix.systemapi.objectschema.modelvalidator;

import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface ITableValidator {
  /**
   * @param table
   * @param column
   * @throws RuntimeException if the given table does not contain the given
   *                          column.
   */
  void assertContainsColumn(ITable table, IColumn column);

  /**
   * @param table
   * @throws RuntimeException if the given table does not belong to a
   *                          {@link IDatabase}.
   */
  void assertDoesNotBelongToDatabase(ITable table);

  /**
   * @param table
   * @param column
   * @throws RuntimeException if the given table contains the given column.
   */
  void assertDoesNotContainColumn(ITable table, IColumn column);

  /**
   * @param table
   * @param name
   * @throws RuntimeException if the given table a {@link IColumn} with the given
   *                          name.
   */
  void assertDoesNotContainColumnWithName(ITable table, String name);

  /**
   * @param table
   * @throws RuntimeException if the given table is referenced.
   */
  void assertIsNotReferenced(ITable table);
}
