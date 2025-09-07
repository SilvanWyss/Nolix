package ch.nolix.systemapi.objectschema.modelsearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

public interface IDatabaseSearcher {
  IContainer<? extends IColumn> getStoredBaseBackReferenceColumns(IDatabase database);

  ITable getStoredTableByName(IDatabase database, String tableName);
}
