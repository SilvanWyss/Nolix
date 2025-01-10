package ch.nolix.core.sql.connectionpool;

import ch.nolix.core.resourcecontrol.resourcepool.WrapperResource;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.coreapi.sqlapi.modelapi.IRecord;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;

public final class WrapperSqlConnection
extends WrapperResource<WrapperSqlConnection, SqlConnection>
implements ISqlConnection {

  private WrapperSqlConnection(final SqlConnection sqlConnection) {
    super(sqlConnection);
  }

  public static WrapperSqlConnection forSqlConnection(final SqlConnection sqlConnection) {
    return new WrapperSqlConnection(sqlConnection);
  }

  @Override
  public void executeStatement(final String statement, final String... statements) {
    getStoredResource().executeStatement(statement, statements);
  }

  @Override
  public void executeStatements(final IContainer<String> statements) {
    getStoredResource().executeStatements(statements);
  }

  @Override
  public SqlDatabaseEngine getDatabaseEngine() {
    return getStoredResource().getDatabaseEngine();
  }

  @Override
  public IContainer<IRecord> getRecordsFromQuery(final String query) {
    return getStoredResource().getRecordsFromQuery(query);
  }

  @Override
  public IContainer<String> getRecordsHavingSinlgeEntryFromQuery(final String query) {
    return getStoredResource().getRecordsHavingSinlgeEntryFromQuery(query);
  }

  @Override
  public IContainer<String> getSingleRecordFromQuery(final String query) {
    return getStoredResource().getSingleRecordFromQuery(query);
  }

  @Override
  public String getSingleRecordHavingSingleEntryFromQuery(final String query) {
    return getStoredResource().getSingleRecordHavingSingleEntryFromQuery(query);
  }
}
