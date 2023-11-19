//package declaration
package ch.nolix.systemtest.sqlschematest.sqlsyntaxtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class SqlSyntaxTestPool extends TestPool {

  //constructor
  public SqlSyntaxTestPool() {
    super(SchemaStatementCreatorTest.class);
  }
}
