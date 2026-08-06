/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.DataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
 */
public interface IDatabasePersister {
  /**
   * Persists the changes of the given database.
   * 
   * @param database
   * @param dataAndSchemaAdapter
   */
  void persistDatabaseChanges(IDatabase database, DataAdapterAndSchemaReader dataAndSchemaAdapter);

  /**
   * Persists the changes of the given database in one transaction.
   * 
   * @param database
   * @param dataAndSchemaAdapter
   */
  void persistDatabaseChangesTransactional(IDatabase database, DataAdapterAndSchemaReader dataAndSchemaAdapter);
}
