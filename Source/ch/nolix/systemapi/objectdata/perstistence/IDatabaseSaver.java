package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 * @version 2025-11-03
 */
public interface IDatabaseSaver {
  /**
   * Saves the changes of the given database.
   * 
   * @param database
   * @param dataAndSchemaAdapter
   */
  void saveDatabaseChanges(IDatabase database, IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
