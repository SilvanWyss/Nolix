//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//own imports
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainer;

//class
public final class SingleContainerTest extends ContainerTest<ISingleContainer> {

  //method
  @Override
  protected ISingleContainer createTestUnit() {
    return new SingleContainer();
  }
}
