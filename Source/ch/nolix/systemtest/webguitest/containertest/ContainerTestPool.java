//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ContainerTestPool extends TestPool {

  //constructor
  public ContainerTestPool() {
    super(
      SingleContainerTest.class);
  }
}
