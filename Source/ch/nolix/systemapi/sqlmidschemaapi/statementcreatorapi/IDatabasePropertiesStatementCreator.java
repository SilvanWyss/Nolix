package ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi;

import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDatabasePropertiesStatementCreator {

  String createStatementToSetSchemaTimestamp(ITime schemaTimestamp);
}
