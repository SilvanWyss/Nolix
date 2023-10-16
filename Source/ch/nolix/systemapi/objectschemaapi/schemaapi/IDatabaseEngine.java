//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IDatabaseEngine {

  //method declaration
  IDatabaseEngine addDatabase(IDatabase database);

  //method declaration
  IDatabaseEngine createDatabaseWithName(String name);

  //method declaration
  IContainer<IDatabase> getStoredDatabases();
}
