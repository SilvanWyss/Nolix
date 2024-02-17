//package declaration
package ch.nolix.systemtest.sqlrawdatatest.statementcreatortest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class StatementCreatorTestPool extends TestPool {

  //constructor
  public StatementCreatorTestPool() {
    super(EntityStatementCreatorTest.class);
  }
}
