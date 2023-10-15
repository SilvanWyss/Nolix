//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.system.objectdatabase.databasehelper.DatabaseHelper;
import ch.nolix.system.objectdatabase.databasevalidator.DatabaseValidator;
import ch.nolix.systemapi.objectdatabaseapi.databasehelperapi.IDatabaseHelper;

//class
final class DatabaseSaver {

  // constant
  private static final IDatabaseHelper DATABASE_HELPER = new DatabaseHelper();

  // constant
  private static final DatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  // constant
  private static final DatabaseSaveValidator DATABASE_SAVE_VALIDATOR = new DatabaseSaveValidator();

  // constant
  private static final EntitySaver ENTITY_SAVER = new EntitySaver();

  // method
  public void saveChangesOfDatabaseThreadSafe(final Database database) {
    synchronized (DatabaseSaver.class) {
      saveChangesOfDatabase(database);
    }
  }

  // method
  private void saveChangesOfDatabase(final Database database) {

    assertCanSaveChangesOfDatabase(database);

    addExpectionThatDatabaseHasInitialSchemaTimestamp(database);

    prepareChangesOfDatabase(database);

    assertNewlyReferencedEntitiesExists(database);

    commitChangesToDatabase(database);
  }

  // method
  private void assertCanSaveChangesOfDatabase(final Database database) {
    DATABASE_VALIDATOR.assertCanSaveChanges(database);
  }

  // method
  private void prepareChangesOfDatabase(final Database database) {

    final var entitiesInLocalData = DATABASE_HELPER.getStoredEntitiesInLocalData(database);

    for (final var e : entitiesInLocalData) {
      ENTITY_SAVER.saveChangesOfEntity(e, database);
    }
  }

  // method
  private void addExpectionThatDatabaseHasInitialSchemaTimestamp(final Database database) {
    database.internalGetRefDataAndSchemaAdapter().expectGivenSchemaTimestamp(database.getSchemaTimestamp());
  }

  // method
  private void assertNewlyReferencedEntitiesExists(final Database database) {
    DATABASE_SAVE_VALIDATOR.addExpectionsThatNewlyReferencedEntitiesExistToDatabase(database);
  }

  // method
  private void commitChangesToDatabase(final Database database) {
    database.internalGetRefDataAndSchemaAdapter().saveChanges();
  }
}
