package ch.nolix.system.objectschema.schematool;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IDatabaseEngineTool;

public final class DatabaseEngineTool extends DatabaseObjectTool implements IDatabaseEngineTool {

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

  @Override
  public boolean containsDatabaseWithGivenName(
    final IDatabaseEngine databaseEngine,
    final String name) {
    return databaseEngine.getStoredDatabases().containsAny(db -> db.hasName(name));
  }

  @Override
  public int getDatabaseCount(final IDatabaseEngine databaseEngine) {
    return databaseEngine.getStoredDatabases().getCount();
  }

  @Override
  public IDatabase getStoredDatabaseWithGivenName(
    final IDatabaseEngine databaseEngine,
    final String name) {
    return databaseEngine.getStoredDatabases().getStoredFirst(db -> db.hasName(name));
  }
}
