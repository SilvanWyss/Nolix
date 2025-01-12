package ch.nolix.systemapi.sqlrawschemaapi.statementcreatorapi;

import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDatabaseInitializationStatementCreator {

  String createStatementToCreateSchemaTimestampEntry(ITime schemaTimestamp);
}
