//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MainTestPool extends TestPool {

  //constructor
  public MainTestPool() {
    super(BasicApplicationTest.class, BasicApplicationOnServerTest.class);
  }
}
