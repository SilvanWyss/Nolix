//package declaration
package ch.nolix.coreapi.sqlapi.connectionapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//interface
public interface ISqlConnection extends GroupCloseable {

  //method declaration
  void executeStatement(String statement, String... statements);

  //method declaration
  void executeStatements(IContainer<String> statements);

  //method declaration
  SqlDatabaseEngine getDatabaseEngine();

  //method declaration
  IContainer<? extends IContainer<String>> getRecordsFromQuery(String query);

  //method declaration
  IContainer<String> getRecordsHavingSinlgeEntryFromQuery(String query);

  //method declaration
  IContainer<String> getSingleRecordFromQuery(String query);

  //method declaration
  String getSingleRecordHavingSingleEntryFromQuery(String query);
}
