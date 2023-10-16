//package declaration
package ch.nolix.system.sqldatabaserawdata.dataandschemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.rawdatabase.databaseandschemaadapter.BaseDataAndSchemaAdapter;
import ch.nolix.system.sqldatabaserawdata.dataadapter.MsSqlDataAdapter;
import ch.nolix.system.sqldatabaserawschema.schemaadapter.MsSqlSchemaAdapter;

//class
public final class MsSqlDataAndSchemaAdapter extends BaseDataAndSchemaAdapter {

  //static method
  public static MsSqlDataAndSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
      final String databaseName,
      final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlDataAndSchemaAdapter(
        MsSqlDataAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool),
        MsSqlSchemaAdapter.forDatabaseWithGivenNameUsingConnectionFromGivenPool(databaseName, sqlConnectionPool));
  }

  //constructor
  private MsSqlDataAndSchemaAdapter(
      final MsSqlDataAdapter msSqlDataAdapter,
      final MsSqlSchemaAdapter msSqlSchemaAdapter) {
    super(msSqlDataAdapter, msSqlSchemaAdapter);
  }
}
