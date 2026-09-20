/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelsearcher;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseSearcher {
  /**
   * @param database
   * @return the {@link Entity}s of the given database in the local data.
   */
  ExtendedIterable<Entity> getStoredEntitiesInLocalData(IDatabase database);

  /**
   * @param database
   * @param tableId
   * @return the {@link ITable} with the given tableId from the given database
   * @throws RuntimeException if the given database does not contain a table with
   *                          the given tableId.
   */
  ITable<Entity> getStoredTableById(IDatabase database, String tableId);
}
