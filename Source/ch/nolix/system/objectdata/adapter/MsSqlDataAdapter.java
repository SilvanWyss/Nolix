/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.adapter;

import ch.nolix.base.sql.connection.UncloseableSqlConnection;
import ch.nolix.base.sql.connectionpool.SqlConnectionPoolBuilder;
import ch.nolix.baseapi.resourcecontrol.resourcepool.ResourcePool;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;
import ch.nolix.system.objectschema.adapter.MsSqlSchemaAdapter;
import ch.nolix.system.sqlmiddata.adapter.MsSqlDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;

/**
 * @author Silvan Wyss
 */
public final class MsSqlDataAdapter extends AbstractDataAdapter {
  private final ResourcePool<? extends ISqlConnection> sqlConnectionPool;

  private MsSqlDataAdapter(
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
        .forHost(ipOrDomain)
        .andPort(port)
        .andDatabase(databaseName)
        .withSqlDatabaseEngine(SqlDatabaseEngine.MS_SQL)
        .andLoginName(loginName)
        .andPassword(loginPassword));
  }

  private MsSqlDataAdapter(
    final String databaseName,
    final IEntityTypeSet entityTypeSet,
    final ResourcePool<? extends ISqlConnection> sqlConnectionPool) {
    this(databaseName, entityTypeSet, sqlConnectionPool, sqlConnectionPool.borrowResource());
  }

  private MsSqlDataAdapter(
    final String databaseName,
    final IEntityTypeSet entityTypeSet,
    final ResourcePool<? extends ISqlConnection> sqlConnectionPool,
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

  public static MsSqlDataAdapter toHostAndPortAndWithDatabaseNameAndUserNameAndUserPasswordAndEntityTypeSet(
    final String host,
    final int port,
    final String databaseName,
    final String userName,
    final String userPassword,
    final IEntityTypeSet entityTypeSet) {
    return new MsSqlDataAdapter(host, port, databaseName, userName, userPassword, entityTypeSet);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractDataAdapter createEmptyCopy() {
    return new MsSqlDataAdapter(getDatabaseName(), getSchema(), sqlConnectionPool);
  }
}
