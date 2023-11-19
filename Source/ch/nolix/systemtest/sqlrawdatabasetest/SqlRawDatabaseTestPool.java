//package declaration
package ch.nolix.systemtest.sqlrawdatabasetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.sqlrawdatabasetest.sqlsyntaxtest.SqlSyntaxTestPool;

//class
public class SqlRawDatabaseTestPool extends TestPool {

  //constructor
  public SqlRawDatabaseTestPool() {
    super(new SqlSyntaxTestPool());
  }
}
