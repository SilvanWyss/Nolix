package ch.nolix.system.sqlrawschema.databaseinitializer;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.resourcecontrolapi.resourcevalidatorapi.IResourceValidator;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.rawschema.databaseinitializer.AbstractDatabaseInitializer;
import ch.nolix.system.sqlrawschema.statementcreator.DatabaseInitializationStatementCreator;
import ch.nolix.system.sqlschema.adapter.SchemaWriter;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;
import ch.nolix.systemapi.sqlrawschemaapi.databaseinitializerapi.IDatabaseStateAnalyser;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.ColumnTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.DatabasePropertyTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.EntityIndexTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.MultiBackReferenceEntryTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.MultiReferenceEntryTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.MultiValueEntryTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.sqlschemadtocatalog.TableTableSqlSchemaDtoCatalog;
import ch.nolix.systemapi.sqlrawschemaapi.statementcreatorapi.IDatabaseInitializationStatementCreator;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class DatabaseInitializer extends AbstractDatabaseInitializer {

  private static final IDatabaseInitializationStatementCreator DATABASE_INITIALIZER_SQL_STATEMENT_CREATOR = //
  new DatabaseInitializationStatementCreator();

  private static final IResourceValidator RESOURCE_VALIDATOR = new ResourceValidator();

  private static final IDatabaseStateAnalyser DATABASE_STATE_ANALYSER = new DatabaseStateAnalyser();

  private final String databaseName;

  private final ISqlConnection sqlConnection;

  private final IQueryCreator sqlSchemaQueryCreator;

  /**
   * Creates a new {@link DatabaseInitializer} with the given databaseName,
   * sqlConnection and sqlSchemaQueryCreator.
   * 
   * @param databaseName
   * @param sqlConnection
   * @param sqlSchemaQueryCreator
   * @throws RuntimeException if the given datbaseName is null or blank.
   * @throws RuntimeException if the given sqlConnection is null or closed.
   * @throws RuntimeException if the given sqlSchemaQueryCreator is null.
   */
  private DatabaseInitializer(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IQueryCreator sqlSchemaQueryCreator) {

    GlobalValidator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();
    RESOURCE_VALIDATOR.assertIsOpen(sqlConnection);
    GlobalValidator.assertThat(sqlSchemaQueryCreator).thatIsNamed("sql schema query creator").isNotNull();

    this.databaseName = databaseName;
    this.sqlConnection = sqlConnection;
    this.sqlSchemaQueryCreator = sqlSchemaQueryCreator;
  }

  /**
   * @param databaseName
   * @param sqlConnection
   * @param sqlSchemaQueryCreator
   * @return a new {@link DatabaseInitializer} with the given databaseName,
   *         sqlConnection and sqlSchemaQueryCreator.
   * @throws RuntimeException if the given datbaseName is null or blank.
   * @throws RuntimeException if the given sqlConnection is null or closed.
   * @throws RuntimeException if the given sqlSchemaQueryCreator is null.
   */
  public static DatabaseInitializer forDatabaseNameAndSqlConnectionAndSqlSchemaQueryCreator(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IQueryCreator sqlSchemaQueryCreator) {
    return new DatabaseInitializer(databaseName, sqlConnection, sqlSchemaQueryCreator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseState getDatabaseState() {
    return DATABASE_STATE_ANALYSER.getDatabasState(databaseName, sqlConnection, sqlSchemaQueryCreator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initializeDatabaseWithInitialSchemaTimestamp(final ITime initialSchemaTimestamp) {

    initializeFixTables();

    saveInitialTimestamp(initialSchemaTimestamp);
  }

  private void initializeFixTables() {

    final var schemaWriter = SchemaWriter.forDatabasNameAndSqlConnection(databaseName, sqlConnection);

    initializeFixTables(schemaWriter);
  }

  private void initializeFixTables(final ISchemaWriter schemaWriter) {

    //Adds meta data tables.
    schemaWriter.addTable(DatabasePropertyTableSqlSchemaDtoCatalog.DATABASE_PROPERTY_TABLE_SQL_DTO);

    //Adds schema tables.
    schemaWriter.addTable(TableTableSqlSchemaDtoCatalog.TABLE_TABLE_SQL_DTO);
    schemaWriter.addTable(ColumnTableSqlSchemaDtoCatalog.COLUMN_TABLE_SQL_DTO);

    //Adds index tables.
    schemaWriter.addTable(EntityIndexTableSqlSchemaDtoCatalog.ENTITY_INDEX_SQL_SCHEMA_TABLE_DTO);

    //Adds multi-entry tables.
    schemaWriter.addTable(MultiValueEntryTableSqlSchemaDtoCatalog.MULTI_VALUE_ENTRY_TABLE_SQL_DTO);
    schemaWriter.addTable(MultiReferenceEntryTableSqlSchemaDtoCatalog.MULTI_REFERENCE_ENTRY_TABLE_SQL_DTO);
    schemaWriter.addTable(MultiBackReferenceEntryTableSqlSchemaDtoCatalog.MULTI_BACK_REFERENCE_ENTRY_TABLE_SQL_DTO);

    //Save changes to database.
    schemaWriter.saveChanges();
  }

  private void saveInitialTimestamp(final ITime initialSchemaTimestamp) {

    sqlConnection.executeStatement("USE " + databaseName);

    final var query = //
    DATABASE_INITIALIZER_SQL_STATEMENT_CREATOR.createStatementToCreateSchemaTimestampEntry(initialSchemaTimestamp);

    sqlConnection.executeStatement(query);
  }
}
