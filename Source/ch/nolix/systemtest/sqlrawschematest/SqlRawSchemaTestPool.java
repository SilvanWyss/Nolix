//package declaration
package ch.nolix.systemtest.sqlrawschematest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.sqlrawschematest.databaseinitializertest.DatabaseInitializerTestPool;

//class
public final class SqlRawSchemaTestPool extends TestPool {

  //constructor
  public SqlRawSchemaTestPool() {
    super(new DatabaseInitializerTestPool());
  }
}
