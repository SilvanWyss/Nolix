//package declaration
package ch.nolix.system.sqlrawschema.databaseinitializer;

//own imports
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlrawschema.columntable.ColumnTableSqlDtoCatalogue;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertyTableSqlDtoCatalogue;
import ch.nolix.system.sqlrawschema.entityheadtable.EntityHeadTableDtoCatalogue;
import ch.nolix.system.sqlrawschema.multibackreferenceentrytable.MultiBackReferenceEntryTableSqlDtoCatalogue;
import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableSqlDtoCatalogue;
import ch.nolix.system.sqlrawschema.multivalueentrytable.MultiValueEntryTableSqlDtoCatalogue;
import ch.nolix.system.sqlrawschema.tabletable.TableTableSqlDtoCatalogue;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
final class InternalDatabaseInitializer {

  //constant
  private static final DatabaseInitializerSqlStatementCreator DATABASE_INITIALIZER_SQL_STATEMENT_CREATOR = //
  new DatabaseInitializerSqlStatementCreator();

  //method
  public void initializeDatabase(
    final String databaseName,
    final ISchemaAdapter schemaAdapter,
    final SqlConnectionPool sqlConnectionPool) {

    createFixTables(schemaAdapter);

    createSchemaTimestampEntry(databaseName, sqlConnectionPool);
  }

  //method
  private void createFixTables(final ISchemaAdapter schemaAdapter) {

    //Adds metadata tables.
    schemaAdapter.addTable(DatabasePropertyTableSqlDtoCatalogue.DATABASE_PROPERTY_TABLE_SQL_DTO);

    //Adds schema tables.
    schemaAdapter.addTable(TableTableSqlDtoCatalogue.TABLE_TABLE_SQL_DTO);
    schemaAdapter.addTable(ColumnTableSqlDtoCatalogue.COLUMN_TABLE_SQL_DTO);

    //Adds index tables.
    schemaAdapter.addTable(EntityHeadTableDtoCatalogue.ENTITY_HEAD_TABLE_DTO);

    //Adds multi-entry tables.
    schemaAdapter.addTable(MultiValueEntryTableSqlDtoCatalogue.MULTI_VALUE_ENTRY_TABLE_SQL_DTO);
    schemaAdapter.addTable(MultiReferenceEntryTableSqlDtoCatalogue.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO);
    schemaAdapter.addTable(MultiBackReferenceEntryTableSqlDtoCatalogue.MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO);

    //Save changes to database.
    schemaAdapter.saveChanges();
  }

  //method
  private void createSchemaTimestampEntry(final String databaseName, SqlConnectionPool sqlConnectionPool) {

    final var now = Time.ofNow();
    final var sqlStatementToCreateSchemaTimestampEntry = //
    DATABASE_INITIALIZER_SQL_STATEMENT_CREATOR.createSqlStatementToCreateSchemaTimestampEntry(now);

    try (final var sqlConnection = sqlConnectionPool.borrowResource()) {
      sqlConnection.executeStatement("USE " + databaseName);
      sqlConnection.executeStatement(sqlStatementToCreateSchemaTimestampEntry);
    }
  }
}
