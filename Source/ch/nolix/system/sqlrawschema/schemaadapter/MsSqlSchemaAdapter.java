//package declaration
package ch.nolix.system.sqlrawschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;

//class
public final class MsSqlSchemaAdapter extends SchemaAdapter {

  //constructor
  private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(
      databaseName,
      sqlConnectionPool,
      ch.nolix.system.sqlschema.schemaadapter.MsSqlSchemaAdapter
        .forDatabaseWithGivenNameUsingConnectionFromGivenPool(
          databaseName,
          sqlConnectionPool));
  }

  //static method
  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
  }
}
