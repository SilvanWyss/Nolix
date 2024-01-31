//package declaration
package ch.nolix.core.sql.connectionpool;

//own imports
import ch.nolix.core.resourcecontrol.resourcepool.WrapperResource;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

//class
public final class SqlConnectionWrapper
extends WrapperResource<SqlConnectionWrapper, SqlConnection>
implements ISqlConnection {

  //constructor
  private SqlConnectionWrapper(final SqlConnection sqlConnection) {
    super(sqlConnection);
  }

  //static method
  public static SqlConnectionWrapper forSqlConnection(final SqlConnection sqlConnection) {
    return new SqlConnectionWrapper(sqlConnection);
  }

  //method
  @Override
  public void executeStatement(final String statement, final String... statements) {
    getStoredResource().executeStatement(statement, statements);
  }

  //method
  @Override
  public void executeStatements(final IContainer<String> statements) {
    getStoredResource().executeStatements(statements);
  }

  //method
  @Override
  public SqlDatabaseEngine getDatabaseEngine() {
    return getStoredResource().getDatabaseEngine();
  }

  //method
  @Override
  public IContainer<? extends IContainer<String>> getRecordsFromQuery(final String query) {
    return getStoredResource().getRecordsFromQuery(query);
  }

  //method
  @Override
  public IContainer<String> getRecordsHavingSinlgeEntryFromQuery(final String query) {
    return getStoredResource().getRecordsHavingSinlgeEntryFromQuery(query);
  }

  //method
  @Override
  public IContainer<String> getSingleRecordFromQuery(final String query) {
    return getStoredResource().getSingleRecordFromQuery(query);
  }

  //method
  @Override
  public String getSingleRecordHavingSingleEntryFromQuery(final String query) {
    return getStoredResource().getSingleRecordHavingSingleEntryFromQuery(query);
  }
}
