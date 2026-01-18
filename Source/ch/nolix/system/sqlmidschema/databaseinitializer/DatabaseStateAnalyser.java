/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.databaseinitializer;

import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.system.sqlschema.adapter.SchemaReader;
import ch.nolix.systemapi.midschema.databaseinitializer.DatabaseState;
import ch.nolix.systemapi.sqlmidschema.databaseinitializer.IDatabaseStateAnalyser;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlschema.adapter.ISchemaReader;

/**
 * @author Silvan Wyss
 */
public final class DatabaseStateAnalyser implements IDatabaseStateAnalyser {
  /**
   * @param schemaReader
   * @return the state of the database from the given schemaReader.
   */
  private static DatabaseState getDatabaseSchemaState(final ISchemaReader schemaReader) {
    if (schemaReader.tableExists(FixTable.DATABASE_PROPERTY.getName())) {
      return DatabaseState.INITIALIZED;
    }

    if (!schemaReader.tableExist()) {
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
    final ISqlConnection sqlConnection) {
    final var schemaReader = //
    SchemaReader.forDatabaseNameAndSqlConnection(databaseName, sqlConnection);

    return getDatabaseSchemaState(schemaReader);
  }
}
