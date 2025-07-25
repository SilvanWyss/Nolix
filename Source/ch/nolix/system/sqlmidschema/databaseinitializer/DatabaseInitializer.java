package ch.nolix.system.sqlmidschema.databaseinitializer;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.midschema.databaseinitializer.AbstractDatabaseInitializer;
import ch.nolix.system.sqlschema.adapter.SchemaWriter;
import ch.nolix.systemapi.midschemaapi.databaseinitializerapi.DatabaseState;
import ch.nolix.systemapi.sqlmidschemaapi.databaseinitializerapi.IDatabaseInitializerTool;
import ch.nolix.systemapi.sqlmidschemaapi.databaseinitializerapi.IDatabaseStateAnalyser;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class DatabaseInitializer extends AbstractDatabaseInitializer {

  private static final IDatabaseStateAnalyser DATABASE_STATE_ANALYSER = new DatabaseStateAnalyser();

  private static final IDatabaseInitializerTool DATABASE_INITIALIZER_TOOL = new DatabaseInitializerTool();

  private final String databaseName;

  private final ISqlConnection sqlConnection;

  /**
   * Creates a new {@link DatabaseInitializer} with the given databaseName and
   * sqlConnection.
   * 
   * @param databaseName
   * @param sqlConnection
   * @throws RuntimeException if the given datbaseName is null or blank.
   * @throws RuntimeException if the given sqlConnection is null or closed.
   */
  private DatabaseInitializer(
    final String databaseName,
    final ISqlConnection sqlConnection) {

    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();
    ResourceValidator.assertIsOpen(sqlConnection);

    this.databaseName = databaseName;
    this.sqlConnection = sqlConnection;
  }

  /**
   * @param databaseName
   * @param sqlConnection
   * @return a new {@link DatabaseInitializer} with the given databaseName and
   *         sqlConnection.
   * @throws RuntimeException if the given datbaseName is null or blank.
   * @throws RuntimeException if the given sqlConnection is null or closed.
   */
  public static DatabaseInitializer forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new DatabaseInitializer(databaseName, sqlConnection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseState getDatabaseState() {
    return DATABASE_STATE_ANALYSER.getDatabasState(databaseName, sqlConnection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void initializeDatabaseWithInitialSchemaTimestamp(final ITime initialSchemaTimestamp) {

    final var schemaWriter = SchemaWriter.forDatabasNameAndSqlConnection(databaseName, sqlConnection);

    DATABASE_INITIALIZER_TOOL.initializeFixTables(schemaWriter);
    DATABASE_INITIALIZER_TOOL.saveSchemaTimestamp(initialSchemaTimestamp, sqlConnection, databaseName);
  }
}
