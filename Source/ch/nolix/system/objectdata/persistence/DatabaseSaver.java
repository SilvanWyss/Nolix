package ch.nolix.system.objectdata.persistence;

import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.objectdata.perstistence.IDatabaseSaver;

/**
 * @author Silvan Wyss
 */
public final class DatabaseSaver implements IDatabaseSaver {
  private static final IDatabaseSearcher DATABASE_TOOL = new DatabaseSearcher();

  private static final EntitySaver ENTITY_SAVER = new EntitySaver();

  /**
   * {@inheritDoc}
   */
  @Override
  public void saveDatabaseChanges(final IDatabase database, final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    final var entitiesInLocalData = DATABASE_TOOL.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      ENTITY_SAVER.saveEntityChanges(e, dataAndSchemaAdapter);
    }
  }
}
