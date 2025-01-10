package ch.nolix.coreapi.sqlapi.connectionapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.sqlapi.modelapi.IRecord;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

public interface ISqlConnection extends GroupCloseable {

  void executeStatement(String statement, String... statements);

  void executeStatements(IContainer<String> statements);

  SqlDatabaseEngine getDatabaseEngine();

  IContainer<IRecord> getRecordsFromQuery(String query);

  //TODO: Delete this method
  IContainer<String> getRecordsHavingSinlgeEntryFromQuery(String query);

  IRecord getSingleRecordFromQuery(String query);

  String getSingleRecordHavingSingleEntryFromQuery(String query);
}
