//package declaration
package ch.nolix.systemtest.sqlschematest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class SqlSchemaTestPool extends TestPool {

  //constructor
  public SqlSchemaTestPool() {
    super(SchemaStatementCreatorTest.class);
  }
}
