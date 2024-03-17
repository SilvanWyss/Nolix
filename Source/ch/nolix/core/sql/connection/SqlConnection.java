//package declaration
package ch.nolix.core.sql.connection;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//own imports
import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public abstract class SqlConnection implements ISqlConnection {

  //attribute
  private final SqlDatabaseEngine sqlDatabaseEngine;

  //attribute
  private final Connection connection;

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //constructor
  protected SqlConnection(final SqlDatabaseEngine sqlDatabaseEngine, final Connection connection) {

    GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();
    GlobalValidator.assertThat(connection).thatIsNamed(Connection.class).isNotNull();

    this.sqlDatabaseEngine = sqlDatabaseEngine;
    this.connection = connection;
  }

  //constructor
  protected SqlConnection(
    final SqlDatabaseEngine sqlDatabaseEngine,
    final int port,
    final String userName,
    final String userPassword) {
    this(
      sqlDatabaseEngine,
      IPv4Catalogue.LOOP_BACK_ADDRESS,
      port,
      userName,
      userPassword);
  }

  //constructor
  protected SqlConnection(
    final SqlDatabaseEngine sqlDatabaseEngine,
    final String ip,
    final int port,
    final String userName,
    final String userPassword) {

    GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();

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

  //method
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

  //method
  @Override
  public final void executeStatement(final String statement, final String... statements) {
    executeStatements(ReadContainer.forElement(statement, statements));
  }

  //method
  @Override
  public final SqlDatabaseEngine getDatabaseEngine() {
    return sqlDatabaseEngine;
  }

  //method
  @Override
  public final IContainer<? extends IContainer<String>> getRecordsFromQuery(final String query) {
    try (final var statement = connection.createStatement()) {
      return getRecordsFromStatement(query, statement);
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  //method
  @Override
  public final IContainer<String> getRecordsHavingSinlgeEntryFromQuery(final String query) {
    return getRecordsFromQuery(query).to(IContainer::getStoredOne);
  }

  //method
  @Override
  public final IContainer<String> getSingleRecordFromQuery(final String query) {
    return getRecordsFromQuery(query).getStoredOne();
  }

  @Override
  public String getSingleRecordHavingSingleEntryFromQuery(final String query) {
    return getRecordsFromQuery(query).getStoredOne().getStoredOne();
  }

  //method
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public final void noteClose() {
    try {
      connection.close();
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  //method declaration
  protected abstract String getSqlDatabaseEngineDriverClass();

  //method
  private IContainer<? extends IContainer<String>> getRecordsFromStatement(
    final String query,
    final Statement statement)
  throws SQLException {
    try (final var resultSet = statement.executeQuery(query)) {
      return getRecordsFromResultSet(resultSet);
    }
  }

  //method
  private final IContainer<? extends IContainer<String>> getRecordsFromResultSet(final ResultSet resultSet)
  throws SQLException {

    final var records = new LinkedList<IContainer<String>>();

    final var columnCount = resultSet.getMetaData().getColumnCount();

    while (resultSet.next()) {

      final ArrayList<String> entries = ArrayList.withInitialCapacity(columnCount);

      for (var i = 1; i <= columnCount; i++) {

        final var entry = resultSet.getString(i);

        if (entry == null) {
          entries.addAtEnd("NULL");
        } else {
          entries.addAtEnd(entry);
        }
      }

      records.addAtEnd(entries);
    }

    return records;
  }

  //method
  private void registerSqlDatabaseEngineDriver() {
    try {
      Class.forName( //NOSONAR: Dynamic class loading is needed to gain driver class.
        getSqlDatabaseEngineDriverClass());
    } catch (final ClassNotFoundException classNotFoundException) {
      throw WrapperException.forError(classNotFoundException);
    }
  }
}
