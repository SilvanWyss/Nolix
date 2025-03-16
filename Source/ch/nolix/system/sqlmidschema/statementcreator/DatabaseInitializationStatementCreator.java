package ch.nolix.system.sqlmidschema.statementcreator;

import ch.nolix.systemapi.midschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlmidschemaapi.statementcreatorapi.IDatabaseInitializationStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DatabaseInitializationStatementCreator implements IDatabaseInitializationStatementCreator {

  @Override
  public String createStatementToCreateSchemaTimestampEntry(final ITime schemaTimestamp) {
    return //
    "INSERT INTO "
    + FixTableType.DATABASE_PROPERTY.getName()
    + " ("
    + DatabasePropertyTableColumn.KEY.getName()
    + ", "
    + DatabasePropertyTableColumn.VALUE.getName()
    + ") VALUES ("
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes()
    + ", '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "');";
  }
}
