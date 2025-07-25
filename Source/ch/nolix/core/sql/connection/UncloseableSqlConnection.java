package ch.nolix.core.sql.connection;

import ch.nolix.core.programcontrol.closepool.UncloseableCloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.coreapi.sqlapi.modelapi.ISqlRecord;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

/**
 * A {@link UncloseableSqlConnection} is a {@link ISqlConnection} that is a
 * wrapper around another {@link ISqlConnection}. When the close method of a
 * {@link UncloseableSqlConnection} is called, the
 * {@link UncloseableSqlConnection} and its wrapped {@link ISqlConnection} will
 * not be closed.
 * 
 * @author Silvan Wyss
 * @version 2025-02-07
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
  public void executeStatement(final String statement, final String... statements) {
    sqlConnection.executeStatement(statement, statements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void executeStatements(final IContainer<String> statements) {
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
  public IContainer<ISqlRecord> getRecordsFromQuery(final String query) {
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
