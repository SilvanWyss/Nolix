//package declaration
package ch.nolix.system.objectschema.schematool;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseobject.databaseobjecthelper.DatabaseObjectTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseEngineTool;

//class
public final class DatabaseEngineTool extends DatabaseObjectTool implements IDatabaseEngineTool {

  //method
  @Override
  public void assertDoesNotContainDatabaseWithGivenName(
    final IDatabaseEngine databaseEngine,
    final String name) {
    if (!containsDatabaseWithGivenName(databaseEngine, name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains a database with the name '" + name + "'");
    }
  }

  //method
  @Override
  public boolean containsDatabaseWithGivenName(
    final IDatabaseEngine databaseEngine,
    final String name) {
    return databaseEngine.getStoredDatabases().containsAny(db -> db.hasName(name));
  }

  //method
  @Override
  public int getDatabaseCount(final IDatabaseEngine databaseEngine) {
    return databaseEngine.getStoredDatabases().getElementCount();
  }

  //method
  @Override
  public IDatabase getStoredDatabaseWithGivenName(
    final IDatabaseEngine databaseEngine,
    final String name) {
    return databaseEngine.getStoredDatabases().getStoredFirst(db -> db.hasName(name));
  }
}
