package ch.nolix.systemapi.objectdata.changesetsaver;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 * @version 2025-08-15
 */
public interface IChangeSetSaver {
  /**
   * Saves the changes of the given database at once using the given
   * dataAndSchemaAdapter.
   * 
   * @param database
   * @param dataAndSchemaAdapter
   */
  void saveChangesOfDatabaseAtOnce(IDatabase database, IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
