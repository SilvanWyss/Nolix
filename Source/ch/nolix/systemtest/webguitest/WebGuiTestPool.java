//package declaration
package ch.nolix.systemtest.webguitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.webguitest.atomiccontroltest.AtomicControlTestPool;
import ch.nolix.systemtest.webguitest.containertest.ContainerTestPool;
import ch.nolix.systemtest.webguitest.itemmenutest.ItemMenuTestPool;
import ch.nolix.systemtest.webguitest.linearcontainertest.LinearContainerTestPool;
import ch.nolix.systemtest.webguitest.maintest.MainTestPool;

//class
public final class WebGuiTestPool extends TestPool {

  // constructor
  public WebGuiTestPool() {
    super(
        new ContainerTestPool(),
        new AtomicControlTestPool(),
        new ItemMenuTestPool(),
        new MainTestPool(),
        new LinearContainerTestPool());
  }
}
