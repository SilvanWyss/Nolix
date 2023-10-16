//package declaration
package ch.nolix.systemtest.sqldatabasebasicschematest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class SqlDatabaseBasicSchemaTestPool extends TestPool {

  //constructor
  public SqlDatabaseBasicSchemaTestPool() {
    super(SchemaStatementCreatorTest.class);
  }
}
