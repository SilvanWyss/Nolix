package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschema.databasestructure.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.statementcreator.IDatabaseInitializationStatementCreator;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
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
