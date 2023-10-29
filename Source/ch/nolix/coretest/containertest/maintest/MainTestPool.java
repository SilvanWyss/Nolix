//package declaration
package ch.nolix.coretest.containertest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.containertest.singlecontainertest.SingleContainerTest;

//class
public final class MainTestPool extends TestPool {

  //constructor
  public MainTestPool() {
    super(
      GapMatrixTest.class,
      ImmutableListTest.class,
      LinkedListTest.class,
      MatrixTest.class,
      ReadContainerTest.class,
      SingleContainerTest.class);
  }
}
