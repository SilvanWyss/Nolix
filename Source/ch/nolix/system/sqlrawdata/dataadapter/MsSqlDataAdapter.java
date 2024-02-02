//package declaration
package ch.nolix.system.sqlrawdata.dataadapter;

import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.system.sqlrawdata.sqlsyntax.SqlSyntaxProvider;
import ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter;

//class
public final class MsSqlDataAdapter extends DataAdapter {

  //constructor
  private MsSqlDataAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(
      databaseName,
      sqlConnectionPool,
      MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
      new SqlSyntaxProvider());
  }

  //static method
  public static MsSqlDataAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlDataAdapter(databaseName, sqlConnectionPool);
  }
}
