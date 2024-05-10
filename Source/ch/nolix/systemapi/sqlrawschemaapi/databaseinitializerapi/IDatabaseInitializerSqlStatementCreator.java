//package declaration
package ch.nolix.systemapi.sqlrawschemaapi.databaseinitializerapi;

//own imports
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IDatabaseInitializerSqlStatementCreator {

  //method declaration
  String createSqlStatementToCreateSchemaTimestampEntry(ITime timestamp);
}
