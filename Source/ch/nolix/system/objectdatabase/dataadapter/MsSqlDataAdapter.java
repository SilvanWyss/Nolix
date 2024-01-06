//package declaration
package ch.nolix.system.objectdatabase.dataadapter;

//own imports
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.sqlapi.sqlproperty.SqlDatabaseEngine;
import ch.nolix.system.objectdatabase.database.DataAdapter;
import ch.nolix.system.objectschema.schemaadapter.MsSqlSchemaAdapter;
import ch.nolix.system.sqlrawdatabase.dataandschemaadapter.MsSqlDataAndSchemaAdapter;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

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
      SqlConnectionPool
        .forIpOrDomain(ipOrDomain)
        .andPort(port)
        .andDatabase(databaseName)
        .withSqlDatabaseEngine(SqlDatabaseEngine.MSSQL)
        .usingLoginName(loginName)
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

  //static method
  public static MsSqlDataAdapterBuilder toIpOrAddress(final String ipOrDomain) {
    return new MsSqlDataAdapterBuilder(ipOrDomain);
  }

  //static method
  public static MsSqlDataAdapterBuilder toLocalHost() {
    return new MsSqlDataAdapterBuilder(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }

  //method
  @Override
  public DataAdapter getEmptyCopy() {
    return new MsSqlDataAdapter(getDatabaseName(), getSchema(), sqlConnectionPool);
  }
}
