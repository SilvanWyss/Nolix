//package declaration
package ch.nolix.coretest.containertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.containertest.arraylisttest.ArrayListTestPool;
import ch.nolix.coretest.containertest.immutablelisttest.ImmutableListTestPool;
import ch.nolix.coretest.containertest.linkedlisttest.LinkedListTestPool;
import ch.nolix.coretest.containertest.matrixtest.MatrixTestPool;
import ch.nolix.coretest.containertest.readcontainertest.ReadContainerTestPool;

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
      new ArrayListTestPool(),
      new ImmutableListTestPool(),
      new LinkedListTestPool(),
      new MatrixTestPool(),
      new ReadContainerTestPool());
  }
}
