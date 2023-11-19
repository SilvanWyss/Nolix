//package declaration
package ch.nolix.systemtest.sqlschematest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.sqlschematest.sqlsyntaxtest.SqlSyntaxTestPool;

//class
public final class SqlSchemaTestPool extends TestPool {

  //constructor
  public SqlSchemaTestPool() {
    super(new SqlSyntaxTestPool());
  }
}
