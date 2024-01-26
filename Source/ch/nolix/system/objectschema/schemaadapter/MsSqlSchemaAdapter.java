//package declaration
package ch.nolix.system.objectschema.schemaadapter;

import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;

//class
public final class MsSqlSchemaAdapter extends SchemaAdapter {

  //constant
  public static final int DEFAULT_PORT = PortCatalogue.MS_SQL;

  //constructor
  MsSqlSchemaAdapter(
    String databaseName,
    final ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(databaseName, msSqlSchemaAdapter);
  }

  //static method
  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(
      databaseName,
      ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter
        .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
          databaseName,
          sqlConnectionPool));
  }

  //static method
  public static MsSqlSchemaAdapterBuilder toIpOrAddress(final String ipOrDomain) {
    return new MsSqlSchemaAdapterBuilder(ipOrDomain);
  }

  //static method
  public static MsSqlSchemaAdapterBuilder toLocalhost() {
    return toIpOrAddress(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }
}
