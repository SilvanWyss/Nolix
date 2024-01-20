//package declaration
package ch.nolix.systemtest.sqlrawschematest.databaseinitializertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DatabaseInitializerTestPool extends TestPool {

  //constructor
  public DatabaseInitializerTestPool() {
    super(DatabaseInitializerSqlStatementCreatorTest.class);
  }
}
