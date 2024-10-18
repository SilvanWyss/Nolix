package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;

public interface IDatabaseEngineTool {

  void assertDoesNotContainDatabaseWithGivenName(IDatabaseEngine databaseEngine, String name);

  boolean containsDatabaseWithGivenName(IDatabaseEngine databaseEngine, String name);

  int getDatabaseCount(IDatabaseEngine databaseEngine);

  IDatabase getStoredDatabaseWithGivenName(IDatabaseEngine databaseEngine, String name);
}
