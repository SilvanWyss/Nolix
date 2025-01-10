package ch.nolix.coreapi.sqlapi.connectionapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

public interface ISqlConnection extends GroupCloseable {

  void executeStatement(String statement, String... statements);

  void executeStatements(IContainer<String> statements);

  SqlDatabaseEngine getDatabaseEngine();

  IContainer<? extends IContainer<String>> getRecordsFromQuery(String query);

  //TODO: Delete this method
  IContainer<String> getRecordsHavingSinlgeEntryFromQuery(String query);

  IContainer<String> getSingleRecordFromQuery(String query);

  String getSingleRecordHavingSingleEntryFromQuery(String query);
}
