package ch.nolix.coreapi.sql.connection;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.coreapi.sql.sqlproperty.SqlDatabaseEngine;

public interface ISqlConnection extends GroupCloseable {

  void executeStatement(String statement, String... statements);

  void executeStatements(IContainer<String> statements);

  SqlDatabaseEngine getDatabaseEngine();

  IContainer<ISqlRecord> getRecordsFromQuery(String query);

  ISqlRecord getSingleRecordFromQuery(String query);
}
