package ch.nolix.core.sql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.net.netconstant.IPv4Catalog;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.coreapi.sql.sqlproperty.SqlDatabaseEngine;

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

  @Override
  public final void executeStatements(final IContainer<String> statements) {
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

  @Override
  public final void executeStatement(final String statement, final String... statements) {
    final var allStatements = ContainerView.forElementAndArray(statement, statements);

    executeStatements(allStatements);
  }

  @Override
  public final SqlDatabaseEngine getDatabaseEngine() {
    return sqlDatabaseEngine;
  }

  @Override
  public final IContainer<ISqlRecord> getRecordsFromQuery(final String query) {
    try (final var statement = connection.createStatement()) {
      return getRecordsFromStatement(query, statement);
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  @Override
  public final ISqlRecord getSingleRecordFromQuery(final String query) {
    return getRecordsFromQuery(query).getStoredOne();
  }

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final void noteClose() {
    try {
      connection.close();
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  protected abstract String getSqlDatabaseEngineDriverClass();

  private IContainer<ISqlRecord> getRecordsFromStatement(
    final String query,
    final Statement statement)
  throws SQLException {
    try (final var resultSet = statement.executeQuery(query)) {
      return getRecordsFromResultSet(resultSet);
    }
  }

  private final IContainer<ISqlRecord> getRecordsFromResultSet(final ResultSet resultSet)
  throws SQLException {
    final ILinkedList<ISqlRecord> sqlRecords = LinkedList.createEmpty();
    final var columnCount = resultSet.getMetaData().getColumnCount();
    var index = 1;

    while (resultSet.next()) {
      final ArrayList<String> entries = ArrayList.withInitialCapacity(columnCount);

      for (var i = 1; i <= columnCount; i++) {
        final var entry = resultSet.getString(i);

        if (entry == null) {

          //TODO: Use List that can store null values
          entries.addAtEnd("NULL");
        } else {
          entries.addAtEnd(entry);
        }
      }

      final var sqlRecord = ch.nolix.core.sql.model.SqlRecord.withOneBasedIndexAndValues(index, entries);

      sqlRecords.addAtEnd(sqlRecord);
      index++;
    }

    return sqlRecords;
  }

  private void registerSqlDatabaseEngineDriver() {
    try {
      Class.forName( //NOSONAR: Dynamic class loading is needed to gain driver class.
        getSqlDatabaseEngineDriverClass());
    } catch (final ClassNotFoundException classNotFoundException) {
      throw WrapperException.forError(classNotFoundException);
    }
  }
}
