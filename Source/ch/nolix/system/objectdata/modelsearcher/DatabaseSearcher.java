package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class DatabaseSearcher extends DatabaseObjectExaminer implements IDatabaseSearcher {
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<IEntity> getStoredEntitiesInLocalData(final IDatabase database) {
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
