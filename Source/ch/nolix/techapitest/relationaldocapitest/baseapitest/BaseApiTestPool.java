//package declaration
package ch.nolix.techapitest.relationaldocapitest.baseapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class BaseApiTestPool extends TestPool {

  //constructor
  public BaseApiTestPool() {
    super(DataTypeTest.class);
  }
}
