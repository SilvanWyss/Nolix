package ch.nolix.systemapi.objectschema.modelvalidator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

public interface IDatabaseValidator {
  void assertContainsTable(IDatabase database, ITable tables);

  void assertContainsTables(IDatabase database, IContainer<ITable> tables);
}
