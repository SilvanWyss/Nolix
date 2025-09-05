package ch.nolix.systemapi.objectschema.modelmutationvalidator;

import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-20
 */
public interface ITableMutationValidator {
  /**
   * @param table
   * @param column
   * @throws RuntimeException if the given column cannot be added to the given
   *                          table.
   */
  void assertCanAddColumnToTable(ITable table, IColumn column);

  /**
   * @param table
   * @throws RuntimeException if the given table cannot be deleted.
   */
  void assertCanDeleteTable(ITable table);

  /**
   * @param table
   * @param name
   * @throws RuntimeException if the given name cannot be set to the given table.
   */
  void assertCanSetNameToTable(ITable table, String name);
}
