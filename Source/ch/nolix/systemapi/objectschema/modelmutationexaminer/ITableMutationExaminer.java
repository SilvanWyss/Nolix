package ch.nolix.systemapi.objectschema.modelmutationexaminer;

import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface ITableMutationExaminer {

  /**
   * @param table
   * @return true if the given table can be added to a {@link IDatabase}, false
   *         otherwise.
   */
  boolean canBeAddedToDatabase(ITable table);
}
