package ch.nolix.systemapi.objectschema.modelexaminer;

import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface ITableExaminer {
  /**
   * @param table
   * @param column
   * @return true if the given table contains the given column, false otherwise.
   */
  boolean containsColumn(ITable table, IColumn column);

  /**
   * @param table
   * @param column
   * @return true if the given table contains a {@link IColumn} that is back
   *         referenced by the given column, false otherwise.
   */
  boolean containsColumnThatIsBackReferencedByColumn(ITable table, IColumn column);

  /**
   * @param table
   * @param column
   * @return true if the given table contains a {@link IColumn} that referenced
   *         back the given column, false otherwise.
   */
  boolean containsColumnThatReferencesBackColumn(ITable table, IColumn column);

  /**
   * @param table
   * @param otherTable
   * @return true if the given table contains a {@link IColumn} that references
   *         the given otherTable, false otherwise.
   */
  boolean containsColumnThatReferencesTable(ITable table, ITable otherTable);

  /**
   * @param table
   * @param name
   * @return if the given table contains a {@link IColumn} with the given name,
   *         false otherwise.
   */
  boolean containsColumnWithName(ITable table, String name);

  /**
   * @param table
   * @return true if the given table is referenced, false otherwise.
   */
  boolean isReferenced(ITable table);
}
