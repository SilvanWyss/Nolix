//package declaration
package ch.nolix.system.objectdata.changesetsaver;

//own imports
import ch.nolix.system.objectdata.data.Database;
import ch.nolix.system.objectdata.data.DatabaseSaveValidator;
import ch.nolix.system.objectdata.datatool.DatabaseTool;
import ch.nolix.system.objectdata.datavalidator.DatabaseValidator;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IDatabaseTool;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public final class ChangeSetSaver {

  //constant
  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  //constant
  private static final DatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  //constant
  private static final DatabaseSaveValidator DATABASE_SAVE_VALIDATOR = new DatabaseSaveValidator();

  //constant
  private static final EntitySaver ENTITY_SAVER = new EntitySaver();

  //method
  public void saveChangesOfDatabaseSynchronously(
    final Database database,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    synchronized (ChangeSetSaver.class) {
      saveChangesOfDatabase(database, dataAndSchemaAdapter);
    }
  }

  //method
  private void saveChangesOfDatabase(final Database database, final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    assertCanSaveChangesOfDatabase(database);

    addExpectionThatDatabaseHasInitialSchemaTimestamp(database, dataAndSchemaAdapter);

    prepareChangesOfDatabase(database, dataAndSchemaAdapter);

    assertNewlyReferencedEntitiesExists(database, dataAndSchemaAdapter);

    commitChangesToDatabase(dataAndSchemaAdapter);
  }

  //method
  private void assertCanSaveChangesOfDatabase(final Database database) {
    DATABASE_VALIDATOR.assertCanSaveChanges(database);
  }

  //method
  private void prepareChangesOfDatabase(final Database database, final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var entitiesInLocalData = DATABASE_TOOL.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      ENTITY_SAVER.saveChangesOfEntity(e, dataAndSchemaAdapter);
    }
  }

  //method
  private void addExpectionThatDatabaseHasInitialSchemaTimestamp(
    final Database database,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    dataAndSchemaAdapter.expectGivenSchemaTimestamp(database.getSchemaTimestamp());
  }

  //method
  private void assertNewlyReferencedEntitiesExists(
    final Database database,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    DATABASE_SAVE_VALIDATOR.addExpectationToDatabaseThatNewlyReferencedEntitiesExist(database, dataAndSchemaAdapter);
  }

  //method
  private void commitChangesToDatabase(final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    dataAndSchemaAdapter.saveChanges();
  }
}
