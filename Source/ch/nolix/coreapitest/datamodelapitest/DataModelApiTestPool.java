//package declaration
package ch.nolix.coreapitest.datamodelapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.datamodelapitest.entityrequestapitest.EntityRequestApiTestPool;

//class
public final class DataModelApiTestPool extends TestPool {

  //constructor
  public DataModelApiTestPool() {
    super(new EntityRequestApiTestPool());
  }
}
