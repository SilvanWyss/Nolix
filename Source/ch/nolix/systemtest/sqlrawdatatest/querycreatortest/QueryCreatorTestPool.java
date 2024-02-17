//package declaration
package ch.nolix.systemtest.sqlrawdatatest.querycreatortest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class QueryCreatorTestPool extends TestPool {

  //constructor
  public QueryCreatorTestPool() {
    super(
      EntityQueryCreatorTest.class,
      MultiReferenceQueryCreatorTest.class,
      MultiValueQueryCreatorTest.class);
  }
}
