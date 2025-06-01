package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.systemapi.midschemaapi.databasestructureapi.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi.IDatabaseInitializationStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DatabaseInitializationStatementCreator implements IDatabaseInitializationStatementCreator {

  @Override
  public String createStatementToCreateSchemaTimestampEntry(final ITime schemaTimestamp) {
    return //
    "INSERT INTO "
    + FixTable.DATABASE_PROPERTY.getName()
    + " ("
    + DatabasePropertyColumn.KEY.getName()
    + ", "
    + DatabasePropertyColumn.VALUE.getName()
    + ") VALUES ("
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes()
    + ", '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "');";
  }
}
