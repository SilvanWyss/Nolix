package ch.nolix.coreapi.sqlapi.connectionapi;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

public interface ISqlConnection extends GroupCloseable {

  void executeStatement(String statement, String... statements);

  void executeStatements(IContainer<String> statements);

  SqlDatabaseEngine getDatabaseEngine();

  IContainer<ISqlRecord> getRecordsFromQuery(String query);

  ISqlRecord getSingleRecordFromQuery(String query);
}
