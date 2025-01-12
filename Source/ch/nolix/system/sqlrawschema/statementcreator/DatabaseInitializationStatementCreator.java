package ch.nolix.system.sqlrawschema.statementcreator;

import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MetaDataTableType;
import ch.nolix.systemapi.sqlrawschemaapi.statementcreatorapi.IDatabaseInitializationStatementCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DatabaseInitializationStatementCreator implements IDatabaseInitializationStatementCreator {

  @Override
  public String createStatementToCreateSchemaTimestampEntry(final ITime schemaTimestamp) {
    return //
    "INSERT INTO "
    + MetaDataTableType.DATABASE_PROPERTY.getQualifiedName()
    + " ("
    + DatabasePropertyTableColumn.KEY.getLabel()
    + ", "
    + DatabasePropertyTableColumn.VALUE.getLabel()
    + ") VALUES ("
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes()
    + ", '"
    + schemaTimestamp.getSpecification().getSingleChildNodeHeader()
    + "');";
  }
}
