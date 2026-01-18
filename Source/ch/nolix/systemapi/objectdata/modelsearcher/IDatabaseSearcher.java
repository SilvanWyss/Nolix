/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelsearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IDatabaseSearcher {
  /**
   * @param database
   * @return the {@link IEntity}s of the given database in the local data.
   */
  IContainer<IEntity> getStoredEntitiesInLocalData(IDatabase database);

  /**
   * @param database
   * @param tableId
   * @return the {@link ITable} with the given tableId from the given database.
   * @throws RuntimeException if the given database does not contain a table with
   *                          the given tableId.
   */
  ITable<IEntity> getStoredTableById(IDatabase database, String tableId);
}
