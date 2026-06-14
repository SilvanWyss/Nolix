/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.connection;

import ch.nolix.base.resourcecontrol.closecontroller.UncloseableCloseController;
import ch.nolix.base.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * A {@link UncloseableSqlConnection} is a {@link ISqlConnection} that is a
 * wrapper around another {@link ISqlConnection}. When the close method of a
 * {@link UncloseableSqlConnection} is called, the
 * {@link UncloseableSqlConnection} and its wrapped {@link ISqlConnection} will
 * not be closed.
 * 
 * @author Silvan Wyss
 */
public final class UncloseableSqlConnection implements ISqlConnection {
  private final ISqlConnection sqlConnection;

  /**
   * Creates a new {@link UncloseableSqlConnection} for the given sqlConnection.
   * 
   * @param sqlConnection
   * @throws RuntimeException if the given sqlConnection is null or not open.
   */
  private UncloseableSqlConnection(final ISqlConnection sqlConnection) {
    ResourceValidator.assertIsOpen(sqlConnection);

    this.sqlConnection = sqlConnection;
  }

  /**
   * @param sqlConnection
   * @return either a new {@link UncloseableSqlConnection} for the given
   *         sqlConnection or the given sqlConnection if the given sqlConnection
   *         is a {@link UncloseableSqlConnection}.
   * @throws RuntimeException if the given sqlConnection is null or not open.
   */
  public static UncloseableSqlConnection forSqlConnection(final ISqlConnection sqlConnection) {
    if (sqlConnection instanceof final UncloseableSqlConnection uncloseableSqlConnection) {
      return uncloseableSqlConnection;
    }

    return new UncloseableSqlConnection(sqlConnection);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeStatement(final String statement) {
    sqlConnection.executeStatement(statement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeStatements(final IWellOrderContainer<String> statements) {
    sqlConnection.executeStatements(statements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeStatements(final String... statements) {
    sqlConnection.executeStatements(statements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SqlDatabaseEngine getDatabaseEngine() {
    return sqlConnection.getDatabaseEngine();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<ISqlRecord> getRecordsFromQuery(final String query) {
    return sqlConnection.getRecordsFromQuery(query);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISqlRecord getSingleRecordFromQuery(final String query) {
    return sqlConnection.getSingleRecordFromQuery(query);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICloseController getStoredCloseController() {
    return new UncloseableCloseController();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    //Does nothing.
  }
}
