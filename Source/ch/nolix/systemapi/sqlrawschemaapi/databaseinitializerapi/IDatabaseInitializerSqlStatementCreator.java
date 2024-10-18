package ch.nolix.systemapi.sqlrawschemaapi.databaseinitializerapi;

import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDatabaseInitializerSqlStatementCreator {

  String createSqlStatementToCreateSchemaTimestampEntry(ITime timestamp);
}
