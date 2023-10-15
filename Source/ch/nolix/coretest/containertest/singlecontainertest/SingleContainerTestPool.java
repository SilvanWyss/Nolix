//package declaration
package ch.nolix.coretest.containertest.singlecontainertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class SingleContainerTestPool extends TestPool {

  // constructor
  public SingleContainerTestPool() {
    super(SingleContainerTest.class);
  }
}
