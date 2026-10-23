/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.adapter;

import ch.nolix.base.sql.connection.UncloseableSqlConnection;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.midschema.adapter.AbstractSchemaAdapter;
import ch.nolix.system.sqlmidschema.databaseinitializer.DatabaseInitializer;
import ch.nolix.system.sqlmidschema.schemareader.SchemaReader;
import ch.nolix.system.sqlmidschema.schemawriter.SchemaWriter;

/**
 * @author Silvan Wyss
 */
public final class SqlSchemaAdapter extends AbstractSchemaAdapter {
  private SqlSchemaAdapter(final String databaseName, final ISqlConnection sqlConnection) {
    super(
      DatabaseInitializer.forDatabaseNameAndSqlConnection(
        databaseName,
        UncloseableSqlConnection.forSqlConnection(sqlConnection)),
      () -> //
      SchemaReader.forDatabaseNameAndSqlConnection(
        databaseName,
        sqlConnection),
      () -> SchemaWriter.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));
  }

  /**
   * @param databaseName
   * @param sqlConnection
   * @return a new {@link SqlSchemaAdapter} for the database with the given
   *         databaseName using the given sqlConnection
   */
  public static SqlSchemaAdapter forDatabaseNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new SqlSchemaAdapter(databaseName, sqlConnection);
  }
}
