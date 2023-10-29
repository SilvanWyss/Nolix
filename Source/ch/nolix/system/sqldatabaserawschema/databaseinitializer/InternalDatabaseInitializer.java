//package declaration
package ch.nolix.system.sqldatabaserawschema.databaseinitializer;

import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabaserawschema.columntable.ColumnTableSqlDtoCatalogue;
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabasePropertyTableSqlDtoCatalogue;
import ch.nolix.system.sqldatabaserawschema.multireferenceentrytable.MultiReferenceEntryTableSqlDtoCatalogue;
import ch.nolix.system.sqldatabaserawschema.multivalueentrytable.MultiValueEntryTableSqlDtoCatalogue;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;
import ch.nolix.system.sqldatabaserawschema.tabletable.TableTableSqlDtoCatalogue;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class InternalDatabaseInitializer {

  //method
  public void initializeDatabase(
    final String databaseName,
    final ISchemaAdapter schemaAdapter,
    final SqlConnectionPool sqlConnectionPool) {

    schemaAdapter.addTable(DatabasePropertyTableSqlDtoCatalogue.DATABASE_PROPERTY_TABLE_SQL_DTO);
    schemaAdapter.addTable(TableTableSqlDtoCatalogue.TABLE_TABLE_SQL_DTO);
    schemaAdapter.addTable(ColumnTableSqlDtoCatalogue.COLUMN_TABLE_SQL_DTO);

    schemaAdapter.addTable(MultiReferenceEntryTableSqlDtoCatalogue.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO);
    schemaAdapter.addTable(MultiValueEntryTableSqlDtoCatalogue.MULTI_VALUE_ENTRY_TABLE_SQL_DTO);

    schemaAdapter.saveChanges();

    createSchemaTimestampEntry(databaseName, sqlConnectionPool);
  }

  private void createSchemaTimestampEntry(final String databaseName, SqlConnectionPool sqlConnectionPool) {
    try (final var sqlConnection = sqlConnectionPool.borrowSqlConnection()) {
      sqlConnection.execute("USE " + databaseName);
      sqlConnection.execute(createQslStatementToCreateSchemaTimestampEntry());
    }
  }

  private String createQslStatementToCreateSchemaTimestampEntry() {
    return "INSERT INTO "
    + SystemDataTable.DATABASE_PROPERTY.getQualifiedName()
    + " ("
    + DatabasePropertySystemTableColumn.KEY.getLabel()
    + ", "
    + DatabasePropertySystemTableColumn.VALUE.getLabel()
    + ") VALUES ("
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes()
    + ", '"
    + Time.ofNow().getSpecification().getStoredSingleChildNode()
    + "');";
  }
}
