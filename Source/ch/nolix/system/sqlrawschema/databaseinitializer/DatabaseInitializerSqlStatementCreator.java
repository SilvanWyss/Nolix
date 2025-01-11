package ch.nolix.system.sqlrawschema.databaseinitializer;

import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawschemaapi.databaseinitializerapi.IDatabaseInitializerSqlStatementCreator;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MetaDataTableType;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public final class DatabaseInitializerSqlStatementCreator implements IDatabaseInitializerSqlStatementCreator {

  @Override
  public String createSqlStatementToCreateSchemaTimestampEntry(final ITime timestamp) {
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
    + timestamp.getSpecification().getSingleChildNodeHeader()
    + "');";
  }
}
