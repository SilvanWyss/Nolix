package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.system.objectdata.datatool.DatabaseTool;
import ch.nolix.system.objectdata.datavalidator.DatabaseValidator;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

public final class ChangeSetSaver {

  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  private static final DatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private static final ChangeSetSaveValidator DATABASE_SAVE_VALIDATOR = new ChangeSetSaveValidator();

  private static final EntitySaver ENTITY_SAVER = new EntitySaver();

  public void saveChangesOfDatabaseSynchronously(
    final IDatabase database,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    synchronized (ChangeSetSaver.class) {
      saveChangesOfDatabase(database, dataAndSchemaAdapter);
    }
  }

  private void saveChangesOfDatabase(final IDatabase database, final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    assertCanSaveChangesOfDatabase(database);

    addExpectionThatDatabaseHasInitialSchemaTimestamp(database, dataAndSchemaAdapter);

    prepareChangesOfDatabase(database, dataAndSchemaAdapter);

    assertNewlyReferencedEntitiesExists(database, dataAndSchemaAdapter);

    commitChangesToDatabase(dataAndSchemaAdapter);
  }

  private void assertCanSaveChangesOfDatabase(final IDatabase database) {
    DATABASE_VALIDATOR.assertCanSaveChanges(database);
  }

  private void prepareChangesOfDatabase(final IDatabase database, final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var entitiesInLocalData = DATABASE_TOOL.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      ENTITY_SAVER.saveChangesOfEntity(e, dataAndSchemaAdapter);
    }
  }

  private void addExpectionThatDatabaseHasInitialSchemaTimestamp(
    final IDatabase database,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    dataAndSchemaAdapter.expectGivenSchemaTimestamp(database.getSchemaTimestamp());
  }

  private void assertNewlyReferencedEntitiesExists(
    final IDatabase database,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    DATABASE_SAVE_VALIDATOR.addExpectationToDatabaseThatNewlyReferencedEntitiesExist(database, dataAndSchemaAdapter);
  }

  private void commitChangesToDatabase(final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    dataAndSchemaAdapter.saveChanges();
  }
}
