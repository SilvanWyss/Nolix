/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;

/**
 * @author Silvan Wyss
 */
public final class DatabaseSearcher implements IDatabaseSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<IEntity> getStoredEntitiesInLocalData(final IDatabase database) {
    if (database == null) {
      return ImmutableList.createEmpty();
    }

    return database.getStoredTables().toMultiples(ITable::internalGetStoredEntitiesInLocalData);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITable<IEntity> getStoredTableById(final IDatabase database, final String tableId) {
    return database.getStoredTables().getStoredFirst(t -> t.hasId(tableId));
  }
}
