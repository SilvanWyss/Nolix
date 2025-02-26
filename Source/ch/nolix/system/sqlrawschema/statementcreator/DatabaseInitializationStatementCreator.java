package ch.nolix.system.sqlrawschema.statementcreator;

import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.statementcreatorapi.IDatabaseInitializationStatementCreator;
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
