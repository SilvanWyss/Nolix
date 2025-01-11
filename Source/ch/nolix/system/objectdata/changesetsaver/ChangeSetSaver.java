package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.system.objectdata.datavalidator.DatabaseValidator;
import ch.nolix.system.objectdata.modelsearcher.DatabaseSearcher;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelsearcher.IDatabaseSearcher;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;

public final class ChangeSetSaver {

  private static final IDatabaseSearcher DATABASE_TOOL = new DatabaseSearcher();

  private static final DatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final ChangeSetSaveValidator DATABASE_SAVE_VALIDATOR = new ChangeSetSaveValidator();

  private static final EntitySaver ENTITY_SAVER = new EntitySaver();

  public void saveChangesOfDatabaseSynchronously(
    final IDatabase database,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    synchronized (ChangeSetSaver.class) {
      saveChangesOfDatabase(database, dataAndSchemaAdapter);
    }
  }

  private void saveChangesOfDatabase(final IDatabase database, final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    assertCanSaveChangesOfDatabase(database);

    addExpectionThatDatabaseHasInitialSchemaTimestamp(database, dataAndSchemaAdapter);

    prepareChangesOfDatabase(database, dataAndSchemaAdapter);

    assertNewlyReferencedEntitiesExists(database, dataAndSchemaAdapter);

    commitChangesToDatabase(dataAndSchemaAdapter);
  }

  private void assertCanSaveChangesOfDatabase(final IDatabase database) {
    DATABASE_VALIDATOR.assertCanSaveChanges(database);
  }

  private void prepareChangesOfDatabase(final IDatabase database,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var entitiesInLocalData = DATABASE_TOOL.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      ENTITY_SAVER.saveChangesOfEntity(e, dataAndSchemaAdapter);
    }
  }

  private void addExpectionThatDatabaseHasInitialSchemaTimestamp(
    final IDatabase database,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    dataAndSchemaAdapter.expectGivenSchemaTimestamp(database.getSchemaTimestamp());
  }

  private void assertNewlyReferencedEntitiesExists(
    final IDatabase database,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    DATABASE_SAVE_VALIDATOR.addExpectationToDatabaseThatNewlyReferencedEntitiesExist(database, dataAndSchemaAdapter);
  }

  private void commitChangesToDatabase(final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    dataAndSchemaAdapter.saveChanges();
  }
}
