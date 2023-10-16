//package declaration
package ch.nolix.systemtest.sqldatabaserawdatatest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.sqldatabaserawdatatest.sqlsyntaxtest.SqlSyntaxTestPool;

//class
public class SqlDatabaseRawDataTestPool extends TestPool {

  //constructor
  public SqlDatabaseRawDataTestPool() {
    super(new SqlSyntaxTestPool());
  }
}
