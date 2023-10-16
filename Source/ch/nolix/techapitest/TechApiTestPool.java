//package declaration
package ch.nolix.techapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.techapitest.relationaldocapitest.RelationalDocApiTestPool;

//class
public final class TechApiTestPool extends TestPool {

  //constructor
  public TechApiTestPool() {
    super(new RelationalDocApiTestPool());
  }
}
