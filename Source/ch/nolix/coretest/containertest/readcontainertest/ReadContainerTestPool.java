//package declaraiton
package ch.nolix.coretest.containertest.readcontainertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ReadContainerTestPool extends TestPool {

  // constructor
  public ReadContainerTestPool() {
    super(ArrayReadContainerTest.class, IterableReadContainerTest.class, MultiReadContainerTest.class);
  }
}
