//package declaration
package ch.nolix.system.sqlrawdatabase.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.baserawschema.databaseandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.sqlrawdatabase.dataadapter.MsSqlDataAdapter;
import ch.nolix.system.sqlrawschema.schemaadapter.MsSqlSchemaAdapter;

//class
public final class MsSqlDataAndSchemaAdapter extends BaseDataAndSchemaAdapter {

  //constructor
  private MsSqlDataAndSchemaAdapter(
    final MsSqlDataAdapter msSqlDataAdapter,
    final MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(msSqlDataAdapter, msSqlSchemaAdapter);
  }

  //static method
  public static MsSqlDataAndSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlDataAndSchemaAdapter(
      MsSqlDataAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
      MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool));
  }
}