/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.net.netconstant.IPv4Catalog;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.sql.connection.ISqlConnection;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractSqlConnection implements ISqlConnection {
  private final SqlDatabaseEngine sqlDatabaseEngine;

  private final Connection connection;

  private final ICloseController closeController = CloseController.forElement(this);

  protected AbstractSqlConnection(final SqlDatabaseEngine sqlDatabaseEngine, final Connection connection) {
    Validator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();
    Validator.assertThat(connection).thatIsNamed(Connection.class).isNotNull();

    this.sqlDatabaseEngine = sqlDatabaseEngine;
    this.connection = connection;
  }

  protected AbstractSqlConnection(
    final SqlDatabaseEngine sqlDatabaseEngine,
    final int port,
    final String userName,
    final String userPassword) {
    this(
      sqlDatabaseEngine,
      IPv4Catalog.LOOP_BACK_ADDRESS,
      port,
      userName,
      userPassword);
  }

  protected AbstractSqlConnection(
    final SqlDatabaseEngine sqlDatabaseEngine,
    final String ip,
    final int port,
    final String userName,
    final String userPassword) {
    Validator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

    this.sqlDatabaseEngine = sqlDatabaseEngine;

    registerSqlDatabaseEngineDriver();

    final var properties = new Properties();
    properties.put("user", userName);
    properties.put("password", userPassword);
    properties.put("encrypt", "true");
    properties.put("trustServerCertificate", "true");

    try {
      connection = DriverManager.getConnection("jdbc:sqlserver://" + ip + ':' + port, properties);
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void executeStatement(final String statement) {
    executeStatements(statement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void executeStatements(final IWellOrderContainer<String> statements) {
    try (final var statement = connection.createStatement()) {
      connection.setAutoCommit(false);

      for (final var sqlStatement : statements) {
        statement.addBatch(sqlStatement);
      }

      statement.executeBatch();
      connection.commit();
    } catch (final SQLException sqlException) {
      try {
        connection.rollback();
      } catch (final SQLException sqlException2) {
        throw WrapperException.forError(sqlException2);
      }

      throw WrapperException.forError(sqlException);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void executeStatements(final String... statements) {
    final var statementsContainer = ContainerView.forArray(statements);

    executeStatements(statementsContainer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final SqlDatabaseEngine getDatabaseEngine() {
    return sqlDatabaseEngine;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IWellOrderContainer<ISqlRecord> getRecordsFromQuery(final String query) {
    try (final var statement = connection.createStatement()) {
      return SqlConnectionHelper.getRecordsFromStatement(query, statement);
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ISqlRecord getSingleRecordFromQuery(final String query) {
    return getRecordsFromQuery(query).getStoredOne();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    try {
      connection.close();
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  protected abstract String getSqlDatabaseEngineDriverClass();

  private void registerSqlDatabaseEngineDriver() {
    try {
      Class.forName( //NOSONAR: Dynamic class loading is needed to gain driver class.
        getSqlDatabaseEngineDriverClass());
    } catch (final ClassNotFoundException classNotFoundException) {
      throw WrapperException.forError(classNotFoundException);
    }
  }
}
