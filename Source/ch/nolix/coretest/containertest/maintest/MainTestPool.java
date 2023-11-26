//package declaration
package ch.nolix.coretest.containertest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MainTestPool extends TestPool {

  //constructor
  public MainTestPool() {
    super(ImmutableListTest.class, LinkedListTest.class);
  }
}
