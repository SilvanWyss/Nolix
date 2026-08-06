/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.adapter;

import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.system.middata.adapter.AbstractDataAdapter;
import ch.nolix.system.sqlmiddata.datawriter.DataWriter;
import ch.nolix.system.sqlmiddata.loader.DataReader;
import ch.nolix.system.sqlmiddata.schemaviewloader.DatabaseSchemaViewLoader;
import ch.nolix.systemapi.midschema.adapter.ISchemaReader;
import ch.nolix.systemapi.midschemainfo.model.DatabaseInfoDto;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractSqlDataAdapter extends AbstractDataAdapter {
  private static final DatabaseSchemaViewLoader DATABASE_SCHEMA_VIEW_LOADER = new DatabaseSchemaViewLoader();

  protected AbstractSqlDataAdapter(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISchemaReader schemaReader) {
    this(
      databaseName,
      DATABASE_SCHEMA_VIEW_LOADER.loadDatabaseSchemaView(databaseName, schemaReader),
      sqlConnection);

    schemaReader.close();
  }

  private AbstractSqlDataAdapter(
    final String databaseName,
    final DatabaseInfoDto databaseSchemaView,
    final ISqlConnection sqlConnection) {
    super(
      DataReader.forDatabaseNameAndDatabaseSchemaViewAndSqlConnection(
        databaseName,
        databaseSchemaView,
        sqlConnection),
      DataWriter.forDatabaseNameAndDatabaseViewAndSqlConnection(
        databaseName,
        databaseSchemaView,
        sqlConnection));
  }
}
