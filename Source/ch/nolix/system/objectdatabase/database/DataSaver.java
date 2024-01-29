//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.databasetool.DatabaseTool;
import ch.nolix.system.objectdatabase.databasevalidator.DatabaseValidator;
import ch.nolix.systemapi.objectdatabaseapi.databasetoolapi.IDatabaseTool;

//class
final class DataSaver {

  //constant
  private static final IDatabaseTool DATABASE_TOOL = new DatabaseTool();

  //constant
  private static final DatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  //constant
  private static final DatabaseSaveValidator DATABASE_SAVE_VALIDATOR = new DatabaseSaveValidator();

  //constant
  private static final EntitySaver ENTITY_SAVER = new EntitySaver();

  //method
  public void saveChangesOfDatabaseSynchronously(final Database database) {
    synchronized (DataSaver.class) {
      saveChangesOfDatabase(database);
    }
  }

  //method
  private void saveChangesOfDatabase(final Database database) {

    assertCanSaveChangesOfDatabase(database);

    addExpectionThatDatabaseHasInitialSchemaTimestamp(database);

    prepareChangesOfDatabase(database);

    assertNewlyReferencedEntitiesExists(database);

    commitChangesToDatabase(database);
  }

  //method
  private void assertCanSaveChangesOfDatabase(final Database database) {
    DATABASE_VALIDATOR.assertCanSaveChanges(database);
  }

  //method
  private void prepareChangesOfDatabase(final Database database) {

    final var entitiesInLocalData = DATABASE_TOOL.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      ENTITY_SAVER.saveChangesOfEntity(e, database);
    }
  }

  //method
  private void addExpectionThatDatabaseHasInitialSchemaTimestamp(final Database database) {
    database.internalGetRefDataAndSchemaAdapter().expectGivenSchemaTimestamp(database.getSchemaTimestamp());
  }

  //method
  private void assertNewlyReferencedEntitiesExists(final Database database) {
    DATABASE_SAVE_VALIDATOR.addExpectationToDatabaseThatNewlyReferencedEntitiesExist(database);
  }

  //method
  private void commitChangesToDatabase(final Database database) {
    database.internalGetRefDataAndSchemaAdapter().saveChanges();
  }
}
