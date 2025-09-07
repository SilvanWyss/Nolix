package ch.nolix.systemapi.objectschema.modeltool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

//TODO: Create IDatabaseSearcher
public interface IDatabaseTool extends IDatabaseObjectExaminer {

  void deleteTableWithGivenName(IDatabase database, String name);

  IContainer<? extends IColumn> getStoredAllBackReferenceColumns(IDatabase database);

  ITable getStoredTableWithGivenName(IDatabase database, String name);

  int getTableCount(IDatabase database);
}
