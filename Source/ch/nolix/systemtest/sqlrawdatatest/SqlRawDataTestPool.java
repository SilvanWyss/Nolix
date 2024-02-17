//package declaration
package ch.nolix.systemtest.sqlrawdatatest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.sqlrawdatatest.querycreatortest.QueryCreatorTestPool;
import ch.nolix.systemtest.sqlrawdatatest.statementcreatortest.StatementCreatorTestPool;

//class
public class SqlRawDataTestPool extends TestPool {

  //constructor
  public SqlRawDataTestPool() {
    super(new QueryCreatorTestPool(), new StatementCreatorTestPool());
  }
}
