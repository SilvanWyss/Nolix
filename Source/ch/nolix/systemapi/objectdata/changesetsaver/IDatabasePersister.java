package ch.nolix.systemapi.objectdata.changesetsaver;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 * @version 2025-11-03
 */
public interface IDatabasePersister {
  /**
   * Persists the changes of the given database.
   * 
   * @param database
   * @param dataAndSchemaAdapter
   */
  void persistDatabaseChanges(IDatabase database, IDataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Persists the changes of the given database in one transaction.
   * 
   * @param database
   * @param dataAndSchemaAdapter
   */
  void persistDatabaseChangesTransactional(IDatabase database, IDataAdapterAndSchemaReader dataAndSchemaAdapter);
}
