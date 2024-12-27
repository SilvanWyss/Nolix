package ch.nolix.systemapi.objectschemaapi.schemavalidatorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface IDatabaseValidator {

  void assertContainsTable(IDatabase database, ITable tables);

  void assertContainsTables(IDatabase database, IContainer<ITable> tables);
}
