//package declaration
package ch.nolix.system.sqlrawschema.databaseinitializer;

import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertyTableColumn;
import ch.nolix.system.sqlrawschema.structure.MetaDataTableType;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class DatabaseInitializerSqlStatementCreator {

  //method
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
