//package declaration
package ch.nolix.systemapi.objectschemaapi.schematoolapi;

//own imports
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IDatabaseEngineTool {

  //method declaration
  void assertDoesNotContainDatabaseWithGivenName(IDatabaseEngine databaseEngine, String name);

  //method declaration
  boolean containsDatabaseWithGivenName(IDatabaseEngine databaseEngine, String name);

  //method declaration
  int getDatabaseCount(IDatabaseEngine databaseEngine);

  //method declaration
  IDatabase getStoredDatabaseWithGivenName(IDatabaseEngine databaseEngine, String name);
}