package ch.nolix.core.sql.connectionpool;

import ch.nolix.core.resourcecontrol.resourcepool.AbstractWrapperResource;
import ch.nolix.core.sql.connection.AbstractSqlConnection;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.sql.connection.ISqlConnection;
import ch.nolix.coreapi.sql.model.ISqlRecord;
import ch.nolix.coreapi.sql.sqlproperty.SqlDatabaseEngine;

public final class WrapperSqlConnection
extends AbstractWrapperResource<WrapperSqlConnection, AbstractSqlConnection>
implements ISqlConnection {

  private WrapperSqlConnection(final AbstractSqlConnection abstractSqlConnection) {
    super(abstractSqlConnection);
  }

  public static WrapperSqlConnection forSqlConnection(final AbstractSqlConnection abstractSqlConnection) {
    return new WrapperSqlConnection(abstractSqlConnection);
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
  public IContainer<ISqlRecord> getRecordsFromQuery(final String query) {
    return getStoredResource().getRecordsFromQuery(query);
  }

  @Override
  public ISqlRecord getSingleRecordFromQuery(final String query) {
    return getStoredResource().getSingleRecordFromQuery(query);
  }
}
