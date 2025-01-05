package ch.nolix.system.sqlrawschema.databaseschemainspector;

import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlschema.schemaadapter.SchemaReader;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;
import ch.nolix.systemapi.sqlrawschemaapi.databaseinitializerapi.IDatabaseInspector;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MetaDataTableType;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaReader;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 */
public final class DatabaseInspector implements IDatabaseInspector {

  /**
   * @param schemaReader
   * @return the state of the database from the given schemaReader.
   */
  private static DatabaseState getDatabaseSchemaState(final ISchemaReader schemaReader) {

    if (schemaReader.tableExists(MetaDataTableType.DATABASE_PROPERTY.getQualifiedName())) {
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
    SchemaReader.forDatabaseWithNameAndSqlConnectionAndQueryCreator(databaseName, sqlConnection, queryCreator);

    return getDatabaseSchemaState(schemaReader);
  }
}
