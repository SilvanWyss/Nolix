package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.systemapi.time.moment.ITime;

public interface IDatabaseInitializationStatementCreator {
  String createStatementToCreateSchemaTimestampEntry(ITime schemaTimestamp);
}
