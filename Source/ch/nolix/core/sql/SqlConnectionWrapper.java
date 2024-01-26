//package declaration
package ch.nolix.core.sql;

//own imports
import ch.nolix.core.resourcecontrol.resourcepool.WrapperResource;

//class
public final class SqlConnectionWrapper extends WrapperResource<SqlConnectionWrapper, SqlConnection> {

  //constructor
  private SqlConnectionWrapper(final SqlConnection sqlConnection) {
    super(sqlConnection);
  }

  //static method
  public static SqlConnectionWrapper forSqlConnection(final SqlConnection sqlConnection) {
    return new SqlConnectionWrapper(sqlConnection);
  }

  //method
  public void execute(final String sqlStatement, final String... sqlStatements) {
    getStoredResource().execute(sqlStatement, sqlStatements);
  }

  //TODO: Create ISqlConnection and delete this method.
  //method
  public SqlConnection getStoredSqlConnection() {
    return getStoredResource();
  }
}
