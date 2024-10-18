package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IDatabaseEngine {

  IDatabaseEngine addDatabase(IDatabase database);

  IDatabaseEngine createDatabaseWithName(String name);

  IContainer<IDatabase> getStoredDatabases();
}
