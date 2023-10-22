//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.system.sqldatabasebasicschema.sqlsyntax.SchemaQueryCreator;
import ch.nolix.system.sqldatabasebasicschema.sqlsyntax.SchemaStatementCreator;

//class
public final class MsSqlSchemaAdapter extends SchemaAdapter {

  //constructor
  private MsSqlSchemaAdapter(final String databaseName, final SqlConnectionPool sqlConnectionPool) {
    super(databaseName, sqlConnectionPool, new SchemaQueryCreator(), new SchemaStatementCreator());
  }

  //static method
  public static MsSqlSchemaAdapter forDatabaseWithGivenNameUsingConnectionFromGivenPool(
      final String databaseName,
      final SqlConnectionPool sqlConnectionPool) {
    return new MsSqlSchemaAdapter(databaseName, sqlConnectionPool);
  }
}
