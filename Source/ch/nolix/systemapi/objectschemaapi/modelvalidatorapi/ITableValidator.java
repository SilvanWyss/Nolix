package ch.nolix.systemapi.objectschemaapi.modelvalidatorapi;

import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

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
