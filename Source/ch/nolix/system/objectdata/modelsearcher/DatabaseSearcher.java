package ch.nolix.system.objectdata.modelsearcher;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelsearcherapi.IDatabaseSearcher;

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

    return database.getStoredTables().toMultiple(ITable::internalGetStoredEntitiesInLocalData);
  }
}
