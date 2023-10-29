//package declaration
package ch.nolix.system.sqldatabaserawdata.dataadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabaserawdata.sqlsyntax.SqlSyntaxProvider;
import ch.nolix.system.sqldatabaserawschema.schemaadapter.MsSqlSchemaAdapter;

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
