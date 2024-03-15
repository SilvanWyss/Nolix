//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.core.sql.connectionpool.SqlConnectionPoolBuilder;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;
import ch.nolix.system.objectdata.data.DataAdapter;
import ch.nolix.system.objectschema.schemaadapter.MsSqlSchemaAdapter;
import ch.nolix.system.sqlrawdata.dataandschemaadapter.MsSqlDataAndSchemaAdapter;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

//class
public final class MsSqlDataAdapter extends DataAdapter {

  //attribute
  private final SqlConnectionPool sqlConnectionPool;

  //constructor
  MsSqlDataAdapter(
    final String ipOrDomain,
    final int port,
    final String databaseName,
    final String loginName,
    final String loginPassword,
    final ISchema schema) {
    this(
      databaseName,
      schema,
      SqlConnectionPoolBuilder
        .createConnectionPool()
        .forIpOrDomain(ipOrDomain)
        .andPort(port)
        .andDatabase(databaseName)
        .withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL)
        .andLoginName(loginName)
        .andLoginPassword(loginPassword));
  }

  //constructor
  private MsSqlDataAdapter(
    final String databaseName,
    final ISchema schema,
    final SqlConnectionPool sqlConnectionPool) {

    super(
      databaseName,
      MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
      schema,
      () -> MsSqlDataAndSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(
        databaseName,
        sqlConnectionPool));

    this.sqlConnectionPool = sqlConnectionPool;
  }

  //method
  @Override
  public DataAdapter getEmptyCopy() {
    return new MsSqlDataAdapter(getDatabaseName(), getSchema(), sqlConnectionPool);
  }
}
