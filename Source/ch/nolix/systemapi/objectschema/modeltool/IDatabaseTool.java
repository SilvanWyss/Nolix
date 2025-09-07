package ch.nolix.systemapi.objectschema.modeltool;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IDatabase;

public interface IDatabaseTool extends IDatabaseObjectExaminer {

  void deleteTableWithGivenName(IDatabase database, String name);

  int getTableCount(IDatabase database);
}
