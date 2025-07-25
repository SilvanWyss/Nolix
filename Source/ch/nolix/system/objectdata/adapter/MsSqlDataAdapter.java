package ch.nolix.system.objectdata.adapter;

import ch.nolix.core.sql.connection.UncloseableSqlConnection;
import ch.nolix.core.sql.connectionpool.SqlConnectionPoolBuilder;
import ch.nolix.coreapi.resourcecontrol.resourcepool.IResourcePool;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.coreapi.sql.sqlproperty.SqlDatabaseEngine;
import ch.nolix.system.objectschema.adapter.MsSqlSchemaAdapter;
import ch.nolix.system.sqlmiddata.adapter.MsSqlDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;

public final class MsSqlDataAdapter extends AbstractDataAdapter {

  private final IResourcePool<? extends ISqlConnection> sqlConnectionPool;

  MsSqlDataAdapter(
    final String ipOrDomain,
    final int port,
    final String databaseName,
    final String loginName,
    final String loginPassword,
    final IEntityTypeSet entityTypeSet) {
    this(
      databaseName,
      entityTypeSet,
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
    final IEntityTypeSet entityTypeSet,
    final IResourcePool<? extends ISqlConnection> sqlConnectionPool) {
    this(databaseName, entityTypeSet, sqlConnectionPool, sqlConnectionPool.borrowResource());
  }

  private MsSqlDataAdapter(
    final String databaseName,
    final IEntityTypeSet entityTypeSet,
    final IResourcePool<? extends ISqlConnection> sqlConnectionPool,
    final ISqlConnection sqlConnection) {

    super(
      databaseName,
      entityTypeSet,
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
