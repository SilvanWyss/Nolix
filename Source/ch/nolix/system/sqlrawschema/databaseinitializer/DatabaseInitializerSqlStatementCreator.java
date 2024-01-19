//package declaration
package ch.nolix.system.sqlrawschema.databaseinitializer;

//own imports
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawschema.tabletype.MetaDataTableType;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class DatabaseInitializerSqlStatementCreator {

  //method
  public String createSqlStatementToCreateSchemaTimestampEntry(final ITime timestamp) {
    return //
    "INSERT INTO "
    + MetaDataTableType.DATABASE_PROPERTY.getQualifiedName()
    + " ("
    + DatabasePropertySystemTableColumn.KEY.getLabel()
    + ", "
    + DatabasePropertySystemTableColumn.VALUE.getLabel()
    + ") VALUES ("
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes()
    + ", '"
    + timestamp.getSpecification().getSingleChildNodeHeader()
    + "');";
  }
}
