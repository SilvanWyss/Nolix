package ch.nolix.system.sqlmidschema.databaseinitializer;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlschema.adapter.SchemaReader;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;
import ch.nolix.systemapi.sqlmidschemaapi.databaseinitializerapi.IDatabaseStateAnalyser;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 */
public final class DatabaseStateAnalyser implements IDatabaseStateAnalyser {

  /**
   * @param schemaReader
   * @return the state of the database from the given schemaReader.
   */
  private static DatabaseState getDatabaseSchemaState(final ISchemaReader schemaReader) {

    if (schemaReader.tableExists(FixTableType.DATABASE_PROPERTY.getName())) {
      return DatabaseState.INITIALIZED;
    }

    if (schemaReader.loadFlatTables().isEmpty()) {
      return DatabaseState.UNINITIALIZED;
    }

    return DatabaseState.INVALID;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseState getDatabasState(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IQueryCreator queryCreator) {

    final var schemaReader = //
    SchemaReader.forDatabaseNameAndSqlConnectionAndQueryCreator(databaseName, sqlConnection, queryCreator);

    return getDatabaseSchemaState(schemaReader);
  }
}
