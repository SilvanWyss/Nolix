/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.connectionpool;

import ch.nolix.base.resourcecontrol.resourcepool.AbstractWrapperResource;
import ch.nolix.base.sql.connection.AbstractSqlConnection;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public final class WrapperSqlConnection
extends AbstractWrapperResource<WrapperSqlConnection, AbstractSqlConnection>
implements ISqlConnection {
  private WrapperSqlConnection(final AbstractSqlConnection abstractSqlConnection) {
    super(abstractSqlConnection);
  }

  public static WrapperSqlConnection forSqlConnection(final AbstractSqlConnection abstractSqlConnection) {
    return new WrapperSqlConnection(abstractSqlConnection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeStatement(final String statement) {
    getStoredResource().executeStatement(statement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeStatements(final IContainer<String> statements) {
    getStoredResource().executeStatements(statements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeStatements(final String... statements) {
    getStoredResource().executeStatements(statements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SqlDatabaseEngine getDatabaseEngine() {
    return getStoredResource().getDatabaseEngine();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ISqlRecord> getRecordsFromQuery(final String query) {
    return getStoredResource().getRecordsFromQuery(query);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISqlRecord getSingleRecordFromQuery(final String query) {
    return getStoredResource().getSingleRecordFromQuery(query);
  }
}
