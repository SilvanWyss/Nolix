package ch.nolix.system.objectdata.adapter;

import ch.nolix.core.sql.connection.UncloseableSqlConnection;
import ch.nolix.core.sql.connectionpool.SqlConnectionPoolBuilder;
import ch.nolix.coreapi.resourcecontrolapi.resourcepoolapi.IResourcePool;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;
import ch.nolix.system.objectdata.model.AbstractDataAdapter;
import ch.nolix.system.objectschema.adapter.MsSqlSchemaAdapter;
import ch.nolix.system.sqlrawdata.adapter.MsSqlDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdataapi.schemamodelapi.ISchema;

public final class MsSqlDataAdapter extends AbstractDataAdapter {

  private final IResourcePool<? extends ISqlConnection> sqlConnectionPool;

  MsSqlDataAdapter(
    final String ipOrDomain,
    final int port,
    final String databaseName,
    final String loginName,
    final String loginPassword,
    final ISchema schema) {
    this(
      databaseName,
      schema,
      SqlConnectionPoolBuilder
        .createConnectionPool()
        .forIpOrDomain(ipOrDomain)
        .andPort(port)
        .andDatabase(databaseName)
        .withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL)
        .andLoginName(loginName)
        .andLoginPassword(loginPassword));
  }

  private MsSqlDataAdapter(
    final String databaseName,
    final ISchema schema,
    final IResourcePool<? extends ISqlConnection> sqlConnectionPool) {
    this(databaseName, schema, sqlConnectionPool, sqlConnectionPool.borrowResource());
  }

  private MsSqlDataAdapter(
    final String databaseName,
    final ISchema schema,
    final IResourcePool<? extends ISqlConnection> sqlConnectionPool,
    final ISqlConnection sqlConnection) {

    super(
      databaseName,
      schema,
      MsSqlSchemaAdapter.forDatabaseNameAndSqlConnection(
        databaseName,
        UncloseableSqlConnection.forSqlConnection(sqlConnection)),
      () -> MsSqlDataAdapterAndSchemaReader.forDatabaseNameAndSqlConnection(databaseName, sqlConnection));

    this.sqlConnectionPool = sqlConnectionPool;
  }

  @Override
  public AbstractDataAdapter createEmptyCopy() {
    return new MsSqlDataAdapter(getDatabaseName(), getSchema(), sqlConnectionPool);
  }
}
