package ch.nolix.system.sqlrawschema.databaseinitializer;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlrawschema.columntable.ColumnTableSqlDtoCatalog;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.DatabasePropertyTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.EntityHeadTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.MultiBackReferenceEntryTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.MultiReferenceEntryTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.MultiValueEntryTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.TableTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaAdapter;

final class InternalDatabaseInitializer {

  private static final DatabaseInitializerSqlStatementCreator DATABASE_INITIALIZER_SQL_STATEMENT_CREATOR = //
  new DatabaseInitializerSqlStatementCreator();

  public void initializeDatabase(
    final String databaseName,
    final ISchemaAdapter schemaAdapter,
    final SqlConnectionPool sqlConnectionPool) {

    createFixTables(schemaAdapter);

    createSchemaTimestampEntry(databaseName, sqlConnectionPool);
  }

  private void createFixTables(final ISchemaAdapter schemaAdapter) {

    //Adds metadata tables.
    schemaAdapter.addTable(DatabasePropertyTableSqlSchemaDtoCatalog.DATABASE_PROPERTY_TABLE_SQL_DTO);

    //Adds schema tables.
    schemaAdapter.addTable(TableTableSqlSchemaDtoCatalog.TABLE_TABLE_SQL_DTO);
    schemaAdapter.addTable(ColumnTableSqlDtoCatalog.COLUMN_TABLE_SQL_DTO);

    //Adds index tables.
    schemaAdapter.addTable(EntityHeadTableSqlSchemaDtoCatalog.ENTITY_HEAD_TABLE_DTO);

    //Adds multi-entry tables.
    schemaAdapter.addTable(MultiValueEntryTableSqlSchemaDtoCatalog.MULTI_VALUE_ENTRY_TABLE_SQL_DTO);
    schemaAdapter.addTable(MultiReferenceEntryTableSqlSchemaDtoCatalog.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO);
    schemaAdapter.addTable(MultiBackReferenceEntryTableSqlSchemaDtoCatalog.MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO);

    //Save changes to database.
    schemaAdapter.saveChanges();
  }

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
