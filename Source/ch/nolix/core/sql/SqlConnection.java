//package declaration
package ch.nolix.core.sql;

//Java imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public abstract class SqlConnection implements GroupCloseable {

  //attribute
  private final SqlDatabaseEngine sqlDatabaseEngine;

  //attribute
  private final Connection connection;

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //optional attribute
  private final SqlConnectionPool parentSqlConnectionPool;

  //constructor
  protected SqlConnection(final SqlDatabaseEngine sqlDatabaseEngine, final Connection connection) {

    GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();
    GlobalValidator.assertThat(connection).thatIsNamed(Connection.class).isNotNull();

    this.sqlDatabaseEngine = sqlDatabaseEngine;
    this.connection = connection;
    parentSqlConnectionPool = null;
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

    try {
      connection = DriverManager.getConnection("jdbc:sqlserver://" + ip + ':' + port, userName, userPassword);
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }

    parentSqlConnectionPool = null;
  }

  //constructor
  protected SqlConnection(
    final SqlDatabaseEngine sqlDatabaseEngine,
    final String ip,
    final int port,
    final String userName,
    final String userPassword,
    final SqlConnectionPool parentSqlConnectionPool) {

    GlobalValidator.assertThat(sqlDatabaseEngine).thatIsNamed(SqlDatabaseEngine.class).isNotNull();
    GlobalValidator.assertThat(parentSqlConnectionPool).thatIsNamed("parent SQLConnectionPool").isNotNull();

    GlobalValidator
      .assertThat(parentSqlConnectionPool)
      .thatIsNamed("parent SQLConnectionPool")
      .fulfills(SqlConnectionPool::isOpen);

    this.sqlDatabaseEngine = sqlDatabaseEngine;

    registerSqlDatabaseEngineDriver();

    try {
      connection = DriverManager.getConnection(
        "jdbc:sqlserver://" + ip + ':' + port,
        userName,
        userPassword);
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }

    this.parentSqlConnectionPool = parentSqlConnectionPool;
  }

  //method
  public final boolean belongsToSqlConnectionPool() {
    return (parentSqlConnectionPool != null);
  }

  //method
  @Override
  public final void close() {
    if (!belongsToSqlConnectionPool()) {
      GroupCloseable.super.close();
    } else {
      giveBackSelfToParentSqlConnectionPool();
    }
  }

  //method
  public final SqlConnection execute(final Iterable<String> sqlStatements) {

    try (final var statement = connection.createStatement()) {

      connection.setAutoCommit(false);

      for (final var sqlStatement : sqlStatements) {
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

    return this;
  }

  //method
  public final SqlConnection execute(final String sqlStatement) {
    return execute(ReadContainer.forElement(sqlStatement));
  }

  //method
  public final SqlConnection execute(final String sqlStatement, final String... sqlStatements) {
    return execute(ReadContainer.forElement(sqlStatement, sqlStatements));
  }

  //method
  public final List<String> getOneRecordFromQuery(final String query) {
    return getRecordsFromQuery(query).getStoredOne();
  }

  //method
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  public final LinkedList<List<String>> getRecordsFromQuery(final String query) {
    try (final var statement = connection.createStatement()) {
      return getRecorsFromStatement(query, statement);
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  //method
  public final IContainer<String> getRecordsAsStringsFromQuery(final String query) {
    return getRecordsFromQuery(query).toStrings();
  }

  //method
  public final SqlDatabaseEngine getSqlDatabaseEngine() {
    return sqlDatabaseEngine;
  }

  //method
  @Override
  public final boolean isClosed() {

    if (!belongsToSqlConnectionPool()) {
      return GroupCloseable.super.isClosed();
    }

    return parentSqlConnectionPool.isClosed();
  }

  //method
  @Override
  public final void noteClose() {
    internalCloseDirectly();
  }

  //method
  final void internalCloseDirectly() {
    try {
      connection.close();
    } catch (final SQLException sqlException) {
      throw WrapperException.forError(sqlException);
    }
  }

  //method declaration
  protected abstract String getSqlDatabaseEngineDriverClass();

  //method
  private LinkedList<List<String>> getRecorsFromStatement(
    final String query,
    final Statement statement)
  throws SQLException {
    try (final var resultSet = statement.executeQuery(query)) {
      return getRecordsFromResultSet(resultSet);
    }
  }

  //method
  private final LinkedList<List<String>> getRecordsFromResultSet(final ResultSet resultSet) throws SQLException {

    final var records = new LinkedList<List<String>>();

    final var columnCount = resultSet.getMetaData().getColumnCount();

    while (resultSet.next()) {

      final var entries = new ArrayList<String>();

      for (var i = 1; i <= columnCount; i++) {
        entries.add(resultSet.getString(i));
      }

      records.addAtEnd(entries);
    }

    return records;
  }

  //method
  private void giveBackSelfToParentSqlConnectionPool() {
    parentSqlConnectionPool.takeBackSqlConnection(this);
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
