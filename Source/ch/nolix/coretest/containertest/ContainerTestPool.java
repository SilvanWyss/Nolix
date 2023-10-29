//package declaration
package ch.nolix.coretest.containertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.containertest.maintest.MainTestPool;
import ch.nolix.coretest.containertest.readcontainertest.ReadContainerTestPool;
import ch.nolix.coretest.containertest.singlecontainertest.SingleContainerTestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class ContainerTestPool extends TestPool {

  //constructor
  /**
   * Creates a new {@link ContainerTestPool}.
   */
  public ContainerTestPool() {
    super(
      new MainTestPool(),
      new ReadContainerTestPool(),
      new SingleContainerTestPool());
  }
}
