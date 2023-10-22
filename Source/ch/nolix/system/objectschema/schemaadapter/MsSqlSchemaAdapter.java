//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.sql.SqlConnectionPool;

//class
public final class MsSqlSchemaAdapter extends SchemaAdapter {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.MSSQL;

  //constructor
  MsSqlSchemaAdapter(
      String databaseName,
      final ch.nolix.system.sqldatabaserawschema.schemaadapter.MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(databaseName, msSqlSchemaAdapter);
  }

  //static method
  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
      final String databaseName,
      final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(
        databaseName,
        ch.nolix.system.sqldatabaserawschema.schemaadapter.MsSqlSchemaAdapter
            .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
                databaseName,
                sqlConnectionPool));
  }

  //static method
  public static MsSqlSchemaAdapterBuilder toIpOrAddress(final String ipOrDomain) {
    return new MsSqlSchemaAdapterBuilder(ipOrDomain, DEFAULT_PORT);
  }

  //static method
  public static MsSqlSchemaAdapterBuilder toLocalhost() {
    return toIpOrAddress(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }
}
