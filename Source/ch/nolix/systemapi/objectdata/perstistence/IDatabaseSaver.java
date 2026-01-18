/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.perstistence;

import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;

/**
 * @author Silvan Wyss
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
